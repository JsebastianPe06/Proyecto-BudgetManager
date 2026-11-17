package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.domain.domainTransaction.EvaluadorDeConsumo;
import com.Proyecto.BudgetManager.dto.MonthlyReport;
import com.Proyecto.BudgetManager.entity.TransactionEntity;
import com.Proyecto.BudgetManager.enums.TransactionType;
import com.Proyecto.BudgetManager.repository.TransactionRepository;
import com.Proyecto.BudgetManager.repository.SavingsTransactionRepository; // NUEVO
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    private final TransactionRepository transactions;
    private final SavingsTransactionRepository savingsTransactions; // CAMBIO

    public ReportService(TransactionRepository transactions, SavingsTransactionRepository savingsTransactions) {
        this.transactions = transactions;
        this.savingsTransactions = savingsTransactions;
    }

    public MonthlyReport generateMonthlyReport(String email, int month, int year) {
        YearMonth ym = YearMonth.of(year, month);
        String startDate = ym.atDay(1).toString();
        String endDate = ym.atEndOfMonth().toString();
        BigDecimal income = transactions.sumAmountByUserAndTypeAndDateRange(
                email, TransactionType.INGRESO, startDate, endDate);
        if (income == null) income = BigDecimal.ZERO;
        BigDecimal expenses = transactions.sumAmountByUserAndTypeAndDateRange(
                email, TransactionType.GASTO, startDate, endDate);
        if (expenses == null) expenses = BigDecimal.ZERO;
        BigDecimal totalSaved = savingsTransactions.sumSavingsByUserAndDateRange(email, startDate, endDate);
        if (totalSaved == null) totalSaved = BigDecimal.ZERO;
        BigDecimal balance = income.subtract(expenses).subtract(totalSaved);
        List<Object[]> results = transactions.sumExpensesByCategory(email, startDate, endDate);
        Map<String, BigDecimal> expensesByCategory = new HashMap<>();
        for (Object[] row : results) {
            String category = row[0].toString();
            BigDecimal amount = (BigDecimal) row[1];
            expensesByCategory.put(category, amount);
        }
        return new MonthlyReport(month, year, income, expenses, totalSaved, balance, expensesByCategory);
    }

    public EvaluadorDeConsumo.NivelConciencia getNivelConciencia(String email, int month, int year) {
        YearMonth ym = YearMonth.of(year, month);
        String startDate = ym.atDay(1).toString();
        String endDate = ym.atEndOfMonth().toString();
        List<TransactionEntity> gastos = transactions.findByUserEmailAndTransactionDateBetweenOrderByTransactionDateDesc(
                email, startDate, endDate).stream()
                .filter(t -> t.getType() == TransactionType.GASTO && !t.isPlanned())
                .toList();
        List<Boolean> satisfacciones = gastos.stream().map(TransactionEntity::isSatisfied).toList();
        return EvaluadorDeConsumo.evaluar(satisfacciones);
    }
}

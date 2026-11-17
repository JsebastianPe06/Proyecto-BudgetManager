package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.domain.domainBudget.EstadoDeCategoria;
import com.Proyecto.BudgetManager.dto.BudgetRequest;
import com.Proyecto.BudgetManager.dto.CategoryLimitRequest;
import com.Proyecto.BudgetManager.entity.BudgetEntity;
import com.Proyecto.BudgetManager.entity.CategoryLimitEntity;
import com.Proyecto.BudgetManager.entity.UserEntity;
import com.Proyecto.BudgetManager.enums.Category;
import com.Proyecto.BudgetManager.exception.BusinessException;
import com.Proyecto.BudgetManager.repository.BudgetRepository;
import com.Proyecto.BudgetManager.repository.TransactionRepository;
import com.Proyecto.BudgetManager.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

@Service
public class BudgetService {
    private final BudgetRepository budgets;
    private final UserRepository users;
    private final TransactionRepository transactions;

    public BudgetService(BudgetRepository budgets, UserRepository users, TransactionRepository transactions) {
        this.budgets = budgets;
        this.users = users;
        this.transactions = transactions;
    }

    @Transactional
    public void createBudget(String email, BudgetRequest req) {
        if (budgets.findByUserEmailAndMonthAndYear(email, req.month(), req.year()).isPresent()) {
            throw new BusinessException("Ya existe un presupuesto para este mes y año");
        }

        UserEntity user = users.findByEmail(email).orElseThrow();
        BudgetEntity budget = new BudgetEntity();
        budget.setUser(user);
        budget.setMonth(req.month());
        budget.setYear(req.year());
        budget.setTotalAmount(req.totalAmount());
        
        budgets.save(budget);
    }

    public BudgetEntity getBudget(String email, int month, int year) {
        return budgets.findByUserEmailAndMonthAndYear(email, month, year).orElse(null);
    }

    // Calcula el estado de cada categoría comparando el límite con lo gastado en el mes
    public Map<Category, EstadoDeCategoria> getCategoryStatus(String email, int month, int year) {
        BudgetEntity budget = getBudget(email, month, year);
        if (budget == null) return new HashMap<>();

        Map<Category, EstadoDeCategoria> estados = new HashMap<>();
        
        // Rango de fechas del mes (como String para SQLite)
        YearMonth ym = YearMonth.of(year, month);
        String startDate = ym.atDay(1).toString();
        String endDate = ym.atEndOfMonth().toString();

        for (CategoryLimitEntity limit : budget.getCategoryLimits()) {
            BigDecimal gastado = transactions.sumAmountByUserAndCategoryAndDateRange(
                    email, limit.getCategory(), startDate, endDate);
            if (gastado == null) gastado = BigDecimal.ZERO;
            
            estados.put(limit.getCategory(), EstadoDeCategoria.evaluar(limit.getLimitAmount(), gastado));
        }
        return estados;
    }

    @Transactional
    public void addCategoryLimit(String email, int month, int year, CategoryLimitRequest req) {
        BudgetEntity budget = budgets.findByUserEmailAndMonthAndYear(email, month, year)
                .orElseThrow(() -> new BusinessException("Primero debes crear el presupuesto del mes"));
        // Verificar si la categoría ya tiene un límite
        boolean exists = budget.getCategoryLimits().stream()
                .anyMatch(l -> l.getCategory() == req.category());
        if (exists) {
            throw new BusinessException("Ya existe un límite para esta categoría");
        }
        CategoryLimitEntity limit = new CategoryLimitEntity();
        limit.setBudget(budget);
        limit.setCategory(req.category());
        limit.setLimitAmount(req.limitAmount());
        budget.getCategoryLimits().add(limit);
        budgets.save(budget); // Guarda el límite en cascada
    }
}

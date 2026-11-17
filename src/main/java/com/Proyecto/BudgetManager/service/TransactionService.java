package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.domain.domainTransaction.ImpactoDeGasto;
import com.Proyecto.BudgetManager.entity.TransactionEntity;
import com.Proyecto.BudgetManager.entity.UserEntity;
import com.Proyecto.BudgetManager.enums.TransactionType;
import com.Proyecto.BudgetManager.exception.BusinessException;
import com.Proyecto.BudgetManager.repository.TransactionRepository;
import com.Proyecto.BudgetManager.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactions;
    private final UserRepository users;

    public TransactionService(TransactionRepository transactions, UserRepository users) {
        this.transactions = transactions;
        this.users = users;
    }

    /**
     * Registra una transacción (ingreso o gasto) para el usuario autenticado.
     * Si es un gasto, evalúa el impacto antes de guardar.
     * Devuelve el impacto si fue gasto; null si fue ingreso.
     */
    @Transactional
    public ImpactoDeGasto registrar(String email, TransactionEntity nueva) {
        UserEntity user = users.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));
        if (nueva.getAmount() == null || nueva.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("El monto debe ser mayor que cero");
        }
        nueva.setUser(user);
        if (nueva.getTransactionDate() == null || nueva.getTransactionDate().isBlank()) {
            nueva.setTransactionDate(java.time.LocalDate.now().toString());
        }
        ImpactoDeGasto impacto = null;
        if (nueva.getType() == TransactionType.GASTO && !nueva.isPlanned()) {
            BigDecimal saldo = calcularSaldoActual(email);
            impacto = ImpactoDeGasto.evaluar(saldo, nueva.getAmount());
            if (impacto.esInsuficiente()) {
                throw new BusinessException(
                        "Saldo insuficiente. Disponible: " + saldo + ", gasto solicitado: " + nueva.getAmount());
            }
        }
        transactions.save(nueva);
        return impacto;
    }

    /**
     * Calcula el saldo actual del usuario: suma de ingresos - suma de gastos reales (no planificados).
     */
    public BigDecimal calcularSaldoActual(String email) {
        // Usamos String en formato ISO
        String inicio = "2000-01-01";
        String fin = "2100-12-31";
        BigDecimal ingresos = transactions.sumAmountByUserAndTypeAndDateRange(
                email, TransactionType.INGRESO, inicio, fin);
        BigDecimal gastos = transactions.sumAmountByUserAndTypeAndDateRange(
                email, TransactionType.GASTO, inicio, fin);
        if (ingresos == null) ingresos = BigDecimal.ZERO;
        if (gastos == null) gastos = BigDecimal.ZERO;
        return ingresos.subtract(gastos);
    }

    public List<TransactionEntity> findByEmail(String email) {
        return transactions.findByUserEmailOrderByTransactionDateDesc(email);
    }

    public List<TransactionEntity> findByEmailAndDateRange(String email, String from, String to) {
        return transactions.findByUserEmailAndTransactionDateBetweenOrderByTransactionDateDesc(email, from, to);
    }
}
package com.Proyecto.BudgetManager.dto;

import java.math.BigDecimal;
import java.util.Map;

public record MonthlyReport(
    int month,
    int year,
    BigDecimal totalIncome,
    BigDecimal totalExpenses,
    BigDecimal totalSaved,
    BigDecimal balance,
    Map<String, BigDecimal> expensesByCategory
) {}

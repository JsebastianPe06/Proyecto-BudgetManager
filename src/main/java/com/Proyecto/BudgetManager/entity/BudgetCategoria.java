package com.Proyecto.BudgetManager.entity;

import com.Proyecto.BudgetManager.enums.BudgetEstado;
import java.math.BigDecimal;

public class BudgetCategoria 
{    
    private int id;
    private int budgetId;
    private int categoryId;

    private BigDecimal limite;
    private BudgetEstado estado;

    public BudgetCategoria(int id, int budgetId, int categoryId, BigDecimal limite, BudgetEstado estado) {
        this.id = id;
        this.budgetId = budgetId;
        this.categoryId = categoryId;
        this.limite = limite;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public BudgetEstado getEstado() {
        return estado;
    }
    
    
    
}

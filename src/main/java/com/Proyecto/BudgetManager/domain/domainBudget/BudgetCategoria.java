package com.Proyecto.BudgetManager.entity;

import com.Proyecto.BudgetManager.enums.BudgetEstado;
import java.math.BigDecimal;
import lombok.Getter;

public class BudgetCategoria 
{    
    @Getter 
    private int id;
    @Getter
    private int budgetId;
    @Getter 
    private int categoriaId;
    @Getter
    private BigDecimal limite;
    @Getter
    private BudgetEstado estado;

    public BudgetCategoria(int id, int budgetId, int categoriaId, BigDecimal limite, BudgetEstado estado) {
        this.id = id;
        this.budgetId = budgetId;
        this.categoriaId = categoriaId;
        this.limite = limite;
        this.estado = estado;
    }
}

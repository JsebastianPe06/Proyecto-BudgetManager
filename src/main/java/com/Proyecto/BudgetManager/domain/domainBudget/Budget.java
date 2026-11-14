package com.Proyecto.BudgetManager.entity;

import com.Proyecto.BudgetManager.enums.BudgetEstado;
import java.math.BigDecimal;
import  lombok.Getter;

public class Budget 
{
    @Getter
    private int id;
    @Getter 
    private int userId;
    @Getter 
    private BigDecimal totalLimite;
    @Getter 
    private fecha fechaInicio;
    @Getter 
    private fecha fechaFinal;
    @Getter 
    private BudgetEstado estado;

    public Budget(int id, int userId, BigDecimal totalLimite, fecha fechaInicio, fecha fechaFinal, BudgetEstado estado) {
        this.id = id;
        this.userId = userId;
        this.totalLimite = totalLimite;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.estado = estado;
    }
}

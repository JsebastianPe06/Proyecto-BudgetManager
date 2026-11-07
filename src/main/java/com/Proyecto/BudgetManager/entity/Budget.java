package com.Proyecto.BudgetManager.entity;

import com.Proyecto.BudgetManager.enums.BudgetEstado;
import java.math.BigDecimal;

public class Budget 
{
    private int id;
    private int userId;

    private BigDecimal totalLimite;
    private fecha fechaInicio;
    private fecha fechaFinal;
    private BudgetEstado estado;

    public Budget(int id, int userId, BigDecimal totalLimite, fecha fechaInicio, fecha fechaFinal, BudgetEstado estado) {
        this.id = id;
        this.userId = userId;
        this.totalLimite = totalLimite;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public BigDecimal getTotalLimite() {
        return totalLimite;
    }

    public fecha getFechaInicio() {
        return fechaInicio;
    }

    public fecha getFechaFinal() {
        return fechaFinal;
    }

    public BudgetEstado getEstado() {
        return estado;
    }
    
    
}

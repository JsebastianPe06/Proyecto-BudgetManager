package com.Proyecto.BudgetManager.domain.domainBudget;

import com.Proyecto.BudgetManager.domain.domainFecha.Fecha;
import com.Proyecto.BudgetManager.enums.BudgetEstado;
import java.math.BigDecimal;
import lombok.Getter;

public class Budget 
{
    @Getter
    private int id;
    @Getter 
    private int userId;
    @Getter 
    private BigDecimal totalLimite;
    @Getter 
    private Fecha fechaInicio;
    @Getter 
    private Fecha fechaFinal;
    @Getter 
    private BudgetEstado estado;

    public Budget(int id, int userId, BigDecimal totalLimite, Fecha fechaInicio, Fecha fechaFinal, BudgetEstado estado) {
        this.id = id;
        this.userId = userId;
        this.totalLimite = totalLimite;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.estado = estado;
    }
}

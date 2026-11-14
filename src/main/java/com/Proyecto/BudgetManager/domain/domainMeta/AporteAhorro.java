package com.Proyecto.BudgetManager.entity;

import java.math.BigDecimal;

import lombok.Getter;


public class AporteAhorro 
{
    @Getter 
    private int id;
    @Getter 
    private int MetaId;
    @Getter 
    private BigDecimal monto;
    @Getter 
    private fecha fecha;

    public AporteAhorro(int id, int MetaId, BigDecimal monto, fecha fecha) {
        this.id = id;
        this.MetaId = MetaId;
        this.monto = monto;
        this.fecha = fecha;
    }
}

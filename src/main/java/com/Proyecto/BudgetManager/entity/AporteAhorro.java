package com.Proyecto.BudgetManager.entity;

import java.math.BigDecimal;


public class AporteAhorro 
{
    private int id;
    private int MetaId;

    private BigDecimal monto;
    private fecha fecha;

    public AporteAhorro(int id, int MetaId, BigDecimal monto, fecha fecha) {
        this.id = id;
        this.MetaId = MetaId;
        this.monto = monto;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public int getMetaId() {
        return MetaId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public fecha getFecha() {
        return fecha;
    }
    
    
}

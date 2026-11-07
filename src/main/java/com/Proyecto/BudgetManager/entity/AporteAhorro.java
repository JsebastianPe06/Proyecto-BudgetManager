package com.Proyecto.BudgetManager.entity;

import java.math.BigDecimal;


public class AporteAhorro 
{
    private int id;
    private int MetalId;

    private BigDecimal monto;
    private fecha fecha;

    public AporteAhorro(int id, int MetalId, BigDecimal monto, fecha fecha) {
        this.id = id;
        this.MetalId = MetalId;
        this.monto = monto;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public int getMetalId() {
        return MetalId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public fecha getFecha() {
        return fecha;
    }
    
    
}

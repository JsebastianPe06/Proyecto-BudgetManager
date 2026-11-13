package com.Proyecto.BudgetManager.entity;

import java.math.BigDecimal;


public class AporteAhorro 
{
    private int id;
    private int MetaId;
    private int userId;

    private BigDecimal monto;
    private fecha fecha;

    public AporteAhorro(int id,int userId, int MetaId, BigDecimal monto, fecha fecha) {
        this.id = id;
        this.userId = userId;
        this.MetaId = MetaId;
        this.monto = monto;
        this.fecha = fecha;
    }

    public int getUserId() {
        return userId;
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

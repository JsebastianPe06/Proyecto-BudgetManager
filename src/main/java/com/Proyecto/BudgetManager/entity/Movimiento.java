package com.Proyecto.BudgetManager.entity;

import com.Proyecto.BudgetManager.enums.PrioridadGasto;
import com.Proyecto.BudgetManager.enums.TipoGasto;
import com.Proyecto.BudgetManager.enums.TipoMovimiento;
import java.math.BigDecimal;

public class Movimiento 
{
    private int id;
    private int userId;
    private String descripccion;
    private BigDecimal monto;
    private fecha date;

    private TipoMovimiento tipo;
    private int categoriaId;

    private PrioridadGasto prioridad;
    private TipoGasto tipoGasto;

    public Movimiento(int id, int userId, String descripccion, BigDecimal monto, fecha date, 
            TipoMovimiento tipo, int categoriaId, PrioridadGasto prioridad, TipoGasto tipoGasto) {
        
        this.id = id;
        this.userId = userId;
        this.descripccion = descripccion;
        this.monto = monto;
        this.date = date;
        this.tipo = tipo;
        this.categoriaId = categoriaId;
        this.prioridad = prioridad;
        this.tipoGasto = tipoGasto;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getDescripccion() {
        return descripccion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public fecha getDate() {
        return date;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public PrioridadGasto getPrioridad() {
        return prioridad;
    }

    public TipoGasto getTipoGasto() {
        return tipoGasto;
    }
    
    

    
}

package com.Proyecto.BudgetManager.domain.domainBudget;

import com.Proyecto.BudgetManager.domain.domainFecha.Fecha;
import com.Proyecto.BudgetManager.enums.PrioridadGasto;
import com.Proyecto.BudgetManager.enums.TipoGasto;
import com.Proyecto.BudgetManager.enums.TipoMovimiento;

import lombok.Getter;

import java.math.BigDecimal;

public class Movimiento 
{
    @Getter 
    private int id;
    @Getter
    private int userId;
    @Getter 
    private String descripccion;
    @Getter
    private BigDecimal monto;
    @Getter
    private Fecha date;
    @Getter 
    private TipoMovimiento tipo;
    @Getter 
    private int categoriaId;

    //Atributos especiales de gasto
    @Getter
    private PrioridadGasto prioridad;
    @Getter 
    private TipoGasto tipoGasto;

    //Constructor de Gasto
    public Movimiento(int id, int userId, String descripccion, BigDecimal monto, Fecha date, 
            TipoMovimiento tipo, int categoriaId, PrioridadGasto prioridad, TipoGasto tipoGasto) {
        
        this.id = id;
        this.userId = userId;
        this.descripccion = descripccion;
        this.monto = monto; //Ingresar el monto negativo?
        this.date = date;
        this.tipo = tipo;
        this.categoriaId = categoriaId;
        this.prioridad = prioridad;
        this.tipoGasto = tipoGasto;
    }

    //Constructor de Ingreso
    public Movimiento(int id, int userId, String descripccion, BigDecimal monto, Fecha date,
            TipoMovimiento tipo, int categoriaId) {
        this.id = id;
        this.userId = userId;
        this.descripccion = descripccion;
        this.monto = monto;
        this.date = date;
        this.tipo = tipo;
        this.categoriaId = categoriaId;
        this.prioridad = null;
        this.tipoGasto = null;
    }
}

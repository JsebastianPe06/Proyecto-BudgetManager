package com.Proyecto.BudgetManager.domain;

import com.Proyecto.BudgetManager.entity.Movimiento;
import com.Proyecto.BudgetManager.enums.TipoMovimiento;
import java.math.BigDecimal;


public class MovimientoValid {
    
    public void validar(Movimiento move) {

        if (move == null) 
        {
            throw new IllegalArgumentException("El movimiento no puede ser nulo");
        }

        if (move.getUserId() == 0) 
        {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }

        if (move.getMonto() == null || move.getMonto().compareTo(BigDecimal.ZERO) <= 0) 
        {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }

        if (move.getDate() == null) 
        {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }

        if (move.getTipo() == null) 
        {
            throw new IllegalArgumentException("El tipo de movimiento es obligatorio");
        }

        if (move.getCategoriaId() == 0) 
        {
            throw new IllegalArgumentException("La categoría es obligatoria");
        }

        if (move.getTipo() == TipoMovimiento.GASTO) 
        {
            if (move.getPrioridad() == null || move.getTipoGasto() == null) 
            {
                throw new IllegalArgumentException("Los gastos requieren prioridad y tipo de gasto");
            }
        }
    }
}
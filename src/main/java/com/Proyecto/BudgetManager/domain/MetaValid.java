package com.Proyecto.BudgetManager.domain;

import com.Proyecto.BudgetManager.entity.MetaAhorro;
import java.math.BigDecimal;

public class MetaValid {
    
    FechaValid fechaValid = new FechaValid();;
    
    public void validar(MetaAhorro meta) {

        if (meta == null) 
        {
            throw new IllegalArgumentException("La meta no puede ser nula");
        }

        if (meta.getUserId() == 0) 
        {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }

        if (meta.getName() == null) 
        {
            throw new IllegalArgumentException("El nombre para la meta es obligatorio");
        }

        if (meta.getObjetivo() == null || meta.getObjetivo().compareTo(BigDecimal.ZERO) <= 0) 
        {
            throw new IllegalArgumentException("El objetivo debe ser mayor que cero");
        }

        if (meta.getFechaInicial() == null) 
        {
            throw new IllegalArgumentException("La fecha inicial del ahorro es obligatorio");
        }

        if (meta.getFechaFinal() == null) 
        {
            throw new IllegalArgumentException("La fecha final del ahorro es obligatorio");
        }

        fechaValid.validar(meta.getFechaInicial());
        
        fechaValid.validar(meta.getFechaFinal());
        
        fechaValid.validarOrden(meta.getFechaInicial(), meta.getFechaFinal());
    }
}
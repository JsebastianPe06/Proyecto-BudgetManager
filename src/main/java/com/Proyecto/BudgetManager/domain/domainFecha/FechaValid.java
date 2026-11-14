package com.Proyecto.BudgetManager.domain.domainFecha;

import com.Proyecto.BudgetManager.domain.domainFecha.Fecha;
import java.time.DateTimeException;
import java.time.LocalDate;

public class FechaValid {
    
    public void validar(Fecha date) 
    {
        if (date == null) 
        {
        throw new IllegalArgumentException("La fecha no puede ser nula");
        }

        try 
        {
            LocalDate.of(date.getYear(), date.getMes(),date.getDia() );
        } 
        catch (DateTimeException e) 
        {
            throw new IllegalArgumentException("La fecha no es válida" );
        }

        if (date.getYear() < 1900) 
        {
            throw new IllegalArgumentException("No hay chance que sea real ese año");
        }
    }

    public void validarOrden(Fecha fechaInicial, Fecha fechaFinal) 
    {
        LocalDate inicial = LocalDate.of(fechaInicial.getYear(), fechaInicial.getMes(), fechaInicial.getDia() );

        LocalDate fin = LocalDate.of(fechaFinal.getYear(), fechaFinal.getMes(), fechaFinal.getDia() );

        if (!fin.isAfter(inicial)) 
        {
            throw new IllegalArgumentException("La fecha final debe ser posterior a la inicial" );
        }
    }
}    
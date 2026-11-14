package com.Proyecto.BudgetManager.domain.domainBudget;

import com.Proyecto.BudgetManager.domain.domainFecha.FechaValid;
import com.Proyecto.BudgetManager.entity.BudgetCategoria;
import java.math.BigDecimal;
import java.util.List;

/**
 * Regla pura: validaciones del presupuesto y de sus límites por categoría (US-03).
 */
public class BudgetValid {

    FechaValid fechaValid = new FechaValid();

    public void validar(Budget budget)
    {
        if (budget == null)
        {
            throw new IllegalArgumentException("El presupuesto no puede ser nulo");
        }
        if (budget.getUserId() == 0)
        {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }
        if (budget.getTotalLimite() == null || budget.getTotalLimite().compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new IllegalArgumentException("El límite total debe ser mayor que cero");
        }
        if (budget.getFechaInicio() == null || budget.getFechaFinal() == null)
        {
            throw new IllegalArgumentException("Las fechas del presupuesto son obligatorias");
        }
        fechaValid.validar(budget.getFechaInicio());
        fechaValid.validar(budget.getFechaFinal());
        fechaValid.validarOrden(budget.getFechaInicio(), budget.getFechaFinal());
    }

    /**
     * Valida un nuevo límite por categoría dentro de un presupuesto.
     * @param nuevo       el límite que se quiere agregar
     * @param budget      el presupuesto al que pertenece
     * @param existentes  los límites que ya tiene ese presupuesto
     */
    public void validarLimiteCategoria(BudgetCategoria nuevo, Budget budget, List<BudgetCategoria> existentes)
    {
        if (nuevo == null || budget == null)
        {
            throw new IllegalArgumentException("El límite y el presupuesto son obligatorios");
        }
        if (nuevo.getCategoriaId() == 0)
        {
            throw new IllegalArgumentException("La categoría es obligatoria");
        }
        if (nuevo.getLimite() == null || nuevo.getLimite().compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new IllegalArgumentException("El límite de la categoría debe ser mayor que cero");
        }

        // US-03, escenario 2: no puede haber dos límites para la misma categoría
        for (BudgetCategoria existente : existentes)
        {
            if (existente.getCategoriaId() == nuevo.getCategoriaId())
            {
                throw new IllegalArgumentException(
                    "Esta categoría ya tiene un límite en este presupuesto. Edita el existente.");
            }
        }

        // La suma de los límites por categoría no puede superar el límite total
        BigDecimal suma = nuevo.getLimite();
        for (BudgetCategoria existente : existentes)
        {
            suma = suma.add(existente.getLimite());
        }
        if (suma.compareTo(budget.getTotalLimite()) > 0)
        {
            throw new IllegalArgumentException(
                "La suma de los límites por categoría supera el límite total del presupuesto");
        }
    }
}

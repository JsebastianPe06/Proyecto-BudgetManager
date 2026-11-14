package com.Proyecto.BudgetManager.domain.domainMeta;

import com.Proyecto.BudgetManager.domain.domainFecha.FechaValid;
import com.Proyecto.BudgetManager.domain.domainMeta.AporteAhorro;
import java.math.BigDecimal;

public class AporteValid {
    
    FechaValid fechaValid = new FechaValid();;

    public void validar(AporteAhorro aporte) 
    {
        if (aporte == null) 
        {
            throw new IllegalArgumentException("El aporte no puede ser nulo");
        }

        if (aporte.getMetaId() == 0) 
        {
            throw new IllegalArgumentException("La meta donde va el aporte es obligatorio");
        }

        if (aporte.getMonto() == null || aporte.getMonto().compareTo(BigDecimal.ZERO) <= 0) 
        {
            throw new IllegalArgumentException("El Monto del aporte debe ser mayor que cero");
        }
        
        fechaValid.validar(aporte.getFecha());
    }
    
}

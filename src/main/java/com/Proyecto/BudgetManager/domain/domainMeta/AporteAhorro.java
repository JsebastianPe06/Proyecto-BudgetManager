package com.Proyecto.BudgetManager.domain.domainMeta;

import com.Proyecto.BudgetManager.domain.domainFecha.Fecha;
import java.math.BigDecimal;

import lombok.Getter;


public class AporteAhorro 
{
    @Getter 
    private int id;
    @Getter 
    private int MetaId;
    @Getter 
    private BigDecimal monto;
    @Getter 
    private Fecha fecha;

    public AporteAhorro(int id, int MetaId, BigDecimal monto, Fecha fecha) {
        this.id = id;
        this.MetaId = MetaId;
        this.monto = monto;
        this.fecha = fecha;
    }
}

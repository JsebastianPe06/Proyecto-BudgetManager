package com.Proyecto.BudgetManager.domain.domainMeta;

import java.math.BigDecimal;
import java.util.List;

import com.Proyecto.BudgetManager.domain.domainFecha.Fecha;

import lombok.Getter;
import lombok.Setter;


public class MetaAhorro 
{
    @Getter
    private int id;
    @Getter 
    private int userId;
    @Getter 
    private String name;
    @Getter 
    private BigDecimal objetivo;
    @Getter 
    private Fecha fechaInicial;
    @Getter @Setter 
    private Fecha fechaFinal;
    @Getter
    private List<BigDecimal> montos;

    public MetaAhorro(int id, int userId, String name, BigDecimal objetivo, Fecha fechaInicial, Fecha fechaFinal) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.objetivo = objetivo;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }
}

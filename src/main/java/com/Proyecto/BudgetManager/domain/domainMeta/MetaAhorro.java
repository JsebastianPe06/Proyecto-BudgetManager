package com.Proyecto.BudgetManager.entity;

import java.math.BigDecimal;
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
    private fecha fechaInicial;
    @Getter @Setter 
    private fecha fechaFinal;

    public MetaAhorro(int id, int userId, String name, BigDecimal objetivo, fecha fechaInicial, fecha fechaFinal) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.objetivo = objetivo;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }
}

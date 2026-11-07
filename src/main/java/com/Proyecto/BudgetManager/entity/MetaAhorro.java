package com.Proyecto.BudgetManager.entity;

import java.math.BigDecimal;


public class MetaAhorro 
{
    private int id;
    private int userId;

    private String name;
    private BigDecimal objetivo;

    private fecha fechaInicial;
    private fecha fechaFinal;

    public MetaAhorro(int id, int userId, String name, BigDecimal objetivo, fecha fechaInicial, fecha fechaFinal) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.objetivo = objetivo;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getObjetivo() {
        return objetivo;
    }

    public fecha getFechaInicial() {
        return fechaInicial;
    }

    public fecha getFechaFinal() {
        return fechaFinal;
    }
    
    
}

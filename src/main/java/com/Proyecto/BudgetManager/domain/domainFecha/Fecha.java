package com.Proyecto.BudgetManager.domain.domainFecha;

import  lombok.Getter;
import  lombok.Setter;

public class Fecha {
    @Getter @Setter
    private int dia;
    @Getter @Setter
    private int mes;
    @Getter @Setter
    private int year;

    public Fecha(int dia, int mes, int year) {
        this.dia = dia;
        this.mes = mes;
        this.year = year;
    }
}
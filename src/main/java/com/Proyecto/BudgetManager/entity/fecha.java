package com.Proyecto.BudgetManager.entity;


public class fecha {
    private int dia;
    private int mes;
    private int year;

    public fecha(int dia, int mes, int year) {
        this.dia = dia;
        this.mes = mes;
        this.year = year;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getYear() {
        return year;
    }
    
}

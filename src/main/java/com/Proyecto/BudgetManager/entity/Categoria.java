/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.BudgetManager.entity;

import com.Proyecto.BudgetManager.enums.TipoCategoria;

/**
 *
 * @author jagua
 */
public class Categoria 
{
    private int id;
    private String name;
    private String descripcion;
    private TipoCategoria tipo; //(gasto/ingrso) 
    
    //Podrian ser utiles para cuando se implemente que el usuario pueda hacer sus propias categorias
    //private boolean custom; //false si es preterminado, tru si custom
    //private int ownerId;    //quien hizo la categoria, null si es del sistema/preterminado

    public Categoria(int id, String name, String descripcion, TipoCategoria tipo/*, boolean custom, int ownerId*/) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.tipo = tipo;
        
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public TipoCategoria getTipo() {
        return tipo;
    }
}

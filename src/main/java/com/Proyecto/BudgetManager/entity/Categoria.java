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
    
    //Pense que las categorias personalizadas deberian tener el id de quien lo creo, 
    // asi el usuario solo ve las predetermindas y sus categorias
    private boolean custom; //false si es preterminado, tru si custom
    private int ownerId;    //quien hizo la categoria, 0 (cero) si es del sistema/preterminado

    public Categoria(int id, String name, String descripcion, TipoCategoria tipo, boolean custom, int ownerId) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.custom = custom;
        this.ownerId = ownerId;
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

    public boolean isCustom() {
        return custom;
    }

    public int getOwnerId() {
        return ownerId;
    }
    
}

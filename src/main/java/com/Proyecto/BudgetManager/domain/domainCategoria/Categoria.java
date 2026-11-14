package com.Proyecto.BudgetManager.domain.domainCategoria;

import com.Proyecto.BudgetManager.enums.TipoCategoria;
import lombok.Getter;

public class Categoria 
{
    @Getter 
    private int id;
    @Getter 
    private String name;
    @Getter 
    private String descripcion;
    @Getter 
    private TipoCategoria tipo; //(gasto/ingrso) 
    @Getter
    private boolean custom; //false si es preterminado, tru si custom
    @Getter 
    private int ownerId;    //quien hizo la categoria, 0 (cero) si es del sistema/preterminado

    public Categoria(int id, String name, String descripcion, TipoCategoria tipo, boolean custom, int ownerId) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.custom = custom;
        this.ownerId = ownerId;
    }
}

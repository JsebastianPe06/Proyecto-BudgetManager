package com.Proyecto.BudgetManager.domain;

import com.Proyecto.BudgetManager.entity.Categoria;
import com.Proyecto.BudgetManager.enums.TipoCategoria;
import com.Proyecto.BudgetManager.enums.TipoMovimiento;


public class CategoriaValid {
    
    public void validarPropiedad(Categoria categoria, int userId)
    {
        if (categoria == null) 
        {            
            throw new IllegalArgumentException("La categoría no existe");
        }

        if (userId == 0) 
        {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }

        if (categoria.isCustom() && userId != categoria.getOwnerId() ) 
        {
            throw new IllegalArgumentException("No puedes utilizar esta categoría");
        }
    }

    public void validarTipo(Categoria categoria, TipoMovimiento tipoMove) 
    {
        if (categoria == null) 
        {            
            throw new IllegalArgumentException("La categoría no existe");
        }
        
        if (tipoMove == null) 
        {            
            throw new IllegalArgumentException("El tipo de movimiento es obligatorio");
        }
        
        TipoCategoria tipoEsperado;
        
        if (tipoMove == TipoMovimiento.INGRESO) 
        {
            tipoEsperado = TipoCategoria.INGRESO;
        } 
        else 
        {
            tipoEsperado = TipoCategoria.GASTO;
        }

        if (categoria.getTipo() != tipoEsperado) 
        { 
            throw new IllegalArgumentException("La categoría no corresponde al tipo de movimiento");
        }
    }
}
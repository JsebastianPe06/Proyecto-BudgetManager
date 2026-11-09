/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.BudgetManager.domain;

import com.Proyecto.BudgetManager.entity.Categoria;
import com.Proyecto.BudgetManager.enums.TipoCategoria;
import com.Proyecto.BudgetManager.enums.TipoMovimiento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaValidTest {

    private final CategoriaValid validator =
            new CategoriaValid();

    @Test
    void aceptarCategoriaPredefinida() {

        Categoria categoria = new Categoria(
                1,
                "Alimentacion",
                "Gastos de alimentos",
                TipoCategoria.GASTO,
                false,
                0
        );

        assertDoesNotThrow(
                () -> validator.validarPropiedad(categoria, 15)
        );
    }

    @Test
    void aceptarCategoriaPersonalizadaDelPropietario() {

        Categoria categoria = new Categoria(
                2,
                "Mascotas",
                "Gastos de mascotas",
                TipoCategoria.GASTO,
                true,
                15
        );

        assertDoesNotThrow(
                () -> validator.validarPropiedad(categoria, 15)
        );
    }

    @Test
    void rechazarCategoriaDeOtroUsuario() {

        Categoria categoria = new Categoria(
                2,
                "Mascotas",
                "Gastos de mascotas",
                TipoCategoria.GASTO,
                true,
                15
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validarPropiedad(categoria, 20)
        );
    }

    @Test
    void aceptarCategoriaDelTipoCorrecto() {

        Categoria categoria = new Categoria(
                1,
                "Alimentacion",
                "Gastos de alimentos",
                TipoCategoria.GASTO,
                false,
                0
        );

        assertDoesNotThrow(
                () -> validator.validarTipo(
                        categoria,
                        TipoMovimiento.GASTO
                )
        );
    }

    @Test
    void rechazarCategoriaDeTipoIncorrecto() {

        Categoria categoria = new Categoria(
                1,
                "Alimentacion",
                "Gastos de alimentos",
                TipoCategoria.GASTO,
                false,
                0
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validarTipo(
                        categoria,
                        TipoMovimiento.INGRESO
                )
        );
    }
}
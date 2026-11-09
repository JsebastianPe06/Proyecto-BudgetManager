package com.Proyecto.BudgetManager.entity;

import com.Proyecto.BudgetManager.enums.TipoCategoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriaTest {

    @Test
    void crearCategoriaPredefinida() {

        Categoria categoria = new Categoria(
                1,
                "Alimentacion",
                "Gastos relacionados con alimentos",
                TipoCategoria.GASTO,
                false,
                0
        );

        assertEquals("Alimentacion", categoria.getName());

        assertEquals(
                TipoCategoria.GASTO,
                categoria.getTipo()
        );

        assertFalse(categoria.isCustom());
    }

    @Test
    void crearCategoriaPersonalizada() {

        Categoria categoria = new Categoria(
                2,
                "Mascotas",
                "Gastos relacionados con mascotas",
                TipoCategoria.GASTO,
                true,
                15
        );

        assertEquals("Mascotas", categoria.getName());

        assertTrue(categoria.isCustom());

        assertEquals(15, categoria.getOwnerId());
    }
}
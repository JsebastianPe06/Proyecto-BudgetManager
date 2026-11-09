/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.BudgetManager.domain;

import com.Proyecto.BudgetManager.entity.MetaAhorro;
import com.Proyecto.BudgetManager.entity.fecha;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class MetaValidTest {

    private final MetaValid validator = new MetaValid();

    @Test
    void aceptarMetaValida() {

        MetaAhorro meta = new MetaAhorro(
                1,
                15,
                "Comprar computador",
                new BigDecimal("3000000"),
                new fecha(1, 11, 2026),
                new fecha(1, 5, 2027)
        );

        assertDoesNotThrow(
                () -> validator.validar(meta)
        );
    }

    @Test
    void rechazarMetaNula() {

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(null)
        );
    }

    @Test
    void rechazarObjetivoCero() {

        MetaAhorro meta = new MetaAhorro(
                1,
                15,
                "Comprar computador",
                BigDecimal.ZERO,
                new fecha(1, 11, 2026),
                new fecha(1, 5, 2027)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(meta)
        );
    }

    @Test
    void rechazarNombreNulo() {

        MetaAhorro meta = new MetaAhorro(
                1,
                15,
                null,
                new BigDecimal("3000000"),
                new fecha(1, 11, 2026),
                new fecha(1, 5, 2027)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(meta)
        );
    }

    @Test
    void rechazarFechaFinalAnterior() {

        MetaAhorro meta = new MetaAhorro(
                1,
                15,
                "Comprar computador",
                new BigDecimal("3000000"),
                new fecha(1, 11, 2026),
                new fecha(1, 10, 2026)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(meta)
        );
    }
}

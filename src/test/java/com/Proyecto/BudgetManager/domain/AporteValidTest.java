/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.BudgetManager.domain;

import com.Proyecto.BudgetManager.entity.AporteAhorro;
import com.Proyecto.BudgetManager.entity.fecha;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AporteValidTest {

    private final AporteValid validator = new AporteValid();

    @Test
    void aceptarAporteValido() {

        AporteAhorro aporte = new AporteAhorro(
                1,
                5,
                new BigDecimal("200000"),
                new fecha(7, 11, 2026)
        );

        assertDoesNotThrow(
                () -> validator.validar(aporte)
        );
    }

    @Test
    void rechazarAporteNulo() {

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(null)
        );
    }

    @Test
    void rechazarMetaNoIndicada() {

        AporteAhorro aporte = new AporteAhorro(
                1,
                0,
                new BigDecimal("200000"),
                new fecha(7, 11, 2026)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(aporte)
        );
    }

    @Test
    void rechazarMontoNegativo() {

        AporteAhorro aporte = new AporteAhorro(
                1,
                5,
                new BigDecimal("-200000"),
                new fecha(7, 11, 2026)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(aporte)
        );
    }

    @Test
    void rechazarFechaInvalida() {

        AporteAhorro aporte = new AporteAhorro(
                1,
                5,
                new BigDecimal("200000"),
                new fecha(31, 2, 2026)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(aporte)
        );
    }
}

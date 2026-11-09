/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.BudgetManager.domain;

import com.Proyecto.BudgetManager.entity.fecha;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FechaValidTest {

    private final FechaValid validator = new FechaValid();

    @Test
    void aceptarFechaValida() {

        fecha date = new fecha(15, 11, 2026);

        assertDoesNotThrow(
                () -> validator.validar(date)
        );
    }

    @Test
    void rechazarDiaCero() {

        fecha date = new fecha(0, 11, 2026);

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(date)
        );
    }

    @Test
    void rechazarMesCero() {

        fecha date = new fecha(15, 0, 2026);

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(date)
        );
    }

    @Test
    void rechazarDiaInexistente() {

        fecha date = new fecha(31, 2, 2026);

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(date)
        );
    }

    @Test
    void aceptarDiaBisiesto() {

        fecha date = new fecha(29, 2, 2024);

        assertDoesNotThrow(
                () -> validator.validar(date)
        );
    }

    @Test
    void rechazarDiaBisiestoEnAnioNoBisiesto() {

        fecha date = new fecha(29, 2, 2025);

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(date)
        );
    }

    @Test
    void aceptarOrdenCorrecto() {

        fecha inicial = new fecha(15, 11, 2026);
        fecha fin = new fecha(10, 11, 2027);

        assertDoesNotThrow(
                () -> validator.validarOrden(inicial, fin)
        );
    }

    @Test
    void rechazarFechaFinalAnterior() {

        fecha inicial = new fecha(15, 11, 2026);
        fecha fin = new fecha(10, 10, 2026);

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validarOrden(inicial, fin)
        );
    }

    @Test
    void rechazarFechasIguales() {

        fecha inicial = new fecha(15, 11, 2026);
        fecha fin = new fecha(15, 11, 2026);

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validarOrden(inicial, fin)
        );
    }
}

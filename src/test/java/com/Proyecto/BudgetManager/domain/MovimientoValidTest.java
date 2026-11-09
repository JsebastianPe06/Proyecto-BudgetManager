/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.BudgetManager.domain;

import com.Proyecto.BudgetManager.entity.Movimiento;
import com.Proyecto.BudgetManager.entity.fecha;
import com.Proyecto.BudgetManager.enums.PrioridadGasto;
import com.Proyecto.BudgetManager.enums.TipoGasto;
import com.Proyecto.BudgetManager.enums.TipoMovimiento;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class MovimientoValidTest {

    private final MovimientoValid validator =
            new MovimientoValid();

    private Movimiento crearGasto(
            BigDecimal monto,
            int userId,
            fecha date,
            TipoMovimiento tipo,
            int categoriaId,
            PrioridadGasto prioridad,
            TipoGasto tipoGasto
    ) {
        return new Movimiento(
                1,
                userId,
                "Compra de alimentos",
                monto,
                date,
                tipo,
                categoriaId,
                prioridad,
                tipoGasto
        );
    }

    @Test
    void aceptarGastoValido() {

        Movimiento gasto = crearGasto(
                new BigDecimal("85000"),
                15,
                new fecha(7, 11, 2026),
                TipoMovimiento.GASTO,
                3,
                PrioridadGasto.ALTO,
                TipoGasto.NECESARIO
        );

        assertDoesNotThrow(() -> validator.validar(gasto));
    }

    @Test
    void rechazarMovimientoNulo() {

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(null)
        );
    }

    @Test
    void rechazarMontoCero() {

        Movimiento gasto = crearGasto(
                BigDecimal.ZERO,
                15,
                new fecha(7, 11, 2026),
                TipoMovimiento.GASTO,
                3,
                PrioridadGasto.ALTO,
                TipoGasto.NECESARIO
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(gasto)
        );
    }

    @Test
    void rechazarMontoNegativo() {

        Movimiento gasto = crearGasto(
                new BigDecimal("-5000"),
                15,
                new fecha(7, 11, 2026),
                TipoMovimiento.GASTO,
                3,
                PrioridadGasto.ALTO,
                TipoGasto.NECESARIO
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(gasto)
        );
    }

    @Test
    void rechazarGastoSinPrioridad() {

        Movimiento gasto = crearGasto(
                new BigDecimal("85000"),
                15,
                new fecha(7, 11, 2026),
                TipoMovimiento.GASTO,
                3,
                null,
                TipoGasto.NECESARIO
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(gasto)
        );
    }

    @Test
    void rechazarGastoSinTipoGasto() {

        Movimiento gasto = crearGasto(
                new BigDecimal("85000"),
                15,
                new fecha(7, 11, 2026),
                TipoMovimiento.GASTO,
                3,
                PrioridadGasto.ALTO,
                null
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> validator.validar(gasto)
        );
    }
}

package com.Proyecto.BudgetManager.entity;

import com.Proyecto.BudgetManager.enums.PrioridadGasto;
import com.Proyecto.BudgetManager.enums.TipoGasto;
import com.Proyecto.BudgetManager.enums.TipoMovimiento;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovimientoTest {
    
    @Test
    void crearGastoCorrectamente() {

        // Arrange
        Movimiento gasto = new Movimiento(
                1,
                15,
                "Compra de alimentos",
                new BigDecimal("85000"),
                new fecha(7, 11, 2026),
                TipoMovimiento.GASTO,
                3,
                PrioridadGasto.ALTO,
                TipoGasto.NECESARIO
        );

        // Assert
        assertEquals(1, gasto.getId());
        assertEquals(15, gasto.getUserId());

        assertEquals(
                0,
                new BigDecimal("85000").compareTo(gasto.getMonto())
        );

        assertEquals(TipoMovimiento.GASTO, gasto.getTipo());
        assertEquals(3, gasto.getCategoriaId());
        assertEquals(PrioridadGasto.ALTO, gasto.getPrioridad());
        assertEquals(TipoGasto.NECESARIO, gasto.getTipoGasto());
    }

    @Test
    void crearIngresoCorrectamente() {

        // Arrange
        Movimiento ingreso = new Movimiento(
                2,
                15,
                "Salario",
                new BigDecimal("2000000"),
                new fecha(1, 11, 2026),
                TipoMovimiento.INGRESO,
                1
        );

        // Assert
        assertEquals(TipoMovimiento.INGRESO, ingreso.getTipo());

        assertEquals(
                0,
                new BigDecimal("2000000").compareTo(ingreso.getMonto())
        );

        assertEquals(1, ingreso.getCategoriaId());

        assertNull(ingreso.getPrioridad());
        assertNull(ingreso.getTipoGasto());
    }
}

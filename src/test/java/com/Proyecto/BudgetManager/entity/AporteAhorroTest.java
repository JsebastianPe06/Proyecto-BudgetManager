package com.Proyecto.BudgetManager.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AporteAhorroTest {

    @Test
    void crearAporteCorrectamente() {

        AporteAhorro aporte = new AporteAhorro(
                1,
                5,
                new BigDecimal("200000"),
                new fecha(7, 11, 2026)
        );

        assertEquals(1, aporte.getId());
        assertEquals(5, aporte.getMetaId());

        assertEquals(
                0,
                new BigDecimal("200000")
                        .compareTo(aporte.getMonto())
        );
    }
}

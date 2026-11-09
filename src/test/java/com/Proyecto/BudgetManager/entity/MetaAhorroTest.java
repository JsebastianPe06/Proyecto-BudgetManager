package com.Proyecto.BudgetManager.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class MetaAhorroTest {

    @Test
    void crearMetaCorrectamente() {

        MetaAhorro meta = new MetaAhorro(
                1,
                15,
                "Comprar computador",
                new BigDecimal("3000000"),
                new fecha(1, 11, 2026),
                new fecha(1, 5, 2027)
        );

        assertEquals(1, meta.getId());
        assertEquals(15, meta.getUserId());

        assertEquals(
                "Comprar computador",
                meta.getName()
        );

        assertEquals(
                0,
                new BigDecimal("3000000")
                        .compareTo(meta.getObjetivo())
        );
    }
}

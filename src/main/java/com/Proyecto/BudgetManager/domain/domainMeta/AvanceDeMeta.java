package com.Proyecto.BudgetManager.domain.domainMeta;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class AvanceDeMeta {
    private final BigDecimal porcentaje;
    private final boolean completada;

    private AvanceDeMeta(BigDecimal objetivo, BigDecimal actual) {
        if (actual.compareTo(objetivo) >= 0) {
            this.porcentaje = BigDecimal.valueOf(100);
            this.completada = true;
        } else {
            this.porcentaje = actual.multiply(BigDecimal.valueOf(100))
                    .divide(objetivo, 2, RoundingMode.HALF_UP);
            this.completada = false;
        }
    }

    public static AvanceDeMeta evaluar(BigDecimal objetivo, BigDecimal actual) {
        if (objetivo == null || objetivo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El objetivo debe ser mayor a cero");
        }
        if (actual == null || actual.compareTo(BigDecimal.ZERO) < 0) {
            actual = BigDecimal.ZERO;
        }
        return new AvanceDeMeta(objetivo, actual);
    }

    public BigDecimal getPorcentaje() { return porcentaje; }
    public boolean isCompletada() { return completada; }
}
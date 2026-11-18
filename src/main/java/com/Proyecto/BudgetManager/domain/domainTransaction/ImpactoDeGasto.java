package com.Proyecto.BudgetManager.domain.domainTransaction;

import java.math.BigDecimal;

/**
 * Regla de negocio pura: evalúa el impacto de un gasto sobre el saldo disponible.
 * No depende de Spring, JPA ni de la base de datos.
 */
public final class ImpactoDeGasto {

    public enum Estado {
        SEGURO,          // El gasto no compromete el saldo
        EN_RIESGO,       // El gasto deja el saldo muy bajo (menos del 10%)
        INSUFICIENTE     // El gasto supera el saldo disponible
    }

    private final BigDecimal saldoAnterior;
    private final BigDecimal montoGasto;
    private final BigDecimal saldoRestante;
    private final Estado estado;
    private final BigDecimal porcentajeConsumido;

    private ImpactoDeGasto(BigDecimal saldoAnterior, BigDecimal montoGasto) {
        this.saldoAnterior = saldoAnterior;
        this.montoGasto = montoGasto;
        this.saldoRestante = saldoAnterior.subtract(montoGasto);
        this.porcentajeConsumido = saldoAnterior.compareTo(BigDecimal.ZERO) == 0
                ? BigDecimal.ZERO
                : montoGasto.multiply(BigDecimal.valueOf(100))
                            .divide(saldoAnterior, 2, java.math.RoundingMode.HALF_UP);
        this.estado = calcularEstado();
    }

    public static ImpactoDeGasto evaluar(BigDecimal saldoAnterior, BigDecimal montoGasto) {
        if (saldoAnterior == null) {
            throw new IllegalArgumentException("El saldo no puede ser nulo");
        }
        if (montoGasto == null || montoGasto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del gasto debe ser mayor que cero");
        }
        return new ImpactoDeGasto(saldoAnterior, montoGasto);
    }

    private Estado calcularEstado() {
        if (saldoRestante.compareTo(BigDecimal.ZERO) < 0) {
            return Estado.INSUFICIENTE;
        }
        // Si queda menos del 10% del saldo original, es riesgo
        BigDecimal diezPorCiento = saldoAnterior.multiply(BigDecimal.valueOf(0.10));
        if (saldoRestante.compareTo(diezPorCiento) < 0) {
            return Estado.EN_RIESGO;
        }
        return Estado.SEGURO;
    }

    public BigDecimal getSaldoAnterior() { return saldoAnterior; }
    public BigDecimal getMontoGasto() { return montoGasto; }
    public BigDecimal getSaldoRestante() { return saldoRestante; }
    public Estado getEstado() { return estado; }
    public BigDecimal getPorcentajeConsumido() { return porcentajeConsumido; }

    public boolean esSeguro() { return estado == Estado.SEGURO; }
    public boolean esInsuficiente() { return estado == Estado.INSUFICIENTE; }
}

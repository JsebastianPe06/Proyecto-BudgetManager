package com.Proyecto.BudgetManager.domain;

import com.Proyecto.BudgetManager.enums.BudgetEstado;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Regla pura: cómo va un presupuesto (general o de una categoría)
 * según lo gastado y el límite. No depende de Spring ni de la base de datos.
 *
 * Umbrales (por confirmar con el equipo):
 *   menos de 70 %      -> NORMAL
 *   de 70 % a 90 %     -> PRECAUCION
 *   de 90 % a 100 %    -> RIESGO
 *   más de 100 %       -> EXCEDIDO
 */
public class EstadoPresupuesto {

    public static final BigDecimal UMBRAL_PRECAUCION = new BigDecimal("70");
    public static final BigDecimal UMBRAL_RIESGO = new BigDecimal("90");
    private static final BigDecimal CIEN = new BigDecimal("100");

    /** Porcentaje usado del límite, con 2 decimales. Ej: 400.000 de 500.000 -> 80.00 */
    public BigDecimal porcentajeUsado(BigDecimal gastado, BigDecimal limite)
    {
        validarMontos(gastado, limite);
        return gastado.multiply(CIEN).divide(limite, 2, RoundingMode.HALF_UP);
    }

    /** Lo que queda disponible. Negativo si ya se pasó del límite. */
    public BigDecimal disponible(BigDecimal gastado, BigDecimal limite)
    {
        validarMontos(gastado, limite);
        return limite.subtract(gastado);
    }

    /** Estado del presupuesto según el porcentaje usado. */
    public BudgetEstado calcularEstado(BigDecimal gastado, BigDecimal limite)
    {
        BigDecimal porcentaje = porcentajeUsado(gastado, limite);

        if (porcentaje.compareTo(CIEN) > 0)
        {
            return BudgetEstado.EXCEDIDO;
        }
        if (porcentaje.compareTo(UMBRAL_RIESGO) >= 0)
        {
            return BudgetEstado.RIESGO;
        }
        if (porcentaje.compareTo(UMBRAL_PRECAUCION) >= 0)
        {
            return BudgetEstado.PRECAUCION;
        }
        return BudgetEstado.NORMAL;
    }

    /**
     * Impacto de un gasto: en qué estado quedaría el presupuesto si se registra.
     * Sirve para avisar ANTES de guardar (el sistema informa, no bloquea).
     */
    public BudgetEstado estadoConGasto(BigDecimal gastado, BigDecimal limite, BigDecimal nuevoGasto)
    {
        validarMontos(gastado, limite);
        if (nuevoGasto == null || nuevoGasto.compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new IllegalArgumentException("El monto del gasto debe ser mayor que cero");
        }
        return calcularEstado(gastado.add(nuevoGasto), limite);
    }

    private void validarMontos(BigDecimal gastado, BigDecimal limite)
    {
        if (limite == null || limite.compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new IllegalArgumentException("El límite debe ser mayor que cero");
        }
        if (gastado == null || gastado.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new IllegalArgumentException("El gasto acumulado no puede ser negativo");
        }
    }
}

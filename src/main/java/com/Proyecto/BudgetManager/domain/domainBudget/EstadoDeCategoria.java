package com.Proyecto.BudgetManager.domain.domainBudget;

import java.math.BigDecimal;

public final class EstadoDeCategoria {
    public enum Estado { SEGURO, EN_RIESGO, EXCEDIDO }

    private final BigDecimal limite;
    private final BigDecimal gastado;
    private final BigDecimal restante;
    private final Estado estado;

    private EstadoDeCategoria(BigDecimal limite, BigDecimal gastado) {
        this.limite = limite;
        this.gastado = gastado;
        this.restante = limite.subtract(gastado);
        
        if (restante.compareTo(BigDecimal.ZERO) < 0) {
            this.estado = Estado.EXCEDIDO;
        } else if (restante.compareTo(limite.multiply(BigDecimal.valueOf(0.1))) < 0) {
            this.estado = Estado.EN_RIESGO;
        } else {
            this.estado = Estado.SEGURO;
        }
    }

    public static EstadoDeCategoria evaluar(BigDecimal limite, BigDecimal gastado) {
        if (limite == null || limite.compareTo(BigDecimal.ZERO) <= 0) 
            throw new IllegalArgumentException("El límite debe ser mayor a cero");
        if (gastado == null || gastado.compareTo(BigDecimal.ZERO) < 0) 
            throw new IllegalArgumentException("El gastado no puede ser negativo");
        return new EstadoDeCategoria(limite, gastado);
    }

    public BigDecimal getRestante() { return restante; }
    public Estado getEstado() { return estado; }
}
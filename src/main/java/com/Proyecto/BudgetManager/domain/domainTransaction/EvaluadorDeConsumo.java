package com.Proyecto.BudgetManager.domain.domainTransaction;

import java.util.List;

public final class EvaluadorDeConsumo {
    
    public enum NivelConciencia { ALTO, MEDIO, BAJO }

    public static NivelConciencia evaluar(List<Boolean> satisfacciones) {
        if (satisfacciones == null || satisfacciones.isEmpty()) {
            return NivelConciencia.ALTO;
        }
        long insatisfechos = satisfacciones.stream().filter(s -> !s).count();
        double porcentajeInsatisfaccion = (double) insatisfechos / satisfacciones.size();
        if (porcentajeInsatisfaccion >= 0.5) {
            return NivelConciencia.BAJO; // La mitad o más de los gastos no fueron conscientes
        } else if (porcentajeInsatisfaccion >= 0.2) {
            return NivelConciencia.MEDIO; // Entre 20% y 50% fueron no conscientes
        } else {
            return NivelConciencia.ALTO; // Menos del 20% no conscientes
        }
    }
}
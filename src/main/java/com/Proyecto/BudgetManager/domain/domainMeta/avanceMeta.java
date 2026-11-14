package com.Proyecto.BudgetManager.domain;
import java.math.BigDecimal;
import com.Proyecto.BudgetManager.entity.fecha;
import com.Proyecto.BudgetManager.entity.MetaAhorro;

public class avanceMeta {

	public BigDecimal calcular_faltante(MetaAhorro meta) {
		BigDecimal total = meta.getMontos().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		return total;
	}

	public float porcentaje(MetaAhorro meta) {
		/*
		Reterna el valor del porcentaje faltante a modo de decimal * 100 con regla
		de 3, razón por la uqe multiplica por 10000 directamente
		*/
		BigDecimal total = calcular_faltante(meta);
		BigDecimal cien = BigDecimal.valueOf(10000);
		total = total.multiply(cien);
		total = total.divide(meta.getObjetivo());
		float porcentaje = total.floatValue();
		return porcentaje;
	}

	public void add_montos(BigDecimal nuevo_monto) {
		nuevo_monto.add(nuevo_monto);
	}
}
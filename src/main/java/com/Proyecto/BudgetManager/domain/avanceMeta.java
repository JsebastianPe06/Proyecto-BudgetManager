package com.Proyecto.BudgetManager.domain;
import java.math.BigDecimal;
import com.Proyecto.BudgetManager.entity.fecha;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class avanceMeta {
	@Getter 
	private fecha fecha_creacion;
	@Getter @Setter
	private fecha fecha_finalizacion;
	@Getter
	private List<BigDecimal> montos;
	@Getter @Setter 
	private BigDecimal objetivo;
	private List<BigDecimal> nuevos_montos;

	public BigDecimal calcular_faltante() {
		BigDecimal total = montos.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		return total;
	}

	public float porcentaje() {
		/*
		Reterna el valor del porcentaje faltante a modo de decimal * 100 con regla
		de 3, razón por la uqe multiplica por 10000 directamente
		*/
		BigDecimal total = calcular_faltante();
		BigDecimal cien = BigDecimal.valueOf(10000);
		total = total.multiply(cien);
		total = total.divide(this.objetivo);
		float porcentaje = total.floatValue();
		return porcentaje;
	}

	public void add_montos(BigDecimal nuevo_monto) {
		nuevo_monto.add(nuevo_monto);
	}
}
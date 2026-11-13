package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.domain.BudgetValid;
import com.Proyecto.BudgetManager.domain.EstadoPresupuesto;
import com.Proyecto.BudgetManager.dto.BudgetForm;
import com.Proyecto.BudgetManager.dto.BudgetResumenDTO;
import com.Proyecto.BudgetManager.dto.CategoriaEstadoDTO;
import com.Proyecto.BudgetManager.dto.LimiteCategoriaForm;
import com.Proyecto.BudgetManager.entity.Budget;
import com.Proyecto.BudgetManager.entity.BudgetCategoria;
import com.Proyecto.BudgetManager.entity.Categoria;
import com.Proyecto.BudgetManager.entity.fecha;
import com.Proyecto.BudgetManager.enums.BudgetEstado;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Presupuesto por periodo y límites por categoría (US-03, FR-09).
 *
 * Solo orquesta: arma los objetos, llama a las reglas puras del dominio
 * (BudgetValid, EstadoPresupuesto) y devuelve DTOs para la vista.
 *
 * Los métodos marcados "TODO capa de datos" dependen de los repositorios
 * que va a implementar la capa de datos (JPA). Mientras tanto lanzan
 * UnsupportedOperationException, igual que los repositorios actuales.
 */
// @Service
public class BudgetService {

    BudgetValid budgetValid = new BudgetValid();
    EstadoPresupuesto estadoPresupuesto = new EstadoPresupuesto();

    // ------------------------------------------------------------------
    // Crear y configurar
    // ------------------------------------------------------------------

    /** Crea un presupuesto para un periodo (US-03). */
    public void crearBudget(int userId, BudgetForm form)
    {
        if (form == null)
        {
            throw new IllegalArgumentException("Los datos del presupuesto son obligatorios");
        }

        Budget budget = new Budget(0, userId, form.getTotalLimite(),
                aFecha(form.getFechaInicio()), aFecha(form.getFechaFinal()), BudgetEstado.NORMAL);

        budgetValid.validar(budget);

        // BudgetRepository.guardar(budget);   TODO capa de datos
    }

    /** Asigna un límite a una categoría dentro de un presupuesto (US-03, escenario 2). */
    public void asignarLimiteCategoria(int userId, LimiteCategoriaForm form)
    {
        if (form == null || form.getBudgetId() == null || form.getCategoriaId() == null)
        {
            throw new IllegalArgumentException("El presupuesto y la categoría son obligatorios");
        }

        Budget budget = buscarBudgetDelUsuario(userId, form.getBudgetId());

        BudgetCategoria nuevo = new BudgetCategoria(0, budget.getId(), form.getCategoriaId(),
                form.getLimite(), BudgetEstado.NORMAL);

        List<BudgetCategoria> existentes = buscarLimitesDelBudget(budget.getId());

        budgetValid.validarLimiteCategoria(nuevo, budget, existentes);

        // BudgetCategoriaRepository.guardar(nuevo);   TODO capa de datos
    }

    // ------------------------------------------------------------------
    // Evaluar
    // ------------------------------------------------------------------

    /** Estado general del presupuesto: todo lo gastado en el periodo contra el límite total. */
    public BudgetEstado evaluarBudgetGeneral(int userId, int budgetId)
    {
        Budget budget = buscarBudgetDelUsuario(userId, budgetId);
        BigDecimal gastado = sumarGastosPeriodo(userId,
                aLocalDate(budget.getFechaInicio()), aLocalDate(budget.getFechaFinal()));

        return estadoPresupuesto.calcularEstado(gastado, budget.getTotalLimite());
    }

    /** Estado de una categoría dentro del presupuesto. */
    public CategoriaEstadoDTO evaluarBudgetCategoria(int userId, int budgetId, int categoriaId)
    {
        Budget budget = buscarBudgetDelUsuario(userId, budgetId);

        for (BudgetCategoria limite : buscarLimitesDelBudget(budgetId))
        {
            if (limite.getCategoryId() == categoriaId)
            {
                return armarEstadoCategoria(userId, budget, limite);
            }
        }
        throw new IllegalArgumentException("Esta categoría no tiene límite en este presupuesto");
    }

    /**
     * Impacto de un gasto antes de registrarlo: en qué estado quedaría la categoría.
     * Lo puede consultar TransactionService al registrar un gasto (sección 3.2 del documento).
     * Si la categoría no tiene límite en el presupuesto, devuelve null (no hay nada que evaluar).
     */
    public BudgetEstado evaluarImpactoGasto(int userId, int budgetId, int categoriaId, BigDecimal monto)
    {
        Budget budget = buscarBudgetDelUsuario(userId, budgetId);

        for (BudgetCategoria limite : buscarLimitesDelBudget(budgetId))
        {
            if (limite.getCategoryId() == categoriaId)
            {
                BigDecimal gastado = sumarGastosCategoria(userId, categoriaId,
                        aLocalDate(budget.getFechaInicio()), aLocalDate(budget.getFechaFinal()));
                return estadoPresupuesto.estadoConGasto(gastado, limite.getLimite(), monto);
            }
        }
        return null;
    }

    /** Resumen completo: total del periodo y estado de cada categoría con límite. */
    public BudgetResumenDTO getBudgetResumen(int userId, int budgetId)
    {
        Budget budget = buscarBudgetDelUsuario(userId, budgetId);
        LocalDate desde = aLocalDate(budget.getFechaInicio());
        LocalDate hasta = aLocalDate(budget.getFechaFinal());

        BigDecimal gastado = sumarGastosPeriodo(userId, desde, hasta);
        BigDecimal limite = budget.getTotalLimite();

        List<CategoriaEstadoDTO> categorias = new ArrayList<>();
        for (BudgetCategoria limiteCategoria : buscarLimitesDelBudget(budgetId))
        {
            categorias.add(armarEstadoCategoria(userId, budget, limiteCategoria));
        }

        return new BudgetResumenDTO(
                budget.getId(), desde, hasta,
                limite,
                gastado,
                estadoPresupuesto.disponible(gastado, limite),
                estadoPresupuesto.porcentajeUsado(gastado, limite),
                estadoPresupuesto.calcularEstado(gastado, limite),
                categorias);
    }

    // ------------------------------------------------------------------
    // Ayudantes
    // ------------------------------------------------------------------

    private CategoriaEstadoDTO armarEstadoCategoria(int userId, Budget budget, BudgetCategoria limiteCategoria)
    {
        BigDecimal limite = limiteCategoria.getLimite();
        BigDecimal gastado = sumarGastosCategoria(userId, limiteCategoria.getCategoryId(),
                aLocalDate(budget.getFechaInicio()), aLocalDate(budget.getFechaFinal()));
        Categoria categoria = buscarCategoria(limiteCategoria.getCategoryId());

        return new CategoriaEstadoDTO(
                limiteCategoria.getCategoryId(),
                categoria.getName(),
                limite,
                gastado,
                estadoPresupuesto.disponible(gastado, limite),
                estadoPresupuesto.porcentajeUsado(gastado, limite),
                estadoPresupuesto.calcularEstado(gastado, limite));
    }

    /** Busca el presupuesto y verifica que sea del usuario (nadie ve presupuestos ajenos). */
    private Budget buscarBudgetDelUsuario(int userId, int budgetId)
    {
        Budget budget = buscarBudget(budgetId);
        if (budget == null)
        {
            throw new IllegalArgumentException("El presupuesto no existe");
        }
        if (budget.getUserId() != userId)
        {
            throw new IllegalArgumentException("No puedes ver ni modificar este presupuesto");
        }
        return budget;
    }

    /** Convierte la fecha del formulario (LocalDate) a la clase fecha del equipo. */
    private static fecha aFecha(LocalDate date)
    {
        if (date == null)
        {
            return null;
        }
        return new fecha(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }

    /** Convierte la clase fecha del equipo a LocalDate (para DTOs y consultas). */
    private static LocalDate aLocalDate(fecha date)
    {
        if (date == null)
        {
            return null;
        }
        return LocalDate.of(date.getYear(), date.getMes(), date.getDia());
    }

    // ------------------------------------------------------------------
    // TODO capa de datos: reemplazar por llamadas a los repositorios JPA
    // ------------------------------------------------------------------

    private Budget buscarBudget(int budgetId)
    {
        throw new UnsupportedOperationException("Falta BudgetRepository (capa de datos)");
    }

    private List<BudgetCategoria> buscarLimitesDelBudget(int budgetId)
    {
        throw new UnsupportedOperationException("Falta BudgetCategoriaRepository (capa de datos)");
    }

    private Categoria buscarCategoria(int categoriaId)
    {
        throw new UnsupportedOperationException("Falta CategoriaRepository (capa de datos)");
    }

    /** Suma de todos los GASTOS del usuario entre dos fechas (incluidas). */
    private BigDecimal sumarGastosPeriodo(int userId, LocalDate desde, LocalDate hasta)
    {
        throw new UnsupportedOperationException("Falta MovimientoRepository.sumarGastos (capa de datos)");
    }

    /** Suma de los GASTOS del usuario en una categoría entre dos fechas (incluidas). */
    private BigDecimal sumarGastosCategoria(int userId, int categoriaId, LocalDate desde, LocalDate hasta)
    {
        throw new UnsupportedOperationException("Falta MovimientoRepository.sumarGastos (capa de datos)");
    }
}

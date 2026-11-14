package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.domain.domainBudget.Movimiento;
import com.Proyecto.BudgetManager.domain.domainCategoria.Categoria;
import com.Proyecto.BudgetManager.domain.domainCategoria.CategoriaValid;
import com.Proyecto.BudgetManager.domain.domainFecha.Fecha;
import com.Proyecto.BudgetManager.domain.domainMeta.MovimientoValid;
import com.Proyecto.BudgetManager.enums.PrioridadGasto;
import com.Proyecto.BudgetManager.enums.TipoGasto;
import com.Proyecto.BudgetManager.enums.TipoMovimiento;
import java.math.BigDecimal;

// @Service
public class TransactionService {
    
    MovimientoValid movimientoValid = new MovimientoValid();
    CategoriaValid categoriaValid= new CategoriaValid();;
    
    public void registrarGasto(int userId,String descripcion, BigDecimal monto,Fecha date, Categoria categoria, 
            PrioridadGasto prioridad, TipoGasto tipo) 
    {
        Movimiento move = new Movimiento(0, userId,descripcion, monto, date, TipoMovimiento.GASTO, categoria.getId(),
                prioridad,tipo);

        movimientoValid.validar(move);

        categoriaValid.validarPropiedad(categoria, userId);

        categoriaValid.validarTipo(categoria, move.getTipo());

        // MovimientoRepository.guardar(move);
    }
    
    public void registrarIngreso(int userId,String descripcion, BigDecimal monto,Fecha date, Categoria categoria) 
    {
        Movimiento move = new Movimiento(0, userId,descripcion, monto, date, TipoMovimiento.INGRESO, categoria.getId());

        movimientoValid.validar(move);

        categoriaValid.validarPropiedad(categoria, userId);

        categoriaValid.validarTipo(categoria, move.getTipo());

        //MovimientoRepository.guardar(move);
    }

    public void getMovimientosPorPerido()
    {
        
    }

    public void eliminarMovimiento(int userId,int Id)
    {
        //MovimientoRepository.eliminar(Id, userId);
    }
    
}

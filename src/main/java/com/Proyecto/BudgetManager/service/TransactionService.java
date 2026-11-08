package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.domain.CategoriaValid;
import com.Proyecto.BudgetManager.domain.MovimientoValid;
import com.Proyecto.BudgetManager.entity.Categoria;
import com.Proyecto.BudgetManager.entity.Movimiento;
import com.Proyecto.BudgetManager.entity.fecha;
import com.Proyecto.BudgetManager.enums.PrioridadGasto;
import com.Proyecto.BudgetManager.enums.TipoGasto;
import com.Proyecto.BudgetManager.enums.TipoMovimiento;
import com.Proyecto.BudgetManager.repository.MovimientoRepository;
import java.math.BigDecimal;

// @Service
public class TransactionService {
    
    MovimientoValid movimientoValid;
    CategoriaValid categoriaValid;
    
    public void registrarGasto(int userId,String descripcion, BigDecimal monto,fecha date, Categoria categoria, 
            PrioridadGasto prioridad, TipoGasto tipo) 
    {
        //para el Id del movimiento creo que iria una notacion Spring
        Movimiento move = new Movimiento(0, userId,descripcion, monto, date, TipoMovimiento.GASTO, categoria.getId(),
                prioridad,tipo);

        movimientoValid.validar(move);

        categoriaValid.validarPropiedad(categoria, userId);

        categoriaValid.validarTipo(categoria, move.getTipo());

        MovimientoRepository.guardar(move);
    }
    
    public void registrarIngreso()
    {
        
    }

    public void getMovimientosPorPerido()
    {
        
    }

    public void eliminarMovimiento()
    {
        
    }
    
}

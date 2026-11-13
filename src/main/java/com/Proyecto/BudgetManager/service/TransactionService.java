package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.domain.CategoriaValid;
import com.Proyecto.BudgetManager.domain.DateManager;
import com.Proyecto.BudgetManager.domain.MovimientoValid;
import com.Proyecto.BudgetManager.dto.GastoForm;
import com.Proyecto.BudgetManager.dto.IngresoForm;
import com.Proyecto.BudgetManager.entity.Categoria;
import com.Proyecto.BudgetManager.entity.Movimiento;
import com.Proyecto.BudgetManager.entity.fecha;
import com.Proyecto.BudgetManager.enums.TipoMovimiento;
import com.Proyecto.BudgetManager.repository.CategoriaRepository;
import com.Proyecto.BudgetManager.repository.MovimientoRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    
    private final MovimientoValid movimientoValid = new MovimientoValid();
    private final CategoriaValid categoriaValid= new CategoriaValid();;
    
    public void registrarGasto(int userId, GastoForm form) 
    {
        Categoria categoria = CategoriaRepository.findById(form.getCategoriaId()).orElseThrow();
        
        fecha date = DateManager.aFecha(form.getFecha());
        
        Movimiento move = new Movimiento(0, userId,form.getDescripcion(), form.getMonto(), date,
                TipoMovimiento.GASTO, form.getCategoriaId(),form.getPrioridad(),form.getTipoGasto());

        movimientoValid.validar(move);

        categoriaValid.validarPropiedad(categoria, userId);

        categoriaValid.validarTipo(categoria, move.getTipo());

        // MovimientoRepository.guardar(move);
    }
    
    public void registrarIngreso(int userId, IngresoForm form) 
    {
        Categoria categoria = CategoriaRepository.findById(form.getCategoriaId()).orElseThrow();
        
        fecha date = DateManager.aFecha(form.getFecha());
        
        Movimiento move = new Movimiento(0, userId,form.getDescripcion(), form.getMonto(), date,
                TipoMovimiento.INGRESO, form.getCategoriaId());

        movimientoValid.validar(move);

        categoriaValid.validarPropiedad(categoria, userId);

        categoriaValid.validarTipo(categoria, move.getTipo());

        //MovimientoRepository.guardar(move);
    }

    public List<Movimiento> getMovimientosPorPeriodo(int userId,LocalDate fechaInicial,LocalDate fechaFinal)   
    {
        List<Movimiento> moves = null; 
        
        fecha dateInicial = DateManager.aFecha(fechaInicial);
        fecha dateFinal = DateManager.aFecha(fechaFinal);
        
        // moves = MovimientoRepository.findBy????(userId,dateInicial,dateFinal).orElseThrow();
        
        return moves;
    }

    public void eliminarMovimiento(int userId,int moveId)
    {
        Movimiento move = MovimientoRepository.findByIdAndUserId(moveId, userId).orElseThrow();

        MovimientoRepository.eliminar(move.getId());
    }
}
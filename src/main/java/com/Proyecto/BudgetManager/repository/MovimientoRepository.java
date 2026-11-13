package com.Proyecto.BudgetManager.repository;

import com.Proyecto.BudgetManager.entity.Movimiento;
import java.time.LocalDate;
import java.util.List;

public class MovimientoRepository {

    public static void guardar(Movimiento move) {
        throw new UnsupportedOperationException("Falta por implementar");
    }

    public static void eliminar(int Id) {
        throw new UnsupportedOperationException("Falta por implementar");    
    }

    public static Movimiento findByIdAndUserId(int moveId, int userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //esto si se puede xd?
    public static List<Movimiento> findByUserIdAndFechaInicialAndFechaFinal(int userId, LocalDate fechaInicial, LocalDate fechaFinal) {
        throw new UnsupportedOperationException("Not supported yet.");
}   }   

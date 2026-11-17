package com.Proyecto.BudgetManager.repository;

import com.Proyecto.BudgetManager.entity.SavingsTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

public interface SavingsTransactionRepository extends JpaRepository<SavingsTransactionEntity, Long> {
    
    // Suma lo abonado en un rango de fechas para un usuario
    @Query("SELECT COALESCE(SUM(st.amount), 0) FROM SavingsTransactionEntity st " +
        "WHERE st.goal.user.email = :email " +
        "AND st.transactionDate >= :startDate AND st.transactionDate <= :endDate")
    BigDecimal sumSavingsByUserAndDateRange(
            @Param("email") String email,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);
}

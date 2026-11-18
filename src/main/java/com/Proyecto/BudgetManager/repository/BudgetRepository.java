package com.Proyecto.BudgetManager.repository;

import com.Proyecto.BudgetManager.entity.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<BudgetEntity, Long> {
    Optional<BudgetEntity> findByUserEmailAndMonthAndYear(String email, int month, int year);
}

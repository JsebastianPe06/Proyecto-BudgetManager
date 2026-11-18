package com.Proyecto.BudgetManager.repository;

import com.Proyecto.BudgetManager.entity.SavingsGoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SavingsGoalRepository extends JpaRepository<SavingsGoalEntity, Long> {
    List<SavingsGoalEntity> findByUserEmailOrderByDeadlineAsc(String email);
}
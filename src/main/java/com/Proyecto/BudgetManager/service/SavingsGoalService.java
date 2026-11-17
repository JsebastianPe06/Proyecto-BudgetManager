package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.domain.domainMeta.AvanceDeMeta;
import com.Proyecto.BudgetManager.dto.SavingsGoalRequest;
import com.Proyecto.BudgetManager.entity.SavingsGoalEntity;
import com.Proyecto.BudgetManager.entity.SavingsTransactionEntity;
import com.Proyecto.BudgetManager.entity.UserEntity;
import com.Proyecto.BudgetManager.exception.BusinessException;
import com.Proyecto.BudgetManager.repository.SavingsGoalRepository;
import com.Proyecto.BudgetManager.repository.SavingsTransactionRepository;
import com.Proyecto.BudgetManager.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class SavingsGoalService {
    private final SavingsGoalRepository goals;
    private final UserRepository users;
    private final SavingsTransactionRepository savingsTransactions;

    public SavingsGoalService(SavingsGoalRepository goals, UserRepository users, SavingsTransactionRepository savingsTransactions) {
        this.goals = goals;
        this.users = users;
        this.savingsTransactions = savingsTransactions;
    }

    @Transactional
    public void createGoal(String email, SavingsGoalRequest req) {
        UserEntity user = users.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));
        SavingsGoalEntity goal = new SavingsGoalEntity();
        goal.setUser(user);
        goal.setName(req.name());
        goal.setTargetAmount(req.targetAmount());
        goal.setCurrentAmount(BigDecimal.ZERO);
        goal.setDeadline(req.deadline());
        goals.save(goal);
    }

    public List<SavingsGoalEntity> getGoals(String email) {
        return goals.findByUserEmailOrderByDeadlineAsc(email);
    }

    public AvanceDeMeta getProgress(SavingsGoalEntity goal) {
        return AvanceDeMeta.evaluar(goal.getTargetAmount(), goal.getCurrentAmount());
    }

    @Transactional
    public void addFunds(String email, Long goalId, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("El monto a abonar debe ser mayor a cero");
        }
        SavingsGoalEntity goal = goals.findById(goalId)
                .orElseThrow(() -> new BusinessException("Meta no encontrada"));
        if (!goal.getUser().getEmail().equals(email)) {
            throw new BusinessException("No tienes permiso para modificar esta meta");
        }
        
        goal.setCurrentAmount(goal.getCurrentAmount().add(amount));
        goals.save(goal);
        registrarTransaccionDeAhorro(goal, amount);
    }

    @Transactional
    public void cerrarMesYAbonar(String email, BigDecimal montoSobranteTotal, BigDecimal montoAAbonar, Long goalId) {
        if (montoAAbonar == null || montoAAbonar.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("El monto a abonar debe ser mayor a cero.");
        }
        SavingsGoalEntity goal = goals.findById(goalId)
                .orElseThrow(() -> new BusinessException("Meta no encontrada"));
        if (!goal.getUser().getEmail().equals(email)) {
            throw new BusinessException("No tienes permiso para modificar esta meta");
        }
        BigDecimal faltanteMeta = goal.getTargetAmount().subtract(goal.getCurrentAmount());
        if (faltanteMeta.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("¡Esta meta ya está completada!");
        }
        if (montoAAbonar.compareTo(montoSobranteTotal) > 0) {
            throw new BusinessException("No puedes abonar más de tu balance mensual ($" + montoSobranteTotal + ").");
        }
        if (montoAAbonar.compareTo(faltanteMeta) > 0) {
            throw new BusinessException("No puedes abonar más de lo que falta para completar la meta. Faltan $" + faltanteMeta + ".");
        }
        goal.setCurrentAmount(goal.getCurrentAmount().add(montoAAbonar));
        goals.save(goal);
        // GUARDAR HISTORIAL
        registrarTransaccionDeAhorro(goal, montoAAbonar);
    }

    private void registrarTransaccionDeAhorro(SavingsGoalEntity goal, BigDecimal amount) {
        SavingsTransactionEntity historial = new SavingsTransactionEntity();
        historial.setGoal(goal);
        historial.setAmount(amount);
        historial.setTransactionDate(LocalDate.now().toString());
        savingsTransactions.save(historial);
    }
}
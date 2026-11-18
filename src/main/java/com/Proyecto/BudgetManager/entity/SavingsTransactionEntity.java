package com.Proyecto.BudgetManager.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "savings_transactions")
public class SavingsTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "goal_id", nullable = false)
    private SavingsGoalEntity goal;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private String transactionDate;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public SavingsGoalEntity getGoal() { return goal; }
    public void setGoal(SavingsGoalEntity goal) { this.goal = goal; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getTransactionDate() { return transactionDate; }
    public void setTransactionDate(String transactionDate) { this.transactionDate = transactionDate; }
}
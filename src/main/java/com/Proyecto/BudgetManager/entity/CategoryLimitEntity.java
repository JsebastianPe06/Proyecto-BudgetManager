package com.Proyecto.BudgetManager.entity;

import com.Proyecto.BudgetManager.enums.Category;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "category_limits")
public class CategoryLimitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "budget_id", nullable = false)
    private BudgetEntity budget;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal limitAmount;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public BudgetEntity getBudget() { return budget; }
    public void setBudget(BudgetEntity budget) { this.budget = budget; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public BigDecimal getLimitAmount() { return limitAmount; }
    public void setLimitAmount(BigDecimal limitAmount) { this.limitAmount = limitAmount; }
}
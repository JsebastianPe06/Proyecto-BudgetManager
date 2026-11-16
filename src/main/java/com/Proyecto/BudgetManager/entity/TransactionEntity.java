package com.Proyecto.BudgetManager.entity;

import com.Proyecto.BudgetManager.enums.TransactionType;
import com.Proyecto.BudgetManager.enums.Category;
import com.Proyecto.BudgetManager.enums.Priority;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type;   // INGRESO | GASTO

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;      // NECESIDADES | OCIO | CULTURA | EXTRAS

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;      // ALTA | MEDIA | BAJA

    @Column(nullable = false)
    private String transactionDate;

    @Column(nullable = false)
    private boolean planned;        // true = gasto planificado (aún no realizado)

    @Column(nullable = false)
    private boolean recurrent;      // true = se repite cada mes

    private Integer recurrentDay;   // día del mes (1-31) si es recurrente

    @Column(nullable = false, updatable = false)
    private String createdAt;

    // --- Constructores ---

    public TransactionEntity() {}

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now().toString();
    }

    // --- Getters y Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public String getTransactionDate() { return transactionDate; }
    public void setTransactionDate(String transactionDate) { this.transactionDate = transactionDate; }

    public boolean isPlanned() { return planned; }
    public void setPlanned(boolean planned) { this.planned = planned; }

    public boolean isRecurrent() { return recurrent; }
    public void setRecurrent(boolean recurrent) { this.recurrent = recurrent; }

    public Integer getRecurrentDay() { return recurrentDay; }
    public void setRecurrentDay(Integer recurrentDay) { this.recurrentDay = recurrentDay; }

    public String getCreatedAt() { return createdAt; }
}

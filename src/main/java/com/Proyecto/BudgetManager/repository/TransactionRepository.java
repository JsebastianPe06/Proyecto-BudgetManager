package com.Proyecto.BudgetManager.repository;

import com.Proyecto.BudgetManager.entity.TransactionEntity;
import com.Proyecto.BudgetManager.enums.Category;
import com.Proyecto.BudgetManager.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByUserEmailOrderByTransactionDateDesc(String email);

    List<TransactionEntity> findByUserEmailAndTransactionDateBetweenOrderByTransactionDateDesc(
        String email, String from, String to);

    List<TransactionEntity> findByUserEmailAndCategoryOrderByTransactionDateDesc(
        String email, Category category);

    List<TransactionEntity> findByUserEmailAndTypeOrderByTransactionDateDesc(
        String email, TransactionType type);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM TransactionEntity t " +
        "WHERE t.user.email = :email AND t.type = :type " +
        "AND t.transactionDate BETWEEN :from AND :to " +
        "AND t.planned = false")
    BigDecimal sumAmountByUserAndTypeAndDateRange(
            @Param("email") String email,
            @Param("type") TransactionType type,
            @Param("from") String from,
            @Param("to") String to);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM TransactionEntity t " +
        "WHERE t.user.email = :email AND t.type = 'GASTO' " +
        "AND t.category = :category " +
        "AND t.transactionDate >= :startDate AND t.transactionDate <= :endDate " +
        "AND t.planned = false")
    BigDecimal sumAmountByUserAndCategoryAndDateRange(
            @Param("email") String email,
            @Param("category") com.Proyecto.BudgetManager.enums.Category category,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    @Query("SELECT t.category, COALESCE(SUM(t.amount), 0) FROM TransactionEntity t " +
        "WHERE t.user.email = :email AND t.type = 'GASTO' " +
        "AND t.transactionDate >= :startDate AND t.transactionDate <= :endDate " +
        "AND t.planned = false " +
        "GROUP BY t.category")
    java.util.List<Object[]> sumExpensesByCategory(
            @Param("email") String email,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);
}

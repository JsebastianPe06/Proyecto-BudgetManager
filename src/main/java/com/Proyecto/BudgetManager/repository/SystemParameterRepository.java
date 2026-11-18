package com.Proyecto.BudgetManager.repository;

import com.Proyecto.BudgetManager.entity.SystemParameterEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemParameterRepository
        extends JpaRepository<SystemParameterEntity, String> {
    // findById(String key), findAll(), save(...), deleteById(...) ya vienen de JpaRepository
}
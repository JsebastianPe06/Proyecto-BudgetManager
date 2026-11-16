package com.Proyecto.BudgetManager.service;


import com.Proyecto.BudgetManager.domain.domainUser.AdminParameterValidator;
import com.Proyecto.BudgetManager.domain.domainUser.SystemParameterKeys;
import com.Proyecto.BudgetManager.entity.SystemParameterEntity;
import com.Proyecto.BudgetManager.exception.BusinessException;
import com.Proyecto.BudgetManager.repository.SystemParameterRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
    private final SystemParameterRepository params;
    public AdminService(SystemParameterRepository params) { this.params = params; }

    @Transactional
    public void upsertParameter(String key, String value) {
        if (!SystemParameterKeys.isValid(key)) {
            throw new BusinessException("Clave de parámetro no válida: " + key);
        }
        try {
            AdminParameterValidator.validateText(value);
        } catch (IllegalArgumentException e) {
            throw new BusinessException(e.getMessage());
        }
        SystemParameterEntity p = params.findById(key)
                .orElseGet(() -> new SystemParameterEntity(key, value));
        p.setValue(value);
        params.save(p);
    }

    public java.util.List<SystemParameterEntity> findAllParameters() {
        return params.findAll();
    }
}
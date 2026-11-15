package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.domain.domainUser.AdminParameterValidator;
import com.Proyecto.BudgetManager.entity.SystemParameterEntity;
import com.Proyecto.BudgetManager.repository.SystemParameterRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
    private final SystemParameterRepository params;
    public AdminService(SystemParameterRepository params) { this.params = params; }

    @Transactional
    public void upsertParameter(String key, String value) {
        AdminParameterValidator.validateText(value); // regla pura
        SystemParameterEntity p = params.findById(key).orElseGet(SystemParameterEntity::new);
        p.setKey(key);
        p.setValue(value);
        params.save(p);
    }
}
package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.dto.UserProfileDTO;
import com.Proyecto.BudgetManager.entity.UserEntity;
import com.Proyecto.BudgetManager.repository.UserRepository;
import com.Proyecto.BudgetManager.exception.BusinessException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository users;
    public UserService(UserRepository users) { this.users = users; }

    public UserProfileDTO getProfile(String email) {
        UserEntity u = users.findByEmail(email)
            .orElseThrow(() -> new BusinessException("Usuario no encontrado"));
        return new UserProfileDTO(u.getFullName(), u.getEmail());
    }

    @Transactional
    public void updateProfile(String currentEmail, UserProfileDTO dto) {
        UserEntity u = users.findByEmail(currentEmail).orElseThrow();
        if (!dto.email().equals(currentEmail) && users.existsByEmail(dto.email()))
            throw new BusinessException("Ese correo ya está en uso");
        u.setFullName(dto.fullName());
        u.setEmail(dto.email());
        users.save(u);
    }
}

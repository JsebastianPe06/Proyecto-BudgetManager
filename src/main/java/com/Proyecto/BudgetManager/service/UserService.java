package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.dto.UserProfileDTO;
import com.Proyecto.BudgetManager.entity.UserEntity;
import com.Proyecto.BudgetManager.repository.UserRepository;
import com.Proyecto.BudgetManager.exception.BusinessException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    public UserProfileDTO getProfile(String email) {
        UserEntity u = users.findByEmail(email)
            .orElseThrow(() -> new BusinessException("Usuario no encontrado"));
        return new UserProfileDTO(u.getFullName(), u.getEmail(), null, null, null);
    }

    @Transactional
    public boolean updateProfile(String currentEmail, UserProfileDTO dto) {
        UserEntity u = users.findByEmail(currentEmail)
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));
        if (!passwordEncoder.matches(dto.currentPassword(), u.getPasswordHash())) {
            throw new BusinessException("La contraseña actual es incorrecta.");
        }
        if (dto.newPassword() != null && !dto.newPassword().isBlank()) {
            if (!dto.newPassword().equals(dto.confirmNewPassword())) {
                throw new BusinessException("Las nuevas contraseñas no coinciden.");
            }
            if (dto.newPassword().length() < 8) {
                throw new BusinessException("La nueva contraseña debe tener al menos 8 caracteres.");
            }
            u.setPasswordHash(passwordEncoder.encode(dto.newPassword()));
        }
        boolean emailChanged = !dto.email().equals(currentEmail);
        if (emailChanged && users.existsByEmail(dto.email())) {
            throw new BusinessException("Ese correo ya está en uso");
        }
        u.setFullName(dto.fullName());
        u.setEmail(dto.email());
        users.save(u);
        return emailChanged;
    }
}

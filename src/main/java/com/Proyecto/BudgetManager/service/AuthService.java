package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.entity.UserEntity;
import com.Proyecto.BudgetManager.repository.UserRepository;
import com.Proyecto.BudgetManager.dto.RegisterRequest;
import com.Proyecto.BudgetManager.exception.BusinessException;
import com.Proyecto.BudgetManager.domain.domainUser.PasswordPolicy;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class AuthService {
    private final UserRepository users;
    private final PasswordEncoder encoder; //BCrypt costo 12 (NFR-01)

    public AuthService(UserRepository users, PasswordEncoder encoder) {
        this.users = users; this.encoder = encoder;
    }

    @Transactional
    public void register(RegisterRequest req) {
        if (!PasswordPolicy.isValid(req.password()))
            throw new BusinessException("La contraseña debe tener al menos 8 caracteres");
        if (users.existsByEmail(req.email()))
            throw new BusinessException("El correo ya está registrado");

        UserEntity u = new UserEntity();
        u.setEmail(req.email());
        u.setFullName(req.fullName());
        u.setPasswordHash(encoder.encode(req.password()));
        u.setRole("USER");
        users.save(u);
    }
}

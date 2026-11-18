package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.entity.UserEntity;
import com.Proyecto.BudgetManager.repository.UserRepository;
import com.Proyecto.BudgetManager.dto.RegisterRequest;
import com.Proyecto.BudgetManager.exception.BusinessException;
import com.Proyecto.BudgetManager.domain.domainUser.LoginAttemptPolicy;
import com.Proyecto.BudgetManager.domain.domainUser.PasswordPolicy;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Clock;



@Service
public class AuthService {
    private final UserRepository users;
    private final PasswordEncoder encoder; //BCrypt costo 12 (NFR-01)
    private final Clock clock;

    public AuthService(UserRepository users, PasswordEncoder encoder, Clock clock) {
        this.users = users;
        this.encoder = encoder;
        this.clock = clock;
    }

    @Transactional
    public void register(RegisterRequest req) {
        if (!PasswordPolicy.isValid(req.password()))
            throw new BusinessException("La contraseña debe tener al menos 8 caracteres");
        String email = req.email().trim().toLowerCase();
        if (users.existsByEmail(email))
            throw new BusinessException("El correo ya está registrado");
        UserEntity u = new UserEntity();
        u.setEmail(req.email());
        u.setFullName(req.fullName());
        u.setPasswordHash(encoder.encode(req.password()));
        u.setRole("USER");
        users.save(u);
    }

    @Transactional
    public void registrarIntentoFallido(String email) {
        users.findByEmail(email).ifPresent(u -> {
            LocalDateTime now = LocalDateTime.now(clock);
            if (u.getLockedUntil() != null && !now.isBefore(u.getLockedUntil())) {
                u.setLockedUntil(null);
                u.setFailedAttempts(0);
            }
            u.setFailedAttempts(u.getFailedAttempts() + 1);
            if (LoginAttemptPolicy.shouldLock(u.getFailedAttempts())) {
                u.setLockedUntil(now.plus(LoginAttemptPolicy.LOCK_DURATION));
            }
            users.save(u);
        });
    }

    @Transactional
    public void limpiarIntentos(String email) {
        users.findByEmail(email).ifPresent(u -> {
            u.setFailedAttempts(0);
            u.setLockedUntil(null);
            users.save(u);
        });
    }
}

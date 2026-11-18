package com.Proyecto.BudgetManager.security;

import com.Proyecto.BudgetManager.repository.UserRepository;
import com.Proyecto.BudgetManager.entity.UserEntity;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.Clock;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository users;
    private final Clock clock;

    public CustomUserDetailsService(UserRepository users, Clock clock) {
        this.users = users;
        this.clock = clock;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity u = users.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Credenciales inválidas"));
        LocalDateTime now = LocalDateTime.now(clock);
        boolean locked = u.getLockedUntil() != null && now.isBefore(u.getLockedUntil());
        return User.withUsername(u.getEmail()).password(u.getPasswordHash())
                .roles(u.getRole()).accountLocked(locked).build();
    }
}

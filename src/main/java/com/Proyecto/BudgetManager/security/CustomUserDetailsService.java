package com.Proyecto.BudgetManager.security;

import com.Proyecto.BudgetManager.repository.UserRepository;
import com.Proyecto.BudgetManager.entity.UserEntity;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository users;
    public CustomUserDetailsService(UserRepository users) { this.users = users; }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity u = users.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("No existe"));
        return User.withUsername(u.getEmail()).password(u.getPasswordHash())
                .roles(u.getRole()).accountLocked(u.getLockedUntil() != null &&
                LocalDateTime.now().isBefore(u.getLockedUntil())).build();
    }
}

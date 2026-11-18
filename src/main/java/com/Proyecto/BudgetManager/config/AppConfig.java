package com.Proyecto.BudgetManager.config;

import java.time.Clock;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;


import com.Proyecto.BudgetManager.entity.UserEntity;
import com.Proyecto.BudgetManager.repository.UserRepository;

@Configuration
@EnableScheduling
public class AppConfig {

    @Bean public Clock clock() { 
        return Clock.systemDefaultZone();
    }

    @Bean
    CommandLineRunner seed(UserRepository users, PasswordEncoder encoder,
                        @Value("${app.seed.admin.email}") String adminEmail,
                        @Value("${app.seed.admin.password}") String adminPassword) {
        return args -> {if (!users.existsByEmail(adminEmail)) {UserEntity a = new UserEntity();
                a.setEmail(adminEmail);
                a.setFullName("Administrador");
                a.setPasswordHash(encoder.encode(adminPassword));
                a.setRole("ADMIN");
                users.save(a);
            }
        };
    }
}

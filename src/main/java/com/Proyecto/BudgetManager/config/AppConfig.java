package com.Proyecto.BudgetManager.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Clock;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;


import com.Proyecto.BudgetManager.entity.UserEntity;
import com.Proyecto.BudgetManager.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableScheduling
public class AppConfig {

    @PostConstruct
    public void initDirectories() {
        try {
            Files.createDirectories(Paths.get("./data"));
            Files.createDirectories(Paths.get("./backups"));
            System.out.println("Directorios 'data' y 'backups' verificados.");
        } catch (IOException e) {
            System.err.println("No se pudieron crear los directorios: " + e.getMessage());
        }
    }

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

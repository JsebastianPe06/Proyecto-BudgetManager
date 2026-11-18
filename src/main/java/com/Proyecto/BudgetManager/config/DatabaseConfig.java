package com.Proyecto.BudgetManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class DatabaseConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        try {
            Files.createDirectories(Paths.get("./data"));
            Files.createDirectories(Paths.get("./backups"));
            System.out.println("Carpetas 'data' y 'backups' creadas/verificadas.");
        } catch (IOException e) {
            System.err.println("Error creando carpetas: " + e.getMessage());
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:./data/budgetmanager.db");
        return dataSource;
    }
}
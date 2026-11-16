package com.Proyecto.BudgetManager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // costo ≥ 12 exigido por NFR-01
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, LoginFailureHandler failure, 
                                        LoginSuccessHandler success) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/auth/**", "/login", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/auth/login")             // A dónde va el usuario si no está logueado
                .loginProcessingUrl("/login")         // A DÓNDE SE ENVÍA EL FORMULARIO (POST)
                .successHandler(success)              // Tu manejador de éxito
                .failureHandler(failure)              // Tu manejador de error
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")                 // URL para cerrar sesión
                .logoutSuccessUrl("/auth/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }
}

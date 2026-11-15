package com.Proyecto.BudgetManager.security;

import org.springframework.context.annotation.*;
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
    public SecurityFilterChain filterChain(HttpSecurity http, CustomUserDetailsService uds,
                                        LoginFailureHandler failure)
        throws Exception {http.authorizeHttpRequests(a -> a.requestMatchers("/", "/auth/**", "/css/**")
            .permitAll().requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated())
            .formLogin(f -> f.loginPage("/auth/login").defaultSuccessUrl("/dashboard", true)
            .failureHandler(failure)).logout(l -> l.logoutSuccessUrl("/auth/login"));
        return http.build();
    }
}

package com.Proyecto.BudgetManager.security;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import java.io.IOException;
import org.springframework.stereotype.Component;

import com.Proyecto.BudgetManager.service.AuthService;


@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final AuthService authService;

    public LoginSuccessHandler(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res,
                                        Authentication auth) throws IOException {
        authService.limpiarIntentos(auth.getName());

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        res.sendRedirect(isAdmin ? "/admin" : "/dashboard");
    }
}

package com.Proyecto.BudgetManager.security;

import com.Proyecto.BudgetManager.service.AuthService;

import jakarta.servlet.http.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    private final AuthService authService;

    public LoginFailureHandler(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res,
                                        AuthenticationException ex) throws IOException {
        String email = req.getParameter("username");
        if (email != null) {
            authService.registrarIntentoFallido(email);
        }
        res.sendRedirect("/auth/login?error");
    }
}

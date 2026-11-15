package com.Proyecto.BudgetManager.security;

import com.Proyecto.BudgetManager.repository.UserRepository;
import com.Proyecto.BudgetManager.domain.domainUser.LoginAttemptPolicy;

import jakarta.servlet.http.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    private final UserRepository users;
    public LoginFailureHandler(UserRepository users) { this.users = users; }

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res,
                                    AuthenticationException ex) throws java.io.IOException {
        String email = req.getParameter("username");
        users.findByEmail(email).ifPresent(u -> {u.setFailedAttempts(u.getFailedAttempts() + 1);
            if (LoginAttemptPolicy.shouldLock(u.getFailedAttempts()))
                u.setLockedUntil(LocalDateTime.now().plus(LoginAttemptPolicy.LOCK_DURATION));
            users.save(u);
        });
        res.sendRedirect("/auth/login?error");
    }
}

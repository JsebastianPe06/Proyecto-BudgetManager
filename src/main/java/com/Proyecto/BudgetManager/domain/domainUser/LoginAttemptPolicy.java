package com.Proyecto.BudgetManager.domain.domainUser;

import java.time.Duration;
import java.time.LocalDateTime;

public final class LoginAttemptPolicy {
    public static final int MAX_ATTEMPTS = 5;
    public static final Duration LOCK_DURATION = Duration.ofMinutes(30);

    public static boolean shouldLock(int failedAttempts) {
        return failedAttempts >= MAX_ATTEMPTS;
    }
    public static boolean isStillLocked(LocalDateTime lockedUntil, LocalDateTime now) {
        return lockedUntil != null && now.isBefore(lockedUntil);
    }
}

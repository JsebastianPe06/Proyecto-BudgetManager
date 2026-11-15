package com.Proyecto.BudgetManager.domain.domainUser;

public final class PasswordPolicy {
    public static final int MIN_LENGTH = 8;
    public static boolean isValid(String raw) {
        return raw != null && raw.length() >= MIN_LENGTH;
    }
}
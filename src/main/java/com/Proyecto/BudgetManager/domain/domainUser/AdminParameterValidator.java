package com.Proyecto.BudgetManager.domain.domainUser;

public final class AdminParameterValidator {
    public static final int MAX_TEXT_LEN = 200;
    public static void validateText(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("El texto no puede estar vacío");
        if (value.length() > MAX_TEXT_LEN)
            throw new IllegalArgumentException("Máximo " + MAX_TEXT_LEN + " caracteres");
    }
}
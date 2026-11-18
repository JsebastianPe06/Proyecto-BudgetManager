package com.Proyecto.BudgetManager.domain.domainUser;

import java.util.Set;

public final class SystemParameterKeys {
    public static final String BASE_CURRENCY = "currency.base";
    public static final String NOTIFICATION_TEXT = "notification.text";

    private static final Set<String> VALID = Set.of(BASE_CURRENCY, NOTIFICATION_TEXT);

    public static boolean isValid(String key) {
        return key != null && VALID.contains(key);
    }
}
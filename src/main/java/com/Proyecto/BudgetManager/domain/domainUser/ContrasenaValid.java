package com.Proyecto.BudgetManager.domain;

public class ContrasenaValid {
    public static void validar(String contrasenaPlana) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9\\s]).{8,}$";
        if (contrasenaPlana == null || !contrasenaPlana.matches(regex)) {
            throw new IllegalArgumentException(
                "La contraseña debe tener 8 caracteres mínimo, compuesta al menos por una mayúscula, una minúscula, un número y un signo"
            );
        }
    }
}

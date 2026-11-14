package com.Proyecto.BudgetManager.domain;

import lombok.Getter;

public class EmailValid {
    @Getter 
    private String valor;

    public EmailValid(String valor) {
        if (valor == null || !valor.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Correo inválido");
        }
        this.valor = valor.toLowerCase(); // Se normaliza automáticamente para siempre
    }
}

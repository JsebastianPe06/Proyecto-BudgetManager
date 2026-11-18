package com.Proyecto.BudgetManager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank(message = "El nombre es obligatorio")
    String fullName,
    @Email(message = "Correo inválido")
    @NotBlank(message = "El correo es obligatorio")
    String email,
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    String password,
    @NotBlank(message = "Debes confirmar la contraseña")
    String confirmPassword
) {}

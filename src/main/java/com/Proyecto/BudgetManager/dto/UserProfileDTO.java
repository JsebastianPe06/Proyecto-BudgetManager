package com.Proyecto.BudgetManager.dto;

import jakarta.validation.constraints.*;

public record UserProfileDTO(
    @NotBlank(message = "El nombre es obligatorio") String fullName,
    @Email(message = "Correo inválido") @NotBlank(message = "El correo es obligatorio") String email
) {}

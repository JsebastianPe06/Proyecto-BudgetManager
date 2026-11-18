package com.Proyecto.BudgetManager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserProfileDTO(
    @NotBlank(message = "El nombre es obligatorio") 
    String fullName,
    @Email(message = "Correo inválido") 
    @NotBlank(message = "El correo es obligatorio") 
    String email,
    @NotBlank(message = "Debes ingresar tu contraseña actual para guardar cambios")
    String currentPassword,
    String newPassword,
    String confirmNewPassword
) {}
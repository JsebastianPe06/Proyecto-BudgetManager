package com.Proyecto.BudgetManager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.Getter;

@Data
@NoArgsConstructor
public class CrearUsuarioForm {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Getter 
    private String nombre;
    @Email (message = "El correo debe ser válido")
    @NotBlank(message = "El correo no puede estar vacío")
    @Getter 
    private String correo;
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Getter 
    private String password;
    @NotBlank(message = "La contraseña es obligatoria")
    private String confirmarPassword;
}
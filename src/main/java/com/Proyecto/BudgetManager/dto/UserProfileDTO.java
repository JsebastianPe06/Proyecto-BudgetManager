package com.Proyecto.BudgetManager.dto;

import jakarta.validation.constraints.*;

public record UserProfileDTO(
    @NotBlank String fullName,
    @Email @NotBlank String email
) {}

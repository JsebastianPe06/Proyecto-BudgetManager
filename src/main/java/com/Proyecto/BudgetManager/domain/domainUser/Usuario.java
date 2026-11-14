package com.Proyecto.BudgetManager.domain.domainUser;

import lombok.Getter;

public class Usuario 
{
    @Getter
    private int id;
    @Getter
    private String username;
    @Getter
    private EmailValid correo;
    @Getter
    private ContrasenaHash contrasenaHash;

    public Usuario(int id, String username, EmailValid correo, ContrasenaHash contrasenaHash) {
        this.id = id;
        if(username == null || username.isBlank()) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        this.username = username;
        this.correo = correo;
        this.contrasenaHash = contrasenaHash;
    }

    public void cambiarEmail(EmailValid nuevoEmail) {
            if (nuevoEmail == null) {
                throw new IllegalArgumentException("El nuevo email no puede ser nulo");
            }
            this.correo = nuevoEmail; // Reemplazamos el Objeto de Valor completo por uno nuevo
        }

        // Método de negocio para cambiar la contraseña
        public void cambiarPassword(ContrasenaHash nuevoPasswordHash) {
            if (nuevoPasswordHash == null) {
                throw new IllegalArgumentException("El nuevo password no puede ser nulo");
            }
            this.contrasenaHash = nuevoPasswordHash;
        }
}


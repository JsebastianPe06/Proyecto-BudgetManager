package com.Proyecto.BudgetManager.entity;


public class Usuario 
{
    private int id;
    private String username;
    private String correo;

    public Usuario(int id, String username, String correo) {
        this.id = id;
        this.username = username;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getCorreo() {
        return correo;
    }
    
    
    
}


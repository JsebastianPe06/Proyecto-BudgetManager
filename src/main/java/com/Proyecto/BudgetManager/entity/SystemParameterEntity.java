package com.Proyecto.BudgetManager.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "system_parameters")
public class SystemParameterEntity {
    @Id
    @Column(name = "param_key")   // "key" es palabra reservada en SQL; usamos param_key
    private String key;

    @Column(name = "param_value", nullable = false, columnDefinition = "TEXT")
    private String value;

    public SystemParameterEntity() {}
    public SystemParameterEntity(String key, String value) { this.key = key; this.value = value; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
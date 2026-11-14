package com.Proyecto.BudgetManager.repository;

import com.Proyecto.BudgetManager.domain.domainUser.*;
import com.Proyecto.BudgetManager.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository 
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    boolean existsByEmail(String email);
    Optional<UsuarioEntity> findByEmail(String email);

    //Convierte de Base de Datos (JPA) a Dominio (Negocio puro)
    public static Usuario toDomain(UsuarioEntity entity) {
        EmailValid correo = new EmailValid(entity.getCorreo());
        ContrasenaHash passwordHash = new ContrasenaHash(entity.getPasswordHash());
        return new Usuario(entity.getId().intValue(), entity.getUsername(), correo, passwordHash);
    }

    // Convierte de Dominio a Base de Datos (JPA) para guardar
    public static UsuarioEntity toEntity(Usuario domainUser) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId((long) domainUser.getId());
        entity.setUsername(domainUser.getUsername());
        entity.setCorreo(domainUser.getCorreo().getValor());
        entity.setPasswordHash(domainUser.getContrasenaHash().getValor());
        return entity;
    }
}

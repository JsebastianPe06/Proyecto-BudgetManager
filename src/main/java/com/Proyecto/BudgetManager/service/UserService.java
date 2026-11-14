package com.Proyecto.BudgetManager.service;

import com.Proyecto.BudgetManager.dto.CrearUsuarioForm;
import com.Proyecto.BudgetManager.entity.UsuarioEntity;
import com.Proyecto.BudgetManager.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UsuarioRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //CREAR USUARIO
    @Transactional
    public void crearUsuario(CrearUsuarioForm dto) {
        if (userRepository.existsByEmail(dto.getCorreo())) {
            throw new IllegalStateException("El correo ya se encuentra registrado.");
        }

        UsuarioEntity user = new UsuarioEntity();
        user.setCorreo(dto.getCorreo());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword())); // Encriptación BCrypt
        userRepository.save(user); //Pasa a estado Persistent
    }

    // 2. EDITAR USUARIO
    @Transactional
    public void editarUsuario(Long id, CrearUsuarioForm dto) {
        // Se busca la entidad existente (Estado Persistent)
        UsuarioEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        // Validar si el nuevo email ya pertenece a otro usuario distinto
        if (!user.getCorreo().equals(dto.getCorreo()) && userRepository.existsByEmail(dto.getCorreo())) {
            throw new IllegalStateException("El nuevo correo ya está en uso por otro usuario.");
        }
        // Modificamos los campos directamente
        user.setCorreo(dto.getCorreo());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        }
    }
}

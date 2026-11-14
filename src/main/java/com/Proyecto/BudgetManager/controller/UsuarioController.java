package com.Proyecto.BudgetManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.Proyecto.BudgetManager.service.UserService;
import com.Proyecto.BudgetManager.dto.CrearUsuarioForm;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UserService usuarioService;

    public UsuarioController(UserService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Void> crearUsuario(@Valid @RequestBody CrearUsuarioForm form) {
        // Si el DTO falla las anotaciones (@NotBlank, @Email), Spring retorna HTTP 400 automáticamente.
        usuarioService.crearUsuario(form);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
        public ResponseEntity<Void> editar(@PathVariable Long id, @Valid @RequestBody CrearUsuarioForm dto) {
            usuarioService.editarUsuario(id, dto);
            return ResponseEntity.ok().build();
        }
}

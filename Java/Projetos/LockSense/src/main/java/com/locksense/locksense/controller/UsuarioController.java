package com.locksense.locksense.controller;

import com.locksense.locksense.dto.UsuarioCadastroDTO;
import com.locksense.locksense.dto.UsuarioLoginDTO;
import com.locksense.locksense.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST para operações de usuário.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para cadastro de usuário.
     */
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@Valid @RequestBody UsuarioCadastroDTO dto) {
        usuarioService.cadastrarUsuario(dto);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

    /**
     * Endpoint para login de usuário.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UsuarioLoginDTO dto) {
        boolean sucesso = usuarioService.fazerLogin(dto);
        if (sucesso) {
            return ResponseEntity.ok("Login realizado com sucesso!");
        }
        return ResponseEntity.badRequest().body("Gmail ou senha incorretos.");
    }
}

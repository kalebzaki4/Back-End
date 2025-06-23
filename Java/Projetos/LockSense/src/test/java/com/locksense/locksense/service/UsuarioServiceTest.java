package com.locksense.locksense.service;

import com.locksense.locksense.dto.UsuarioCadastroDTO;
import com.locksense.locksense.dto.UsuarioLoginDTO;
import org.junit.jupiter.api.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes para UsuarioService.
 */
class UsuarioServiceTest {

    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();
        // Limpa o arquivo antes de cada teste
        new File("usuarios.json").delete();
    }

    @Test
    void testCadastroELogin() {
        UsuarioCadastroDTO cadastro = new UsuarioCadastroDTO();
        cadastro.setGmail("teste@teste.com");
        cadastro.setNome("Teste");
        cadastro.setSenha("123456");
        usuarioService.cadastrarUsuario(cadastro);

        UsuarioLoginDTO login = new UsuarioLoginDTO();
        login.setGmail("teste@teste.com");
        login.setSenha("123456");
        assertTrue(usuarioService.fazerLogin(login));

        login.setSenha("errada");
        assertFalse(usuarioService.fazerLogin(login));
    }
}

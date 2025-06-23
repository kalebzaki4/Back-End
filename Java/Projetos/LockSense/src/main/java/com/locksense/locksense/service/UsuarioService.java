package com.locksense.locksense.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.locksense.locksense.Models.Usuario;
import com.locksense.locksense.dto.UsuarioCadastroDTO;
import com.locksense.locksense.dto.UsuarioLoginDTO;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private static final String ARQUIVO = "usuarios.json";
    private static final Gson gson = new Gson();

    public void cadastrarUsuario(UsuarioCadastroDTO dto) {
        List<Usuario> usuarios = carregarUsuarios();
        usuarios.add(new Usuario(dto.getGmail(), dto.getNome(), dto.getSenha()));
        salvarUsuarios(usuarios);
    }

    public boolean fazerLogin(UsuarioLoginDTO dto) {
        List<Usuario> usuarios = carregarUsuarios();
        return usuarios.stream()
                .anyMatch(u -> u.getGmail().equals(dto.getGmail()) && u.getSenha().equals(dto.getSenha()));
    }


    private List<Usuario> carregarUsuarios() {
        try (FileReader reader = new FileReader(ARQUIVO)) {
            Type listType = new TypeToken<ArrayList<Usuario>>() {}.getType();
            List<Usuario> usuarios = gson.fromJson(reader, listType);
            return usuarios != null ? usuarios : new ArrayList<>();
        } catch (Exception e) {
            // Arquivo não existe ou erro de leitura
            return new ArrayList<>();
        }
    }

    private void salvarUsuarios(List<Usuario> usuarios) {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            gson.toJson(usuarios, writer);
        } catch (Exception e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }
}

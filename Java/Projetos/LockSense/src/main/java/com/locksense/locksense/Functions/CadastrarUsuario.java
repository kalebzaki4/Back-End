package com.locksense.locksense.Functions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.locksense.locksense.Models.Usuario;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.locksense.locksense.Functions.FazerLogin.FazerLogin;

public class CadastrarUsuario {

    private static final String ARQUIVO = "usuarios.json";
    private static final Gson gson = new Gson();

    public static void cadastrarUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu Gmail: ");
        String gmail = scanner.nextLine();

        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        String senha;
        String confirmaSenha;

        do {
            System.out.print("Digite uma senha: ");
            senha = scanner.nextLine();

            System.out.print("Confirme a senha: ");
            confirmaSenha = scanner.nextLine();

            if (!senha.equals(confirmaSenha)) {
                System.out.println("As senhas não conferem. Tente novamente.");
            }
        } while (!senha.equals(confirmaSenha));

        Usuario novoUsuario = new Usuario(gmail, nome, senha);

        List<Usuario> usuarios = carregarUsuarios();
        usuarios.add(novoUsuario);
        salvarUsuarios(usuarios);

        System.out.println("Usuário cadastrado com sucesso!");
        FazerLogin();
    }

    private static List<Usuario> carregarUsuarios() {
        try (FileReader reader = new FileReader(ARQUIVO)) {
            Type listType = new TypeToken<ArrayList<Usuario>>() {}.getType();
            List<Usuario> usuarios = gson.fromJson(reader, listType);
            return usuarios != null ? usuarios : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private static void salvarUsuarios(List<Usuario> usuarios) {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }
}

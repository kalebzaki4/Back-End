package com.empresa;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SistemaCadastro {
    private static final String ARQUIVO_USUARIO = "usuario.json";

    public void cadastrarUsuario() {
        Scanner scanner = new Scanner(System.in);

        String nome = "";
        while (nome.length() < 4) {
            System.out.println("Digite seu nome (mínimo de 4 caracteres): ");
            nome = scanner.nextLine();
            if (nome.length() < 4) {
                System.out.println("Nome muito curto. Por favor, digite um nome com pelo menos 4 caracteres.");
            }
        }

        String email = "";
        while (!email.contains("@") || !email.contains(".")) {
            System.out.println("Digite seu e-mail: ");
            email = scanner.nextLine();
            if (!email.contains("@") || !email.contains(".")) {
                System.out.println("E-mail inválido. Por favor, insira um e-mail válido.");
            }
        }

        String senha = "";
        while (senha.length() < 6) {
            System.out.println("Digite uma senha (mínimo de 6 caracteres): ");
            senha = scanner.nextLine();
            if (senha.length() < 6) {
                System.out.println("Senha muito curta. Por favor, digite uma senha com pelo menos 6 caracteres.");
            }
        }

        Usuario usuario = new Usuario(nome, email, senha, null);
        salvarUsuario(usuario);
        System.out.println("Cadastro realizado com sucesso!");
    }

    private void salvarUsuario(Usuario usuario) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(ARQUIVO_USUARIO), usuario);
            System.out.println("Dados salvos em " + ARQUIVO_USUARIO);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o usuário.");
            e.printStackTrace();
        }
    }
}

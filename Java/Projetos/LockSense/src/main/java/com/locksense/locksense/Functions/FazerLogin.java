package com.locksense.locksense.Functions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.locksense.locksense.Models.Usuario;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

import static com.locksense.locksense.Functions.CriaCodigo.criaCodigo;

public class FazerLogin {

    private static final String ARQUIVO = "usuarios.json";
    private static final Gson gson = new Gson();

    public static void FazerLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu Gmail: ");
        String gmail = scanner.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        List<Usuario> usuarios = carregarUsuarios();

        for (Usuario u : usuarios) {
            if (u.getGmail().equals(gmail) && u.getSenha().equals(senha)) {
                System.out.println("Login realizado com sucesso!");
                int codigo = criaCodigo();
                System.out.print("Digite o código de acesso: ");
                String codigoDigitado = scanner.nextLine();
                if (String.valueOf(codigo).equals(codigoDigitado)) {
                    System.out.println("Acesso liberado. Bem-vindo!");
                } else {
                    System.out.println("Código incorreto. Acesso negado.");
                }
                return;
            }
        }

        System.out.println("Gmail ou senha incorretos.");
    }

    private static List<Usuario> carregarUsuarios() {
        try (FileReader reader = new FileReader(ARQUIVO)) {
            Type listType = new TypeToken<List<Usuario>>() {}.getType();
            List<Usuario> usuarios = gson.fromJson(reader, listType);
            return usuarios != null ? usuarios : List.of();
        } catch (Exception e) {
            return List.of();
        }
    }
}

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
                criaCodigo();
                return;
            }
        }

        System.out.println("Gmail ou senha incorretos.");
    }

    private static List<Usuario> carregarUsuarios() {
        try (FileReader reader = new FileReader(ARQUIVO)) {
            Type listType = new TypeToken<List<Usuario>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            return List.of();
        }
    }
}

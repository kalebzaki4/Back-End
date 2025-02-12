package com.empresa;

import com.empresa.SistemaCadastro;
import com.empresa.SistemaLogin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaCadastro sistemaCadastro = new SistemaCadastro();
        SistemaLogin sistemaLogin = new SistemaLogin();

        while (true) {
            exibirMenu();
            System.out.print("\uD83D\uDD22 Escolha uma opção: ");

            String entrada = scanner.nextLine();
            int opcao;

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("❌ Opção inválida. Digite um número entre 1 e 4.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\u2705 Iniciando cadastro...");
                    sistemaCadastro.cadastrarUsuario();
                    break;
                case 2:
                    System.out.println("\uD83D\uDD10 Iniciando login...");
                    sistemaLogin.login();
                    break;
                case 3:
                    System.out.println("\uD83D\uDD12 Validando código...");
                    sistemaLogin.validarCodigo();
                    break;
                case 4:
                    System.out.println("\uD83D\uDEAA Saindo do sistema...");
                    return;
                default:
                    System.out.println("❌ Opção inválida. Digite um número entre 1 e 4.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== \uD83D\uDD10 Sistema LockSense ===");
        System.out.println("1️⃣ Cadastrar usuário");
        System.out.println("2️⃣ Fazer login");
        System.out.println("3️⃣ Validar código");
        System.out.println("4️⃣ Sair");
    }
}
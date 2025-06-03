package com.locksense.locksense.Functions;

import java.util.Scanner;

public class FazerLogin {
    public static void FazerLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu Gmail: ");
        String gmail = scanner.nextLine();

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
        System.out.println("Usuário cadastrado com sucesso!");
    }
}

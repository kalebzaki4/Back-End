package com.locksense.locksense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static com.locksense.locksense.Functions.CadastrarUsuario.cadastrarUsuario;
import static com.locksense.locksense.Functions.FazerLogin.FazerLogin;

@SpringBootApplication
public class LocksenseApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SpringApplication.run(LocksenseApplication.class, args);
		System.out.println("Bem vindo ao LockSense!");
		System.out.println("escolha sua opção abaixo: ");
		System.out.println("1 - Cadastrar usuario.");
		System.out.println("2 - Fazer login.");

		int opcao = Integer.parseInt(scanner.nextLine());

		if (opcao == 1) {
			cadastrarUsuario();
		} else if (opcao == 2) {
			FazerLogin();
		}
	}
}

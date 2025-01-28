package com.empresa;

import java.util.Scanner;

public class cadastro {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("Bem-vindo ao Sistema de Cadastro");
        System.out.println("========================================");

        // Solicitar e validar o nome
        String nome;
        while (true) {
            System.out.print("Digite seu nome: ");
            nome = scanner.nextLine().trim();

            if (nome.length() >= 2 && nome.matches("[a-zA-ZÀ-ú\\s]+")) {
                break;
            } else {
                System.out.println("Nome inválido! O nome deve ter pelo menos 2 caracteres e conter apenas letras e espaços.");
            }
        }

        // Solicitar DDD e validar
        String ddd;
        while (true) {
            System.out.print("Digite seu DDD (2 dígitos): ");
            ddd = scanner.nextLine();
            if (ddd.length() == 2 && ddd.matches("\\d{2}")) {
                break;
            }
            System.out.println("DDD inválido! Digite exatamente 2 dígitos.");
        }

        // Solicitar número restante e validar
        String numeroRestante;
        while (true) {
            System.out.print("Digite o restante do número (8 ou 9 dígitos): ");
            numeroRestante = scanner.nextLine();
            if (numeroRestante.matches("\\d{8,9}")) {
                break;
            }
            System.out.println("Número inválido! Digite 8 ou 9 dígitos.");
        }

        // Combinar DDD e número restante no formato (XX) XXXXX-XXXX
        String numero = "(" + ddd + ") " + numeroRestante.substring(0, 5) + "-" + numeroRestante.substring(5);

        // Solicitar código de segurança
        System.out.print("Digite seu código de segurança: ");
        String codigoSeguranca = scanner.nextLine();

        // Solicitar e validar CPF
        String cpf;
        while (true) {
            System.out.println("Digite seu CPF no formato XXX.XXX.XXX-XX (exemplo: 123.456.789-01): ");
            cpf = scanner.nextLine();
            if (cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                break; // CPF válido
            } else {
                System.out.println("CPF inválido! Digite no formato XXX.XXX.XXX-XX (exemplo: 123.456.789-01).");
            }
        }

        // Solicitar e validar RG
        String rg;
        while (true) {
            System.out.println("Digite seu RG no formato XX.XXX.XXX-X (exemplo: 12.345.678-9): ");
            rg = scanner.nextLine();
            if (rg.matches("\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}")) {
                break; // RG válido
            } else {
                System.out.println("RG inválido! Digite no formato XX.XXX.XXX-X (exemplo: 12.345.678-9).");
            }
        }

        System.out.println("========================================");
        System.out.println("Cadastro realizado com sucesso!");
        System.out.println("========================================");

        scanner.close();
    }
}

package com.empresa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDateTime;

public class SistemaLogin {
    private static final String ARQUIVO_USUARIO = "usuario.json";

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== 🔐 Login ===");
        System.out.print("✉️ Digite seu e-mail: ");
        String emailInput = scanner.nextLine();
        System.out.print("🔑 Digite sua senha: ");
        String senhaInput = scanner.nextLine();

        Usuario usuario = carregarUsuario();

        if (usuario != null && usuario.getEmail().equals(emailInput) && usuario.getSenha().equals(senhaInput)) {
            System.out.println("✅ Login realizado com sucesso!");
            gerarCodigoAcesso(usuario);
        } else {
            System.out.println("❌ Credenciais inválidas. Login não realizado.");
        }
    }

    private Usuario carregarUsuario() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            return objectMapper.readValue(new File(ARQUIVO_USUARIO), Usuario.class);
        } catch (IOException e) {
            System.out.println("⚠️ Nenhum usuário cadastrado. Faça o cadastro primeiro.");
            return null;
        }
    }

    private void gerarCodigoAcesso(Usuario usuario) {
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000);
        LocalDateTime validade = LocalDateTime.now().plusHours(24);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.println("🔢 Seu código de 6 dígitos é: " + codigo);
        System.out.println("⏳ Este código é válido até: " + validade.format(formatter));

        CodigoAcesso codigoAcesso = new CodigoAcesso(codigo, validade);
        usuario.setCodigoAcesso(codigoAcesso);
        salvarUsuario(usuario);
    }

    private void salvarUsuario(Usuario usuario) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            objectMapper.writeValue(new File(ARQUIVO_USUARIO), usuario);
            System.out.println("💾 Código de acesso salvo com sucesso.");
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar o código.");
            e.printStackTrace();
        }
    }

    public void validarCodigo() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Scanner scanner = new Scanner(System.in);

        int codigoDigitado = 0;
        boolean codigoValido = false;

        while (!codigoValido) {
            System.out.print("🔢 Digite o código de 6 dígitos para validar: ");
            if (scanner.hasNextInt()) {
                codigoDigitado = scanner.nextInt();
                if (String.valueOf(codigoDigitado).length() == 6) {
                    codigoValido = true;
                } else {
                    System.out.println("⚠️ O código deve ter 6 dígitos. Tente novamente.");
                }
            } else {
                System.out.println("❌ Entrada inválida. Por favor, digite um número de 6 dígitos.");
                scanner.next();
            }
        }

        try {
            Usuario usuario = objectMapper.readValue(new File(ARQUIVO_USUARIO), Usuario.class);
            CodigoAcesso codigoSalvo = usuario.getCodigoAcesso();

            if (codigoSalvo != null && codigoSalvo.getCodigo() == codigoDigitado && LocalDateTime.now().isBefore(codigoSalvo.getValidade())) {
                System.out.println("✅ Código válido! Acesso permitido.");
            } else {
                System.out.println("❌ Código inválido ou expirado.");
            }
        } catch (IOException e) {
            System.out.println("⚠️ Erro ao ler o arquivo de usuário.");
        }
    }
}

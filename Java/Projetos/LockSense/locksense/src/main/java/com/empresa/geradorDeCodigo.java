package com.empresa;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class geradorDeCodigo {

    private static String codigoConfirmacao;
    private static LocalDateTime dataExpiracao;

    private static class Usuario {

        String nome;
        String numero;
        String codigoSeguranca;
        String cpf;
        String rg;
    }

    private static class ListaUsuarios {

        List<Usuario> usuarios;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Alteração do caminho para o arquivo JSON
        List<Usuario> usuarios = carregarUsuariosDoJSON("src/main/java/com/empresa/dadosPerfil.json");
        if (usuarios == null) {
            System.out.println("Erro ao carregar os dados do JSON. Encerrando o programa.");
            return;
        }

        System.out.println("========================================");
        System.out.println("Bem-vindo ao Sistema de Segurança");
        System.out.println("========================================");

        int tentativasRestantes = 6;

        while (tentativasRestantes > 0) {
            System.out.println("Tentativas restantes: " + tentativasRestantes);

            String nome;
            while (true) {
                System.out.print("Digite seu nome: ");
                nome = scanner.nextLine().trim();

                if (nome.length() >= 2 && nome.matches("[a-zA-ZÀ-ú\\s]+")) {
                    break;
                } else {
                    System.out.println("Nome inválido! O nome deve ter pelo menos 2 caracteres e conter apenas letras e espaços.");
                    tentativasRestantes--;
                    if (tentativasRestantes == 0) {
                        break;
                    }
                }
            }
            if (tentativasRestantes == 0) {
                break;
            }

            String ddd;
            while (true) {
                System.out.print("Digite seu DDD (2 dígitos): ");
                ddd = scanner.nextLine();
                if (ddd.length() == 2 && ddd.matches("\\d{2}")) {
                    break;
                } else {
                    System.out.println("DDD inválido! Digite exatamente 2 dígitos.");
                    tentativasRestantes--;
                    if (tentativasRestantes == 0) {
                        break;
                    }
                }
            }
            if (tentativasRestantes == 0) {
                break;
            }

            String numeroRestante;
            while (true) {
                System.out.print("Digite o restante do número (8 ou 9 dígitos): ");
                numeroRestante = scanner.nextLine();
                if (numeroRestante.matches("\\d{8,9}")) {
                    break;
                } else {
                    System.out.println("Número inválido! Digite 8 ou 9 dígitos.");
                    tentativasRestantes--;
                    if (tentativasRestantes == 0) {
                        break;
                    }
                }
            }
            if (tentativasRestantes == 0) {
                break;
            }

            String numero = "(" + ddd + ") " + numeroRestante.substring(0, 5) + "-" + numeroRestante.substring(5);

            System.out.print("Digite seu código de segurança: ");
            String codigoSeguranca = scanner.nextLine();

            String cpf;
            while (true) {
                System.out.println("Digite seu CPF no formato XXX.XXX.XXX-XX (exemplo: 123.456.789-01): ");
                cpf = scanner.nextLine();
                if (cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                    break;
                } else {
                    System.out.println("CPF inválido! Digite no formato XXX.XXX.XXX-XX (exemplo: 123.456.789-01).");
                    tentativasRestantes--;
                    if (tentativasRestantes == 0) {
                        break;
                    }
                }
            }
            if (tentativasRestantes == 0) {
                break;
            }

            String rg;
            while (true) {
                System.out.println("Digite seu RG no formato XX.XXX.XXX-X (exemplo: 12.345.678-9): ");
                rg = scanner.nextLine();
                if (rg.matches("\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}")) {
                    break;
                } else {
                    System.out.println("RG inválido! Digite no formato XX.XXX.XXX-X (exemplo: 12.345.678-9).");
                    tentativasRestantes--;
                    if (tentativasRestantes == 0) {
                        break;
                    }
                }
            }
            if (tentativasRestantes == 0) {
                break;
            }

            boolean acessoConcedido = false;
            for (Usuario usuario : usuarios) {
                if (nome.equalsIgnoreCase(usuario.nome)
                        && numero.equals(usuario.numero)
                        && codigoSeguranca.equals(usuario.codigoSeguranca)
                        && cpf.equals(usuario.cpf)
                        && rg.equals(usuario.rg)) {
                    acessoConcedido = true;
                    break;
                }
            }

            if (acessoConcedido) {
                System.out.println("========================================");
                System.out.println("Verificação bem-sucedida! Acesso concedido.");
                System.out.println("========================================");

                gerarNovoCodigoConfirmacao();
                System.out.println("Um novo código de confirmação foi gerado: " + codigoConfirmacao);
                System.out.println("Este código expira em: " + dataExpiracao);

                while (true) {
                    System.out.print("Digite o código de confirmação: ");
                    String codigoDigitado = scanner.nextLine();

                    if (verificarCodigoConfirmacao(codigoDigitado)) {
                        System.out.println("Código válido! Acesso completo concedido.");
                        break;
                    } else {
                        System.out.println("Código inválido ou expirado. Um novo código será gerado.");
                        gerarNovoCodigoConfirmacao();
                        System.out.println("Novo código de confirmação: " + codigoConfirmacao);
                        System.out.println("Este código expira em: " + dataExpiracao);
                    }
                }

                scanner.close();
                return;
            } else {
                System.out.println("========================================");
                System.out.println("Dados inválidos! Acesso negado.");
                System.out.println("========================================");
                tentativasRestantes--;
            }
        }

        System.out.println("========================================");
        System.out.println("Você excedeu o número máximo de tentativas. Acesso bloqueado.");
        System.out.println("========================================");

        scanner.close();
    }

    private static List<Usuario> carregarUsuariosDoJSON(String caminhoArquivo) {
        try (FileReader reader = new FileReader(caminhoArquivo)) {
            Gson gson = new Gson();
            Type tipoListaUsuarios = new TypeToken<ListaUsuarios>() {
            }.getType();
            ListaUsuarios listaUsuarios = gson.fromJson(reader, tipoListaUsuarios);
            return listaUsuarios.usuarios;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void gerarNovoCodigoConfirmacao() {
        Random random = new Random();
        codigoConfirmacao = String.format("%06d", random.nextInt(1000000));
        dataExpiracao = LocalDateTime.now().plus(2, ChronoUnit.DAYS);
    }

    private static boolean verificarCodigoConfirmacao(String codigoDigitado) {
        if (codigoDigitado.equals(codigoConfirmacao) && LocalDateTime.now().isBefore(dataExpiracao)) {
            return true;
        }
        return false;
    }
}

package br.com.alura;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {
        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");
        try {
            int opcaoEscolhida = 0;
            while (opcaoEscolhida != 5) {
                System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
                System.out.println("1 -> Listar abrigos cadastrados");
                System.out.println("2 -> Cadastrar novo abrigo");
                System.out.println("3 -> Listar pets do abrigo");
                System.out.println("4 -> Importar pets do abrigo");
                System.out.println("5 -> Sair");

                String textoDigitado = new Scanner(System.in).nextLine();
                opcaoEscolhida = Integer.parseInt(textoDigitado);

                if (opcaoEscolhida == 1) {
                    listarAbrigo();
                } else if (opcaoEscolhida == 2) {
                    cadastrarAbrigo();
                } else if (opcaoEscolhida == 3) {
                    listarPetsDoAbrigo();
                } else if (opcaoEscolhida == 4) {
                    importarPetsDoAbrigo();
                } else if (opcaoEscolhida == 5) {
                    break;
                } else {
                    System.out.println("NÚMERO INVÁLIDO!");
                }
            }
            System.out.println("Finalizando o programa...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void cadastrarAbrigo() throws IOException, InterruptedException {
        String uri = "http://localhost:8080/abrigos";
        String responseBody = disparaRequisicaoGet(uri);
        JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
        System.out.println("Abrigos cadastrados:");

        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            long id = jsonObject.get("id").getAsLong();
            String nome = jsonObject.get("nome").getAsString();
            System.out.println(id + " - " + nome);
        }
    }

    private static void listarAbrigo() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do abrigo:");
        String nome = scanner.nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = scanner.nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = scanner.nextLine();

        JsonObject json = new JsonObject();
        json.addProperty("nome", nome);
        json.addProperty("telefone", telefone);
        json.addProperty("email", email);

        String uri = "http://localhost:8080/abrigos";
        HttpResponse<String> response = disparaRequisicaoPost(uri, json.toString());

        if (response.statusCode() == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(response.body());
        } else {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(response.body());
        }
    }

    private static void listarPetsDoAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = new Scanner(System.in).nextLine();

        String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets";
        String responseBody = disparaRequisicaoGet(uri);

        if (responseBody.equals("ID ou nome do abrigo não encontrado!")) {
            System.out.println(responseBody);
            return;
        }

        JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
        System.out.println("Pets cadastrados:");
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            long id = jsonObject.get("id").getAsLong();
            String tipo = jsonObject.get("tipo").getAsString();
            String nome = jsonObject.get("nome").getAsString();
            String raca = jsonObject.get("raca").getAsString();
            int idade = jsonObject.get("idade").getAsInt();
            System.out.println(id + " - " + tipo + " - " + nome + " - " + raca + " - " + idade + " ano(s)");
        }
    }

    private static void importarPetsDoAbrigo() {
        System.out.println("Função de importação de pets ainda não implementada completamente.");
    }

    private static String disparaRequisicaoGet(String uri) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            return "ID ou nome do abrigo não encontrado!";
        }
        return response.body();
    }

    private static HttpResponse<String> disparaRequisicaoPost(String uri, String body) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(body))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
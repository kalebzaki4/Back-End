package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Pet;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PetService {

    private ClientHttpConfiguration client;

    public PetService(ClientHttpConfiguration client) {
        this.client = client;
    }

    public void listarPetsDoAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = new Scanner(System.in).nextLine();

        String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets";
        HttpResponse<String> response = client.dispararRequisicaoGet(uri);
        int statusCode = response.statusCode();

        if (statusCode == 404 || statusCode == 500) {
            System.out.println("ID ou nome não cadastrado!");
            return;
        }

        String responseBody = response.body();
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

    public void importarPetsDoAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = new Scanner(System.in).nextLine();

        System.out.println("Digite o nome do arquivo CSV:");
        String nomeArquivo = new Scanner(System.in).nextLine();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + nomeArquivo);
            return;
        }

        Gson gson = new Gson();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] campos = line.split(",");
            String tipo = campos[0].toUpperCase();
            String nome = campos[1];
            String raca = campos[2];
            int idade = Integer.parseInt(campos[3]);
            String cor = campos[4];
            Float peso = Float.parseFloat(campos[5]);

            Pet pet = new Pet(tipo, nome, raca, idade, cor, peso);
            String json = gson.toJson(pet);

            String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets";
            HttpResponse<String> response = client.dispararRequisicaoPost(uri, json);
            int statusCode = response.statusCode();
            String responseBody = response.body();

            if (statusCode == 200) {
                System.out.println("Pet cadastrado com sucesso: " + nome);
            } else if (statusCode == 404) {
                System.out.println("Id ou nome do abrigo não encontrado!");
                break;
            } else {
                System.out.println("Erro ao cadastrar o pet: " + nome);
                System.out.println(responseBody);
                break;
            }
        }

        reader.close();
    }
}

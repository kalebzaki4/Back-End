package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite um filme para busca: ");
        var busca = leitura.nextLine();

        String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=85c44cfa";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            System.out.println(json);

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();

            TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);

            if (meuTituloOmdb.getResponse().equalsIgnoreCase("True")) {
                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println("Título já convertido:");
                System.out.println(meuTitulo);
            } else {
                System.out.println("Filme não encontrado ou erro na resposta da API.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Erro de formato numérico: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de argumento na busca, verifique o endereço: " + e.getMessage());
        } catch (ErroDeConversaoDeAnoException e) {
            System.out.println("Erro na conversão do ano: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }

        System.out.println("O programa finalizou corretamente!");
    }
}

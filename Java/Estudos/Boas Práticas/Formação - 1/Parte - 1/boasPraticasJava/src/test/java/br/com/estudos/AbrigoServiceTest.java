package br.com.estudos;

import br.com.estudos.client.ClientHttpConfiguration;
import br.com.estudos.domain.Abrigo;
import br.com.estudos.service.AbrigoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbrigoServiceTest {

    private final ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);
    private final AbrigoService abrigoService = new AbrigoService(client);
    private final HttpResponse<String> response = mock(HttpResponse.class);

    @Test
    public void deveVerificarSeDispararRequisicaoGetSeraChamado() throws IOException, InterruptedException {
        // Arrange
        String jsonResponse = "[{\"id\":0,\"nome\":\"Teste\",\"telefone\":\"61981880392\",\"email\":\"abrigo_alura@gmail.com\"}]";
        when(response.body()).thenReturn(jsonResponse);
        when(client.dispararRequisicaoGet(anyString())).thenReturn(response);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        try {

            abrigoService.listarAbrigo();
        } finally {

            System.setOut(originalOut);
        }

        // Assert
        String output = baos.toString();
        String[] lines = output.split(System.lineSeparator());

        String expectedAbrigosCadastrados = "Abrigos cadastrados:";
        String expectedIdENome = "0 - Teste";

        Assertions.assertTrue(lines.length >= 2, "Saída esperada com pelo menos duas linhas.");
        Assertions.assertEquals(expectedAbrigosCadastrados, lines[0].trim());
        Assertions.assertEquals(expectedIdENome, lines[1].trim());
    }
}

package br.com.aplicacao.screenmatch;

import br.com.aplicacao.screenmatch.service.ConsumirApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		var consumirApi = new ConsumirApi();
		var json = consumirApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
		System.out.println(json);
	}

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

}

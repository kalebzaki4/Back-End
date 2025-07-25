package br.com.estudos.adopet;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.service.CalculadoraProbabilidadeAdocao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdopetApiApplicationTests {

	@Test
	void cenario01() {

		Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
				"Abrigo feliz",
				"94999999999",
				"abrigofeliz@email.com.br"
		));
		Pet pet = new Pet(new CadastroPetDto(
				TipoPet.GATO,
				"Miau",
				"Siames",
				4,
				"Cinza",
				4.0f
		), abrigo);

		CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
		ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

	}}

package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidacaoPetDisponivelTest {
    @Test
    void deveriaPermitirsoliciataoPet() {
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(
                1L,
                2l,
                "João" );
        ValidacaoPetDisponivel validacao = new ValidacaoPetDisponivel();

        validacao.validar(dto);
    }

}
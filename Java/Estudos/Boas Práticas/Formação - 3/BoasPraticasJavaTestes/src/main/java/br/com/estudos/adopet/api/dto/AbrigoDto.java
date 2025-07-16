package br.com.estudos.adopet.api.dto;

import br.com.estudos.adopet.api.model.Abrigo;

public record AbrigoDto(Long id, String nome) {

    public AbrigoDto(Abrigo abrigo) {
        this(abrigo.getId(), abrigo.getNome());
    }

}

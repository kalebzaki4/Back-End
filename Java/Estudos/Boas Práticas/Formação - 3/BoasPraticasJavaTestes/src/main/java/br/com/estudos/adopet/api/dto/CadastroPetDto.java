package br.com.estudos.adopet.api.dto;

import br.com.estudos.adopet.api.model.TipoPet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroPetDto(
        @NotNull
        TipoPet tipo,
        @NotBlank
        String nome,
        @NotBlank
        String raca,
        @NotNull
        Integer idade,
        @NotBlank
        String cor,
        @NotNull
        Float peso) {
}

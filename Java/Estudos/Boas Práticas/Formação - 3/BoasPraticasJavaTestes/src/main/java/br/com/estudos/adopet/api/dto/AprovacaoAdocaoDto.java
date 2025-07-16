package br.com.estudos.adopet.api.dto;

import jakarta.validation.constraints.NotNull;

public record AprovacaoAdocaoDto(@NotNull Long idAdocao) {
}

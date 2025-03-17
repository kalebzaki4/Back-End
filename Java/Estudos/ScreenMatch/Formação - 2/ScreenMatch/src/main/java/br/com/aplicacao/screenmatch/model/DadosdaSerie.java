package br.com.aplicacao.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosdaSerie(@JsonAlias("Title") String titulo, @JsonAlias("totalSeasons") Integer totalTemporadas, @JsonAlias("imdbRating") String avaliacao) {

}

package br.com.alura.screenmatch.modelos;

public record TituloOmdb(String title, String year, String runtime) {
    public String getResponse() {
        return "";
    }
}

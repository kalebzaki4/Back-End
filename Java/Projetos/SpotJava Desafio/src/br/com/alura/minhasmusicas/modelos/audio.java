package br.com.alura.minhasmusicas.modelos;

public class audio {
    private String Titulo;
    private int totalReproducoes;
    private int totalCurtidas;
    private int classificao;

    public void curte() {
        this.totalCurtidas++;
    }

    public void reproduz() {
        this.totalReproducoes++;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public int getToalReproducoes() {
        return totalReproducoes;
    }

    public int getTotalCurtidas() {
        return totalCurtidas;
    }

    public int getClassificao() {
        return classificao;
    }
}

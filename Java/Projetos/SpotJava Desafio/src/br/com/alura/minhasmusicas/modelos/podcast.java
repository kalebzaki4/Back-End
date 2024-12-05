package br.com.alura.minhasmusicas.modelos;

public class podcast extends audio {
    private String apresentador;
    private String preview;

    public String getApresentador() {
        return apresentador;
    }

    public void setApresentador(String apresentador) {
        this.apresentador = apresentador;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
}

package br.com.alura.minhasmusicas.modelos;

public class musica extends audio {
    private String album;
    private String cantor;
    private String genero;

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getCantor() {
        return cantor;
    }

    public void setCantor(String cantor) {
        this.cantor = cantor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public int getClassificao() {
        if (this.getToalReproducoes() > 2000) {
            return 9;
        } else {
            return 7;
        }
    }
}

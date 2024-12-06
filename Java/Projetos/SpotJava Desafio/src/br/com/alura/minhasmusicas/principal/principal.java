package br.com.alura.minhasmusicas.principal;

import br.com.alura.minhasmusicas.modelos.musica;
import br.com.alura.minhasmusicas.modelos.podcast;

public class principal {
    public static void main(String[] args) {
        musica minhaMusica = new musica();
        minhaMusica.setTitulo("Minha Musica");
        minhaMusica.setCantor("Kiss");

        for (int i = 0; i < 1000; i++) {
            minhaMusica.reproduz();
        }

        for (int i = 0; i < 50; i++) {
            minhaMusica.curte();
        }

        podcast meuPodcast = new podcast();
        meuPodcast.setTitulo("Flow Podcast");
        meuPodcast.setApresentador("Igor3k");

        for (int i = 0; i < 5000; i++) {
            meuPodcast.reproduz();
        }

        for (int i = 0; i < 1000; i++) {
            meuPodcast.curte();
        }
    }
}

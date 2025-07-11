Episodio ep1 = new(4, "Técnicas de Facilitação", 45);
ep1.AdicionarConvidado("Ana Pereira");
ep1.AdicionarConvidado("Mário Francis");

Episodio ep2 = new(2, "Aprendendo a aprender", 78);
ep2.AdicionarConvidado("Marcos Felício");

Episodio ep3 = new(3, "Consciênciologia", 87);
ep3.AdicionarConvidado("Flavio Almeida");
ep3.AdicionarConvidado("Gui Lima");
ep3.AdicionarConvidado("Fernanda Fernandes");

Episodio ep0 = new(1, "Filosofia de software", 93);
ep0.AdicionarConvidado("Fernando Roberto");
ep0.AdicionarConvidado("Gabriel Barbosa");

Podcast podcast = new("TI para Poucos", "Daniel Portugal");
podcast.AdicionarEpisodio(ep1);
podcast.AdicionarEpisodio(ep2);
podcast.AdicionarEpisodio(ep3);
podcast.AdicionarEpisodio(ep0);

podcast.ExibirDetalhes();
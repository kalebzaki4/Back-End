Musica musica1 = new Musica();
musica1.Nome = "Bohemian Rhapsody";
musica1.Artista = "Queen";
musica1.Duracao = 354;
musica1.Disponivel = true;
System.Console.WriteLine(musica1.Disponivel);

Musica musica2 = new Musica();
musica2.Nome = "Imagine";
musica2.Artista = "John Lennon";
musica2.Duracao = 183;
musica2.Disponivel = false;

musica1.ExibirFichaTecnica();
musica2.ExibirFichaTecnica();

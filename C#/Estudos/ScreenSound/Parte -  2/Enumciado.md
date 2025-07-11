# Desafio do curso
Crie duas classes `Podcast` e `Episodio`, relacionadas entre si, com as seguintes especificações:
* `Podcast` possui propriedades `Nome`, `Host` e `TotalEpisodios`. Um podcast nasce com nome e host definidos. À medida que episódios forem criados, serão adicionados ao podcast. 
* `Podcast` possui métodos `AdicionarEpisodio()` e `ExibirDetalhes()`. O método para exibir detalhes deve mostrar o nome do podcast e seu host na primeira linha, em seguida sua lista de episódios ordenada por sequência e numa última linha o total de  episódios.
* `Episodio` possui propriedades `Ordem`, `Titulo`, `Duracao` e `Resumo`. Episódios nascem com ordem, título e duração definidos.
* `Episodio` possui o método `AdicionaConvidado()`, que será chamado quantas vezes forem necessárias para incluir os convidados do episódio.
* O resumo de um episódio será a concatenação dos valores de ordem, título, duração e os convidados do episódio. Por exemplo: um episódio com ordem 1, título `Técnicas de Facilitação`, duração 45 minutos e convidados Ana Pereira e Marcio Francis, o resumo seria: 
> `1. Técnicas de Facilitação (45 min) - Ana Pereira, Marcio Francis`
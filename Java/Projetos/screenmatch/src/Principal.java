import br.com.alura.screenmatch.modelos.Filme;
import br.com.alura.screenmatch.modelos.Serie;

public class Principal {
    public static void main(String[] args) {
        Filme meuFilme = new Filme();
        meuFilme.setNome("O poderoso chefão");
        meuFilme.setAnoDeLancamento(2015);
        meuFilme.setDuracaoEmMinuto(180);
        System.out.println("Duração do filme: " + meuFilme.getDuracaoEmMinuto());

        meuFilme.exibeFichaTecnica();
        meuFilme.avalia(8);
        meuFilme.avalia(5);
        meuFilme.avalia(10);
        System.out.println("Total de Avaliações: " + meuFilme.getTotalAvaliacoes());

        System.out.println(meuFilme.retornaMediaDasAvaliacoes());

        Serie lost = new Serie();
        lost.setAnoDeLancamento(2000);
        lost.exibeFichaTecnica();
        lost.setTemporadas(10);
        lost.setEpisodiosPorTemporada(10);
        System.out.println("Duração do filme: " + lost.getDuracaoEmMinuto());

    }
}

package br.com.alura.screenmatch.modelos;

public class Filme {
    public String nome;
    public int anoDeLancamento;
    boolean incluidoNoPlano;
    private double somaDasAvaliacoes;
    private int totalAvaliacoes;
    public int duracaoEmMinuto;

    public int getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    public void exibeFichaTecnica(){
        System.out.println("Nome do filme: " + nome);
        System.out.println("Ano de lançamento: " + anoDeLancamento);
    }

    public void avalia(double nota) {
        somaDasAvaliacoes += nota;
        totalAvaliacoes++;
    }

    public double retornaMediaDasAvaliacoes(){
        return somaDasAvaliacoes / totalAvaliacoes;
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class aplicacaoDeCompras {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        double limiteCartao = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print("Digite o limite do seu cartão de crédito: ");
                limiteCartao = Double.parseDouble(scanner.nextLine());
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um valor numérico.");
            }
        }

        double saldoDisponivel = limiteCartao;


        List<Compra> compras = new ArrayList<>();

        int opcao = 0;
        do {

            String descricaoCompra = "";
            entradaValida = false;
            while (!entradaValida) {
                System.out.print("Digite a descrição da compra: ");
                descricaoCompra = scanner.nextLine().trim();


                if (descricaoCompra.isEmpty()) {
                    System.out.println("Descrição inválida! A descrição não pode estar vazia.");
                } else if (descricaoCompra.length() < 2) {
                    System.out.println("Descrição inválida! A descrição deve ter pelo menos 2 caracteres.");
                } else {
                    entradaValida = true;
                }
            }


            double valorCompra = 0;
            entradaValida = false;
            while (!entradaValida) {
                try {
                    System.out.print("Digite o valor da compra: ");
                    valorCompra = Double.parseDouble(scanner.nextLine());
                    entradaValida = true;
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Digite um valor numérico.");
                }
            }


            if (valorCompra > saldoDisponivel) {
                System.out.println("Saldo insuficiente! Compra não realizada.");
            } else {

                saldoDisponivel -= valorCompra;
                compras.add(new Compra(descricaoCompra, valorCompra)); // Adiciona a compra à lista
                System.out.println("Compra realizada: " + descricaoCompra + " - R$" + valorCompra);
                System.out.println("Saldo disponível: R$" + saldoDisponivel);
            }


            entradaValida = false;
            while (!entradaValida) {
                try {
                    System.out.print("Digite 0 para sair ou 1 para comprar mais: ");
                    opcao = Integer.parseInt(scanner.nextLine());
                    if (opcao == 0 || opcao == 1) {
                        entradaValida = true;
                    } else {
                        System.out.println("Entrada inválida! Digite 0 ou 1.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Digite 0 ou 1.");
                }
            }

        } while (opcao != 0 && saldoDisponivel > 0);


        if (saldoDisponivel <= 0) {
            System.out.println("Limite do cartão excedido. Não é possível realizar mais compras.");
        }


        Collections.sort(compras, Comparator.comparingDouble(Compra::getValor));


        System.out.println("\nCompras realizadas (ordenadas por valor):");
        for (Compra compra : compras) {
            System.out.println(compra.getDescricao() + " - R$" + compra.getValor());
        }

        System.out.println("Programa encerrado. Saldo final: R$" + saldoDisponivel);

        scanner.close();
    }
}



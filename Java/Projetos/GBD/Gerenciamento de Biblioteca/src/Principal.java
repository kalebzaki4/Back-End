import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Livro> livros = new ArrayList<>(); // Lista para armazenar todos os livros

        String nomeFilme, autor, resposta = "", anoDePublicacao, disponibilidade, continuar;

        // Loop para adicionar filmes à lista
        do {
            do {
                System.out.println("Digite o nome do seu filme: ");
                nomeFilme = sc.nextLine().trim();

                if (!isNomeFilmeValido(nomeFilme)) {
                    System.out.println("Nome inválido! O nome do filme deve conter pelo menos 3 letras e não pode conter números ou símbolos.");
                    continue; // Repetir o loop até receber uma entrada válida
                }

                System.out.println("Seu filme é: " + nomeFilme + " Correto? (sim/não)");
                resposta = sc.nextLine().toLowerCase();
            } while (!resposta.equals("sim"));

            do {
                System.out.println("Digite o nome do diretor/autor do filme: ");
                autor = sc.nextLine().trim();

                if (!isNomeValido(autor)) {
                    System.out.println("Nome do diretor inválido! Por favor, insira um nome com pelo menos duas letras.");
                    continue; // Repetir o loop até receber uma entrada válida
                }
                break; // Sai do loop caso o nome seja válido
            } while (true);
    
            System.out.println("Digite o ano de lançamento do filme: ");
            anoDePublicacao = sc.nextLine();

            System.out.println("O filme está disponível para vendas? (sim/não)");
            disponibilidade = sc.nextLine().toLowerCase();
            if (!disponibilidade.equals("sim") && !disponibilidade.equals("não")) {
                System.out.println("Resposta inválida. Por padrão, será considerado como 'não disponível'.");
                disponibilidade = "não";
            }

            livros.add(new Livro(nomeFilme, autor, anoDePublicacao, disponibilidade));

            System.out.println("Deseja adicionar outro filme? (sim/não)");
            continuar = sc.nextLine().toLowerCase();
        } while (continuar.equals("sim"));

        // Menu para listar ou gerenciar filmes
        while (true) {
            System.out.println("\n--- Opções ---");
            System.out.println("1. Listar filmes");
            System.out.println("2. Alterar status para 'Emprestado'");
            System.out.println("3. Sair");
            System.out.println("4. Alterar status para 'Disponível'");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine(); // Consumindo a quebra de linha para evitar conflitos no Scanner

            switch (opcao) {
                case 1: // Listar todos os filmes
                    System.out.println("\n--- Lista de Filmes ---");
                    for (Livro livro : livros) {
                        System.out.println(livro); // Chama o método `toString` da classe Livro
                    }
                    break;

                case 2: // Alterar status de um filme para "Emprestado"
                    System.out.println("Digite o nome do filme para alterar o status: ");
                    String filmeParaEmprestar = sc.nextLine();

                    Livro livroEncontrado = null;
                    for (Livro livro : livros) {
                        if (livro.getNome().equalsIgnoreCase(filmeParaEmprestar)) {
                            livroEncontrado = livro;
                            break;
                        }
                    }

                    if (livroEncontrado != null) {
                        if (livroEncontrado.getDisponibilidade().equals("sim")) {
                            System.out.println("Digite o nome da pessoa que está pegando este filme emprestado: ");
                            String nomePessoa = sc.nextLine();

                            if (!isNomeValido(nomePessoa)) {
                                System.out.println("Nome inválido! Por favor, insira um nome válido.");
                                break; // Sai do fluxo sem continuar
                            }

                            livroEncontrado.setDisponibilidade("emprestado");
                            livroEncontrado.setEmprestadoPara(nomePessoa);

                            System.out.println("Status do filme alterado para 'Emprestado'.");
                        } else if (livroEncontrado.getDisponibilidade().equals("emprestado")) {
                            System.out.println("Este filme já está emprestado para " + livroEncontrado.getEmprestadoPara() + ".");
                        } else {
                            System.out.println("Este filme não está disponível para empréstimo no momento.");
                        }
                    } else {
                        System.out.println("Filme não encontrado na lista.");
                    }
                    break;

                case 3: // Sair do programa
                    System.out.println("Saindo do programa...");
                    sc.close();
                    return; // Encerra o programa

                case 4: // Alterar status de um filme para "Disponível"
                    System.out.println("Digite o nome do filme para alterar o status para 'Disponível': ");
                    String filmeParaDisponivel = sc.nextLine(); // Recebe o nome do filme

                    Livro livroParaSerAlterado = null;
                    for (Livro livro : livros) {
                        if (livro.getNome().equalsIgnoreCase(filmeParaDisponivel)) {
                            livroParaSerAlterado = livro; // Filme encontrado
                            break;
                        }
                    }

                    if (livroParaSerAlterado != null) {
                        if (livroParaSerAlterado.getDisponibilidade().equals("emprestado")) {
                            // Neste caso, o filme já está marcado como "Emprestado"
                            System.out.println("O status do filme foi 'Emprestado'. Ele precisará ser devolvido antes de estar disponível.");
                        } else if (!livroParaSerAlterado.getDisponibilidade().equals("sim")) {
                            // Caso o status não seja "sim", alteramos para "Disponível"
                            livroParaSerAlterado.setDisponibilidade("sim"); // Altera o status
                            livroParaSerAlterado.setEmprestadoPara(null); // Remove o nome de quem emprestou, caso exista
                            System.out.println("O status do filme foi alterado para 'Disponível'.");
                        } else {
                            System.out.println("O filme já está disponível.");
                        }
                    } else {
                        System.out.println("Filme não encontrado na lista.");
                    }
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    // Método para validar os nomes de filmes
    private static boolean isNomeFilmeValido(String nome) {
        // Verifica se o nome tem pelo menos 3 caracteres e só contém letras e espaço
        return nome != null
                && nome.length() >= 3
                && nome.matches("^[a-zA-Z ]+$");
    }

    // Método para validar os nomes de pessoas ou autores
    private static boolean isNomeValido(String nome) {
        return nome != null && nome.length() > 1 && !nome.trim().isEmpty() && nome.matches("[a-zA-Z ]+");
    }
}

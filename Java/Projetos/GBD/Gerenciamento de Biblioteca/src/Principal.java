import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Livro> livros = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Opções ---");
            System.out.println("1. Adicionar filme");
            System.out.println("2. Listar filmes");
            System.out.println("3. Alterar status para 'Emprestado'");
            System.out.println("4. Alterar status para 'Disponível'");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine(); // Consumindo a quebra de linha

            switch (opcao) {
                case 1:
                    adicionarFilme();
                    break;
                case 2:
                    listarFilmes();
                    break;
                case 3:
                    alterarStatusParaEmprestado();
                    break;
                case 4:
                    alterarStatusParaDisponivel();
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    sc.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void adicionarFilme() {
        String nomeFilme, autor, anoDePublicacao, disponibilidade;

        do {
            System.out.println("Digite o nome do filme: ");
            nomeFilme = sc.nextLine().trim();
            if (!isNomeFilmeValido(nomeFilme)) {
                System.out.println("Nome inválido! O nome do filme deve conter pelo menos 3 letras e não pode conter números ou símbolos.");
                continue;
            }
            System.out.println("Seu filme é: " + nomeFilme + " Correto? (sim/não)");
        } while (!sc.nextLine().equalsIgnoreCase("sim"));

        do {
            System.out.println("Digite o nome do diretor/autor do filme: ");
            autor = sc.nextLine().trim();
            if (!isNomeValido(autor)) {
                System.out.println("Nome do diretor inválido! Por favor, insira um nome com pelo menos duas letras.");
                continue;
            }
            break;
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
    }

    private static void listarFilmes() {
        System.out.println("\n--- Lista de Filmes ---");
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    private static void alterarStatusParaEmprestado() {
        System.out.println("Digite o nome do filme para alterar o status: ");
        String filmeParaEmprestar = sc.nextLine();

        Livro livroEncontrado = encontrarLivro(filmeParaEmprestar);
        if (livroEncontrado != null) {
            if (livroEncontrado.getDisponibilidade().equals("sim")) {
                System.out.println("Digite o nome da pessoa que está pegando este filme emprestado: ");
                String nomePessoa = sc.nextLine();

                if (!isNomeValido(nomePessoa)) {
                    System.out.println("Nome inválido! Por favor, insira um nome válido.");
                    return;
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
    }

    private static void alterarStatusParaDisponivel() {
        System.out.println("Digite o nome do filme para alterar o status para 'Disponível': ");
        String filmeParaDisponivel = sc.nextLine();

        Livro livroParaSerAlterado = encontrarLivro(filmeParaDisponivel);
        if (livroParaSerAlterado != null) {
            if (livroParaSerAlterado.getDisponibilidade().equals("emprestado")) {
                System.out.println("O status do filme foi 'Emprestado'. Ele precisará ser devolvido antes de estar disponível.");
            } else if (!livroParaSerAlterado.getDisponibilidade().equals("sim")) {
                livroParaSerAlterado.setDisponibilidade("sim");
                livroParaSerAlterado.setEmprestadoPara(null);
                System.out.println("O status do filme foi alterado para 'Disponível'.");
            } else {
                System.out.println("O filme já está disponível.");
            }
        } else {
            System.out.println("Filme não encontrado na lista.");
        }
    }

    private static Livro encontrarLivro(String nome) {
        for (Livro livro : livros) {
            if (livro.getNome().equalsIgnoreCase(nome)) {
                return livro;
            }
        }
        return null;
    }

    private static boolean isNomeFilmeValido(String nome) {
        return nome != null && nome.length() >= 3 && nome.matches("^[a-zA-Z ]+$");
    }

    private static boolean isNomeValido(String nome) {
        return nome != null && nome.length() > 1 && !nome.trim().isEmpty() && nome.matches("[a-zA-Z ]+");
    }
}

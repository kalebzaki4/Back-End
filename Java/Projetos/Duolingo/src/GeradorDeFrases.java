import java.util.*;
import java.util.regex.Pattern;

public class GeradorDeFrases {

    // Enum para categorias de palavras
    private enum Categoria {
        SUJEITO, VERBO, OBJETO, EXTRA, CONJUNCAO
    }

    // Mapa de traduções dinâmicas
    private static final Map<String, String> TRADUCOES = new HashMap<>();

    static {
        // Sujeitos
        adicionarTraducao(Categoria.SUJEITO, "O gato", "The cat");
        adicionarTraducao(Categoria.SUJEITO, "A menina", "The girl");
        adicionarTraducao(Categoria.SUJEITO, "Um cientista", "A scientist");
        adicionarTraducao(Categoria.SUJEITO, "O dragão", "The dragon");
        adicionarTraducao(Categoria.SUJEITO, "O professor", "The teacher");
        adicionarTraducao(Categoria.SUJEITO, "Uma estrela cadente", "A shooting star");

        // Verbos
        adicionarTraducao(Categoria.VERBO, "corre", "runs");
        adicionarTraducao(Categoria.VERBO, "pula", "jumps");
        adicionarTraducao(Categoria.VERBO, "descobre", "discovers");
        adicionarTraducao(Categoria.VERBO, "queima", "burns");
        adicionarTraducao(Categoria.VERBO, "explica", "explains");
        adicionarTraducao(Categoria.VERBO, "cai", "falls");

        // Objetos
        adicionarTraducao(Categoria.OBJETO, "no jardim", "in the garden");
        adicionarTraducao(Categoria.OBJETO, "no parque", "in the park");
        adicionarTraducao(Categoria.OBJETO, "um segredo", "a secret");
        adicionarTraducao(Categoria.OBJETO, "a floresta mágica", "the magical forest");
        adicionarTraducao(Categoria.OBJETO, "um experimento", "an experiment");
        adicionarTraducao(Categoria.OBJETO, "em alta velocidade", "at high speed");

        // Extras
        adicionarTraducao(Categoria.EXTRA, "rapidamente", "quickly");
        adicionarTraducao(Categoria.EXTRA, "com coragem", "with courage");
        adicionarTraducao(Categoria.EXTRA, "enquanto voa", "while flying");
        adicionarTraducao(Categoria.EXTRA, "num castelo perdido", "in a lost castle");
        adicionarTraducao(Categoria.EXTRA, "com muita energia", "with a lot of energy");

        // Conjunções
        adicionarTraducao(Categoria.CONJUNCAO, "e", "and");
        adicionarTraducao(Categoria.CONJUNCAO, "mas", "but");
    }

    // Método para adicionar traduções ao mapa
    private static void adicionarTraducao(Categoria categoria, String pt, String en) {
        TRADUCOES.put(pt, en);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean modoDeus = false;

        int nivel = 1;
        int pontos = 0;
        int vidas = 3;

        System.out.println("Bem-vindo ao Gerador de Frases Avançado!");
        System.out.println("Você começará com frases simples, mas o nível de dificuldade aumentará!");
        System.out.println("Digite 'ActivateModeGod' para ativar o Modo Deus e ver as respostas.");
        System.out.println("Digite 'DeactivateModeGod' para desativar o Modo Deus.");
        System.out.println("Digite 'sair' para encerrar o jogo.");
        System.out.println("Boa sorte!");

        while (vidas > 0) {
            System.out.println("========================================================");
            System.out.println("Nível atual: " + nivel + " | Pontos: " + pontos + " | Vidas: " + "♥".repeat(vidas));
            String frasePortugues = gerarFrasePortugues(random, nivel);
            String fraseIngles = traduzirFraseParaIngles(frasePortugues);

            if (modoDeus) {
                System.out.println("[Modo Deus Ativado] Resposta correta: " + fraseIngles);
            }

            System.out.println("Traduza a seguinte frase para o inglês:");
            System.out.println(frasePortugues);

            String respostaJogador = scanner.nextLine().trim();

            if (respostaJogador.equalsIgnoreCase("ActivateModeGod")) {
                modoDeus = true;
                System.out.println("Modo Deus ativado! Respostas serão mostradas.");
                continue;
            }

            if (respostaJogador.equalsIgnoreCase("DeactivateModeGod")) {
                modoDeus = false;
                System.out.println("Modo Deus desativado.");
                continue;
            }

            if (respostaJogador.equalsIgnoreCase("sair")) {
                System.out.println("========================================================");
                System.out.println("Obrigado por jogar! Você acumulou " + pontos + " pontos. Até a próxima!");
                break;
            }

            // Comparar respostas ignorando pontuação e maiúsculas/minúsculas
            if (compararRespostas(respostaJogador, fraseIngles)) {
                pontos++;
                System.out.println("Correto! Você ganhou 1 ponto.");

                if (pontos % 5 == 0) {
                    nivel++;
                    System.out.println("Parabéns! Você subiu para o nível " + nivel + "!");
                }
            } else {
                System.out.println("Errado! A resposta correta é: " + fraseIngles);
                vidas--;
                System.out.println("Você perdeu uma vida! Vidas restantes: " + "♥".repeat(vidas));
            }
        }

        System.out.println("========================================================");
        System.out.println("Game Over! Você alcançou o nível " + nivel + " e acumulou " + pontos + " pontos.");
        scanner.close();
    }

    // Método para gerar frases em português
    private static String gerarFrasePortugues(Random random, int nivel) {
        List<String> sujeitos = filtrarPalavrasPorCategoria(Categoria.SUJEITO);
        List<String> verbos = filtrarPalavrasPorCategoria(Categoria.VERBO);
        List<String> objetos = filtrarPalavrasPorCategoria(Categoria.OBJETO);
        List<String> extras = filtrarPalavrasPorCategoria(Categoria.EXTRA);
        List<String> conjuncoes = filtrarPalavrasPorCategoria(Categoria.CONJUNCAO);

        StringBuilder frase = new StringBuilder();
        frase.append(sujeitos.get(random.nextInt(sujeitos.size()))).append(" ");
        frase.append(verbos.get(random.nextInt(verbos.size()))).append(" ");
        frase.append(objetos.get(random.nextInt(objetos.size())));

        if (nivel > 2) {
            frase.append(" ").append(extras.get(random.nextInt(extras.size())));
        }

        if (nivel > 5) {
            String conjuncao = conjuncoes.get(random.nextInt(conjuncoes.size()));
            frase.append(" ").append(conjuncao).append(" ");
            frase.append(sujeitos.get(random.nextInt(sujeitos.size())).toLowerCase()).append(" ");
            frase.append(verbos.get(random.nextInt(verbos.size()))).append(" ");
            frase.append(objetos.get(random.nextInt(objetos.size())));
        }

        return frase.append(".").toString();
    }

    // Método para filtrar palavras por categoria
    private static List<String> filtrarPalavrasPorCategoria(Categoria categoria) {
        return TRADUCOES.keySet().stream()
                .filter(key -> TRADUCOES.get(key).startsWith(categoria.name().charAt(0) == 'S' ? "The" : ""))
                .toList();
    }

    // Método para traduzir frases para inglês
    private static String traduzirFraseParaIngles(String frasePt) {
        String fraseEn = frasePt;

        // Usar expressões regulares para substituir palavras inteiras
        for (Map.Entry<String, String> entry : TRADUCOES.entrySet()) {
            fraseEn = fraseEn.replaceAll("\\b" + Pattern.quote(entry.getKey()) + "\\b", entry.getValue());
        }

        return fraseEn.toLowerCase();
    }

    // Método para comparar respostas ignorando pontuação e maiúsculas/minúsculas
    private static boolean compararRespostas(String respostaJogador, String respostaCorreta) {
        // Remover pontuação e converter para letras minúsculas
        String respostaJogadorLimpa = respostaJogador.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        String respostaCorretaLimpa = respostaCorreta.replaceAll("[^a-zA-Z ]", "").toLowerCase();

        return respostaJogadorLimpa.equals(respostaCorretaLimpa);
    }
}

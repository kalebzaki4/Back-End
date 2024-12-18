import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

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

            if (respostaJogador.equalsIgnoreCase(fraseIngles)) {
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
        String[] sujeitos = TRADUCOES.keySet().stream()
                .filter(key -> TRADUCOES.get(key).startsWith("The") || TRADUCOES.get(key).startsWith("A "))
                .toArray(String[]::new);
        String[] verbos = TRADUCOES.keySet().stream()
                .filter(key -> TRADUCOES.get(key).endsWith("s"))
                .toArray(String[]::new);
        String[] objetos = TRADUCOES.keySet().stream()
                .filter(key -> TRADUCOES.get(key).contains("the") || TRADUCOES.get(key).contains("a "))
                .toArray(String[]::new);
        String[] extras = TRADUCOES.keySet().stream()
                .filter(key -> TRADUCOES.get(key).contains("with") || TRADUCOES.get(key).contains("while"))
                .toArray(String[]::new);

        String frase = sujeitos[random.nextInt(sujeitos.length)] + " " +
                verbos[random.nextInt(verbos.length)] + " " +
                objetos[random.nextInt(objetos.length)];

        if (nivel > 2) {
            frase += " " + extras[random.nextInt(extras.length)];
        }

        if (nivel > 5) {
            String conjuncao = random.nextBoolean() ? "e" : "mas";
            frase += " " + conjuncao + " " +
                    sujeitos[random.nextInt(sujeitos.length)].toLowerCase() + " " +
                    verbos[random.nextInt(verbos.length)] + " " +
                    objetos[random.nextInt(objetos.length)];
        }

        return frase + ".";
    }

    // Método para traduzir frases para inglês
    private static String traduzirFraseParaIngles(String frasePt) {
        String fraseEn = frasePt;
        for (Map.Entry<String, String> entry : TRADUCOES.entrySet()) {
            fraseEn = fraseEn.replace(entry.getKey(), entry.getValue());
        }
        return fraseEn.toLowerCase();
    }
}

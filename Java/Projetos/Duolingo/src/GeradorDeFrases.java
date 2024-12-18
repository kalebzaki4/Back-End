import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class GeradorDeFrases {

    private static final Map<String, String> TRADUCOES = new HashMap<>();

    static {
        // Sujeitos
        TRADUCOES.put("O gato", "The cat");
        TRADUCOES.put("A menina", "The girl");
        TRADUCOES.put("Um cientista", "A scientist");
        TRADUCOES.put("O dragão", "The dragon");
        TRADUCOES.put("O professor", "The teacher");
        TRADUCOES.put("Uma estrela cadente", "A shooting star");

        // Verbos
        TRADUCOES.put("corre", "runs");
        TRADUCOES.put("pula", "jumps");
        TRADUCOES.put("descobre", "discovers");
        TRADUCOES.put("queima", "burns");
        TRADUCOES.put("explica", "explains");
        TRADUCOES.put("cai", "falls");

        // Objetos
        TRADUCOES.put("no jardim", "in the garden");
        TRADUCOES.put("no parque", "in the park");
        TRADUCOES.put("um segredo", "a secret");
        TRADUCOES.put("a floresta mágica", "the magical forest");
        TRADUCOES.put("um experimento", "an experiment");
        TRADUCOES.put("em alta velocidade", "at high speed");

        // Extras
        TRADUCOES.put("rapidamente", "quickly");
        TRADUCOES.put("com coragem", "with courage");
        TRADUCOES.put("enquanto voa", "while flying");
        TRADUCOES.put("num castelo perdido", "in a lost castle");
        TRADUCOES.put("com muita energia", "with a lot of energy");

        // Conjunções
        TRADUCOES.put("e", "and");
        TRADUCOES.put("mas", "but");
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

    private static String gerarFrasePortugues(Random random, int nivel) {
        String[] sujeitos = {"O gato", "A menina", "Um cientista", "O dragão", "O professor", "Uma estrela cadente"};
        String[] verbos = {"corre", "pula", "descobre", "queima", "explica", "cai"};
        String[] objetos = {"no jardim", "no parque", "um segredo", "a floresta mágica", "um experimento", "em alta velocidade"};
        String[] extras = {"rapidamente", "com coragem", "enquanto voa", "num castelo perdido", "com muita energia"};

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

    private static String traduzirFraseParaIngles(String frasePt) {
        String fraseEn = frasePt;
        for (Map.Entry<String, String> entry : TRADUCOES.entrySet()) {
            fraseEn = fraseEn.replace(entry.getKey(), entry.getValue());
        }
        return fraseEn.toLowerCase();
    }
}

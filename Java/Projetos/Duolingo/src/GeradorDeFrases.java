import java.util.Random;
import java.util.Scanner;

public class GeradorDeFrases {

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
        return frasePt
                .replace("O gato", "The cat")
                .replace("A menina", "The girl")
                .replace("Um cientista", "A scientist")
                .replace("O dragão", "The dragon")
                .replace("O professor", "The teacher")
                .replace("Uma estrela cadente", "A shooting star")
                .replace("corre", "runs")
                .replace("pula", "jumps")
                .replace("descobre", "discovers")
                .replace("queima", "burns")
                .replace("explica", "explains")
                .replace("cai", "falls")
                .replace("no jardim", "in the garden")
                .replace("no parque", "in the park")
                .replace("um segredo", "a secret")
                .replace("a floresta mágica", "the magical forest")
                .replace("um experimento", "an experiment")
                .replace("em alta velocidade", "at high speed")
                .replace("rapidamente", "quickly")
                .replace("com coragem", "with courage")
                .replace("enquanto voa", "while flying")
                .replace("num castelo perdido", "in a lost castle")
                .replace("com muita energia", "with a lot of energy")
                .replace("e", "and")
                .replace("mas", "but")
                .toLowerCase();
    }
}
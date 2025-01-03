import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class geradorDeCodigo {
    private static String codigoConfirmacao;
    private static LocalDateTime dataExpiracao;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criar lista de inscrições fixas
        List<PACKAGE_NAME.dadosSeguranca> inscricoes = PACKAGE_NAME.dadosSeguranca.criarListaInscricoes();

        System.out.println("========================================");
        System.out.println("Bem-vindo ao Sistema de Segurança");
        System.out.println("========================================");

        int tentativasRestantes = 6; // Número máximo de tentativas

        while (tentativasRestantes > 0) {
            System.out.println("Tentativas restantes: " + tentativasRestantes);

            // Solicitar e validar o nome
            String nome;
            while (true) {
                System.out.print("Digite seu nome: ");
                nome = scanner.nextLine().trim(); // Remove espaços extras no início e no fim

                // Verificar se o nome tem pelo menos 2 caracteres e contém apenas letras e espaços
                if (nome.length() >= 2 && nome.matches("[a-zA-ZÀ-ú\\s]+")) {
                    break; // Nome válido
                } else {
                    System.out.println("Nome inválido! O nome deve ter pelo menos 2 caracteres e conter apenas letras e espaços.");
                    tentativasRestantes--;
                    if (tentativasRestantes == 0) break;
                }
            }
            if (tentativasRestantes == 0) break;

            // Solicitar DDD e validar
            String ddd;
            while (true) {
                System.out.print("Digite seu DDD (2 dígitos): ");
                ddd = scanner.nextLine();
                if (ddd.length() == 2 && ddd.matches("\\d{2}")) {
                    break;
                } else {
                    System.out.println("DDD inválido! Digite exatamente 2 dígitos.");
                    tentativasRestantes--;
                    if (tentativasRestantes == 0) break;
                }
            }
            if (tentativasRestantes == 0) break;

            // Solicitar número restante e validar
            String numeroRestante;
            while (true) {
                System.out.print("Digite o restante do número (8 ou 9 dígitos): ");
                numeroRestante = scanner.nextLine();
                if (numeroRestante.matches("\\d{8,9}")) {
                    break;
                } else {
                    System.out.println("Número inválido! Digite 8 ou 9 dígitos.");
                    tentativasRestantes--;
                    if (tentativasRestantes == 0) break;
                }
            }
            if (tentativasRestantes == 0) break;

            // Combinar DDD e número restante no formato (XX) XXXXX-XXXX
            String numero = "(" + ddd + ") " + numeroRestante.substring(0, 5) + "-" + numeroRestante.substring(5);

            System.out.print("Digite seu código de segurança: ");
            String codigoSeguranca = scanner.nextLine();

            // Solicitar e validar CPF
            String cpf;
            while (true) {
                System.out.println("Digite seu CPF no formato XXX.XXX.XXX-XX (exemplo: 123.456.789-01): ");
                cpf = scanner.nextLine();
                if (cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                    break; // CPF válido
                } else {
                    System.out.println("CPF inválido! Digite no formato XXX.XXX.XXX-XX (exemplo: 123.456.789-01).");
                    tentativasRestantes--;
                    if (tentativasRestantes == 0) break;
                }
            }
            if (tentativasRestantes == 0) break;

            // Solicitar e validar RG
            String rg;
            while (true) {
                System.out.println("Digite seu RG no formato XX.XXX.XXX-X (exemplo: 12.345.678-9): ");
                rg = scanner.nextLine();
                if (rg.matches("\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}")) {
                    break; // RG válido
                } else {
                    System.out.println("RG inválido! Digite no formato XX.XXX.XXX-X (exemplo: 12.345.678-9).");
                    tentativasRestantes--;
                    if (tentativasRestantes == 0) break;
                }
            }
            if (tentativasRestantes == 0) break;

            // Verificar se os dados coincidem com algum registro na lista
            boolean acessoConcedido = false;
            for (PACKAGE_NAME.dadosSeguranca dadosSegurancaValida : inscricoes) {
                if (nome.equalsIgnoreCase(dadosSegurancaValida.getNome()) && // Ignora maiúsculas/minúsculas
                        numero.equals(dadosSegurancaValida.getNumero()) &&
                        codigoSeguranca.equals(dadosSegurancaValida.getCodigoSeguranca()) &&
                        cpf.equals(dadosSegurancaValida.getCpf()) &&
                        rg.equals(dadosSegurancaValida.getRg())) {
                    acessoConcedido = true;
                    break;
                }
            }

            if (acessoConcedido) {
                System.out.println("========================================");
                System.out.println("Verificação bem-sucedida! Acesso concedido.");
                System.out.println("========================================");

                // Gerar um novo código de confirmação
                gerarNovoCodigoConfirmacao();
                System.out.println("Um novo código de confirmação foi gerado: " + codigoConfirmacao);
                System.out.println("Este código expira em: " + dataExpiracao);

                // Verificar se o código de confirmação ainda é válido
                while (true) {
                    System.out.print("Digite o código de confirmação: ");
                    String codigoDigitado = scanner.nextLine();

                    if (verificarCodigoConfirmacao(codigoDigitado)) {
                        System.out.println("Código válido! Acesso completo concedido.");
                        break;
                    } else {
                        System.out.println("Código inválido ou expirado. Um novo código será gerado.");
                        gerarNovoCodigoConfirmacao();
                        System.out.println("Novo código de confirmação: " + codigoConfirmacao);
                        System.out.println("Este código expira em: " + dataExpiracao);
                    }
                }

                scanner.close();
                return; // Encerra o programa após acesso concedido
            } else {
                System.out.println("========================================");
                System.out.println("Dados inválidos! Acesso negado.");
                System.out.println("========================================");
                tentativasRestantes--; // Reduz o número de tentativas restantes
            }
        }

        // Se o usuário exceder o número de tentativas
        System.out.println("========================================");
        System.out.println("Você excedeu o número máximo de tentativas. Acesso bloqueado.");
        System.out.println("========================================");

        scanner.close();
    }

    // Método para gerar um novo código de confirmação
    private static void gerarNovoCodigoConfirmacao() {
        Random random = new Random();
        codigoConfirmacao = String.format("%06d", random.nextInt(1000000)); // Gera um código de 6 dígitos
        dataExpiracao = LocalDateTime.now().plus(2, ChronoUnit.DAYS); // Expira em 2 dias
    }

    // Método para verificar se o código de confirmação é válido
    private static boolean verificarCodigoConfirmacao(String codigoDigitado) {
        if (codigoDigitado.equals(codigoConfirmacao) && LocalDateTime.now().isBefore(dataExpiracao)) {
            return true; // Código válido e não expirado
        }
        return false; // Código inválido ou expirado
    }
}

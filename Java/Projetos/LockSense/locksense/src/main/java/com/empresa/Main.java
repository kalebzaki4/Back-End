import com.empresa.SistemaCadastro;
import com.empresa.SistemaLogin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaCadastro sistemaCadastro = new SistemaCadastro();
        SistemaLogin sistemaLogin = new SistemaLogin();

        while (true) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");

            String entrada = scanner.nextLine();
            int opcao;

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número entre 1 e 4.");
                continue;
            }

            switch (opcao) {
                case 1:
                    sistemaCadastro.cadastrarUsuario();
                    break;
                case 2:
                    sistemaLogin.login();
                    break;
                case 3:
                    sistemaLogin.validarCodigo();
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida. Digite um número entre 1 e 4.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== Sistema LockSense ===");
        System.out.println("1. Cadastrar usuário");
        System.out.println("2. Fazer login");
        System.out.println("3. Validar código");
        System.out.println("4. Sair");
    }
}

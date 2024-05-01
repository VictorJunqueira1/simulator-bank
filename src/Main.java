import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.print("Por favor, entre com o nome do titular da conta: ");
        String nomeTitular = scanner.nextLine();
        ContaBancaria minhaConta = new ContaBancaria(12345, 0, nomeTitular);

        while (true) {
            System.out.println("\nEscolha uma operação:");
            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Transferir");
            System.out.println("4 - Consultar Saldo");
            System.out.println("0 - Sair");
            System.out.print("Operação: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Por favor, digite um número válido.");
                scanner.next(); // Limpa o buffer do scanner
                continue;
            }
            int operacao = scanner.nextInt();

            double valor;
            switch (operacao) {
                case 1:
                    System.out.print("Valor para depósito: ");
                    if (scanner.hasNextDouble()) {
                        valor = scanner.nextDouble();
                        minhaConta.depositar(valor);
                    } else {
                        System.out.println("Por favor, digite um número válido.");
                        scanner.next(); // Limpa o buffer do scanner
                    }
                    break;
                case 2:
                    System.out.print("Valor para saque: ");
                    if (scanner.hasNextDouble()) {
                        valor = scanner.nextDouble();
                        minhaConta.sacar(valor);
                    } else {
                        System.out.println("Por favor, digite um número válido.");
                        scanner.next(); // Limpa o buffer do scanner
                    }
                    break;
                case 3:
                    System.out.print("Valor para transferência: ");
                    if (scanner.hasNextDouble()) {
                        valor = scanner.nextDouble();
                        System.out.print("Número da conta destinatária: ");
                        if (scanner.hasNextInt()) {
                            int numContaDest = scanner.nextInt();
                            scanner.nextLine(); // Limpa o buffer do scanner
                            System.out.print("Nome do titular da conta destinatária: ");
                            String nomeDest = scanner.nextLine();
                            minhaConta.transferir(new ContaBancaria(numContaDest, 0, nomeDest), valor);
                        } else {
                            System.out.println("Por favor, digite um número válido para a conta.");
                            scanner.next(); // Limpa o buffer do scanner
                        }
                    } else {
                        System.out.println("Por favor, digite um número válido.");
                        scanner.next(); // Limpa o buffer do scanner
                    }
                    break;
                case 4:
                    minhaConta.consultarSaldo();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema bancário...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Operação inválida.");
            }
        }
    }
}
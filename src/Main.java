import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        GerenciadorDeContas gerenciador = new GerenciadorDeContas();

        int opcao = -1;
        do {
            try {
                System.out.println("\nMenu:");
                System.out.println("1 - Entrar numa conta existente");
                System.out.println("2 - Criar nova conta");
                System.out.println("3 - Mostrar todas as contas");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        entrarContaExistente(scanner, gerenciador);
                        break;
                    case 2:
                        criarNovaConta(scanner, gerenciador);
                        break;
                    case 3:
                        gerenciador.mostrarTodasAsContas();
                        break;
                    case 0:
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, tente novamente com um número.");
                scanner.nextLine();
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void entrarContaExistente(Scanner scanner, GerenciadorDeContas gerenciador) {
        System.out.print("Digite o número da conta: ");
        int numConta = scanner.nextInt();
        ContaBancaria contaAtual = gerenciador.obterConta(numConta);
        if (contaAtual == null) {
            System.out.println("Conta não encontrada. Por favor, tente novamente.");
        } else {
            System.out.println("Bem-vindo(a), " + contaAtual.getNomeTitular() + "!");
            subMenu(scanner, contaAtual, gerenciador);
        }
    }

    private static void criarNovaConta(Scanner scanner, GerenciadorDeContas gerenciador) {
        System.out.print("Entre com o nome do titular da nova conta: ");
        String novoNomeTitular = scanner.nextLine();
        System.out.print("Entre com o número da nova conta: ");
        int novoNumeroDaConta = scanner.nextInt();
        if (gerenciador.contaExiste(novoNumeroDaConta)) {
            System.out.println("Erro: Uma conta com esse número já existe.");
            return;
        }
        System.out.print("Entre com o saldo inicial: ");
        double novoSaldoInicial = scanner.nextDouble();
        ContaBancaria novaConta = new ContaBancaria(novoNumeroDaConta, novoSaldoInicial, novoNomeTitular);
        gerenciador.adicionarConta(novaConta);
        System.out.println("Conta criada com sucesso!");
    }

    private static void subMenu(Scanner scanner, ContaBancaria conta, GerenciadorDeContas gerenciador) {
        int escolha;
        do {
            System.out.println("\nSub-menu:");
            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Transferir");
            System.out.println("4 - Consultar Saldo");
            System.out.println("5 - Exibir Histórico de Transações");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma operação: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.print("Digite o valor para depósito: ");
                    double deposito = scanner.nextDouble();
                    conta.depositar(deposito);
                    break;
                case 2:
                    System.out.print("Digite o valor para saque: ");
                    double saque = scanner.nextDouble();
                    conta.sacar(saque);
                    break;
                case 3:
                    System.out.print("Número da conta destinatária: ");
                    int numContaDest = scanner.nextInt();
                    ContaBancaria contaDest = gerenciador.obterConta(numContaDest);
                    if (contaDest != null) {
                        System.out.print("Digite o valor para transferência: ");
                        double valor = scanner.nextDouble();
                        conta.transferir(contaDest, valor);
                    } else {
                        System.out.println("Conta destinatária não encontrada.");
                    }
                    break;
                case 4:
                    conta.consultarSaldo();
                    break;
                case 5:
                    conta.exibirHistoricoTransacoes();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (escolha != 0);
    }
}
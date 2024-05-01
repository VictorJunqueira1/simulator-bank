import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        GerenciadorDeContas gerenciador = new GerenciadorDeContas();
        
        System.out.print("Entre com o nome do titular da conta: ");
        String nomeTitular = scanner.nextLine();
        ContaBancaria minhaConta = new ContaBancaria(12345, 0, nomeTitular);
        gerenciador.adicionarConta(minhaConta);

        while (true) {
            System.out.println("\nEscolha uma operação:");
            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Transferir");
            System.out.println("4 - Consultar Saldo");
            System.out.println("5 - Exibir Histórico de Transações");
            System.out.println("6 - Criar nova conta");
            System.out.println("0 - Sair");
            System.out.print("Operação: ");

            int operacao = scanner.nextInt();

            double valor;
            switch (operacao) {
                case 1:
                    System.out.print("Valor para depósito: ");
                    valor = scanner.nextDouble();
                    minhaConta.depositar(valor);
                    break;
                case 2:
                    System.out.print("Valor para saque: ");
                    valor = scanner.nextDouble();
                    minhaConta.sacar(valor);
                    break;
                case 3:
                    System.out.print("Valor para transferência: ");
                    valor = scanner.nextDouble();
                    System.out.print("Número da conta destinatária: ");
                    int numeroContaDest = scanner.nextInt();
                    ContaBancaria contaDest = gerenciador.obterConta(numeroContaDest);
                    if (contaDest != null) {
                        minhaConta.transferir(contaDest, valor);
                    } else {
                        System.out.println("Conta destinatária não encontrada.");
                    }
                    break;
                case 4:
                    minhaConta.consultarSaldo();
                    break;
                case 5:
                    minhaConta.exibirHistoricoTransacoes();
                    break;
                case 6:
                    System.out.print("Entre com o nome do titular da nova conta: ");
                    String novoNomeTitular = scanner.next();
                    System.out.print("Entre com o número da nova conta: ");
                    int novoNumeroDaConta = scanner.nextInt();
                    System.out.print("Entre com o saldo inicial: ");
                    double novoSaldoInicial = scanner.nextDouble();
                    ContaBancaria novaConta = new ContaBancaria(novoNumeroDaConta, novoSaldoInicial, novoNomeTitular);
                    gerenciador.adicionarConta(novaConta);
                    System.out.println("Conta criada com sucesso!");
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
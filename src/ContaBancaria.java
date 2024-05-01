import java.util.ArrayList;
import java.util.List;

public class ContaBancaria {
    private double saldo;
    private String nomeDoTitular;
    private List<ContaBancaria> contasDestinatarias; 
    private List<String> historicoTransacoes; 

    public ContaBancaria(int numeroDaConta, double saldoInicial, String nomeDoTitular) {
        this.saldo = saldoInicial;
        this.nomeDoTitular = nomeDoTitular;
        this.contasDestinatarias = new ArrayList<>();
        this.historicoTransacoes = new ArrayList<>();
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            historicoTransacoes.add("Depósito: R$ " + String.format("%.2f", valor));
            System.out.println("Depósito realizado com sucesso! Saldo da conta de " + nomeDoTitular + ": R$ "
                    + String.format("%.2f", saldo));
        } else {
            System.out.println("O valor do depósito deve ser positivo.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            historicoTransacoes.add("Saque: R$ " + String.format("%.2f", valor));
            System.out.println("Saque realizado com sucesso! Saldo da conta de " + nomeDoTitular + ": R$ "
                    + String.format("%.2f", saldo));
        } else if (valor <= 0) {
            System.out.println("O valor do saque deve ser positivo.");
        } else {
            System.out.println("Saldo insuficiente para o saque.");
        }
    }

    public void transferir(ContaBancaria destino, double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            destino.depositar(valor);
            contasDestinatarias.add(destino);
            historicoTransacoes
                    .add("Transferência para " + destino.nomeDoTitular + ": R$ " + String.format("%.2f", valor));
            System.out.println("Transferência realizada com sucesso! Saldo da conta de " + nomeDoTitular + ": R$ "
                    + String.format("%.2f", saldo));
        } else if (valor <= 0) {
            System.out.println("O valor da transferência deve ser positivo.");
        } else {
            System.out.println("Saldo insuficiente para transferência.");
        }
    }

    public void consultarSaldo() {
        System.out.println("Saldo da conta de " + nomeDoTitular + ": R$ " + String.format("%.2f", saldo));
    }

    public void exibirHistoricoTransacoes() {
        if (historicoTransacoes.isEmpty()) {
            System.out.println("Não há transações registradas.");
        } else {
            System.out.println("Histórico de transações de " + nomeDoTitular + ":");
            for (String transacao : historicoTransacoes) {
                System.out.println(transacao);
            }
        }
    }
}
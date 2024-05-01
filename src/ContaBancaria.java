import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContaBancaria {
    private int numeroDaConta;
    private double saldo;
    private String nomeDoTitular;
    private List<String> historicoTransacoes;

    public ContaBancaria(int numeroDaConta, double saldoInicial, String nomeDoTitular) {
        this.numeroDaConta = numeroDaConta;
        this.saldo = saldoInicial;
        this.nomeDoTitular = nomeDoTitular;
        this.historicoTransacoes = new ArrayList<>();
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNomeTitular() {
        return nomeDoTitular;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            String transacao = "Depósito próprio: R$ " + String.format("%.2f", valor);
            historicoTransacoes.add(transacao);
            System.out.println(transacao + " realizado com sucesso! Saldo atual: R$ " + String.format("%.2f", saldo));
        } else {
            System.out.println("O valor do depósito deve ser positivo.");
        }
    }

    public void depositar(double valor, String nomeRemetente) {
        if (valor > 0) {
            saldo += valor;
            String transacao = "Depósito de " + nomeRemetente + ": R$ " + String.format("%.2f", valor);
            historicoTransacoes.add(transacao);
            System.out.println(transacao + " realizado com sucesso! Saldo atual: R$ " + String.format("%.2f", saldo));
        } else {
            System.out.println("O valor do depósito deve ser positivo.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            historicoTransacoes.add("Saque: R$ " + String.format("%.2f", valor));
            System.out.println("Saque realizado com sucesso! Saldo atual: R$ " + String.format("%.2f", saldo));
        } else if (valor <= 0) {
            System.out.println("O valor do saque deve ser positivo.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public void transferir(ContaBancaria destino, double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            destino.depositar(valor, this.nomeDoTitular);
            historicoTransacoes
                    .add("Transferência para " + destino.getNomeTitular() + ": R$ " + String.format("%.2f", valor));
            System.out.println("Transferência realizada com sucesso! Saldo atual: R$ " + String.format("%.2f", saldo));
        } else if (valor <= 0) {
            System.out.println("O valor da transferência deve ser positivo.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public void consultarSaldo() {
        System.out.println("Saldo atual: R$ " + String.format("%.2f", saldo));
    }

    public void exibirHistoricoTransacoes() {
        System.out.println("Histórico de transações para " + nomeDoTitular + ":");
        for (String transacao : historicoTransacoes) {
            System.out.println(transacao);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ContaBancaria that = (ContaBancaria) o;
        return numeroDaConta == that.numeroDaConta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroDaConta);
    }
}
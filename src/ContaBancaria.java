public class ContaBancaria {
    private int numeroDaConta;
    private double saldo;
    private String nomeDoTitular;  // Nome do titular da conta

    public ContaBancaria(int numeroDaConta, double saldoInicial, String nomeDoTitular) {
        this.numeroDaConta = numeroDaConta;
        this.saldo = saldoInicial;
        this.nomeDoTitular = nomeDoTitular;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado com sucesso! Saldo da conta de " + nomeDoTitular + ": R$ " + String.format("%.2f", saldo));
        } else {
            System.out.println("O valor do depósito deve ser positivo.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque realizado com sucesso! Saldo da conta de " + nomeDoTitular + ": R$ " + String.format("%.2f", saldo));
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
            System.out.println("Transferência realizada com sucesso! Saldo da conta de " + nomeDoTitular + ": R$ " + String.format("%.2f", saldo));
        } else if (valor <= 0) {
            System.out.println("O valor da transferência deve ser positivo.");
        } else {
            System.out.println("Saldo insuficiente para transferência.");
        }
    }

    public void consultarSaldo() {
        System.out.println("Saldo da conta de " + nomeDoTitular + ": R$ " + String.format("%.2f", saldo));
    }
}
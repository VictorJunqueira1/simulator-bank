import java.util.HashMap;
import java.util.Map;

public class GerenciadorDeContas {
    private Map<Integer, ContaBancaria> contas = new HashMap<>();

    public void adicionarConta(ContaBancaria conta) {
        if (!contaExiste(conta.getNumeroDaConta())) {
            contas.put(conta.getNumeroDaConta(), conta);
        }
    }

    public ContaBancaria obterConta(int numeroDaConta) {
        return contas.get(numeroDaConta);
    }

    public boolean contaExiste(int numeroDaConta) {
        return contas.containsKey(numeroDaConta);
    }

    public void mostrarTodasAsContas() {
        if (contas.isEmpty()) {
            System.out.println("Não há contas cadastradas.");
        } else {
            System.out.println("Contas cadastradas:");
            for (ContaBancaria conta : contas.values()) {
                System.out.printf("Conta Nº %d - Titular: %s - Saldo: R$ %.2f\n",
                        conta.getNumeroDaConta(), conta.getNomeTitular(), conta.getSaldo());
            }
        }
    }
}
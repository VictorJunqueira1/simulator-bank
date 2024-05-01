import java.util.HashMap;
import java.util.Map;

public class GerenciadorDeContas {
    private Map<Integer, ContaBancaria> contas;

    public GerenciadorDeContas() {
        this.contas = new HashMap<>();
    }

    public ContaBancaria obterConta(int numeroDaConta) {
        return contas.get(numeroDaConta);
    }

    public void adicionarConta(ContaBancaria conta) {
        contas.put(conta.getNumeroDaConta(), conta);
    }
}
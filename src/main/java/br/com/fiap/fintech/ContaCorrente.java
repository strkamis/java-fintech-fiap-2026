package br.com.fiap.fintech;

public class ContaCorrente extends Conta {
    private final double limite;

    public ContaCorrente(String numero, Cliente titular, double limite) {
        super(numero, titular);
        if (!Double.isFinite(limite) || limite < 0) {
            throw new IllegalArgumentException("Limite não pode ser negativo");
        }
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    @Override
    public boolean sacar(double valor) {
        validarValor(valor);
        if (consultarSaldo() + limite < valor) {
            return false;
        }
        if (super.sacar(valor)) {
            return true;
        }
        ajustarSaldo(-valor, TipoTransacao.SAQUE, "Saque com uso do limite");
        return true;
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() + String.format(" | Limite: R$ %.2f", limite);
    }
}

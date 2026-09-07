package br.com.fiap.fintech;

public class ContaPoupanca extends Conta {
    private final double taxaRendimento;

    public ContaPoupanca(String numero, Cliente titular, double taxaRendimento) {
        super(numero, titular);
        if (!Double.isFinite(taxaRendimento) || taxaRendimento < 0) {
            throw new IllegalArgumentException("Taxa não pode ser negativa");
        }
        this.taxaRendimento = taxaRendimento;
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public double renderJuros() {
        double rendimento = consultarSaldo() * taxaRendimento / 100;
        if (rendimento > 0) {
            ajustarSaldo(rendimento, TipoTransacao.RENDIMENTO, "Rendimento da poupança");
        }
        return rendimento;
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() + String.format(" | Rendimento: %.2f%%", taxaRendimento);
    }
}

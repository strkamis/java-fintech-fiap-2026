package br.com.fiap.fintech;

public class ContaPoupanca extends Conta {
    private final double taxaRendimento;

    public ContaPoupanca(String numero, Cliente titular, double taxaRendimento) {
        super(numero, titular);
        if (!Double.isFinite(taxaRendimento) || taxaRendimento < 0) {
            throw new IllegalArgumentException("Taxa não pode ser negativa");
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaPoupanca extends Conta {
    private final BigDecimal taxaRendimento;

    public ContaPoupanca(String numero, Cliente titular, BigDecimal saldoInicial,
                         BigDecimal taxaRendimento) {
        super(numero, titular, saldoInicial);
        if (taxaRendimento == null || taxaRendimento.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("A taxa de rendimento não pode ser negativa.");
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
    public BigDecimal getTaxaRendimento() {
        return taxaRendimento;
    }

    @Override
    public BigDecimal calcularRendimento() {
        return getSaldo().multiply(taxaRendimento).setScale(2, RoundingMode.HALF_EVEN);
    }
}

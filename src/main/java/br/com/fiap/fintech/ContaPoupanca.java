package br.com.fiap.fintech;

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

    public BigDecimal getTaxaRendimento() {
        return taxaRendimento;
    }

    @Override
    public BigDecimal calcularRendimento() {
        return getSaldo().multiply(taxaRendimento).setScale(2, RoundingMode.HALF_EVEN);
    }
}

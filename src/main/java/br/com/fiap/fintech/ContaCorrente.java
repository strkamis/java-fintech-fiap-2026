package br.com.fiap.fintech;

import java.math.BigDecimal;

public class ContaCorrente extends Conta {
    private final BigDecimal limite;
    private final BigDecimal tarifa;

    public ContaCorrente(String numero, Cliente titular, BigDecimal saldoInicial,
                         BigDecimal limite, BigDecimal tarifa) {
        super(numero, titular, saldoInicial);
        if (limite == null || limite.compareTo(BigDecimal.ZERO) < 0
                || tarifa == null || tarifa.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Limite e tarifa não podem ser negativos.");
        }
        this.limite = limite;
        this.tarifa = tarifa;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public BigDecimal getTarifa() {
        return tarifa;
    }

    @Override
    public void sacar(BigDecimal valor) {
        validarValor(valor);
        if (getSaldo().add(limite).compareTo(valor.add(tarifa)) < 0) {
            throw new IllegalArgumentException("Saldo e limite insuficientes.");
        }
        debitar(valor.add(tarifa));
    }

    @Override
    public BigDecimal calcularRendimento() {
        return BigDecimal.ZERO;
    }
}

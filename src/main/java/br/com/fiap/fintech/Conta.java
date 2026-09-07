package br.com.fiap.fintech;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Conta {
    private final String numero;
    private final Cliente titular;
    private BigDecimal saldo;

    protected Conta(String numero, Cliente titular, BigDecimal saldoInicial) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("O número da conta é obrigatório.");
        }
        this.numero = numero;
        this.titular = Objects.requireNonNull(titular, "O titular é obrigatório.");
        if (saldoInicial == null || saldoInicial.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O saldo inicial não pode ser negativo.");
        }
        this.saldo = saldoInicial;
    }

    public String getNumero() {
        return numero;
    }

    public Cliente getTitular() {
        return titular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void depositar(BigDecimal valor) {
        validarValor(valor);
        saldo = saldo.add(valor);
    }

    public void sacar(BigDecimal valor) {
        validarValor(valor);
        if (saldo.compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        debitar(valor);
    }

    public void transferir(Conta destino, BigDecimal valor) {
        Objects.requireNonNull(destino, "A conta de destino é obrigatória.");
        sacar(valor);
        destino.depositar(valor);
    }

    public abstract BigDecimal calcularRendimento();

    protected final void debitar(BigDecimal valor) {
        validarValor(valor);
        saldo = saldo.subtract(valor);
    }

    protected final void validarValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
    }
}

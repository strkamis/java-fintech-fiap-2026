package com.fintech.model;

/**
 * Subclasse de {@link Conta} que representa uma conta poupança,
 * cujo rendimento é calculado com base em uma taxa mensal de juros.
 */
public class ContaPoupanca extends Conta {

    private double taxaRendimentoMensal;

    public ContaPoupanca(String agencia, Cliente titular, double taxaRendimentoMensal) {
        super(agencia, titular);
        this.taxaRendimentoMensal = taxaRendimentoMensal;
    }

    public ContaPoupanca(String agencia, Cliente titular, double saldoInicial, double taxaRendimentoMensal) {
        super(agencia, titular, saldoInicial);
        this.taxaRendimentoMensal = taxaRendimentoMensal;
    }

    public double getTaxaRendimentoMensal() {
        return taxaRendimentoMensal;
    }

    public void setTaxaRendimentoMensal(double taxaRendimentoMensal) {
        if (taxaRendimentoMensal < 0) {
            throw new IllegalArgumentException("Taxa não pode ser negativa.");
        }
        this.taxaRendimentoMensal = taxaRendimentoMensal;
    }

    // Sobrescrita: rendimento calculado com base na taxa mensal sobre o saldo.
    @Override
    public double calcularRendimento() {
        return getSaldo() * taxaRendimentoMensal;
    }

    @Override
    public String extrato() {
        return String.format("%s | Taxa de rendimento: %.2f%% a.m.", super.extrato(), taxaRendimentoMensal * 100);
    }
}

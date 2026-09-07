package com.fintech.model;

/**
 * Subclasse de {@link Conta} que representa uma conta corrente,
 * oferecendo um limite de cheque especial e cobrança de tarifa mensal.
 */
public class ContaCorrente extends Conta {

    private double limiteChequeEspecial;
    private static final double TARIFA_MENSAL = 12.90;

    public ContaCorrente(String agencia, Cliente titular, double limiteChequeEspecial) {
        super(agencia, titular);
        this.limiteChequeEspecial = Math.max(limiteChequeEspecial, 0);
    }

    public ContaCorrente(String agencia, Cliente titular, double saldoInicial, double limiteChequeEspecial) {
        super(agencia, titular, saldoInicial);
        this.limiteChequeEspecial = Math.max(limiteChequeEspecial, 0);
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        if (limiteChequeEspecial < 0) {
            throw new IllegalArgumentException("Limite não pode ser negativo.");
        }
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    // Sobrescrita: saldo disponível considera o limite de cheque especial.
    @Override
    public double getSaldoDisponivel() {
        return getSaldo() + limiteChequeEspecial;
    }

    // Conta corrente não rende, mas cobra tarifa mensal (regra própria).
    @Override
    public double calcularRendimento() {
        return -TARIFA_MENSAL;
    }

    @Override
    public String extrato() {
        return String.format("%s | Limite cheque especial: R$ %.2f", super.extrato(), limiteChequeEspecial);
    }
}

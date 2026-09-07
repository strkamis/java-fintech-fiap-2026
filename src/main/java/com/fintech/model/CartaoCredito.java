package com.fintech.model;

/**
 * Classe que representa um cartão de crédito vinculado a um cliente.
 * Demonstra encapsulamento por meio de atributos privados acessados
 * somente via métodos públicos que aplicam as regras de negócio.
 */
public class CartaoCredito {

    private final String numero;
    private final Cliente titular;
    private double limite;
    private double saldoUtilizado;

    public CartaoCredito(String numero, Cliente titular, double limite) {
        this.numero = numero;
        this.titular = titular;
        this.limite = limite;
        this.saldoUtilizado = 0.0;
    }

    public String getNumero() {
        return numero;
    }

    public Cliente getTitular() {
        return titular;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        if (limite < saldoUtilizado) {
            throw new IllegalArgumentException("Novo limite não pode ser menor que o valor já utilizado.");
        }
        this.limite = limite;
    }

    public double getSaldoUtilizado() {
        return saldoUtilizado;
    }

    public double getLimiteDisponivel() {
        return limite - saldoUtilizado;
    }

    /**
     * Realiza uma compra no cartão, desde que haja limite disponível.
     */
    public boolean realizarCompra(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor da compra deve ser positivo.");
        }
        if (valor > getLimiteDisponivel()) {
            return false;
        }
        saldoUtilizado += valor;
        return true;
    }

    /**
     * Paga (total ou parcialmente) a fatura, debitando o valor da conta
     * informada e reduzindo o saldo utilizado do cartão.
     */
    public boolean pagarFatura(Conta contaPagadora, double valor) {
        if (contaPagadora == null) {
            throw new IllegalArgumentException("Conta pagadora inválida.");
        }
        if (valor <= 0 || valor > saldoUtilizado) {
            throw new IllegalArgumentException("Valor de pagamento inválido.");
        }
        if (contaPagadora.sacar(valor)) {
            saldoUtilizado -= valor;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Cartão %s | Titular: %s | Limite: R$ %.2f | Disponível: R$ %.2f",
                numero, titular != null ? titular.getNome() : "N/A", limite, getLimiteDisponivel());
    }
}

package com.fintech.model;

/**
 * Superclasse abstrata que representa uma conta bancária genérica.
 * Concentra o comportamento comum (depósito, saque, extrato) e
 * define um método abstrato ({@link #calcularRendimento()}) que cada
 * subclasse deve implementar de forma própria, caracterizando o
 * polimorfismo do projeto.
 */
public abstract class Conta {

    private static int proximoNumero = 1000;

    private final int numero;
    private final String agencia;
    private double saldo;
    private Cliente titular;

    protected Conta(String agencia, Cliente titular) {
        this.numero = proximoNumero++;
        this.agencia = agencia;
        this.saldo = 0.0;
        if (titular != null) {
            setTitular(titular);
        }
    }

    protected Conta(String agencia, Cliente titular, double saldoInicial) {
        this(agencia, titular);
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial não pode ser negativo.");
        }
        this.saldo = saldoInicial;
    }

    public int getNumero() {
        return numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
        if (titular != null) {
            titular.adicionarConta(this);
        }
    }

    /**
     * Deposita um valor positivo na conta.
     */
    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de depósito deve ser positivo.");
        }
        this.saldo += valor;
    }

    /**
     * Saca um valor da conta, respeitando o saldo disponível
     * (incluindo eventual limite concedido pela subclasse).
     */
    public boolean sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser positivo.");
        }
        if (valor > getSaldoDisponivel()) {
            return false;
        }
        this.saldo -= valor;
        return true;
    }

    /**
     * Saldo disponível para saque. Por padrão é o próprio saldo, mas
     * subclasses como ContaCorrente podem sobrescrever para considerar
     * limites adicionais (polimorfismo).
     */
    public double getSaldoDisponivel() {
        return saldo;
    }

    /**
     * Transfere um valor desta conta para outra conta.
     */
    public boolean transferir(Conta destino, double valor) {
        if (destino == null) {
            throw new IllegalArgumentException("Conta de destino inválida.");
        }
        if (sacar(valor)) {
            destino.depositar(valor);
            return true;
        }
        return false;
    }

    /**
     * Calcula o rendimento da conta. Cada subclasse define sua própria
     * regra de negócio, exemplificando o polimorfismo em tempo de execução.
     */
    public abstract double calcularRendimento();

    /**
     * Aplica o rendimento calculado diretamente sobre o saldo da conta.
     */
    public void aplicarRendimento() {
        this.saldo += calcularRendimento();
    }

    public String extrato() {
        return String.format("Conta %d (Ag. %s) | Titular: %s | Saldo: R$ %.2f",
                numero, agencia, titular != null ? titular.getNome() : "N/A", saldo);
    }

    @Override
    public String toString() {
        return extrato();
    }
}

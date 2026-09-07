package br.com.fiap.fintech;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Conta {
    private final String numero;
    private final Cliente titular;
    private BigDecimal saldo;
    private final List<Transacao> historico = new ArrayList<>();

    public Conta(String numero, Cliente titular) {
        if (numero == null || numero.isBlank() || titular == null) {
            throw new IllegalArgumentException("Número e titular são obrigatórios");
        }
        this.numero = numero;
        this.titular = titular;
        this.saldo = BigDecimal.ZERO;
    }

    public String getNumero() {
        return numero;
    }

    public Cliente getTitular() {
        return titular;
    }

    public double consultarSaldo() {
        return saldo.doubleValue();
    }

    public List<Transacao> getHistorico() {
        return Collections.unmodifiableList(historico);
    }

    public void depositar(double valor) {
        validarValor(valor);
        ajustarSaldo(valor, TipoTransacao.DEPOSITO, "Depósito em conta");
    }

    public boolean sacar(double valor) {
        validarValor(valor);
        BigDecimal quantia = BigDecimal.valueOf(valor);
        if (saldo.compareTo(quantia) < 0) {
            return false;
        }
        ajustarSaldo(-valor, TipoTransacao.SAQUE, "Saque em conta");
        return true;
    }

    public boolean transferir(Conta destino, double valor) {
        if (destino == null || destino == this) {
            throw new IllegalArgumentException("Destino deve ser outra conta");
        }
        if (!sacar(valor)) {
            return false;
        }
        destino.depositar(valor);
        historico.add(new Transacao(TipoTransacao.TRANSFERENCIA, valor,
                "Transferência para " + destino.getNumero()));
        return true;
    }

    public String exibirResumo() {
        return "Conta " + numero + " | Titular: " + titular.getNome()
                + " | Saldo: R$ " + String.format("%.2f", consultarSaldo());
    }

    protected void validarValor(double valor) {
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser positivo");
        }
    }

    protected void ajustarSaldo(double valor, TipoTransacao tipo, String descricao) {
        saldo = saldo.add(BigDecimal.valueOf(valor));
        historico.add(new Transacao(tipo, Math.abs(valor), descricao));
    }
}

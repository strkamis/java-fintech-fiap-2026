package br.com.fiap.fintech;

public class ContaCorrente extends Conta {
    private final double limite;

    public ContaCorrente(String numero, Cliente titular, double limite) {
        super(numero, titular);
        if (!Double.isFinite(limite) || limite < 0) {
            throw new IllegalArgumentException("Limite não pode ser negativo");
        }
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    @Override
    public boolean sacar(double valor) {
        validarValor(valor);
        if (consultarSaldo() + limite < valor) {
            return false;
        }
        if (super.sacar(valor)) {
            return true;
        }
        ajustarSaldo(-valor, TipoTransacao.SAQUE, "Saque com uso do limite");
        return true;
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() + String.format(" | Limite: R$ %.2f", limite);
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

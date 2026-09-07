package br.com.fiap.fintech;

import java.math.BigDecimal;
import java.util.List;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente("Ana Souza", "123.456.789-00");
        ContaCorrente corrente = new ContaCorrente(
                "001", cliente, new BigDecimal("1000.00"),
                new BigDecimal("500.00"), new BigDecimal("5.00"));
        ContaPoupanca poupanca = new ContaPoupanca(
                "002", cliente, new BigDecimal("500.00"), new BigDecimal("0.01"));

        cliente.adicionarConta(corrente);
        cliente.adicionarConta(poupanca);
        corrente.transferir(poupanca, new BigDecimal("100.00"));
        poupanca.depositar(new BigDecimal("50.00"));

        List<Conta> contas = cliente.getContas();
        for (Conta conta : contas) {
            System.out.printf("Conta %s: saldo R$ %s, rendimento R$ %s%n",
                    conta.getNumero(), conta.getSaldo(), conta.calcularRendimento());
        }
    }
}

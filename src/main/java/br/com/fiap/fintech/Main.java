package br.com.fiap.fintech;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Ana Souza", "123.456.789-00", "ana@fiap.com.br");
        Conta contaCorrente = new ContaCorrente("001", cliente, 500.00);
        Conta contaPoupanca = new ContaPoupanca("002", cliente, 1.0);

        contaCorrente.depositar(1000.00);
        contaCorrente.transferir(contaPoupanca, 250.00);
        ((ContaPoupanca) contaPoupanca).renderJuros();

        System.out.println(contaCorrente.exibirResumo());
        System.out.println(contaPoupanca.exibirResumo());
        System.out.println("Transações na conta corrente: " + contaCorrente.getHistorico().size());
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

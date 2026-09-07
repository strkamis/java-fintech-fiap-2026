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
    }
}

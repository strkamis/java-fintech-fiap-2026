package com.fintech;

import com.fintech.model.CartaoCredito;
import com.fintech.model.Cliente;
import com.fintech.model.Conta;
import com.fintech.model.ContaCorrente;
import com.fintech.model.ContaPoupanca;

/**
 * Classe de execução (teste) do projeto Fintech.
 * Instancia objetos das classes do domínio, atribui valores aos seus
 * atributos e invoca seus métodos, simulando o funcionamento real do
 * sistema e demonstrando herança e polimorfismo.
 */
public class Main {

    public static void main(String[] args) {
        // Instanciando um Cliente
        Cliente cliente = new Cliente("Maria Silva", "123.456.789-00", "maria.silva@email.com", "(11) 99999-0000");

        // Instanciando as duas contas (subclasses de Conta), associadas ao cliente
        Conta contaCorrente = new ContaCorrente("0001", cliente, 500.0, 1000.0);
        Conta contaPoupanca = new ContaPoupanca("0001", cliente, 2000.0, 0.005);

        System.out.println("=== Cadastro do cliente ===");
        System.out.println(cliente.descricao());

        System.out.println("\n=== Contas do cliente ===");
        for (Conta conta : cliente.getContas()) {
            System.out.println(conta);
        }

        System.out.println("\n=== Operações bancárias ===");
        contaCorrente.depositar(300.0);
        System.out.println("Depósito de R$ 300,00 na conta corrente. " + contaCorrente);

        boolean saqueRealizado = contaCorrente.sacar(1200.0);
        System.out.println("Saque de R$ 1200,00 na conta corrente (usa limite): " + saqueRealizado);
        System.out.println(contaCorrente);

        boolean transferencia = contaPoupanca.transferir(contaCorrente, 500.0);
        System.out.println("Transferência de R$ 500,00 da poupança para a corrente: " + transferencia);
        System.out.println(contaPoupanca);
        System.out.println(contaCorrente);

        System.out.println("\n=== Polimorfismo: cálculo de rendimento de cada tipo de conta ===");
        // O mesmo tipo de referência (Conta) chama calcularRendimento(),
        // mas o comportamento executado depende do tipo real do objeto.
        for (Conta conta : cliente.getContas()) {
            System.out.printf("%s -> Rendimento calculado: R$ %.2f%n",
                    conta.getClass().getSimpleName(), conta.calcularRendimento());
        }

        contaPoupanca.aplicarRendimento();
        contaCorrente.aplicarRendimento();
        System.out.println("\n=== Saldos após aplicação do rendimento/tarifa ===");
        System.out.println(contaPoupanca);
        System.out.println(contaCorrente);

        System.out.println("\nPatrimônio total do cliente: R$ " + String.format("%.2f", cliente.patrimonioTotal()));

        // Instanciando um Cartão de Crédito para o mesmo cliente
        System.out.println("\n=== Operações no cartão de crédito ===");
        CartaoCredito cartao = new CartaoCredito("**** **** **** 1234", cliente, 3000.0);
        System.out.println(cartao);

        boolean compraAprovada = cartao.realizarCompra(450.0);
        System.out.println("Compra de R$ 450,00 aprovada? " + compraAprovada);
        System.out.println(cartao);

        boolean pagamentoRealizado = cartao.pagarFatura(contaCorrente, 200.0);
        System.out.println("Pagamento de R$ 200,00 da fatura com a conta corrente: " + pagamentoRealizado);
        System.out.println(cartao);
        System.out.println(contaCorrente);
    }
}

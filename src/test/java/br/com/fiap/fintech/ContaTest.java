package br.com.fiap.fintech;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaTest {
    private final Cliente cliente = new Cliente("Ana", "123");

    @Test
    void correnteUsaLimiteEPolimorfismoMantemSaldoNegativo() {
        Conta conta = new ContaCorrente("1", cliente, 100);
        conta.depositar(50);

        assertTrue(conta.sacar(120));
        assertEquals(-70, conta.consultarSaldo(), 0.001);
        assertTrue(conta.exibirResumo().contains("Limite"));
    }

    @Test
    void poupancaCalculaRendimento() {
        ContaPoupanca conta = new ContaPoupanca("2", cliente, 10);
        conta.depositar(200);

        assertEquals(20, conta.renderJuros(), 0.001);
        assertEquals(220, conta.consultarSaldo(), 0.001);
    }

    @Test
    void transferenciaInterageComAsContas() {
        Conta origem = new Conta("1", cliente);
        Conta destino = new Conta("2", cliente);
        origem.depositar(100);

        assertTrue(origem.transferir(destino, 40));
        assertEquals(60, origem.consultarSaldo(), 0.001);
        assertEquals(40, destino.consultarSaldo(), 0.001);
    }
}

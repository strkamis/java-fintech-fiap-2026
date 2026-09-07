package com.fintech.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Subclasse de {@link Pessoa} que representa um cliente da Fintech.
 * Demonstra herança (estende Pessoa) e adiciona atributos e
 * comportamentos próprios de um cliente, como o relacionamento com
 * suas contas bancárias.
 */
public class Cliente extends Pessoa {

    private String email;
    private String telefone;
    private final List<Conta> contas;

    public Cliente(String nome, String cpf, String email, String telefone) {
        super(nome, cpf);
        this.email = email;
        this.telefone = telefone;
        this.contas = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Conta> getContas() {
        return Collections.unmodifiableList(contas);
    }

    /**
     * Associa uma conta a este cliente, garantindo que o vínculo
     * também seja estabelecido do lado da conta.
     */
    public void adicionarConta(Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta não pode ser nula.");
        }
        if (!contas.contains(conta)) {
            contas.add(conta);
            if (conta.getTitular() != this) {
                conta.setTitular(this);
            }
        }
    }

    /**
     * Soma o saldo de todas as contas do cliente.
     */
    public double patrimonioTotal() {
        double total = 0;
        for (Conta conta : contas) {
            total += conta.getSaldo();
        }
        return total;
    }

    // Sobrescrita (polimorfismo) do método descricao() da superclasse Pessoa.
    @Override
    public String descricao() {
        return String.format("%s | E-mail: %s | Telefone: %s | Qtd. contas: %d",
                super.descricao(), email, telefone, contas.size());
    }
}

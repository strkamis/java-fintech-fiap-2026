package com.fintech.model;

/**
 * Superclasse que representa uma pessoa cadastrada no sistema Fintech.
 * Concentra os dados e comportamentos comuns a qualquer tipo de pessoa,
 * permitindo que subclasses (como {@link Cliente}) reaproveitem essa
 * estrutura por meio de herança.
 */
public abstract class Pessoa {

    private String nome;
    private String cpf;

    protected Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser vazio.");
        }
        this.cpf = cpf;
    }

    /**
     * Método que descreve a pessoa. Pode ser sobrescrito (polimorfismo)
     * pelas subclasses para agregar informações específicas.
     */
    public String descricao() {
        return String.format("Nome: %s | CPF: %s", nome, cpf);
    }

    @Override
    public String toString() {
        return descricao();
    }
}

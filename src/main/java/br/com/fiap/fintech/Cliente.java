package br.com.fiap.fintech;

import java.util.Objects;

public class Cliente {
    private String nome;
    private final String cpf;
    private String email;

    public Cliente(String nome, String cpf) {
        this(nome, cpf, null);
    }

    public Cliente(String nome, String cpf, String email) {
        setNome(nome);
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF é obrigatório");
        }
        this.cpf = cpf;
        setEmail(email);
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cliente {
    private final String nome;
    private final String documento;
    private final List<Conta> contas = new ArrayList<>();

    public Cliente(String nome, String documento) {
        if (nome == null || nome.isBlank() || documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("Nome e documento são obrigatórios.");
        }
        this.nome = nome;
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && !email.isBlank() && !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido");
        }
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cliente cliente)) return false;
        return cpf.equals(cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    public String getDocumento() {
        return documento;
    }

    public List<Conta> getContas() {
        return Collections.unmodifiableList(contas);
    }

    public void adicionarConta(Conta conta) {
        Objects.requireNonNull(conta, "A conta é obrigatória.");
        if (conta.getTitular() != this) {
            throw new IllegalArgumentException("A conta não pertence a este cliente.");
        }
        contas.add(conta);
    }
}

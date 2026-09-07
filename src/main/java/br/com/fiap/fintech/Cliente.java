package br.com.fiap.fintech;

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

package br.com.fiap.fintech;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transacao {
    private final TipoTransacao tipo;
    private final BigDecimal valor;
    private final LocalDateTime dataHora;
    private final String descricao;

    public Transacao(TipoTransacao tipo, double valor, String descricao) {
        if (tipo == null || valor <= 0) {
            throw new IllegalArgumentException("Tipo e valor positivo são obrigatórios");
        }
        this.tipo = tipo;
        this.valor = BigDecimal.valueOf(valor);
        this.dataHora = LocalDateTime.now();
        this.descricao = descricao == null ? "" : descricao;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getDescricao() {
        return descricao;
    }
}

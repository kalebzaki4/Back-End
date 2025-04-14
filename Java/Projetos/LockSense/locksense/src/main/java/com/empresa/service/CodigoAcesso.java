package com.empresa;

import java.time.LocalDateTime;

public class CodigoAcesso {
    private int codigo;
    private LocalDateTime validade;

    public CodigoAcesso() {
    }

    public CodigoAcesso(int codigo, LocalDateTime validade) {
        this.codigo = codigo;
        this.validade = validade;
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDateTime getValidade() {
        return validade;
    }
}

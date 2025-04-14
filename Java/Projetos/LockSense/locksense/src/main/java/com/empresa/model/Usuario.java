package com.empresa;

import java.time.LocalDateTime;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private CodigoAcesso codigoAcesso;

    public Usuario() {}

    public Usuario(String nome, String email, String senha, CodigoAcesso codigoAcesso) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.codigoAcesso = codigoAcesso;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public CodigoAcesso getCodigoAcesso() {
        return codigoAcesso;
    }

    public void setCodigoAcesso(CodigoAcesso codigoAcesso) {
        this.codigoAcesso = codigoAcesso;
    }
}

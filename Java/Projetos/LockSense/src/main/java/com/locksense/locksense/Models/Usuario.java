package com.locksense.locksense.Models;

public class Usuario {
    private String gmail;
    private String nome;
    private String senha;

    public Usuario(String gmail, String nome, String senha) {
        this.gmail = gmail;
        this.nome = nome;
        this.senha = senha;
    }

    public String getGmail() {
        return gmail;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
}

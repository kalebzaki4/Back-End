package com.empresa;

import java.util.ArrayList;
import java.util.List;

public final class dadosSeguranca {

    private final String nome;
    private final String numero;
    private final String codigoSeguranca;
    private final String cpf;
    private final String rg;

    private dadosSeguranca(String nome, String numero, String codigoSeguranca, String cpf, String rg) {
        this.nome = nome;
        this.numero = numero;
        this.codigoSeguranca = codigoSeguranca;
        this.cpf = cpf;
        this.rg = rg;
    }

    // Método estático para criar instâncias (fábrica)
    public static dadosSeguranca criarInscricao(String nome, String numero, String codigoSeguranca, String cpf, String rg) {
        return new dadosSeguranca(nome, numero, codigoSeguranca, cpf, rg);
    }

    // Getters para acessar os dados (sem setters para evitar modificação)
    public String getNome() {
        return nome;
    }

    public String getNumero() {
        return numero;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "inscricao{"
                + "nome='" + nome + '\''
                + ", numero='" + numero + '\''
                + ", codigoSeguranca='" + codigoSeguranca + '\''
                + ", cpf='" + cpf + '\''
                + ", rg='" + rg + '\''
                + '}';
    }

    // Método para criar uma lista de inscrições fixas
    public static List<dadosSeguranca> criarListaInscricoes() {
        List<dadosSeguranca> inscricoes = new ArrayList<>();

        // Adicionando dados fixos de várias pessoas
        inscricoes.add(criarInscricao(
                "Kaleb", // Nome
                "(11) 98765-4321", // Número de telefone
                "123456", // Código de segurança
                "123.456.789-01", // CPF
                "12.345.678-9" // RG
        ));

        inscricoes.add(criarInscricao(
                "Maria", // Nome
                "(21) 99876-5432", // Número de telefone
                "654321", // Código de segurança
                "987.654.321-00", // CPF
                "98.765.432-1" // RG
        ));

        inscricoes.add(criarInscricao(
                "João", // Nome
                "(31) 98765-1234", // Número de telefone
                "456789", // Código de segurança
                "456.789.123-45", // CPF
                "45.678.912-3" // RG
        ));

        inscricoes.add(criarInscricao(
                "Ana", // Nome
                "(41) 98765-6789", // Número de telefone
                "789123", // Código de segurança
                "321.654.987-00", // CPF
                "32.165.498-7" // RG
        ));

        inscricoes.add(criarInscricao(
                "Pedro", // Nome
                "(51) 98765-9876", // Número de telefone
                "321654", // Código de segurança
                "654.321.987-11", // CPF
                "65.432.198-7" // RG
        ));

        return inscricoes;
    }
}
package com.empresa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Random;

class Usuario {
    private String nome;
    private String email;
    private String senhaHash;
    private String salt;
    private String codigo;
    private LocalDate validadeCodigo;

    // Construtor que inicializa o usuário com nome, e-mail e senha
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.salt = gerarSalt();
        this.senhaHash = hashSenha(senha, this.salt);
        this.gerarCodigo();
    }

    // Gera um salt aleatório de 16 bytes e codifica em Base64
    private String gerarSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Gera o hash da senha utilizando o salt
    private String hashSenha(String senha, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((senha + salt).getBytes());
            return Base64.getEncoder().encodeToString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }

    // Valida a senha comparando o hash da senha fornecida com o hash armazenado
    public boolean validarSenha(String senha) {
        return this.senhaHash.equals(hashSenha(senha, this.salt));
    }

    // Gera um código aleatório de 6 dígitos válido por 6 meses
    public void gerarCodigo() {
        Random random = new Random();
        this.codigo = String.format("%06d", random.nextInt(1000000));
        this.validadeCodigo = LocalDate.now().plusMonths(6);
    }

    // Valida o código verificando a sua correspondência e validade
    public boolean validarCodigo(String codigo) {
        return this.codigo.equals(codigo) && LocalDate.now().isBefore(this.validadeCodigo);
    }

    // Métodos de acesso
    public String getEmail() {
        return email;
    }

    public String getCodigo() {
        return codigo;
    }
}

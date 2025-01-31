package com.empresa;

import java.util.ArrayList;
import java.util.List;

public class SistemaCadastro {
    // Lista de usuários cadastrados
    private List<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        SistemaCadastro sistema = new SistemaCadastro();

        // Cadastrando usuários
        sistema.cadastrarUsuario("kaleb", "senha123");
        sistema.cadastrarUsuario("maria", "1234senha");

        // Verificando credenciais
        boolean autenticado = sistema.verificarCredenciais("kaleb", "senha123");
        System.out.println("Autenticação: " + (autenticado ? "Bem-vindo!" : "Falha na autenticação."));
    }

    // Método para cadastrar um novo usuário
    public void cadastrarUsuario(String nome, String senha) {
        // Criptografando a senha
        String senhaHash = criptografarSenha(senha);

        // Criando o objeto de usuário e adicionando na lista
        Usuario usuario = new Usuario(nome, senhaHash);
        usuarios.add(usuario);
        System.out.println("Usuário " + nome + " cadastrado com sucesso.");
    }

    // Método para verificar se as credenciais de login estão corretas
    public boolean verificarCredenciais(String nome, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nome) && usuario.getSenhaHash().equals(criptografarSenha(senha))) {
                return true;
            }
        }
        return false;
    }

    // Método para criptografar a senha (simulação simples)
    private String criptografarSenha(String senha) {
        return senha + "_hash"; // Apenas um exemplo simples, normalmente você usaria uma biblioteca como bcrypt
    }

    // Classe Usuario representando o usuário no sistema
    public class Usuario {
        private String nome;
        private String senhaHash;

        // Construtor
        public Usuario(String nome, String senhaHash) {
            this.nome = nome;
            this.senhaHash = senhaHash;
        }

        // Getter para nome
        public String getNome() {
            return nome;
        }

        // Getter para senhaHash
        public String getSenhaHash() {
            return senhaHash;
        }
    }
}

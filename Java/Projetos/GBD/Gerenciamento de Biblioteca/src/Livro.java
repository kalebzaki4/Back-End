class Livro {
    private String nome;
    private String autor;
    private String anoPublicacao;
    private String disponibilidade;
    private String emprestadoPara;

    public Livro(String nome, String autor, String anoPublicacao, String disponibilidade) {
        this.nome = nome;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.disponibilidade = disponibilidade;
        this.emprestadoPara = null;
    }

    public String getNome() {
        return nome;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getEmprestadoPara() {
        return emprestadoPara;
    }

    public void setEmprestadoPara(String emprestadoPara) {
        this.emprestadoPara = emprestadoPara;
    }

    @Override
    public String toString() {
        return "Filme: " + nome +
                ", Diretor: " + autor +
                ", Ano: " + anoPublicacao +
                ", Disponibilidade: " + disponibilidade +
                (emprestadoPara != null ? ", Emprestado para: " + emprestadoPara : "");
    }
}
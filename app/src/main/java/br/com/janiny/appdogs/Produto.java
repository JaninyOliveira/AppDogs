package br.com.janiny.appdogs;

public class Produto {
    public int id;
    public String nome, categoria, castracao;

    public Produto() {
    }

    public Produto(int id, String nome, String categoria, String castracao) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.castracao = castracao;
    }

    public Produto(String nome, String categoria, String castracao) {
        this.nome = nome;
        this.categoria = categoria;
        this.castracao = castracao;
    }

    public Produto(String lista_vazia, String s) {
    }


    @Override
    public String toString() {
        return "Pet{" +
                nome + "  |  "+ categoria + "  |  " + castracao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCastracao() {
        return castracao;
    }

    public void setCastracao(String castracao) {
        this.castracao = castracao;
    }
}

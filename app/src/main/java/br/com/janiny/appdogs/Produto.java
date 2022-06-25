package br.com.janiny.appdogs;

public class Produto {
    public int id;
    public String nome, categoria, castracao, raca, porte, idade, ONG, contato, observacoes;
    public byte[] foto;

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Produto() {
    }

    public Produto(int id, String nome, String categoria, String castracao, String raca,
                   String porte, String idade, String ONG, String contato, String observacoes,
                   byte[] foto) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.castracao = castracao;
        this.raca = raca;
        this.porte = porte;
        this.idade = idade;
        this.ONG = ONG;
        this.contato = contato;
        this.observacoes = observacoes;
        this.foto = foto;
    }

    public Produto(byte[] foto,String nome,String porte, String idade) {
        this.foto = foto;
        this.nome = nome;
        this.porte = porte;
        this.idade = idade;
    }

    public Produto(String lista_vazia, String s) {
    }


    @Override
    public String toString() {
        return "Pet{" +
                getFoto() + "  |  "+ categoria + "  |  " + castracao;
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

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getONG() {
        return ONG;
    }

    public void setONG(String ONG) {
        this.ONG = ONG;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}

package model;

import java.math.BigDecimal;

/**
 *
 * @author ALEFF
 */
public class Servico {

    private int id;
    private String nome;
    private Double valor;
    private String descricao;
    private int porcentagem;

    public Servico() {
    }

    //CONSTRUTOR PARA INSERIR NO BANCO DE DADOS!
    public Servico(String nome, Double valor, String descricao, int porcentagem) {
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.porcentagem = porcentagem;
    }

    //CONSTRUTOR PARA BUSCAR NO BANCO DE DADOS!
    public Servico(int id, String nome, Double valor, String descricao, int porcentagem) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.porcentagem = porcentagem;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Serviço: " + nome;
    }

//    public Servico(String nome, Double valor, String descricao, int porcentagem) {
//        this.nome = nome;
//        this.valor = valor;
//        this.descricao = descricao;
//        this.porcentagem = porcentagem;
//    }
//
//    public Servico(int id, String nome, Double valor, String descricao, int porcentagem) {
//        this.id = id;
//        this.nome = nome;
//        this.valor = valor;
//        this.descricao = descricao;
//        this.porcentagem = porcentagem;
//    }
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }

}

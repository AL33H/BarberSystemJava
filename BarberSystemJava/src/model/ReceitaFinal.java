/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ALEFF
 */
public class ReceitaFinal {
    private String mes;
    private String nome;
    private int contagem;
    private Double valor;

    public ReceitaFinal(String nome, int contagem, Double valor) {
        this.nome = nome;
        this.contagem = contagem;
        this.valor = valor;
    }

    public ReceitaFinal(String mes, String nome, int contagem, Double valor) {
        this.mes = mes;
        this.nome = nome;
        this.contagem = contagem;
        this.valor = valor;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getContagem() {
        return contagem;
    }

    public void setContagem(int contagem) {
        this.contagem = contagem;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
    
    
    
}

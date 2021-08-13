/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.Month;
import javax.swing.JOptionPane;

/**
 *
 * @author ALEFF
 */
public class Receita {

    private int id;
    private Funcionario funcionario;
    private Agendamento agendamento;
    private int metododepagamento;
    private Double lucrofuncionario;
    private Double lucroestabelecimento;
    private LocalDate data;

//LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(0, 4)), Integer.parseInt(data.substring(5, 7)), Integer.parseInt(data.substring(8, 10)));
    public Receita() {
    }

    //CONSTRUTOR PARA INSERIR NO BANCO DE DADOS!
    public Receita(Funcionario funcionario, Agendamento agendamento, int metododepagamento, Double lucrofuncionario, Double lucroestabelecimento, String data) {

        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(0, 4)), Integer.parseInt(data.substring(5, 7)), Integer.parseInt(data.substring(8, 10)));

        this.funcionario = funcionario;
        this.agendamento = agendamento;
        this.metododepagamento = metododepagamento;
        this.lucrofuncionario = lucrofuncionario;
        this.lucroestabelecimento = lucroestabelecimento;
        this.data = datafinal;
    }

    //CONSTRUTOR PARA BUSCAR DO BANCO DE DADOS!
    public Receita(int id, Funcionario funcionario, Agendamento agendamento, int metododepagamento, Double lucrofuncionario, Double lucroestabelecimento, String data) {
        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(0, 4)), Integer.parseInt(data.substring(5, 7)), Integer.parseInt(data.substring(8, 10)));

        this.id = id;
        this.funcionario = funcionario;
        this.agendamento = agendamento;
        this.metododepagamento = metododepagamento;
        this.lucrofuncionario = lucrofuncionario;
        this.lucroestabelecimento = lucroestabelecimento;
        this.data = datafinal;
    }

    public int getId() {
        return id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public int getMetododepagamento() {
        return metododepagamento;
    }

    public void setMetododepagamento(int metododepagamento) {
        this.metododepagamento = metododepagamento;
    }

    public Double getLucrofuncionario() {
        return lucrofuncionario;
    }

    public void setLucrofuncionario(Double lucrofuncionario) {
        this.lucrofuncionario = lucrofuncionario;
    }

    public Double getLucroestabelecimento() {
        return lucroestabelecimento;
    }

    public void setLucroestabelecimento(Double lucroestabelecimento) {
        this.lucroestabelecimento = lucroestabelecimento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
    

}

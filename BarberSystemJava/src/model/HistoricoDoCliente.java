/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author ALEFF
 */
public class HistoricoDoCliente {

    private int id_cliente;
    private String servico;
    private String funcionario;
    private Double valor;
    private LocalDate data;
    private int id_agendamento;

    public HistoricoDoCliente() {
    }

    //CONSTRUTOR PARA INSERIR NO BANCO DE DADOS!
    public HistoricoDoCliente(String servico, String funcionario, double valor, String data) {

        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(0, 4)), Integer.parseInt(data.substring(5, 7)), Integer.parseInt(data.substring(8, 10)));

        this.servico = servico;
        this.funcionario = funcionario;
        this.valor = valor;
        this.data = datafinal;
        this.id_agendamento = id_agendamento;
    }

    //CONSTRUTOR PARA BUSCAR NO BANCO DE DADOS!
    public HistoricoDoCliente(int id_cliente, String servico, String funcionario, Double valor, String data, int id_agendamento) {

        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(0, 4)), Integer.parseInt(data.substring(5, 7)), Integer.parseInt(data.substring(8, 10)));

        this.id_cliente = id_cliente;
        this.servico = servico;
        this.funcionario = funcionario;
        this.valor = valor;
        this.data = datafinal;
        this.id_agendamento = id_agendamento;

    }

//    public HistoricoDoCliente(String servico, String funcionario, Double valor, String data) {
//        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));
//
//        this.servico = servico;
//        this.funcionario = funcionario;
//        this.valor = valor;
//        this.data = datafinal;
//    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getId_agendamento() {
        return id_agendamento;
    }

    public void setId_agendamento(int id_agendamento) {
        this.id_agendamento = id_agendamento;
    }

}

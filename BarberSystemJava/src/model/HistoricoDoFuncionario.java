package model;

import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author ALEFF
 */
public class HistoricoDoFuncionario {

    private int id_funcionario;
    private String servico;
    private String cliente;
    private Double valor;
    private LocalDate data;
    private int id_agendamento;

    public HistoricoDoFuncionario() {
    }

    //CONSTRUTOR PARA INSERIR NO BANCO DE DADOS!
    public HistoricoDoFuncionario(String servico, String cliente, double valor, String data) {

        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(0, 4)), Integer.parseInt(data.substring(5, 7)), Integer.parseInt(data.substring(8, 10)));

        this.servico = servico;
        this.cliente = cliente;
        this.valor = valor;
        this.data = datafinal;
        this.id_agendamento = id_agendamento;
    }

//CONSTRUTOR PARA BUSCAR NO BANCO DE DADOS!
    public HistoricoDoFuncionario(int id_funcionario, String servico, String cliente, Double valor, String data, int id_agendamento) {

        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(0, 4)), Integer.parseInt(data.substring(5, 7)), Integer.parseInt(data.substring(8, 10)));

        this.id_funcionario = id_funcionario;
        this.servico = servico;
        this.cliente = cliente;
        this.valor = valor;
        this.data = datafinal;
        this.id_agendamento = id_agendamento;

    }

//    public HistoricoDoFuncionario(String servico, String cliente, Double valor, String data) {
//        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));
//
//        this.servico = servico;
//        this.cliente = cliente;
//        this.valor = valor;
//        this.data = datafinal;
//    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
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

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

}

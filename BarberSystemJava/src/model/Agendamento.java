package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ALEFF
 */
public class Agendamento {

    private int id;
    private Cliente cliente;
    private Servico servico;
    private Funcionario funcionario;
    private LocalDate data;
    private LocalTime hora;
    private Double valor;
    private int status;

    public Agendamento() {
    }

    public Agendamento(int id) {
        this.id = id;
    }

    
    
    //CONSTRUTOR PARA INSERIR NO BANCO DE DADOS!
    public Agendamento(Cliente cliente, Servico servico, Funcionario funcionario, Double valor, String data, String hora, int status) {

        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));

        LocalTime horafinal = LocalTime.of(Integer.parseInt(hora.substring(0, 2)), Integer.parseInt(hora.substring(3, 5)), Integer.parseInt("00"));

        this.cliente = cliente;
        this.servico = servico;
        this.funcionario = funcionario;
        this.data = datafinal;
        this.hora = horafinal;
        this.valor = valor;
        this.status = status;
    }

    //CONSTRUTOR PARA BUSCAR NO BANCO DE DADOS!
    public Agendamento(int id, Cliente cliente, Servico servico, Funcionario funcionario, Double valor, String data, String hora, int status) {

        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(0, 4)), Integer.parseInt(data.substring(5, 7)), Integer.parseInt(data.substring(8, 10)));

        LocalTime horafinal = LocalTime.of(Integer.parseInt(hora.substring(0, 2)), Integer.parseInt(hora.substring(3, 5)), Integer.parseInt(hora.substring(6, 8)));

        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.funcionario = funcionario;
        this.data = datafinal;
        this.hora = horafinal;
        this.valor = valor;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setData(String data) {

        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(0, 4)), Integer.parseInt(data.substring(5, 7)), Integer.parseInt(data.substring(8, 10)));

        this.data = datafinal;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {

        this.hora = hora;
    }

    public void setHora(String hora) {
        LocalTime horafinal = LocalTime.of(Integer.parseInt(hora.substring(0, 2)), Integer.parseInt(hora.substring(3, 5)), Integer.parseInt("00"));

        this.hora = horafinal;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}

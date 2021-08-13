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
public class Cliente {

    private int id;
    private String nome;
    private String sobrenome;
    private String sexo;
    private LocalDate dataNascimento;
    private String CPF;
    private String telefone;
    private String rua;
    private int numeroDaCasa;
    private String bairro;
    private String complemento;
    private String cidade;
    private String estado;

    public Cliente() {
    }
    
//CONSTRUTOR PARA BUSCAR DO BANCO DE DADOS
    public Cliente(int id, String nome, String sobrenome, String sexo, String dataNascimento, String CPF, String telefone, String rua, int numeroDaCasa, String bairro, String complemento, String cidade, String estado) {
        
        LocalDate datafinal = LocalDate.of(Integer.parseInt(dataNascimento.substring(0, 4)), Integer.parseInt(dataNascimento.substring(5, 7)), Integer.parseInt(dataNascimento.substring(8, 10)));

        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.dataNascimento = datafinal;
        this.CPF = CPF;
        this.telefone = telefone;
        this.rua = rua;
        this.numeroDaCasa = numeroDaCasa;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
    }

    //CONSTRUTOR PARA INSERIR NO BANCO DE DADOS:
    public Cliente(String nome, String sobrenome, String sexo, String dataNascimento, String CPF, String telefone, String rua, int numeroDaCasa, String bairro, String complemento, String cidade, String estado) {
       
        LocalDate datafinal = LocalDate.of(Integer.parseInt(dataNascimento.substring(6, 10)), Integer.parseInt(dataNascimento.substring(3, 5)), Integer.parseInt(dataNascimento.substring(0, 2)));

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.dataNascimento = datafinal;
        this.CPF = CPF;
        this.telefone = telefone;
        this.rua = rua;
        this.numeroDaCasa = numeroDaCasa;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
    }
    
    
    public Cliente(String nome) {
        this.nome = nome;
    }

//    public Cliente(String whatsapp, String rua, int numeroDaCasa, String bairro, String complemento, String cidade, String estado, String nome, String sobrenome, String sexo, String dataNascimento, String CPF) {
//        this.nome = nome;
//        this.sobrenome = sobrenome;
//        this.sexo = sexo;
//        this.dataNascimento = dataNascimento;
//        this.CPF = CPF;
//        this.telefone = telefone;
//        this.rua = rua;
//        this.numeroDaCasa = numeroDaCasa;
//        this.bairro = bairro;
//        this.complemento = complemento;
//        this.cidade = cidade;
//        this.estado = estado;
//    }
//
//    public Cliente(int id, String whatsapp, String rua, int numeroDaCasa, String bairro, String cidade, String estado, String nome, String sobrenome, String sexo, String CPF) {
//        super(nome, sobrenome, sexo, CPF);
//        this.id = id;
//        this.whatsapp = whatsapp;
//        this.rua = rua;
//        this.numeroDaCasa = numeroDaCasa;
//        this.bairro = bairro;
//        this.cidade = cidade;
//        this.estado = estado;
//    }
//

//
//    public Cliente(int id, String nome, String sobrenome, String sexo, Date datanascimento, String CPF, String whatsapp, int numero, String rua, String bairro, String complemento, String cidade, String estado) {
//        super(nome, sobrenome, sexo, CPF);
//        this.id = id;
//        this.whatsapp = whatsapp;
//        this.rua = rua;
//        this.numeroDaCasa = numero;
//        this.bairro = bairro;
//        this.cidade = cidade;
//        this.estado = estado;
//    }
//
//    public Cliente(int id, String nome, String sobrenome, String sexo, Date dataNascimento, String CPF,String whatsapp, String rua, int numeroDaCasa, String bairro, String complemento, String cidade, String estado) {
//        super(nome, sobrenome, sexo, dataNascimento, CPF);
//        this.id = id;
//        this.whatsapp = whatsapp;
//        this.rua = rua;
//        this.numeroDaCasa = numeroDaCasa;
//        this.bairro = bairro;
//        this.complemento = complemento;
//        this.cidade = cidade;
//        this.estado = estado;
//    }
    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " " + sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumeroDaCasa() {
        return numeroDaCasa;
    }

    public void setNumeroDaCasa(int numeroDaCasa) {
        this.numeroDaCasa = numeroDaCasa;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author ALEFF
 */
public class Estabelecimento {

    private String nome;
    private LocalDate data;
    private String versao;

    public Estabelecimento() {
    }

    public Estabelecimento(String nome, String data, String versao) {

        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(0, 4)), Integer.parseInt(data.substring(5, 7)), Integer.parseInt(data.substring(8, 10)));

        this.nome = nome;
        this.data = datafinal;
        this.versao = versao;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getData() {
        return data;
    }

    public String getVersao() {
        return versao;
    }

}

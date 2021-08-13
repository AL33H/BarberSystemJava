package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ALEFF
 */
public class Despesa {

    private int id;
    private String nome;
    private String descricao;
    private Double valor;
    private LocalDate data;

    public Despesa() {
    }

    public Despesa(String nome, String descricao, Double valor, String data) {
        
        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));

        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.data = datafinal;
    }

    public Despesa(int id, String nome, String descricao, Double valor, String data) {
        
        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(0, 4)), Integer.parseInt(data.substring(5, 7)), Integer.parseInt(data.substring(8, 10)));

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.data = datafinal;
    }
    
    public Despesa(String nome, String descricao, Double valor, LocalDate data) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

}

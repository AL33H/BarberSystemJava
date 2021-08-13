package model;

import java.util.Date;

/**
 *
 * @author ALEFF
 */
public class Usuario {
    private int id;
    private String usuario;
    private String senha;
    private int nivelDeAcesso;

    public Usuario() {
    }
    
    //CONSTRUTOR PARA INSERIR NO BANCO DE DADOS!
    public Usuario(String usuario, String senha, int nivelDeAcesso) {
        this.usuario = usuario;
        this.senha = senha;
        this.nivelDeAcesso = nivelDeAcesso;
    }
    
    //CONSTRUTOR PARA VERIFICAR NO BANCO DE DADOS!
    public Usuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    //CONSTRUTOR PARA BUSCAR NO BANCO DE DADOS!
    public Usuario(int id, String usuario, String senha, int nivelDeAcesso) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.nivelDeAcesso = nivelDeAcesso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNivelDeAcesso() {
        return nivelDeAcesso;
    }

    public void setNivelDeAcesso(int nivelDeAcesso) {
        this.nivelDeAcesso = nivelDeAcesso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    
    

}

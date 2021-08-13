/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import org.postgresql.util.PSQLException;

/**
 *
 * @author ALEFF
 */
public class UsuarioDAO {

    public boolean inserirUsuarioNoBanco(Usuario usuario) {
        Connection conexao = null;
        PreparedStatement statment = null;
        String sql = "insert into login(usuario,senha,niveldeacesso) values(?,?,?);";

        try {

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setString(1, usuario.getUsuario());
            statment.setString(2, usuario.getSenha());
            statment.setInt(3, usuario.getNivelDeAcesso());
            statment.execute();

            conexao.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeConection(conexao);
        }

    }

    public boolean autenticarUsuario(Usuario usuario) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        String sql = "select * from login where usuario = ? and senha = ?";
        Usuario usuariofim = new Usuario();
        try {

            conexao = new Conexao().getConnection();
            if(conexao!=null){
                System.out.println("DEUCERTO");
            }
            
            statment = conexao.prepareStatement(sql);

            statment.setString(1, usuario.getUsuario());
            statment.setString(2, usuario.getSenha());
            statment.execute();
            resultSet = statment.getResultSet();

            if (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }
    }

    public Usuario existePorUsuarioId(int id) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        String sql = "select * from login where id = ?";
        Usuario usuariofim = new Usuario();

        try {
            conexao = new Conexao().getConnection();

            statment = conexao.prepareStatement(sql);

            statment.setInt(1, id);

            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                usuariofim.setId(resultSet.getInt(1));
                usuariofim.setUsuario(resultSet.getString(2));
                usuariofim.setSenha(resultSet.getString(3));
                usuariofim.setNivelDeAcesso(resultSet.getInt(4));
            }

            return usuariofim;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }

    }

    public Usuario existePorUsuario(String nome) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        String sql = "select * from login where usuario = ?";
        Usuario usuariofim = new Usuario();

        try {
            conexao = new Conexao().getConnection();

            statment = conexao.prepareStatement(sql);

            statment.setString(1, nome);

            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                usuariofim.setId(resultSet.getInt(1));
                usuariofim.setUsuario(resultSet.getString(2));
                usuariofim.setSenha(resultSet.getString(3));
                usuariofim.setNivelDeAcesso(resultSet.getInt(4));
            }

            return usuariofim;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }

    }

    public Usuario existePorUsuarioESenha(Usuario usuario) {
        Connection conexao;
        PreparedStatement statment;
        ResultSet resultSet;
        
        String sql = "select * from login where usuario = ? and senha = ?";
        Usuario usuariofim = new Usuario();
        try {

            conexao = new Conexao().getConnection();

            statment = conexao.prepareStatement(sql);

            statment.setString(1, usuario.getUsuario());
            statment.setString(2, usuario.getSenha());
            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                usuariofim.setId(resultSet.getInt(1));
                usuariofim.setUsuario(resultSet.getString(2));
                usuariofim.setSenha(resultSet.getString(3));
                usuariofim.setNivelDeAcesso(resultSet.getInt(4));
            }

            return usuariofim;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
//            Conexao.closeStatment(statment);
//            Conexao.closeResultSet(resultSet);
//            Conexao.closeConection(conexao);
        }

    }

    public ArrayList<Usuario> procurarTodosUsuarios() {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "select id, usuario, senha, niveldeacesso from login id";

        try {

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.execute();
            resultSet = statment.getResultSet();
            while (resultSet.next()) {
                Usuario us = new Usuario(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), Integer.parseInt(resultSet.getString(4)));
                usuarios.add(us);
            }

            return usuarios;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }

    }

    public boolean deletarUSARIOSelecionado(int id) {
        Connection conexao = null;
        PreparedStatement statment = null;
        String sql = "delete from login where id = ?";
        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setInt(1, id);
            statment.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeConection(conexao);
        }

    }

}

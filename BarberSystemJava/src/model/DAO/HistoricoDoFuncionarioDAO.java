/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.HistoricoDoCliente;
import model.HistoricoDoFuncionario;

/**
 *
 * @author ALEFF
 */
public class HistoricoDoFuncionarioDAO {

    public ArrayList<HistoricoDoFuncionario> HistoricoDeUMFuncionario(int id) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        ArrayList<HistoricoDoFuncionario> historicos = new ArrayList<>();

        String sql = "select servico,cliente,valor,data from historicofuncionario where id_funcionario = ? ORDER by data DESC";

        try {

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setInt(1, id);

            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                HistoricoDoFuncionario historico = new HistoricoDoFuncionario(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4));
                if(historicos.size()<=50){
                historicos.add(historico);
                }
            }

            return historicos;
        } catch (SQLException ex) {
            Logger.getLogger(HistoricoDoFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }

    }

    public boolean insert(HistoricoDoFuncionario historicodofuncionario) {
        Connection conexao = null;
        PreparedStatement statment = null;
        String sql = "insert into historicofuncionario(id_funcionario,servico,cliente,valor,data,id_agendamento) values (?, ?, ?, ?, ?, ?);";

        try {

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment = conexao.prepareStatement(sql);
            statment.setInt(1, historicodofuncionario.getId_funcionario());
            statment.setString(2, historicodofuncionario.getServico());
            statment.setString(3, historicodofuncionario.getCliente());
            statment.setDouble(4, historicodofuncionario.getValor());
            statment.setDate(5, Date.valueOf(historicodofuncionario.getData()));
            statment.setInt(6, historicodofuncionario.getId_agendamento());
            statment.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            
            Conexao.closeStatment(statment);
            Conexao.closeConection(conexao);
        }

    }
}

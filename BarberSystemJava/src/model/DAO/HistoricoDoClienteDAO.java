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
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.HistoricoDoCliente;

/**
 *
 * @author ALEFF
 */
public class HistoricoDoClienteDAO {

    public ArrayList<HistoricoDoCliente> HistoricoDeUMcliente(int id) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        ArrayList<HistoricoDoCliente> historicos = new ArrayList<>();

        String sql = "select servico,funcionario,valor,data from historicocliente where id_cliente = ? ORDER by data DESC";

        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setInt(1, id);

            statment.execute();
            resultSet = statment.getResultSet();
            while (resultSet.next()) {
                HistoricoDoCliente historico = new HistoricoDoCliente(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4));
                if (historicos.size() <= 24) {
                    historicos.add(historico);
                } else {

                    break;
                }
            }

            return historicos;
        } catch (SQLException ex) {
            Logger.getLogger(HistoricoDoClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }
        return null;

    }

    public boolean insert(HistoricoDoCliente historicodocliente) {
        Connection conexao = null;
        PreparedStatement statment = null;
        String sql = "insert into historicocliente(id_cliente,servico,funcionario,valor,data,id_agendamento) values (?, ?, ?, ?, ?, ?);";

        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setInt(1, historicodocliente.getId_cliente());
            statment.setString(2, historicodocliente.getServico());
            statment.setString(3, historicodocliente.getFuncionario());
            statment.setDouble(4, historicodocliente.getValor());
            statment.setDate(5, Date.valueOf(historicodocliente.getData()));
            statment.setInt(6, historicodocliente.getId_agendamento());
            statment.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeConection(conexao);
        }

    }

    public ArrayList<String> BuscarHistorico(String data) {

        LocalDate datainicio = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));
        
        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(6, 10)) , Integer.parseInt(data.substring(3, 5)) , datainicio.lengthOfMonth());

        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;

        ArrayList<String> historicocliente = new ArrayList();
        String sql = "SELECT a.id, a.nome,a.sobrenome, SUM(b.valor) AS total from cliente a join historicocliente b on a.id = b.id_cliente WHERE DATE(data) > ? and DATE(data) < ? GROUP BY a.id, a.nome, a.sobrenome ORDER By total DESC";
        try {

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);
            statment.setDate(1, Date.valueOf(datainicio));
            statment.setDate(2, Date.valueOf(datafinal));
 
            
            
            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                Object[] objeto = new Object[3];

                objeto[0] = resultSet.getString("id");
                objeto[1] = resultSet.getString("nome");
                objeto[2] = resultSet.getString("sobrenome");
                
                String fim = "ID: "+objeto[0].toString()+" || NOME: " + objeto[1].toString() +" "+ objeto[2].toString();
                
                if (historicocliente.size() < 5) {
                    historicocliente.add(fim);
                } else {
                    break;
                }
            }

            return historicocliente;

        } catch (SQLException ex) {
            Logger.getLogger(HistoricoDoClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexao.closeResultSet(resultSet);
            Conexao.closeStatment(statment);
            Conexao.closeConection(conexao);

        }

    }

  
}

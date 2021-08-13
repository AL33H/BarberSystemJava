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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Despesa;

/**
 *
 * @author ALEFF
 */
public class DespesaDAO {

    public DespesaDAO() {
    }

    public boolean insert(Despesa despesa) {
        Connection conexao = null;
        PreparedStatement statment = null;
        String sql = "insert into despesa(nome,descricao,valor,data) values(?,?,?,?);";

        try {

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);
            statment = conexao.prepareStatement(sql);
            statment.setString(1, despesa.getNome());
            statment.setString(2, despesa.getDescricao());
            statment.setDouble(3, despesa.getValor());
            statment.setDate(4, Date.valueOf(despesa.getData()));

            statment.execute();

            conexao.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DespesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            Conexao.closeStatment(statment);
            Conexao.closeConection(conexao);
        }

    }

    public ArrayList<Despesa> selectfromdate(String data) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));
        ArrayList<Despesa> despesas = new ArrayList<>();
        String sql = "select * from despesa where data = ?";
        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setDate(1, Date.valueOf(datafinal));
            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                Despesa despesa = new Despesa(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5));
                despesas.add(despesa);
            }

            return despesas;
        } catch (SQLException ex) {
            Logger.getLogger(DespesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {

            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }

    }

    public ArrayList<Despesa> selectFromTwoDates(String data, String data2) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        LocalDate datainicio = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));
        LocalDate datafinal = LocalDate.of(Integer.parseInt(data2.substring(6, 10)), Integer.parseInt(data2.substring(3, 5)), Integer.parseInt(data2.substring(0, 2)));
        ArrayList<Despesa> despesas = new ArrayList<>();
        String sql = "select * from despesa WHERE data between ? AND ?";
        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setDate(1, Date.valueOf(datainicio));
            statment.setDate(2, Date.valueOf(datafinal));

            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                Despesa despesa = new Despesa(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5));
                despesas.add(despesa);
            }

            return despesas;
        } catch (SQLException ex) {
            Logger.getLogger(DespesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {

            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }

    }

    public ArrayList<Despesa> selectFromMonthYear(String data) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        LocalDate datafim = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));

        ArrayList<Despesa> despesas = new ArrayList<>();
        String sql = "SELECT *\n"
                + "   FROM despesa\n"
                + "   WHERE EXTRACT(month FROM (SELECT data)) = ?\n"
                + "   and EXTRACT(year FROM (SELECT data)) = ?";
        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setInt(1, datafim.getMonthValue());
            statment.setInt(2, datafim.getYear());

            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                Despesa despesa = new Despesa(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5));
                despesas.add(despesa);
            }

            return despesas;
        } catch (SQLException ex) {
            Logger.getLogger(DespesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {

            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }

    }

    public boolean deletefromId(int id) {
        Connection conexao = null;
        PreparedStatement statment = null;

        String sql = "delete from despesa where id = ?";
        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setInt(1, id);
            statment.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DespesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            Conexao.closeStatment(statment);
            Conexao.closeConection(conexao);

        }

    }


}

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
import model.Agendamento;
import model.Funcionario;
import model.Receita;
import model.ReceitaFinal;

/**
 *
 * @author ALEFF
 */
public class ReceitaDAO {

    public boolean insert(Receita receita) {
        Connection conexao = null;
        PreparedStatement statment = null;
        String sql = "insert into receita(id_funcionario,id_agendamento,metodopagamento,lucrofuncionario,lucroestabelecimento,data) values(?,?,?,?,?,?);";

        try {

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);
            statment = conexao.prepareStatement(sql);
            statment.setInt(1, receita.getFuncionario().getId());
            statment.setInt(2, receita.getAgendamento().getId());
            statment.setInt(3, receita.getMetododepagamento());
            statment.setDouble(4, receita.getLucrofuncionario());
            statment.setDouble(5, receita.getLucroestabelecimento());
            statment.setDate(6, Date.valueOf(receita.getData()));

            statment.execute();

            conexao.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            Conexao.closeStatment(statment);
            Conexao.closeConection(conexao);
        }

    }

    public ArrayList<Receita> selectfromdate(String data) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        ArrayList<Receita> receitas = new ArrayList<>();
        String sql = "select b.*, c.*, a.* from receita a join funcionario b on a.id_funcionario = b.id join Agendamento c on a.id_agendamento = c.id where a.data = ?";
        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));

        try {

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setDate(1, Date.valueOf(datafinal));
            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                Funcionario funcionario = new Funcionario(resultSet.getInt(1));
                Agendamento agendamento = new Agendamento(resultSet.getInt(7));
                Receita receita = new Receita(resultSet.getInt(15), funcionario, agendamento, resultSet.getInt(18), resultSet.getDouble(19), resultSet.getDouble(20), resultSet.getString(21));
                receitas.add(receita);
            }

            return receitas;
        } catch (SQLException ex) {
            Logger.getLogger(ReceitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }

    }

    public ArrayList<Receita> selectFromMonth(String data, String data2) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        ArrayList<Receita> receitas = new ArrayList<>();
        String sql = "SELECT b.*, c.*, a.* FROM receita a join funcionario b on a.id_funcionario = b.id join Agendamento c on a.id_agendamento = c.id WHERE a.data between ? AND ?";
        
        LocalDate datainicio = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));
        LocalDate datafinal = LocalDate.of(Integer.parseInt(data2.substring(6, 10)), Integer.parseInt(data2.substring(3, 5)), Integer.parseInt(data2.substring(0, 2)));

        try {

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setDate(1, Date.valueOf(datainicio));
            statment.setDate(2, Date.valueOf(datafinal));
            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                Funcionario funcionario = new Funcionario(resultSet.getInt(1));
                Agendamento agendamento = new Agendamento(resultSet.getInt(7));
                Receita receita = new Receita(resultSet.getInt(15), funcionario, agendamento, resultSet.getInt(18), resultSet.getDouble(19), resultSet.getDouble(20), resultSet.getString(21));
                receitas.add(receita);
            }

            return receitas;
        } catch (SQLException ex) {
            Logger.getLogger(ReceitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }

    }

    public ArrayList<ReceitaFinal> selectfromidanddate(String data) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        ArrayList<ReceitaFinal> receitas = new ArrayList<>();
        String sql = "select b.nome, COUNT(b.nome), sum(a.lucrofuncionario) from receita a join funcionario b on a.id_funcionario = b.id where a.data = ? group by b.nome";
        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));

        try {

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setDate(1, Date.valueOf(datafinal));

            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                ReceitaFinal receita = new ReceitaFinal(resultSet.getString(1), resultSet.getInt(2), resultSet.getDouble(3));
                receitas.add(receita);
            }

            return receitas;
        } catch (SQLException ex) {
            Logger.getLogger(ReceitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }
    }
    
    public ArrayList<ReceitaFinal> selectfromTwoDates(String data, String data2) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        ArrayList<ReceitaFinal> receitas = new ArrayList<>();
        String sql = "select b.nome, COUNT(b.nome), sum(a.lucrofuncionario) from receita a join funcionario b on a.id_funcionario = b.id WHERE a.data between ? AND ? group by b.nome";

        LocalDate datainicio = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));
        LocalDate datafinal = LocalDate.of(Integer.parseInt(data2.substring(6, 10)), Integer.parseInt(data2.substring(3, 5)), Integer.parseInt(data2.substring(0, 2)));

        
        try {

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setDate(1, Date.valueOf(datainicio));
            statment.setDate(2, Date.valueOf(datafinal));

            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                ReceitaFinal receita = new ReceitaFinal(resultSet.getString(1), resultSet.getInt(2), resultSet.getDouble(3));
                receitas.add(receita);
            }

            return receitas;
        } catch (SQLException ex) {
            Logger.getLogger(ReceitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }
    }
    
        public ArrayList<ReceitaFinal> selectfromTwoDatesReturningMonth(String data, String data2) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        ArrayList<ReceitaFinal> receitas = new ArrayList<>();
        String sql = "select to_char(a.data, 'TMMonth'), b.nome, COUNT(b.nome), sum(a.lucrofuncionario) from receita a join funcionario b on a.id_funcionario = b.id where a.data between ? AND ? group by b.nome,to_char ORDER BY to_char DESC";

        LocalDate datainicio = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));
        LocalDate datafinal = LocalDate.of(Integer.parseInt(data2.substring(6, 10)), Integer.parseInt(data2.substring(3, 5)), Integer.parseInt(data2.substring(0, 2)));

        
        try {

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setDate(1, Date.valueOf(datainicio));
            statment.setDate(2, Date.valueOf(datafinal));

            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                ReceitaFinal receita = new ReceitaFinal(resultSet.getString(1),resultSet.getString(2), resultSet.getInt(3), resultSet.getDouble(4));
                receitas.add(receita);
            }

            return receitas;
        } catch (SQLException ex) {
            Logger.getLogger(ReceitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }
    }
    


    public boolean delete(int id) {
        Connection conexao = null;
        PreparedStatement statment = null;
        String sql = "delete from receita where id = ?";
        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);
            statment.setInt(1, id);
            statment.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ReceitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeConection(conexao);
        }

    }
}

package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Funcionario;
import org.postgresql.util.PSQLException;

/**
 *
 * @author ALEFF
 */
public class FuncionarioDAO {

    public boolean inserirClientesNoBanco(Funcionario funcionario) {
        Connection conexao = null;
        PreparedStatement statment = null;
        String sql = "insert into funcionario(nome,sobrenome,sexo,funcao) values(?,?,?,?)";

        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);
            statment.setString(1, funcionario.getNome());
            statment.setString(2, funcionario.getSobrenome());
            statment.setString(3, funcionario.getSexo());
            statment.setString(4, funcionario.getFuncao());
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

    public Funcionario pegarFuncionarioPorID(int id) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        Funcionario funcionario = new Funcionario();
        String sql = "select * from funcionario where id = ? and ativo = '0'";
        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setInt(1, id);
            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {

                funcionario.setId(resultSet.getInt("id"));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setSobrenome(resultSet.getString("sobrenome"));
                funcionario.setSexo(resultSet.getString("sexo"));
                funcionario.setFuncao(resultSet.getString("funcao"));

            }
            return funcionario;

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {

            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }
    }

    public ArrayList<Funcionario> procurarTodosFuncionarios() {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;

        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        String sql = "select * from funcionario WHERE ativo = '0'";
        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);
            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {

                Funcionario funcionario = new Funcionario(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("sobrenome"), resultSet.getString("sexo"), resultSet.getString("funcao"));

                funcionarios.add(funcionario);
            }

            return funcionarios;

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {

            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);

        }
    }

//    public boolean delete(int id) {
//        Connection conexao = null;
//        PreparedStatement statment = null;
//
//        String sql = "delete from funcionario where id = ?";
//
//        try {
//            conexao = new Conexao().getConnection();
//            statment = conexao.prepareStatement(sql);
//            ;
//            statment.setInt(1, id);
//            statment.execute();
//            return true;
//        } catch (PSQLException ex) {
//            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        } catch (SQLException ex) {
//            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        } finally {
//
//            Conexao.closeStatment(statment);
//            Conexao.closeConection(conexao);
//
//        }
//
//    }
    public ArrayList<Funcionario> filtrarFuncionario(String filtro) {

        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        String sql = "select * from funcionario where LOWER(nome) LIKE LOWER('" + filtro + "%') and ativo = '0'";
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try {
            conexao = new Conexao().getConnection();

            statment = conexao.prepareStatement(sql);

            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {

                Funcionario funcionario = new Funcionario(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("sobrenome"), resultSet.getString("sexo"), resultSet.getString("funcao"));

                funcionarios.add(funcionario);
            }

            return funcionarios;

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);

        }
    }

    public boolean UpdateTOINATIVE(int id) {
        Connection conexao = null;
        PreparedStatement statment = null;

        String sql = "Update funcionario SET ativo = '1' where id = ?";

        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setInt(1, id);
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

}

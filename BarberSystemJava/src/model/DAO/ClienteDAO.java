package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Usuario;

/**
 *
 * @author ALEFF
 */
public class ClienteDAO {

    public boolean inserirClientesNoBanco(Cliente cliente) {
        Connection conexao = null;
        PreparedStatement statment = null;
        String sql = "insert into cliente(nome,sobrenome,sexo,datanascimento,cpf,whatsapp,rua,numero,bairro,complemento,cidade,estado) values(?,?,?,?,?,?,?,?,?,?,?,?);";

        try {

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);
            
            statment.setString(1, cliente.getNome());
            statment.setString(2, cliente.getSobrenome());
            statment.setString(3, cliente.getSexo());
            statment.setDate(4, Date.valueOf(cliente.getDataNascimento()));
            statment.setString(5, cliente.getCPF());
            statment.setString(6, cliente.getTelefone());
            statment.setString(7, cliente.getRua());
            statment.setInt(8, cliente.getNumeroDaCasa());
            statment.setString(9, cliente.getBairro());
            statment.setString(10, cliente.getComplemento());
            statment.setString(11, cliente.getCidade());
            statment.setString(12, cliente.getEstado());
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

    public ArrayList<Cliente> procurarTodosClientes() {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "select * from cliente WHERE ativo = '0'";
        try {

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                Cliente cliente = new Cliente(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12), resultSet.getString(13));
                clientes.add(cliente);
            }

            return clientes;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }
    }

    public ArrayList<Cliente> procurarTodosClientesFiltro(String filtro) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "select * from cliente where LOWER(nome) LIKE LOWER('" + filtro + "%') and ativo = '0'";

        try {

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                Cliente cliente = new Cliente(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("sobrenome"), resultSet.getString("sexo"), resultSet.getString("datanascimento"), resultSet.getString("cpf"), resultSet.getString("whatsapp"), resultSet.getString("rua"), resultSet.getInt("numero"), resultSet.getString("bairro"), resultSet.getString("complemento"), resultSet.getString("cidade"), resultSet.getString("estado"));
                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }
    }

    public ArrayList<Cliente> procurarTodosClientesPorID(int id) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "select * from cliente where id = ? and ativo = '0'";

        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setInt(1, id);
            statment.execute();
            resultSet = statment.getResultSet();
            while (resultSet.next()) {
                Cliente cliente = new Cliente(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12), resultSet.getString(13));
                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }
    }

    public ArrayList<Cliente> procurarTodosClientesERetornarNome() {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;

        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "select nome from cliente WHERE ativo = '0'";

        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                Cliente cliente = new Cliente(resultSet.getString(1));
                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
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

        String sql = "Update cliente SET ativo = '1' where id = ?";

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

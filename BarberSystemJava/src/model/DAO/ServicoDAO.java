package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Servico;

/**
 *
 * @author ALEFF
 */
public class ServicoDAO {
    
    public boolean inserirServicoNoBanco(Servico servico) {
        Connection conexao = null;
        PreparedStatement statment = null;
        String sql = "insert into servico(nome,valor,descricao,porcentagem) values(?,?,?,?);";
        
        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);
            statment = conexao.prepareStatement(sql);
            statment.setString(1, servico.getNome());
            statment.setDouble(2, servico.getValor());
            statment.setString(3, servico.getDescricao());
            statment.setInt(4, servico.getPorcentagem());
            statment.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeConection(conexao);
        }
        
    }
    
    public boolean UpdateServico(Servico servico) {
        Connection conexao = null;
        PreparedStatement statment = null;
        
        String sql = "update servico SET nome = ?,valor = ?,descricao = ?, porcentagem = ? where id = ?";
        
        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);
            
            
            statment.setString(1, servico.getNome());
            statment.setDouble(2, servico.getValor());
            statment.setString(3, servico.getDescricao());
            statment.setInt(4, servico.getPorcentagem());
            statment.setInt(5, servico.getId());
            
            statment.execute();
            
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeConection(conexao);
        }
        
    }
    
    public ArrayList<Servico> procurarTodosServicos() {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        ArrayList<Servico> servicos = new ArrayList<>();
        String sql = "select * from servico id";
        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);
            
            statment.execute();
            
            resultSet = statment.getResultSet();
            
            while (resultSet.next()) {
                Servico ser = new Servico(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4), resultSet.getInt(5));
                servicos.add(ser);
            }
            
            return servicos;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
            
        }
        
    }
    
        public Servico procurarServicoID(int id) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        
        String sql = "select * from servico where id = ?";
        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);
            statment.setInt(1, id);
            statment.execute();
            
            resultSet = statment.getResultSet();
            Servico servico = new Servico();
            while (resultSet.next()) {
                servico.setId(resultSet.getInt("id"));
                servico.setNome(resultSet.getString("nome"));
                servico.setValor(resultSet.getDouble("valor"));
                servico.setDescricao(resultSet.getString("descricao"));
                servico.setPorcentagem(resultSet.getInt("porcentagem"));
            }
            
            return servico;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        String sql = "delete from servico where id = ?";
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

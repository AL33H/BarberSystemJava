package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Agendamento;
import model.Cliente;
import model.Funcionario;
import model.Servico;
import org.postgresql.util.PSQLException;

/**
 *
 * @author ALEFF
 */
public class AgendamentoDAO {

    public ArrayList<Agendamento> procurarTodosAgendamentos(String data) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;

        ArrayList<Agendamento> agendamentos = new ArrayList<>();
        String sql = "select b.id,b.nome,b.sobrenome,b.sexo,b.datanascimento,b.cpf,b.whatsapp,b.rua,b.numero,b.bairro,b.complemento,b.cidade,b.estado, c.*, d.*, a.* from agendamento a join cliente b on b.id = a.id_cliente join servico c on a.id_servico = c.id join funcionario d on a.id_funcionario = d.id where a.data = ? ORDER BY a.hora";
        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));

        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setDate(1, Date.valueOf(datafinal));
            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                Cliente cliente = new Cliente(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12), resultSet.getString(13));
                Servico servico = new Servico(resultSet.getInt(14), resultSet.getString(15), resultSet.getDouble(16), resultSet.getString(17), resultSet.getInt(18));
                Funcionario funcionario = new Funcionario(resultSet.getInt(19), resultSet.getString(20), resultSet.getString(21), resultSet.getString(22), resultSet.getString(23));
                Agendamento agendamento = new Agendamento(resultSet.getInt(25), cliente, servico, funcionario, resultSet.getDouble(29), resultSet.getString(30), resultSet.getString(31), resultSet.getInt(32));

                agendamentos.add(agendamento);
            }

            return agendamentos;

        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }
    }

    public ArrayList<Agendamento> procurarFILTROparaTodosAgendamentos(String filtro, String data, int modo) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;

        ArrayList<Agendamento> agendamentos = new ArrayList<>();
        String sql = null;

        switch (modo) {

            case 0:
                sql = " select b.id,b.nome,b.sobrenome,b.sexo,b.datanascimento,b.cpf,b.whatsapp,\n"
                        + " b.rua,b.numero,b.bairro,b.complemento,b.cidade,b.estado, c.*, d.*, a.* \n"
                        + " from agendamento a join cliente b on b.id = a.id_cliente \n"
                        + " join servico c \n"
                        + " on a.id_servico = c.id \n"
                        + " join funcionario d \n"
                        + " on a.id_funcionario = d.id \n"
                        + " where a.data = ? and LOWER(d.nome) LIKE LOWER('" + filtro + "%')  \n"
                        + " ORDER BY a.hora;";
                break;

            case 1:
                sql = " select b.id,b.nome,b.sobrenome,b.sexo,b.datanascimento,b.cpf,b.whatsapp,\n"
                        + " b.rua,b.numero,b.bairro,b.complemento,b.cidade,b.estado, c.*, d.*, a.* \n"
                        + " from agendamento a join cliente b on b.id = a.id_cliente \n"
                        + " join servico c \n"
                        + " on a.id_servico = c.id \n"
                        + " join funcionario d \n"
                        + " on a.id_funcionario = d.id \n"
                        + " where a.data = ? and LOWER(b.nome) LIKE LOWER('" + filtro + "%')  \n"
                        + " ORDER BY a.hora;";
                break;

            case 2:

                sql = " select b.id,b.nome,b.sobrenome,b.sexo,b.datanascimento,b.cpf,b.whatsapp,\n"
                        + " b.rua,b.numero,b.bairro,b.complemento,b.cidade,b.estado, c.*, d.*, a.* \n"
                        + " from agendamento a join cliente b on b.id = a.id_cliente \n"
                        + " join servico c \n"
                        + " on a.id_servico = c.id \n"
                        + " join funcionario d \n"
                        + " on a.id_funcionario = d.id \n"
                        + " where a.data = ? and LOWER(c.nome) LIKE LOWER('" + filtro + "%')  \n"
                        + " ORDER BY a.hora;";
                break;

        }
        try {
//        String sql = "select b.id,b.nome,b.sobrenome,b.sexo,b.datanascimento,b.cpf,b.whatsapp,b.rua,b.numero,b.bairro,b.complemento,b.cidade,b.estado, c.*, d.*, a.* from agendamento a join cliente b on b.id = a.id_cliente join servico c on a.id_servico = c.id join funcionario d on a.id_funcionario = d.id where a.data = ? ORDER BY a.hora";
            LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setDate(1, Date.valueOf(datafinal));
            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                Cliente cliente = new Cliente(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12), resultSet.getString(13));
                Servico servico = new Servico(resultSet.getInt(14), resultSet.getString(15), resultSet.getDouble(16), resultSet.getString(17), resultSet.getInt(18));
                Funcionario funcionario = new Funcionario(resultSet.getInt(19), resultSet.getString(20), resultSet.getString(21), resultSet.getString(22), resultSet.getString(23));
                Agendamento agendamento = new Agendamento(resultSet.getInt(25), cliente, servico, funcionario, resultSet.getDouble(29), resultSet.getString(30), resultSet.getString(31), resultSet.getInt(32));

                agendamentos.add(agendamento);
            }

            return agendamentos;

        } catch (PSQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }
    }

    public Agendamento procurarAgendamentoPorId(int id) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;

        Agendamento agendamento = new Agendamento();
        String sql = "select b.*, c.*, d.*, a.* from agendamento a join cliente b on b.id = a.id_cliente join servico c on a.id_servico = c.id join funcionario d on a.id_funcionario = d.id where a.id = ? ORDER BY a.hora";

        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setInt(1, id);
            statment.execute();
            resultSet = statment.getResultSet();

            while (resultSet.next()) {
                Cliente cliente = new Cliente(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getInt(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12), resultSet.getString(13));
                Servico servico = new Servico(resultSet.getInt(15), resultSet.getString(16), resultSet.getDouble(17), resultSet.getString(18), resultSet.getInt(19));
                Funcionario funcionario = new Funcionario(resultSet.getInt(20), resultSet.getString(21), resultSet.getString(22), resultSet.getString(23), resultSet.getString(24));

                agendamento.setId(resultSet.getInt(26));
                agendamento.setCliente(cliente);
                agendamento.setServico(servico);
                agendamento.setFuncionario(funcionario);
                agendamento.setValor(resultSet.getDouble(30));
                agendamento.setData(resultSet.getString(31));
                agendamento.setHora(resultSet.getString(32));
                agendamento.setStatus(resultSet.getInt(33));
            }

            return agendamento;

        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {

            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }
    }

    public boolean updateto1(int id) {
        Connection conexao = null;
        PreparedStatement statment = null;

        String sql = "update agendamento set status = 1 where id = ?";
        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setInt(1, id);
            statment.execute();
            conexao.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            Conexao.closeStatment(statment);
            Conexao.closeConection(conexao);
        }

    }

    public boolean updateto2(int id) {
        Connection conexao = null;
        PreparedStatement statment = null;
        String sql = "update agendamento set status = 2 where id = ? ";
        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.setInt(1, id);
            statment.execute();
            conexao.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            Conexao.closeStatment(statment);
            Conexao.closeConection(conexao);
        }
    }

    public boolean inserirClientesNoBanco(Agendamento agendamento) {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        String sql = "insert into agendamento(id_cliente,id_servico,id_funcionario,valor,data,hora,status) values(?,?,?,?,?,?,?);";

        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment = conexao.prepareStatement(sql);
            statment.setInt(1, agendamento.getCliente().getId());
            statment.setInt(2, agendamento.getServico().getId());
            statment.setInt(3, agendamento.getFuncionario().getId());
            statment.setDouble(4, agendamento.getValor());
            statment.setDate(5, Date.valueOf(agendamento.getData()));
            statment.setTime(6, Time.valueOf(agendamento.getHora().toString() + ":00"));
            statment.setInt(7, agendamento.getStatus());

            statment.execute();
            resultSet = statment.getResultSet();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }
    }

    public boolean delete(int id) {
        Connection conexao = null;
        PreparedStatement statment = null;
        String sql = "delete from agendamento where id = ?";
        try {
            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);
            statment.setInt(1, id);
            statment.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            Conexao.closeStatment(statment);
            Conexao.closeConection(conexao);
        }
    }
}

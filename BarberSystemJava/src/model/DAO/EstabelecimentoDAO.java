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
import model.Estabelecimento;

/**
 *
 * @author ALEFF
 */
public class EstabelecimentoDAO {

    public Estabelecimento find() {
        Connection conexao = null;
        PreparedStatement statment = null;
        ResultSet resultSet = null;

        String sql = "select * from estabelecimento";
        try {

            conexao = new Conexao().getConnection();
            statment = conexao.prepareStatement(sql);

            statment.execute();
            resultSet = statment.getResultSet();

            if (resultSet.next()) {
                Estabelecimento estabelecimento = new Estabelecimento(resultSet.getString("nome"), resultSet.getDate("data").toString(), resultSet.getString("versao"));
                return estabelecimento;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {

            Conexao.closeStatment(statment);
            Conexao.closeResultSet(resultSet);
            Conexao.closeConection(conexao);
        }
    }
}

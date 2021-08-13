/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Exception.BDException;

/**
 *
 * @author ALEFF
 */
public class Conexao {

    private static Connection conn = null;

    public Connection getConnection() {
        try {

            Properties props = loadPropeties();
            String url = props.getProperty("dburl");
            conn = DriverManager.getConnection(url, props);

            return conn;
        } catch (SQLException ex) {
            System.out.println("GETCONECTION" + ex.getMessage());
        }
//        Connection conexao;
//        conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Barbearia", "postgres", "postgresql");
        return null;

    }

    public static Properties loadPropeties() {
        try (FileInputStream fs = new FileInputStream("C:\\Users\\ALEFF\\Documents\\NetBeansProjects\\ProjetoBarbearia\\src\\db.propeties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public static void closeConection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                throw new BDException("AQUI" + ex.getMessage());
            }
        }

    }

    public static void closeStatment(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                throw new BDException(ex.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new BDException(ex.getMessage());
            }
        }
    }
}

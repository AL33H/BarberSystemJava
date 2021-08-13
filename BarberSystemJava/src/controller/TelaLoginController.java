/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Dialog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import model.DAO.Conexao;
import model.DAO.EstabelecimentoDAO;
import model.DAO.UsuarioDAO;
import model.Estabelecimento;
import model.Usuario;
import view.TelaUsuarioCadastro;
import view.TelaLogin;
import view.TelaLoginSplash;
import view.TelaMenu;

/**
 *
 * @author ALEFF
 */
public class TelaLoginController {

    private final TelaLogin view;

    public TelaLoginController(TelaLogin view) {
        this.view = view;
    }

    public void AutenticarUsuario() {
        /*
        *Buscar Usuario da view;
        Autenticar Usuario no Banco de dados;
        *Chamar Tela de Splash;
        *Chamar TelaMenu;
         */

        String viewUsuario = view.getjTextFieldLOGIN().getText();
        String viewSenha = view.getjPasswordField1().getText();

        Usuario usuario = new Usuario(viewUsuario, viewSenha);

        UsuarioDAO usuariodao = new UsuarioDAO();
        boolean existe = usuariodao.autenticarUsuario(usuario);
        Usuario existePorUsuarioESenha = usuariodao.existePorUsuarioESenha(usuario);

        if (existe) {

            if (validarSistema()) {
                TelaMenu menu = new TelaMenu(existePorUsuarioESenha);
                menu.setVisible(true);
                view.dispose();
            }

        } else {

            JOptionPane.showMessageDialog(view, "Usuario ou Senha invalidas!");
            view.getjTextFieldLOGIN().setText("");
            view.getjPasswordField1().setText("");
        }

    }

    private boolean validarSistema() {
        EstabelecimentoDAO estabelecimentodao = new EstabelecimentoDAO();
        Estabelecimento estab = estabelecimentodao.find();

        if (LocalDate.now().isBefore(estab.getData())) {

            return true;

        } else if (LocalDate.now().isAfter(estab.getData()) || estab.getNome().isEmpty()) {
            JOptionPane.showMessageDialog(view, "A data de validade do sistema se venceu, porfavor contate o Administrador!");
            return false;
        }

        return false;
    }

}

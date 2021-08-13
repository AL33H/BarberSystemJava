/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DAO.Conexao;
import model.DAO.UsuarioDAO;
import model.Usuario;
import view.TelaUsuarioCadastro;
import view.TelaUsuario;

/**
 *
 * @author ALEFF
 */
public class TelaUsuarioController {

    private final TelaUsuario view;

    public TelaUsuarioController(TelaUsuario view) {
        this.view = view;
    }

    public void PreencherTabelaUsuario() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        UsuarioDAO usuariodao = new UsuarioDAO();

        usuarios = usuariodao.procurarTodosUsuarios();

        DefaultTableModel dtmReceitas = (DefaultTableModel) view.getjTableUSUARIO().getModel();
        dtmReceitas.setNumRows(0);

        for (Usuario usu : usuarios) {
            dtmReceitas.addRow(new Object[]{
                usu.getId(),
                usu.getUsuario(),
                usu.getSenha(),
                usu.getNivelDeAcesso(),});
        }
    }

    public int pegarClienteSelecionado() {
        int selectedRow = view.getjTableUSUARIO().getSelectedRow();

        if (selectedRow >= 0) {
            return (int) view.getjTableUSUARIO().getValueAt(selectedRow, 0);
        } else {
            JOptionPane.showMessageDialog(view, "Cliente não selecionado!");
        }
        return 0;
    }

    public void apagarUsuarioSelecionado(int id) {

        int apagar = pegarClienteSelecionado();

        if (apagar != 0) {
            int showInternalConfirmDialog = JOptionPane.showConfirmDialog(null, "Deseja apagar o Usuario");

            if (showInternalConfirmDialog == 0) {
                UsuarioDAO clientedao = new UsuarioDAO();

                Usuario existePorUsuarioId = clientedao.existePorUsuarioId(id);
                if (existePorUsuarioId.getNivelDeAcesso() != 3) {
                    boolean deletarClienteSelecionado = clientedao.deletarUSARIOSelecionado(id);

                    if (deletarClienteSelecionado) {
                        JOptionPane.showMessageDialog(view, "Usuario apagado!");
                        PreencherTabelaUsuario();
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Não é possivel apagar Usuario Administrador!");
                }
            }
        }
    }

    public void chamarCadastroUsuario() {
        if (TelaUsuarioCadastro.instance == null) {
            new TelaUsuarioCadastro().setVisible(true);
        } else {
            TelaUsuarioCadastro.instance.dispose();
        }
    }

    public void atualizarTabelaUsuario() {
        PreencherTabelaUsuario();
    }

    public void sairDaTelaUsuario() {
        view.dispose();
    }

}

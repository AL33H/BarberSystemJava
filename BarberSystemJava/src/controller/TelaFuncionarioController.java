/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DAO.FuncionarioDAO;
import model.Funcionario;
import view.TelaFuncionario;
import view.TelaFuncionarioCadastro;
import view.TelaFuncionarioVisualizacao;

/**
 *
 * @author ALEFF
 */
public class TelaFuncionarioController {

    private final TelaFuncionario view;

    public TelaFuncionarioController(TelaFuncionario view) {
        this.view = view;
    }

    public void PreencherTabelaFuncionarios() {

        ArrayList<Funcionario> funcionarios;

        FuncionarioDAO funcionariodao = new FuncionarioDAO();

        funcionarios = funcionariodao.procurarTodosFuncionarios();

        //JOptionPane.showMessageDialog(view, funcionarios.toString());
        DefaultTableModel dtmReceitas = (DefaultTableModel) view.getjTableFUNCIONARIOS().getModel();

        dtmReceitas.setNumRows(0);

        for (Funcionario fun : funcionarios) {
            dtmReceitas.addRow(new Object[]{
                fun.getId(),
                fun.getNome() + " " + fun.getSobrenome(),
                fun.getSexo(),
                fun.getFuncao()});

        }

    }

    public int pegarFuncionarioSelecionado() {
        int selectedRow = view.getjTableFUNCIONARIOS().getSelectedRow();

        if (selectedRow >= 0) {
            return (int) view.getjTableFUNCIONARIOS().getValueAt(selectedRow, 0);
        } else {
            JOptionPane.showMessageDialog(view, "Funcionario não selecionado!");
        }
        return 0;
    }

    public void chamarTelaFuncionarioVisualizacao(int id) {

        if (id > 0) {

            if (TelaFuncionarioVisualizacao.instance == null) {
                new TelaFuncionarioVisualizacao(id).setVisible(true);
            } else {
                TelaFuncionarioVisualizacao.instance.dispose();
            }

        }

    }

    public void apagarFuncionarioSelecionado() {

        int pegarFuncionarioSelecionado = pegarFuncionarioSelecionado();
        if (pegarFuncionarioSelecionado >= 1) {
            int showInternalConfirmDialog = JOptionPane.showConfirmDialog(null, "Deseja apagar o Funcionario?");
            if (showInternalConfirmDialog == 0) {
                FuncionarioDAO funcionariodao = new FuncionarioDAO();

                boolean deletarFuncionarioSelecionado;

                deletarFuncionarioSelecionado = funcionariodao.UpdateTOINATIVE(pegarFuncionarioSelecionado);

                if (deletarFuncionarioSelecionado) {
                    JOptionPane.showMessageDialog(view, "Funcionario apagado!");
                    PreencherTabelaFuncionarios();
                }

            }
        }
    }

    public void adicionarFuncionario() {

        if (TelaFuncionarioCadastro.instance == null) {
            TelaFuncionarioCadastro cadastrofuncionario = new TelaFuncionarioCadastro();
            cadastrofuncionario.setVisible(true);

        } else {
            TelaFuncionarioCadastro.instance.dispose();
        }
    }

    public void SairDaTela() {
        view.dispose();
    }

    public void atualizarListaCliente() {
        PreencherTabelaFuncionarios();
    }

}

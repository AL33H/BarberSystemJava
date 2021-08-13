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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.DAO.ClienteDAO;
import view.TelaClienteCadastro;
import view.TelaCliente;
import view.TelaClienteVisualizacao;

/**
 *
 * @author ALEFF
 */
public class TelaClienteController {

    private final TelaCliente view;

    public TelaClienteController(TelaCliente view) {
        this.view = view;
    }

    public void PreencherTabelaCliente() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        ClienteDAO clientedao = new ClienteDAO();

        clientes = clientedao.procurarTodosClientes();

        view.getjLabelNUMERODECLIENTES().setText(clientes.size() + "");

        DefaultTableModel dtmReceitas = (DefaultTableModel) view.getjTableClientes().getModel();
        dtmReceitas.setNumRows(0);

        for (Cliente cli : clientes) {
            dtmReceitas.addRow(new Object[]{
                cli.getId(),
                cli.getNome() + " " + cli.getSobrenome(),
                cli.getCPF(),
                cli.getTelefone(),
                cli.getSexo(),
                cli.getRua() + " " + cli.getNumeroDaCasa() + " " + cli.getBairro(),
                cli.getCidade() + " " + cli.getEstado()});

        }

    }

    public void PreencherTabelaCliente(boolean filtro) {
        String filtrarPor = view.getjTextFieldFILTRO().getText();

        ArrayList<Cliente> clientes = new ArrayList<>();
        ClienteDAO clientedao = new ClienteDAO();

        clientes = clientedao.procurarTodosClientesFiltro(filtrarPor);

        view.getjLabelNUMERODECLIENTES().setText(clientes.size() + "");

        DefaultTableModel dtmReceitas = (DefaultTableModel) view.getjTableClientes().getModel();
        dtmReceitas.setNumRows(0);

        for (Cliente cli : clientes) {
            dtmReceitas.addRow(new Object[]{
                cli.getId(),
                cli.getNome() + " " + cli.getSobrenome(),
                cli.getCPF(),
                cli.getTelefone(),
                cli.getSexo(),
                cli.getRua() + " " + cli.getNumeroDaCasa() + " " + cli.getBairro(),
                cli.getCidade() + " " + cli.getEstado()});

        }

    }

    public int pegarClienteSelecionado() {
        int selectedRow = view.getjTableClientes().getSelectedRow();

        if (selectedRow >= 0) {
            return (int) view.getjTableClientes().getValueAt(selectedRow, 0);
        } else {
            JOptionPane.showMessageDialog(view, "Cliente não selecionado!");
        }
        return 0;
    }

    public void chamarTelaVisualizacao(int id) {
        if (id > 0) {

            if (TelaClienteVisualizacao.instance == null) {
                new TelaClienteVisualizacao(id).setVisible(true);
            } else {
                TelaClienteVisualizacao.instance.dispose();
            }

        }
    }

    public void apagarClienteSelecionado() {
        int pegarClienteSelecionado = pegarClienteSelecionado();
        if (pegarClienteSelecionado > 0) {
            int showInternalConfirmDialog = JOptionPane.showConfirmDialog(null, "Deseja apagar o Usuario");
            if (showInternalConfirmDialog == 0) {
                ClienteDAO clientedao = new ClienteDAO();

                boolean deletarClienteSelecionado;

                deletarClienteSelecionado = clientedao.UpdateTOINATIVE(pegarClienteSelecionado);

                if (deletarClienteSelecionado) {
                    JOptionPane.showMessageDialog(view, "Cliente apagado!");
                    PreencherTabelaCliente();
                }

            }
        }
    }

    public void adicionarCliente() {
        if (TelaClienteCadastro.instance == null) {
            new TelaClienteCadastro().setVisible(true);
        } else {
            TelaClienteCadastro.instance.dispose();
        }

    }

    public void SairDaTela() {
        view.dispose();
    }

    public void atualizarListaCliente() {
        PreencherTabelaCliente();
    }

}

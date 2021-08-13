/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.DAO.ClienteDAO;
import model.DAO.FuncionarioDAO;
import model.DAO.HistoricoDoClienteDAO;
import model.DAO.HistoricoDoFuncionarioDAO;
import model.Funcionario;
import model.HistoricoDoCliente;
import model.HistoricoDoFuncionario;
import view.TelaClienteVisualizacao;
import view.TelaFuncionarioVisualizacao;

/**
 *
 * @author ALEFF
 */
public class TelaFuncionarioVisualizacaoController {

    private final TelaFuncionarioVisualizacao view;

    public TelaFuncionarioVisualizacaoController(TelaFuncionarioVisualizacao view) {
        this.view = view;
    }

    public void pegarUsuarioSelecionado(int id) {
        Funcionario funcionario = new Funcionario();
        FuncionarioDAO funcionariodao = new FuncionarioDAO();

        funcionario = funcionariodao.pegarFuncionarioPorID(id);

        PreencherInformacoesDoFuncionario(funcionario);
    }

    public void PreencherInformacoesDoFuncionario(Funcionario funcionario) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        view.getjTextFieldID().setText(funcionario.getId() + "");
        view.getjTextFieldNOME().setText(funcionario.getNome());
        view.getjTextFieldSOBRENOME().setText(funcionario.getSobrenome());
        view.getjComboBoxSEXO().setSelectedItem(funcionario.getSexo());
        view.getjTextFieldFUNCAO().setText(funcionario.getFuncao());

    }

    public void preencherTabelaHistorico(int id) {

        ArrayList<HistoricoDoFuncionario> historicodofuncionario = new ArrayList<>();
        HistoricoDoFuncionarioDAO historicodao = new HistoricoDoFuncionarioDAO();
        ArrayList<HistoricoDoFuncionario> HistoricoDefuncionarios;
        HistoricoDefuncionarios = historicodao.HistoricoDeUMFuncionario(id);
        DefaultTableModel dtm = (DefaultTableModel) view.getjTableHISTORICO().getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("#0.00");

        dtm.setNumRows(0);

        for (HistoricoDoFuncionario his : HistoricoDefuncionarios) {

            dtm.addRow(new Object[]{
                his.getServico(),
                his.getCliente(),
                df.format(his.getValor()),
                his.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                his.getId_agendamento()});

        }

    }

}

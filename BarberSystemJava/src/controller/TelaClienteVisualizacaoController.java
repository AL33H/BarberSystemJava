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
import model.DAO.HistoricoDoClienteDAO;
import model.HistoricoDoCliente;
import view.TelaClienteVisualizacao;

/**
 *
 * @author ALEFF
 */
public class TelaClienteVisualizacaoController {

    private final TelaClienteVisualizacao view;

    public TelaClienteVisualizacaoController(TelaClienteVisualizacao view) {
        this.view = view;
    }

    public void pegarUsuarioSelecionado(int id) {
        ArrayList<Cliente> clientes = new ArrayList();
        ClienteDAO clientedao = new ClienteDAO();

        clientes = clientedao.procurarTodosClientesPorID(id);

        Cliente cliente = clientes.get(0);
        PreencherInformacoesDoCliente(cliente);
    }

    public void PreencherInformacoesDoCliente(Cliente cliente) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        view.getjTextFieldID().setText(cliente.getId() + "");
        view.getjTextFieldNOME().setText(cliente.getNome());
        view.getjTextFieldSOBRENOME().setText(cliente.getSobrenome());
        view.getjFormattedTextFieldCPF().setText(cliente.getCPF());
        view.getjFormattedTextFieldDATA().setText(cliente.getDataNascimento().format(formatter));
        view.getjFormattedTextFieldWHATSAPP().setText(cliente.getTelefone());
        view.getjComboBoxSEXO().setSelectedItem(cliente.getSexo());
        view.getjTextFieldRUA().setText(cliente.getRua());
        view.getjTextFieldNUMERO().setText(cliente.getNumeroDaCasa() + "");
        view.getjTextFieldIBAIRRO().setText(cliente.getBairro());
        view.getjTextFieldCOMPLEMENTO().setText(cliente.getComplemento());
        view.getjTextFieldCIDADE().setText(cliente.getCidade());
        view.getjTextFieldESTADO().setText(cliente.getEstado());

    }

    public void preencherTabelaHistorico(int id) {

        ArrayList<HistoricoDoCliente> historicodocliente = new ArrayList<>();
        HistoricoDoClienteDAO historicodao = new HistoricoDoClienteDAO();
        ArrayList<HistoricoDoCliente> HistoricoDeclientes;
        HistoricoDeclientes = historicodao.HistoricoDeUMcliente(id);
        DefaultTableModel dtm = (DefaultTableModel) view.getjTableHISTORICO().getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("#0.00");

        dtm.setNumRows(0);

        for (HistoricoDoCliente his : HistoricoDeclientes) {

            dtm.addRow(new Object[]{
                his.getServico(),
                his.getFuncionario(),
                df.format(his.getValor()),
                his.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                his.getId_agendamento()});

        }

    }

}

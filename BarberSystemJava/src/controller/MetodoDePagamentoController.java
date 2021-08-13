/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Agendamento;
import model.DAO.AgendamentoDAO;
import model.DAO.HistoricoDoClienteDAO;
import model.DAO.HistoricoDoFuncionarioDAO;
import model.DAO.ReceitaDAO;
import model.HistoricoDoCliente;
import model.HistoricoDoFuncionario;
import model.Receita;
import view.MetodoDePagamento;

/**
 *
 * @author ALEFF
 */
public class MetodoDePagamentoController {

    private final MetodoDePagamento view;

    public MetodoDePagamentoController(MetodoDePagamento view) {
        this.view = view;
    }

    public void FinalizarVenda(int id_agendamento) {

        AgendamentoDAO agendamentodao = new AgendamentoDAO();

        int metododepagamento = view.getjComboBoxMETODODEPAGAMENTO().getSelectedIndex();

        agendamentodao.updateto1(id_agendamento);

        Agendamento agendamento = agendamentodao.procurarAgendamentoPorId(id_agendamento);

        HistoricoDoCliente historicodocliente = new HistoricoDoCliente(agendamento.getCliente().getId(), agendamento.getServico().getNome(), agendamento.getFuncionario().getNome(), agendamento.getValor(), agendamento.getData().toString(), id_agendamento);
        HistoricoDoClienteDAO historicodoclientedao = new HistoricoDoClienteDAO();
        historicodoclientedao.insert(historicodocliente);

        Double valorfuncionario = ((agendamento.getServico().getPorcentagem() / 100.0) * agendamento.getValor());
        Double valorEstabelecimento = agendamento.getValor() - valorfuncionario;

        HistoricoDoFuncionario historicodofuncionario = new HistoricoDoFuncionario(agendamento.getFuncionario().getId(), agendamento.getServico().getNome(), agendamento.getCliente().getNome(), valorfuncionario, agendamento.getData().toString(), id_agendamento);
        HistoricoDoFuncionarioDAO historicodofuncionariodao = new HistoricoDoFuncionarioDAO();
        historicodofuncionariodao.insert(historicodofuncionario);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Receita receita = new Receita(agendamento.getFuncionario(), agendamento, metododepagamento, valorfuncionario, valorEstabelecimento, agendamento.getData().toString());
        ReceitaDAO receitadao = new ReceitaDAO();
        receitadao.insert(receita);

        JOptionPane.showMessageDialog(view, "Agendamento Finalizado!");
        view.dispose();

    }

    public void preencherInformacoes(int id_agendamento) {
        AgendamentoDAO agendamentodao = new AgendamentoDAO();
        Agendamento agendamento = agendamentodao.procurarAgendamentoPorId(id_agendamento);
        DecimalFormat df = new DecimalFormat("0.00");
        view.getjLabelIDAGENDAMENTO().setText(agendamento.getId() + "");
        view.getjLabelCLIENTEAGENDAMENTO().setText(agendamento.getCliente().getNome());
        view.getjLabelVALORAGENDAMENTO().setText(df.format(agendamento.getValor()));
    }

    public void calcularTROCO() {
        try {
            Double valordoagendamento = Double.parseDouble(view.getjLabelVALORAGENDAMENTO().getText().replace(",", "."));
            Double valordopagamento = Double.parseDouble(view.getjFormattedTextFieldVALOR().getText().replace(",", "."));

            if (!view.getjComboBoxMETODODEPAGAMENTO().getSelectedItem().equals("DINHEIRO")) {
                
                view.getjFormattedTextFieldVALOR().setEnabled(false);
                view.getjFormattedTextFieldTROCO().setValue(0.00);

                view.getjFormattedTextFieldVALOR().setValue(valordoagendamento);
            } else {

                view.getjFormattedTextFieldVALOR().setEnabled(true);
                

                if (valordoagendamento <= valordopagamento) {
                    DecimalFormat df = new DecimalFormat("0.00");
                    Double troco = valordoagendamento - valordopagamento;
                    view.getjFormattedTextFieldTROCO().setText((df.format(troco)+"").replace("-", ""));
                }

            }
        } catch (NumberFormatException ex) {

        }

//        try {
        //            view.getjFormattedTextFieldVALOR().setEnabled(true);
        //            DecimalFormat df = new DecimalFormat("0.00");
        //            view.getjFormattedTextFieldVALOR().setText("0.00");
        //            if (view.getjComboBoxMETODODEPAGAMENTO().getSelectedItem().equals("DINHEIRO")) {
        //
        //                if (Double.parseDouble(view.getjFormattedTextFieldVALOR().getText()) >= Double.parseDouble(view.getjLabelVALORAGENDAMENTO().getText())) {
        //                    view.getjFormattedTextFieldVALOR().setEnabled(true);
        //
        //                    Double valor = Double.parseDouble(view.getjFormattedTextFieldVALOR().getText().replace(",", "."));
        //                    Double troco = Double.parseDouble(view.getjLabelVALORAGENDAMENTO().getText().replace(",", ".")) - valor;
        //
        //                    view.getjFormattedTextFieldTROCO().setText(df.format(troco).replace("-", ""));
        //                } else {
        //                    view.getjFormattedTextFieldVALOR().setText("0.00");
        //                }
        //
        //            } else {
        //                view.getjFormattedTextFieldVALOR().setText(view.getjLabelVALORAGENDAMENTO().getText());
        //                view.getjFormattedTextFieldVALOR().setEnabled(false);
        //                view.getjFormattedTextFieldTROCO().setText("0.00");
        //            }
        //        } catch (NumberFormatException ex) {
        //
        //        }
    }

    public void cancelar() {
        view.dispose();
    }

}

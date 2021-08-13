/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Agendamento;
import model.DAO.AgendamentoDAO;
import model.DAO.DespesaDAO;
import model.DAO.FuncionarioDAO;
import model.DAO.ReceitaDAO;
import model.Despesa;
import model.Receita;
import model.ReceitaFinal;
import view.TelaCaixaDiario;

/**
 *
 * @author ALEFF
 */
public class TelaCaixaDiarioController {

    private final TelaCaixaDiario view;

    public TelaCaixaDiarioController(TelaCaixaDiario view) {
        this.view = view;
    }

    public void PreencherInformacoesdodia(String date) {
        ArrayList<Receita> receitas = new ArrayList<>();
        ArrayList<Despesa> despesas = new ArrayList<>();
        
        
        ReceitaDAO receitadao = new ReceitaDAO();
        DespesaDAO despesadao = new DespesaDAO();
        AgendamentoDAO agendamentodao = new AgendamentoDAO();
        
        receitas = receitadao.selectfromdate(date);
        despesas = despesadao.selectfromdate(date);
        
        Double dinheiro = 0.0;
        Double deposito = 0.0;
        Double credito = 0.0;
        Double debido = 0.0;
        Double aplicativo = 0.0;
        Double outro = 0.0;
        Double caixaliquido = 0.0;

        
        
        Double despesafuncionario = 0.0;
        Double despesaestabelecimento = 0.0;
        Double despesatotal = 0.0;

        Double caixaBrutoEstabelecimento = 0.0;
        
        

        for (Receita receita : receitas) {

            if (receita.getMetododepagamento() == 0) {
                dinheiro = dinheiro + receita.getLucrofuncionario();
                dinheiro = dinheiro + receita.getLucroestabelecimento();
                despesafuncionario = despesafuncionario + receita.getLucrofuncionario();
            }

            if (receita.getMetododepagamento() == 1) {
                deposito = deposito + receita.getLucrofuncionario();
                deposito = deposito + receita.getLucroestabelecimento();
                despesafuncionario = despesafuncionario + receita.getLucrofuncionario();
            }

            if (receita.getMetododepagamento() == 2) {
                credito = credito + receita.getLucrofuncionario();
                credito = credito + receita.getLucroestabelecimento();
                despesafuncionario = despesafuncionario + receita.getLucrofuncionario();
            }

            if (receita.getMetododepagamento() == 3) {
                debido = debido + receita.getLucrofuncionario();
                debido = debido + receita.getLucroestabelecimento();
                despesafuncionario = despesafuncionario + receita.getLucrofuncionario();
            }

            if (receita.getMetododepagamento() == 4) {
                aplicativo = aplicativo + receita.getLucrofuncionario();
                aplicativo = aplicativo + receita.getLucroestabelecimento();
                despesafuncionario = despesafuncionario + receita.getLucrofuncionario();
            }

            if (receita.getMetododepagamento() == 5) {
                outro = outro + receita.getLucrofuncionario();
                outro = outro + receita.getLucroestabelecimento();
                despesafuncionario = despesafuncionario + receita.getLucrofuncionario();
            }

        }
        
        for (Despesa despesa : despesas) {
            despesaestabelecimento += despesa.getValor();
        }

        int numerodeagendamento = receitas.size();
        int agendamentos = agendamentodao.procurarTodosAgendamentos(date).size();
        int agendamentocancelado = agendamentos - numerodeagendamento;
        
        despesatotal = despesaestabelecimento + despesafuncionario;
        caixaliquido = dinheiro + deposito + credito + debido + aplicativo + outro;
        caixaBrutoEstabelecimento = caixaliquido - despesatotal;

        DecimalFormat df = new DecimalFormat("0.00");

        
        view.getjLabelAGENDAMENTOSEFETUADOS().setText(numerodeagendamento+"");
        view.getjLabelAGENDAMENTOSCANCELADOS().setText(agendamentocancelado+"");
        view.getjLabelDINHEIRO().setText(df.format(dinheiro));
        view.getjLabelDEDOSITOBANCARIO().setText(df.format(deposito));
        view.getjLabelCARTAODECREDITO().setText(df.format(credito));
        view.getjLabelCARTAODEDEBIDO().setText(df.format(debido));
        view.getjLabelAPLICATIVOS().setText(df.format(aplicativo));
        view.getjLabelOUTROS().setText(df.format(outro));
        view.getjLabelCAIXALIQUIDO().setText(df.format(caixaliquido));
        view.getjLabelTOTALAGENDAMENTOS().setText((agendamentos) + "");
        view.getjLabelPAGAMENTOSFUNCIONARIOS().setText(df.format(despesafuncionario));
        view.getjLabelPAGAMENTOSREALIZADOS().setText(df.format(despesaestabelecimento));
        view.getjLabelDESPESA().setText(df.format(despesatotal));
        view.getjLabelCAIXABRUTOESTABELECIMENTO().setText(df.format(caixaBrutoEstabelecimento));
    }

    public void PreencherTabelaReceita(String date) {
        DecimalFormat df = new DecimalFormat("0.00");

        ArrayList<ReceitaFinal> receitas = new ArrayList<>();
        ReceitaDAO receitadao = new ReceitaDAO();
        receitas = receitadao.selectfromidanddate(date);
        DefaultTableModel dtmReceitas = (DefaultTableModel) view.getjTableRECEITAS().getModel();
        dtmReceitas.setNumRows(0);

        for (ReceitaFinal rec : receitas) {
            dtmReceitas.addRow(new Object[]{
                rec.getNome(),
                rec.getContagem(),
                df.format(rec.getValor())});

        }

    }

    public void PreencherLabeldeData(String date) {
        view.getjLabeDATE().setText(date);
    }

}

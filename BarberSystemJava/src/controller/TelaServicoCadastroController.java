/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.DAO.ServicoDAO;
import model.Exception.CamposInvalidosExceptionException;
import model.Servico;
import view.TelaServicoCadastro;

/**
 *
 * @author ALEFF
 */
public class TelaServicoCadastroController {

    private final TelaServicoCadastro view;

    public TelaServicoCadastroController(TelaServicoCadastro view) {
        this.view = view;
    }

    public void TratarCadastroServico() throws CamposInvalidosExceptionException {
        if (view.getjTextFieldNOME().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo nome vazio!");
        }

        if (view.getjTextFieldNOME().getText().length() < 5) {
            throw new CamposInvalidosExceptionException("Campo nome curto!");
        }

        if (view.getjTextFieldNOME().getText().length() > 30) {
            throw new CamposInvalidosExceptionException("Campo nome longo!");
        }

        if (view.getjTextFieldDESCRICAO().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo descricao vazia!");
        }

        if (view.getjTextFieldDESCRICAO().getText().length() < 5) {
            throw new CamposInvalidosExceptionException("Campo descricao vazia!");
        }

        if (view.getjTextFieldDESCRICAO().getText().length() > 30) {
            throw new CamposInvalidosExceptionException("Campo descricao vazia!");
        }
        if (view.getjFormattedTextFieldVALOR().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo valor vazio!");
        }

    }

    public void CadastrarServicoDaView() {
        try {
            TratarCadastroServico();

            String nome = view.getjTextFieldNOME().getText().toUpperCase();
            String descricao = view.getjTextFieldDESCRICAO().getText().toUpperCase();
            String valorFormatado = view.getjFormattedTextFieldVALOR().getText().replace(",", ".");
            int porcentagem = Integer.parseInt(view.getjTextFieldPORCENTAGEM().getText());
            Double valor = Double.parseDouble(valorFormatado);

            Servico servicoCadastro = new Servico(nome, valor, descricao, porcentagem);

            ServicoDAO servicodao = new ServicoDAO();

            if (servicodao.inserirServicoNoBanco(servicoCadastro)) {
                JOptionPane.showMessageDialog(view, "Servico Cadastrado com Sucesso!");

                view.dispose();

            }

        } catch (CamposInvalidosExceptionException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }

    }

    public void alterarValorPorcentagem() {
        view.getjTextFieldPORCENTAGEM().setText(view.getjSliderPORCENTAGEM().getValue() + "");

    }

    public void LimparTela() {

        view.getjTextFieldNOME().setText("");
        view.getjTextFieldDESCRICAO().setText("");
        view.getjFormattedTextFieldVALOR().setText("");

    }

    public void sairDaTela() {
        view.dispose();

    }

}

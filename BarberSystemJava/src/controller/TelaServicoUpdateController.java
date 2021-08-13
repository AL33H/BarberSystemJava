/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.DAO.ServicoDAO;
import model.Exception.CamposInvalidosExceptionException;
import model.Servico;
import view.TelaServicoUpdate;

/**
 *
 * @author ALEFF
 */
public class TelaServicoUpdateController {

    private final TelaServicoUpdate view;

    public TelaServicoUpdateController(TelaServicoUpdate view) {
        this.view = view;
    }

    public void sairDaTela() {
        view.dispose();
    }

    public void alterarValorPorcentagem() {
        view.getjTextFieldPORCENTAGEM().setText(view.getjSliderPORCENTAGEM().getValue() + "");
    }

    public void PreencherInformacoes(int idpesquisa) {
        ServicoDAO servicodao = new ServicoDAO();

        Servico servicodapesquisa = servicodao.procurarServicoID(idpesquisa);
        DecimalFormat df = new DecimalFormat("0.00");
        view.getjTextFieldid().setText(servicodapesquisa.getId() + "");
        view.getjTextFieldNOME().setText(servicodapesquisa.getNome());
        view.getjFormattedTextFieldVALOR().setText(df.format(servicodapesquisa.getValor()));
        view.getjTextFieldDESCRICAO().setText(servicodapesquisa.getDescricao());
        view.getjTextFieldPORCENTAGEM().setText(servicodapesquisa.getPorcentagem()+"");

    }

    public void Update() {

        try {
            ServicoDAO servicodao = new ServicoDAO();


            tratarCamposServicos();
            int id = Integer.parseInt(view.getjTextFieldid().getText());
            String nome = view.getjTextFieldNOME().getText();
            String descricao = view.getjTextFieldDESCRICAO().getText();
            Double valor = Double.parseDouble(view.getjFormattedTextFieldVALOR().getText().replace(",", "."));
            int lucro = Integer.parseInt(view.getjTextFieldPORCENTAGEM().getText());

            Servico servico = new Servico(id, nome, valor, descricao, lucro);

            if (servicodao.UpdateServico(servico)) {
                JOptionPane.showMessageDialog(view, "Servico Atualizado com Sucesso!");
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Servico não atualizado!");
            }

        } catch (CamposInvalidosExceptionException ex) {
            Logger.getLogger(TelaServicoUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void tratarCamposServicos() throws CamposInvalidosExceptionException {

        if (view.getjTextFieldNOME().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Nome não pode ser vazio!");
        }

        if (view.getjTextFieldDESCRICAO().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Descricao não pode ser vazio!");
        }

        if (view.getjFormattedTextFieldVALOR().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Valor não pode ser vazio!");
        }

        if (view.getjTextFieldPORCENTAGEM().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Porcentagem não pode ser vazio!");
        }

        if (view.getjTextFieldNOME().getText().length() <= 3) {
            throw new CamposInvalidosExceptionException("Nome curto!");
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.time.format.DateTimeFormatter;
import model.DAO.EstabelecimentoDAO;
import model.Estabelecimento;
import view.TelaSobre;

/**
 *
 * @author ALEFF
 */
public class TelaSobreController {

    private final TelaSobre view;

    public TelaSobreController(TelaSobre view) {
        this.view = view;
    }

    public void preencherInformacoes() {
        EstabelecimentoDAO estabelecimentodao = new EstabelecimentoDAO();
        Estabelecimento estab = estabelecimentodao.find();
        
        view.getjTextFieldNOME().setText(estab.getNome());
        view.getjFormattedTextFieldDATA().setText(estab.getData().format(DateTimeFormatter.ofPattern("d' de 'MMMM' de 'yyyy")));
        view.getjTextFieldVERSAO().setText(estab.getVersao());
    }

}

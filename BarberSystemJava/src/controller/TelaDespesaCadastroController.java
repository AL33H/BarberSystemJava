/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import model.DAO.DespesaDAO;
import model.Despesa;
import model.Exception.CamposInvalidosExceptionException;
import view.TelaDespesaCadastro;

/**
 *
 * @author ALEFF
 */
public class TelaDespesaCadastroController {

    private final TelaDespesaCadastro view;

    public TelaDespesaCadastroController(TelaDespesaCadastro view) {
        this.view = view;
    }

    public void InicializarTelaCadastroController() {
        if (view.getjRadioButtonAVISTA().isSelected()) {
            view.getjFormattedTextFieldQUANTIDADEDEPARCELAS().setVisible(false);
            view.getjFormattedTextFieldVALORDASPARCELAS().setVisible(false);
            view.getjLabelQUANTIDADEDEPARCELAS().setVisible(false);
            view.getjLabelVALORDASPARCELAS().setVisible(false);
        }
    }

    public void MudarStatusdoJbutton() {
        if (view.getjRadioButtonPARCELADO().isSelected()) {
            view.getjFormattedTextFieldQUANTIDADEDEPARCELAS().setVisible(true);
            view.getjFormattedTextFieldVALORDASPARCELAS().setVisible(true);
            view.getjLabelQUANTIDADEDEPARCELAS().setVisible(true);
            view.getjLabelVALORDASPARCELAS().setVisible(true);
        }

        if (view.getjRadioButtonAVISTA().isSelected()) {
            view.getjFormattedTextFieldQUANTIDADEDEPARCELAS().setVisible(false);
            view.getjFormattedTextFieldVALORDASPARCELAS().setVisible(false);
            view.getjLabelQUANTIDADEDEPARCELAS().setVisible(false);
            view.getjLabelVALORDASPARCELAS().setVisible(false);

            view.getjFormattedTextFieldQUANTIDADEDEPARCELAS().setText("");
            view.getjFormattedTextFieldVALORDASPARCELAS().setText("");
        }

    }

    public void calcularValorDasParcelas() {
        Double valor = Double.parseDouble(view.getjFormattedTextFieldVALOR().getText().replace(",", "."));
        int quantidadedeparcelas = Integer.parseInt(view.getjFormattedTextFieldQUANTIDADEDEPARCELAS().getText());
        Double valorfinal = valor / quantidadedeparcelas;
        DecimalFormat df = new DecimalFormat("0.00");
        view.getjFormattedTextFieldVALORDASPARCELAS().setText(df.format(valorfinal));
    }

    public void CalcularValorTotal() {
        int quantidadedeparcelas = Integer.parseInt(view.getjFormattedTextFieldQUANTIDADEDEPARCELAS().getText());
        Double valordasparcelas = Double.parseDouble(view.getjFormattedTextFieldVALORDASPARCELAS().getText().replace(",", "."));

        Double valor = quantidadedeparcelas * valordasparcelas;
        DecimalFormat df = new DecimalFormat("0.00");
        view.getjFormattedTextFieldVALOR().setText(df.format(valor));

    }

    public void TratarDespesa() throws CamposInvalidosExceptionException {
        if (view.getjTextFieldNOME().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo nome não pode se vazio!");
        }

        if (view.getjFormattedTextFieldVALOR().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo valor não pode se vazio!");
        }

        if (view.getjDateChooser1().toString().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo Data não pode se vazio!");
        }

        if (view.getjLabelQUANTIDADEDEPARCELAS().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo Parcelas não pode se vazio!");
        }

    }

    public void CadastrarDespesa() {

        Double valor;
        int parcelas;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            TratarDespesa();
            if (view.getjRadioButtonAVISTA().isSelected()) {
                valor = Double.parseDouble(view.getjFormattedTextFieldVALOR().getText().replace(",", "."));
                parcelas = 1;

                String nome = view.getjTextFieldNOME().getText().toUpperCase();
                String descricao = view.getjTextFieldDESCRICAO().getText().toUpperCase();
                String data = sdf.format(view.getjDateChooser1().getDate());

                Despesa despesa = new Despesa(nome, descricao, valor, data);
                DespesaDAO despesadao = new DespesaDAO();

                if (JOptionPane.showConfirmDialog(view, "Deseja inserir despesa? Nome: " + nome + "   Valor: " + df.format(valor)) == 0) {

                    if (despesadao.insert(despesa)) {
                        JOptionPane.showMessageDialog(view, "Despesa adicionada!");
                        view.dispose();
                    }
                }

            }

            if (view.getjRadioButtonPARCELADO().isSelected()) {
                valor = Double.parseDouble(view.getjFormattedTextFieldVALORDASPARCELAS().getText().replace(",", "."));
                parcelas = Integer.parseInt(view.getjFormattedTextFieldQUANTIDADEDEPARCELAS().getText());

                String nome = view.getjTextFieldNOME().getText().toUpperCase();
                String descricao = view.getjTextFieldDESCRICAO().getText().toUpperCase();
                String data = sdf.format(view.getjDateChooser1().getDate());

                LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));

                //int i = 1;
                int i = 0;

                DespesaDAO despesadao = new DespesaDAO();

                if (JOptionPane.showConfirmDialog(view, "Deseja adicionar despesa? Nome: " + nome + "   Parcelas: " + parcelas + "   Valor das parcelas: " + df.format(valor)) == 0) {

                    do {
                        LocalDate dataatual = datafinal.plusMonths(i);
                        Despesa despesa = new Despesa(nome + " " + (i+1) + "/" + parcelas, descricao, valor, dataatual);
                        despesadao.insert(despesa);
                        i++;

                    } while (i < parcelas);

                    JOptionPane.showMessageDialog(view, "Todas as Despesas adicionada!");
                    view.dispose();
                }

            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        } catch (CamposInvalidosExceptionException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }

    }

    public void limparTela() {
        view.getjTextFieldNOME().setText("");
        view.getjTextFieldDESCRICAO().setText("");
        view.getjFormattedTextFieldVALOR().setText("");

    }

}

package controller;

import com.toedter.calendar.JDateChooser;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cliente;
import model.DAO.ClienteDAO;
import model.Exception.CamposInvalidosExceptionException;
import view.TelaClienteCadastro;

/**
 *
 * @author ALEFF
 */
public class TelaClienteCadastroController {

    private final TelaClienteCadastro view;

    public TelaClienteCadastroController(TelaClienteCadastro view) {
        this.view = view;
    }

    public void tratarCadastroCliente() throws CamposInvalidosExceptionException {

        if (view.getjTextFieldNOME().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo nome vazio!");
        }

        if (view.getjTextFieldNOME().getText().length() < 5) {
            throw new CamposInvalidosExceptionException("Campo nome curto!");
        }

        if (view.getjTextFieldNOME().getText().length() > 30) {
            throw new CamposInvalidosExceptionException("Campo nome longo!");
        }

        if (view.getjTextFieldSOBRENOME().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo Sobrenome vazio!");
        }

        if (view.getjTextFieldSOBRENOME().getText().length() < 5) {
            throw new CamposInvalidosExceptionException("Campo sobrenome curto!");
        }

        if (view.getjTextFieldSOBRENOME().getText().length() > 30) {
            throw new CamposInvalidosExceptionException("Campo sobrenome longo!");
        }

        if (view.getjFormattedTextFieldWHATSAPP().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo whatsapp vazio!");
        }

        if (view.getjFormattedTextFieldWHATSAPP().getText().length() < 8) {
            throw new CamposInvalidosExceptionException("Campo whatsapp curto!");
        }

        if (view.getjFormattedTextFieldWHATSAPP().getText().length() > 14) {
            throw new CamposInvalidosExceptionException("Campo whatsapp longo!");
        }

        if (view.getjFormattedTextFieldCPF().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo CPF vazio!");
        }

        if (view.getjFormattedTextFieldCPF().getText().length() != 14) {
            throw new CamposInvalidosExceptionException("Campo CPF invalido");
        }

        if (view.getjTextFieldRUA().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo Rua vazia!");
        }

        if (view.getjTextFieldRUA().getText().length() > 30) {
            throw new CamposInvalidosExceptionException("Campo Rua longo!");
        }

        if (view.getjTextFieldNUMERO().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo Numero vazio!");
        }

        if (view.getjTextFieldNUMERO().getText().length() > 30) {
            throw new CamposInvalidosExceptionException("Campo Numero longo!");
        }

        if (view.getjTextFieldIBAIRRO().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo bairro vazio!");
        }

        if (view.getjTextFieldIBAIRRO().getText().length() < 5) {
            throw new CamposInvalidosExceptionException("Campo bairro curto!");
        }

        if (view.getjTextFieldIBAIRRO().getText().length() > 30) {
            throw new CamposInvalidosExceptionException("Campo bairro longo!");
        }

        if (view.getjTextFieldCOMPLEMENTO().getText().length() > 30) {
            throw new CamposInvalidosExceptionException("Campo complemento longo!");
        }

        if (view.getjTextFieldCIDADE().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo cidade vazia!");
        }

        if (view.getjTextFieldCIDADE().getText().length() < 5) {
            throw new CamposInvalidosExceptionException("Campo cidade curta!");
        }

        if (view.getjTextFieldCIDADE().getText().length() > 30) {
            throw new CamposInvalidosExceptionException("Campo cidade longo!");
        }

        if (view.getjTextFieldESTADO().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo estado vazio!");
        }

        if (view.getjTextFieldESTADO().getText().length() > 30) {
            throw new CamposInvalidosExceptionException("Campo estado longo!");
        }

    }

    public void CadastrarUsuarioDaView() {
        try {
            tratarCadastroCliente();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            String nome = view.getjTextFieldNOME().getText().toUpperCase();
            String sobrenome = view.getjTextFieldSOBRENOME().getText().toUpperCase();
            String telefone = view.getjFormattedTextFieldWHATSAPP().getText();
            String CPF = view.getjFormattedTextFieldCPF().getText();
            String sexo = view.getjComboBoxSEXO().getSelectedItem() + "";
            String dataDeNascimento = sdf.format(view.getjFormattedTextFieldDATA().getDate());
            String rua = view.getjTextFieldRUA().getText().toUpperCase();
            int numero = Integer.parseInt(view.getjTextFieldNUMERO().getText());
            String bairro = view.getjTextFieldIBAIRRO().getText().toUpperCase();
            String complemento = view.getjTextFieldCOMPLEMENTO().getText().toUpperCase();
            String cidade = view.getjTextFieldCIDADE().getText().toUpperCase();
            String estado = view.getjTextFieldESTADO().getText().toUpperCase();
            Cliente cliente = new Cliente(nome, sobrenome, sexo, dataDeNascimento, CPF, telefone, rua, numero, bairro, complemento, cidade, estado);
            ClienteDAO clientedao = new ClienteDAO();

            if (clientedao.inserirClientesNoBanco(cliente)) {
                JOptionPane.showMessageDialog(view, "Cliente Cadastrado com Sucesso!");
                view.dispose();
            }
        } catch (NumberFormatException ex) {
           JOptionPane.showMessageDialog(view, "Campo de Numero da residencia somente numeros!");
        } catch (NullPointerException np) {
            JOptionPane.showMessageDialog(view, "Campo data errada");
        } catch (CamposInvalidosExceptionException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(view, "Erro inesperado! Porfavor comunique o Administrador do Sistema!");
        }

    }

    public void LimparTela() {
        view.getjTextFieldNOME().setText("");
        view.getjTextFieldSOBRENOME().setText("");
        view.getjTextFieldCIDADE().setText("");
        view.getjTextFieldCOMPLEMENTO().setText("");
        view.getjTextFieldESTADO().setText("");
        view.getjTextFieldIBAIRRO().setText("");
        view.getjTextFieldNUMERO().setText("");
        view.getjTextFieldRUA().setText("");
        view.getjFormattedTextFieldCPF().setText("");
        view.getjFormattedTextFieldWHATSAPP().setText("");
    }

    public void sairDaTela() {
        view.dispose();

    }

}

package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.DAO.FuncionarioDAO;
import model.Exception.CamposInvalidosExceptionException;
import model.Funcionario;
import view.TelaFuncionarioCadastro;

/**
 *
 * @author ALEFF
 */
public class TelaFuncionarioCadastroController {

    private final TelaFuncionarioCadastro view;

    public TelaFuncionarioCadastroController(TelaFuncionarioCadastro view) {
        this.view = view;
    }

    public void TratarCadastroFuncionario() throws CamposInvalidosExceptionException {
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

        if (view.getjTextFieldFUNCAO().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campo funcao vazio!");
        }

        if (view.getjTextFieldFUNCAO().getText().length() < 5) {
            throw new CamposInvalidosExceptionException("Campo funcao curto!");
        }

        if (view.getjTextFieldFUNCAO().getText().length() > 30) {
            throw new CamposInvalidosExceptionException("Campo funcao longo!");
        }

    }

    public void CadastrarUsuarioDaView() {

        try {
            TratarCadastroFuncionario();

            String nome = view.getjTextFieldNOME().getText().toUpperCase();
            String sobrenome = view.getjTextFieldSOBRENOME().getText().toUpperCase();
            String funcao = view.getjTextFieldFUNCAO().getText().toUpperCase();
            String sexo = view.getjComboBoxSEXO().getSelectedItem() + "";

            Funcionario funcionario = new Funcionario(nome, sobrenome, sexo, funcao);

            FuncionarioDAO funcionariodao = new FuncionarioDAO();

            if (funcionariodao.inserirClientesNoBanco(funcionario)) {
                JOptionPane.showMessageDialog(view, "SALVO COM SUCESSO!");
                view.dispose();
            }

        } catch (CamposInvalidosExceptionException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(view, "Data selecionada Invalida");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(view, "Erro inesperado! Porfavor comunique o Administrador do Sistema!");
        }

    }

    public void LimparTela() {
        view.getjTextFieldNOME().setText("");
        view.getjTextFieldSOBRENOME().setText("");
        view.getjTextFieldFUNCAO().setText("");

    }

    public void sairDaTela() {
        view.dispose();

    }

}

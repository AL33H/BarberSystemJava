/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Agendamento;
import model.Cliente;
import model.DAO.AgendamentoDAO;
import model.DAO.ClienteDAO;
import model.DAO.FuncionarioDAO;
import model.DAO.ServicoDAO;
import model.Exception.CamposInvalidosExceptionException;
import model.Funcionario;
import model.Servico;
import view.TelaAgendamentoCadastro;

/**
 *
 * @author ALEFF
 */
public class TelaAgendamentoCadastroController {

    private final TelaAgendamentoCadastro view;

    public TelaAgendamentoCadastroController(TelaAgendamentoCadastro view) {
        this.view = view;
    }

    public void preencherJCOMBOBOXCLIENTE() {
        view.getjComboBoxCLIENTE().removeAllItems();
        ClienteDAO clientedao = new ClienteDAO();
        ArrayList<Cliente> procurarTodosClientes;

        procurarTodosClientes = clientedao.procurarTodosClientes();

        procurarTodosClientes.forEach((cli) -> {
            view.getjComboBoxCLIENTE().addItem(cli);
        });

    }

    public void preencherJCOMBOBOXSERVICO() {
        view.getjComboBoxSERVICO().removeAllItems();
        ServicoDAO servicodao = new ServicoDAO();

        ArrayList<Servico> procurarTodosServicos = servicodao.procurarTodosServicos();
        procurarTodosServicos.forEach((ser) -> {
            view.getjComboBoxSERVICO().addItem(ser);
        });

    }

    public void preencherJCOMBOBOXFUNCIONARIO() {
        view.getjComboBoxFUNCIONARIO().removeAllItems();
        FuncionarioDAO funcionariodao = new FuncionarioDAO();

        ArrayList<Funcionario> procurarTodosFuncionarios = funcionariodao.procurarTodosFuncionarios();

        procurarTodosFuncionarios.forEach((func) -> {
            view.getjComboBoxFUNCIONARIO().addItem(func);
        });

    }
    
    public void PreencherDataHj(){
    
    view.getjFormattedTextFieldDATA().setDate(Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
    }

    public void tratarModeloAgendamento() throws CamposInvalidosExceptionException {

        if (view.getjComboBoxCLIENTE().getItemCount() == 0) {
            throw new CamposInvalidosExceptionException("Não há nenhum cliente para Agendar! Por favor cadastrar.");
        }

        if (view.getjComboBoxFUNCIONARIO().getItemCount() == 0) {
            throw new CamposInvalidosExceptionException("Não há nenhum Funcionario para Agendar! Por favor cadastrar.");
        }

        if (view.getjComboBoxSERVICO().getItemCount() == 0) {
            throw new CamposInvalidosExceptionException("Não há nenhum Servico para Agendar! Por favor cadastrar.");
        }

        if (view.getjComboBoxCLIENTE().getSelectedIndex() < 0) {
            throw new CamposInvalidosExceptionException("Cliente Invalido!");
        }

        if (view.getjComboBoxFUNCIONARIO().getSelectedIndex() < 0) {
            throw new CamposInvalidosExceptionException("Funcionario Invalido!");
        }

        if (view.getjComboBoxSERVICO().getSelectedIndex() < 0) {
            throw new CamposInvalidosExceptionException("Servico Invalido!");
        }
        
        if (view.getjFormattedTextFieldDATA().getDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CamposInvalidosExceptionException("Não pode agendar no passado");
        }

    }

    public void buscarpreco() {
        try {
            Servico selectedItem = (Servico) view.getjComboBoxSERVICO().getSelectedItem();
            view.getjFormattedTextFieldVALOR().setValue(selectedItem.getValor());
        } catch (NullPointerException e) {

        }

    }
    
    

    public void CadastrarAgendamento() {

        try {
            tratarModeloAgendamento();
            int selectedIndexCliente = view.getjComboBoxCLIENTE().getSelectedIndex();
            Cliente Cliente = view.getjComboBoxCLIENTE().getItemAt(selectedIndexCliente);
            int selectedIndexServico = view.getjComboBoxSERVICO().getSelectedIndex();
            Servico Servico = view.getjComboBoxSERVICO().getItemAt(selectedIndexServico);
            int selectedIndexFuncionario = view.getjComboBoxFUNCIONARIO().getSelectedIndex();
            Funcionario Funcionario = view.getjComboBoxFUNCIONARIO().getItemAt(selectedIndexFuncionario);

            Double valor = Double.parseDouble(view.getjFormattedTextFieldVALOR().getText().replace(",", "."));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String data = sdf.format(view.getjFormattedTextFieldDATA().getDate());
            String hora = view.getjFormattedTextFieldDATAHORA().getText();

            LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));

            if (datafinal.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(view, "Não é permitido agendar no passado!");
            } else {

                Agendamento agendamento = new Agendamento(Cliente, Servico, Funcionario, valor, data, hora, 0);
                AgendamentoDAO agendamentodao = new AgendamentoDAO();

                if (agendamentodao.inserirClientesNoBanco(agendamento)) {
                    JOptionPane.showMessageDialog(view, "Agendamento inserido!");
                    view.dispose();
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Campoes não preenchidos corretamente");

        } catch (CamposInvalidosExceptionException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());

        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(view, "Erro inesperado!");
        }

    }

    public Double calcularPorcentagemFuncionario(int porcentagem, Double valor) {
        return (70 / 100.0) * 100.0;
    }

}

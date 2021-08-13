/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Frame;
import javax.swing.JOptionPane;
import model.DAO.UsuarioDAO;
import model.Usuario;
import view.TelaClienteCadastro;
import view.TelaFuncionarioCadastro;
import view.TelaAgendamento;
import view.TelaCliente;
import view.TelaDespesa;
import view.TelaFuncionario;
import view.TelaServicoCadastro;
import view.TelaUsuarioCadastro;
import view.TelaLogin;
import view.TelaLoginSplash;
import view.TelaMenu;
import view.TelaRankingDeClientes;
import view.TelaRelatorios;
import view.TelaServico;
import view.TelaSobre;
import view.TelaUsuario;

/**
 *
 * @author ALEFF
 */
public class TelaMenuController {

    private final TelaMenu view;

    public TelaMenuController(TelaMenu view) {
        this.view = view;
    }

    public void TrocarUsuario() {
        if (TelaLogin.instance == null) {
            new TelaLogin().setVisible(true);
        } else {
            TelaLogin.instance.dispose();
        }
    }

    public void Deslogar() {
        TelaLogin telalogin = new TelaLogin();
        telalogin.setVisible(true);
        view.dispose();
    }

    public void sairDoSistema() {
        int showConfirmDialog = JOptionPane.showConfirmDialog(view, "Deseja encerrar o Sistema?");

        if (showConfirmDialog == 0) {
            System.exit(0);
        }

    }

    public void mostrarUsuarioENiveldeAcesso(Usuario usuario) {

        view.getjLabelUSUARIO().setText(usuario.getUsuario());

        if (usuario.getNivelDeAcesso() < 3) {
            view.getjMenuItemUSUARIO().setVisible(false);
        }

    }

    public void carregar() {
        TelaLoginSplash telaloginsplash = new TelaLoginSplash();
        telaloginsplash.setVisible(true);
        telaloginsplash.toFront();
        telaloginsplash.requestFocus();

    }

    public void chamarTelaUsuario() {

        if (TelaUsuario.instance == null) {
            new TelaUsuario().setVisible(true);
        } else {
            TelaUsuario.instance.dispose();

        }
    }

    public void chamarTelaServico(Usuario usuario) {
        if (TelaServico.instance == null) {
            new TelaServico().setVisible(true);
        } else {
            TelaServico.instance.dispose();

        }
    }

    public void chamarTelaCliente(Usuario usuario) {
        if (TelaCliente.instance == null) {
            new TelaCliente().setVisible(true);
        } else {
            TelaCliente.instance.dispose();
        }
    }

    public void chamarTelaFuncionario(Usuario usuario) {
        if (TelaFuncionario.instance == null) {
            new TelaFuncionario().setVisible(true);
        } else {
            TelaFuncionario.instance.dispose();
        }

    }

    public void chamarTelaAgendamento(Usuario usuario) {
        if (TelaAgendamento.instance == null) {
            new TelaAgendamento().setVisible(true);
        } else {
            TelaAgendamento.instance.dispose();

        }
    }

    public void chamarTelaDespesa() {
        if (TelaDespesa.instance == null) {
            new TelaDespesa().setVisible(true);
        } else {
            TelaDespesa.instance.dispose();

        }
    }

    public void chamarTelaRelatorios() {
        if (TelaRelatorios.instance == null) {
            new TelaRelatorios().setVisible(true);
        } else {
            TelaRelatorios.instance.dispose();
        }
    }

    public void chamarTelaReceitas() {

    }

    public void chamarTelaRankingDeClientes() {
        if (TelaRankingDeClientes.instance == null) {
            new TelaRankingDeClientes().setVisible(true);
        } else {
            TelaRankingDeClientes.instance.dispose();
        }
    }

    public void chamarTelaSobre() {
        if (TelaSobre.instance == null) {
            new TelaSobre().setVisible(true);
        } else {
            TelaSobre.instance.dispose();
        }

    }

}

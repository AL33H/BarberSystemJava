/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.DAO.Conexao;
import model.DAO.UsuarioDAO;
import model.Exception.CamposInvalidosExceptionException;
import model.Usuario;
import view.TelaUsuarioCadastro;

/**
 *
 * @author ALEFF
 */
public class TelaUsuarioCadastroController {
    
    private final TelaUsuarioCadastro view;
    
    public TelaUsuarioCadastroController(TelaUsuarioCadastro view) {
        this.view = view;
    }
    
    public void TratarCamposDaView() throws CamposInvalidosExceptionException {
        if (view.getjTextFieldUSUARIO().getText().isEmpty() || view.getjPasswordFieldSENHA().getText().isEmpty() || view.getjPasswordFieldCONFIRMARSENHA().getText().isEmpty()) {
            throw new CamposInvalidosExceptionException("Campos não preenchidos");
        }
        
        if (view.getjTextFieldUSUARIO().getText().length() < 6) {
            throw new CamposInvalidosExceptionException("Usuario curto");
        }
        
        if (view.getjTextFieldUSUARIO().getText().length() > 20) {
            throw new CamposInvalidosExceptionException("Usuario Longo");
        }
        
        if (view.getjPasswordFieldSENHA().getPassword().length < 6) {
            throw new CamposInvalidosExceptionException("Senha muito curta");
            
        }
        
        if (view.getjPasswordFieldSENHA().getPassword().length > 30) {
            throw new CamposInvalidosExceptionException("Senha muito longa");
            
        }
        if (!view.getjPasswordFieldCONFIRMARSENHA().getText().equals(view.getjPasswordFieldSENHA().getText())) {
            throw new CamposInvalidosExceptionException("Confirmação de senha invalida!");
            
        }
        
    }
    
    public void CadastrarUsuarioDaView() {
        
        try {
            TratarCamposDaView();
            String usuario = view.getjTextFieldUSUARIO().getText();
            String senha1 = view.getjPasswordFieldSENHA().getText();
            int nivelDeAcesso = view.getjComboBoxNIVELDEACESSO().getSelectedIndex() + 1;
            
            Usuario usuarioCadastro = new Usuario(usuario, senha1, nivelDeAcesso);
            UsuarioDAO usuariodao = new UsuarioDAO();
            
            Usuario existePorUsuario = usuariodao.existePorUsuario(usuarioCadastro.getUsuario());
            
            if (existePorUsuario.getUsuario() == null) {
                
                if (usuariodao.inserirUsuarioNoBanco(usuarioCadastro)) {
                    JOptionPane.showMessageDialog(view, "Usuario Cadastrado com Sucesso!");
                    view.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(view, "Usuario nome já cadastrado!");
                
            }
            
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(view, "Campos invalidos");
        } catch (CamposInvalidosExceptionException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }
    
    public void LimparTela() {
        
        view.getjTextFieldUSUARIO().setText("");
        view.getjPasswordFieldSENHA().setText("");
        view.getjPasswordFieldCONFIRMARSENHA().setText("");
        
    }
    
    public void sairDaTela() {
        view.dispose();
        
    }
    
}

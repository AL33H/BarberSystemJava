package controller;

import javax.swing.JOptionPane;
import model.Usuario;
import view.TelaAgendamento;
import view.TelaCliente;
import view.TelaFuncionario;
import view.TelaServico;
import view.TelaUsuario;

/**
 *
 * @author ALEFF
 */
public class NivelDeAcessibilidadeController {

    public NivelDeAcessibilidadeController(TelaCliente view, Usuario usuario) {
        try {
            if (usuario.getNivelDeAcesso() == 1) {
                JOptionPane.showMessageDialog(view, usuario.getUsuario());
                view.getjButtonADICIONAR().setEnabled(false);
                view.getjButtonREMOVER().setEnabled(false);
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
    }

    public NivelDeAcessibilidadeController(TelaServico view, Usuario usuario) {
        try  {
            if (usuario.getNivelDeAcesso() == 1) {
                view.getjButtonADICIONAR().setEnabled(false);
                view.getjButtonREMOVER().setEnabled(false);
            }
        }catch (NullPointerException e) {
            e.getMessage();
        }
    }

    public NivelDeAcessibilidadeController(TelaFuncionario view, Usuario usuario) {
        try {
            if (usuario.getNivelDeAcesso() == 1) {
                view.getjButtonADICIONAR().setEnabled(false);
                view.getjButtonREMOVER().setEnabled(false);
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
    }

    public NivelDeAcessibilidadeController(TelaAgendamento view, Usuario usuario) {
        try {
            if (usuario.getNivelDeAcesso() == 1) {
                view.getjButtonAGENDAR().setEnabled(false);
                view.getjButtonREMOVER().setEnabled(false);
                view.getjButtonFINALIZAR().setEnabled(false);
                view.getjButtonCANCELAR().setEnabled(false);
                
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
    }
}

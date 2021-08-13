/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import model.Usuario;
import view.TelaLoginSplash;
import view.TelaMenu;

/**
 *
 * @author ALEFF
 */
public class TelaLoginSplashController {

    private final TelaLoginSplash view;

    public TelaLoginSplashController(TelaLoginSplash view) {
        this.view = view;
        JDialog jd = new JDialog();
        jd.setModal(true);
    }

    public void Inicializar() {
        view.getjProgressBar().setStringPainted(true);
        view.setLocationRelativeTo(null);
        new Thread() {
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    try {

                        view.getjProgressBar().setValue(i);

                        if (view.getjProgressBar().getValue() < 25) {
                            sleep(65);

                            view.getjLabelDESCRICOA().setText("Carregando Sistema!");
                        } else if (view.getjProgressBar().getValue() > 25 && view.getjProgressBar().getValue() < 70) {
                            sleep(95);
                            view.getjLabelDESCRICOA().setText("Carregando banco de Dados!");
                        } else if (view.getjProgressBar().getValue() > 70 && view.getjProgressBar().getValue() < 100) {

                            sleep(23);
                            view.getjLabelDESCRICOA().setText("Inicializando Sistema!");
                        }

                    } catch (InterruptedException ex) {
                        Logger.getLogger(view.TelaLoginSplash.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                Usuario usuario = new Usuario();
                //TelaMenu menu = new TelaMenu();

                //menu.setVisible(true);
                view.dispose();
            }

        }.start();

    }

}

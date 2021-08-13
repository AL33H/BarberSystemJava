package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import model.Cliente;
import model.DAO.ClienteDAO;
import model.DAO.HistoricoDoClienteDAO;
import view.TelaRankingDeClientes;

/**
 *
 * @author ALEFF
 */
public class TelaRankingDeClientesController {

    private final TelaRankingDeClientes view;

    public TelaRankingDeClientesController(TelaRankingDeClientes view) {
        this.view = view;
    }

    public void LimparDados() {
        view.getjLabelCLIENTE1().setText("");
        view.getjLabelCLIENTE2().setText("");
        view.getjLabelCLIENTE3().setText("");
        view.getjLabelCLIENTE4().setText("");
        view.getjLabelCLIENTE5().setText("");

    }

    public void PreencherDados() {
        LimparDados();
        //pegar todos os clientes
        view.getjMonthChooserDATA().getMonth();
        view.getjYearChooserANO().getYear();

        DecimalFormat df = new DecimalFormat("00");

        HistoricoDoClienteDAO historico = new HistoricoDoClienteDAO();
        ArrayList<String> BuscarHistorico = historico.BuscarHistorico("01/" + df.format(view.getjMonthChooserDATA().getMonth() + 1) + "/" + view.getjYearChooserANO().getYear());

        switch (BuscarHistorico.size()) {

            case 1:
                view.getjLabelCLIENTE1().setText(BuscarHistorico.get(0));
                break;
            case 2:;
                view.getjLabelCLIENTE1().setText(BuscarHistorico.get(0));
                view.getjLabelCLIENTE2().setText(BuscarHistorico.get(1));
                break;
            case 3:
                view.getjLabelCLIENTE1().setText(BuscarHistorico.get(0));
                view.getjLabelCLIENTE2().setText(BuscarHistorico.get(1));
                view.getjLabelCLIENTE3().setText(BuscarHistorico.get(2));
                break;
            case 4:
                view.getjLabelCLIENTE1().setText(BuscarHistorico.get(0));
                view.getjLabelCLIENTE2().setText(BuscarHistorico.get(1));
                view.getjLabelCLIENTE3().setText(BuscarHistorico.get(2));
                view.getjLabelCLIENTE4().setText(BuscarHistorico.get(3));
                break;
            case 5:
                view.getjLabelCLIENTE1().setText(BuscarHistorico.get(0));
                view.getjLabelCLIENTE2().setText(BuscarHistorico.get(1));
                view.getjLabelCLIENTE3().setText(BuscarHistorico.get(2));
                view.getjLabelCLIENTE4().setText(BuscarHistorico.get(3));
                view.getjLabelCLIENTE5().setText(BuscarHistorico.get(4));
                break;

        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DAO.DespesaDAO;
import model.Despesa;
import view.TelaDespesa;
import view.TelaDespesaCadastro;

/**
 *
 * @author ALEFF
 */
public class TelaDespesaController {

    private final TelaDespesa view;

    public TelaDespesaController(TelaDespesa view) {
        this.view = view;
    }

    public void mudarDataPeloJChooser() {

        SimpleDateFormat sdf = new SimpleDateFormat("'MMMM de yyyy'");
        DecimalFormat df2 = new DecimalFormat("00");

        String data = "01/" + df2.format(view.getjMonthChooser1().getSelectedIndex() + 1) + "/" + view.getjYearChooser1().getYear() + "";

        LocalDate datafinal = LocalDate.of(Integer.parseInt(data.substring(6, 10)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));

        view.getjLabelMOSTRARDATA().setText(datafinal.format(DateTimeFormatter.ofPattern("MMMM 'de' yyyy")));
    }

    public void InicializarJChooser() {
        String date = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        view.getjMonthChooser1().setSelectedItem(LocalDate.now().getMonthValue());
        view.getjYearChooser1().setYear(LocalDate.now().getYear());
        mudarDataPeloJChooser();
    }

    public void PreencherTabelDespesa() {

        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        SimpleDateFormat sdfdata = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat df2 = new DecimalFormat("00");
        String data = "01/" + df2.format(view.getjMonthChooser1().getSelectedIndex() + 1) + "/" + view.getjYearChooser1().getYear() + "";

        DecimalFormat df = new DecimalFormat("0.00");
        ArrayList<Despesa> despesas = new ArrayList<>();
        DespesaDAO despesadao = new DespesaDAO();

        despesas = despesadao.selectFromMonthYear(data);
        DefaultTableModel dtmReceitas = (DefaultTableModel) view.getjTableDESPESAS().getModel();
        dtmReceitas.setNumRows(0);

        for (Despesa des : despesas) {
            dtmReceitas.addRow(new Object[]{
                des.getId(),
                des.getNome(),
                des.getDescricao(),
                df.format(des.getValor()),
                des.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))});

        }

    }

    public void chamarTelaDespesaCadastro() {
        if (TelaDespesaCadastro.instance == null) {
            new TelaDespesaCadastro().setVisible(true);
        } else {
            TelaDespesaCadastro.instance.dispose();
        }

    }

    public void removerDespesa() {
        int selectedRow = view.getjTableDESPESAS().getSelectedRow();

        if (selectedRow >= 0) {
            int id = (int) view.getjTableDESPESAS().getValueAt(selectedRow, 0);
            DespesaDAO despesadao = new DespesaDAO();
            if (despesadao.deletefromId(id)) {
                JOptionPane.showMessageDialog(view, "Despesa removida com sucecsso!");
                PreencherTabelDespesa();
            } else {
                JOptionPane.showMessageDialog(view, "Error ao remover despesa!");

            }
        } else {
            JOptionPane.showMessageDialog(view, "Despesa não selecionado!");
        }

    }

    public void sairDaTela() {
        view.dispose();
    }
}

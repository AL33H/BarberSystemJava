package controller;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DAO.ServicoDAO;
import model.Servico;
import view.TelaServico;
import view.TelaServicoCadastro;
import view.TelaServicoUpdate;

/**
 *
 * @author ALEFF
 */
public class TelaServicoController {

    private final TelaServico view;

    public TelaServicoController(TelaServico view) {
        this.view = view;
    }

    public void PreencherTabelaServico() {
        ArrayList<Servico> servicos = new ArrayList<>();
        ServicoDAO usuariodao = new ServicoDAO();

        servicos = usuariodao.procurarTodosServicos();

        DefaultTableModel dtmReceitas = (DefaultTableModel) view.getjTableSERVICO().getModel();
        dtmReceitas.setNumRows(0);

        DecimalFormat df = new DecimalFormat("##0.00");

        for (Servico ser : servicos) {
            dtmReceitas.addRow(new Object[]{
                ser.getId(),
                ser.getNome(),
                df.format(ser.getValor()),
                ser.getDescricao(),
                ser.getPorcentagem(),});
        }
    }

    public void apagarServicoSelecionado() {
        int id = pegarClienteSelecionado();
        if (id > 0) {
            int showInternalConfirmDialog = JOptionPane.showConfirmDialog(null, "Deseja apagar o Usuario");
            if (showInternalConfirmDialog == 0) {
                ServicoDAO servicodao = new ServicoDAO();

                boolean deletarClienteSelecionado;

                deletarClienteSelecionado = servicodao.delete(id);

                if (deletarClienteSelecionado) {
                    JOptionPane.showMessageDialog(view, "Servico apagado!");
                    PreencherTabelaServico();

                } else {
                    JOptionPane.showMessageDialog(view, "Não é possivel apagar esse Serviço!");
                }

            }
        }
    }

    public int pegarClienteSelecionado() {
        int selectedRow = view.getjTableSERVICO().getSelectedRow();

        if (selectedRow >= 0) {
            return (int) view.getjTableSERVICO().getValueAt(selectedRow, 0);
        } else {
            JOptionPane.showMessageDialog(view, "Servico não selecionado!");
        }
        return 0;
    }

    public void chamarTelaServicoCadastro() {
        if (TelaServicoCadastro.instance == null) {
            new TelaServicoCadastro().setVisible(true);
        } else {
            TelaServicoCadastro.instance.dispose();
        }

    }

    public void SairDaTela() {
        view.dispose();

    }

    public void chamarTelaUpdate() {

        int selectedRow = view.getjTableSERVICO().getSelectedRow();
        int id;
        if (selectedRow >= 0) {
            id = (int) view.getjTableSERVICO().getValueAt(selectedRow, 0);

            if (TelaServicoUpdate.instance == null) {
                new TelaServicoUpdate(id).setVisible(true);
            } else {
                TelaServicoUpdate.instance.dispose();
            }

        } else {
            JOptionPane.showMessageDialog(view, "Cliente não selecionado!");
        }

    }

}

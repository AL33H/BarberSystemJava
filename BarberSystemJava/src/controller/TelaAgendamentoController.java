package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Agendamento;
import model.DAO.AgendamentoDAO;
import model.DAO.FuncionarioDAO;
import model.DAO.HistoricoDoClienteDAO;
import model.DAO.HistoricoDoFuncionarioDAO;
import model.DAO.ReceitaDAO;
import model.HistoricoDoCliente;
import model.HistoricoDoFuncionario;
import model.Receita;
import view.MetodoDePagamento;
import view.TelaAgendamento;
import view.TelaAgendamentoCadastro;
import view.TelaCaixaDiario;
import view.TelaDespesa;

/**
 *
 * @author ALEFF
 */
public class TelaAgendamentoController {

    private final TelaAgendamento view;

    public TelaAgendamentoController(TelaAgendamento view) {
        this.view = view;
    }

    public void chamarTelaAgendamentoCadastro() {
        if (TelaAgendamentoCadastro.instance == null) {
            new TelaAgendamentoCadastro().setVisible(true);
        } else {
            TelaAgendamentoCadastro.instance.dispose();
        }

    }

    public void mudarDataPeloJChooser() {

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d' de 'MMMM' de 'yyyy");
        String teste = sdf.format(view.getjDateChooser1().getDate());
        view.getjLabelMOSTRARDATA().setText(teste.toUpperCase());
    }

    public void InicializarJChooser() {
        Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        view.getjDateChooser1().setDate(date);
        mudarDataPeloJChooser();
    }

    public void apagarAgendamentoSelecionado() {

        int id = pegarAgendamentoSelecionado();
        int showInternalConfirmDialog = 1;
        if (id > 0) {
            showInternalConfirmDialog = JOptionPane.showConfirmDialog(null, "Deseja apagar o Agendamento?");
        }
        if (showInternalConfirmDialog == 0) {
            AgendamentoDAO agendamentodao = new AgendamentoDAO();

            boolean deletarClienteSelecionado;

            deletarClienteSelecionado = agendamentodao.delete(id);

            if (deletarClienteSelecionado) {
                JOptionPane.showMessageDialog(view, "Agendamento apagado!");
                preencherTabelaAgendamento();
            }

        }
    }

    public void chamarTelaReceita() {
        if (TelaCaixaDiario.instance == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            new TelaCaixaDiario(sdf.format(view.getjDateChooser1().getDate())).setVisible(true);
        } else {
            TelaCaixaDiario.instance.dispose();
        }

    }

    public void chamarTelaDespesa() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (TelaDespesa.instance == null) {
            new TelaDespesa(sdf.format(view.getjDateChooser1().getDate())).setVisible(true);
        } else {
            TelaDespesa.instance.dispose();
        }
    }

    public int pegarAgendamentoSelecionado() {
        int selectedRow = view.getjTableAGENDAMENTO().getSelectedRow();

        if (selectedRow >= 0) {
            return (int) view.getjTableAGENDAMENTO().getValueAt(selectedRow, 0);
        } else {
            JOptionPane.showMessageDialog(view, "Agendamento não selecionado!");
        }
        return 0;
    }

    public void preencherTabelaAgendamento() {
        ArrayList<Agendamento> agendamentos = new ArrayList<>();

        AgendamentoDAO agendamentodao = new AgendamentoDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("0.00");
        String data = sdf.format(view.getjDateChooser1().getDate());

        agendamentos = agendamentodao.procurarTodosAgendamentos(data);

        DefaultTableModel dtmReceitas = (DefaultTableModel) view.getjTableAGENDAMENTO().getModel();
        dtmReceitas.setNumRows(0);

        for (Agendamento age : agendamentos) {

            dtmReceitas.addRow(new Object[]{
                age.getId(),
                age.getCliente().getNome(),
                age.getServico().getNome(),
                age.getFuncionario().getNome(),
                df.format(age.getValor()),
                age.getHora(),
                age.getStatus()});

        }

    }

    public void preencherTabelaAgendamento(ArrayList<Agendamento> agendamentos) {

        AgendamentoDAO agendamentodao = new AgendamentoDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("0.00");

        DefaultTableModel dtmReceitas = (DefaultTableModel) view.getjTableAGENDAMENTO().getModel();
        dtmReceitas.setNumRows(0);

        for (Agendamento age : agendamentos) {

            dtmReceitas.addRow(new Object[]{
                age.getId(),
                age.getCliente().getNome(),
                age.getServico().getNome(),
                age.getFuncionario().getNome(),
                df.format(age.getValor()),
                age.getHora(),
                age.getStatus()});

        }

    }

    public void Filtrar() {
        int selectedItem = (int) view.getjComboBoxCOMBOBOXFILTRO().getSelectedIndex();

        AgendamentoDAO agendamento = new AgendamentoDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String data = sdf.format(view.getjDateChooser1().getDate());

        ArrayList<Agendamento> procurarFILTROparaTodosAgendamentos = agendamento.procurarFILTROparaTodosAgendamentos(view.getjTextFieldFILTROTEXTO().getText(), data, selectedItem);
        preencherTabelaAgendamento(procurarFILTROparaTodosAgendamentos);

    }

    public void mudarStatusAgendamentoConcluido() {

        AgendamentoDAO agendamentodao = new AgendamentoDAO();
        int selectedRow = view.getjTableAGENDAMENTO().getSelectedRow();
        int id_agendamento;
        int status;

        if (selectedRow >= 0) {
            id_agendamento = (int) view.getjTableAGENDAMENTO().getValueAt(selectedRow, 0);
            status = (int) view.getjTableAGENDAMENTO().getValueAt(selectedRow, 6);

            if (status == 0) {

                MetodoDePagamento metododepagamento = new MetodoDePagamento(id_agendamento);
                metododepagamento.setVisible(true);

                preencherTabelaAgendamento();

            }
        } else {
            JOptionPane.showMessageDialog(view, "Agendamento não selecionado!");
        }

    }

    public void mudarStatusAgendamentoCancelado() {
        int showConfirmDialog = JOptionPane.showConfirmDialog(view, "Deseja cancelar o agendamento?");

        if (showConfirmDialog == 0) {
            int selectedRow = view.getjTableAGENDAMENTO().getSelectedRow();
            int id_agendamento;
            int status;
            if (selectedRow >= 0) {
                id_agendamento = (int) view.getjTableAGENDAMENTO().getValueAt(selectedRow, 0);

                status = (int) view.getjTableAGENDAMENTO().getValueAt(selectedRow, 6);
                if (status == 0) {
                    AgendamentoDAO agendamentodao = new AgendamentoDAO();

                    agendamentodao.updateto2(id_agendamento);

                    preencherTabelaAgendamento();
                    JOptionPane.showMessageDialog(view, "Status do Agendamento Atualizado para Cancelado!");

                } else {
                    JOptionPane.showMessageDialog(view, "Status já marcado como Realizado/Cancelado");

                }

            }

        }
    }

    public void SairDaTela() {
        view.dispose();

    }

}

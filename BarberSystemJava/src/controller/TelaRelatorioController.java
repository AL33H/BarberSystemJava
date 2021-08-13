/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.DAO.DespesaDAO;
import model.DAO.ReceitaDAO;
import model.Despesa;
import model.Exception.CamposInvalidosExceptionException;
import model.Receita;
import model.ReceitaFinal;
import view.TelaRelatorios;

/**
 *
 * @author ALEFF
 */
public class TelaRelatorioController {

    private final TelaRelatorios view;

    public TelaRelatorioController(TelaRelatorios view) {
        this.view = view;
    }

    public void tratarDatas() throws CamposInvalidosExceptionException {
        if (view.getjDateChooserINICIO().toString().isEmpty()) {
            throw new CamposInvalidosExceptionException("Data Invalida");
        }

        if (view.getjDateChooserFINAL().toString().isEmpty()) {
            throw new CamposInvalidosExceptionException("Data Invalida");
        }

    }

    public void GerarRelatorioPDF() {

        Document document = new Document();

        try {
            tratarDatas();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataInicio = sdf.format(view.getjDateChooserINICIO().getDate());
            String dataFinal = sdf.format(view.getjDateChooserFINAL().getDate());

            //LOCAL DO ARQUIVO
            PdfWriter.getInstance(document, new FileOutputStream("C:/Users/ALEFF/Documents/BarberSoftware/Pdf.PDF"));

            //ABRIR DOCUMENTO
            document.open();

            //FONTES
            Font red = FontFactory.getFont(FontFactory.TIMES_BOLD, Font.DEFAULTSIZE, Font.BOLD);
            Font blue = FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.ITALIC);
            Font title = FontFactory.getFont(FontFactory.TIMES_BOLD, 25, Font.BOLD);
            Font subtitle = FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD);
            Font TableHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
            Font Tablelement = FontFactory.getFont(FontFactory.TIMES, 9);

            //FORMATAÇÃO, ARRAYLIST E OUTROS
            ReceitaDAO receitadao = new ReceitaDAO();
            DespesaDAO despesadao = new DespesaDAO();

            DecimalFormat df = new DecimalFormat("0.00");

            ArrayList<ReceitaFinal> selectfromdate = receitadao.selectfromTwoDatesReturningMonth(dataInicio, dataFinal);
            ArrayList<Receita> receitas = new ArrayList<>();
            ArrayList<Despesa> despesas = new ArrayList<>();

            receitas = receitadao.selectFromMonth(dataInicio, dataFinal);
            despesas = despesadao.selectFromTwoDates(dataInicio, dataFinal);

            ////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////
            document.add(new Paragraph("                       Relatorio Financeiro", title));

            document.add(new Paragraph("_________________________________________________________________________"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("PERIODO: " + dataInicio + " Até " + dataFinal));

            document.add(new Paragraph("DATA DA CONSULTA: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss")) + ""));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("_________________________________________________________________________"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("        FUNCIONARIO", subtitle));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            
            PdfPCell cell0 = new PdfPCell(new Paragraph("MÊS", TableHeader));
            PdfPCell cell1 = new PdfPCell(new Paragraph("FUNCIONARIO", TableHeader));
            PdfPCell cell2 = new PdfPCell(new Paragraph("SERVIÇOS", TableHeader));
            PdfPCell cell3 = new PdfPCell(new Paragraph("COMISSÃO FUNCIONARIOS", TableHeader));

            table.addCell(cell0);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);

            int contagemTotal = 0;
            Double ValorTotal = 0.0;

            for (ReceitaFinal receita : selectfromdate) {

                cell0 = new PdfPCell(new Paragraph(receita.getMes().toUpperCase() + "", Tablelement));
                cell1 = new PdfPCell(new Paragraph(receita.getNome() + "", Tablelement));
                cell2 = new PdfPCell(new Paragraph(receita.getContagem() + "", Tablelement));
                cell3 = new PdfPCell(new Paragraph(df.format(receita.getValor()) + "", Tablelement));

                table.addCell(cell0);
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);

                contagemTotal += receita.getContagem();
                ValorTotal += receita.getValor();

            }

            document.add(table);
            document.add(new Paragraph("_________________________________________________________________________"));

            ////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////
            document.add(new Paragraph(" "));
            document.add(new Paragraph("        EMPRESA", subtitle));
            document.add(new Paragraph(" "));

            PdfPTable table2 = new PdfPTable(6);
            PdfPCell cell11 = new PdfPCell(new Paragraph("MÊS", TableHeader));
            PdfPCell cell22 = new PdfPCell(new Paragraph("SERVIÇOS EFETUADOS", TableHeader));
            PdfPCell cell33 = new PdfPCell(new Paragraph("RECEITAS", TableHeader));
            PdfPCell cell44 = new PdfPCell(new Paragraph("DESPESAS", TableHeader));
            PdfPCell cell55 = new PdfPCell(new Paragraph("COMISSÃO FUNCIONARIOS", TableHeader));
            PdfPCell cell66 = new PdfPCell(new Paragraph("SALDO", TableHeader));

            table2.addCell(cell11);
            table2.addCell(cell22);
            table2.addCell(cell33);
            table2.addCell(cell44);
            table2.addCell(cell55);
            table2.addCell(cell66);

            LocalDate datainicio = LocalDate.of(Integer.parseInt(dataInicio.substring(6, 10)), Integer.parseInt(dataInicio.substring(3, 5)), Integer.parseInt(dataInicio.substring(0, 2)));
            LocalDate datafinal = LocalDate.of(Integer.parseInt(dataFinal.substring(6, 10)), Integer.parseInt(dataFinal.substring(3, 5)), Integer.parseInt(dataFinal.substring(0, 2)));

            String nomedomes = "";
            Double estabelecimentoFINAL = 0.0;
            int contagemdeservicoFINAL = 0;
            Double comissaofuncionarioFINAL = 0.0;
            Double despesaTotalFINAL = 0.0;
            for (int i = 1; i <= datafinal.getMonthValue(); i++) {

                Double estabelecimento = 0.0;
                int contagemdeservico = 0;
                Double comissaofuncionario = 0.0;

                for (Receita receita : receitas) {
                    if (receita.getData().getMonthValue() == i) {
                        estabelecimento += receita.getLucroestabelecimento();
                        contagemdeservico++;
                        comissaofuncionario += receita.getLucrofuncionario();

                    }
                }

                Double despesaTotal = 0.0;
                for (Despesa despesa : despesas) {
                    if (despesa.getData().getMonthValue() == i) {
                        despesaTotal += despesa.getValor();
                    }
                }

                switch (i) {
                    case 1:
                        nomedomes = "JANEIRO";
                        break;
                    case 2:
                        nomedomes = "FEVEREIRO";
                        break;
                    case 3:
                        nomedomes = "MARÇO";
                        break;
                    case 4:
                        nomedomes = "ABRIL";
                        break;
                    case 5:
                        nomedomes = "MAIO";
                        break;
                    case 6:
                        nomedomes = "JUNHO";
                        break;
                    case 7:
                        nomedomes = "JULHO";
                        break;
                    case 8:
                        nomedomes = "AGOSTO";
                        break;
                    case 9:
                        nomedomes = "SETEMBRO";
                        break;
                    case 10:
                        nomedomes = "OUTUBRO";
                        break;
                    case 11:
                        nomedomes = "NOVEMBRO";
                        break;
                    case 12:
                        nomedomes = "DEZEMBRO";
                        break;
                }

                cell11 = new PdfPCell(new Paragraph(nomedomes, Tablelement));
                cell22 = new PdfPCell(new Paragraph(contagemdeservico + "", Tablelement));
                cell33 = new PdfPCell(new Paragraph(df.format(estabelecimento) + "", Tablelement));
                cell44 = new PdfPCell(new Paragraph(df.format(despesaTotal) + "", Tablelement));
                cell55 = new PdfPCell(new Paragraph(df.format(comissaofuncionario) + "", Tablelement));
                cell66 = new PdfPCell(new Paragraph(df.format(estabelecimento - (despesaTotal + comissaofuncionario)) + "", Tablelement));

                table2.addCell(cell11);
                table2.addCell(cell22);
                table2.addCell(cell33);
                table2.addCell(cell44);
                table2.addCell(cell55);
                table2.addCell(cell66);

                estabelecimentoFINAL += estabelecimento;
                contagemdeservicoFINAL += contagemdeservico;
                comissaofuncionarioFINAL += comissaofuncionario;
                despesaTotalFINAL += despesaTotal;

            }
            cell11 = new PdfPCell(new Paragraph("TOTAL", Tablelement));
            cell22 = new PdfPCell(new Paragraph(contagemdeservicoFINAL + "", Tablelement));
            cell33 = new PdfPCell(new Paragraph(df.format(estabelecimentoFINAL) + "", Tablelement));
            cell44 = new PdfPCell(new Paragraph(df.format(despesaTotalFINAL) + "", Tablelement));
            cell55 = new PdfPCell(new Paragraph(df.format(comissaofuncionarioFINAL) + "", Tablelement));
            cell66 = new PdfPCell(new Paragraph(df.format(estabelecimentoFINAL - (despesaTotalFINAL + comissaofuncionarioFINAL)) + "", Tablelement));

            table2.addCell(cell11);
            table2.addCell(cell22);
            table2.addCell(cell33);
            table2.addCell(cell44);
            table2.addCell(cell55);
            table2.addCell(cell66);

            document.add(table2);
            document.add(new Paragraph("_________________________________________________________________________"));

            document.close();

            ////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////
            view.dispose();
            Desktop.getDesktop().open(new File("C:/Users/ALEFF/Documents/BarberSoftware/Pdf.PDF"));

        } catch (CamposInvalidosExceptionException ex) {
            JOptionPane.showMessageDialog(view, "Campos de Data invalidas!");
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(view, "Campos de Data invalidas!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TelaRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(TelaRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TelaRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cancelar() {
        view.dispose();
    }

}

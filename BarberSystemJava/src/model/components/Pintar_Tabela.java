/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.components;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
//import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 *
 * @author ALEFF
 */
public class Pintar_Tabela extends DefaultTableCellRenderer {

    public Pintar_Tabela() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        
        Color background = Color.WHITE;

        Object objeto = table.getValueAt(row, 6);

        if (objeto.toString().equals("1")) {
            background = Color.cyan;
        }
        
        if(objeto.toString().equals("2")){
            background = Color.YELLOW;
        }
        
        if(objeto.toString().equals("3")){
             background = Color.GREEN;
        }
        
        label.setBackground(background);
        return label;
    }
    
    
    
    

}

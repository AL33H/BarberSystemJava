/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.NivelDeAcessibilidadeController;
import controller.TelaFuncionarioController;
import javax.swing.JButton;
import javax.swing.JTable;
import model.Usuario;

/**
 *
 * @author ALEFF
 */
public class TelaFuncionario extends javax.swing.JFrame {

	private final TelaFuncionarioController controller;
	public static TelaFuncionario instance = null;

	/**
	 * Creates new form TelaFuncionario
	 */
	public TelaFuncionario() {
		initComponents();
		this.setLocationRelativeTo(null);
		controller = new TelaFuncionarioController(this);
		instance = this;
	}

	public TelaFuncionario(Usuario usuario) {
		initComponents();
		this.setLocationRelativeTo(null);
		controller = new TelaFuncionarioController(this);
		NivelDeAcessibilidadeController nda = new NivelDeAcessibilidadeController(this, usuario);
		instance = this;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTableFUNCIONARIOS = new javax.swing.JTable();
		jButtonADICIONAR = new javax.swing.JButton();
		jButtonREMOVER = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jButtonVISUALIZAR = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowActivated(java.awt.event.WindowEvent evt) {
				formWindowActivated(evt);
			}

			public void windowClosed(java.awt.event.WindowEvent evt) {
				formWindowClosed(evt);
			}
		});
		getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jPanel2.setBackground(java.awt.SystemColor.activeCaption);
		jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
		jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel1.setBackground(java.awt.SystemColor.activeCaption);
		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(255, 255, 255));
		jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icons/funcionarios64.png"))); // NOI18N
		jLabel1.setText("Lista de Funcionarios");
		jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

		jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 90));

		jTableFUNCIONARIOS.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "ID:", "Nome:", "SEXO:", "FUNÇÃO:" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTableFUNCIONARIOS.setFillsViewportHeight(true);
		jTableFUNCIONARIOS.setGridColor(new java.awt.Color(255, 255, 255));
		jTableFUNCIONARIOS.setShowHorizontalLines(false);
		jTableFUNCIONARIOS.setShowVerticalLines(false);
		jTableFUNCIONARIOS.getTableHeader().setReorderingAllowed(false);
		jScrollPane1.setViewportView(jTableFUNCIONARIOS);
		if (jTableFUNCIONARIOS.getColumnModel().getColumnCount() > 0) {
			jTableFUNCIONARIOS.getColumnModel().getColumn(0).setMinWidth(25);
			jTableFUNCIONARIOS.getColumnModel().getColumn(0).setPreferredWidth(25);
			jTableFUNCIONARIOS.getColumnModel().getColumn(0).setMaxWidth(50);
			jTableFUNCIONARIOS.getColumnModel().getColumn(1).setMinWidth(100);
			jTableFUNCIONARIOS.getColumnModel().getColumn(1).setPreferredWidth(240);
			jTableFUNCIONARIOS.getColumnModel().getColumn(1).setMaxWidth(300);
			jTableFUNCIONARIOS.getColumnModel().getColumn(2).setMinWidth(50);
			jTableFUNCIONARIOS.getColumnModel().getColumn(2).setPreferredWidth(100);
			jTableFUNCIONARIOS.getColumnModel().getColumn(2).setMaxWidth(150);
			jTableFUNCIONARIOS.getColumnModel().getColumn(3).setMinWidth(50);
			jTableFUNCIONARIOS.getColumnModel().getColumn(3).setPreferredWidth(100);
			jTableFUNCIONARIOS.getColumnModel().getColumn(3).setMaxWidth(150);
		}

		jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 600, 220));

		jButtonADICIONAR
				.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icons/botao-adicionar.png"))); // NOI18N
		jButtonADICIONAR.setText("ADICIONAR");
		jButtonADICIONAR.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonADICIONARActionPerformed(evt);
			}
		});
		jPanel1.add(jButtonADICIONAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, -1, -1));

		jButtonREMOVER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icons/lixo.png"))); // NOI18N
		jButtonREMOVER.setText("REMOVER");
		jButtonREMOVER.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonREMOVERActionPerformed(evt);
			}
		});
		jPanel1.add(jButtonREMOVER, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, -1, -1));

		jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icons/sair32.png"))); // NOI18N
		jButton4.setText("SAIR");
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});
		jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 330, -1, -1));

		jButtonVISUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icons/lupa64.png"))); // NOI18N
		jButtonVISUALIZAR.setText("VISUALIZAR");
		jButtonVISUALIZAR.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonVISUALIZARActionPerformed(evt);
			}
		});
		jPanel1.add(jButtonVISUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, 120, 40));

		getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 615, 380));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void formWindowActivated(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowActivated
		controller.PreencherTabelaFuncionarios();
	}// GEN-LAST:event_formWindowActivated

	private void jButtonADICIONARActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonADICIONARActionPerformed
		controller.adicionarFuncionario();
	}// GEN-LAST:event_jButtonADICIONARActionPerformed

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
		controller.SairDaTela();
	}// GEN-LAST:event_jButton4ActionPerformed

	private void jButtonREMOVERActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonREMOVERActionPerformed

		controller.apagarFuncionarioSelecionado();
	}// GEN-LAST:event_jButtonREMOVERActionPerformed

	private void jButtonVISUALIZARActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonVISUALIZARActionPerformed
		int id = controller.pegarFuncionarioSelecionado();
		controller.chamarTelaFuncionarioVisualizacao(id);

	}// GEN-LAST:event_jButtonVISUALIZARActionPerformed

	private void formWindowClosed(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosed
		instance = null;
	}// GEN-LAST:event_formWindowClosed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TelaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TelaFuncionario().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButtonADICIONAR;
	private javax.swing.JButton jButtonREMOVER;
	private javax.swing.JButton jButtonVISUALIZAR;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTableFUNCIONARIOS;
	// End of variables declaration//GEN-END:variables

	public JTable getjTableFUNCIONARIOS() {
		return jTableFUNCIONARIOS;
	}

	public void setjTableFUNCIONARIOS(JTable jTableFUNCIONARIOS) {
		this.jTableFUNCIONARIOS = jTableFUNCIONARIOS;
	}

	public JButton getjButtonADICIONAR() {
		return jButtonADICIONAR;
	}

	public void setjButtonADICIONAR(JButton jButtonADICIONAR) {
		this.jButtonADICIONAR = jButtonADICIONAR;
	}

	public JButton getjButtonREMOVER() {
		return jButtonREMOVER;
	}

	public void setjButtonREMOVER(JButton jButtonREMOVER) {
		this.jButtonREMOVER = jButtonREMOVER;
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.TelaUsuarioCadastroController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author ALEFF
 */
public class TelaUsuarioCadastro extends javax.swing.JFrame {

	private final TelaUsuarioCadastroController controller;
	public static TelaUsuarioCadastro instance = null;

	/**
	 * Creates new form TelaUsuario
	 */
	public TelaUsuarioCadastro() {
		initComponents();
		this.setLocationRelativeTo(null);
		controller = new TelaUsuarioCadastroController(this);
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
		jLabel5 = new javax.swing.JLabel();
		jButtonCADASTRAR = new javax.swing.JButton();
		jButtonLIMPAR = new javax.swing.JButton();
		jButtonSAIR = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		jLabel4 = new javax.swing.JLabel();
		jComboBoxNIVELDEACESSO = new javax.swing.JComboBox<>();
		jPasswordFieldCONFIRMARSENHA = new javax.swing.JPasswordField();
		jLabel3 = new javax.swing.JLabel();
		jPasswordFieldSENHA = new javax.swing.JPasswordField();
		jLabel2 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jTextFieldUSUARIO = new javax.swing.JTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		addWindowListener(new java.awt.event.WindowAdapter() {
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

		jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
		jLabel5.setForeground(new java.awt.Color(255, 255, 255));
		jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icons/user64.png"))); // NOI18N
		jLabel5.setText("CADASTRAR USUARIO");
		jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

		jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 80));

		jButtonCADASTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icons/certo24.png"))); // NOI18N
		jButtonCADASTRAR.setText("Cadastrar");
		jButtonCADASTRAR.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonCADASTRARActionPerformed(evt);
			}
		});
		jPanel1.add(jButtonCADASTRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, -1, -1));

		jButtonLIMPAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icons/limpar24.png"))); // NOI18N
		jButtonLIMPAR.setText("Limpar");
		jButtonLIMPAR.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonLIMPARActionPerformed(evt);
			}
		});
		jPanel1.add(jButtonLIMPAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, -1, -1));

		jButtonSAIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icons/back24.png"))); // NOI18N
		jButtonSAIR.setText("Sair");
		jButtonSAIR.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonSAIRActionPerformed(evt);
			}
		});
		jPanel1.add(jButtonSAIR, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, -1));

		jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		jLabel4.setText("NIVEL DE ACESSO:");
		jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

		jComboBoxNIVELDEACESSO
				.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Operador", "Funcion??rio" }));
		jPanel3.add(jComboBoxNIVELDEACESSO, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 210, -1));
		jPanel3.add(jPasswordFieldCONFIRMARSENHA, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 200, -1));

		jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		jLabel3.setText("CONFIRMAR SENHA:");
		jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));
		jPanel3.add(jPasswordFieldSENHA, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 290, -1));

		jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		jLabel2.setText("SENHA:");
		jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		jLabel1.setText("USUARIO:");
		jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
		jPanel3.add(jTextFieldUSUARIO, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 280, -1));

		jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 380, 130));

		getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 270));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButtonCADASTRARActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonCADASTRARActionPerformed
		controller.CadastrarUsuarioDaView();
	}// GEN-LAST:event_jButtonCADASTRARActionPerformed

	private void jButtonLIMPARActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonLIMPARActionPerformed
		controller.LimparTela();
	}// GEN-LAST:event_jButtonLIMPARActionPerformed

	private void jButtonSAIRActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonSAIRActionPerformed
		controller.sairDaTela();
	}// GEN-LAST:event_jButtonSAIRActionPerformed

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
			java.util.logging.Logger.getLogger(TelaUsuarioCadastro.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaUsuarioCadastro.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaUsuarioCadastro.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaUsuarioCadastro.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TelaUsuarioCadastro().setVisible(true);

			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButtonCADASTRAR;
	private javax.swing.JButton jButtonLIMPAR;
	private javax.swing.JButton jButtonSAIR;
	private javax.swing.JComboBox<String> jComboBoxNIVELDEACESSO;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPasswordField jPasswordFieldCONFIRMARSENHA;
	private javax.swing.JPasswordField jPasswordFieldSENHA;
	private javax.swing.JTextField jTextFieldUSUARIO;
	// End of variables declaration//GEN-END:variables

	public JComboBox<String> getjComboBoxNIVELDEACESSO() {
		return jComboBoxNIVELDEACESSO;
	}

	public void setjComboBoxNIVELDEACESSO(JComboBox<String> jComboBoxNIVELDEACESSO) {
		this.jComboBoxNIVELDEACESSO = jComboBoxNIVELDEACESSO;
	}

	public JPasswordField getjPasswordFieldCONFIRMARSENHA() {
		return jPasswordFieldCONFIRMARSENHA;
	}

	public void setjPasswordFieldCONFIRMARSENHA(JPasswordField jPasswordFieldCONFIRMARSENHA) {
		this.jPasswordFieldCONFIRMARSENHA = jPasswordFieldCONFIRMARSENHA;
	}

	public JPasswordField getjPasswordFieldSENHA() {
		return jPasswordFieldSENHA;
	}

	public void setjPasswordFieldSENHA(JPasswordField jPasswordFieldSENHA) {
		this.jPasswordFieldSENHA = jPasswordFieldSENHA;
	}

	public JTextField getjTextFieldUSUARIO() {
		return jTextFieldUSUARIO;
	}

	public void setjTextFieldUSUARIO(JTextField jTextFieldUSUARIO) {
		this.jTextFieldUSUARIO = jTextFieldUSUARIO;
	}

}

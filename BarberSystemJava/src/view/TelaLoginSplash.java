/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.TelaLoginSplashController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import model.Usuario;

/**
 *
 * @author ALEFF
 */
public class TelaLoginSplash extends javax.swing.JFrame {

	private final TelaLoginSplashController controller;

	/**
	 * Creates new form TelaLoginSplash
	 */
	public TelaLoginSplash() {
		initComponents();
		this.toFront();
		this.requestFocus();
		controller = new TelaLoginSplashController(this);
		controller.Inicializar();

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
		jLabelDESCRICOA = new javax.swing.JLabel();
		jProgressBar = new javax.swing.JProgressBar();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setBackground(new java.awt.Color(0, 0, 0));
		setFocusTraversalPolicyProvider(true);
		setUndecorated(true);
		getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jPanel1.setBackground(new java.awt.Color(0, 0, 0));
		jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0), 3));

		jLabelDESCRICOA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		jLabelDESCRICOA.setForeground(new java.awt.Color(255, 255, 255));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 329,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout.createSequentialGroup()
										.addComponent(jLabelDESCRICOA, javax.swing.GroupLayout.PREFERRED_SIZE, 209,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(53, 53, 53)))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabelDESCRICOA, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

		pack();
	}// </editor-fold>//GEN-END:initComponents

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
			java.util.logging.Logger.getLogger(TelaLoginSplash.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaLoginSplash.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaLoginSplash.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaLoginSplash.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(() -> {
			new TelaLoginSplash().setVisible(true);

		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel jLabelDESCRICOA;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JProgressBar jProgressBar;
	// End of variables declaration//GEN-END:variables

	public JLabel getjLabelDESCRICOA() {
		return jLabelDESCRICOA;
	}

	public void setjLabelDESCRICOA(JLabel jLabelDESCRICOA) {
		this.jLabelDESCRICOA = jLabelDESCRICOA;
	}

	public JProgressBar getjProgressBar() {
		return jProgressBar;
	}

	public void setjProgressBar(JProgressBar jProgressBar) {
		this.jProgressBar = jProgressBar;
	}

}

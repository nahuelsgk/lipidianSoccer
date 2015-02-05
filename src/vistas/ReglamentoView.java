/*
 * Jaume Vinyes Navas
 */

package vistas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import controladores.CtrlPresentacion;

import vistas.FutbolFrame.PanelMenu;

public class ReglamentoView extends JPanel {

	private static final long serialVersionUID = -7752494946823417143L;
	private JTextPane reglamentoTextPane;
	private JScrollPane jScrollPane1;
	private JButton volverButton;
	private JLabel reglamentoLabel;

					

	public ReglamentoView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		{
			this.setPreferredSize(new java.awt.Dimension(800, 600));
			this.setLayout(null);
			{
				reglamentoLabel = new JLabel();
				this.add(reglamentoLabel);
				reglamentoLabel.setText("Reglamento LES");
				reglamentoLabel.setBounds(53, 35, 384, 61);
				reglamentoLabel.setFont(new java.awt.Font("Verdana",3,28));
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(53, 102, 705, 435);
				{
					reglamentoTextPane = new JTextPane();
					jScrollPane1.setViewportView(reglamentoTextPane);
					reglamentoTextPane.setBounds(53, 102, 705, 435);
					reglamentoTextPane.setText(CtrlPresentacion.getInstancia().getReglamento());
					reglamentoTextPane.setEditable(false);
					
				}
			}
			{
				volverButton = new JButton();
				this.add(volverButton);
				volverButton.setText("Volver");
				volverButton.setBounds(612, 557, 146, 32);
				volverButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						volverButtonActionPerformed(evt);
					}
				});
			}
		}

	}
	
	private void volverButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_PRINCIPAL, false);
	}
}

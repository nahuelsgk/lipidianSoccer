/*
 * Jaume Vinyes Navas
 */


package vistas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JPanel;

import controladores.CtrlPresentacion;

import vistas.FutbolFrame.PanelMenu;


public class PartidaRapidaView extends JPanel {
	
	private static final long serialVersionUID = -5780453130670120744L;
	private JButton pcvspcButton;
	private JButton menuPrincipalButton;
	private JLabel tituloLabel;
	private JButton pvpcButton;
	private JButton pvpButton;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}
	
	public PartidaRapidaView() {
		super();
		initGUI();
	}

	private void initGUI() {
		{
			this.setPreferredSize(new java.awt.Dimension(800, 600));
			this.setLayout(null);
			{
				pvpButton = new JButton();
				this.add(pvpButton);
				pvpButton.setText("Jugador vs Jugador");
				pvpButton.setBounds(298, 181, 245, 50);
				pvpButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						pvpButtonActionPerformed(evt);
					}
				});
			}
			{
				pvpcButton = new JButton();
				this.add(pvpcButton);
				pvpcButton.setText("Jugador vs Sistema");
				pvpcButton.setBounds(298, 327, 245, 50);
				pvpcButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						pvpcButtonActionPerformed(evt);
					}
				});
			}
			{
				pcvspcButton = new JButton();
				this.add(pcvspcButton);
				pcvspcButton.setText("Sistema vs Sistema");
				pcvspcButton.setBounds(298, 252, 245, 50);
				pcvspcButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						pcvspcButtonActionPerformed(evt);
					}
				});
			}
			{
				menuPrincipalButton = new JButton();
				this.add(menuPrincipalButton);
				menuPrincipalButton.setText("Volver al Menu Principal");
				menuPrincipalButton.setBounds(298, 398, 245, 50);
				menuPrincipalButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						menuPrincipalButtonActionPerformed(evt);
					}
				});
			}
			{
				tituloLabel = new JLabel();
				this.add(tituloLabel);
				tituloLabel.setText("Partida rapida");
				tituloLabel.setBounds(97, 22, 388, 147);
				tituloLabel.setFont(new java.awt.Font("Verdana",3,36));
			}
		}
	}
	
	private void pvpButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion.getInstancia().reset_cpr();
		CtrlPresentacion.getInstancia().setJugadoresPartidaRapida(0);
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.ESCOGER_IA,true);
	}
	
	private void pcvspcButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion.getInstancia().reset_cpr();
		CtrlPresentacion.getInstancia().setJugadoresPartidaRapida(2);
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.ESCOGER_IA,true);
	}
	
	private void pvpcButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion.getInstancia().reset_cpr();
		CtrlPresentacion.getInstancia().setJugadoresPartidaRapida(1);
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.ESCOGER_IA,true);
	}
	
	private void menuPrincipalButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_PRINCIPAL,false);
	}
}

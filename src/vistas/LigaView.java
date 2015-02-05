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


public class LigaView extends JPanel {
	private static final long serialVersionUID = -8085245991786193726L;
	private JButton nuevaLigaButton;
	private JLabel Liga;
	private JButton menuPincipalButton;
	private JButton eliminarLigaButton;
	private JButton cargarLigaButton;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}
	
	public LigaView() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(800, 600));
				this.setLayout(null);
				{
					nuevaLigaButton = new JButton();
					this.add(nuevaLigaButton);
					nuevaLigaButton.setText("Nueva Liga");
					nuevaLigaButton.setBounds(285, 200, 245, 50);
					nuevaLigaButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							nuevaLigaButtonActionPerformed(evt);
						}
					});
				}
				{
					cargarLigaButton = new JButton();
					this.add(cargarLigaButton);
					cargarLigaButton.setText("Cargar Liga");
					cargarLigaButton.setBounds(284, 268, 245, 50);
					cargarLigaButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							cargarLigaButtonActionPerformed(evt);
						}
					});
				}
				{
					eliminarLigaButton = new JButton();
					this.add(eliminarLigaButton);
					eliminarLigaButton.setText("Eliminar Liga");
					eliminarLigaButton.setBounds(284, 337, 245, 50);
					eliminarLigaButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							eliminarLigaButtonActionPerformed(evt);
						}
					});
				}
				{
					menuPincipalButton = new JButton();
					this.add(menuPincipalButton);
					menuPincipalButton.setText("Volver al Menu Principal");
					menuPincipalButton.setBounds(284, 403, 245, 50);
					menuPincipalButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							menuPincipalButtonActionPerformed(evt);
						}
					});
				}
				{
					Liga = new JLabel();
					this.add(Liga);
					Liga.setText("Liga");
					Liga.setBounds(118, 31, 317, 137);
					Liga.setFont(new java.awt.Font("Verdana",3,36));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void menuPincipalButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_PRINCIPAL,false);
	}
	
	private void nuevaLigaButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.NUEVA_LIGA,true);
	}
	
	private void cargarLigaButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion.getInstancia().setEstadoGestion(true);
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.CARGAR_LIGA,true);
	}
	
	private void eliminarLigaButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion.getInstancia().setEstadoGestion(true);
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.ELIMINAR_LIGA, true);
	}

}

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
import javax.swing.JOptionPane;

import javax.swing.JPanel;

import controladores.CtrlPresentacion;

import vistas.FutbolFrame.PanelMenu;


public class GestionLigaView extends JPanel {
	private static final long serialVersionUID = 650773283069244147L;
	private JLabel tituloLabel;
	private JButton salvarButton;
	private JButton menuPrinicipalButton;
	private JButton volverButton;
	private JButton eliminarButton;
	private JButton cargarButton;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}

	public GestionLigaView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(800, 600));
				this.setLayout(null);
				{
					tituloLabel = new JLabel();
					this.add(tituloLabel);
					tituloLabel.setText("Gestion Liga");
					tituloLabel.setBounds(56, 38, 374, 67);
					tituloLabel.setFont(new java.awt.Font("Verdana",3,36));
				}
				{
					salvarButton = new JButton();
					this.add(salvarButton);
					salvarButton.setText("Salvar Liga");
					salvarButton.setBounds(302, 157, 200, 50);
					salvarButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							salvarButtonActionPerformed(evt);
						}
					});
				}
				{
					cargarButton = new JButton();
					this.add(cargarButton);
					cargarButton.setText("Cargar Liga");
					cargarButton.setBounds(302, 230, 200, 50);
					cargarButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							cargarButtonActionPerformed(evt);
						}
					});
				}
				{
					eliminarButton = new JButton();
					this.add(eliminarButton);
					eliminarButton.setText("Eliminar Liga");
					eliminarButton.setBounds(302, 306, 200, 50);
					eliminarButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							eliminarButtonActionPerformed(evt);
						}
					});
				}
				{
					menuPrinicipalButton = new JButton();
					this.add(menuPrinicipalButton);
					menuPrinicipalButton.setText("Menu Principal");
					menuPrinicipalButton.setBounds(302, 380, 200, 50);
					menuPrinicipalButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							menuPrinicipalButtonActionPerformed(evt);
						}
					});
				}
				{
					volverButton = new JButton();
					this.add(volverButton);
					volverButton.setText("Volver");
					volverButton.setBounds(639, 534, 120, 30);
					volverButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							volverButtonActionPerformed(evt);
						}
					});
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void volverButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_LIGA, false);
	}
	
	private void salvarButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion.getInstancia().salvarLiga();
	}
	
	private void cargarButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion.getInstancia().setEstadoGestion(false);
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.CARGAR_LIGA, true);
	}
	
	private void eliminarButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion.getInstancia().setEstadoGestion(false);
		//CtrlPresentacion.getInstancia().eliminarLiga();
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.ELIMINAR_LIGA, true);
	}
	
	private void menuPrinicipalButtonActionPerformed(ActionEvent evt) {
		String[] options = {
				"Accpetar",
				"Cancelar",
				};
		int n = JOptionPane.showOptionDialog(FutbolFrame.getInstancia(),
				"Seguro que quieres volver al menu principal?"
				+ " Se perdera todo la informacion.",
				"Aviso!",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				options,
				options[0]);
	
		if(n == 0) {
			CtrlPresentacion.getInstancia().clearLiga();
			CtrlPresentacion.getInstancia().setTurno(1);
			FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_PRINCIPAL,false);
		}
	}
}

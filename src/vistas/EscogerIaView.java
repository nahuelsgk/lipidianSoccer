/*
 * Alexandre Vidal Obiols
 */


package vistas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JPanel;

import controladores.CtrlPresentacion;
import domain.Constantes;

import vistas.FutbolFrame.PanelMenu;


public class EscogerIaView extends JPanel {

	private static final long serialVersionUID = 3793052086191946146L;
	private JLabel sistema1Label;
	private JComboBox iaComboBox;
	private JComboBox iaComboBox2;
	private JLabel dificultad;
	private JLabel druacionLabel;
	private JComboBox duracionDesplegable;
	private JLabel opcionesLabel;
	private JComboBox dificultadDesplegable;
	private JButton seleccionar;
	private JButton volverButton;
	private JLabel jLabel1;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}

	public EscogerIaView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(800, 533));
				this.setLayout(null);
				{
					sistema1Label = new JLabel();
					this.add(sistema1Label);
					sistema1Label.setText("Jugador Local:");
					sistema1Label.setBounds(100, 185, 116, 14);
				}
				{
					ComboBoxModel IaComboBoxModel = 
						new DefaultComboBoxModel(
								new String[] { "IA Equipo", "IA Individual" });
					iaComboBox = new JComboBox();
					this.add(iaComboBox);
					iaComboBox.setModel(IaComboBoxModel);
					iaComboBox.setBounds(249, 181, 129, 23);
				}
				{
					jLabel1 = new JLabel();
					this.add(jLabel1);
					jLabel1.setText("Jugador Visitante:");
					jLabel1.setBounds(423, 188, 149, 14);
				}
				{
					volverButton = new JButton();
					this.add(volverButton);
					volverButton.setText("Volver");
					volverButton.setBounds(397, 389, 149, 21);
					volverButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							volverButtonActionPerformed(evt);
						}
					});
				}
				{
					ComboBoxModel jComboBox2Model = 
						new DefaultComboBoxModel(
								new String[] { "IA Equipo", "IA Individual" });
					iaComboBox2 = new JComboBox();
					this.add(iaComboBox2);
					iaComboBox2.setModel(jComboBox2Model);
					iaComboBox2.setBounds(590, 183, 129, 23);
				}
				{
					seleccionar = new JButton();
					this.add(seleccionar);
					seleccionar.setText("Seleccionar");
					seleccionar.setBounds(602, 389, 149, 22);
					seleccionar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							seleccionarActionPerformed(evt);
						}
					});
				}
				{
					ComboBoxModel dificultaddesplegableModel = 
						new DefaultComboBoxModel(
								new String[] { "Baja", "Normal", "Alta" });
					dificultadDesplegable = new JComboBox();
					this.add(dificultadDesplegable);
					dificultadDesplegable.setModel(dificultaddesplegableModel);
					dificultadDesplegable.setBounds(243, 262, 129, 24);
				}
				{
					ComboBoxModel duraciondesplegableModel = 
						new DefaultComboBoxModel(
								new String[] { "5 Minutos", "10 Minutos" });
					duracionDesplegable = new JComboBox();
					this.add(duracionDesplegable);
					duracionDesplegable.setModel(duraciondesplegableModel);
					duracionDesplegable.setBounds(584, 264, 129, 22);
				}
				{
					druacionLabel = new JLabel();
					this.add(druacionLabel);
					druacionLabel.setText("Duracion:");
					druacionLabel.setBounds(423, 267, 83, 15);
				}
				{
					opcionesLabel = new JLabel();
					this.add(opcionesLabel);
					opcionesLabel.setText("Opciones de la partida");
					opcionesLabel.setBounds(64, 68, 382, 46);
					opcionesLabel.setFont(new java.awt.Font("Dialog",0,26));
				}
				{
					dificultad = new JLabel();
					this.add(dificultad);
					dificultad.setText("Dificultad:");
					dificultad.setBounds(100, 267, 83, 15);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void volverButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.PARTIDA_RAPIDA,false);
	}
	private void seleccionarActionPerformed(ActionEvent evt) {
		int dificultad;
		String s = (String)dificultadDesplegable.getSelectedItem();
		dificultad = 4;
		if (s.compareTo("Alta") == 0) dificultad = Constantes.DIFICIL;
		else if (s.compareTo("Normal") == 0) dificultad = Constantes.NORMAL;
		else dificultad = Constantes.FACIL;
		CtrlPresentacion.getInstancia().setDificultadPartidaRapida(dificultad);
		CtrlPresentacion.getInstancia().setTipoPartidaRapida(2);
		int duracion;
		if (duracionDesplegable.getSelectedIndex() == 0) duracion = 2700;
		else duracion = 5400;
		CtrlPresentacion.getInstancia().setTiempoPartidaRapida(duracion);
		if(iaComboBox.getSelectedItem() == "IA Individual") CtrlPresentacion.getInstancia().setIaJ1(false);
		if(iaComboBox2.getSelectedItem() == "IA Individual") CtrlPresentacion.getInstancia().setIaJ2(false);
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.ESCOGER_EQUIPOS_RAPIDO,true);
	}

}

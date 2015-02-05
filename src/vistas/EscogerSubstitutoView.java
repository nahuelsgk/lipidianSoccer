/*
 * Alexandre Vidal Obiols
 */

package vistas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controladores.CtrlPresentacion;
import domain.Constantes;


public class EscogerSubstitutoView extends JPanel {

	private static final long serialVersionUID = 7211518831371392770L;
	private JLabel jugador;
	private JLabel imagenLabel;
	private JLabel equipoeraLab;
	private JLabel regateLabel;
	private JLabel regate;
	private JLabel agresividadLabel;
	private JLabel agresividad;
	private JLabel pase;
	private JLabel paseLabel;
	private JLabel remateLabel;
	private JLabel posicionLabel;
	private JLabel equipoLabel;
	private JLabel remate;
	private JLabel resistenciaLabel;
	private JLabel resistencia;
	private JLabel dorsalLabel;
	private JLabel dorsal;
	private JLabel posicion;
	private JLabel velocidadLabel;
	private JLabel velocidad;
	private JButton aceptarButton;
	private JList reservasList;
	private JLabel substituirLabel;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}

	public EscogerSubstitutoView() {
		super();
		initGUI();
	}
	
	
	private void initGUI() {
		{
			this.setPreferredSize(new java.awt.Dimension(810, 611));
			this.setLayout(null);
			FutbolFrame.getInstancia().setSize(800, 620);
			{
				jugador = new JLabel();
				this.add(jugador);
				jugador.setText("Has tenido una baja! Un futbolista no puede seguir jugando.");
				jugador.setBounds(12, 12, 499, 15);
			}
			{
				substituirLabel = new JLabel();
				this.add(substituirLabel);
				substituirLabel.setText("Elige quien lo va a substituir:");
				substituirLabel.setBounds(12, 39, 230, 15);
			}
			{
				ListModel reservasListModel = 
					new DefaultComboBoxModel(CtrlPresentacion.getInstancia().getReservas());
				reservasList = new JList();
				this.add(reservasList);
				reservasList.setModel(reservasListModel);
				reservasList.setBounds(12, 66, 330, 271);
				reservasList.addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						reservasListValueChanged(e);
					}
				});
				reservasList.requestFocus();
			}
			{
				aceptarButton = new JButton();
				this.add(aceptarButton);
				aceptarButton.setText("Aceptar cambio");
				aceptarButton.setBounds(603, 305, 171, 32);
				aceptarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						aceptarbuttonActionPerformed(evt);
					}
				});
			}
			{
				velocidad = new JLabel();
				this.add(velocidad);
				velocidad.setBounds(472, 101, 48, 21);
			}
			{
				velocidadLabel = new JLabel();
				this.add(velocidadLabel);
				velocidadLabel.setText("Velocidad:");
				velocidadLabel.setBounds(385, 99, 93, 28);
			}
			{
				posicionLabel = new JLabel();
				this.add(posicionLabel);
				posicionLabel.setText("Posicion:");
				posicionLabel.setBounds(388, 183, 62, 40);
			}
			{
				posicion = new JLabel();
				this.add(posicion);
				posicion.setBounds(467, 198, 77, 13);
			}
			{
				dorsal = new JLabel();
				this.add(dorsal);
				dorsal.setBounds(469, 76, 57, 17);
			}
			{
				dorsalLabel = new JLabel();
				this.add(dorsalLabel);
				dorsalLabel.setText("Dorsal:");
				dorsalLabel.setBounds(388, 68, 54, 30);
			}
			{
				resistencia = new JLabel();
				this.add(resistencia);
				resistencia.setBounds(473, 158, 47, 23);
			}
			{
				resistenciaLabel = new JLabel();
				this.add(resistenciaLabel);
				resistenciaLabel.setText("Resistencia:");
				resistenciaLabel.setBounds(388, 158, 85, 28);
			}
			{
				remate = new JLabel();
				this.add(remate);
				remate.setBounds(473, 130, 47, 20);
			}
			{
				remateLabel = new JLabel();
				this.add(remateLabel);
				remateLabel.setText("Remate:");
				remateLabel.setBounds(388, 130, 60, 23);
			}
			{
				paseLabel = new JLabel();
				this.add(paseLabel);
				paseLabel.setText("Pase:");
				paseLabel.setBounds(592, 128, 41, 31);
			}
			{
				pase = new JLabel();
				this.add(pase);
				pase.setBounds(705, 132, 69, 22);
			}
			{
				agresividad = new JLabel();
				this.add(agresividad);
				agresividad.setBounds(705, 105, 64, 15);
			}
			{
				agresividadLabel = new JLabel();
				this.add(agresividadLabel);
				agresividadLabel.setText("Agresividad:");
				agresividadLabel.setBounds(592, 93, 81, 40);
			}
			{
				regate = new JLabel();
				this.add(regate);
				regate.setBounds(705, 77, 64, 16);
			}
			{
				regateLabel = new JLabel();
				this.add(regateLabel);
				regateLabel.setText("Regate:");
				regateLabel.setBounds(592, 64, 54, 40);
			}
			{
				equipoeraLab = new JLabel();
				this.add(equipoeraLab);
				equipoeraLab.setText("Era del equipo:");
				equipoeraLab.setBounds(388, 44, 132, 15);
			}
			{
				equipoLabel = new JLabel();
				this.add(equipoLabel);
				equipoLabel.setText(CtrlPresentacion.getInstancia().equipoSolicitante());
				equipoLabel.setBounds(504, 45, 189, 13);
			}
			{
				imagenLabel = new JLabel(new ImageIcon(getClass().getResource("/img/lesion.jpg")));
				this.add(imagenLabel);
				imagenLabel.setBounds(12, 361, 776, 231);
			}
		}


	}
	
	
	private void reservasListValueChanged(ListSelectionEvent evt){
		int n = reservasList.getSelectedIndex();
		if (n < 0) n = 0;

		int pos;
		String posicionaux;
		remate.setText(String.valueOf(CtrlPresentacion.getInstancia().getAtributoReserva(CtrlPresentacion.REMATE, n)));
		resistencia.setText(String.valueOf(CtrlPresentacion.getInstancia().getAtributoReserva(CtrlPresentacion.RESISTENCIA, n)));
		velocidad.setText(String.valueOf(CtrlPresentacion.getInstancia().getAtributoReserva(CtrlPresentacion.VELOCIDAD, n)));
		regate.setText(String.valueOf(CtrlPresentacion.getInstancia().getAtributoReserva(CtrlPresentacion.REGATE, n)));
		agresividad.setText(String.valueOf(CtrlPresentacion.getInstancia().getAtributoReserva(CtrlPresentacion.AGRESIVIDAD, n)));
		pase.setText(String.valueOf(CtrlPresentacion.getInstancia().getAtributoReserva(CtrlPresentacion.PASE, n)));
		dorsal.setText(String.valueOf(CtrlPresentacion.getInstancia().getAtributoReserva(CtrlPresentacion.DORSAL, n)));
		pos = CtrlPresentacion.getInstancia().getAtributoReserva(CtrlPresentacion.TIPO, n);
		posicionaux = "Nulo";
		switch(pos) {
			case Constantes.PORTERO: posicionaux = "Portero"; break;
			case Constantes.MEDIO: posicionaux = "Medio"; break;
			case Constantes.DELANTERO: posicionaux = "Delantero"; break;
			case Constantes.DEFENSA: posicionaux = "Defensa"; break;
		}
		posicion.setText(posicionaux);
	}
	
	private void aceptarbuttonActionPerformed(ActionEvent evt) {
		int n = reservasList.getSelectedIndex();
		if (n < 0) n = 0;
		CtrlPresentacion.getInstancia().aceptaCambioLesionado(n);
		CtrlPresentacion.getInstancia().reanudaPartido(0);
	}
	
	
}
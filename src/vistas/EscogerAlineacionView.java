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
import javax.swing.JList;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vistas.FutbolFrame.PanelMenu;
import controladores.CtrlPresentacion;
import domain.Constantes;


public class EscogerAlineacionView extends JPanel {

	private static final long serialVersionUID = 4366060987808928298L;
	private JComboBox alineamiento;
	private JLabel posAuxLabel;
	private JLabel posLabel;
	private JLabel posicionStrLabel;
	private JLabel equipoLabel;
	private JLabel cambiosNum;
	private JLabel cambiosLabel;
	private JButton supButton;
	private JLabel sup5Lab;
	private JLabel suplente5Label;
	private JLabel sup4Lab;
	private JLabel suplente4Label;
	private JLabel sup3Lab;
	private JLabel suplente3Label;
	private JLabel sup2Lab;
	private JLabel suplente2Label;
	private JLabel sup1Lab;
	private JLabel suplente1Label;
	private JLabel errorLabel;
	private JButton aleatoriaButton;
	private JLabel eqActual;
	private JButton aceptar;
	private JLabel asignar;
	private JLabel posicion;
	private JLabel dorsal;
	private JLabel pase;
	private JLabel agresividad;
	private JLabel regate;
	private JLabel posicionLabel;
	private JLabel jug11;
	private JLabel jug10;
	private JLabel jug9;
	private JLabel jug8;
	private JLabel jug7;
	private JLabel jug6;
	private JLabel jug5;
	private JLabel jug4;
	private JLabel jug3;
	private JLabel jug2;
	private JLabel pos11;
	private JLabel pos10;
	private JLabel pos9;
	private JLabel pos8;
	private JLabel pos7;
	private JLabel pos6;
	private JLabel pos5;
	private JLabel pos4;
	private JLabel pos3;
	private JLabel pos2;
	private JLabel jug1;
	private JLabel pos1;
	private JButton confirmar;
	private JTextField seleccion;
	private JLabel dorsalLabel;
	private JLabel paseLabel;
	private JLabel agresividadLabel;
	private JLabel regateLabel;
	private JLabel velocidad;
	private JLabel peso;
	private JLabel resistencia;
	private JLabel remate;
	private JLabel altura;
	private JLabel velocidadLabel;
	private JLabel resistenciaLabel;
	private JLabel remateLabel;
	private JLabel pesoLabel;
	private JLabel alturaLabel;
	private JButton volverButton;
	private JList jugadores;
	private JLabel dibujo;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}
	


	public EscogerAlineacionView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(815, 627));
				this.setLayout(null);
				FutbolFrame.getInstancia().setSize(800, 620);
				{
					ComboBoxModel alineamientoModel = 
						new DefaultComboBoxModel(
								new String[] { "4-3-3", "4-4-2", "5-3-2", "5-4-1" });
					alineamiento = new JComboBox();
					this.add(alineamiento);
					alineamiento.setModel(alineamientoModel);
					alineamiento.setBounds(12, 12, 151, 22);
					Integer x = CtrlPresentacion.getInstancia().getAlineacion();
					String s = "/img/4-3-3.jpg";
					switch (x) {
						case Constantes.ALINEACION_4_3_3:
							s = "/img/4-3-3.jpg";
							alineamiento.setSelectedIndex(0);
							break;
						case Constantes.ALINEACION_4_4_2:
							s = "/img/4-4-2.jpg";
							alineamiento.setSelectedIndex(1);
							break;
						case Constantes.ALINEACION_5_3_2:
							s = "/img/5-3-2.jpg";
							alineamiento.setSelectedIndex(2);
							break;
						case Constantes.ALINEACION_5_4_1:
							s = "/img/5-4-1.jpg";
							alineamiento.setSelectedIndex(3);
							break;
						
					}
					dibujo = new JLabel(new ImageIcon(getClass().getResource(s)));
					this.add(dibujo);
					dibujo.setBounds(12, 56, 151, 230);
					alineamiento.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							alineamientoValuechanged(e);
						}
					});
				}
				{
					ListModel jugadoresModel = 
						new DefaultComboBoxModel(CtrlPresentacion.getInstancia().getNombresJugadoresEquipo1());
					jugadores = new JList();
					this.add(jugadores);
					jugadores.setModel(jugadoresModel);
					jugadores.setBounds(175, 51, 221, 362);
					jugadores.addListSelectionListener(new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent e) {
							jugadoresValueChanged(e);
						}
					});
					jugadores.requestFocus();
					
				}
				{
					volverButton = new JButton();
					this.add(volverButton);
					volverButton.setText("Volver");
					volverButton.setBounds(430, 382, 124, 31);
					volverButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							volverbuttonActionPerformed(evt);
						}
					});
				}
				{
					alturaLabel = new JLabel();
					this.add(alturaLabel);
					alturaLabel.setText("Altura:");
					alturaLabel.setBounds(430, 51, 60, 25);
				}
				{
					pesoLabel = new JLabel();
					this.add(pesoLabel);
					pesoLabel.setText("Peso:");
					pesoLabel.setBounds(430, 82, 60, 22);
				}
				{
					remateLabel = new JLabel();
					this.add(remateLabel);
					remateLabel.setText("Remate:");
					remateLabel.setBounds(430, 110, 60, 23);
				}
				{
					resistenciaLabel = new JLabel();
					this.add(resistenciaLabel);
					resistenciaLabel.setText("Resistencia:");
					resistenciaLabel.setBounds(430, 138, 85, 28);
				}
				{
					velocidadLabel = new JLabel();
					this.add(velocidadLabel);
					velocidadLabel.setText("Velocidad:");
					velocidadLabel.setBounds(430, 169, 93, 28);
				}
				{
					altura = new JLabel();
					this.add(altura);
					altura.setBounds(515, 54, 47, 20);
				}
				{
					peso = new JLabel();
					this.add(peso);
					peso.setBounds(515, 83, 48, 18);
				}
				{
					remate = new JLabel();
					this.add(remate);
					remate.setBounds(515, 110, 47, 20);
				}
				{
					resistencia = new JLabel();
					this.add(resistencia);
					resistencia.setBounds(515, 138, 47, 23);
				}
				{
					velocidad = new JLabel();
					this.add(velocidad);
					velocidad.setBounds(517, 172, 48, 21);
				}
				{
					regateLabel = new JLabel();
					this.add(regateLabel);
					regateLabel.setText("Regate:");
					regateLabel.setBounds(580, 43, 54, 40);
				}
				{
					agresividadLabel = new JLabel();
					this.add(agresividadLabel);
					agresividadLabel.setText("Agresividad:");
					agresividadLabel.setBounds(580, 72, 81, 40);
				}
				{
					paseLabel = new JLabel();
					this.add(paseLabel);
					paseLabel.setText("Pase:");
					paseLabel.setBounds(580, 107, 41, 31);
				}
				{
					dorsalLabel = new JLabel();
					this.add(dorsalLabel);
					dorsalLabel.setText("Dorsal:");
					dorsalLabel.setBounds(580, 137, 54, 30);
				}
				{
					posicionLabel = new JLabel();
					this.add(posicionLabel);
					posicionLabel.setText("Posicion:");
					posicionLabel.setBounds(580, 162, 62, 40);
				}
				{
					regate = new JLabel();
					this.add(regate);
					regate.setBounds(693, 56, 64, 16);
				}
				{
					agresividad = new JLabel();
					this.add(agresividad);
					agresividad.setBounds(693, 84, 64, 15);
				}
				{
					pase = new JLabel();
					this.add(pase);
					pase.setBounds(693, 111, 69, 22);
				}
				{
					dorsal = new JLabel();
					this.add(dorsal);
					dorsal.setBounds(693, 141, 76, 17);
				}
				{
					posicion = new JLabel();
					this.add(posicion);
					posicion.setBounds(692, 176, 77, 13);
				}
				{
					asignar = new JLabel();
					this.add(asignar);
					asignar.setText("Selecciona la posicion para el jugador seleccionado:");
					asignar.setBounds(430, 214, 347, 19);
				}
				{
					seleccion = new JTextField();
					this.add(seleccion);
					seleccion.setBounds(430, 247, 117, 22);
				}
				{
					confirmar = new JButton();
					this.add(confirmar);
					confirmar.setText("Asignar a posicion");
					confirmar.setBounds(430, 284, 167, 27);
					confirmar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							confirmarActionPerformed(evt);
						}
					});
				}
				{
					pos1 = new JLabel();
					this.add(pos1);
					pos1.setText("Posicion 0:");
					pos1.setBounds(24, 427, 86, 15);
				}
				{
					jug1 = new JLabel();
					this.add(jug1);
					jug1.setBounds(137, 427, 259, 15);					
					if (CtrlPresentacion.getInstancia() != null) jug1.setText(CtrlPresentacion.getInstancia().quienOcupaPosicion(0));
				}
				{
					pos2 = new JLabel();
					this.add(pos2);
					pos2.setText("Posicion 1:");
					pos2.setBounds(24, 448, 78, 15);
				}
				{
					pos3 = new JLabel();
					this.add(pos3);
					pos3.setText("Posicion 2:");
					pos3.setBounds(24, 469, 70, 15);
				}
				{
					pos4 = new JLabel();
					this.add(pos4);
					pos4.setText("Posicion 3:");
					pos4.setBounds(24, 490, 70, 15);
				}
				{
					pos5 = new JLabel();
					this.add(pos5);
					pos5.setText("Posicion 4:");
					pos5.setBounds(24, 511, 70, 15);
				}
				{
					pos6 = new JLabel();
					this.add(pos6);
					pos6.setText("Posicion 5:");
					pos6.setBounds(24, 532, 70, 15);
				}
				{
					pos7 = new JLabel();
					this.add(pos7);
					pos7.setText("Posicion 6:");
					pos7.setBounds(25, 554, 91, 15);
				}
				{
					pos8 = new JLabel();
					this.add(pos8);
					pos8.setText("Posicion 7:");
					pos8.setBounds(24, 575, 91, 15);
				}
				{
					pos9 = new JLabel();
					this.add(pos9);
					pos9.setText("Posicion 8:");
					pos9.setBounds(431, 427, 85, 15);
				}
				{
					pos10 = new JLabel();
					this.add(pos10);
					pos10.setText("Posicion 9:");
					pos10.setBounds(431, 448, 79, 15);
				}
				{
					pos11 = new JLabel();
					this.add(pos11);
					pos11.setText("Posicion 10:");
					pos11.setBounds(430, 469, 79, 15);
				}
				{
					jug2 = new JLabel();
					this.add(jug2);
					jug2.setBounds(137, 448, 259, 15);
					if (CtrlPresentacion.getInstancia() != null) jug2.setText(CtrlPresentacion.getInstancia().quienOcupaPosicion(1));
				}
				{
					jug3 = new JLabel();
					this.add(jug3);
					jug3.setBounds(137, 469, 259, 15);
					if (CtrlPresentacion.getInstancia() != null) jug3.setText(CtrlPresentacion.getInstancia().quienOcupaPosicion(2)); 
				}
				{
					jug4 = new JLabel();
					this.add(jug4);
					jug4.setBounds(137, 490, 252, 15);
					if (CtrlPresentacion.getInstancia() != null) jug4.setText(CtrlPresentacion.getInstancia().quienOcupaPosicion(3));
				}
				{
					jug5 = new JLabel();
					this.add(jug5);
					jug5.setBounds(137, 511, 259, 15);
					if (CtrlPresentacion.getInstancia() != null) jug5.setText(CtrlPresentacion.getInstancia().quienOcupaPosicion(4));
				}
				{
					jug6 = new JLabel();
					this.add(jug6);
					jug6.setBounds(137, 532, 252, 15);
					if (CtrlPresentacion.getInstancia() != null) jug6.setText(CtrlPresentacion.getInstancia().quienOcupaPosicion(5));
				}
				{
					jug7 = new JLabel();
					this.add(jug7);
					jug7.setBounds(137, 552, 252, 17);
					if (CtrlPresentacion.getInstancia() != null) jug7.setText(CtrlPresentacion.getInstancia().quienOcupaPosicion(6));
				}
				{
					jug8 = new JLabel();
					this.add(jug8);
					jug8.setBounds(137, 574, 259, 15);
					if (CtrlPresentacion.getInstancia() != null) jug8.setText(CtrlPresentacion.getInstancia().quienOcupaPosicion(7));
				}
				{
					jug9 = new JLabel();
					this.add(jug9);
					jug9.setBounds(543, 427, 247, 15);
					if (CtrlPresentacion.getInstancia() != null) jug9.setText(CtrlPresentacion.getInstancia().quienOcupaPosicion(8));
				}
				{
					jug10 = new JLabel();
					this.add(jug10);
					jug10.setBounds(543, 448, 247, 15);
					if (CtrlPresentacion.getInstancia() != null) jug10.setText(CtrlPresentacion.getInstancia().quienOcupaPosicion(9));
				}
				{
					jug11 = new JLabel();
					this.add(jug11);
					jug11.setBounds(543, 469, 256, 15);
					if (CtrlPresentacion.getInstancia() != null) jug11.setText(CtrlPresentacion.getInstancia().quienOcupaPosicion(10));
				}
				{
					aceptar = new JButton();
					this.add(aceptar);
					aceptar.setText("Aceptar Alineacion");
					aceptar.setBounds(587, 382, 182, 31);
					aceptar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							aceptarActionPerformed(evt);
						}
					});
				}
				{
					eqActual = new JLabel();
					this.add(eqActual);
					eqActual.setText("Equipo:");
					eqActual.setBounds(175, 16, 82, 15);
				}

				{
					aleatoriaButton = new JButton();
					this.add(aleatoriaButton);
					aleatoriaButton.setText("Asignacion Automatica");
					aleatoriaButton.setBounds(430, 331, 167, 34);
					if (CtrlPresentacion.getInstancia().getEstatAlineacio() >= 3) aleatoriaButton.setVisible(false);
					else aleatoriaButton.setVisible(true);
					aleatoriaButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							aleatorioActionPerformed(evt);
						}
					});
				}
				{
					errorLabel = new JLabel();
					this.add(errorLabel);
					errorLabel.setBounds(430, 16, 347, 25);
				}
				{
					suplente1Label = new JLabel();
					this.add(suplente1Label);
					suplente1Label.setText("Suplente 1:");
					suplente1Label.setBounds(430, 489, 78, 15);
				}
				{
					sup1Lab = new JLabel();
					this.add(sup1Lab);
					sup1Lab.setBounds(543, 489, 252, 15);
					if (CtrlPresentacion.getInstancia() != null) sup1Lab.setText(CtrlPresentacion.getInstancia().quienOcupaPosicion(-1));
				}
				{
					suplente2Label = new JLabel();
					this.add(suplente2Label);
					suplente2Label.setText("Suplente 2:");
					suplente2Label.setBounds(430, 510, 70, 15);
				}
				{
					sup2Lab = new JLabel();
					this.add(sup2Lab);
					sup2Lab.setBounds(543, 510, 252, 16);
					if (CtrlPresentacion.getInstancia() != null) sup2Lab.setText(CtrlPresentacion.getInstancia().quienOcupaPosicion(-2));
				}
				{
					suplente3Label = new JLabel();
					this.add(suplente3Label);
					suplente3Label.setText("Suplente 3:");
					suplente3Label.setBounds(430, 532, 79, 15);
				}
				{
					sup3Lab = new JLabel();
					this.add(sup3Lab);
					sup3Lab.setBounds(543, 532, 241, 15);
					if (CtrlPresentacion.getInstancia() != null) sup3Lab.setText(CtrlPresentacion.getInstancia().quienOcupaPosicion(-3));
				}
				{
					suplente4Label = new JLabel();
					this.add(suplente4Label);
					suplente4Label.setText("Suplente 4:");
					suplente4Label.setBounds(430, 553, 79, 15);
				}
				{
					sup4Lab = new JLabel();
					this.add(sup4Lab);
					sup4Lab.setBounds(543, 552, 241, 16);
					if (CtrlPresentacion.getInstancia() != null) sup4Lab.setText(CtrlPresentacion.getInstancia().quienOcupaPosicion(-4));
				}
				{
					suplente5Label = new JLabel();
					this.add(suplente5Label);
					suplente5Label.setText("Suplente 5:");
					suplente5Label.setBounds(430, 574, 79, 15);
				}
				{
					sup5Lab = new JLabel();
					this.add(sup5Lab);
					sup5Lab.setBounds(543, 574, 241, 15);
					if (CtrlPresentacion.getInstancia() != null) sup5Lab.setText(CtrlPresentacion.getInstancia().quienOcupaPosicion(-5));
				}
				{
					supButton = new JButton();
					this.add(supButton);
					supButton.setText("Asignar a suplente");
					supButton.setBounds(608, 284, 161, 28);
					supButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							supButtonActionPerformed(evt);
						}
					});
				}
				{
					cambiosLabel = new JLabel();
					this.add(cambiosLabel);
					cambiosLabel.setText(CtrlPresentacion.getInstancia().getTextoCambiosRestantes());
					cambiosLabel.setBounds(430, 339, 160, 15);
				}
				{
					cambiosNum = new JLabel();
					this.add(cambiosNum);
					cambiosNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getNumCambiosRestantes()));
					cambiosNum.setBounds(563, 339, 46, 15);
				}
				{
					equipoLabel = new JLabel();
					this.add(equipoLabel);
					equipoLabel.setText(CtrlPresentacion.getInstancia().getNombreEquipo());
					equipoLabel.setBounds(238, 16, 365, 15);
				}
				{
					posicionStrLabel = new JLabel();
					this.add(posicionStrLabel);
					posicionStrLabel.setText("Posicion actual:");
					posicionStrLabel.setBounds(559, 250, 107, 15);
				}
				{
					posLabel = new JLabel();
					this.add(posLabel);
					posLabel.setBounds(666, 250, 103, 15);
				}
				{
					posAuxLabel = new JLabel();
					this.add(posAuxLabel);
					posAuxLabel.setBounds(735, 250, 34, 15);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//imagen = new ImageIcon(getClass().getResource("/img/fondoPrincipal.jpg"));
	private void alineamientoValuechanged(ActionEvent evt) {
		String s = null;
		switch (alineamiento.getSelectedIndex()) {
			case 0: s = "/img/4-3-3.jpg"; 
					CtrlPresentacion.getInstancia().setAlineacion(Constantes.ALINEACION_4_3_3);
					break;
			case 1: s = "/img/4-4-2.jpg"; 
				CtrlPresentacion.getInstancia().setAlineacion(Constantes.ALINEACION_4_4_2);
				break;
			case 2: s = "/img/5-3-2.jpg"; 
				CtrlPresentacion.getInstancia().setAlineacion(Constantes.ALINEACION_5_3_2);
				break;
			case 3: s = "/img/5-4-1.jpg"; 
				CtrlPresentacion.getInstancia().setAlineacion(Constantes.ALINEACION_5_4_1);
				break;
		}
		this.remove(dibujo);
		dibujo = new JLabel(new ImageIcon(getClass().getResource(s)));
		this.add(dibujo);
		dibujo.setBounds(12, 56, 151, 230);


	}
	
	private void aceptarActionPerformed(ActionEvent evt) {
		switch (CtrlPresentacion.getInstancia().getEstatAlineacio()) {

			case 0:
				if (CtrlPresentacion.getInstancia().completo()){
					if (CtrlPresentacion.getInstancia().getJugadoresPartidaRapida() == 1) {
						CtrlPresentacion.getInstancia().iniciaPartido();
					}
					else{
						CtrlPresentacion.getInstancia().setEstatAlineacio(1);
						String[] s = CtrlPresentacion.getInstancia().getNombresJugadoresEquipo1();
						DefaultComboBoxModel m = new DefaultComboBoxModel(s);
						jugadores.setModel(m);
						jugadores.updateUI();
						jug1.setText("Nadie");
						jug2.setText("Nadie");
						jug3.setText("Nadie");
						jug4.setText("Nadie");
						jug5.setText("Nadie");
						jug6.setText("Nadie");
						jug7.setText("Nadie");
						jug8.setText("Nadie");
						jug9.setText("Nadie");
						jug10.setText("Nadie");
						jug11.setText("Nadie");
						sup1Lab.setText("Nadie");
						sup2Lab.setText("Nadie");
						sup3Lab.setText("Nadie");
						sup4Lab.setText("Nadie");
						sup5Lab.setText("Nadie");
						altura.setText("");
						peso.setText("");
						remate.setText("");
						velocidad.setText("");
						regate.setText("");
						agresividad.setText("");
						resistencia.setText("");
						pase.setText("");
						dorsal.setText("");
						posicion.setText("");
						equipoLabel.setText(CtrlPresentacion.getInstancia().getNombreEquipo());
					}
				}else errorLabel.setText("Error: Necesitas posicionar a 11 jugadores");
				break;
				
			case 1:
				if (CtrlPresentacion.getInstancia().completo()) CtrlPresentacion.getInstancia().iniciaPartido();
				else errorLabel.setText("Error: Necesitas posicionar a 11 jugadores");
				break;
				
			case 2:
				if (CtrlPresentacion.getInstancia().completo()) {
					CtrlPresentacion.getInstancia().setyaAsignado(1);
					FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_LIGA,true);
				}else{
					errorLabel.setText("Error: Necesitas posicionar a 11 jugadores");
				}
				break;
			case 3:
				CtrlPresentacion.getInstancia().reanudaPartido(1);
				break;
			case 4:
				CtrlPresentacion.getInstancia().reanudaPartido(1);
				break;
			default:
		}
	}

	
	private void volverbuttonActionPerformed(ActionEvent evt) {
		switch (CtrlPresentacion.getInstancia().getEstatAlineacio()) {
			
			case 0:
				CtrlPresentacion.getInstancia().setSeleccionado(false);
				CtrlPresentacion.getInstancia().setPrimeroIndex(9);
				CtrlPresentacion.getInstancia().setSegundoIndex(9);
				CtrlPresentacion.getInstancia().setAsignandoActual(0);
				FutbolFrame.getInstancia().ponerMenu(PanelMenu.ESCOGER_EQUIPOS_RAPIDO,true);
				if (CtrlPresentacion.getInstancia().getPrimerfet()) {
					CtrlPresentacion.getInstancia().setPrimerfet();
				}
				break;
			
			case 1:
				CtrlPresentacion.getInstancia().setSeleccionado(false);
				CtrlPresentacion.getInstancia().setPrimeroIndex(9);
				CtrlPresentacion.getInstancia().setSegundoIndex(9);
				CtrlPresentacion.getInstancia().setAsignandoActual(0);
				FutbolFrame.getInstancia().ponerMenu(PanelMenu.ESCOGER_EQUIPOS_RAPIDO,true);
				if (CtrlPresentacion.getInstancia().getPrimerfet()) {
					CtrlPresentacion.getInstancia().setPrimerfet();
				}
				break;
			case 2:
				CtrlPresentacion.getInstancia().resetAlineaciones();
				FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_LIGA,true);
				break;
			case 3:
				CtrlPresentacion.getInstancia().reanudaPartido(0);
				break;
			case 4:
				CtrlPresentacion.getInstancia().reanudaPartido(0);
				break;
			default:
		}
	}
	
	private void aleatorioActionPerformed(ActionEvent evt) {
		CtrlPresentacion.getInstancia().alineacionAutomatica();
		pintaNoms();
	}
	
	
	
	private void asignar(Integer n) {
		Boolean cambioValido = true;
		Boolean indicesCorrectos = true;
		Boolean elMismo = false;
		Boolean sacam = true;
		Integer posicion = null;
		Boolean malPuesto = false;
		Boolean noPermitido = false;
		

		try {
			Integer.parseInt(seleccion.getText());
		}
		catch (NumberFormatException nfe) {
				malPuesto = true;
		}

		if (!malPuesto) posicion = Integer.parseInt(seleccion.getText());

		
		String jugadorstr = null;
		Integer jugador = jugadores.getSelectedIndex();
		if (jugador >= 0) jugadorstr = (String) jugadores.getSelectedValue();
		
		if (!(!malPuesto && ((posicion >= 0 && posicion <= 10 && n == 0) || (n == 1 && posicion <= 5 && posicion >= 1)))) {
			indicesCorrectos = false;
		}
		
		if (indicesCorrectos) {
			int posicionJugadorQuieroSalir = CtrlPresentacion.getInstancia().estaAsignado(jugador);
			int posicionJugadorQuieroIr = posicion;		
	
			
			if ((n == 0 && (posicionJugadorQuieroIr == posicionJugadorQuieroSalir)) || (n == 1 && (-posicionJugadorQuieroIr == posicionJugadorQuieroSalir))) {
				elMismo = true;
			}
			
			if ((posicionJugadorQuieroSalir < 0 || n == 1) && !(posicionJugadorQuieroSalir < 0 && n == 1) && (CtrlPresentacion.getInstancia().getEstatAlineacio() >= 3)) 	{
				
				
				//if (Integer.parseInt(CtrlPresentacion.getInstancia().getNumCambiosRestantes()) == 0) cambioValido = false;
				if (CtrlPresentacion.getInstancia().numeroCambios() == 3) cambioValido = false;
				if (n == 1) {
					posicionJugadorQuieroIr = -posicionJugadorQuieroIr;
				}
				
				if (CtrlPresentacion.getInstancia().quienOcupaPosicion(posicionJugadorQuieroIr).compareTo("Nadie") == 0)  noPermitido = true;
				if (n == 1) {
					posicionJugadorQuieroIr = -posicionJugadorQuieroIr;
				}
				
			}
			
			if (cambioValido && !elMismo && !noPermitido) {
				
				if (n == 1) {
					posicionJugadorQuieroIr = -posicionJugadorQuieroIr;
				}
				
				String s = CtrlPresentacion.getInstancia().quienOcupaPosicion(posicionJugadorQuieroIr);
				if (s.compareTo("Nadie") == 0) sacam = false;
				
				
				CtrlPresentacion.getInstancia().unset(posicionJugadorQuieroSalir);
				if (posicionJugadorQuieroIr != -6) CtrlPresentacion.getInstancia().unset(posicionJugadorQuieroIr);
				
				
				if (CtrlPresentacion.getInstancia().getEstatAlineacio() <= 1) {
					CtrlPresentacion.getInstancia().setAssig(jugadorstr, posicionJugadorQuieroIr);
				} else {
					CtrlPresentacion.getInstancia().setPosicion(jugador, posicionJugadorQuieroIr);
				}

				if (CtrlPresentacion.getInstancia().getEstatAlineacio() <= 1 && sacam) {
					CtrlPresentacion.getInstancia().setAssig(s, posicionJugadorQuieroSalir);
				} else if (sacam){
					CtrlPresentacion.getInstancia().setAssig(s, posicionJugadorQuieroSalir);
				}
				
				pintaNoms();
				
				if (posicionJugadorQuieroIr == -6) {
					posAuxLabel.setVisible(false);
					posLabel.setText("Sin Posici—n");
				}
				else {
					posAuxLabel.setVisible(true);
					if (posicionJugadorQuieroIr < 0) {
						posLabel.setText("Suplente");
						posicionJugadorQuieroIr = -posicionJugadorQuieroIr;
					}
					else {
						posLabel.setText("Jugador");
					}
					posAuxLabel.setText(String.valueOf(posicionJugadorQuieroIr));
				}
				errorLabel.setText(" ");
				int temp = CtrlPresentacion.getInstancia().getEstatAlineacio();
				if (temp == 3 || temp == 4) cambiosNum.setText(String.valueOf(3-CtrlPresentacion.getInstancia().numeroCambios()));
			} else if (!cambioValido) {
				errorLabel.setText("Error: No puedes hacer mas cambios");
			} else if (noPermitido) {
				errorLabel.setText("Error: No puedes cambiar con un vacio");
			}
		} else {
			errorLabel.setText("Error: Indices Incorrectos");
		}

	}
	
	private void confirmarActionPerformed(ActionEvent evt) {
		asignar(0);
	}
	
	private void supButtonActionPerformed(ActionEvent evt) {
		asignar(1);
	}
	
	private void muestraStatsRapida(Integer n, Integer m) {
		int pos;
		String posicionaux;
		/*altura.setText(String.valueOf(CtrlPresentacion.getInstancia().getAlturaPredef(m, n)));
		peso.setText(String.valueOf(CtrlPresentacion.getInstancia().getPesoPredef(m, n)));
		remate.setText(String.valueOf(CtrlPresentacion.getInstancia().getRematePredef(m, n)));
		resistencia.setText(String.valueOf(CtrlPresentacion.getInstancia().getResistenciaPredef(m, n)));
		velocidad.setText(String.valueOf(CtrlPresentacion.getInstancia().getVelocidadPredef(m, n)));
		regate.setText(String.valueOf(CtrlPresentacion.getInstancia().getRegatePredef(m, n)));
		agresividad.setText(String.valueOf(CtrlPresentacion.getInstancia().getAgresividadPredef(m, n)));
		pase.setText(String.valueOf(CtrlPresentacion.getInstancia().getPasePredef(m, n)));
		dorsal.setText(String.valueOf(CtrlPresentacion.getInstancia().getDorsalPredef(m, n)));
		pos = CtrlPresentacion.getInstancia().getPosicionPredef(m, n);*/
		altura.setText(String.valueOf(CtrlPresentacion.getInstancia().getAlturaEq(n)));
		peso.setText(String.valueOf(CtrlPresentacion.getInstancia().getStatsEq(CtrlPresentacion.PESO, n)));
		remate.setText(String.valueOf(CtrlPresentacion.getInstancia().getStatsEq(CtrlPresentacion.REMATE, n)));
		resistencia.setText(String.valueOf(CtrlPresentacion.getInstancia().getStatsEq(CtrlPresentacion.RESISTENCIA, n)));
		velocidad.setText(String.valueOf(CtrlPresentacion.getInstancia().getStatsEq(CtrlPresentacion.VELOCIDAD, n)));
		regate.setText(String.valueOf(CtrlPresentacion.getInstancia().getStatsEq(CtrlPresentacion.REGATE, n)));
		agresividad.setText(String.valueOf(CtrlPresentacion.getInstancia().getStatsEq(CtrlPresentacion.AGRESIVIDAD, n)));
		pase.setText(String.valueOf(CtrlPresentacion.getInstancia().getStatsEq(CtrlPresentacion.PASE, n)));
		dorsal.setText(String.valueOf(CtrlPresentacion.getInstancia().getStatsEq(CtrlPresentacion.DORSAL, n)));
		pos = CtrlPresentacion.getInstancia().getPosicionPredef(m, n);
		posicionaux = "Nulo";
		switch(pos) {
		case Constantes.PORTERO: posicionaux = "Portero"; break;
		case Constantes.MEDIO: posicionaux = "Medio"; break;
		case Constantes.DELANTERO: posicionaux = "Delantero"; break;
		case Constantes.DEFENSA: posicionaux = "Defensa"; break;
		}
		posicion.setText(posicionaux);
	}
	
	private void muestraStatsLiga(Integer n) {
		int pos;
		String posicionaux;
		altura.setText(String.valueOf(CtrlPresentacion.getInstancia().getAlturaDorsal(n)));
		peso.setText(String.valueOf(CtrlPresentacion.getInstancia().getPesoDorsal(n)));
		remate.setText(String.valueOf(CtrlPresentacion.getInstancia().getRemateDorsal(n)));
		resistencia.setText(String.valueOf(CtrlPresentacion.getInstancia().getResistenciaDorsal(n)));
		velocidad.setText(String.valueOf(CtrlPresentacion.getInstancia().getVelocidadDorsal(n)));
		regate.setText(String.valueOf(CtrlPresentacion.getInstancia().getRegateDorsal(n)));
		agresividad.setText(String.valueOf(CtrlPresentacion.getInstancia().getAgresividadDorsal(n)));
		pase.setText(String.valueOf(CtrlPresentacion.getInstancia().getPaseDorsal(n)));
		dorsal.setText(String.valueOf(CtrlPresentacion.getInstancia().getDorsal(n)));
		pos = CtrlPresentacion.getInstancia().getPosicionDorsal(n);
		posicionaux = "Nulo";
		switch(pos) {
		case Constantes.PORTERO: posicionaux = "Portero"; break;
		case Constantes.MEDIO: posicionaux = "Medio"; break;
		case Constantes.DELANTERO: posicionaux = "Delantero"; break;
		case Constantes.DEFENSA: posicionaux = "Defensa"; break;
		}
		posicion.setText(posicionaux);
	}
	
	private void muestraStatsPartida(Integer n) {
		int pos;
		String posicionaux;
		remate.setText(String.valueOf(CtrlPresentacion.getInstancia().getAtributoNormal(CtrlPresentacion.REMATE, n)));
		resistencia.setText(String.valueOf(CtrlPresentacion.getInstancia().getAtributoNormal(CtrlPresentacion.RESISTENCIA, n)));
		velocidad.setText(String.valueOf(CtrlPresentacion.getInstancia().getAtributoNormal(CtrlPresentacion.VELOCIDAD, n)));
		regate.setText(String.valueOf(CtrlPresentacion.getInstancia().getAtributoNormal(CtrlPresentacion.REGATE, n)));
		agresividad.setText(String.valueOf(CtrlPresentacion.getInstancia().getAtributoNormal(CtrlPresentacion.AGRESIVIDAD, n)));
		pase.setText(String.valueOf(CtrlPresentacion.getInstancia().getAtributoNormal(CtrlPresentacion.PASE, n)));
		dorsal.setText(String.valueOf(CtrlPresentacion.getInstancia().getAtributoNormal(CtrlPresentacion.DORSAL, n)));
		pos = CtrlPresentacion.getInstancia().getAtributoNormal(CtrlPresentacion.TIPO, n);
		altura.setText(String.valueOf(CtrlPresentacion.getInstancia().getAlturaNormal(n)));
		peso.setText(String.valueOf(CtrlPresentacion.getInstancia().getAtributoNormal(CtrlPresentacion.PESO, n)));

		posicionaux = "Nulo";
		switch(pos) {
			case Constantes.PORTERO: posicionaux = "Portero"; break;
			case Constantes.MEDIO: posicionaux = "Medio"; break;
			case Constantes.DELANTERO: posicionaux = "Delantero"; break;
			case Constantes.DEFENSA: posicionaux = "Defensa"; break;
		}
		posicion.setText(posicionaux);
	}
	
	private void jugadoresValueChanged(ListSelectionEvent evt) {
		int m = CtrlPresentacion.getInstancia().getEstatAlineacio();
		int n = jugadores.getSelectedIndex();
		if (n < 0) n = 0;
		if (m < 0) m = 0;

		switch (CtrlPresentacion.getInstancia().getEstatAlineacio()) {
			case 0:
				muestraStatsRapida(n, m);
				break;
				
			case 1:
				muestraStatsRapida(n, m);
				break;
				
			case 2:
				muestraStatsLiga(n);
				break;
				
			case 3:
				muestraStatsPartida(n);
				break;
				
			case 4:
				muestraStatsPartida(n);
				break;
				
			default:
		}
		int a = CtrlPresentacion.getInstancia().estaAsignado(n);
		if (a == -6) {
			posAuxLabel.setVisible(false);
			posLabel.setText("Sin Posicion");
		}
		else {
			posAuxLabel.setVisible(true);
			if (a < 0) {
				posLabel.setText("Suplente");
				a = -a;
			}
			else {
				posLabel.setText("Jugador");
			}
			posAuxLabel.setText(String.valueOf(a));
		}
	}
	
	
	private void pintaNoms() {
		
		for (int i = 0; i < 11; ++i) {
			String s = CtrlPresentacion.getInstancia().quienOcupaPosicion(i);
			switch (i + 1) {
				case 1: jug1.setText(s);
						break;
				case 2: jug2.setText(s);
						break;
				case 3: jug3.setText(s);
						break;
				case 4: jug4.setText(s);
						break;
				case 5: jug5.setText(s);
						break;
				case 6: jug6.setText(s);
						break;
				case 7: jug7.setText(s);
						break;
				case 8: jug8.setText(s);
						break;
				case 9: jug9.setText(s);
						break;
				case 10: jug10.setText(s);
						break;
				case 11: jug11.setText(s);
						break;
			}
		
		}
		for (int i = -5; i < 0; ++i) {
			String s = CtrlPresentacion.getInstancia().quienOcupaPosicion(i);
			switch (i) {
				case -1: sup1Lab.setText(s);
						break;
				case -2: sup2Lab.setText(s);
						break;
				case -3: sup3Lab.setText(s);
						break;
				case -4: sup4Lab.setText(s);
						break;
				case -5: sup5Lab.setText(s);
						break;
			}
		
		}
		
	}

}

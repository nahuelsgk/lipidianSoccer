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
import javax.swing.JScrollPane;

import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vistas.FutbolFrame.PanelMenu;

import controladores.CtrlPresentacion;


public class ResultadoPartidaRapidaView extends JPanel {
	private static final long serialVersionUID = 7748443977405343933L;
	private JLabel resultadoLabel;
	private JLabel eq2Label;
	private JLabel tarjetasAmarillasLabel;
	private JLabel estadisticasJugadorLabel;
	private JLabel jugadoresEquipoLabel;
	private JList jugadoresList;
	private JLabel golesRecibidosNum;
	private JLabel golesRecibidosLabel;
	private JLabel golesMarcadosNum;
	private JLabel golesmarcadoslabel;
	private JLabel estequipolabel;
	private JLabel selequipolab;
	private JList equipoList;
	private JLabel partidosPerdidosNum;
	private JLabel partidosEmpatadosNum;
	private JLabel partidosGanadosNum;
	private JLabel partidosPerdidosLabel;
	private JLabel partidosEmpatadosLabel;
	private JLabel partidosGanadosLabel;
	private JLabel tarjetasRojasNum;
	private JLabel tarjetasRojasLabel;
	private JLabel pasesRealizadosLabel;
	private JLabel partidasJugadosNum;
	private JLabel faltasRecibidas;
	private JLabel faltasCometidasNum;
	private JLabel porcPasesBuenosNum;
	private JLabel pasesRealizadosNum;
	private JLabel pasesBuenosNum;
	private JLabel tarjetasRojasJugNum;
	private JLabel tarjetasAmarillasJugNum;
	private JLabel golesRecibidosJugNum;
	private JLabel golesMarcadosJugNum;
	private JLabel partidosJugadorLabel;
	private JLabel faltasRecibidasLabel;
	private JLabel faltasCometidasLabel;
	private JLabel porcPsesBuenosLabel;
	private JLabel pasesBuenosLabel;
	private JLabel tarjetasRojasJugadorLabel;
	private JLabel tarjetasAmarillasJugLabel;
	private JLabel golesRecibidosJugLabel;
	private JLabel jLabel1;
	private JLabel tarjetasAmarillasNum;
	private JLabel numgoles2;
	private JLabel numgoles1;
	private JLabel eq1label;
	private JScrollPane scrollPaneEquipos;
	private JScrollPane scrollPaneJugadores;
	private JButton volverButton;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}

	public ResultadoPartidaRapidaView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(800, 600));
				this.setLayout(null);
				{
					resultadoLabel = new JLabel();
					this.add(resultadoLabel);
					resultadoLabel.setText("Resultado:");
					resultadoLabel.setBounds(21, 20, 195, 21);
					resultadoLabel.setFont(new java.awt.Font("Dialog",0,22));
				}
				{
					eq1label = new JLabel();
					this.add(eq1label);
					eq1label.setText(CtrlPresentacion.getInstancia().getLocal());
					eq1label.setBounds(21, 47, 357, 37);
					eq1label.setFont(new java.awt.Font("Dialog",0,28));
				}
				{
					eq2Label = new JLabel();
					this.add(eq2Label);
					eq2Label.setText(CtrlPresentacion.getInstancia().getVisitante());
					eq2Label.setBounds(390, 41, 381, 43);
					eq2Label.setFont(new java.awt.Font("Dialog",0,28));
				}
				{
					numgoles1 = new JLabel();
					this.add(numgoles1);
					numgoles1.setText(String.valueOf(CtrlPresentacion.getInstancia().getGolesLocal()));
					numgoles1.setBounds(277, 84, 45, 35);
					numgoles1.setFont(new java.awt.Font("Dialog",0,28));
				}
				{
					numgoles2 = new JLabel();
					this.add(numgoles2);
					numgoles2.setText(String.valueOf(CtrlPresentacion.getInstancia().getGolesVisitante()));
					numgoles2.setBounds(669, 90, 42, 34);
					numgoles2.setFont(new java.awt.Font("Dialog",0,28));
				}
				{
					ListModel equiposlistModel = null;
					/*if (CtrlPresentacion.getInstancia().getPartidoIniciado() != 0) {*/
					equiposlistModel = 
						new DefaultComboBoxModel(CtrlPresentacion.getInstancia().getEquiposSimulacion());
					/*}
					else {
						equiposlistModel = 
							new DefaultComboBoxModel(CtrlPresentacion.getInstancia().getEquiposPredefinidos());
					}*/
					equipoList = new JList();
					this.add(equipoList);
					equipoList.setModel(equiposlistModel);
					equipoList.setBounds(21, 165, 135, 143);
					equipoList.addListSelectionListener(new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent e) {
							equiposlistvalueChanged(e);
						}
					});
				}
				{
					selequipolab = new JLabel();
					this.add(selequipolab);
					selequipolab.setText("Selecciona el equipo:");
					selequipolab.setBounds(24, 138, 130, 15);
				}
				{
					estequipolabel = new JLabel();
					this.add(estequipolabel);
					estequipolabel.setText("Estadisticas equipo:");
					estequipolabel.setBounds(34, 325, 134, 15);
				}
				{
					golesmarcadoslabel = new JLabel();
					this.add(golesmarcadoslabel);
					golesmarcadoslabel.setText("Goles Marcados:");
					golesmarcadoslabel.setBounds(34, 355, 109, 15);
				}
				{
					golesMarcadosNum = new JLabel();
					this.add(golesMarcadosNum);
					golesMarcadosNum.setBounds(191, 355, 40, 18);
				}
				{
					golesRecibidosLabel = new JLabel();
					this.add(golesRecibidosLabel);
					golesRecibidosLabel.setText("Goles Recibidos:");
					golesRecibidosLabel.setBounds(34, 380, 110, 19);
				}
				{
					golesRecibidosNum = new JLabel();
					this.add(golesRecibidosNum);
					golesRecibidosNum.setBounds(191, 379, 45, 21);
				}
				{
					tarjetasAmarillasLabel = new JLabel();
					this.add(tarjetasAmarillasLabel);
					tarjetasAmarillasLabel.setText("Tarjetas Amarillas:");
					tarjetasAmarillasLabel.setBounds(34, 407, 126, 18);
				}
				{
					tarjetasAmarillasNum = new JLabel();
					this.add(tarjetasAmarillasNum);
					tarjetasAmarillasNum.setBounds(191, 409, 45, 16);
				}
				{
					tarjetasRojasLabel = new JLabel();
					this.add(tarjetasRojasLabel);
					tarjetasRojasLabel.setText("Tarjetas Rojas:");
					tarjetasRojasLabel.setBounds(34, 433, 98, 19);
				}
				{
					tarjetasRojasNum = new JLabel();
					this.add(tarjetasRojasNum);
					tarjetasRojasNum.setBounds(191, 432, 60, 17);
				}
				{
					partidosGanadosLabel = new JLabel();
					this.add(partidosGanadosLabel);
					partidosGanadosLabel.setText("Partidos Ganados:");
					partidosGanadosLabel.setBounds(34, 463, 125, 15);
				}
				{
					partidosEmpatadosLabel = new JLabel();
					this.add(partidosEmpatadosLabel);
					partidosEmpatadosLabel.setText("Partidos Empatados:");
					partidosEmpatadosLabel.setBounds(35, 490, 133, 15);
				}
				{
					partidosPerdidosLabel = new JLabel();
					this.add(partidosPerdidosLabel);
					partidosPerdidosLabel.setText("Partidos Perdidos:");
					partidosPerdidosLabel.setBounds(35, 517, 119, 15);
				}
				{
					partidosGanadosNum = new JLabel();
					this.add(partidosGanadosNum);
					partidosGanadosNum.setBounds(191, 463, 81, 15);
				}
				{
					partidosEmpatadosNum = new JLabel();
					this.add(partidosEmpatadosNum);
					partidosEmpatadosNum.setBounds(191, 490, 46, 15);
				}
				{
					partidosPerdidosNum = new JLabel();
					this.add(partidosPerdidosNum);
					partidosPerdidosNum.setBounds(191, 516, 46, 17);
				}
				{
					jugadoresEquipoLabel = new JLabel();
					this.add(jugadoresEquipoLabel);
					jugadoresEquipoLabel.setText("Jugadores del equipo:");
					jugadoresEquipoLabel.setBounds(265, 138, 144, 15);
				}
				{
					estadisticasJugadorLabel = new JLabel();
					this.add(estadisticasJugadorLabel);
					estadisticasJugadorLabel.setText("Estadisticas jugador:");
					estadisticasJugadorLabel.setBounds(521, 138, 140, 15);
				}
				{
					jLabel1 = new JLabel();
					this.add(jLabel1);
					jLabel1.setText("Goles Marcados:");
					jLabel1.setBounds(521, 171, 109, 15);
				}
				{
					golesRecibidosJugLabel = new JLabel();
					this.add(golesRecibidosJugLabel);
					golesRecibidosJugLabel.setText("Goles Recibidos:");
					golesRecibidosJugLabel.setBounds(521, 198, 109, 15);
				}
				{
					tarjetasAmarillasJugLabel = new JLabel();
					this.add(tarjetasAmarillasJugLabel);
					tarjetasAmarillasJugLabel.setText("Tarjetas Amarillas:");
					tarjetasAmarillasJugLabel.setBounds(521, 225, 140, 15);
				}
				{
					tarjetasRojasJugadorLabel = new JLabel();
					this.add(tarjetasRojasJugadorLabel);
					tarjetasRojasJugadorLabel.setText("Tarjetas Rojas:");
					tarjetasRojasJugadorLabel.setBounds(521, 251, 109, 15);
				}
				{
					pasesBuenosLabel = new JLabel();
					this.add(pasesBuenosLabel);
					pasesBuenosLabel.setText("Pases Buenos:");
					pasesBuenosLabel.setBounds(521, 279, 98, 15);
				}
				{
					pasesRealizadosLabel = new JLabel();
					this.add(pasesRealizadosLabel);
					pasesRealizadosLabel.setText("Pases Realizados:");
					pasesRealizadosLabel.setBounds(522, 306, 116, 15);
				}
				{
					porcPsesBuenosLabel = new JLabel();
					this.add(porcPsesBuenosLabel);
					porcPsesBuenosLabel.setText("Porcentaje Pases Buenos:");
					porcPsesBuenosLabel.setBounds(522, 333, 163, 15);
				}
				{
					faltasCometidasLabel = new JLabel();
					this.add(faltasCometidasLabel);
					faltasCometidasLabel.setText("Faltas Cometidas:");
					faltasCometidasLabel.setBounds(521, 360, 116, 15);
				}
				{
					faltasRecibidasLabel = new JLabel();
					this.add(faltasRecibidasLabel);
					faltasRecibidasLabel.setText("Faltas Recibidas:");
					faltasRecibidasLabel.setBounds(521, 388, 116, 15);
				}
				{
					partidosJugadorLabel = new JLabel();
					this.add(partidosJugadorLabel);
					partidosJugadorLabel.setText("Partidos Jugados:");
					partidosJugadorLabel.setBounds(521, 415, 129, 15);
				}
				{
					golesMarcadosJugNum = new JLabel();
					this.add(golesMarcadosJugNum);
					golesMarcadosJugNum.setBounds(697, 173, 69, 12);
				}
				{
					golesRecibidosJugNum = new JLabel();
					this.add(golesRecibidosJugNum);
					golesRecibidosJugNum.setBounds(697, 200, 69, 15);
				}
				{
					tarjetasAmarillasJugNum = new JLabel();
					this.add(tarjetasAmarillasJugNum);
					tarjetasAmarillasJugNum.setBounds(697, 226, 69, 14);
				}
				{
					tarjetasRojasJugNum = new JLabel();
					this.add(tarjetasRojasJugNum);
					tarjetasRojasJugNum.setBounds(697, 252, 69, 15);
				}
				{
					pasesBuenosNum = new JLabel();
					this.add(pasesBuenosNum);
					pasesBuenosNum.setBounds(697, 279, 69, 15);
				}
				{
					pasesRealizadosNum = new JLabel();
					this.add(pasesRealizadosNum);
					pasesRealizadosNum.setBounds(697, 306, 69, 15);
				}
				{
					porcPasesBuenosNum = new JLabel();
					this.add(porcPasesBuenosNum);
					porcPasesBuenosNum.setBounds(697, 332, 63, 15);
				}
				{
					faltasCometidasNum = new JLabel();
					this.add(faltasCometidasNum);
					faltasCometidasNum.setBounds(697, 360, 63, 15);
				}
				{
					faltasRecibidas = new JLabel();
					this.add(faltasRecibidas);
					faltasRecibidas.setBounds(697, 387, 63, 15);
				}
				{
					partidasJugadosNum = new JLabel();
					this.add(partidasJugadosNum);
					partidasJugadosNum.setBounds(697, 415, 63, 15);
				}
				{
					scrollPaneEquipos = new JScrollPane(equipoList);
					this.add(scrollPaneEquipos);
					scrollPaneEquipos.setBounds(21, 165, 135, 143);
				}
				{
					ListModel jugadoreslistModel = 
						new DefaultComboBoxModel(CtrlPresentacion.getInstancia().getJugadoresSimulacion(0));
					jugadoresList = new JList();
					this.add(jugadoresList);
					jugadoresList.setModel(jugadoreslistModel);
					jugadoresList.setBounds(251, 166, 258, 365);
					jugadoresList.addListSelectionListener(new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent e) {
							jugadoreslistvalueChanged(e);
						}
					});
				}
				{
					scrollPaneJugadores = new JScrollPane(jugadoresList);
					this.add(scrollPaneJugadores);
					scrollPaneJugadores.setBounds(251, 166, 258, 365);
				}
				{
					volverButton = new JButton();
					this.add(volverButton);
					volverButton.setText("Menu principal");
					volverButton.setBounds(580, 499, 174, 32);
					volverButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							volverbuttonActionPerformed(evt);
						}
					});
				}

			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	private void equiposlistvalueChanged(ListSelectionEvent evt) {
		int n = equipoList.getSelectedIndex();
		if (n < 0) n = 0;
		String[] s = CtrlPresentacion.getInstancia().getJugadoresSimulacion(n); 

		
		ListModel listajug = new DefaultComboBoxModel(s);
		jugadoresList.setModel(listajug);
		jugadoresList.updateUI();
		golesMarcadosNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getGolesMarcadosEquipoSimulacion(n)));
		golesRecibidosNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getGolesRecibidosEquipoSimulacion(n)));
		tarjetasAmarillasNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getTarjetasAmarillasEquipoSimulacion(n)));
		tarjetasRojasNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getTarjetasRojasEquipoSimulacion(n)));
		partidosGanadosNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getPartidosGanados(n)));
		partidosEmpatadosNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getPartidosEmpatados(n)));
		partidosPerdidosNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getPartidosPerdidos(n)));
	}
	
	private void jugadoreslistvalueChanged(ListSelectionEvent evt) {
		int m = equipoList.getSelectedIndex();
		int n = jugadoresList.getSelectedIndex();
		if (n < 0) n = 0;
		if (n >= 18) n = 17;
		if (m < 0) m = 0;
		if (m >= 18) m = 17;
		golesMarcadosJugNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getGolesMarcadosJugadorSimulacion(m, n)));
		golesRecibidosJugNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getGolesRecibidosJugadorSimulacion(m, n)));
		tarjetasAmarillasJugNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getTarjetasAmarillasJugadorSimulacion(m, n)));
		tarjetasRojasJugNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getTarjetasRojasJugadorSimulacion(m, n)));
		pasesBuenosNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getPasesBuenos(m, n)));
		pasesRealizadosNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getPasesRealizados(m, n)));
		porcPasesBuenosNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getPorcentajePasesBuenos(m, n)));
		faltasCometidasNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getFaltasCometidas(m, n)));
		faltasRecibidas.setText(String.valueOf(CtrlPresentacion.getInstancia().getFaltasRecibidas(m, n)));
		partidasJugadosNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getPartidoJugados(m, n)));
	}
	
	private void volverbuttonActionPerformed(ActionEvent evt) {
		CtrlPresentacion.getInstancia().setSeleccionado(false);
		CtrlPresentacion.getInstancia().setPrimeroIndex(9);
		CtrlPresentacion.getInstancia().setSegundoIndex(9);
		CtrlPresentacion.getInstancia().setAsignandoActual(0);
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_PRINCIPAL,true);
	}

}
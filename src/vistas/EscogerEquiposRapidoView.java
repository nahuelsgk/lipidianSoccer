/*
 * Alexandre Vidal Obiols
 */


package vistas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controladores.CtrlPresentacion;
import domain.Constantes;

import vistas.FutbolFrame.PanelMenu;


public class EscogerEquiposRapidoView extends JPanel {

	private static final long serialVersionUID = -8059618595346487160L;
	private JLabel Explicacion;
	private JList listaEquipos;
	private JButton aceptarButton;
	private JScrollPane scrollPaneJugadores;
	private JLabel regateNum;
	private JLabel regateLabel;
	private JLabel remateNum;
	private JLabel remateLabel;
	private JScrollPane scrollPaneEquipos;
	private JLabel equiposLabes;
	private JLabel paradaNum;
	private JLabel paradaLabel;
	private JLabel paseNum;
	private JLabel paseLabel;
	private JLabel agresividadNum;
	private JLabel agresividadLabel;
	private JLabel velocidadNum;
	private JLabel velocidadLabel;
	private JLabel resistenciaNum;
	private JLabel resistenciaLabel;
	private JLabel mediaValue;
	private JLabel mediaLabel;
	private JLabel posicionValue;
	private JList listaJugadores;
	private JButton volverButton;
	private JLabel jLabel2;
	private JLabel equipo1Sel;
	private JLabel jLabel1;
	private JLabel equipo1;
	private JLabel posicionLabel;
	private JLabel dorsalValue;
	private JLabel dorsalLabel;
	private Boolean acabado;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}

	public EscogerEquiposRapidoView() {
		super();
		initGUI();
		listaEquipos.requestFocus();
		listaEquipos.setSelectedIndex(0);
	}
	
	private void initGUI() {
		{
			this.setPreferredSize(new java.awt.Dimension(818, 570));
			this.setLayout(null);
			{
				Explicacion = new JLabel();
				this.add(Explicacion);
				Explicacion.setBounds(34, 104, 524, 15);
				Explicacion.setText("Seleccione el equipo del jugador 1.");
				
			}
			{
				volverButton = new JButton();
				this.add(volverButton);
				volverButton.setText("Volver");
				volverButton.setBounds(525, 510, 114, 22);
				volverButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						volverbuttonActionPerformed(evt);
					}
				});
			}
			{
				aceptarButton = new JButton();
				acabado = false;
				this.add(aceptarButton);
				aceptarButton.setText("Aceptar");
				aceptarButton.setBounds(654, 510, 119, 22);
				aceptarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						aceptarbuttonActionPerformed(evt);
					}
				});
			}
			{
				equipo1 = new JLabel();
				this.add(equipo1);
				equipo1.setText("Equipo 1:");
				equipo1.setBounds(34, 486, 74, 15);
			}
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("Equipo 2:");
				jLabel1.setBounds(285, 486, 74, 15);
			}
			{
				equipo1Sel = new JLabel();
				this.add(equipo1Sel);
				equipo1Sel.setText("<por seleccionar>");
				equipo1Sel.setBounds(128, 513, 180, 15);
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2);
				jLabel2.setText("<por seleccionar>");
				jLabel2.setBounds(362, 513, 180, 15);
			}
			{
				dorsalLabel = new JLabel();
				this.add(dorsalLabel);
				dorsalLabel.setText("Dorsal:");
				dorsalLabel.setBounds(522, 137, 66, 15);
			}
			{
				dorsalValue = new JLabel();
				this.add(dorsalValue);
				dorsalValue.setBounds(588, 166, 58, 16);
			}
			{
				posicionLabel = new JLabel();
				this.add(posicionLabel);
				posicionLabel.setText("Posicion:");
				posicionLabel.setBounds(522, 194, 66, 15);
			}
			{
				posicionValue = new JLabel();
				this.add(posicionValue);
				posicionValue.setBounds(579, 225, 67, 17);
			}
			{
				mediaLabel = new JLabel();
				this.add(mediaLabel);
				mediaLabel.setText("Media:");
				mediaLabel.setBounds(525, 250, 58, 15);
			}
			{
				mediaValue = new JLabel();
				this.add(mediaValue);
				mediaValue.setBounds(579, 277, 59, 15);
			}
			{
				scrollPaneEquipos = new JScrollPane(listaEquipos);
				this.add(scrollPaneEquipos);
				scrollPaneEquipos.setBounds(34, 131, 179, 317);
				{
					ListModel listaequiposModel = 
						new DefaultComboBoxModel(CtrlPresentacion.getInstancia().getEquiposPredefinidos());
					listaEquipos = new JList();
					scrollPaneEquipos.setViewportView(listaEquipos);
					listaEquipos.setModel(listaequiposModel);
					listaEquipos.setBounds(256, 202, 176, 314);
					listaEquipos.addListSelectionListener(new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent e) {
							listaequiposValueChanged(e);
						}
					});
				}
			}
			{
				scrollPaneJugadores = new JScrollPane(listaJugadores);
				this.add(scrollPaneJugadores);
				scrollPaneJugadores.setBounds(232, 131, 261, 313);
				{
					ListModel listajugadoresModel = 
						new DefaultComboBoxModel(
								new String[] { " "});
					listaJugadores = new JList();
					scrollPaneJugadores.setViewportView(listaJugadores);
					listaJugadores.setModel(listajugadoresModel);
					listaJugadores.setBounds(450, 49, 258, 310);
					listaJugadores.addListSelectionListener(new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent e) {
							listajugadoresValueChanged(e);
						}
					});
				}
			}
			{
				remateLabel = new JLabel();
				this.add(remateLabel);
				remateLabel.setText("Remate:");
				remateLabel.setBounds(525, 303, 72, 15);
			}
			{
				remateNum = new JLabel();
				this.add(remateNum);
				remateNum.setBounds(579, 331, 59, 17);
			}
			{
				regateLabel = new JLabel();
				this.add(regateLabel);
				regateLabel.setText("Regate:");
				regateLabel.setBounds(525, 361, 72, 15);
			}
			{
				regateNum = new JLabel();
				this.add(regateNum);
				regateNum.setBounds(579, 388, 59, 20);
			}
			{
				resistenciaLabel = new JLabel();
				this.add(resistenciaLabel);
				resistenciaLabel.setText("Resistencia:");
				resistenciaLabel.setBounds(654, 137, 103, 15);
			}
			{
				resistenciaNum = new JLabel();
				this.add(resistenciaNum);
				resistenciaNum.setBounds(739, 167, 61, 15);
			}
			{
				velocidadLabel = new JLabel();
				this.add(velocidadLabel);
				velocidadLabel.setText("Velocidad:");
				velocidadLabel.setBounds(654, 194, 103, 15);
			}
			{
				velocidadNum = new JLabel();
				this.add(velocidadNum);
				velocidadNum.setBounds(739, 222, 47, 16);
			}
			{
				agresividadLabel = new JLabel();
				this.add(agresividadLabel);
				agresividadLabel.setText("Agresividad:");
				agresividadLabel.setBounds(654, 250, 116, 15);
			}
			{
				agresividadNum = new JLabel();
				this.add(agresividadNum);
				agresividadNum.setBounds(739, 277, 65, 15);
			}
			{
				paseLabel = new JLabel();
				this.add(paseLabel);
				paseLabel.setText("Pase:");
				paseLabel.setBounds(654, 303, 69, 15);
			}
			{
				paseNum = new JLabel();
				this.add(paseNum);
				paseNum.setBounds(741, 334, 65, 14);
			}
			{
				paradaLabel = new JLabel();
				this.add(paradaLabel);
				paradaLabel.setText("Parada:");
				paradaLabel.setBounds(654, 361, 85, 15);
			}
			{
				paradaNum = new JLabel();
				this.add(paradaNum);
				paradaNum.setBounds(739, 388, 67, 21);
			}
			{
				equiposLabes = new JLabel();
				this.add(equiposLabes);
				equiposLabes.setText("Seleccion de equipos");
				equiposLabes.setBounds(34, 20, 382, 59);
				equiposLabes.setFont(new java.awt.Font("Dialog",0,26));
			}
		}

	}
	
	private void listaequiposValueChanged(ListSelectionEvent evt) {
		int n = listaEquipos.getSelectedIndex();
		if (n < 0) n = 0;
		n = CtrlPresentacion.getInstancia().filtrarIndex(n);
		String[] s = CtrlPresentacion.getInstancia().getNombresJugadoresPredefinidos(n);
		ListModel listajug = new DefaultComboBoxModel(s);
		listaJugadores.setModel(listajug);
		listaJugadores.updateUI();
	}
	
	private void listajugadoresValueChanged(ListSelectionEvent evt) {
		int m = listaEquipos.getSelectedIndex();
		int n = listaJugadores.getSelectedIndex();
		if (n < 0) n = 0;
		if (m < 0) m = 0;
		m = CtrlPresentacion.getInstancia().filtrarIndex(m);
		int pos, med, dors;
		dors = CtrlPresentacion.getInstancia().getDorsalPredef(m, n);
		pos = CtrlPresentacion.getInstancia().getPosicionPredef(m, n);
		med = CtrlPresentacion.getInstancia().getMediaPredef(m, n);
		regateNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getRegatePredef(m, n)));
		remateNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getRematePredef(m, n)));
		velocidadNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getVelocidadPredef(m, n)));
		paradaNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getParadaPredef(m, n)));
		paseNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getPasePredef(m, n)));
		agresividadNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getAgresividadPredef(m, n)));
		resistenciaNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getResistenciaPredef(m, n)));
		mediaValue.setText(med+" ");
		mediaValue.updateUI();
		String posicion = "Nulo";
		switch(pos) {
		case Constantes.PORTERO: posicion = "Portero"; break;
		case Constantes.MEDIO: posicion = "Medio"; break;
		case Constantes.DELANTERO: posicion = "Delantero"; break;
		case Constantes.DEFENSA: posicion = "Defensa"; break;
		}
		posicionValue.setText(posicion);
		posicionValue.updateUI();
		dorsalValue.setText(dors+" ");
		dorsalValue.updateUI();
	}
	
	private void volverbuttonActionPerformed(ActionEvent evt) {
		
		equipo1Sel.setText("<por seleccionar>");
		jLabel2.setText("<por seleccionar>");
		Explicacion.setText("Seleccione el equipo del jugador 1.");
		
		CtrlPresentacion.getInstancia().setSeleccionado(false);
		CtrlPresentacion.getInstancia().setPrimeroIndex(9);
		CtrlPresentacion.getInstancia().setSegundoIndex(9);
		ListModel listaequiposModel3 = 
			new DefaultComboBoxModel(CtrlPresentacion.getInstancia().getEquiposPredefinidos());
		listaEquipos.setModel(listaequiposModel3);

		FutbolFrame.getInstancia().ponerMenu(PanelMenu.ESCOGER_IA,false);
	}
	private void aceptarbuttonActionPerformed(ActionEvent evt) {
		if (acabado) {
			if (CtrlPresentacion.getInstancia().getJugadoresPartidaRapida() == 2) {
				equipo1Sel.setText("<por seleccionar>");
				jLabel2.setText("<por seleccionar>");
				Explicacion.setText("Seleccione el equipo del jugador 1.");
				
				CtrlPresentacion.getInstancia().setSeleccionado(false);
				CtrlPresentacion.getInstancia().setPrimeroIndex(9);
				CtrlPresentacion.getInstancia().setSegundoIndex(9);
				CtrlPresentacion.getInstancia().iniciaPartido();
			}
			else {
				equipo1Sel.setText("<por seleccionar>");
				jLabel2.setText("<por seleccionar>");
				Explicacion.setText("Seleccione el equipo del jugador 1.");
				
				CtrlPresentacion.getInstancia().setSeleccionado(false);
				CtrlPresentacion.getInstancia().setPrimeroIndex(9);
				CtrlPresentacion.getInstancia().setSegundoIndex(9);
				CtrlPresentacion.getInstancia().setEstatAlineacio(0);
				CtrlPresentacion.getInstancia().iniciarAlineaciones();
				FutbolFrame.getInstancia().ponerMenu(PanelMenu.ESCOGER_ALINEACION,true);
			}
		}
		else if (!CtrlPresentacion.getInstancia().getSeleccionado()) {
			CtrlPresentacion.getInstancia().setSeleccionado(true);
			String f = (String) listaEquipos.getSelectedValue();
			CtrlPresentacion.getInstancia().setPrimero(f);
			CtrlPresentacion.getInstancia().setPrimeroIndex(listaEquipos.getSelectedIndex());
			
			CtrlPresentacion.getInstancia().confirmarEquipo1(listaEquipos.getSelectedIndex());
		
			String[] aux = new String[7];
			String[] aux2 = CtrlPresentacion.getInstancia().getEquiposPredefinidos();
			String primero = CtrlPresentacion.getInstancia().getPrimero();
			int j = 0;
			for (int i = 0; i < 8; i++){
				if (aux2[i] != primero) {
					aux[j] = aux2[i];
					j++;
				}
			}
			ListModel listaequiposModel2 = 
				new DefaultComboBoxModel(aux);
			listaEquipos.setModel(listaequiposModel2);
			listaEquipos.updateUI();
			Explicacion.setText("Seleccione el equipo del jugador 2.");
			Explicacion.updateUI();
			equipo1Sel.setText(primero);
			equipo1Sel.updateUI();
			listaEquipos.requestFocus();
			listaEquipos.setSelectedIndex(0);
		}
		else{
			String segundo = (String) listaEquipos.getSelectedValue();
			CtrlPresentacion.getInstancia().setSegundo(segundo);
			CtrlPresentacion.getInstancia().setSegundoIndex(listaEquipos.getSelectedIndex());
			
			//CtrlPresentacion.getInstancia().confirmarEquipo2(CtrlPresentacion.getInstancia().getSegundoIndex());
			CtrlPresentacion.getInstancia().confirmarEquipo2String(segundo);
			
			String[] aux = new String[8];
			String[] aux2 = CtrlPresentacion.getInstancia().getEquiposPredefinidos();
			String primero = CtrlPresentacion.getInstancia().getPrimero();
			int j = 0;
			for (int i = 0; i < 8; i++){
				if (aux2[i] != primero && aux2[i] != segundo) {
					aux[j] = aux2[i];
					j++;
				}
			}
			
			ListModel listaequiposModel2 = 
				new DefaultComboBoxModel(aux);
			listaEquipos.setModel(listaequiposModel2);
			listaEquipos.updateUI();
			
			Explicacion.setText("Equipos seleccionados con exito.");
			Explicacion.updateUI();
			
			aceptarButton.setText("Avanzar");
			aceptarButton.updateUI();
			acabado = true;
			
			jLabel2.setText(segundo);
			jLabel2.updateUI();
			
			listaEquipos.requestFocus();
			listaEquipos.setSelectedIndex(0);

		}

	}
}

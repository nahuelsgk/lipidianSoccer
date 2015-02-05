/*
 * Jaume Vinyes Navas
 */


package vistas;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vistas.FutbolFrame.PanelMenu;

import controladores.CtrlPresentacion;
import domain.Constantes;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EscogerEquiposView extends JPanel {

	private static final long serialVersionUID = -283601995719205066L;
	private JList equipsList;
	private JScrollPane jScrollPane1;
	private JLabel remateAtLabel;
	private JLabel regateAtLabel;
	private JLabel velAtLabel;
	private JLabel resistenciaAtLabel;
	private JLabel atrAlLabel;
	private JLabel posicionLabel;
	private JLabel paradaAtLabel;
	private JLabel paradaLabel;
	private JButton personalizarButton;
	private JLabel playerLabel;
	private JLabel posAtLabel;
	private JLabel dorAtLabel;
	private JLabel paseAtLabel;
	private JLabel agresAtLabel;
	private JLabel pesoAtLabel;
	private JLabel alturaAtLabel;
	private JLabel dorsalLabel;
	private JLabel paseLabel;
	private JLabel agresividadLabel;
	private JLabel regateLabel;
	private JLabel velocidadLabel;
	private JLabel resistenciaLabel;
	private JLabel remateLabel;
	private JLabel pesoLabel;
	private JLabel alturaLabel;
	private JSeparator atributosSeparator;
	private JButton atrasButton;
	private JButton escogerEquipoButton;
	private JLabel escogerEquiposLigaLabel;
	private JList skillsEquipsList;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}
	
	public EscogerEquiposView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(800,600));
				this.setLayout(null);
				{
					
					ListModel equipsListModel = 
						new DefaultComboBoxModel(CtrlPresentacion.getInstancia().getEquiposPredefinidosLibres());
					equipsList = new JList();
					this.add(equipsList);
					equipsList.setModel(equipsListModel);
					equipsList.setBounds(67, 114, 300, 400);
					equipsList.setFixedCellHeight(50);
					equipsList.setSelectedIndex(0);
					equipsList.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent evt) {
							equipsListValueChanged(evt);
						}
					});
				}
				{
					jScrollPane1 = new JScrollPane();
					this.add(jScrollPane1);
					jScrollPane1.setBounds(436, 114, 300, 223);
					{
						ListModel skillsEquipsListModel = 
							new DefaultComboBoxModel(
									CtrlPresentacion.getInstancia().getNombresJugadoresPredefinidosString(String.valueOf(equipsList.getSelectedValue())));
						skillsEquipsList = new JList();
						jScrollPane1.setViewportView(skillsEquipsList);
						skillsEquipsList.setModel(skillsEquipsListModel);
						skillsEquipsList.setBounds(436, 114, 300, 259);
						skillsEquipsList.setFixedCellHeight(25);
						skillsEquipsList.setSelectedIndex(0);
						skillsEquipsList.addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent evt) {
								skillsEquipsListValueChanged(evt);
							}
						});
					}
				}
				{
					escogerEquiposLigaLabel = new JLabel();
					this.add(escogerEquiposLigaLabel);
					escogerEquiposLigaLabel.setText("Seleccion de equipos");
					escogerEquiposLigaLabel.setBounds(67, 12, 435, 65);
					escogerEquiposLigaLabel.setFont(new java.awt.Font("Verdana",3,36));
				}
				{
					escogerEquipoButton = new JButton();
					this.add(escogerEquipoButton);
					escogerEquipoButton.setText("Escoger Equipo");
					escogerEquipoButton.setBounds(584, 535, 152, 25);
					escogerEquipoButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							escogerEquipoButtonActionPerformed(evt);
						}
					});
				}
				{
					atrasButton = new JButton();
					this.add(atrasButton);
					atrasButton.setText("Volver");
					atrasButton.setBounds(436, 535, 124, 25);
					atrasButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							atrasButtonActionPerformed(evt);
						}
					});
				}
				{
					atributosSeparator = new JSeparator();
					this.add(atributosSeparator);
					atributosSeparator.setBounds(436, 355, 300, 10);
				}
				{
					alturaLabel = new JLabel();
					this.add(alturaLabel);
					alturaLabel.setText("Altura:");
					alturaLabel.setBounds(436, 376, 96, 15);
				}
				{
					pesoLabel = new JLabel();
					this.add(pesoLabel);
					pesoLabel.setText("Peso:");
					pesoLabel.setBounds(436, 403, 96, 15);
				}
				{
					remateLabel = new JLabel();
					this.add(remateLabel);
					remateLabel.setText("Remate:");
					remateLabel.setBounds(436, 430, 96, 15);
				}
				{
					resistenciaLabel = new JLabel();
					this.add(resistenciaLabel);
					resistenciaLabel.setText("Resistencia:");
					resistenciaLabel.setBounds(436, 457, 96, 15);
				}
				{
					velocidadLabel = new JLabel();
					this.add(velocidadLabel);
					velocidadLabel.setText("Velocidad:");
					velocidadLabel.setBounds(436, 484, 96, 15);
				}
				{
					regateLabel = new JLabel();
					this.add(regateLabel);
					regateLabel.setText("Regate:");
					regateLabel.setBounds(596, 376, 94, 15);
				}
				{
					agresividadLabel = new JLabel();
					this.add(agresividadLabel);
					agresividadLabel.setText("Agresividad:");
					agresividadLabel.setBounds(596, 398, 94, 15);
				}
				{
					paseLabel = new JLabel();
					this.add(paseLabel);
					paseLabel.setText("Pase:");
					paseLabel.setBounds(596, 419, 94, 15);
				}
				{
					dorsalLabel = new JLabel();
					this.add(dorsalLabel);
					dorsalLabel.setText("Dorsal:");
					dorsalLabel.setBounds(596, 440, 94, 15);
				}
				{
					posicionLabel = new JLabel();
					this.add(posicionLabel);
					posicionLabel.setText("Posicion:");
					posicionLabel.setBounds(596, 461, 94, 15);
				}
				{
					atrAlLabel = new JLabel();
					this.add(atrAlLabel);
					atrAlLabel.setBounds(541, 377, 43, 15);
				}
				{
					alturaAtLabel = new JLabel();
					this.add(alturaAtLabel);
					alturaAtLabel.setBounds(541, 377, 30, 15);
				}
				{
					pesoAtLabel = new JLabel();
					this.add(pesoAtLabel);
					pesoAtLabel.setBounds(544, 403, 30, 15);
				}
				{
					remateAtLabel = new JLabel();
					this.add(remateAtLabel);
					remateAtLabel.setBounds(542, 430, 29, 15);
					remateAtLabel.setSize(30, 15);
				}
				{
					resistenciaAtLabel = new JLabel();
					this.add(resistenciaAtLabel);
					resistenciaAtLabel.setBounds(542, 457, 10, 10);
					resistenciaAtLabel.setSize(30, 15);
				}
				{
					velAtLabel = new JLabel();
					this.add(velAtLabel);
					velAtLabel.setBounds(542, 484, 10, 10);
					velAtLabel.setSize(30, 15);
				}
				{
					regateAtLabel = new JLabel();
					this.add(regateAtLabel);
					regateAtLabel.setBounds(693, 376, 10, 10);
					regateAtLabel.setSize(30, 15);
				}
				{
					agresAtLabel = new JLabel();
					this.add(agresAtLabel);
					agresAtLabel.setBounds(693, 398, 30, 15);
				}
				{
					paseAtLabel = new JLabel();
					this.add(paseAtLabel);
					paseAtLabel.setBounds(690, 419, 30, 15);
				}
				{
					dorAtLabel = new JLabel();
					this.add(dorAtLabel);
					dorAtLabel.setBounds(690, 440, 30, 15);
				}
				{
					posAtLabel = new JLabel();
					this.add(posAtLabel);
					posAtLabel.setBounds(690, 461, 98, 15);
				}
				{
					playerLabel = new JLabel();
					this.add(playerLabel);
					playerLabel.setText("Jugador " + CtrlPresentacion.getInstancia().getTurno());
					playerLabel.setBounds(538, 34, 152, 36);
					playerLabel.setFont(new java.awt.Font("Andale Mono",2,20));
				}
				{
					personalizarButton = new JButton();
					this.add(personalizarButton);
					personalizarButton.setText("Personalizar equipo");
					personalizarButton.setBounds(186, 537, 181, 22);
					personalizarButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							personalizarButtonActionPerformed(evt);
						}
					});
				}
				{
					paradaLabel = new JLabel();
					this.add(paradaLabel);
					paradaLabel.setText("Parada:");
					paradaLabel.setBounds(596, 484, 88, 15);
				}
				{
					paradaAtLabel = new JLabel();
					this.add(paradaAtLabel);
					paradaAtLabel.setBounds(690, 484, 41, 15);
				}
				{
					CtrlPresentacion c = CtrlPresentacion.getInstancia();
					String eq = String.valueOf(equipsList.getSelectedValue());
					int n = skillsEquipsList.getSelectedIndex();
					if (n < 0) n = 0;
					alturaAtLabel.setText(String.valueOf(c.getAlturaString(eq,n)));
					pesoAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.PESO)));
					remateAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.REMATE)));
					resistenciaAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.RESISTENCIA)));
					velAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.VELOCIDAD)));
					regateAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.REGATE)));
					agresAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.AGRESIVIDAD)));
					paseAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.PASE)));
					dorAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.DORSAL)));
					paradaAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.PARADA)));
					if(c.getPredefinidosString(eq,n,CtrlPresentacion.TIPO) == Constantes.DEFENSA) {
						posAtLabel.setText("Defensa");
					}
					else if(c.getPredefinidosString(eq,n,CtrlPresentacion.TIPO)  == Constantes.MEDIO) {
						posAtLabel.setText("Medio");
					}
					else if(c.getPredefinidosString(eq,n,CtrlPresentacion.TIPO)  == Constantes.DELANTERO) {
						posAtLabel.setText("Delantero");
					}
					else if(c.getPredefinidosString(eq,n,CtrlPresentacion.TIPO)  == Constantes.PORTERO) {
						posAtLabel.setText("Portero");
					}
				
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void escogerEquipoButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		c.setAgafats(String.valueOf(equipsList.getSelectedValue()),true);
		c.addEquipo(String.valueOf(equipsList.getSelectedValue()));
		int i = c.getTurno();
		if(i <= c.getEquiposUs()) {
			++i;
			c.setTurno(i);
			if(i <= c.getEquiposUs()) {
				playerLabel.setText("Jugador " + String.valueOf(i));
			}
			else if (i > c.getEquiposUs()) {
				c.setTurno(1);
				c.setEquipo();
				CtrlPresentacion.getInstancia().generarCalenario();
				CtrlPresentacion.getInstancia().setJornadas();
				FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_LIGA,true);
			}
		}
		ListModel equipsListModel = 
			new DefaultComboBoxModel(CtrlPresentacion.getInstancia().getEquiposPredefinidosLibres());
		equipsList.setModel(equipsListModel);
		equipsList.setSelectedIndex(0);
		equipsList.updateUI();
	}
	
	private void atrasButtonActionPerformed(ActionEvent evt) {
		String[] options = {
					"Accpetar",
					"Cancelar",
					};
		int n = JOptionPane.showOptionDialog(FutbolFrame.getInstancia(),
					"Seguro que quieres volver al menu de liga?"
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
			FutbolFrame.getInstancia().ponerMenu(PanelMenu.NUEVA_LIGA,true);
		}
	}
	
	private void equipsListValueChanged(ListSelectionEvent evt) {
		int n = equipsList.getSelectedIndex();
		if (n < 0) n = 0;
		if (equipsList.getSelectedValue() != null) {
			String[] s = CtrlPresentacion.getInstancia().getNombresJugadoresPredefinidosString(String.valueOf(equipsList.getSelectedValue()));
			ListModel listajug = new DefaultComboBoxModel(s);
			skillsEquipsList.setModel(listajug);
			skillsEquipsList.setSelectedIndex(0);
			skillsEquipsList.updateUI();
		}

	}
	
	private void skillsEquipsListValueChanged(ListSelectionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		String eq = String.valueOf(equipsList.getSelectedValue());
		int n = skillsEquipsList.getSelectedIndex();
		if (n < 0) n = 0;
		alturaAtLabel.setText(String.valueOf(c.getAlturaString(eq,n)));
		pesoAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.PESO)));
		remateAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.REMATE)));
		resistenciaAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.RESISTENCIA)));
		velAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.VELOCIDAD)));
		regateAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.REGATE)));
		agresAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.AGRESIVIDAD)));
		paseAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.PASE)));
		dorAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.DORSAL)));
		paradaAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.PARADA)));
		if(c.getPredefinidosString(eq,n,CtrlPresentacion.TIPO) == Constantes.DEFENSA) {
			posAtLabel.setText("Defensa");
		}
		else if(c.getPredefinidosString(eq,n,CtrlPresentacion.TIPO)  == Constantes.MEDIO) {
			posAtLabel.setText("Medio");
		}
		else if(c.getPredefinidosString(eq,n,CtrlPresentacion.TIPO)  == Constantes.DELANTERO) {
			posAtLabel.setText("Delantero");
		}
		else if(c.getPredefinidosString(eq,n,CtrlPresentacion.TIPO)  == Constantes.PORTERO) {
			posAtLabel.setText("Portero");
		}
	
	}
	
	private void personalizarButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion.getInstancia().setEquipoPersonalizado(String.valueOf(equipsList.getSelectedValue()));
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.PERSONALIZAR_EQUIPO, true);
	}

}

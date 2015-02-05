/*
 * Jaume Vinyes Navas
 */


package vistas;
import javax.swing.ComboBoxModel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vistas.FutbolFrame.PanelMenu;

import controladores.CtrlPresentacion;
import domain.Constantes;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PersonalizarEquipoView extends JPanel {
	private static final long serialVersionUID = 6816440767208808179L;
	private JLabel titloLabel;
	private JScrollPane jScrollPane1;
	private JSeparator jSeparator;
	private JButton resetEquipoButton;
	private JTextField localidadTextField;
	private JLabel localidadLabel;
	private JTextField nombreCampoTextField;
	private JButton volverButton;
	private JButton accpetarButton;
	private JLabel nombreCampoLabel;
	private JTextField nombreEquipoTextField;
	private JLabel equipoLabel;
	private JList futbolistasList;
	private JLabel alturaLabel;
	private JLabel pesoLabel;
	private JLabel costePasLabel;
	private JLabel costeResLabel;
	private JLabel costeVelLabel;
	private JLabel costeRemLabel;
	private JLabel costeAgLabel;
	private JLabel equipoPerLabel;
	private JLabel costeParadaLabel;
	private JButton paradaLessButton;
	private JButton paraddaPlusButton;
	private JLabel paradaAtLabel;
	private JLabel ParadaLabel;
	private JLabel costeRegLabel;
	private JLabel expAtLabel;
	private JLabel expLabel;
	private JLabel remateLabel;
	private JLabel resistenciaLabel;
	private JLabel velocidadLabel;
	private JLabel regateLabel;
	private JLabel agresividadLabel;
	private JLabel paseLabel;
	private JLabel posicionLabel;
	private JButton regateLessButton;
	private JButton regatePlusButton;
	private JSeparator jSeparator2;
	private JComboBox posicionComboBox;
	private JTextField pesTextField;
	private JTextField alturaTextField;
	private JTextField nombreTextField;
	private JLabel nombreFutbolistaLabel;
	private JLabel remateAtLabel;
	private JLabel resistenciaAtLabel;
	private JLabel velAtLabel;
	private JLabel regateAtLabel;
	private JLabel agresAtLabel;
	private JLabel paseAtLabel;
	private JButton agrePlusButton;
	private JButton agreLessButton;
	private JButton pasePlusButton;
	private JButton paseLessButton;
	private JButton resisPlusButton;
	private JButton resisLessButton;
	private JButton rematePlusButton;
	private JButton remateLessButton;
	private JButton veloPlusButton;
	private JButton veloLessButton;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}
	
	public PersonalizarEquipoView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(800, 600));
				this.setLayout(null);
				{
					titloLabel = new JLabel();
					this.add(titloLabel);
					titloLabel.setText("Personalizar ");
					titloLabel.setFont(new java.awt.Font("Verdana",3,36));
					titloLabel.setBounds(40, 30, 324, 45);
				}
				{
					jScrollPane1 = new JScrollPane();
					this.add(jScrollPane1);
					jScrollPane1.setBounds(40, 127, 235, 400);
					{
						String eq = CtrlPresentacion.getInstancia().getEquipoPersonalizado();
						ListModel futbolistasListModel;
						if(eq != null) {
							futbolistasListModel = 
								new DefaultComboBoxModel(
										CtrlPresentacion.getInstancia().getNombresJugadoresPredefinidosString(eq));
						}
						else {
							futbolistasListModel =
							new DefaultComboBoxModel(
									new String[] { "Item One", "Item Two" });
						}
						futbolistasList = new JList();
						jScrollPane1.setViewportView(futbolistasList);
						futbolistasList.setModel(futbolistasListModel);
						futbolistasList.setBounds(40, 127, 300, 400);
						futbolistasList.setFixedCellHeight(50);
						futbolistasList.setSelectedIndex(0);
						futbolistasList.addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent evt) {
								futbolistasListValueChanged(evt);
							}
						});
					}
				}
				{
					equipoLabel = new JLabel();
					this.add(equipoLabel);
					equipoLabel.setText("Nombre Equipo:");
					equipoLabel.setBounds(305, 149, 126, 15);
				}
				{
					nombreEquipoTextField = new JTextField();
					this.add(nombreEquipoTextField);
					nombreEquipoTextField.setBounds(437, 146, 220, 22);
				}
				{
					nombreCampoLabel = new JLabel();
					this.add(nombreCampoLabel);
					nombreCampoLabel.setText("Nombre Campo:");
					nombreCampoLabel.setBounds(305, 179, 126, 15);
				}
				{
					nombreCampoTextField = new JTextField();
					this.add(nombreCampoTextField);
					nombreCampoTextField.setBounds(437, 176, 220, 22);
				}
				{
					localidadLabel = new JLabel();
					this.add(localidadLabel);
					localidadLabel.setText("Localidad:");
					localidadLabel.setBounds(305, 209, 126, 15);
				}
				{
					localidadTextField = new JTextField();
					this.add(localidadTextField);
					localidadTextField.setBounds(437, 206, 220, 22);
				}
				{
					resetEquipoButton = new JButton();
					this.add(resetEquipoButton);
					resetEquipoButton.setText("Realizar Cambios");
					resetEquipoButton.setBounds(515, 240, 199, 39);
					resetEquipoButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							resetEquipoButtonActionPerformed(evt);
						}
					});
				}
				{
					jSeparator = new JSeparator();
					this.add(jSeparator);
					jSeparator.setBounds(305, 299, 426, 10);
				}
				{
					alturaLabel = new JLabel();
					this.add(alturaLabel);
					alturaLabel.setText("Altura:");
					alturaLabel.setBounds(302, 357, 96, 15);
				}
				{
					pesoLabel = new JLabel();
					this.add(pesoLabel);
					pesoLabel.setText("Peso:");
					pesoLabel.setBounds(302, 388, 96, 15);
				}
				{
					remateLabel = new JLabel();
					this.add(remateLabel);
					remateLabel.setText("Remate:");
					remateLabel.setBounds(513, 448, 96, 15);
				}
				{
					resistenciaLabel = new JLabel();
					this.add(resistenciaLabel);
					resistenciaLabel.setText("Resistencia:");
					resistenciaLabel.setBounds(513, 419, 96, 15);
				}
				{
					velocidadLabel = new JLabel();
					this.add(velocidadLabel);
					velocidadLabel.setText("Velocidad:");
					velocidadLabel.setBounds(513, 476, 96, 15);
				}
				{
					regateLabel = new JLabel();
					this.add(regateLabel);
					regateLabel.setText("Regate:");
					regateLabel.setBounds(515, 324, 94, 15);
				}
				{
					agresividadLabel = new JLabel();
					this.add(agresividadLabel);
					agresividadLabel.setText("Agresividad:");
					agresividadLabel.setBounds(515, 355, 94, 15);
				}
				{
					paseLabel = new JLabel();
					this.add(paseLabel);
					paseLabel.setText("Pase:");
					paseLabel.setBounds(516, 388, 94, 15);
				}
				{
					posicionLabel = new JLabel();
					this.add(posicionLabel);
					posicionLabel.setText("Posicion:");
					posicionLabel.setBounds(302, 421, 94, 15);
				}
				{
					remateAtLabel = new JLabel();
					this.add(remateAtLabel);
					remateAtLabel.setBounds(596, 453, 30, 15);
				}
				{
					resistenciaAtLabel = new JLabel();
					this.add(resistenciaAtLabel);
					resistenciaAtLabel.setBounds(596, 421, 30, 15);
				}
				{
					velAtLabel = new JLabel();
					this.add(velAtLabel);
					velAtLabel.setBounds(596, 474, 30, 15);
				}
				{
					regateAtLabel = new JLabel();
					this.add(regateAtLabel);
					regateAtLabel.setBounds(596, 325, 30, 15);
				}
				{
					agresAtLabel = new JLabel();
					this.add(agresAtLabel);
					agresAtLabel.setBounds(596, 356, 30, 15);
				}
				{
					paseAtLabel = new JLabel();
					this.add(paseAtLabel);
					paseAtLabel.setBounds(596, 390, 30, 15);
				}
				{
					nombreFutbolistaLabel = new JLabel();
					this.add(nombreFutbolistaLabel);
					nombreFutbolistaLabel.setText("Nombre:");
					nombreFutbolistaLabel.setBounds(301, 324, 95, 15);
				}
				{
					nombreTextField = new JTextField();
					this.add(nombreTextField);
					nombreTextField.setBounds(373, 321, 124, 22);
				}
				{
					alturaTextField = new JTextField();
					this.add(alturaTextField);
					alturaTextField.setBounds(374, 354, 124, 22);
				}
				{
					pesTextField = new JTextField();
					this.add(pesTextField);
					pesTextField.setBounds(374, 385, 124, 22);
				}
				{
					ComboBoxModel posicionComboBoxModel = 
						new DefaultComboBoxModel(
								new String[] { "Defensa", "Medio", "Delantero", "Portero" });
					posicionComboBox = new JComboBox();
					this.add(posicionComboBox);
					posicionComboBox.setModel(posicionComboBoxModel);
					posicionComboBox.setBounds(374, 417, 124, 22);
				}
				{
					accpetarButton = new JButton();
					this.add(accpetarButton);
					accpetarButton.setText("Realizar cambios");
					accpetarButton.setBounds(299, 463, 197, 42);
					accpetarButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							accpetarButtonActionPerformed(evt);
						}
					});
				}
				{
					volverButton = new JButton();
					this.add(volverButton);
					volverButton.setText("Volver");
					volverButton.setBounds(458, 548, 102, 22);
					volverButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							volverButtonActionPerformed(evt);
						}
					});
				}
				{
					jSeparator2 = new JSeparator();
					this.add(jSeparator2);
					jSeparator2.setBounds(305, 535, 426, 10);
				}
				{
					regatePlusButton = new JButton();
					this.add(regatePlusButton);
					regatePlusButton.setText("+");
					regatePlusButton.setBounds(631, 321, 42, 23);
					regatePlusButton.setFont(new java.awt.Font("Verdana",0,8));
					regatePlusButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							regatePlusButtonActionPerformed(evt);
						}
					});
				}
				{
					regateLessButton = new JButton();
					this.add(regateLessButton);
					regateLessButton.setText("-");
					regateLessButton.setFont(new java.awt.Font("Verdana",0,8));
					regateLessButton.setBounds(678, 321, 42, 23);
					regateLessButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							regateLessButtonActionPerformed(evt);
						}
					});
				}
				{
					agrePlusButton = new JButton();
					this.add(agrePlusButton);
					agrePlusButton.setText("+");
					agrePlusButton.setBounds(631, 350, 42, 23);
					agrePlusButton.setFont(new java.awt.Font("Verdana",0,8));
					agrePlusButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							agrePlusButtonActionPerformed(evt);
						}
					});
				}
				{
					agreLessButton = new JButton();
					this.add(agreLessButton);
					agreLessButton.setText("-");
					agreLessButton.setFont(new java.awt.Font("Verdana",0,8));
					agreLessButton.setBounds(678, 350, 42, 23);
					agreLessButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							agreLessButtonActionPerformed(evt);
						}
					});
				}
				{
					pasePlusButton = new JButton();
					this.add(pasePlusButton);
					pasePlusButton.setText("+");
					pasePlusButton.setBounds(631, 380, 42, 23);
					pasePlusButton.setFont(new java.awt.Font("Verdana",0,8));
					pasePlusButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							pasePlusButtonActionPerformed(evt);
						}
					});
				}
				{
					paseLessButton = new JButton();
					this.add(paseLessButton);
					paseLessButton.setText("-");
					paseLessButton.setFont(new java.awt.Font("Verdana",0,8));
					paseLessButton.setBounds(678, 380, 42, 23);
					paseLessButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							paseLessButtonActionPerformed(evt);
						}
					});
				}
				{
					resisPlusButton = new JButton();
					this.add(resisPlusButton);
					resisPlusButton.setText("+");
					resisPlusButton.setBounds(631, 410, 42, 23);
					resisPlusButton.setFont(new java.awt.Font("Verdana",0,8));
					resisPlusButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							resisPlusButtonActionPerformed(evt);
						}
					});
				}
				{
					resisLessButton = new JButton();
					this.add(resisLessButton);
					resisLessButton.setText("-");
					resisLessButton.setFont(new java.awt.Font("Verdana",0,8));
					resisLessButton.setBounds(678, 410, 42, 23);
					resisLessButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							resisLessButtonActionPerformed(evt);
						}
					});
				}
				{
					rematePlusButton = new JButton();
					this.add(rematePlusButton);
					rematePlusButton.setText("+");
					rematePlusButton.setBounds(631, 440, 42, 23);
					rematePlusButton.setFont(new java.awt.Font("Verdana",0,8));
					rematePlusButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							rematePlusButtonActionPerformed(evt);
						}
					});
				}
				{
					remateLessButton = new JButton();
					this.add(remateLessButton);
					remateLessButton.setText("-");
					remateLessButton.setFont(new java.awt.Font("Verdana",0,8));
					remateLessButton.setBounds(678, 440, 42, 23);
					remateLessButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							remateLessButtonActionPerformed(evt);
						}
					});
				}
				{
					veloPlusButton = new JButton();
					this.add(veloPlusButton);
					veloPlusButton.setText("+");
					veloPlusButton.setBounds(631, 474, 42, 23);
					veloPlusButton.setFont(new java.awt.Font("Verdana",0,8));
					veloPlusButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							veloPlusButtonActionPerformed(evt);
						}
					});
				}
				{
					veloLessButton = new JButton();
					this.add(veloLessButton);
					veloLessButton.setText("-");
					veloLessButton.setFont(new java.awt.Font("Verdana",0,8));
					veloLessButton.setBounds(678, 474, 42, 23);
					veloLessButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							veloLessButtonActionPerformed(evt);
						}
					});
				}
				{
					expLabel = new JLabel();
					this.add(expLabel);
					expLabel.setText("Exp:");
					expLabel.setBounds(631, 551, 38, 15);
				}
				{
					expAtLabel = new JLabel();
					this.add(expAtLabel);
					expAtLabel.setBounds(675, 551, 103, 15);
				}
				{
					costeRegLabel = new JLabel();
					this.add(costeRegLabel);
					costeRegLabel.setBounds(731, 324, 57, 16);
				}
				{
					costeAgLabel = new JLabel();
					this.add(costeAgLabel);
					costeAgLabel.setBounds(731, 352, 57, 16);
				}
				{
					costePasLabel = new JLabel();
					this.add(costePasLabel);
					costePasLabel.setBounds(731, 380, 57, 16);
				}
				{
					costeResLabel = new JLabel();
					this.add(costeResLabel);
					costeResLabel.setBounds(731, 410, 57, 16);
				}
				{
					costeRemLabel = new JLabel();
					this.add(costeRemLabel);
					costeRemLabel.setBounds(731, 438, 57, 16);
				}
				{
					costeVelLabel = new JLabel();
					this.add(costeVelLabel);
					costeVelLabel.setBounds(731, 480, 57, 16);
				}
				{
					ParadaLabel = new JLabel();
					this.add(ParadaLabel);
					ParadaLabel.setText("Parada:");
					ParadaLabel.setBounds(513, 503, 104, 15);
				}
				{
					paradaAtLabel = new JLabel();
					this.add(paradaAtLabel);
					paradaAtLabel.setBounds(596, 502, 28, 16);
				}
				{
					paraddaPlusButton = new JButton();
					this.add(paraddaPlusButton);
					paraddaPlusButton.setText("+");
					paraddaPlusButton.setBounds(631, 506, 42, 23);
					paraddaPlusButton.setFont(new java.awt.Font("Verdana",0,8));
					paraddaPlusButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							paraddaPlusButtonActionPerformed(evt);
						}
					});
				}
				{
					paradaLessButton = new JButton();
					this.add(paradaLessButton);
					paradaLessButton.setText("-");
					paradaLessButton.setBounds(679, 506, 21, 22);
					paradaLessButton.setSize(42, 23);
					paradaLessButton.setFont(new java.awt.Font("Verdana",0,8));
					paradaLessButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							paradaLessButtonActionPerformed(evt);
						}
					});
				}
				{
					costeParadaLabel = new JLabel();
					this.add(costeParadaLabel);
					costeParadaLabel.setBounds(732, 508, 10, 10);
					costeParadaLabel.setSize(57, 16);
				}
				{
					equipoPerLabel = new JLabel();
					this.add(equipoPerLabel);
					equipoPerLabel.setBounds(373, 30, 284, 45);
					equipoPerLabel.setFont(new java.awt.Font("Verdana",2,22));
				}
				CtrlPresentacion c = CtrlPresentacion.getInstancia();
				String eq = c.getEquipoPersonalizado();
				if(eq != null) {
					int n = futbolistasList.getSelectedIndex();
					if (n < 0) n = 0;
					alturaTextField.setText(String.valueOf(c.getAlturaString(eq,n)));
					pesTextField.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.PESO)));
					remateAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.REMATE)));
					resistenciaAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.RESISTENCIA)));
					velAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.VELOCIDAD)));
					regateAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.REGATE)));
					agresAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.AGRESIVIDAD)));
					paseAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.PASE)));
					paradaAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.PARADA)));
					equipoPerLabel.setText(c.getEquipoPersonalizado());
					updateCostes(eq, n);
					
					expAtLabel.setText(String.valueOf(c.getExperiencia(eq,n)));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void volverButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.ESCOGER_EQUIPOS, true);
	}
	
	private void futbolistasListValueChanged(ListSelectionEvent evt) {
		
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		String eq = c.getEquipoPersonalizado();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		alturaTextField.setText(String.valueOf(c.getAlturaString(eq,n)));
		pesTextField.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.PESO)));
		remateAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.REMATE)));
		resistenciaAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.RESISTENCIA)));
		velAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.VELOCIDAD)));
		regateAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.REGATE)));
		agresAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.AGRESIVIDAD)));
		paseAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.PASE)));
		paradaAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.PARADA)));
		nombreTextField.setText("");
	
		updateCostes(eq, n);
		
	}
	
	private void updateCostes(String eq, int n) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		costePasLabel.setText(String.valueOf(c.calculaExperiencia(Constantes.PASE , eq, n)));
		costeResLabel.setText(String.valueOf(c.calculaExperiencia(Constantes.RESISTENCIA , eq, n)));
		costeVelLabel.setText(String.valueOf(c.calculaExperiencia(Constantes.VELOCIDAD , eq, n)));
		costeRemLabel.setText(String.valueOf(c.calculaExperiencia(Constantes.REMATE , eq, n)));
		costeAgLabel.setText(String.valueOf(c.calculaExperiencia(Constantes.AGRESIVIDAD , eq, n)));
		costeRegLabel.setText(String.valueOf(c.calculaExperiencia(Constantes.REGATE , eq, n)));
		costeParadaLabel.setText(String.valueOf(c.calculaExperiencia(Constantes.PARADA, eq, n)));
		expAtLabel.setText(String.valueOf(c.getExperiencia(eq,n)));
	}

	private void resetEquipoButtonActionPerformed(ActionEvent evt) {
		if(nombreEquipoTextField.getText().length() > 0 && nombreCampoTextField.getText().length() > 0
				&& localidadTextField.getText().length() > 0 && CtrlPresentacion.getInstancia().esEquipoValido(nombreEquipoTextField.getText())) {
			
			CtrlPresentacion.getInstancia().setEquipoPredefinido(CtrlPresentacion.getInstancia().getEquipoPersonalizado(),
					nombreEquipoTextField.getText(), nombreCampoTextField.getText(), localidadTextField.getText());
			
			equipoPerLabel.setText(nombreEquipoTextField.getText());
			
			CtrlPresentacion.getInstancia().setEquipoPersonalizado(equipoPerLabel.getText());
			futbolistasList.updateUI();
			
			
		}
		else {
			String[] options = {
					"Accpetar"
						};
			int n = JOptionPane.showOptionDialog(FutbolFrame.getInstancia(),
						"No se pudo modificar el equipo."
						+ " El equipo debe tener nombre, campo y localidad. El equipo no puede tener un nombre repetido",
						"Aviso!",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE,
						null,
						options,
						options[0]);
			
			if(n == 0) {
				FutbolFrame.getInstancia().ponerMenu(PanelMenu.PERSONALIZAR_EQUIPO,true);
			}
		}
	}
	
	
	private void accpetarButtonActionPerformed(ActionEvent evt) {
		if(nombreTextField.getText().length() > 0 && pesTextField.getText().length() > 0 &&
				alturaTextField.getText().length() > 0 && dadesValides()) {
			Integer posicion = new Integer(0);
			if(posicionComboBox.getSelectedItem().equals("Defensa")) {
				posicion = Constantes.DEFENSA;
			}
			else if(posicionComboBox.getSelectedItem().equals("Medio")) {
				posicion = Constantes.MEDIO;
			}
			else if(posicionComboBox.getSelectedItem().equals("Delantero")) {
				posicion = Constantes.DELANTERO;
			}
			else if(posicionComboBox.getSelectedItem().equals("Portero")) {
				posicion = Constantes.PORTERO;
			}
			CtrlPresentacion.getInstancia().modificaJugador(nombreTextField.getText(),
					alturaTextField.getText(),pesTextField.getText(),posicion,
					CtrlPresentacion.getInstancia().getEquipoPersonalizado(),futbolistasList.getSelectedIndex());
			
			String eq = CtrlPresentacion.getInstancia().getEquipoPersonalizado();
			ListModel futbolistasListModel = 
				new DefaultComboBoxModel(
						CtrlPresentacion.getInstancia().getNombresJugadoresPredefinidosString(eq));
			futbolistasList.setModel(futbolistasListModel);
			futbolistasList.setSelectedIndex(0);
			nombreTextField.setText("");
		}
		else {
			String[] options = {
					"Accpetar"
						};
			int n = JOptionPane.showOptionDialog(FutbolFrame.getInstancia(),
						"No se pudo modificar el futbolista."
						+ " El futbolista debe tener valores correctos.",
						"Aviso!",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE,
						null,
						options,
						options[0]);
			
			if(n == 0) {
				FutbolFrame.getInstancia().ponerMenu(PanelMenu.PERSONALIZAR_EQUIPO,false);
			}
		}

	}
	
	private boolean dadesValides() {
		int pes = 0;
		float altura = 0;
		try{
			 pes = Integer.parseInt(pesTextField.getText());
			 altura = Float.valueOf(alturaTextField.getText());
		}
		catch(Exception e){
			return false;
		}
		if(pes > 0 && altura > 0 && altura < 2.15) {
			return true;
		}
		else {
			return false;
		}
	}

	private void regatePlusButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		String eq = c.getEquipoPersonalizado();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.subirAtributo(Constantes.REGATE, eq, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(eq,n)));
		regateAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.REGATE)));
		updateCostes(eq,n);
	}
	
	private void agrePlusButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		String eq = c.getEquipoPersonalizado();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.subirAtributo(Constantes.AGRESIVIDAD, eq, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(eq,n)));
		agresAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.AGRESIVIDAD)));
		updateCostes(eq,n);
	}
	
	private void pasePlusButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		String eq = c.getEquipoPersonalizado();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.subirAtributo(Constantes.PASE, eq, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(eq,n)));
		paseAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.PASE)));
		updateCostes(eq,n);
	}
	
	private void resisPlusButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		String eq = c.getEquipoPersonalizado();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.subirAtributo(Constantes.RESISTENCIA, eq, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(eq,n)));
		resistenciaAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.RESISTENCIA)));
		updateCostes(eq,n);
	}
	
	private void rematePlusButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		String eq = c.getEquipoPersonalizado();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.subirAtributo(Constantes.REMATE, eq, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(eq,n)));
		remateAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.REMATE)));
		updateCostes(eq,n);
	}
	
	private void veloPlusButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		String eq = c.getEquipoPersonalizado();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.subirAtributo(Constantes.VELOCIDAD, eq, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(eq,n)));
		velAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.VELOCIDAD)));
		updateCostes(eq,n);
	}
	
	private void paraddaPlusButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		String eq = c.getEquipoPersonalizado();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.subirAtributo(Constantes.PARADA, eq, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(eq,n)));
		paradaAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.PARADA)));
		updateCostes(eq,n);
	}

	
	private void regateLessButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		String eq = c.getEquipoPersonalizado();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.bajarAtributo(Constantes.REGATE, eq, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(eq,n)));
		regateAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.REGATE)));
		updateCostes(eq,n);
	}
	
	private void agreLessButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		String eq = c.getEquipoPersonalizado();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.bajarAtributo(Constantes.AGRESIVIDAD, eq, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(eq,n)));
		agresAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.AGRESIVIDAD)));
		updateCostes(eq,n);
	}
	
	private void paseLessButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		String eq = c.getEquipoPersonalizado();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.bajarAtributo(Constantes.PASE, eq, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(eq,n)));
		paseAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.PASE)));
		updateCostes(eq,n);
	}
	
	private void resisLessButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		String eq = c.getEquipoPersonalizado();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.bajarAtributo(Constantes.RESISTENCIA, eq, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(eq,n)));
		resistenciaAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.RESISTENCIA)));
		updateCostes(eq,n);
	}
	
	private void remateLessButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		String eq = c.getEquipoPersonalizado();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.bajarAtributo(Constantes.REMATE, eq, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(eq,n)));
		remateAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.REMATE)));
		updateCostes(eq,n);
	}
	
	private void veloLessButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		String eq = c.getEquipoPersonalizado();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.bajarAtributo(Constantes.VELOCIDAD, eq, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(eq,n)));
		velAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.VELOCIDAD)));
		updateCostes(eq,n);
	}
	
	private void paradaLessButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		String eq = c.getEquipoPersonalizado();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.bajarAtributo(Constantes.PARADA, eq, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(eq,n)));
		paradaAtLabel.setText(String.valueOf(c.getPredefinidosString(eq,n,CtrlPresentacion.PARADA)));
		updateCostes(eq,n);
	}
	
}

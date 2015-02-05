/*
 * Jaume Vinyes Navas
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
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vistas.FutbolFrame.PanelMenu;

import controladores.CtrlPresentacion;
import domain.Constantes;

public class AtributosView extends JPanel {
	
	private static final long serialVersionUID = -3897922668731452250L;
	private JScrollPane jScrollPane1;
	private JList futbolistasList;
	private JLabel tituloLabel;
	private JButton volverButton;
	private JLabel alturaLabel;
	private JLabel pesoLabel;
	private JLabel costePasLabel;
	private JLabel costeResLabel;
	private JLabel costeVelLabel;
	private JLabel costeRemLabel;
	private JLabel costeAgLabel;
	private JLabel posicionAtLabel;
	private JLabel alturaAtLabel;
	private JLabel dorsalAtLabel;
	private JLabel pesoAtLabel;
	private JLabel costeParadaLabel;
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
	private JLabel dorsalLabel;
	private JLabel posicionLabel;
	private JButton regatePlusButton;
	private JLabel nombreFutbolistaLabel;
	private JLabel remateAtLabel;
	private JLabel resistenciaAtLabel;
	private JLabel velAtLabel;
	private JLabel regateAtLabel;
	private JLabel agresAtLabel;
	private JLabel paseAtLabel;
	private JButton agrePlusButton;
	private JButton pasePlusButton;
	private JButton resisPlusButton;
	private JButton rematePlusButton;
	private JButton veloPlusButton;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}
	
	
	public AtributosView() {
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
					tituloLabel.setText("Modificar Atributos");
					tituloLabel.setBounds(42, 23, 372, 73);
					tituloLabel.setFont(new java.awt.Font("Verdana",3,28));
				}
				{
					jScrollPane1 = new JScrollPane();
					this.add(jScrollPane1);
					jScrollPane1.setBounds(42, 113, 340, 445);
					{
						ListModel futbolistasListModel = 
							new DefaultComboBoxModel(
									CtrlPresentacion.getInstancia().getNombresFutbolistas());
						futbolistasList = new JList();
						jScrollPane1.setViewportView(futbolistasList);
						futbolistasList.setModel(futbolistasListModel);
						futbolistasList.setBounds(42, 113, 340, 445);
						futbolistasList.setFixedCellHeight(25);
						futbolistasList.setSelectedIndex(0);
						futbolistasList.setPreferredSize(new java.awt.Dimension(230, 442));
						futbolistasList.addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent evt) {
								futbolistasListValueChanged(evt);
							}
						});
					}
				}
				{
					alturaLabel = new JLabel();
					this.add(alturaLabel);
					alturaLabel.setText("Altura:");
					alturaLabel.setBounds(612, 133, 72, 15);
				}
				{
					pesoLabel = new JLabel();
					pesoLabel.setText("Peso:");
					pesoLabel.setBounds(305, 419, 96, 15);
				}
				{
					remateLabel = new JLabel();
					this.add(remateLabel);
					remateLabel.setText("Remate:");
					remateLabel.setBounds(429, 355, 96, 15);
				}
				{
					resistenciaLabel = new JLabel();
					this.add(resistenciaLabel);
					resistenciaLabel.setText("Resistencia:");
					resistenciaLabel.setBounds(429, 326, 96, 15);
				}
				{
					velocidadLabel = new JLabel();
					this.add(velocidadLabel);
					velocidadLabel.setText("Velocidad:");
					velocidadLabel.setBounds(430, 383, 96, 15);
				}
				{
					regateLabel = new JLabel();
					this.add(regateLabel);
					regateLabel.setText("Regate:");
					regateLabel.setBounds(430, 231, 94, 15);
				}
				{
					agresividadLabel = new JLabel();
					this.add(agresividadLabel);
					agresividadLabel.setText("Agresividad:");
					agresividadLabel.setBounds(430, 262, 94, 15);
				}
				{
					paseLabel = new JLabel();
					this.add(paseLabel);
					paseLabel.setText("Pase:");
					paseLabel.setBounds(430, 295, 94, 15);
				}
				{
					dorsalLabel = new JLabel();
					this.add(dorsalLabel);
					dorsalLabel.setText("Dorsal:");
					dorsalLabel.setBounds(430, 162, 71, 15);
				}
				{
					posicionLabel = new JLabel();
					this.add(posicionLabel);
					posicionLabel.setText("Posicion:");
					posicionLabel.setBounds(612, 162, 72, 15);
				}
				{
					remateAtLabel = new JLabel();
					this.add(remateAtLabel);
					remateAtLabel.setText(
								String.valueOf(CtrlPresentacion.getInstancia().getRemate(
										futbolistasList.getSelectedIndex()))
							);
					remateAtLabel.setBounds(545, 361, 30, 15);
				}
				{
					resistenciaAtLabel = new JLabel();
					this.add(resistenciaAtLabel);
					resistenciaAtLabel.setText(
							String.valueOf(CtrlPresentacion.getInstancia().getResistencia(
									futbolistasList.getSelectedIndex()))
						);
					resistenciaAtLabel.setBounds(545, 329, 30, 15);
				}
				{
					velAtLabel = new JLabel();
					this.add(velAtLabel);
					velAtLabel.setText(
							String.valueOf(CtrlPresentacion.getInstancia().getVelocidad(
									futbolistasList.getSelectedIndex()))
						);
					velAtLabel.setBounds(545, 382, 30, 15);
				}
				{
					regateAtLabel = new JLabel();
					this.add(regateAtLabel);
					regateAtLabel.setText(
							String.valueOf(CtrlPresentacion.getInstancia().getRegate(
									futbolistasList.getSelectedIndex()))
						);
					regateAtLabel.setBounds(545, 233, 30, 15);
				}
				{
					agresAtLabel = new JLabel();
					this.add(agresAtLabel);
					agresAtLabel.setText(
							String.valueOf(CtrlPresentacion.getInstancia().getAgresividad(
									futbolistasList.getSelectedIndex()))
						);
					agresAtLabel.setBounds(545, 264, 30, 15);
				}
				{
					paseAtLabel = new JLabel();
					this.add(paseAtLabel);
					paseAtLabel.setText(
							String.valueOf(CtrlPresentacion.getInstancia().getPase(
									futbolistasList.getSelectedIndex()))
						);
					paseAtLabel.setBounds(545, 298, 30, 15);
				}
				{
					nombreFutbolistaLabel = new JLabel();
					this.add(nombreFutbolistaLabel);
					nombreFutbolistaLabel.setText("Peso:");
					nombreFutbolistaLabel.setBounds(435, 135, 72, 15);
				}
				{
					volverButton = new JButton();
					this.add(volverButton);
					volverButton.setText("Volver");
					volverButton.setBounds(619, 548, 102, 22);
					volverButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							volverButtonActionPerformed(evt);
						}
					});
				}
				{
					regatePlusButton = new JButton();
					this.add(regatePlusButton);
					regatePlusButton.setText("+");
					regatePlusButton.setBounds(612, 227, 42, 23);
					regatePlusButton.setFont(new java.awt.Font("Verdana",0,8));
					regatePlusButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							regatePlusButtonActionPerformed(evt);
						}
					});
				}
				{
					agrePlusButton = new JButton();
					this.add(agrePlusButton);
					agrePlusButton.setText("+");
					agrePlusButton.setBounds(612, 256, 42, 23);
					agrePlusButton.setFont(new java.awt.Font("Verdana",0,8));
					agrePlusButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							agrePlusButtonActionPerformed(evt);
						}
					});
				}
	
				{
					pasePlusButton = new JButton();
					this.add(pasePlusButton);
					pasePlusButton.setText("+");
					pasePlusButton.setBounds(612, 286, 42, 23);
					pasePlusButton.setFont(new java.awt.Font("Verdana",0,8));
					pasePlusButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							pasePlusButtonActionPerformed(evt);
						}
					});
				}
				{
					resisPlusButton = new JButton();
					this.add(resisPlusButton);
					resisPlusButton.setText("+");
					resisPlusButton.setBounds(612, 316, 42, 23);
					resisPlusButton.setFont(new java.awt.Font("Verdana",0,8));
					resisPlusButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							resisPlusButtonActionPerformed(evt);
						}
					});
				}
				{
					rematePlusButton = new JButton();
					this.add(rematePlusButton);
					rematePlusButton.setText("+");
					rematePlusButton.setBounds(612, 346, 42, 23);
					rematePlusButton.setFont(new java.awt.Font("Verdana",0,8));
					rematePlusButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							rematePlusButtonActionPerformed(evt);
						}
					});
				}
				{
					veloPlusButton = new JButton();
					this.add(veloPlusButton);
					veloPlusButton.setText("+");
					veloPlusButton.setBounds(612, 380, 42, 23);
					veloPlusButton.setFont(new java.awt.Font("Verdana",0,8));
					veloPlusButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							veloPlusButtonActionPerformed(evt);
						}
					});
				}
				{
					expLabel = new JLabel();
					this.add(expLabel);
					expLabel.setText("Experiencia:");
					expLabel.setBounds(429, 467, 86, 15);
				}
				{
					expAtLabel = new JLabel();
					this.add(expAtLabel);
					expAtLabel.setBounds(521, 467, 172, 15);
				}
				{
					costeRegLabel = new JLabel();
					this.add(costeRegLabel);
					costeRegLabel.setBounds(686, 232, 57, 16);
				}
				{
					costeAgLabel = new JLabel();
					this.add(costeAgLabel);
					costeAgLabel.setBounds(686, 260, 57, 16);
				}
				{
					costePasLabel = new JLabel();
					this.add(costePasLabel);
					costePasLabel.setBounds(686, 288, 57, 16);
				}
				{
					costeResLabel = new JLabel();
					this.add(costeResLabel);
					costeResLabel.setBounds(686, 318, 57, 16);
				}
				{
					costeRemLabel = new JLabel();
					this.add(costeRemLabel);
					costeRemLabel.setBounds(686, 346, 57, 16);
				}
				{
					costeVelLabel = new JLabel();
					this.add(costeVelLabel);
					costeVelLabel.setBounds(686, 388, 57, 16);
				}
				{
					ParadaLabel = new JLabel();
					this.add(ParadaLabel);
					ParadaLabel.setText("Parada:");
					ParadaLabel.setBounds(430, 410, 104, 15);
				}
				{
					paradaAtLabel = new JLabel();
					this.add(paradaAtLabel);
					paradaAtLabel.setText(
							String.valueOf(CtrlPresentacion.getInstancia().getParada(
									futbolistasList.getSelectedIndex()))
						);
					paradaAtLabel.setBounds(545, 415, 36, 16);
				}
				{
					paraddaPlusButton = new JButton();
					this.add(paraddaPlusButton);
					paraddaPlusButton.setText("+");
					paraddaPlusButton.setBounds(612, 412, 42, 23);
					paraddaPlusButton.setFont(new java.awt.Font("Verdana",0,8));
					paraddaPlusButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							paraddaPlusButtonActionPerformed(evt);
						}
					});
				}
				{
					costeParadaLabel = new JLabel();
					this.add(costeParadaLabel);
					costeParadaLabel.setBounds(687, 416, 57, 16);
				}
				{
					pesoAtLabel = new JLabel();
					this.add(pesoAtLabel);
					pesoAtLabel.setText(
							String.valueOf(CtrlPresentacion.getInstancia().getPeso(
									futbolistasList.getSelectedIndex()))
						);
					pesoAtLabel.setBounds(507, 135, 58, 15);
				}
				{
					dorsalAtLabel = new JLabel();
					this.add(dorsalAtLabel);
					dorsalAtLabel.setText(
							String.valueOf(CtrlPresentacion.getInstancia().getDorsal(
									futbolistasList.getSelectedIndex()))
						);
					dorsalAtLabel.setBounds(507, 162, 58, 15);
				}
				{
					alturaAtLabel = new JLabel();
					this.add(alturaAtLabel);
					alturaAtLabel.setText(
							String.valueOf(CtrlPresentacion.getInstancia().getAltura(
									futbolistasList.getSelectedIndex()))
						);
					alturaAtLabel.setBounds(684, 133, 60, 15);
				}
				{
					posicionAtLabel = new JLabel();
					this.add(posicionAtLabel);
					if(CtrlPresentacion.getInstancia().getPosicion(futbolistasList.getSelectedIndex()) == Constantes.DEFENSA) {
						posicionAtLabel.setText("Defensa");
					}
					else if(CtrlPresentacion.getInstancia().getPosicion(futbolistasList.getSelectedIndex()) == Constantes.MEDIO) {
						posicionAtLabel.setText("Medio");
					}
					else if(CtrlPresentacion.getInstancia().getPosicion(futbolistasList.getSelectedIndex()) == Constantes.DELANTERO) {
						posicionAtLabel.setText("Delantero");
					}
					else if(CtrlPresentacion.getInstancia().getPosicion(futbolistasList.getSelectedIndex()) == Constantes.PORTERO) {
						posicionAtLabel.setText("Portero");
					}
					posicionAtLabel.setBounds(684, 162, 86, 15);
				}
				updateCostes(futbolistasList.getSelectedIndex());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void futbolistasListValueChanged(ListSelectionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		pesoAtLabel.setText(String.valueOf(c.getPeso(n)));
		remateAtLabel.setText(String.valueOf(c.getRemate(n)));
		resistenciaAtLabel.setText(String.valueOf(c.getResistencia(n)));
		velAtLabel.setText(String.valueOf(c.getVelocidad(n)));
		regateAtLabel.setText(String.valueOf(c.getRegate(n)));
		agresAtLabel.setText(String.valueOf(c.getAgresividad(n)));
		paseAtLabel.setText(String.valueOf(c.getPase(n)));
		dorsalAtLabel.setText(String.valueOf(c.getDorsal(n)));
		alturaAtLabel.setText(String.valueOf(c.getAltura(n)));
		expAtLabel.setText(String.valueOf(c.getExperiencia(n)));
		paradaAtLabel.setText(String.valueOf(c.getParada(n)));
		if(CtrlPresentacion.getInstancia().getPosicion(n) == Constantes.DEFENSA) {
			posicionAtLabel.setText("Defensa");
		}
		else if(CtrlPresentacion.getInstancia().getPosicion(n) == Constantes.MEDIO) {
			posicionAtLabel.setText("Medio");
		}
		else if(CtrlPresentacion.getInstancia().getPosicion(n) == Constantes.DELANTERO) {
			posicionAtLabel.setText("Delantero");
		}
		else if(CtrlPresentacion.getInstancia().getPosicion(n) == Constantes.PORTERO) {
			posicionAtLabel.setText("Portero");
		}
		
		updateCostes(n);
	}
	
	private void volverButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_LIGA, true);
	}
	
	private void updateCostes(int n) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		costePasLabel.setText(String.valueOf(c.calculaExperiencia(Constantes.PASE ,n)));
		costeResLabel.setText(String.valueOf(c.calculaExperiencia(Constantes.RESISTENCIA , n)));
		costeVelLabel.setText(String.valueOf(c.calculaExperiencia(Constantes.VELOCIDAD , n)));
		costeRemLabel.setText(String.valueOf(c.calculaExperiencia(Constantes.REMATE , n)));
		costeAgLabel.setText(String.valueOf(c.calculaExperiencia(Constantes.AGRESIVIDAD , n)));
		costeRegLabel.setText(String.valueOf(c.calculaExperiencia(Constantes.REGATE , n)));
		costeParadaLabel.setText(String.valueOf(c.calculaExperiencia(Constantes.PARADA, n)));
		expAtLabel.setText(String.valueOf(c.getExperiencia(n)));
	}

	private void regatePlusButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.subirAtributo(Constantes.REGATE, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(n)));
		regateAtLabel.setText(String.valueOf(c.getRegate(n)));
		updateCostes(n);
	}
	
	private void agrePlusButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.subirAtributo(Constantes.AGRESIVIDAD, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(n)));
		agresAtLabel.setText(String.valueOf(c.getAgresividad(n)));
		updateCostes(n);
	}
	
	private void pasePlusButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.subirAtributo(Constantes.PASE,n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(n)));
		paseAtLabel.setText(String.valueOf(c.getPase(n)));
		updateCostes(n);
	}
	
	private void resisPlusButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.subirAtributo(Constantes.RESISTENCIA,n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(n)));
		resistenciaAtLabel.setText(String.valueOf(c.getResistencia(n)));
		updateCostes(n);
	}
	
	private void rematePlusButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.subirAtributo(Constantes.REMATE,n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(n)));
		remateAtLabel.setText(String.valueOf(c.getRemate(n)));
		updateCostes(n);
	}
	
	private void veloPlusButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.subirAtributo(Constantes.VELOCIDAD, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(n)));
		velAtLabel.setText(String.valueOf(c.getVelocidad( n)));
		updateCostes(n);
	}
	
	private void paraddaPlusButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		int n = futbolistasList.getSelectedIndex();
		if (n < 0) n = 0;
		c.subirAtributo(Constantes.PARADA, n);
		expAtLabel.setText(String.valueOf(c.getExperiencia(n)));
		paradaAtLabel.setText(String.valueOf(c.getParada(n)));
		updateCostes(n);
	}
	
}

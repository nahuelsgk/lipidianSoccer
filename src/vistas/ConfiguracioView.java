/*
 * Alexandre Vidal Obiols
 */


package vistas;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vistas.FutbolFrame.PanelMenu;

import controladores.CtrlPresentacion;


public class ConfiguracioView extends JPanel {
	

	private static final long serialVersionUID = 7642444238025492882L;
	private ImageIcon imagen;
	private JTextField presionField;
	private JLabel presionLabel;
	private JLabel intercepcionLabel;
	private JTextField distanciaPorteriaField;
	private JTextField fueraJuegoField;
	private JLabel fueraJuegoLabel;
	private JTextField aleatorioField;
	private JLabel randomLabel;
	private JLabel error5Label;
	private JLabel explicacion2Label;
	private JLabel explicacionLabel;
	private JLabel errorGran2Label;
	private JLabel errorGranLabel;
	private JButton originalButton;
	private JLabel error8Label;
	private JLabel error6Label;
	private JLabel error4Label;
	private JLabel error2Label;
	private JLabel error7Label;
	private JLabel error3Label;
	private JLabel error1Label;
	private JLabel distanciaPorteriaLabel;
	private JTextField distanciaPaseField;
	private JLabel distanciaPaseLabel;
	private JTextField intercepcionField;
	private JTextField nivelCompaneroField;
	private JLabel nivelCompaneroLabel;
	private JTextField cansancioField;
	private JLabel cansancioLabel;
	private JComboBox posCornerCombo;
	private JLabel posCornerLabel;
	private JButton aceptarButton;
	private JButton volverButton;
	private JComboBox zonadinamicaComp;
	private JLabel zonadinamicaLabel;
	private JLabel configuracionLabel;
	private Integer explicacion;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}
	
	public ConfiguracioView() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(800, 600));
				this.setLayout(null);
				{
					configuracionLabel = new JLabel();
					this.add(configuracionLabel);
					configuracionLabel.setText("Configuracion IA");
					configuracionLabel.setBounds(28, 12, 317, 52);
					configuracionLabel.setFont(new java.awt.Font("Dialog",0,26));
				}
				{
					zonadinamicaLabel = new JLabel();
					this.add(zonadinamicaLabel);
					zonadinamicaLabel.setText("Zonas Dinamicas:");
					zonadinamicaLabel.setBounds(27, 79, 146, 41);
					zonadinamicaLabel.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {
							explicacion = 0;
							explicacionIn(e);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							explicacionOut(e);
						}

					});
				}
				{
					ComboBoxModel zonadinamicaCompModel = 
						new DefaultComboBoxModel(
								new String[] { "Activadas", "Desactivadas" });
					zonadinamicaComp = new JComboBox();
					this.add(zonadinamicaComp);
					zonadinamicaComp.setModel(zonadinamicaCompModel);
					zonadinamicaComp.setBounds(208, 88, 162, 22);
					if (CtrlPresentacion.getInstancia().getZonaDinamicaValor() == 0) zonadinamicaComp.setSelectedIndex(1);
				}
				{
					volverButton = new JButton();
					this.add(volverButton);
					volverButton.setText("Volver");
					volverButton.setBounds(256, 530, 98, 34);
					volverButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							volverButtonActionPerformed(evt);
						}
					});
				}
				{
					aceptarButton = new JButton();
					this.add(aceptarButton);
					aceptarButton.setText("Aceptar");
					aceptarButton.setBounds(655, 530, 105, 34);
					aceptarButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							aceptarButtonActionPerformed(evt);
						}
					});
				}
				{
					posCornerLabel = new JLabel();
					this.add(posCornerLabel);
					posCornerLabel.setText("Posicion corner:");
					posCornerLabel.setBounds(425, 79, 117, 47);
					posCornerLabel.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {
							explicacion = 1;
							explicacionIn(e);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							explicacionOut(e);
						}

					});
				}
				{
					ComboBoxModel posCornerComboModel = 
						new DefaultComboBoxModel(
								new String[] { "Activado", "Desactivado" });
					posCornerCombo = new JComboBox();
					this.add(posCornerCombo);
					posCornerCombo.setModel(posCornerComboModel);
					posCornerCombo.setBounds(604, 88, 156, 22);
					if (CtrlPresentacion.getInstancia().getPosicionCornerValor() == 0) posCornerCombo.setSelectedIndex(1);
				}
				{
					cansancioLabel = new JLabel();
					this.add(cansancioLabel);
					cansancioLabel.setText("Cansancio:");
					cansancioLabel.setBounds(28, 132, 139, 41);
					cansancioLabel.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {
							explicacion = 2;
							explicacionIn(e);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							explicacionOut(e);
						}

					});

				}
				{
					cansancioField = new JTextField();
					this.add(cansancioField);
					cansancioField.setBounds(208, 142, 162, 22);
					cansancioField.setText(String.valueOf(CtrlPresentacion.getInstancia().getCansancioValor()));
				}
				{
					presionLabel = new JLabel();
					this.add(presionLabel);
					presionLabel.setText("Presion:");
					presionLabel.setBounds(425, 132, 123, 41);
					presionLabel.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {
							explicacion = 3;
							explicacionIn(e);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							explicacionOut(e);
						}

					});
				}
				{
					presionField = new JTextField();
					this.add(presionField);
					presionField.setBounds(604, 142, 156, 22);
					presionField.setText(String.valueOf(CtrlPresentacion.getInstancia().getPresionValor()));
				}
				{
					nivelCompaneroLabel = new JLabel();
					this.add(nivelCompaneroLabel);
					nivelCompaneroLabel.setText("Nivel companero:");
					nivelCompaneroLabel.setBounds(28, 187, 139, 46);
					nivelCompaneroLabel.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {
							explicacion = 4;
							explicacionIn(e);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							explicacionOut(e);
						}

					});
				}
				{
					nivelCompaneroField = new JTextField();
					this.add(nivelCompaneroField);
					nivelCompaneroField.setBounds(208, 201, 162, 22);
					nivelCompaneroField.setText(String.valueOf(CtrlPresentacion.getInstancia().getNivelCompaneroValor()));
				}
				{
					intercepcionLabel = new JLabel();
					this.add(intercepcionLabel);
					intercepcionLabel.setText("Intercepcion:");
					intercepcionLabel.setBounds(425, 188, 117, 46);
					intercepcionLabel.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {
							explicacion = 5;
							explicacionIn(e);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							explicacionOut(e);
						}

					});
				}
				{
					intercepcionField = new JTextField();
					this.add(intercepcionField);
					intercepcionField.setBounds(604, 201, 156, 22);
					intercepcionField.setText(String.valueOf(CtrlPresentacion.getInstancia().getIntercepcionValor()));
				}
				{
					distanciaPaseLabel = new JLabel();
					this.add(distanciaPaseLabel);
					distanciaPaseLabel.setText("Distancia pase:");
					distanciaPaseLabel.setBounds(28, 252, 136, 49);
					distanciaPaseLabel.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {
							explicacion = 6;
							explicacionIn(e);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							explicacionOut(e);
						}

					});
				}
				{
					distanciaPaseField = new JTextField();
					this.add(distanciaPaseField);
					distanciaPaseField.setBounds(208, 266, 162, 22);
					distanciaPaseField.setText(String.valueOf(CtrlPresentacion.getInstancia().getDistanciaPaseValor()));
				}
				{
					distanciaPorteriaLabel = new JLabel();
					this.add(distanciaPorteriaLabel);
					distanciaPorteriaLabel.setText("Distancia porteria:");
					distanciaPorteriaLabel.setBounds(425, 252, 139, 49);
					distanciaPorteriaLabel.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {
							explicacion = 7;
							explicacionIn(e);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							explicacionOut(e);
						}

					});
				}
				{
					distanciaPorteriaField = new JTextField();
					this.add(distanciaPorteriaField);
					distanciaPorteriaField.setBounds(604, 266, 156, 22);
					distanciaPorteriaField.setText(String.valueOf(CtrlPresentacion.getInstancia().getDistanciaPorteriaValor()));
				}
				{
					randomLabel = new JLabel();
					this.add(randomLabel);
					randomLabel.setText("Factor aleatorio:");
					randomLabel.setBounds(28, 319, 136, 48);
					randomLabel.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {
							explicacion = 8;
							explicacionIn(e);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							explicacionOut(e);
						}

					});
				}
				{
					aleatorioField = new JTextField();
					this.add(aleatorioField);
					aleatorioField.setBounds(208, 334, 162, 22);
					aleatorioField.setText(String.valueOf(CtrlPresentacion.getInstancia().getRandomValor()));
				}
				{
					fueraJuegoLabel = new JLabel();
					this.add(fueraJuegoLabel);
					fueraJuegoLabel.setBounds(425, 319, 137, 48);
					fueraJuegoLabel.setText("Fuera de juego:");
					fueraJuegoLabel.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {
							explicacion = 9;
							explicacionIn(e);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							explicacionOut(e);
						}

					});
				}
				{
					fueraJuegoField = new JTextField();
					this.add(fueraJuegoField);
					fueraJuegoField.setBounds(604, 334, 156, 22);
					fueraJuegoField.setText(String.valueOf(CtrlPresentacion.getInstancia().getFueraJuegoValor()));
				}
				{
					error1Label = new JLabel();
					this.add(error1Label);
					error1Label.setBounds(173, 145, 48, 15);
				}
				{
					error3Label = new JLabel();
					this.add(error3Label);
					error3Label.setBounds(173, 204, 55, 15);
				}
				{
					error5Label = new JLabel();
					this.add(error5Label);
					error5Label.setBounds(171, 269, 58, 15);
				}
				{
					error7Label = new JLabel();
					this.add(error7Label);
					error7Label.setBounds(172, 337, 55, 15);
				}
				{
					error2Label = new JLabel();
					this.add(error2Label);
					error2Label.setBounds(568, 145, 56, 15);
				}
				{
					error4Label = new JLabel();
					this.add(error4Label);
					error4Label.setBounds(567, 204, 55, 15);
				}
				{
					error6Label = new JLabel();
					this.add(error6Label);
					error6Label.setBounds(567, 269, 57, 15);
				}
				{
					error8Label = new JLabel();
					this.add(error8Label);
					error8Label.setBounds(568, 335, 61, 19);
				}
				{
					originalButton = new JButton();
					this.add(originalButton);
					originalButton.setText("Proponer valores originales");
					originalButton.setBounds(386, 530, 238, 34);
					originalButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							originalButtonActionPerformed(evt);
						}
					});
				}
				{
					errorGranLabel = new JLabel();
					this.add(errorGranLabel);
					errorGranLabel.setBounds(28, 453, 732, 25);
				}
				{
					errorGran2Label = new JLabel();
					this.add(errorGran2Label);
					errorGran2Label.setBounds(28, 484, 732, 21);
				}
				{
					explicacionLabel = new JLabel();
					this.add(explicacionLabel);
					explicacionLabel.setBounds(28, 394, 732, 19);
				}
				{
					explicacion2Label = new JLabel();
					this.add(explicacion2Label);
					explicacion2Label.setBounds(28, 419, 732, 24);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean esDouble(String s) {
		try {
			Double.parseDouble(s);
		}
		catch (NumberFormatException nfe) {
				return false;
		}
		return true;
	}
	
	private void aceptarButtonActionPerformed(ActionEvent evt) {
		
		error1Label.setText("");
		error2Label.setText("");
		error3Label.setText("");
		error4Label.setText("");
		error5Label.setText("");
		error6Label.setText("");
		error7Label.setText("");
		error8Label.setText("");
		errorGranLabel.setText("");
		errorGran2Label.setText("");
		
		boolean fes = true;
		String s = cansancioField.getText();
		if (!esDouble(s) || Double.parseDouble(s) <= 0) {
			error1Label.setText("Error");
			fes = false;
		}
		
		s = presionField.getText();
		if (!esDouble(s) || Double.parseDouble(s) <= 0) {
			error2Label.setText("Error");
			fes = false;
		}
		
		s = nivelCompaneroField.getText();
		if (!esDouble(s) || Double.parseDouble(s) <= 0) {
			error3Label.setText("Error");
			fes = false;
		}
		
		s = intercepcionField.getText();
		if (!esDouble(s) || Double.parseDouble(s) <= 0) {
			error4Label.setText("Error");
			fes = false;
		}
		
		s = distanciaPaseField.getText();
		if (!esDouble(s) || Double.parseDouble(s) <= 0) {
			error5Label.setText("Error");
			fes = false;
		}
		
		s = distanciaPorteriaField.getText();
		if (!esDouble(s) || Double.parseDouble(s) <= 0) {
			error6Label.setText("Error");
			fes = false;
		}

		s = aleatorioField.getText();
		if (!esDouble(s) || Double.parseDouble(s) <= 0) {
			error7Label.setText("Error");
			fes = false;
		}

		s = fueraJuegoField.getText();
		if (!esDouble(s) || Double.parseDouble(s) <= 0) {
			error8Label.setText("Error");
			fes = false;
		}
		
		if (fes) {
			s = cansancioField.getText();
			CtrlPresentacion.getInstancia().setCansancioValor(Double.parseDouble(s));
			
			s = presionField.getText();
			CtrlPresentacion.getInstancia().setPresionValor(Double.parseDouble(s));
			
			s = nivelCompaneroField.getText();
			CtrlPresentacion.getInstancia().setNivelCompaneroValor(Double.parseDouble(s));
			
			s = intercepcionField.getText();
			CtrlPresentacion.getInstancia().setIntercepcionValor(Double.parseDouble(s));
			
			s = distanciaPaseField.getText();
			CtrlPresentacion.getInstancia().setDistanciaPaseValor(Double.parseDouble(s));
			
			s = distanciaPorteriaField.getText();
			CtrlPresentacion.getInstancia().setDistanciaPorteriaValor(Double.parseDouble(s));

			s = aleatorioField.getText();
			CtrlPresentacion.getInstancia().setRandomValor(Double.parseDouble(s));

			s = fueraJuegoField.getText();
			CtrlPresentacion.getInstancia().setFueraValor(Double.parseDouble(s));

			if (zonadinamicaComp.getSelectedIndex() == 0) {
				CtrlPresentacion.getInstancia().setZonaDinamicaValor(1);
			}else {
				CtrlPresentacion.getInstancia().setZonaDinamicaValor(0);
			}
			
			if (posCornerCombo.getSelectedIndex() == 0) {
				CtrlPresentacion.getInstancia().setPosicionCornerValor(1);
			}else {
				CtrlPresentacion.getInstancia().setPosicionCornerValor(0);
			}
			
			CtrlPresentacion.getInstancia().iAConfinit();
			FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_PRINCIPAL,true);
		}else {
			errorGranLabel.setText("Alguno de los valores no es valido. Asegurate que todos son reales estrictamente positivos.");
			errorGran2Label.setText("Los cambios se haran cuando todos los valores sean correctos.");
		}
	}	
	
	private void volverButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_PRINCIPAL,true);
	}
	
	private void originalButtonActionPerformed(ActionEvent evt) {
		error1Label.setText("");
		error2Label.setText("");
		error3Label.setText("");
		error4Label.setText("");
		error5Label.setText("");
		error6Label.setText("");
		error7Label.setText("");
		error8Label.setText("");
		errorGranLabel.setText("");
		errorGran2Label.setText("");
		
		cansancioField.setText("1.0");
		
		presionField.setText("1.0");
		
		nivelCompaneroField.setText("1.0");
		
		intercepcionField.setText("2.0");
		
		distanciaPaseField.setText("1.0");
		
		distanciaPorteriaField.setText("3.0");

		aleatorioField.setText("1.0");

		fueraJuegoField.setText("1.0");

		zonadinamicaComp.setSelectedIndex(0);
		
		posCornerCombo.setSelectedIndex(0);
		
	}

	public void explicacionIn(MouseEvent e) {
		switch(explicacion) {
			case 0:
				explicacionLabel.setText("Zonas Dinamicas: Activar/desactivar el uso de zonas de juego de los futbolistas en funcion de ");
				explicacion2Label.setText("la posicion del balon.");
				break;
			case 1:
				explicacionLabel.setText("Posicion corner: Activar/desactivar formacion de los futbolistas en caso de saque de esquina.");
				break;
			case 2:
				explicacionLabel.setText("Cansancio: Peso del cansancio del futbolista a la hora de tomar decisiones relacionadas.");
				explicacion2Label.setText("Con 0 ignoran el cansancio, con mas de 1 tienen especialmente en cuenta el cansancio.");
				break;
			case 3:
				explicacionLabel.setText("Presion: Peso de la presion de un futbolista a la hora de tomar decisiones.");
				explicacion2Label.setText("La presion de un futbolista se mide en funcion de parametros relacionados con la cercania de rivales.");
				break;
			case 4:
				explicacionLabel.setText("Nivel companero: Peso de la calidad de un futbolista companero a la hora de elegir un objetivo de pase.");
				break;
			case 5:
				explicacionLabel.setText("Intercepcion: La intercepcion es la cantidad de rivales en opcion de interceptar un pase realizado");
				explicacion2Label.setText(" a un futbolista. Esta opcion decide el peso de este parametro a la hora de tomar decisiones.");
				break;
			case 6:
				explicacionLabel.setText("Distancia pase: Cuando se busca un companero adecuado para el pase, la distancia es un factor negativo.");
				explicacion2Label.setText("Este parametro define el peso de este factor.");
				break;
			case 7:
				explicacionLabel.setText("Distancia porteria: Al realizar un pase, tienen preferencia aquellos mas cercanos a la porteria rival.");
				explicacion2Label.setText("Este parametro modifica el peso de este factor.");
				break;
			case 8:
				explicacionLabel.setText("Factor aleatorio: Peso de variables aleatorias en determinadas situaciones.");
				break;
			case 9:
				explicacionLabel.setText("Fuera de juego: Los futbolistas procuran no pasar el balon a un companero en fuera de juego. Un valor alto en este ");
				explicacion2Label.setText("parametro evita que se produzcan. Asi mismo, un 0 ignora en las decisiones la posicion de fuera de juego.");
				break;
			default:
		}

	}
	
	public void explicacionOut(MouseEvent e) {
		explicacionLabel.setText("");
		explicacion2Label.setText("");
	}
}

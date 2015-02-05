/*
 * Jaume Vinyes Navas
 */

package vistas;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class EntrenarView extends JPanel{

	private static final long serialVersionUID = 5094863092493845019L;
	private JLabel tituloLabel;
	private JScrollPane jScrollPane1;
	private JLabel horasRestatnesAtLabel;
	private JTextField horasAsignadasTextField;
	private JRadioButton agresividadRadioButton;
	private JRadioButton paseRadioButton;
	private JRadioButton velocidadRadioButton;
	private JRadioButton regateRadioButton;
	private JLabel oroLabel;
	private JLabel valorActLabel;
	private JLabel oroDispLabel;
	private JLabel oroAtLabel;
	private JLabel oroDisLabel;
	private JLabel atrActualLabel;
	private JRadioButton remateRadioButton;
	private JRadioButton resistenciaRadioButton;
	private JRadioButton paradaRadioButton;
	private JSeparator jSeparator3;
	private JButton volverButton;
	private JButton acceptarButton;
	private JLabel horasAsignadasLabel;
	private JLabel horasRestantesLabel;
	private JSeparator jSeparator2;
	private JLabel titulo3Label;
	private JLabel titulo2Label;
	private JLabel atSujLabel;
	private JLabel incrLabel;
	private JSeparator jSeparator1;
	private JList futbolistasList;
	private ButtonGroup group;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}

	public EntrenarView() {
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
					tituloLabel.setText("Entrenar");
					tituloLabel.setBounds(50, 25, 304, 83);
					tituloLabel.setFont(new java.awt.Font("Verdana",3,36));
				}
				{
					jScrollPane1 = new JScrollPane();
					this.add(jScrollPane1);
					jScrollPane1.setBounds(50, 114, 340, 445);
					{
						ListModel futbolistasListModel = 
							new DefaultComboBoxModel(
									CtrlPresentacion.getInstancia().getNombresFutbolistas());
						futbolistasList = new JList();
						jScrollPane1.setViewportView(futbolistasList);
						futbolistasList.setModel(futbolistasListModel);
						futbolistasList.setFixedCellHeight(25);
						futbolistasList.setBounds(50, 114, 340, 445);
						futbolistasList.setSelectedIndex(0);
						futbolistasList.addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent evt) {
								futbolistasListValueChanged(evt);
							}
						});
					}
				}
				{
					titulo2Label = new JLabel();
					this.add(titulo2Label);
					titulo2Label.setText("Selecciona el Atributo:");
					titulo2Label.setBounds(444, 145, 166, 15);
					titulo2Label.setFont(new java.awt.Font("Verdana",0,14));
				}
				{
					jSeparator1 = new JSeparator();
					this.add(jSeparator1);
					jSeparator1.setBounds(444, 174, 309, 10);
				}
				{
					titulo3Label = new JLabel();
					this.add(titulo3Label);
					titulo3Label.setText("Asignacion:");
					titulo3Label.setBounds(444, 323, 153, 18);
					titulo3Label.setFont(new java.awt.Font("Verdana",0,14));
				}
				{
					jSeparator2 = new JSeparator();
					this.add(jSeparator2);
					jSeparator2.setBounds(444, 347, 306, 8);
				}
				{
					horasRestantesLabel = new JLabel();
					this.add(horasRestantesLabel);
					horasRestantesLabel.setText("Horas restantes:");
					horasRestantesLabel.setBounds(456, 367, 133, 15);
				}
				{
					horasAsignadasLabel = new JLabel();
					this.add(horasAsignadasLabel);
					horasAsignadasLabel.setText("Horas asignadas:");
					horasAsignadasLabel.setBounds(456, 391, 133, 15);
				}
				{
					acceptarButton = new JButton();
					this.add(acceptarButton);
					acceptarButton.setText("Aceptar");
					acceptarButton.setBounds(468, 541, 130, 25);
					acceptarButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							acceptarButtonActionPerformed(evt);
						}
					});
				}
				{
					volverButton = new JButton();
					this.add(volverButton);
					volverButton.setText("Volver");
					volverButton.setBounds(660, 541, 100, 25);
					volverButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							volverButtonActionPerformed(evt);
						}
					});
				}
				{
					jSeparator3 = new JSeparator();
					this.add(jSeparator3);
					jSeparator3.setBounds(451, 519, 309, 10);
				}
				{
					paradaRadioButton = new JRadioButton();
					this.add(paradaRadioButton);
					paradaRadioButton.setText("Parada");
					paradaRadioButton.setBounds(456, 187, 110, 19);
					paradaRadioButton.setSelected(true);
				}
				{
					resistenciaRadioButton = new JRadioButton();
					this.add(resistenciaRadioButton);
					resistenciaRadioButton.setText("Resistencia");
					resistenciaRadioButton.setBounds(456, 219, 110, 19);
				}
				{
					remateRadioButton = new JRadioButton();
					this.add(remateRadioButton);
					remateRadioButton.setText("Remate");
					remateRadioButton.setBounds(456, 252, 110, 19);
				}
				{
					regateRadioButton = new JRadioButton();
					this.add(regateRadioButton);
					regateRadioButton.setText("Regate");
					regateRadioButton.setBounds(456, 286, 110, 19);
				}
				{
					velocidadRadioButton = new JRadioButton();
					this.add(velocidadRadioButton);
					velocidadRadioButton.setText("Velocidad");
					velocidadRadioButton.setBounds(597, 187, 116, 19);
				}
				{
					paseRadioButton = new JRadioButton();
					this.add(paseRadioButton);
					paseRadioButton.setText("Pase");
					paseRadioButton.setBounds(597, 219, 116, 19);
				}
				{
					agresividadRadioButton = new JRadioButton();
					this.add(agresividadRadioButton);
					agresividadRadioButton.setText("Agresividad");
					agresividadRadioButton.setBounds(597, 252, 130, 19);
				}
				{
					horasAsignadasTextField = new JTextField();
					this.add(horasAsignadasTextField);
					horasAsignadasTextField.setBounds(633, 388, 87, 22);
					horasAsignadasTextField.setText("10");
					horasAsignadasTextField.addKeyListener(new KeyAdapter() {
						public void keyReleased(KeyEvent evt) {
							horasAsignadasTextFieldKeyReleased(evt);
						}
					});
				}
				{
					horasRestatnesAtLabel = new JLabel();
					this.add(horasRestatnesAtLabel);
					horasRestatnesAtLabel.setBounds(653, 364, 43, 18);
					horasRestatnesAtLabel.setText(CtrlPresentacion.getInstancia().getHorasRestantes());
				}
				{
					incrLabel = new JLabel();
					this.add(incrLabel);
					incrLabel.setText("Atr. Subidos:");
					incrLabel.setBounds(456, 416, 113, 14);
				}
				{
					atSujLabel = new JLabel();
					this.add(atSujLabel);
					atSujLabel.setBounds(653, 416, 51, 14);
				}
				{
					atrActualLabel = new JLabel();
					this.add(atrActualLabel);
					atrActualLabel.setText("Valor actual:");
					atrActualLabel.setBounds(456, 442, 141, 15);
				}
				{
					oroLabel = new JLabel();
					this.add(oroLabel);
					oroLabel.setText("Oro:");
					oroLabel.setBounds(456, 465, 75, 15);
				}
				{
					oroDisLabel = new JLabel();
					this.add(oroDisLabel);
					oroDisLabel.setText("Oro disponible:");
					oroDisLabel.setBounds(456, 492, 129, 15);
				}
				{
					oroAtLabel = new JLabel();
					this.add(oroAtLabel);
					oroAtLabel.setBounds(634, 467, 83, 15);
				}
				{
					oroDispLabel = new JLabel();
					this.add(oroDispLabel);
					oroDispLabel.setText(CtrlPresentacion.getInstancia().getOro());
					oroDispLabel.setBounds(633, 488, 84, 15);
				}
				{
					valorActLabel = new JLabel();
					this.add(valorActLabel);
					valorActLabel.setBounds(633, 442, 84, 15);
				}
				{
					group = new ButtonGroup();
				    group.add(paradaRadioButton);
				    paradaRadioButton.addActionListener(new ActionListener() {
				    	public void actionPerformed(ActionEvent evt) {
				    		paradaRadioButtonActionPerformed(evt);
				    	}
				    });
				    group.add(remateRadioButton);
				    remateRadioButton.addActionListener(new ActionListener() {
				    	public void actionPerformed(ActionEvent evt) {
				    		remateRadioButtonActionPerformed(evt);
				    	}
				    });
				    group.add(regateRadioButton);
				    regateRadioButton.addActionListener(new ActionListener() {
				    	public void actionPerformed(ActionEvent evt) {
				    		regateRadioButtonActionPerformed(evt);
				    	}
				    });
				    group.add(velocidadRadioButton);
				    velocidadRadioButton.addActionListener(new ActionListener() {
				    	public void actionPerformed(ActionEvent evt) {
				    		velocidadRadioButtonActionPerformed(evt);
				    	}
				    });
				    group.add(agresividadRadioButton);
				    agresividadRadioButton.addActionListener(new ActionListener() {
				    	public void actionPerformed(ActionEvent evt) {
				    		agresividadRadioButtonActionPerformed(evt);
				    	}
				    });
				    group.add(paseRadioButton);
				    paseRadioButton.addActionListener(new ActionListener() {
				    	public void actionPerformed(ActionEvent evt) {
				    		paseRadioButtonActionPerformed(evt);
				    	}
				    });
				    group.add(resistenciaRadioButton);
				    resistenciaRadioButton.addActionListener(new ActionListener() {
				    	public void actionPerformed(ActionEvent evt) {
				    		resistenciaRadioButtonActionPerformed(evt);
				    	}
				    });
				}
				int n = CtrlPresentacion.getInstancia().cantidadPorExperiencia(
						Constantes.PARADA,Integer.parseInt(horasAsignadasTextField.getText())*5,
						futbolistasList.getSelectedIndex());
				atSujLabel.setText(String.valueOf(n));
				oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
				valorActLabel.setText(String.valueOf(CtrlPresentacion.getInstancia().getParada(futbolistasList.getSelectedIndex())));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void acceptarButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		int n = futbolistasList.getSelectedIndex();
		int atr = 0; int exp = Integer.parseInt(horasAsignadasTextField.getText())*5;
		c.setExperienciaFutbolista(exp,n);
		if(paradaRadioButton.isSelected()) atr = Constantes.PARADA;
		else if(remateRadioButton.isSelected()) atr = Constantes.REMATE;
		else if(regateRadioButton.isSelected()) atr = Constantes.REGATE;
		else if(velocidadRadioButton.isSelected()) atr = Constantes.VELOCIDAD;
		else if(agresividadRadioButton.isSelected()) atr = Constantes.AGRESIVIDAD;
		else if(paseRadioButton.isSelected()) atr = Constantes.PASE;
		else if(resistenciaRadioButton.isSelected()) atr = Constantes.RESISTENCIA;
		int max = c.cantidadPorExperiencia(atr,exp,futbolistasList.getSelectedIndex());
		
		if((Integer.parseInt(c.getOro())-Integer.parseInt(oroAtLabel.getText())) < 0) {
			String[] options = {
					"Accpetar"
					};
			int m = JOptionPane.showOptionDialog(FutbolFrame.getInstancia(),
					"No puedes realizar esta operacion.\n"
					+ "Debes tener oro y horas suficientes.",
					"Aviso!",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE,
					null,
					options,
					options[0]);
			
			if(m == 0) {
				FutbolFrame.getInstancia().ponerMenu(PanelMenu.ENTRENAR_VIEW,false);
			}
		}
		else if(Integer.parseInt(horasAsignadasTextField.getText()) > Integer.parseInt(horasRestatnesAtLabel.getText())) {
			String[] options = {
					"Accpetar"
					};
			int m = JOptionPane.showOptionDialog(FutbolFrame.getInstancia(),
					"No puedes realizar esta operacion.\n"
					+ "Has asignadao mas horas de las que tienes disponibles.",
					"Aviso!",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE,
					null,
					options,
					options[0]);
			
			if(m == 0) {
				FutbolFrame.getInstancia().ponerMenu(PanelMenu.ENTRENAR_VIEW,false);
			}
		}
		else if(Integer.parseInt(atSujLabel.getText()) <= 0) {
			String[] options = {
					"Accpetar"
					};
			int m = JOptionPane.showOptionDialog(FutbolFrame.getInstancia(),
					"No puedes realizar esta operacion.\n"
					+ "No subes ningun atributo.",
					"Aviso!",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE,
					null,
					options,
					options[0]);
			
			if(m == 0) {
				FutbolFrame.getInstancia().ponerMenu(PanelMenu.ENTRENAR_VIEW,false);
			}
		}
		else {
			for(int i = 0; i < max; ++i) {
				c.subirAtributo(atr, n);
			}
			c.setOro(Integer.parseInt(c.getOro())-Integer.parseInt(oroAtLabel.getText()));
			oroDispLabel.setText(c.getOro());
			if(paradaRadioButton.isSelected()) {
				valorActLabel.setText(String.valueOf(c.getParada(futbolistasList.getSelectedIndex())));
			}
			else if(remateRadioButton.isSelected()) {
				valorActLabel.setText(String.valueOf(c.getRemate(futbolistasList.getSelectedIndex())));
			}
			else if(regateRadioButton.isSelected()) {
				valorActLabel.setText(String.valueOf(c.getRegate(futbolistasList.getSelectedIndex())));
			}
			else if(velocidadRadioButton.isSelected()) {
				valorActLabel.setText(String.valueOf(c.getVelocidad(futbolistasList.getSelectedIndex())));
			}
			else if(agresividadRadioButton.isSelected()) {
				valorActLabel.setText(String.valueOf(c.getAgresividad(futbolistasList.getSelectedIndex())));
			}
			else if(paseRadioButton.isSelected()) {
				valorActLabel.setText(String.valueOf(c.getPase(futbolistasList.getSelectedIndex())));
			}
			else if(resistenciaRadioButton.isSelected()) {
				valorActLabel.setText(String.valueOf(c.getResistencia(futbolistasList.getSelectedIndex())));
			}
			c.setHorasRestantes(Integer.parseInt(c.getHorasRestantes())-Integer.parseInt(horasAsignadasTextField.getText()));
			horasRestatnesAtLabel.setText(c.getHorasRestantes());
		}
		
		
	}
	
	private boolean dadesValides() {
		int pes = 0;
		try{
			pes = Integer.parseInt(horasAsignadasTextField.getText());
		}
		catch(Exception e){
			return false;
		}
		if(pes > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private void volverButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_LIGA, true);
	}
	
	
	private void paradaRadioButtonActionPerformed(ActionEvent evt) {		
		int n = CtrlPresentacion.getInstancia().cantidadPorExperiencia(
				Constantes.PARADA,Integer.parseInt(horasAsignadasTextField.getText())*5,
				futbolistasList.getSelectedIndex());
		atSujLabel.setText(String.valueOf(n));
		oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
		valorActLabel.setText(String.valueOf(CtrlPresentacion.getInstancia().getParada(futbolistasList.getSelectedIndex())));
	}
	
	private void resistenciaRadioButtonActionPerformed(ActionEvent evt) {
		int n = CtrlPresentacion.getInstancia().cantidadPorExperiencia(
				Constantes.RESISTENCIA,Integer.parseInt(horasAsignadasTextField.getText())*5,
				futbolistasList.getSelectedIndex());
		atSujLabel.setText(String.valueOf(n));
		oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
		valorActLabel.setText(String.valueOf(CtrlPresentacion.getInstancia().getResistencia(futbolistasList.getSelectedIndex())));
	}
	
	private void remateRadioButtonActionPerformed(ActionEvent evt) {
		int n = CtrlPresentacion.getInstancia().cantidadPorExperiencia(
				Constantes.REMATE,Integer.parseInt(horasAsignadasTextField.getText())*5,
				futbolistasList.getSelectedIndex());
		atSujLabel.setText(String.valueOf(n));
		oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
		valorActLabel.setText(String.valueOf(CtrlPresentacion.getInstancia().getRemate(futbolistasList.getSelectedIndex())));
	}
	
	private void regateRadioButtonActionPerformed(ActionEvent evt) {
		int n = CtrlPresentacion.getInstancia().cantidadPorExperiencia(
				Constantes.REGATE,Integer.parseInt(horasAsignadasTextField.getText())*5,
				futbolistasList.getSelectedIndex());
		atSujLabel.setText(String.valueOf(n));
		oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
		valorActLabel.setText(String.valueOf(CtrlPresentacion.getInstancia().getRegate(futbolistasList.getSelectedIndex())));
	}
	
	private void velocidadRadioButtonActionPerformed(ActionEvent evt) {
		int n = CtrlPresentacion.getInstancia().cantidadPorExperiencia(
				Constantes.VELOCIDAD,Integer.parseInt(horasAsignadasTextField.getText())*5,
				futbolistasList.getSelectedIndex());
		atSujLabel.setText(String.valueOf(n));
		oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
		valorActLabel.setText(String.valueOf(CtrlPresentacion.getInstancia().getVelocidad(futbolistasList.getSelectedIndex())));
	}
	
	private void paseRadioButtonActionPerformed(ActionEvent evt) {
		int n = CtrlPresentacion.getInstancia().cantidadPorExperiencia(
				Constantes.PASE,Integer.parseInt(horasAsignadasTextField.getText())*5,
				futbolistasList.getSelectedIndex());
		atSujLabel.setText(String.valueOf(n));
		oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
		valorActLabel.setText(String.valueOf(CtrlPresentacion.getInstancia().getPase(futbolistasList.getSelectedIndex())));
	}
	
	private void agresividadRadioButtonActionPerformed(ActionEvent evt) {
		int n = CtrlPresentacion.getInstancia().cantidadPorExperiencia(
				Constantes.AGRESIVIDAD,Integer.parseInt(horasAsignadasTextField.getText())*5,
				futbolistasList.getSelectedIndex());
		atSujLabel.setText(String.valueOf(n));
		oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
		valorActLabel.setText(String.valueOf(CtrlPresentacion.getInstancia().getAgresividad(futbolistasList.getSelectedIndex())));
	}
	
	private void horasAsignadasTextFieldKeyReleased(KeyEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		if(horasAsignadasTextField.getText().length() > 0 && dadesValides()) {
			if(paradaRadioButton.isSelected()) {
				int n = c.cantidadPorExperiencia(
						Constantes.PARADA,Integer.parseInt(horasAsignadasTextField.getText())*5,
						futbolistasList.getSelectedIndex());
				atSujLabel.setText(String.valueOf(n));
				oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
				valorActLabel.setText(String.valueOf(c.getParada(futbolistasList.getSelectedIndex())));
			}
			else if(remateRadioButton.isSelected()) {
				int n = c.cantidadPorExperiencia(
						Constantes.REMATE,Integer.parseInt(horasAsignadasTextField.getText())*5,
						futbolistasList.getSelectedIndex());
				atSujLabel.setText(String.valueOf(n));
				oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
				valorActLabel.setText(String.valueOf(c.getRemate(futbolistasList.getSelectedIndex())));
			}
			else if(regateRadioButton.isSelected()) {
				int n = c.cantidadPorExperiencia(
						Constantes.REGATE,Integer.parseInt(horasAsignadasTextField.getText())*5,
						futbolistasList.getSelectedIndex());
				atSujLabel.setText(String.valueOf(n));
				oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
				valorActLabel.setText(String.valueOf(c.getRegate(futbolistasList.getSelectedIndex())));
			}
			else if(velocidadRadioButton.isSelected()) {
				int n = c.cantidadPorExperiencia(
						Constantes.VELOCIDAD,Integer.parseInt(horasAsignadasTextField.getText())*5,
						futbolistasList.getSelectedIndex());
				atSujLabel.setText(String.valueOf(n));
				oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
				valorActLabel.setText(String.valueOf(c.getVelocidad(futbolistasList.getSelectedIndex())));
			}
			else if(agresividadRadioButton.isSelected()) {
				int n = c.cantidadPorExperiencia(
						Constantes.AGRESIVIDAD,Integer.parseInt(horasAsignadasTextField.getText())*5,
						futbolistasList.getSelectedIndex());
				atSujLabel.setText(String.valueOf(n));
				oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
				valorActLabel.setText(String.valueOf(c.getAgresividad(futbolistasList.getSelectedIndex())));
			}
			else if(paseRadioButton.isSelected()) {
				int n = c.cantidadPorExperiencia(
						Constantes.PASE,Integer.parseInt(horasAsignadasTextField.getText())*5,
						futbolistasList.getSelectedIndex());
				atSujLabel.setText(String.valueOf(n));
				oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
				valorActLabel.setText(String.valueOf(c.getPase(futbolistasList.getSelectedIndex())));
			}
			else if(resistenciaRadioButton.isSelected()) {
				int n = c.cantidadPorExperiencia(
						Constantes.RESISTENCIA,Integer.parseInt(horasAsignadasTextField.getText())*5,
						futbolistasList.getSelectedIndex());
				atSujLabel.setText(String.valueOf(n));
				oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
				valorActLabel.setText(String.valueOf(c.getResistencia(futbolistasList.getSelectedIndex())));
			}
		}
		else {
			oroAtLabel.setText("0");
			horasAsignadasTextField.setText("10");
			String[] options = {
					"Accpetar"
					};
			int m = JOptionPane.showOptionDialog(FutbolFrame.getInstancia(),
					"No puedes realizar esta operacion.\n"
					+ "Debes asignar un numero entero.",
					"Aviso!",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE,
					null,
					options,
					options[0]);
			
			if(m == 0) {
				FutbolFrame.getInstancia().ponerMenu(PanelMenu.ENTRENAR_VIEW,false);
			}
		}
	}
	private void futbolistasListValueChanged(ListSelectionEvent evt) {
		CtrlPresentacion c = CtrlPresentacion.getInstancia();
		if(paradaRadioButton.isSelected()) {
			int n = c.cantidadPorExperiencia(
					Constantes.PARADA,Integer.parseInt(horasAsignadasTextField.getText())*5,
					futbolistasList.getSelectedIndex());
			atSujLabel.setText(String.valueOf(n));
			oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
			valorActLabel.setText(String.valueOf(c.getParada(futbolistasList.getSelectedIndex())));
		}
		else if(remateRadioButton.isSelected()) {
			int n = c.cantidadPorExperiencia(
					Constantes.REMATE,Integer.parseInt(horasAsignadasTextField.getText())*5,
					futbolistasList.getSelectedIndex());
			atSujLabel.setText(String.valueOf(n));
			oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
			valorActLabel.setText(String.valueOf(c.getRemate(futbolistasList.getSelectedIndex())));
		}
		else if(regateRadioButton.isSelected()) {
			int n = c.cantidadPorExperiencia(
					Constantes.REGATE,Integer.parseInt(horasAsignadasTextField.getText())*5,
					futbolistasList.getSelectedIndex());
			atSujLabel.setText(String.valueOf(n));
			oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
			valorActLabel.setText(String.valueOf(c.getRegate(futbolistasList.getSelectedIndex())));
		}
		else if(velocidadRadioButton.isSelected()) {
			int n = c.cantidadPorExperiencia(
					Constantes.VELOCIDAD,Integer.parseInt(horasAsignadasTextField.getText())*5,
					futbolistasList.getSelectedIndex());
			atSujLabel.setText(String.valueOf(n));
			oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
			valorActLabel.setText(String.valueOf(c.getVelocidad(futbolistasList.getSelectedIndex())));
		}
		else if(agresividadRadioButton.isSelected()) {
			int n = c.cantidadPorExperiencia(
					Constantes.AGRESIVIDAD,Integer.parseInt(horasAsignadasTextField.getText())*5,
					futbolistasList.getSelectedIndex());
			atSujLabel.setText(String.valueOf(n));
			oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
			valorActLabel.setText(String.valueOf(c.getAgresividad(futbolistasList.getSelectedIndex())));
		}
		else if(paseRadioButton.isSelected()) {
			int n = c.cantidadPorExperiencia(
					Constantes.PASE,Integer.parseInt(horasAsignadasTextField.getText())*5,
					futbolistasList.getSelectedIndex());
			atSujLabel.setText(String.valueOf(n));
			oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
			valorActLabel.setText(String.valueOf(c.getPase(futbolistasList.getSelectedIndex())));
		}
		else if(resistenciaRadioButton.isSelected()) {
			int n = c.cantidadPorExperiencia(
					Constantes.RESISTENCIA,Integer.parseInt(horasAsignadasTextField.getText())*5,
					futbolistasList.getSelectedIndex());
			atSujLabel.setText(String.valueOf(n));
			oroAtLabel.setText(String.valueOf(Integer.parseInt(horasAsignadasTextField.getText())*20));
			valorActLabel.setText(String.valueOf(c.getResistencia(futbolistasList.getSelectedIndex())));
		}
	}

}

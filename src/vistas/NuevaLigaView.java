/*
 * Jaume Vinyes Navas
 */


package vistas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import javax.swing.JTextField;

import vistas.FutbolFrame.PanelMenu;
import controladores.CtrlPresentacion;
import domain.Constantes;
 

public class NuevaLigaView extends JPanel {
	private static final long serialVersionUID = -7789678013874755676L;
	private JLabel nuevaLigaLabel;
	private JComboBox jComboBox2;
	private JLabel jLabel1;
	private JButton crearLigaButton;
	private JButton atrasButton;
	private JRadioButton dificilRadioButton;
	private JRadioButton mediaRadioButton;
	private JRadioButton facilRadioButton;
	private JComboBox numeroEquiposComboBox;
	private JTextField nombreLigaTextField;
	private JLabel dificultadLabel;
	private JLabel equiposLabel;
	private JLabel nombreLabel;
	private ButtonGroup group;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}
	public NuevaLigaView() {
		super();
		initGUI();
	}

	
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(800,600));
				this.setLayout(null);
				{
					nuevaLigaLabel = new JLabel();
					this.add(nuevaLigaLabel);
					nuevaLigaLabel.setText("Nueva Liga");
					nuevaLigaLabel.setBounds(85, 42, 288, 75);
					nuevaLigaLabel.setFont(new java.awt.Font("Verdana",3,36));
				}
				{
					nombreLabel = new JLabel();
					this.add(nombreLabel);
					nombreLabel.setText("Seleccione el nombre de la liga:");
					nombreLabel.setBounds(131, 162, 209, 14);
				}
				{
					equiposLabel = new JLabel();
					this.add(equiposLabel);
					equiposLabel.setText("Numero equipos sistema:");
					equiposLabel.setBounds(131, 202, 209, 18);
				}
				{
					dificultadLabel = new JLabel();
					this.add(dificultadLabel);
					dificultadLabel.setText("Seleccione la dificultad:");
					dificultadLabel.setBounds(131, 300, 192, 14);
				}
				{
					nombreLigaTextField = new JTextField();
					this.add(nombreLigaTextField);
					nombreLigaTextField.setBounds(393, 155, 212, 21);
				}
				{
					ComboBoxModel numeroEquiposComboBoxModel = 
						new DefaultComboBoxModel(
								new String[] { "2","3", "4","5", "6","7", "8" });
					numeroEquiposComboBox = new JComboBox();
					this.add(numeroEquiposComboBox);
					numeroEquiposComboBox.setModel(numeroEquiposComboBoxModel);
					numeroEquiposComboBox.setBounds(393, 199, 212, 21);
				}
				{
					facilRadioButton = new JRadioButton();
					this.add(facilRadioButton);
					facilRadioButton.setText("Facil");
					facilRadioButton.setBounds(399, 296, 61, 18);
				}
				{
					mediaRadioButton = new JRadioButton();
					this.add(mediaRadioButton);
					mediaRadioButton.setText("Normal");
					mediaRadioButton.setBounds(465, 296, 81, 18);
				}
				{
					dificilRadioButton = new JRadioButton();
					this.add(dificilRadioButton);
					dificilRadioButton.setText("Dificil");
					dificilRadioButton.setBounds(547, 296, 102, 18);
				}
				{
					atrasButton = new JButton();
					this.add(atrasButton);
					atrasButton.setText("Volver");
					atrasButton.setBounds(131, 340, 200, 40);
					atrasButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							atrasButtonActionPerformed(evt);
						}
					});
				}
				{
					crearLigaButton = new JButton();
					this.add(crearLigaButton);
					crearLigaButton.setText("Crear liga");
					crearLigaButton.setBounds(393, 340, 200, 40);
					crearLigaButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							crearLigaButtonActionPerformed(evt);
						}
					});
				}
				{
					jLabel1 = new JLabel();
					this.add(jLabel1);
					jLabel1.setText("Numero equipos usuario:");
					jLabel1.setBounds(131, 255, 192, 14);
				}
				{
					ComboBoxModel jComboBox2Model = 
						new DefaultComboBoxModel(
								new String[] { "1", "2","3", "4","5", "6","7", "8" });
					jComboBox2 = new JComboBox();
					this.add(jComboBox2);
					jComboBox2.setModel(jComboBox2Model);
					jComboBox2.setBounds(394, 251, 211, 18);
					jComboBox2.setSize(212, 21);
				}
				{
					group = new ButtonGroup();
				    group.add(dificilRadioButton);
				    group.add(mediaRadioButton);
				    group.add(facilRadioButton);
				    mediaRadioButton.setSelected(true);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void atrasButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.LIGA,false);
	}
	
	private void crearLigaButtonActionPerformed(ActionEvent evt) {
		if(nombreLigaTextField.getText().length() == 0){
			String[] options = {
		
					"Accpetar"
						};
			int n = JOptionPane.showOptionDialog(FutbolFrame.getInstancia(),
						"No se pudo crear la liga."
						+ " Debes poner nombre a la liga.",
						"Aviso!",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE,
						null,
						options,
						options[0]);
			
			if(n == 0) {
				FutbolFrame.getInstancia().ponerMenu(PanelMenu.NUEVA_LIGA,false);
			}
		}
		else {
			CtrlPresentacion.getInstancia().nuevaLiga(nombreLigaTextField.getText(), 1);
			if(facilRadioButton.isSelected()) {
				CtrlPresentacion.getInstancia().agregarEquipos(numeroEquiposComboBox.getSelectedIndex()+2,Constantes.FACIL);
			}
			else if(mediaRadioButton.isSelected()) {
				CtrlPresentacion.getInstancia().agregarEquipos(numeroEquiposComboBox.getSelectedIndex()+2,Constantes.NORMAL);
			}
			else if(dificilRadioButton.isSelected()) {
				CtrlPresentacion.getInstancia().agregarEquipos(numeroEquiposComboBox.getSelectedIndex()+2,Constantes.DIFICIL);
			}
			CtrlPresentacion.getInstancia().setEquiposUs(jComboBox2.getSelectedIndex()+1);
			
			CtrlPresentacion.getInstancia().regeneraPredef();
			
			FutbolFrame.getInstancia().ponerMenu(PanelMenu.ESCOGER_EQUIPOS,true);
		}
	}
	

}

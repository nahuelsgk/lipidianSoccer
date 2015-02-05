/*
 * Jaume Vinyes Navas
 */



package vistas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controladores.CtrlPresentacion;

import vistas.FutbolFrame.PanelMenu;



public class MenuLigaView extends JPanel {
	private static final long serialVersionUID = 2099476743742129222L;
	private JLabel nomEquipLabel;
	private JLabel estadioLabel;
	private JLabel oroLabel;
	private JLabel fisioAtLabel;
	private JLabel fisioLabel;
	private JLabel entrenadorAtLabel2;
	private JLabel entrenadorLabel;
	private JScrollPane jScrollPane1;
	private JButton estadisticasButton;
	private JLabel ligaAtLabel;
	private JLabel ligaLabel;
	private JButton jugarButton;
	private JTable plantillaTable;
	private JButton gestionButton;
	private JButton atributosButton;
	private JButton clasificacionButton;
	private JButton alineacionButton;
	private JLabel tempLabel;
	private JButton entrenarButton;
	private JButton calendarioButton;
	private JLabel jLabel1;
	private JSeparator jSeparator1;
	private JLabel oroAtLabel;
	private JLabel esAtLabel;
	private JLabel eqAtLabel;
	private JLabel temporadaLabel;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}

	public MenuLigaView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(800, 600));
				this.setLayout(null);
				{
					temporadaLabel = new JLabel();
					this.add(temporadaLabel);
					temporadaLabel.setText("Temporada:");
					temporadaLabel.setBounds(404, 127, 102, 15);
				}
				{
					nomEquipLabel = new JLabel();
					this.add(nomEquipLabel);
					nomEquipLabel.setText("Equipo:");
					nomEquipLabel.setBounds(24, 127, 82, 15);
				}
				{
					estadioLabel = new JLabel();
					this.add(estadioLabel);
					estadioLabel.setText("Estadio:");
					estadioLabel.setBounds(24, 154, 76, 15);
				}
				{
					oroLabel = new JLabel();
					this.add(oroLabel);
					oroLabel.setText("Oro:");
					oroLabel.setBounds(25, 181, 81, 15);
				}
				{
					eqAtLabel = new JLabel();
					this.add(eqAtLabel);
					eqAtLabel.setText(CtrlPresentacion.getInstancia().getNombre());
					eqAtLabel.setBounds(98, 129, 171, 15);
				}
				{
					esAtLabel = new JLabel();
					this.add(esAtLabel);
					esAtLabel.setText(CtrlPresentacion.getInstancia().getCampo());
					esAtLabel.setBounds(98, 154, 171, 15);
				}
				{
					oroAtLabel = new JLabel();
					this.add(oroAtLabel);
					oroAtLabel.setText(CtrlPresentacion.getInstancia().getOro());
					oroAtLabel.setBounds(98, 181, 171, 15);
				}
				{
					jSeparator1 = new JSeparator();
					this.add(jSeparator1);
					jSeparator1.setBounds(25, 210, 753, 10);
				}
				{
					jLabel1 = new JLabel();
					this.add(jLabel1);
					jLabel1.setText("Menu Liga");
					jLabel1.setBounds(24, 21, 371, 73);
					jLabel1.setFont(new java.awt.Font("Verdana",3,36));
				}
				{
					tempLabel = new JLabel();
					this.add(tempLabel);
					tempLabel.setText(CtrlPresentacion.getInstancia().getTemporada());
					tempLabel.setBounds(502, 127, 106, 15);
				}
				{
					calendarioButton = new JButton();
					this.add(calendarioButton);
					calendarioButton.setText("Calendario");
					calendarioButton.setBounds(37, 222, 150, 35);
					calendarioButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							calendarioButtonActionPerformed(evt);
						}
					});
				}
				{
					entrenarButton = new JButton();
					this.add(entrenarButton);
					entrenarButton.setText("Entrenar");
					entrenarButton.setBounds(37, 311, 150, 35);
					entrenarButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							entrenarButtonActionPerformed(evt);
						}
					});
				}
				{
					alineacionButton = new JButton();
					this.add(alineacionButton);
					alineacionButton.setText("Alineacion");
					alineacionButton.setBounds(37, 359, 150, 35);
					alineacionButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							alineacionButtonActionPerformed(evt);
						}
					});
				}
				{
					clasificacionButton = new JButton();
					this.add(clasificacionButton);
					clasificacionButton.setText("Clasificacion");
					clasificacionButton.setBounds(37, 405, 150, 35);
					clasificacionButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							clasificacionButtonActionPerformed(evt);
						}
					});
				}
				{
					atributosButton = new JButton();
					this.add(atributosButton);
					atributosButton.setText("Atributos");
					atributosButton.setBounds(37, 451, 150, 35);
					atributosButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							atributosButtonActionPerformed(evt);
						}
					});
				}
				{
					gestionButton = new JButton();
					this.add(gestionButton);
					gestionButton.setText("Gestion Liga");
					gestionButton.setBounds(37, 497, 150, 35);
					gestionButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							gestionButtonActionPerformed(evt);
						}
					});
				}
				{
					jScrollPane1 = new JScrollPane();
					this.add(jScrollPane1);
					jScrollPane1.setBounds(212, 220, 569, 358);
					{
						TableModel plantillaTableModel = 
							new DefaultTableModel(
									CtrlPresentacion.getInstancia().getAtributosFutbolistas(),
									new String[] { "Nombre", "Posicion", "Dorsal", "Sancion", "Amarillas", "Rojas","Lesion" });
						plantillaTable = new JTable();
						jScrollPane1.setViewportView(plantillaTable);
						plantillaTable.setModel(plantillaTableModel);
						plantillaTable.setBounds(376, 256, 402, 314);
						plantillaTable.setRowHeight(20);
						//plantillaTableModel.
					}
				}
				{
					jugarButton = new JButton();
					this.add(jugarButton);
					jugarButton.setText("Jugar Partido");
					jugarButton.setBounds(37, 543, 150, 35);
					jugarButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jugarButtonActionPerformed(evt);
						}
					});
				}
				{
					ligaLabel = new JLabel();
					this.add(ligaLabel);
					ligaLabel.setText("Liga:");
					ligaLabel.setBounds(404, 154, 59, 15);
				}
				{
					ligaAtLabel = new JLabel();
					this.add(ligaAtLabel);
					ligaAtLabel.setText(CtrlPresentacion.getInstancia().getNombreLiga());
					ligaAtLabel.setBounds(475, 154, 287, 15);
				}
				{
					estadisticasButton = new JButton();
					this.add(estadisticasButton);
					estadisticasButton.setText("Estadisticas");
					estadisticasButton.setBounds(37, 265, 150, 35);
					estadisticasButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							estadisticasButtonActionPerformed(evt);
						}
					});
				}
				{
					entrenadorLabel = new JLabel();
					this.add(entrenadorLabel);
					entrenadorLabel.setText("Entrenador:");
					entrenadorLabel.setBounds(404, 181, 92, 15);
				}
				{
					entrenadorAtLabel2 = new JLabel();
					this.add(entrenadorAtLabel2);
					entrenadorAtLabel2.setText(CtrlPresentacion.getInstancia().getNombreEnrenador());
					entrenadorAtLabel2.setBounds(502, 181, 276, 15);
				}
				{
					fisioLabel = new JLabel();
					this.add(fisioLabel);
					fisioLabel.setText("Fisioterapeuta:");
					fisioLabel.setBounds(404, 100, 143, 15);
				}
				{
					fisioAtLabel = new JLabel();
					this.add(fisioAtLabel);
					fisioAtLabel.setText(CtrlPresentacion.getInstancia().getNombreFisio());
					fisioAtLabel.setBounds(526, 100, 254, 14);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void calendarioButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.CALENDARIO, true);
	}
	
	private void gestionButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.GESTION_LIGA, false);
	}
	
	private void atributosButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.ATRIUTOS_VIEW, true);
	}
	
	private void jugarButtonActionPerformed(ActionEvent evt) {
		if(!CtrlPresentacion.getInstancia().getCtrlLiga().completo()) {
			String[] options = {
					"Accpetar",
					};
			int n = JOptionPane.showOptionDialog(FutbolFrame.getInstancia(),
						"Atencion!"
						+ " Debes asignar una alineacion antes de jugar el partido.",
						"Aviso!",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE,
						null,
						options,
						options[0]);
			
			if(n == 0) {
				FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_LIGA,true);
			}
		}
		else {
			CtrlPresentacion c = CtrlPresentacion.getInstancia();		
			int i = c.getTurno();
			String equipo = c.getNombre();
			if(i < c.getEquiposUs()) {
				++i;
				c.setTurno(i);
				/*
				 * Asignar convocados
				 */
				CtrlPresentacion.getInstancia().setConvocados(equipo);
				CtrlPresentacion.getInstancia().resetHorasRestantes();
				
				/*
				 * Jugar els partits de sistema
				 */
				if(i-1 == 1) {
					c.jugarSistema();
				}
				c.setEquipo();
				FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_LIGA, true);
			}
			else {
				c.setTurno(1);
				if(i == 1) {
					c.jugarSistema();
				}
				/*
				 * Asignar convocados
				 */
				CtrlPresentacion.getInstancia().setConvocados(equipo);
				CtrlPresentacion.getInstancia().resetHorasRestantes();
				/*
				 * Jugar els partits d'usuari
				 */
				c.jugarPartido();
			}
		}
	}
	
	private void entrenarButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.ENTRENAR_VIEW, true);
	}
	
	private void estadisticasButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.ESTADISTICAS_VIEW, true);
	}

	private void alineacionButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion.getInstancia().setEstatAlineacio(2);
		CtrlPresentacion.getInstancia().iniciarAlineaciones();
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.ESCOGER_ALINEACION, true);
	}
	
	private void clasificacionButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.CLASIFICACION_VIEW, true);
	}

}

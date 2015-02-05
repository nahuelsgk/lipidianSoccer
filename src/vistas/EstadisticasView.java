/*
 * 	Jaume Vinyes Navas
 */

package vistas;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import javax.swing.table.TableModel;

import vistas.FutbolFrame.PanelMenu;

import controladores.CtrlPresentacion;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EstadisticasView extends JPanel {

	private static final long serialVersionUID = 7656214413895208292L;
	private JLabel tituloLabel;
	private JComboBox equiposComboBox;
	private JButton volverButton;
	private JScrollPane jScrollPane1;
	private JTable futbolistasTable;
	private JLabel empatadosAtLabel;
	private JLabel jLabel1;
	private JLabel ganadosAtLabel;
	private JLabel rojasAtLabel;
	private JLabel amarillasAtLabel;
	private JLabel golesRciAtLabel;
	private JLabel golesMarAtLabel;
	private JLabel empatadosLabel;
	private JLabel perdidosLabel;
	private JLabel ganadosLabel;
	private JLabel rojasLabel;
	private JLabel amarillasLabel;
	private JLabel golesRejLabel1;
	private JLabel golesjLabel;
	private JSeparator jSeparator1;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}

	public EstadisticasView() {
		super();
		initGUI();
	}

	private void initGUI() {
		{
			this.setPreferredSize(new java.awt.Dimension(800, 600));
			this.setLayout(null);
			{
				tituloLabel = new JLabel();
				this.add(tituloLabel);
				tituloLabel.setText("Estadisticas");
				tituloLabel.setBounds(23, 29, 321, 47);
				tituloLabel.setFont(new java.awt.Font("Verdana",3,36));
			}
			{
				if(CtrlPresentacion.getInstancia().getEquiposLiga().length > 0) {
					ComboBoxModel equiposComboBoxModel = 
						new DefaultComboBoxModel(
								CtrlPresentacion.getInstancia().getEquiposLiga());
					equiposComboBox = new JComboBox();
					this.add(equiposComboBox);
					equiposComboBox.setModel(equiposComboBoxModel);
					equiposComboBox.setBounds(23, 145, 173, 24);
					equiposComboBox.setSelectedIndex(0);
				}
				else {
					ComboBoxModel equiposComboBoxModel = 
						new DefaultComboBoxModel(
								CtrlPresentacion.getInstancia().getEquiposLiga());
					equiposComboBox = new JComboBox();
					this.add(equiposComboBox);
					equiposComboBox.setModel(equiposComboBoxModel);
					equiposComboBox.setBounds(23, 145, 173, 24);
				}
				equiposComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						equiposComboBoxActionPerformed(evt);
					}
				});
			}
			{
				jSeparator1 = new JSeparator();
				this.add(jSeparator1);
				jSeparator1.setBounds(232, 86, 10, 483);
				jSeparator1.setOrientation(SwingConstants.VERTICAL);
			}
			{
				golesjLabel = new JLabel();
				this.add(golesjLabel);
				golesjLabel.setText("Goles Marcados:");
				golesjLabel.setBounds(35, 215, 136, 15);
			}
			{
				golesRejLabel1 = new JLabel();
				this.add(golesRejLabel1);
				golesRejLabel1.setText("Goles Recibidos:");
				golesRejLabel1.setBounds(35, 250, 136, 15);
			}
			{
				amarillasLabel = new JLabel();
				this.add(amarillasLabel);
				amarillasLabel.setText("Tarjetas Amarillas:");
				amarillasLabel.setBounds(35, 285, 136, 15);
			}
			{
				rojasLabel = new JLabel();
				this.add(rojasLabel);
				rojasLabel.setText("Tarjetas Rojas:");
				rojasLabel.setBounds(35, 320, 136, 15);
			}
			{
				ganadosLabel = new JLabel();
				this.add(ganadosLabel);
				ganadosLabel.setText("Partidos Ganados:");
				ganadosLabel.setBounds(35, 355, 136, 15);
			}
			{
				perdidosLabel = new JLabel();
				this.add(perdidosLabel);
				perdidosLabel.setText("Partidos Perdidos:");
				perdidosLabel.setBounds(35, 390, 136, 15);
			}
			{
				empatadosLabel = new JLabel();
				this.add(empatadosLabel);
				empatadosLabel.setText("Partidos Empatados:");
				empatadosLabel.setBounds(35, 425, 136, 15);
			}
			{
				golesMarAtLabel = new JLabel();
				this.add(golesMarAtLabel);
				golesMarAtLabel.setBounds(168, 215, 53, 15);
			}
			{
				golesRciAtLabel = new JLabel();
				this.add(golesRciAtLabel);
				golesRciAtLabel.setBounds(168, 250, 53, 15);
			}
			{
				amarillasAtLabel = new JLabel();
				this.add(amarillasAtLabel);
				amarillasAtLabel.setBounds(168, 285, 53, 15);
			}
			{
				rojasAtLabel = new JLabel();
				this.add(rojasAtLabel);
				rojasAtLabel.setBounds(168, 320, 53, 15);
			}
			{
				ganadosAtLabel = new JLabel();
				this.add(ganadosAtLabel);
				ganadosAtLabel.setBounds(168, 355, 53, 15);
			}
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setBounds(168, 390, 53, 14);
			}
			{
				empatadosAtLabel = new JLabel();
				this.add(empatadosAtLabel);
				empatadosAtLabel.setBounds(168, 425, 53, 15);
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(248, 96, 530, 473);
				//jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				{
					TableModel futbolistasTableModel;
					if(CtrlPresentacion.getInstancia().getEquiposLiga().length > 0) {
						futbolistasTableModel = 
							new DefaultTableModel(
									CtrlPresentacion.getInstancia().getAtributosFutbolistasEstadisticas(equiposComboBox.getSelectedIndex()),
									new String[] { "Nombre","GM","GR","TA","TR","PB","PR","%PB","FR","FR","PJ" });
					}
					else {
						futbolistasTableModel = 
							new DefaultTableModel(
									new String[][] {{" "},{" "},{" "},{" "},{" "},{" "},{" "},{" "},{" "},{" "},{" "},{" "},{" "},{" "},{" "},{" "}},
									new String[] { "Nombre","GM","GR","TA","TR","PB","PR","%PB","FR","FR","PJ" });
					}
					futbolistasTable = new JTable();
					jScrollPane1.setViewportView(futbolistasTable);
					futbolistasTable.setModel(futbolistasTableModel);
					futbolistasTable.setBounds(248, 96, 530, 480);
					futbolistasTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
					futbolistasTable.setRowHeight(27);
					for(int i = 0; i < 11; ++i) {
						TableColumn col = futbolistasTable.getColumnModel().getColumn(i); 
						if(i==0)col.setPreferredWidth(200);
						else col.setPreferredWidth(33);
					}
					
				}
			}
			{
				volverButton = new JButton();
				this.add(volverButton);
				volverButton.setText("Volver");
				volverButton.setBounds(35, 538, 161, 31);
				volverButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						volverButtonActionPerformed(evt);
					}
				});
			}
			if(CtrlPresentacion.getInstancia().getEquiposLiga().length > 0) {
				int n = equiposComboBox.getSelectedIndex();
				golesMarAtLabel.setText(CtrlPresentacion.getInstancia().getGolesMarcadosEquipoLiga(n));
				golesRciAtLabel.setText(CtrlPresentacion.getInstancia().getGolesRecibidosEquipoLiga(n));
				amarillasAtLabel.setText(CtrlPresentacion.getInstancia().getTarjetasAmarillasEquipoLiga(n));
				rojasAtLabel.setText(CtrlPresentacion.getInstancia().getTarjetaRojaEquipoLiga(n));
				ganadosAtLabel.setText(CtrlPresentacion.getInstancia().getPartidosGanadosEquipoLiga(n));
				jLabel1.setText(CtrlPresentacion.getInstancia().getPartidosPerdidosEquipoLiga(n));
				empatadosAtLabel.setText(CtrlPresentacion.getInstancia().getPartidosEmpatadosEquipoLiga(n));
			}
		}

	}
	
	private void equiposComboBoxActionPerformed(ActionEvent evt) {
		TableModel futbolistasTableModel = 
			new DefaultTableModel(
					CtrlPresentacion.getInstancia().getAtributosFutbolistasEstadisticas(equiposComboBox.getSelectedIndex()) ,
					new String[] { "Nombre","GM","GR","TA","TR","PB","PR","%PB","FR","FR","PJ" });
		futbolistasTable.setModel(futbolistasTableModel);
		futbolistasTable.setRowHeight(27);
		for(int i = 0; i < 11; ++i) {
			TableColumn col = futbolistasTable.getColumnModel().getColumn(i); 
			if(i==0)col.setPreferredWidth(200);
			else col.setPreferredWidth(33);
		}
		int n = equiposComboBox.getSelectedIndex();
		golesMarAtLabel.setText(CtrlPresentacion.getInstancia().getGolesMarcadosEquipoLiga(n));
		golesRciAtLabel.setText(CtrlPresentacion.getInstancia().getGolesRecibidosEquipoLiga(n));
		amarillasAtLabel.setText(CtrlPresentacion.getInstancia().getTarjetasAmarillasEquipoLiga(n));
		rojasAtLabel.setText(CtrlPresentacion.getInstancia().getTarjetaRojaEquipoLiga(n));
		ganadosAtLabel.setText(CtrlPresentacion.getInstancia().getPartidosGanadosEquipoLiga(n));
		jLabel1.setText(CtrlPresentacion.getInstancia().getPartidosPerdidosEquipoLiga(n));
		empatadosAtLabel.setText(CtrlPresentacion.getInstancia().getPartidosEmpatadosEquipoLiga(n));
	}
	
	private void volverButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_LIGA, false);
	}
}

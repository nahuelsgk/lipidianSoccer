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
import javax.swing.ListModel;

import vistas.FutbolFrame.PanelMenu;

import controladores.CtrlPresentacion;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CargarLigaView extends JPanel {
	private static final long serialVersionUID = 7166175496260201587L;
	private JLabel tituloLabel;
	private JScrollPane jScrollPane1;
	private JList ligaList;
	private JButton volverButton;
	private JButton cargarButton;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}

	public CargarLigaView() {
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
					tituloLabel.setText("Cargar Liga");
					tituloLabel.setBounds(119, 60, 318, 64);
					tituloLabel.setFont(new java.awt.Font("Verdana",3,28));
				}
				{
					cargarButton = new JButton();
					this.add(cargarButton);
					cargarButton.setText("Cargar Liga");
					cargarButton.setBounds(491, 538, 130, 30);
					cargarButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							cargarButtonActionPerformed(evt);
						}
					});
				}
				{
					volverButton = new JButton();
					this.add(volverButton);
					volverButton.setText("Volver");
					volverButton.setBounds(637, 538, 130, 30);
					volverButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							volverButtonActionPerformed(evt);
						}
					});
				}
				{
					jScrollPane1 = new JScrollPane();
					this.add(jScrollPane1);
					jScrollPane1.setBounds(231, 144, 390, 360);
					{
						ListModel ligaListModel = 
							new DefaultComboBoxModel(
									CtrlPresentacion.getInstancia().listarLigas());
						ligaList = new JList();
						jScrollPane1.setViewportView(ligaList);
						ligaList.setModel(ligaListModel);
						ligaList.setFixedCellHeight(50);
						if(CtrlPresentacion.getInstancia().listarLigas().length > 0) {
							ligaList.setSelectedIndex(0);
						}
						ligaList.setBounds(231, 144, 390, 360);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void volverButtonActionPerformed(ActionEvent evt) {
		if(CtrlPresentacion.getInstancia().getEstadoGestion()) {
			FutbolFrame.getInstancia().ponerMenu(PanelMenu.LIGA, false);
		}
		else {
			FutbolFrame.getInstancia().ponerMenu(PanelMenu.GESTION_LIGA, false);
		}
	}
	
	private void cargarButtonActionPerformed(ActionEvent evt) {
		if(CtrlPresentacion.getInstancia().listarLigas().length > 0) {
			String les = String.valueOf(ligaList.getSelectedValue());
			CtrlPresentacion.getInstancia().cargarLiga(les);
			FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_LIGA, true);
		}
		else {
			String[] options = {
					"Accpetar",
					};
			int n = JOptionPane.showOptionDialog(FutbolFrame.getInstancia(),
						"No se puede cargar ninguna liga."
						+ " No hay ningun fichero disponible.",
						"Aviso!",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE,
						null,
						options,
						options[0]);
			
			if(n == 0) {
				FutbolFrame.getInstancia().ponerMenu(PanelMenu.CARGAR_LIGA,true);
			}
		}
	}

}

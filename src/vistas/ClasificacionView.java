/*
 * Jaume Vinyes Navas
 */

package vistas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import controladores.CtrlPresentacion;

import vistas.FutbolFrame.PanelMenu;

public class ClasificacionView extends JPanel {
	private static final long serialVersionUID = -1833721128238218225L;
	private JLabel tituloLabel;
	private JTable clasificacionTable;
	private JScrollPane jScrollPane1;
	private JButton volverButton;


	public ClasificacionView() {
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
				tituloLabel.setText("Clasificacion de la liga");
				tituloLabel.setBounds(59, 26, 489, 64);
				tituloLabel.setFont(new java.awt.Font("Verdana",3,36));
			}
			{
			
			}
			{
				volverButton = new JButton();
				this.add(volverButton);
				volverButton.setText("Volver");
				volverButton.setBounds(636, 542, 132, 37);
				volverButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						volverButtonActionPerformed(evt);
					}
				});
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(100, 128, 594, 382);
				{
					TableModel clasificacionTableModel = 
						new DefaultTableModel(
								CtrlPresentacion.getInstancia().getClasificacion(),
								new String[] { "Nombre","Puntos" });
					clasificacionTable = new JTable();
					jScrollPane1.setViewportView(clasificacionTable);
					clasificacionTable.setModel(clasificacionTableModel);
					clasificacionTable.setBounds(100, 128, 594, 382);
					clasificacionTable.setEnabled(false);
				
					clasificacionTable.setRowHeight(50);
					for(int i = 0; i < 2; ++i) {
						TableColumn col = clasificacionTable.getColumnModel().getColumn(i); 
						if(i==0)col.setPreferredWidth(400);
						else col.setPreferredWidth(33);
					}
				}
			}
		}

	}
	
	private void volverButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_LIGA, false);
	}
}

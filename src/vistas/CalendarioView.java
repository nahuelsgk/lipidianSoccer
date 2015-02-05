/*
 * Jaume Vinyes Navas
 */


package vistas;
import javax.swing.JButton;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import vistas.FutbolFrame.PanelMenu;

import controladores.CtrlPresentacion;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class CalendarioView extends JPanel {

	private static final long serialVersionUID = 712504828311882366L;
	private JButton volverButton;
	private JTextPane calendariJornadaTextPane;
	private ImageIcon imagen;
	private JSlider seleccionSlider;
	private JLabel tituloLabel;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}

	public CalendarioView() {
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
					tituloLabel.setText("Calendario");
					tituloLabel.setBounds(23, 29, 321, 47);
					tituloLabel.setFont(new java.awt.Font("Verdana",3,36));
				}
				{
					calendariJornadaTextPane = new JTextPane();
					this.add(calendariJornadaTextPane);
					calendariJornadaTextPane.setBounds(66, 148, 664, 361);
					calendariJornadaTextPane.setEditable(false);
					calendariJornadaTextPane.setText(
							CtrlPresentacion.getInstancia().getCalendarioJornada(
									CtrlPresentacion.getInstancia().getJornadaActual()));
				}
				{
					volverButton = new JButton();
					this.add(volverButton);
					volverButton.setText("Volver");
					volverButton.setBounds(593, 536, 137, 30);
					volverButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							volverButtonActionPerformed(evt);
						}
					});
				}
				{
					seleccionSlider = new JSlider();
					this.add(seleccionSlider);
					seleccionSlider.setBounds(66, 82, 664, 34);
					seleccionSlider.setMaximum(CtrlPresentacion.getInstancia().getJornadas());
					seleccionSlider.setMinimum(1);
					seleccionSlider.setValue(CtrlPresentacion.getInstancia().getJornadaActual());
					seleccionSlider.setMajorTickSpacing(1);
					seleccionSlider.setPaintTicks(true);
					seleccionSlider.setPaintLabels(true);
					seleccionSlider.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent evt) {
							seleccionSliderStateChanged(evt);
						}
					});
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void volverButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_LIGA, true);
	}
	
	private void seleccionSliderStateChanged(ChangeEvent evt) {
		int m = seleccionSlider.getValue();
		if (m < 0) m = 1;
		calendariJornadaTextPane.setText(CtrlPresentacion.getInstancia().getCalendarioJornada(m));
	}
}

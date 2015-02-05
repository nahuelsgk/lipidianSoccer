/*
 * Jaume Vinyes Navas
 * Alexandre Vidal Obiols
 */


package vistas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JPanel;

import vistas.FutbolFrame.PanelMenu;



public class CreditosView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1564144148645340851L;
	private JLabel alberto;
	private JLabel alex;
	private JLabel jaume;
	private JButton volverButton;
	private JLabel nahuel;
	private JLabel creditosLabel;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian2.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}

	public CreditosView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(800, 600));
				this.setLayout(null);
				{
					alberto = new JLabel();
					this.add(alberto);
					alberto.setText("Alberto Moreno");
					alberto.setBounds(289, 169, 298, 49);
					alberto.setFont(new java.awt.Font("Andy MT",3,36));
					alberto.setForeground(Color.red);

				}
				{
					nahuel = new JLabel();
					this.add(nahuel);
					nahuel.setText("Nahuel Velazco");
					nahuel.setBounds(289, 224, 298, 57);
					nahuel.setFont(new java.awt.Font("Andy MT",3,36));
					nahuel.setForeground(Color.red);

				}
				{
					alex = new JLabel();
					this.add(alex);
					alex.setText("Alex Vidal");
					alex.setBounds(289, 281, 298, 60);
					alex.setFont(new java.awt.Font("Andy MT",3,36));
					alex.setForeground(Color.red);

				}
				{
					jaume = new JLabel();
					this.add(jaume);
					jaume.setText("Jaume Vinyes");
					jaume.setBounds(289, 347, 298, 59);
					jaume.setFont(new java.awt.Font("Andy MT",3,36));
					jaume.setForeground(Color.red);

				}
				{
					volverButton = new JButton();
					this.add(volverButton);
					volverButton.setText("Volver");
					volverButton.setBounds(606, 531, 147, 37);
					volverButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							volverButtonActionPerformed(evt);
						}
					});
				}
				{
					creditosLabel = new JLabel();
					this.add(creditosLabel);
					creditosLabel.setText("Creditos");
					creditosLabel.setBounds(64, 40, 261, 60);
					creditosLabel.setFont(new java.awt.Font("Verdana",3,36));
					creditosLabel.setForeground(Color.red);

				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void volverButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_PRINCIPAL,false);
	}

}

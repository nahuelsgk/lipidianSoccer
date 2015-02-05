/*
 * Jaume Vinyes Navas
 */


package vistas;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import vistas.FutbolFrame.PanelMenu;
import javax.swing.JPanel;

import controladores.CtrlPresentacion;




public class MenuPrincipalView extends JPanel {

	private static final long serialVersionUID = 4848787403387310837L;
	private JButton partidaRapidaButton;
	private JButton creditosButton;
	private JButton salirButton;
	private JLabel tituloLabel;
	private JButton hallOfFameButton;
	private JButton reglamentoButton;
	private JButton configuracionButton;
	private JButton ligaButton;
	private ImageIcon imagen;

	public MenuPrincipalView() {
		super();
		initGUI();
	}
	
	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/fondoPrincipal.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}
	
	private void initGUI() {
		{
			this.setPreferredSize(new java.awt.Dimension(800, 600));
			this.setLayout(null);
			this.setBackground(new java.awt.Color(255,255,255));
			{
				partidaRapidaButton = new JButton();
				this.add(partidaRapidaButton);
				partidaRapidaButton.setText("Partida rapida");
				partidaRapidaButton.setBounds(61, 129, 195, 50);
				partidaRapidaButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						partidaRapidaButtonActionPerformed(evt);
					}
				});
			}
			{
				ligaButton = new JButton();
				this.add(ligaButton);
				ligaButton.setText("Liga");
				ligaButton.setBounds(61, 190, 195, 50);
				ligaButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						ligaButtonActionPerformed(evt);
					}
				});
			}
			{
				configuracionButton = new JButton();
				this.add(configuracionButton);
				configuracionButton.setText("Configuracion");
				configuracionButton.setBounds(61, 251, 195, 50);
				configuracionButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						configuracionButtonActionPerformed(evt);
					}
				});
			}
			{
				reglamentoButton = new JButton();
				this.add(reglamentoButton);
				reglamentoButton.setText("Reglamento");
				reglamentoButton.setBounds(61, 373, 195, 50);
				reglamentoButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						reglamentoButtonActionPerformed(evt);
					}
				});
			}
			{
				creditosButton = new JButton();
				this.add(creditosButton);
				creditosButton.setText("Creditos");
				creditosButton.setBounds(61, 438, 195, 50);
				creditosButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						creditosButtonActionPerformed(evt);
					}
				});
			}
			{
				hallOfFameButton = new JButton();
				this.add(hallOfFameButton);
				hallOfFameButton.setText("Hall of Fame");
				hallOfFameButton.setBounds(61, 312, 195, 50);
				hallOfFameButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						hallOfFameButtonActionPerformed(evt);
					}
				});
			}
			{
				salirButton = new JButton();
				this.add(salirButton);
				salirButton.setText("Salir");
				salirButton.setBounds(61, 499, 195, 50);
				salirButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						salirButtonActionPerformed(evt);
					}
				});
			}
			{
				tituloLabel = new JLabel();
				this.add(tituloLabel);
				tituloLabel.setText("Lipidian Evolution Soccer");
				tituloLabel.setBounds(194, -23, 528, 186);
				tituloLabel.setFont(new java.awt.Font("Verdana",3,36));
				tituloLabel.setForeground(new java.awt.Color(255,0,0));
			}
		}
	}
	
	private void partidaRapidaButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion.getInstancia().setPartidaRapida(1);
		CtrlPresentacion.getInstancia().initPredef(true);
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.PARTIDA_RAPIDA,false);
	}
	
	private void creditosButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.CREDITOS,false);
	}
	
	private void salirButtonActionPerformed(ActionEvent evt) {
		System.exit(0);
	}
	
	private void ligaButtonActionPerformed(ActionEvent evt) {
		CtrlPresentacion.getInstancia().setPartidaRapida(0);
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.LIGA,false);
	}

	private void hallOfFameButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.RECORDS_VIEW,true);
	}
	
	private void reglamentoButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.REGLAMENTO_VIEW,true);
	}
	
	private void configuracionButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.CONFIGURACION_VIEW,true);
	}

	/*private void simulacionButtonActionPerformed(ActionEvent evt) {
		domain.MainIA a = new MainIA();
	}
	*/

}

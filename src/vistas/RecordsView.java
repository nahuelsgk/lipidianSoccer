/*Alexandre Vidal Obiols*/

package vistas;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JPanel;

import controladores.CtrlPresentacion;
import javax.swing.JButton;

import vistas.FutbolFrame.PanelMenu;


public class RecordsView extends JPanel {

	private static final long serialVersionUID = -8959814813262105836L;
	private JLabel jugMasGolesLabel;
	private JLabel jugTarjRojLabel;
	private JLabel jugMasGolesNum;
	private JLabel jugMasGolesNombre;
	private JLabel jugTarjAmarNum;
	private JLabel portMasParadasLabel;
	private JLabel recordsLabel;
	private JLabel portMasParadasNum;
	private JButton volverButton;
	private JLabel equipoMasPerdidosNum;
	private JLabel equipoMasPerdidosNombre;
	private JLabel equipoMasPerdidos;
	private JLabel equipoMasGanadosNum;
	private JLabel equipoMasGanadosNombre;
	private JLabel equipoMasGanados;
	private JLabel equipoMasEmpatadosNum;
	private JLabel equipoMasEmpatadosNombre;
	private JLabel equipoMasEmpatados;
	private JLabel equipoMasGolesMarcados;
	private JLabel equipoMasGolesMarcadosNombre;
	private JLabel equipoMasGolesMarcadosNum;
	private JLabel equipoMasGoleado;
	private JLabel equipoMasGoleadoNombre;
	private JLabel equipoMasGoleadoNum;
	private JLabel porMasParadasNombre;
	private JLabel jugTarjAmarNombre;
	private JLabel jugTarjAmarLabel;
	private JLabel jugTarjRojNum;
	private JLabel jugTarjRojNombre;
	private ImageIcon imagen;

	public void paintComponent(Graphics g) {
		imagen = new ImageIcon(getClass().getResource("/img/lipidian4.jpg"));
		g.drawImage(imagen.getImage(),0,0,800,600,null);
	}

	public RecordsView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		{
			this.setPreferredSize(new java.awt.Dimension(768, 581));
			this.setLayout(null);
			{
				jugMasGolesLabel = new JLabel();
				this.add(jugMasGolesLabel);
				jugMasGolesLabel.setText("Jugador con mas goles:");
				jugMasGolesLabel.setBounds(71, 122, 211, 15);
			}
			{
				jugMasGolesNombre = new JLabel();
				this.add(jugMasGolesNombre);
				jugMasGolesNombre.setText(CtrlPresentacion.getInstancia().getJugadorMasGolesNombre());
				jugMasGolesNombre.setBounds(366, 122, 221, 15);
			}
			{
				jugMasGolesNum = new JLabel();
				this.add(jugMasGolesNum);
				jugMasGolesNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getJugadorMasGolesValue()));
				jugMasGolesNum.setBounds(583, 122, 145, 15);
			}
			{
				jugTarjRojLabel = new JLabel();
				this.add(jugTarjRojLabel);
				jugTarjRojLabel.setText("Jugador con mas tarjetas rojas:");
				jugTarjRojLabel.setBounds(71, 202, 247, 15);
			}
			{
				jugTarjRojNombre = new JLabel();
				this.add(jugTarjRojNombre);
				jugTarjRojNombre.setText(CtrlPresentacion.getInstancia().getJugadorTarjetasRojasNombre());
				jugTarjRojNombre.setBounds(366, 202, 236, 15);
			}
			{
				jugTarjRojNum = new JLabel();
				this.add(jugTarjRojNum);
				jugTarjRojNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getJugadorTarjetasRojasValue()));
				jugTarjRojNum.setBounds(583, 202, 145, 15);
			}
			{
				jugTarjAmarLabel = new JLabel();
				this.add(jugTarjAmarLabel);
				jugTarjAmarLabel.setText("Jugador con mas tarjetas amarillas:");
				jugTarjAmarLabel.setBounds(71, 244, 277, 15);
			}
			{
				jugTarjAmarNombre = new JLabel();
				this.add(jugTarjAmarNombre);
				jugTarjAmarNombre.setText(CtrlPresentacion.getInstancia().getJugadorTarjetasAmarillasNombre());
				jugTarjAmarNombre.setBounds(366, 244, 228, 15);
			}
			{
				jugTarjAmarNum = new JLabel();
				this.add(jugTarjAmarNum);
				jugTarjAmarNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getJugadorTarjetasAmarillasValue()));
				jugTarjAmarNum.setBounds(583, 244, 145, 15);
			}
			{
				portMasParadasLabel = new JLabel();
				this.add(portMasParadasLabel);
				portMasParadasLabel.setText("Portero con mas paradas:");
				portMasParadasLabel.setBounds(71, 160, 193, 15);
			}
			{
				porMasParadasNombre = new JLabel();
				this.add(porMasParadasNombre);
				porMasParadasNombre.setText(CtrlPresentacion.getInstancia().getPorteroMasParadasNombre());
				porMasParadasNombre.setBounds(366, 160, 217, 15);
			}
			{
				portMasParadasNum = new JLabel();
				this.add(portMasParadasNum);
				portMasParadasNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getPorteroMasParadasValue()));
				portMasParadasNum.setBounds(583, 160, 145, 15);
			}
			{
				recordsLabel = new JLabel();
				this.add(recordsLabel);
				recordsLabel.setText("Hall Of Fame");
				recordsLabel.setBounds(50, 46, 250, 29);
				recordsLabel.setFont(new java.awt.Font("Dialog",1,26));
			}
			{
				equipoMasGanados = new JLabel();
				this.add(equipoMasGanados);
				equipoMasGanados.setText("Equipo con mas partidos ganados:");
				equipoMasGanados.setBounds(70, 287, 290, 15);
			}
			{
				equipoMasGanadosNombre = new JLabel();
				this.add(equipoMasGanadosNombre);
				equipoMasGanadosNombre.setText(CtrlPresentacion.getInstancia().getEquipoMasPartidosGanadosNombre());
				equipoMasGanadosNombre.setBounds(366, 287, 205, 15);
			}
			{
				equipoMasGanadosNum = new JLabel();
				this.add(equipoMasGanadosNum);
				equipoMasGanadosNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getEquipoMasPartidosGanadosValue()));
				equipoMasGanadosNum.setBounds(583, 287, 145, 15);
			}
			{
				equipoMasPerdidos = new JLabel();
				this.add(equipoMasPerdidos);
				equipoMasPerdidos.setText("Equipo con mas partidos perdidos:");
				equipoMasPerdidos.setBounds(70, 330, 262, 15);
			}
			{
				equipoMasPerdidosNombre = new JLabel();
				this.add(equipoMasPerdidosNombre);
				equipoMasPerdidosNombre.setText(CtrlPresentacion.getInstancia().getEquipoMasPartidosPerdidosNombre());
				equipoMasPerdidosNombre.setBounds(366, 330, 205, 15);
			}
			{
				equipoMasPerdidosNum = new JLabel();
				this.add(equipoMasPerdidosNum);
				equipoMasPerdidosNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getEquipoMasPartidosPerdidosValue()));
				equipoMasPerdidosNum.setBounds(583, 330, 145, 15);
			}
			{
				equipoMasEmpatados = new JLabel();
				this.add(equipoMasEmpatados);
				equipoMasEmpatados.setText("Equipo con mas partidos empatados:");
				equipoMasEmpatados.setBounds(70, 376, 278, 15);
			}
			{
				equipoMasEmpatadosNombre = new JLabel();
				this.add(equipoMasEmpatadosNombre);
				equipoMasEmpatadosNombre.setText(CtrlPresentacion.getInstancia().getEquipoMasPartidosEmpatadosNombre());
				equipoMasEmpatadosNombre.setBounds(366, 375, 205, 16);
			}
			{
				equipoMasEmpatadosNum = new JLabel();
				this.add(equipoMasEmpatadosNum);
				equipoMasEmpatadosNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getEquipoMasPartidosEmpatadosValue()));
				equipoMasEmpatadosNum.setBounds(583, 376, 145, 15);
			}
			{
				equipoMasGolesMarcados = new JLabel();
				this.add(equipoMasGolesMarcados);
				equipoMasGolesMarcados.setText("Equipo mas goleador:");
				equipoMasGolesMarcados.setBounds(70, 421, 229, 15);
			}
			{
				equipoMasGolesMarcadosNombre = new JLabel();
				this.add(equipoMasGolesMarcadosNombre);
				equipoMasGolesMarcadosNombre.setText(CtrlPresentacion.getInstancia().getEquipoMasGolesMarcadosString());
				equipoMasGolesMarcadosNombre.setBounds(366, 420, 211, 16);
			}
			{
				equipoMasGolesMarcadosNum = new JLabel();
				this.add(equipoMasGolesMarcadosNum);
				equipoMasGolesMarcadosNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getEquipoMasGolesMarcadosValue()));
				equipoMasGolesMarcadosNum.setBounds(583, 421, 145, 15);
			}
			{
				equipoMasGoleado = new JLabel();
				this.add(equipoMasGoleado);
				equipoMasGoleado.setText("Equipo mas goleado:");
				equipoMasGoleado.setBounds(70, 467, 248, 15);
			}
			{
				equipoMasGoleadoNombre = new JLabel();
				this.add(equipoMasGoleadoNombre);
				equipoMasGoleadoNombre.setText(CtrlPresentacion.getInstancia().getEquipoMasGoleadoString());
				equipoMasGoleadoNombre.setBounds(366, 465, 217, 18);
			}
			{
				equipoMasGoleadoNum = new JLabel();
				this.add(equipoMasGoleadoNum);
				equipoMasGoleadoNum.setText(String.valueOf(CtrlPresentacion.getInstancia().getEquipoMasGoleadoValue()));
				equipoMasGoleadoNum.setBounds(583, 467, 145, 15);
			}
			{
				volverButton = new JButton();
				this.add(volverButton);
				volverButton.setText("Volver");
				volverButton.setBounds(583, 522, 117, 27);
				volverButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						volverButtonActionPerformed(evt);
					}
				});
			}
		}

	}
	
	private void volverButtonActionPerformed(ActionEvent evt) {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_PRINCIPAL,false);
	}
	
	
}
/*
 * Jaume Vinyes Navas
 */


package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;



public class FutbolFrame extends JFrame {

	public static enum PanelMenu {
		MENU_PRINCIPAL(new MenuPrincipalView()),
		PARTIDA_RAPIDA(new PartidaRapidaView()),
		LIGA(new LigaView()),
		NUEVA_LIGA(new NuevaLigaView()),
		ESCOGER_EQUIPOS(new EscogerEquiposView()),
		ESCOGER_IA(new EscogerIaView()),
		ESCOGER_EQUIPOS_RAPIDO(new EscogerEquiposRapidoView()),
		MENU_LIGA(new MenuLigaView()),
		PERSONALIZAR_EQUIPO(new PersonalizarEquipoView()),
		ESCOGER_ALINEACION(new EscogerAlineacionView()),
		SIMULACION(new SimulacionView()),
		CREDITOS(new CreditosView()),
		RESULTADO_PARTIDA_RAPIDA(new ResultadoPartidaRapidaView()),
		CALENDARIO(new CalendarioView()),
		GESTION_LIGA(new GestionLigaView()),
		CARGAR_LIGA(new CargarLigaView()),
		ATRIUTOS_VIEW(new AtributosView()),
		ENTRENAR_VIEW(new EntrenarView()),
		ESTADISTICAS_VIEW(new EstadisticasView()),
		ESCOGER_SUBSTITUTO(new EscogerSubstitutoView()),
		CLASIFICACION_VIEW(new ClasificacionView()),
		RECORDS_VIEW(new RecordsView()),
		ELIMINAR_LIGA(new EliminarLigaView()),
		REGLAMENTO_VIEW(new ReglamentoView()),
		CONFIGURACION_VIEW(new ConfiguracioView());
		
		
		private JPanel panelMenu;
		
		private PanelMenu(JPanel panel) {
			this.panelMenu = panel;
		}
		
		public JPanel getPanel() {
			return panelMenu;
		}
		
		public void rePaint() {
			switch (this) {
				case MENU_PRINCIPAL:
					panelMenu = new MenuPrincipalView();
					return;
				case ESCOGER_EQUIPOS:
					panelMenu = new EscogerEquiposView();
					return;
				case PERSONALIZAR_EQUIPO:
					panelMenu = new PersonalizarEquipoView();
					return;
				case ESCOGER_EQUIPOS_RAPIDO:
					panelMenu = new EscogerEquiposRapidoView();
					return;
				case ESCOGER_ALINEACION:
					panelMenu = new EscogerAlineacionView();
					return;
				case RESULTADO_PARTIDA_RAPIDA:
					panelMenu = new ResultadoPartidaRapidaView();
					return;
				case CALENDARIO:
					panelMenu= new CalendarioView();
					return;
				case ATRIUTOS_VIEW:
					panelMenu = new AtributosView();
					return;
				case ENTRENAR_VIEW:
					panelMenu = new EntrenarView();
					return;
				case MENU_LIGA:
					panelMenu = new MenuLigaView();
					return;
				case ESTADISTICAS_VIEW:
					panelMenu = new EstadisticasView();
					return;
				case ESCOGER_SUBSTITUTO:
					panelMenu = new EscogerSubstitutoView();
					return;
				case CLASIFICACION_VIEW:
					panelMenu = new ClasificacionView();
					return;
				case SIMULACION:
					panelMenu = new SimulacionView();
					return;
				case RECORDS_VIEW:
					panelMenu = new RecordsView();
					return;
				case ESCOGER_IA:
					panelMenu = new EscogerIaView();
					return;
				case NUEVA_LIGA:
					panelMenu = new NuevaLigaView();
					return;
				case CARGAR_LIGA:
					panelMenu = new CargarLigaView();
					return;
				case ELIMINAR_LIGA:
					panelMenu = new EliminarLigaView();
					return;
				case CONFIGURACION_VIEW:
					panelMenu = new ConfiguracioView();
					return;
			}
		}
		
		public void setVisible(boolean v) {
			panelMenu.setVisible(v);
		}
	}
	
	
	private static final long serialVersionUID = -3949622421264025287L;
	private static FutbolFrame instancia;
	private PanelMenu menu;
	
	public static FutbolFrame getInstancia() {
		if (instancia == null) {
			instancia = new FutbolFrame();
		}
		return instancia;
	}
	
	private FutbolFrame() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		menu = PanelMenu.MENU_PRINCIPAL;
		ponerMenu(PanelMenu.MENU_PRINCIPAL,false);
		getContentPane().setSize(800, 600);
		getContentPane().setPreferredSize(new java.awt.Dimension(800, 600));
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		getContentPane().setVisible(true);
	}
	
	private void setS() {
		this.setSize(1080, 780);
	}

	public void ponerMenu(PanelMenu panel, boolean repaint) {
		if (panel != null) {
			this.menu.setVisible(false);
			if(repaint) {
				panel.rePaint();
			}
		}
		if(panel == PanelMenu.SIMULACION) {
			setS();
		}
		this.menu = panel;
		add(panel.getPanel());
		this.menu.setVisible(true);
	}
	
}

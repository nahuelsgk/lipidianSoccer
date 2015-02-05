/*
 * Jaume Vinyes Navas
 */



package controladores;

import java.util.Vector;

import javax.swing.JOptionPane;

import vistas.FutbolFrame;
import vistas.FutbolFrame.PanelMenu;

import domain.Constantes;
import domain.Convocado;
import domain.EquipoFutbol;
import domain.Liga;
import domain.Partido;

public class CtrlNuevaLiga {
	
	private Liga l;
	private int k;
	private static CtrlNuevaLiga instancia;
	
		
	public CtrlNuevaLiga() {
		l = new Liga(0,"");
	}
	
	public void nuevaLiga(String nom, Integer temp){
		l = null;
		l = new Liga(temp, nom);
	}
	
	public void generarCalenario() {
		l.generarCalendario();
	}
	
	public void addEquipo() {
		l.addEquipo();
	}
	
	public void addEquipo(String eq) {
		EquipoFutbol e = CtrlPresentacion.getInstancia().getCtrlEquiposPredefinidos().getEquipoNombre(eq);
		l.addEquipo(e,true);
	}

	public void clearLiga() {
		l = null;		
	}

	public Integer getJornadas() {
		return l.getJornadas();
	}

	public String getCalendarioJornada(int jornada) {
		return l.getCalendarioJornada(jornada);
	}
		
	public String getTemporada() {
		return String.valueOf(l.getTemporada());
	}

	public String getNombreLiga() {
		return l.getNom();
	}

	public String[][] getAtributosFutbolistasEstadisticas(int eq) {
		return l.getAtributosFutbolistasEstadisticas(eq);
	}

	public String getGolesMarcadosEquipo(int eq) {
		return String.valueOf(l.getGolesMarcados(eq));
	}

	public String getGolesRecibidosEquipo(int eq) {
		return String.valueOf(l.getGolesRecibidos(eq));
	}

	public String getTarjetasAmarillasEquipo(int eq) {
		return String.valueOf(l.getTarjetasAmarillas(eq));
	}

	public String getTarjetaRojaEquipo(int eq) {
		return String.valueOf(l.getTarjetasRojas(eq));
	}

	public String getPartidosGanadosEquipo(int eq) {
		return String.valueOf(l.getPartidosGanados(eq));
	}

	public String getPartidosPerdidosEquipo(int eq) {
		return String.valueOf(l.getPartidosPerdidos(eq));
	}

	public String getPartidosEmpatadosEquipo(int eq) {
		return String.valueOf(l.getPartidosEmpatados(eq));
	}
	
	public String[] getEquiposLiga() {
		return l.getEquiposLiga();
	}
	
	/*
	 * Datos
	 */
	
	public Liga getLiga() {
		return l;
	}
	
	public void setLiga(Liga l) {
		this.l = l;
	}
	
	public EquipoFutbol getEquipo() {
		return l.getEquipo();
	}
	
	public void setEquipo(EquipoFutbol ef) {
		l.addEquipo(ef, ef.esUsuario());
	}

	public int getJornadaActual() {
		return l.getJornadaActual();
	}
	
	public static CtrlNuevaLiga getInstancia() {
		if(instancia == null) {
			instancia = new CtrlNuevaLiga();
		}
		return instancia;
	}

	public void jugarPartido() {
		k = 1;
		continuarPartidos(true);
	}
	
	public void continuarPartidos(boolean iniciarVisualizacion) {
		int jornada = getJornadaActual();
		if(k <= l.getPartidosJornada()) {
			Partido p = l.getPartidoJornadaPartido(jornada, k);
			++k;
			EquipoFutbol efV = p.getEquipoVisitante();
			EquipoFutbol efL = p.getEquipoLocal();
			if(!efV.esFantasma() && !efL.esFantasma()) {
				if(efV.esUsuario() || efL.esUsuario()) {
					p.iniciarPartido();
					CtrlPresentacion.getInstancia().setEsperandoLiga(false);
					if(!iniciarVisualizacion) { 
						String[] options = {
								"Accpetar"
									};
						JOptionPane.showOptionDialog(FutbolFrame.getInstancia(),
									"El partido ha terminado.\n"
									+ "Para ver el siquente partido haz clic en aceptar.",
									"Aviso!",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.WARNING_MESSAGE,
									null,
									options,
									options[0]);
					}
					CtrlSimulacion.getInstancia().inicioSimulacion(p.getVisitantes(),p.getLocales(),p, true);
					if (iniciarVisualizacion) {
						FutbolFrame.getInstancia().ponerMenu(PanelMenu.SIMULACION, true);
					}
				}
				else {
					continuarPartidos(iniciarVisualizacion);
				}
			}
			else {
				continuarPartidos(iniciarVisualizacion);
			}
		}
		else {
			CtrlPresentacion.getInstancia().setEquipo();
			CtrlPresentacion.getInstancia().setJornadaActual(CtrlPresentacion.getInstancia().getJornadaActual()+1);
			recuperacion();
			if(CtrlPresentacion.getInstancia().getJornadaActual() > CtrlPresentacion.getInstancia().getJornadas()) {
				l.resetPartidos();
				generarCalenario();
				CtrlPresentacion.getInstancia().setJornadaActual(1);
				l.setTemporada(l.getTemporada()+1);
				/*
				 * Reset de la puntuacion
				 */
				l.resetPuntos();
				String[] options = {
						"Accpetar",
						};
				int n = JOptionPane.showOptionDialog(FutbolFrame.getInstancia(),
							"Atencion!"
							+ " Has acabado la temporada, preparate para ser aun mejor en la siguiente! .",
							"Aviso!",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE,
							null,
							options,
							options[0]);
				
				if(n == 0) {
					FutbolFrame.getInstancia().dispose();
				  	FutbolFrame.getInstancia().pack();
				  	FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_LIGA, true);
				  	FutbolFrame.getInstancia().setVisible(true);
				}
			}
			FutbolFrame.getInstancia().dispose();
		  	FutbolFrame.getInstancia().pack();
		  	FutbolFrame.getInstancia().ponerMenu(PanelMenu.MENU_LIGA, true);
		  	FutbolFrame.getInstancia().setVisible(true);
			
		}
		
	}

	public void jugarSistema() {
		int jornada = getJornadaActual();
		if(jornada <= l.getJornadas()) {
			for(int i = 1; i <= l.getPartidosJornada(); ++i) {
				Partido p = l.getPartidoJornadaPartido(jornada, i);
				EquipoFutbol efV = p.getEquipoVisitante();
				EquipoFutbol efL = p.getEquipoLocal();
				boolean finals = false;
				Vector<Convocado> visitante = new Vector<Convocado>();
				Vector<Convocado> local = new Vector<Convocado>();
				Vector<Convocado> visitanteReservas = new Vector<Convocado>();
				Vector<Convocado> localReservas = new Vector<Convocado>();
				if(!efV.esUsuario() && !efV.esFantasma()) { 
					Convocado c;
					int n = 0;
					for(int j = 0; j < 18 && !finals; ++j) {
						c = efV.asignarConvocado(efV.getDorsal(j));
						if(c != null&& n < 11) {
							c.setPosicion(n);
							visitante.add(c);
							++n;
							if(n == 11) finals = true;
						}
						else if(c != null) {
							c.setPosicion(k);
							visitanteReservas.add(c);
							++n;
							--k;
							if(n == 16) finals = true;
						}
					}
				}
				if(!efL.esUsuario() && !efL.esFantasma()) {
					finals = false;
					Convocado c;
					int n = 0;
					int k = -1;
					for(int j = 0; j < 18 && !finals; ++j) {
						c = efL.asignarConvocado(efL.getDorsal(j));
						if(c != null && n < 11) {
							c.setPosicion(n);
							local.add(c);
							++n;
						}
						else if(c != null) {
							c.setPosicion(k);
							localReservas.add(c);
							++n;
							--k;
							if(n == 16) finals = true;
						}
					}
				}
				if(!efV.esUsuario() && !efL.esUsuario() && !efV.esFantasma() && !efL.esFantasma()) {
					CtrlSimulacion.getInstancia().simulacionInstantanea(visitante, local, p, true);
				}
				else if(!efV.esUsuario()  && !efV.esFantasma() && efL.esUsuario()) {
					p.setVisitantes(visitante);
					p.setVisitantesReserva(visitanteReservas);
					p.setAlineacionVisitante(Constantes.ALINEACION_4_3_3);		
				}
				else if(efV.esUsuario() && !efL.esFantasma() && !efL.esUsuario()) {
					p.setLocales(local);
					p.setLocalesReserva(localReservas);
					p.setAlineacionLocal(Constantes.ALINEACION_4_3_3);					
				}
			}
		}
	}

	public int getNumeroEquipos() {
		return l.getNumeroEquipos();
	}

	public int getNumeroEquipoSistema() {
		return l.getNumeroEquipoSistema();
	}
	public void setJornadaActual(int i) {
		l.setJornadaActual(i);
	}

	public void setDificultad(int dif) {
		l.setDificultadEquipo(dif);
	}

	public void setConvocadosPartido(String equipo) {
		int jornada = getJornadaActual();
		if(jornada <= l.getJornadas()) {
			for(int i = 1; i <= l.getPartidosJornada(); ++i) {
				Partido p = l.getPartidoJornadaPartido(jornada, i);
				EquipoFutbol efV = p.getEquipoVisitante();
				EquipoFutbol efL = p.getEquipoLocal();
				if(efV.getNombre().equals(equipo)) {
					p.setVisitantes(CtrlPresentacion.getInstancia().getCtrlLiga().getConvocados());
					p.setVisitantesReserva(CtrlPresentacion.getInstancia().getCtrlLiga().getReservas());
					p.setAlineacionVisitante(CtrlPresentacion.getInstancia().getCtrlLiga().getAlineacion());
				}
				else if(efL.getNombre().equals(equipo)) {
					p.setLocales(CtrlPresentacion.getInstancia().getCtrlLiga().getConvocados());
					p.setLocalesReserva(CtrlPresentacion.getInstancia().getCtrlLiga().getReservas());
					p.setAlineacionLocal(CtrlPresentacion.getInstancia().getCtrlLiga().getAlineacion());
				}
			}
			/*
			 * posar vectors de convocats i reserves a null
			 */
			CtrlPresentacion.getInstancia().resetConvocadosLiga();
		}
	}

	public String[][] getClasificacion() {
		String[][] at = new String[getNumeroEquipos()][2];
		for(int i = 0; i < getNumeroEquipos(); ++i) {
			if(!l.getEquipo(i).esFantasma()) {
				for(int j = 0; j < 2; ++j) {
					switch(j) {
						case 0:
							at[i][j] = l.getEquipo(i).getNombre();
							break;
						case 1:
							at[i][j] = String.valueOf(l.getEquipo(i).getPuntos());
							break;
					}
				}
			}
		}
		/*
		 * ordenacion
		 */
		if(getNumeroEquipos() > 0) {
			for(int i = 0; i < getNumeroEquipos(); ++i) {
				for(int j = getNumeroEquipos()-1; j > i; j--) {
					if(Integer.parseInt(at[j-1][1]) < Integer.parseInt(at[j][1])) {
						String[] aux = new String[2];
						aux = at[j-1];
						at[j-1] = at[j];
						at[j] = aux;
					}
				}
			}
		}
		return at;
	}
	/*
	 * Fin Datos
	 */

	public void recuperacion() {
		l.recuperacionJugadores();
	}

	public boolean esValido(String equipo) {
		return l.esEquipoValido(equipo);
	}
	
	
}
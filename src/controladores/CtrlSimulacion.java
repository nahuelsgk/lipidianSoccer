/*
 * Alberto Moreno Vega
 * Alexandre Vidal Obiols
 */


package controladores;


import java.util.Random;
import java.util.Vector;
import java.util.HashMap;



import domain.Arbitraje;
import domain.Convocado;
import domain.IA;
import domain.Partido;
import domain.PelotaFutbol;

public class CtrlSimulacion {


	private static CtrlSimulacion instancia;
	private Partido part;
	private Integer jugando;
	private boolean finalPartido;
	private int evento;
	private int alineacion1;
	private int alineacion2;
	
	private Integer cambiosRestantes;
	private HashMap<Integer, Integer> cambios;
	private HashMap<Integer, Integer> posLocales;
	private HashMap<Integer, Integer> posVisitantes;
	private boolean liga;
	
	
	public CtrlSimulacion() {
		jugando = 0;
		evento = 0;
		liga = false;
		
			CtrlIAConf.getInstancia().init();
		
	}
	
	public void actualizarDatosPartido() {
		Vector<Convocado> visitantes = part.getVisitantes();
		Vector<Convocado> locales = part.getLocales();
		Vector<Convocado> lesionadosSancionados = part.getLesionadosSancionados();
		CtrlPresentacion.getInstancia().reiniciaJugadores();
		for (int i = 0; i < visitantes.size(); ++i) {
			CtrlPresentacion.getInstancia().agregaJugador((int) visitantes.elementAt(i).getPosX(), (int) visitantes.elementAt(i).getPosY(), 
					visitantes.elementAt(i).getDorsal(), CtrlPresentacion.VISITANTE);
		}
		for (int i = 0; i < locales.size(); ++i) {
			CtrlPresentacion.getInstancia().agregaJugador((int)locales.elementAt(i).getPosX(),(int) locales.elementAt(i).getPosY(), 
					locales.elementAt(i).getDorsal(), CtrlPresentacion.LOCAL);
		}
		CtrlPresentacion.getInstancia().reiniciaLesionadosSancionados();
		for (int i = 0; i < lesionadosSancionados.size(); ++i) {
			CtrlPresentacion.getInstancia().actualizaLesionadosSancionados((int)lesionadosSancionados.elementAt(i).getPosX(),
					(int) lesionadosSancionados.elementAt(i).getPosY(), 
					lesionadosSancionados.elementAt(i).getDorsal());
		}
		PelotaFutbol p = part.getPelota();
		CtrlPresentacion.getInstancia().actualizaPelota((int)p.getX(),(int) p.getY(), (int)p.getZ());
	}
	

	
	public boolean siguienteMovimiento() {

		IA.getInstancia().ejecutar();
		comprobarCambios();
		PelotaFutbol p = part.getPelota();
		p.siguientePosicion();
		Arbitraje.getInstancia().compruebaNormativa();
		actualizarDatosPartido();
		if (part.getTiempoTotal() == 2700) {
			CtrlPresentacion.getInstancia().actualizaTiempo(part.getTiempoMostrado()*2);
		}
		else {
			CtrlPresentacion.getInstancia().actualizaTiempo(part.getTiempoMostrado());
		}
		CtrlPresentacion.getInstancia().setGoles(part.getGolesVisitante(), part.getGolesLocal());
		mostrarEventos();
		if (part.getFinal()) {
			evento = 0;
			CtrlPresentacion.getInstancia().finalizaPartido();
			finalizarSimulacion();
			return true;
		}
		return false;
	}
	
	private void comprobarCambios() {
		if (part.getSolicitarCambio() != 0) {
			if (part.getSolicitarCambio() == Partido.CAMBIO_LOCAL) {
				CtrlPresentacion.getInstancia().solicitarCambio(CtrlPresentacion.LOCAL);
			}
			else {
				CtrlPresentacion.getInstancia().solicitarCambio(CtrlPresentacion.VISITANTE);
			}
			part.setSolicitarCambio(0);
		}
	}

	
	public void cambiaFutbolistaFresco(Convocado cambiado, Convocado nuevo, int equipo) {
		if (cambiado != null) {
			if (equipo == CtrlPresentacion.LOCAL) {
				IA.getInstancia().realizaCambioTactico(cambiado ,nuevo, Partido.CAMBIO_LOCAL);
			}
			else {
				IA.getInstancia().realizaCambioTactico(cambiado, nuevo, Partido.CAMBIO_VISITANTE);
			}
		}
		
	}
	
	
	public void cambiaFutbolistaLesionado(Convocado c, int equipo) {
		if (c != null) {
			if (equipo == CtrlPresentacion.LOCAL) {
				IA.getInstancia().realizaCambioLesionado(part.getCambio() ,c, Partido.CAMBIO_LOCAL);
			}
			else {
				IA.getInstancia().realizaCambioLesionado(part.getCambio(), c, Partido.CAMBIO_VISITANTE);
			}
		}
		
	}
	
	public boolean getLiga() {
		return this.liga;
	}


	public void setLiga(boolean liga) {
		this.liga = liga;
	}

	public void finalizarSimulacion() {
		if (!this.liga) {
			CtrlPresentacion.getInstancia().ventanaEstadisticas();
		}
		else {
			CtrlPresentacion.getInstancia().continuarPartidos();
		}
	}
	
	public void inicioSimulacion(Vector<Convocado> visitantes, Vector<Convocado> locales, Partido part, boolean liga) {
		this.liga = liga;
		this.part = part;
		this.finalPartido = false;
		jugando = 1;
		
		PelotaFutbol p = part.getPelota();
		part.setLocales(locales);
		part.setVisitantes(visitantes);
		CtrlPresentacion.getInstancia().reiniciaJugadores();
		CtrlPresentacion.getInstancia().setVisitanteUsuario(part.getEquipoVisitante().esUsuario());
		CtrlPresentacion.getInstancia().setLocalUsuario(part.getEquipoLocal().esUsuario());
		
		for (int i = 0; i < visitantes.size(); ++i) {
			CtrlPresentacion.getInstancia().agregaJugador((int)visitantes.elementAt(i).getPosX(), (int)visitantes.elementAt(i).getPosY(), 
					visitantes.elementAt(i).getDorsal(), CtrlPresentacion.VISITANTE);
		}
		for (int i = 0; i < locales.size(); ++i) {
			CtrlPresentacion.getInstancia().agregaJugador((int)locales.elementAt(i).getPosX(),(int) locales.elementAt(i).getPosY(), 
					locales.elementAt(i).getDorsal(), CtrlPresentacion.LOCAL);
		}
		CtrlPresentacion.getInstancia().agregaPelota((int)p.getX(),(int) p.getY(), (int)p.getZ());

		CtrlPresentacion.getInstancia().actualizaTiempo(part.getTiempoMostrado());
		CtrlPresentacion.getInstancia().setNombresEquipos(part.getEquipoVisitante().getNombre(), part.getEquipoLocal().getNombre());
		CtrlPresentacion.getInstancia().setGoles(part.getGolesLocal(), part.getGolesVisitante());
		CtrlPresentacion.getInstancia().setMeteorologia(part.getMeteo().descripcio().get("Nom"));
		CtrlPresentacion.getInstancia().setFinPartido(false);
		if (!liga) {
			CtrlPresentacion.getInstancia().simula();
		}

	}
	
	public void simulacionRapida(Vector<Convocado> visitantes, Vector<Convocado> locales, Partido part, boolean liga) {
		this.liga = liga;
		this.part = part;
		this.finalPartido = false;
		jugando = 1;
		PelotaFutbol p = part.getPelota();
		part.setLocales(locales);
		part.setVisitantes(visitantes);
		part.iniciarPartido();
		CtrlPresentacion.getInstancia().setNombresEquipos(part.getEquipoVisitante().getNombre(), part.getEquipoLocal().getNombre());
		
		while (!part.getFinal()) {
			IA.getInstancia().ejecutar();
			comprobarCambios();
			p.siguientePosicion();
			Arbitraje.getInstancia().compruebaNormativa();
			CtrlPresentacion.getInstancia().setGoles(part.getGolesLocal(), part.getGolesVisitante()); 
		}

	}
	
	public void simulacionInstantanea(Vector<Convocado> visitantes, Vector<Convocado> locales, Partido part, boolean liga) {
		this.liga = liga;
		this.part = part;
		this.finalPartido = false;
		part.setLocales(locales);
		part.setVisitantes(visitantes);
		jugando = 1;
		CtrlPresentacion.getInstancia().setNombresEquipos(part.getEquipoVisitante().getNombre(), part.getEquipoLocal().getNombre());
		asignaGoles(visitantes, locales);
		asignaFaltas(visitantes, locales);
		asignaPases(visitantes, locales);
		asignaParadasPortero(visitantes, locales);
		Arbitraje.getInstancia().setPartido(part);
		CtrlPresentacion.getInstancia().setGoles(part.getGolesLocal(), part.getGolesVisitante()); 
		part.setFinal();
		Arbitraje.getInstancia().actualizarEstadisticas();
		this.finalPartido = true;
		if (part.getEquipoLocal().esUsuario() || part.getEquipoVisitante().esUsuario()) {
			finalizarSimulacion();
		}
		
	}
	
	private void asignaParadasPortero(Vector<Convocado> visitantes, Vector<Convocado> locales) {
		Random r = new Random();
		for (int i = 0; i < visitantes.size(); i++) {
			if (visitantes.elementAt(i).getPosicion() == 0) {
				int paradas = r.nextInt(8);
				paradas += 3;
				for (int j = 0; j < paradas; j++) {
					visitantes.elementAt(i).incrementaParadas();
				}
				break;
			}
		}
		for (int i = 0; i < locales.size(); i++) {
			if (locales.elementAt(i).getPosicion() == 0) {
				int paradas = r.nextInt(8);
				paradas += 3;
				for (int j = 0; j < paradas; j++) {
					locales.elementAt(i).incrementaParadas();
				}
				break;
			}
		}
	}
	
	private void asignaPases(Vector<Convocado> visitantes, Vector<Convocado> locales) {
		int probabilidadBase = 35;
		Convocado c;
		Random r = new Random();
		int prob;
		for (int i = 0; i < visitantes.size(); i++) {
			c = visitantes.elementAt(i);
			int pase = (c.getPase()/3) + probabilidadBase;
			if (c.getPosicion() == 0) {
				prob = r.nextInt(15);
				prob += 20;
			}
			else {
				prob = r.nextInt(10);
			}
			int rnd = r.nextInt(20);
			double ratio = ((double)pase+(double)rnd)/100;
			double buenos = ((double)prob)*ratio;
			int pasesBuenos = (int)buenos;
			for (int j = 0; j < prob; j++) {
				c.incrementaPasesRealizados();
			}
			for (int j = 0; j < pasesBuenos; j++) {
				c.incrementaPasesBuenos();
			}
		}
		for (int i = 0; i < locales.size(); i++) {
			c = locales.elementAt(i);
			int pase = (c.getPase()/3) + probabilidadBase;
			if (c.getPosicion() == 0) {
				prob = r.nextInt(15);
				prob += 20;
			}
			else {
				prob = r.nextInt(10);
			}
			int rnd = r.nextInt(20);
			double ratio = ((double)pase+(double)rnd)/100;
			double buenos = ((double)prob)*ratio;
			int pasesBuenos = (int)buenos;
			for (int j = 0; j < prob; j++) {
				c.incrementaPasesRealizados();
			}
			for (int j = 0; j < pasesBuenos; j++) {
				c.incrementaPasesBuenos();
			}
		}
	}
	
	private void asignaFaltas(Vector<Convocado> visitantes, Vector<Convocado> locales) {
		int probabilidadBase = 5;
		Random r = new Random();
		int prob;
		Convocado c;
		int faltasALocales = 0;
		int faltasAVisitantes = 0;
		for (int i = 0; i < visitantes.size(); i++) {
			c = visitantes.elementAt(i);
			double agresividad = c.getAgresividad();
			agresividad /= 10;
			prob = r.nextInt(100);
			int amarillas = 0;
			while (prob < agresividad*probabilidadBase) {
				faltasALocales++;
				c.incrementaFaltasRealizadas();
				if (prob < (agresividad*probabilidadBase/3)) {
					amarillas++;
					c.incrementaTarjetasAmarillas();
				}
				else if (prob < (agresividad*probabilidadBase/2)) {
					c.incrementaTarjetasRojas();
					break;
				}
				if (amarillas == 2) {
					break;
				}
				prob = r.nextInt(100);
			}
		}
		for (int i = 0; i < locales.size(); i++) {
			c = locales.elementAt(i);
			double agresividad = c.getAgresividad();
			agresividad /= 10;
			prob = r.nextInt(100);
			int amarillas = 0;
			while (prob < agresividad*probabilidadBase) {
				faltasAVisitantes++;
				c.incrementaFaltasRealizadas();
				if (prob < (agresividad*probabilidadBase/3)) {
					amarillas++;
					c.incrementaTarjetasAmarillas();
				}
				else if (prob < (agresividad*probabilidadBase/2)) {
					c.incrementaTarjetasRojas();
					break;
				}
				if (amarillas == 2) {
					break;
				}
				prob = r.nextInt(100);
			}
		}
		int receptor;
		for (int i = 0; i < faltasALocales; i++) {
			receptor = r.nextInt(locales.size()-1);
			receptor++;
			for (int j = 0; j < locales.size(); j++) {
				c = locales.elementAt(j);
				if (c.getPosicion() == receptor) {
					int agresividad = c.getAgresividad();
					int aleat = r.nextInt(100);
					c.incrementaFaltasRecibidas();
					if (aleat < (agresividad/4)) {
						c.setLesion(aleat);
					}
					break;
				}
			}
		}
		for (int i = 0; i < faltasAVisitantes; i++) {
			receptor = r.nextInt(visitantes.size()-1);
			receptor++;
			for (int j = 0; j < visitantes.size(); j++) {
				c = visitantes.elementAt(j);
				if (c.getPosicion() == receptor) {
					int agresividad = c.getAgresividad();
					int aleat = r.nextInt(100);
					c.incrementaFaltasRecibidas();
					if (aleat < (agresividad/4)) {
						c.setLesion(aleat);
					}
					break;
				}
			}
		}
	}
	
	private void asignaGoles(Vector<Convocado> visitantes, Vector<Convocado> locales) {
		int probabilidadBase = 15; //15%
		Random r = new Random();
		int prob;
		int n = 0;
		Convocado c;
		for (int i = visitantes.size()-1; i >= 0 && n < 3; i--) {
			n++;
			c = visitantes.elementAt(i);
			double remate = c.getRemate();
			remate /= 30;
			prob = r.nextInt(100);
			while (prob < remate*probabilidadBase) {
				c.incrementaGolesMarcados();
				part.setGolesVisitante(part.getGolesVisitante()+1);
				prob = r.nextInt(100);
				for (int j = 0; j < locales.size(); ++j) {
  					if (locales.elementAt(j).getPosicion() == 0) {
  						locales.elementAt(j).incrementaGolesRecibidos();
  						break;
  					}
  				}
			}
		}
		n = 0;
		for (int i = locales.size()-1; i >= 0 && n < 3; i--) {
			n++;
			c = locales.elementAt(i);
			double remate = c.getRemate();
			remate /= 30;
			prob = r.nextInt(100);
			while (prob < remate*probabilidadBase) {
				c.incrementaGolesMarcados();
				part.setGolesLocal(part.getGolesLocal()+1);
				prob = r.nextInt(100);
				for (int j = 0; j < visitantes.size(); ++j) {
  					if (visitantes.elementAt(j).getPosicion() == 0) {
  						visitantes.elementAt(j).incrementaGolesRecibidos();
  						break;
  					}
  				}
			}
		}
	}
	
	private void mostrarEventos() {
		int eventoArb = Arbitraje.getInstancia().getEvento();
		if (evento == 0) {
			evento = eventoArb;
			if (eventoArb != 0) {
				switch (eventoArb) {
				case Arbitraje.INICIO_PARTIDO: 
					CtrlPresentacion.getInstancia().setEvento(CtrlPresentacion.INICIO_PARTIDO);
					break;
				case Arbitraje.INICIO_SEGUNDA_PARTE: 
					CtrlPresentacion.getInstancia().setEvento(CtrlPresentacion.INICIO_SEGUNDA_PARTE);
					break;
				case Arbitraje.GOL_VISITANTE: 
					CtrlPresentacion.getInstancia().setEvento(CtrlPresentacion.GOL_VISITANTE);
					break;
				case Arbitraje.GOL_LOCAL: 
					CtrlPresentacion.getInstancia().setEvento(CtrlPresentacion.GOL_LOCAL);
					break;
				case Arbitraje.FALTA_NORMAL: 
					CtrlPresentacion.getInstancia().setEvento(CtrlPresentacion.FALTA_NORMAL);
					break;
				case Arbitraje.FALTA_AMARILLA: 
					CtrlPresentacion.getInstancia().setEvento(CtrlPresentacion.FALTA_AMARILLA);
					break;
				case Arbitraje.FALTA_ROJA: 
					CtrlPresentacion.getInstancia().setEvento(CtrlPresentacion.FALTA_ROJA);
					break;
				case Arbitraje.PENALTI: 
					CtrlPresentacion.getInstancia().setEvento(CtrlPresentacion.PENALTI);
					break;
				case Arbitraje.FUERA_DE_JUEGO: 
					CtrlPresentacion.getInstancia().setEvento(CtrlPresentacion.FUERA_DE_JUEGO);
					break;
				case Arbitraje.FUERA: 
				case Arbitraje.FUERA_PORTERIA:
					CtrlPresentacion.getInstancia().setEvento(CtrlPresentacion.FUERA);
					break;
				default: break;
				}
			}
		}
		else {
			evento = eventoArb;
		}
	}
	
	public Vector<Convocado> getCovocadosLocales(){
		return part.getLocales();
	}
	public Vector<Convocado> getCovocadosVisitante(){
		return part.getVisitantes();
	}
	public static CtrlSimulacion getInstancia() {
		if(instancia == null) {
			instancia = new CtrlSimulacion();
		}
		return instancia;
	}
		
	public Integer getGolesMarcadosEquipoSimulacion(Integer x) {
		
		if (x == 0) {
			return part.getEquipoLocal().getGolesMarcados();
		}
		else {
			return part.getEquipoVisitante().getGolesMarcados();
		}
		
	}
	
	public Integer getGolesRecibidosEquipoSimulacion(Integer x) {
		if (x == 0) {
			return part.getEquipoLocal().getGolesRecibidos();
		}
		else {
			return part.getEquipoVisitante().getGolesRecibidos();
		}
	}

	public Integer getTarjetasAmarillasEquipoSimulacion(Integer x) {
		if (x == 0) {
			return part.getEquipoLocal().getTarjetasAmarillas();
		}
		else {
			return part.getEquipoVisitante().getTarjetasAmarillas();
		}
	}
	
	public Integer getTarjetasRojasEquipoSimulacion(Integer x) {
		if (x == 0) {
			return part.getEquipoLocal().getTarjetasRojas();
		}
		else {
			return part.getEquipoVisitante().getTarjetasRojas();
		}
	}
	
	public Integer getGolesMarcadosJugadorSimulacion(Integer x, Integer y) {
		if (x == 0) {
			Integer dorsal = part.getEquipoLocal().getDorsal(y);
			return part.getEquipoLocal().getGolesMarcados(dorsal - 1);
		}
		else {
			Integer dorsal = part.getEquipoVisitante().getDorsal(y);
			return part.getEquipoVisitante().getGolesMarcados(dorsal - 1);
		}
	}
	
	public Integer getGolesRecibidosJugadorSimulacion(Integer x, Integer y) {
		if (x == 0) {
			Integer dorsal = part.getEquipoLocal().getDorsal(y);
			return part.getEquipoLocal().getGolesRecibidos(dorsal - 1);
		}
		else {
			Integer dorsal = part.getEquipoVisitante().getDorsal(y);
			return part.getEquipoVisitante().getGolesRecibidos(dorsal - 1);
		}
	}

	public Integer getTarjetasAmarillasJugadorSimulacion(Integer x, Integer y) {
		if (x == 0) {
			Integer dorsal = part.getEquipoLocal().getDorsal(y);
			return part.getEquipoLocal().getTarjetasAmarillas(dorsal - 1);
		}
		else {
			Integer dorsal = part.getEquipoVisitante().getDorsal(y);
			return part.getEquipoVisitante().getTarjetasAmarillas(dorsal - 1);
		}
	}
	
	public Integer getTarjetasRojasJugadorSimulacion(Integer x, Integer y) {
		if (x == 0) {
			Integer dorsal = part.getEquipoLocal().getDorsal(y);
			return part.getEquipoLocal().getTarjetasRojas(dorsal - 1);
		}
		else {
			Integer dorsal = part.getEquipoVisitante().getDorsal(y);
			return part.getEquipoVisitante().getTarjetasRojas(dorsal - 1);
		}
	}
	
	
	public Integer getPartidosGanados(Integer x) {
		if (x == 0) {
			return part.getEquipoLocal().getPartidosGanados();
		}
		else {
			return part.getEquipoVisitante().getPartidosGanados();
		}
	}

	public Integer getPartidosEmpatados(Integer x) {
		if (x == 0) {
			return part.getEquipoLocal().getPartidosEmpatados();
		}
		else {
			return part.getEquipoVisitante().getPartidosEmpatados();
		}
	}
	
	public Integer getPartidosPerdidos(Integer x) {
		if (x == 0) {
			return part.getEquipoLocal().getPartidosPerdidos();
		}
		else {
			return part.getEquipoVisitante().getPartidosPerdidos();
		}
	}
	
	public Integer getPasesBuenos(Integer x, Integer y) {
		if (x == 0) {
			Integer dorsal = part.getEquipoLocal().getDorsal(y);
			return part.getEquipoLocal().getPasesBuenos(dorsal - 1);
		}
		else {
			Integer dorsal = part.getEquipoVisitante().getDorsal(y);
			return part.getEquipoVisitante().getPasesBuenos(dorsal - 1);
		}
	}
	
	public Integer getPartidosJugados(Integer x, Integer y) {
		if (x == 0) {
			Integer dorsal = part.getEquipoLocal().getDorsal(y);
			return part.getEquipoLocal().getPartidosJugados(dorsal - 1);
		}
		else {
			Integer dorsal = part.getEquipoVisitante().getDorsal(y);
			return part.getEquipoVisitante().getPartidosJugados(dorsal - 1);
		}
	}
	
	
	public Integer getPasesRealizados(Integer x, Integer y) {
		if (x == 0) {
			Integer dorsal = part.getEquipoLocal().getDorsal(y);
			return part.getEquipoLocal().getPasesRealizados(dorsal - 1);
		}
		else {
			Integer dorsal = part.getEquipoVisitante().getDorsal(y);
			return part.getEquipoVisitante().getPasesRealizados(dorsal - 1);
		}
	}
	
	public Integer getFaltasCometidas(Integer x, Integer y) {
		if (x == 0) {
			Integer dorsal = part.getEquipoLocal().getDorsal(y);
			return part.getEquipoLocal().getFaltasRealizadas(dorsal - 1);
		}
		else {
			Integer dorsal = part.getEquipoVisitante().getDorsal(y);
			return part.getEquipoVisitante().getFaltasRealizadas(dorsal - 1);
		}
	}
	
	public Integer getFaltasRecibidas(Integer x, Integer y) {
		if (x == 0) {
			Integer dorsal = part.getEquipoLocal().getDorsal(y);
			return part.getEquipoLocal().getFaltasRecibidas(dorsal - 1);
		}
		else {
			Integer dorsal = part.getEquipoVisitante().getDorsal(y);
			return part.getEquipoVisitante().getFaltasRecibidas(dorsal - 1);
		}
	}
	
	public Float getPorcentajePasesBuenos(Integer x, Integer y) {
		if (x == 0) {
			Integer dorsal = part.getEquipoLocal().getDorsal(y);
			return part.getEquipoLocal().getPorcentajePasesBuenos(dorsal - 1);
		}
		else {
			Integer dorsal = part.getEquipoVisitante().getDorsal(y);
			return part.getEquipoVisitante().getPorcentajePasesBuenos(dorsal - 1);
		}
	}
	
	public String[] getEquiposSimulacion() {
		if (jugando == 1) return part.getEquipos();
		String[] s = new String[1];
		s[0] = "";
		return s;
	}
	
	public String[] getJugadoresSimulacion(Integer x) {
		if (jugando == 1) return part.getJugadores(x);
		String[] s = new String[1];
		s[0] = "";
		return s;
	}
	
	
	public void setAlineacion1(Integer i) {
		alineacion1 = i;
	}
	
	public Integer getAlineacion1() {
		return 	alineacion1;
	}
	
	
	public void setAlineacion2(Integer i) {
		alineacion2 = i;
	}
	
	public Integer getAlineacion2() {
		return alineacion2;
	}


	
	public String quienOcupaPosicion(Integer n) {//dada una posicion retorna quien la ocupa
		
		Vector<Convocado> aux = null;
		Vector<Convocado> auxr = null;
		HashMap<Integer, Integer> hash = null;
		if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 3) {
			aux = part.getLocales();
			auxr = part.getLocalesReserva();
			hash = posLocales;
		}
		else if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 4) {
			aux = part.getVisitantes();
			auxr = part.getVisitantesReserva();
			hash = posVisitantes;
		}
		
		for (int i = 0; i < aux.size(); i++) {
			if (hash.get(aux.get(i).getDorsal()) == n) return aux.get(i).getNombre();
		}
		for (int i = 0; i < auxr.size(); i++) {
			if (hash.get(auxr.get(i).getDorsal()) == n) return auxr.get(i).getNombre();
		}
		
		
		return "Nadie";
	}
	
	public void unset(Integer index) {
		Vector<Convocado> aux = null;
		HashMap<Integer, Integer> hash = null;
		
		if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 3) {
			hash = posLocales;
			if (index < 0) {
				aux = part.getLocalesReserva();
			}
			else {
				aux = part.getLocales();
			}
		}
		else if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 4) {
			hash = posVisitantes;
			if (index < 0) {
				aux = part.getVisitantesReserva();
			}
			else {
				aux = part.getVisitantes();
			}
		}
		
		boolean trobat = false;
		for (int i = 0; i < aux.size() && !trobat; i++) {
				if (hash.get(aux.get(i).getDorsal()) == index) {
					hash.remove(aux.get(i).getDorsal());
					trobat = true;
				}
		}


		
	}
	
	public Integer getPosicion(Integer index) {
		Vector<Convocado> aux = null;
		HashMap<Integer, Integer> hash = null;
		Integer dorsal; 
		
		if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 3) {
			hash = posLocales;
			if (index >= part.getLocales().size()) {
				index -= part.getLocales().size();
				aux = part.getLocalesReserva();
			} else {
				aux = part.getLocales();
			}
		}
		else if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 4) {
			hash = posVisitantes;
			if (index >= part.getVisitantes().size()) {
				index -= part.getVisitantes().size();
				aux = part.getVisitantesReserva();
			} else {
				aux = part.getVisitantes();
			}
		}
		
		if (index < 0) return -6;
		dorsal = aux.get(index).getDorsal();
		if (!hash.containsKey(dorsal)) return -6;
		else return hash.get(dorsal);
	}
	
	public void setPosicion(Integer index, Integer pos) {
		Vector<Convocado> aux = null;
		HashMap<Integer, Integer> hash = null;
		Integer dorsal;
		
		if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 3) {
			hash = posLocales;
			if (index >= part.getLocales().size()) {
				index -= part.getLocales().size();
				aux = part.getLocalesReserva();
			} else {
				aux = part.getLocales();
			}
		}
		else if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 4) {
			hash = posVisitantes;
			if (index >= part.getVisitantes().size()) {
				index -= part.getVisitantes().size();
				aux = part.getVisitantesReserva();
			} else {
				aux = part.getVisitantes();
			}
		}
		
		dorsal = aux.get(index).getDorsal();
		hash.put(dorsal, pos);
		
	}
	
	public void setPosicionNombre(String nombre, Integer pos) {
		Vector<Convocado> aux = null;
		Vector<Convocado> auxr = null;
		HashMap<Integer, Integer> hash = null;
		Integer dorsal = 0;
		
		if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 3) {
			hash = posLocales;
			auxr = part.getLocalesReserva();
			aux = part.getLocales();
		}
		else if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 4) {
			hash = posVisitantes;
			auxr = part.getVisitantesReserva();
			aux = part.getVisitantes();
		}
		
		boolean trobat = false;
		for (int i = 0; i < aux.size() && !trobat; ++i) {
			if (aux.get(i).getNombre() == nombre) {
				dorsal = aux.get(i).getDorsal();
				trobat = true;
			}
		}
		for (int i = 0; i < auxr.size() && !trobat; ++i) {
			if (auxr.get(i).getNombre() == nombre) {
				dorsal = auxr.get(i).getDorsal();
				break;
			}
		}

		hash.put(dorsal, pos);
		
	}

	
	public String[] getNombresEq1() {
		Vector<Convocado> conv = part.getLocales();
		Vector<Convocado> res = part.getLocalesReserva();
		String[] s = new String[conv.size() + res.size()];
		for (int i = 0; i < conv.size(); ++i) s[i] = conv.get(i).getNombre();
		for (int i = 0; i < res.size(); ++i) s[conv.size() + i] = res.get(i).getNombre();
		return s;
	}
	
	public String[] getNombresEq2() {
		Vector<Convocado> conv = part.getVisitantes();
		Vector<Convocado> res = part.getVisitantesReserva();
		String[] s = new String[conv.size() + res.size()];
		for (int i = 0; i < conv.size(); ++i) s[i] = conv.get(i).getNombre();
		for (int i = 0; i < res.size(); ++i) s[conv.size() + i] = res.get(i).getNombre();
		return s;
	}
	
	public String[] getReservasEq1() {
		Vector<Convocado> res = part.getLocalesReserva();
		String[] s = new String[res.size()];
		for (int i = 0; i < res.size(); ++i) {
			s[i] = res.get(i).getNombre();
		}
		return s;
	}
	
	public String[] getReservasEq2() {
		Vector<Convocado> res = part.getVisitantesReserva();
		String[] s = new String[res.size()];
		for (int i = 0; i < res.size(); ++i) {
			s[i] = res.get(i).getNombre();
		}
		return s;
	}
	
	
	public String getEquipo1() {
		return part.getEquipoLocal().getNombre();
	}
	
	public String getEquipo2() {
		return part.getEquipoVisitante().getNombre();
	}
	
	public void iniciaCambios() {
		if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 3)	cambiosRestantes = part.getCambiosLocal();
		else cambiosRestantes = part.getCambiosVisitante();
		posLocales = new HashMap<Integer, Integer>();
		posVisitantes = new HashMap<Integer, Integer>();
		cambios = new HashMap<Integer, Integer>();
		
		alineacion1 = part.getAlineacionLocal();
		alineacion2 = part.getAlineacionVisitante();
		
		for (int i = 0; i < part.getLocales().size(); ++i) {
			posLocales.put(part.getLocales().get(i).getDorsal(), part.getLocales().get(i).getPosicion());
		}
		for (int i = 0; i < part.getLocalesReserva().size(); ++i) {
			posLocales.put(part.getLocalesReserva().get(i).getDorsal(), part.getLocalesReserva().get(i).getPosicion());
		}
		
		for (int i = 0; i < part.getVisitantes().size(); ++i) {
			posVisitantes.put(part.getVisitantes().get(i).getDorsal(), part.getVisitantes().get(i).getPosicion());
		}
		for (int i = 0; i < part.getVisitantesReserva().size(); ++i) {
			posVisitantes.put(part.getVisitantesReserva().get(i).getDorsal(), part.getVisitantesReserva().get(i).getPosicion());
		}
		
	}
	
	public Integer getAtributoNormal(Integer n, Integer index) {
		Vector<Convocado> aux = null;

		if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 3) {
			if (index >= part.getLocales().size()) {
				index -= part.getLocales().size();
				aux = part.getLocalesReserva();
			} else {
				aux = part.getLocales();
			}
		}
		else if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 4) {
			if (index >= part.getVisitantes().size()) {
				index -= part.getVisitantes().size();
				aux = part.getVisitantesReserva();
			} else {
				aux = part.getVisitantes();
			}
		}
		
		switch (n) {
			case CtrlPresentacion.DORSAL:
				return aux.get(index).getDorsal();
			case CtrlPresentacion.TIPO:
				return aux.get(index).getTipoFutbolista();
			case CtrlPresentacion.AGRESIVIDAD:
				return aux.get(index).getAgresividad();
			case CtrlPresentacion.REGATE:
				return aux.get(index).getRegate();
			case CtrlPresentacion.VELOCIDAD:
				return aux.get(index).getVelocidad();
			case CtrlPresentacion.RESISTENCIA:
				return aux.get(index).getResistencia();
			case CtrlPresentacion.REMATE:
				return aux.get(index).getRemate();
			case CtrlPresentacion.PARADA:
				return aux.get(index).getParada();
			case CtrlPresentacion.PASE:
				return aux.get(index).getPase();
			case CtrlPresentacion.PESO:
				return aux.get(index).getPeso();
			default:
				return -1;
		}
		
		
	}
	
	
	public Integer indexReal(Integer index) {
		if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 3) {
			if (index >= part.getLocales().size()) {
				index -= part.getLocales().size();
			}
		}
		else if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 4) {
			if (index >= part.getVisitantes().size()) {
				index -= part.getVisitantes().size();
			}
		}
		
		return index;
	}
	
	public double getAlturaNormal(Integer index) {
		Vector<Convocado> aux = null;
		if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 3) {
			if (index >= part.getLocales().size()) {
				index -= part.getLocales().size();
				aux = part.getLocalesReserva();
			} else {
				aux = part.getLocales();
			}
		}
		else if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 4) {
			if (index >= part.getVisitantes().size()) {
				index -= part.getVisitantes().size();
				aux = part.getVisitantesReserva();
			} else {
				aux = part.getVisitantes();
			}
		}
		
		return aux.get(index).getAltura();
	}
	
	public Integer getAtributoReserva(Integer n, Integer index) {
		Vector<Convocado> aux = null;
		if (CtrlPresentacion.getInstancia().getQuienCambia() == 0) aux = part.getLocalesReserva();
		else aux = part.getVisitantesReserva();
		
		switch (n) {
			case CtrlPresentacion.DORSAL:
				return aux.get(index).getDorsal();
			case CtrlPresentacion.TIPO:
				return aux.get(index).getTipoFutbolista();
			case CtrlPresentacion.AGRESIVIDAD:
				return aux.get(index).getAgresividad();
			case CtrlPresentacion.REGATE:
				return aux.get(index).getRegate();
			case CtrlPresentacion.VELOCIDAD:
				return aux.get(index).getVelocidad();
			case CtrlPresentacion.RESISTENCIA:
				return aux.get(index).getResistencia();
			case CtrlPresentacion.REMATE:
				return aux.get(index).getRemate();
			case CtrlPresentacion.PARADA:
				return aux.get(index).getParada();
			case CtrlPresentacion.PASE:
				return aux.get(index).getPase();
			default:
				return -1;
		}
		
		
	}
	
	
	public Integer numeroCambios() {
		Vector<Convocado> aux = null;
		Vector<Convocado> auxr = null;
		HashMap<Integer, Integer> hash = null;
		
		if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 3) {
			hash = posLocales;
			auxr = part.getLocalesReserva();
			aux = part.getLocales();
		}
		
		else if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 4) {
			hash = posVisitantes;
			auxr = part.getVisitantesReserva();
			aux = part.getVisitantes();
		}
		
		//pintaConvocadosHash();
		
		int w = 0;
		int veces = 0;
		for (w = 0; w < auxr.size(); ++w) {
			if (hash.get(auxr.get(w).getDorsal()) >= 0) {
				for (int j = 0; j < aux.size(); j++) {
					if (hash.get(aux.get(j).getDorsal()) < 0) {
						veces++;
						break;
					}
				}
			}
		}
		
		return veces + 3 - cambiosRestantes;
	}
	
	
	public void aceptaCambioLesionado(Integer n) {
		
		int equipo = 0;
		Vector<Convocado> aux = null;
		int a = CtrlPresentacion.getInstancia().getQuienCambia();
		if (a == 0) {
			equipo = CtrlPresentacion.LOCAL;
			aux = part.getLocalesReserva();
		}else {
			equipo = CtrlPresentacion.VISITANTE;
			aux = part.getVisitantesReserva();
		}
		this.cambiaFutbolistaLesionado(aux.get(n), equipo);
	}

	
		public void reanudaPartido() {
			Vector<Convocado> aux = null;
			Vector<Convocado> auxr = null;
			HashMap<Integer, Integer> hash = null;
			Integer alineacion = 0;
			Integer equipo = 0;
			if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 3) {
				hash = posLocales;
				auxr = part.getLocalesReserva();
				aux = part.getLocales();
				equipo = CtrlPresentacion.LOCAL;
				alineacion = alineacion1;
			}
			
			else if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 4) {
				hash = posVisitantes;
				auxr = part.getVisitantesReserva();
				aux = part.getVisitantes();
				equipo = CtrlPresentacion.VISITANTE;
				alineacion = alineacion2;
			}
			
			
			boolean trobat = false;
			int w = 0;
			while (!trobat){
				if (hash.get(auxr.get(w).getDorsal()) >= 0) {
					for (int j = 0; j < aux.size(); j++) {
						if (hash.get(aux.get(j).getDorsal()) < 0) {
							cambiaFutbolistaFresco(aux.get(j), auxr.get(w), equipo);
							w = -1;
							cambiosRestantes--;
							break;
						}
					}
				}
				w++;
				if (w == auxr.size()) trobat = true;
			}
			
			for (int i = 0; i < aux.size(); i++) {
				aux.get(i).setPosicion(hash.get(aux.get(i).getDorsal()));
				IA.getInstancia().definirZonaDeCampo(aux.get(i), aux.get(i).getCampo(), alineacion);
			}
			
			for (int i = 0; i < auxr.size(); i++) {
				auxr.get(i).setPosicion(hash.get(auxr.get(i).getDorsal()));
				IA.getInstancia().definirZonaDeCampo(auxr.get(i), auxr.get(i).getCampo(), alineacion);
			}
			if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 3) {
				part.setCambiosLocal(cambiosRestantes);
			}
			
			else if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 4) {
				part.setCambiosVisitante(cambiosRestantes);
			}

			part.setAlineacionLocal(alineacion1);
			part.setAlineacionVisitante(alineacion2);
			
		}
		
		public void pintaConvocadosHash() {
			Vector<Convocado> aux = null;
			Vector<Convocado> auxr = null;
			HashMap<Integer, Integer> hash = null;
			if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 3) {
				aux = part.getLocales();
				auxr = part.getLocalesReserva();
				hash = posLocales;
			}
			else if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 4) {
				aux = part.getVisitantes();
				auxr = part.getVisitantesReserva();
				hash = posVisitantes;
			}
			
			for (int i = 0; i < aux.size(); i++) {
				System.out.print(" " + aux.get(i).getNombre());
				if (hash.containsKey(aux.get(i).getDorsal())) {
					System.out.print(" / " + hash.get(aux.get(i).getDorsal()));
				}
				else {
					System.out.print(" / NaN");
				}
			}
			System.out.print('\n');
			for (int i = 0; i < auxr.size(); i++) {
				System.out.print(" " + auxr.get(i).getNombre());
				if (hash.containsKey(auxr.get(i).getDorsal())) {
					System.out.print(" / " + hash.get(auxr.get(i).getDorsal()));
				}
				else {
					System.out.print(" / NaN");
				}
			}
			System.out.print('\n');
		}
		
		public void pintaConvocadosVector() {
			Vector<Convocado> aux = null;
			Vector<Convocado> auxr = null;
			if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 3) {
				aux = part.getLocales();
				auxr = part.getLocalesReserva();
			}
			else if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 4) {
				aux = part.getVisitantes();
				auxr = part.getVisitantesReserva();
			}
			
			for (int i = 0; i < aux.size(); i++) {
				System.out.print(" " + aux.get(i).getNombre() + " / " + aux.get(i).getPosicion());
			}
			System.out.print('\n');
			for (int i = 0; i < auxr.size(); i++) {
				System.out.print(" " + auxr.get(i).getNombre() + " / " + auxr.get(i).getPosicion());
			}
			System.out.print('\n');
		}
		
		public void ponerCambio(Integer posicionPorDejar, Integer posicionPorOcupar) {
			Integer dorsal1 = 0;
			Integer dorsal2 = 0;
			Vector<Convocado> aux = null;
			Vector<Convocado> auxr = null;
			HashMap<Integer, Integer> hash = null;
			if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 3) {
				aux = part.getLocales();
				auxr = part.getLocalesReserva();
				hash = posLocales;
			}
			else if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 4) {
				aux = part.getVisitantes();
				auxr = part.getVisitantesReserva();
				hash = posVisitantes;
			}

			for (int i = 0; i < aux.size(); i++) {
				if (hash.containsKey(aux.get(i).getDorsal())) {
					if (hash.get(aux.get(i).getDorsal()) == posicionPorOcupar) dorsal2 = aux.get(i).getDorsal();
				}
			}
			
			for (int i = 0; i < auxr.size(); i++) {
				if (hash.containsKey(auxr.get(i).getDorsal())) {
					if (hash.get(auxr.get(i).getDorsal()) == posicionPorDejar) dorsal1 = auxr.get(i).getDorsal();
				}
			}
			
			cambiosRestantes--;
			cambios.put(dorsal1, dorsal2);
		}
		
		public Integer getCambiosRestantes() {
			return cambiosRestantes;
		}
		
		public String getNombreEquipo(Integer i) {
			if (i == 0) return part.getEquipoLocal().getNombre();
			else if (i == 1) return part.getEquipoVisitante().getNombre();
			else return "Error";
		}

	}


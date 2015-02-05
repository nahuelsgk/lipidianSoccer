/*
 * Alberto Moreno Vega
 */


package domain;

import java.util.Random;
import java.util.Vector;

public class Arbitraje {
	
	private static Arbitraje instancia;
	
	
	public static final int FUERA = 1;
	public static final int GOL_VISITANTE = 2;
	public static final int GOL_LOCAL = 3;
	public static final int AREA_DERECHA = 4;
	public static final int AREA_IZQUIERDA = 5;
	public static final int CAMPO = 6;
	public static final int INICIO_PARTIDO = 7;
	public static final int FALTA_NORMAL = 8;
	public static final int FALTA_AMARILLA = 9;
	public static final int FALTA_ROJA = 10;
	public static final int ESPERANDO = 11; //la ia esta esperando a terminar de posicionar jugadores
	public static final int EN_POSICION = 12; //la ia ha posicionado todos sus jugadores
	public static final int SAQUE_CENTRO = 13;
	public static final int INICIO_SEGUNDA_PARTE = 14;
	public static final int FUERA_PORTERIA_DERECHA = 15;
	public static final int FUERA_PORTERIA_IZQUIERDA = 16;
	public static final int SAQUE_BANDA = 17;
	public static final int SAQUE_ESQUINA = 18;
	public static final int FUERA_PORTERIA = 19;
	public static final int FUERA_ESQUINA = 20;
	public static final int PENALTI = 21;
	public static final int SAQUE_PENALTI = 22;
	public static final int FUERA_DE_JUEGO = 23;
	
	private int evento; 
	private int temporizador;
	private XYZ retorno;
	private Partido part;
	
	
  	public static Arbitraje getInstancia() {
  		if(instancia == null) {
			instancia = new Arbitraje();
		}
	return instancia;
  	}
  	
  	
  	public void comenzarPartido() {
  		
  		evento = INICIO_PARTIDO;
  		IA.getInstancia().setEvento(evento);
  		temporizador = 0; 
  		part.getPelota().setXYZ(500, 350, 0);
  		retorno = part.getPelota().getXYZ();
  		part.getPelota().resetearFuerzas();
  	
  	}
  	
  	public void comenzarSegundaParte() {
  		evento = INICIO_SEGUNDA_PARTE;
  		IA.getInstancia().setEvento(evento);
  		temporizador = 0; 
  		part.getPelota().setXYZ(500, 350, 0);
  		retorno = part.getPelota().getXYZ();
  		part.getPelota().resetearFuerzas();
  		part.getPelota().resetearPosesion();
  	}
  	
  	private void comprobacionesDeTiempo() {
  		double tiempoMostrado = part.getTiempoMostrado()+0.25;
  		double tiempoJugado = part.getTiempoJugado();
  		double tiempoTotal = part.getTiempoTotal();
  		part.setTiempoMostrado(tiempoMostrado);
  		if (evento == 0) {
  			tiempoJugado += 0.25;
  			part.setTiempoJugado(tiempoJugado);
  			
  		}
  	
  		if (tiempoJugado >= tiempoTotal) {
  			finalPartido();
  		}
  		else if (tiempoJugado == (tiempoTotal/2)) {
  			finalPrimeraParte();
  		}
  		else if (((tiempoMostrado-300) > (tiempoTotal/2)) && (tiempoJugado < (tiempoTotal/2))) {
  			finalPrimeraParte(); //si no hay errores no deberia entrar
  		}
  		else if (((tiempoMostrado-300) > tiempoTotal) && (tiempoJugado < tiempoTotal)) {
  			finalPartido();
  		}
  		if ((tiempoJugado < tiempoTotal) && (tiempoMostrado-180) > tiempoJugado) { //si hay mas de 3 mins de diferencia
  			if (((tiempoMostrado-120) > (tiempoTotal/2)) && (tiempoJugado <= (tiempoTotal/2))) { //estamos al final de la 1� parte
  				tiempoJugado = (tiempoTotal/2)-0.25; //no debe acabarse el tiempo en mitad de un evento
  			}
  			else if (((tiempoMostrado-120) > tiempoTotal) && (tiempoJugado <= tiempoTotal)) {//estamos al final de la 2� parte
  				tiempoJugado = tiempoTotal-0.25;
  			}
  			else {
  				tiempoJugado = tiempoMostrado-120;
  			}
  			part.setTiempoJugado(tiempoJugado);
  		}
  	}
  	
  	public void finalPartido() {
  		actualizarEstadisticas();
  		part.setFinal();
  		
  	}
  	
  	private void finalPrimeraParte() {
  		part.iniciarSegundaParte();
  	}
  	
  	public void compruebaNormativa() {
  		if (evento != INICIO_PARTIDO && evento != INICIO_SEGUNDA_PARTE) {
  			comprobacionesDeTiempo();
  		}
  		compruebaLesionadosSancionados();
  		if (evento == 0) { //activacion de eventos
  			//al recorrer todos los convocados mirar si hay alguno lesionado
  			XYZ pel = new XYZ(part.getPelota().getX(), part.getPelota().getY(), part.getPelota().getZ());
  			int zona = zonaCampo(pel);
  			switch (zona) {
  			case FUERA: 
  				evento = FUERA;
  				temporizador = 160; //160 iteraciones antes de recolocar la pelota en su sitio, son 4 segundos
  				if (pel.getY() > 700) pel.setY(699);
  				if (pel.getY() < 0) pel.setY(1);
  				pel.setX(pel.getX());
  				
  				IA.getInstancia().setEvento(evento);
  				break;
  			case FUERA_PORTERIA_DERECHA:
  				evento = FUERA_PORTERIA;
  				temporizador = 160; //160 iteraciones antes de recolocar la pelota en su sitio, son 4 segundos
  				if (part.getPelota().getConvocado().getCampo() == Convocado.CAMPO_IZQUIERDO) {
  					//si el que ha sacado fuera la pelota es del campo izquierdo, saque de portero
  					pel.setX(980);
  					pel.setY(350);
  					IA.getInstancia().setEvento(FUERA_PORTERIA);
  				}
  				else {
  					pel.setX(1000);
  					if (part.getPelota().getY() > 350) {
  						pel.setY(700);
  					}
  					else {
  						pel.setY(0);
  					}
  					IA.getInstancia().setEvento(FUERA_ESQUINA);
  				}
  				
  				
  				break;
  			case FUERA_PORTERIA_IZQUIERDA:
  				evento = FUERA_PORTERIA;
  				temporizador = 160; //160 iteraciones antes de recolocar la pelota en su sitio, son 4 segundos
  				if (part.getPelota().getConvocado().getCampo() == Convocado.CAMPO_DERECHO) {
  					//si el que ha sacado fuera la pelota es del campo derecho, saque de portero
  					pel.setX(20);
  					pel.setY(350);
  					IA.getInstancia().setEvento(FUERA_PORTERIA);
  				}
  				else {
  					pel.setX(0);
  					if (part.getPelota().getY() > 350) {
  						pel.setY(700);
  					}
  					else {
  						pel.setY(0);
  					}
  					IA.getInstancia().setEvento(FUERA_ESQUINA);
  				}
  				
  				break;
  			case GOL_VISITANTE:
  				evento = GOL_VISITANTE;
  				temporizador = 160;
  				//incrementar contador de goles visitante, alinear visitantes correctamente
  				part.getPelota().getConvocado().incrementaGolesMarcados(); 
  				Vector<Convocado> locales = part.getLocales();
  				for (int i = 0; i < locales.size(); ++i) {
  					if (locales.elementAt(i).getPosicion() == 0) {
  						locales.elementAt(i).incrementaGolesRecibidos();
  						break;
  					}
  				}
  				part.setGolesVisitante(part.getGolesVisitante()+1);
  				pel.setX(500);
  				pel.setY(350);
  				IA.getInstancia().setEvento(evento);
  				break;
  			case GOL_LOCAL:
  				evento = GOL_LOCAL;
  				temporizador = 160;
  				//incrementar contador de goles locales, alinear locales correctamente
  				part.getPelota().getConvocado().incrementaGolesMarcados();
  				Vector<Convocado> visitantes = part.getVisitantes();
  				for (int i = 0; i < visitantes.size(); ++i) {
  					if (visitantes.elementAt(i).getPosicion() == 0) {
  						visitantes.elementAt(i).incrementaGolesRecibidos();
  						break;
  					}
  				}
  				part.setGolesLocal(part.getGolesLocal()+1);
  				pel.setX(500);
  				pel.setY(350);
  				IA.getInstancia().setEvento(evento);
  				break;
  			default: 
  				evento = 0;
  				break;
  			}
  			retorno = pel;
  		}
  		else {
  			switch (evento) {
  			case INICIO_PARTIDO: coordinarIA(SAQUE_CENTRO); break;
  			case INICIO_SEGUNDA_PARTE: coordinarIA(SAQUE_CENTRO); break;
  			case GOL_VISITANTE: coordinarIA(SAQUE_CENTRO); break;
  			case GOL_LOCAL: coordinarIA(SAQUE_CENTRO); break;
  			case FUERA: coordinarIA(SAQUE_BANDA); break;
  			case FUERA_PORTERIA: coordinarIA(SAQUE_ESQUINA); break;
  			case FALTA_NORMAL: coordinarIA(SAQUE_BANDA); break;  //las reglas del saque de banda y de falta coinciden
  			case FALTA_AMARILLA: coordinarIA(SAQUE_BANDA); break;  //las reglas del saque de banda y de falta coinciden
  			case FALTA_ROJA: coordinarIA(SAQUE_BANDA); break;  //las reglas del saque de banda y de falta coinciden
  			case PENALTI: coordinarIA(SAQUE_PENALTI); break;
  			case FUERA_DE_JUEGO: coordinarIA(SAQUE_BANDA); break;
  			default: evento = 0; break;
  			}
  			if (temporizador != 0) {
  				temporizador--;
  				if (temporizador <= 0) {
  					temporizador = 0;
  					part.getPelota().setXYZ(retorno.getX(), retorno.getY(), 0);
  	  				part.getPelota().resetearFuerzas();
  				}
  			}
  		}	
  	}
  	
  	private void coordinarIA(int siguienteEventoIA) {
  		if (IA.getInstancia().getEvento() == EN_POSICION) {
				if (temporizador > 0) {
					temporizador = 0;
					part.getPelota().setXYZ(retorno.getX(), retorno.getY(), 0);
	  				part.getPelota().resetearFuerzas();
				}
				else {
					IA.getInstancia().setEvento(siguienteEventoIA);
				}
			}
			else if (IA.getInstancia().getEvento() == 0) {
				evento = 0;
			}
  	}
  	
  	
  	public int zonaCampo(XYZ punto) {
  		double x = punto.getX();
  		double y = punto.getY();
  		double z = punto.getZ();
  		if (y > 700 || y < 0) return FUERA;
  		if (x > 1000) {
  			if (y > 313.4 && y < 386.6 && z < 24.4) { //gol marcado en la porteria de la derecha
  				if (part.getTiempoJugado() < (part.getTiempoTotal()/2)) { //si estamos en la primera parte
  					return GOL_VISITANTE;
  				}
  				else return GOL_LOCAL;
  			}
  			else return FUERA_PORTERIA_DERECHA;
  		}
  		if (x < 0) {
  			if (y > 313.4 && y < 386.6 && z < 24.4) {
  				if (part.getTiempoJugado() < (part.getTiempoTotal()/2)) { //si estamos en la primera parte
  					return GOL_LOCAL;
  				}
  				else return GOL_VISITANTE;
  			}
  			else return FUERA_PORTERIA_IZQUIERDA;
  		}
  		if (y > 148.4 && y < 551.6) {
  			if (x > 0 && x < 165) {
  			//if (x > 0 && x < 1000) {
  				return AREA_IZQUIERDA;
  			}
  			if (x > 835 && x < 1000) {
  			//if (x > 0 && x < 1000) {
  				return AREA_DERECHA;
  			}
  		}
  		return CAMPO;
  	}
  	
  	public void setPartido(Partido p){
  		part = p;
  		
  	}
  	
  	public Partido getPartido(Partido p) {
  		return this.part;
  	}
  	
  	void compruebaLesionadosSancionados() {
  		Vector<Convocado> locales = part.getLocales();
  		Vector<Convocado> visitantes = part.getVisitantes();
  		for (int i = 0; i < locales.size(); i++) {
  			if (locales.elementAt(i).getLesion() > 0 || locales.elementAt(i).getTarjetasAmarillas() >= 2) {
  				Convocado c = locales.remove(i);
  				if (part.getPelota().getConvocado() == c) {
  					part.getPelota().resetearPosesion();
  				}
  				if (c.getLesion() > 0) {
  					IA.getInstancia().cambioJugador(c);
  				}
  				part.getLesionadosSancionados().add(c);
  			}
  		}
  		for (int i = 0; i < visitantes.size(); i++) {
  			if (visitantes.elementAt(i).getLesion() > 0 || visitantes.elementAt(i).getTarjetasAmarillas() >= 2) {
  				Convocado c = visitantes.remove(i);
  				if (part.getPelota().getConvocado() == c) {
  					part.getPelota().resetearPosesion();
  				}
  				if (c.getLesion() > 0) {
  					IA.getInstancia().cambioJugador(c);
  				}
  				part.getLesionadosSancionados().add(c);

  			}
  		}
  	}

  	
  	public void actualizarEstadisticas() { //se llama al acabar el partido
  		Vector<Convocado> locales = part.getLocales();
  		Vector<Convocado> visitantes = part.getVisitantes();
  		EquipoFutbol local = part.getEquipoLocal();
  		EquipoFutbol visitante = part.getEquipoVisitante();
  		if (part.getGolesLocal() > part.getGolesVisitante()) { //ganan locales
  			for (int i = 0; i < locales.size(); i++) {
  				locales.elementAt(i).incrementaPartidosJugados(Constantes.PARTIDO_GANADO);
  			}
  			for (int i = 0; i < visitantes.size(); i++) {
  				visitantes.elementAt(i).incrementaPartidosJugados(Constantes.PARTIDO_PERDIDO);
  			}
  			local.setPuntos(local.getPuntos()+3);
  			local.setPartidosGanados(local.getPartidosGanados()+1);
  			visitante.setPartidosPerdidos(visitante.getPartidosPerdidos()+1);
  			local.setPerdidosConsec(0);
  			local.setGanadosConsec(local.getGanadosConsec()+1);
  			visitante.setGanadosConsec(0);
  			visitante.setPerdidosConsec(visitante.getGanadosConsec()+1);
  			local.setOro(local.getOro()+1000);
  			visitante.setOro(visitante.getOro()+100);
  		}
  		else if (part.getGolesLocal() < part.getGolesVisitante()) { //ganan visitantes
  			for (int i = 0; i < locales.size(); i++) {
  				locales.elementAt(i).incrementaPartidosJugados(Constantes.PARTIDO_PERDIDO);
  			}
  			for (int i = 0; i < visitantes.size(); i++) {
  				visitantes.elementAt(i).incrementaPartidosJugados(Constantes.PARTIDO_GANADO);
  			}
  			
  			visitante.setPuntos(visitante.getPuntos()+3);
  			visitante.setPartidosGanados(visitante.getPartidosGanados()+1);
  			local.setPartidosPerdidos(local.getPartidosPerdidos()+1);
  			visitante.setPerdidosConsec(0);
  			visitante.setGanadosConsec(visitante.getGanadosConsec()+1);
  			local.setGanadosConsec(0);
  			local.setPerdidosConsec(local.getGanadosConsec()+1);
  			local.setOro(local.getOro()+100);
  			visitante.setOro(visitante.getOro()+1000);
  		}
  		else { //empate
  			for (int i = 0; i < locales.size(); i++) {
  				locales.elementAt(i).incrementaPartidosJugados(Constantes.PARTIDO_EMPATADO);
  			}
  			for (int i = 0; i < visitantes.size(); i++) {
  				visitantes.elementAt(i).incrementaPartidosJugados(Constantes.PARTIDO_EMPATADO);
  			}
  			local.setPuntos(local.getPuntos()+1);
  			visitante.setPuntos(visitante.getPuntos()+1);
  			local.setPartidosEmpatados(local.getPartidosEmpatados()+1);
  			visitante.setPartidosEmpatados(visitante.getPartidosEmpatados()+1);
  			local.setPerdidosConsec(0);
  			local.setGanadosConsec(0);
  			visitante.setGanadosConsec(0);
  			visitante.setPerdidosConsec(0);
  			local.setOro(local.getOro()+300);
  			visitante.setOro(visitante.getOro()+300);
  		}
  	}
  	
  	public boolean faltaProducida(Convocado atacante, Convocado receptor) {
  		//1% de hacer falta minimo, 30% maximo
  		int agr1 = atacante.getAgresividadPartido();
  		int agr2 = receptor.getAgresividadPartido();
  		int agresividadTotal = (agr1 + agr2/2)/5;
  		if (agresividadTotal < 1) agresividadTotal = 1;
  		Random r = new Random();
  		int falta = 0;
  		//agresividadTotal = 100; //100% probabilidad falta
  		
  		if (r.nextInt(100) < agresividadTotal) {
  			//20%-40% tarjeta amarilla, 10%-20% tarjeta roja
  			atacante.incrementaFaltasRealizadas();
  			receptor.incrementaFaltasRecibidas();
  			int tipoFalta = r.nextInt(100);
  			
  			//tipoFalta = -1; //100% probabilidad roja
  			//tipoFalta = ((agr1+100)/10)-1; // 100% probabilidad amarilla
  			
  			if (tipoFalta < ((agr1+100)/10)) {
  				//tarjeta roja
  				atacante.incrementaTarjetasRojas();
  				falta = FALTA_ROJA;
  			}
  			else if (tipoFalta < ((agr1+100)/5)) {
  				//tarjeta amarilla
  				atacante.incrementaTarjetasAmarillas();
  				falta = FALTA_AMARILLA;
  			}
  			else {
  				//falta normal
  				falta = FALTA_NORMAL;
  			}
  		}
  		if (falta != 0) {
  			if (zonaCampo(part.getPelota().getXYZ()) == AREA_DERECHA && receptor.getCampo() == Convocado.CAMPO_IZQUIERDO) {
  				evento = PENALTI;
  				retorno.setX(890);
  	  	  		retorno.setY(350);
  			}
  			else if (zonaCampo(part.getPelota().getXYZ()) == AREA_IZQUIERDA && receptor.getCampo() == Convocado.CAMPO_DERECHO) {
  				evento = PENALTI;
  				retorno.setX(110);
  	  	  		retorno.setY(350);
  			}
  			else {
  				evento = falta;
  				retorno.setX(part.getPelota().getX());
  	  	  		retorno.setY(part.getPelota().getY());
  			}
  			part.setAgresor(atacante);
  	  		part.setReceptor(receptor);
  	  		temporizador = 160;
  	  		IA.getInstancia().setEvento(evento);
  	  		part.getPelota().resetearPosesion();
  	  		return true;
  		}
  		return false;
  	}
  	
  	public void comprobarLesion(Convocado atacante, Convocado receptor) {
  		//1% minimo, 10% maximo (100 agresividad, 1 recuperacion)
  		int agr = atacante.getAgresividadPartido();
  		int recup = receptor.getRecuperacion();
  		int calculo = (agr - recup)/10;
  		if (calculo < 1) calculo = 1;
  		Random r = new Random();
  		int gravedad = r.nextInt(100);
  		
  		//calculo = 100; //100% probabilidad de lesion
  		
  		if (gravedad <= calculo) {
  			gravedad = (gravedad+1)*4;
  			receptor.setLesion(gravedad);
  		}
  	}
  	
  	
  	public void fueraDeJuego(int campo) {
  		evento = FUERA_DE_JUEGO;
  		retorno.setY(350);
  		temporizador = 160;
  		if (campo == Convocado.CAMPO_DERECHO) {
  			retorno.setX(980);
  		}
  		else {
  			retorno.setX(20);
  		}
  	}
  	
  	public int getEvento() {
  		return evento;
  	}
}

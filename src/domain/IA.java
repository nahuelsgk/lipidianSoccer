/*
 * Nahuel Velazco Sanchez
 * Alberto Moreno Vega
 *
 */

package domain;

import java.util.Random;
import java.util.Vector;


public class IA {
	/*CONFIGURACION IA*/
	private int CONF_CORNER_POSICION;
	private int CONF_ZONA_DINAMICA;
	private double CONF_CANSANCIO; //por defecto 1. Si < 1 los futbolistas arriesgan mas y se cansan mas. Si > 1 al reves
	private double CONF_PRESION; //por defecto 1. Si < 1 la presion influye mas. Si > 1 la presion influye menos
	private double CONF_NIVEL_COMPANERO; //por defecto 1. Peso del nivel del compa�ero para elegir pase
	private double CONF_INTERCEPCION;  //por defecto 2. Peso de los rivales que interceptan un pase
	private double CONF_DISTANCIA_PASE; //por defecto 1. peso de la distancia para realizar un pase
	private double CONF_DISTANCIA_PORTERIA; //por defecto 3. peso de la distancia a porteria para realizar un pase
	private double CONF_RANDOM;  //por defecto 1. Peso de los valores aleatorios para seleccionar pases
	private double CONF_FUERA_JUEGO;  //por defecto 1. Peso de que un jugador este en fuera de juego para pasarle el balon
	/*CONFIGURACION IA*/
	
	private static final double PUNTOS_POR_METRO = 10;
	private static final double ESPRINT_MAXIMO = 8; //maxima velocidad que un jugador puede llegar a conseguir, en m/s
	private static final double ESPRINT_MINIMO = 6; //maxima velocidad que un jugador puede conseguir teniendo la minima velocidad
	private static final double CORRER_MAXIMO = 4;
	private static final double CORRER_MINIMO = 3;
	private static final double ANDAR_MAXIMO = 2;
	private static final double ANDAR_MINIMO = 1.5;
	private static final double CANSADO_MAXIMO = 0.7;
	private static final double CANSADO_MINIMO = 0.3;
	private static final double RANGO_MAXIMO = 100;   //rango maximo de velocidad
	private static final double SEGS_ITERACION = (double) 0.025; //tiempo (en segundos) entre iteraciones (periodo)
	private static final double RANGO_COLISION = 0;  //radio en puntos que se considera una colision
	private static final double CANSANCIO_PUNTO = 0.03; //Cansancio de un jugador andando un punto de distancia
	private static final double RECUPERACION_CANSANCIO = 0.05; //Recuperacion de cansancio de un jugador que no se mueve

	//Prioridades de movimiento, se tienen en cuenta para decidir la velocidad y por tanto el cansancio
	public static final int PRIORIDAD_ALTA = 10;
	public static final int PRIORIDAD_MEDIA = 11;		
	public static final int PRIORIDAD_BAJA = 12;

	public static final int ANDAR = 1;
	public static final int CORRER = 2;		//doble de rapido que andar
	public static final int ESPRINTAR = 3;   //doble de rapido que correr


	public static final int LOCAL = 0;
	public static final int VISITANTE = 1;

	private Partido part;


	private static IA instancia;

	
	public IA(){

	}

	public void setPartido(Partido actual){
		part = actual;
	}
	public Partido getPartido() {
		return part;
	}
	
	public static IA getInstancia() {
		if(instancia == null) {
			instancia = new IA();
		}
		return instancia;
	}

	
	public void ejecutar(){
		Vector<Convocado> locales = part.getLocales();
		Vector<Convocado> visitantes = part.getVisitantes();
		extraeLesionadosSancionados();
		if(CONF_ZONA_DINAMICA==1) actualizarZonasDinamicas();
		if (part.getEvento() != 0) {
			compruebaCampo();
			switch (part.getEvento()) {
			case Arbitraje.INICIO_PARTIDO:
				saqueDeCentro(Convocado.CAMPO_IZQUIERDO);
				part.setEvento(Arbitraje.ESPERANDO);
				break;
			case Arbitraje.INICIO_SEGUNDA_PARTE:
				saqueDeCentro(Convocado.CAMPO_IZQUIERDO); //han cambiado de campo, sacan los otros, osea los que ahora
															//estan en el mismo lado de los que sacaron antes
				part.setEvento(Arbitraje.ESPERANDO);
				break;
			case Arbitraje.ESPERANDO:
				posicionarFutbolistasPorEvento();
				break;
			case Arbitraje.SAQUE_CENTRO: 
				resuelveSaqueCentro();
				break;
			case Arbitraje.SAQUE_BANDA: 
				resuelveSaqueDeBanda();
				break;
			case Arbitraje.SAQUE_ESQUINA:
				resuelveSaqueDeEsquina();
				break;
			case Arbitraje.SAQUE_PENALTI:
				resuelveSaqueDePenalti();
				break;
			case Arbitraje.GOL_VISITANTE:
				saqueDeCentro(locales.firstElement().getCampo());
				part.setEvento(Arbitraje.ESPERANDO);
				break;
			case Arbitraje.GOL_LOCAL:
				saqueDeCentro(visitantes.firstElement().getCampo());
				part.setEvento(Arbitraje.ESPERANDO);
				break;
			case Arbitraje.FUERA:
				saqueDeBanda();
				part.setEvento(Arbitraje.ESPERANDO);
				break;
			case Arbitraje.FUERA_PORTERIA:
				saqueDePorteria(); 
				part.setEvento(Arbitraje.ESPERANDO);
				break;
			case Arbitraje.FUERA_ESQUINA:
				saqueDeEsquina(CONF_CORNER_POSICION);  
				part.setEvento(Arbitraje.ESPERANDO);
				break;
			case Arbitraje.FALTA_NORMAL:
				saqueDeFalta();
				part.setEvento(Arbitraje.ESPERANDO);
				break;
			case Arbitraje.FALTA_AMARILLA:
				saqueDeFalta();
				part.setEvento(Arbitraje.ESPERANDO);
				break;
			case Arbitraje.FALTA_ROJA:
				saqueDeFalta();
				part.setEvento(Arbitraje.ESPERANDO);
				break;
			case Arbitraje.PENALTI:
				saqueDePenalti();
				part.setEvento(Arbitraje.ESPERANDO);
				break;
			case Arbitraje.FUERA_DE_JUEGO:
				fueraDeJuego();
				part.setEvento(Arbitraje.ESPERANDO);
				break;
			default: break;
			}
		}
		else {
			if (part.getLocalIAEquipo()) {
				for(int i  = 0;i< locales.size(); i++){
					resolverIAEquipo(locales.elementAt(i),LOCAL);
					
				}
			}
			else {
				for(int i  = 0;i< locales.size(); i++){
					resolver(locales.elementAt(i),LOCAL);
				}
			}
			if (part.getVisitanteIAEquipo()) {
				for(int i  = 0;i< visitantes.size(); i++){
					resolverIAEquipo(visitantes.elementAt(i),VISITANTE);
				}
			}
			else {
				for(int i  = 0;i< visitantes.size(); i++){
					resolver(visitantes.elementAt(i),VISITANTE);
				}
			}
		}
	}
	
	private void compruebaCampo() {
		Vector<Convocado> izquierdos;
		Vector<Convocado> derechos;
		if (part.getTiempoJugado() < (part.getTiempoTotal()/2)) { //primera parte
			izquierdos = part.getVisitantes();
			derechos = part.getLocales();
		}
		else { //segunda parte
			derechos = part.getVisitantes();
			izquierdos = part.getLocales();
		}
		for (int i = 0; i < derechos.size(); i++) {
			derechos.elementAt(i).setCampo(Convocado.CAMPO_DERECHO);
		}
		for (int i = 0; i < izquierdos.size(); i++) {
			izquierdos.elementAt(i).setCampo(Convocado.CAMPO_IZQUIERDO);
		}
	}
	
	public void realizaCambioTactico(Convocado cambiado, Convocado nuevo, int equipo) {
		if (equipo == Partido.CAMBIO_LOCAL) {
			if (part.getLocalesReserva().remove(nuevo)) {
				part.getLocales().add(nuevo);
				part.getLocales().remove(cambiado);
				part.getLocalesReserva().add(cambiado);
				actualizaDatosRepuesto(cambiado, nuevo);
			}
		}
		else {
			if (part.getVisitantesReserva().remove(nuevo)) {
				part.getVisitantes().add(nuevo);
				part.getVisitantes().remove(cambiado);
				part.getVisitantesReserva().add(cambiado);
				actualizaDatosRepuesto(cambiado, nuevo);
			}
		}
	}
	
	
	
	public void realizaCambioLesionado(Convocado cambiado, Convocado nuevo, int equipo) { //cambia un lesionado por un reserva
		if (equipo == Partido.CAMBIO_LOCAL) {
			if (part.getLocalesReserva().remove(nuevo)) {
				part.getLocales().add(nuevo);
				actualizaDatosRepuesto(cambiado, nuevo);
			}
		}
		else {
			if (part.getVisitantesReserva().remove(nuevo)) {
				part.getVisitantes().add(nuevo);
				actualizaDatosRepuesto(cambiado, nuevo);
			}
		}
	}
	
	public void cambioJugador(Convocado c) {
		if (c.getCampo() == part.getLocales().firstElement().getCampo() && part.getCambiosLocal() > 0) { //si el jugador es local
			part.setCambiosLocal(part.getCambiosLocal()-1);
			if (part.getEquipoLocal().esUsuario()) {
				part.setSolicitarCambio(Partido.CAMBIO_LOCAL);
				part.setCambio(c);
			}
			else {
				Convocado repuesto = seleccionaMejorCambio(c, part.getLocalesReserva());
				if (repuesto != null) {
					realizaCambioLesionado(c, repuesto, Partido.CAMBIO_LOCAL);
				}
			}
		}
		else if (part.getCambiosVisitante() > 0){
			part.setCambiosVisitante(part.getCambiosVisitante()-1);
			if (part.getEquipoVisitante().esUsuario()) {
				part.setSolicitarCambio(Partido.CAMBIO_VISITANTE);
				part.setCambio(c);
			}
			else {
				Convocado repuesto = seleccionaMejorCambio(c, part.getVisitantesReserva());
				if (repuesto != null) {
					realizaCambioLesionado(c, repuesto, Partido.CAMBIO_VISITANTE);
				}
			}
		}
	}

	public void actualizaDatosRepuesto(Convocado cambiado, Convocado repuesto) {
		repuesto.setPosicion(cambiado.getPosicion());
		repuesto.setPosicionEvento(cambiado.getPosicionEvento().getX(), cambiado.getPosicionEvento().getY());
		repuesto.setPosX(500);
		repuesto.setPosX(800);
		repuesto.setZonaCampo(cambiado.getZonaCampoXmin(), cambiado.getZonaCampoXmax(), cambiado.getZonaCampoYmin(),
						cambiado.getZonaCampoYmax());
		repuesto.setZonaCampoOriginal(cambiado.getZonaCampoXminOriginal(), cambiado.getZonaCampoXmaxOriginal(), 
						cambiado.getZonaCampoYminOriginal(), cambiado.getZonaCampoYmaxOriginal());
		repuesto.setJugando(true);
		repuesto.setCampo(cambiado.getCampo());
		cambiado.incrementaPartidosJugados(Constantes.PARTIDO_PERDIDO); //El resultado del partido solo influye en la xp
		//un jugador que no juega todo el partido solo recibe experiencia como si lo hubiera perdido
	}
	
	private Convocado seleccionaMejorCambio(Convocado c, Vector<Convocado> repuestos) {
		int tipo = c.getTipoFutbolista();
		for (int i = 0; i < repuestos.size(); i++) {
			if (tipo == repuestos.elementAt(i).getTipoFutbolista()) {
				return repuestos.elementAt(i);
			}
		}
		if (repuestos.size() == 0) return null;
		return repuestos.firstElement();
	}
	
	private void posicionarFutbolistasPorEvento() {
		Vector<Convocado> locales = part.getLocales();
		Vector<Convocado> visitantes = part.getVisitantes();
		int n = 0;
		for(int i  = 0; i < locales.size(); i++){
			if (posicionarEvento(locales.elementAt(i))) {
				n++;
			}
		}
		for(int i  = 0; i< visitantes.size(); i++){
			if (posicionarEvento(visitantes.elementAt(i))) {
				n++;
			}
		}	
		if (n == (locales.size() + visitantes.size())) {
			part.setEvento(Arbitraje.EN_POSICION);
		}
	}
	

	private void fueraDeJuego() {
		Vector<Convocado> futbolistas;
		if (part.getProtagonistaEvento().getCampo() == part.getLocales().firstElement().getCampo()) {
			futbolistas = part.getLocales();
		}
		else {
			futbolistas = part.getVisitantes();
		}
		int min = 1000;
		int pos = 1000;
		for (int i = 0; i < futbolistas.size(); i++) {
			pos = futbolistas.elementAt(i).getPosicion();
			if (pos < min) {
				min = pos;
				part.setProtagonistaEvento(futbolistas.elementAt(i));
			}
		}
		Vector<Convocado> visitantes = part.getVisitantes();
		Vector<Convocado> locales = part.getLocales();
		for (int i = 0; i < visitantes.size(); i++) {
			posicionSaqueDePorteria(visitantes.elementAt(i)); 
		}
		for (int i = 0; i < locales.size(); i++) {
			posicionSaqueDePorteria(locales.elementAt(i));
		}
		
	}
	
	private void saqueDePenalti() {
		seleccionaSacadorFalta();
		Vector<Convocado> visitantes = part.getVisitantes();
		Vector<Convocado> locales = part.getLocales();
		for (int i = 0; i < visitantes.size(); i++) {
			posicionSaquePenalti(visitantes.elementAt(i)); 
		}
		for (int i = 0; i < locales.size(); i++) {
			posicionSaquePenalti(locales.elementAt(i));
		}
		
	}
	
	private void saqueDeFalta() {
		seleccionaSacadorFalta();
		Vector<Convocado> visitantes = part.getVisitantes();
		Vector<Convocado> locales = part.getLocales();
		for (int i = 0; i < visitantes.size(); i++) {
			posicionSaqueBanda(visitantes.elementAt(i)); //el saque de banda y de falta coinciden en posiciones
		}
		for (int i = 0; i < locales.size(); i++) {
			posicionSaqueBanda(locales.elementAt(i));
		}
		
	}
	
	private void seleccionaSacadorFalta() { //saca la falta el que la ha recibido, a menos que este lesionado en
											//cuyo caso la saca el compa�ero mas cercano
		Convocado receptorFalta = part.getReceptor();
		Vector<Convocado> equipoReceptor;
		if (receptorFalta.getCampo() == part.getLocales().firstElement().getCampo()) {
			equipoReceptor = part.getLocales();
		}
		else {
			equipoReceptor = part.getVisitantes();
		}
		if (receptorFalta.getLesion() > 0) {
			double dist = 100000;
			double distMin = 100000;
			for (int i = 0; i < equipoReceptor.size(); i++) {
				dist = receptorFalta.getPosicionActual().distanciaEnPlano(equipoReceptor.elementAt(i).getPosicionActual());
				if (dist < distMin) { 
					distMin = dist;
					receptorFalta = equipoReceptor.elementAt(i);
				}
			}
		}
		part.setProtagonistaEvento(receptorFalta);
	}
	
	private void saqueDePorteria() {
		Vector<Convocado> visitantes = part.getVisitantes();
		Vector<Convocado> locales = part.getLocales();
		double x = part.getPelota().getX();
		int campo;
		if (x <= 0) {
			campo = Convocado.CAMPO_IZQUIERDO;
		}
		else {
			campo = Convocado.CAMPO_DERECHO;
		}
		for (int i = 0; i < visitantes.size(); i++) {
			if (visitantes.elementAt(i).getPosicion() == 0 && visitantes.elementAt(i).getCampo() == campo) {
				part.setProtagonistaEvento(visitantes.elementAt(i));
			}
			posicionSaqueDePorteria(visitantes.elementAt(i));
			
		}
		for (int i = 0; i < locales.size(); i++) {
			if (locales.elementAt(i).getPosicion() == 0 && locales.elementAt(i).getCampo() == campo) {
				part.setProtagonistaEvento(locales.elementAt(i));
			}
			posicionSaqueDePorteria(locales.elementAt(i));
		}
	}
	
	private void saqueDeBanda() {
		int campo = part.getPelota().getConvocado().getCampo();
		XYZ posPelota = part.getPelota().getXYZ();
		Vector<Convocado> futbolistas1;
		Vector<Convocado> futbolistas2;
		if (part.getLocales().firstElement().getCampo() == campo) { //los locales han sacado fuera la pelota
			futbolistas1 = part.getVisitantes();
			futbolistas2 = part.getLocales();
		}
		else { //los visitantes han sacado fuera la pelota
			futbolistas1 = part.getLocales();
			futbolistas2 = part.getVisitantes();
		}
		double dist = 100000;
		double distMin = 100000;
		for (int i = 0; i < futbolistas1.size(); i++) {
			dist = posPelota.distanciaEnPlano(futbolistas1.elementAt(i).getPosicionActual());
			if (dist < distMin) { 
				distMin = dist;
				part.setProtagonistaEvento(futbolistas1.elementAt(i));
			}
		}
		for (int i = 0; i < futbolistas1.size(); i++) {
			posicionSaqueBanda(futbolistas1.elementAt(i));
		}
		for (int i = 0; i < futbolistas2.size(); i++) {
			posicionSaqueBanda(futbolistas2.elementAt(i));
		}
	}
	
	private void saqueDeEsquina(int configuracioIASaqueDeEsquina) {
		int campo = part.getPelota().getConvocado().getCampo();
		
		XYZ posPelota = part.getPelota().getXYZ();
		Vector<Convocado> futbolistas1;
		Vector<Convocado> futbolistas2;
		if (part.getLocales().firstElement().getCampo() == campo) { //los locales han sacado fuera la pelota
			futbolistas1 = part.getVisitantes();
			futbolistas2 = part.getLocales();
		}
		else { //los visitantes han sacado fuera la pelota
			futbolistas1 = part.getLocales();
			futbolistas2 = part.getVisitantes();
		}
		int campoSaque = futbolistas2.firstElement().getCampo();
		double dist = 100000;
		double distMin = 100000;
		for (int i = 0; i < futbolistas1.size(); i++) {
			dist = posPelota.distanciaEnPlano(futbolistas1.elementAt(i).getPosicionActual());
			if (dist < distMin) { 
				distMin = dist;
				part.setProtagonistaEvento(futbolistas1.elementAt(i));
			}
		}
		//Configuracion Saque esquina vale 0 nadie de posiciona excepto protagonistaEvento
		if(configuracioIASaqueDeEsquina==1){
			for (int i = 0; i < futbolistas1.size(); i++) {
				posicionSaqueEsquina(futbolistas1.elementAt(i), campoSaque);
			}
			for (int i = 0; i < futbolistas2.size(); i++) {
				posicionSaqueEsquina(futbolistas2.elementAt(i), campoSaque);
			}
		}
		else {
			for (int i = 0; i < futbolistas1.size(); i++) {
				posicionSaqueBanda(futbolistas1.elementAt(i));
			}
			for (int i = 0; i < futbolistas2.size(); i++) {
				posicionSaqueBanda(futbolistas2.elementAt(i));
			}
		}
	}
	
	private void saqueDeCentro(int campo) {
		Vector<Convocado> locales = part.getLocales();
		Vector<Convocado> visitantes = part.getVisitantes();
		int sacadorLoc = 0;  //persona que sacara el balon
		int sacadorVis = 0;
		int companeroLoc = 0;  //persona que recibira el pase del saque
		int companeroVis = 0;
		for (int i = 0; i < locales.size(); i++) {
			if (locales.elementAt(i).getPosicion() > sacadorLoc) { //el sacador es la posicion mas elevada
				companeroLoc = sacadorLoc;
				sacadorLoc = locales.elementAt(i).getPosicion();
			}
			else if (locales.elementAt(i).getPosicion() > companeroLoc) { //el companero es la segunda mas elevada
				companeroLoc = locales.elementAt(i).getPosicion();
			}
		}
		for (int i = 0; i < visitantes.size(); i++) {
			if (visitantes.elementAt(i).getPosicion() > sacadorVis) {
				companeroVis = sacadorVis;
				sacadorVis = visitantes.elementAt(i).getPosicion();
			}
			else if (visitantes.elementAt(i).getPosicion() > companeroVis) {
				companeroVis = visitantes.elementAt(i).getPosicion();
			}
		}
		for(int i  = 0;i< locales.size(); i++){
			posicionSaque(locales.elementAt(i),campo, sacadorLoc, companeroLoc);
		}
		for(int i  = 0;i< visitantes.size(); i++){
			posicionSaque(visitantes.elementAt(i),campo, sacadorVis, companeroVis);
		}
	}
	
	
	public void posicionSaqueEsquina(Convocado c, int campoSaque) {
		int i = c.getPosicion();
		if (c.getCampo() == Convocado.CAMPO_IZQUIERDO) { //jugadores del campo izquierdo
			if (campoSaque == Convocado.CAMPO_IZQUIERDO) { //saque de esquina en el campo izquierdo
				switch (i) {
				case 0:  c.setPosicionEvento(20, 350); break;
				case 1:  c.setPosicionEvento(100, 250); break;
				case 2:  c.setPosicionEvento(50, 200); break;
				case 3:  c.setPosicionEvento(50, 500); break;
				case 4:  c.setPosicionEvento(100, 450); break;
				case 6:  c.setPosicionEvento(150, 350); break;
				default: 
					double x = ((c.getZonaCampoXmax()-c.getZonaCampoXmin())/2) + c.getZonaCampoXmin();
					double y = ((c.getZonaCampoYmax()-c.getZonaCampoYmin())/2) + c.getZonaCampoYmin();
					c.setPosicionEvento(x, y);
					break;
				}
			}
			else { //saque de esquina en el campo derecho
				switch (i) {
				case 5:  c.setPosicionEvento(900, 200); break;
				case 6:  c.setPosicionEvento(900, 500); break;
				case 7:  c.setPosicionEvento(835, 565); break;
				case 8:  c.setPosicionEvento(835, 135); break;
				case 9:  c.setPosicionEvento(800, 350); break;
				case 10:  c.setPosicionEvento(900, 350); break;
				default:
					double x = ((c.getZonaCampoXmax()-c.getZonaCampoXmin())/2) + c.getZonaCampoXmin();
					double y = ((c.getZonaCampoYmax()-c.getZonaCampoYmin())/2) + c.getZonaCampoYmin();
					c.setPosicionEvento(x, y);
					break;
				}
			}
		}
		else {  //jugadores del campo derecho
			if (campoSaque == Convocado.CAMPO_DERECHO) { //saque de esquina en el campo derecho
				switch (i) {
				case 0:  c.setPosicionEvento(980, 350); break;
				case 1:  c.setPosicionEvento(900, 250); break;
				case 2:  c.setPosicionEvento(950, 200); break;
				case 3:  c.setPosicionEvento(950, 500); break;
				case 4:  c.setPosicionEvento(900, 450); break;
				case 6:  c.setPosicionEvento(850, 350); break;
				default: 
					double x = ((c.getZonaCampoXmax()-c.getZonaCampoXmin())/2) + c.getZonaCampoXmin();
					double y = ((c.getZonaCampoYmax()-c.getZonaCampoYmin())/2) + c.getZonaCampoYmin();
					c.setPosicionEvento(x, y);
					break;
				}
			}
			else { //saque de esquina en el campo izquierdo
				switch (i) {
				case 5:  c.setPosicionEvento(100, 200); break;
				case 6:  c.setPosicionEvento(100, 500); break;
				case 7:  c.setPosicionEvento(165, 565); break;
				case 8:  c.setPosicionEvento(165, 135); break;
				case 9:  c.setPosicionEvento(200, 350); break;
				case 10:  c.setPosicionEvento(100, 350); break;
				default:
					double x = ((c.getZonaCampoXmax()-c.getZonaCampoXmin())/2) + c.getZonaCampoXmin();
					double y = ((c.getZonaCampoYmax()-c.getZonaCampoYmin())/2) + c.getZonaCampoYmin();
					c.setPosicionEvento(x, y);
					break;
				}
			}
		}
		if (c == part.getProtagonistaEvento()) {
			double x, y;
			if (part.getPelota().getX() > 500) {
				x = 1000;
			}
			else {
				x = 0;
			}
			if (part.getPelota().getY() < 350) {
				y = 0;
			}
			else {
				y = 700;
			}
			c.setPosicionEvento(x, y);
		}
	}
	
	public void posicionSaque(Convocado c, int campoSaque, int sacador, int companero) {
		int i = c.getPosicion();
		if(c.getCampo() == Convocado.CAMPO_IZQUIERDO){
			switch (i) {
				case 0:  c.setPosicionEvento(20, 350); break;
				case 1:  c.setPosicionEvento(200, 120); break;
				case 2:  c.setPosicionEvento(200, 350); break;
				case 3:  c.setPosicionEvento(200, 580); break;
				case 4:  c.setPosicionEvento(350, 580); break;
				case 5:  c.setPosicionEvento(350, 120); break;
				case 6:  c.setPosicionEvento(300, 235); break;
				case 7:  c.setPosicionEvento(350, 350); break;
				case 8:  c.setPosicionEvento(300, 465); break;
				case 9:  c.setPosicionEvento(400, 235); break;
				case 10: c.setPosicionEvento(400, 465); break;
				default: break;
				}
			}
		else {
			switch (i) {
				case 0:  c.setPosicionEvento(980, 350); break;
				case 1:  c.setPosicionEvento(800, 120); break;
				case 2:  c.setPosicionEvento(800, 350); break;
				case 3:  c.setPosicionEvento(800, 580); break;
				case 4:  c.setPosicionEvento(650, 580); break;
				case 5:  c.setPosicionEvento(650, 120); break;
				case 6:  c.setPosicionEvento(700, 235); break;
				case 7:  c.setPosicionEvento(650, 350); break;
				case 8:  c.setPosicionEvento(700, 465); break;
				case 9:  c.setPosicionEvento(600, 235); break;
				case 10: c.setPosicionEvento(600, 465); break;
				default: break;
			}
			
		}
		if (c.getCampo() == campoSaque) {
			if (i == sacador) {
				c.setPosicionEvento(500, 365);
				part.setProtagonistaEvento(c);
			}
			else if (i == companero) {
				c.setPosicionEvento(500, 310);
				part.setAyudanteEvento(c);
				c.setMeHanPasado(false);
			}
		}
	}
	
	private void posicionSaqueDePorteria(Convocado c) {
		double x = ((c.getZonaCampoXmax()-c.getZonaCampoXmin())/2) + c.getZonaCampoXmin();
		double y = ((c.getZonaCampoYmax()-c.getZonaCampoYmin())/2) + c.getZonaCampoYmin();
		c.setPosicionEvento(x, y);
	}
	
	private void posicionSaqueBanda(Convocado c) {
		if (c == part.getProtagonistaEvento()) {
			c.setPosicionEvento(part.getPelota().getX(), part.getPelota().getY());
		}
		else {
			double x = ((c.getZonaCampoXmax()-c.getZonaCampoXmin())/2) + c.getZonaCampoXmin();
			double y = ((c.getZonaCampoYmax()-c.getZonaCampoYmin())/2) + c.getZonaCampoYmin();
			c.setPosicionEvento(x, y);
		}
	}
	
	private void posicionSaquePenalti(Convocado c) {
		if (c == part.getProtagonistaEvento()) {
			if (c.getCampo() == Convocado.CAMPO_DERECHO) {
				c.setPosicionEvento(200, 350);
			}
			else {
				c.setPosicionEvento(800, 350);
			}
		}
		else {
			double x = ((c.getZonaCampoXmax()-c.getZonaCampoXmin())/2) + c.getZonaCampoXmin();
			double y = ((c.getZonaCampoYmax()-c.getZonaCampoYmin())/2) + c.getZonaCampoYmin();
			c.setPosicionEvento(x, y);
		}
	}
	
	private boolean posicionarEvento(Convocado c) { //retorna true si el convocado esta en posicion
		c.moverConvocadoA(calculaMovimiento(c, c.getPosicionEvento(), CORRER));
		if (c.getPosicionActual().superposicion(c.getPosicionEvento())) return true;
		return false;
	}
	
	//Define las zonas del campo asignadas al jugador: 
	public void definirZonaDeCampo(Convocado c, int campo, int alineacion){
		int i = c.getPosicion();
		switch (alineacion) {
			case Constantes.ALINEACION_4_3_3:
				switch (i) {
					case 0: c.setZonaCampo(-55, 55, 258.4, 441.6); break;
					case 1: c.setZonaCampo(0, 750, 0, 200); break;
					case 2: c.setZonaCampo(0, 375, 180, 375); break;
					case 3: c.setZonaCampo(0, 375, 325, 520); break;
					case 4: c.setZonaCampo(0, 750, 500, 700); break;
					case 5: c.setZonaCampo(250, 800, 0, 340); break;
					case 6: c.setZonaCampo(320, 820, 175, 525); break;
					case 7: c.setZonaCampo(250, 800, 360, 700); break;
					case 8: c.setZonaCampo(600, 1000, 0, 400); break;
					case 9: c.setZonaCampo(600, 1000, 300, 700); break;
					case 10: c.setZonaCampo(700, 1000, 148.4, 551.6); break;
					default: break;
				}
			break;
			case Constantes.ALINEACION_4_4_2:
				switch (i) {
					case 0: c.setZonaCampo(-55, 55, 258.4, 441.6); break;
					case 1: c.setZonaCampo(0, 550, 0, 200); break;
					case 2: c.setZonaCampo(0, 375, 180, 375); break;
					case 3: c.setZonaCampo(0, 375, 325, 520); break;
					case 4: c.setZonaCampo(0, 550, 500, 700); break;
					case 5: c.setZonaCampo(225, 825, 0, 240); break;
					case 6: c.setZonaCampo(270, 770, 84, 434); break; 
					case 7: c.setZonaCampo(270, 770, 266, 616); break;
					case 8: c.setZonaCampo(225, 825, 460, 700); break;
					case 9: c.setZonaCampo(670, 1000, 0, 528.4); break;
					case 10: c.setZonaCampo(670, 1000, 171.6, 700); break;
					default: break;
				}
			break;
			case Constantes.ALINEACION_5_4_1:
				switch (i) {
					case 0: c.setZonaCampo(-55, 55, 258.4, 441.6); break;
					case 1: c.setZonaCampo(0, 550, 0, 200); break;
					case 2: c.setZonaCampo(0, 375, 130, 325); break;
					case 3: c.setZonaCampo(0, 375, 252.5, 447.5); break;
					case 4: c.setZonaCampo(0, 375, 375, 570); break;
					case 5: c.setZonaCampo(0, 550, 500, 700); break;
					case 6: c.setZonaCampo(225, 825, 0, 240); break;
					case 7: c.setZonaCampo(270, 770, 84, 434); break;
					case 8: c.setZonaCampo(270, 770, 266, 616); break;
					case 9: c.setZonaCampo(225, 825, 460, 700); break;
					case 10: c.setZonaCampo(550, 1000, 0, 700); break;
					default: break;
				}
			break;
			case Constantes.ALINEACION_5_3_2:
				switch (i) {
					case 0: c.setZonaCampo(-55, 55, 258.4, 441.6); break;
					case 1: c.setZonaCampo(0, 550, 0, 200); break;
					case 2: c.setZonaCampo(0, 375, 130, 325); break;
					case 3: c.setZonaCampo(0, 375, 252.5, 447.5); break;
					case 4: c.setZonaCampo(0, 375, 375, 570); break;
					case 5: c.setZonaCampo(0, 550, 500, 700); break;
					case 6: c.setZonaCampo(250, 800, 0, 340); break;
					case 7: c.setZonaCampo(320, 820, 175, 525); break;
					case 8: c.setZonaCampo(250, 800, 360, 700); break;
					case 9: c.setZonaCampo(670, 1000, 0, 528.4); break;
					case 10: c.setZonaCampo(670, 1000, 171.6, 700); break;
					default: break;
				}
			break;
			default: break;
		}
		if (campo == Convocado.CAMPO_DERECHO) {
			zonaCampoDerecho(c);
		}
		c.setDinamicasComoOriginales();
	}
	
	private void zonaCampoDerecho(Convocado c) {
		double xmin = c.getZonaCampoXmin();
		double xmax = c.getZonaCampoXmax();
		double ymin = c.getZonaCampoYmin();
		double ymax = c.getZonaCampoYmax();
		c.setZonaCampo(1000-xmax,1000-xmin, ymin, ymax);
	}
	
	
	public void posicionarJugadoresSegundaParte() {
		Vector<Convocado> visitantes = part.getVisitantes();
		Vector<Convocado> locales = part.getLocales();
		for(int i  = 0;i< visitantes.size(); i++){
			visitantes.elementAt(i).setCampo(Convocado.CAMPO_DERECHO);
			definirZonaDeCampo(visitantes.elementAt(i),Convocado.CAMPO_DERECHO, part.getAlineacionVisitante());
			visitantes.elementAt(i).setPosX(500);
			visitantes.elementAt(i).setPosY(0);
		}
		for(int i  = 0;i< locales.size(); i++){
			locales.elementAt(i).setCampo(Convocado.CAMPO_IZQUIERDO);
			definirZonaDeCampo(locales.elementAt(i),Convocado.CAMPO_IZQUIERDO, part.getAlineacionLocal());
			locales.elementAt(i).setPosX(500);
			locales.elementAt(i).setPosY(700);
		}
	}
	
	public void posicionarJugadoresInicioPartido(Partido actual) {
		this.part = actual;
		Vector<Convocado> visitantes = actual.getVisitantes();
		Vector<Convocado> locales = actual.getLocales();
		for(int i  = 0;i< visitantes.size(); i++){
			visitantes.elementAt(i).setCampo(Convocado.CAMPO_IZQUIERDO);
			definirZonaDeCampo(visitantes.elementAt(i),Convocado.CAMPO_IZQUIERDO, part.getAlineacionVisitante());
			visitantes.elementAt(i).setPosX(500);
			visitantes.elementAt(i).setPosY(700);
		}
		for(int i  = 0;i< locales.size(); i++){
			locales.elementAt(i).setCampo(Convocado.CAMPO_DERECHO);
			definirZonaDeCampo(locales.elementAt(i),Convocado.CAMPO_DERECHO, part.getAlineacionLocal());
			locales.elementAt(i).setPosX(500);
			locales.elementAt(i).setPosY(0);
		}
	}
	

	private void resuelveSaqueDePenalti() {
		if (part.getProtagonistaEvento().getPosesion()) {
			Convocado c = part.getProtagonistaEvento();
			c.chutarAPpuerta(part.getPelota());
			part.setEvento(0);
		}
		else {
			part.getProtagonistaEvento().moverConvocadoA(calculaMovimiento(part.getProtagonistaEvento(), part.getPelota().getXYZ(), CORRER));
			part.getProtagonistaEvento().cogerPelota(part.getPelota());
		}
	}
	
	private void resuelveSaqueDeEsquina() {
		if (part.getProtagonistaEvento().getPosesion()) {
			Convocado c;
			if (part.getLocales().contains(part.getProtagonistaEvento())) {
				c = seleccionCompaneroPase(part.getProtagonistaEvento(), LOCAL);
			}
			else {
				c = seleccionCompaneroPase(part.getProtagonistaEvento(), VISITANTE);
			}

			part.getProtagonistaEvento().pasarA(c, part.getPelota()); //el saque de esquina no comprueba fuera de juego
			part.setEvento(0);
		}
		else {
			part.getProtagonistaEvento().moverConvocadoA(calculaMovimiento(part.getProtagonistaEvento(), part.getPelota().getXYZ(), CORRER));
			part.getProtagonistaEvento().cogerPelota(part.getPelota());
		}
	}
	
	
	private void resuelveSaqueDeBanda() {
		if (part.getProtagonistaEvento().getPosesion()) {
			Convocado c;
			if (part.getLocales().contains(part.getProtagonistaEvento())) {
				c = seleccionCompaneroPase(part.getProtagonistaEvento(), LOCAL);
			}
			else {
				c = seleccionCompaneroPase(part.getProtagonistaEvento(), VISITANTE);
			}
			pase(part.getProtagonistaEvento(), c);
			part.setEvento(0);
		}
		else {
			part.getProtagonistaEvento().moverConvocadoA(calculaMovimiento(part.getProtagonistaEvento(), part.getPelota().getXYZ(), CORRER));
			part.getProtagonistaEvento().cogerPelota(part.getPelota());
		}
	}
	
	private void resuelveSaqueCentro() {
		if (part.getProtagonistaEvento().getPosesion()) {
			pase(part.getProtagonistaEvento(), part.getAyudanteEvento());
			part.setEvento(0);
		}
		else {
			part.getProtagonistaEvento().moverConvocadoA(calculaMovimiento(part.getProtagonistaEvento(), part.getPelota().getXYZ(), CORRER));
			part.getProtagonistaEvento().cogerPelota(part.getPelota());
		}
	}
	
	private void extraeLesionadosSancionados() {
		Vector<Convocado> lesSan = part.getLesionadosSancionados();
		XYZ objetivo;
		int vel;
		for (int i = 0; i < lesSan.size(); i++) {
			if (lesSan.elementAt(i).getCampo() == Convocado.CAMPO_DERECHO) {
				objetivo = new XYZ(500, -200, 0);
			}
			else {
				objetivo = new XYZ(500, 900, 0);
			}
			if (lesSan.elementAt(i).getLesion() > 0) {
				vel = ANDAR;
			}
			else {
				vel = CORRER;
			}
			lesSan.elementAt(i).moverConvocadoA(calculaMovimiento(lesSan.elementAt(i), objetivo, vel));
			if (lesSan.elementAt(i).getPosY() > 800 || lesSan.elementAt(i).getPosY() < -100) {
				lesSan.remove(i);
			}
		}
	}
	
	//Decidir cual es el objetivo del jugador
	private XYZ siguienteObjetivo(Convocado c){
		XYZ ret = new XYZ();
		double px = part.getPelota().getX();
		double py = part.getPelota().getY();
		
		if (c.getPosesion()) { //si tengo el balon voy a puerta enemiga
			ret.setY(350);
			if (c.getCampo() == Convocado.CAMPO_DERECHO) ret.setX(0);
			else ret.setX(1000);
		}
		else if (c.getMeHanPasado()) { //si me pasan voy a por la pelota

			if (c.getPosicion() == 0) { //soy portero
				ret = part.getPelota().posicionDeIntercepcion(calculaDistanciaMovimiento(c, ESPRINTAR), c.getPosicionActual(), c.getParadaPartido());
				if (ret.getX() < 0) {
					ret.setX(0);
				}
				else if (ret.getX() > 1000) {
					ret.setX(1000);
				}
			}
			else {
				ret = part.getPelota().posicionDeIntercepcion(calculaDistanciaMovimiento(c, CORRER), c.getPosicionActual(), 0);
			}
		}
		//El balon esta en mi zona: voy a por el balon en mi zona
		else if(c.getZonaCampoXmin()<=px && px<=c.getZonaCampoXmax() && 
				c.getZonaCampoYmin()<=py && py<=c.getZonaCampoYmax()){
			if (part.getPelota().getPosesion() && c.comparaEquipo(part.getPelota().getConvocado())) { 
				//si la pelota la tiene alguien de mi equipo
				if (part.getPelota().getXYZ().distanciaEnPlano(c.getPosicionActual()) > 40) {
					//si el de mi equipo esta mas lejos de 40 puntos, voy hacia el
					ret.setX(part.getPelota().getX());
					ret.setY(part.getPelota().getY());
				}
				else {//si no me quedo quieto
					ret.setX(c.getPosX());
					ret.setY(c.getPosY());
				}
			}
			else {

				if (c.getPosicion() == 0) { //soy portero
					ret = part.getPelota().posicionDeIntercepcion(calculaDistanciaMovimiento(c, ESPRINTAR), c.getPosicionActual(), c.getParadaPartido());
					if (ret.getX() < 0) {
						ret.setX(0);
					}
					else if (ret.getX() > 1000) {
						ret.setX(1000);
					}
				}
				else {
					ret = part.getPelota().posicionDeIntercepcion(calculaDistanciaMovimiento(c, CORRER), c.getPosicionActual(), 0);
				}
			}
			
		}
		else { //No esta en mi zona:
			if (c.getPosicion() == 0) { //soy portero
				ret = posicionIntercepcionPortero(c.getCampo()); //retorna 0,0,0 si no hay solucion, creo que nunca se da el caso
			}
			else if (c.getZonaCampoXmin()<=c.getPosX() && c.getPosX()<=c.getZonaCampoXmax() && 
					c.getZonaCampoYmin()<=c.getPosY() && c.getPosY()<=c.getZonaCampoYmax() &&
					part.getPelota().getXYZ().distanciaEnPlano(c.getPosicionActual()) < 150) {
				//estoy dentro de mi zona y la pelota esta cerca, avanzo hacia donde este la pelota
				ret = part.getPelota().posicionDeIntercepcion(calculaDistanciaMovimiento(c, CORRER), c.getPosicionActual(), 0);
			}
			else { //no estoy en mi zona, voy a ella
				ret.setX((c.getZonaCampoXmax()+c.getZonaCampoXmin())/2);
				ret.setY((c.getZonaCampoYmax()+c.getZonaCampoYmin())/2);
			}
		}
		
		return ret;
	}


	//Resulve que ha de hacer un convocado
	private void resolver(Convocado c, int equipoLocalOVisitante){

		if (c.getMeHanPasado() && part.getPelota().getPosesion()) { //si alguien que no soy yo ha cogido la pelota
			c.setMeHanPasado(false);
		}
		if (!c.conmocionado()) { //si estoy conmocionado por haber fallado un regateo no hago nada
			// Heuristico para resolver objetivo
			XYZ objetivo = siguienteObjetivo(c);
			XYZ ret;
			if (c.getPosicion() == 0) ret = calculaMovimiento(c, objetivo, ESPRINTAR); //si soy el portero voy a toda ostia
			else ret = calculaMovimiento(c, objetivo, CORRER);
			c.moverConvocadoA(new XYZ(ret.getX(),ret.getY()));
			if (c.getPosesion()) {
				if (eventoPosible(c, equipoLocalOVisitante)) { //existe la posibilidad de que decida realizar un pase en base a ciertos parametros
					Convocado objetivoPase = seleccionCompaneroPase(c, equipoLocalOVisitante);
					pase(c, objetivoPase);
				}
				else if (c.getPosX() >= c.getZonaCampoXmax()) {
					Convocado objetivoPase = seleccionCompaneroPase(c, equipoLocalOVisitante);
					pase(c, objetivoPase);
				}
				else if (c.getPosX() <= c.getZonaCampoXmin()) {
					Convocado objetivoPase = seleccionCompaneroPase(c, equipoLocalOVisitante);
					pase(c, objetivoPase);
				}
				else if (c.getPosY() >= c.getZonaCampoYmax()) {
					Convocado objetivoPase = seleccionCompaneroPase(c, equipoLocalOVisitante);
					pase(c, objetivoPase);
				}
				else if (c.getPosY() <= c.getZonaCampoYmin()) {
					Convocado objetivoPase = seleccionCompaneroPase(c, equipoLocalOVisitante);
					pase(c, objetivoPase);
				}
				else if (c.distanciaPorteriaRival() < 200) {
					c.chutarAPpuerta(part.getPelota());
				}
			}
			else if (c.getPosicion() == 0) {
				if (part.getPelota().enRangoPortero(c)) {
					Convocado pasador = null;
					if (c.getMeHanPasado()) {
						//se incrementan las estadisticas de la persona que me paso la pelota 
						pasador = part.getPelota().getConvocado();
					}
					if (part.getPelota().paradaDePortero(c)) {
						if (pasador != null) {
							pasador.incrementaPasesBuenos();
						}
						c.setPosesion(true);
					}
				}
			}
			else if (part.getPelota().enRango(c.getPosX(), c.getPosY(), c.getAltura())) {
				Convocado pasador = null;
				if (c.getMeHanPasado()) {
					//se incrementan las estadisticas de la persona que me paso la pelota 
					pasador = part.getPelota().getConvocado();
				}
				if (c.cogerPelota(part.getPelota())) {  //si he podido coger la pelota la paso 
					if (pasador != null) {
						pasador.incrementaPasesBuenos();
					}
					c.setPosesion(true);
				}
			}
		}
	}
	
	private void resolverIAEquipo(Convocado c, int equipoLocalOVisitante){
		if (c.getMeHanPasado() && part.getPelota().getPosesion()) { //si alguien ha cogido la pelota
			c.setMeHanPasado(false);
		}
		if (!c.conmocionado()) { //si estoy conmocionado no juego este turno
			if (c.getPosicion() == 0) {
				// los porteros son muy distintos a los demas, merece la pena resolverlos a parte
				resolverPortero(c, equipoLocalOVisitante); 
			}
			else {
				if (c.getPosesion()) { //tengo el balon
					resolverAccionConBalon(c, equipoLocalOVisitante);
				}
				else {
					resolverAccionSinBalon(c, equipoLocalOVisitante);
				}
			}
		}
	}

	
	private void resolverPortero(Convocado c, int equipoLocalOVisitante) {
		if (c.getPosesion()) { //tengo el balon
			paseAvanzado(c, seleccionCompaneroPase(c, equipoLocalOVisitante));
		}
		else { //no lo tengo
			if (dentroZonaDinamica(c, part.getPelota().getX(), part.getPelota().getY())) { //la pelota esta en mi zona
				irAPorBalon(c);
				Convocado pasador = null;
				if (c.getMeHanPasado()) {
					if (part.getPelota().enRango(c.getPosX(), c.getPosY(), c.getAltura())) {
						pasador = part.getPelota().getConvocado(); 
						c.setMeHanPasado(false);
					}
				}
				if (part.getPelota().paradaDePortero(c)) {
					if (pasador != null) {
						pasador.incrementaPasesBuenos();
					}
					c.setPosesion(true);
				}
			}
			else if (part.getPelota().getPosesion()){ //alguien tiene la pelota
				if (part.getPelota().getConvocado().getCampo() == c.getCampo()) {//mi equipo
					movimientoAvanzado(c, posicionIntercepcionPortero(c.getCampo()), PRIORIDAD_BAJA);
				}
				else {
					movimientoAvanzado(c, posicionIntercepcionPortero(c.getCampo()), PRIORIDAD_MEDIA);
					if (part.getPelota().paradaDePortero(c)) {
						c.setPosesion(true);
					}
				}
			}
			else { //la pelota no la tiene nadie
				XYZ intercepcion = part.getPelota().posicionDeIntercepcion(calculaDistanciaMovimiento(c, ESPRINTAR), c.getPosicionActual(), c.getParadaPartido());
				if (dentroZonaDinamica(c, intercepcion.getX(), intercepcion.getY())) {
					movimientoAvanzado(c, intercepcion, PRIORIDAD_ALTA);
				}
				else {
					movimientoAvanzado(c, posicionIntercepcionPortero(c.getCampo()), PRIORIDAD_ALTA);
				}
				if (part.getPelota().paradaDePortero(c)) {
					c.setPosesion(true);
				}
			}
		}
	}
	
	private void resolverAccionConBalon(Convocado c, int equipoLocalOVisitante) {
		if (decidirCutePorteria(c)) {
			c.chutarAPpuerta(part.getPelota());
		}
		else if (limiteZonaDinamica(c)) {
			Convocado companero = seleccionCompaneroPase(c, equipoLocalOVisitante);
			paseAvanzado(c, companero);
		}
		else if (estoyPresionado(c) || estoyCansado(c)) {
			Convocado companero = seleccionCompaneroPase(c, equipoLocalOVisitante);
			paseAvanzado(c, companero);
		}
		else {
			avanzarAPorteriaEnemiga(c);
		}
	}
	

	
	private void resolverAccionSinBalon(Convocado c, int equipoLocalOVisitante) {
		if (c.getMeHanPasado()) { //me han pasado el balon
			irAPorBalon(c);
			Convocado pasador = null;
			if (part.getPelota().enRango(c.getPosX(), c.getPosY(), c.getAltura())) {
				pasador = part.getPelota().getConvocado(); 
				c.setMeHanPasado(false);
			}
			if (c.cogerPelota(part.getPelota())) {
				if (pasador != null) {
					pasador.incrementaPasesBuenos();
				}
			}

		}
		else if (part.getPelota().getPosesion()) { //alguien tiene la pelota
			if (part.getPelota().getConvocado().getCampo() == c.getCampo()) { //mi equipo tiene la pelota
				movimientoAvanzado(c, posicionDeDesmarque(c), PRIORIDAD_MEDIA);
			}
			else { //el equipo contrario tiene la pelota
				if (dentroZonaDinamica(c, part.getPelota().getX(), part.getPelota().getY())) { //el rival esta en mi zona
					movimientoAvanzado(c, part.getPelota().getXYZ(), PRIORIDAD_ALTA);
					c.cogerPelota(part.getPelota());
				}
				else { //la pelota no esta en mi zona
					movimientoAvanzado(c, posicionDeMarcaje(c), PRIORIDAD_BAJA);
				}
			} 
		}
		else { //la pelota no la tiene nadie
			if (dentroZonaDinamica(c, part.getPelota().getX(), part.getPelota().getY())) { //pelota en mi zona
				irAPorBalon(c);
				c.cogerPelota(part.getPelota());
			}
			else {//la pelota no esta en mi zona
				movimientoAvanzado(c, centroZonaDinamica(c), PRIORIDAD_BAJA);
			}
		}
	}
	
	private boolean estoyCansado(Convocado c) {
		if (c.getCansancio() < (10*CONF_CANSANCIO)) {
			return true;
		}		
		return false;
	}
	
	private boolean estoyPresionado(Convocado c) {
		int presion = presionFutbolista(c);
		if (presion > (50*CONF_PRESION)) {
			return true;
		}
		return false;
	}
	
	private boolean limiteZonaDinamica(Convocado c) {
		if (c.getCampo() == Convocado.CAMPO_DERECHO) {
			if (c.getPosX() < c.getZonaCampoXmin()) {
				return true;
			}
		}
		else {
			if (c.getPosX() > c.getZonaCampoXmax()) {
				return true;
			}
		}
		return false;
	}
	
	private void avanzarAPorteriaEnemiga(Convocado c) { //prioridad alta pq se da por sentado que c tiene el balon
		XYZ porteria = new XYZ(0, 350, 0);
		if (c.getCampo() == Convocado.CAMPO_IZQUIERDO) {
			porteria.setX(1000);
		}
		movimientoAvanzado(c, porteria, PRIORIDAD_ALTA);
	}
	
	private void irAPorBalon(Convocado c) {
		int parada = 0;
		int prioridad = PRIORIDAD_MEDIA;
		int vel = CORRER;
		if (c.getPosicion() == 0) {
			parada = c.getParadaPartido();
			prioridad = PRIORIDAD_ALTA;
			vel = ESPRINTAR;
		}
		XYZ intercepcion = part.getPelota().posicionDeIntercepcion(calculaDistanciaMovimiento(c, vel), c.getPosicionActual(), parada);
		movimientoAvanzado(c, intercepcion, prioridad);
	}
	
	
	private void movimientoAvanzado(Convocado c, XYZ objetivo, int prioridad) {
		int modoVel;
		double cansancio = c.getCansancio();
		switch (prioridad) {
		case PRIORIDAD_BAJA:
			if (cansancio < (20*CONF_CANSANCIO)) {
				modoVel = ANDAR;
			}
			else {
				modoVel = CORRER;
			}
			break;
		case PRIORIDAD_MEDIA:
			if (cansancio < (30*CONF_CANSANCIO)) {
				modoVel = CORRER;
			}
			else {
				modoVel = ESPRINTAR;
			}
			break;
		case PRIORIDAD_ALTA:
			if (cansancio < (10*CONF_CANSANCIO)) {
				modoVel = CORRER;
			}
			else {
				modoVel = ESPRINTAR;
			}
			break;
		default: 
			modoVel = CORRER;
			break;
		
		}
		c.moverConvocadoA(calculaMovimiento(c, objetivo, modoVel));
	}
	
	private void pase(Convocado origen, Convocado destinoPase) {
		origen.pasarA(destinoPase, part.getPelota());
		double x = destinoPase.getPosX();
		Vector<Convocado> futbolistas;
		if (destinoPase.getCampo() == part.getLocales().firstElement().getCampo()) {
			futbolistas = part.getVisitantes();
		}
		else {
			futbolistas = part.getLocales();
		}
		compruebaFueraDeJuego(futbolistas, x);
	}
	
	private void compruebaFueraDeJuego(Vector<Convocado> futbolistas, double x) {
		if (futbolistas.firstElement().getCampo() == Convocado.CAMPO_DERECHO) { //buscar X mayor
			double maxX = 0;
			double provX = 0;
			for (int i = 0; i < futbolistas.size(); i++) {
				if (futbolistas.elementAt(i).getPosicion() != 0) {
					provX = futbolistas.elementAt(i).getPosX();
				}
				if (provX > maxX) {
					maxX = provX;
				}
			}
			if (x > maxX) {
				//fuera de juego
				part.setProtagonistaEvento(futbolistas.firstElement());
				Arbitraje.getInstancia().fueraDeJuego(Convocado.CAMPO_DERECHO);
				part.setEvento(Arbitraje.FUERA_DE_JUEGO);
			}
		}
		else {
			double minX = 10000;
			double provX = 10000;
			for (int i = 0; i < futbolistas.size(); i++) {
				if (futbolistas.elementAt(i).getPosicion() != 0) {
					provX = futbolistas.elementAt(i).getPosX();
				}
				if (provX < minX) {
					minX = provX;
				}
			}
			if (x < minX) {
				//fuera de juego
				part.setProtagonistaEvento(futbolistas.firstElement());
				Arbitraje.getInstancia().fueraDeJuego(Convocado.CAMPO_IZQUIERDO);
				part.setEvento(Arbitraje.FUERA_DE_JUEGO);
			}
		}
	}
	
	public double calculaDistanciaMovimiento(Convocado c, int modoVel) {
		double distPosible;
		int vel = c.getVelocidadPartido();
		double velMax;
		double velMin;
		if (c.getCansancio() > 3) {
			switch (modoVel) {
			case ANDAR: 
				velMax = ANDAR_MAXIMO;
				velMin = ANDAR_MINIMO;
				break;
			case CORRER:
				velMax = CORRER_MAXIMO;
				velMin = CORRER_MINIMO;
				break;
			case ESPRINTAR:
				velMax = ESPRINT_MAXIMO;
				velMin = ESPRINT_MINIMO;
				break;
			default:
				velMax = 0;
				velMin = 0;
				break;
			}
		}
		else {
			velMax = CANSADO_MAXIMO;
			velMin = CANSADO_MINIMO;
		}
		distPosible = (((velMax-velMin)*vel)/RANGO_MAXIMO) + velMin;
		distPosible = (distPosible*PUNTOS_POR_METRO)*SEGS_ITERACION; //displazamiento maximo posible a esta velocidad
		if (c.getPosesion()) { //si tengo la pelota, voy mas lento
			distPosible *= 0.75;
		}
		return distPosible;
	}
	
	//Calcula a donde a de ir
	public XYZ calculaMovimiento(Convocado c, XYZ objetivo, int modoVel) { //depende de la velocidad del jugador (getvel) y reduce cansancio
		XYZ actual = c.getPosicionActual();
		double distPosible = calculaDistanciaMovimiento(c, modoVel);
		double distObjetivo = Math.hypot(actual.getX()-objetivo.getX(), actual.getY()-objetivo.getY());
		XYZ resultado = new XYZ();
		if (distPosible >= distObjetivo) {
			resultado.setX(objetivo.getX());
			resultado.setY(objetivo.getY());
		}
		else {
			double coef = distObjetivo/distPosible;
			resultado.setX(((objetivo.getX()-actual.getX())/coef)+actual.getX());
			resultado.setY(((objetivo.getY()-actual.getY())/coef)+actual.getY());
		}
		double distRecorrida = Math.hypot(actual.getX()-resultado.getX(), actual.getY()-resultado.getY());
		actualizaCansancio(c, distRecorrida, modoVel);
		return resultado;
	}
	
	public void actualizaCansancio(Convocado c, double dist, int modo_vel) {
		if (c.getCansancio() <= 0) c.setCansancio(c.getCansancio() + RECUPERACION_CANSANCIO);
		double multCans;
		switch (modo_vel) {
			case ANDAR: multCans = 1; break;
			case CORRER: multCans = 2; break;
			case ESPRINTAR: multCans = 3; break;
			default: multCans = 0; break;
		}
		double cansancio = dist*CANSANCIO_PUNTO*multCans;
		double cansancioconvocado = c.getCansancio();
		cansancioconvocado = cansancioconvocado-cansancio; 
		if (((double)c.getResistenciaPartido()-cansancioconvocado) > RECUPERACION_CANSANCIO) cansancioconvocado += RECUPERACION_CANSANCIO;
		else cansancioconvocado = (double)c.getResistenciaPartido();
		c.setCansancio(cansancioconvocado);
	}
	
	private boolean convocadoJugadorEnFueraDeJuego(Convocado c) {
		Vector<Convocado> futbolistas;
		double x = c.getPosX();
		if (c.getCampo() == part.getLocales().firstElement().getCampo()) {
			futbolistas = part.getVisitantes();
		}
		else {
			futbolistas = part.getLocales();
		}
		double xf;
		for (int i = 0; i < futbolistas.size(); i++) {
			xf = futbolistas.elementAt(i).getPosX();
			if (c.getCampo() == Convocado.CAMPO_DERECHO) {
				if (x < xf) {
					return true;
				}
			}
			else {
				if (x > xf) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	//Funcion heuristica para saber cuanto buena es la posicion del companyero
	private double heuristicoOpcionDePase(Convocado ConvocadoActual, Convocado PosiblePaseAConvocado){
		double heuristico;
		Random r = new Random();
		heuristico = 5000;
		double distanciaPase = ConvocadoActual.getPosicionActual().distanciaEnPlano(PosiblePaseAConvocado.getPosicionActual());
		int intercepcion = rivalesEnOpcionDeIntercepcion(ConvocadoActual.getPosicionActual(), PosiblePaseAConvocado.getPosicionActual(), 50, ConvocadoActual.getCampo());
		double distanciaPorteria = distanciaPorteriaRival(PosiblePaseAConvocado);
		boolean hayFueraJuego = convocadoJugadorEnFueraDeJuego(PosiblePaseAConvocado); 
		
		
		int puntuacionCompanero = calculaNivelConvocado(PosiblePaseAConvocado);
		int puntuacionPresionado = presionFutbolista(PosiblePaseAConvocado);
		int puntuacionDistancia = ((int)distanciaPase)/10;
		int puntuacionIntercepcion = intercepcion*30;
		int puntuacionDistanciaPorteria = ((int)distanciaPorteria)/10;
		int puntuacionRandom = r.nextInt(200);
		int puntuacionFueraJuego = 0;
		if (hayFueraJuego) {
			puntuacionFueraJuego = 100;
		}
		
		//ponderaciones del config
		puntuacionCompanero *= CONF_NIVEL_COMPANERO;
		puntuacionPresionado *= CONF_PRESION;
		puntuacionDistancia *= CONF_DISTANCIA_PASE;
		puntuacionIntercepcion *= CONF_INTERCEPCION;
		puntuacionDistanciaPorteria *= CONF_DISTANCIA_PORTERIA;
		puntuacionFueraJuego *= CONF_FUERA_JUEGO;
		puntuacionRandom *= CONF_RANDOM;
		if (puntuacionRandom > 100) {
			puntuacionRandom -= 200;
		}
		
		heuristico += puntuacionCompanero;
		heuristico -= puntuacionPresionado;
		heuristico -= puntuacionDistancia;
		heuristico -= puntuacionIntercepcion;
		heuristico -= puntuacionDistanciaPorteria;
		heuristico -= puntuacionFueraJuego;
		heuristico += puntuacionRandom;
		
		return heuristico;
	}
	
	//Funcion para seleccionar el companyero de equipo
	private Convocado seleccionCompaneroPase(Convocado ConvocadoActual, int equipoLocalOVisitante){
		Convocado ret;
		//Nota de corte para decidir mejor quien es la opcion mas aconsejada
		double maxHeuristico= -30000;
		double tmpHeuristico= -30000;
		
		Vector <Convocado> aEvaluacion;
		if (equipoLocalOVisitante == LOCAL) aEvaluacion = part.getLocales();
		else aEvaluacion = part.getVisitantes();
		ret = aEvaluacion.elementAt(0); //inicializacion de seguridad;
		//Recorro todos los que no sean yo. Para saber si soy yo me comparo con mi posicion
		for(int i=0; i<aEvaluacion.size() ; i++){
			
			if(aEvaluacion.elementAt(i) != ConvocadoActual){
				
				tmpHeuristico = heuristicoOpcionDePase(ConvocadoActual,aEvaluacion.elementAt(i));
				if(tmpHeuristico > maxHeuristico){
					maxHeuristico = tmpHeuristico;
					ret = aEvaluacion.elementAt(i);
				}
			}
		}
		return ret;
	}
	

	//Funcion para saber si un jugador esta cerca de otro. Propongo llamarla zonaPersonalOcupada. Determina si dos jugadores se estan pisando
	private XYZ zonaOcupada(XYZ zona, XYZ posicionJugador){
		Vector <Convocado> locales = part.getLocales();
		Vector <Convocado> visitantes = part.getVisitantes();
		for(int i=0; i<locales.size(); i++){
			//Si hay colision y no es conmigo mismo
			if((colision(locales.elementAt(i).getPosicionActual(),zona)) &&
					(locales.elementAt(i).getPosicionActual().superposicion(posicionJugador) == false)) {
				return locales.elementAt(i).getPosicionActual();
			}
		}
		for(int i=0; i<visitantes.size(); i++){
			//Si hay colision y no es conmigo mismo
			if((colision(visitantes.elementAt(i).getPosicionActual(),zona)) &&
					(visitantes.elementAt(i).getPosicionActual().superposicion(posicionJugador) == false)) {
				return visitantes.elementAt(i).getPosicionActual();
			}
		}
		return null;
	}
	
	
	private XYZ posicionIntercepcionPortero(int campo) {
		XYZ posicionPelota = part.getPelota().getXYZ();
		XYZ puntoFinal = new XYZ (0, 350, 0);
		double x;
		if (campo == Convocado.CAMPO_DERECHO) {
			puntoFinal.setX(1100);
			x = 980;
		}
		else {
			puntoFinal.setX(-100);
			x = 20;
		}
		//y = mx+n <- equacion explicita de la recta
		//posicionPelota.getY() = posicionPelota.getX() * m + n
		//puntoFinal.getY() = puntoFinal.getX() * m + n
		//despejamos la m y la n con la siguiente llamada
		Vector<Double> mn = resuelveEquacionSimple(posicionPelota.getX(), 1, posicionPelota.getY(), puntoFinal.getX(), 1, puntoFinal.getY());
		if (mn.firstElement() == 0.0 && mn.lastElement() == 0.0) {
			//funcion sin solucion
			XYZ solucion = new XYZ(0, 0, 0);
			return solucion;
		}
		//ahora tenemos y = mn.firstElement()*x + mn.lastElement()
		//forzamos la x y obtenemos la y para saber donde colocar al portero
		double y = mn.firstElement()*x + mn.lastElement();
		XYZ solucion = new XYZ(x, y, 0);
		return solucion;
	}
	
	
	
	private int rivalesEnOpcionDeIntercepcion(XYZ origen, XYZ destino, double distancia, int campo) {
		//origen y destino son los puntos desde los que se calcula la intercepcion. Distancia es el rango desde la linea
		//de intercepcion a partir de la cual se considera que un rival esta en opcion de intercepcion. Campo es el campo
		//de los jugadores AMIGOS
		Vector<Convocado> rivales;
		if (part.getLocales().firstElement().getCampo() == campo) {
			rivales = part.getVisitantes();
		}
		else {
			rivales = part.getLocales();
		}
		
		Vector<Double> mnOriginal = resuelveEquacionSimple(origen.getX(), 1, origen.getY(), destino.getX(), 1, destino.getY());
		double mOriginal = mnOriginal.firstElement();
		double nOriginal = mnOriginal.lastElement();
		if ((mOriginal == 0.0 && nOriginal == 0.0)  || (mOriginal == 0.0)) {
			//funcion sin solucion
			return 0;
		}
		double longitudSegmento = origen.distanciaEnPlano(destino);
		double mPerp = -1/mOriginal;
		double y;
		double nPerp;
		Vector<Double> xyIntersec;
		int contador = 0;
		for (int i = 0; i < rivales.size(); i++) {
			y = mPerp*rivales.elementAt(i).getPosX();
			nPerp = rivales.elementAt(i).getPosY() - y;
			
			xyIntersec = resuelveEquacionSimple(-mOriginal, 1, nOriginal, -mPerp, 1, nPerp);
			if (xyIntersec.firstElement() != 0.0 || xyIntersec.lastElement() != 0.0) { //ecuacion con solucion
				if (Math.hypot(xyIntersec.firstElement()-origen.getX(),  //interseccion DENTRO del segmento
						xyIntersec.lastElement()-origen.getY()) <= longitudSegmento && 
						Math.hypot(xyIntersec.firstElement()-destino.getX(), 
								xyIntersec.lastElement()-destino.getY()) <= longitudSegmento) { 
					if (Math.hypot(rivales.elementAt(i).getPosX()-xyIntersec.firstElement(), 
							rivales.elementAt(i).getPosY()-xyIntersec.lastElement()) <= distancia) { //dentro del rango
						contador++;
					}
				}
			}
		}
		return contador;
	}
	
	private int cantidadDePosiblesPasesInterceptados(XYZ posicion, double distancia, int campo) {
		//dada una posicion, una distancia (rango de intercepcion) y el campo AMIGO,
		//devuelve la cantidad de pases que se pueden interceptar desde "posicion"
		Vector<Convocado> rivales;
		if (part.getLocales().firstElement().getCampo() == campo) {
			rivales = part.getVisitantes();
		}
		else {
			rivales = part.getLocales();
		}
		XYZ origen = part.getPelota().getXYZ();
		XYZ destino;
		Convocado tienePelota = part.getPelota().getConvocado();
		int contador = 0;
		for (int i = 0; i < rivales.size(); i++) {
			if (rivales.elementAt(i) != tienePelota) {
				destino = rivales.elementAt(i).getPosicionActual();
				Vector<Double> mnOriginal = resuelveEquacionSimple(origen.getX(), 1, origen.getY(), destino.getX(), 1, destino.getY());
				double mOriginal = mnOriginal.firstElement();
				double nOriginal = mnOriginal.lastElement();
				if ((mOriginal != 0.0 || nOriginal != 0.0)  && (mOriginal != 0.0)) { //funcion con solucion
					double longitudSegmento = origen.distanciaEnPlano(destino);
					double mPerp = -1/mOriginal;
					double y;
					double nPerp;
					y = mPerp*posicion.getX();
					nPerp = posicion.getY() - y;
					Vector<Double> xyIntersec = resuelveEquacionSimple(-mOriginal, 1, nOriginal, -mPerp, 1, nPerp);
					if (xyIntersec.firstElement() != 0.0 || xyIntersec.lastElement() != 0.0) { //ecuacion con solucion
						if (Math.hypot(xyIntersec.firstElement()-origen.getX(),  //interseccion DENTRO del segmento
								xyIntersec.lastElement()-origen.getY()) <= longitudSegmento && 
								Math.hypot(xyIntersec.firstElement()-destino.getX(), 
										xyIntersec.lastElement()-destino.getY()) <= longitudSegmento) { 
							if (Math.hypot(posicion.getX()-xyIntersec.firstElement(), 
									posicion.getY()-xyIntersec.lastElement()) <= distancia) { //dentro del rango
								contador++;
							}
						}
					}
				}
			}
		}
		return contador;
	}
	
	private void paseAvanzado(Convocado origen, Convocado destino) {
		int altura = 0;
		int fuerza = 0;
		double distancia = origen.getPosicionActual().distanciaEnPlano(destino.getPosicionActual());
		int intercepciones = rivalesEnOpcionDeIntercepcion(origen.getPosicionActual(), destino.getPosicionActual(), 50, origen.getCampo());
		if (distancia < 50) {
			altura = PelotaFutbol.TIRO_RASO;
			if (intercepciones == 0) {
				fuerza = PelotaFutbol.TIRO_SUAVE;
			}
			else {
				fuerza = PelotaFutbol.TIRO_NORMAL;
			}
		}
		else if (distancia < 150) { 
			fuerza = PelotaFutbol.TIRO_NORMAL;
			if (intercepciones == 0) {
				altura = PelotaFutbol.TIRO_RASO;
			}
			else {
				altura = PelotaFutbol.TIRO_MEDIO;
			}
		}
		else {
			if (intercepciones == 0) {
				altura = PelotaFutbol.TIRO_MEDIO;
				fuerza = PelotaFutbol.TIRO_NORMAL;
			}
			else {
				altura = PelotaFutbol.TIRO_ALTO;
				fuerza = PelotaFutbol.TIRO_POTENTE;
			}
		}
		origen.pasarAvanzado(destino, part.getPelota(), fuerza, altura);
		double x = destino.getPosX();
		Vector<Convocado> futbolistas;
		if (destino.getCampo() == part.getLocales().firstElement().getCampo()) {
			futbolistas = part.getVisitantes();
		}
		else {
			futbolistas = part.getLocales();
		}
		compruebaFueraDeJuego(futbolistas, x);
	}
	
	private int presionFutbolista(Convocado c) {
		Vector<Convocado> rivales;
		if (c.getCampo() == part.getLocales().firstElement().getCampo()) {
			rivales = part.getVisitantes();
		}
		else {
			rivales = part.getLocales();
		}
		int presion = 0;
		int regatePropio = c.getRegatePartido();
		int regateRival;
		double distancia;
		for (int i = 0; i < rivales.size(); i++) {
			distancia = rivales.elementAt(i).getPosicionActual().distanciaEnPlano(c.getPosicionActual());
			if (distancia <= 100) {
				regateRival = rivales.elementAt(i).getRegatePartido();
				boolean adelantado = false;
				if (c.getCampo() == Convocado.CAMPO_DERECHO && rivales.elementAt(i).getPosX() < c.getPosX()) {
					adelantado = true;
				}
				else if (c.getCampo() == Convocado.CAMPO_IZQUIERDO && rivales.elementAt(i).getPosX() > c.getPosX()) {
					adelantado = true;
				}
				presion += presionRival(distancia, regatePropio, regateRival, adelantado);
			}
		}
		return presion;
	}
	
	private double distanciaPorteriaRival(Convocado c) {
		XYZ porteria;
		if (c.getCampo() == Convocado.CAMPO_DERECHO) {
			porteria = new XYZ(0, 350, 0);
		}
		else {
			porteria = new XYZ(1000, 350, 0);
		}
		double distancia = porteria.distanciaEnPlano(c.getPosicionActual());
		return distancia;
	}
	
	private boolean decidirCutePorteria(Convocado c) {
		int remate = c.getRematePartido();
		XYZ porteria;
		if (c.getCampo() == Convocado.CAMPO_DERECHO) {
			porteria = new XYZ(0, 350, 0);
		}
		else {
			porteria = new XYZ(1000, 350, 0);
		}
		double distancia = distanciaPorteriaRival(c);
		if (distancia < 75) {
			return true;
		}
		int rivalesInterc = rivalesEnOpcionDeIntercepcion(c.getPosicionActual(), porteria, 50, c.getCampo());
		double mod;
		if (rivalesInterc == 0) {
			mod = 4;
		}
		else if (rivalesInterc == 1) {
			mod = 3;
		}
		else {
			mod = 2;
		}
		double distMin = mod*remate;
		if (distancia <= distMin) {
			return true;
		}		
		return false;
	}
	
	private int presionRival(double distancia, int regatePropio, int regateRival, boolean adelantado) {
		//Rango de soluciones van entre 4 y 63
		double presion = 10;
		double modDistancia = 10-(distancia/10)+1;  //mayor distancia menos me afecta
		modDistancia = 1+(modDistancia/10); //entre 1 a distancia 100 y 2.1 a distancia 0
		double modAdelantado;
		if (adelantado) {  //el rival esta mas cerca que yo de su porteria
			modAdelantado = 1.5;
		}
		else {
			modAdelantado = 0.75;
		}
		double modRegateo;
		if (regatePropio > regateRival) {  //el rival es peor que yo regateando
			double cociente = regatePropio/regateRival;
			if (cociente <= 1.2) {
				modRegateo = 1;
			}
			else if (cociente <= 1.5) {
				modRegateo = 0.75;
			}
			else {
				modRegateo = 0.5;
			}
		}
		else {  //el rival es mejor que yo regateando
			double cociente = regateRival/regatePropio;
			if (cociente <= 1.2) {
				modRegateo = 1;
			}
			else if (cociente <= 1.5) {
				modRegateo = 1.25;
			}
			else {
				modRegateo = 2;
			}
		}
		presion = presion*modDistancia*modAdelantado*modRegateo;
		int res = (int)presion;
		return res;
	}
	
	private Vector<Double> resuelveEquacionSimple(double a1, double b1, double c1, double a2, double b2, double c2) {
		/* a1*x + b1*y = c1
		 * a2*x + b2*y = c2
		*/
		//utilizo la regla de Cramer, para ecuaciones tan pequenas da soluciones muy rapidamente
		Vector<Double> sol = new Vector<Double>();
		double denominador = determinante2x2(a1, b1, a2, b2);
		if (denominador == 0.0) {
			sol.add(0.0);
			sol.add(0.0);
			return sol;
			//ecuacion sin solucion!!
		}
		double numeradorX = determinante2x2(c1, b1, c2, b2);
		double numeradorY = determinante2x2(a1, c1, a2, c2);
		double x = numeradorX/denominador;
		double y = numeradorY/denominador;

		sol.add(x);
		sol.add(y);
		return sol;
	}
	
	private double determinante2x2(double a, double b, double c, double d) {
		/*  |a  b|
		 *  |c  d|
		 */
		double sol = (a*d) - (c*b);
		return sol;
	}
	
	
	private boolean dentroZonaDinamica(Convocado c, double x, double y) {
		if (x >= c.getZonaCampoXmin() && x <= c.getZonaCampoXmax() && 
				y >= c.getZonaCampoYmin() && y <= c.getZonaCampoYmax()) {
			return true;
		}
		return false;
	}
	
	
	private XYZ centroZonaDinamica(Convocado c) {
		XYZ centro = new XYZ(((c.getZonaCampoXmax()-c.getZonaCampoXmin())/2)+c.getZonaCampoXmin(), 
								((c.getZonaCampoYmax() - c.getZonaCampoYmin())/2)+c.getZonaCampoYmin(), 0);
		return centro;
	}
	
	
	private XYZ posicionDeMarcaje(Convocado c) { //se supone que la pelota la tiene un rival
		XYZ resultado = null;
		XYZ provisional;
		double dist = -1;
		double distMax = -1;
		int intercep = -1;
		int intercepMax = -1;
		for (int i = 0; i < 9; i++) {
			provisional = centroMiniZonaDinamica(i, c);
			dist = provisional.distanciaEnPlano(part.getPelota().getXYZ());
			intercep = cantidadDePosiblesPasesInterceptados(provisional, 50, c.getCampo());
			if (intercep > intercepMax) {
				intercepMax = intercep;
				resultado = provisional;
				distMax = dist;
			}
			else if (intercep == intercepMax) {
				if (dist > distMax) {
					resultado = provisional;
					distMax = dist;
				}
			}
		}
		if (resultado == null) { //esto no deberia pasar nunca, pero asi al menos no habra acceso ilegal si pasa
			resultado = new XYZ(0,0,0);
		}
		return resultado;
	}
	
	
	private int calculaNivelConvocado(Convocado c) {
		int remate = c.getRematePartido();
		int regate = c.getRegatePartido();
		int pase = c.getPasePartido();
		int media = (remate+regate+pase)/3;
		return media;
		
	}
	
	private XYZ posicionDeDesmarque(Convocado c) { //se supone que la pelota la tiene un amigo
		XYZ resultado = null;
		XYZ provisional;
		double dist = 10000;
		double distMin = 10000;
		int intercep = 10000;
		int intercepMin = 10000;
		for (int i = 0; i < 9; i++) {
			provisional = centroMiniZonaDinamica(i, c);
			dist = provisional.distanciaEnPlano(part.getPelota().getXYZ());
			intercep = rivalesEnOpcionDeIntercepcion(part.getPelota().getXYZ(), provisional, 50, c.getCampo());
			if (intercep < intercepMin) {
				intercepMin = intercep;
				resultado = provisional;
				distMin = dist;
			}
			else if (intercep == intercepMin) {
				if (dist < distMin) {
					resultado = provisional;
					distMin = dist;
				}
			}
		}
		if (resultado == null) { //esto no deberia pasar nunca, pero asi al menos no habra acceso ilegal si pasa
			resultado = new XYZ(0,0,0);
		}
		return resultado;
	}
	
	private XYZ centroMiniZonaDinamica(int zona, Convocado c) { 
		//dividimos la zona dinamica en 9 minizonas, esta funcion devuelve el centro de cada una, numeradas de 1 a 9
		//y repartidas de izquierda a derecha, de arriba a abajo
		double xMin = c.getZonaCampoXmin();
		double xMax = c.getZonaCampoXmax();
		double yMin = c.getZonaCampoYmin();
		double yMax = c.getZonaCampoYmax();
		XYZ resultado;
		switch (zona) {
			case 1: resultado = new XYZ(((xMax-xMin)/4)+xMin, ((yMax-yMin)/4)+yMin, 0); break;
			case 2: resultado = new XYZ(((xMax-xMin)/2)+xMin, ((yMax-yMin)/4)+yMin, 0); break;
			case 3: resultado = new XYZ((((xMax-xMin)*3)/4)+xMin, ((yMax-yMin)/4)+yMin, 0); break;
			case 4: resultado = new XYZ(((xMax-xMin)/4)+xMin, ((yMax-yMin)/2)+yMin, 0); break;
			case 5: resultado = new XYZ(((xMax-xMin)/2)+xMin, ((yMax-yMin)/2)+yMin, 0); break;
			case 6: resultado = new XYZ((((xMax-xMin)*3)/4)+xMin, ((yMax-yMin)/2)+yMin, 0); break;
			case 7: resultado = new XYZ(((xMax-xMin)/4)+xMin, (((yMax-yMin)*3)/4)+yMin, 0); break;
			case 8: resultado = new XYZ(((xMax-xMin)/2)+xMin, (((yMax-yMin)*3)/4)+yMin, 0); break;
			case 9: resultado = new XYZ((((xMax-xMin)*3)/4)+xMin, (((yMax-yMin)*3)/4)+yMin, 0); break;
			default: resultado = centroZonaDinamica(c); break;
		}
		return resultado;
	}
	
	public XYZ compruebaColision(XYZ objetivo, XYZ actual) {
		XYZ ocupado;
		ocupado = zonaOcupada(objetivo,actual); //devuelve la posicion exacta del jugador que ocupa la zona
		if (ocupado != null) {
			double distObjetivo = Math.hypot(actual.getX()-objetivo.getX(), actual.getY()-objetivo.getY());
			double dist = distObjetivo;
			XYZ provisional = new XYZ();
			dist -= 0.1;
			while (dist > 0) {
				double coef = distObjetivo/dist;
				provisional.setX(((objetivo.getX()-actual.getX())/coef)+actual.getX());
				provisional.setY(((objetivo.getY()-actual.getY())/coef)+actual.getY());
				if (colision(provisional, ocupado)) {
					dist -= 0.1;
				}
				else {
					objetivo.setX(provisional.getX());
					objetivo.setY(provisional.getY());
					dist = 0;
				}
			}
			if (dist < 0) {
				objetivo.setX(actual.getX());
				objetivo.setY(actual.getY());
			}
		}
		return objetivo;
	}

	public boolean colision(XYZ objetivo, XYZ ocupado) {
		double dist = Math.hypot(ocupado.getX()-objetivo.getX(), ocupado.getY()-objetivo.getY());
		if (dist > RANGO_COLISION) return false;
		return true;
	}
	
	/*
	 * Esta funcion devuelve true con una probabilidad de 0.5% suponiendo que ningun rival esta a menos de 50 puntos.
	 * Si un rival esta a menos de 50 puntos, devuelve true con una probabilidad entre 0.5% y 1% (aumenta 0.1% cada 10 puntos).
	 * Esto supone, iterando 40 veces por segundo, una probabilidad entre aprox 18% y 33% de devolver true por segundo.
	 */
	private boolean eventoPosible(Convocado ConvocadoActual, int equipoLocalOVisitante) {
		Random r = new Random();
		int x = r.nextInt(1000);
		Vector <Convocado> aEvaluacion;
		if (equipoLocalOVisitante == VISITANTE) aEvaluacion = part.getLocales();
		else aEvaluacion = part.getVisitantes();
		double minDistancia = 30000;
		double distancia;
		//Recorro todos los rivales
		for(int i=0; i<aEvaluacion.size() ; i++){
			distancia = ConvocadoActual.getPosicionActual().distanciaEnPlano(aEvaluacion.elementAt(i).getPosicionActual());
			if (distancia < minDistancia) {
				minDistancia = distancia;
			}
		}
		int probabilidadBase = 5;
		if (minDistancia < 50) {
			int dist = (int)minDistancia;
			probabilidadBase = probabilidadBase + (5-(dist/10));
		}
		if (x < probabilidadBase) return true;
		return false;
	}
	
	public void setEvento(int evento) {
		part.setEvento(evento);
	}
	
	public int getEvento() {
		return part.getEvento();
	}
	public void setConfiguracion(int cornerPosicion, int zonaDinamica,double cansancio,double presion,double nivelCompanero,double intercepcion,
			double distanciaPase, double distanciaPorteria, double random, double fueraJuego){
		this.CONF_CORNER_POSICION = cornerPosicion;
		this.CONF_ZONA_DINAMICA = zonaDinamica;
		this.CONF_CANSANCIO = cansancio; 
		this.CONF_PRESION = presion;
		this.CONF_NIVEL_COMPANERO = nivelCompanero; //por defecto 1. Peso del nivel del compa�ero para elegir pase
		this.CONF_INTERCEPCION = intercepcion;  //por defecto 2. Peso de los rivales que interceptan un pase
		this.CONF_DISTANCIA_PASE = distanciaPase; //por defecto 1. peso de la distancia para realizar un pase
		this.CONF_DISTANCIA_PORTERIA = distanciaPorteria; //por defecto 3. peso de la distancia a porteria para realizar un pase
		this.CONF_RANDOM = random;  //por defecto 1. Peso de los valores aleatorios para seleccionar pases
		this.CONF_FUERA_JUEGO = fueraJuego; //por defecto 1
	}
	
	
	private void actualizarZonasDinamicas() {
		double x, y;
		x = part.getPelota().getX();
		y = part.getPelota().getY();
		if (x < (1000/3)) {
			reduceZonaXDerecha();
		}
		else if (x > (1000*2/3)) {
			reduceZonaXIzquierda();
		}
		else {
			asignaZonaXOriginal();
		}
		if (y < (700/3)) {
			reduceZonaYAbajo();
		}
		else if (y > (700*2/3)) {
			reduceZonaYArriba();
		}
		else {
			asignaZonaYOriginal();
		}
	}
	
	private void reduceZonaXDerecha() {
		Vector<Convocado> locales = part.getLocales();
		Vector<Convocado> visitantes = part.getVisitantes();
		double dist;
		Convocado c;
		for (int i = 0; i < locales.size(); i++) {
			c = locales.elementAt(i);
			dist = (c.getZonaCampoXmaxOriginal() - c.getZonaCampoXminOriginal())*2/3;
			c.setZonaCampo(c.getZonaCampoXminOriginal(), c.getZonaCampoXminOriginal()+dist, 
					c.getZonaCampoYmin(), c.getZonaCampoYmax());
		}
		for (int i = 0; i < visitantes.size(); i++) {
			c = visitantes.elementAt(i);
			dist = (c.getZonaCampoXmaxOriginal() - c.getZonaCampoXminOriginal())*2/3;
			c.setZonaCampo(c.getZonaCampoXminOriginal(), c.getZonaCampoXminOriginal()+dist, 
					c.getZonaCampoYmin(), c.getZonaCampoYmax());
		}
	}
	
	private void reduceZonaXIzquierda() {
		Vector<Convocado> locales = part.getLocales();
		Vector<Convocado> visitantes = part.getVisitantes();
		double dist;
		Convocado c;
		for (int i = 0; i < locales.size(); i++) {
			c = locales.elementAt(i);
			dist = (c.getZonaCampoXmaxOriginal() - c.getZonaCampoXminOriginal())*2/3;
			c.setZonaCampo(c.getZonaCampoXmaxOriginal()-dist, c.getZonaCampoXmaxOriginal(), 
					c.getZonaCampoYmin(), c.getZonaCampoYmax());
		}
		for (int i = 0; i < visitantes.size(); i++) {
			c = visitantes.elementAt(i);
			dist = (c.getZonaCampoXmaxOriginal() - c.getZonaCampoXminOriginal())*2/3;
			c.setZonaCampo(c.getZonaCampoXmaxOriginal()-dist, c.getZonaCampoXmaxOriginal(), 
					c.getZonaCampoYmin(), c.getZonaCampoYmax());
		}
	}
	
	private void reduceZonaYAbajo() {
		Vector<Convocado> locales = part.getLocales();
		Vector<Convocado> visitantes = part.getVisitantes();
		double dist;
		Convocado c;
		for (int i = 0; i < locales.size(); i++) {
			c = locales.elementAt(i);
			dist = (c.getZonaCampoYmaxOriginal() - c.getZonaCampoYminOriginal())*2/3;
			c.setZonaCampo(c.getZonaCampoXmin(), c.getZonaCampoXmax(), 
					c.getZonaCampoYminOriginal(), c.getZonaCampoYminOriginal()+dist);
		}
		for (int i = 0; i < visitantes.size(); i++) {
			c = visitantes.elementAt(i);
			dist = (c.getZonaCampoYmaxOriginal() - c.getZonaCampoYminOriginal())*2/3;
			c.setZonaCampo(c.getZonaCampoXmin(), c.getZonaCampoXmax(), 
					c.getZonaCampoYminOriginal(), c.getZonaCampoYminOriginal()+dist);
		}
	}
	
	private void reduceZonaYArriba() {
		Vector<Convocado> locales = part.getLocales();
		Vector<Convocado> visitantes = part.getVisitantes();
		double dist;
		Convocado c;
		for (int i = 0; i < locales.size(); i++) {
			c = locales.elementAt(i);
			dist = (c.getZonaCampoYmaxOriginal() - c.getZonaCampoYminOriginal())*2/3;
			c.setZonaCampo(c.getZonaCampoXmin(), c.getZonaCampoXmax(), 
					c.getZonaCampoYmaxOriginal()-dist, c.getZonaCampoYmaxOriginal());
		}
		for (int i = 0; i < visitantes.size(); i++) {
			c = visitantes.elementAt(i);
			dist = (c.getZonaCampoYmaxOriginal() - c.getZonaCampoYminOriginal())*2/3;
			c.setZonaCampo(c.getZonaCampoXmin(), c.getZonaCampoXmax(), 
					c.getZonaCampoYmaxOriginal()-dist, c.getZonaCampoYmaxOriginal());
		}
	}	
	
	private void asignaZonaXOriginal() {
		Vector<Convocado> locales = part.getLocales();
		Vector<Convocado> visitantes = part.getVisitantes();
		Convocado c;
		for (int i = 0; i < locales.size(); i++) {
			c = locales.elementAt(i);
			c.setZonaCampo(c.getZonaCampoXminOriginal(), c.getZonaCampoXmaxOriginal(), 
					c.getZonaCampoYmin(), c.getZonaCampoYmax());
		}
		for (int i = 0; i < visitantes.size(); i++) {
			c = visitantes.elementAt(i);
			c.setZonaCampo(c.getZonaCampoXminOriginal(), c.getZonaCampoXmaxOriginal(), 
					c.getZonaCampoYmin(), c.getZonaCampoYmax());
		}
	}
	
	private void asignaZonaYOriginal() {
		Vector<Convocado> locales = part.getLocales();
		Vector<Convocado> visitantes = part.getVisitantes();
		Convocado c;
		for (int i = 0; i < locales.size(); i++) {
			c = locales.elementAt(i);
			c.setZonaCampo(c.getZonaCampoXmin(), c.getZonaCampoXmax(), 
					c.getZonaCampoYminOriginal(), c.getZonaCampoYmaxOriginal());
		}
		for (int i = 0; i < visitantes.size(); i++) {
			c = visitantes.elementAt(i);
			c.setZonaCampo(c.getZonaCampoXmin(), c.getZonaCampoXmax(), 
					c.getZonaCampoYminOriginal(), c.getZonaCampoYmaxOriginal());
		}
	}
	
}
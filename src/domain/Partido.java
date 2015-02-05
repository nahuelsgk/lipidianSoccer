/*
 * Nahuel Velazco Sanchez
 * Alberto Moreno Vega
 */


package domain;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class Partido {
	//Constantes
	public static final int CAMBIO_LOCAL = 1;
	public static final int CAMBIO_VISITANTE = 2;

	
	private Integer golesVisitante;
	private Integer golesLocal;
	private Integer cambiosVisitante;
	private Integer cambiosLocal;
	private Integer jornada;
	private Boolean jugado;

	private double tiempoJugado;   //tiempo transcurrido SIN eventos
	private double tiempoMostrado; //tiempo mostrado por pantalla, incluyendo eventos
	private double tiempoTotal;   //tiempo de duracion del partido sin eventos (en segundos ficticios)
	private boolean finPartido;
	
	private EquipoFutbol local;
	private EquipoFutbol visitante;
	private PelotaFutbol pelotaUtilizada;
	
	private int alineacionLocal;
	private int alineacionVisitante;
	
	//Variables del estado de la IA
	private int eventoIA;
	private Convocado protagonistaEvento;
	private Convocado ayudanteEvento;
	private Convocado cambio;
	private boolean visitanteIAEquipo;
	private boolean localIAEquipo;
	
	private MeteorologiaGen meteo;
	
	//Variables del estado de arbitraje
	private Convocado agresor;
	private Convocado receptor;


	private Vector<Convocado> locales;
	private Vector<Convocado> visitantes;
	private Vector<Convocado> lesionadosSancionados;
	private Vector<Convocado> localesReserva;
	private Vector<Convocado> visitantesReserva;
	
	private int solicitarCambio;
	
	public Partido(EquipoFutbol local,EquipoFutbol visitante, Integer jornada){
		this.local = local;
		this.visitante = visitante;
		this.lesionadosSancionados = new Vector<Convocado>();
		this.setCambiosLocal(3);
		this.setCambiosVisitante(3);
		this.setGolesLocal(0);
		this.setGolesVisitante(0);
		this.setJornada(jornada);
		this.setJugado(false);
		this.setTiempoMostrado(0);
		this.solicitarCambio = 0;
		this.pelotaUtilizada = new PelotaFutbol(500,300,0,"Bola");
		this.tiempoJugado = 0;
		this.tiempoTotal = 5400; //90 mins
		finPartido = false;
		visitanteIAEquipo = true; //equipo rojo
		localIAEquipo = true; //equipo azul
		this.alineacionLocal = Constantes.ALINEACION_4_3_3;
		this.alineacionVisitante = Constantes.ALINEACION_4_3_3;
		iniciarMeteo();
		try {
			this.pelotaUtilizada.setMeteo(meteo.Vector());
		}
		catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	
	public Integer getGolesVisitante(){
		return this.golesVisitante;
	}
	public void setGolesVisitante(Integer goles){
		this.golesVisitante = goles;
	}


	public Integer getGolesLocal(){
		return this.golesLocal;
	}
	public void setGolesLocal(Integer goles){
		this.golesLocal=goles;
	}


	public Integer getCambiosVisitante(){
		return this.cambiosVisitante;
	}
	public void setCambiosVisitante(Integer cambios){
		this.cambiosVisitante=cambios;
	}


	public Integer getCambiosLocal(){
		return this.cambiosLocal;
	}
	public void setCambiosLocal(Integer cambios){
		this.cambiosLocal = cambios;
	}


	public Integer getJornada(){
		return this.jornada;
	}
	public void setJornada(Integer jornada){
		this.jornada = jornada;
	}

	public void setFinal(){
		finPartido = true;
	}
	
	public boolean getFinal() {
		return finPartido;
	}


	public Boolean getJugado(){
		return this.jugado;
	}
	public Boolean setJugado(Boolean b){
		return this.jugado = b;
	}



	public double getTiempoMostrado(){
		return this.tiempoMostrado;
	}
	public void setTiempoMostrado(double nuevoTiempo){
		this.tiempoMostrado = nuevoTiempo;
	}
	
	public double getTiempoJugado(){
		return this.tiempoJugado;
	}
	public void setTiempoJugado(double nuevoTiempo){
		this.tiempoJugado = nuevoTiempo;
	}
	
	public double getTiempoTotal(){
		return this.tiempoTotal;
	}
	public void setTiempoTotal(double nuevoTiempo){
		this.tiempoTotal = nuevoTiempo;
	}
	
	public EquipoFutbol getEquipoLocal() {
		return this.local;
	}
	
	public EquipoFutbol getEquipoVisitante() {
		return this.visitante;
	}
	

	public void iniciarPartido(){
		//tiempoTotal = 30;
		

		IA.getInstancia().posicionarJugadoresInicioPartido(this);
		Arbitraje.getInstancia().setPartido(this);
		Arbitraje.getInstancia().comenzarPartido();
		
	}
	
	public void iniciarSegundaParte() {
		tiempoJugado = (tiempoTotal/2)+0.25;
		tiempoMostrado = (tiempoTotal/2)+0.25;
		IA.getInstancia().posicionarJugadoresSegundaParte();
		Arbitraje.getInstancia().comenzarSegundaParte();
	}
	
	public Vector<Convocado> getLocales(){
		return this.locales;
	}
	public Vector<Convocado> getVisitantes(){
		return this.visitantes;
	}
	public void setLocales(Vector<Convocado> locales){
		this.locales = locales;
	}
	public void setVisitantes(Vector<Convocado> visitantes){
		this.visitantes = visitantes;
	}
	public Vector<Convocado> getLocalesReserva() {
		return this.localesReserva;
	}
	public Vector<Convocado> getVisitantesReserva() {
		return this.visitantesReserva;
	}
	public void setLocalesReserva(Vector<Convocado> locRes) {
		this.localesReserva = locRes;
	}
	public void setVisitantesReserva(Vector<Convocado> locVis) {
		this.visitantesReserva = locVis;
	}
	
	public void setAlineacionLocal(int alineacion) {
		this.alineacionLocal = alineacion;
	}
	
	public void setAlineacionVisitante(int alineacion) {
		this.alineacionVisitante = alineacion;
	}
	
	public int getAlineacionLocal() {
		return this.alineacionLocal;
	}
	
	public int getAlineacionVisitante() {
		return this.alineacionVisitante;
	}
	
	public PelotaFutbol getPelota(){
		return pelotaUtilizada;
	}
	
	private void iniciarMeteo() {
		Random r = new Random();
		int intensidad = r.nextInt(50);


		int condiciones = r.nextInt(6);
		HashMap<String,String> desc =  new HashMap<String,String>();
		switch (condiciones) {
		case 0: desc.put("Nom", "Pluja"); break;
		case 1: desc.put("Nom", "Sol"); break;
		case 2: desc.put("Nom", "Nuvol"); break;
		case 3: desc.put("Nom", "Vent"); break;
		default: desc.put("Nom", "Neu"); break;
		}

		
		meteo = new MeteorologiaGen((double)intensidad, desc);
	}
	public MeteorologiaGen getMeteo() {
		return this.meteo;
	}
	public void setMeteo(MeteorologiaGen met) {
		this.meteo = met;
	}

	public String[] getEquipos() {
		String s[] = new String[2];
		s[0] = local.getNombre();
		s[1] = visitante.getNombre();
		return s;
	}
	
	public String[] getJugadores(Integer x) {
		String s[] = new String[18];
		EquipoFutbol eq = null;
		if (x == 0) eq = local;
		else eq = visitante;
		for (int i = 0; i < 18; ++i) s[i] = eq.getNombre(i);
		return s;
	}
	
	public int getEvento() {
		return this.eventoIA;
	}
	
	public void setEvento(int evento) {
		this.eventoIA = evento;
	}
	
	public Convocado getProtagonistaEvento() {
		return protagonistaEvento;
	}
	
	public void setProtagonistaEvento(Convocado protagonista) {
		protagonistaEvento = protagonista;
	}
	
	public Convocado getAyudanteEvento() {
		return ayudanteEvento;
	}
	
	public void setAyudanteEvento(Convocado ayudante) {
		ayudanteEvento = ayudante;
	}
	
	public Convocado getAgresor() {
		return this.agresor;
	}
	
	public void setAgresor(Convocado c) {
		this.agresor = c;
	}
	
	public Convocado getReceptor() {
		return this.receptor;
	}
	
	public void setReceptor(Convocado c) {
		this.receptor = c;
	}
	
	public Vector<Convocado> getLesionadosSancionados() {
		return this.lesionadosSancionados;
	}
	
	public void setSolicitarCambio(int localOVisitante) {
		this.solicitarCambio = localOVisitante;
	}
	
	public int getSolicitarCambio() {
		return this.solicitarCambio;
	}
	
	public Convocado getCambio() {
		return this.cambio;
	}
	
	public void setCambio(Convocado c) {
		this.cambio = c;
	}
	
	public void setVisitanteIAEquipo(boolean b) {
		this.visitanteIAEquipo = b;
	}
	
	public void setLocalIAEquipo(boolean b) {
		this.localIAEquipo = b;
	}
	
	public boolean getVisitanteIAEquipo() {
		return this.visitanteIAEquipo;
	}
	
	public boolean getLocalIAEquipo() {
		return this.localIAEquipo;
	}
	
}
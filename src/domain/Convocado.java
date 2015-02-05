/*
 * Nahuel Velazco Sanchez
 * Alberto Moreno Vega
 * 
 */

package domain;

import java.util.Random;


public class Convocado {

	private double coordenadaXMinima;
	private double coordenadaXMaxima;
	private double coordenadaYMinima;
	private double coordenadaYMaxima;
	
	private double coordenadaXMinimaOriginal;
	private double coordenadaXMaximaOriginal;
	private double coordenadaYMinimaOriginal;
	private double coordenadaYMaximaOriginal;
	
	
	private XYZ posicionActual;
	private XYZ posicionEvento;
	private Integer tarjetasAmarillas;
	private Boolean jugando;
	private Boolean posesion;
	private double cansancio;
	
	private int stun; //tiempo de conmocion para el jugador por haber fallado un regateo
	private boolean meHanPasado;
	
	
	private int posicion;
	private int campo;
	private Futbolista esConvocado;

	
	public static final int CAMPO_DERECHO = 1;
	public static final int CAMPO_IZQUIERDO = 2;
	public static final int CONMOCION_REGATEO = 80;
	
	public Convocado(double posX, double posY,Boolean jugando,Futbolista jugador){
		this.jugando=jugando;
		this.posesion=false;
		this.esConvocado=jugador;
		this.cansancio = this.getResistencia();
		jugador.getEquipo();
		posicionActual = new XYZ(posX,posY);
		posicionEvento = new XYZ();
		stun = 0;
		meHanPasado = false;
		tarjetasAmarillas = 0;
		
	}
	public Integer getTarjetasAmarillas(){
		return this.tarjetasAmarillas;
	}
	public void setTarjetasAmarillas(Integer targetas){
		tarjetasAmarillas=targetas;
	}
	public Boolean getJugando(){
		return this.jugando;
	}
	public void setJugando(Boolean nuevoJugando){
		this.jugando = nuevoJugando;
	}

	public Boolean getPosesion(){
		return this.posesion;
	}
	public void setPosesion(Boolean nuevaPosesion){
		this.posesion = nuevaPosesion;
	}
	
	public void moverConvocadoA(XYZ nuevaPosicion){
		getResistenciaPartido();
		this.setPosX(nuevaPosicion.getX());
		this.setPosY(nuevaPosicion.getY());
		this.setPosZ(nuevaPosicion.getZ());
		
	}
	
	public void pasarA(Convocado destinoPase, PelotaFutbol p){
		if (posesion) p.chutar(destinoPase.getPosicionActual().getX(), destinoPase.getPosicionActual().getY(), 
								esConvocado.getPase(), PelotaFutbol.TIRO_NORMAL, PelotaFutbol.TIRO_MEDIO);
		posesion = false;
		destinoPase.setMeHanPasado(true);
		incrementaPasesRealizados();
	}
	
	public void pasarAvanzado(Convocado destinoPase, PelotaFutbol p, int fuerza, int altura){
		if (posesion) p.chutar(destinoPase.getPosicionActual().getX(), destinoPase.getPosicionActual().getY(), 
								esConvocado.getPase(), fuerza, altura);
		posesion = false;
		destinoPase.setMeHanPasado(true);
		incrementaPasesRealizados();
	}
	
	public void chutarAleatoriamente(PelotaFutbol p){
		Random r = new Random();
		int x = r.nextInt(600)+ 60;
		int y = r.nextInt(600) + 100;
		p.chutar(x, y, esConvocado.getRemate(), PelotaFutbol.TIRO_SUAVE, PelotaFutbol.TIRO_RASO);
		posesion = false;
	}
	
	public boolean cogerPelota(PelotaFutbol p) {
		if (p.solicitarPosesion(this)) {
			posesion = true;
			return true;
		}
		if (p.enRango(this.getPosX(), this.getPosY(), this.getAltura())) {
			//si no ha podido coger la pelota, pero esta estaba en rango quiere decir que he fallado al regatear
			conmocionRegateo();
		}
		posesion = false;
		return false;
		
		
	}
	
	public void chutarAPpuerta(PelotaFutbol p) {
		if (campo == CAMPO_DERECHO) p.tiroAPuerta(PelotaFutbol.PORTERIA_IZQUIERDA, esConvocado.getRemate(), PelotaFutbol.TIRO_POTENTE, PelotaFutbol.TIRO_RASO);
		else p.tiroAPuerta(PelotaFutbol.PORTERIA_DERECHA, esConvocado.getRemate(), PelotaFutbol.TIRO_POTENTE, PelotaFutbol.TIRO_RASO);
		posesion = false;
	}
	
	
	
	private int comprobarEfectoMeteo(int atrib, String efecto) {
		String meteo = IA.getInstancia().getPartido().getMeteo().descripcio().get("Nom");
		int ret = atrib;
		double intensidad = 0;
		if (meteo.compareTo("efecto") == 0) {
			intensidad = IA.getInstancia().getPartido().getMeteo().intensitat();
			intensidad /= 20;
		}
		ret -= (int)intensidad;
		return ret;
	}
	
	
	public int getRegate() {
		return esConvocado.getRegate();
	}
	
	public Integer getRegatePartido(){
		int est = esConvocado.getEstrategia();
		int dif = esConvocado.getDificultad();
		int reg = esConvocado.getRegate();
		if (est == Constantes.AGRESIVA) {
			reg *= 1.15;
			if (reg > 100) reg = 100;
		}
		reg = modificadorDificultad(reg,dif);
		
		reg = comprobarEfectoMeteo(reg, "Sol");
		
		return reg;
	}
	
	public double getAltura(){
		return this.esConvocado.getAltura();
	}
	
	public int getPeso() {
		return this.esConvocado.getPeso();
	}
	

	public int getDorsal() {
		return esConvocado.getDorsal();
	}
	
	public int getVelocidad() {
		return esConvocado.getVelocidad();
	}
	
	public int getVelocidadPartido() {
		int est = esConvocado.getEstrategia();
		int dif = esConvocado.getDificultad();
		int vel = esConvocado.getVelocidad();
		if (est == Constantes.NEUTRAL) {
			vel *= 1.15;
			if (vel > 100) vel = 100;
		}
		vel = modificadorDificultad(vel,dif);
		
		vel = comprobarEfectoMeteo(vel, "Pluja");
		vel = comprobarEfectoMeteo(vel, "Neu");
		
		return vel;
	}
	
	public int getRemate() {
		return esConvocado.getRemate();
	}
	
	public int getRematePartido() {
		int est = esConvocado.getEstrategia();
		int dif = esConvocado.getDificultad();
		int rem = esConvocado.getRemate();
		if (est == Constantes.AGRESIVA) {
			rem *= 1.15;
			if (rem > 100) rem = 100;
		}
		rem = modificadorDificultad(rem,dif);
		
		rem = comprobarEfectoMeteo(rem, "Nuvol");
		
		
		return rem;
	}
	
	public int getAgresividad() {
		return esConvocado.getAgresividad();
	}
	
	public int getAgresividadPartido() {
		int est = esConvocado.getEstrategia();
		int dif = esConvocado.getDificultad();
		int agr = esConvocado.getAgresividad();
		if (est == Constantes.AGRESIVA) {
			agr *= 1.15;
			if (agr > 100) agr = 100;
		}
		agr = modificadorDificultad(agr,dif);
		
		agr = comprobarEfectoMeteo(agr, "Sol");
		
		return agr;
	}
	
	public int getResistencia() {
		return esConvocado.getResistencia();
	}
	
	public int getResistenciaPartido() {
		int est = esConvocado.getEstrategia();
		int dif = esConvocado.getDificultad();
		int res = esConvocado.getResistencia();
		if (est == Constantes.NEUTRAL) {
			res *= 1.15;
			if (res > 100) res = 100;
		}
		res = modificadorDificultad(res,dif);
		
		res = comprobarEfectoMeteo(res, "Sol");
		
		return res;
	}
	
	public int getPase() {
		return esConvocado.getPase();
	}
	
	public int getPasePartido() {
		int est = esConvocado.getEstrategia();
		int dif = esConvocado.getDificultad();
		int pas = esConvocado.getPase();
		if (est == Constantes.DEFENSIVA) {
			pas *= 1.15;
			if (pas > 100) pas = 100;
		}
		pas = modificadorDificultad(pas,dif);
		
		pas = comprobarEfectoMeteo(pas, "Nuvol");
		
		return pas;
	}
	
	public int getParada() {
		return esConvocado.getParada();
	}
	
	public int getParadaPartido() {
		int est = esConvocado.getEstrategia();
		int dif = esConvocado.getDificultad();
		int par = esConvocado.getParada();
		if (est == Constantes.DEFENSIVA) {
			par *= 1.15;
			if (par > 100) par = 100;
		}
		par = modificadorDificultad(par,dif);
		
		par = comprobarEfectoMeteo(par, "Vent");
		
		return par;
	}
	
	public int getRecuperacion() {
		return esConvocado.getRecuperacion();
	}
	
	public boolean getMeHanPasado() {
		return meHanPasado;
	}
	
	public void setMeHanPasado(boolean pase) {
		meHanPasado = pase;
	}
	
	private int modificadorDificultad(int atributo, int dificultad) {
		if (dificultad == Constantes.FACIL) {
			atributo *= 0.8;
		}
		else if (dificultad == Constantes.DIFICIL) {
			atributo *= 1.2;
			if (atributo > 100) atributo = 100;
		}
		return atributo;
	}
	
	
	public double getCansancio() {
		return cansancio;
	}

	public void setLesion(int gravedad) {
		int grav = esConvocado.getGravedad();
		if (gravedad > grav) esConvocado.setGravedad(gravedad);
	}
	
	public Futbolista getEsConvocado() {
		return this.esConvocado;
	}
	
	public void setFutbolista(Futbolista f) {
		this.esConvocado = f;
	}
	
	public int getLesion() {
		return esConvocado.getGravedad();
	}
	
	public void incrementaFaltasRecibidas() {
		esConvocado.setFaltasRecibidas(esConvocado.getFaltasRecibidas()+1);
	}
	
	public void incrementaFaltasRealizadas() {
		esConvocado.setFaltasRealizadas(esConvocado.getFaltasRealizadas()+1);
	}
	
	public void incrementaGolesMarcados() {
		esConvocado.incrementaGolesMarcados();
	}
	
	public void incrementaGolesRecibidos() {
		esConvocado.incrementaGolesRecibidos();
	}
	
	public void incrementaPasesRealizados() {
		esConvocado.setPasesRealizados(esConvocado.getPasesRealizados()+1);
	}
	
	public void incrementaPasesBuenos() {
		esConvocado.incrementaPasesBuenos();
	}
	
	public void incrementaParadas() {
		esConvocado.incrementaParadas();
	}
	
	public void incrementaTarjetasAmarillas() {
		esConvocado.incrementaTarjetasAmarillas();
		tarjetasAmarillas++;
		if (tarjetasAmarillas == 2) incrementaTarjetasRojas();
	}
	
	public void incrementaTarjetasRojas() {
		esConvocado.incrementaTarjetasRojas();
		tarjetasAmarillas = 2; //forzar tarjeta roja, pero no cuentan como amarillas en estadisticas
	}
	
	public void incrementaPartidosJugados(int resultado) {
		esConvocado.incrementaPartidosJugados(resultado);
	}
	
	public void setCansancio(double cansancio) {
		this.cansancio = cansancio;
	}
	
	public XYZ getPosicionActual(){
		return this.posicionActual;
	}
	
	public void setPosicionActual(XYZ pos) {
		this.posicionActual = pos;
	}
	
	public void setPosX(double X){
		this.posicionActual.setX(X);
	}
	
	public void setPosY(double Y){
		this.posicionActual.setY(Y);
	}
	
	public void setPosZ(double Z){
		this.posicionActual.setZ(Z);
	}
	
	public double getPosX(){
		return this.posicionActual.getX();
	}
	
	public double getPosY(){
		return this.posicionActual.getY();
	}
	
	public void setZonaCampo(double xmin , double xmax , double ymin , double ymax){
		this.coordenadaXMaxima = xmax;
		this.coordenadaXMinima = xmin;
		this.coordenadaYMaxima = ymax;
		this.coordenadaYMinima = ymin;
	}
	
	public void setZonaCampoOriginal(double xmin , double xmax , double ymin , double ymax){
		this.coordenadaXMaximaOriginal = xmax;
		this.coordenadaXMinimaOriginal = xmin;
		this.coordenadaYMaximaOriginal = ymax;
		this.coordenadaYMinimaOriginal = ymin;
	}
	
	public double getZonaCampoXmin(){
		return this.coordenadaXMinima;
	}
	public double getZonaCampoXmax(){
		return this.coordenadaXMaxima;
	}
	public double getZonaCampoYmin(){
		return this.coordenadaYMinima;
	}
	public double getZonaCampoYmax(){
		return this.coordenadaYMaxima;
	}
	public double getZonaCampoXminOriginal(){
		return this.coordenadaXMinimaOriginal;
	}
	public double getZonaCampoXmaxOriginal(){
		return this.coordenadaXMaximaOriginal;
	}
	public double getZonaCampoYminOriginal(){
		return this.coordenadaYMinimaOriginal;
	}
	public double getZonaCampoYmaxOriginal(){
		return this.coordenadaYMaximaOriginal;
	}
	
	public int getPosicion() {
		return this.posicion;
	}
	
	public void setPosicion(int p) {
		this.posicion = p;
	}
	
	public int getCampo() {
		return this.campo;
	}
	
	public void setCampo(int campo) {
		this.campo = campo;
	}
	
	public boolean comparaEquipo(Convocado c) {
		return (this.campo == c.getCampo());
	}
	
	public void setPosicionEvento(double x, double y) {
		posicionEvento.setX(x);
		posicionEvento.setY(y);
	}
	
	public XYZ getPosicionEvento() {
		return posicionEvento;
	}
	
	public double distanciaPorteriaRival() {
		double dist;
		if (campo == CAMPO_IZQUIERDO) {
			dist = Math.hypot(posicionActual.getX()-1000, posicionActual.getY()-350);
		}
		else {
			dist = Math.hypot(posicionActual.getX()-0, posicionActual.getY()-350);
		}
		return dist;
	}
	
	public void conmocionRegateo() { //he fallado al regatear, me stuneo
		stun = CONMOCION_REGATEO;
	}
	
	public boolean conmocionado() { //si estoy conmocionado decremento conmocion y lo digo, si no digo que no
		if (stun > 0) {
			stun--;
			return true;
		}
		return false;
	}
	
	public void recuperarZonasDeCampoOriginales(){
		this.coordenadaXMinima=coordenadaXMinimaOriginal;
		this.coordenadaXMaxima=coordenadaXMaximaOriginal;
		this.coordenadaYMinima=coordenadaYMinimaOriginal;
		this.coordenadaYMaxima=coordenadaYMaximaOriginal;
	}

	public void recuperarZonasDeCampoOriginalesEjeX(){
		this.coordenadaXMinima=coordenadaXMinimaOriginal;
		this.coordenadaXMaxima=coordenadaXMaximaOriginal;
	}

	public void recuperarZonasDeCampoOriginalesEjeY(){
		this.coordenadaYMinima=coordenadaYMinimaOriginal;
		this.coordenadaYMaxima=coordenadaYMaximaOriginal;
	}

	public void setDinamicasComoOriginales(){
		this.coordenadaXMinimaOriginal = this.coordenadaXMinima;
		this.coordenadaXMaximaOriginal = this.coordenadaXMaxima;
		this.coordenadaYMinimaOriginal = this.coordenadaYMinima;
		this.coordenadaYMaximaOriginal = this.coordenadaYMaxima;
	}
		
	
	public String getNombre() {
		return esConvocado.getNombre();
	}
	
	public int getTipoFutbolista() {
		return esConvocado.getPosicion();
	}
	
	public int getStun() {
		return this.stun;
	}
	
	public void setStun(int st) {
		this.stun = st;
	}
		
}
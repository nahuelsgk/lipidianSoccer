/*
 * Jaume Vinyes Navas
 * Alexandre Vidal Obiols
 */

package controladores;


import java.util.Vector;


import simPartidoBeta.*;
import vistas.FutbolFrame;
import vistas.FutbolFrame.PanelMenu;

public class CtrlPresentacion {
		
	/*
	 * Estructura:
	 * 
	 * CREADORAS
	 * EQUIPOS PREDEFINIDOS
	 * PARTIDA RAPIDA
	 * LIGA - CREACION
	 * LIGA - PERSONALIZAR
	 * LIGA - FLOW Y DATOS
	 * SIMULACION - INIT
	 * SIMULACION - FUNCIONAMIENTO
	 * CAMBIO ALINEACION Y LESION
	 * ESTADISTICAS
	 * RECORDS
	 * CONFIGURACION IA
	 */
	
	private CtrlNuevaLiga nl;
	private CtrlSimulacion cs;
	private CtrlEquiposPredefinidos predef;
	private CtrlPartidaRapida cpr;
	private CtrlLiga li;
	private CtrlRecords rec;
	
	private static CtrlPresentacion instancia;
	private Integer partidoIniciado;
	
	private Vector<Jugador> visitantes;
	private Vector<Jugador> locales;
	private PelotaSim p;
	private Integer jornadas;
	/* Seleccionar equipos partido rapido */
	
	private String equipo1;
	private String equipo2;
	private Integer equipo1Index;
	private Integer equipo2Index;
	private Boolean seleccionado;
	private Boolean estadoGestion;
	private Integer partidaRapida;
	
	private Boolean primerfet;
	private Integer estatAlineacio;
	
	/* Propios del partido */
	private Double tiempo;
	private String visitante;
	private String local;
	private Integer golesVisitante;
	private Integer golesLocal;
	private boolean finPartido;
	private int evento;
	private boolean localUsuario;
	private boolean visitanteUsuario;
	private String meteorologia;
	
	private Integer equiposUs;
	private String equipoPersonalizado;
	private Integer turno;
	private Vector<Jugador> lesionadosSancionados;
	private Integer quienCambia;
	private boolean pausa;
	private boolean esperandoLiga;
	
	public static final int VISITANTE = 800;
	public static final int LOCAL = 801;
	public static final int FUERA = 1;
	public static final int GOL_VISITANTE = 2;
	public static final int GOL_LOCAL = 3;
	public static final int INICIO_PARTIDO = 7;
	public static final int FALTA_NORMAL = 8;
	public static final int FALTA_AMARILLA = 9;
	public static final int FALTA_ROJA = 10;
	public static final int INICIO_SEGUNDA_PARTE = 14;
	public static final int PENALTI = 21;
	public static final int FUERA_DE_JUEGO = 23;

	public static final int DORSAL = 0;
	public static final int TIPO = 1;
	public static final int AGRESIVIDAD = 2;
	public static final int REGATE = 3;
	public static final int VELOCIDAD = 4;
	public static final int RESISTENCIA = 5;
	public static final int REMATE = 6;
	public static final int PARADA = 7;
	public static final int PASE = 8;
	public static final int ALTURA = 9;
	public static final int PESO = 10;
	public static final int EXPERIENCIA = 11;
	public static final int MEDIA = 12;

	
	/*
	 * GET INSTANCE
	 * 
	 */
	
	public static CtrlPresentacion getInstancia() {
		if(instancia == null) {
			instancia = new CtrlPresentacion();
		}
		return instancia;
	}
	
	/*
	 * FIN GET INSTANCE
	 * 
	 *
	
	
	/*
	 * DESTROY OBJECTS
	 *  
	 */
	public void clearLiga() {
		nl.clearLiga();
		predef = null;
	}
	/*
	 * FIN DESTROY OBJECTS
	 * 
	 *
	
	/*
	 * INIT OBJECTS
	 * 
	 */
	
	public void initPredef(boolean b) {
		predef = new CtrlEquiposPredefinidos();
		predef.InitEquiposPredefinidos(b);
	}
	
	/*
	 * FIN INIT OBJECTS
	 * 
	 *
	
	/*
	 * CREADORAS
	 * 
	 */
	public CtrlPresentacion() {
		nl = new CtrlNuevaLiga();
		cpr = new CtrlPartidaRapida();
		rec = new CtrlRecords();
		initPredef(false);
		equipo1Index = 9;
		equipo2Index = 9;
		seleccionado = false;
		primerfet = false;
		visitantes = new Vector<Jugador>();
		locales = new Vector<Jugador>();
		visitanteUsuario = false;
		localUsuario = false;
		cs = CtrlSimulacion.getInstancia();
		li = new CtrlLiga();
		pausa = false;
		turno = 1;
		jornadas = 2;
		estadoGestion = true;
	}
	
		
	public CtrlPresentacion(CtrlSimulacion cs) {
		this.cs = cs;
		visitantes = new Vector<Jugador>();
		locales = new Vector<Jugador>();
	}
	
	
	
	public void setEquipo() {
		li.setEquipo(nl.getEquipo());
	}
	
	/*
	 * FIN CREADORAS
	 * 
	 *
	
	/*
	 * EQUIPOS PREDEFINIDOSf
	 * 
	 */
	public int filtrarIndex(int n) { // Para cuando se han quitados elementos de la lista.
		if (equipo1Index <= n) n++;
		if (equipo2Index <= n) n++;
		return n;
	}
	
	public String[] getEquiposPredefinidos() {
		return predef.getNombresPredefinidos();
	}
	
	public String[] getEquiposPredefinidosLibres() {
		return predef.getNombresPredefinidosLibres();
	}
	
	public void setAgafats(String s, boolean b) {
		predef.setAgafats(s, b);
	}
	
	
	public String[] getNombresJugadoresPredefinidos(int n) {
		return predef.getNombresJPredefinidos(n);
	}
	
	public String[] getNombresJugadoresPredefinidosString(String s) {
		return predef.getNombresJPredefinidosString(s);
	}
	
	public String[] getNombresJugadoresEquipo1() {

		if (estatAlineacio != null && estatAlineacio == 2) {
			return li.getNombresFutbolistasValidos();
		} else if (estatAlineacio != null && estatAlineacio == 0){
			return cpr.getNombresEq1();
		} else if (estatAlineacio != null && estatAlineacio == 3) {
			return cs.getNombresEq1();
		} else if (estatAlineacio != null && estatAlineacio == 4) {
			return cs.getNombresEq2();
		} else if (estatAlineacio != null && estatAlineacio == 1) {
			return cpr.getNombresEq2();
		}
		
		return cpr.getNombresDummy();
	}
	
	public String[] getNombresJugadoresEquipo2() {
		if (equipo2Index < 9) return predef.getNombresJPredefinidos(equipo2Index);
		return predef.getNombresJPredefinidos(1);
	}
	
	public int getDorsalPredef(int x, int y) {
		return predef.getDorsal(x, y);
	}
	
	public int getMediaPredef(int x, int y) {
		return predef.getMedia(x, y);
	}
	
	public int getPosicionPredef(int x, int y) {
		return predef.getPosicion(x, y);
	}
	
	public float getAlturaPredef(int x, int y) {
		return predef.getAltura(x, y);
	}
	
	public int getPesoPredef(int x, int y) {
		return predef.getPeso(x, y);
	}
	
	public int getParadaPredef(int x, int y) {
		return predef.getParada(x,y);
	}

	public int getRematePredef(int x, int y) {
		return predef.getRemate(x, y);
	}
	
	public int getResistenciaPredef(int x, int y) {
		return predef.getResistencia(x, y);
	}
	
	public int getVelocidadPredef(int x, int y) {
		return predef.getVelocidad(x, y);
	}
	
	public int getRegatePredef(int x, int y) {
		return predef.getRegate(x, y);
	}
	
	public int getAgresividadPredef(int x, int y) {
		return predef.getAgresividad(x, y);
	}
	
	public int getPasePredef(int x, int y) {
		return predef.getPase(x, y);
	}
	
	public Integer getExperiencia(String eq, int y) {
		return predef.getExperiencia(eq, y);
	}
	
	public int getPredefinidosString(String nombreEquipo, int indice, int getter) {
		return predef.getPredefinidosString(nombreEquipo, indice, getter);
	}
	
	public float getAlturaString(String s, int indice) {
		return predef.getAlturaString(s, indice);
	}
	
	public void setEquipoPredefinido(String equipo, String nombre, String campo, String localidad) {
		predef.setEquipoPredefinido(equipo, nombre, campo, localidad);
	}
	
	
	public void modificaJugador(String nombre, String altura,
			String peso, int posicion, String equipo, int y) {
			predef.modificaJugador(nombre,Float.valueOf(altura),Integer.parseInt(peso),posicion,equipo,y);		
	}
	
	
	public void bajarAtributo(int atributo, String eq, int y) {
		predef.bajarAtributo(atributo,eq,y);
	}
	
	public void subirAtributo(int atributo, String eq, int n) {
		predef.subirAtributo(atributo,eq,n);
	}
	
	/*
	 * FIN EQUIPOS PREDEFINIDOS
	 * 
	 */
	
	
	/* PARTIDA RAPIDA
	 * 
	 * 
	 */
	
	public String getPrimero(){
		return equipo1;
	}
	
	public void setPrimero(String s){
		equipo1 = s;
	}
	
	public String getSegundo(){
		return equipo2;
	}
	
	public void setSegundo(String s){
		equipo2 = s;
	}
	
	public Integer getPrimeroIndex(){
		return equipo1Index;
	}
	
	public void setPrimeroIndex(Integer i){
		equipo1Index = i;
	}
	
	public Integer getSegundoIndex(){
		return equipo2Index;
	}
	
	public void setSegundoIndex(Integer i){
		if (i <= equipo1Index) i++;
		equipo2Index = i;
	}

	public Boolean getPrimerfet() {
		return primerfet;
	}
	
	public void setPrimerfet() {
		primerfet = !primerfet;
	}
	
	public Boolean getSeleccionado() {
		return seleccionado;
	}
	
	public void setSeleccionado(Boolean b) {
		seleccionado = b;
	}
		
	
	/*Getters y setters de Partida Rapida
	 * 
	 */
	public void setMeteorologia(String s) {
		meteorologia = s;
	}
	
	public String getMeteorologia() {
		return meteorologia;
	}
	
	public void setVisitanteUsuario(boolean b) {
		this.visitanteUsuario = b;
	}
	public void setLocalUsuario(boolean b) {
		this.localUsuario = b;
	}
	
	public boolean getVisitanteUsuario() {
		return this.visitanteUsuario;
	}
	
	public boolean getLocalUsuario() {
		return this.localUsuario;
	}
	
	public Integer getTiempoPartidaRapida() {
		return cpr.getTiempo();
	}
	
	public void setTiempoPartidaRapida(Integer i) {
		cpr.setTiempo(i);
	}
	
	public void setTipoPartidaRapida(Integer i) {
		cpr.setTipo(i);
	}
	
	public Integer getTipoPartidaRapida() {
		return cpr.getTipo();
	}
	
	public void setDificultadPartidaRapida(Integer i) {
		cpr.setDificultad(i);
	}
	
	public Integer getDificultadPartidaRapida() {
		return cpr.getDificultad();
	}
	
	public void setPartidaRapida(Integer i) {
		partidaRapida = i;
	}
	
	public Integer getPartidaRapida() {
		return partidaRapida;
	}
	
	
	public void setJugadoresPartidaRapida(Integer i) {
		cpr.setJugadores(i);
	}
	
	public Integer getJugadoresPartidaRapida() {
		return cpr.getJugadores();
	}
	
	public void setIaJ1(boolean b) {
		cpr.setIaJ1(b);
	}
	
	public void setIaJ2(boolean b) {
		cpr.setIaJ2(b);
	}
	
	
	/* Comunicacion con el controlador de partida rapida
	 * 
	 */
	
	
	
	public void reset_cpr() {
		cpr = new CtrlPartidaRapida();
	}
	
	public void resetAssig(Integer equipo) {
		cpr.resetAssig();
	}

	public void confirmarEquipo1(Integer x) {
		cpr.setEq1(predef.getEquipo(x));
	}
	
	public void confirmarEquipo2(Integer x) {
		x = filtrarIndex(x);
		cpr.setEq2(predef.getEquipo(x));
	}
	
	public void confirmarEquipo2String(String s) {
		cpr.setEq2(predef.getEquipoNombre(s));
	}
	
	public void setAsignandoActual(Integer i) {
		cpr.setAsignandoActual(i);
	}
	
	public Integer getAsignandoActual() {
		return cpr.getAsignandoActual();
	}
	
	public void iniciaPartido() {
		finPartido = false;
		cpr.iniciaPartido();
	}


	/*
	 * FIN PARTIDA RAPIDA
	 */
	
	
	/*
	 * LIGA - CREACION
	 * 
	 */
	public void nuevaLiga(String nom, Integer temp){
		nl.nuevaLiga(nom, temp);
		initPredef(true);
	}
	
	public void regeneraPredef() {
		initPredef(true);
	}
	
	public String getNombreLiga() {
		return nl.getNombreLiga();
	}
	
	public String getTemporada() {
		return nl.getTemporada();
	}
	
	public void generarCalenario() {
		nl.generarCalenario();
	}
	
	public int getNumeroEquipoSistema() {
		return nl.getNumeroEquipoSistema();
	}
	
	public void agregarEquipos(Integer n, int difi) {
		for(int i = 0; i < n; ++i) {
			nl.addEquipo();
		}
		nl.setDificultad(difi);
	}
	
	public void addEquipo(String eq) {
		nl.addEquipo(eq);
	}
	
	/*
	public String getNombreEquipos(int i) {
		EquipoFutbol e = new EquipoFutbol();
		return e.getRandomName();
	}
	*/

	
	/*
	 * FIN LIGA - CREACION
	 * 
	 */
	
	/*
	 * LIGA - PERSONALIZAR
	 */
	public int getDorsal(int y) {
		return li.getDorsal(y);
	}
	
	public int getMedia(int y) {
		return li.getMedia(y);
	}
	
	public int getPosicion(int y) {
		return li.getPosicion(y);
	}
	
	public int getPase(int y) {
		return li.getPase(y);
	}
	
	public int getAgresividad(int y) {
		return li.getAgresividad(y);
	}
	
	public int getRegate(int y) {
		return li.getRegate(y);
	}
	
	public int getVelocidad(int y) {
		return li.getVelocidad(y);
	}
	
	public int getResistencia(int y) {
		return li.getResistencia(y);
	}
	
	public int getRemate(int y) {
		return li.getRemate(y);
	}
	
	public int getPeso(int y) {
		return li.getPeso(y);
	}
	
	public float getAltura(int y) {
		return li.getAltura(y);
	}
	
	public int getParada(int y) {
		return li.getParada(y);
	}
	
	public int getMediaDorsal(int y) {
		return li.getMediaDorsal(y);
	}
	
	public int getPosicionDorsal(int y) {
		return li.getPosicionDorsal(y);
	}
	
	public int getPaseDorsal(int y) {
		return li.getPaseDorsal(y);
	}
	
	public int getAgresividadDorsal(int y) {
		return li.getAgresividadDorsal(y);
	}
	
	public int getRegateDorsal(int y) {
		return li.getRegateDorsal(y);
	}
	
	public int getVelocidadDorsal(int y) {
		return li.getVelocidadDorsal(y);
	}
	
	public int getResistenciaDorsal(int y) {
		return li.getResistenciaDorsal(y);
	}
	
	public int getRemateDorsal(int y) {
		return li.getRemateDorsal(y);
	}
	
	public int getPesoDorsal(int y) {
		return li.getPesoDorsal(y);
	}
	
	public float getAlturaDorsal(int y) {
		return li.getAlturaDorsal(y);
	}
	
	public int getParadaDorsal(int y) {
		return li.getParadaDorsal(y);
	}
	
	public int getDorsalDorsal(int y) {
		return li.getDorsalDorsal(y);
	}
	
	public int getExperiencia(int y) {
		return li.getExperiencia(y);
	}
	
	public String[] getNombresFutbolistas() {
		return li.getNombresFutbolistas();
	}

	public Integer calculaExperiencia(Integer atributo,Integer y) {
		return li.calculaExperiencia(atributo,y);
	}

	public void bajarAtributo(int atributo, int y) {
		li.bajarAtributo(atributo,y);
	}

	public void subirAtributo(int atributo, int n) {
		li.subirAtributo(atributo,n);		
	}
		
	public String getNombre() {
		return li.getNombre();
	}
	
	public String getCampo() {
		return li.getCampo();
	}
	
	public String getCiudad() {
		return li.getCiudad();
	}
	
	public String getOro() {
		return li.getOro();
	}
	
	
	/*
	 * FIN LIGA - PERSONALIZAR
	 */
	

	/*
	 * LIGA - FLOW Y DATOS
	 */

	public Integer calculaExperiencia(int atributo, String eq, int n) {
		return predef.calculaExperiencia(atributo, eq, n);
	}

	public void setJornadas() {
		this.jornadas = nl.getJornadas();
	}
	
	public String[] getJornadasLiga() {
		String[] s = new String[jornadas];
		for(int i = 0; i < jornadas; ++i) {
			s[i] = "Jornada " + String.valueOf(i+1);
		}
		return s;
	}

	public Integer getEquiposUs() {
		return equiposUs;
	}

	public void setEquiposUs(Integer equiposUs) {
		this.equiposUs = equiposUs;
	}
	
	public Integer getJornadas() {
		return jornadas;
	}

	public void setJornadas(Integer jornadas) {
		this.jornadas = jornadas;
	}
	
	public Integer getTurno() {
		return turno;
	}

	public void setTurno(Integer turno) {
		this.turno = turno;
	}
	
	public String getEquipoPersonalizado() {
		return equipoPersonalizado;
	}

	public void setEquipoPersonalizado(String equipo) {
		this.equipoPersonalizado = equipo;
	}

	public int getJornadaActual() {
		return nl.getJornadaActual();
	}

	public void setOro(int oro) {
		li.setOro(oro);
	}

	/*
	 * ÀFin datos?
	 */

	public void setHorasRestantes(int horas) {
		li.setHorasRestantes(horas);
	}

	public CtrlNuevaLiga getCtrlNuevaLiga() {
		return nl;
	}

	public void setCtrlNuevaLiga(CtrlNuevaLiga nl) {
		this.nl = nl;
	}

	public CtrlLiga getCtrlLiga() {
		return li;
	}

	public void setCtrlLiga(CtrlLiga li) {
		this.li = li;
	}

	public void jugarPartido() {
		nl.jugarPartido();
	}

	public void jugarSistema() {
		nl.jugarSistema();		
	}

	public String[][] getClasificacion() {
		return nl.getClasificacion();
	}

	public void setJornadaActual(int i) {
		nl.setJornadaActual(i);
	}

	public String getNombreEnrenador() {
		return li.getNombreEntrenador();
	}

	public String getNombreFisio() {
		return li.getNombresFisio();
	}
	
	public String getCalendarioJornada(int m) {
		return nl.getCalendarioJornada(m);
	}

	public void setPartidoIniciado(Integer x) {
		this.partidoIniciado = x;
	}
	
	public Integer getPartidoIniciado() {
		return partidoIniciado;
	}

	public void salvarLiga() {
		CtrlGestionLiga cgl = new CtrlGestionLiga();
		cgl.salvarLiga(nl.getLiga());
	}

	public void cargarLiga(String les) {
		CtrlGestionLiga cgl = new CtrlGestionLiga();
		cgl.cargarLiga(les);
	}

	public void eliminarLiga(String les) {
		CtrlGestionLiga cgl = new CtrlGestionLiga();
		cgl.eliminarLiga(les);
		
	}

	public String[] listarLigas() {
		CtrlGestionLiga cgl = new CtrlGestionLiga();
		return cgl.listarLigas();
	}

	public String[][] getAtributosFutbolistas() {
		return li.getAtributosFutbolistas();
	}

	public String getHorasRestantes() {
		return li.getHorasRestantes();
	}

	public String[][] getAtributosFutbolistasEstadisticas(int eq) {
		return nl.getAtributosFutbolistasEstadisticas(eq);
	}

	public String[] getEquiposLiga() {
		return nl.getEquiposLiga();
	}
	
	public String getGolesMarcadosEquipoLiga(int eq) {
		return nl.getGolesMarcadosEquipo(eq);
	}
	
	public String getGolesRecibidosEquipoLiga(int eq) {
		return nl.getGolesRecibidosEquipo(eq);
	}

	public String getTarjetasAmarillasEquipoLiga(int eq) {
		return nl.getTarjetasAmarillasEquipo(eq);
	}

	public String getTarjetaRojaEquipoLiga(int eq) {
		return nl.getTarjetaRojaEquipo(eq);
	}

	public String getPartidosGanadosEquipoLiga(int eq) {
		return nl.getPartidosGanadosEquipo(eq);
	}

	public String getPartidosPerdidosEquipoLiga(int eq) {
		return nl.getPartidosPerdidosEquipo(eq);
	}

	public String getPartidosEmpatadosEquipoLiga(int eq) {
		return nl.getPartidosEmpatadosEquipo(eq);
	}

	public void setExperienciaFutbolista(int exp, int n) {
		li.setExperienciaFutbolista(exp,n);
	}

	public Integer cantidadPorExperiencia(int atributo, int exp, int i) {
		return li.cantidadPorExperiencia(atributo,exp,i);
	}
	
	
	
	
	public void setConvocados(String equipo) {
		nl.setConvocadosPartido(equipo);
	}



	public int getAlineacionLiga() {
		return li.getAlineacion();
	}

	public boolean getEsperandoLiga() {
		return esperandoLiga;
	}

	public void setEsperandoLiga(boolean esperandoLiga) {
		this.esperandoLiga = esperandoLiga;
	}

	public void continuarPartidos() {
		nl.continuarPartidos(false);		
	}

	public void resetConvocadosLiga() {
		li.resetAssig();

	}

	public Boolean getEstadoGestion() {
		return estadoGestion;
	}

	public void setEstadoGestion(Boolean estadoGestion) {
		this.estadoGestion = estadoGestion;
	}

	public CtrlEquiposPredefinidos getCtrlEquiposPredefinidos() {
		return predef;
	}

	public void resetHorasRestantes() {
		li.resetHorasRestantes();
	}

	public String getReglamento() {
		CtrlReglamento c = new CtrlReglamento();
		return c.leerReglamento();
	}

	public int getNumeroEquiposLiga() {
		return nl.getNumeroEquipos();
	}

	public boolean esEquipoValido(String equipo) {
		if(predef.esValido(equipo) && nl.esValido(equipo)) {
			return true;
		}
		else return false;
	}

	
	/*
	 * FIN LIGA - FLOW Y DATOS
	 */
	
	
	/*
	 * SIMULACION - INIT
	 */
	
	public void agregaJugador(int x, int y, int dorsal, int equipo) {
		if (equipo == VISITANTE) {
			Jugador j = new Jugador (dorsal, x, y);
			visitantes.add(j);
		}
		else if (equipo == LOCAL) {
			locales.add(new Jugador (dorsal, x, y));
		}
	}
	
	public void actualizaJugador(int x, int y, int dorsal, int equipo) {
		if (equipo == VISITANTE) {
			for (int i = 0; i < visitantes.size(); ++i) { 
				if (visitantes.elementAt(i).getDorsal() == dorsal) visitantes.elementAt(i).setXY(x, y);
			}
		}
		else if (equipo == LOCAL) {
			for (int i = 0; i < locales.size(); ++i) { 
				if (locales.elementAt(i).getDorsal() == dorsal) locales.elementAt(i).setXY(x, y);
			}
		}
	}
	
	public void reiniciaJugadores() {
        visitantes = new Vector<Jugador>();
        locales = new Vector<Jugador>();
    }

	public void reiniciaLesionadosSancionados() {
        lesionadosSancionados = new Vector<Jugador>();
    }
    
    public void actualizaLesionadosSancionados(int x, int y, int dorsal) {
        lesionadosSancionados.add(new Jugador(dorsal, x, y));
    }
	
    public Vector<Jugador> getLesionadosSancionados() {
        return lesionadosSancionados;
    }
    
	public void agregaPelota(int x, int y, int z) {
		p = new PelotaSim();
		p.setXYZ(x, y, z);
	}
	
	public void actualizaPelota(int x, int y, int z) {
		p.setXYZ(x, y, z);
	}
	
	public void simula() {
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.SIMULACION, true); //sim_partido_beta
	}
	
	public PelotaSim getPelota() {
		return p;
	}
	
	public Vector<Jugador> getVisitantes() {
		return visitantes;
	}
	public Vector<Jugador> getLocales() {
		return locales;
	}
	
	public boolean siguienteMovimiento() {
		return cs.siguienteMovimiento();
	}
	
	/*
	 * FIN SIMULACION - INIT
	 */
	
	/*
	 * SIMULACION - FUNCIONAMIENTO
	 */
	
	public void finalizaPartido(){
		finPartido = true;
	}
	
	public void setFinPartido(boolean b) {
		finPartido = b;
	}
	
	public boolean partidoFinalizado() {
		return finPartido;
	}
	 
	public void actualizaTiempo(Double time) {
		this.tiempo = time;
	}
	
	public Double getTiempo() {
		return this.tiempo;
	}
	
	public void setNombresEquipos(String visitante, String local) {
		this.visitante = visitante;
		this.local = local;
	}

	public String getVisitante() {
		return this.visitante;
	}
	
	public String getLocal() {
		return this.local;
	}
	
	public void setGoles(Integer golesVisitante, Integer golesLocal) {
		this.golesVisitante = golesVisitante;
		this.golesLocal = golesLocal;
	}
	
	public Integer getGolesLocal() {
		return this.golesLocal;
	}
	
	public Integer getGolesVisitante() {
		return this.golesVisitante;
	}

	public void setEvento(int evento) {
		this.evento = evento;
	}
	
	public int getEvento() {
		return this.evento;
	}
	
	/*
	 * FIN SIMULACION - FUNCIONAMIENTO
	 */
	

	
	

	/*
	 * CAMBIO ALINEACION Y LESION
	 */
	public void iniciarAlineaciones() {
		switch(estatAlineacio) {
			case 0: 
				cpr.setAsignandoActual(0);
				cpr.resetAssig();
				break;
			case 1:
				cpr.setAsignandoActual(1);
				cpr.resetAssig();
			case 2: 
				li.generaIDT();
				li.salvaAssig();
				break;
			case 3:
			case 4:
			default:
		}
	}

	public void resetAlineaciones() {
		switch(estatAlineacio) {
			case 0: 
				cpr.resetAssig();
				break;
			case 1:
				cpr.resetAssig();
			case 2: 
				li.salvaAssig();
				break;
			default:
		}
	}
	
	
	public void alineacionAutomatica() {
		switch (estatAlineacio) {
			case 0: cpr.alineacionAutomatica();
					break;
			case 1: cpr.alineacionAutomatica();
					break;
			case 2: li.alineacionAutomatica();
					break;
			default:
		}
	}
	
	public String getNombreEquipo() {
		if (estatAlineacio != null) {
			switch (estatAlineacio) {
				case 0: if (cpr != null) return cpr.getNombreEquipo(0);
				case 1: if (cpr != null) return cpr.getNombreEquipo(1);
				case 2: if (li != null) return li.getNombre();
				case 3: if (cs != null) return cs.getNombreEquipo(0);
				case 4: if (cs != null) return cs.getNombreEquipo(1);
				default:
						return "Ninguno";
			}
		}
		else return "Ninguno";
	}
	
	public String quienOcupaPosicion(Integer n) {
		if (estatAlineacio != null) {
			switch (estatAlineacio) {
				case 0: if (cpr != null) return cpr.quienOcupaPosicion(n);
				case 1: if (cpr != null) return cpr.quienOcupaPosicion(n);
				case 2: if (li != null) return li.quienOcupaPosicion(n);
				case 3: if (cs != null) return cs.quienOcupaPosicion(n);
				case 4: if (cs != null) return cs.quienOcupaPosicion(n);
				default:
						return "Nadie";
			}
		}
		else return "Nadie";
	}
	
	public Integer estaAsignado(Integer n) {
		switch (estatAlineacio) {
			case 0: return cpr.getPosicion(n);
			case 1: return cpr.getPosicion(n);
			case 2: return li.getPosicion(n);
			case 3: return cs.getPosicion(n);
			case 4: return cs.getPosicion(n);
			default:
					return 0;
		}
	}
	
	public void setAssig(String x, Integer y) {
		switch (estatAlineacio) {
			case 0: cpr.setPosicion(x, y); break;
			case 1: cpr.setPosicion(x, y); break;
			case 2: li.setPosicionNombre(x, y); break;
			case 3: cs.setPosicionNombre(x, y); break;	
			case 4: cs.setPosicionNombre(x, y); break;
			default:
		}
	}
	
	public Integer getyaAsignado() {
		return li.getyaAsignado();
	}
	
	public void setyaAsignado(Integer n) {
		li.setyaAsignado(n);
	}
	
	public void setPosicion(Integer x, Integer y) {
		switch (estatAlineacio) {
			case 2: li.setPosicion(x, y); break;
			case 3: cs.setPosicion(x, y); break;	
			case 4: cs.setPosicion(x, y); break;
			default:
		}
	}

	public void unset(Integer n) {
		switch (estatAlineacio) {
			case 0:
				cpr.unset(n);
				break;
			case 1:
				cpr.unset(n);
				break;
			case 2:
				li.unset(n);
				break;
			case 3:
				cs.unset(n);
				break;
			case 4:
				cs.unset(n);
				break;
			default:
				
		}
	}
	
	public void ponerCambio(Integer x, Integer y) {
		cs.ponerCambio(x, y);
	}
	
	public Integer numeroCambios() {
		return cs.numeroCambios();
	}
	
	public Integer getEstatAlineacio() {
		if (estatAlineacio != null) return estatAlineacio;
		return -1;
	}
	
	public void setEstatAlineacio(Integer est) {
		estatAlineacio = est;
		cpr.setAsignandoActual(est);
	}
	
	public Boolean completo() {
		if (estatAlineacio <= 1) return cpr.completo();
		else if (estatAlineacio == 2) return li.completo();
		return false;
	}
	
	public String[] getReservas() {
		if (quienCambia != null) {
			if (quienCambia == 0) return cs.getReservasEq1();
			else if (quienCambia == 1) return cs.getReservasEq2();

		}

			String[] s = new String[1];
			s[0] = "null";
			return s;

	}
	
	public String equipoSolicitante() {
		if (quienCambia != null) {
			if (quienCambia == 0) return cs.getEquipo1();
			else if (quienCambia == 1) return cs.getEquipo2();

		}

			String s = new String();
			s = "null";
			return s;

	}
	
	public Integer getQuienCambia() {
		return quienCambia;
	}
	
	public Integer getStatsEq(Integer n, Integer index) {
		return cpr.getStatsEq(n, index);
	}
	
	public float getAlturaEq(Integer index) {
		return cpr.getAlturaEq(index);
	}
	
	public Integer getAtributoNormal(Integer n, Integer index) {
		return cs.getAtributoNormal(n, index);
	}
	
	public float getAlturaNormal(Integer index) {
		return (float) cs.getAlturaNormal(index);
	}
	
	public Integer getAtributoReserva(Integer n, Integer index) {
		return cs.getAtributoReserva(n, index);
	}

	public void aceptaCambioLesionado(Integer index) {
		cs.aceptaCambioLesionado(index);
	}
	
	public void setPausa(boolean b) {
		pausa = b;
	}
	
	public boolean getPausa() {
		return pausa;
	}
	
public void cambioAlineacion(int equipo) {
		
		//Llama a la ventana de eleccion de alineaciones
		
		if (equipo == CtrlPresentacion.LOCAL){
			estatAlineacio = 3;
		}else {
			estatAlineacio = 4;
		}
		
		cs.iniciaCambios();
    	FutbolFrame.getInstancia().ponerMenu(PanelMenu.ESCOGER_ALINEACION, true);
		
		pausa = true;

		
	}
	
	
	public void setAlineacion(Integer x) {
		switch(estatAlineacio) {
			case 0: 
				cpr.setAlineacion1(x);
				break;
			case 1:
				cpr.setAlineacion2(x);
				break;
			case 2: 
				li.setAlineacion(x);
				break;
			case 3:
				cs.setAlineacion1(x);
				break;
			case 4:
				cs.setAlineacion2(x);
				break;
			default:
		}
	}
	
	public Integer getAlineacion() {
		if (estatAlineacio != null) {
				switch(estatAlineacio) {
					case 0: 
						return cpr.getAlineacion1();
					case 1:
						return cpr.getAlineacion2();
					case 2: 
						return li.getAlineacion();
					case 3:
						return cs.getAlineacion1();
					case 4:
						return cs.getAlineacion2();
					default:

				}
		}
		return 0;
	}
	
	public String getTextoCambiosRestantes() {
		if (estatAlineacio != null) {
			if (estatAlineacio >= 3 && estatAlineacio <= 4) return "Cambios restantes:";
		}
		
		return "";
	}
	
	public String getNumCambiosRestantes() {
		if (estatAlineacio != null) {
			if (estatAlineacio >= 3 && estatAlineacio <= 4) return String.valueOf(cs.getCambiosRestantes());
		}
		
		return "1";
	}
	
	public void reanudaPartido(Integer x) {
		
		if (x == 1) cs.reanudaPartido();
		pausa = false;
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.SIMULACION, false);
	}
	
	public void solicitarCambio(int equipo) { //equipo vale CtrlPresentacion.LOCAL o CtrlPresentacion.VISITANTE

		// Llama a la ventanda de substitucion de jugadores
		
		if (equipo == CtrlPresentacion.LOCAL){
			quienCambia = 0;
		}else {
			quienCambia = 1;
		}

		FutbolFrame.getInstancia().dispose();
  		FutbolFrame.getInstancia().pack();
		
		FutbolFrame.getInstancia().ponerMenu(PanelMenu.ESCOGER_SUBSTITUTO,true);
		FutbolFrame.getInstancia().setVisible(true);
		
		pausa = true;
	}
	
	
	/*
	 * FIN CAMBIO ALINEACION Y LESION
	 */
	


	/* ESTADISTICAS
	 * 
	 */
	public void ventanaEstadisticas() {
		FutbolFrame.getInstancia().dispose();
  		FutbolFrame.getInstancia().pack();
  		FutbolFrame.getInstancia().ponerMenu(PanelMenu.RESULTADO_PARTIDA_RAPIDA, true);
  		FutbolFrame.getInstancia().setVisible(true);
	}
	
	public Integer getGolesMarcadosEquipoSimulacion(Integer x) {
		return CtrlSimulacion.getInstancia().getGolesMarcadosEquipoSimulacion(x);
	}
	
	public Integer getGolesRecibidosEquipoSimulacion(Integer x) {
		return CtrlSimulacion.getInstancia().getGolesRecibidosEquipoSimulacion(x);
	}

	public Integer getTarjetasAmarillasEquipoSimulacion(Integer x) {
		return CtrlSimulacion.getInstancia().getTarjetasAmarillasEquipoSimulacion(x);
	}
	
	public Integer getTarjetasRojasEquipoSimulacion(Integer x) {
		return CtrlSimulacion.getInstancia().getTarjetasRojasEquipoSimulacion(x);
	}
	
	public Integer getPartidosGanados(Integer x) {
		return CtrlSimulacion.getInstancia().getPartidosGanados(x);
	}

	public Integer getPartidosEmpatados(Integer x) {
		return CtrlSimulacion.getInstancia().getPartidosEmpatados(x);
	}
	
	public Integer getPartidosPerdidos(Integer x) {
		return CtrlSimulacion.getInstancia().getPartidosPerdidos(x);
	}
	
	public Integer getPasesBuenos(Integer x, Integer y) {
		return CtrlSimulacion.getInstancia().getPasesBuenos(x, y);
	}
	
	public Integer getPasesRealizados(Integer x, Integer y) {
		return CtrlSimulacion.getInstancia().getPasesRealizados(x, y);
	}
	
	public Integer getFaltasCometidas(Integer x, Integer y) {
		return CtrlSimulacion.getInstancia().getFaltasCometidas(x, y);
	}
	
	public Integer getFaltasRecibidas(Integer x, Integer y) {
		return CtrlSimulacion.getInstancia().getFaltasRecibidas(x, y);
	}
	
	public Float getPorcentajePasesBuenos(Integer x, Integer y) {
		return CtrlSimulacion.getInstancia().getPorcentajePasesBuenos(x, y);
	}
	
	public Integer getPartidoJugados(Integer x, Integer y) {
		return CtrlSimulacion.getInstancia().getPartidosJugados(x, y);
	}
	
	public String[] getEquiposSimulacion() {
		return CtrlSimulacion.getInstancia().getEquiposSimulacion();
	}

	public String[] getJugadoresSimulacion(Integer x) {
		return CtrlSimulacion.getInstancia().getJugadoresSimulacion(x);
	}
	
	public Integer getGolesMarcadosJugadorSimulacion(Integer x, Integer y) {
		return CtrlSimulacion.getInstancia().getGolesMarcadosJugadorSimulacion(x, y);
	}
	
	public Integer getGolesRecibidosJugadorSimulacion(Integer x, Integer y) {
		return CtrlSimulacion.getInstancia().getGolesRecibidosJugadorSimulacion(x, y);
	}

	public Integer getTarjetasAmarillasJugadorSimulacion(Integer x, Integer y) {
		return CtrlSimulacion.getInstancia().getTarjetasAmarillasJugadorSimulacion(x, y);
	}
	
	public Integer getTarjetasRojasJugadorSimulacion(Integer x, Integer y) {
		return CtrlSimulacion.getInstancia().getTarjetasRojasJugadorSimulacion(x, y);
	}



	
	/*
	 * RECORDS
	 */
	
	public int getJugadorMasGolesValue(){
		return rec.getJugadorMasGolesValue();
	}
	public String getJugadorMasGolesNombre(){
		return rec.getJugadorMasGolesNombre();
	}
	
	public int getJugadorTarjetasRojasValue(){
		return rec.getJugadorTarjetasRojasValue();
	}
	public String getJugadorTarjetasRojasNombre(){
		return rec.getJugadorTarjetasRojasNombre();
	}
	
	public int getJugadorTarjetasAmarillasValue(){
		return rec.getJugadorTarjetasAmarillasValue();
	}
	public String getJugadorTarjetasAmarillasNombre(){
		return rec.getJugadorTarjetasAmarillasNombre();
	}
	
	public int getPorteroMasParadasValue(){
		return rec.getPorteroMasParadasValue();
	}
	public String getPorteroMasParadasNombre(){
		return rec.getPorteroMasParadasNombre();
	}
	
	public int getEquipoMasGolesMarcadosValue(){
		return rec.getEquipoMasGolesMarcadosValue();
	}
	public String getEquipoMasGolesMarcadosString(){
		return rec.getEquipoMasGolesMarcadosString();
	}
	
	public int getEquipoMasGoleadoValue(){
		return rec.getEquipoMasGoleadoValue();
	}
	public String getEquipoMasGoleadoString(){
		return rec.getEquipoMasGoleadoString();
	}
	
	public int getEquipoMasPartidosGanadosValue(){
		return rec.getEquipoMasPartidosGanadosValue();
	}
	public String getEquipoMasPartidosGanadosNombre(){
		return rec.getEquipoMasPartidosGanadosNombre();
	}
	public int getEquipoMasPartidosPerdidosValue(){
		return rec.getEquipoMasPartidosPerdidosValue();
	}
	public String getEquipoMasPartidosPerdidosNombre(){
		return rec.getEquipoMasPartidosPerdidosNombre();
	}
	public int getEquipoMasPartidosEmpatadosValue(){
		return rec.getEquipoMasPartidosEmpatadosValue();
	}
	public String getEquipoMasPartidosEmpatadosNombre(){
		return rec.getEquipoMasPartidosEmpatadosNombre();
	}

	
	/*
	 * FIN RECORDS
	 */
	
	/*
	 * CONFIGURACION IA
	 */
	
	
	public int getPosicionCornerValor(){
		
		return CtrlIAConf.getInstancia().getPosicionCornerValor();
	}
	
	public void setPosicionCornerValor(int valor){
		
		CtrlIAConf.getInstancia().setPosicionCornerValor(valor);
		
	}
	
	public int getZonaDinamicaValor() {

		return CtrlIAConf.getInstancia().getZonaDinamicaValor();

	}
	
	public void setZonaDinamicaValor(Integer x) {

		CtrlIAConf.getInstancia().setZonaDinamicaValor(x);
	}


	public double getCansancioValor(){
		return CtrlIAConf.getInstancia().getCansancioValor();
	}
	public void setCansancioValor(double valor){
		CtrlIAConf.getInstancia().setCansancioValor(valor);
	}

	public double getPresionValor(){
		return CtrlIAConf.getInstancia().getPresionValor();
	}
	public void setPresionValor(double valor){
		CtrlIAConf.getInstancia().setPresionValor(valor);
	}

	public double getNivelCompaneroValor(){
		return CtrlIAConf.getInstancia().getNivelCompaneroValor();
	}
	public void setNivelCompaneroValor(double valor){
		CtrlIAConf.getInstancia().setNivelCompaneroValor(valor);
	}

	public double getIntercepcionValor(){
		return CtrlIAConf.getInstancia().getIntercepcionValor();
	}
	public void setIntercepcionValor(double valor){
		CtrlIAConf.getInstancia().setIntercepcionValor(valor);
	}

	public double getDistanciaPaseValor(){
		return CtrlIAConf.getInstancia().getDistanciaPaseValor();
	}
	public void setDistanciaPaseValor(double valor){
		CtrlIAConf.getInstancia().setDistanciaPaseValor(valor);
	}

	public double getDistanciaPorteriaValor(){
		return CtrlIAConf.getInstancia().getDistanciaPorteriaValor();
	}
	public void setDistanciaPorteriaValor(double valor){
		CtrlIAConf.getInstancia().setDistanciaPorteriaValor(valor);
	}

	public double getRandomValor(){
		return CtrlIAConf.getInstancia().getRandomValor();
	}
	public void setRandomValor(double valor){
		CtrlIAConf.getInstancia().setRandomValor(valor);
	}

	public double getFueraJuegoValor(){
		return CtrlIAConf.getInstancia().getFueraJuegoValor();
	}
	public void setFueraValor(double valor){
		CtrlIAConf.getInstancia().setFueraValor(valor);
	}
	
	public void iAConfinit(){
		CtrlIAConf.getInstancia().init();
	}
	/*
	 * FIN CONFIGURACION IA
	 */
	

}

/*
 * Jaume Vinas Navas
 * 
 */


package domain;

import java.util.Vector;


public class Liga {

	private Integer temporada;

	private String nom;

	private Vector<EquipoFutbol> equipos;
	
	private Integer jornadaActual;
	
	private Integer partidoActual;
	
	private Integer dificultad;
	
	private String jornadasIda[][];
	private String jornadasVuelta[][];
	

	private int usuario;

	private Vector<Partido> partidosIda;

	private Vector<Partido> partidosVuelta;
	
	/*
	 * Constructora
	 */
  
	public int getUsuario() {
		return usuario-1;
	}


	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}


	public Liga(Integer temporada, String nom) {
		this.temporada = temporada;
		this.nom = nom;
		equipos = new Vector<EquipoFutbol>();
		partidosIda = new Vector<Partido>();
		partidosVuelta = new Vector<Partido>();
		jornadaActual = 1;
		partidoActual = 1;
		usuario = 0;
	}
	
	
	/*
	 * Setters y Getters
	 */
	
	public Integer getDificultad() {
		return dificultad;
	}


	public void setDificultad(Integer dificultad) {
		this.dificultad = dificultad;
	}


	public Integer getTemporada() {
		return temporada;
	}

	public void setTemporada(Integer temporada) {
		this.temporada = temporada;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Integer getJornadaActual() {
		return jornadaActual;
	}


	public void setJornadaActual(Integer jornadaActual) {
		this.jornadaActual = jornadaActual;
	}


	public Integer getPartidoActual() {
		return partidoActual;
	}


	public void setPartidoActual(Integer partidoActual) {
		this.partidoActual = partidoActual;
	}
	
	
	public Vector<EquipoFutbol> getEquiposDeFutbol(){
		return equipos;
	}
	/*
	 * Metodos
	 */
	
	
	public void generarCalendario() {
		/*
		 * PRE: Se asume que para poder generar el calendario deberemos tener los equipos que
		 * disputaran la liga debidamente asignados a la liga, en caso contrario no funciona.
		 * 
		 * Si el numero de equipos es impar, anadimos un nuevo equipo fantasma X,
		 * Cuando a un equipo le toque jugar contra el equipo fantasma quiere decir
		 * que tiene jornada de descanso.
		 * 
		 */
		
		if(equipos.size()%2 == 1) {
			EquipoFutbol e = new EquipoFutbol();
			e.setNombre("Descanso");
			e.setFantasma(true);
			equipos.add(e);
		}
		
		/*
		 *  Copia de los nombres de todos los equipos que juegan la liga
		 */
		
		/*Vector<String> clubs = new Vector<String>();
		for(int i = 0; i < equipos.size(); ++i) {
			clubs.add(equipos.get(i).getNombre());
		}
			*/	
		int numJornadas = equipos.size()-1;
		int numPartidos = equipos.size()/2;
		
		
		
		jornadasIda = new String[numJornadas*numPartidos][2];
		jornadasVuelta = new String[numJornadas*numPartidos][2];
		
		int q = 0;
		for(int i = 0; i < numJornadas; ++i) {
			for(int j = 0; j < numPartidos; ++j) {
				jornadasIda[q][0] = equipos.get(j).getNombre();
				jornadasIda[q][1] = equipos.get(numJornadas-j).getNombre();
				/*
				 * Creamos el enuentro correspondiente a esta jornada
				 */
				Partido p1 = new Partido(equipos.get(j), equipos.get(numJornadas-j), i+1);
				partidosIda.add(p1);
				jornadasVuelta[q][0] = equipos.get(numJornadas-j).getNombre();
				jornadasVuelta[q][1] = equipos.get(j).getNombre();
				Partido p2 = new Partido(equipos.get(numJornadas-j), equipos.get(j),numJornadas+i+1);
				partidosVuelta.add(p2);
				++q;
			}
			
			/*
			 * Rotacion de los clubs
			 */
			EquipoFutbol ultimo = equipos.lastElement();
			equipos.remove(equipos.size()-1);
			equipos.add(1, ultimo);
		}
		
	}
	
	public Partido getPartido(String equipo) {
		int numJornadas = equipos.size()-1;
		int partidosPorJornada = equipos.size()/2;
		int jornada = 0;
		if(jornadaActual > numJornadas) {
			jornada = jornadaActual - numJornadas;
			//local = jornadasVuelta[partidosPorJornada*(jornada-1)+partido-1][0];
			for(int i = 1; i <= partidosPorJornada; ++i) {
				if(equipo == getEquipoLocalJornadaPartido(jornada, i) || 
				   equipo == getEquipoVisitanteJornadaPartido(jornada, i)) {
					return getPartidoJornadaPartido(jornada,i);
				}
			}
		}
		else {
			//local = jornadasIda[partidosPorJornada*(jornada-1)+partido-1][0];
			for(int i = 1; i <= partidosPorJornada; ++i) {
				if(equipo == getEquipoLocalJornadaPartido(jornadaActual, i) || 
				   equipo == getEquipoVisitanteJornadaPartido(jornadaActual, i)) {
					return getPartidoJornadaPartido(jornadaActual,i);
				}
			}
		}
		return null;
	}
	
	public Partido getPartidoJornadaPartido(Integer jornada, int partido) {
		int numJornadas = equipos.size()-1;
		int partidosPorJornada = equipos.size()/2;
		if(jornada > numJornadas) {
			jornada = jornada - numJornadas;
			return partidosVuelta.get(partidosPorJornada*(jornada-1)+partido-1);
		}
		else {
			return partidosIda.get(partidosPorJornada*(jornada-1)+partido-1);
		}
	}


	public String getEquipoLocalJornadaPartido(Integer jornada, Integer partido) {
		/*
		 * getEquipoLocalJornadaPartido devuelve un String con el nombre del equipo local de la jornada "jornada" i
		 * del numero de partido dentro de la jornda "partido". Tanto partido como jornado son enteros > 0.
		 */
		int numJornadas = equipos.size()-1;
		int partidosPorJornada = equipos.size()/2;
		String local;
		if(jornada > numJornadas) {
			jornada = jornada - numJornadas;
			local = jornadasVuelta[partidosPorJornada*(jornada-1)+partido-1][0];
		}
		else {
			local = jornadasIda[partidosPorJornada*(jornada-1)+partido-1][0];
		}
		return local;
	}

	public String getEquipoVisitanteJornadaPartido(Integer jornada, Integer partido) {
		/*
		 * getEquipoLocalJornadaPartido devuelve un String con el nombre del equipo visitante de la jornada "jornada" i
		 * del numero de partido dentro de la jornda "partido". Tanto partido como jornado son enteros > 0.
		 */
		int numJornadas = equipos.size()-1;
		int partidosPorJornada = equipos.size()/2;
		String visitante;
		if(jornada > numJornadas) {
			jornada = jornada - numJornadas;
			visitante = jornadasVuelta[partidosPorJornada*(jornada-1)+partido-1][1];
		}
		else {
			visitante = jornadasIda[partidosPorJornada*(jornada-1)+partido-1][1];
		}
		return visitante;
	
	}
	

	public void addEquipo() {
		EquipoFutbol e = new EquipoFutbol();
		while(!esValido(e.getNombre())) {
			e = new EquipoFutbol();
		}
		equipos.add(e);
	}

	private boolean esValido(String nombre) {
		for(int i = 0; i < equipos.size(); ++i) {
			if(equipos.get(i).getNombre().equals(nombre)) {
				return false;
			}
		}
		return true;
	}


	public void addEquipo(EquipoFutbol e, Boolean usuario) {
		e.setUsuario(usuario);
		equipos.add(e);
	}
	
	public String getCalendarioJornada(int jornada) {
		int numPartidos = equipos.size()/2;
		String calendari = new String();
		for(int j = 1; j <= numPartidos; ++j) {
			calendari = calendari + getEquipoLocalJornadaPartido(jornada, j);
			calendari = calendari + "  -  ";
			calendari = calendari + getEquipoVisitanteJornadaPartido(jornada, j);
			calendari = calendari + "\n";
		}
		return calendari;
	}


	public Integer getJornadas() {
		return 2*(equipos.size()-1);
	}


	public EquipoFutbol getEquipo() {
		if(usuario < 0) {
			usuario = 0;
		}
		if(usuario == equipos.size()) {
			usuario = 0;
		}
		while(usuario < equipos.size()) {
			if(!equipos.get(usuario).esUsuario()) {
				if(usuario == equipos.size()-1) {
					usuario = 0;
				}
				else {
					usuario++;
				}
			}
			else {
				usuario++;
				return equipos.get(usuario-1);
			}
		}
		return null;
	}


	public String[][] getAtributosFutbolistasEstadisticas(int eq) {
		String[][] at = new String[18][11];
		for(int i = 0; i < 18; ++i) {
			for(int j = 0; j < 11; ++j) {
				switch(j) {
					case 0:
						at[i][j] = equipos.get(eq).getNombre(i);
						break;
					case 1:
						at[i][j] = String.valueOf(equipos.get(eq).getGolesMarcados(i));
						break;
					case 2:
						at[i][j] = String.valueOf(equipos.get(eq).getGolesRecibidos(i));
						break;
					case 3:
						at[i][j] = String.valueOf(equipos.get(eq).getTarjetasAmarillas(i));
						break;
					case 4:
						at[i][j] = String.valueOf(equipos.get(eq).getTarjetasRojas(i));
						break;
					case 5:
						at[i][j] = String.valueOf(equipos.get(eq).getPasesBuenos(i));
						break;
					case 6:
						at[i][j] = String.valueOf(equipos.get(eq).getPasesRealizados(i));
						break;
					case 7:
						at[i][j] = String.valueOf(equipos.get(eq).getPorcentajePasesBuenos(i));
						break;
					case 8:
						at[i][j] = String.valueOf(equipos.get(eq).getFaltasRealizadas(i));
						break;
					case 9:
						at[i][j] = String.valueOf(equipos.get(eq).getFaltasRecibidas(i));
						break;
					case 10:
						at[i][j] = String.valueOf(equipos.get(eq).getPartidosJugados(i));
						break;
										
				}	
			}
		}
		return at;
	}


	public String[] getEquiposLiga() {
		String[] s = new String[1];
		boolean trobat = true;
		for(int i = 0; i < equipos.size(); ++i) {
			if(equipos.get(i).esFantasma()) {
				s = new String[equipos.size()-1];
				trobat = false;
			}
		}
		if(trobat) s = new String[equipos.size()];
		for(int i = 0; i < equipos.size(); ++i) {
			if(!equipos.get(i).esFantasma()) {
				s[i] = equipos.get(i).getNombre();
			}
		}
		return s;
	}
	
	/*
	 * Gestion de datos
	 */
	
	public Vector<Partido> getPartidosIda() {
		return partidosIda;
	}
	
	public Vector<Partido> getPartidosVuelta() {
		return partidosVuelta;
	}
	
	public void setPartidos(Vector<Partido> ida, Vector<Partido> vuelta) {
		this.partidosIda = ida;
		this.partidosVuelta = vuelta;
	}
	
	public void addFutbolistaEquipo(Futbolista f, EstadisticasJugador est, EquipoFutbol e) {
		boolean trobat = false;
		for(int i = 0; i < equipos.size() && !trobat; ++i) {
			if(equipos.get(i).getNombre().equals(e.getNombre())) {
				trobat = true;
				equipos.get(i).setFutbolista(f);
				equipos.get(i).setEstadisticaFutbolista(est,f);
			}
		}
	}
	
	public void addEquipoEstadistica(EstadisticasEquipo est, EquipoFutbol e) {
		boolean trobat = false;
		for(int i = 0; i < equipos.size() && !trobat; ++i) {
			if(equipos.get(i).getNombre().equals(e.getNombre())) {
				trobat = true;
				equipos.get(i).setEstadisticaEquipo(est);
			}
		}
	}
	
	public void addEntrenadorEquipo(Entrenador ent, EquipoFutbol e) {
		boolean trobat = false;
		for(int i = 0; i < equipos.size() && !trobat; ++i) {
			if(equipos.get(i).getNombre().equals(e.getNombre())) {
				trobat = true;
				equipos.get(i).setEntrenador(ent);
				equipos.get(i).generaHorasEntrenador();
			}
		}
	}
	
	public void addFisioEquipo(Fisioterapeuta fisio, EquipoFutbol e) {
		boolean trobat = false;
		for(int i = 0; i < equipos.size() && !trobat; ++i) {
			if(equipos.get(i).getNombre().equals(e.getNombre())) {
				trobat = true;
				equipos.get(i).setFisio(fisio);
			}
		}
	}


	public Integer getGolesMarcados(int eq) {
		return equipos.get(eq).getGolesMarcados();
	}


	public Integer getGolesRecibidos(int eq) {
		return equipos.get(eq).getGolesRecibidos();
	}


	public Integer getTarjetasAmarillas(int eq) {
		return equipos.get(eq).getTarjetasAmarillas();
	}


	public Integer getTarjetasRojas(int eq) {
		return equipos.get(eq).getTarjetasRojas();
	}


	public Integer getPartidosGanados(int eq) {
		return equipos.get(eq).getPartidosGanados();
	}


	public Integer getPartidosPerdidos(int eq) {
		return equipos.get(eq).getPartidosPerdidos();
	}


	public Integer getPartidosEmpatados(int eq) {
		return equipos.get(eq).getPartidosEmpatados();
	}


	public String[][] getJornadasIda() {
		return jornadasIda;
	}


	public void setJornadasIda(String[][] jornadasIda) {
		this.jornadasIda = jornadasIda;
	}


	public String[][] getJornadasVuelta() {
		return jornadasVuelta;
	}


	public void setJornadasVuelta(String[][] jornadasVuelta) {
		this.jornadasVuelta = jornadasVuelta;
	}


	public int getPartidosJornada() {
		return equipos.size()/2;
	}


	public int getNumeroEquipos() {
		for(int i = 0; i < equipos.size(); ++i) {
			if(equipos.get(i).esFantasma()) {
				return equipos.size()-1;
			}
		}
		return equipos.size();
	}


	public void setDificultadEquipo(int dif) {
		for(int i = 0; i < equipos.size(); ++i) {
			equipos.get(i).setDificultad(dif);
		}
	}


	public EquipoFutbol getEquipo(int i) {
		return equipos.get(i);
	}

	public EquipoFutbol getEquipoDatos(String eq) {
		for(int i = 0; i < equipos.size(); ++i) {
			if(equipos.get(i).getNombre() == eq) {
				return equipos.get(i);
			}
		}
		return null;
	}
	
	public void recuperacionJugadores() {
		for(int i = 0; i < equipos.size(); ++i) {
			for(int j = 0; j < 18; ++j) {
				equipos.get(i).getFutbolista(j).recuperacion();
			}
		}
	}


	public int getNumeroEquipoSistema() {
		int n = 0;
		for(int i = 0; i < equipos.size(); ++i) {
			if(!equipos.get(i).esFantasma() && !equipos.get(i).esUsuario()) {
				++n;
			}
		}
		return n;
	}


	public boolean esEquipoValido(String equipo) {
		return esValido(equipo);
	}


	public void resetPuntos() {
		for(int i = 0; i < equipos.size(); ++i) {
			equipos.get(i).setPuntos(0);
		}
	}


	public void resetPartidos() {
		partidosIda = new Vector<Partido>();
		partidosVuelta = new Vector<Partido>();
	}
}
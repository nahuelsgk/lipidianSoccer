/*
 * Jaume Vinyes Navas
 */


package domain;

import java.util.Random;
import java.util.Vector;
import domain.Constantes;
import domain.Futbolista;

public class EquipoFutbol extends Equipo {

	private EstadisticasEquipo estadisticas;
    
    private Liga pertenece;

    private Vector<Futbolista> futbolistas;
    
    private Entrenador entrenador;
    
    private Fisioterapeuta fisio;
    
    private Integer dificultad;
    
    private Boolean usuario;
    
    private Boolean fantasma;
    
    private Integer horasRestantes;
    
    /*
     * Constructora
     */
    

	public EquipoFutbol() {
		super();
		futbolistas = new Vector<Futbolista>();
		dificultad = Constantes.NORMAL;
		estadisticas = new EstadisticasEquipo();
		usuario = false;
		fantasma = false;
		this.nombre = "F.C. " + this.nombre;
		poblar();
		horasRestantes = ((entrenador.getIncrementador()+entrenador.getRebaja())/2)+20;
	}
	
	public EquipoFutbol(Boolean b) {
		super();
		futbolistas = new Vector<Futbolista>();
		dificultad = Constantes.NORMAL;
		estadisticas = new EstadisticasEquipo();
		usuario = false;
		fantasma = false;
		this.nombre = "F.C. " + this.nombre;
	}
	
	public void generaHorasEntrenador() {
		horasRestantes = ((entrenador.getIncrementador()+entrenador.getRebaja())/2)+20;
	}
	
	public String getRandomName() {
		Random r = new Random();
		int n = r.nextInt(100);
		String s = Equipo.NOMBRESE[n];
		return s;
	}
	
	/*
	 * Setters y Getters
	 */
	
	/*
	 *  Inicio Equip
	 */
	
	public void setFantasma(Boolean fantasma) {
		this.fantasma = fantasma;
	}
	
	public void setUsuario(Boolean usuario) {
		this.usuario = usuario;
	}

	public Integer getDificultad() {
		return dificultad;
	}

	public void setDificultad(Integer dificultad) {
		this.dificultad = dificultad;
	}
	
	public Liga getPertenece() {
		return pertenece;
	}
	
	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public Fisioterapeuta getFisio() {
		return fisio;
	}

	public void setFisio(Fisioterapeuta fisio) {
		this.fisio = fisio;
	}

	public void setPertenece(Liga pertenece) {
		this.pertenece = pertenece;
	}
	
	/*
	 * Fin EQUIPO
	 */

	/*
	 *  Inicio FUTBOLISTA
	 */
	
	public Vector<Futbolista> getFutbolistas() {
		return futbolistas;
	}
	
	public int getPase(int n) {
    	return futbolistas.get(n).getPase();
    }
    
    public int getAgresividad(int n) {
    	return futbolistas.get(n).getAgresividad();
    }
    
    public int getRegate(int n) {
    	return futbolistas.get(n).getRegate();
    }
    
    public int getVelocidad(int n) {
    	return futbolistas.get(n).getVelocidad();
    }
    
    public int getResistencia(int n) {
    	return futbolistas.get(n).getResistencia();
    }
    
    public int getRemate(int n) {
    	return futbolistas.get(n).getRemate();
    }
    
    public int getPeso(int n) {
    	return futbolistas.get(n).getPeso();
    }
    
    public float getAltura(int n) {
    	return futbolistas.get(n).getAltura();
    }
    
    public String getNombre(int n) {
    	return futbolistas.get(n).getNombre();
    }
    
    public int getDorsal(int n) {
    	return futbolistas.get(n).getDorsal();
    }
    
    public int getPosicion(int n) {
    	return futbolistas.get(n).getPosicion();
    }

	public int getParada(int n) {
		return futbolistas.get(n).getParada();
	}
    
    public void setPosicion(int n, int posicion) {
    	futbolistas.get(n).setPosicion(posicion);
    }
    
    public void setDorsal(int n, int dorsal) {
    	futbolistas.get(n).setDorsal(dorsal);
    }
    
    public void setNombre(int n, String nombre) {
    	futbolistas.get(n).setNombre(nombre);
    }
    
    public Integer getMedia(int n) {
    	return futbolistas.get(n).getMedia();
    }
	
	
	public Integer calculaExperiencia(Integer atributo,Integer y) {
		return futbolistas.get(y).calculaExperiencia(entrenador.getEstrategia(), entrenador.getRebaja(), atributo);
	}
	
	public Futbolista getFutbolista(int n) {
    	return futbolistas.get(n);
    }
    
	public Integer getExperiencia(int y) {
		return futbolistas.get(y).getExperiencia();
	}
	
	/*
	 *  Fin FUTBOLISTA
	 */
	
	/*
	 *  Inicio Estadisticas EQUIPO
	 */
	
	public Integer getPuntos() {
		return estadisticas.getPuntos();
	}
	
	public void setPuntos(Integer puntos) {
		estadisticas.setPuntos(puntos);
	}
	
	public Integer getGanadosConsec() {
		return estadisticas.getGanadosConsec();
	}
	
	public void setGanadosConsec(Integer ganadosConsec) {
		estadisticas.setGanadosConsec(ganadosConsec);
	}
	
	public Integer getPerdidosConsec() {
		return estadisticas.getPerdidosConsec();
	}
	
	public void setPerdidosConsec(Integer perdidosConsec) {
		estadisticas.setPerdidosConsec(perdidosConsec);
	}
	
	public Integer getPartidosGanados() {
		return estadisticas.getPartidosGanados();
	}
	
	public void setPartidosGanados(Integer partidosGanados) {
		estadisticas.setPartidosGanados(partidosGanados);
	}
	
	public Integer getPartidosEmpatados() {
		return estadisticas.getPartidosEmpatados();
	}
	
	public void setPartidosEmpatados(Integer partidosEmpatados) {
		estadisticas.setPartidosEmpatados(partidosEmpatados);
	}
	
	public Integer getPartidosPerdidos() {
		return estadisticas.getPartidosPerdidos();
	}
	
	public void setPartidosPerdidos(Integer partidosPerdidos) {
		estadisticas.setPartidosPerdidos(partidosPerdidos);
	}
	/*
	 *  Fin estadisticass EQUIPO
	 */
	/*
	 * Inicio estadisticas FUTBOLISTA
	 */
	
	public Integer getPasesBuenos(Integer dorsal) {
		return futbolistas.get(dorsal).getPasesBuenos();
	}

	public Integer getPasesRealizados(Integer dorsal) {
		return futbolistas.get(dorsal).getPasesRealizados();
	}
	
	public Float getPorcentajePasesBuenos(Integer dorsal) {
		return futbolistas.get(dorsal).porcentagePasesBuenos();
	}
	
	public Integer getFaltasRecibidas(Integer dorsal) {
		return futbolistas.get(dorsal).getFaltasRecibidas();
	}
			
	public Integer getFaltasRealizadas(Integer dorsal) {
		return futbolistas.get(dorsal).getFaltasRealizadas();
	}
	
	public Integer getPartidosJugados(Integer dorsal) {
		return futbolistas.get(dorsal).getPartidosJugados();
	}

	
	public Integer getGolesRecibidos(Integer dorsal) {
		return futbolistas.get(dorsal).getGolesRecibidos();
	}
	
	public Integer getGolesMarcados(Integer dorsal) {
		return futbolistas.get(dorsal).getGolesMarcados();
	}

	public Integer getTarjetasAmarillas(Integer dorsal) {
		return futbolistas.get(dorsal).getTarjetasAmarillas();
	}

	public Integer getTarjetasRojas(Integer dorsal) {
		return futbolistas.get(dorsal).getTarjetasRojas();
	}
	
	
	

	/*
	 * Fin estadisticas FUTBOLISTA
	 */
	/*
	 *  Inicio estadisticas GLOBALES
	 */
	
	public Integer getGolesRecibidos() {
		return estadisticas.getGolesRecibidos();
	}
	
	public void setGolesRecibidos(Integer golesRecibidos) {
		estadisticas.setGolesRecibidos(golesRecibidos);
	}

	public Integer getGolesMarcados() {
		return estadisticas.getGolesMarcados();
	}
	
	public void setGolesMarcados(Integer golesMarcados) {
		estadisticas.setGolesMarcados(golesMarcados);
	}
	
	public Integer getTarjetasAmarillas() {
		return estadisticas.getTarjetasAmarillas();
	}
	
	public void setTarjetasAmarillas(Integer tarjetasAmarillas) {
		estadisticas.setTarjetasAmarillas(tarjetasAmarillas);
	}
	
	public Integer getTarjetasRojas() {
		return estadisticas.getTarjetasRojas();
	}
	
	public void setTarjetasRojas(Integer tarjetasRojas) {
		estadisticas.setTarjetasRojas(tarjetasRojas);
	}
	
	/*
	 * Fin estadisticas GLOBALES
	 */

	
	
	
	/*
	 *  Metodos 
	 */

	public EstadisticasEquipo getEstadisticasEquipo() {
		/*
		 * devuelve el objeto EStadisticas Equipo entero.
		 */
		return estadisticas;
    }
    
	public Boolean esUsuario() {
		return usuario;
	}
	
	public Integer getEstrategia() {
		return entrenador.getEstrategia();
	}
	
	public Integer getRebaja() {
		return entrenador.getRebaja();
	}
	
	public Integer recuperacionFisio() {
    	return fisio.getRecuperacion();
    }
    
    public Boolean esFantasma() {
    	return this.fantasma; 
    }
    
    
    
    public void asignarFutbolista(Futbolista f) {
    	futbolistas.add(f);
    }

    public Convocado asignarConvocado(Integer dorsal) {
    	/*
    	 * Convoca para el partido de la jornada "jorndada",
    	 *  el futbolista con dorsal "dorsal".
    	 */
    	for(int i = 0; i < futbolistas.size(); ++i) {
            Futbolista f = futbolistas.get(i);
            int d = f.getDorsal();
            if(d == dorsal) {
                return futbolistas.get(i).convocar();
            }  
        }
		return null;
    }
    
    public Integer cuantosFutbolistas() {
    	return futbolistas.size();
    }
    

	public void bajarAtributo(int atributo, int y) {
		futbolistas.get(y).bajarAtributo(entrenador.getEstrategia(), entrenador.getRebaja(), atributo);
	}

	public void subirAtributo(int atributo, int n) {
		futbolistas.get(n).subirAtributo(entrenador.getEstrategia(), entrenador.getRebaja(), atributo);
		
	}
	
	/*
	 * Fin metodos
	 */
   
    public void poblar() {
    	for (int i = 0; i < 18; ++i) {
    		Futbolista f = null;

    		if (i < 2) f = new Futbolista(this, Constantes.PORTERO, i + 1);
    		else if (i < 7) f = new Futbolista(this, Constantes.MEDIO, i + 1); // cambiado, antes era 8
    		else if (i < 13) f = new Futbolista(this, Constantes.DEFENSA, i + 1);
    		else f = new Futbolista(this, Constantes.DELANTERO, i + 1);

    		futbolistas.add(f);
    	}
    	Random r = new Random();
		int x = r.nextInt(10);
		x += 30;
		int y = r.nextInt(10);
		y += 30;
		fisio = new Fisioterapeuta(x, y);
		x = r.nextInt(10);
		x += 30;
		y = r.nextInt(30);
		y += 30;
		int z = r.nextInt(3);
		switch (z) {
			case 0: z = Constantes.AGRESIVA; break;
			case 1: z = Constantes.DEFENSIVA; break;
			case 2: z = Constantes.NEUTRAL;
		}
		entrenador = new Entrenador(z, x, y);
    }

	public int getNumeroFutbolistas() {
		return futbolistas.size();
	}

	public String getHorasRestantes() {
		return String.valueOf(horasRestantes);
	}

	public void setFutbolista(Futbolista f) {
		futbolistas.add(f);		
	}

	public void setEstadisticaFutbolista(EstadisticasJugador est, Futbolista f) {
		boolean trobat = false;
		for(int i = 0; i < futbolistas.size() && !trobat; ++i) {
			if(futbolistas.get(i).getDorsal() == f.getDorsal()) {
				trobat = true;
				futbolistas.get(i).setFaltasRealizadas(est.getFaltasRealizadas());
				futbolistas.get(i).setFaltasRecibidas(est.getFaltasRecibidas());
				futbolistas.get(i).setGolesMarcados(est.getGolesMarcados());
				futbolistas.get(i).setGolesRecibidos(est.getGolesRecibidos());
				futbolistas.get(i).setPartidosJugados(est.getPartidosJugados());
				futbolistas.get(i).setPasesBuenos(est.getPasesBuenos());
				futbolistas.get(i).setPasesRealizados(est.getPasesRealizados());
				futbolistas.get(i).setTarjetasAmarillas(est.getTarjetasAmarillas());
				futbolistas.get(i).setTarjetasRojas(est.getTarjetasRojas());
			}
		}
	}

	public void setEstadisticaEquipo(EstadisticasEquipo est) {
		this.estadisticas = est;		
	}

	public void setExperienciaFutbolista(int exp, int n) {
		futbolistas.get(n).setExperiencia(exp);
	}

	public void setHorasRestantes(int horas) {
		this.horasRestantes = horas;
	}

	public String getNombreEntrenador() {
		return entrenador.getNombre();
	}

	public String getNombreFisioterapeuta() {
		return fisio.getNombre();
	}

	public Integer getSancion(int f) {
		return futbolistas.get(f).getSancionado();
	}

	public Integer getLesion(int f) {
		return futbolistas.get(f).getGravedad();
	}

	public void resetHorasRestantes() {
		horasRestantes = ((entrenador.getIncrementador()+entrenador.getRebaja())/2)+20;
	}

	public void setPeso(int y, int peso) {
		futbolistas.get(y).setPeso(peso);
	}

	public void setAltura(int y, Float altura) {
		futbolistas.get(y).setAltura(altura);
	}

    
}

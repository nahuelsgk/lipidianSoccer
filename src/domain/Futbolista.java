/*
 * Alberto Moreno Vega
 * 
 */


package domain;

import java.util.Random;

public class Futbolista extends Persona {

	protected Float altura;

	protected Integer peso;
	
	protected Integer remate;

	protected Integer resistencia;

	protected Integer velocidad;

	protected Integer regate;

	protected Integer agresividad;

	protected Integer pase;

	protected Integer dorsal;

	protected Integer posicion;

	protected Integer gravedad;

	protected Integer sancionado;

	protected Integer parada;

	protected Integer experiencia;

	protected EstadisticasJugador jugadorTiene;
	
	protected EquipoFutbol juegaEn;
    /**
   * 
   * @element-type Convocado
   */
	
	/*
	 *Constructora
	 */
	
  	public Futbolista(EquipoFutbol equip, Integer posicion, Integer dorsal) {
  		super(Persona.HOMBRE);
  		juegaEn = equip;
  		
  		Random rand = new Random();
  		int x = rand.nextInt(20) + 160;
  		altura = (float)x;
  		altura = altura/100;
  		peso = 60 + x;
  		x = rand.nextInt(4);
  		peso = peso + x - 160;
  		this.dorsal = dorsal;
  		this.posicion = posicion;
  		gravedad = 0;
  		sancionado = 0;
  		experiencia = 0;
  		jugadorTiene = new EstadisticasJugador();
  		remate = inicializaAtributo();
  		resistencia = inicializaAtributo();
  		velocidad = inicializaAtributo();
  		regate = inicializaAtributo();
  		agresividad = inicializaAtributo();
  		pase = inicializaAtributo();
  		parada = inicializaAtributo();
  		int incrementoClase = 15;
  		switch (posicion) {
  			case Constantes.DELANTERO: 	
  							remate = remate + incrementoClase; 
  							regate = regate + incrementoClase;
  							agresividad = agresividad + incrementoClase;
  							break;
  			case Constantes.MEDIO: 		
  							pase = pase + incrementoClase; 
  							velocidad = velocidad + incrementoClase;
  							regate = regate + incrementoClase;
  							break;
  			case Constantes.DEFENSA : 	
  							agresividad = agresividad + incrementoClase; 
					  		pase = pase + incrementoClase;
					  		resistencia = resistencia + incrementoClase;
					  		break;
  			case Constantes.PORTERO : 	
  							parada = parada + incrementoClase; 
					  		pase = pase + incrementoClase;
					  		velocidad = velocidad + incrementoClase;
					  		break;
  		default:
  				break;
  		}
  	}

  	
  	private Integer inicializaAtributo() {
  		Integer base = 35;
  		Integer margenAleatorio = 15;
  		Random rand = new Random();
  		Integer x = rand.nextInt(margenAleatorio);
  		x = x + base;
  		return x;
  	}
  	
  	/*
  	 * Getters y setters
  	 */
  	
		
	public Float getAltura() {
		return altura;
	}
	
	public Integer getPeso() {
		return peso;
	}
	
	public Integer getResistencia() {
		return resistencia;
	}
	
	public Integer getVelocidad() {
		return velocidad;
	}
	
	public Integer getRegate() {
		return regate;
	}
	
	public Integer getAgresividad() {
		return agresividad;
	}
	
	public Integer getPase() {
		return pase;
	}
	
	public Integer getDorsal() {
		return dorsal;
	}
	
	public int getPosicion() {
		return posicion;
	}
	
	public Integer getGravedad() {
		return gravedad;
	}
	
	public Integer getSancionado() {
		return sancionado;
	}
	
	
	public Integer getParada() {
		return parada;
	}
	
	public Integer getExperiencia() {
		return experiencia;
	}
	
	public Integer getRemate() {
		return remate;
	}

    public String getEquipo() {
  	  	return juegaEn.getNombre();
    }
    
  	public void setEquipo(EquipoFutbol equip) {
  		juegaEn = equip;
  	}

	public void setRemate(Integer remate) {
		this.remate = remate;
	}


	public void setDorsal(Integer dors) {
		dorsal = dors;
	}
	
	public void setGravedad(Integer grav) {
		gravedad = grav;
	}
	
	public void setSancionado(Integer sanc) {
		sancionado = sanc;
	}
	
	public void setExperiencia(Integer exp) {
		experiencia = exp;
	}
	
	public void setAltura(float altura) {
		this.altura = altura;
	}
	
	public void setPeso (int peso) {
		this.peso = peso;
	}
	
	/*
	 * Metodos
	 */
	private Integer costeExperiencia(Integer valor) {
		/*Los rangos van de 1 a 100. Cada rango_incremento puntos el atributo cuesta un incremento_coste%
  		 * mas que en el rango anterior los precios parten de un valor de valor_base puntos de experiencia 
  		 * (de 1 al 10)
  		*/
  		/*int valor_base = 10;
  		int rango_incremento = 10;*/
  		Float incrementoCoste = new Float(Constantes.RANGO_INCREMENTO);
  		incrementoCoste = incrementoCoste/100;
  		
  		int aux = valor/Constantes.RANGO_INCREMENTO;
  		
  		int res = Constantes.VALOR_BASE;
  		for (int i = 0; i < aux; ++i) {
  			res = (int) (res + res*incrementoCoste);
  		}
  		return res*10;
	}
		
  	public Integer calculaExperiencia(Integer estrategia, Integer rebaja, Integer atributo) {
  		int coste;
  		double reb = rebaja;
  		switch (atributo) {
  		case Constantes.REMATE: coste = costeExperiencia(remate);
  				if (estrategia == Constantes.AGRESIVA) coste -= (int)((double)coste * (double)((100-reb)/100));
  				break;
  		case Constantes.RESISTENCIA: coste = costeExperiencia(resistencia);
				if (estrategia == Constantes.NEUTRAL) coste -= (int)((double)coste * (double)((100-reb)/100));
				break;
  		case Constantes.VELOCIDAD: coste = costeExperiencia(velocidad);
  				if (estrategia == Constantes.NEUTRAL) coste -= (int)((double)coste * (double)((100-reb)/100));
  				break;
  		case Constantes.REGATE: coste = costeExperiencia(regate);
  				if (estrategia == Constantes.AGRESIVA) coste -= (int)((double)coste * (double)((100-reb)/100));
  				break;
  		case Constantes.AGRESIVIDAD: coste = costeExperiencia(agresividad);
				if (estrategia == Constantes.AGRESIVA) coste -= (int)((double)coste * (double)((100-reb)/100));
				break;
  		case Constantes.PASE: coste = costeExperiencia(pase);
				if (estrategia == Constantes.DEFENSIVA) coste -= (int)((double)coste * (double)((100-reb)/100));
				break;
  		case Constantes.PARADA: coste = costeExperiencia(parada);
				if (estrategia == Constantes.DEFENSIVA) coste -= (int)((double)coste * (double)((100-reb)/100));
				break;
		default: coste = 0; break;
  		}
  		return coste;
  	}

  	
  	public Boolean subirAtributo(int estrategia, int rebaja, int atributo) {
  		int coste;
  		coste = calculaExperiencia(estrategia, rebaja, atributo);
  		if (coste <= experiencia) {
  			switch (atributo) {
  	  		case Constantes.REMATE: 
  	  			if (remate == 100) {
  	  				return false;
  	  			}
  	  			remate++; 
  	  			break;
  	  		case Constantes.RESISTENCIA: 
  	  		if (resistencia == 100) {
	  				return false;
	  			}
  	  			resistencia++; 
  	  			break;
  	  		case Constantes.VELOCIDAD: 
  	  		if (velocidad == 100) {
	  				return false;
	  			}
  	  			velocidad++; 
  	  			break;
  	  		case Constantes.REGATE: 
  	  		if (regate == 100) {
	  				return false;
	  			}
  	  			regate++; 
  	  			break;
  	  		case Constantes.AGRESIVIDAD: 
  	  		if (agresividad == 100) {
	  				return false;
	  			}
  	  			agresividad++; 
  	  			break;
		  	case Constantes.PASE: 
		  		if (pase == 100) {
  	  				return false;
  	  			}
		  		pase++; 
		  		break;
		  	case Constantes.PARADA: 
		  		if (parada == 100) {
  	  				return false;
  	  			}
		  		parada++; 
		  		break;
		  	default: return false;
  	  		}
  			experiencia = experiencia - coste;
  			return true;
  		}
  		return false;
  	}

  	public Boolean bajarAtributo(int estrategia, int rebaja, int atributo) {
  		int ganancia;
  		ganancia = calculaGanancia(estrategia, rebaja, atributo);
  		switch (atributo) {
	  	  	case Constantes.REMATE: {
	  	  		if (remate <= 1) {
	  	  			return false;
	  	  		}
	  	  		remate--; 
	  	  		break;
	  	  	}
	  	  	case Constantes.RESISTENCIA: 
		  	  	if (resistencia <= 1) {
	  	  			return false;
	  	  		}
	  	  		resistencia--; 
	  	  		break;
	  	  	case Constantes.VELOCIDAD: 
		  	  	if (velocidad <= 1) {
	  	  			return false;
	  	  		}
	  	  		velocidad--; 
	  	  		break;
	  	  	case Constantes.REGATE: 
		  	  	if (regate <= 1) {
	  	  			return false;
	  	  		}
	  	  		regate--; 
	  	  		break;
	  	  	case Constantes.AGRESIVIDAD: 
		  	  	if (agresividad <= 1) {
	  	  			return false;
	  	  		}
	  	  		agresividad--; 
	  	  		break;
			case Constantes.PASE: 
				if (pase <= 1) {
	  	  			return false;
	  	  		}
				pase--; 
				break;
			case Constantes.PARADA: 
				if (parada <= 1) {
	  	  			return false;
	  	  		}
				parada--; 
				break;
			default: return false;

  	  	}
  		experiencia = experiencia + ganancia;
	  	return true;
  	}
  	
  	
  	public int calculaGanancia(int estrategia, int rebaja, int atributo) {
	  	int ganancia;
	  	double reb = rebaja;
		switch (atributo) {
			case Constantes.REMATE: 
				ganancia = costeExperiencia(remate-1);
				if (estrategia == Constantes.AGRESIVA) ganancia -= (int)((double)ganancia * (double)((100-reb)/100));
				break;
			case Constantes.RESISTENCIA: 
				ganancia = costeExperiencia(resistencia-1); 
				if (estrategia == Constantes.NEUTRAL) ganancia -= (int)((double)ganancia * (double)((100-reb)/100));
				break;
			case Constantes.VELOCIDAD: 
				ganancia = costeExperiencia(velocidad-1);
				if (estrategia == Constantes.NEUTRAL) ganancia -= (int)((double)ganancia * (double)((100-reb)/100));
				break;
			case Constantes.REGATE: 
				ganancia = costeExperiencia(regate-1); 
				if (estrategia == Constantes.AGRESIVA) ganancia -= (int)((double)ganancia * (double)((100-reb)/100));
				break;
			case Constantes.AGRESIVIDAD: 
				ganancia = costeExperiencia(agresividad-1); 
				if (estrategia == Constantes.AGRESIVA) ganancia -= (int)((double)ganancia * (double)((100-reb)/100));
				break;
			case Constantes.PASE: 
				ganancia = costeExperiencia(pase-1); 
				if (estrategia == Constantes.DEFENSIVA) ganancia -= (int)((double)ganancia * (double)((100-reb)/100));
				break;
			case Constantes.PARADA: 
				ganancia = costeExperiencia(parada-1);
				if (estrategia == Constantes.DEFENSIVA) ganancia -= (int)((double)ganancia * (double)((100-reb)/100));
				break;
			default: ganancia = 0; break;
		}
		return ganancia;
  	}
  	
  	public void incrementarExperiencia(int exp) {
  		experiencia += exp;
  	}
  	
  	public void incrementaParadas() {
  		jugadorTiene.setParadas(jugadorTiene.getParadas()+1);
  	}
  	
  	public void recuperacion() {
  		int recuperacion = juegaEn.recuperacionFisio();
  		gravedad = gravedad - recuperacion;
  		if (gravedad < 0) gravedad = 0;
  		if (sancionado > 0) {
  			sancionado--;
  		}
  	}
  	
  	public int getRecuperacion() {
  		return juegaEn.recuperacionFisio();
  	}

  	public Convocado convocar(){
  		 if (sancionado == 0 && gravedad == 0) {
	 		 Convocado c = new Convocado(-1, -1, false, this);
	 		 return c;
  		 }
  		 return null;
 	 }
  	
  	public void setPosicion(int pos) {
  		this.posicion = pos;
  	}
  	 /*
  	  * getters y setters de estadisticas
  	 */
	public Integer getPasesBuenos() {
		return jugadorTiene.getPasesBuenos();
	}


	public void setPasesBuenos(Integer pasesBuenos) {
		jugadorTiene.setPasesBuenos(pasesBuenos);
	}
	
	public void setVelocidad(int vel) {
		velocidad = vel;
	}
	
	public void setResistencia(int res) {
		resistencia = res;
	}
	
	public void setRegate(int reg) {
		regate = reg;
	}
	
	public void setPase(int pas) {
		pase = pas;
	}
	
	public void serParada(int par) {
		parada = par;
	}
	
	public void setAgresividad(int agr) {
		agresividad = agr;
	}
	
	public void incrementaPasesBuenos() {
		jugadorTiene.setPasesBuenos(jugadorTiene.getPasesBuenos()+1);
		experiencia += Constantes.EXPERIENCIA_PASE;
	}
	
	
	public Integer getPasesRealizados() {
		return jugadorTiene.getPasesRealizados();
	}
	
	
	public void setPasesRealizados(Integer pasesRealizados) {
		jugadorTiene.setPasesRealizados(pasesRealizados);
		
	}
	
	public void setParadas(Integer par) {
		jugadorTiene.setParadas(par);
	}
	
	public Integer getParadas() {
		return jugadorTiene.getParadas();
	}
	
	public Integer getFaltasRecibidas() {
		return jugadorTiene.getFaltasRecibidas();
	}
	
	
	public void setFaltasRecibidas(Integer faltasRecibidas) {
		jugadorTiene.setFaltasRecibidas(faltasRecibidas);
	}
	
	
	public Integer getFaltasRealizadas() {
		return jugadorTiene.getFaltasRealizadas();
	}
	
	
	public void setFaltasRealizadas(Integer faltasRealizadas) {
		jugadorTiene.setFaltasRealizadas(faltasRealizadas);
	}
	
	
	public Integer getPartidosJugados() {
		return jugadorTiene.getPartidosJugados();
	}
	
	public void incrementaPartidosJugados(int resultado) {
		jugadorTiene.setPartidosJugados(jugadorTiene.getPartidosJugados()+1);
		switch (resultado) {
		case Constantes.PARTIDO_GANADO: 
			experiencia += Constantes.EXPERIENCIA_PARTIDO_GANADO;
			break;
		case Constantes.PARTIDO_EMPATADO:
			experiencia += Constantes.EXPERIENCIA_PARTIDO_EMPATADO;
			break;
			
		case Constantes.PARTIDO_PERDIDO:
			experiencia += Constantes.EXPERIENCIA_PARTIDO_PERDIDO;
			break;
		default: break;
		}
		
	}
	
	public int getDificultad() {
		return juegaEn.getDificultad();
	}
	
	public int getEstrategia() {
		return juegaEn.getEstrategia();
	}
	
	public void setPartidosJugados(Integer partidosJugados) {
		jugadorTiene.setPartidosJugados(partidosJugados);
	}


	public Float porcentagePasesBuenos() {
		return jugadorTiene.porcentagePasesBuenos();
	}
  	
	public Integer getGolesRecibidos() {
		return jugadorTiene.getGolesRecibidos();
	}
	
	public void setGolesRecibidos(Integer golesRecibidos) {
		jugadorTiene.setGolesRecibidos(golesRecibidos);
	}
	
	public void incrementaGolesRecibidos() {
		jugadorTiene.setGolesRecibidos(jugadorTiene.getGolesRecibidos()+1);
		juegaEn.setGolesRecibidos(juegaEn.getGolesRecibidos()+1);
	}

	public Integer getGolesMarcados() {
		return jugadorTiene.getGolesMarcados();
	}
	
	public void setGolesMarcados(Integer golesMarcados) {
		jugadorTiene.setGolesMarcados(golesMarcados);
	}
	
	public void incrementaGolesMarcados() {
		jugadorTiene.setGolesMarcados(jugadorTiene.getGolesMarcados()+1);
		experiencia += Constantes.EXPERIENCIA_GOL;
		juegaEn.setGolesMarcados(juegaEn.getGolesMarcados()+1);
	}
	
	public int getMedia() {
		int media = 0;
		media += remate;
		media += resistencia;
		media += velocidad;
		media += regate;
		media += agresividad;
		media += pase;
		media += parada;
		return (media/7);
	}
	
	public Integer getTarjetasAmarillas() {
		return jugadorTiene.getTarjetasAmarillas();
	}
	
	public void setTarjetasAmarillas(Integer tarjetasAmarillas) {
		jugadorTiene.setTarjetasAmarillas(tarjetasAmarillas);
	}
	
	public void incrementaTarjetasAmarillas() {
		jugadorTiene.setTarjetasAmarillas(jugadorTiene.getTarjetasAmarillas()+1);
		juegaEn.setTarjetasAmarillas(juegaEn.getTarjetasAmarillas()+1);
		if (juegaEn.getTarjetasAmarillas()%5 == 0) {
			sancionado++;
		}
	}
	
	public Integer getTarjetasRojas() {
		return jugadorTiene.getTarjetasRojas();
	}
	
	public void setTarjetasRojas(Integer tarjetasRojas) {
		jugadorTiene.setTarjetasRojas(tarjetasRojas);
	}
	
	public void incrementaTarjetasRojas() {
		jugadorTiene.setTarjetasRojas(jugadorTiene.getTarjetasRojas()+1);
		juegaEn.setTarjetasRojas(juegaEn.getTarjetasRojas()+1);
		sancionado++;
	}
  	
}
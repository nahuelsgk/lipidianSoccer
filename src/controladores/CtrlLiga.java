/*
 * Jaume Vinyes Navas
 * Alexandre Vidal Obiols
 */

package controladores;

import java.util.Vector;

import domain.Constantes;
import domain.EquipoFutbol;
import domain.Convocado;
import domain.Futbolista;

public class CtrlLiga {

		// Entrar al partido
	private EquipoFutbol ef;
	private Vector<Convocado> convocados;
	private Vector<Convocado> reservas;
	private Integer alineacion;
	
	
	private Vector<Convocado> convocadosaux; // auxiliar cambio alineacion
	private Vector<Convocado> reservasaux; // auxiliar cambio alineacion
	private Vector<Integer> idt; // auxiliar cambio alineacion
	private Integer yaAsignado;

	
	private static CtrlLiga instancia;
	
	
	/*
	 *  Creadora
	 */
	public CtrlLiga() {
		ef = new EquipoFutbol();
		convocados = new Vector<Convocado>();
		reservas = new Vector<Convocado>();
		idt = new Vector<Integer>();
		yaAsignado = 0;
		alineacion = Constantes.ALINEACION_4_3_3;
	}
	
	
	
	/*
	 * Getters atributs Futbolista
	 */
	public int getDorsal(int y) {
		return ef.getDorsal(y);
	}
	
	public int getMedia(int y) {
		return ef.getMedia(y);
	}
	
	public int getPosicion(int y) {
		return ef.getPosicion(y);
	}
	
	public int getPase(int y) {
		return ef.getPase(y);
	}
	
	public int getAgresividad(int y) {
		return ef.getAgresividad(y);
	}
	
	public int getRegate(int y) {
		return ef.getRegate(y);
	}
	
	public int getVelocidad(int y) {
		return ef.getVelocidad(y);
	}
	
	public int getResistencia(int y) {
		return ef.getResistencia(y);
	}
	
	public int getRemate(int y) {
		return ef.getRemate(y);
	}
	
	public int getPeso(int y) {
		return ef.getPeso(y);
	}
	
	public float getAltura(int y) {
		return ef.getAltura(y);
	}
	
	public int getParada(int y) {
		return ef.getParada(y);
	}

	public int getExperiencia(int y) {
		return ef.getExperiencia(y);
	}

	/* Getters jugador por dorsal
	 * 
	 */
	
	public int getDorsalDorsal(int y) {
		return idt.get(y);
	}
	
	
	public int getMediaDorsal(int y) {
		return ef.getMedia(idt.get(y) - 1);
	}
	
	public int getPosicionDorsal(int y) {
		return ef.getPosicion(idt.get(y) - 1);
	}
	
	public int getPaseDorsal(int y) {
		return ef.getPase(idt.get(y) - 1);
	}
	
	public int getAgresividadDorsal(int y) {
		return ef.getAgresividad(idt.get(y) - 1);
	}
	
	public int getRegateDorsal(int y) {
		return ef.getRegate(idt.get(y) - 1);
	}
	
	public int getVelocidadDorsal(int y) {
		return ef.getVelocidad(idt.get(y) - 1);
	}
	
	public int getResistenciaDorsal(int y) {
		return ef.getResistencia(idt.get(y) - 1);
	}
	
	public int getRemateDorsal(int y) {
		return ef.getRemate(idt.get(y) - 1);
	}
	
	public int getPesoDorsal(int y) {
		return ef.getPeso(idt.get(y) - 1);
	}
	
	public float getAlturaDorsal(int y) {
		return ef.getAltura(idt.get(y) - 1);
	}
	
	public int getParadaDorsal(int y) {
		return ef.getParada(idt.get(y) - 1);
	}

	
	
	/*
	 * Getter de Equipo
	 */
	
	public String getNombre() {
		return ef.getNombre();
	}
	
	public String getCampo() {
		return ef.getCampo();
	}
	
	public String getCiudad() {
		return ef.getCiudad();
	}
	
	public String getOro() {
		return String.valueOf(ef.getOro());
	}

	
	
	
	/*
	 * Getter de los nombres de futbolistas
	 */
	public String[] getNombresFutbolistas() {
		String[] s = new String[18];
		if (ef != null) {
			for (int i = 0; i < 18; ++i) {
				s[i] = ef.getNombre(i);
			}
		}
		return s;
	}
	
	public String[] getNombresFutbolistasValidos() {
		Integer sancionado;
		Integer gravedad;
		String[] s = new String[ef.getFutbolistas().size()];
		if (ef != null) {
			for (int i = 0; i < ef.getFutbolistas().size(); ++i) {
				sancionado = ef.getFutbolistas().get(i).getSancionado();
				gravedad = ef.getFutbolistas().get(i).getGravedad();
				if (sancionado == 0 && gravedad == 0) {
					s[i] = ef.getNombre(i);
				}
			}
		}
		return s;
	}

	
	/*
	 * Metodos
	 */
	public Integer calculaExperiencia(Integer atributo,Integer y) {
		return ef.calculaExperiencia(atributo,y);
	}
	
	public Integer cantidadPorExperiencia(int atributo, int exp, int fut) {
		int n = 0;
		Futbolista f = ef.getFutbolista(fut);
        int expAnterior = f.getExperiencia();
        f.setExperiencia(exp);
        while (f.subirAtributo(ef.getEstrategia(), ef.getRebaja(), atributo)) {
            n++;
        }
        for (int i = 0; i < n; i++) {
            f.bajarAtributo(ef.getEstrategia(), ef.getRebaja(), atributo);
        }
        f.setExperiencia(expAnterior);
        return n;
	}

	public void bajarAtributo(int atributo, int y) {
		ef.bajarAtributo(atributo,y);
	}

	public void subirAtributo(int atributo,int n) {
		ef.subirAtributo(atributo,n);		
	}

	public String[][] getAtributosFutbolistas() {
		String[][] at = new String[18][7];
		for(int i = 0; i < 18; ++i) {
			for(int j = 0; j < 7; ++j) {
				switch(j) {
					case 0:
						at[i][j] = ef.getNombre(i);
						break;
					case 1:
						int posicion = ef.getPosicion(i);
						String pos = "";
						if(posicion == Constantes.DEFENSA) {
							pos = "Defensa";
						}
						else if(posicion == Constantes.MEDIO) {
							pos = "Medio";
						}
						else if(posicion == Constantes.DELANTERO) {
							pos = "Delantero";
						}
						else if(posicion == Constantes.PORTERO) {
							pos = "Portero";
						}
						at[i][j] = pos;
						break;
					case 2:
						at[i][j] = String.valueOf(ef.getDorsal(i));
						break;
					case 3:
						at[i][j] = String.valueOf(ef.getSancion(i));
						break;
					case 4:
						at[i][j] = String.valueOf(ef.getTarjetasAmarillas(i));
						break;
					case 5:
						at[i][j] = String.valueOf(ef.getTarjetasRojas(i));
						break;
					case 6:
						at[i][j] = String.valueOf(ef.getLesion(i));
						break;
				}	
			}
		}
		return at;
	}

	public String getHorasRestantes() {
		return ef.getHorasRestantes();
	}

	public String[][] getAtributosFutbolistasEstadisticas() {
		String[][] at = new String[18][11];
		for(int i = 0; i < 18; ++i) {
			for(int j = 0; j < 11; ++j) {
				switch(j) {
					case 0:
						at[i][j] = ef.getNombre(i);
						break;
					case 1:
						at[i][j] = String.valueOf(ef.getGolesMarcados(i));
						break;
					case 2:
						at[i][j] = String.valueOf(ef.getGolesRecibidos(i));
						break;
					case 3:
						at[i][j] = String.valueOf(ef.getTarjetasAmarillas(i));
						break;
					case 4:
						at[i][j] = String.valueOf(ef.getTarjetasRojas(i));
						break;
					case 5:
						at[i][j] = String.valueOf(ef.getPasesBuenos(i));
						break;
					case 6:
						at[i][j] = String.valueOf(ef.getPasesRealizados(i));
						break;
					case 7:
						at[i][j] = String.valueOf(ef.getPorcentajePasesBuenos(i));
						break;
					case 8:
						at[i][j] = String.valueOf(ef.getFaltasRealizadas(i));
						break;
					case 9:
						at[i][j] = String.valueOf(ef.getFaltasRecibidas(i));
						break;
					case 10:
						at[i][j] = String.valueOf(ef.getPartidosJugados(i));
						break;
										
				}	
			}
		}
		return at;
	}

	public void generaIDT() {
		Integer sancionado;
		Integer gravedad;
		for (int i = 0; i < ef.getFutbolistas().size(); i++) {
			sancionado = ef.getFutbolistas().get(i).getSancionado();
			gravedad = ef.getFutbolistas().get(i).getGravedad();
			if (sancionado == 0 && gravedad == 0) {
				idt.add(i, ef.getFutbolistas().get(i).getDorsal());
			}
		}
		reservasaux = reservas;
		convocadosaux = convocados;
	}
	
	public Integer getIDT(Integer dorsal) {
		
		for (int i = 0; i < idt.size(); i++) {
			if (idt.get(i) == dorsal) return i;
		}
		
		return -1;
	}
	
	public void setPosicion(Integer index, Integer pos) {
		
		int dors = idt.get(index);
		Convocado c = ef.asignarConvocado(dors);
		c.setJugando(true);
		c.setPosicion(pos);
		if (pos >= 0) convocados.add(c);
		else reservas.add(c);
		
	}
	
	public void unset(Integer n) {
		int i = 0;
		boolean trobat = false;
		
		if (convocados.size() > 0 && n >= 0) {
			for (i = 0; i < convocados.size() && !trobat; ++i){
				
				if (convocados.get(i).getPosicion() == n) {
					convocados.remove(i);
					trobat = true;
				}
			}
		} else if (reservas.size() > 0 && n < 0){
			for (i = 0; i < reservas.size() && !trobat; ++i){
				if (reservas.get(i).getPosicion() == n) {
					reservas.remove(i);
					trobat = true;
				}
			}
		}
		
		
	}
	
	public void setPosicionNombre(String nombre, Integer pos) {

		int dors = 0;
		for (int i = 0; i < ef.getNumeroFutbolistas(); ++i) {
			if (ef.getFutbolista(i).getNombre() == nombre) {
				dors = ef.getFutbolista(i).getDorsal();
				break;
			}
		}
		Convocado c = ef.asignarConvocado(dors);
		c.setJugando(true);
		c.setPosicion(pos);
		if (pos >= 0) convocados.add(c);
		else reservas.add(c);
		
	}

	
	public void alineacionAutomatica() {
		convocados = new Vector<Convocado>();
		reservas = new Vector<Convocado>();
		Vector<Boolean> tablaConv = new Vector<Boolean>();
		Vector<Boolean> tablaPos = new Vector<Boolean>();
		Vector<Boolean> tablaPosRes = new Vector<Boolean>();
		for (int i = 0; i < 18; i++) tablaConv.add(i, false);
		for (int i = 0; i < 11; i++) tablaPos.add(i, false);
		for (int i = 0; i < 5; i++) tablaPosRes.add(i, false);
		int a, b, c;
		a = b = c = 0;
			
		switch (alineacion) {
			case 1:
				a = 4;
				b = 4;
				c = 2;
				break;
			case 0:
				a = 4;
				b = 3;
				c = 3;
				break;
			case 3:
				a = 5;
				b = 3;
				c = 2;
				break;
			case 2:
				a = 5;
				b = 4;
				c = 1;
				break;
			default:
		}
		

		Convocado con = null;
		int cuentaDef, cuentaMed, cuentaDel, cuentaPort;
		cuentaDef = cuentaMed = cuentaDel = cuentaPort = 0;
		int j = 0;
		int viejo = 1;
		while (cuentaPort < 2) {
			if (ef.getPosicion(j) == Constantes.PORTERO && ef.getSancion(j) == 0 && ef.getLesion(j) == 0) {
				con = ef.asignarConvocado(ef.getDorsal(j));
				con.setJugando(true);
				tablaConv.set(j, true);
				if (cuentaPort == 1) {
					con.setPosicion(-1);
					tablaPosRes.set(0, true);
					//tablaPosRes.set(1, true);
					reservas.add(con);
				}else {
					con.setPosicion(0);
					tablaPos.set(1+cuentaDef, true);
					convocados.add(con);
				}

				cuentaPort++;
			}
			j++;
			if (viejo == j) break;
			if (j%18 == 0) j = 0;
		}
		j = 1;
		while (cuentaDef < a+1) {
				if (ef.getPosicion(j) == Constantes.DEFENSA && ef.getSancion(j) == 0 && ef.getLesion(j) == 0) {
					con = ef.asignarConvocado(ef.getDorsal(j));
					con.setJugando(true);
					tablaConv.set(j, true);
					if (cuentaDef == a) {
						con.setPosicion(-2);
						tablaPosRes.set(1, true);
						//tablaPosRes.set(1, true);
						reservas.add(con);
					}else {
						con.setPosicion(1+cuentaDef);
						tablaPos.set(1+cuentaDef, true);
						convocados.add(con);
					}

					cuentaDef++;
				}
				j++;
				if (viejo == j) break;
				if (j%18 == 0) j = 0;
			}
			viejo = j;
			while (cuentaMed < b+2) {
				if (ef.getPosicion(j) == Constantes.MEDIO && ef.getSancion(j) == 0 && ef.getLesion(j) == 0) {
					con = ef.asignarConvocado(ef.getDorsal(j));
					con.setJugando(true);
					tablaConv.set(j, true);
					if (cuentaMed == b) {
						//tablaPosRes.set(2, true);
						tablaPosRes.set(2, true);
						con.setPosicion(-3);
						reservas.add(con);
					} else if (cuentaMed == b + 1) {
						//tablaPosRes.set(1, true);
						tablaPosRes.set(3, true);
						con.setPosicion(-4);
						reservas.add(con);
					} else {
						con.setPosicion(1+a+cuentaMed);
						tablaPos.set(1+a+cuentaMed, true);
						convocados.add(con);
					}
					
					cuentaMed++;
				}
				j++;
				if (viejo == j) break;
				if (j%18 == 0) j = 0;
			}
			viejo = j;
			while (cuentaDel < c + 1) {
				if (ef.getPosicion(j) == Constantes.DELANTERO && ef.getSancion(j) == 0 && ef.getLesion(j) == 0) {
					con = ef.asignarConvocado(ef.getDorsal(j));
					con.setJugando(true);
					tablaConv.set(j, true);
					if (cuentaDel == c) {
						con.setPosicion(-5);
						//tablaPosRes.set(4, true);
						tablaPosRes.set(4, true);
						reservas.add(con);
					}else {
						con.setPosicion(1+a+b+cuentaDel);
						tablaPos.set(1+a+b+cuentaDel, true);
						convocados.add(con);
					}
					cuentaDel++;
				}
				j++;
				if (viejo == j) break;
				if (j%18 == 0) j = 0;
			}
		
		boolean fin = false;
		if (convocados.size() != 11){
			for (int i = 0; i < 18 && !fin; i++) {
				if (!tablaConv.get(i) && ef.getSancion(i) == 0 && ef.getLesion(i) == 0) {
					int k = 0;
					while (k < 11 && tablaPos.get(k)) k++;
					if (k != 11) {
						tablaPos.set(k, true);
						con = ef.asignarConvocado(ef.getDorsal(i));
						con.setJugando(true);
						tablaConv.set(i, true);
						con.setPosicion(k);
						convocados.add(con);
					} else {
						fin = true;
					}
				}
			}
		}
		fin = false;
		if (reservas.size() != 5){
			for (int i = 0; i < 18 && !fin; i++) {
				if (!tablaConv.get(i) && ef.getSancion(i) == 0 && ef.getLesion(i) == 0) {
					int k = 0;
					while (k < 5 && tablaPosRes.get(k)) k++;
					if (k != 5) {
						tablaPosRes.set(k, true);
						con = ef.asignarConvocado(ef.getDorsal(i));
						con.setJugando(true);
						tablaConv.set(i, true);
						con.setPosicion(-k-1);
						reservas.add(con);
					} else {
						fin = true;
					}
				}
			}
		}

	}
	
	public Integer getPosicion(Integer index) {//dado un nombre retorna su posicion

		int dors = idt.get(index);
		
		for (int i = 0; i < convocados.size(); i++) {
			if (dors == convocados.get(i).getDorsal()) return convocados.get(i).getPosicion();
		}
		for (int i = 0; i < reservas.size(); i++) {
			if (dors == reservas.get(i).getDorsal()) return reservas.get(i).getPosicion();
		}
				
		return -6;
	}
	
	public String quienOcupaPosicion(Integer n) {//dada una posicion retorna quien la ocupa

		for (int i = 0; i < convocados.size(); i++) {
			if (n == convocados.get(i).getPosicion()) return convocados.get(i).getNombre();
		}
		
		for (int i = 0; i < reservas.size(); i++) {
			if (n == reservas.get(i).getPosicion()) return reservas.get(i).getNombre();
		}
		
		return "Nadie";
	}
	
	public void resetAssig() {
		convocados = new Vector<Convocado>();
		reservas = new Vector<Convocado>();
	}

	public void salvaAssig() {
		convocados = convocadosaux;
		reservas = reservasaux;
	}
	
	public void setAlineacion(Integer x){
		alineacion = x;
	}
	
	public Integer getAlineacion() {
		return alineacion;
	}
	
	public Boolean completo() {
		
		if (convocados.size() == 11) return true;
		return false;
		
	}
	
	public Integer getyaAsignado() {
		return yaAsignado;
	}
	
	public void setyaAsignado(Integer n) {
		yaAsignado = n;
	}
	
	public void setExperienciaFutbolista(int exp, int n) {
		ef.setExperienciaFutbolista(exp,n);		
	}
	
	/*
	 * Datos
	 */
	
	public void setEquipo(EquipoFutbol ef) {
		this.ef = ef;
	}
	
	public EquipoFutbol getEquipo() {
		return ef;
	}

	public Vector<Convocado> getConvocados() {
		return convocados;
	}

	public void setConvocados(Vector<Convocado> convocados) {
		this.convocados = convocados;
	}

	public Vector<Convocado> getReservas() {
		return reservas;
	}

	public void setReservas(Vector<Convocado> reservas) {
		this.reservas = reservas;
	}

	public Vector<Convocado> getConvocadosaux() {
		return convocadosaux;
	}

	public void setConvocadosaux(Vector<Convocado> convocadosaux) {
		this.convocadosaux = convocadosaux;
	}

	public Vector<Convocado> getReservasaux() {
		return reservasaux;
	}

	public void setReservasaux(Vector<Convocado> reservasaux) {
		this.reservasaux = reservasaux;
	}

	public Vector<Integer> getIdt() {
		return idt;
	}

	public void setIdt(Vector<Integer> idt) {
		this.idt = idt;
	}

	public static void setInstancia(CtrlLiga instancia) {
		CtrlLiga.instancia = instancia;
	}
	
	public static CtrlLiga getInstancia() {
		if(instancia == null) {
			instancia = new CtrlLiga();
		}
		return instancia;
	}



	public void setOro(int oro) {
		ef.setOro(oro);		
	}



	public void setHorasRestantes(int horas) {
		ef.setHorasRestantes(horas);
	}




	public String getNombreEntrenador() {
		return ef.getNombreEntrenador();
	}



	public String getNombresFisio() {
		return ef.getNombreFisioterapeuta();
	}



	public void resetHorasRestantes() {
		ef.resetHorasRestantes();
	}

	



	
	/*
	 * Fin Datos
	 */
}

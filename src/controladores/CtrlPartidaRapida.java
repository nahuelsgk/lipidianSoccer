/*
 * Alexandre Vidal Obiols
 */


package controladores;

import java.util.Vector;

import domain.Constantes;
import domain.EquipoFutbol;
import domain.Partido;
import domain.Convocado;

public class CtrlPartidaRapida {
	
	private EquipoFutbol eq1;
	private EquipoFutbol eq2;
	private Integer tipo;
	private Integer dificultad;
	private Integer tiempo;
	private Integer jugadores;
	private boolean iaJ1;
	private boolean iaJ2;
	private Vector<Convocado> eq1conv, eq2conv, eq1res, eq2res;
	private Integer asignandoActual;
	private Integer alineacion1;
	private Integer alineacion2;
	
	public CtrlPartidaRapida() {
		eq1 = new EquipoFutbol();
		eq2 = new EquipoFutbol();
		eq1conv = new Vector<Convocado>();
		eq2conv = new Vector<Convocado>();
		eq1res = new Vector<Convocado>();
		eq2res = new Vector<Convocado>();
		asignandoActual = 0;
		tipo = -1;
		dificultad = -1;
		jugadores = -1;
		alineacion1 = Constantes.ALINEACION_4_3_3;
		alineacion2 = Constantes.ALINEACION_4_3_3;
		iaJ1 = true;
		iaJ2 = true;
	}
	

	
	public void setAsignandoActual(Integer i) {
		asignandoActual = i;
	}
	
	public Integer getAsignandoActual() {
		return asignandoActual;
	}
	
	public void setTipo(Integer i) {
		tipo = i;
	}
	
	public Integer getTipo() {
		return tipo;
	}
	
	public void setJugadores(Integer i) {
		jugadores = i;
	}
	
	public Integer getJugadores() {
		return jugadores;
	}
	
	public void setDificultad(Integer i) {
		dificultad = i;
	}
	
	public Integer getDificultad() {
		return dificultad;
	}
	
	public void setTiempo(Integer i) {
		tiempo = i;
	}
	
	public Integer getTiempo() {
		return tiempo;
	}
	
	public void setAlineacion1(Integer i) {
		alineacion1 = i;
	}
	
	public Integer getAlineacion1() {
		return alineacion1;
	}
	
	
	public void setAlineacion2(Integer i) {
		alineacion2 = i;
	}
	
	public Integer getAlineacion2() {
		return alineacion2;
	}
	
	public void setIaJ1(boolean b) {
		iaJ1 = b;
	}
	
	public void setIaJ2(boolean b) {
		iaJ2 = b;
	}
	
	
	public void setEq1(EquipoFutbol eq) {
		eq1 = eq;
	}
	
	public void setEq2(EquipoFutbol eq) {
		eq2 = eq;
	}
	
	public String[] getNombresDummy() {
		String[] s = new String[18];
		for (int i = 0; i < 18; ++i) {
			s[i] = "Buit";
		}
		
		return s;
	}
	
	public String[] getNombresEq1() {
		String[] s = new String[18];
		for (int i = 0; i < 18; ++i) {
			s[i] = eq1.getNombre(i);
		}
		
		return s;
	}
	
	public String[] getNombresEq2() {
		String[] s = new String[18];
		for (int i = 0; i < 18; ++i) {
			s[i] = eq2.getNombre(i);
		}
		
		return s;
	}
	
	public Integer getStatsEq(Integer n, Integer index) {
		EquipoFutbol aux = null;
		if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 0) {
			aux = eq1;
		} else {
			aux = eq2;
		}
		
		switch (n) {
			case CtrlPresentacion.DORSAL:
				return aux.getFutbolista(index).getDorsal();
			case CtrlPresentacion.TIPO:
				return aux.getFutbolista(index).getPosicion();
			case CtrlPresentacion.AGRESIVIDAD:
				return aux.getFutbolista(index).getAgresividad();
			case CtrlPresentacion.REGATE:
				return aux.getFutbolista(index).getRegate();
			case CtrlPresentacion.VELOCIDAD:
				return aux.getFutbolista(index).getVelocidad();
			case CtrlPresentacion.RESISTENCIA:
				return aux.getFutbolista(index).getResistencia();
			case CtrlPresentacion.REMATE:
				return aux.getFutbolista(index).getRemate();
			case CtrlPresentacion.PARADA:
				return aux.getFutbolista(index).getParada();
			case CtrlPresentacion.PASE:
				return aux.getFutbolista(index).getPase();
			case CtrlPresentacion.PESO:
				return aux.getFutbolista(index).getPeso();
			case CtrlPresentacion.MEDIA:
				return aux.getFutbolista(index).getMedia();
			default:
				return -1;
		}
	}
	
	public float getAlturaEq(Integer index) {
		EquipoFutbol aux = null;
		if (CtrlPresentacion.getInstancia().getEstatAlineacio() == 0) {
			aux = eq1;
		} else {
			aux = eq2;
		}
		
		return aux.getFutbolista(index).getAltura();
	}
	
	public void setPosicion(String name, Integer pos) {
		EquipoFutbol aux = null;
		Vector<Convocado> auxc = null;
		Vector<Convocado> auxr = null;
		if (asignandoActual == 0) {
			aux = eq1;
			auxc = eq1conv;
			auxr = eq1res;
		}
		else {
			aux = eq2;
			auxc = eq2conv;
			auxr = eq2res;
		}
		
		int i = 0;
		boolean trobat = false;
		for (i = 0; i < aux.getFutbolistas().size() && !trobat; i++) {
			if (aux.getFutbolistas().get(i).getNombre() == name) {
				Convocado c = aux.asignarConvocado(aux.getDorsal(i));
				c.setJugando(true);
				c.setPosicion(pos);
				if (pos >= 0 && auxc.size() <= 11) auxc.add(c);
				else if (pos < 0 && auxr.size() <= 5) auxr.add(c);
				trobat = true;
			}
		}
		
		if (asignandoActual == 0) {
			eq1 = aux;
			eq1conv = auxc;
			eq1res = auxr;
		}
		else {
			eq2 = aux;
			eq2conv = auxc;
			eq2res = auxr;
		}
	}
	
	public void unset(Integer n) {
		int i = 0;
		Vector<Convocado> auxc = null;
		Vector<Convocado> auxr = null;
		if (asignandoActual == 0) auxc = eq1conv;
		else auxc = eq2conv;
		if (asignandoActual == 0) auxr = eq1res;
		else auxr = eq2res;
		boolean trobat = false;

		if (auxc.size() > 0 && n >= 0) {
			for (i = 0; i < auxc.size() && !trobat; ++i){
				if (auxc.get(i).getPosicion() == n) {
					auxc.remove(i);
					trobat = true;
				}
			}
		} else if (auxr.size() > 0 && n < 0){
			for (i = 0; i < auxr.size() && !trobat; ++i){
				if (auxr.get(i).getPosicion() == n) {
					auxr.remove(i);
					trobat = true;
				}
			}
		}
		
		if (asignandoActual == 0) eq1conv = auxc;
		else eq2conv = auxc;
		if (asignandoActual == 0) eq1res = auxr;
		else eq2res = auxr;
	}
	
	public void alineacionAutomatica() {
		EquipoFutbol aux = new EquipoFutbol();
		Vector<Convocado> auxc = new Vector<Convocado>();
		Vector<Convocado> auxc2 = new Vector<Convocado>();
		Integer alineacion = 0;
		if (asignandoActual == 0) {
			aux = eq1;
			auxc = eq1conv;
			auxc2 = eq1res;
			alineacion = alineacion1;
		}
		else {
			aux = eq2;
			auxc = eq2conv;
			auxc2 = eq2res;
			alineacion = alineacion2;
		}
		
		auxc = new Vector<Convocado>();
		auxc2 = new Vector<Convocado>();
		
		int a, b, c;
		a = b = c = 0;
		resetAssig();
		
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

		
		Convocado con = aux.asignarConvocado(aux.getDorsal(0));
		con.setJugando(true);
		con.setPosicion(0);
		auxc.add(con);
		
		con = aux.asignarConvocado(aux.getDorsal(1));
		con.setJugando(true);
		con.setPosicion(-1);
		auxc2.add(con);

		int cuentaDef, cuentaMed, cuentaDel;
		cuentaDef = cuentaMed = cuentaDel = 0;
		int j = 1;
		while (cuentaDef < a+1) {
			if (aux.getPosicion(j) == Constantes.DEFENSA) {
				con = aux.asignarConvocado(aux.getDorsal(j));
				con.setJugando(true);
				if (cuentaDef == a) {
					con.setPosicion(-2);
					auxc2.add(con);
				}else {
					con.setPosicion(1+cuentaDef);
					auxc.add(con);
				}

				cuentaDef++;
			}
			j++;
			if (j%18 == 0) j = 0;
		}
		while (cuentaMed < b+2) {
			if (aux.getPosicion(j) == Constantes.MEDIO) {
				con = aux.asignarConvocado(aux.getDorsal(j));
				con.setJugando(true);
				if (cuentaMed == b) {
					con.setPosicion(-3);
					auxc2.add(con);
				} else if (cuentaMed == b + 1) {
					con.setPosicion(-4);
					auxc2.add(con);
				} else {
					con.setPosicion(1+a+cuentaMed);
					auxc.add(con);
				}

				cuentaMed++;
			}
			j++;
			if (j%18 == 0) j = 0;
		}
		while (cuentaDel < c + 1) {
			if (aux.getPosicion(j) == Constantes.DELANTERO) {
				con = aux.asignarConvocado(aux.getDorsal(j));
				con.setJugando(true);
				if (cuentaDel == c) {
					con.setPosicion(-5);
					auxc2.add(con);
				}else {
					con.setPosicion(1+a+b+cuentaDel);
					auxc.add(con);
				}

				cuentaDel++;
			}
			j++;
			if (j%18 == 0) j = 0;
		}

		if (asignandoActual == 0) {
			eq1 = aux;
			eq1conv = auxc;
			eq1res = auxc2;
		}
		else {
			eq2 = aux;
			eq2conv = auxc;
			eq2res = auxc2;
		}
	}
	
	public Integer getPosicion(Integer n) {//dado un indice retorna su posicion
		EquipoFutbol aux = null;
		Vector<Convocado> auxc = null;
		Vector<Convocado> auxr = null;
		if (asignandoActual == 0) auxc = eq1conv;
		else auxc = eq2conv;
		if (asignandoActual == 0) aux = eq1;
		else aux = eq2;
		if (asignandoActual == 0) auxr = eq1res;
		else auxr = eq2res;

		String name = aux.getFutbolista(n).getNombre();
		for (int i = 0; i < auxc.size(); i++) {
			if (name == auxc.get(i).getNombre()) return auxc.get(i).getPosicion();
		}
		for (int i = 0; i < auxr.size(); i++) {
			if (name == auxr.get(i).getNombre()) return auxr.get(i).getPosicion();
		}
		
		return -6;
	}
	
	public String quienOcupaPosicion(Integer n) {//dada una posicion retorna quien la ocupa
		Vector<Convocado> auxc = null;
		Vector<Convocado> auxr = null;
		if (asignandoActual == 0) auxc = eq1conv;
		else auxc = eq2conv;
		if (asignandoActual == 0) auxr = eq1res;
		else auxr = eq2res;


		
		for (int i = 0; i < auxc.size(); i++) {
			if (n == auxc.get(i).getPosicion()) return auxc.get(i).getNombre();
		}
		for (int i = 0; i < auxr.size(); i++) {
			if (n == auxr.get(i).getPosicion()) return auxr.get(i).getNombre();
		}
		
		return "Nadie";
	}
	
	public void resetAssig() {
		if (asignandoActual == 0) {
			eq1conv = new Vector<Convocado>();
			eq1res = new Vector<Convocado>();
		}
		else {
			eq2conv = new Vector<Convocado>();
			eq2res = new Vector<Convocado>();
		}
	}
	
	public Boolean completo() {
		Vector<Convocado> aux = null;
		
		if (asignandoActual == 0) aux = eq1conv;
		else aux = eq2conv;
		
		if (aux.size() == 11) return true;
		else {
			return false;
		}
		
	}


	
	public EquipoFutbol getEq1() {
		return eq1;
	}
	
	public EquipoFutbol getEq2() {
		return eq2;
	}
	
	
	public String getNombreEquipo(Integer i) {
		if (i == 0) return eq1.getNombre();
		else if (i == 1) return eq2.getNombre();
		else return "Error";
	}

	public void iniciaPartido() {

		
		if (jugadores == 2) {
			asignandoActual = 0;
			alineacionAutomatica();
			asignandoActual = 1;
			alineacionAutomatica();
		}
		else if (jugadores == 1){
			asignandoActual = 1;
			alineacionAutomatica();
		}
		
		if (CtrlPresentacion.getInstancia().getJugadoresPartidaRapida() == 0) {
			eq1.setUsuario(true);
			eq2.setUsuario(true);
		}else if (CtrlPresentacion.getInstancia().getJugadoresPartidaRapida() == 1){
			eq1.setUsuario(true);
			eq2.setDificultad(dificultad);
		}else {
			eq1.setDificultad(dificultad);
			eq2.setDificultad(dificultad);
		}
		
		
		Partido p = new Partido(eq1, eq2, 0);
		p.setLocales(eq1conv);
		p.setVisitantes(eq2conv);
		p.setLocalesReserva(eq1res);
		p.setVisitantesReserva(eq2res);
		p.setAlineacionLocal(alineacion1);
		p.setAlineacionVisitante(alineacion2);
		p.setTiempoTotal(tiempo);
		p.setVisitanteIAEquipo(iaJ2);
		p.setLocalIAEquipo(iaJ1);
		p.iniciarPartido();
		



		
		CtrlSimulacion derp = CtrlSimulacion.getInstancia();
		derp.inicioSimulacion(eq2conv, eq1conv,p, false);
	}
	
	
	
}
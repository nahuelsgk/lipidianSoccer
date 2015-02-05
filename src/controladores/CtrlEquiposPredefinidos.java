/*
 * Alexandre Vidal Obiols
 * Jaume Vinyes Navass
 */


package controladores;

import domain.EquipoFutbol;

public class CtrlEquiposPredefinidos {
	
	private EquipoFutbol[] eq; // Equips Predefinits del sistema
	private boolean[] agafats; // Equips que ja han estat elegits a la lliga
	
	public CtrlEquiposPredefinidos() {
		eq = new EquipoFutbol[8];
		agafats = new boolean[8];
		for (int i = 0; i < 8; ++i) agafats[i] = false;
	}
	
	public EquipoFutbol getEquipoPredef(Integer x) {
		return eq[x];
	}
	
	private boolean esValido(String nombre, int max, boolean b) { 
		
		/*Retorna false si el nom ja el te un altre equip predefinit 
		o un equip del sistema a la lliga si b es cert*/
		
		for(int i = 0; i < max; ++i) {
			if(eq[i].getNombre().compareTo(nombre) == 0) { 
				return false;
			}
		}

		if (b && CtrlPresentacion.getInstancia().getPartidaRapida() == 0) {

				String[] s = CtrlPresentacion.getInstancia().getEquiposLiga();
				Integer n = CtrlPresentacion.getInstancia().getNumeroEquipoSistema();
				for(int i = 0; i < n; ++i) {
					if(s[i].compareTo(nombre) == 0) { 
						return false;
					}
				}

		}


		return true;
	}
	
	public void InitEquiposPredefinidos(boolean b) { 

		//Inicialitza els equips predefinits
		
		for (int i = 0; i < 8; ++i) {
			eq[i] = new EquipoFutbol();
			while (!esValido(eq[i].getNombre(), i, b)) {
				eq[i] = new EquipoFutbol();
			}
		}
	}
	
	public String[] getNombresPredefinidos() {
		String[] s = new String[8];
		for (int i = 0; i < 8; i++){
			s[i] = eq[i].getNombre();
		}
		return s;
	}
	
	public String[] getNombresPredefinidosLibres() {
		int k = 0;
		for (int j = 0; j < 8; ++j) if (!agafats[j]) k++;
		String[] s = new String[k];
		Integer i = 0;
		int g = 0;
		while (i < 8) {
			if (!agafats[i]){
				s[g] = eq[i].getNombre();
				g++;
			}
			i++;
		}
		return s;
	}
	
	public String[] getNombresJPredefinidos(int n) {
		String[] s = new String[18];
		for (int i = 0; i < 18; ++i) {
			s[i] = eq[n].getNombre(i);
		}
		
		return s;
	}
	
	public String[] getNombresJPredefinidosString(String nombreEquipo) {
		int n = 0;
		
		for (int i = 0; i < 8; ++i) {
			if (eq[i].getNombre().compareTo(nombreEquipo) == 0) break;
			n++;
		}
		
		String[] s = new String[18];
		for (int i = 0; i < 18; ++i) {
			s[i] = eq[n].getNombre(i);
		}
		
		return s;
	}

	
	public int getPredefinidosString(String nombreEquipo, int indice, int getter) {
		int n = 0;
		for (int i = 0; i < 8; ++i) {
			if (eq[i].getNombre().equals(nombreEquipo)) break;
			n++;
		}
		
		switch (getter) {
			case CtrlPresentacion.DORSAL:
				return eq[n].getDorsal(indice);
			case CtrlPresentacion.TIPO:
				return eq[n].getPosicion(indice);
			case CtrlPresentacion.AGRESIVIDAD:
				return eq[n].getAgresividad(indice);
			case CtrlPresentacion.REGATE:
				return eq[n].getRegate(indice);
			case CtrlPresentacion.VELOCIDAD:
				return eq[n].getVelocidad(indice);
			case CtrlPresentacion.RESISTENCIA:
				return eq[n].getResistencia(indice);
			case CtrlPresentacion.REMATE:
				return eq[n].getRemate(indice);
			case CtrlPresentacion.PARADA:
				return eq[n].getParada(indice);
			case CtrlPresentacion.PASE:
				return eq[n].getPase(indice);
			case CtrlPresentacion.EXPERIENCIA:
				return eq[n].getExperiencia(indice);
			case CtrlPresentacion.PESO:
				return eq[n].getPeso(indice);
			default:
				return -1;
		}
	}
	
	public float getAlturaString(String s, int indice) {
		int n = 0;
		for (int i = 0; i < 8; ++i) {
			if (eq[i].getNombre().equals(s)) return eq[i].getAltura(indice);
			n++;
		}
		
		return 0;
	}
	
	public int getDorsal(int x, int y) {
		return eq[x].getDorsal(y);
	}
	
	public int getMedia(int x, int y) {
		return eq[x].getMedia(y);
	}
	
	public int getPosicion(int x, int y) {
		return eq[x].getPosicion(y);
	}
	
	public int getPase(int x, int y) {
		return eq[x].getPase(y);
	}
	
	public int getAgresividad(int x, int y) {
		return eq[x].getAgresividad(y);
	}
	
	public int getRegate(int x, int y) {
		return eq[x].getRegate(y);
	}
	
	public int getVelocidad(int x, int y) {
		return eq[x].getVelocidad(y);
	}
	
	public int getResistencia(int x, int y) {
		return eq[x].getResistencia(y);
	}
	
	public int getRemate(int x, int y) {
		return eq[x].getRemate(y);
	}
	
	public int getPeso(int x, int y) {
		return eq[x].getPeso(y);
	}
	
	public float getAltura(int x, int y) {
		return eq[x].getAltura(y);
	}
	
	public int getParada(int x, int y) {
		return eq[x].getParada(y);
	}
	
	public Integer getExperiencia(String eq2, int y) {
		int n = 0;
		for (int i = 0; i < 8; ++i) {
			if (eq[i].getNombre().equals(eq2)) break;
			n++;
		}
		return eq[n].getExperiencia(y);
	}
	
	public EquipoFutbol getEquipo(int x) {
		for (int i = 0; i < 8; ++i) {
		}
		return eq[x];
	}
	
	public EquipoFutbol getEquipoNombre(String s) {
		EquipoFutbol aux = new EquipoFutbol();
		for (int i = 0; i < 8; ++i) {
			if (eq[i].getNombre().equals(s)) return eq[i];
		}
		
		return aux;
	}


	public void setEquipoPredefinido(String equipo, String nombre, String campo, String localidad) {
		int n = 0;
		for (int i = 0; i < 8; ++i) {
			if (eq[i].getNombre().equals(equipo)) break;
			n++;
		}
		eq[n].setCampo(campo);
		eq[n].setCiudad(localidad);
		eq[n].setNombre(nombre);
	}

	public void modificaJugador(String nombre, Float altura,
			int peso, int posicion,String equipo, int y) {
		int n = 0;
		for (int i = 0; i < 8; ++i) {
			if (eq[i].getNombre().equals(equipo)) break;
			n++;
		}
		eq[n].setNombre(y, nombre);
		eq[n].setAltura(y, altura);
		eq[n].setPeso(y, peso);
		eq[n].setPosicion(y, posicion);
	}


	public Integer calculaExperiencia(Integer atributo, String eq2, Integer y) {
		int n = 0;
		for (int i = 0; i < 8; ++i) {
			if (eq[i].getNombre().equals(eq2)) break;
			n++;
		}
		return eq[n].calculaExperiencia(atributo,y);
	}

	public void bajarAtributo(int atributo, String eq2, int y) {
		int n = 0;
		for (int i = 0; i < 8; ++i) {
			if (eq[i].getNombre().equals(eq2)) break;
			n++;
		}
		eq[n].bajarAtributo(atributo,y);
	}

	public void subirAtributo(int atributo, String eq2, int y) {
		int n = 0;
		for (int i = 0; i < 8; ++i) {
			if (eq[i].getNombre().equals(eq2)) break;
			n++;
		}
		eq[n].subirAtributo(atributo,y);		
	}

	public void setAgafats(String s, boolean b){
		for (int i = 0; i < 8; i++) {
			if (s.equals(eq[i].getNombre())) {
				agafats[i] = true;
				break;
			}
			
		}
	}

	public boolean esValido(String equipo) {
		return esValido(equipo, 8, false);
	}
	
}
/*
 * Jaume Vinyes Navas
 * Alexandre Vidal Obiols
 * Alberto Moreno Vega
 * Nahuel Velazco Sanchez
 * 
 */


package domain;


public final class Constantes {
	// Errores
	public final static int ERROR=-1;
	
	// Acciones
	
	public final static int CHUTAR=100;
	public final static int CORRERHACIA=101;
	public final static int SALTAR=101;
	
	// Situaciones
	public final static int AREALOCAL=200;
	public final static int AREAVISITANTE=201;
	public final static int LATERALIZQUIERDO=202;
	public final static int LATERALDERECHO=203;
	public final static int CENTRALIZQUIERDO=204;
	public final static int CENTRALDERECHO=205;
	public final static int FUERADELCAMPO=230;
	
	// Posiciones jugador
	public final static int DELANTERO=300; 
	public final static int MEDIO=301; 
	public final static int DEFENSA=302; 
	public final static int PORTERO=303;
	
	//Estrategias Entrenado
	public final static int AGRESIVA=400;
	public final static int DEFENSIVA=401;
	public final static int NEUTRAL=402;

	//Atributos Jugador
	public final static int REMATE=500;
	public final static int PARADA=501;
	public final static int VELOCIDAD=502;
	public final static int RESISTENCIA=503;
	public final static int REGATE=504;
	public final static int PASE=505;
	public final static int AGRESIVIDAD=506;
	
	public final static int FACIL = 700;
	public final static int NORMAL = 701;
	public final static int DIFICIL = 702;
	
	//Constantes experiencia
	public final static int VALOR_BASE = 10; // coste de experiencia minimo
	public final static int RANGO_INCREMENTO = 10; //cada cuanto incrementa el coste de un atributo
	public final static float INCREMENTO_COSTE = (float) 25.0; //% de incremento del coste
	public final static int EXPERIENCIA_GOL = 100;
	public final static int EXPERIENCIA_PASE = 20;
	public final static int EXPERIENCIA_PARTIDO_GANADO = 100;
	public final static int EXPERIENCIA_PARTIDO_EMPATADO = 50;
	public final static int EXPERIENCIA_PARTIDO_PERDIDO = 25;

	
	
	//Constantes tipo persona
	public final static int FUTBOLISTA = 600;
	public final static int FISIOTERAPEUTA = 601;
	public final static int ENTRENADOR = 602;
	
	
	//resultados partido
	public final static int PARTIDO_GANADO = 800;
	public final static int PARTIDO_PERDIDO = 801;
	public final static int PARTIDO_EMPATADO = 802;

	//Constantes de control
	public final static int NUMERO_EQUIPOS = 8; // no tocar
	public final static int JUGADORES_EQ_PREDEF = 18; // no tocar

	//Constantes de alineacion
	public final static int ALINEACION_4_3_3 = 0;
	public final static int ALINEACION_4_4_2 = 1;
	public final static int ALINEACION_5_4_1 = 2;
	public final static int ALINEACION_5_3_2 = 3;
	
	private Constantes(){
	    throw new AssertionError();
	  }

}

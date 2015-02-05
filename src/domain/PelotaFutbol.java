/*
 * Alberto Moreno Vega
 */


package domain;

import java.util.Random;


public class PelotaFutbol extends Pilota{

  private boolean posesion;
  private boolean chutado;
  private int temporizador; //despues de chutar transcurre un tiempo hasta que alguien puede volver a coger el balon
  private Convocado c;
  private boolean chutePorteria;
  
  //constantes de chute
  public static final int TIRO_SUAVE = 1; 			//la fuerza justa para que el balon llegue al punto
  public static final int TIRO_NORMAL = 2; 		//fuerza para que el balon recorra un 50% mas de lo necesario
  public static final int TIRO_POTENTE = 3;		//fuerza para que el balon recorra el doble de lo necesario
  public static final int TIRO_MUY_POTENTE = 4; 	//fuerza para que el balon recorra el triple de lo necesario
  public static final int TIRO_RASO = 5;			//los tiros rasos van sin componente Z
  public static final int TIRO_MEDIO = 6;			//los tiros altos van con una componente Z proporcional a la fuerza
  public static final int TIRO_ALTO = 7;			//por muy potente que sea un tiro, nunca tendra una componente superior a 100
  
  public static final int PORTERIA_DERECHA = 1;
  public static final int PORTERIA_IZQUIERDA = 0;
  //constantes calculo de chute
  /*
   *supondremos que una pelota recorre 4,5 veces la inversa del rozamiento iteraciones
   *antes de detenerse (es una simplificacion burda, pero nos permite ahorrarnos una deribada)
   *Mediante esta suposicion, con un rozamiento de 0,5% tendriamos 900 iteraciones antes de detenerse
   */
  private static final double SIMPLIFICACION = 4.5;


  protected double CHOQUE_POSTE = 0.05;
  protected double CHOQUE_RED = 0.9;
  
  //constantes de posesion
  private static final double RANGO_POSESION = 3; //debes estar a RANGO_POSESION decimetros para poder coger el balon 
  
    /**
   * 
   * @element-type Partido
   */

  public PelotaFutbol(double x, double y, double z, String nombre) {
	  super(x, y, z, nombre);
	  posesion = false;
	  chutado = false;
	  chutePorteria = false;
  }
  
  
  	public void siguientePosicion() {
  		if (temporizador > 0) temporizador--;
  		if (chutado) {  //si alguien me acaba de chutar, es cuando empiezo a moberme que desactivo la posesion
  			chutado = false;
  			posesion = false;
  		}
  		if (posesion) {
  			posX = c.getPosX();
			posY = c.getPosY();
			posZ = 0;
  		}
  		else {
  			double antX, antY, antZ;
  			antX = posX;
  			antY = posY;
  			antZ = posZ;
	  		avanza();
	  		compruebaRebote(antX, antY, antZ);
  		}
  	
  		
  	}
  	
  	private void compruebaRebote(double antX, double antY, double antZ) {
  		if (antZ < 24.4) {
  			if (antX < 1000 && posX > 1000) {
  				if (choquePoste(386.6, 387.6, antY) || choquePoste(312.4, 313.4, antY)) {
  					reboteConPoste(antX, antY, antZ);
  				}
  			}
  			else if (antX > 0 && posX < 0) {
  				if (choquePoste(386.6, 387.6, antY) || choquePoste(312.4, 313.4, antY)) {
  					reboteConPoste(antX, antY, antZ);
  				}
  			}
  			if (antX < 1024 && posX > 1024) {
  				if (antY > 313.4 && antY < 387.6) {
  					reboteConRedX(antX, antY, antZ);
  				}
  			}
  			else if (antX > -24 && posX < -24) {
  				if (antY > 313.4 && antY < 387.6) {
  					reboteConRedX(antX, antY, antZ);
  				}
  			}
  			else if (antY < 386.6 && posY > 386.6) {
  				if (posX > 1000 && posX < 1024) {
  					reboteConRedY(antX, antY, antZ);
  				}
  				else if (posX < 0 && posX > -24) {
  					reboteConRedY(antX, antY, antZ);
  				}
  			}
  			else if (antY > 386.6 && posY < 386.6) {
  				if (posX > 1000 && posX < 1024) {
  					reboteConRedY(antX, antY, antZ);
  				}
  				else if (posX < 0 && posX > -24) {
  					reboteConRedY(antX, antY, antZ);
  				}
  			}
  			
  			else if (antY < 313.4 && posY > 313.4) {
  				if (posX > 1000 && posX < 1024) {
  					reboteConRedY(antX, antY, antZ);
  				}
  				else if (posX < 0 && posX > -24) {
  					reboteConRedY(antX, antY, antZ);
  				}
  			}
  			else if (antY > 313.4 && posY < 313.4) {
  				if (posX > 1000 && posX < 1024) {
  					reboteConRedY(antX, antY, antZ);
  				}
  				else if (posX < 0 && posX > -24) {
  					reboteConRedY(antX, antY, antZ);
  				}
  			}
  		}
  	}
  	
  	private void reboteConRedX(double antX, double antY, double antZ) { 
  		fX = -fX;
 
  		posX = antX;
  		
  		fX -= fX*CHOQUE_RED;
  		fY -= fY*CHOQUE_RED;
  		fZ -= fZ*CHOQUE_RED;
  		
  		
  	}
  	
  	private void reboteConRedY(double antX, double antY, double antZ) { 
  		fY = -fY;
  		 
  		posY = antY;
  		
  		fX -= fX*CHOQUE_RED;
  		fY -= fY*CHOQUE_RED;
  		fZ -= fZ*CHOQUE_RED;
  	}
  	
  	private void reboteConPoste(double antX, double antY, double antZ) {
  		fX = -fX;
  		if (posX < 0) {
  			posX = -posX;
  		}
  		else {
  			posX = 1000 - (posX - 1000);
  		}
  		fX -= fX*CHOQUE_POSTE;
  		fY -= fY*CHOQUE_POSTE;
  		fZ -= fZ*CHOQUE_POSTE;
  	}

  	
  	private boolean choquePoste(double yMin, double yMax, double antY) {
  		if (antY < yMax && antY > yMin) {
  			return true;
  		}
  		if (antY < yMin) {
  			if (posY > yMin) {
  				return true;
  			}
  		}
  		if (antY > yMax) {
  			if (posY < yMax) {
  				return true;
  			}
  		}
  		return false;
  	}
  	
  	private double distancia(double x1, double y1, double x2, double y2) {
  		Double x = x2-x1;
  		Double y = y2-y1;
  		y *= y;
  		x *= x;
  		return Math.sqrt(x+y);
  	}
  	
  	public void chutar(double x, double y, int precision, int potencia, int altura) { 

  		double dist = distancia(posX, posY, x, y);
  		double f;
  		f = (((dist/ITERACIONES_SEG)*RANGO_MAXIMO)/VELOCIDAD_MAXIMA) + 0.5;
  		f = f*1.75;
  		/*
  		 * f contiene de forma burda el valor teorico de m/s necesario para, en el tiempo de recorrido calculado,
  		 * recorrer la distancia justa hasta el objetivo. Todo eso, normalizado a los valores de rango 100. La suma de
  		 * 0.5 es para contrarestar la aproximacion a 0 de velocidades pequeï¿½as. A partir de este dato se puede saber
  		 * la fuerza total necesaria para alcanzar el objetivo si el tiro fuera raso. A continuacion se aplican coeficientes
  		 * de reduccion en funcion de la altura del tiro (tiros altos tienen menos rozamiento)
  		 */
  		double fz;
  		switch (altura) {
	  		case(TIRO_RASO):  fz = 0; break;
	  		case(TIRO_MEDIO): fz = 15; f = f*3/4; break;
	  		case(TIRO_ALTO):  fz = 30; f = f*2/3; break;
	  		default: fz = 0; break;
  		}
  		switch (potencia) {
	  		case (TIRO_SUAVE): fz = fz*0.5; break;
	  		case (TIRO_NORMAL): f = f*5/4; break;
	  		case (TIRO_POTENTE): f = f*1.8; fz = fz*1.5; break;
	  		case(TIRO_MUY_POTENTE): f = f*3; fz = fz*2; break;
  		default: break;
  		}
  		if (f > 100) f = 100; //potencia maxima
  		//ahora f contiene la fuerza total, a repartir entre X e Y en funcion del angulo. fz contiene la fuerza en z final
  		double dx = x-posX;
  		double dy = y-posY;
  		double cosAlpha = dx/dist;
  		//double senAlpha = dy/dist;
  		
  		double alpha = Math.toDegrees(Math.acos(cosAlpha)); //con 1 de precision, desvio de +-25º, con 100 no hay desvio
  		double desvio = RANGO_MAXIMO-precision+2; //el +2 evita que de 0 y es necesario para que se cumpla la descripcion
  		int desv = (int) (desvio/2);
  		Random r = new Random();
  		if (desv < 1) { //no deveria suceder, pero si lo hace asi evitamos que rand de error
  			desv = 1;
  		}
  		int aleat = r.nextInt(desv);
  		double aleat2 = (double)aleat;
  		if (dy < 0) {
  			alpha = 360 - alpha;
  		}
  		alpha = (alpha - desvio/4) + aleat2;
  		cosAlpha = Math.cos(Math.toRadians(alpha));
  		double senAlpha = Math.sin(Math.toRadians(alpha));
  		fX = f*cosAlpha;
  		fY = f*senAlpha;
  		fZ = fz;
  		chutado = true;
  		posesion = false;
  		temporizador = 3;
  	}
  	
  	
	public boolean enRango(double x, double y, double altura) {
		if ((distancia(x, y, posX, posY) < RANGO_POSESION )) {
			if (((altura*15) > posZ)) return true;
			return false;
		}
		return false;
  	}
  	
  	
  	private boolean regateo(int reg1, int reg2) {
  		Random rand = new Random();
  		int gana1 = rand.nextInt(100);	//bajando estos valores se le da mas peso al nivel de regateo
  		int gana2 = rand.nextInt(100);
  		if (reg1 > reg2) gana1 += reg1-reg2;
  		else if (reg2 > reg1) gana2 += reg2-reg1;
  		return gana1>gana2;
  	}
  	
  	public boolean enRangoPortero(Convocado c) {
  		if ((c.getPosicionActual().distanciaEnPlano(getXYZ()) < RANGO_POSESION+(c.getParadaPartido()/30)) && (c.getAltura()*1.7 >= posZ)) {
  			return true;
  		}
  		return false;
  	}
  	
  	public boolean paradaDePortero(Convocado c) {
  		if (posesion == true || temporizador > 0) {
  			return false;
  		}
  		if (enRangoPortero(c)) {
  			if (balonAgarrado(c)) {
  				if (chutePorteria) {
  					if (c.getPosicion() == 0) {
  						c.incrementaParadas();
  					}
  					chutePorteria = false;
  				}
	  			posX = c.getPosX();
				posY = c.getPosY();
				posZ = 0;
				posesion = true;
				this.c = c;
				return true;
  			}
  			else {
  				temporizador = 3;
  			}
  		}
  		return false;
  	}
  	
  	private boolean balonAgarrado(Convocado c) {
  		/* El balon con mas fuerza es 116.5. Un portero para automaticamente balones hasta 15 fuerza.
  		 * Un portero con 100 de parada para automaticamente balones hasta 25 de fuerza.
  		 * Un futbolista normal se considera un portero con 1 de parada.
  		*/
  		double fuerza = calculaModuloFuerza();
		double parada = 1;
		if (c.getPosicion() == 0) {
			parada = c.getParadaPartido();
		}
		double capacidadParada = 15 + (parada/10);
		
		//capacidadParada = 20; //con esto no se tiene en cuenta la parada del portero
		
		if (fuerza < capacidadParada) {
			return true;
		}
		else {
			fuerza -= capacidadParada;
			Random r = new Random();
			int x = r.nextInt(100);
			if (x > fuerza) {
				return true;
			}
			return false;
		}
  	}
  	
  	private double calculaModuloFuerza() {
  		double modulo;
  		modulo = fX*fX+fY*fY+fZ*fZ;
  		modulo = Math.sqrt(modulo);
  		return modulo;
  	}
  	
  	public boolean solicitarPosesion(Convocado c) {
  		if (temporizador > 0) return false;
  		if (enRango(c.getPosX(), c.getPosY(), c.getAltura())) {
  			if (posesion == true) {
  				if ((c.getCampo() != this.c.getCampo()) && this.c.getPosicion() != 0 && regateo(c.getRegatePartido(), this.c.getRegatePartido())) {
  					boolean falta = false;
  					this.c.setPosesion(false);
  					this.c.conmocionRegateo();
  					if (Arbitraje.getInstancia().faltaProducida(c, this.c)) {
  						falta = true;
  					}
  					Arbitraje.getInstancia().comprobarLesion(c, this.c);
  					this.c = c;
  					posX = c.getPosX();
  					posY = c.getPosY();
  					posZ = 0;
  					chutado = false; //si ya me habian chutado, ahora ya no
  					if (falta) {
  						return false;
  					}
  					return true;
  				}
  				else return false;
  			}
  			else if (balonAgarrado(c)){
  				if (chutePorteria) {
  					if (c.getPosicion() == 0) {
  						c.incrementaParadas();
  					}
  					chutePorteria = false;
  				}
  				posX = c.getPosX();
				posY = c.getPosY();
				posZ = 0;
  				posesion = true;
  				this.c = c;
  				return true;
  			}
  			
  		}
  		return false;
  	}
  
  	  	
  	public XYZ getXYZ() {
  		XYZ xyz = new XYZ(posX, posY, posZ);
  		return xyz;
  	}
  	
  	public void resetearFuerzas() {
  		fX = 0;
  		fY = 0;
  		fZ = 0;
  	}
  	

  	
  	public XYZ posicionDeIntercepcion(double distanciaPosible, XYZ posicionActual, int parada) { 
  		//Si alguien tiene la pelota, devuelve su posicion actual, si no:
  		//devuelve la posicion mas cercana a posicionActual desde la que es posible interceptar el balon, si esta intercepcion
  		//se puede producir en 3000 o menos iteraciones. En caso contrario devuelve la posicion actual del balon
  		XYZ posicionPelota = new XYZ(this.posX, this.posY, 0);
  		if (posesion) {
  			return posicionPelota;
  		}
  		double posX = this.posX;
  		double posY = this.posY;
  		double posZ = this.posZ;
  		double fX = this.fX;
  		double fY = this.fY;
  		double fZ = this.fZ;
  		double distanciaAcumulada = distanciaPosible+RANGO_POSESION+(parada/30);
  		int i;
  		for (i = 0; i < 3000 && distanciaAcumulada < posicionActual.distanciaEnPlano(posicionPelota); i++) {  			
	  		posX += fX*DESPLAZAMIENTO_POR_PUNTO*PUNTOS_POR_METRO;
	        posY += fY*DESPLAZAMIENTO_POR_PUNTO*PUNTOS_POR_METRO;
	        posZ += fZ*DESPLAZAMIENTO_POR_PUNTO*PUNTOS_POR_METRO;
	        if (posZ > 0) {
	            fX -= fX*ROZAMIENTO_AIRE;
	            fX += FUERZA_AIRE_X;
	            fY -= fY*ROZAMIENTO_AIRE;
	            fX += FUERZA_AIRE_Y;
	            fZ -= fZ*ROZAMIENTO_AIRE;
	            fZ += FUERZA_AIRE_Z;
	            fZ -= VARIACION_FZ;
	        }
	        else {
	            fX -= fX*ROZAMIENTO_CESPED;
	            fY -= fY*ROZAMIENTO_CESPED;
	        }
	        if(posZ < 0) {
	            posZ = 0;
	            fZ -= fZ*REBOTE_CESPED;
	            fZ = -fZ;
	        }
	        if(Math.abs(fX) < FUERZA_MINIMA) fX = 0;
	        if(Math.abs(fY) < FUERZA_MINIMA) fY = 0;
	        if(Math.abs(fZ) < FUERZA_MINIMA) fZ = 0;
	        posicionPelota.setX(posX);
  			posicionPelota.setY(posY);
  			distanciaAcumulada += distanciaPosible;
  		}
  		if (i == 3000) {
  			XYZ posicionPlano = new XYZ(this.posX, this.posY, 0);
  			return posicionPlano;
  		}
  		return posicionPelota;
  	}
  	
  	
  	public void tiroAPuerta(int porteria, int remate, int potencia, int altura) {
  		int apuntar;
  		chutePorteria = true;
  		Random r = new Random();
  		apuntar = r.nextInt(466);
  		apuntar -= remate;
  		double y = apuntar;
  		y = y/100;
  		if (r.nextInt(2) == 1) y +=350;
  		else y = 350 - y;
  		if (porteria == PORTERIA_DERECHA) {
  			chutar(1100, y, remate, potencia, altura);
  		}
  		else chutar(-100, y, remate, potencia, altura);
  	}
  	
  	public boolean getPosesion() {
  		return this.posesion;
  	}
  	
  	public Convocado getConvocado() {
  		return this.c;
  	}
  	
  	public void resetearPosesion() {
  		if (posesion) {
  			c.setPosesion(false);
  			posesion = false;
  		}
  		chutePorteria = false;
  	}
  	
  	public double getVectorFX(){
  		return this.fX;
  	}
  	public double getVectorFY(){
  		return this.fY;
  	}
  	public double getVectorFZ(){
  		return this.fZ;
  	}
}
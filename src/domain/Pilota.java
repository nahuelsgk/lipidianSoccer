package domain;

/**
 *
 * @author Daniel Ramirez Sanchez
 */

import javax.vecmath.Vector3d;

public class Pilota {
    protected double posX;  // Posicion de la pelota (ancho)
    protected double posY;  // Posicion de la pelota (largo)
    protected double posZ;  // Posicion de la pelota (altura)

    protected double fX;
    protected double fY;
    protected double fZ;

    protected String nombre; 
    protected double presion;
    protected double diametro;
    protected double peso;

    protected static final int MODIFICADOR_VELOCIDAD = 1;
  /*
   * Hipotesis de calculo:
   * - 25 ms entre iteraciones (40 iteraciones por segundo)
   * - 26 m/s de velocidad media de chutes/remates
   * - 28 m/s de velocidad maxima en un chute/remate
   * - 0.1 m/s de velocidad minima del balon (menos, se considera cero absoluto). A partir de -0.1 se considera que la pelota esta en retroceso
   * - 30% de perdida cinetica al rebotar contra el suelo en fútbol. En caso de voley se para el juego.
   * - 10% de perdida cinetica al rebotar contra superficies solidas. En caso de voley se para el juego.
   * - 0,5% de perdida cinetica (por iteracion) debido al rozamiento con el aire. Salvo modificaciones
   * - 1% de perdida cinetica (por iteracion) en el rozamiento con el cesped (para tiros rasos). En caso de voley se para el juego.
   * - Aceleracion vertical segun la formula v = g*t, donde t = 0,025, g = 9,81, v = 0,245 m/s de variacion de velocidad en Z.
   * - La variacion por iteracion en por gravedad fZ es de aprox 0,245*100 (100 es el rango maximo)
   *
   * Significados de los rangos de fuerza:
   * - 100 equivale al maximo, correspondiendo con una velocidad en el eje de 28 m/s
   * - 0 indica no movimiento en el eje, los rangos intermedios se extrapolan linealmente
   * - cada valor incrementa en 0,28 m/s la velocidad
   */

  // Constantes del cálculo de posicion en funcion de fuerza
  protected static final double RANGO_MAXIMO = 100;  // Fuerzas máximas de 100
  protected double ROZAMIENTO_AIRE = 0.005;
  protected double FUERZA_AIRE_X =  0.0;
  protected double FUERZA_AIRE_Y = 0.0;
  protected double FUERZA_AIRE_Z = 0.0;
  protected static final double ROZAMIENTO_CESPED = (double) 0.01;  //pierde un 1% de fuerza por iteracion
  protected static final double VELOCIDAD_MAXIMA = 28;
  protected static final double VELOCIDAD_MINIMA = 0.1; // Velocidades negativas implican el retroceso del balón
  protected static final double FUERZA_MINIMA = VELOCIDAD_MINIMA/(VELOCIDAD_MAXIMA/RANGO_MAXIMO); // Menos implica fuerza nula
  protected static final double SEGS_ITERACION = (double) 0.025; // Tiempo (en segundos) entre iteraciones (periodo)
  protected static final double ITERACIONES_SEG = 1/SEGS_ITERACION; // Numero de iteraciones por segundo (frecuencia)
  protected static final double DESPLAZAMIENTO_POR_PUNTO = (VELOCIDAD_MAXIMA/RANGO_MAXIMO)/ITERACIONES_SEG; // Cuanto modifica cada punto de fuerza las coordenadas
  protected static final double PUNTOS_POR_METRO = 10; // 10 puntos de coordenada son 1 metro en la vida real
  protected static final double REBOTE_CESPED = 0.30; // Al rebotar con el cesped la fuerza en el eje Z se reduce un 30%
  protected static final double FUERZA_GRAVEDAD = 9.81; // Constante gravitacional
  protected static final double VARIACION_FZ = SEGS_ITERACION*FUERZA_GRAVEDAD*RANGO_MAXIMO/VELOCIDAD_MAXIMA;


    public Pilota(double x, double y, double z, String name) {
        posX = x;
        posY = y;
        posZ = z;
        fX = 0;
        fY = 0;
        fZ = 0;
        nombre = name;
    }

    // Getters
    public double getX() {
        return posX;
    }

    public double getY() {
        return posY;
    }

    public double getZ() {
        return posZ;
    }

    // Colocar la pelota
    public void setXYZ(double x, double y, double z) {
        posX = x;
        posY = y;
        posZ = z;
    }

    // Siguiente posición de la pelota
    public void avanza() {
        // Avanzamos la pelota
        posX += fX*DESPLAZAMIENTO_POR_PUNTO*PUNTOS_POR_METRO;
        posY += fY*DESPLAZAMIENTO_POR_PUNTO*PUNTOS_POR_METRO;
        posZ += fZ*DESPLAZAMIENTO_POR_PUNTO*PUNTOS_POR_METRO;

        // La pelota no esta tocando suelo, friccion con el viento + gravedad.
        if (posZ > 0) {
            fX -= fX*ROZAMIENTO_AIRE;
            fX += FUERZA_AIRE_X;
            fY -= fY*ROZAMIENTO_AIRE;
            fX += FUERZA_AIRE_Y;
            fZ -= fZ*ROZAMIENTO_AIRE;
            fZ += FUERZA_AIRE_Z;
            fZ -= VARIACION_FZ;
        }
        // La pelota toca suelo, solo contamos rozamiento con el césped. Ni viento ni gravedad.
        else {
            fX -= fX*ROZAMIENTO_CESPED;
            fY -= fY*ROZAMIENTO_CESPED;
        }

        // Rebote
        if(posZ < 0) {
            posZ = 0;
            fZ -= fZ*REBOTE_CESPED;
            fZ = -fZ;
        }

         // Acotamos rangos mínimos
        if(Math.abs(fX) < FUERZA_MINIMA) fX = 0;
        if(Math.abs(fY) < FUERZA_MINIMA) fY = 0;
        if(Math.abs(fZ) < FUERZA_MINIMA) fZ = 0;
    }

    // ¿Donde tocara suelo?
    public Vector3d boteEsperado() {
        Vector3d pos = new Vector3d(posX, posY, posZ);
        Vector3d f = new Vector3d(fX, fY, fZ);

        while(pos.z != 0) {
            pos.x += f.x*DESPLAZAMIENTO_POR_PUNTO*PUNTOS_POR_METRO;
            pos.y += f.y*DESPLAZAMIENTO_POR_PUNTO*PUNTOS_POR_METRO;
            pos.z += f.z*DESPLAZAMIENTO_POR_PUNTO*PUNTOS_POR_METRO;
            if(pos.z < 0) pos.z = 0;
            f.x -= f.x*ROZAMIENTO_AIRE;
            f.x += FUERZA_AIRE_X;
            f.y -= f.y*ROZAMIENTO_AIRE;
            f.y += FUERZA_AIRE_Y;
            f.z -= f.z*ROZAMIENTO_AIRE;
            f.z += FUERZA_AIRE_Z;
            f.z -= VARIACION_FZ;
            if(Math.abs(f.x) < FUERZA_MINIMA) f.x = 0;
            if(Math.abs(f.y) < FUERZA_MINIMA) f.y = 0;
            if(Math.abs(f.z) < FUERZA_MINIMA) f.z = 0;
        }
        return pos;
    }

    public void setMeteo(Vector3d v){
        FUERZA_AIRE_X = v.x;
        FUERZA_AIRE_Y = v.y;
        FUERZA_AIRE_Z = v.z;
    }

    @Override
    public String toString() {
        return nombre;
    }

}

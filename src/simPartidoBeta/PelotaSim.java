/*
 * Alberto Moreno Vega
 */


package simPartidoBeta;



public class PelotaSim {

   
    private int x;
    private int y;
    private int z;
    private int altura;
    private int anchura;

    public final static int VELOCIDAD = 2;
    
    public PelotaSim() {

    	
        x = SimPartidoBeta.LARGO_CAMPO/2;
        y = SimPartidoBeta.ANCHO_CAMPO/2;
        z = 0;
        altura = 5;
        anchura = 5;

    }



    public int getX() {
        return x;
    }
    
    public int getAltura() {
    	double alt = z;
    	double mult = alt/10;
    	if (mult < 1) mult = 1;
    	double tam = mult*altura;
        return (int)tam;
    }
    
    public int getAnchura() {
    	double alt = z;
    	double mult = alt/10;
    	if (mult < 1) mult = 1;
    	double tam = mult*anchura;
    	return (int)tam;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
    
    public void setXYZ(int x, int y, int z) {
    	this.x = x;
    	this.y = y;
    	this.z = z;
    }

 
}
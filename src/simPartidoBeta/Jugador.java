/*
 * Alberto Moreno Vega
 */


package simPartidoBeta;

public class Jugador {
	
	private int x;
    private int y;
    private int dorsal;
    private boolean visible;
    
    public final static int VELOCIDAD = 1;
    public final static int DIAMETRO = 10;
    
    public Jugador(int dorsal, int x, int y) {
    	this.x = x;
    	this.y = y;
    	this.dorsal = dorsal;
    	visible = true;
    }
    
    public void setX(int x) {
    	this.x = x;
    	
    }
    
    public void setY(int y) {
    	this.y = y;
    	
    }

    public void setXY(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    
	public void setVisibidlidad(boolean visible) {
		this.visible = visible;
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getDorsal() {
		return dorsal;
	}
	
	public boolean getVisibilidad() {
		return visible;
	}
}


/*
 * Nahuel Velazco Sanchez
 */

package domain;

public class XYZ {
	private double X;
	private double Y;
	private double Z;
	
	public XYZ(){
		this.X=-1;
		this.Y=-1;
		this.Z=0;
	}
	public XYZ(double x, double y){
		this.X=x;
		this.Y=y;
		this.Z=0;
	}
	
	public XYZ(double x, double y, double z){
		this.X=x;
		this.Y=y;
		this.Z=z;
	}
	
	public void setX(double xPos){
		this.X = xPos;
	}
	public void setY(double yPos){
		this.Y = yPos;
	}
	public void setZ(double zPos){
		this.Z = zPos;
	}
	public double getX(){
		return this.X;
	}
	public double getY(){
		return this.Y;
	}
	public double getZ(){
		return this.Z;
	}
	
	public boolean superposicion(XYZ comparacion){
		if((this.getX() == comparacion.getX())  && 
				(this.getY() == comparacion.getY()) &&
				(this.getZ() ==  comparacion.getZ())) return true;
		else return false;
	}
	
	public double distanciaEnPlano(XYZ comparacion) {
		return Math.hypot(comparacion.getX()-this.X, comparacion.getY()-this.Y);
	}
}

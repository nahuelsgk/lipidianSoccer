/*
 * Alberto Moreno Vega
 * 
 */


package domain;


public class Fisioterapeuta extends Persona {

	protected Integer recuperacion;

	protected Integer valor;
	
	
	/*
	 * constructora
	 */

	public Fisioterapeuta (Integer rec, Integer valor) {
		super(Persona.HOMBRE);
  		recuperacion = rec;
  		this.valor = valor;
	}
	
	/*
	 * Getters y setters
	 */
	public Integer getRecuperacion( ) {
  		return recuperacion;
  	}
	
	public Integer getValor( ) {
  		return valor;
  	}
	
	public void setRecuperacion(Integer rec){
		recuperacion = rec;
	}
	
	public void setValor(Integer val){
		valor = val;
	}
}
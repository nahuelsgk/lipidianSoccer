/*
 * Alberto Moreno Vega
 * 
 */


package domain;

public class Entrenador extends Persona {

  private Integer estrategia;

  private Integer incrementador;

  private Integer rebaja;

  	/*
  	 * constructora
  	 */
  	public Entrenador(int est, Integer inc, Integer reb) { 
  		super(Persona.HOMBRE);
  		
  		estrategia = est;
  		incrementador = inc;
  		rebaja = reb;
  	}
  	
  	/*
  	 * Getters y setters
  	 */
  	public Integer getEstrategia() {
  		return estrategia;
  	}
  	
  	public Integer getIncrementador() {
  		return incrementador;
  	}
  	
  	public Integer getRebaja() {
  		return rebaja;
  	}
  	
  	public void setEstrategia(Integer est) {
  		estrategia = est;
  	}
  	
  	public void setIncrementador(Integer inc) {
  		incrementador = inc;
  	}
  	
  	public void setRebaja(Integer reb) {
  		rebaja = reb;
  	}
 
}
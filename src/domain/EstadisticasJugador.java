/*
 * Alexandre Vidal Obiols
 */


package domain;


public class EstadisticasJugador extends Estadisticas {

  private Integer pasesBuenos;

  private Integer pasesRealizados;

  private Integer faltasRecibidas;

  private Integer faltasRealizadas;

  private Integer partidosJugados;
  
  private Integer paradas;
  
  	public EstadisticasJugador() {
  		pasesBuenos = 0;
  		pasesRealizados = 0;
  		faltasRecibidas = 0;
  		faltasRealizadas = 0;
  		partidosJugados = 0;
  		paradas = 0;
  	}
  	
	public Integer getPasesBuenos() {
		return pasesBuenos;
	}


	public void setPasesBuenos(Integer pasesBuenos) {
		this.pasesBuenos = pasesBuenos;
	}
	
	
	public Integer getPasesRealizados() {
		return pasesRealizados;
	}
	
	
	public void setPasesRealizados(Integer pasesRealizados) {
		this.pasesRealizados = pasesRealizados;
	}
	
	
	public Integer getFaltasRecibidas() {
		return faltasRecibidas;
	}
	
	
	public void setFaltasRecibidas(Integer faltasRecibidas) {
		this.faltasRecibidas = faltasRecibidas;
	}
	
	
	public Integer getFaltasRealizadas() {
		return faltasRealizadas;
	}
	
	
	public void setFaltasRealizadas(Integer faltasRealizadas) {
		this.faltasRealizadas = faltasRealizadas;
	}
	
	
	public Integer getPartidosJugados() {
		return partidosJugados;
	}
	
	
	public void setPartidosJugados(Integer partidosJugados) {
		this.partidosJugados = partidosJugados;
	}
	
	public void setParadas(Integer par) {
		this.paradas = par;
	}
	
	public Integer getParadas() {
		return this.paradas;
	}


	public Float porcentagePasesBuenos() {
		if (this.pasesRealizados == 0) return (float)0.0;
		return (float)this.pasesBuenos/this.pasesRealizados;
	  }
	

}
/*
 * Alexandre Vidal Obiols
 */


package domain;


public class EstadisticasEquipo extends Estadisticas {

	private Integer puntos;
	
	private Integer ganadosConsec;
	
	private Integer perdidosConsec;
	
	private Integer partidosGanados;
	
	private Integer partidosEmpatados;
	
	private Integer partidosPerdidos;

	
	public EstadisticasEquipo() {
		puntos = 0;
		ganadosConsec = 0;
		perdidosConsec = 0;
		partidosGanados = 0;
		partidosEmpatados = 0;
		partidosPerdidos = 0;
	}
	
	public Integer getPuntos() {
		return puntos;
	}
	
	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}
	
	public Integer getGanadosConsec() {
		return ganadosConsec;
	}
	
	public void setGanadosConsec(Integer ganadosConsec) {
		this.ganadosConsec = ganadosConsec;
	}
	
	public Integer getPerdidosConsec() {
		return perdidosConsec;
	}
	
	public void setPerdidosConsec(Integer perdidosConsec) {
		this.perdidosConsec = perdidosConsec;
	}
	
	public Integer getPartidosGanados() {
		return partidosGanados;
	}
	
	public void setPartidosGanados(Integer partidosGanados) {
		this.partidosGanados = partidosGanados;
	}
	
	public Integer getPartidosEmpatados() {
		return partidosEmpatados;
	}
	
	public void setPartidosEmpatados(Integer partidosEmpatados) {
		this.partidosEmpatados = partidosEmpatados;
	}
	
	public Integer getPartidosPerdidos() {
		return partidosPerdidos;
	}
	
	public void setPartidosPerdidos(Integer partidosPerdidos) {
		this.partidosPerdidos = partidosPerdidos;
	}

  
}
/*
 * Alexandre Vidal Obiols
 */


package domain;


public abstract class Estadisticas {
	
	private Integer golesRecibidos;
	
	private Integer golesMarcados;
	
	private Integer tarjetasAmarillas;
	
	private Integer tarjetasRojas;

	
	public Estadisticas() {
		golesRecibidos = 0;
		golesMarcados = 0;
		tarjetasAmarillas = 0;
		tarjetasRojas = 0;
	}
	
	
	public Integer getGolesRecibidos() {
		return golesRecibidos;
	}
	
	public void setGolesRecibidos(Integer golesRecibidos) {
		this.golesRecibidos = golesRecibidos;
	}

	public Integer getGolesMarcados() {
		return golesMarcados;
	}
	
	public void setGolesMarcados(Integer golesMarcados) {
		this.golesMarcados = golesMarcados;
	}
	
	public Integer getTarjetasAmarillas() {
		return tarjetasAmarillas;
	}
	
	public void setTarjetasAmarillas(Integer tarjetasAmarillas) {
		this.tarjetasAmarillas = tarjetasAmarillas;
	}
	
	public Integer getTarjetasRojas() {
		return tarjetasRojas;
	}
	
	public void setTarjetasRojas(Integer tarjetasRojas) {
		this.tarjetasRojas = tarjetasRojas;
	}

  
}
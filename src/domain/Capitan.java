/*
 *  Alberto Moreno Vega
 * 
 * 
 * NOTA: Esta clase pertenece a un objetivo opcional del enunciado. Solo se implementara si hay tiempo suficiente.
 */

package domain;

public class Capitan extends Futbolista {

  public Capitan(EquipoFutbol equip,
			Integer posicion, Integer dorsal) {
		super(equip, posicion, dorsal);
	}

public Integer liderazgo;

  public String habilidad;

}
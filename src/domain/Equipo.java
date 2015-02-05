/*
 * Jaume Vinyes Navas
 */

package domain;

import java.util.Random;

public abstract class Equipo {

	protected String nombre;
	
	protected String ciudad;
		
	protected String campo;
		
	protected Integer oro;
    
	public final static String[] NOMBRESE = {"Belbush","Blackville","Bluefield Barrens","Blueholt","Byshore",
		"Castlebeach","Cleartown Barrens","Deerbush","Dorfort","Dorshore","Esterbush Island","Esterfall Moor",
		"Fairmill","Fallland","Fallmoor","Falltown Crags","Foxhill","Grasscliff","Greymarsh","Greywater Mill",
		"Havenbarrow","Havencastle","Hedgebank","Highley","Hollowcastle","Lightbeach","Magegate","Meadowcastle",
		"Northwitch Cliff","Ostden","Pondedge","Redlake","Riverland","Shadowmeadow","Snowdell","Snowland",
		"Snowmill Crossing","Spellhedge","Springmere","Springwall Point","Starrysage Moor","Summershore","Swynwall",
		"Violetland","Wellcrest","Wellwind Bush","Westsage","Whitefall","Winterdale","Wintermage Edge","Aldborough",
		"Belfield","Bluebeach Crags","Bluelake","Butterbush","Castleland","Clearton Crossing","Cliffbush","Corshore",
		"Crystalburn","Dellmont","Dorbeach","Dortown","Falconpond","Faybridge","Glassland Crossing","Goldviolet Meadow",
		"Griffinhaven","Havencliff","Ironcliff Downs","Ironmount Crags","Janwyn","Lightsummer Mill","Mallowmoor",
		"Merrihollow","Merrowland","Northmarsh","Norwald","Oldfield","Oldfield Mill","Oldland","Orwyn","Ostlyn",
		"Rayden","Redmarsh Forest","Sagemount","Silvermead","Snowcrest Island","Southhaven","Stoneness","Strongbridge",
		"Strongspring","Swynlyn","Vertcastle Island","Wellglass","Westvale","Wildehollow","Winterfall","Witchbeach","Woodmill"};
    /*
     * Setter y Getters
     */
    
    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public Integer getOro() {
		return oro;
	}

	public void setOro(Integer oro) {
		this.oro = oro;
	}

	public String getNombreAleatrio() {
		Random r = new Random();
		int n = r.nextInt(100);
		String s = Equipo.NOMBRESE[n];
		return s;
	}
	
	public Equipo() {
		String s = getNombreAleatrio();
		this.nombre = s;
		this.ciudad = s;
		this.campo = getNombreAleatrio();
		this.oro = 1000;
	}

}
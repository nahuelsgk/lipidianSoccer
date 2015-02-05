/*Autor: Nahuel Velazco*/
package controladores;

public class CtrlReglamento {
	public CtrlReglamento(){
		
	}
	
	public String leerReglamento(){
		return CtrlDatos.getInstancia().leerArchivoReglamento();
	}
}

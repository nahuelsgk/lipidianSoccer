/*Autor: Nahuel Velazco*/
package controladores;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import datos.ioArchivoIAXML;
import datos.ioDatosEncriptado;
import datos.ioReglamento;
import datos.recordsData;

public class CtrlDatos {
	private static CtrlDatos instancia;
	
	public static CtrlDatos getInstancia() {
		if(instancia == null) {
			instancia = new CtrlDatos();
		}
		return instancia;
	}
	
	public CtrlDatos(){
		
	}
	/*BEGIN Ia: Archivo de configuracion*/
	public Document leerArchivoConfiguracionIA() throws ParserConfigurationException, IOException{
		ioArchivoIAXML iaConf = new ioArchivoIAXML();
		Document XML = iaConf.leerArchivo();
		return XML;
	}
	
	public void crearArchivoConfiguracionIAPorDefecto(){
		ioArchivoIAXML iaConf = new ioArchivoIAXML();
		iaConf.crearXMLPorDefecto();
	}
	public void salvarIaConf(Document xML) {
		ioArchivoIAXML iaConf = new ioArchivoIAXML();
		iaConf.escribirArchivo(xML);
	}
	/*END Ia Archivo de configuracion*/
	
	
	/*BEGIN Gestion Datos Liga*/
	public Document cargarLigaDatos(String nombreLiga) throws Exception{
		ioDatosEncriptado io = new ioDatosEncriptado(nombreLiga);
		return (Document)io.leerDisco();
	}
	
	public String[] listarArchivosLigas() {
		ioDatosEncriptado io = new ioDatosEncriptado();
		return io.listarArchivosCifrados();
	}
	
	public void salvaLigaDatos(Document XML,String nombreLiga) throws Exception{
		ioDatosEncriptado io = new ioDatosEncriptado(nombreLiga+".les");
		io.escribirEnDisco(XML);
	}
	public void eliminarLigaDatos(String nombreLiga) throws Exception{
		ioDatosEncriptado io = new ioDatosEncriptado(nombreLiga);
		io.eliminarEnDisco();
	}
	/*END Gestion Datos Liga*/
	
	/*BEGIN Records*/
	public Document leerArchivoRecords() throws Exception{
		recordsData records = new recordsData();
		return records.leerRecords();
	}
	
	public void escribirRecords(Document XML) throws Exception{
		recordsData records = new recordsData();
		records.salvarRecords(XML);
	}
	/*END Records*/
	
	/*BEGIN Reglamentos*/
	public String leerArchivoReglamento(){
		ioReglamento io = new ioReglamento();
		return io.leerArchivoReglamento();
	}
	/*END Reglamentos*/

}

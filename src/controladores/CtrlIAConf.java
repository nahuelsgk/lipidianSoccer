/*Autor: Nahuel Velazco*/
package controladores;


import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import domain.IA;

public class CtrlIAConf {
	private static CtrlIAConf instancia;
	Document XML;
	
	public CtrlIAConf() throws ParserConfigurationException, SAXException, IOException {
		XML = CtrlDatos.getInstancia().leerArchivoConfiguracionIA();
	}
	
	public void init(){
		//Cargar a la IA todos los parametros
		IA.getInstancia().setConfiguracion(
				this.getValorIntElement("posicionCorner", XML), 
				this.getValorIntElement("zonaDinamica", XML),
				this.getValorDoubleElement("cansancio", XML),
				this.getValorDoubleElement("presion", XML),
				this.getValorDoubleElement("nivelCompanero", XML),
				this.getValorDoubleElement("intercepcion", XML),
				this.getValorDoubleElement("distanciaPase", XML),
				this.getValorDoubleElement("distanciaPoteria", XML),
				this.getValorDoubleElement("random", XML),
				this.getValorDoubleElement("fueraJuego", XML));
	}

	/*BEGIN: Posicion Corner*/
	public int getPosicionCornerValor(){
		Element root = XML.getDocumentElement();
		NodeList nl = root.getElementsByTagName("posicionCorner");
		if(nl.getLength()==0){
			CtrlDatos.getInstancia().crearArchivoConfiguracionIAPorDefecto();
		}
		else{
			Element posicionCornerElement =  (Element) nl.item(0);
			NodeList nll = posicionCornerElement.getElementsByTagName("valor");
			return Integer.parseInt(((Element)nll.item(0)).getTextContent());
		}
		return 0;
	}
	
	public String getPosicionCornerNombre(){
		Element root = XML.getDocumentElement();
		NodeList nl = root.getElementsByTagName("posicionCorner");
		if(nl.getLength()==0){
			CtrlDatos.getInstancia().crearArchivoConfiguracionIAPorDefecto();
		}
		else{
			Element posicionCornerElement =  (Element) nl.item(0);
			NodeList nll = posicionCornerElement.getElementsByTagName("nombre");
			return ((Element)nll.item(0)).getTextContent();
		}
		return null;
	}
	public void setPosicionCornerValor(int valor){
		setValorElement("posicionCorner", String.valueOf(valor), XML);
		CtrlDatos.getInstancia().salvarIaConf(XML);
	}
	/*END: Posicion Corner*/
	
	/*BEGIN: Zona Dinamica*/
	public int getZonaDinamicaValor(){
		Element root = XML.getDocumentElement();
		NodeList nl = root.getElementsByTagName("zonaDinamica");
		if(nl.getLength()==0){
			CtrlDatos.getInstancia().crearArchivoConfiguracionIAPorDefecto();
		}
		else{
			Element zonaDinamicaElement =  (Element) nl.item(0);
			NodeList nll = zonaDinamicaElement.getElementsByTagName("valor");
			return Integer.valueOf(((Element)nll.item(0)).getTextContent());
		}
		return 0;
	}

	public String getZonaDinamicaNombre(){
		Element root = XML.getDocumentElement();
		NodeList nl = root.getElementsByTagName("zonaDinamica");
		if(nl.getLength()==0){
			CtrlDatos.getInstancia().crearArchivoConfiguracionIAPorDefecto();
		}
		else{
			Element zonaDinamicaElement =  (Element) nl.item(0);
			NodeList nll = zonaDinamicaElement.getElementsByTagName("nombre");
			return ((Element)nll.item(0)).getTextContent();
		}
		return null;
	}
	public void setZonaDinamicaValor(int valor){
		setValorElement("zonaDinamica", String.valueOf(valor), XML);
		CtrlDatos.getInstancia().salvarIaConf(XML);
	}
	/*END: Zona Dinamica*/
	
	/*BEGIN cansancio*/
	public double getCansancioValor(){
		return this.getValorDoubleElement("cansancio", XML);
	}
	public void setCansancioValor(double valor){
		setValorElement("cansancio", String.valueOf(valor), XML);
		CtrlDatos.getInstancia().salvarIaConf(XML);
	}
	/*END cansancio*/
	/*BEGIN presion*/
	public double getPresionValor(){
		return this.getValorDoubleElement("presion", XML);
	}
	public void setPresionValor(double valor){
		setValorElement("presion", String.valueOf(valor), XML);
		CtrlDatos.getInstancia().salvarIaConf(XML);
	}
	/*END presion*/
	/*BEGIN nivelCompanero*/
	public double getNivelCompaneroValor(){
		return this.getValorDoubleElement("nivelCompanero", XML);	
	}
	public void setNivelCompaneroValor(double valor){
		setValorElement("nivelCompanero", String.valueOf(valor), XML);
		CtrlDatos.getInstancia().salvarIaConf(XML);
	}
	/*END nivelCompanero*/
	/*BEGIN intercepcion*/
	public double getIntercepcionValor(){
		return this.getValorDoubleElement("intercepcion", XML);	
	}
	public void setIntercepcionValor(double valor){
		setValorElement("intercepcion", String.valueOf(valor), XML);
		CtrlDatos.getInstancia().salvarIaConf(XML);
	}
	/*END intercepcion*/
	/*BEGIN distanciaPase*/
	public double getDistanciaPaseValor(){
		return this.getValorDoubleElement("distanciaPase", XML);
	}
	public void setDistanciaPaseValor(double valor){
		setValorElement("distanciaPase", String.valueOf(valor), XML);
		CtrlDatos.getInstancia().salvarIaConf(XML);
	}
	/*END distanciaPase*/
	/*BEGIN distanciaPoteria*/
	public double getDistanciaPorteriaValor(){
		return this.getValorDoubleElement("distanciaPoteria", XML);	
	}
	public void setDistanciaPorteriaValor(double valor){
		setValorElement("distanciaPoteria", String.valueOf(valor), XML);
		CtrlDatos.getInstancia().salvarIaConf(XML);
	}
	/*END distanciaPoteria*/
	/*BEGIN random*/
	public double getRandomValor(){
		return this.getValorDoubleElement("random", XML);
	}
	public void setRandomValor(double valor){
		setValorElement("random", String.valueOf(valor), XML);
		CtrlDatos.getInstancia().salvarIaConf(XML);
	}
	/*END random*/
	/*BEGIN fueraJuego*/
	public double getFueraJuegoValor(){
		return this.getValorDoubleElement("fueraJuego", XML);	
	}
	public void setFueraValor(double valor){
		setValorElement("fueraJuego", String.valueOf(valor), XML);
		CtrlDatos.getInstancia().salvarIaConf(XML);
	}
	/*END fueraJuego*/
	/*Genericas*/
	/*private String getNombreElement(String tag , Document xml){
		Element root = xml.getDocumentElement();
		NodeList nl = root.getElementsByTagName(tag);
		if(nl.getLength()==0){
			CtrlDatos.getInstancia().crearArchivoConfiguracionIAPorDefecto();
		}
		else{
			Element nombreElement =  (Element) nl.item(0);
			NodeList nll = nombreElement.getElementsByTagName("nombre");
			return ((Element)nll.item(0)).getTextContent();
		}
		return null;
	}
	*/
	
	private int getValorIntElement(String tag, Document xml){
		Element root = xml.getDocumentElement();
		NodeList nl = root.getElementsByTagName(tag);
		if(nl.getLength()==0){
			CtrlDatos.getInstancia().crearArchivoConfiguracionIAPorDefecto();
		}
		else{
			Element tagElement =  (Element) nl.item(0);
			NodeList nll = tagElement.getElementsByTagName("valor");
			return Integer.valueOf(((Element)nll.item(0)).getTextContent());
		}
		return 0;
	}
	
	private double getValorDoubleElement(String tag, Document xml){
		Element root = xml.getDocumentElement();
		NodeList nl = root.getElementsByTagName(tag);
		if(nl.getLength()==0){
			CtrlDatos.getInstancia().crearArchivoConfiguracionIAPorDefecto();
		}
		else{
			Element tagElement =  (Element) nl.item(0);
			NodeList nll = tagElement.getElementsByTagName("valor");
			return Double.valueOf(((Element)nll.item(0)).getTextContent());
		}
		return 0;
	}
	private void setValorElement(String tag,String value,Document XML){
		NodeList confRaiz = XML.getElementsByTagName("iaconf");
		Element confRaizElement = (Element) confRaiz.item(0);
		
		NodeList uniqueConfRaiz = confRaizElement.getElementsByTagName(tag);
		Element uniqueConfRaizElement = (Element) uniqueConfRaiz.item(0);
		
		NodeList uniqueConfValueRaiz = uniqueConfRaizElement.getElementsByTagName("valor");
		Element uniqueConfValueRaizElement = (Element) uniqueConfValueRaiz.item(0);
		uniqueConfValueRaizElement.getParentNode().removeChild(uniqueConfValueRaizElement);
		
		
		Element tagElement = XML.createElement(tag);
			Element idElement = XML.createElement("valor");
			idElement.setTextContent(value);
			tagElement.appendChild(idElement);
		uniqueConfRaizElement.appendChild(tagElement);
	}
	/*END Genericas*/
	public static CtrlIAConf getInstancia() {
		if(instancia == null) {
			try {
				instancia = new CtrlIAConf();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instancia;
	}
}
	
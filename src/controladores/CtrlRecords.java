/*Autor: Nahuel Velazco*/
package controladores;

import java.io.StringWriter;
import java.util.Vector;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import datos.recordsData;
import domain.EquipoFutbol;
import domain.Futbolista;
import domain.Liga;

public class CtrlRecords {
	private Document XML;
	recordsData records;
	public CtrlRecords(){
		try {
			records =  new recordsData();
			XML = records.leerRecords();
			//printarPorPantalla(XML);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void setJugadorMasGoles(String nombre, int valor){
		reemplazaElementoRecord("jugadorMasGoles", nombre, valor);
	}
	public void setJugadorTarjetasRojas(String nombre, int valor){
		reemplazaElementoRecord("jugadorMasRojas", nombre, valor);
	}
	public void setJugadorTarjetasAmarillas(String nombre, int valor){
		reemplazaElementoRecord("jugadorMasAmarillas", nombre, valor);
	}
	public void setPorteroMasParadas(String nombre, int valor){
		reemplazaElementoRecord("porteroMasParadas", nombre, valor);
	}
	public void setEquipoMasGolesMarcados(String nombre, int valor){
		reemplazaElementoRecord("equipoMasGolesMarcados", nombre, valor);
	}
	public void setEquipoMasGoleado(String nombre, int valor){
		reemplazaElementoRecord("equipoMasGoleado", nombre, valor);
	}
	public void setEquipoMasPartidosGanados(String nombre, int valor){
		reemplazaElementoRecord("equipoMasPartidosGanados", nombre, valor);
	}
	public void setEquipoMasPartidosPerdidos(String nombre, int valor){
		reemplazaElementoRecord("equipoMasPartidosPerdidos", nombre, valor);
	}
	public void setEquipoMasPartidosEmpatados(String nombre, int valor){
		reemplazaElementoRecord("equipoMasPartidosEmpatados", nombre, valor);
	}
	public int getJugadorMasGolesValue(){
		return buscarElementValor("jugadorMasGoles");
	}
	public String getJugadorMasGolesNombre(){
		return buscarElementNombre("jugadorMasGoles");
	}
	
	public int getJugadorTarjetasRojasValue(){
		return buscarElementValor("jugadorMasRojas");
	}
	public String getJugadorTarjetasRojasNombre(){
		return buscarElementNombre("jugadorMasRojas");
	}
	
	public int getJugadorTarjetasAmarillasValue(){
		return buscarElementValor("jugadorMasAmarillas");
	}
	public String getJugadorTarjetasAmarillasNombre(){
		return buscarElementNombre("jugadorMasAmarillas");
	}
	
	public int getPorteroMasParadasValue(){
		return buscarElementValor("porteroMasParadas");
	}
	public String getPorteroMasParadasNombre(){
		return buscarElementNombre("porteroMasParadas");
	}
	
	public int getEquipoMasGolesMarcadosValue(){
		return buscarElementValor("equipoMasGolesMarcados");
	}
	public String getEquipoMasGolesMarcadosString(){
		return buscarElementNombre("equipoMasGolesMarcados");
	}
	
	public int getEquipoMasGoleadoValue(){
		return buscarElementValor("equipoMasGoleado");
	}
	public String getEquipoMasGoleadoString(){
		return buscarElementNombre("equipoMasGoleado");
	}
	
	public int getEquipoMasPartidosGanadosValue(){
		return buscarElementValor("equipoMasPartidosGanados");
	}
	public String getEquipoMasPartidosGanadosNombre(){
		return buscarElementNombre("equipoMasPartidosGanados");
	}
	public int getEquipoMasPartidosPerdidosValue(){
		return buscarElementValor("equipoMasPartidosPerdidos");
	}
	public String getEquipoMasPartidosPerdidosNombre(){
		return buscarElementNombre("equipoMasPartidosPerdidos");
	}
	public int getEquipoMasPartidosEmpatadosValue(){
		return buscarElementValor("equipoMasPartidosEmpatados");
	}
	public String getEquipoMasPartidosEmpatadosNombre(){
		return buscarElementNombre("equipoMasPartidosEmpatados");
	}
	
	
	
	public void printarPorPantalla(Document xml){
		try{
			//Parseamos por pantalla
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");

			//Creamos el string para que lo saque por pantalla.
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(xml);
			trans.setOutputProperty(OutputKeys.INDENT, "yes");
			trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			trans.transform(source, result);
			String xmlString = sw.toString();


			//print xml
			System.out.println("Here's the xml:\n\n" + xmlString);
		}
		catch (Exception e){
			System.out.println("Error en las transformaciones a pantalla");
		}
	}

	
	private String buscarElementNombre(String tag){
		//printarPorPantalla(XML);
		NodeList recordsRaiz = XML.getElementsByTagName("records");
		Element recordsRaizElement = (Element) recordsRaiz.item(0);
		NodeList uniqueRecordRaiz = recordsRaizElement.getElementsByTagName(tag);
		Element uniqueRecordRaizElement = (Element) uniqueRecordRaiz.item(0);
		NodeList nombreUniqueRecord = uniqueRecordRaizElement.getElementsByTagName("id");
		Element nombreUniqueRecordElement = (Element) nombreUniqueRecord.item(0);
		return nombreUniqueRecordElement.getTextContent();
	}
	
	private int buscarElementValor(String tag){
		NodeList recordsRaiz = XML.getElementsByTagName("records");
		Element recordsRaizElement = (Element) recordsRaiz.item(0);
		NodeList uniqueRecordRaiz = recordsRaizElement.getElementsByTagName(tag);
		Element uniqueRecordRaizElement = (Element) uniqueRecordRaiz.item(0);
		NodeList nombreUniqueRecord = uniqueRecordRaizElement.getElementsByTagName("value");
		Element nombreUniqueRecordElement = (Element) nombreUniqueRecord.item(0);
		String retString = nombreUniqueRecordElement.getTextContent();
		int ret =Integer.valueOf(retString);
		return ret;
	}
	
	private void reemplazaElementoRecord(String tag, String nombre , int valor){
		NodeList recordsRaiz = XML.getElementsByTagName("records");
		Element recordsRaizElement = (Element) recordsRaiz.item(0);
		
		NodeList uniqueRecordRaiz = recordsRaizElement.getElementsByTagName(tag);
		Element uniqueRecordRaizElement = (Element) uniqueRecordRaiz.item(0);
		
		uniqueRecordRaizElement.getParentNode().removeChild(uniqueRecordRaizElement);
		
		Element tagElement = XML.createElement(tag);
			Element idElement = XML.createElement("id");
			idElement.setTextContent(nombre);
			tagElement.appendChild(idElement);
			Element valorElement = XML.createElement("value");
			valorElement.setTextContent(String.valueOf(valor));
			tagElement.appendChild(valorElement);
		recordsRaizElement.appendChild(tagElement);
	}

	public void salvarRecords(Liga l) {
		actualizaEquipoMasGoleado(l);
		actualizaMasGolesMarcados(l);
		actualizaMasPartidosEmpatados(l);
		actualizaEquipoMasPartidosGanados(l);
		actualizaEquipoMasPartidosPerdidos(l);
		actualizaJugadorTarjetasAmarillas(l);
		actualizaJugadorTarjetasRojas(l);
		actualizaPorteroMasParadas(l);
		actualizaJugadorMasGoles(l);
		try {
			recordsData records =  new recordsData();
			records.salvarRecords(XML);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//printarPorPantalla(XML);
	}

	private void actualizaJugadorMasGoles(Liga l) {
		int max = getJugadorMasGolesValue();
		Vector<EquipoFutbol> equipos = l.getEquiposDeFutbol();
		int maxActual = 0;
		for(int i=0;i<equipos.size();i++){
			EquipoFutbol iteratorI = equipos.elementAt(i);
			Vector<Futbolista> futbolistas = iteratorI.getFutbolistas();
			for(int j=0;j<futbolistas.size();j++){
				Futbolista iteratorJ = futbolistas.elementAt(j);
				maxActual = iteratorJ.getGolesMarcados();
				if(maxActual>max){
					String id = iteratorJ.getNombre();
					setJugadorMasGoles(id, maxActual);
				}	
			}
		}
		
	}

	private void actualizaPorteroMasParadas(Liga l) {
		int max = getJugadorMasGolesValue();
		Vector<EquipoFutbol> equipos = l.getEquiposDeFutbol();
		int maxActual = 0;
		for(int i=0;i<equipos.size();i++){
			EquipoFutbol iteratorI = equipos.elementAt(i);
			Vector<Futbolista> futbolistas = iteratorI.getFutbolistas();
			for(int j=0;j<futbolistas.size();j++){
				Futbolista iteratorJ = futbolistas.elementAt(j);
					maxActual = iteratorJ.getParadas();
					if(maxActual>max){
						String id = iteratorJ.getNombre();
						setPorteroMasParadas(id, maxActual);
				}
			}
		}
	}

	private void actualizaJugadorTarjetasRojas(Liga l) {
		int max = getJugadorTarjetasRojasValue();
		Vector<EquipoFutbol> equipos = l.getEquiposDeFutbol();
		int maxActual = 0;
		for(int i=0;i<equipos.size();i++){
			EquipoFutbol iteratorI = equipos.elementAt(i);
			Vector<Futbolista> futbolistas = iteratorI.getFutbolistas();
			for(int j=0;j<futbolistas.size();j++){
				Futbolista iteratorJ = futbolistas.elementAt(j);
				maxActual = iteratorJ.getTarjetasRojas();
				if(maxActual>max){
					String id = iteratorJ.getNombre();
					setJugadorTarjetasRojas(id, maxActual);
				}	
			}
		}
	}

	private void actualizaJugadorTarjetasAmarillas(Liga l) {
		int max = getJugadorTarjetasAmarillasValue();
		Vector<EquipoFutbol> equipos = l.getEquiposDeFutbol();
		int maxActual = 0;
		for(int i=0;i<equipos.size();i++){
			EquipoFutbol iteratorI = equipos.elementAt(i);
			Vector<Futbolista> futbolistas = iteratorI.getFutbolistas();
			for(int j=0;j<futbolistas.size();j++){
				Futbolista iteratorJ = futbolistas.elementAt(j);
				maxActual = iteratorJ.getTarjetasAmarillas();
				if(maxActual>max){
					String id = iteratorJ.getNombre();
					setJugadorTarjetasAmarillas(id, maxActual);
				}	
			}
		}
	}

	private void actualizaEquipoMasPartidosPerdidos(Liga l) {
		int max = getEquipoMasPartidosPerdidosValue();
		Vector<EquipoFutbol> equipos = l.getEquiposDeFutbol();
		int maxActual = 0;
		for(int i=0;i<equipos.size();i++){
			EquipoFutbol iterator = equipos.elementAt(i);
			maxActual = iterator.getPartidosPerdidos();
			if(maxActual>max){
				String id = iterator.getNombre();
				setEquipoMasPartidosPerdidos(id, maxActual);
			}
		}
		
	}

	private void actualizaEquipoMasPartidosGanados(Liga l) {
		int max = getEquipoMasPartidosGanadosValue();
		Vector<EquipoFutbol> equipos = l.getEquiposDeFutbol();
		int maxActual = 0;
		for(int i=0;i<equipos.size();i++){
			EquipoFutbol iterator = equipos.elementAt(i);
			maxActual = iterator.getPartidosGanados();
			if(maxActual>max){
				String id = iterator.getNombre();
				setEquipoMasPartidosGanados(id, maxActual);
			}
		}
	}

	private void actualizaMasPartidosEmpatados(Liga l) {
		int max = getEquipoMasPartidosEmpatadosValue();
		Vector<EquipoFutbol> equipos = l.getEquiposDeFutbol();
		int maxActual = 0;
		for(int i=0;i<equipos.size();i++){
			EquipoFutbol iterator = equipos.elementAt(i);
			maxActual = iterator.getPartidosEmpatados();
			if(maxActual>max){
				String id = iterator.getNombre();
				setEquipoMasPartidosEmpatados(id, maxActual);
			}
		}
	}

	private void actualizaEquipoMasGoleado(Liga l) {
		int max = getEquipoMasGoleadoValue();
		Vector<EquipoFutbol> equipos = l.getEquiposDeFutbol();
		int maxActual = 0;
		for(int i=0;i<equipos.size();i++){
			EquipoFutbol iterator = equipos.elementAt(i);
			maxActual = iterator.getGolesRecibidos();
			if(maxActual>max){
				String id = iterator.getNombre();
				setEquipoMasGoleado(id, maxActual);
			}
		}
	}
	private void actualizaMasGolesMarcados(Liga l){
		int max = getEquipoMasGolesMarcadosValue();
		Vector<EquipoFutbol> equipos = l.getEquiposDeFutbol();
		int maxActual = 0;
		for(int i=0;i<equipos.size();i++){
			EquipoFutbol iterator = equipos.elementAt(i);
			maxActual = iterator.getGolesMarcados();
			if(maxActual>max){
				String id = iterator.getNombre();
				setEquipoMasGolesMarcados(id, maxActual);
			}
		}
	}
}

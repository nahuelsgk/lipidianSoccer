/*Autor: Nahuel Velazco*/
package datos;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class recordsData {
	private String filepath;
	
	public recordsData() throws Exception{
		String rutaArchivoRecordsDesdeWorkspace = "Records" + System.getProperty("file.separator") + "Records.les";
		String directorioRecords = System.getProperty("user.dir")+ System.getProperty("file.separator") + "Data" + System.getProperty("file.separator") + "Records";
		String rutaArchivoRecords = directorioRecords + System.getProperty("file.separator") + "Records.les";
		this.filepath = rutaArchivoRecordsDesdeWorkspace;
		
		File directorio = new File(directorioRecords);
		File archivo = new File(rutaArchivoRecords);
		
		if(directorio.exists()==false){
			directorio.mkdirs();
		}
		if(archivo.exists()==false){
			archivo.createNewFile();
			Document defaultArchivoRecords = crearRecordsPorDefecto();
			ioDatosEncriptado io = new ioDatosEncriptado(filepath);
			io.escribirEnDisco(defaultArchivoRecords);
		}
	}
	
	public Document crearRecordsPorDefecto(){
		File file = new File(filepath);
		file.delete();
		
		Document XML = crearXML();
		
		Element iaconf = XML.createElement("records");

		Element jugadorMasGoles = XML.createElement("jugadorMasGoles");
			Element jugadorMasGolesID = XML.createElement("id");
			jugadorMasGolesID.setTextContent("Angela Nebot");
			jugadorMasGoles.appendChild(jugadorMasGolesID);
			Element jugadorMasGolesValue = XML.createElement("value");
			jugadorMasGolesValue.setTextContent("0");
			jugadorMasGoles.appendChild(jugadorMasGolesValue);
		iaconf.appendChild(jugadorMasGoles);
		Element jugadorMasRojas = XML.createElement("jugadorMasRojas");
			Element jugadorMasRojasID = XML.createElement("id");
			jugadorMasRojasID.setTextContent("Nahuel Velazco");
			jugadorMasRojas.appendChild(jugadorMasRojasID);
			Element jugadorMasRojasValue = XML.createElement("value");
			jugadorMasRojasValue.setTextContent("0");
			jugadorMasRojas.appendChild(jugadorMasRojasValue);
		iaconf.appendChild(jugadorMasRojas);
		Element jugadorMasAmarillas = XML.createElement("jugadorMasAmarillas");
			Element jugadorMasAmarillasID = XML.createElement("id");
			jugadorMasAmarillasID.setTextContent("Jaume Vinyas");
			jugadorMasAmarillas.appendChild(jugadorMasAmarillasID);
			Element jugadorMasAmarillasValue = XML.createElement("value");
			jugadorMasAmarillasValue.setTextContent("0");
			jugadorMasAmarillas.appendChild(jugadorMasAmarillasValue);
		iaconf.appendChild(jugadorMasAmarillas);
		Element porteroMasParadas = XML.createElement("porteroMasParadas");
			Element porteroMasParadasID = XML.createElement("id");
			porteroMasParadasID.setTextContent("Alex Vidal");
			porteroMasParadas.appendChild(porteroMasParadasID);
			Element porteroMasParadasValue = XML.createElement("value");
			porteroMasParadasValue.setTextContent("0");
			porteroMasParadas.appendChild(porteroMasParadasValue);
		iaconf.appendChild(porteroMasParadas);
		Element equipoMasGolesMarcados = XML.createElement("equipoMasGolesMarcados");
			Element equipoMasGolesMarcadosID = XML.createElement("id");
			equipoMasGolesMarcadosID.setTextContent("Alberto Moreno");
			equipoMasGolesMarcados.appendChild(equipoMasGolesMarcadosID);
			Element equipoMasGolesMarcadosValue = XML.createElement("value");
			equipoMasGolesMarcadosValue.setTextContent("0");
			equipoMasGolesMarcados.appendChild(equipoMasGolesMarcadosValue);
		iaconf.appendChild(equipoMasGolesMarcados);
		Element equipoMasGoleado = XML.createElement("equipoMasGoleado");
			Element equipoMasGoleadoID = XML.createElement("id");
			equipoMasGoleadoID.setTextContent("Angela Nebot F.C.");
			equipoMasGoleado.appendChild(equipoMasGoleadoID);
			Element equipoMasGoleadoValue = XML.createElement("value");
			equipoMasGoleadoValue.setTextContent("0");
			equipoMasGoleado.appendChild(equipoMasGoleadoValue);
		iaconf.appendChild(equipoMasGoleado);
		Element equipoMasPartidosGanados = XML.createElement("equipoMasPartidosGanados");
			Element equipoMasPartidosGanadosID = XML.createElement("id");
			equipoMasPartidosGanadosID.setTextContent("Nahuel Velazco F.C.");
			equipoMasPartidosGanados.appendChild(equipoMasPartidosGanadosID);
			Element equipoMasPartidosGanadosValue = XML.createElement("value");
			equipoMasPartidosGanadosValue.setTextContent("0");
			equipoMasPartidosGanados.appendChild(equipoMasPartidosGanadosValue);
		iaconf.appendChild(equipoMasPartidosGanados);
		
		Element equipoMasPartidosPerdidos = XML.createElement("equipoMasPartidosPerdidos");
			Element equipoMasPartidosPerdidosID = XML.createElement("id");
			equipoMasPartidosPerdidosID.setTextContent("Alberto Moreno F.C.");
			equipoMasPartidosPerdidos.appendChild(equipoMasPartidosPerdidosID);
			Element equipoMasPartidosPerdidosValue = XML.createElement("value");
			equipoMasPartidosPerdidosValue.setTextContent("0");
			equipoMasPartidosPerdidos.appendChild(equipoMasPartidosPerdidosValue);
		iaconf.appendChild(equipoMasPartidosPerdidos);
		
		Element equipoMasPartidosEmpatados = XML.createElement("equipoMasPartidosEmpatados");
			Element equipoMasPartidosEmpatadosID = XML.createElement("id");
			equipoMasPartidosEmpatadosID.setTextContent("Alex Vidal F.C.");
			equipoMasPartidosEmpatados.appendChild(equipoMasPartidosEmpatadosID);
			Element equipoMasPartidosEmpatadosValue = XML.createElement("value");
			equipoMasPartidosEmpatadosValue.setTextContent("0");
			equipoMasPartidosEmpatados.appendChild(equipoMasPartidosEmpatadosValue);
		iaconf.appendChild(equipoMasPartidosEmpatados);
						
		XML.appendChild(iaconf);
		return XML;
	}

	private Document crearXML(){
		try{
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = fact.newDocumentBuilder();
			Document document = parser.newDocument();
			return document;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public void salvarRecords(Document XML) throws Exception{
		ioDatosEncriptado io = new ioDatosEncriptado(this.filepath);
		io.escribirEnDisco(XML);
	}
	
	public Document leerRecords() throws Exception{
		ioDatosEncriptado io = new ioDatosEncriptado(this.filepath);
		return (Document)io.leerDisco();
	}
}

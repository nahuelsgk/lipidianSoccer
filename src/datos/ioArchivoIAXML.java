/*Autor: Nahuel Velazco*/

package datos;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ioArchivoIAXML {
	private String filepath;
	
	public ioArchivoIAXML(){
		String directorioEjecutable = System.getProperty("user.dir");
		//System.out.println("Se accede al directorio: " + directorioEjecutable);
		String rutaDirectorioArchivoConfiguracion = directorioEjecutable + System.getProperty("file.separator") + "Conf";
		String rutaArchivoConfiguracion = directorioEjecutable + System.getProperty("file.separator") + "Conf" + System.getProperty("file.separator") + "IA.xml";
		File rutaArchivoConfiguracionFile = new File(rutaArchivoConfiguracion);
		File rutaDirectorioConfiguracionFile = new File(rutaDirectorioArchivoConfiguracion);
		this.filepath = rutaArchivoConfiguracion;
		if(rutaDirectorioConfiguracionFile.exists()==false){
			rutaDirectorioConfiguracionFile.mkdir();
		}
		if(rutaArchivoConfiguracionFile.exists()==false){
			Document defaultArchivoConfiguracion = crearXMLPorDefecto();
			escribirArchivo(defaultArchivoConfiguracion);
		}
	}
	
	public Document leerArchivo() throws ParserConfigurationException, IOException{
		File file = new File(this.filepath);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		try {
			Document doc;
			doc = db.parse(file);
			doc.getDocumentElement().normalize();
			return doc;
		} catch (SAXException e) {
			crearXMLPorDefecto();
		}
		return null;
		
	}
	
	public Document crearXMLPorDefecto(){
		File file = new File(filepath);
		file.delete();
		
		Document XML = crearXML();
		
		Element iaconf = XML.createElement("iaconf");

			addOpcionIA("posicionCorner", "\nPosicion Corner: valores posibles (0,1)\n" +
					"0: Los jugadores no se posicionan en formacion cuando ocurre un corner\n" +
					"1: Los jugadores se posicionan estrategicamente cuando ocurre un corner\n", "Posicion de los jugadores en el corner","1", iaconf, XML);
			addOpcionIA("zonaDinamica", "\n Zona dinamica: valore posible (0,1)\n" + 
					"0: Los jugadores juegan de forma estatica manteniendo sus posiciones\n" + 
					"1: Los jugadores juegan dinamicamente\n ", "Zona Dinamica", "1",iaconf, XML);
			addOpcionIA("cansancio", "\nPor defecto 1.\n Si el valor < 1 los futbolistas arriesgan mas y se cansan mas.\n Si el valor es > 1 al reves\n", "Configuracion del cansancio de los jugadores", "1" , iaconf, XML);
			addOpcionIA("presion","\nPor defecto 1. Si el valor < 1 la presion influye mas. Si valor > 1 la presion influye menos\n","Presion hacia los jugadores","1", iaconf,XML);
			addOpcionIA("nivelCompanero", "\nPor defecto 1. \nPeso del nivel del companero para elegir pase.\n", "Peso del nivel companero","1", iaconf, XML);
			addOpcionIA("intercepcion", "\nPor defecto 2. \n Peso de los rivales que interceptan un pase.\n", "Peso de la intercepcion", "2", iaconf, XML);
			addOpcionIA("distanciaPase","\nPor defecto 1. \nPeso de la distancia para realizar un pase.\n","Distancia Pase","1",iaconf,XML);
			addOpcionIA("distanciaPoteria", "\nPor defecto 3. \nPeso de la distancia a porteria para realizar un pase.\n", "Distancia a porteria", "3", iaconf, XML);
			addOpcionIA("random","\nPor defecto 1.\n Peso de los valores aleatorios para seleccionar pases.\n","Random","1",iaconf,XML);
			addOpcionIA("fueraJuego", "\nPor defecto 1.\n Peso del fuera de juego.\n","Peso del fuera de Juego" , "1", iaconf, XML);
		XML.appendChild(iaconf);
		return XML;
	}
	
	public void escribirArchivo(Document doc) { 
		try {  
			Source source = new DOMSource(doc); 
			 
			File file = new File(this.filepath); 
			StreamResult result = new StreamResult(file); 
			 
			Transformer xformer = TransformerFactory.newInstance().newTransformer(); 
			xformer.setOutputProperty(OutputKeys.INDENT, "yes");
			xformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			xformer.transform(source, result); 
		} 
		catch (TransformerConfigurationException e) {
			System.out.println("Error en la configuracion");
		} 
		catch (TransformerException e) { 
			System.out.println("Error en la transformacion");
		} 
	} 

	public Document crearXML(){
		try{
			//Crear un documento XML: incializas la api, construyes el documento y lo parseas
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
	
	private void addOpcionIA(String tag, String comentario,String descripcion,String valor,Element parent,Document xml){
	Element element = xml.createElement(tag);
	Comment descripcionElement = xml.createComment(comentario);
	element.appendChild(descripcionElement);
		Element elementNombre = xml.createElement("nombre");
		elementNombre.setTextContent(descripcion);
		
		Element elementValor = xml.createElement("valor");
		elementValor.setTextContent(valor);
	element.appendChild(elementNombre);
	element.appendChild(elementValor);
	parent.appendChild(element);
	}
}
/*Autor: Nahuel Velazco*/
package controladores;

import java.io.StringWriter;
import java.util.Vector;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import domain.Convocado;
import domain.Entrenador;
import domain.EquipoFutbol;
import domain.EstadisticasEquipo;
import domain.EstadisticasJugador;
import domain.Fisioterapeuta;
import domain.Futbolista;
import domain.Liga;
import domain.Partido;

public class CtrlGestionLiga {
	
	/*Creadoras*/
	public CtrlGestionLiga(){
	}
	
	
	/*Publicas*/
	public int salvarLiga(Liga l){	
		Document XML = crearXMLVacio();
		crearXMLLiga(XML, l);
		try {
			CtrlDatos.getInstancia().salvaLigaDatos(XML,l.getNom());
			CtrlRecords records = new CtrlRecords();
			records.salvarRecords(l);
		} catch (Exception e) {
				e.printStackTrace();
		}
		return 0;
	}
	
	public int cargarLiga(String ligaGuardada){
		try {
			Document XML = CtrlDatos.getInstancia().cargarLigaDatos(ligaGuardada);
			crearEntornoLiga(XML);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int eliminarLiga(String les){
		try {
			CtrlDatos.getInstancia().eliminarLigaDatos(les);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String[] listarLigas(){
		String[] listadoLigas = CtrlDatos.getInstancia().listarArchivosLigas();
		return listadoLigas;
	}
	
	/*Privadas*/
	private static Document crearXMLVacio(){
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
	
	private Document crearXMLLiga(Document XML, Liga l){
		Element raiz = XML.createElement("liga");
		XML.appendChild(raiz);
		
		addElementToParentWithContent(raiz, "nombre", l.getNom(), XML);
		
		addElementToParentWithAtribute(raiz,"jornada","actual",String.valueOf(l.getJornadaActual()),XML);
		
		//Partido actual
		addElementToParentWithAtribute(raiz,"partido","actual",String.valueOf(l.getPartidoActual()),XML);
		
		//Dificultad
		addElementToParentWithContent(raiz, "dificultad", String.valueOf(l.getDificultad()), XML);
		
		//Temporada
		addElementToParentWithContent(raiz, "temporada", String.valueOf(l.getTemporada()), XML);
		
		//usuario
		addElementToParentWithContent(raiz, "usuarioLiga", String.valueOf(l.getUsuario()), XML);
		
		//Equipos de futbol
		Vector<EquipoFutbol> equipos = l.getEquiposDeFutbol();
		Element raizEquipos = XML.createElement("equipos");
		
		for(int i=0; i< equipos.size() ; i++){
			EquipoFutbol iteratorEquipo = equipos.get(i);
			
			Element raizEquipo = XML.createElement("equipo");
			raizEquipo.setAttribute("id", String.valueOf(i));
			
			addElementToParentWithContent(raizEquipo, "nombre", iteratorEquipo.getNombre(), XML);
			
			//Campo
			addElementToParentWithContent(raizEquipo, "campo", iteratorEquipo.getCampo(), XML);
			
			//Ciudad
			addElementToParentWithContent(raizEquipo,"ciudad",iteratorEquipo.getCiudad(),XML);
			
			//Oro
			addElementToParentWithContent(raizEquipo,"oro",String.valueOf(iteratorEquipo.getOro()),XML);
			
			//Usuario
			addElementToParentWithContent(raizEquipo,"usuario",String.valueOf(iteratorEquipo.esUsuario()),XML);
			
			//dificultadEquipo
			addElementToParentWithContent(raizEquipo,"dificultadEquipo",String.valueOf(iteratorEquipo.getDificultad()),XML);
			
			//esFantasma
			addElementToParentWithContent(raizEquipo, "fantasma", String.valueOf(iteratorEquipo.esFantasma()), XML);
			
			//Entrenador
			Element entrenador = XML.createElement("entrenador");
				//Entrenador Nombre
				addElementToParentWithContent(entrenador,"entrenadorNombre",iteratorEquipo.getEntrenador().getNombre(),XML);
				
				//Entrenador FechaN
				addElementToParentWithContent(entrenador,"entrenadorFechaN",iteratorEquipo.getEntrenador().getFechaN(),XML);
				
				//Entrenador rebaja
				addElementToParentWithContent(entrenador,"entrenadorRebaja",String.valueOf(iteratorEquipo.getEntrenador().getRebaja()),XML);
				
				//Entrenador incrementador
				addElementToParentWithContent(entrenador,"entrenadorIncrementador",String.valueOf(iteratorEquipo.getEntrenador().getIncrementador()),XML);
				
				//Entrenador estrategia
				addElementToParentWithContent(entrenador,"entrenadorEstrategia",String.valueOf(iteratorEquipo.getEntrenador().getEstrategia()),XML);
			raizEquipo.appendChild(entrenador);
			
			//Fisioterateupa
			Element fisioterapeuta = XML.createElement("fisioterapeuta");
				//Fisio Nombre
				addElementToParentWithContent(fisioterapeuta, "fisioterapeutaNombre", iteratorEquipo.getFisio().getNombre(), XML);
				
				//Fisio FechaN
				addElementToParentWithContent(fisioterapeuta, "fisioterapeutaFechaN", iteratorEquipo.getFisio().getFechaN(), XML);
				
				//Fisio Recuperacion
				addElementToParentWithContent(fisioterapeuta, "fisioterapeutaRecuperacion", String.valueOf(iteratorEquipo.getFisio().getRecuperacion()), XML);
				
				//Fisio Valor
				addElementToParentWithContent(fisioterapeuta,"fisioterapeutaValor" , String.valueOf(iteratorEquipo.getFisio().getValor()), XML);
			raizEquipo.appendChild(fisioterapeuta);
			
			//Jugadores
			Vector<Futbolista> futbolistas = iteratorEquipo.getFutbolistas();
			Element futbolistasRaiz = XML.createElement("futbolistas");
			for(int j=0;j<futbolistas.size();j++){
				Futbolista iteratorFutbolista = futbolistas.get(j);
				
				Element futbolistaRaiz = XML.createElement("futbolista");
				futbolistaRaiz.setAttribute("id", String.valueOf(j));
						
				//Agresividad
				addElementToParentWithContent(futbolistaRaiz,"agresividad", String.valueOf(iteratorFutbolista.getAgresividad()), XML);
				
				//altura
				addElementToParentWithContent(futbolistaRaiz,"altura", String.valueOf(iteratorFutbolista.getAltura()), XML);
				
				//dorsal
				addElementToParentWithContent(futbolistaRaiz,"dorsal", String.valueOf(iteratorFutbolista.getDorsal()), XML);
				
				//posicion
				addElementToParentWithContent(futbolistaRaiz,"posicion", String.valueOf(iteratorFutbolista.getPosicion()), XML);
				
				//estrategia
				addElementToParentWithContent(futbolistaRaiz,"estrategia", String.valueOf(iteratorFutbolista.getEstrategia()), XML);
				
				//experiencia
				addElementToParentWithContent(futbolistaRaiz,"experiencia", String.valueOf(iteratorFutbolista.getExperiencia()), XML);
				
				//fechaN
				addElementToParentWithContent(futbolistaRaiz,"fechaN", String.valueOf(iteratorFutbolista.getFechaN()), XML);
				
				//golesMarcados
				addElementToParentWithContent(futbolistaRaiz,"golesMarcados", String.valueOf(iteratorFutbolista.getGolesMarcados()), XML);
				
				//golesRecibidos
				addElementToParentWithContent(futbolistaRaiz,"golesRecibidos", String.valueOf(iteratorFutbolista.getGolesRecibidos()), XML);
				
				//gravedad
				addElementToParentWithContent(futbolistaRaiz,"gravedad", String.valueOf(iteratorFutbolista.getGravedad()), XML);
				
				
				//nombre
				addElementToParentWithContent(futbolistaRaiz,"nombre", String.valueOf(iteratorFutbolista.getNombre()), XML);
				
				//parada
				addElementToParentWithContent(futbolistaRaiz,"parada", String.valueOf(iteratorFutbolista.getParada()), XML);
				
				//pase
				addElementToParentWithContent(futbolistaRaiz,"pase", String.valueOf(iteratorFutbolista.getPase()), XML);
				
				//recuperacion
				addElementToParentWithContent(futbolistaRaiz,"recuperacion", String.valueOf(iteratorFutbolista.getRecuperacion()), XML);
				
				//regate
				addElementToParentWithContent(futbolistaRaiz,"regate", String.valueOf(iteratorFutbolista.getRegate()), XML);
				
				//remate
				addElementToParentWithContent(futbolistaRaiz,"remate", String.valueOf(iteratorFutbolista.getRemate()), XML);
				
				//resistencia
				addElementToParentWithContent(futbolistaRaiz,"resistencia", String.valueOf(iteratorFutbolista.getResistencia()), XML);
				
				//sancionado
				addElementToParentWithContent(futbolistaRaiz,"sancionado", String.valueOf(iteratorFutbolista.getSancionado()), XML);
				
								
				//velocidad
				addElementToParentWithContent(futbolistaRaiz,"velocidad", String.valueOf(iteratorFutbolista.getVelocidad()), XML);
				
				//peso 
				addElementToParentWithContent(futbolistaRaiz,"peso", String.valueOf(iteratorFutbolista.getPeso()), XML);
				
					//Estadisticas
					Element estadisticasJugador = XML.createElement("estadisticasJugador");
					
						//faltasRealizadas
						addElementToParentWithContent(estadisticasJugador, "faltasRealizadas", String.valueOf(iteratorFutbolista.getFaltasRealizadas()), XML);
						
						//pasesRealizados
						addElementToParentWithContent(estadisticasJugador, "pasesRealizados", String.valueOf(iteratorFutbolista.getPasesRealizados()), XML);
						
						//pasesBuenos
						addElementToParentWithContent(estadisticasJugador, "pasesBuenos", String.valueOf(iteratorFutbolista.getPasesBuenos()), XML);
						
						//faltasRecibidas
						addElementToParentWithContent(estadisticasJugador, "faltasRecibidas", String.valueOf(iteratorFutbolista.getFaltasRecibidas()), XML);
						
						//partidosJugados
						addElementToParentWithContent(estadisticasJugador, "partidosJugados", String.valueOf(iteratorFutbolista.getPartidosJugados()), XML);
						
						//targetasAmarillas
						addElementToParentWithContent(estadisticasJugador, "targetasAmarillas", String.valueOf(iteratorFutbolista.getTarjetasAmarillas()), XML);
						
						//targetasRojas
						addElementToParentWithContent(estadisticasJugador, "targetasRojas", String.valueOf(iteratorFutbolista.getTarjetasRojas()), XML);
				futbolistaRaiz.appendChild(estadisticasJugador);
				
				futbolistasRaiz.appendChild(futbolistaRaiz);
				
			}
			raizEquipo.appendChild(futbolistasRaiz);
			
			
			//Estadisticas Equipo
			Element estadisticasEquipo = XML.createElement("estadisticasEquipo");
				//golesRecibidos
				addElementToParentWithContent(estadisticasEquipo, "golesRecibidos", String.valueOf(iteratorEquipo.getGolesRecibidos()), XML);
				
				//golesMarcados
				addElementToParentWithContent(estadisticasEquipo, "golesMarcados", String.valueOf(iteratorEquipo.getGolesMarcados()), XML);
				
				//tarjetasAmarillas
				addElementToParentWithContent(estadisticasEquipo, "tarjetasAmarillas", String.valueOf(iteratorEquipo.getTarjetasAmarillas()), XML);
				
				//tarjetasRojas
				addElementToParentWithContent(estadisticasEquipo, "tarjetasRojas", String.valueOf(iteratorEquipo.getTarjetasRojas()), XML);
				
				//puntos
				addElementToParentWithContent(estadisticasEquipo, "puntos", String.valueOf(iteratorEquipo.getPuntos()), XML);
				
				//ganadosConsec
				addElementToParentWithContent(estadisticasEquipo, "ganadosConsec", String.valueOf(iteratorEquipo.getGanadosConsec()), XML);
				
				//perdidosConsec
				addElementToParentWithContent(estadisticasEquipo, "perdidosConsec", String.valueOf(iteratorEquipo.getPerdidosConsec()), XML);
				
				//partidosGanados
				addElementToParentWithContent(estadisticasEquipo, "partidosGanados", String.valueOf(iteratorEquipo.getPartidosGanados()), XML);
				
				//partidosEmpatados
				addElementToParentWithContent(estadisticasEquipo, "partidosEmpatados", String.valueOf(iteratorEquipo.getPartidosEmpatados()), XML);
				
				//partidosPerdidos
				addElementToParentWithContent(estadisticasEquipo, "partidosPerdidos", String.valueOf(iteratorEquipo.getPartidosPerdidos()), XML);
				
				raizEquipo.appendChild(estadisticasEquipo);
			
			
			raizEquipos.appendChild(raizEquipo);
		}
		raiz.appendChild(raizEquipos);

			Element partidosIdaRaiz = XML.createElement("partidosIda");
				//Bucle para conseguir partidos Ida
				Vector<Partido> partidosIdaVector = l.getPartidosIda();
				for(int i = 0; i < partidosIdaVector.size() ; i ++ ){
					Partido partidoIdaIterator = partidosIdaVector.get(i);	
					Element partidoIdaElement = XML.createElement("partidoIda");
						addElementToParentWithContent(partidoIdaElement, "equipoLocal", String.valueOf(partidoIdaIterator.getEquipoLocal().getNombre()), XML);
						
						addElementToParentWithContent(partidoIdaElement, "equipoVisitante", String.valueOf(partidoIdaIterator.getEquipoVisitante().getNombre()), XML);
						
						addElementToParentWithContent(partidoIdaElement, "cambiosLocal", String.valueOf(partidoIdaIterator.getCambiosLocal()), XML);
						
						addElementToParentWithContent(partidoIdaElement, "cambiosVisitante", String.valueOf(partidoIdaIterator.getCambiosVisitante()), XML);
						
						addElementToParentWithContent(partidoIdaElement, "golesLocal", String.valueOf(partidoIdaIterator.getGolesLocal()), XML);
					
						addElementToParentWithContent(partidoIdaElement, "golesVisitante", String.valueOf(partidoIdaIterator.getGolesVisitante()), XML);
						
						addElementToParentWithContent(partidoIdaElement, "jornada", String.valueOf(partidoIdaIterator.getJornada()), XML);
							
						addElementToParentWithContent(partidoIdaElement, "jugado", String.valueOf(partidoIdaIterator.getJugado()), XML);
						
						addElementToParentWithContent(partidoIdaElement, "finalPartido",String.valueOf(partidoIdaIterator.getFinal()), XML);
						
						addElementToParentWithContent(partidoIdaElement, "tiempoJugado", String.valueOf(partidoIdaIterator.getTiempoJugado()), XML);
						
						addElementToParentWithContent(partidoIdaElement, "tiempoMostrado", String.valueOf(partidoIdaIterator.getTiempoMostrado()), XML);
						
						addElementToParentWithContent(partidoIdaElement, "tiempoTotal", String.valueOf(partidoIdaIterator.getTiempoTotal()), XML);
				
						partidosIdaRaiz.appendChild(partidoIdaElement);
				}
			raiz.appendChild(partidosIdaRaiz);
			
			//Bucle para conseguir partidos Vuelta
			Element partidosVueltaRaiz = XML.createElement("partidosVuelta");
			Vector<Partido> partidosVueltaVector = l.getPartidosVuelta();
			for(int i = 0; i < partidosVueltaVector.size() ; i ++ ){
				Partido partidoVueltaIterator = partidosVueltaVector.get(i);	
				Element partidoVueltaElement = XML.createElement("partidoVuelta");
					partidoVueltaElement.setAttribute("id", String.valueOf(i));
					
					addElementToParentWithContent(partidoVueltaElement, "equipoLocal", String.valueOf(partidoVueltaIterator.getEquipoLocal().getNombre()), XML);
					
					addElementToParentWithContent(partidoVueltaElement, "equipoVisitante", String.valueOf(partidoVueltaIterator.getEquipoVisitante().getNombre()), XML);
					
					addElementToParentWithContent(partidoVueltaElement, "cambiosLocal", String.valueOf(partidoVueltaIterator.getCambiosLocal()), XML);
					
					addElementToParentWithContent(partidoVueltaElement, "cambiosVisitante", String.valueOf(partidoVueltaIterator.getCambiosVisitante()), XML);
					
					addElementToParentWithContent(partidoVueltaElement, "golesLocal", String.valueOf(partidoVueltaIterator.getGolesLocal()), XML);
				
					addElementToParentWithContent(partidoVueltaElement, "golesVisitante", String.valueOf(partidoVueltaIterator.getGolesVisitante()), XML);
					
					addElementToParentWithContent(partidoVueltaElement, "jornada", String.valueOf(partidoVueltaIterator.getJornada()), XML);
						
					addElementToParentWithContent(partidoVueltaElement, "jugado", String.valueOf(partidoVueltaIterator.getJugado()), XML);
					
					addElementToParentWithContent(partidoVueltaElement, "finalPartido",String.valueOf(partidoVueltaIterator.getFinal()), XML);
					
					addElementToParentWithContent(partidoVueltaElement, "tiempoJugado", String.valueOf(partidoVueltaIterator.getTiempoJugado()), XML);
					
					addElementToParentWithContent(partidoVueltaElement, "tiempoMostrado", String.valueOf(partidoVueltaIterator.getTiempoMostrado()), XML);
					
					addElementToParentWithContent(partidoVueltaElement, "tiempoTotal", String.valueOf(partidoVueltaIterator.getTiempoTotal()), XML);
					
				partidosVueltaRaiz.appendChild(partidoVueltaElement);
			}
			
			raiz.appendChild(partidosVueltaRaiz);
			
			Element jornadasIda = XML.createElement("jornadasIda");
				String [][] jornadasIdaMatrix = l.getJornadasIda();
				addElementToParentWithContent(jornadasIda, "sizeMatrixIda", String.valueOf(jornadasIdaMatrix.length), XML);

				for(int i = 0; i <jornadasIdaMatrix.length; i++){
					Element jornadaIdaIterator = XML.createElement("jornadaIda");
					for(int j = 0 ; j < jornadasIdaMatrix[i].length;j++){
						addElementToParentWithContent(jornadaIdaIterator, "partidoJornadaIda", jornadasIdaMatrix[i][j], XML);
					}
					jornadasIda.appendChild(jornadaIdaIterator);
				}
			raiz.appendChild(jornadasIda);
			
			Element jornadasVuelta = XML.createElement("jornadasVuelta");
			String [][] jornadasVueltaMatrix = l.getJornadasVuelta();
			addElementToParentWithContent(jornadasVuelta, "sizeMatrixVuelta", String.valueOf(jornadasVueltaMatrix.length), XML);
			for(int i = 0; i <jornadasVueltaMatrix.length; i++){
				Element jornadaVueltaIterator = XML.createElement("jornadaVuelta");
				for(int j = 0 ; j < jornadasVueltaMatrix[i].length;j++){
					addElementToParentWithContent(jornadaVueltaIterator, "partidoJornadaVuelta", jornadasVueltaMatrix[i][j], XML);
				}
				jornadasVuelta.appendChild(jornadaVueltaIterator);
			}
			raiz.appendChild(jornadasVuelta);
		
			//Controlador Nueva Liga
			Element controladorNuevaLiga = XML.createElement("ctrlNuevaLiga");
			raiz.appendChild(controladorNuevaLiga);
			
			//Controlador Liga. En realidad es para ctrlPresentacion
			Element controladorLiga = XML.createElement("ctrlLiga");
				//Controlador
				EquipoFutbol equipoControladorFutbol = CtrlPresentacion.getInstancia().getCtrlLiga().getEquipo();
				addElementToParentWithContent(controladorLiga, "equipoCtrl", equipoControladorFutbol.getNombre(), XML);
				//Convocados Crtl
				Vector<Convocado> convocadosCtrl = CtrlLiga.getInstancia().getConvocados();
				Element convocadosCtrlRaiz = XML.createElement("convocadosCtrl");
				for(int k = 0;k<convocadosCtrl.size();k++){
					Convocado convocadoIterator = convocadosCtrl.get(k);
					
					Element nombreConvocado = XML.createElement("nombreConvocado");
					nombreConvocado.setTextContent(convocadoIterator.getNombre());		
					convocadosCtrlRaiz.appendChild(nombreConvocado);
					
					//Padre,"tag",String contenido
					addElementToParentWithContent(convocadosCtrlRaiz,"posX",String.valueOf(convocadoIterator.getPosX()),XML);
					
				}
				controladorLiga.appendChild(convocadosCtrlRaiz);
				
				//Reservas Ctrl
				Vector<Convocado> reservasCtrl = CtrlLiga.getInstancia().getReservas();
				Element reservasCtrlRaiz = XML.createElement("reservasCtrl");
				for(int k=0;k<reservasCtrl.size();k++){
					Convocado reservaIterator = reservasCtrl.get(k);
					addElementToParentWithContent(reservasCtrlRaiz, "nombreReserva", reservaIterator.getNombre(), XML);
				}
				controladorLiga.appendChild(reservasCtrlRaiz);
				
				//Vector IDT
				Vector<Integer> vectorIDT = CtrlLiga.getInstancia().getIdt();
				Element idtCtrlRaiz = XML.createElement("idtCtrl");
				for(int k=0;k<vectorIDT.size();k++){
					Integer integerIterator = vectorIDT.get(k);
					addElementToParentWithContent(idtCtrlRaiz, "idt", String.valueOf(integerIterator), XML);
				}
				controladorLiga.appendChild(idtCtrlRaiz);
				
			raiz.appendChild(controladorLiga);
			
			//Controlador Presentacion
			Element controladorPresentacion = XML.createElement("ctrPresentacion");
				
				addElementToParentWithContent(controladorPresentacion, "estadoAlineacion", String.valueOf(CtrlPresentacion.getInstancia().getEstatAlineacio()),XML);
				addElementToParentWithContent(controladorPresentacion, "equipoUs", String.valueOf(CtrlPresentacion.getInstancia().getEquiposUs()), XML);
				addElementToParentWithContent(controladorPresentacion, "turno", String.valueOf(CtrlPresentacion.getInstancia().getTurno()), XML);
				addElementToParentWithContent(controladorPresentacion, "equipoPersonalizado", CtrlPresentacion.getInstancia().getEquipoPersonalizado(), XML);
				addElementToParentWithContent(controladorPresentacion, "jornadaCtrlPresentacion", String.valueOf(CtrlPresentacion.getInstancia().getJornadas()), XML);
			raiz.appendChild(controladorPresentacion);
			
		return XML;
	}
	
	private void printarPorPantalla(Document xml){
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



	private void crearEntornoLiga(Document XML){
		Vector<EquipoFutbol> vectorEquiposFutbol = new Vector<EquipoFutbol>();
		Vector<Partido> partidosIda = new Vector<Partido>();
		Vector<Partido> partidosVuelta = new Vector<Partido>();

		Element liga = XML.getDocumentElement();
				
		//Nombre Liga
		NodeList nombreLiga = liga.getElementsByTagName("nombre");
		String nombreLigaString = ((Element)nombreLiga.item(0)).getTextContent();
		
		//Temporada Liga
		Integer temporadaLigaInteger = Integer.valueOf(getContentFromElement(liga, "temporada"));
		
		//Jornada actual
		NodeList jornada = liga.getElementsByTagName("jornada");
		Integer jornadaInteger = Integer.valueOf(((Element)jornada.item(0)).getAttribute("actual"));
		
		//Partido actual
		NodeList partido = liga.getElementsByTagName("partido");
		Integer partidoInteger = Integer.valueOf(((Element)partido.item(0)).getAttribute("actual"));
		
		//Dificultad Liga
		Integer dificultadInteger = Integer.getInteger(getContentFromElement(liga,"dificultad"));

		//Usuario Liga
		Integer usuarioInteger = Integer.valueOf(getContentFromElement(liga, "usuarioLiga"));
		
		//Crear la liga
		Liga ligaRecuperada = new Liga(temporadaLigaInteger,nombreLigaString);
		ligaRecuperada.setJornadaActual(jornadaInteger);
		ligaRecuperada.setPartidoActual(partidoInteger);
		ligaRecuperada.setDificultad(dificultadInteger);
		ligaRecuperada.setTemporada(temporadaLigaInteger);
		ligaRecuperada.setUsuario(usuarioInteger);
		
		//Equipos
		NodeList equipos = liga.getElementsByTagName("equipo");
		
		for(int i = 0; i < equipos.getLength() ; i++){
			Node equipo = equipos.item(i);
			
			/*BEGIN Datos basicos del equipo equipo*/
			Element equipoElement = (Element) equipo;
			String nombreEquipoString = getContentFromElement(equipoElement, "nombre");
			
			String campoEquipoString = getContentFromElement(equipoElement, "campo");
			
			String ciudadEquipoString = getContentFromElement(equipoElement, "ciudad");
			
			Integer oroEquipoInteger = Integer.valueOf(getContentFromElement(equipoElement, "oro"));
			
			Boolean usuarioEquipoBoolean = Boolean.valueOf(getContentFromElement(equipoElement, "usuario"));
						
			Integer dificultadEquipoInteger =  Integer.valueOf(getContentFromElement(equipoElement, "dificultadEquipo"));
			
			Boolean fantasmaEquipo = Boolean.valueOf(getContentFromElement(equipoElement, "fantasma"));
			
			EquipoFutbol equipoFutbolRecuperado = new EquipoFutbol(true);
			equipoFutbolRecuperado.setNombre(nombreEquipoString);
			equipoFutbolRecuperado.setCampo(campoEquipoString);
			equipoFutbolRecuperado.setCiudad(ciudadEquipoString);
			equipoFutbolRecuperado.setDificultad(dificultadEquipoInteger);
			equipoFutbolRecuperado.setOro(oroEquipoInteger);
			equipoFutbolRecuperado.setUsuario(usuarioEquipoBoolean);
			equipoFutbolRecuperado.setFantasma(fantasmaEquipo);
			
			//Añado el equipo a la liga
			ligaRecuperada.addEquipo(equipoFutbolRecuperado, usuarioEquipoBoolean);
			/*END Datos basicos del equipo equipo*/
					
			
			/*BEGIN Entrenador*/
			NodeList entrenadorEquipo = equipoElement.getElementsByTagName("entrenador");
			Element entrenadorEquipoElement = (Element) entrenadorEquipo.item(0);
			
			//Entrenador Nombre
			String entrenadorNombreString = getContentFromElement(entrenadorEquipoElement, "entrenadorNombre");
			
			//Entrenador FechaN
			String entrenadorFechaNString = getContentFromElement(entrenadorEquipoElement, "entrenadorFechaN");
			
			//Entrenador rebaja
			Integer entrenadorRebajaInteger = Integer.valueOf(getContentFromElement(entrenadorEquipoElement, "entrenadorRebaja"));
			
			//Entrenador incrementador
			Integer entrenadorIncrementadorInteger = Integer.valueOf(getContentFromElement(entrenadorEquipoElement, "entrenadorIncrementador"));
			
			//Entrenador estrategia
			Integer entrenadorEstrategiaInt = Integer.valueOf(getContentFromElement(entrenadorEquipoElement, "entrenadorEstrategia"));
			
			Entrenador entrenadorObjeto = new Entrenador(entrenadorEstrategiaInt, entrenadorIncrementadorInteger, entrenadorRebajaInteger);
			entrenadorObjeto.setFechaN(entrenadorFechaNString);
			entrenadorObjeto.setNombre(entrenadorNombreString);
			
			//equipoFutbolRecuperado.setEntrenador(entrenadorObjeto);
			ligaRecuperada.addEntrenadorEquipo(entrenadorObjeto, equipoFutbolRecuperado);
			/*END Entrenador*/
			
			/*BEGIN Fisio*/
			NodeList fisioterapeutaEquipo = equipoElement.getElementsByTagName("fisioterapeuta");
			Element fisioterapeutaEquipoElement = (Element) fisioterapeutaEquipo.item(0);
			
			//fisioterapeuta Nombre
			String fisioterapeutaNombreString = getContentFromElement(fisioterapeutaEquipoElement, "fisioterapeutaNombre");
			
			//fisioterapeuta FechaN
			String fisioterapeutaFechaNString = getContentFromElement(fisioterapeutaEquipoElement, "fisioterapeutaFechaN");
			
			//fisioterapeutaValor
			Integer fisioterapeutaValorInteger = Integer.valueOf(getContentFromElement(fisioterapeutaEquipoElement, "fisioterapeutaValor"));
			
			//fisioterapeutaRecuperacion
			Integer fisioterapeutaRecuperacionInteger = Integer.valueOf(getContentFromElement(fisioterapeutaEquipoElement, "fisioterapeutaRecuperacion"));
			
			Fisioterapeuta fisioterapeutaObjeto = new Fisioterapeuta(fisioterapeutaRecuperacionInteger, fisioterapeutaValorInteger);
			fisioterapeutaObjeto.setFechaN(fisioterapeutaFechaNString);
			fisioterapeutaObjeto.setNombre(fisioterapeutaNombreString);
			
			//equipoFutbolRecuperado.setFisio(fisioterapeutaObjeto);
			ligaRecuperada.addFisioEquipo(fisioterapeutaObjeto, equipoFutbolRecuperado);
			/*END Fisio*/

			NodeList jugadores = equipoElement.getElementsByTagName("futbolista");
			for(int j=0; j < jugadores.getLength() ; j ++){
				Node jugador = jugadores.item(j);
				
				Element jugadorElement = (Element) jugador;
				
				String nombreJugadorString = getContentFromElement(jugadorElement, "nombre");
				
				//Agresividad
				Integer agresividadInteger = Integer.valueOf(getContentFromElement(jugadorElement, "agresividad"));
				
				//altura
				Float alturaFloat = Float.valueOf(getContentFromElement(jugadorElement, "altura"));
				
				//peso
				Integer pesoInteger = Integer.valueOf(getContentFromElement(jugadorElement, "peso"));
				
				//dorsal
				Integer dorsalInteger = Integer.valueOf(getContentFromElement(jugadorElement, "dorsal"));
				
				//posicion
				Integer posicionInteger = Integer.valueOf(getContentFromElement(jugadorElement, "posicion"));
				
				
				//experiencia
				Integer experienciaInteger = Integer.valueOf(getContentFromElement(jugadorElement, "experiencia"));
				
				//fechaN
				String fechaNString = getContentFromElement(jugadorElement, "fechaN");
				
				//golesMarcados
				Integer golesMarcadosJugadorInteger = Integer.valueOf(getContentFromElement(jugadorElement, "golesMarcados"));
				
				//golesRecibidos
				Integer golesRecibidosJugadorInteger = Integer.valueOf(getContentFromElement(jugadorElement, "golesRecibidos"));
				
				//gravedad
				Integer gravedadInteger = Integer.valueOf(getContentFromElement(jugadorElement, "gravedad"));
				
				//media
				
				//parada
				Integer paradaInteger = Integer.valueOf(getContentFromElement(jugadorElement, "parada"));
				
				//pase
				Integer paseInteger = Integer.valueOf(getContentFromElement(jugadorElement, "pase"));
								
				//regate
				Integer regateInteger = Integer.valueOf(getContentFromElement(jugadorElement, "regate"));
				
				//remate
				Integer remateInteger = Integer.valueOf(getContentFromElement(jugadorElement, "remate"));
				
				//resistencia
				Integer resistenciaInteger = Integer.valueOf(getContentFromElement(jugadorElement, "resistencia"));
				
				//sancionado
				Integer sancionadoInteger = Integer.valueOf(getContentFromElement(jugadorElement, "sancionado"));
				
				//velocidad
				Integer velocidadInteger = Integer.valueOf(getContentFromElement(jugadorElement, "velocidad"));
				
				//Estadisticas Jugador
				NodeList estadisticasJugador = jugadorElement.getElementsByTagName("estadisticasJugador");
				Element estadisticasJugadorElement = (Element) estadisticasJugador.item(0);
					
					//faltasRealizadas
					Integer faltasRealizadasInteger = Integer.valueOf(getContentFromElement(estadisticasJugadorElement, "faltasRealizadas"));
					
					//pasesRealizados
					Integer pasesRealizadosInteger = Integer.valueOf(getContentFromElement(estadisticasJugadorElement, "pasesRealizados"));
							
					//pasesBuenos
					Integer pasesBuenosInteger = Integer.valueOf(getContentFromElement(estadisticasJugadorElement, "pasesBuenos"));
					
					//faltasRecibidas
					Integer faltasRecibidasInteger = Integer.valueOf(getContentFromElement(estadisticasJugadorElement,"faltasRecibidas"));
					
					//partidosJugados
					Integer partidosJugadosInteger = Integer.valueOf(getContentFromElement(estadisticasJugadorElement, "partidosJugados"));
					
					//tarjetasAmarillas
					Integer tarjetasAmarillasInteger = Integer.valueOf(getContentFromElement(estadisticasJugadorElement, "targetasAmarillas"));
					
					//tarjetasRojas
					Integer targetasRojasInteger = Integer.valueOf(getContentFromElement(estadisticasJugadorElement, "targetasRojas"));
							
					EstadisticasJugador estadisticaJugadorObjeto = new EstadisticasJugador();
					estadisticaJugadorObjeto.setFaltasRealizadas(faltasRealizadasInteger);
					estadisticaJugadorObjeto.setFaltasRecibidas(faltasRecibidasInteger);
					estadisticaJugadorObjeto.setGolesMarcados(golesMarcadosJugadorInteger);
					estadisticaJugadorObjeto.setGolesRecibidos(golesRecibidosJugadorInteger);
					estadisticaJugadorObjeto.setPartidosJugados(partidosJugadosInteger);
					estadisticaJugadorObjeto.setPasesBuenos(pasesBuenosInteger);
					estadisticaJugadorObjeto.setPasesRealizados(pasesRealizadosInteger);
					estadisticaJugadorObjeto.setTarjetasAmarillas(tarjetasAmarillasInteger);
					estadisticaJugadorObjeto.setTarjetasRojas(targetasRojasInteger);
					
					
					
				Futbolista futbolistaIterator = new Futbolista(equipoFutbolRecuperado, posicionInteger, dorsalInteger);
		
				futbolistaIterator.setAltura(alturaFloat);
				futbolistaIterator.setDorsal(dorsalInteger);
				futbolistaIterator.setEquipo(equipoFutbolRecuperado);
				futbolistaIterator.setExperiencia(experienciaInteger);
				futbolistaIterator.setFechaN(fechaNString);
				futbolistaIterator.setGravedad(gravedadInteger);
				futbolistaIterator.setNombre(nombreJugadorString);
				futbolistaIterator.setPeso(pesoInteger);
				futbolistaIterator.setRemate(remateInteger);
				futbolistaIterator.setVelocidad(velocidadInteger);
				futbolistaIterator.setResistencia(resistenciaInteger);
				futbolistaIterator.setRegate(regateInteger);
				futbolistaIterator.setPase(paseInteger);
				futbolistaIterator.setAgresividad(agresividadInteger);
				futbolistaIterator.serParada(paradaInteger);
				futbolistaIterator.setSancionado(sancionadoInteger);			
				
				ligaRecuperada.addFutbolistaEquipo(futbolistaIterator, estadisticaJugadorObjeto,equipoFutbolRecuperado);
			}
			NodeList estadisticasEquipo = equipoElement.getElementsByTagName("estadisticasEquipo");
			Element estadisticasEquipoElement = (Element)(estadisticasEquipo.item(0));
					
				//golesRecibidos
				Integer golesRecibidosInteger = Integer.valueOf(getContentFromElement(estadisticasEquipoElement, "golesRecibidos"));
				
				//golesMarcados
				Integer golesMarcadosInteger = Integer.valueOf(getContentFromElement(estadisticasEquipoElement, "golesMarcados"));
						
				//tarjetasAmarillas
				Integer tarjetasAmarillasInteger = Integer.valueOf(getContentFromElement(estadisticasEquipoElement, "tarjetasAmarillas"));
						
				//tarjetasRojas
				Integer tarjetasRojasInteger = Integer.valueOf(getContentFromElement(estadisticasEquipoElement, "tarjetasRojas"));
						
				//puntos
				Integer puntosInteger = Integer.valueOf(getContentFromElement(estadisticasEquipoElement, "puntos"));
				
				//ganadosConsec
				Integer ganadosConsecInteger = Integer.valueOf(getContentFromElement(estadisticasEquipoElement,"ganadosConsec"));
				
				//perdidosConsec
				Integer perdidosConsecInteger = Integer.valueOf(getContentFromElement(estadisticasEquipoElement,"perdidosConsec"));
						
				//partidosGanados
				Integer partidosGanadosInteger = Integer.valueOf(getContentFromElement(estadisticasEquipoElement, "partidosGanados"));
				
				//partidosEmpatados
				Integer partidosEmpatadosInteger = Integer.valueOf(getContentFromElement(estadisticasEquipoElement,"partidosEmpatados"));
				
				//partidosPerdidos
				Integer partidosPerdidosInteger = Integer.valueOf(getContentFromElement(estadisticasEquipoElement,"partidosPerdidos"));
						
				EstadisticasEquipo estadisticaEquipoObjeto = new EstadisticasEquipo();
				estadisticaEquipoObjeto.setGanadosConsec(ganadosConsecInteger);
				estadisticaEquipoObjeto.setGolesMarcados(golesMarcadosInteger);
				estadisticaEquipoObjeto.setGolesRecibidos(golesRecibidosInteger);
				estadisticaEquipoObjeto.setPartidosEmpatados(partidosEmpatadosInteger);
				estadisticaEquipoObjeto.setPartidosGanados(partidosGanadosInteger);
				estadisticaEquipoObjeto.setPartidosPerdidos(partidosPerdidosInteger);
				estadisticaEquipoObjeto.setPerdidosConsec(perdidosConsecInteger);
				estadisticaEquipoObjeto.setPuntos(puntosInteger);
				estadisticaEquipoObjeto.setTarjetasAmarillas(tarjetasAmarillasInteger);
				estadisticaEquipoObjeto.setTarjetasRojas(tarjetasRojasInteger);
								
				ligaRecuperada.addEquipoEstadistica(estadisticaEquipoObjeto, equipoFutbolRecuperado);
	
				vectorEquiposFutbol.add(equipoFutbolRecuperado);
		}
		
		/*BEGIN PARTIDOSIDA*/
		NodeList partidosIdaLista = liga.getElementsByTagName("partidosIda");
		Element partidosIdaElement = (Element) partidosIdaLista.item(0);
		
		NodeList partidoIda = partidosIdaElement.getElementsByTagName("partidoIda");
		for(int i=0;i<partidoIda.getLength();i++){
			Element partidoIterator = (Element) partidoIda.item(i);
			
			String equipoLocalString =  getContentFromElement(partidoIterator, "equipoLocal"); 
				
			String equipoVisitanteString =  getContentFromElement(partidoIterator, "equipoVisitante");
			
			Integer cambiosLocalInteger = Integer.valueOf(getContentFromElement(partidoIterator, "cambiosLocal")); 
			
			Integer cambiosVisitanteInteger = Integer.valueOf(getContentFromElement(partidoIterator, "cambiosVisitante"));
			
			Integer golesLocalInteger = Integer.valueOf(getContentFromElement(partidoIterator, "golesLocal"));
			
			Integer golesVisitanteInteger = Integer.valueOf(getContentFromElement(partidoIterator, "golesVisitante"));
			
			Integer jornadaPartidoInteger = Integer.valueOf(getContentFromElement(partidoIterator, "jornada"));
			
			boolean jugadoBoolean = Boolean.parseBoolean(getContentFromElement(partidoIterator, "jugado"));
			
			//boolean finalPartidoBoolean = Boolean.parseBoolean(getContentFromElement(partidoIterator, "finalPartido"));
			
			Double tiempoJugadoDouble = Double.valueOf(getContentFromElement(partidoIterator, "tiempoJugado"));
			
			Double tiempoMostradoDouble = Double.valueOf(getContentFromElement(partidoIterator, "tiempoMostrado"));
			
			Double tiempoTotalDouble = Double.valueOf(getContentFromElement(partidoIterator, "tiempoTotal"));
			
			EquipoFutbol local = searchEquipoVector(vectorEquiposFutbol,equipoLocalString);
			EquipoFutbol visitante = searchEquipoVector(vectorEquiposFutbol,equipoVisitanteString);
			Partido partidoIdaObject = new Partido(local, visitante	, jornadaPartidoInteger);

			partidoIdaObject.setCambiosLocal(cambiosLocalInteger);
			partidoIdaObject.setCambiosVisitante(cambiosVisitanteInteger);
			partidoIdaObject.setGolesLocal(golesLocalInteger);
			partidoIdaObject.setGolesVisitante(golesVisitanteInteger);
			partidoIdaObject.setJugado(jugadoBoolean);
			partidoIdaObject.setTiempoJugado(tiempoJugadoDouble);
			partidoIdaObject.setTiempoMostrado(tiempoMostradoDouble);
			partidoIdaObject.setTiempoTotal(tiempoTotalDouble);
			partidosIda.add(partidoIdaObject);
		}
		/*END PARTIDOSIDA*/

		/*BEGIN PARTIDOSVUELTA*/
		NodeList partidosVueltaLista = liga.getElementsByTagName("partidosVuelta");
		Element partidosVueltaElement = (Element) partidosVueltaLista.item(0);
		
		NodeList partidoVuelta = partidosVueltaElement.getElementsByTagName("partidoVuelta");
		for(int i=0;i<partidoVuelta.getLength();i++){
			Element partidoIterator = (Element) partidoVuelta.item(i);
			
			String equipoLocalString =  getContentFromElement(partidoIterator, "equipoLocal"); 
				
			String equipoVisitanteString =  getContentFromElement(partidoIterator, "equipoVisitante");
			
			Integer cambiosLocalInteger = Integer.valueOf(getContentFromElement(partidoIterator, "cambiosLocal")); 
			
			Integer cambiosVisitanteInteger = Integer.valueOf(getContentFromElement(partidoIterator, "cambiosVisitante"));
			
			Integer golesLocalInteger = Integer.valueOf(getContentFromElement(partidoIterator, "golesLocal"));
			
			Integer golesVisitanteInteger = Integer.valueOf(getContentFromElement(partidoIterator, "golesVisitante"));
			
			Integer jornadaPartidoInteger = Integer.valueOf(getContentFromElement(partidoIterator, "jornada"));
			
			boolean jugadoBoolean = Boolean.parseBoolean(getContentFromElement(partidoIterator, "jugado"));
			
			//boolean finalPartidoBoolean = Boolean.parseBoolean(getContentFromElement(partidoIterator, "finalPartido"));
			
			Double tiempoJugadoDouble = Double.valueOf(getContentFromElement(partidoIterator, "tiempoJugado"));
			
			Double tiempoMostradoDouble = Double.valueOf(getContentFromElement(partidoIterator, "tiempoMostrado"));
			
			Double tiempoTotalDouble = Double.valueOf(getContentFromElement(partidoIterator, "tiempoTotal"));
			
			Partido partidoVueltaObject = new Partido(searchEquipoVector(vectorEquiposFutbol,equipoLocalString),
													searchEquipoVector(vectorEquiposFutbol,equipoVisitanteString), jornadaPartidoInteger);

			partidoVueltaObject.setCambiosLocal(cambiosLocalInteger);
			partidoVueltaObject.setCambiosVisitante(cambiosVisitanteInteger);
			partidoVueltaObject.setGolesLocal(golesLocalInteger);
			partidoVueltaObject.setGolesVisitante(golesVisitanteInteger);
			partidoVueltaObject.setJugado(jugadoBoolean);
			partidoVueltaObject.setTiempoJugado(tiempoJugadoDouble);
			partidoVueltaObject.setTiempoMostrado(tiempoMostradoDouble);
			partidoVueltaObject.setTiempoTotal(tiempoTotalDouble);
			
			partidosVuelta.add(partidoVueltaObject);
		}
		/*END PARTIDOSVUELTA*/
		
		ligaRecuperada.setPartidos(partidosIda, partidosVuelta);
		
		/*BEGIN CALENDARIO*/
		NodeList jornadasIdaRaiz = liga.getElementsByTagName("jornadasIda");
		Element jornadasIdaRaizElement = (Element) jornadasIdaRaiz.item(0);
		int sizeVector = Integer.valueOf(getContentFromElement(jornadasIdaRaizElement, "sizeMatrixIda"));
		String[][] calendarioRecuperadoJornadasIda = new String[sizeVector][2];
		NodeList jornadasIda = jornadasIdaRaizElement.getElementsByTagName("jornadaIda");
			for(int i=0;i<jornadasIda.getLength();i++){
				Element jornadaIdaIterator = (Element) jornadasIda.item(i);
				NodeList partidosJornadaIda = jornadaIdaIterator.getElementsByTagName("partidoJornadaIda");
				for(int j=0;j<partidosJornadaIda.getLength();j++){
					Element partidoIdaCalendario = (Element) partidosJornadaIda.item(j);
					calendarioRecuperadoJornadasIda[i][j] = partidoIdaCalendario.getTextContent();
				}
			}
		
		ligaRecuperada.setJornadasIda(calendarioRecuperadoJornadasIda);
		
		NodeList jornadasVueltaRaiz = liga.getElementsByTagName("jornadasVuelta");
		Element jornadasVueltaRaizElement = (Element) jornadasVueltaRaiz.item(0);
		int sizeVector2 = Integer.valueOf(getContentFromElement(jornadasVueltaRaizElement, "sizeMatrixVuelta"));
		String[][] calendarioRecuperadoJornadasVuelta = new String[sizeVector2][2];
		NodeList jornadasVuelta = jornadasVueltaRaizElement.getElementsByTagName("jornadaVuelta");
			for(int i=0;i<jornadasVuelta.getLength();i++){
				Element jornadaVueltaIterator = (Element) jornadasVuelta.item(i);
				NodeList partidosJornadaVuelta = jornadaVueltaIterator.getElementsByTagName("partidoJornadaVuelta");
				for(int j=0;j<partidosJornadaVuelta.getLength();j++){
					Element partidoVueltaCalendario = (Element) partidosJornadaVuelta.item(j);
					calendarioRecuperadoJornadasVuelta[i][j] = partidoVueltaCalendario.getTextContent();
				}
			}
		
		ligaRecuperada.setJornadasVuelta(calendarioRecuperadoJornadasVuelta);
		/*END CALENDARIO*/
		/*BEGIN CONTROLADORES*/
		
		/*BEGIN CTRLPRESENTACION*/
		NodeList ctrlPresentacion = liga.getElementsByTagName("ctrPresentacion");
		Element ctrlPresentacionElement = (Element)ctrlPresentacion.item(0);
		
		Integer equipoUsInteger = Integer.valueOf(getContentFromElement(ctrlPresentacionElement, "equipoUs"));
		
		Integer turnoInteger =Integer.valueOf( getContentFromElement(ctrlPresentacionElement,"turno"));
		
		String equipoPersonalizadoString = getContentFromElement(ctrlPresentacionElement,"equipoPersonalizado");
		
		Integer jornadaPresentacionInteger = Integer.valueOf( getContentFromElement(ctrlPresentacionElement,"jornadaCtrlPresentacion"));
		
		Integer estadoAlineacionInteger = Integer.valueOf(getContentFromElement(ctrlPresentacionElement,"estadoAlineacion"));
		CtrlPresentacion.getInstancia();
		CtrlPresentacion.getInstancia().setEquiposUs(equipoUsInteger);
		CtrlPresentacion.getInstancia().setTurno(1);
		CtrlPresentacion.getInstancia().setEquipoPersonalizado(equipoPersonalizadoString);
		CtrlPresentacion.getInstancia().setJornadas(jornadaPresentacionInteger);
		CtrlPresentacion.getInstancia().setEstatAlineacio(estadoAlineacionInteger);
		
		/*END CTRLPRESENTACION*/

		
		/*BEGIN CTRNUEVALIGA.No es singleton. No he podido guardar nada del controlador*/
		//NodeList ctrlNuevaLiga = liga.getElementsByTagName("ctrlNuevaLiga");
		CtrlNuevaLiga.getInstancia();
		CtrlNuevaLiga.getInstancia().setLiga(ligaRecuperada);
		CtrlPresentacion.getInstancia().setCtrlNuevaLiga(CtrlNuevaLiga.getInstancia());
		/*END CTRLNUEVALIGA*/
		
		/*END CONTROLADORES*/
		
		/*BEGIN CTRLLIGA*/
		NodeList ctrlLiga = liga.getElementsByTagName("ctrlLiga");
		Element ctrlLigaElement = (Element)ctrlLiga.item(0);
		
			String equipoFutbolID = getContentFromElement(ctrlLigaElement, "equipoCtrl");
			//CtrlLiga cl = 
			EquipoFutbol aux =  searchEquipoVector(vectorEquiposFutbol,equipoFutbolID);
			CtrlPresentacion cp = CtrlPresentacion.getInstancia();
			cp.setCtrlLiga(CtrlLiga.getInstancia());
			cp.getCtrlLiga().setEquipo(aux);
			CtrlPresentacion.getInstancia().setEquipo();
			
			NodeList idtRaiz = ctrlLigaElement.getElementsByTagName("idtCtrl");
			Element idtRaizElement = (Element)idtRaiz.item(0);
			
			Vector<Integer> idtVector = new Vector<Integer>();
			NodeList idtList = idtRaizElement.getElementsByTagName("idt");
			for(int i=0;i<idtList.getLength();i++){
				Element idtIntegerElement = (Element)idtList.item(i);
				idtVector.add(Integer.valueOf(idtIntegerElement.getTextContent()));
			}
			CtrlLiga.getInstancia().setIdt(idtVector);

		/*END CTRLLIGA*/

	}
	private String getContentFromElement(Element container, String Tag){
		NodeList nl = container.getElementsByTagName(Tag);
		return ((Element)nl.item(0)).getTextContent();
	}
	
	private EquipoFutbol searchEquipoVector(Vector<EquipoFutbol> vectorEquiposFutbol,String equipoString){
		for(int i = 0; i < vectorEquiposFutbol.size() ; i++){
			EquipoFutbol iterator = vectorEquiposFutbol.get(i);
			if(iterator.getNombre() == equipoString ) return iterator;
		}
		return null;
	}
	
	private void addElementToParentWithContent(Element padre,String tag, String content, Document XML){
		Element aux = XML.createElement(tag);
		aux.setTextContent(content);		
		padre.appendChild(aux);
			
	}
	
	private void addElementToParentWithAtribute(Element raiz,String tag, String atributo, String valor, Document xml){
		Element element = xml.createElement(tag);
		element.setAttribute(atributo, valor);
		raiz.appendChild(element);
			
	}
	
	
}

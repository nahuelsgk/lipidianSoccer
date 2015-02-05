/*
 * Alberto Moreno Vega
 * 
 */


package domain;

import java.util.Random;

public abstract class Persona {

	protected String nombre;

	protected String fechaN;

    

    public final static String[] NOMBRES_MUJER = {"Adda","Adala","Adela","Addela","Adhela","Athela","Adalberta","Adalind","Adalindis","Adalmut",
    	"Adalrada","Adaltrutis","Adeltrudis","Adaluuidis","Adalwif","Adelaidis","Adwala","Aebbe","Agentrudis","Agglethrudis","Albelenda","Alburg",
    	"Alburch","Albruga","Aldguda","Aldgudana","Aldruth","Alfgarda","Alfild","Alflent","Amalberga","Amalbirga","Amulberga","Amoltrud",
    	"Anselda","Anstruda","Avacyn","Auacyn","Avekin","Auekin","Avin","Auin","Auina","Balthildis","Baltelda","Balthechildis","Bava","Bavacin",
    	"Bavin","Bauin","Belegardis","Berehta","Bergard","Bergundis","Beriungis","Berna","Bernewif","Bernewief","Berta","Berhta","Bertaida",
    	"Berthlenda","Bertildis","Berthildis","Bertliana","Bertrada","Bertruda","Bertswinda","Bettin","Blitekin","Boltiarda","Bova","Boviardis",
    	"Childebertana","Clodauuiua","Clotrada","Crapahildis","Cunegundis","Conegundis","Conegont","Conegunt","Dadin","Dagarada","Danburga",
    	"Doda","Dodda","Duda","Ebertana","Edeberga","Edeborg","Egecin","Egesburga","Egesloga","Ehgelhild","Ehgeluuara","Emecin","Engelgard",
    	"Engelsuit","Engelwara","Engeluuara","Ingelwara","Ingeluuara","Enna","Eremburgis","Herenborg","Ereprad","Erkembrog","Erkenbrog",
    	"Erchembrog","Erkenburoc","Erkenrad","Ermecin","Ermengarda","Ermengardis","Ermegardis","Hermengarda","Irmengard","Ermeswindis","Ermina",
    	"Erpsuid","Errictruda","Ethelchif","Ethelgard","Ethelgarda","Euerloga","Everelda","Evereldis","Pharahildis","Folclind","Folclinda",
    	"Folcrada","Folcuuara","Folgarda","Folsuindis","Folsuuendis","Fordola","Fortlifh","Frauuara","Fredeburgis","Frederada","Fredeuuara",
    	"Frethegard","Frethesuindis","Frethesuinda","Fridesuenda","Fridewiga","Frisburgis","Frithelinda","Geila","Gelduuara","Gerberga",
    	"Geretrudis","Gertruda","Gerlinda","Gerlent","Gersenda","Gersuenda","Gersuinda","Geruuara","Geva","Geua","Glismodis","Gisla","Godalinda",
    	"Godelinda","Godeca","Godecin","Godildis","Godelda","Guodhelda","Goduuara","Gothuuera","Grimuuara","Gundrada","Guodlia","Hadaken",
    	"Harwara","Hazeca","Heilewif","Heilswinda","Heleuuidis","Helinda","Heltrada","Heletradana","Hengelsenda","Herdin","Herden","Herenfrida",
    	"Herlinda","Hildberta","Hildborg","Hildeburg","Heldeburga","Hildeberga","Hildegard","Hildegardis","Hildcardis","Hildelana","Hildemunda",
    	"Hildeswindis","Hildewif","Hildeuuif","Hildrada","Hildwara","Hildewara","Hildeuuara","Hiltrude","Hostaruuara","Idasgarda","Ideslef",
    	"Ideswif","Idesuuif","Idisiardis","Imma","Imicina","Ingela","Ingelswindis","Iodberta","Irmenhild","Irmenlind","Hirmenlind","Isburch",
    	"Landburuga","Landgarda","Landrada","Lanthildis","Lantuuara","Lebdrudis","Leddinga","Leuekin","Leuuich","Liaueld","Lidiardis","Liefhun",
    	"Lieftet","Lietgarda","Lietgardis","Litgardis","Litiardis","Lietuuif","Lieuuara","Lifgarda","Liodburga","Litburh","Liodgard","Liodrada",
    	"Liedrada","Madelrada","Madhalberta","Magtildis","Magthildis","Mathildis","Machtildis","Markuuara","Megenberta","Megendrod","Megenhelda",
    	"Meinnelda","Megenlind","Megenlioba","Megensind","Megensinda","Meinsent","Megenuuara","Meinburg","Menborch","Meinswindis","Methdin",
    	"Nidlebis","Nordrada","Odburga","Odela","Odgiva","Otgiva","Odguda","Odgudana","Odlenda","Olburgis","Olga","Osgarda","Osgiua","Otgiua",
    	"Otberta","Oydela","Radburgis","Radborg","Radburg","Radeken","Radgert","Radlia","Radsuinda","Ramburga","Regana","Regenburuga",
    	"Renburgis","Regenelda","Rainilda","Rainildis","Regenlind","Regenset","Reginsuint","Reinsuent","Regneuuig","Reinewif","Rennewief",
    	"Reingard","Reingardis","Reingart","Reingaud","Reingod","Riberta","Richildis","Richelda","Rikildis","Riclindis","Ricsuinda","Rinilda",
    	"Rinelt","Rodburga","Rotburga","Rodgarda","Rodgardae","Hruodgarda","Rofsind","Rothin","Rotlenda","Hruotberta","Sigarda","Syardis",
    	"Sigberta","Sigeberta","Sigeburgis","Sigiburgis","Siborch","Siburg","Seburg","Seburga","Sigethrod","Snelburch","Stenburch","Stilleuuara",
    	"Strilleburg","Suitburgis","Thancuuara","Thedela","Thidela","Thieda","Thietgarda","Thietwara","Theaduuara","Thiutuuara","Thietuuich",
    	"Thiodsind","Teudsindis","Thiodsuinda","Thrasborg","Thrudberga","Trhutborgana","Ticekin","Tietlenda","Tietza","Trudlinde","Trutilda",
    	"Uoldolberta","Vrowecin","Frouuin","Frouuina","Vualdberta","Vualdedruda","Vualdetruda","UUaldethruda","Vuifken","Vuiuechin","Vuinetberta",
    	"UUaldburg","Wavin","UUeremund","UUerenburoc","UUiburgis","Wiburgis","Wihted","Wilberga","Wilgeva","Willelda","Willesuindis",
    	"UUindborog","UUinebarga","UUireda","Wivecin","Wivin","Wlbgis","Wlfildis","UUlgarda","Wlgert"}; //hay 393
    public final static String[] NOMBRES_HOMBRE = {"Abelard","Absolon","Achard","Achart","Adalbero",
		"Agu","Aignen","Agnien","Alaire","Alarge","Alazas","Albin","Albinus","Alcher","Aldebrand",
		"Aldemund","Alderan","Aleaumin","Aliaume","Alyaume","Alerot","Alodet","Aloysius","Alured",
		"Amadeus","Amalric","Amalvis","Amanieu","Amigart","Araimfres","Arbert","Arnulf","Arsieu",
		"Arvide","Augebert","Avenel","Baderon","Baldric","Bangin","Bardin","Barnier","Baut","Biche",
		"Billebaut","Bochard","Bon-Ami","Bouchard","Burnel","Buselin","Centule","Chartain","Chatbert",
		"Claudien","Colbert","Coumyn","Cyon","Cyprian","Daimbert","Dalmas","Daluce","Damian","Darius",
		"Delion","Digne","Doete","Dolfin","Duche","Dudic","Alazar","Emambe","Emont","Engelard","Engenouf",
		"Enion","Erengier","Ertaut","Esdelot","Espan","Estène","Estout","Fabien","Famte","Fameite","Fangeaux",
		"Fiebras","Flambard","Floouen","Fluellen","Fortin","Fourcaut","Foursi","Fray","Fremin","Freskin",
		"Ganelon","Garsille","Gascot","Gaubert","Gauchier","Gaude","Gauguein","Gembert","Gentian,","Gentien",
		"Gerland","Gildon","Giriaume","Gobin","Gobelin","Godebert","Godichal","Golias","Gonfroi","Gontier",
		"Goumelet","Gourdet","Gracien","Gracyen","Granville","Grefin","Grenville","Grifon","Gringoire"
		,"Guermont","Guigue","Guineboud","Habreham","Haguin","Haiete","Halebran","Halinard","Haquin",
		"Harchier","Hascouf","Hecelin","Henseus","Herbrand","Herchier","Herculles","Hernouet","Hesdin",
		"Hetouyn","Heude","Hique","Hocequin","Honora","Honot","Huard","Huart","Huebald","Huidelon","Huitace",
		"Humbert","Hunout","Huoul","Huroin","Ianto","Ilbert","Isore","Japhet","Jeharraz","Jehaue","Jessamy",
		"Jevan","Jolis","Jollivet","Jonas","Jonathas","Jorin","Jude","Jumel","Juste","Kerrich","Lagot","Leal",
		"Leavold","Leigh","Leofard","Letard","Lie","Lice","Maillart","Maillet","Mainnet","Malise","Marcel",
		"Marin","Maulore","Meriet","Merigot","Mervin","Mervyn","Mittainne","Morel","Nopolion","Navelet",
		"Nivelet","Nicaise","Noe","Noa","Norbert","Obert","Odard","Omerus","Ondart","Orderic","Orland",
		"Orrick","Othuel","Otuel","Papin","Parcin","Pariset","Pepin","Petruche","Philbert","Picot",
		"Platiau","Popin,","Popiniau","Porchier","Poufille","Pricion","Quabin","Quenall","Raguenel",
		"Raguet","Rahere,","Rahier","Rasequin","Ratier","Rauve","Redway","Renonys","Renost","Renouf",
		"Resse","Ribald","Ringerus","Rionet","Roderick","Rodney","Rodolph","Roncin","Roscelin",
		"Rocelin","Rocelinus","Rosser","Rostand","Rotrou","Roucaud","Ruald","Ruaud","Rufier","Rufin",
		"Rufus","Russell","Roussel","Sadun","Saillot","Sainte","Santin","Samer","Sanguin",
		"Savaric","Savary","Seguin","Sehier","Syhier","Senebaut","Sequin","Sernays","Sevrin",
		"Sicard","Sicho","Sicre","Sigan","Sigismund","Sirion","Sivis","Sohalet","Soolet","Sohier",
		"Souni","Sonnet","Taillefer","Taillemache","Talon","Tassart","Tassot","Tassin","Tevenot",
		"Thouche","Tiessot","Torphin","Turbertus","Turquan","Tutain","Ulger","Vane","Vannes","Vazian",
		"Victor","Waleran","Waleron","Wynkyn","Ydevert"}; //hay 306
    public final static String[] APELLIDOS = {"Achard","Aguilon","Alis","Anzeray","Asselin","Auffrye",
		"Azor","Bailleul","Ballard","Basset","Bauldry","Beaumanoir","Beauvallet","Belet","Benoist",
		"Bernires","Blancbaston","Blouet","Boislevesque","Bolam","Bonel","Bonvalet","Bosc","Bostel",
		"Botin","Bourdekin","Bradwardine","Brebeuf","Breteuil","Brimou","Briqueville","Budi","Burguet",
		"Busnois","Cailli","Calmette","Canaigres","Carbonnel","Carnet","Caunter","Cely","Chartres","Cherbourg",
		"Clerinell","Colleville","Comyn","Corbire","Corneilles","Couci","Courcelles","Courcy","Crevecoeur",
		"Cugey","Louf","Curteys","d'Aguillon","Dalyngridge","Damours","d'Angers","Danneville","Darcy",
		"D'Argouges","d'Arques","d'Auberville","d'Aumale","D'Auvay","d'Avre","Bans","Beauchamp","Beaumont",
		"Bellemare","Berchelai","Bernieres","Bethencourt","Blays","Bourgueville","Calmesnil","Challon",
		"Corbeil","Courseilles","Felius","Gosbeck","Hattes","Lamprire","Malhortye","Mesniel","Monluc",
		"Montgomery","Munchesney","Pinchemont","Reymes","Saussay","Servian","Tocni","Vandes","Villy","d'Engagne",
		"Moutiers","d'Escalles","d'Espagne","d'Evreux","d'Houdetot","Ditton","d'Ivri","d'Olgeanc","d'Ornontville",
		"Droullin","Bec","du","Breuil","Du","Merle","du","Perron","du","Theil","Dubosc","Duhamel","Dupasquier",
		"Durerie","Duval","Elers","Erquemboure","Evelyn","Faintree","Faucon","Ferrieres","Fitzherbert",
		"Flambard","Fonnereau","Fougeres","Fresle","Fromentin","Gand","Gibard","Gilpin","Godart","Gouel",
		"Grai","Grenteville","Grimoult","Guribout","Guideville","Guinand","Hachet","Hamage","Hauville",
		"Herbard","Hewse","Hotot","Hynde","Jubert","la","Cleve","la","Mare","la","Vache","Labba","l'Aigle",
		"Lanquetot","l'Aune","Blanc","Breton","Conte","Despensier","Gaucher","Le","Gras","Maistre","Marinier",
		"Lee","Pelletier","Pravost","Seigneur","Tellier","Lefebre","Levasseur","Ligonier","Lisieux",
		"Loucelles","Lovet","Lynom","Maci","Mallebisse","Mallory","Maminot","Mansel","Marchmain","Margas",
		"Marisco","Maubenc","Maunsell","Maynet","Meri","Meulan","Middleton","Monceaux","Montbrai","Montfort",
		"Moron","Mortain","Moyaux","Murdac","Mussegros","Neot","Neuville","Orlebar","Osmont","Paixdecouer",
		"Papelion","Parry","Pasquier","Paumera","Peis","Paricard","Peveril","Picot","Pinel","Piquiri","Plucknet",
		"Poillei","Pont","Pontchardon","Poussin","Quesnel","Raimbeaucourt","Rames","Ravenot","Restault","Rivire",
		"Roger","Ros","Roussel","Saint-Clair","Saint-Helene","Saint-Quentin","Saint-Waleri","Scolland","Senlis",
		"Sollers","Strivelyn","Taillebois","Tessel","Thibault","Tibon","Tirel","Touchet","Tournai","Tourneville",
		"Trelli","Valance","Vatteville","Vaux","Venois","Verdun","Verrall","Veteripont","Villon","Vis-de-Loup",
		"Viville","Warci","Willoughby"}; //hay 262
    public final static int HOMBRE = 1;
    public final static int MUJER = 2;
    
    
    
    public Persona(int sexo) {
    	nombreAleatorio(sexo);
    	fechaAleatoria();
    }
    
    public Persona(int sexo, String fecha) {
    	nombreAleatorio(sexo);
    	fechaN = fecha;
    }
    
    public void fechaAleatoria() {
    	Integer n;
    	Random r = new Random();
    	n = r.nextInt(28);
    	fechaN = n.toString();
    	n = r.nextInt(12);
    	fechaN = fechaN + '-' + n.toString();
    	n = r.nextInt(10) + 1980;
    	fechaN = fechaN + '-' + n.toString();
    }
    
    
    public void nombreAleatorio(int sexo) {
    	Random r = new Random();
    	String nombre;
    	if (sexo == HOMBRE) {
			int n = r.nextInt(306);
			
			nombre = NOMBRES_HOMBRE[n];
    	}
    	else {
    		int n = r.nextInt(393);
			nombre = NOMBRES_MUJER[n];
    	}
    	String apellido1 = APELLIDOS[r.nextInt(262)];
    	String apellido2 = APELLIDOS[r.nextInt(262)];
    	this.nombre = nombre + " " + apellido1 + " " + apellido2;
    	
    }
    
  /*
   * Getters y setters
   */

    
  	
  	public String getNombre() {
  		return nombre;
  	}
  	
  	public String getFechaN() {
  		return fechaN;
  	}
  	
  	public void setNombre(String nom) {
  		nombre = nom;
  	}
  	
  	public void setFechaN(String fecha) {
  		fechaN = fecha;
  	}
  	
}
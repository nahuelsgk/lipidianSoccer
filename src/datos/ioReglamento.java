/*Autor: Nahuel Velazco*/
package datos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ioReglamento {
	String rutaReglamento;
	public ioReglamento(){
		this.rutaReglamento = System.getProperty("user.dir") + System.getProperty("file.separator") + "Conf" +System.getProperty("file.separator") + "Reglamento.txt";
		String directorio = System.getProperty("user.dir") + System.getProperty("file.separator") + "Conf";
		File directorioFile = new File(directorio);
		if(directorioFile.exists()==false){
			directorioFile.mkdirs();
		}
		File reglamentoFile = new File(this.rutaReglamento);
		if(reglamentoFile.exists()== false){
			escribirArchivoReglamento(this.rutaReglamento,crearReglamentoPorDefecto());
		}
	}
	
	public String leerArchivoReglamento(){
		File file = new File(this.rutaReglamento);
	    StringBuffer contents = new StringBuffer();
	    BufferedReader reader = null;
        try{
	       reader = new BufferedReader(new FileReader(file));
	       String text = null;
	       while ((text = reader.readLine()) != null)
	            {
	                contents.append(text).append(System.getProperty("line.separator"));
	            }
	        } catch (FileNotFoundException e)
	        {
	            e.printStackTrace();
	        } catch (IOException e)
	        {
	            e.printStackTrace();
	        } finally
	        {
	            try
	            {
	                if (reader != null)
	                {
	                    reader.close();
	                }
	            } catch (IOException e)
	            {
	                e.printStackTrace();
	            }
	        }
	        return contents.toString();
	    }
	


	private void escribirArchivoReglamento(String archivoReglamento,String reglamento) {
		   try{
			    BufferedWriter out = new BufferedWriter(new OutputStreamWriter
                        (new FileOutputStream(this.rutaReglamento),System.getProperty("file.encoding")));
			    out.write(reglamento);
			    out.close();
			    }catch (Exception e){
			      System.err.println("Error: " + e.getMessage());
			    }
			  }
	

	private String crearReglamentoPorDefecto(){
		String reglamento = new String("Regla 1 - El terreno de juego"
 + "\n" + ""
 + "\n" + "1- Dimensiones :"
 + "\n" + "El terreno de juego debe ser rectangular. De longitud 100 metros y anchura 70 metros."
 + "\n" + ""
 + "\n" + "2- Marcado de campo :"
 + "\n" + "El terreno de juego debe marcarse con arreglo al plano adjunto."
 + "\n" + ""
 + "\n" + "Las líneas más largas se llaman líneas de banda y las más cortas, líneas de meta."
 + "\n" + "El centro del terreno de juego será indicado con un punto alrededor del cual se trazará"
 + "\n" + "un círculo de 9'15 metros de diámetro."
 + "\n" + ""
 + "\n" + "3- Area de meta:"
 + "\n" + "A cada extremo del terreno y a 5'5 metros de cada poste del marco, se marcarán dos"
 + "\n" + "líneas perpendiculares a la línea de meta, que se adentrarán 5'5 metros en el terreno de"
 + "\n" + "juego y que se unirán en sus extremos mediante otra línea paralela a la meta. Cada uno"
 + "\n" + "de los dos espacios delimitados por estas líneas se denomina área de meta."
 + "\n" + ""
 + "\n" + "4- Area de penal :"
 + "\n" + "A cada extremo del terreno de juego y a 16'5 metros de distancia de cada poste del"
 + "\n" + "marco, se trazarán dos líneas de 16'5 metros perpendiculares a la línea de meta. Estas"
 + "\n" + ""
 + "\n" + "19"
 + "\n" + ""
 + "\n" + "Lipidian Evolution Soccer"
 + "\n" + ""
 + "\n" + "dos líneas se unirán en sus extremos por otra, paralela a la línea de meta. La superficie"
 + "\n" + "comprendida entre estas líneas se denominará área penal."
 + "\n" + "En cada área penal se marcará de forma visible un punto, que estará situado sobre una"
 + "\n" + "línea imaginaria perpendicular a la línea de meta en su centro, y a una distancia de 11"
 + "\n" + "metros de esta. Esta señal será el punto de ejecución del penal."
 + "\n" + "Tomando como centro los puntos de penal, se trazará al exterior de cada área penal un"
 + "\n" + "arco de circunferencia de 9'15 metros de radio."
 + "\n" + ""
 + "\n" + "5- Area de esquina:"
 + "\n" + "Desde cada banderola de esquina y en la parte interior del terreno de juego, se"
 + "\n" + "marcarán cuatro arcos de circunferencia de 1 metro de radio."
 + "\n" + ""
 + "\n" + "6- Las porterias"
 + "\n" + "En el centro de cada línea de meta se colocaran los marcos, formados por dos postes"
 + "\n" + "verticales, equidistantes de las banderolas de esquina y separadas 7'32 metros entre sí"
 + "\n" + "(medida interior), unidos en sus extremos por un larguero horizontal cuyo borde inferior"
 + "\n" + "estará a 2'44 metros del suelo."
 + "\n" + ""
 + "\n" + "Regla2 - El balón"
 + "\n" + "El balón será esférico; su cubierta ha de ser de cuero o de otro material aprobado."
 + "\n" + ""
 + "\n" + "Regla 3 - Numero de jugadores"
 + "\n" + "1- El partido será jugado por dos equipos compuestos cada uno por 11 jugadores, de los"
 + "\n" + "cuales uno jugará como guardameta."
 + "\n" + ""
 + "\n" + "2- Se podrán utilizar jugadores sustitutos en cualquier partido siempre que se atenga a"
 + "\n" + "las condiciones siguientes :"
 + "\n" + "(c)- que cualquier equipo no puede usar más de tres sustitutos en cualquier partido,"
 + "\n" + "escogidos de los 7 que pueden ser requeridos a presentarse."
 + "\n" + ""
 + "\n" + "3- Cualquiera de los otros jugadores podrá cambiar su puesto con el guardameta siempre"
 + "\n" + "que el árbitro haya sido previamente informado, y siempre también que el cambio sea"
 + "\n" + "efectuado durante una detención del juego."
 + "\n" + ""
 + "\n" + "4- Se estima que un encuentro no puede ser considerado normal si hay menos de siete"
 + "\n" + "jugadores en uno de los dos equipos."
 + "\n" + ""
 + "\n" + "Regla 4 - Duración del partido"
 + "\n" + ""
 + "\n" + "El partido comprenderá dos tiempos iguales de 45 minutos cada uno, exceptuando  que"
 + "\n" + "la duración de cada período deberá ser prolongada a fin de permitir la ejecución de un"
 + "\n" + "penal."
 + "\n" + ""
 + "\n" + "Los jugadores tienen derecho a un descanso en el medio tiempo. El descanso entre los"
 + "\n" + "dos tiempos no podrá exceder de cinco minutos, a menos que lo autorice el árbitro."
 + "\n" + ""
 + "\n" + "20"
 + "\n" + ""
 + "\n" + "Lipidian Evolution Soccer"
 + "\n" + ""
 + "\n" + "Regla 5 - Saque de salida"
 + "\n" + ""
 + "\n" + "(a) Al iniciarse el partido (saque de comienzo), la elección de campos y del saque de"
 + "\n" + "comienzo se sorteará mediante una moneda. A una señal del árbitro, el juego comenzará"
 + "\n" + "con un saque a balón parado, es decir, con un puntapié dado, en dirección al campo"
 + "\n" + "contrario, al balón colocado en tierra en el centro del terreno. Todos los jugadores"
 + "\n" + "deberán estar situados en su propio campo y los del bando contrario a aquel que efectúa"
 + "\n" + "el saque de salida no podrán acercarse a menos de 9'15 metros. del balón antes de que"
 + "\n" + "el saque haya sido ejecutado."
 + "\n" + ""
 + "\n" + "(b) Después de marcado un tanto. El juego se reanudará de la misma forma antes"
 + "\n" + "indicada, haciendo el saque de salida un jugador del bando contrario al que marcó el"
 + "\n" + "tanto."
 + "\n" + ""
 + "\n" + "(c) Después del descanso, los equipos cambiarán de campo y el saque de salida lo"
 + "\n" + "efectuará un jugador del bando contrario al que marcó el tanto."
 + "\n" + ""
 + "\n" + "Regla 6 - Tanto marcado"
 + "\n" + ""
 + "\n" + "Se ganará un tanto cuando el balón haya traspasado totalmente la línea de meta entre"
 + "\n" + "los postes y por debajo del larguero sin que haya sido llevado, lanzado o"
 + "\n" + "intencionadamente golpeado con la mano o el brazo por cualquier jugador del equipo"
 + "\n" + "atacante, excepto en el caso de que lo haga el guardameta que se halle en su propia"
 + "\n" + "área de penal."
 + "\n" + "El equipo que haya marcado mayor numero de tantos ganará el partido; Si no se hubiese"
 + "\n" + "marcado ningún tanto, o si ambos equipos han logrado igual numero de ellos, el partido"
 + "\n" + "se considerará empatado."
 + "\n" + ""
 + "\n" + "Regla 7 - Fuera de juego"
 + "\n" + ""
 + "\n" + "1- un jugador está en posición de fuera de juego si se encuentra más cerca de la línea de"
 + "\n" + "meta contraria que el balón, salvo :"
 + "\n" + ""
 + "\n" + "(a) Que se encuentre en su propia mitad del terreno."
 + "\n" + ""
 + "\n" + "(b) Que haya entre él y la línea de meta contraria dos adversarios por lo menos."
 + "\n" + ""
 + "\n" + "2- Un jugador solamente será declarado fuera de juego y sancionado por estar en"
 + "\n" + "posición de fuera de juego si, en el momento en que el balón toca o es jugado por un"
 + "\n" + "compañero, él está, según el árbitro :"
 + "\n" + ""
 + "\n" + "(a) Interfiriendo el juego o a un contrario."
 + "\n" + ""
 + "\n" + "(b) Tratando de sacar ventaja de esta posición."
 + "\n" + ""
 + "\n" + "3- Un jugador no será declarado fuera de juego por el árbitro :"
 + "\n" + ""
 + "\n" + "(a) Simplemente por encontrarse en una posición de fuera de juego."
 + "\n" + ""
 + "\n" + "(b) Si recibe la pelota directamente de un saque de meta, saque de esquina, saque de"
 + "\n" + "banda o de un balón a tierra del árbitro."
 + "\n" + ""
 + "\n" + "21"
 + "\n" + ""
 + "\n" + "Lipidian Evolution Soccer"
 + "\n" + ""
 + "\n" + "4- Si un jugador es declarado fuera de juego, el árbitro deberá otorgar un tiro libre"
 + "\n" + "indirecto que será lanzado por un jugador del equipo contrario desde el lugar donde se"
 + "\n" + "cometió la falta, a menos que la infracción sea cometida por un jugador en el área de"
 + "\n" + "meta contraria. En este caso, el tiro libre podrá ser lanzado desde cualquier lugar"
 + "\n" + "dentro de aquella mitad del área de meta en la cual se cometió la falta."
 + "\n" + ""
 + "\n" + "Regla 8 - Faltas e incorrecciones"
 + "\n" + "Un jugador que comete intencionadamente una de las nueve faltas siguientes :"
 + "\n" + ""
 + "\n" + "(a) Dar o intentar dar una patada a un contrario."
 + "\n" + ""
 + "\n" + "(b) Poner una zancadilla a un contrario, es decir, hacerle caer o intentarlo, sea por"
 + "\n" + "medio de la pierna o agachándose delante o detrás de él."
 + "\n" + ""
 + "\n" + "(c) Saltar sobre un adversario."
 + "\n" + ""
 + "\n" + "(d) Cargar violenta o peligrosamente a un contrario."
 + "\n" + ""
 + "\n" + "(e) Cargar por detrás a un adversario que no hace obstrucción."
 + "\n" + ""
 + "\n" + "(f) Golpear o intentar golpear a un adversario o escupirlo."
 + "\n" + ""
 + "\n" + "(g) Sujetar a un adversario."
 + "\n" + ""
 + "\n" + "(h) Empujar a un adversario."
 + "\n" + ""
 + "\n" + "(i) Jugar el balón es decir, llevarlo, golpearlo o lanzarlo con la mano o el brazo (esta"
 + "\n" + "disposición no es aplicable al guardameta dentro de su propia área de penal)"
 + "\n" + ""
 + "\n" + "Será castigado con golpe franco directo, concedido al equipo contrario en el sitio donde"
 + "\n" + "la falta fue cometida, a menos que la infracción fuera por un jugador en el área de meta"
 + "\n" + "contraria. En este caso, el tiro libre podrá ser lanzado de cualquier lugar de aquella"
 + "\n" + "mitad del área de meta en la cual se cometió la falta."
 + "\n" + "Si un jugador del equipo defensor comete intencionadamente dentro del área penal una"
 + "\n" + "de las nueve faltas anteriormente indicadas, será castigado con un penal."
 + "\n" + ""
 + "\n" + "Regla 9 - Penal"
 + "\n" + "El penal se tirará desde el punto de penal y, antes de que se ejecute, todos los"
 + "\n" + "jugadores, a excepción del que va a ejecutar el castigo y del guardameta adversario,"
 + "\n" + "deberán estar en el interior del campo de juego, pero fuera del área de penal y"
 + "\n" + "distanciados por lo menos 9'15 metros del punto de penal."
 + "\n" + "En caso de necesidad, podrá prolongarse la duración del juego al final de la primera"
 + "\n" + "parte o del partido, con objeto de permitir la ejecución de un penal."
 + "\n" + ""
 + "\n" + "Regla 10 - Saque de banda"
 + "\n" + "Cuando el balón en su totalidad haya traspasado la línea de banda, ya sea por tierra o"
 + "\n" + "por aire, será puesto nuevamente en juego lanzándolo al interior del campo en cualquier"
 + "\n" + "dirección, desde el punto por el que franqueó la línea, por un jugador del equipo"
 + "\n" + "contrario al del que tocó el balón en ultimo lugar."
 + "\n" + ""
 + "\n" + "22"
 + "\n" + ""
 + "\n" + "Lipidian Evolution Soccer"
 + "\n" + ""
 + "\n" + "Regla 11 - Saque de meta"
 + "\n" + "Cuando el balón en su totalidad haya traspasado la línea de meta, excluida la parte"
 + "\n" + "comprendida entre los postes del marco, ya sea por tierra o por aire, habiendo sido"
 + "\n" + "jugada en ultimo termino por un jugador del equipo atacante, se colocara en un punto"
 + "\n" + "cualquiera de aquella mitad del área de meta mas cercana al sitio por el cual cruzo la"
 + "\n" + "línea, y será lanzado con el pie, directamente al juego más allá del área penal, por un"
 + "\n" + "jugador del equipo defensor."
 + "\n" + ""
 + "\n" + "Regla 12 - Saque de esquina"
 + "\n" + "Cuando el balón en su totalidad haya traspasado la línea de meta, excluida la parte"
 + "\n" + "comprendida entre los postes del marco, ya sea por tierra o por aire, habiendo sido"
 + "\n" + "jugado en ultimo termino por un jugador del equipo defensor, se concederá un saque de"
 + "\n" + "esquina, que será lanzado por un jugador del equipo atacante así : todo el balón será"
 + "\n" + "colocado en el interior del cuarto de circulo correspondiente a la banderola de esquina"
 + "\n" + "más cercana al sitio por donde salió el balón, banderola que no podrá ser trasladada, y"
 + "\n" + "es de este lugar de donde será lanzado el balón. Podrá ganarse un tanto directamente"
 + "\n" + "de un saque de esquina.");
		
		return reglamento;
	}
}

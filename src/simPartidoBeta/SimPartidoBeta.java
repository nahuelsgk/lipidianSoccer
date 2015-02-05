/*
 * Alberto Moreno Vega
 */


package simPartidoBeta;


import javax.swing.JFrame;
import controladores.CtrlPresentacion;

public class SimPartidoBeta extends JFrame {

	public final static int ANCHO_FUERA = 80;
	public final static int LARGO_CAMPO = 1000+ANCHO_FUERA; //El +20 es el ancho de la zona de "fuera" 
	public final static int ANCHO_CAMPO = 700+ANCHO_FUERA;
	
	
	
    public SimPartidoBeta() {


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(LARGO_CAMPO, ANCHO_CAMPO);
        setLocationRelativeTo(null);
        setTitle("Simulador partidos beta");
        setResizable(false);
        setVisible(true);
    }

}

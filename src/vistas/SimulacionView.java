/*
 * Alberto Moreno Vega
 */


package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;


import simPartidoBeta.Jugador;
import simPartidoBeta.PelotaSim;

import controladores.CtrlPresentacion;




public class SimulacionView extends JPanel implements Runnable {
	private static final long serialVersionUID = 2010927556452001189L;
	public final static int ANCHO_FUERA = 80;
	public final static int LARGO_CAMPO = 1000+ANCHO_FUERA; 
	public final static int ANCHO_CAMPO = 700+ANCHO_FUERA;
	
	boolean blocked;
	private int temporizador;
	private int evento;
	private JButton pauseButton;
	
	
	//equipo 2 visitante
	//equipo 1 local
	private void visibilidadBotones() {
		equipo2CambiosButton.setVisible(CtrlPresentacion.getInstancia().getVisitanteUsuario());
		equipo1CambiosButton.setVisible(CtrlPresentacion.getInstancia().getLocalUsuario());
	}
	
	private void initGUI() {
		{
			this.setPreferredSize(new java.awt.Dimension(1080, 780));
			this.setLayout(null);
			{
				pauseButton = new JButton();
				pauseButton.setText("Pausa");
				pauseButton.setBounds(490, 0, 122, 20);
				pauseButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						pauseButtonActionPerformed(evt);
					}
				});
				this.add(pauseButton);
				pauseButton.setVisible(true);
			}
			{
				equipo2CambiosButton = new JButton();
				this.add(equipo2CambiosButton);
				equipo2CambiosButton.setText("Alineacion");
				equipo2CambiosButton.setBounds(40, 14, 122, 22);
				equipo2CambiosButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						equipo2CambiosButtonActionPerformed(evt);
					}
				});
				equipo2CambiosButton.setVisible(CtrlPresentacion.getInstancia().getVisitanteUsuario());
			}
			{
				equipo1CambiosButton = new JButton();
				this.add(equipo1CambiosButton);
				equipo1CambiosButton.setText("Alineacion");
				equipo1CambiosButton.setBounds(918, 14, 122, 22);
				equipo1CambiosButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						equipo1CambiosButtonActionPerformed(evt);
					}
				});
				equipo1CambiosButton.setVisible(CtrlPresentacion.getInstancia().getLocalUsuario());
			}
		}
	}
	
	public SimulacionView() {
		super();
        setFocusable(true);
        setBackground(Color.GREEN);
        setDoubleBuffered(true);
        blocked = false;
        temporizador = 0;
        initGUI();
       
  
	}

    private Thread animator;

    

    private final int DELAY = 25; //valor correcto es 25
    private JButton equipo1CambiosButton;
    private JButton equipo2CambiosButton;

    
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }
    
    
    public void paint(Graphics g) {
        super.paint(g);
   
        
        Graphics2D g2d = (Graphics2D)g;
        
        //Activar antialiaising
       // RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       // rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
       // g2d.setRenderingHints(rh);
      	

        dibujaCampo(g2d);
        dibujaJugadores(g2d);
        
        
        //dibujar balon
        PelotaSim pelota = CtrlPresentacion.getInstancia().getPelota();
        Ellipse2D circle = new Ellipse2D.Double((pelota.getX()+ANCHO_FUERA/2)-pelota.getAnchura()/2, 
        											(pelota.getY()+ANCHO_FUERA/2)-pelota.getAltura()/2,
        									pelota.getAltura(), pelota.getAnchura());
        String meteo = CtrlPresentacion.getInstancia().getMeteorologia();
        Color pelotaCol = Color.black;
      	g2d.setColor(pelotaCol);
      	g2d.fill(circle);
      	
      	g2d.setColor(Color.black);
      	dibujaTiempo(CtrlPresentacion.getInstancia().getTiempo(), g2d);
      	dibujaMarcador(g2d);
      	dibujaRotulosEventos(g2d);
      	dibujaMeteo(g2d);
 
      	Rectangle2D altura = new Rectangle2D.Double(0, ANCHO_CAMPO-40-pelota.getZ(), 10, pelota.getZ());
      	
      	
      	if (meteo.compareTo("Neu") == 0) {
      		g2d.setColor(Color.BLACK);
      	}
      	else {
      		g2d.setColor(Color.WHITE);
      	}
    	g2d.fill(altura);
      	
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }


   private void dibujaMeteo(Graphics2D g2d) {
	   Font small = new Font("Helvetica", Font.BOLD, 14);
	   g2d.setFont(small);
	   g2d.setColor(Color.WHITE);
	   String meteo = CtrlPresentacion.getInstancia().getMeteorologia();
	   String msg;;
	   if (meteo.compareTo("Pluja") == 0) {
		   msg = "Esta lloviendo";
	   }
	   else if (meteo.compareTo("Sol") == 0) {
		   msg = "El tiempo es soleado";
	   }
	   else if (meteo.compareTo("Neu") == 0) {
		   msg = "Esta nevando";
		   g2d.setColor(Color.BLACK);
	   }
	   else if (meteo.compareTo("Nuvol") == 0) {
		   msg = "El cielo esta nublado";
	   }
	   else {
		   msg = "Hace bastante viento";
	   }

	   g2d.drawString(msg, 250, 20);
   }
    
    
    private void dibujaRotulosEventos(Graphics2D g2d) {

    	if (CtrlPresentacion.getInstancia().getEvento() != 0) {
    		evento = CtrlPresentacion.getInstancia().getEvento();
    		CtrlPresentacion.getInstancia().setEvento(0);
    		temporizador = 240;
    	}
    	if (temporizador > 0) {
	    	switch (evento) {
	    	case CtrlPresentacion.INICIO_PARTIDO: rotuloInicioPartido(g2d, true); break;
	    	case CtrlPresentacion.INICIO_SEGUNDA_PARTE: rotuloInicioPartido(g2d, false); break;
	    	case CtrlPresentacion.FALTA_NORMAL: rotuloGenerico(g2d, "Falta!!"); break;
	    	case CtrlPresentacion.FALTA_AMARILLA: rotuloGenerico(g2d, "Tarjeta Amarilla!!"); break;
	    	case CtrlPresentacion.FALTA_ROJA: rotuloGenerico(g2d, "Tarjeta Roja!!"); break;
	    	case CtrlPresentacion.FUERA_DE_JUEGO: rotuloGenerico(g2d, "Fuera de juego!!"); break;
	    	case CtrlPresentacion.GOL_LOCAL: rotuloGenerico(g2d, "GOL!!!!!!"); break;
	    	case CtrlPresentacion.GOL_VISITANTE: rotuloGenerico(g2d, "GOL!!!!!!"); break;
	    	case CtrlPresentacion.FUERA: rotuloGenerico(g2d, "Fuera!!"); break;
	    	case CtrlPresentacion.PENALTI: rotuloGenerico(g2d, "Penalti!!"); break;
	    	default: break;
	    	}
    	}
    }

    private void rotuloGenerico(Graphics2D g2d, String rotulo) {
    	Font small = new Font("Helvetica", Font.BOLD, 80);
    	g2d.setFont(small);
    	String meteo = CtrlPresentacion.getInstancia().getMeteorologia();
    	if (meteo.compareTo("Neu") == 0) {
      		g2d.setColor(Color.BLACK);
      	}
      	else {
      		g2d.setColor(Color.WHITE);
      	}
    	int x = 500 - (rotulo.length() * 15);
    	g2d.drawString(rotulo, x, 350);
    	
    }
    
    private void rotuloInicioPartido(Graphics2D g2d, boolean primeraParte) {
    	Font small = new Font("Helvetica", Font.BOLD, 40);
    	g2d.setFont(small);
    	int x1;
    	int x2;
    	if (primeraParte) {
    		x1 = 100;
    		x2 = 600;
    	}
    	else {
    		x1 = 600;
    		x2 = 100;
    	}
    	String local = CtrlPresentacion.getInstancia().getLocal();
    	String visitante = CtrlPresentacion.getInstancia().getVisitante();
    	g2d.setColor(Color.RED);
    	g2d.drawString(visitante, x1, 350);
    	g2d.setColor(Color.BLUE);
    	g2d.drawString(local, x2, 350);
    }
    
    private void dibujaMarcador (Graphics2D g2d) {
    	Font small = new Font("Helvetica", Font.BOLD, 14);
        g2d.setFont(small);
        String local = CtrlPresentacion.getInstancia().getLocal();
        String visitante = CtrlPresentacion.getInstancia().getVisitante();
        Integer golLocal = CtrlPresentacion.getInstancia().getGolesLocal();
        Integer golVisitante = CtrlPresentacion.getInstancia().getGolesVisitante();
        String msg1 = visitante+" "+golVisitante;
        String msg2 = golLocal+" "+local;
        int x = msg1.length()*8;
        g2d.setColor(Color.RED);
        g2d.drawString(msg1, 550, 35);
        g2d.setColor(Color.BLACK);
        g2d.drawString("-", 550+x, 35);
        g2d.setColor(Color.BLUE);
        g2d.drawString(msg2, x+8+550, 35);
    }
    
    private void dibujaTiempo (Double tiempo, Graphics2D g2d) {
    	Font small = new Font("Helvetica", Font.BOLD, 14);
    	g2d.setFont(small);
      	Integer segs = (int)(double)tiempo;
      	segs = segs%60;
      	String txtseg = segs.toString();
      	if (segs < 10) {
      		txtseg = "0"+txtseg;
      	}
      	Integer mins = (int)(double)tiempo;
      	mins = mins/60;
      	String txtmin = mins.toString();
      	if (mins < 10) {
      		txtmin = "0"+txtmin;
      	}

      	g2d.drawString(txtmin+":"+txtseg, 450, 35);
      
    }
    
    public void dibujaJugadores(Graphics2D g2d) {
    	Ellipse2D monigote = new Ellipse2D.Double();
    	String msg;;
        Font small = new Font("Helvetica", Font.BOLD, 14);

       
        g2d.setFont(small);
        
        Integer dors;
    	
        Vector<Jugador> visitantes = CtrlPresentacion.getInstancia().getVisitantes();
        Vector<Jugador> locales = CtrlPresentacion.getInstancia().getLocales();
        Vector<Jugador> lesSan = CtrlPresentacion.getInstancia().getLesionadosSancionados();
        
    	for (int i = 0; i < visitantes.size(); ++i) {
    		if (visitantes.elementAt(i).getVisibilidad()) { //si visibilidad = false el jugador no se muestra
	    		dors = visitantes.elementAt(i).getDorsal();
	    		msg = dors.toString();
	    		monigote.setFrame((visitantes.elementAt(i).getX()+ANCHO_FUERA/2)-Jugador.DIAMETRO/2, 
	    							(visitantes.elementAt(i).getY()+ANCHO_FUERA/2)-Jugador.DIAMETRO/2, 
	    											Jugador.DIAMETRO, Jugador.DIAMETRO);
	    		g2d.setColor(Color.RED);
	          	g2d.fill(monigote);
	          	g2d.drawString(msg, (visitantes.elementAt(i).getX()+ANCHO_FUERA/2)-Jugador.DIAMETRO/2,
	          			(visitantes.elementAt(i).getY()+ANCHO_FUERA/2)-Jugador.DIAMETRO/2);
    		}
    	}

    	for (int i = 0; i < locales.size(); ++i) {
    		if (locales.elementAt(i).getVisibilidad()) {
	    		dors = locales.elementAt(i).getDorsal();
	    		msg = dors.toString();
	    		monigote.setFrame((locales.elementAt(i).getX()+ANCHO_FUERA/2)-Jugador.DIAMETRO/2, 
	    							(locales.elementAt(i).getY()+ANCHO_FUERA/2)-Jugador.DIAMETRO/2, 
	    											Jugador.DIAMETRO, Jugador.DIAMETRO);
	    		g2d.setColor(Color.BLUE);
	          	g2d.fill(monigote);
	          	g2d.drawString(msg, (locales.elementAt(i).getX()+ANCHO_FUERA/2)-Jugador.DIAMETRO/2, 
	          					(locales.elementAt(i).getY()+ANCHO_FUERA/2)-Jugador.DIAMETRO/2);
    		}
    	}
    	
    	for (int i = 0; i < lesSan.size(); ++i) {
    		if (lesSan.elementAt(i).getVisibilidad()) {
	    		dors = lesSan.elementAt(i).getDorsal();
	    		msg = dors.toString();
	    		monigote.setFrame((lesSan.elementAt(i).getX()+ANCHO_FUERA/2)-Jugador.DIAMETRO/2, 
	    							(lesSan.elementAt(i).getY()+ANCHO_FUERA/2)-Jugador.DIAMETRO/2, 
	    											Jugador.DIAMETRO, Jugador.DIAMETRO);
	    		g2d.setColor(Color.YELLOW);
	          	g2d.fill(monigote);
	          	g2d.drawString(msg, (lesSan.elementAt(i).getX()+ANCHO_FUERA/2)-Jugador.DIAMETRO/2, 
	          					(lesSan.elementAt(i).getY()+ANCHO_FUERA/2)-Jugador.DIAMETRO/2);
    		}
    	}
    	
    }
    

    public void dibujaCampo(Graphics2D g2d) {
    	int fuera = ANCHO_FUERA/2;
    	int largo = LARGO_CAMPO;
    	int ancho = ANCHO_CAMPO;
    	String meteo = CtrlPresentacion.getInstancia().getMeteorologia();
    	Color fondo = Color.GREEN;
    	Color lineas = Color.WHITE;
    	setBackground(Color.GREEN);
    	if (meteo.compareTo("Neu") == 0) {
    		fondo = Color.white;
        	lineas = Color.green;
    		setBackground(Color.WHITE);
 	   	}
    	else if (meteo.compareTo("Nuvol") == 0 || meteo.compareTo("Pluja") == 0) {
    		Color verdeOscuro = new Color(0, 200, 0);
    		setBackground(verdeOscuro);
    		fondo = verdeOscuro;
    	}
    	
    	//lineas de fuera
    	Rectangle2D square = new Rectangle2D.Double(fuera, fuera, largo-2*fuera , ancho-2*fuera);
    	g2d.setColor(lineas);
    	g2d.fill(square);

    	Rectangle2D square2 = new Rectangle2D.Double(fuera+2, fuera+2, largo-2*fuera-4, ancho-2*fuera-4);
    	g2d.setColor(fondo);
    	g2d.fill(square2);
    	
    	//porteria
    	int anch_port = 24; //medidas del reglamento
    	int lar_port = 72;
    	

    	Rectangle2D port = new Rectangle2D.Double(fuera-anch_port, (ancho/2)-(lar_port/2), anch_port , lar_port);
    	g2d.setColor(lineas);
    	g2d.fill(port);
    	Rectangle2D port2 = new Rectangle2D.Double(largo-fuera, (ancho/2)-(lar_port/2), anch_port , lar_port);
    	g2d.setColor(lineas);
    	g2d.fill(port2);
    	
    	//centro
    	int diam = 182;
    	Ellipse2D centro = new Ellipse2D.Double((largo/2)-(diam/2), (ancho/2)-(diam/2), diam, diam);
    	g2d.setColor(lineas);
      	g2d.fill(centro);
      	Ellipse2D centro2 = new Ellipse2D.Double((largo/2)-(diam/2)+2, (ancho/2)-(diam/2)+2, diam-4, diam-4);
    	g2d.setColor(fondo);
      	g2d.fill(centro2);

      	
      	Ellipse2D centro3 = new Ellipse2D.Double((largo/2)-(10/2), (ancho/2)-(10/2), 10, 10);
    	g2d.setColor(lineas);
      	g2d.fill(centro3);
    	
    	//linea de medio campo
    	GeneralPath linea = new GeneralPath();
    	linea.moveTo(largo/2,fuera);
    	linea.lineTo(largo/2, ancho-fuera);
    	g2d.setColor(lineas);
    	g2d.draw(linea);
    	
    	
    	//arco area castigo
    	int x_cast = 165; //valores de area de castigo
    	int y_cast = 430; 
    	
    	int arco_cen = fuera + x_cast-(diam/3)*2;
    	Ellipse2D arco1_1 = new Ellipse2D.Double(arco_cen, (ancho/2)-(diam/2), diam, diam);
    	g2d.setColor(lineas);
      	g2d.fill(arco1_1);
      	Ellipse2D arco1_2 = new Ellipse2D.Double(arco_cen+2, (ancho/2)-(diam/2)+2, diam-4, diam-4);
    	g2d.setColor(fondo);
      	g2d.fill(arco1_2);
      	Ellipse2D arco2_1 = new Ellipse2D.Double(largo-arco_cen-diam, (ancho/2)-(diam/2), diam, diam);
    	g2d.setColor(lineas);
      	g2d.fill(arco2_1);
      	Ellipse2D arco2_2 = new Ellipse2D.Double(largo-arco_cen-diam+2, (ancho/2)-(diam/2)+2, diam-4, diam-4);
    	g2d.setColor(fondo);
      	g2d.fill(arco2_2);
    	
    	
    	//area de castigo
    	Rectangle2D area_cast1_1 = new Rectangle2D.Double(fuera, (ancho/2)-(y_cast/2), x_cast , y_cast);
    	g2d.setColor(lineas);
    	g2d.fill(area_cast1_1);
    	Rectangle2D area_cast1_2 = new Rectangle2D.Double(fuera+2, (ancho/2)-(y_cast/2)+2, x_cast-4 , y_cast-4);
    	g2d.setColor(fondo);
    	g2d.fill(area_cast1_2);
    	Rectangle2D area_cast2_1 = new Rectangle2D.Double(largo-fuera-x_cast, (ancho/2)-(y_cast/2), x_cast , y_cast);
    	g2d.setColor(lineas);
    	g2d.fill(area_cast2_1);
    	Rectangle2D area_cast2_2 = new Rectangle2D.Double(largo-fuera-x_cast+2, (ancho/2)-(y_cast/2)+2, x_cast-4, y_cast-4);
    	g2d.setColor(fondo);
    	g2d.fill(area_cast2_2);
    	
    	
    	
    	//area de meta
    	int x_meta = 55;
    	int y_meta = 183;
    	Rectangle2D area_meta1_1 = new Rectangle2D.Double(fuera, (ancho/2)-(y_meta/2), x_meta , y_meta);
    	g2d.setColor(lineas);
    	g2d.fill(area_meta1_1);
    	Rectangle2D area_meta1_2 = new Rectangle2D.Double(fuera+2, (ancho/2)-(y_meta/2)+2, x_meta-4 , y_meta-4);
    	g2d.setColor(fondo);
    	g2d.fill(area_meta1_2);
    	Rectangle2D area_meta2_1 = new Rectangle2D.Double(largo-fuera-x_meta, (ancho/2)-(y_meta/2), x_meta, y_meta);
    	g2d.setColor(lineas);
    	g2d.fill(area_meta2_1);
    	Rectangle2D area_meta2_2 = new Rectangle2D.Double(largo-fuera-x_meta+2, (ancho/2)-(y_meta/2)+2, x_meta-4, y_meta-4);
    	g2d.setColor(fondo);
    	g2d.fill(area_meta2_2);
    	
    	//punto penalty
    	int x_pen = 110;
    	Ellipse2D penalty1 = new Ellipse2D.Double(x_pen+fuera - 10/2, (ancho/2)-(10/2), 10, 10);
    	g2d.setColor(lineas);
      	g2d.fill(penalty1);
      	Ellipse2D penalty2 = new Ellipse2D.Double(largo-x_pen-fuera-10/2, (ancho/2)-(10/2), 10, 10);
    	g2d.setColor(lineas);
      	g2d.fill(penalty2);
    }
    
  

    public void cycle() {
    	 repaint();
    }
    
    
    public void pausarSimulacion() {
    	while (CtrlPresentacion.getInstancia().getPausa()) {
    		try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        
        if (!blocked) { //es una especie de mutex casero
        	blocked = true;
	        while (!CtrlPresentacion.getInstancia().partidoFinalizado()) {
	        	if (temporizador > 0) {
	        		temporizador--;
	        	}
	        	else {
	        		evento = 0;
	        	}
	        	
	        	
	        	CtrlPresentacion.getInstancia().siguienteMovimiento();
	        	if (CtrlPresentacion.getInstancia().getPausa()) {
	        		pausarSimulacion();
	        	}
	        	
	        	visibilidadBotones();
	            repaint();
	            
	            timeDiff = System.currentTimeMillis() - beforeTime;
	            sleep = DELAY - timeDiff;
	
	            if (sleep < 0)
	                sleep = 2;
	            try {
	                Thread.sleep(sleep);
	            } catch (InterruptedException e) {
	                System.out.println("interrupted");
	               
	            }
	            beforeTime = System.currentTimeMillis();
	        }
        }
    }

    
    private void pauseButtonActionPerformed(ActionEvent evt) {
		/*if(pauseButton.getText() == "Pausa") {
			pauseButton.setText("Pausa");
			CtrlPresentacion.getInstancia().setPausa(false);
		}
		else {
			pauseButton.setText("Pausa");
			CtrlPresentacion.getInstancia().setPausa(true);
		}*/
    	CtrlPresentacion.getInstancia().setPausa(!CtrlPresentacion.getInstancia().getPausa());
	}
    
    private void equipo2CambiosButtonActionPerformed(ActionEvent evt) {
    	CtrlPresentacion.getInstancia().cambioAlineacion(CtrlPresentacion.VISITANTE);
    	//FutbolFrame.getInstancia().ponerMenu(PanelMenu.ESCOGER_ALINEACION, true);
    }
    
    private void equipo1CambiosButtonActionPerformed(ActionEvent evt) {
    	CtrlPresentacion.getInstancia().cambioAlineacion(CtrlPresentacion.LOCAL);
    	//FutbolFrame.getInstancia().ponerMenu(PanelMenu.ESCOGER_ALINEACION, true);
    }
}

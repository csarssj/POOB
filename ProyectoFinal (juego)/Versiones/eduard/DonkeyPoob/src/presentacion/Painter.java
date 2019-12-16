package presentacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


import aplicacion.*;


public class Painter extends JPanel implements Observer {
	private PantallaJuego frame;
	public DonkeyPoob logica;
	private InputController inputController = new InputController();
	private ArrayList<Integer> keysDown;
	private Thread hilo;
	private int score = 0;
	private int level = 1;
	private int highScore;
	private DonkeyPoob model;
	private String modoJuego;
	private String personaje;
	private String personajeColor;
	private String personaje2;
	private String personajeColor2;
	private String peachColor;
	private boolean usaRojo;
	private boolean usaAzul;
	private boolean usaAmarillo;
	private boolean usaVerde;
	private boolean usaSorpresa;
	boolean pausa=false; 
	
	
	
	//Imagenes
	Image mario = new ImageIcon(getClass().getResource("/resources/mario.png")).getImage();
	Image marioA = new ImageIcon(getClass().getResource("/resources/marioA.png")).getImage();
	Image marioB = new ImageIcon(getClass().getResource("/resources/marioB.png")).getImage();
	Image marioM = new ImageIcon(getClass().getResource("/resources/marioM.png")).getImage();
	Image marioH = new ImageIcon(getClass().getResource("/resources/mario-hammer-gif.gif")).getImage();
	Image luigi = new ImageIcon(getClass().getResource("/resources/luigi.png")).getImage();
    Image kong = new ImageIcon(getClass().getResource("/resources/konky_dong.gif")).getImage();
    Image peach = new ImageIcon(getClass().getResource("/resources/peach.png")).getImage();
    Image peachV = new ImageIcon(getClass().getResource("/resources/peachV.png")).getImage();
    Image peachA = new ImageIcon(getClass().getResource("/resources/peachA.png")).getImage();
    Image barrel = new ImageIcon(getClass().getResource("/resources/barrel.png")).getImage();
    Image barrel1 = new ImageIcon(getClass().getResource("/resources/barrelblue.png")).getImage();
    Image barrel2 = new ImageIcon(getClass().getResource("/resources/barrelgreen.png")).getImage();
    Image barrel3 = new ImageIcon(getClass().getResource("/resources/barrelred.png")).getImage();
    Image platform = new ImageIcon(getClass().getResource("/resources/platform.png")).getImage();
    Image ladder = new ImageIcon(getClass().getResource("/resources/ladder.png")).getImage();
    Image ladderS = new ImageIcon(getClass().getResource("/resources/ladderS.png")).getImage();
    Image hammer = new ImageIcon(getClass().getResource("/resources/hammer.PNG")).getImage();
    Image hongo = new ImageIcon(getClass().getResource("/resources/hongo2.png")).getImage();
    Image apple = new ImageIcon(getClass().getResource("/resources/apple.png")).getImage();
    Image heart = new ImageIcon(getClass().getResource("/resources/corazon.png")).getImage();
    Image cherry= new ImageIcon(getClass().getResource("/resources/cherry.png")).getImage();
    Image rope= new ImageIcon(getClass().getResource("/resources/rope.png")).getImage();
    Image ball= new ImageIcon(getClass().getResource("/resources/ballB.png")).getImage();
	
	public Painter(String name,String personaje,String personajeColor,String peachColor,boolean rojo, boolean azul, boolean amarillo, boolean verde, boolean sorpresa) throws DonkeyException {
	     //colores
		try {
		 System.out.println("Entra EN Painter Normal");
		 setOpaque(true);
	     this.setBackground(Color.black);
	     if(name.equals("")){throw new DonkeyException(DonkeyException.NOT_NAME);}
	     Font thisFont = new Font("Arial", Font.PLAIN, 36);
	     this.personaje = personaje;
	     this.personajeColor=personajeColor;
	     this.peachColor=peachColor;
	     this.usaRojo = rojo;
	     this.usaAzul = azul;
	     this.usaAmarillo = amarillo;
	     this.usaVerde = verde;
	     this.usaSorpresa = sorpresa;
	     this.model = new DonkeyPoob("normal",personaje,usaRojo, usaAzul, usaAmarillo, usaVerde, usaSorpresa); //crear nuevo modo de juego
	   	 modoJuego = "normal";
		PlayGame();
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Painter(DonkeyPoob model) 
    {
		System.out.println("ENTRA EN VS Painter");
        setOpaque(true);
        setBackground(Color.BLACK);
        this.model = model;
        this.model.addObserver(this);
        modoJuego="JugadorVsPc";
        model = new DonkeyPoob("JugadorVsPc","Mario",usaRojo, usaAzul, usaAmarillo, usaVerde, usaSorpresa); //crear nuevo modo de juego VS
        //model = new DonkeyPoob("normal"); //crear nuevo modo de juego
        
        
    }
	
	//Metodo prueba para que funcione la IA
	public Painter(String modo,String name,String name2,String personaje,String personajeColor,String CPU,String CPUColor,String peachColor,String rival,boolean rojo, boolean azul, boolean amarillo, boolean verde, boolean sorpresa) throws DonkeyException {
	     try
         {
	    	 System.out.println("Esta en Painter Mimo: "+modo);
	         setOpaque(true);
	         setBackground(Color.BLACK);
	         if(name.equals("")||name2.equals("")){throw new DonkeyException(DonkeyException.NOT_NAME);}
	         this.personaje = personaje;
		     this.personajeColor=personajeColor;
		     this.personaje2 = CPU;
		     this.personajeColor2=CPUColor;
		     this.peachColor=peachColor;
		     this.usaRojo = rojo;
		     this.usaAzul = azul;
		     this.usaAmarillo = amarillo;
		     this.usaVerde = verde;
		     this.usaSorpresa = sorpresa;
		     Font thisFont = new Font("Arial", Font.PLAIN, 36);
		     if (modo.equals("JugadorVsJugador"))
	    	 	this.model = new DonkeyPoob("JugadorVsJugador",personaje,personaje2,usaRojo, usaAzul, usaAmarillo, usaVerde, usaSorpresa); //crear nuevo modo de juego
		     else if (modo.equals("JugadorVsPc"))
		     {
		     	this.model = new DonkeyPoob("JugadorVsPc",personaje,personaje2,usaRojo, usaAzul, usaAmarillo, usaVerde, usaSorpresa);
		     	model.setRival(rival);
		     }
		     	
	    	 modoJuego=modo;
	    	 //model = new DonkeyPoob("normal"); //crear nuevo modo de juego
             PlayGame();
         } catch (InterruptedException | IOException ex) {
             //Logger.getLogger(Painter.class.getName()).log(Level.SEVERE, null, ex);
         }
	}
	
	public void setRival(String rival) {
		model.setRival(rival);
	}

	public void setDonkey(DonkeyPoob newModel) {
		this.model= newModel;
	}

     class InputController implements KeyListener //clase utilizada para presionar las teclas desde el teclado
     {
     private final boolean[] down = new boolean[255];
     private final boolean[] pressed = new boolean[255];

     @Override
     public void keyPressed(KeyEvent e)
     {
         down[e.getKeyCode()] = true;
         pressed[e.getKeyCode()] = true;
         model.passKeysDownToPlayer(down); //pasar la llave al modelo
     }

     @Override
     public void keyReleased(KeyEvent e)
     {
         down[e.getKeyCode()]= false;
         model.passKeysDownToPlayer(down); //pasar la llave al modelo
     }

     @Override
     public void keyTyped(KeyEvent e)
     {
    	// hacer: todos los métodos deben ser anulados
     }
 
     }
     
     public Painter getPainter() {
    	 return this;
     }
	
	public void PlayGame() throws InterruptedException, IOException
    {
        
        //Painter tempPan = this;
        frame = new PantallaJuego(model,this); //crear el frame del modo de juego MODIFICADO
        frame.addKeyListener(inputController);
        System.out.println("A�adio el listener en PlayGame Paintener");
        //hacer un hilo que controle la lógica del modo de juego
        Thread thread = new Thread(){
            @Override
            public void run()
            {	
                try
                {
                    boolean endGame = false;
                    while (!endGame)
                    {
                    	System.out.println("ESTE ES EL MODO DE JUEGO DEL HILO: "+modoJuego);
                    	if (modoJuego.equals("normal")){
                    		//System.out.println("Entra en normal Painter "+model);

                            if (!model.startGame(frame))
                            {
                                //el juego ha finalizado.
                                frame.dispose();
                                endGame = true;
                            }
                            else
                            {
                                //el jugador quiere volver a jugar, crear un nuevo controlador para las teclas; si no, Mario sigue saltando / corriendo después de que se inicia el nuevo juego
                                frame.dispose();
                                model = new DonkeyPoob("normal",personaje,usaRojo, usaAzul, usaAmarillo, usaVerde, usaSorpresa); //crear nuevo modo de juego
                                frame = new PantallaJuego(model,getPainter());
                                inputController = new InputController();
                                frame.addKeyListener(inputController);
                            }
                    		
                    	}else if(modoJuego.equals("JugadorVsPc")) {
                    		System.out.println("ModoJuego==jugadorVsPc modonormal "+model);
                            if (!model.startGame2(frame))
                            {
                                //el juego ha finalizado.
                                frame.dispose();
                                endGame = true;
                            }
                            else
                            {
                                //el jugador quiere volver a jugar, crear un nuevo controlador para las teclas; si no, Mario sigue saltando / corriendo después de que se inicia el nuevo juego
                                frame.dispose();
                                //model = new DonkeyPoob("VS"); //crear nuevo modo de juego
                                model = new DonkeyPoob("JugadorVsPc",personaje,usaRojo, usaAzul, usaAmarillo, usaVerde, usaSorpresa);
                               // model = new DonkeyPoob("normal");
                                frame = new PantallaJuego(model,getPainter());
                                inputController = new InputController();
                                frame.addKeyListener(inputController);
                            }
                    	}else if(modoJuego.equals("JugadorVsJugador")) {
                    		if (!model.startGame2(frame))
                            {
                                //el juego ha finalizado.
                                frame.dispose();
                                endGame = true;
                            }
                            else
                            {
                                frame.dispose();
                                model = new DonkeyPoob("JugadorVsPc",personaje,usaRojo, usaAzul, usaAmarillo, usaVerde, usaSorpresa);
                                frame = new PantallaJuego(model,getPainter());
                                inputController = new InputController();
                                frame.addKeyListener(inputController);
                            }
                    	}
             
                    }
                } catch (InterruptedException e) {
                } catch (IOException ex) {
                    Logger.getLogger(Painter.class.getName()).log(Level.SEVERE, null, ex);
                }    
            }
        };
        thread.start(); //iniciamos el hilo
        AbstractAction FPSTimer = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.gamePanel.repaint(); //volver a pintar el frame				
            }			
        };
        new Timer(15, FPSTimer).start();
    }
	@Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g); 
        //g.drawImage(marioH, 60, 120, 100, 100, this);
        g.drawImage(kong, 60, 120, 100, 100, this); //dibujar a  kong
        for(int i = 0; i<model.life;i++) {
        	g.drawImage(mario,500+i*10,70,10,13, null);
        }
        for(int i = 0; i<model.life2;i++) {
        	g.drawImage(luigi,500+i*10,85,10,13, null);
        }
      //  g.drawImage(hammer, 60, 120, 100, 100, this);
        for (ObjetoEstatico object : model.getSOList()) //pintar cada objeto estatico, escaleras,plataformas y peach
        {
          //  if(object instanceof Plataforma) g.drawImage(platform,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
        	if(object instanceof Plataforma) {
            	//System.out.println("entraa");
            	if(object instanceof PlataformaMagica) {
            		
	            	 int [] xpoints= {(int)object.getXPos(),(int)object.getWidth(),(int)object.getWidth(),(int)object.getXPos()};
	                 int [] ypoints= {(int)object.getYPos(),(int)object.getHeight(),(int)object.getY3(),(int)object.getY4()};
	                 int y = (int)object.getYPos();
	                 for(int i = (int)object.getXPos(); i < (int)object.getWidth(); i = i + 30)
	                 {
	                	 g.drawImage(platform,i, y, 30, 20, null);
	                	 if(y<(int)object.getHeight()) {
	                		 y = y +1;
	                	 }
	                 }
	                 g.drawPolygon(xpoints, ypoints, 4);
	            	//
            	}else {
            		g.drawImage(platform,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            	}
            }
        	 if(object instanceof Escalera) { 
             	g.drawRect((int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight());
             	for(int i = 0; i < (int)object.getHeight(); i += 10)
                 {
             			g.drawImage(ladder,(int)object.getXPos(), ((int)object.getYPos()+(int)object.getHeight())-i, 15, 15, null);
                 }
             }
        	 if(object instanceof EscaleraSegmentada) {
             	g.drawRect((int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight());
             	for(int i = 0; i < (int)object.getHeight(); i += 10)
                 {
             		if(i!=40 && i!=30) {
             			g.drawImage(ladderS,(int)object.getXPos(), ((int)object.getYPos()+(int)object.getHeight())-i, 15, 15, null);
             		}
                 }
             }
        	//if(object instanceof Escalera) g.drawImage(ladder,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            //if(object instanceof EscaleraSegmentada) g.drawImage(ladderS,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            if(object instanceof Peach) {
            	if(peachColor=="Amarillo") g.drawImage(peachA,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            	else if(peachColor=="Verde") g.drawImage(peachV,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            	else g.drawImage(peach,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            }
            if(object instanceof Hongo) g.drawImage(hongo, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            if(object instanceof Martillo) g.drawImage(hammer, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            if(object instanceof Manzana) g.drawImage(apple, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            if(object instanceof Corazon) g.drawImage(heart, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            if(object instanceof Cereza) g.drawImage(cherry, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            if(object instanceof Soga) g.drawImage(rope, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            
        }
        
        //dibujar el puntaje arriba de la pantalla
        g.setColor(Color.WHITE);
        g.drawString("Score: " + model.getScore(), 300, 50); 
        
        g.setColor(Color.WHITE);
        g.drawString("Score: " + model.getScore(), 500, 50); 
        
        for (ObjetoMovimiento object : model.getMOList()) //pintar cada objeto en movimiento, barriles y mario
        {
            if(object instanceof Barril) g.drawImage(barrel,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            if(object instanceof BarrilAzul) g.drawImage(barrel1,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            if(object instanceof BarrilVerde) g.drawImage(barrel2,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            if(object instanceof BarrilRojo) g.drawImage(barrel3,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            if(object instanceof Mario) {
            	if(personajeColor=="Amarillo")g.drawImage(marioA, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            	else if(personajeColor=="Azul")g.drawImage(marioB, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            	else if(personajeColor=="Morado")g.drawImage(marioM, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            	else if(personajeColor=="Verde")g.drawImage(luigi, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            	else g.drawImage(mario, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            }
            if(object instanceof Luigi) {
            	if(personajeColor=="Amarillo")g.drawImage(marioA, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            	else if(personajeColor=="Azul")g.drawImage(marioB, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            	else if(personajeColor=="Morado")g.drawImage(marioM, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            	else if(personajeColor=="Rojo")g.drawImage(mario, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            	else g.drawImage(luigi, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            }
            if(object instanceof JugadorMimo) { 
            	if(personajeColor2=="Amarillo")g.drawImage(marioA, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            	else if(personajeColor2=="Azul")g.drawImage(marioB, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            	else if(personajeColor2=="Morado")g.drawImage(marioM, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            	else if(personajeColor2=="Rojo")g.drawImage(mario, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            	else g.drawImage(luigi, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            	//g.drawImage(luigi, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            }
            
        } 	
    }

    public void update(Observable caller, Object data)
    {
        repaint();
    }
}

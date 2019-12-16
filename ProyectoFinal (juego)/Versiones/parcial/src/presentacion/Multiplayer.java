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
import java.io.UnsupportedEncodingException;
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
import presentacion.UnJugador.InputController;

public class Multiplayer extends JPanel implements Observer{
	private PantallaJuego frame;
	public DonkeyPoob logica;
	private InputController inputController = new InputController();
	private ArrayList<Integer> keysDown;
	private Thread hilo;
	private int score = 0;
	private int score2 = 0;
	private int level = 1;
	private int highScore;
	private DonkeyPoob model;
	boolean pausa=false; 
	
	//Imagenes
	Image mario = new ImageIcon(getClass().getResource("/resources/mario.png")).getImage();
	Image marioH = new ImageIcon(getClass().getResource("/resources/mario-hammer-gif.gif")).getImage();
	Image luigi = new ImageIcon(getClass().getResource("/resources/luigi.png")).getImage();
    Image kong = new ImageIcon(getClass().getResource("/resources/konky_dong.gif")).getImage();
    Image peach = new ImageIcon(getClass().getResource("/resources/peach.png")).getImage();
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
    
	public Multiplayer() {
		//colores
		 setOpaque(true);
	     this.setBackground(Color.black);
	     Font thisFont = new Font("Arial", Font.PLAIN, 36);
	
	     //usando un diseño de bolsa de rejilla en el panel con restricciones
	     GridBagLayout gblPanel = new GridBagLayout();
	     this.setLayout(gblPanel);
	     GridBagConstraints c = new GridBagConstraints();
	     c.insets = new Insets(0,0,0,0);
	     
	     try
        {
            PlayGame();
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(UnJugador.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	public Multiplayer(DonkeyPoob model) throws IOException 
    {
        setOpaque(true);
        setBackground(Color.BLACK);
        this.model = model;
        this.model.addObserver(this);
        model = new DonkeyPoob("VS");
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
	@Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g); 
        g.drawImage(kong, 60, 120, 100, 100, this); //dibujar a  kong
        
        for(int i = 0; i<model.life;i++) {
        	g.drawImage(mario,500+i*10,70,10,13, null);
        }
        for(int i = 0; i<model.life2;i++) {
        	g.drawImage(mario,500+i*10,70,10,13, null);
        }
        for (ObjetoEstatico object : model.getSOList()) //pintar cada objeto estatico, escaleras,plataformas y peach
        {
        	if(object instanceof Plataforma) g.drawImage(platform,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            if(object instanceof Escalera) g.drawImage(ladder,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            if(object instanceof EscaleraSegmentada) g.drawImage(ladderS,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            if(object instanceof Peach) g.drawImage(peach,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
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
            if(object instanceof Mario) g.drawImage(mario, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
            if(object instanceof Luigi) g.drawImage(luigi, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
        } 	
    }
	public void PlayGame() throws InterruptedException, IOException
    {
        
        frame = new PantallaJuego(model,false); //crear el frame del modo de juego
        frame.addKeyListener(inputController);
        
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
                            model = new DonkeyPoob("VS");
                            frame = new PantallaJuego(model,false);
                            inputController = new InputController();
                            frame.addKeyListener(inputController);
                        }
                    }
                } catch (InterruptedException e) {
                } catch (IOException ex) {
                    Logger.getLogger(UnJugador.class.getName()).log(Level.SEVERE, null, ex);
                }    
            }
        };
        thread.start(); //iniciamos el hilo
        AbstractAction FPSTimer = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.gamePanel2.repaint(); //volver a pintar el frame				
            }			
        };
        new Timer(15, FPSTimer).start();
    }
	
	public void update(Observable caller, Object data)
	{
        repaint();
    }

}

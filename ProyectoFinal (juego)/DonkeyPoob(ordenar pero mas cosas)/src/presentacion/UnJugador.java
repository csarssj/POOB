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


public class UnJugador extends JPanel implements Observer {
	private DonkeyGUI frame;
	public DonkeyPoob logica;
	private InputController inputController = new InputController();
	private ArrayList<Integer> keysDown;
	private Thread hilo;
	private int score = 0;
	private int level = 1;
	private int highScore;
	private DonkeyPoob model;
	boolean pausa=false; 
	
	//Imagenes
	Image mario = new ImageIcon(getClass().getResource("/resources/mario.png")).getImage();
    Image kong = new ImageIcon(getClass().getResource("/resources/konky_dong.gif")).getImage();
    Image peach = new ImageIcon(getClass().getResource("/resources/peach.png")).getImage();
    Image barrel = new ImageIcon(getClass().getResource("/resources/barrel.png")).getImage();
    Image platform = new ImageIcon(getClass().getResource("/resources/platform.png")).getImage();
    Image ladder = new ImageIcon(getClass().getResource("/resources/ladder.png")).getImage();
	
	public UnJugador() {
	     //colors
		 setOpaque(true);
	     this.setBackground(Color.black);
	     Font thisFont = new Font("Arial", Font.PLAIN, 36);
	
	     //using a grid bag layout in the panel with constraints
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
	    /* //play the game button
	     this.play.addActionListener((ActionEvent e) -> {
	         try
	         {
	             PlayGame();
	         } catch (InterruptedException | IOException ex) {
	             Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
	         }
	     });*/
	}
	public UnJugador(DonkeyPoob model) throws IOException //constructor
    {
        setOpaque(true);
        setBackground(Color.BLACK);
        this.model = model;
        this.model.addObserver(this);
    }	

     class InputController implements KeyListener //class used for getting the keys pressed from the keyboard
     {
     private final boolean[] down = new boolean[255];
     private final boolean[] pressed = new boolean[255];

     @Override
     public void keyPressed(KeyEvent e)
     {
         down[e.getKeyCode()] = true;
         pressed[e.getKeyCode()] = true;
         model.passKeysDownToPlayer(down); //pass the key to the model
     }

     @Override
     public void keyReleased(KeyEvent e)
     {
         down[e.getKeyCode()]= false;
         model.passKeysDownToPlayer(down); //pass the key to the model
     }

     @Override
     public void keyTyped(KeyEvent e)
     {
         // to do - all methods need to be overriden
     }
 
     }
	
	public void PlayGame() throws InterruptedException, IOException
    {
        model = new DonkeyPoob(); //create new game model
        UnJugador tempPan = this;
        frame = new DonkeyGUI(model, tempPan); //create frame from the current model
        frame.addKeyListener(inputController);
        
        //make a thread that controls game model logic
        Thread thread = new Thread(){
            @Override
            public void run()
            {	
                try
                {
                    boolean endGame = false;
                    while (!endGame)
                    {
                        if (!model.startGame(frame))
                        {
                            //the game has ended, get username and score and try to update the high score table
                            frame.dispose();
                            endGame = true;
                        }
                        else
                        {
                            //player wants to play again, create a new controller for keys - if not, mario keeps jumping/running after new game is initialized
                            frame.dispose();
                            model = new DonkeyPoob();
                            frame = new DonkeyGUI(model, tempPan);
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
        thread.start(); //start the thread
        AbstractAction FPSTimer = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.gamePanel.repaint(); //repainting the frame					
            }			
        };
        new Timer(15, FPSTimer).start();
    }
	@Override
    public void paintComponent(Graphics g) //overriden paintComponent function
    {
        super.paintComponent(g); 
        g.drawImage(kong, 60, 120, 100, 100, this); //draw kong
        for (StaticObject object : model.getSOList()) //paint every static object - ladders, platforms, peach
        {
            if(object instanceof Plataforma) g.drawImage(platform,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            if(object instanceof Escalera) g.drawImage(ladder,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            if(object instanceof Peach) g.drawImage(peach,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
        }
        
        //write the score on the top of the screen
        g.setColor(Color.WHITE);
        g.drawString("Score: " + model.getScore(), 500, 50); 

        for (MovingObject object : model.getMOList()) //paint every moving object - mario and barrel
        {
            if(object instanceof Barril) g.drawImage(barrel,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            if(object instanceof Mario) g.drawImage(mario, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
        } 	
    }

    public void update(Observable caller, Object data)
    {
        repaint();
    }
}

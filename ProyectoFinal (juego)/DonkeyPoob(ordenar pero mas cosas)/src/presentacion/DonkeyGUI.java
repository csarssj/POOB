package presentacion;


import javax.swing.*;

import aplicacion.DonkeyPoob;
import resources.*;



import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class DonkeyGUI extends JFrame{
    UnJugador gamePanel; //panel that is used in current frame
    DonkeyPoob gameModel; //current game model that uses this frame
    UnJugador menuPanel; //menu panel 
	private final static int ANCHO = 900;
	private final static int ALTO = 700;
	private JFileChooser fileChooser;
	private JMenu opciones;
	private JMenuBar menu;
	private JMenuItem abrir,guardar,importar2;
	private static JPanel Pmenu,normal,dosJugadores;
	private String color1,color2;
	private boolean isRandom;
	private Clip sonido;
	
	private DonkeyGUI(){
		
		setResizable(false);
		isRandom = false;
		prepareElementos();
		prepareElementosMenu();
		prepareAcciones();
		
	}
	public DonkeyGUI(DonkeyPoob model, UnJugador panel) throws IOException //constructor
    {
		/*Prepare JFrame*/
		this.setTitle("Donkey Poob");
		this.setSize(ANCHO , ALTO);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		addWindowListener(new FrameListener()); 
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	    this.gameModel = model; //get model
	    this.menuPanel = panel; //get panel
	    gamePanel = new UnJugador(model); //create a gamePanel from a gameModel
	    gamePanel.setSize(ANCHO,ALTO);
	    this.add(gamePanel); //add panel to the frame
    }
	public static void main(String[] args) throws IOException, InstantiationException{
		DonkeyGUI gui = new DonkeyGUI();
		/*Prepare JFrame*/
		gui.setTitle("Donkey Poob");
		gui.setSize(ANCHO , ALTO);
		gui.setResizable(false);
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
	}
	private void prepareElementos(){
		
		Pmenu=new MenuInicio();
	//	normal=new UnJugador();
	//	normal.setFocusable(true);
		//dosJugadores = new Multiplayer();
		//pantallaDosJ.setFocusable(true);
		add(Pmenu);
//		add(normal);
		//add(dosJugadores);
		Pmenu.setVisible(true);
	//	normal.setVisible(false);
		//pantallaDosJ.setVisible(false);
		
		setLocationRelativeTo(null);
		fileChooser = new JFileChooser();
		fileChooser.setVisible(false);
		
	}
	private void prepareElementosMenu() {
		menu= new JMenuBar();
		opciones= new JMenu("Opciones");
		abrir= new JMenuItem("abrir");
		guardar= new JMenuItem("guardar");
		importar2= new JMenuItem("importar");
		opciones.add(abrir);
		opciones.add(guardar);
		opciones.add(importar2);

		menu.add(opciones);
		add(menu, BorderLayout.NORTH);
	}
	public static  void unJugador() {
		normal=new UnJugador();
		normal.setFocusable(true);
		
		normal.setVisible(true);
		//Pmenu.setVisible(true);
		//Pmenu.setFocusable(false);
		
	}
	/*
	public static void dosJp() {
		pantallaDosJ.setFocusable(true);
		
		pantallaDosJ.setVisible(true);
		pantallaInicio.setVisible(false);
		pantallaInicio.setFocusable(false);
		
	}*/
	public static  void abrirMenu1() {
		Pmenu.setVisible(true);
		
	}
	private void prepareAcciones() {
		
		abrir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//abrir();
			}
		});
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//guardar();
			}
		});
		importar2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			//	importar();
			}
		});
		
	}
	/*private void prepareAcciones(){
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
                accionSalir();
            }
        });
		play.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				//accionIrAlJuego();
            }
        });
		open.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				//accionOpenFile();
            }
        });
		importar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
			//	accionImportFile();
            }
        });
	}*/
	
	
	/*private void accionOpenFile(){
		fileChooser.setVisible(true);
		fileChooser.setDialogTitle("Abrir");
		int seleccion = fileChooser.showOpenDialog(open);
		try{
			if (seleccion == JFileChooser.APPROVE_OPTION){
				Arkanoid game = ArkapoobDAO.open(fileChooser.getSelectedFile());
				PantallaJuego pantalla = new PantallaJuego(isRandom,game.getPlayersAmount(),game.getPlayers(),"Orange","Green",game.getCpu(),"");
				pantalla.setGame(game);
				pantalla.iniciar();
				pantalla.setVisible(true);
			}
		}
		catch(ArkanoidException e){
			ArkapoobDAO.registre(e);
			JOptionPane.showMessageDialog(fileChooser,e.getMessage());
		}
	}
	private void accionImportFile(){
		fileChooser.setVisible(true);
		fileChooser.setDialogTitle("Importar");
		int seleccion = fileChooser.showOpenDialog(open);
		try{
			if (seleccion == JFileChooser.APPROVE_OPTION){
				Arkanoid game = ArkapoobDAO.importar(fileChooser.getSelectedFile());
				PantallaJuego pantalla = new PantallaJuego(isRandom,game.getPlayersAmount(),game.getPlayers(),"Orange","Green",game.getCpu(),"");
				pantalla.setGame(game);
				pantalla.iniciar();
				pantalla.setVisible(true);
			}
		}
		catch(ArkanoidException e){
			ArkapoobDAO.registre(e);
			JOptionPane.showMessageDialog(fileChooser,e.getMessage());
		}
	}*/
	private void accionSalir(){
		int confirmado = JOptionPane.showConfirmDialog(null, "Seguro que deseas salir","Guardar",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
		if (confirmado == 0){
			// Se cierra el clip.
            sonido.close();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.exit(0);
		}
		else if (confirmado == 1 || confirmado == 2){
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}
	class FrameListener extends WindowAdapter //on window close operation, ask whether it's okay to exit
    {
       public void windowClosing(WindowEvent e)
        {
            gameModel.wait = true; //set gameModel to not update anymore - will be stuck in a loop until wait parameter set otherwise
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Your score is " + gameModel.score + ". Are you sure you want to exit?", "Exit", dialogButton);
            if(dialogResult == 0)
            {
                //yes option, therefore go back to the main menu
                gameModel.username = JOptionPane.showInputDialog("Please input your username:");
                if (gameModel.username.equals("")) gameModel.username = "unknown";
                gameModel.gameOver = true; 
                
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //close the window
            }
            else
            {
                //no option, therefore continue
                gameModel.wait = false; //continue playing the game
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //don't close the frame

            }
        }
    }
    
    public UnJugador getGamePanel() //return function for private gamePanel
    {
        return gamePanel;
    }
		
	
}
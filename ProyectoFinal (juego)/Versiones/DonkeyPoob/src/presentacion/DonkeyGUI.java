package presentacion;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import aplicacion.*;
import persistencia.donkeyDAO;
import resources.*;



import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class DonkeyGUI extends JFrame{
	private JFileChooser fileChooser;
	//private static transient ArkapoobDAO arkaDAO;
	private myButton jugar,abrir,importar,salir;
	private static JPanel Pmenu,normal,VS;
	private Container contenedor;
	private JPanelB panelPantalla;
	private JPanel panelLogo;
	private JPanel panelBotones;
	private JLabel logo;
	private myButton jugarBoton;
	private myButton abrirBoton;
	private myButton importarBoton;
	private transient Clip sonido;
	
	public DonkeyGUI(){
		
		super("Donkey Poob");
		setResizable(false);
		contenedor = getContentPane();
		prepareElementos();
		prepareAcciones();
		
		
	}
	public static void main(String[] args) throws IOException, InstantiationException{
		/*Prepare JFrame*/
		DonkeyGUI gui = new DonkeyGUI();
		gui.setVisible(true);
		
	}
	public void prepareElementos() {
		ajusteFrame();
		prepareElementosPantalla();
	}
	
	public void ajusteFrame(){
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
		int height=700;
		int width=600;
		setSize(width,height);
		int centerX = size.width/2;
		int centerY = size.height/2;
		int halfWidth = width/2;
		int halfHeight = height-340;
		setLocation(centerX - halfWidth, centerY - halfHeight);
	}
	
	public void prepareElementosPantalla(){
		fileChooser=new JFileChooser();
		setIconImage(new ImageIcon(getClass().getResource("/resources/icono.png")).getImage());
		
		panelPantalla = new JPanelB();
		panelPantalla.setLayout(new GridLayout(2,1));
		panelPantalla.setBackground(new ImageIcon(getClass().getResource("/resources/Inicio.png")));
		
		panelLogo = new JPanel();
		panelLogo.setLayout(new GridBagLayout());
		panelPantalla.add(panelLogo);
		panelLogo.setOpaque(false);
		
		panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(3,3,0,5));
		panelPantalla.add(panelBotones);
		panelBotones.setOpaque(false);
		
		preparePanelBotones();
		preparePanelLogo();
		contenedor.add(panelPantalla);
	}
	
	public void prepareAcciones(){
		prepareAccionesVentana();
		prepareAccionesPantalla();
	}
	
	public void prepareAccionesVentana(){
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE );
		addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				accionSalir();
			}
		});
	}
	
	public void prepareAccionesPantalla(){
		jugarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				accionJugar();
			}
		});
		 
		abrirBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				accionAbrir();
			}
		});
		
		importarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				accionImportFile();
			}
		});
	}
	
	public void preparePanelBotones(){
		
		panelBotones.add(new JLabel()); 
		Icon icono = new ImageIcon(getClass().getResource("/resources/jugar.png"));
		jugarBoton = new myButton(icono);
		jugarBoton.setTransparent();
		panelBotones.add(jugarBoton);
		panelBotones.add(new JLabel()); 
		
		panelBotones.add(new JLabel());
		Icon icono1 = new ImageIcon(getClass().getResource("/resources/abrir.png"));
		abrirBoton = new myButton(icono1);
		abrirBoton.setTransparent();
		panelBotones.add(abrirBoton);
		panelBotones.add(new JLabel());
		
		panelBotones.add(new JLabel());
		Icon icono2 = new ImageIcon(getClass().getResource("/resources/importar.png"));
		importarBoton = new myButton(icono2);
		importarBoton.setTransparent();
		panelBotones.add(importarBoton);
		panelBotones.add(new JLabel());
	}
	
	private void preparePanelLogo() {
		logo = new JLabel(new ImageIcon(getClass().getResource("/resources/FUENTE.png")));
		panelLogo.add(logo);		
	}

	private void accionAbrir() {
		fileChooser.setVisible(true);
		fileChooser.setDialogTitle("Abrir");
		int seleccion = fileChooser.showOpenDialog(abrir);
		try{
			if (seleccion == JFileChooser.APPROVE_OPTION){
				DonkeyPoob game = donkeyDAO.open(fileChooser.getSelectedFile());
				PantallaJuego pantalla = new PantallaJuego(game);
				pantalla.setDonkey(game);
				pantalla.playGame();
				try {
					game.pause();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pantalla.setVisible(true);
				
			}
		}
		catch(DonkeyException e){
			//dokeyDAO.registre(e);
			JOptionPane.showMessageDialog(fileChooser,e.getMessage());
		}
	}
	
	private void accionSalir(){
		int confirmado = JOptionPane.showConfirmDialog(null, "Seguro que deseas salir","Guardar",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
		if (confirmado == 0){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.exit(0);
		}
		else if (confirmado == 1 || confirmado == 2){
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}
	public void salga(){
		int option = JOptionPane.showConfirmDialog(null,"Seguro que deseas salir?","Guardar?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(option==0){
			//sonido.close();
			System.exit(0);
		}
	}
	public void accionJugar(){
		seleccionModoGUI modoGui = new seleccionModoGUI();
		modoGui.setVisible(true);
	}
	private void accionImportFile(){
		fileChooser.setVisible(true);
		fileChooser.setDialogTitle("Importar");
		int seleccion = fileChooser.showOpenDialog(abrir);
		try{
			if (seleccion == JFileChooser.APPROVE_OPTION){
				DonkeyPoob game = donkeyDAO.importar(fileChooser.getSelectedFile());
				Painter pantalla = new Painter();
				pantalla.setFocusable(true);
				pantalla.setVisible(true);
				pantalla.setDonkey(game);
				//pantalla.pausa();
				pantalla.setVisible(true);
			}
		}
		catch(DonkeyException e){
			//donkeyDAO.registre(e);
			JOptionPane.showMessageDialog(fileChooser,e.getMessage());
		}
	}

	
	

		
	
}
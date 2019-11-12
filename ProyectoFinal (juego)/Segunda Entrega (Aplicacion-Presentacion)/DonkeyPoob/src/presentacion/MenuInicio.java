package presentacion;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;


public class MenuInicio extends JPanel{
	private JButton Salir;
	private JButton unJugador;
	private JButton VS;
	private JButton CPU;
	private ImageIcon imagen,uno,dos,tres,cuatro;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	protected ImageIcon imagen2 = new ImageIcon(getClass().getResource("/resources/Inicio.png"));
	Icon ic = new ImageIcon(imagen2.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
	/**
	 * Constructor de los objetos de la clase PantallaInicio.
	 */
	public MenuInicio() {
		prepareElementos();
		prepareAcciones();
	}
	/**
	 * Este metodo se encarga de pintar todo los componentes en el panel de la pantalla inicial.
	 */
	public void paint(Graphics g) {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension tam=getSize();
		g.drawImage(imagen2.getImage(), 0,0, tam.width, tam.height,null);        
	    imagen = new ImageIcon(getClass().getResource("/resources/FUENTE.png"));
	    g.drawImage(imagen.getImage(),tam.width/8,tam.height/4-100	,600,200,null);
	    
	    uno = new ImageIcon(getClass().getResource("/resources/jugador.png"));
	    g.drawImage(uno.getImage(),370,345, 100,20,null);
	    
	    dos = new ImageIcon(getClass().getResource("/resources/MULTI.png"));
	    g.drawImage(dos.getImage(),370,385, 100,20,null);
	    
	    tres = new ImageIcon(getClass().getResource("/resources/SALIR.png"));
	    g.drawImage(tres.getImage(),370,425, 100,20,null);
	    
		this.setOpaque(false);
		super.paint(g);	
	}
	/**
	 * Prepara todos los elementos que vamos a usar.
	 */
	public void prepareElementos() {
		prepareMenu();
		prepareVentana();
	}
	
	private void prepareVentana() {
		
	}
	/**
	 * Prepara todos los oyentes que vamos a capturar de los eventos que estamos creando.
	 */
	private void prepareAcciones(){
		unJugador.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
            	DonkeyGUI.unJugador();
            }
        });
		VS.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
            	//DonkeyGUI.dosJp();
            }
        });
		
		Salir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                confirmarSalida();
            }
        });
		
	}
	/**
	 * Pregunta si quiere salir o no de la aplicación.
	 */
	private void confirmarSalida(){
		int valor=JOptionPane.showConfirmDialog(this,"Esta seguro de cerrar la aplicacion","Advertencia",JOptionPane.YES_NO_OPTION);
		if(valor==JOptionPane.YES_OPTION){
			System.exit(0);
			}
	}
	/**
	 * Para cualquier boton solo dejara vivible la imagen de fondo o en su defecto el texto.
	 * @param boton,El boton al que queremos aplicarle esta propiedad.
	 */
	private void botonInvisible(JButton boton) {
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		
	}
	/**
	 * Prepara todos los elementos del panel.
	 */
	public void prepareMenu() {
		setLayout(null);
		setBounds(0,0,800,700);
		Salir=new JButton();
		unJugador=new JButton();
		VS=new JButton();
		CPU=new JButton();
		Salir.setBounds(new Rectangle(370,425, 100,20));
		unJugador.setBounds(new Rectangle(370,365, 100,20));
		VS.setBounds(new Rectangle(350,385, 150,20));
		CPU.setBounds(new Rectangle(350,405, 150,20));
		Salir.setForeground(Color.WHITE);
		Salir.setBackground(Color.BLACK);
		VS.setForeground(Color.WHITE);
		VS.setBackground(Color.BLACK);
		CPU.setForeground(Color.WHITE);
		CPU.setBackground(Color.BLACK);
		unJugador.setForeground(Color.WHITE);
		unJugador.setBackground(Color.BLACK);
		botonInvisible(Salir);
		botonInvisible(unJugador);
		botonInvisible(VS);
		botonInvisible(CPU);
		add(unJugador);
		add(Salir);
		add(VS);
		add(CPU);

	}
	
	
}


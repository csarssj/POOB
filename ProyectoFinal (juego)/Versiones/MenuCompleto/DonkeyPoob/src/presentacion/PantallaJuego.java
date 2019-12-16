package presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


import aplicacion.DonkeyException;
import aplicacion.DonkeyPoob;
import persistencia.donkeyDAO;

public class PantallaJuego extends JFrame{
	Painter gamePanel; //panel que se usa en frame actual
    DonkeyPoob gameModel; //modo de juego actual que usa este frame
    Painter menuPanel;
	private JFileChooser fileChooser;
	private JMenu opciones;
	private JMenuBar menu;
	private JMenuItem exportar,salvar;
	private static JPanel Pmenu,normal,VS;
	
	public PantallaJuego(DonkeyPoob model,Painter gamePanel) //constructor
    {
		/*Prepare JFrame*/
		this.setTitle("Donkey Poob");
		this.setResizable(false);
		this.setVisible(true);
		prepareElementos();
		prepareAcciones();
		addWindowListener(new FrameListener());
		
	    this.gameModel = model; //get model
	    //gamePanel = new Painter(model); //crear un panel de juego a partir de un modo de juego
	    this.gamePanel = gamePanel;
	    this.add(gamePanel); //agregar panel al frame
    }
	
	public void prepareElementos() {
		setIconImage(new ImageIcon(getClass().getResource("/resources/icono.png")).getImage());
		fileChooser=new JFileChooser();
		ajusteFrame();
		prepareElementosMenu();
	}
	private void prepareElementosMenu() {
		menu= new JMenuBar();
		opciones= new JMenu("Opciones");
		exportar= new JMenuItem("Exportar");
		salvar= new JMenuItem("salvar");
		opciones.add(exportar);
		opciones.add(salvar);

		menu.add(opciones);
		add(menu, BorderLayout.NORTH);
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
	private void prepareAcciones() {
		
		exportar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//accionAbrir();
			}
		});
		salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				accionSalvar();
			}
		});
				
	}

	private void accionSalvar(){
		if(gameModel.wait) {
			fileChooser.setVisible(true);
			fileChooser.setDialogTitle("Guardar");
	    	fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo DAT","dat"));
	    	int confirmado = fileChooser.showSaveDialog(this);
	    	try {
	    		if(confirmado==fileChooser.APPROVE_OPTION) {
	    			gameModel.save(fileChooser.getSelectedFile());
	    			try {
						gameModel.pause();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    	}
	    	catch(DonkeyException e) {
	    		JOptionPane.showMessageDialog(this, e.getMessage());
	    	}
		}else {
			try {
				gameModel.pause();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	class FrameListener extends WindowAdapter // pregunte si está bien salir
    {
       public void windowClosing(WindowEvent e)
        {
            gameModel.wait = true; //configura gameModel para que no se actualice más: se bloqueará en un bucle hasta que se establezca el parámetro de espera
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Tu puntaje es: " + gameModel.score + ". ¿Estas seguro de salir?", "Salir", dialogButton);
            if(dialogResult == 0)
            {
                //opción sí, por lo tanto, regrese al menú principal
                gameModel.gameOver = true; 
                
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //cerrar la ventana
            }
            else
            {
                //opcion no, por lo tanto continua 
                gameModel.wait = false; //continua jugando
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //no cierre el frame

            }
        }
    }
    
    public Painter getGamePanel() 
    {
        return gamePanel;
    }
    public void setDonkey(DonkeyPoob game) {
    	gamePanel.setDonkey(game);
    }
    public void playGame() {
    	try {
			gamePanel.PlayGame();
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

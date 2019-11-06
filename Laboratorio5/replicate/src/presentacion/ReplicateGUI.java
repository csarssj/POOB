package presentacion;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.Toolkit;
import java.awt.Dimension;


public class ReplicateGUI extends JFrame{
    private JMenu opciones;
    private JMenuBar elementos;
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem salvar;
    private JMenuItem salvarComo;
    private JMenuItem cambiarColor;
    private JMenuItem salir;
    private JFileChooser fileChooser;
    private JColorChooser colorChooser;
    private JPanel juego,b,houses,tablero,panelSuperior,panelInferior;;
    private JLabel cpu,player,titulo,jugadas; 
    private Color colorBolas = Color.WHITE;
    
    public  ReplicateGUI(){
        prepareElementos();
        prepareAcciones();
    }
    public static void main(String[] args){
        ReplicateGUI gui = new ReplicateGUI();
        gui.setVisible(true);
    }
    private void prepareElementos(){
        /*Prepare JFrame*/
        setTitle("Replicate");
        setTam();
        setPosicion();
        prepareElementosMenu();
        prepareElementosTablero();
        fileChooser = new JFileChooser();
        fileChooser.setVisible(false);
        colorChooser = new JColorChooser();
        colorChooser.setVisible(false);
    }
    private void prepareElementosMenu(){
        elementos = new JMenuBar();
        setJMenuBar(elementos);
        opciones = new JMenu("Opciones");
        nuevo= new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        salvar = new JMenuItem("Salvar");
        salvarComo = new JMenuItem("Salvar como");
        cambiarColor = new JMenuItem("Cambiar Color");
        salir = new JMenuItem("Salir");
        elementos.add(opciones);
        opciones.add(nuevo);
        opciones.add(abrir);
        opciones.add(salvar);
        opciones.add(salvarComo);
        opciones.add(cambiarColor);
        opciones.add(salir);
    }
    private void prepareElementosTablero(){
        grid();
        revalidate();
    }
    private void prepareAcciones(){
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
                salga();
            }
        });
        salir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                salga();
            }
        });
        abrir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                accionOpenFile();
            }
        });
        salvar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                accionSaveFile();
            }
        });
        salvarComo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                accionSaveFile();
            }
        });
        cambiarColor.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
			cambiarColores();
            }
        });
    }
    private void setTam(){
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho =(screen.width-getSize().width)/2;
        int largo =(screen.height-getSize().height)/2;
        setSize(ancho,largo);
    }
    private void setPosicion(){
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int xP =(screen.width-getSize().width)/2;
        int xY =(screen.height-getSize().height)/2;
        setLocation(xP,xY);
    }
    private void salga(){
        int resp = JOptionPane.showConfirmDialog(null, "Esta seguro?", "Salir?",JOptionPane.YES_NO_OPTION);
        if(resp==JOptionPane.YES_OPTION){
            setDefaultCloseOperation(EXIT_ON_CLOSE);                
            System.exit(0);     
        }
        else{
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }   
    }
    private void accionOpenFile(){
        fileChooser.setVisible(true);
        int seleccion = fileChooser.showOpenDialog(abrir);
        if (seleccion == JFileChooser.APPROVE_OPTION){
            File fichero = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(abrir,"El archivo "+fichero+" no se puede abrir porque las funcionalidades estan en construccion ");
        }
    }
    private void accionSaveFile(){
        fileChooser.setVisible(true);
        int seleccion = fileChooser.showSaveDialog(salvar);
        if (seleccion == JFileChooser.APPROVE_OPTION){
            File fichero = fileChooser.getSelectedFile();
             JOptionPane.showMessageDialog(salvar,"El archivo en la ruta "+fichero+" no se puede guardar porque las funcionalidades estan en construccion ");
        }
    }
    private void cambiarColores(){
	JColorChooser chooser = new JColorChooser();
	Color temp = chooser.showDialog(null,"Cambiar Color",colorBolas);
	if(temp!=null){
		colorBolas = temp;
		b.setVisible(false);
		b.removeAll();
		prepareElementosTablero();
		b.setVisible(true);
	}
    }
    public void grid(){
        b = new JPanel();
        b.setLayout(new BorderLayout());
        add(new Button(),BorderLayout.NORTH);
        add(new Button(),BorderLayout.SOUTH);
        add(new Button(),BorderLayout.EAST);
        add(new Button(),BorderLayout.WEST);
        b.setLayout(new GridLayout(10,10,10,10));
        for (int row=0; row < 10; row++)
        {
            for (int col=0; col < 10; col++)
            {
                int value = 2;
                Ficha canica= new Ficha(colorBolas,true,value,row,col); 
                b.add(canica,BorderLayout.CENTER);  
            }
        }
        this.add(b,BorderLayout.CENTER);
    }
}
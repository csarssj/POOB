package presentacion;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.*;
import java.awt.event.*;
import java.lang.Thread;
import java.io.*;

import aplicacion.*;


public class TeatroColonGUI extends JFrame{
    
    private Teatro teatro=null;
    
    private JFileChooser fileChooser;
    private JColorChooser colorChooser;
    private JPanel botones;
    private JScrollPane contenedor;
    private JButton botonAccion;
    private JButton botonCorten;
    private JButton botonDecision;  
    private JMenu opciones;
	private JMenuBar menu;
	private JMenuItem abra,salve,importe,exporte,salir,iniciar;
   
    private FotoTeatro foto;
    
    
    private TeatroColonGUI() {
        super("Teatro Colón");
        try {
            teatro=Teatro.demeTeatro();     
            teatro.algunosEnEscena();
            elementos();
            acciones();
            prepareElementosMenu();
    		prepareAcciones();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void elementos() throws Exception {
    	
    	fileChooser = new JFileChooser();
    	fileChooser.setVisible(false);
        setLayout(new BorderLayout());    
        contenedor = new JScrollPane();
        
        foto= new FotoTeatro();
        contenedor.getViewport().add(foto);
        
        botones=new JPanel(new GridLayout(1,3));
        botonAccion=new JButton("Actuen");
        botonCorten=new JButton("Corten");
        botonDecision=new JButton("Decidan"); 
        botones.add(botonAccion);
        botones.add(botonCorten);
        botones.add(botonDecision); 
        
        add(contenedor,BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);
        
        pack();
        setSize(Teatro.MAXIMO+100,Teatro.MAXIMO+135);

        setResizable(false);
    }
    private void prepareElementosMenu() {
		menu= new JMenuBar();
		opciones= new JMenu("Opciones");
		iniciar= new JMenuItem("Iniciar");
		abra= new JMenuItem("Abra");
		exporte= new JMenuItem("Exporte");
		importe= new JMenuItem("Importe");
		salve= new JMenuItem("Salve");
		salir= new JMenuItem("Salir");
		opciones.add(iniciar);
		opciones.add(abra);
		opciones.add(salve);
		opciones.add(importe); 
		opciones.add(exporte);
		opciones.add(salir);

		menu.add(opciones);
		add(menu, BorderLayout.NORTH);
	}
	private void prepareAcciones()  {
			iniciar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					opcionIniciar();
				}
			});
			abra.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					opcionAbrir();
				}
			});
			salve.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					opcionSalvar();
				}
			});
			importe.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					opcionImportar();
				}
			});
			exporte.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					opcionExportar();
				}	
			});
			salir.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					opcionSalir();
				}
			});
			
		}
	public void opcionIniciar() {
		
		teatro.iniciarDeNuevo();
        actualice();
		
	}
	public void opcionSalir() {
        dispose();
        System.exit(0);
		
	}
    private void acciones(){
        ActionListener oyenteBotonAccion=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                accion();
            }   
        };  
        botonAccion.addActionListener(oyenteBotonAccion);
        
        ActionListener oyenteBotonCorten=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                corten();
            }   
        };  
        botonCorten.addActionListener(oyenteBotonCorten);
        
        ActionListener oyenteBotonDecision=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                decidan();
            }   
        }; 
        
        botonDecision.addActionListener(oyenteBotonDecision);
        
        WindowListener w = new WindowAdapter() { 
            public void windowClosing(WindowEvent e) {
                salir();
            }
        };  
        
        this.addWindowListener(w);
        
    }   
    
    
    
    private void accion(){
         teatro.accion();
         actualice();
    }
    
    
    private void corten(){       
        teatro.corten();
        actualice();
    }       
    
    private void decidan(){       
        teatro.decidan();
        actualice();
    }   
    
    private void actualice(){
        foto.actualice();
    }
    
    
    
    private void salir(){
        dispose();
        System.exit(0);
    }   
    
  
	private void opcionSalvar(){
		fileChooser.setVisible(true);
		fileChooser.setDialogTitle("Guardar");
    	fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo DAT","dat"));
    	int confirmado = fileChooser.showSaveDialog(this);
    	try {
    		if(confirmado==fileChooser.APPROVE_OPTION) {
    			teatro.salve(fileChooser.getSelectedFile());
    		}
    	}
    	catch(TeatroColonException e) {
    		JOptionPane.showMessageDialog(this, e.getMessage());
    	}
	}
	
	private void opcionAbrir(){
		fileChooser.setVisible(true);
		fileChooser.setDialogTitle("Abrir");
    	fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo DAT","dat"));
    	int confirmado = fileChooser.showSaveDialog(this);
    	try {
    		if(confirmado==fileChooser.APPROVE_OPTION) {
    			Teatro teatro2 = teatro.abrir(fileChooser.getSelectedFile());
    			teatro.cambieTeatro(teatro2);
				foto.actualice();
    		}
    	}
    	catch(TeatroColonException e) {
    		JOptionPane.showMessageDialog(this, e.getMessage());
    	}
	}
	
	private void opcionExportar(){
		fileChooser.setVisible(true);
		fileChooser.setDialogTitle("Exportar");
    	fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo TXT","txt"));
		int confirmado = fileChooser.showSaveDialog(this);
    	try {
			if(confirmado==fileChooser.APPROVE_OPTION) {
				teatro.exporte(fileChooser.getSelectedFile());
			}
    	}
    	catch(TeatroColonException e) {
    		JOptionPane.showMessageDialog(this, e.getMessage());
    	}
	}
	private void opcionImportar(){
		fileChooser.setVisible(true);
		fileChooser.setDialogTitle("Importar");
    	fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo TXT","txt"));
		int confirmado = fileChooser.showSaveDialog(this);
    	try {
			if(confirmado==fileChooser.APPROVE_OPTION) {
				Teatro teatro2 = teatro.importe(fileChooser.getSelectedFile());
				teatro.cambieTeatro(teatro2);
				foto.actualice();
			}
    	}
    	catch(TeatroColonException e) {
    		JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
    public static void main(String[] args) {
        TeatroColonGUI gui=new TeatroColonGUI();
        gui.setVisible(true);
    }   
    
    class FotoTeatro extends JComponent {
        private int x,y;
        
        private static final int MAX=Teatro.MAXIMO;
        
        
        public void actualice(){
            teatro=Teatro.demeTeatro();
            repaint();
        }
        
        public void paintComponent(Graphics g){
            g.setFont(new Font("TimesRoman", Font.PLAIN, 8)); 
            
            for (int i=1; i<=teatro.numeroEnEscena(); i++) {
                
                EnEscena e=teatro.demeEnEscena(i);
                int x=e.getPosicionX();
                int y=MAX-e.getPosicionY();  
                
                g.setColor(e.getColor()); 
                g.drawString(e.mensaje(),x+20,y+10);   
                
                if (e.forma().equals("Persona")){
                    humano(g,(Persona)e,x,y);
                } else  if (e.forma().equals("Circulo")){
                    g.fillOval(x+10,y+0,20,20);
                } else  if (e.forma().equals("CortinaIzquierda")){
                    
                    g.fillRect(0,0,e.getPosicionX(),600);
                    
                    
                } 
                else  if (e.forma().equals("CortinaDerecha")){
                    
                    g.fillRect(e.getPosicionY(),0,600,600);
                    
                    
                } 
                else{
                	int[] xxx = {e.getPosicionX(),e.getPosicionX1(),e.getPosicionX2()};
                	int[] yyy = {e.getPosicionY(),e.getPosicionY1(),e.getPosicionY2()};
                    g.fillPolygon(xxx,yyy,3);
                }
            }
            super.paintComponent(g);
        }
        
        
        public void humano(Graphics g, Persona e,int x, int y){
            int pos;
            g.setColor(Color.PINK);
            g.fillOval(x+10,y+0,10,10);/*cabeza*/
            g.setColor(e.getColor()); 
            g.drawLine(x+10+5,y+10,x+10+5,y+10+20);
            
            pos=e.getPosicionBrazo('I');
            if (pos==Persona.ARRIBA){
                g.drawLine(x+10+5,y+10+5,x+10+15,y+10);/*brazo izq arriba*/
            } else if (pos==Persona.FRENTE){
                g.drawLine(x+10+5,y+10+5,x+10+15,y+10+5);/*brazo izq al frente*/
            } else {
                g.drawLine(x+10+5,y+10+5,x+10+15,y+10+10);/*brazo izq abajo*/
            }
            
            pos=e.getPosicionBrazo('D');
            if (pos==Persona.ARRIBA){
                g.drawLine(x+10+5,y+10+5,x+5,y+10);/*brazo der arriba*/
            } else if  (pos==Persona.FRENTE){
                g.drawLine(x+10+5,y+10+5,x+5,y+10+5);/*brazo der al frente*/
            } else{
                g.drawLine(x+10+5,y+10+5,x+5,y+10+10);/*brazo der abajo*/
            }
            
            g.drawLine(x+10+5,(y+15)+10+5,x+10+15,(y+15)+10+15);
            g.drawLine(x+10+5,(y+15)+10+5,x+5,(y+15)+10+15);
            
           pos=e.getPosicionPierna('D');
            if (pos==Persona.ARRIBA){
                g.drawLine(x+5,(y+15)+10+15,x+5+10,(y+15)+10+15);/*pierna der arriba*/
            } else if  (pos==Persona.FRENTE){
                g.drawLine(x+5,(y+15)+10+15,x+5-10,(y+15)+10+15+5);/*pierna der al frente*/
            } else{
                g.drawLine(x+5,(y+15)+10+15,x+5,(y+15)+10+15+10);/*pierna der abajo*/
            }
            
            pos=e.getPosicionPierna('I');
            if (pos==Persona.ARRIBA){
                g.drawLine(x+10+15,(y+15)+10+15,x+10+15-10,(y+15)+10+15);/*pierna izq arriba*/
            } else if  (pos==Persona.FRENTE){
                g.drawLine(x+10+15,(y+15)+10+15,x+10+15+10,(y+15)+10+15+5);/*pierna izq al frente*/
            }else {
                g.drawLine(x+10+15,(y+15)+10+15,x+10+15,(y+15)+10+15+10);/*piernaizqabajo*/
            }
        }
    }
}






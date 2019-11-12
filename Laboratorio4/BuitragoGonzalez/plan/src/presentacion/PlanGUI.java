package presentacion; 
  

import aplicacion.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * @version ECI 2019-02
 */
public class PlanGUI extends JFrame{

    private static final int ANCHO_PREFERIDO = 450;
    private static final int ALTO_PREFERIDO= 450;
    private static final Dimension DIMENSION_PREFERIDA =
                         new Dimension(ANCHO_PREFERIDO,ALTO_PREFERIDO);

    private Plan coleccion;

    /*Panel botonListar*/
    private JButton botonListar;
    private JButton botonReiniciarListar;
    private JTextArea textoInformacion;
    
    /*Panel botonAdicionar*/
    private JTextField textoSigla;   
    private JTextField textoCreditos;
    private JTextArea textoDescripcion;

    
    private JButton botonAdicionar;
    private JButton botonReiniciarAdicionar;
    
    /*Panel buscar*/
    private JTextField busquedaTexto;
    private JTextArea resultadosTexto;
    

    
    
    private PlanGUI() throws ActividadExcepcion{
        coleccion=new Plan();
        coleccion.adicione();
        prepareElementos();
        prepareAcciones();
    }


    private void prepareElementos(){
        setTitle("Plan 14. Ingenieria de Sistemas.");
        textoSigla = new JTextField(50);
        textoCreditos = new JTextField(50);
        textoDescripcion = new JTextArea(10, 50);
        textoDescripcion.setLineWrap(true);
        textoDescripcion.setWrapStyleWord(true);
        JTabbedPane etiquetas = new JTabbedPane();
        etiquetas.add("Listar",   prepareListar());
        etiquetas.add("Adicionar",  prepareAdicionar());
        etiquetas.add("Buscar", prepareBuscar());
        getContentPane().add(etiquetas);
        setSize(DIMENSION_PREFERIDA);
        
    }


    /**
     * Prepara el panel para listar
     * @return
     */
    private JPanel prepareListar(){

        textoInformacion = new JTextArea(10, 50);
        textoInformacion.setEditable(false);
        textoInformacion.setLineWrap(true);
        textoInformacion.setWrapStyleWord(true);
        JScrollPane scrollArea =
                new JScrollPane(textoInformacion,
                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                                
        JPanel  botones = new JPanel();
        botonListar = new JButton("Listar");
        botonReiniciarListar = new JButton("Limpiar");
        botones.add(botonListar);
        botones.add(botonReiniciarListar);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollArea, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);

        return panel;
     }
     
    /**
     * Prepara el area de adición
     * @return El area de adición
     */
    private JPanel prepareAdicionar(){
        
        Box textoSiglaArea = Box.createHorizontalBox();
        textoSiglaArea.add(new JLabel("Sigla", JLabel.LEFT));
        textoSiglaArea.add(Box.createGlue());
        Box siglaArea = Box.createVerticalBox();
        siglaArea.add(textoSiglaArea);
        siglaArea.add(textoSigla);

        Box textoCreditosArea = Box.createHorizontalBox();
        textoCreditosArea.add(new JLabel("Creditos", JLabel.LEFT));
        textoCreditosArea.add(Box.createGlue());
        Box creditosArea = Box.createVerticalBox();
        creditosArea.add(textoCreditosArea);
        creditosArea.add(textoCreditos);
        
        Box textoDescripcionArea = Box.createHorizontalBox();
        textoDescripcionArea.add(new JLabel("Descripcion", JLabel.LEFT));
        textoDescripcionArea.add(Box.createGlue());
        Box descripcionArea = Box.createVerticalBox();
        descripcionArea.add(textoDescripcionArea);
        descripcionArea.add(textoDescripcion);

       
        Box singleLineFields = Box.createVerticalBox();
        singleLineFields.add(siglaArea);
        singleLineFields.add(creditosArea);

        JPanel textoInformacionPanel = new JPanel();
        textoInformacionPanel.setLayout(new BorderLayout());
        textoInformacionPanel.add(singleLineFields, BorderLayout.NORTH);
        textoInformacionPanel.add(descripcionArea, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botonAdicionar = new JButton("Adicionar");
        botonReiniciarAdicionar = new JButton("Limpiar");

        botones.add(botonAdicionar);
        botones.add(botonReiniciarAdicionar);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(textoInformacionPanel, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);
        return panel;
    }

    



   /**
     * Prepara el area de liatar
     * @return El panel para buscar coleccions
     */
    private JPanel prepareBuscar(){

        Box busquedaEtiquetaArea = Box.createHorizontalBox();
        busquedaEtiquetaArea.add(new JLabel("Buscar", JLabel.LEFT));
        busquedaEtiquetaArea.add(Box.createGlue());
        busquedaTexto = new JTextField(50);
        Box busquedaArea = Box.createHorizontalBox();
        busquedaArea.add(busquedaEtiquetaArea);
        busquedaArea.add(busquedaTexto);
        
        resultadosTexto = new JTextArea(10,50);
        resultadosTexto.setEditable(false);
        resultadosTexto.setLineWrap(true);
        resultadosTexto.setWrapStyleWord(true);
        JScrollPane scrollArea = new JScrollPane(resultadosTexto,
                                     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                     JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel botonListarArea = new JPanel();
        botonListarArea.setLayout(new BorderLayout());
        botonListarArea.add(busquedaArea, BorderLayout.NORTH);
        botonListarArea.add(scrollArea, BorderLayout.CENTER);

        return botonListarArea;
    }


    private void prepareAcciones(){
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
                setVisible(false);
                System.exit(0);
            }
        });
        
        /*Listar*/
        botonListar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                accionListar();
            }
        });

        botonReiniciarListar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                textoInformacion.setText("");
            }
        });
        
        /*Adicionar*/
        botonAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                accionAdicionar();                    
            }
        });
        
        botonReiniciarAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                textoSigla.setText("");
                textoCreditos.setText("");
                textoDescripcion.setText("");
            }
        });
        
        /*Buscarr*/
        busquedaTexto.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent ev){
                accionBuscar();

            }
           
            public void insertUpdate(DocumentEvent ev){
                accionBuscar();
            }
            
            public void removeUpdate(DocumentEvent ev){
                accionBuscar();
            }
            

        });
    }    

    
    private void accionListar(){
        textoInformacion.setText(coleccion.toString());
    }
    
    
    private void  accionAdicionar(){
        try {
    		  coleccion.adicione(textoSigla.getText(),textoCreditos.getText(), textoDescripcion.getText());
    	}catch(ActividadExcepcion e) {
    		JPanel panel = new JPanel();
    		panel.add(new JLabel(e.getMessage()));
    		JOptionPane.showConfirmDialog(null, panel, "ERROR",JOptionPane.DEFAULT_OPTION);
    	}
       
    }

    private void accionBuscar(){
        String patronBusqueda=busquedaTexto.getText();
        StringBuffer buffer = new StringBuffer();
        if(patronBusqueda.length() > 0) {
            ArrayList <Actividad> results = coleccion.busque(patronBusqueda);
            for(int i = 0; i < results.size(); i++) {
                buffer.append(results.get(i).toString());
                buffer.append('\n');
                buffer.append('\n');
             }
        }
        resultadosTexto.setText(buffer.toString());
    } 
    
   public static void main(String args[]) throws ActividadExcepcion{
       PlanGUI gui=new PlanGUI();
       gui.setVisible(true);
   }    
}
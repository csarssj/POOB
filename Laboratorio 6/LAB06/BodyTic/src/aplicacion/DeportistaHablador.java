package aplicacion;

import java.util.*;
import java.awt.Color;
/**
 * DeportistaHablador clase que hereda de Deportista.
 *
 * @author (Jimenez Eduard - Murillo Carlos)
 * @version (1.0)
 */
public class DeportistaHablador extends Deportista
{
    private int x;
    private ArrayList<EnSalon> elemento ;
    /**
     * Metodo Constructor de DeportistaHablador
	 * @param salon indica el salon al cual pertenece
	 * @param nombre es el nombre del deportista
	 * @param posicionx es la posicion en el eje x
	 * @param posiciony es la posicion en el eje y
     */
    public DeportistaHablador(Salon salon,String nombre,int posicionx, int posiciony){
        super(salon,nombre, posicionx, posiciony);
        super.setColor(Color.ORANGE);
        
        
    }
    
    /**
     * Metodo que permite que el deportistaHablador inicie sus movimientos.
     */
    @Override 
    public void inicie(){
        palabras="";
        paso++;
        if (getPosicionBrazo('I')==ABAJO && getPosicionBrazo('D')==ABAJO){
            muevaBrazo('I','S');
            muevaBrazo('I','S');
            muevaBrazo('D','S');
            muevaBrazo('D','S');
            muevaPierna('I','S');
            muevaPierna('D','S');
            palabras="Vamos!";
        } else if  (getPosicionBrazo('I')==FRENTE && getPosicionBrazo('D')==FRENTE){
            muevaBrazo('I','S'); 
            muevaBrazo('D','S');
            muevaPierna('I','S');
            muevaPierna('D','S');
            palabras="Vamos!";
        } else if (getPosicionBrazo('I')==ARRIBA && getPosicionBrazo('D')==ARRIBA){
            muevaBrazo('I','B'); 
            muevaBrazo('D','B');
            muevaPierna('I','B');
            muevaPierna('D','B');
            palabras="¡Uff Realizar esto es muy agotador!";
        }
        
        if (super.puedeMover('E')){
            muevase('E');
        }
    
        
    }

}
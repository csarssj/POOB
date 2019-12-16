package aplicacion;

import java.awt.Color;
import java.io.*;
/**
 * Deportista es la clase que hereda de persona y puede realizar acciones en el salon. 
 */
public class Deportista extends Persona implements EnSalon,Serializable{
    

    private Salon salon;   
    protected String palabras;
    protected int paso;
    
    /**
     * Metodo constructor
	 * @param salon indica el salon al cual pertenece
	 * @param nombre es el nombre del deportista
	 * @param posicionx es la posicion en el eje x
	 * @param posiciony es la posicion en el eje y
     */
    public Deportista(Salon salon,String nombre,int posicionx, int posiciony){
        super(nombre,posicionx,posiciony);
        this.salon=salon;
        color=Color.BLACK;
        palabras="Soy"+nombre;
        salon.adicione(this);
        paso=0;
    }
    
    
    public void setColor(Color color){
        this.color = color;
    }
    
    
    /**
     * Verifica si puede moverse un deportista dada la dirección. 
     * @param direccion la dirección a la que se quiere mover puede ser 'N','E','S','O'.
     * @return true si puede false dlc.
     */
    protected boolean puedeMover(char direccion) {
        boolean puede=false;
        int posX = getPosicionX();
        int posY = getPosicionY();
        switch(direccion){
            case 'N' : puede = (posY+1 < salon.MAXIMO);
            break;
            case 'E' : puede = (posX+1 < salon.MAXIMO);
            break;
            case 'S' : puede = (posY-1 >= 0);
            break;
            case 'O': puede = (posX-1 >= 0);
            break; 
        } 
        return puede;
    }
    
    /**
     * Para el movimiento de los deportistas.
     */
    public void pare(){
        muevaBrazo('I','B'); 
        muevaPierna('I','P');
        muevaBrazo('D','B'); 
        muevaPierna('D','P');       
        palabras="¡Uff!";
    }
    
    /**
     * Comienza el movimiento de los deportistas. 
     */
    public void inicie(){
        palabras="";
        paso++;
        if (getPosicionBrazo('I')==ABAJO && getPosicionBrazo('D')==ABAJO){
            muevaBrazo('I','S'); 
            muevaPierna('I','S');
        } else if  (getPosicionBrazo('I')==FRENTE){
            muevaBrazo('I','S'); 
            muevaPierna('I','S');
        } else if (getPosicionBrazo('I')==ARRIBA){
            muevaBrazo('I','B'); 
            muevaPierna('I','B');
            muevaBrazo('I','B'); 
            muevaPierna('I','B');
            muevaBrazo('D','S'); 
            muevaPierna('D','S');
        }else if (getPosicionBrazo('D')==FRENTE){
            muevaBrazo('D','S'); 
            muevaPierna('D','S');
            muevaBrazo('D','S'); 
            muevaPierna('D','S');
            muevaBrazo('I','B'); 
            muevaPierna('I','B');
        }else if (getPosicionBrazo('D')==ARRIBA){
            muevaBrazo('D','B'); 
            muevaPierna('D','B');
            muevaBrazo('D','B'); 
            muevaPierna('D','B');
            muevaBrazo('I','S'); 
            muevaPierna('I','S');
        }       
        char direccion=( (paso % 2 == 0)  ? 'E':'O');
        if (puedeMover(direccion)){
            muevase(direccion);
        }
    }

    /**
     * Retorna el tipo de forma que tiene un deportista.
     * @return  el tipo de forma que es, en este caso, persona. 
     */
    public String forma(){
        return EnSalon.FORMAS[0];
    }
    
    /**
     * Retorna un mensaje diciendo las palabras que tenga asociadas un deportista.
     * @return mensaje el mensaje que va a decir. 
     */
    public String mensaje(){
        return  palabras;
    }

}


package aplicacion;


import java.awt.Color;
/**
 * Clase actor que es extendiende a persona
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 3/10/2019
 */
public class Actor extends Persona implements EnEscena{
    

    private Teatro teatro;   
    protected String palabras;

    public Teatro getTeatro(){
        return teatro;
    }
     /**
     * Constructor del Actor
     * @param teatro en donde se trabaja
     * @param nombre del actor
     * @param  posicionx del actor
     * @param  posicionxy del actor
     */
    public Actor(Teatro teatro,String name,int posicionx, int posiciony){
        super(name,posicionx,posiciony);
        this.teatro=teatro;
        color=Color.BLACK;
        palabras="¡Hola!";
        
    }

    /**
     * saber si el actor se puede mover hacia alguna dirección
     * @param char direccion a la que se quiere mover 
     * @return puede retorna true o false si se puede mover
     */
    private boolean puedeMover(char direccion) {
        boolean puede=false;
        int posX = getPosicionX();
        int posY = getPosicionY();
        switch(direccion){
            case 'N' : puede = (posY+1 < teatro.MAXIMO);
            break;
            case 'E' : puede = (posX+1 < teatro.MAXIMO);
            break;
            case 'S' :  puede = (posY-1 >= 0);
            break;
            case 'O':puede = (posX-1 >= 0);
            break; 
        } 
        return puede;
    }
    
    /**
     * cuando la escena para
     */
    
    public void corte(){
        muevaBrazo('I','B'); 
        muevaPierna('I','P');
        muevaBrazo('D','B'); 
        muevaPierna('D','P');       
        palabras="";
    }
    /**
     * el actor se mueve cuando empieza la escena
     */
    public void actue(){
      
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
        muevase('S');   
        palabras="Soy " + this;
    }
    /**
     * heredado de enEscena pero aca no se usa
     */
    public int[] getPosicionX1(){
        int[] x={};
        return x;
    }
    /**
     * heredado de enEscena pero aca no se usa
     */
    public int[] getPosicionY1(){
        int[] x={};
        return x;
    }
    /**
     * mensaje del actor
     * @return palabras mensaje del actor
     */
    public String mensaje(){
        return  palabras;
    }

}


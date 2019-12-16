package aplicacion;

import java.awt.Color;
/**
 * DeportistaPrincipiante clase que hereda de Deportista.
 *
 * @author (Jimenez Eduard - Murillo Carlos)
 * @version (1.0)
 */
public class DeportistaPrincipiante extends Deportista{
    private int saltos = 0;
    private Salon salon = null;
    /**
     * Metodo constructor
	 * @param salon indica el salon al cual pertenece
	 * @param nombre es el nombre del deportista
	 * @param posicionx es la posicion en el eje x
	 * @param posiciony es la posicion en el eje y
     */
    public DeportistaPrincipiante(Salon salon,String nombre,int posicionx, int posiciony){
        super(salon, nombre, posicionx, posiciony);
        super.setColor(Color.BLUE);
        
    }
    
    private boolean puedeSaltar(){
        salon = Salon.demeSalon();
        boolean band = false;
        for (int i=1; i<=salon.numeroEnSalon(); i++) {
            EnSalon e=salon.deme(i);            
            if (e.forma().equals("Cuadrado")){
                if(e.getColor().equals(Color.BLUE))
                    band = true;
                else
                    band  = false;
            }
        }
        if(band)
            return true;
        else
            return false;
    }

    /**
     * Metodo Sobreescrito de la clase Deportista, inicia el movimiento del DeportistaPrincipiante
     */
    @Override 
    public void inicie(){
        palabras="";
        if(puedeSaltar()){
            saltos++;
            if(saltos <= 8){
                if (getPosicionBrazo('I')==ABAJO && getPosicionBrazo('D')==ABAJO){
                    muevaBrazo('I','S');
                    muevaBrazo('I','S');
                    muevaBrazo('D','S');
                    muevaBrazo('D','S');
                    muevaPierna('I','S');
                    muevaPierna('D','S');
                    palabras="Ya estoy cansado";
                } else if  (getPosicionBrazo('I')==FRENTE && getPosicionBrazo('D')==FRENTE){
                    muevaBrazo('I','S'); 
                    muevaBrazo('D','S');
                    muevaPierna('I','S');
                    muevaPierna('D','S');
                    palabras="Estoy demasiado cansado";
                } else if (getPosicionBrazo('I')==ARRIBA && getPosicionBrazo('D')==ARRIBA){
                    muevaBrazo('I','B'); 
                    muevaBrazo('D','B');
                    muevaPierna('I','B');
                    muevaPierna('D','B');
                    palabras="Ya me quiero ir a casa";
                }
                if (super.puedeMover('E')){
                    muevase('E');
                }
            }
            palabras="Ya no aguanto mas";
        }
    }
    /**
     * El deportista principiante dedcide que acción quiere tomar, en este caso, siempre quiere parar.
     */
    public void decida(){
        pare();
    }
}

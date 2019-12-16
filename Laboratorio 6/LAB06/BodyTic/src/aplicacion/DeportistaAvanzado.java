package aplicacion;

import java.awt.Color;
/**
 * DeportistaAvanzado clase que hereda de Deportista.
 * 
 * @author (Jimenez Eduard - Murillo Carlos) 
 * @version (1.0)
 */
public class DeportistaAvanzado extends Deportista
{
    private int x;
    private int contador;
    /**
     * Metodo Constructor
	 * @param salon indica el salon al cual pertenece
	 * @param nombre es el nombre del deportista
	 * @param posicionx es la posicion en el eje x
	 * @param posiciony es la posicion en el eje y
     */
    public DeportistaAvanzado(Salon salon,String nombre,int posicionx, int posiciony){
        super(salon, nombre, posicionx, posiciony);
        super.setColor(Color.ORANGE);
        contador = 0;
    }
    
    /**
     * Metodo sobreescrito que detiene las acciones del deportista Avanzado
     */
    @Override
    public void pare(){
        contador++;
        if(contador%4==0){
            muevaBrazo('I','B'); 
            muevaPierna('I','P');
            muevaBrazo('D','B'); 
            muevaPierna('D','P');       
            palabras="¡Uff!";
        }
        
    }
    
    /**
     * El deportista Avanzado decide que acción tomar, en este caso siempre decide hacer ejercicio. 
     */
    public void decida(){
        if (r.nextBoolean()){
            inicie();
        }
    }

}

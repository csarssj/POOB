package aplicacion;
import java.awt.Color;
import java.util.Random;

/**
 * Interface EnSalon es la interfaz que usaran las otras clases.
 */
public interface EnSalon{
    public static String[] FORMAS = new String[]{"Persona", "Circulo", "Cuadrado"};
    
    Random r = new Random(1);
    
    int getPosicionX();
    int getPosicionY();
    Color getColor();
   
    void inicie();
    void pare();
    
    /**
     * Retorna una forma determinada en este caso un "Circulo".
	 * @return forma el tipo de forma.
     */
    default String forma(){
       return FORMAS[1];
    }
    
    
    /**
     * Da un mensaje vacio
	 * @return a una cadena con el mensaje
     */
    default String mensaje(){
       return "";
    }
    
    /**
     * Decide aleatoriamente la acción a tomar
     */
    default void decida(){
        if (r.nextBoolean()){
            inicie();
        }else{
            pare();
        }
    }
}

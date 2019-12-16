package aplicacion;
import java.awt.Color;
import java.util.Random;
/**
 * Interface EnEscena para simular todo lo de un escenario
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 3/10/2019
 */
public interface EnEscena{
    String[] FORMAS = new String[]{"Persona", "Circulo", "Cuadrado"};
    
    Random r = new Random(1);
    
    int getPosicionX();
    int getPosicionY();
    int getPosicionX1();
    int getPosicionY1();
    int getPosicionX2();
    int getPosicionY2();
    Color getColor();
    String toString();
    void actue();
    void corte();
    
    /**
     * Es una persona
     */
    default String forma(){
       return FORMAS[0];
    }
    
    /**
     * El mensaje es la representación cadena del objeto
     */
    default String mensaje(){
       return toString();
    }
    
    /**
     * Decide aleatoriamente la acción a tomar
     */
    default void decida(){
        if (r.nextBoolean()){
            actue();
        }else{
            corte();
        }
    }    
}

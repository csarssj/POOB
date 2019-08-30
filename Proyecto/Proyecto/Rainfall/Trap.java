package Rainfall;

import Shapes.*;
/**
 * Clase de lonas las cuales impiden que le den calor a los turistas
 * pero que los viñedos no se vean afectados
 * 
 * @author César Eduardo González y Brayan Santiango Bitrafo 
 * @version 29/08/2019
 */
public class Trap
{
    // instance variables - replace the example below with your own
    private Line lona;

    /**
     * Constructor para la clase Trap
     * 
     * @int[] ini punto inicial de la lona
     * @int[] fin punto final de la lona
    **/
    public Trap(int[] ini,int[] fin)
    {
        lona = new Line(ini[0],ini[1],fin[0],fin[1]);
        lona.makeVisible();
    }
    /**
     * Hace invisible la lona dada.
     * 
     */
    public void makeInvisible(){
        lona.makeInvisible();
    }
    
    /**
     * Hace visible la lona dado.
     */
    public void makeVisible(){
        lona.makeVisible();
    }

 
}

package Rainfall;

import Shapes.*;
/**
 * Clase de Viñedos los cuales deben ser regados y recibir luz solar
 * 
 * @author César Eduardo González y Brayan Santiango Bitrafo 
 * @version 29/08/2019
 */
public class VineYard
{
    // instance variables - replace the example below with your own
    private Rectangle viñedo;
    
    /**
     * Constructor de la clase VineYard(viñedos)
     */
    public VineYard(String name,int xi,int xf)
    {
        viñedo = new Rectangle();
        viñedo.changeSize(10,xf-xi);
        viñedo.moveVertical(90);
        viñedo.changeColor("green");
        viñedo.makeVisible();
        
    }
    /**
     * Hace invisible el viñedo dado.
     * 
     */
    public void makeInvisible(){
        viñedo.makeInvisible();
    }
    
    /**
     * Hace visible el viñedo dado.
     */
    public void makeVisible(){
        viñedo.makeVisible();
    }
    

}

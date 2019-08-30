package Rainfall;

import Shapes.*;
/**
 * Clase de Vi�edos los cuales deben ser regados y recibir luz solar
 * 
 * @author C�sar Eduardo Gonz�lez y Brayan Santiango Bitrafo 
 * @version 29/08/2019
 */
public class VineYard
{
    // instance variables - replace the example below with your own
    private Rectangle vi�edo;
    
    /**
     * Constructor de la clase VineYard(vi�edos)
     */
    public VineYard(String name,int xi,int xf)
    {
        vi�edo = new Rectangle();
        vi�edo.changeSize(10,xf-xi);
        vi�edo.moveVertical(90);
        vi�edo.changeColor("green");
        vi�edo.makeVisible();
        
    }
    /**
     * Hace invisible el vi�edo dado.
     * 
     */
    public void makeInvisible(){
        vi�edo.makeInvisible();
    }
    
    /**
     * Hace visible el vi�edo dado.
     */
    public void makeVisible(){
        vi�edo.makeVisible();
    }
    

}

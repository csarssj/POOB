package Rainfall;

import Shapes.*;
/**
 * Clase de Vi�edos los cuales deben ser regados y recibir luz solar
 * 
 * @author C�sar Eduardo Gonz�lez y Brayan Santiango Buitrago 
 * @version 15/09/2019
 */
public class VineYard
{
    // instance variables - replace the example below with your own
    private Rectangle vi�edo;
    private int x;
    private int y;
    private String color;
    
    /**
     * Constructor de la clase VineYard(vi�edos)
     */
    public VineYard(String name,int xi,int xf,String color,int maxY)
    {
        vi�edo = new Rectangle();
        x = xi;
        xf = xf;
        this.color =color;
        y = maxY;
        vi�edo.changeColor(color);
        vi�edo.changeSize(10,xf-xi);
        vi�edo.moveHorizontal(xi);
        vi�edo.moveVertical(maxY-20);
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
    /**
     * Cambia el color del vi�edo
     * @param color, el color a cambiar
     */
    public void changeColor(String color){
        vi�edo.changeColor(color);
    }
    /**
     * Cambia el tama�o del vi�edo
     */
    public void changeSize1(){
        vi�edo.changeSize(vi�edo.getH()*2,vi�edo.getW()*2);
    }
    /**
     * Cambia el tama�o del vi�edo
     */
    public void changeSize2(){
        vi�edo.changeSize(vi�edo.getH()/2,vi�edo.getW()/2);
    }
   

}

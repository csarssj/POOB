package Rainfall;

import Shapes.*;
/**
 * Clase de Viñedos los cuales deben ser regados y recibir luz solar
 * 
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 15/09/2019
 */
public class VineYard
{
    // instance variables - replace the example below with your own
    private Rectangle viñedo;
    private int x;
    private int y;
    private String color;
    
    /**
     * Constructor de la clase VineYard(viñedos)
     */
    public VineYard(String name,int xi,int xf,String color,int maxY)
    {
        viñedo = new Rectangle();
        x = xi;
        xf = xf;
        this.color =color;
        y = maxY;
        viñedo.changeColor(color);
        viñedo.changeSize(10,xf-xi);
        viñedo.moveHorizontal(xi);
        viñedo.moveVertical(maxY-20);
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
    /**
     * Cambia el color del viñedo
     * @param color, el color a cambiar
     */
    public void changeColor(String color){
        viñedo.changeColor(color);
    }
    /**
     * Cambia el tamaño del viñedo
     */
    public void changeSize1(){
        viñedo.changeSize(viñedo.getH()*2,viñedo.getW()*2);
    }
    /**
     * Cambia el tamaño del viñedo
     */
    public void changeSize2(){
        viñedo.changeSize(viñedo.getH()/2,viñedo.getW()/2);
    }
   

}

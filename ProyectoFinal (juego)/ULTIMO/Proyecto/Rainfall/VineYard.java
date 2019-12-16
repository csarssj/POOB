package Rainfall;

import Shapes.*;
/**
 * Clase de Vi�edos los cuales deben ser regados y recibir luz solar
 * 
 * @author C�sar Eduardo Gonz�lez y Brayan Santiango Buitrago 
 * @version 27/10/2019
 */
public class VineYard
{
    // instance variables - replace the example below with your own
    private Rectangle vi�edo;
    private int xi;
    private int xf;
    private int y;
    private int[] pos;
    private String color;
    
    /**
     * Constructor de la clase VineYard(vi�edos)
     * @param name nombre del vi�edo
     * @param xi posicion inicial en x del vi�edo
     * @param xf posicion final en y del vi�edo
     * @param color color del vi�edo (es �nico, no se puede repetir)
     * @param maxY largo del valle
     */
    public VineYard(String name,int xi,int xf,String color,int maxY)
    {
        vi�edo = new Rectangle();
        this.xi = xi;
        this.xf = xf;
        this.color =color;
        this.y = maxY;
        vi�edo.changeColor(color);
        vi�edo.changeSize(10,xf-xi);
        vi�edo.moveHorizontal(xi);
        vi�edo.moveVertical(maxY-20);
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
     * @param color el color a cambiar
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
    /**
     * Retorna las posiciones del vi�edo
     * @returns pos lista con las posiciones
     */
    public int[] getPos(){
        xi=this.xi;
        xf=this.xf;
        int [] pos = {xi,xf};
        return pos;
    }
    /**
     * Retorna las posiciones del vi�edo
     * @returns el color del vi�edo
     */
    public String getColor(){
        return color;
    }

}

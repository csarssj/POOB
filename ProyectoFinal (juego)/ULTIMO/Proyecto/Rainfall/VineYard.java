package Rainfall;

import Shapes.*;
/**
 * Clase de Viñedos los cuales deben ser regados y recibir luz solar
 * 
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 27/10/2019
 */
public class VineYard
{
    // instance variables - replace the example below with your own
    private Rectangle viñedo;
    private int xi;
    private int xf;
    private int y;
    private int[] pos;
    private String color;
    
    /**
     * Constructor de la clase VineYard(viñedos)
     * @param name nombre del viñedo
     * @param xi posicion inicial en x del viñedo
     * @param xf posicion final en y del viñedo
     * @param color color del viñedo (es único, no se puede repetir)
     * @param maxY largo del valle
     */
    public VineYard(String name,int xi,int xf,String color,int maxY)
    {
        viñedo = new Rectangle();
        this.xi = xi;
        this.xf = xf;
        this.color =color;
        this.y = maxY;
        viñedo.changeColor(color);
        viñedo.changeSize(10,xf-xi);
        viñedo.moveHorizontal(xi);
        viñedo.moveVertical(maxY-20);
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
     * @param color el color a cambiar
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
    /**
     * Retorna las posiciones del viñedo
     * @returns pos lista con las posiciones
     */
    public int[] getPos(){
        xi=this.xi;
        xf=this.xf;
        int [] pos = {xi,xf};
        return pos;
    }
    /**
     * Retorna las posiciones del viñedo
     * @returns el color del viñedo
     */
    public String getColor(){
        return color;
    }

}

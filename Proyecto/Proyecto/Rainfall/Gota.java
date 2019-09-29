package Rainfall;

import Shapes.*;
/**
 * Clase que simula las gotas de la lluvia de un vi�edo
 * 
 * @author C�sar Eduardo Gonz�lez y Brayan Santiango Buitrago 
 * @version 1/10/2019
 */
public class Gota
{
    // instance variables - replace the example below with your own
    public int x;
    public int y;
    private Circle gota;
    public String color;
    
    /**
     * Constructor de una gota, que forma parte de la lluvia
     * @param x posicion de la gota en x
     * @param y posicion de la gota en y
     */
    public Gota(int x,int y)
    {
        this.x=x;
        this.y=y;
        gota = new Circle();
        gota.moveHorizontal(x);
        gota.moveVertical(y);
        gota.changeSize(10);
        gota.changeColor("cyan");
    }
    /**
     * Hace visible la gota 
     */
    public void makeVisible(){
        gota.makeVisible();
    }
    /**
     * Hace invisible la gota 
     */
    public void makeInvisible(){
        gota.makeInvisible();
    }
    /**
     * Cambia de posici�n la gota
     * @param x posicion de la gota en x
     * @param y posicion de la gota en y
     */
    public void move(int x, int y){
        gota.moveHorizontal(x);
        gota.moveVertical(y);
    }
    /**
     * Aumenta el tama�o de la gota 
     */
    public void changeSize1()
    {
        gota.changeSize(gota.getDiameter()*2);
    }
    /**
     * Reduce el tama�o de la gota
     */
    public void changeSize2()
    {
            gota.changeSize(gota.getDiameter()/2);
    }
    /**
     * Retorna la posiciones en "x" y en "y" de la gota
     * @returns int[] posiciones de la gota
     */
    public int[] getPos(){
        int[] pos = {this.x,this.y}; 
        return pos;
    }
    
}


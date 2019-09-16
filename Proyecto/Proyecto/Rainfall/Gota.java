package Rainfall;

import Shapes.*;
/**
 * Write a description of class Gota here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gota
{
    // instance variables - replace the example below with your own
    public int x;
    public int y;
    private Circle gota;
    public String color;
    
    /**
     * Constructor for objects of class Gota
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

    public void makeVisible(){
        gota.makeVisible();
    }
    public void makeInvisible(){
        gota.makeInvisible();
    }
    public void move(int x, int y){
        gota.moveHorizontal(x);
        gota.moveVertical(y);
    }
    
}


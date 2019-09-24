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
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void changeSize1()
    {
        gota.changeSize(gota.getDiameter()*2);
    }
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void changeSize2()
    {
            gota.changeSize(gota.getDiameter()/2);
    }
    
}


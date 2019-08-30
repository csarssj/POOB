package Shapes;

import java.util.*;
import java.awt.Color;
import java.awt.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 * Write a description of class SlotMachine here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SlotMachine
{
    // instance variables - replace the example below with your own
    private int dimension ;
    private Canvas canvas;
    private Rectangle caja;
    private ArrayList<Circle>  Figuras= new ArrayList <Circle> ();
    private ArrayList<Rectangle>  Figuras2= new ArrayList <Rectangle> ();
    private int numero;
    /**
     * Constructor for objects of class SlotMachine
     */
    public SlotMachine(int n)
    {
        canvas = new Canvas("New", 800,800,Color.white);
        canvas.getCanvas(800,800);
        dimension = n;
        numero = n;
        caja = new Rectangle();
        if (n==1) caja.changeSize(120,(120*n));
        else caja.changeSize(120-(n+15),(125*n)-(35*n));
        caja.changeColor("blue");
        caja.moveVertical(280);
        caja.moveHorizontal(20);
        caja.makeVisible();
        int ene= 40;
        for (int i=0;i<n;i++){
            Circle circulo= new Circle();
            Figuras.add(circulo);
            if (n==1) circulo.changeSize(50);
            else circulo.changeSize(50-(n+15));
            circulo.changeColor("red");
            circulo.moveVertical(315);
            circulo.moveHorizontal(ene+65);            
            Rectangle cuadrado=new Rectangle();
            Figuras2.add(cuadrado);
            if (n==1) cuadrado.changeSize(80,80);
            else cuadrado.changeSize(80-(n+15),80-(n+15));
            cuadrado.changeColor("white");
            cuadrado.moveVertical(300);
            cuadrado.moveHorizontal(ene); 
            ene += 100-(n+15);
            cuadrado.makeVisible();
            circulo.makeVisible();
        }
   
        
    }
    /**
     * Constructor for objects of class SlotMachine
     */
    public SlotMachine(int n,int x, int y)
    {
        canvas = new Canvas("New", 800,800,Color.white);
        canvas.getCanvas(800,800);
        dimension = n;
        numero = n;
        caja = new Rectangle();
        if (n==1) caja.changeSize(120,(120*n));
        else caja.changeSize(120-(n+15),(125*n)-(35*n));
        caja.changeColor("blue");
        caja.moveVertical(280+y);
        caja.moveHorizontal(20+x);
        caja.makeVisible();
        int ene= 40;
        for (int i=0;i<n;i++){
            Circle circulo= new Circle();
            Figuras.add(circulo);
            if (n==1) circulo.changeSize(50);
            else circulo.changeSize(50-(n+15));
            circulo.changeColor("red");
            circulo.moveVertical(315+y);
            circulo.moveHorizontal(ene+65+x);            
            Rectangle cuadrado=new Rectangle();
            Figuras2.add(cuadrado);
            if (n==1) cuadrado.changeSize(80,80);
            else cuadrado.changeSize(80-(n+15),80-(n+15));
            cuadrado.changeColor("white");
            cuadrado.moveVertical(300+y);
            cuadrado.moveHorizontal(ene+x); 
            ene += 100-(n+15);
            cuadrado.makeVisible();
            circulo.makeVisible();
        }
   
        
    }
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void pull()
    { 
        ArrayList <String> colores = new ArrayList<String>();
        colores.add("red");
        colores.add("orange");
        colores.add("green");
        colores.add("blue");
        colores.add("black");
        colores.add("yellow");
        colores.add("peru");
        colores.add("tomato");
        colores.add("magenta");
        for(int i=0; i<Figuras.size();i++){
            int random = (int) (Math.random () * (getN()+1)) + 1;
            Figuras.get(i).changeColor(colores.get(random));
            }
    }
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void pull(int times)
    { 
        ArrayList <String> colores = new ArrayList<String>();
        colores.add("red");
        colores.add("orange");
        colores.add("green");
        colores.add("blue");
        colores.add("black");
        colores.add("yellow");
        colores.add("peru");
        colores.add("tomato");
        colores.add("magenta");
        for (int x=0; x<times;x++){
            for(int i=0; i<Figuras.size();i++){
                int random = (int) (Math.random () * (getN()+1)) + 1;
                Figuras.get(i).changeColor(colores.get(random));
                }
        }
    }
    private int getN(){
        return numero;
    }
    public void makeVisible(){
        caja.makeVisible();
         for(int i=0; i<Figuras2.size();i++){
                Figuras2.get(i).makeVisible();
         }
        for(int i=0; i<Figuras.size();i++){
                Figuras.get(i).makeVisible();
         }
        
        
    }
    public void makeInvisible(){
        caja.makeInvisible();
         for(int i=0; i<Figuras2.size();i++){
                Figuras2.get(i).makeInvisible();
         }
        for(int i=0; i<Figuras.size();i++){
                Figuras.get(i).makeInvisible();
         }
        
        
    }
    public void reset(){
        for(int i=0; i<Figuras.size();i++){
                Figuras.get(i).changeColor("red");
         }
    }
    public Boolean isWinningState(){
        int r=0,b=0,o=0,p=0,bl=0,g=0,m=0,t=0,y=0,i=0;
        while(i<Figuras.size()){
                String color=Figuras.get(i).getColor();
                if(color=="red") r +=1;
                else if(color=="peru") p +=1;
                else if(color=="blue") bl +=1;
                else if(color=="magenta") m +=1;
                else if(color=="black") b +=1;
                else if(color=="tomato") t +=1;
                else if(color=="green") g +=1;
                else if(color=="yellow") y +=1;
                else o +=1;                
                i++;
        }
        if(r==numero|p==numero|bl==numero|o==numero|g==numero|t==numero|b==numero|m==numero|y==numero){
            return true;
        }
        else return false;
    }
    public void move(int horizontal, int vertical){
        caja.moveHorizontal(horizontal);
        caja.moveVertical(vertical);
         for(int i=0; i<Figuras2.size();i++){
                Figuras2.get(i).moveHorizontal(horizontal);
                Figuras2.get(i).moveVertical(vertical);
         }
        for(int i=0; i<Figuras.size();i++){
                Figuras.get(i).moveHorizontal(horizontal);
                Figuras.get(i).moveVertical(vertical);
         }
    }
   
}

package Rainfall;

import java.util.*;
import java.awt.*;
import Shapes.*;
/**
 * Clase de lonas las cuales impiden que le den calor a los turistas
 * pero que los viñedos no se vean afectados
 * 
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 15/09/2019
 */
public class Trap
{
    // instance variables - replace the example below with your own
    private Line lona;
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private double inter;
    private double p;
    private double y;
    private String color;
    public ArrayList <Puncture> huecos = new ArrayList<Puncture>();
    /**
     * Constructor para la clase Trap
     * 
     * @int[] ini punto inicial de la lona
     * @int[] fin punto final de la lona
    **/
    public Trap(int[] ini,int[] fin)
    {
        lona = new Line(ini[0],fin[0],ini[1],fin[1]);
        x1= ini[0];
        x2= fin[0];
        y1=ini[1];
        y2=fin[1];
        double p = ((double)y2-(double)y1)/((double)x2-(double)x1);
        double y = -p*x2+y2;
        lona.makeVisible();
        int inter = 0;
        color="black";
    }
    /**
     * Hace invisible la lona dada.
     * 
     */
    public void makeInvisible(){
        lona.makeInvisible();
    }
    
    /**
     * Hace visible la lona dado.
     */
    public void makeVisible(){
        lona.makeVisible();
    }
    /**
     * Hace un hueco en la lona
     * @param x, la posicion en x para hacer el hueco
     */
    public void makePuncture(int x){
        double p = ((double)y2-(double)y1)/((double)x2-(double)x1);
        double y = -p*x2+y2;
        inter = p * x + y;
        huecos.add(new Puncture(x,(int)inter+1));
    }
    /**
     * Tapa un hueco en la lona
     * @param x, la posicion en x para tapar el hueco
     */
    public void patchPuncture(int x){
        double p = ((double)y2-(double)y1)/((double)x2-(double)x1);
        double y = -p*x2+y2;
        inter = p * x + y;
        for(int i=0; i<huecos.size();i++){
            if(huecos.get(i).getXPuncture()==x && huecos.get(i).getYPuncture()==(int)inter+1){
                huecos.get(i).makeInvisible();
                huecos.remove(i);
            }
        }
    }
    public double getP(){
        double p = ((double)y2-(double)y1)/((double)x2-(double)x1);
        return p;
    }
    public double getY(){
        double p = ((double)y2-(double)y1)/((double)x2-(double)x1);
        double y = -p*x2+y2;
        return y;
    }
    public int getX1(){
        return x1;
    }
    public int getX2(){
        return x2;
    }
    public int getY1(){
        return y1;
    }
    public int getY2(){
        return y2;
    }
}

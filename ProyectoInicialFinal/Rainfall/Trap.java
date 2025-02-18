package Rainfall;

import java.util.*;
import java.awt.*;
import Shapes.*;
/**
 * Clase de lonas las cuales impiden que le den calor a los turistas
 * pero que los vi�edos no se vean afectados
 * 
 * @author C�sar Eduardo Gonz�lez y Brayan Santiango Buitrago 
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
        this.x1= ini[0];
        this.x2= fin[0];
        this.y1=ini[1];
        this.y2=fin[1];
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
        double p = ((double)this.y2-(double)this.y1)/((double)this.x2-(double)this.x1);
        return p;
    }
    public double getY(){
        double p = ((double)this.y2-(double)this.y1)/((double)this.x2-(double)this.x1);
        double y = -p*this.x2+this.y2;
        return y;
    }
    public int[][] getPos(){
        int [][] pos = {{this.x1,this.y1},{this.x2,this.y2}};
        return pos;
    }
    /**
     * Cambia el tama�o de la lona
     */
    public void changeSize1(){
        lona.changeSize2(lona.getH(),lona.getW());
    }
    /**
     * Cambia el tama�o de la lona
     */
    public void changeSize2(){
        lona.changeSize3(lona.getH(),lona.getW());
    }
    /**
     * Cambia el color de la lona
     * @param color, nuevo color
     */
    public void changeColor(String newColor){
        color = newColor;
        lona.changeColor(newColor);
    }
    /**
     * Retorna el color de la lona
     * @Returns color
     */
    public String getColor(){
        return color;
    }
    /**
     * Retorna  las lista de huecos de una lona
     * @Returns huecos
     */
    public ArrayList<Puncture> getHuecos(){
        return huecos;
    }
}

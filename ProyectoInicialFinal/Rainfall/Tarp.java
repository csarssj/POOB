package Rainfall;

import java.util.*;
import java.awt.*;
import Shapes.*;
/**
 * Clase de lonas las cuales impiden que le den calor a los turistas
 * pero que los viñedos no se vean afectados
 * 
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 20/10/2019
 */
public class Tarp
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
    private boolean isVisible;
    protected String color,tipo;
    private Circle distintivo;
    public ArrayList <Puncture> huecos = new ArrayList<Puncture>();
    /**
     * Constructor para la clase Trap
     * @param ini punto inicial de la lona
     * @param fin punto final de la lona
    **/
    public Tarp(int[] ini,int[] fin)
    {
        lona = new Line(ini[0],fin[0],ini[1],fin[1]);
        this.x1= ini[0];
        this.x2= fin[0];
        this.y1=ini[1];
        this.y2=fin[1];
        int inter = 0;
        color="black";
        tipo = "normal";
        isVisible=false;
        distintivo = new Circle();
        distintivo.moveHorizontal(ini[0]);
        distintivo.moveVertical(ini[1]);
        distintivo.changeSize(10);
        distintivo.changeColor("black");
    }
    /**
     * Hace invisible la lona dada.
     * 
     */
    public void makeInvisible(){
        lona.makeInvisible();
        distintivo.makeInvisible();
        isVisible=false;
    }
    /**
     * Hace invisible sus huecos
     * 
     */
    public void makeInvisibleHuecos(){
        if(huecos.size()>0){
            for(int i=0; i<huecos.size();i++){
                huecos.get(i).makeInvisible();
            }
        }
    }
    /**
     * Hace visible sus huecos
     * 
     */
    public void makeVisibleHuecos(){
        if(huecos.size()>0){
            for(int i=0; i<huecos.size();i++){
                huecos.get(i).makeVisible();
            }
        }
    }
    
    /**
     * Hace visible la lona dado.
     */
    public void makeVisible(){
        lona.makeVisible();
        distintivo.makeVisible();
        isVisible=true;
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
    /**
     * Retorna la pendiente de la lona
     * @returns p pendiente de la lona
     */
    public double getP(){
        double p = ((double)this.y2-(double)this.y1)/((double)this.x2-(double)this.x1);
        return p;
    }
    /**
     * Retorna el punto de corte en y de la lona
     * @returns y punto de corte en y
     */
    public double getY(){
        double p = ((double)this.y2-(double)this.y1)/((double)this.x2-(double)this.x1);
        double y = -p*this.x2+this.y2;
        return y;
    }
    /**
     * Retorna los puntos de los extremos de una lona
     * @returns int[][] lista de posiciones de la lona
     */
    public int[][] getPos(){
        int [][] pos = {{this.x1,this.y1},{this.x2,this.y2}};
        return pos;
    }
    /**
     * Cambia el tamaño de la lona
     */
    public void changeSize1(){
        lona.changeSize2(lona.getH(),lona.getW());
    }
    /**
     * Cambia el tamaño de la lona
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
     * @Returns huecos arraylist de huecos
     */
    public ArrayList<Puncture> getHuecos(){
        return huecos;
    }
    /**
     * Retorna las posiciones en x de los huecos
     * @returns int[] lista de posiciones en x de los huecos
     */
    public int[] getPosH(){
        int len = huecos.size();
        int[] res = new int[(len > 0) ? len: 1];
        if (len == 0) res[0] = -1;
        else{
            for(int i = 0; i< len ; i++){
                res[i] = huecos.get(i).getXPuncture();
            }
        }return res;
    }
    /**
     * Retorna el tipo de la lona
     * @returns tipo
     */
    public String getTipo(){
        return tipo;
    }
    /**
     * Retorna si esta invisible la lona
     * @returns isVisible
     */
    public boolean getVisible(){
        return isVisible;
    }
}

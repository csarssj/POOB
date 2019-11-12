package Rainfall;

import Shapes.*;
/**
 * Clase Puncture (huecos), donde se crean los huecos en las lonas, para que los 
 * vi�edos puedan ser regados.
 *
 * @author C�sar Eduardo Gonz�lez y Brayan Santiango Buitrago 
 * @version 1/10/2019
 */
public class Puncture
{
    private Rectangle hueco;
    private int x;
    private int y;
    /**
     * Constructor de la clase Puncture
     * @param x posici�n en x donde se hace el hueco
     * @param y posicion en y donde se hace el hueco
     */
    public Puncture(int x,int y)
    {
        hueco = new Rectangle();
        this.x=x;
        this.y=y;
        hueco.changeSize(10,10);
        hueco.moveVertical(y);
        hueco.moveHorizontal(x);
        hueco.changeColor("white");
    }
    /**
     * Hace invisible el hueco dado.
     * 
     */
    public void makeInvisible(){
        hueco.makeInvisible();
    }
    
    /**
     * Hace visible el hueco dado.
     */
    public void makeVisible(){
        hueco.makeVisible();
    }
    /**
     * @Return x, la posicion en x del hueco.
     */
    public int getXPuncture(){
        return this.x;
    }
    /**
     *@Return y, la posicion en y del hueco. 
     */
    public int getYPuncture(){
        return this.y;
    }
    /**
     * Cambia el tama�o de la lona
     */
    public void changeSize1(){
        hueco.changeSize(hueco.getH()*2,hueco.getW()*2);
    }
    /**
     * Cambia el tama�o de la lona
     */
    public void changeSize2(){
        hueco.changeSize(hueco.getH()/2,hueco.getW()/2);
    }
}

package Rainfall;

import java.util.ArrayList;
import java.util.Random;
import Shapes.*;
/**
* Clase de la lona crazy que abre un hueco random diferente o igual al que le indican
* 
* @author César Eduardo González y Brayan Santiango Buitrago 
* @version 20/10/2019
*/
public class Crazy extends Tarp

{   
    private int r1,r2;
    private Circle distintivo;
    /**
     * Constructor para la clase Hard
     * @param ini punto inicial de la lona
     * @param fin punto final de la lona
    **/
    public Crazy(int[] ini,int[] fin)
    {
        super(ini,fin );
        tipo = "crazy";
        this.r1= ini[0];
        this.r2= fin[0];
        distintivo = new Circle();
        distintivo.moveHorizontal(ini[0]);
        distintivo.moveVertical(ini[1]);
        distintivo.changeSize(10);
        distintivo.changeColor("tomato");
    }
    @Override
    public void makePuncture(int x){
        Random r = new Random();
        int ran = r.nextInt(this.r2-this.r1);
        super.makePuncture(ran);
    }
    @Override
    public void makeInvisible(){
        super.makeInvisible();
        distintivo.makeInvisible();
    }
    @Override
    public void makeVisible(){
        super.makeVisible();
        distintivo.makeVisible();
    }
}

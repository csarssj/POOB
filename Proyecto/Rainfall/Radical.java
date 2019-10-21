package Rainfall;

import Shapes.*;
/**
* Clase de la lona Radical la cual si se agujerea se elimina
* 
* @author César Eduardo González y Brayan Santiango Buitrago 
* @version 20/10/2019
*/
public class Radical extends Tarp

{   
    private Circle distintivo;
    /**
     * Constructor para la clase Hard
     * @param ini punto inicial de la lona
     * @param fin punto final de la lona
    **/
    public Radical(int[] ini,int[] fin)
    {
        super(ini,fin );
        tipo = "radical";
        distintivo = new Circle();
        distintivo.moveHorizontal(ini[0]);
        distintivo.moveVertical(ini[1]);
        distintivo.changeSize(10);
        distintivo.changeColor("tomato");
    }

    @Override
    public void makePuncture(int x){
        
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
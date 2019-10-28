package Rainfall;

import Shapes.*;

/**
* Clase de la lona hard la cual no se elimina ni se agujerea
* 
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 27/10/2019
 */
public class Hard extends Tarp

{   
    private Circle distintivo;
    private boolean isVisible;
    /**
     * Constructor para la clase Hard
     * @param ini punto inicial de la lona
     * @param fin punto final de la lona
    **/
    public Hard(int[] ini,int[] fin)
    {
        super(ini,fin );
        tipo="hard";
        isVisible=true;
        distintivo = new Circle();
        distintivo.moveHorizontal(ini[0]);
        distintivo.moveVertical(ini[1]);
        distintivo.changeSize(10);
        distintivo.changeColor("gold");
    }
    @Override
    public void makePuncture(int x){
        
    }

    @Override
    public void makeInvisible(){
      
    }
    @Override
    public void makeVisible(){
        super.makeVisible();
        distintivo.makeVisible();
    }
    @Override
    public boolean getVisible(){
        return true;
    }
}
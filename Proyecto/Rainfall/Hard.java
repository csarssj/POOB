package Rainfall;

import Shapes.*;

/**
* Clase de la lona hard la cual no se elimina ni se agujerea
* 
* @author César Eduardo González y Brayan Santiango Buitrago 
* @version 20/10/2019
*/
public class Hard extends Tarp

{   
    private Circle distintivo;
    /**
     * Constructor para la clase Hard
     * @param ini punto inicial de la lona
     * @param fin punto final de la lona
    **/
    public Hard(int[] ini,int[] fin)
    {
        super(ini,fin );
        tipo="hard";
        distintivo = new Circle();
        distintivo.moveHorizontal(ini[0]);
        distintivo.moveVertical(ini[1]);
        distintivo.changeSize(10);
        distintivo.changeColor("gold");
    }
    
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
}
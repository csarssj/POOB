package Rainfall;

import java.util.ArrayList;
import java.util.Random;
import Shapes.*;
/**
* Clase de la lona Flexible la cual si se agujerea se elimina
* 
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 27/10/2019
 */
public class Flexible extends Tarp{
    
    private Circle distintivo; 
    
    public Flexible(int[] ini,int[] fin){
        super(ini, fin  );
        distintivo = new Circle();
        distintivo.moveHorizontal(ini[0]);
        distintivo.moveVertical(ini[1]);
        distintivo.changeSize(10);
        distintivo.changeColor("peru");
        tipo = "flexible";
    }
    /**
     * Devuelve un punto al azar dentro de las fronteras del simulador.
     * @param simHeight altura del simulador.
     * @param simWidth largo del simulador.
     * @return array con las coordenadas.
     */
    public static final int[] getFlexiblePoint(int simHeight, int simWidth){
        int xCoord, yCoord;
        Random r = new Random();
        do{
            xCoord = r.nextInt(simWidth);
            yCoord = r.nextInt(simHeight);
        }while (0==xCoord || 0==yCoord);
        return new int[]{xCoord, yCoord};
    }
    
    @Override
    public void makePuncture(int x){
        for(int i=0; i<huecos.size();i++){
             huecos.get(i).makeInvisible();
             huecos.remove(i);
        }
        super.makePuncture(x);
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
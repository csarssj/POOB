package Rainfall;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
* Clase lluvia acida la cual traspasa cualquier lona sin si hay hueco o no
* 
* @author César Eduardo González y Brayan Santiango Buitrago 
* @version 20/10/2019
*/
public class Acid extends Rain
{   
    /**
     * Constructor de la clase Lluvia acida
     * @param x posicion en x de la lluvia acida
     */
    public Acid(int x)
    {
        super(x);
    }

    public void start(int x,int y, ArrayList<Tarp> lonas){
        while(ini<y-20){
            
            Gota gota = new Gota(x,ini,"magenta");
            gotas.add(gota);
            ini++;
            
        }
        this.xf=x;
    }
}

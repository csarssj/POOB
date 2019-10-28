package Rainfall;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
* Clase lluvia Heavy la cual no cae por los huecos
* 
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 27/10/2019
 */
public class Heavy extends Rain
{
    private boolean contacto;
    private int actual;
    /**
     * Constructor de la clase Lluvia Heavy
     * @param x posicion en x de la lluvia Heavy
     */
    public Heavy(int x)
    {
        super(x);
        contacto = false;
        actual=0;
    }
        /**
     *  Simula la lluvia
     * @param x posicion en x de la lluvia 
     * @param y tamaño maximo del viñedo en y
     * @param lonas lonas del valle
     */
    public void start(int x,int y, ArrayList<Tarp> lonas){
        while(ini<y-20){
            for (int i=0;i<lonas.size();i++){
                int[][] pos = lonas.get(i).getPos();
                if((ini == (int)(lonas.get(i).getP()*x+lonas.get(i).getY())) && (pos[0][0]<x && pos[1][0]>x)){
                    boolean f = true; 
                    if(lonas.get(i).getP()<0){
                        for(int j = x; j>=pos[0][0];j--){
                               if(!f){
                                   break;
                                }else{Gota gota = new Gota(j,(int)(lonas.get(i).getP()*j+lonas.get(i).getY()),"golden");
                                    gotas.add(gota);
                                }
                        }
                        if(f){
                            x=pos[0][0]+1;
                            ini=(int)(lonas.get(i).getP()*x+lonas.get(i).getY())+1;
                            
                        }else{
                            ini++;
                        }
                    }
                    else if(lonas.get(i).getP()>0){
                        for(int k=x; k<=pos[1][0];k++){
                            if(!f){
                                break;
                            }else{
                                     Gota gota = new Gota(k,(int)(lonas.get(i).getP()*k+lonas.get(i).getY()),"golden");
                                    gotas.add(gota);
                                }
                            }
                        if(f){
                            x=pos[1][0]-1;
                            ini=(int)(lonas.get(i).getP()*x+lonas.get(i).getY())+1;

                            
                        }else{
                            ini++;
                        }
                    }
                }
            }
            Gota gota = new Gota(x,ini,"golden");
            gotas.add(gota);
            ini++;
            
        }
        this.xf=x;
    }
    /**
     * Retorna si hay contacto con una lona radical
     */
    public boolean getContacto(){
        return contacto;
    }
    /**
     * Retorna posicion de la lona actual donde se esta cayendo
     */
    public int getActual(){
        return actual;
    }
    /**
     * indica si hay contacto con una lona radical
     */
    public void setContacto(boolean c){
        contacto = c;
    }
    /**
     * cambia posicion de la lona actual donde se esta cayendo
     */
    public void setActual(int x){
        actual = x;
    }
    @Override
    public String getTipo(){
        return "heavy";
    }
}

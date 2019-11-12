package Rainfall;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
* Clase lluvia Straight cambia su forma para mantenerse recta y sólo para si no está regando un viñedo
* 
* @author César Eduardo González y Brayan Santiango Buitrago 
* @version 20/10/2019
*/
public class Straight extends Rain
{
    // instance variables - replace the example below with your own

    /**
     * Constructor de la clase Lluvia Straight
     * @param x posicion en x de la lluvia 
     */
    public Straight(int x)
    {   
        super(x);
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
                                }else{
                                    if(lonas.get(i).huecos.size()!=0){
                                        for(int z=0; z<lonas.get(i).huecos.size();z++){
                                            Gota gota = new Gota(j,(int)(lonas.get(i).getP()*j+lonas.get(i).getY()),"cyan");
                                            gotas.add(gota);
                                            if(lonas.get(i).huecos.get(z).getXPuncture()==j){
                                                x=j;
                                                f=false;
                                                break;
                                            }   
                                        }
                                    }
                                    Gota gota = new Gota(j,(int)(lonas.get(i).getP()*j+lonas.get(i).getY()),"cyan");
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
                                    if(lonas.get(i).huecos.size()!=0){
                                        for(int z=0; z<lonas.get(i).huecos.size();z++){
                                            Gota gota = new Gota(k,(int)(lonas.get(i).getP()*k+lonas.get(i).getY()),"cyan");
                                            gotas.add(gota);
                                            if(lonas.get(i).huecos.get(z).getXPuncture()==k){
                                                x=k;
                                                f=false;
                                                break;
                                            }   
                                        }
                                    }
                                    Gota gota = new Gota(k,(int)(lonas.get(i).getP()*k+lonas.get(i).getY()),"cyan");
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
            Gota gota = new Gota(x,ini,"cyan");
            gotas.add(gota);
            ini++;
            
        }
        this.xf=x;
    }
    @Override
    public String getTipo(){
        return "straight";
    }
}
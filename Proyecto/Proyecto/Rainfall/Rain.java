package Rainfall;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
 *Clase Lluvia que simula la misma en el problema Directing Rainfall
 * 
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 1/10/2019
 */
public class Rain
{
    // instance variables - replace the example below with your own
    public int x;
    public ArrayList<Gota> gotas = new ArrayList<Gota>();
    public int xf;
    /**
     * Constructor for objects of class Lluvia
     */
    public Rain(int x,int y,ArrayList<Trap> lonas)
    {   
        this.x=x;
        int ini=0;
        
        while(ini<y-10){
            for (int i=0;i<lonas.size();i++){
                int[][] pos = lonas.get(i).getPos();
                if((ini == (int)(lonas.get(i).getP()*x+lonas.get(i).getY())) && (pos[0][0]<x && pos[1][0]>x)){
                    boolean f = true; 
                    if(lonas.get(i).getP()<0){
                        for(int j = x; i>=pos[0][0];j--){
                                    Gota gota = new Gota(j,(int)(lonas.get(i).getP()*j+lonas.get(i).getY()));
                                    gotas.add(gota);
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
                                Gota gota = new Gota(k,(int)(lonas.get(i).getP()*k+lonas.get(i).getY()));
                                gotas.add(gota);
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
            Gota gota = new Gota(x,ini);
            gotas.add(gota);
            ini++;
            
        }
        this.xf=x;
    }
    /**
     * Vuelve visible las gotas que conforman la lluvia
     */
    public void makeVisible(){
        for(Gota g: gotas){
            g.makeVisible();
        }
    }
    /**
     * Vuelve invisible las gotas que conforman la lluvia
     */
    public void makeInvisible()
    {
        for(Gota g: gotas){
            g.makeInvisible();
        }
       
    }
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void changeSize1()
    {
        for(Gota g: gotas){
            g.changeSize1();
        }
       
    }
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void changeSize2()
    {
        for(Gota g: gotas){
            g.changeSize2();
        }
       
    }
    /**
     * Retorna x, que es la posicion de la lluvia
     */
    public int getX(){
        return x;
    }
}
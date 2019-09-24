package Rainfall;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
 * Write a description of class Lluvia here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
            //gota.makeVisible();
            gotas.add(gota);
            ini++;
            
        }
        xf=x;
    }
    public void makeVisible(){
        for(Gota g: gotas){
            g.makeVisible();
        }
    }
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
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
    
}
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
                if((ini == (int)(lonas.get(i).getP()*x+lonas.get(i).getY())) && (lonas.get(i).getX1()<x && lonas.get(i).getX2()>x)){
                    boolean f = true; 
                    if(lonas.get(i).getP()<0){
                        for(int j = x; i>=lonas.get(i).getX1();j--){
                                    Gota gota = new Gota(j,(int)(lonas.get(i).getP()*j+lonas.get(i).getY()));
                                    gotas.add(gota);
                        }
                        if(f){
                            x=lonas.get(i).getX1()+1;
                            ini=(int)(lonas.get(i).getP()*x+lonas.get(i).getY())+1;
                            
                        }else{
                            
                            ini++;
                        }
                    }
                    else if(lonas.get(i).getP()>0){
                        for(int k=x; k<=lonas.get(i).getX2();k++){
                                Gota gota = new Gota(k,(int)(lonas.get(i).getP()*k+lonas.get(i).getY()));
                                gotas.add(gota);
                            }
                        if(f){
                            x=lonas.get(i).getX2()-1;
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
}
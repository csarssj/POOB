package Rainfall;

import Shapes.*;
import java.util.*;
import java.awt.Color;
import java.awt.*;
import java.awt.Graphics;
import javax.swing.JOptionPane;

/**
 * Clase valle en donde se encuentra vi�edos juntos sus lonas
 * 
 * @author C�sar Eduardo Gonz�lez y Brayan Santiango Bitrafo 
 * @version 29/08/2019
 */
public class Valley
{
    private int maxY; 
    private int maxX;
    private int x;
    private Shapes.Canvas canvas;
    private ArrayList<Trap> lonas= new ArrayList<Trap>();
    private Hashtable<String,VineYard> vi�edos=new Hashtable<String,VineYard>();
    private ArrayList<String> nombres = new ArrayList<String>();
    private boolean ok;
    /**
     * Constructor para objetos de la clase Valle
     * @param  int maxX para el largo del tablero
     * @param  int maxY para el alto del tablero
     */
    public Valley(int maxX,int maxY)
    {
        this.maxX = maxX;
        this.maxY = maxY;
        canvas = new Shapes.Canvas("New", this.maxX,this.maxY,Color.white);
        canvas.getCanvas(maxX,maxY);
        ok = true;
    }

    /**
     * Crea un vi�edo 
     * @name nombre del vi�edo
     * @xi posici�n x en donde empieza el vi�edo
     * @xf posici�n x en donde termina el vi�edo 
     */
    public void openVineYard(String name,int xi,int xf)
    {
        vi�edos.put(name,new VineYard(name,xi, xf));
        nombres.add(name);
        ok=true;
    }
    /**
     * Elimina un vi�edo
     * @nombre el nombre del vi�edo a eliminar
     */
    public void closeVineYard(String name)
    {
        if (vi�edos.get(name)==null){
                JOptionPane.showMessageDialog(null,"El vi�edo que esta intentando eliminar no existe.");
                ok=false;
            }
        else{
                vi�edos.get(name).makeInvisible();
                vi�edos.remove(name);
                nombres.remove(name);
                ok=true;
        }
        
    }
    /**
     * Crea una lona
     * @name nombre del vi�edo
     * @ini punto en donde empieza la lona
     * @fin punto en donde termina la lona 
     */
    public void addTrap(int[] ini,int[] fin)
    {
        lonas.add(new Trap(ini,fin));
        ok=true;
    }
    /**
     * Elimina un vi�edo
     * @nombre el nombre del vi�edo a eliminar
     */
    public void removeTrap(int position)
    {
        if (lonas.get(position)==null){
                JOptionPane.showMessageDialog(null,"La lona que esta intentando eliminar no existe.");
                ok=false;
            }
        else{
                lonas.get(position).makeInvisible();
                lonas.remove(position);
                ok=true;
        }
        
    }
    /**
     * Hace visible el valle junto con todos sus objetos (vi�edos,lonas ,lluvia, etc). Si esta visible no hace nada.
     */
    public void makeVisible(){
        for(int i=0; i<vi�edos.size();i++){
            vi�edos.get(nombres.get(i)).makeVisible();
        }
        for(int i=0; i< lonas.size();i++){
            lonas.get(i).makeVisible();
        }
        ok = true;
    }
    public void makePuncture(int trap, int x){
        
        
    }
    /**
     * Hace invisible el valle junto con todos sus objetos (vi�edos,lonas ,lluvia, etc). Si esta invisible no hace nada.
     */
    public void makeInvisible(){
        for(int i=0; i<vi�edos.size();i++){
            vi�edos.get(nombres.get(i)).makeInvisible();
        }
        for(int i=0; i< lonas.size();i++){
            lonas.get(i).makeInvisible();
        }
        ok = true;
    }
    public void MakePuncture(int trap, int x ){
         
    }
    /**
     * Revisa si la acci�n de un metodo se realizo correctamente o no. 
     * @return boolean ok true si se realizo correctamente la acci�n false dlc. 
     */
    public boolean ok(){
        return ok;
    }
    /**
     * Termina el programa eliminando el valle y todo lo que contenga dentro. 
     */
    public void finish(){
        System.exit(0);
    }
}

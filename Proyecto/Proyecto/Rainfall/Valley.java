package Rainfall;

import Shapes.*;
import java.util.*;
import java.awt.Color;
import java.awt.*;
import java.awt.Graphics;
import javax.swing.JOptionPane;

/**
 * Clase valle en donde se encuentra viñedos juntos sus lonas
 * 
 * @author César Eduardo González y Brayan Santiango Bitrafo 
 * @version 29/08/2019
 */
public class Valley
{
    private int maxY; 
    private int maxX;
    private int x;
    private Shapes.Canvas canvas;
    private ArrayList<Trap> lonas= new ArrayList<Trap>();
    private Hashtable<String,VineYard> viñedos=new Hashtable<String,VineYard>();
    private ArrayList<String> nombres = new ArrayList<String>();
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
    }

    /**
     * Crea un viñedo 
     * @name nombre del viñedo
     * @xi posición x en donde empieza el viñedo
     * @xf posición x en donde termina el viñedo 
     */
    public void openVineYard(String name,int xi,int xf)
    {
        viñedos.put(name,new VineYard(name,xi, xf));
        nombres.add(name);
    }
    /**
     * Elimina un viñedo
     * @nombre el nombre del viñedo a eliminar
     */
    public void closeVineYard(String name)
    {
        if (viñedos.get(name)==null){
                JOptionPane.showMessageDialog(null,"El viñedo que esta intentando eliminar no existe.");
            }
        else{
                viñedos.get(name).makeInvisible();
                viñedos.remove(name);
                nombres.remove(name);
        }
        
    }
    /**
     * Crea una lona
     * @name nombre del viñedo
     * @ini punto en donde empieza la lona
     * @fin punto en donde termina la lona 
     */
    public void addTrap(int[] ini,int[] fin)
    {
        lonas.add(new Trap(ini,fin));
    }
    /**
     * Elimina un viñedo
     * @nombre el nombre del viñedo a eliminar
     */
    public void removeTrap(int position)
    {
        if (lonas.get(position)==null){
                JOptionPane.showMessageDialog(null,"La lona que esta intentando eliminar no existe.");
            }
        else{
                lonas.get(position).makeInvisible();
                lonas.remove(position);
        }
        
    }
    /**
     * Hace visible el valle junto con todos sus objetos (viñedos,lonas ,lluvia, etc). Si esta visible no hace nada.
     */
    public void makeVisible(){
        for(int i=0; i<viñedos.size();i++){
            viñedos.get(nombres.get(i)).makeVisible();
        }
        for(int i=0; i< lonas.size();i++){
            lonas.get(i).makeVisible();
        }
    }
    /**
     * Hace invisible el valle junto con todos sus objetos (viñedos,lonas ,lluvia, etc). Si esta invisible no hace nada.
     */
    public void makeInvisible(){
        for(int i=0; i<viñedos.size();i++){
            viñedos.get(nombres.get(i)).makeInvisible();
        }
        for(int i=0; i< lonas.size();i++){
            lonas.get(i).makeInvisible();
        }
    }
    public void MakePuncture(Trap ){
         
    }
}

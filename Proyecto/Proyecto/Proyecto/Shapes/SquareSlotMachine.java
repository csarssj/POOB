package Shapes;

import java.util.*;
import java.awt.Color;
import java.awt.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.applet.AudioClip;
/**
 * Write a description of class SquareSlotMachine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SquareSlotMachine
{
    // instance variables - replace the example below with your own
    private static int x = 0,y =0;
    private SlotMachine slot;
    private ArrayList <SlotMachine> Square= new ArrayList<SlotMachine>(); 
    protected AudioClip damage= java.applet.Applet.newAudioClip(getClass().getResource("/win.wav"));
    /**
     * Constructor for objects of class SquareSlotMachine
     */
    public SquareSlotMachine(int n)
    {
        // initialise instance variables
        for(int i=0;i<n;i++ ){
            slot = new SlotMachine(n,x,y);
            y += 100;
            Square.add(slot);
        }
        
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void pullAll()
    {
        for(int i=0;i<Square.size();i++){
            Square.get(i).pull();
        }
        damage.play();
    }
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void pullAll(int n)
    {
        for(int i=0;i<Square.size();i++){
            Square.get(i).pull(n);
        }
        damage.play();
    }
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void pull(int n)
    {
        Square.get(n).pull();
        damage.play();
    }
   /**
    * Aca puede verificar si se gano comparando los elementos
    * del arreglo de filas y si gano pues que suene
    * le voy a agregar que suene ya que usted esta pensando esto
    * ademas este deberia estar cada vez que gire para verificar
    * si ganó
    */
    private void gano(){
        damage.play();
    }
    public Boolean isWinningState(){
        boolean flag=false;
        for(int i=0;i<Square.size();i++){
            if(Square.get(i).isWinningState()) flag = true;
        }
        for(int i=0;i<Square.size();i++){
            
        } 
        return false;
    }
}

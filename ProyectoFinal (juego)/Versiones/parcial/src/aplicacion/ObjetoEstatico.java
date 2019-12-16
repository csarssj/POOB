package aplicacion;

import java.io.Serializable;

/**
 * Clase abstracta Objeto estatico, para los objetos que no tienen movimiento en el juego
 * @author Cesar Gonzalez y Brayan Buitrago
 * @version 4/12/2019
 */

public abstract class ObjetoEstatico implements Serializable
{
   protected float xPos; 
   protected float yPos; 
   protected float height;
   protected float width; 

   /**
    * Constructor para la clase objeto estatico
    * @param x posicion en coordenada x
    * @param y posicion en coordenada y
    * @param h alto del objeto
    * @param w ancho del objeto
    */
   public ObjetoEstatico(int x, int y, int h, int w) 
   {
       xPos = x;
       yPos = y;
       height = h;
       width = w;
   }
   
   //funciones que retornan parametros

   public float getXPos()
   {
       return xPos;
   }
   
   public void setXPos(float x)
   {
       xPos = x;
   }
   
   public float getYPos()
   {
       return yPos;
   }
   
   public void setYPos(float y)
   {
       yPos = y;
   }
   
   public float getHeight()
   {
       return height;
   }
   
   public float getWidth()
   {
       return width;
   }   
}
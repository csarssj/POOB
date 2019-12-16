package aplicacion;

import java.awt.Color;
import java.io.*;
/**
 * Dado es un elemento que estará en el salon, implementa de la interfaz EnSalon.
 * 
 * @author (Jimenez Eduard -Murillo Carlos) 
 * @version (1.1)
 */
public class Dado implements EnSalon,Serializable
{
    private int posicionx;
    private int posiciony;
    private int numero;
    private Salon salon;
    private static final int longitud = 40;
    private Color color;
    /**
     * Metodo Constructor de la clase Dado.
	 * @param salon se refiere al salon en el que se encuentra
	 * @param posicionx coordenada x de la posicion 
	 * @param posiciony coordenada y de la posicion
     */
    public Dado(Salon salon,String mensaje,int posicionx, int posiciony)
    {
        this.posicionx = posicionx;
        this.posiciony = posiciony;
        this.salon = salon;
        color = Color.BLUE;
        salon.adicione(this);
        numero = 0;
    }

    public void setPosicionX(int x){
        posicionx = x;
    }
    
    public void setPosicionY(int y){
        posiciony = y;
    }
    
    public int getLongitud(){
        return longitud;
    }
    
    /**
     * Metodo que da inicio al movimiento del Dado.
     */
    public void inicie(){
        if(numero%2!=0)
            color = Color.BLUE;
        else{
            color = Color.ORANGE;
        }
        numero++;
    }
    
    /**
     * El dado decide que hacer. 
     */
    public void decida(){
        if (r.nextBoolean()){
            inicie();
        }
    }
    
    /**
     * El dado cambia de color lo que representa que pare su acción.
     */
    public void pare(){
        color = Color.ORANGE;
    }
    
    public Color getColor(){
        return color;
    }
    
    public int getPosicionX(){
        return posicionx;
    }
    
    public int getPosicionY(){
        return posiciony;
    }
    
    /**
     * Retorna que tipo de elemento es el Dado, en este caso un Cuadrado.
     * @return String el tipo de forma que es el dado.
     */
    public String forma(){
        return EnSalon.FORMAS[2];
    }
}

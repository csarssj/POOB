package aplicacion;

import java.awt.Color;
import java.io.*;
/**
 * Clase Bola es un elemento que esta almacenado en el Salon, implementa la interfaz EnSalon.
 */
public class Bola implements EnSalon,Serializable{
    private int posicionx;
    private int posiciony;
    private Salon salon;
    private static final int diametro = 30;
    private Color color;
    /**
     * Metodo constructor de la clase Bola.
	 * @param salon se refiere al salon en el que se encuentra
	 * @param posicionx coordenada x de la posicion 
	 * @param posiciony coordenada y de la posicion
     */
    public Bola(Salon salon,String mensaje,int posicionx, int posiciony){
        this.posicionx = posicionx;
        this.posiciony = posiciony;
        this.salon = salon;
        color = Color.RED;
        salon.adicione(this);
    }
    
    public void setPosicionX(int x){
        posicionx = x;
    }
    
    public void setPosicionY(int y){
        posiciony = y;
    }
    
    public int getDiameter(){
        return diametro;
    }
    
    /**
     * Metodo que da inicio al movimiento de la Bola. 
     */
    public void inicie(){
        color = Color.RED;
    }
    
    /**
     * Metodo que para el movimiento de la Bola.
     */
    public void pare(){
        color = Color.GRAY;
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
}

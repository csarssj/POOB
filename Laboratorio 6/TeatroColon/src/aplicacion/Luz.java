package aplicacion;
import java.awt.*;
import java.io.Serializable;
/**
 * Clase Luz que es un elemento del escenario
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 3/10/2019
 */
public class Luz implements EnEscena,Serializable{
    private Teatro teatro;
    private Color color;
    private int x,x1,x2,y,y1,y2;
    private String nombre;
    private Boolean apagado;
    /**
     * Constructor de la luz
     * @param teatro en donde se trabaja
     * @param nombre de la luz a adicionar
     * @param  x lista de posiciones en x de la luz
     * @param  y lista de posiciones en y de las luz
     */
    public Luz(Teatro teatro, String name, int x,int x1,int x2, int y,int y1,int y2 ){
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.teatro = teatro;
        this.color = new Color(102,102,102);
        this.nombre = name;
        teatro.adicione(this);
        apagado=true;
    }
    /**
     * Retorna posicion en x de la luz
     * @Returns x[0] primer punto en x
     */
    public int getPosicionX(){
        return x;
    }
    /**
     * Retorna posicion en y de la luz
     * @Returns y[0] primer punto en y
     */
    public int getPosicionY(){
        return y;
    }
    /**
     * Retorna posiciones en x de la luz
     * @Returns x lista de posiciones en  x
     */
    public int getPosicionX1(){
        return x1;
    }
    /**
     * Retorna posiciones en y de la luz
     * @Returns y lista de posiciones en y
     */
    public int getPosicionY1(){
        return y1;
    }
    /**
     * Retorna posiciones en x de la luz
     * @Returns x lista de posiciones en  x
     */
    public int getPosicionX2(){
        return x2;
    }
    /**
     * Retorna posiciones en y de la luz
     * @Returns y lista de posiciones en y
     */
    public int getPosicionY2(){
        return y2;
    }
    /**
     * Retorna el color de la luz(para saber si esta prendida o apagada)
     * @Returns color de la luz
     */
    public Color getColor(){
        return color;
    }
    /**
     * Enciende la luz si los actores actuan
     */
    public void actue(){
        color = new Color(255,255,255);
        apagado=false;
    }
    /**
     * apaga la luz si los actores descansan
     */
    public void corte(){
        color = new Color(102,102,102);
        apagado=true;
    };
    @Override
    public String forma(){
       return "Luz";
    }
    /**
     * retorna si esta la luz prendida o apagada
     * @return apagado booleano que dice si esta prendido o apagado
     */
    public boolean apagado(){
        return apagado;
    }
    public String toString(){
		return nombre+" "+x+" "+x1+" "+x2+" "+y+" "+y1+" "+y2; 
	}
    
}

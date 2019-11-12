package aplicacion;
import java.awt.*;
/**
 * Clase Cortunas que es un elemento del escenario
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 3/10/2019
 */
public class Cortinas implements EnEscena{
    private Teatro teatro;
    private Color color;
    private int xIni;
    private int xFin;
    private String nombre;
    /**
     * Constructor de la cortina
     * @param teatro en donde se trabaja
     * @param nombre de la cortina a adicionar
     */
    public Cortinas(Teatro teatro,String name){
        
        this.teatro = teatro;
        this.color = Color.RED;
        this.nombre = name;
        if (nombre=="CortinaIzquierda"){
            xIni=0;
            
        }
        else if (nombre=="CortinaDerecha"){
            
            xFin=600;
        }

    }   
    /**
     * Retorna el color de la cortina
     * @Returns color de la cortina
     */
    public Color getColor(){
        return color;
    }
    /**
     * abre las cortinas
     */
    public void actue(){
        xIni=0;
        xFin=600;
    }
    /**
     * cierra las cortinas
     */
    public void corte(){
        xIni+=10;
        xFin-=10;
        if (xIni>xFin){
            //palabras="GRACIAS";
            mensaje();
        }
    };
    @Override
    public String forma(){
       return nombre;
    }
    /**
     * Retorna posicion en x de la cortina
     * @Returns xIni punto en x
     */
     public int getPosicionX(){
        return xIni;
    }
    /**
     * Retorna posicion en x de la cortina
     * @Returns xFin punto en x
     */
    public int getPosicionY(){
            return xFin;
    }
    /**
     * heredado de enEscena pero aca no se usa
     */
    public int[] getPosicionX1(){
        int[] y={};
        return y;
    }
    /**
     * heredado de enEscena pero aca no se usa
     */
    public int[] getPosicionY1(){
        int[] y={};
        return y;
    }


    
}

    

package aplicacion;
import java.awt.*;
import java.io.Serializable;
/**
 * Clase Cortunas que es un elemento del escenario
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 3/10/2019
 */
public class Cortinas implements EnEscena,Serializable{
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
    public Cortinas(Teatro teatro,String name,int ini){
        
        this.teatro = teatro;
        teatro.adicione(this);
        this.color = Color.RED;
        this.nombre = name;
        if (ini == 0){
            xIni=0;
            
        }
        else if (ini==600){
            
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
        xIni+=100;
        xFin-=100;
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
    public int getPosicionX1(){
        return 0;
    }
    /**
     * heredado de enEscena pero aca no se usa
     */
    public int getPosicionY1(){
        return 0;
    }

    /**
     * heredado de enEscena pero aca no se usa
     */
    public int getPosicionX2(){
        return 0;
    }
    /**
     * heredado de enEscena pero aca no se usa
     */
    public int getPosicionY2(){
        return 0;
    }
    public String toString(){
		return nombre+" "+xFin; 
	}
    

    
}

    

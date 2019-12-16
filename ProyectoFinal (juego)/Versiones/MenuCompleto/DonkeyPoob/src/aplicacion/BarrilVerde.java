package aplicacion;
import java.util.ArrayList;


/**
 * Clase barril verde el cual le otroga una vida a mario (extienede ObjetoMovible).
 * @author: Cesar Gonzalez y Brayan Buitrago
 * @version: 9/12/2019
 */
public class BarrilVerde extends Barril {
    
    private boolean direction;     //false - izquierda, true - derecha
    private boolean moveDownLadder; //necesita ser heredado de un objeto en movimiento
    boolean pointAwarded = false; //si el punto fue otorgado para este barril,por defecto false
    private int distanceFallen = 0; //realizar un seguimiento de la distancia caída para cambiar de direccion

    /**
     * Constructor del barril
     * @param x posicion en coordenada x
     * @param y posicion en coordenada y
     * @param h alto del objeto
     * @param w ancho del objeto
     * @param SOList lista objeto estatico
     */
    public BarrilVerde(int x, int y, int h, int w, ArrayList<ObjetoEstatico> SOList, boolean d) 
    {
        super(x, y, h, w, SOList,d);
        direction = true; //establecer direccion a la derecha - comenzando desde kong a la derecha
        xVel = 6;
    }

    
}

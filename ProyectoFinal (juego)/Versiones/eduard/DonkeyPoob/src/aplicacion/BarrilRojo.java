package aplicacion;
import java.util.ArrayList;


/**
 * Clase Barril rojo el cual baja sin tener en cuenta las plataformas (extienede ObjetoMovible).
 * @author Cesar Gonzalez y Brayan Buitrago
 * @version 4/12/2019
 */
public class BarrilRojo extends Barril {
    
    private boolean direction;     //false - izquierda, true - derecha
    private boolean moveDownLadder; //necesita ser heredado de un objeto en movimiento
    boolean pointAwarded = false; //si el punto fue otorgado para este barril,por defecto false
    private int distanceFallen = 0; //realizar un seguimiento de la distancia caida para cambiar de direccion

    /**
     * Constructor del barril
     * @param x posicion en coordenada x
     * @param y posicion en coordenada y
     * @param h alto del objeto
     * @param w ancho del objeto
     * @param SOList lista objeto estatico
     */
    public BarrilRojo(int x, int y, int h, int w, ArrayList<ObjetoEstatico> SOList, boolean d) 
    {
        super(x, y, h, w, SOList,d);
        direction = true; //establecer direccion a la derecha - comenzando desde kong a la derecha
        xVel = 1;
    }
    @Override
    public boolean standing() 
    {
        
        return false; //no esta quieto o parado en la plataforma
    }
    public void act(int time)
    {
        super.act(time);
    }


    
}

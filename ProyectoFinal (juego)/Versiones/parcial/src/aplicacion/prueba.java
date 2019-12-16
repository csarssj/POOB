package aplicacion;
import java.util.ArrayList;


/**
 * Clase Barril los cuales mario tiene que esquivar (extienede ObjetoMovible).
 * @author Cesar Gonzalez y Brayan Buitrago
 * @version 4/12/2019
 */
public class prueba extends Barril {
	
	private final float jumpHeight = 3f; //distancia que salta
	
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
    public prueba(int x, int y, int h, int w, ArrayList<ObjetoEstatico> SOList, boolean d) 
    {
        super(x, y, h, w, SOList,d);
        direction = true; //establecer direccion a la derecha - comenzando desde kong a la derecha
        xVel = 1;
    }
    @Override
    public void act(int time)
    {
        super.act(time);
        dy += -jumpHeight;
        if(distanceFallen > 0 && standing()) direction = !direction; //si cae por mas de 2 unidades de tiempo, cambie de direccion
        if(standing())
        {
            distanceFallen = 0; //restablecer la distancia de caída
            if(direction) dx = xVel;
            else dx = -xVel;
        }
        else
        {
            if(distanceFallen > 0) dx = 0f; //solo deje que un barril haga una pausa en su movimiento horizontal si cae una gran distancia
        }
        //update
        xPos += dx;
        yPos += dy;
        distanceFallen += dy;
    }

    
}

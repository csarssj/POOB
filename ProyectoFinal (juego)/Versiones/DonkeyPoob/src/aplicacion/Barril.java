package aplicacion;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Clase Barril los cuales mario tiene que esquivar (extienede ObjetoMovible).
 * @author Cesar Gonzalez y Brayan Buitrago
 * @version 4/12/2019
 */
public class Barril extends ObjetoMovimiento implements Serializable {
    
    private boolean direction;     //false - izquierda, true - derecha
    private boolean moveDownLadder; //necesita ser heredado de un objeto en movimiento
    boolean pointAwarded = false; //si el punto fue otorgado para este barril,por defecto false
    private int distanceFallen = 0; //realizar un seguimiento de la distancia caída para cambiar de dirección

    /**
     * Constructor del barril
     * @param x posicion en coordenada x
     * @param y posicion en coordenada y
     * @param h alto del objeto
     * @param w ancho del objeto
     * @param SOList lista objeto estatico
     */
    public Barril(int x, int y, int h, int w, ArrayList<ObjetoEstatico> SOList, boolean d) 
    {
        super(x, y, h, w, SOList);
        direction = true; //establecer dirección a la derecha - comenzando desde kong a la derecha
        xVel = 4;
    }

    /**
     * Verifica si un objeto esta parado en la plataforma
     * @return true o false
     */
    @Override
    public boolean standing() 
    {
        for(ObjetoEstatico SO : SOList)
        {
            if (!(SO instanceof Plataforma))
            {
                continue;
            }else if(SO instanceof PlataformaMagica) {
            	float l1 = xPos, r1 = xPos+width, b1 = yPos+height;
	            float l2 = SO.xPos, r2 = SO.xPos+SO.width, t2 = SO.yPos, b2 = SO.yPos+20;
	            if((b1 <= b2 && b1 >= t2) && r1 >= l2 && l1 <= r2) //+40 para situaciones en las que Mario podría estar ligeramente sobre la plataforma
	            { 	
	            	yPos = t2 - height; //hacer que el objeto se coloque exactamente encima de la plataforma
	                return true; //parado en una plataforma
	            }
            
            }else {
	            float l1 = xPos, r1 = xPos+width, b1 = yPos+height;
	            float l2 = SO.xPos, r2 = SO.xPos+SO.width, t2 = SO.yPos, b2 = SO.yPos+SO.height;
	            if((b1 <= b2 && b1 >= t2) && r1 >= l2 && l1 <= r2) //+40 para situaciones en las que Mario podría estar ligeramente sobre la plataforma
	            { 
	                yPos = t2 - height; //hacer que el objeto se coloque exactamente encima de la plataforma
	                return true; //parado en una plataforma
	            }
            }
        }
        return false; //no esta quieto o parado en la plataforma
    }
    public void act(int time)
    {
        super.act(time);
        dy = gravity * time;
        if(distanceFallen > 50 && standing()) direction = !direction; //si cae por más de 2 unidades de tiempo, cambie de dirección
        if(standing())
        {
            distanceFallen = 0; //restablecer la distancia de caída
            if(direction) dx = xVel;
            else dx = -xVel;
            dy +=10;
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

    //implementando todas las funciones abstractas para barril
    /**
     * dice si el barril va a la izquierda
     * @return direccion del barril
     */
    public boolean left() 
    {
        return !direction;
    }
    
    /**
     * dice si el barril va a la derecha
     * @return direccion del barril
     */
    public boolean right()
    {
        return direction;
    }
    /**
     * dice si el barril va hacia arriba
     * @return direccion del barril
     */
    public boolean up()
    {
        return false;
    }
    /**
     * dice si el barril va hacia abajo
     * @return direccion del barril
     */
    public boolean down()
    {
        return moveDownLadder;
    }
    
}

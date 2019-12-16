package aplicacion;
import java.util.ArrayList;


/**
 * Clase Barril Azul los cuales mario tiene que esquivar este es el unico que baja por las escaleras.
 * @author Cesar Gonzalez y Brayan Buitrago
 * @version 4/12/2019
 */
public class BarrilAzul extends Barril {

    private boolean direction;     //false - izquierda, true - derecha
    private boolean moveDownLadder; //necesita ser heredado de un objeto en movimiento
    boolean pointAwarded = false; //si el punto fue otorgado para este barril,por defecto false
    private int distanceFallen = 0; //realizar un seguimiento de la distancia caída para cambiar de dirección

    /**
     * Constructor del barril Azul
     * @param x posicion en coordenada x
     * @param y posicion en coordenada y
     * @param h alto del objeto
     * @param w ancho del objeto
     * @param SOList lista objeto estatico
     */
    public BarrilAzul(int x, int y, int h, int w, ArrayList<ObjetoEstatico> SOList, boolean d) 
    {
        super(x, y, h, w, SOList,d);
        direction = true; //establecer dirección a la derecha - comenzando desde kong a la derecha
        xVel = 2;
       
    }
    @Override
    public void act(int time)
    {	
        super.act(time);
        dy = gravity * time;
        if (standing() || collidingWithLadder == null) isClimbing = false;  //no suba a menos que colisione con la escalera
        if((collidingWithLadder != null)) isClimbing = true;
        if((distanceFallen > 50) && standing()) direction = !direction; //si cae por más de 2 unidades de tiempo, cambie de dirección
        if(isClimbing) {
        	dx=0f;//solo deje que un barril haga una pausa en su movimiento horizontal si cae una gran distancia
        }
        if(standing() && isClimbing) {//cambie de posicion y comience a bajar para no estar sobre la plataforma
        	dx=0f;	
          	yPos+=50;
        }
        else if(standing()){
        	distanceFallen = 0; //restablecer la distancia de caída
            if(direction) dx = xVel;
            else dx = -xVel;
        }
        else{
            if(distanceFallen > 0 ) dx = 0f; 
        }
        if (checkSOCollisions(SOList)){ // hubo una colisi�n
        	if (collidingWithLadder == null) yPos -= dy; //deshabilite el movimiento si el movimiento en y resultar�a en una colisi�n
        }
        //update
        xPos += dx;
        yPos += dy;
        distanceFallen += dy;
    }
    /**
     * Verifica las coliciones con objetos esaticos
     * @param SOList array de objetos estaticos
     * @return si hay colision o no
     */
    @Override
    public boolean checkSOCollisions(ArrayList<ObjetoEstatico> SOList) 
    {
        for(ObjetoEstatico SO : SOList)
        {
            float l1 = xPos, r1 = xPos+width, t1 = yPos, b1 = yPos+height; //obtener las coordenadas del lado izquierdo, derecho, superior e inferior del jugador
            float l2 = SO.xPos, r2 = SO.xPos+SO.width, t2 = SO.yPos-50, b2 = t2+SO.height; // establecer las coordenadas del lado izquierdo, derecho, superior e inferior del otro objetot
            if (!(l1>=r2 || l2>=r1 || t1>=b2 || t2>=b1) && t2 < b1 && b1 > b2) //condicion necesaria para la colision
            //if (t1==t2 && l1==l2 && r1==r2 && b1==b2) //condicion necesaria para la colision
            {	
            	//MOList.add(new BarrilAzul(510,250,20,25, SOList, true));
            	if (SO instanceof Escalera) collidingWithLadder = SO; //colision con la escalera
            	if (SO instanceof EscaleraSegmentada) collidingWithLadder = SO; //colison con la escalera segmentada
                return true; //return collision
            }
            collidingWithLadder = null;
        }	
        return false;
    }
    


    
}

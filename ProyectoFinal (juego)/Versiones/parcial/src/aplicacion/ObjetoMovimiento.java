package aplicacion;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase abstracta Objeto Movimiento, para los objetos que tienen movimiento en el juego (mario, barriles)
 * @author Cesar Gonzalez y Brayan Buitrago
 * @version 4/12/2019
 */


public abstract class ObjetoMovimiento extends ObjetoEstatico implements Serializable {
    protected float xVel; //velocidad en x
    protected float yVel; //velocidad en y
    protected float dx; //cambio en la coordenada en x
    protected float dy; //cambio en la coordenada en y
    

    protected boolean collidingWithMagic=false; //barril toca plataforma magica
    protected boolean collidingWithGreen=false; //sorpresa, con la cual mario choca
    protected boolean collidingWithRope=false; //sorpresa, con la cual mario choca
    protected boolean collidingWithCherry=false; //sorpresa, con la cual mario choca
    protected boolean collidingWithHeart=false; //sorpresa, con la cual mario choca
    protected boolean collidingWithApple=false;	//sorpresa, con la cual mario choca
    protected boolean collidingWithHammer=false; //sorpresa, con la cual mario choca
    protected boolean invert=false;
    protected boolean isClimbing = false; //para mario
    protected boolean collidingWithPeach = false; //verdadero si hay colision con peach, para mario
    protected ObjetoEstatico collidingWithLadder; //escalera, con la cual mario choca
    protected float gravity = 2; //gravedad del objeto en movimiento actual
    protected boolean pointAwarded = false; //solo puede otorgarse un punto por barril
    protected ArrayList<ObjetoEstatico> SOList; //para verificar colisiones

    /**
     * Construtor para la clase objeto Movible
     * @param x posicion en coordenada x
     * @param y posicion en coordenada y
     * @param h ancho del objeto
     * @param w alto del objeto
     * @param SOList lista objeto estatico
     */
    public ObjetoMovimiento(int x, int y, int h, int w, ArrayList<ObjetoEstatico> SOList) 
    {
        super(x, y, h, w);	
        this.SOList = SOList;
    }
    /**
     * Cambia la coordenada del objeto en un instante(o tiempo).
     * @param time fragmento del tiempo
     */
    public void act(int time)
    {
        if (standing()) dy = 0; //si esta parado no cambia la coordenada en y
        if (!isClimbing) dy += gravity * time; //al escalar, cambia la coordenada en y
    }
    //Instrucciones a implementar en subclases
    public abstract boolean left();
    public abstract boolean right();
    public abstract boolean up();
    public abstract boolean down();
    
    /**
     * Verifica si un objeto esta parado en la plataforma
     * @return true o false
     */
    public boolean standing() 
    {
        for(ObjetoEstatico SO : SOList)
        {
            if ((SO instanceof Plataforma)||(SO instanceof PlataformaMagica))
            {
            	float l1 = xPos, r1 = xPos+width, b1 = yPos+height;
                float l2 = SO.xPos, r2 = SO.xPos+SO.width, t2 = SO.yPos, b2 = SO.yPos+SO.height;
                if((b1 <= b2 && b1 >= t2) && r1 >= l2 && l1 <= r2) //+40 para situaciones en las que Mario podría estar ligeramente sobre la plataforma
                { 
                    yPos = t2 - height; //hacer que el objeto se coloque exactamente encima de la plataforma
                    return true; //parado en una plataforma
                }
            }else {
            	continue;
            }
            
        }
        return false; //no esta quieto o parado en la plataforma
    }
    /**
     * Verifica las coliciones con objetos esaticos
     * @param SOList array de objetos estaticos
     * @return si hay colision o no
     */
    public boolean checkSOCollisions(ArrayList<ObjetoEstatico> SOList) 
    {
        for(ObjetoEstatico SO : SOList)
        {
            float l1 = xPos, r1 = xPos+width, t1 = yPos, b1 = yPos+height; //obtener las coordenadas del lado izquierdo, derecho, superior e inferior del jugador
            float l2 = SO.xPos, r2 = SO.xPos+SO.width, t2 = SO.yPos, b2 = SO.yPos+SO.height; // establecer las coordenadas del lado izquierdo, derecho, superior e inferior del otro objetot
            if (!(l1>=r2 || l2>=r1 || t1>=b2 || t2>=b1) && t2 < b1 && b1 > b2) //condicion necesaria para la colision
            {
            	if (!(l1>=r2 || l2>=r1 || t1>=b2 || t2>=b1) && t2 < b1 && b1 > b2) {
            		if (SO instanceof Escalera) collidingWithLadder = SO; //colision con la escalera
            	}
                if (SO instanceof Peach) collidingWithPeach = true; //colision con Peach, por lo tanto, ganamos
                if (SO instanceof Hongo) {invert = true;SOList.remove(SO);} //colision con Hongo, por lo tanto, invierte controles
                if (SO instanceof Martillo) {collidingWithHammer = true;SOList.remove(SO);} //colision con Martillo, por lo tanto, destruye barriles
                if (SO instanceof Manzana) {collidingWithApple = true;SOList.remove(SO);} //colision con Manzanas, por lo tanto, gana cinco puntos
                if (SO instanceof Cereza) {collidingWithCherry = true;SOList.remove(SO);} //colision con Cereza, por lo tanto, gana diez puntos
                if (SO instanceof Corazon) {collidingWithHeart = true;SOList.remove(SO);} //colision con Corazon, por lo tanto, gana una vida extra
                if (SO instanceof Soga) {collidingWithRope = true;SOList.remove(SO);} //colision con Corazon, por lo tanto, gana una vida extra
                
                return true; //return collision
            }
        }	
        //no hay conlision,(ni con la princesa ni escalera)
        collidingWithLadder = null;
        collidingWithPeach = false;
        return false;
    }
    
    /**
     * Verifica las coliciones con objetos en movimiento
     * @param MOList array de objetos en movimiento
     * @return
     */
    public boolean checkMOCollision(ArrayList<ObjetoMovimiento> MOList) 
    {
        for(int i = 1; i < MOList.size(); i++)
        {
            ObjetoMovimiento MO = MOList.get(i);
            float l1 = xPos, r1 = xPos+width, t1 = yPos, b1 = yPos+height; //establecer las coordenadas del lado izquierdo, derecho, superior e inferior del jugador
            float l2 = MO.xPos, r2 = MO.xPos+MO.width, t2 = MO.yPos, b2 = MO.yPos+MO.height; // establecer las coordenadas del lado izquierdo, derecho, superior e inferior del otro objeto
            if (!(l1>=r2 || l2>=r1 || t1>=b2 || t2>=b1)) return true; //condicion para colision, game over
        }
        return false; //no hay colision
    }
    
    public void setPointAwarded()
    {
        pointAwarded = true;
    }
    
    public boolean pointAwarded()
    {
        return pointAwarded;
    }
    
}

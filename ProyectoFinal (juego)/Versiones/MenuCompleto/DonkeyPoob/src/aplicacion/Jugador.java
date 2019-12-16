package aplicacion;

import java.util.ArrayList;

/**
 * Clase Jugador rescatador de la princesa de las manos de DonkeyKong
 * @author: Cesar Gonzalez y Brayan Buitrago
 * @version: 9/12/2019
 */
public class Jugador extends ObjetoMovimiento {
   
    //constantes
    private final float jumpHeight = 3.6f; //altura que jugador salta
    
    //booleanos que nos dicen que teclas fueron presionadas
    private boolean goLeft;
    private boolean goRight;
    private boolean goUp;
    private boolean goDown;
    private boolean jump; //espacio fue presionado
    private boolean enter;
    
    private boolean keysDown[] = new boolean[255]; //Array de booleanos que nos dice qué tecla se presiona
    public boolean jumping; //si jugador esta� saltando o no
    private boolean hasWon = false; //si se gano, falso por defecto
    
    /**
     * Constructor de jugador
     * @param x posicion en coordenada x
     * @param y posicion en coordenada y
     * @param h alto del objeto
     * @param w ancho del objeto
     * @param SOList lista objeto estatico
     */
    public Jugador(int x, int y, int h, int w, ArrayList<ObjetoEstatico> SOList) 
    {
        super(x, y, h, w, SOList);
        xVel = 5f;
        yVel = 5f;
    }
    
    /**
     * Coloca todos los parametros de jugador por default
     */
    public void setToDefault() 
    {
        this.goLeft = false;
        this.goRight = false;
        this.goUp = false;
        this.goDown = false;
        this.jump = false;
        for (int i = 0; i < 255;i++) this.keysDown[i] = false; 
        this.jumping = false;
        this.hasWon = false;
    }

    @Override
    public void act(int time)
    {
        //inicializar cambios
        dx = 0;
        dy = 0;
        //llama a la funcion  super act para la gravedad y pararse en la plataforma
        super.act(time);
        readInput();
        if(jump && !jumping && standing()) jumping = true; //empieza a saltar
        if (standing() || collidingWithLadder == null) isClimbing = false;  //no suba a menos que colisione con la escalera
        if((collidingWithLadder != null)) isClimbing = true; //Si jugador está colisionando con la escalera, es verdadero
        dx += (goRight ? xVel : 0) - (goLeft ? xVel : 0); //cambiar el delta x
        if (isClimbing) dy += (goDown ? yVel : 0) - (goUp ? yVel : 0); //si jugador está subiendo, cambie delta y
        if(jumping) dy += -jumpHeight; //Si jugador está saltando, cambie la coordenada y
        xPos += dx; 
        yPos += dy;
        if (collidingWithLadder != null && !standing()) xPos -= dx; //desactivar la caída de la escalera
        if (checkSOCollisions(SOList)) // hubo una colision
        {
        	if(collidingWithRope) {yPos -=110;collidingWithRope=false;}
            if (collidingWithPeach) hasWon = true; //si jugador ha alcanzado a peach, el juego ha terminado
            if (collidingWithLadder == null) yPos -= dy; //deshabilite el movimiento si el movimiento en y resultaría en una colision
        }
        if (this.xPos >= 230 && this.xPos <=270 && this.yPos <= 130 && this.yPos >= 90) hasWon = true; //compruebe si no está cerca de peach, para que no tenga que estar exactamente en la misma posicion, peach está en 250, 105
    }
    @Override
    public boolean checkMOCollision(ArrayList<ObjetoMovimiento> MOList) 
    {
        for(int i = 1; i < MOList.size(); i++)
        {
            ObjetoMovimiento MO = MOList.get(i);
            if(MO instanceof Barril) {
            	if(MO instanceof BarrilVerde) {
    	            float l1 = xPos, r1 = xPos+width, t1 = yPos, b1 = yPos+height; //establecer las coordenadas del lado izquierdo, derecho, superior e inferior del jugador
    	            float l2 = MO.xPos, r2 = MO.xPos+MO.width, t2 = MO.yPos, b2 = MO.yPos+MO.height; // establecer las coordenadas del lado izquierdo, derecho, superior e inferior del otro objeto
    	            if (!(l1>=r2 || l2>=r1 || t1>=b2 || t2>=b1)) {collidingWithGreen=true;return true;}  //condicion para colision, game over
                }
            	else {
		            float l1 = xPos, r1 = xPos+width, t1 = yPos, b1 = yPos+height; //establecer las coordenadas del lado izquierdo, derecho, superior e inferior del jugador
		            float l2 = MO.xPos, r2 = MO.xPos+MO.width, t2 = MO.yPos, b2 = MO.yPos+MO.height; // establecer las coordenadas del lado izquierdo, derecho, superior e inferior del otro objeto
		            if (!(l1>=r2 || l2>=r1 || t1>=b2 || t2>=b1)) {collidingWithGreen=false;return true;} //condicion para colision, game over
            	}
            }
            
        }
        return false; //no hay colision
    }
    
    /**
     * Lectura de las teclas
     */
    public void readInput()
    {
    }

    /**
     * Ingresan las teclas presionadas del modo de juego.
     * @param down arreglo de booleanos que nos dice que tecla fue presionada
     */
    public void setKeysDown(boolean[] down) 
    {
        keysDown = down;
    }
    
    //funciones que retornan parametros
    /**
     * dice si el jugador va hacia la izquierda
     * @return direccion del jugador
     */
    @Override
    public boolean left() 
    {
        return goLeft;
    }
    /**
     * dice si el jugador va hacia la derecha
     * @return direccion del jugador
     */
    @Override
    public boolean right() 
    {
        return goRight;
    }
    /**
     * dice si el jugador va hacia arriba
     * @return direccion del jugador
     */
    @Override
    public boolean up() 
    {
        return goUp;
    }
    /**
     * dice si el jugador va hacia abajo
     * @return direccion del jugador
     */
    @Override
    public boolean down() 
    {
        return goDown;
    }
    /**
     * cambia el estado si jugador salta
     * @param b boolean
     */
    public void setJump(boolean b)
    {
        jumping = b;
    }
    /**
     * cambia el estado si jugador toma el hongo
     * @param b boolean
     */
    public void setInvert(boolean b)
    {
        invert = true;
    }
    /**
     * Verifica si jugador gano
     * @return hasWon
     */
    public boolean hasWon()
    {
        return hasWon;
    }

}


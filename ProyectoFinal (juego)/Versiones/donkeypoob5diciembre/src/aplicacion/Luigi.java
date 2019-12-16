package aplicacion;

import java.util.ArrayList;

/**
 * Clase Luigi rescatador de la princesa de las manos de DonkeyKong
 * @author Cesar Gonzalez y Brayan Buitrago
 * @version 4/12/2019
 */
public class Luigi extends Mario {
   
    //constantes
    private final float jumpHeight = 3.6f; //altura que mario salta
    
    //booleanos que nos dicen que teclas fueron presionadas
    private boolean goLeft;
    private boolean goRight;
    private boolean goUp;
    private boolean goDown;
    private boolean jump; //espacio fue presionado
    private boolean enter;
    
    private boolean keysDown[] = new boolean[255]; //Array de booleanos que nos dice que tecla se presiona
    public boolean jumping; //si Luigi esta� saltando o no
    private boolean hasWon = false; //si se gano, falso por defecto
    
    /**
     * Constructor de luigi
     * @param x posicion en coordenada x
     * @param y posicion en coordenada y
     * @param h alto del objeto
     * @param w ancho del objeto
     * @param SOList lista objeto estatico
     */
    public Luigi(int x, int y, int h, int w, ArrayList<ObjetoEstatico> SOList) 
    {
        super(x, y, h, w, SOList);
        xVel = 5f;
        yVel = 5f;
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
        if((collidingWithLadder != null)) isClimbing = true; //Si Mario está colisionando con la escalera, es verdadero
        dx += (goRight ? xVel : 0) - (goLeft ? xVel : 0); //cambiar el delta x
        if (isClimbing) dy += (goDown ? yVel : 0) - (goUp ? yVel : 0); //si Mario está subiendo, cambie delta y
        if(jumping) dy += -jumpHeight; //Si Mario está saltando, cambie la coordenada y
        xPos += dx; 
        yPos += dy;
        if (collidingWithLadder != null && !standing()) xPos -= dx; //desactivar la caída de la escalera
        if (checkSOCollisions(SOList)) // hubo una colision
        {
            if (collidingWithPeach) hasWon = true; //si Mario ha alcanzado a peach, el juego ha terminado
            if (collidingWithLadder == null) yPos -= dy; //deshabilite el movimiento si el movimiento en y resultaría en una colision
        }
        if (this.xPos >= 230 && this.xPos <=270 && this.yPos <= 130 && this.yPos >= 90) hasWon = true; //compruebe si no está cerca de peach, para que no tenga que estar exactamente en la misma posicion, peach está en 250, 105
    }
    /**
     * Coloca todos los parametros de mario por default
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
    /**
     * Lectura de las teclas
     */
    @Override
    public void readInput()
    {
        if(invert) {
        	goLeft = keysDown[65]; 
            goRight = keysDown[68]; 
            goUp = keysDown[87];
            goDown = keysDown[83]; 
            jump = keysDown[71]; 
            enter = keysDown[13];
    	}else {
    		goLeft = keysDown[68]; 
            goRight = keysDown[65]; 
            goUp = keysDown[83];
            goDown = keysDown[87]; 
            jump = keysDown[13]; 
            enter = keysDown[71];
    	}
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
    
    @Override
    public boolean left() 
    {
        return goLeft;
    }

    @Override
    public boolean right() 
    {
        return goRight;
    }

    @Override
    public boolean up() 
    {
        return goUp;
    }

    @Override
    public boolean down() 
    {
        return goDown;
    }
    public void setJump(boolean b)
    {
        jumping = b;
    }

}


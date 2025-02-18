package aplicacion;

import java.util.ArrayList;

public class JugadorProtector extends Jugador{
    private final float jumpHeight = 4.6f; //altura que mario salta
    
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
	
	
	private String tipo;
	public JugadorProtector(int x, int y, int h, int w, ArrayList<ObjetoEstatico> SOList){
		super(x,y,h,w,SOList);
		xVel = 5f;
        yVel = 5f;
        tipo="mimo";
        System.out.println("ENTRA EN SUPERMACHO BRO");
        
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
	        goLeft = keysDown[39]; 
	        goRight = keysDown[37];
	        goUp = keysDown[40];
	        goDown = keysDown[38]; 
	        jump = keysDown[32]; 
	        enter = keysDown[13];
    	}else {
    		goLeft = keysDown[37]; 
 	        goRight = keysDown[39]; 
 	        goUp = keysDown[38];
 	        goDown = keysDown[40]; 
 	        jump = keysDown[32]; 
 	        enter = keysDown[13];
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
    @Override
    public void setInvert(boolean b)
    {
        invert = true;
    }
    public void setJump(boolean b)
    {
        jumping = b;
    }
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String type) {
		tipo=type;
	}
		
}

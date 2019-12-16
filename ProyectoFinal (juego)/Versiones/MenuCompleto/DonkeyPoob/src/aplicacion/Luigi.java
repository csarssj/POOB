package aplicacion;

import java.util.ArrayList;

/**
 * Clase Luigi rescatador de la princesa de las manos de DonkeyKong
 * @author: Cesar Gonzalez y Brayan Buitrago
 * @version: 9/12/2019
 */
public class Luigi extends Jugador {
   
    //constantes
    private final float jumpHeight = 4.6f; //altura que luigi salta
    
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
        readInput();
        //llama a la funcion  super act para la gravedad y pararse en la plataforma
        if (standing()) {

            if(standing() && collidingWithLadder != null) isClimbing=true;
            else dy = 0; //si esta parado no cambia la coordenada en y
        }
        if (!isClimbing) dy += gravity * time; //al escalar, cambia la coordenada en y
        if(jump && !jumping && standing()) jumping = true; //empieza a saltar
        if (collidingWithLadder == null) isClimbing = false;  //no suba a menos que colisione con la escalera
        if((collidingWithLadder != null)) isClimbing = true; //Si luigi está colisionando con la escalera, es verdadero
        dx += (goRight ? xVel : 0) - (goLeft ? xVel : 0); //cambiar el delta x
        if (isClimbing) dy += (goDown ? yVel : 0) - (goUp ? yVel : 0); //si luigi está subiendo, cambie delta y
        if(jumping) dy += -jumpHeight; //Si luigi está saltando, cambie la coordenada y
        xPos += dx; 
        yPos += dy;
        if (collidingWithLadder != null && !standing()) xPos -= dx; //desactivar la caída de la escalera
        if (checkSOCollisions(SOList)) // hubo una colision
        {
        	if(collidingWithRope) {yPos -=120;collidingWithRope=false;}
            if (collidingWithPeach) hasWon = true; //si luigi ha alcanzado a peach, el juego ha terminado
            if (collidingWithLadder == null) yPos -= dy; //deshabilite el movimiento si el movimiento en y resultaría en una colision
        }
        if (this.xPos >= 230 && this.xPos <=270 && this.yPos <= 130 && this.yPos >= 90) hasWon = true; //compruebe si no está cerca de peach, para que no tenga que estar exactamente en la misma posicion, peach está en 250, 105 }
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
            if (SO instanceof Escalera) {
            	float l3 = SO.xPos, r3 = SO.xPos+SO.width, t3 = SO.yPos-20, b3 = SO.yPos+SO.height; // establecer las coordenadas del lado izquierdo, derecho, superior e inferior del otro objetot
            	if (!(l1>=r3 || l3>=r1 || t1>=b3 || t3>=b1) ) {
            		if (SO instanceof Escalera) collidingWithLadder = SO; //colision con la escalera
            		return true; //return collision
            	}
            }
            float l2 = SO.xPos, r2 = SO.xPos+SO.width, t2 = SO.yPos, b2 = SO.yPos+SO.height; // establecer las coordenadas del lado izquierdo, derecho, superior e inferior del otro objetot
            if (!(l1>=r2 || l2>=r1 || t1>=b2 || t2>=b1) && t2 < b1 && b1 > b2) //condicion necesaria para la colision
            {
            	
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
     * Verifica si un objeto esta parado en la plataforma
     * @return true o false
     */
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
	            if((b1 <= b2 && b1 >= t2) && r1 >= l2 && l1 <= r2) //+40 para situaciones en las que luigi podría estar ligeramente sobre la plataforma
	            { 	if(!isClimbing) {
	                	yPos = t2 - height; //hacer que el objeto se coloque exactamente encima de la plataforma
	            	}
	            	return true; //parado en una plataforma
	            }
            
            }else {
	            float l1 = xPos, r1 = xPos+width, b1 = yPos+height;
	            float l2 = SO.xPos, r2 = SO.xPos+SO.width, t2 = SO.yPos, b2 = SO.yPos+SO.height;
	            if((b1 <= b2 && b1 >= t2) && r1 >= l2 && l1 <= r2) //+40 para situaciones en las que luigi podría estar ligeramente sobre la plataforma
	            { 	
	            	if(!isClimbing){
		                yPos = t2 - height; //hacer que el objeto se coloque exactamente encima de la plataforma
	            	}
	            	return true; //parado en una plataforma
	            }
            }
        }
        return false; //no esta quieto o parado en la plataforma
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
     * Coloca todos los parametros de luigi por default
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
    		goLeft = keysDown[68]; 
            goRight = keysDown[65]; 
            goUp = keysDown[83];
            goDown = keysDown[87]; 
            jump = keysDown[71]; 
            enter = keysDown[13];
    	}else {
    		goLeft = keysDown[65]; 
            goRight = keysDown[68]; 
            goUp = keysDown[87];
            goDown = keysDown[83]; 
            jump = keysDown[71]; 
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

}


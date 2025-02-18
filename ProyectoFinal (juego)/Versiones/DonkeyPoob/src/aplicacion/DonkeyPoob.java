package aplicacion;

import presentacion.*;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JOptionPane;

import persistencia.donkeyDAO;

/**
 * Clase DonkeyPoob ejecuta el juego, prepara todos los elementos y ejecuta la parte logica.
 * @author Cesar Gonzalez y Brayan Buitrago
 * @version 4/12/2019
*/
public final class DonkeyPoob extends Observable implements Serializable {
    
    //constantes
    final int SCREEN_X = 640; //ancho de la pantalla
    final int SCREEN_Y = 680; //alto de la pantalla
    final int platform_HEIGHT = 20; //alto de la plataforma
    final int platform_WIDTH = 30; //ancho de la plataforma
    final private int gravityTime = 10; //manejar la gravedad cada 10 milisegundos
    final private int barrelSpawnTime = 150; //barilles aparecen cada 150 milisegundos
    
    private static DonkeyPoob donkeypoob=null;
    public boolean wait = false; //si este modelo debe pausarse
    public String username; //nombre de usuario que se preguntara al final del juego
    public int score; //puntaje actual
    public int score2; //puntaje actual
    public boolean gameOver; //true si el juego termina
    private ArrayList<ObjetoEstatico> SOList; //todos los ojetos estaticos
    public ArrayList<ObjetoMovimiento> MOList; //todos los objetos en movimiento
    private int timer = 0; //tiempo actual, comienza en 0
    public String model; //modo de juego
    public String personaje; //seleccion de personaje
    
    private int epochs; //una variable utilizada solo para iterar y finalizar el juego si lleva demasiado tiempo
    public ArrayList<Integer> gravityTimes; //tiempo de gravedad para cada objeto en movimiento: cada objeto con indice i en MO tiene el tiempo de gravedad en esta lista en el indice i
    public int life = 3; //vida jugador1
    public int life2 = 3; //vida jugador 2
    private Mario mario; //jugador
    private Luigi luigi; //jugador 2
    private Peach peach; //princesa
    private Thread thread;
    private PantallaJuego frame;
    
    
    public DonkeyPoob(String model) 
    {
        this.model=model;
        SOList = new ArrayList<>();
        MOList = new ArrayList<>();
    	initGame();	
    }
    public ArrayList<ObjetoEstatico> gerSOList(){
    	return SOList;
    }
    public ArrayList<ObjetoMovimiento> gerMOList(){
    	return MOList;
    }
    /**
     * Construye un nuevo Donkey, borrando el anterior
	 * @param random El valor booleano que determina si el juego esta generando los niveles de forma aleatoria. 
	 * @param cpu El valor booleano que determina si el juego esta juagndo con una cpu. 
    */
	public static void newDonkey(String random){
		donkeypoob = new DonkeyPoob(random);
	}
	/**
     * Consigue un nuevo DonkeyPoob
	 * @param random El valor string modelo de juego 
	 * @param cpu El valor booleano que determina si el juego esta juagndo con una cpu. 
    */
	public static DonkeyPoob giveMe(String random){
		if(donkeypoob ==null){
			donkeypoob = new DonkeyPoob(random);
		}
		return donkeypoob ;
	}
	/**
	 * Hilo que hace que los objetos se muevan y maneja la logica del juego
	 * @param frame
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
    public boolean startGame(PantallaJuego frame) throws InterruptedException, IOException
    {	
    	this.frame=frame;
        while(epochs < 10000) //despues de que epochs sea 10000, el juego terminara
        {
            while (wait == true) //bucle para JFrame: cuando la espera es verdadera, el juego esta "en pausa"
            {
                Thread.sleep(1);
            }
            epochs++;
            timer++;   
            if(mario.collidingWithHeart) {life ++;mario.collidingWithHeart=false;}
            if(timer % gravityTime == 0) incrementTime(); //manejar la gravedad cada 10 milisegundos
            if(timer % barrelSpawnTime == 0) spawnBarrel2(); //generar un barril cada 150 milisegundos      
            if(timer > 1500) timer = 0; //restablecer el temporizador eventualmente, para evitar el overflow
            if(!checkMOList2()) {
            	return false;
            }
            Painter tempPanel = frame.getGamePanel(); //obtener el panel del frame
            tempPanel.repaint(); //repaint el panel
            frame.add(tempPanel); //mostrar el panel en el frame
            thread.sleep(15);
        }
        return true;
    }
    private boolean checkMOList() throws InterruptedException {
    	for(int i = 0; i < MOList.size(); i++)
        {
            //hacer que todos los objetos en movimiento actuen / se muevan
            MOList.get(i).act(gravityTimes.get(i));
            if(mario.hasWon()) //mario ha ganado
            {
                if (!continueAfterWin()) return false;
            }
            if(mario.checkMOCollision(MOList)) //Mario es golpeado por un barril
            {
            	if(mario.collidingWithHammer) {
            		if(MOList.get(i) instanceof Mario) continue;
            		else {
            			//golpea martillo y desaparece de la pantalla
            			MOList.remove(i);
            			gravityTimes.remove(i);
            		}
            	}
            	else if(mario.collidingWithGreen) {
            		//adiciona vida el barril verde
            		life ++;
            		if(MOList.get(i) instanceof Mario) continue;
            		else {
            			//golpea barril verde busca otrogar una vida y desaparecer
            			MOList.remove(i);
            			gravityTimes.remove(i);
            		}
            	}
            	else {
                    //mario murio
            		if(life<0) {
	                    endLostGame(); //funcion para saber si perdio
	                    if (gameOver) //el jugador ha elegido no volver a jugar
	                    {
	                        frame.dispose(); //cerrar el marco -> volver al menu principal
	                        return false; //declaraciones de retorno para la funcion menuPanel
	                    }
	                    else return true;
            		}else {
            			life--;
            			initGame();
            			thread.sleep(30);
            		}
            	}
            }
            else if(MOList.get(i).getYPos() >= SCREEN_Y) //eliminar objeto si esta fuera de la pantalla
            {
                if (mario.equals(MOList.get(i))) //Mario se salio de la pantalla
                { //juego perdido
                    endLostGame(); //funcion de juego perdido
                    if (gameOver) //el jugador ha elegido no volver a jugar
                    {
                    	frame.dispose(); //cerrar el marco -> volver al menu principal
                        return false; //declaraciones de retorno para la funcion menuPane
                    }
                    else return true;
                }
                else
                {
                    //el objeto que cae de la pantalla es un barril, por lo tanto, retire el barril y es el tiempo de gravedad fuera de la pantalla
                    MOList.remove(i);
                    gravityTimes.remove(i);
                }
            }
            //aumentar la puntuacion al saltar sobre el barril
            else checkForPoints(MOList.get(i));
        }
    	return true;
    }
    /**
	 * Hilo que hace que los objetos se muevan y maneja la logica del juego (planeado para multijugador estado prueba)
	 * @param frame
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
    public boolean startGame2(PantallaJuego frame) throws InterruptedException, IOException
    {
        while(epochs < 10000) //despues de que epochs sea 10000, el juego terminara
        {
            while (wait == true) //bucle para JFrame: cuando la espera es verdadera, el juego esta "en pausa"
            {
                thread.sleep(1);
            }
            epochs++;
            timer++;   
            if(mario.collidingWithHeart) {life ++;mario.collidingWithHeart=false;}
            if(timer % gravityTime == 0) incrementTime(); //manejar la gravedad cada 10 milisegundos
            if(timer % barrelSpawnTime == 0) spawnBarrel2(); //generar un barril cada 150 milisegundos      
            if(timer > 1500) timer = 0; //restablecer el temporizador eventualmente, para evitar el overflow
            if(!checkMOList2()) {
            	return false;
            }
            Painter tempPanel = frame.getGamePanel(); //obtener el panel del frame
            tempPanel.repaint(); //repaint el panel
            frame.add(tempPanel); //mostrar el panel en el frame
            thread.sleep(15);
        }
        return true;
    }
    private boolean checkMOList2() throws InterruptedException {
    	for(int i = 0; i < MOList.size(); i++)
        {
            //hacer que todos los objetos en movimiento actuen / se muevan
            MOList.get(i).act(gravityTimes.get(i));
            if(mario.hasWon()) //mario ha ganado
            {
                if (!continueAfterWin()) return false;
            }
            if(luigi.hasWon()) //mario ha ganado
            {
                if (!continueAfterWin()) return false;
            }
            if(mario.checkMOCollision(MOList)) //Mario es golpeado por un barril
            {
            	if(mario.collidingWithHammer) {
            		if(MOList.get(i) instanceof Mario) continue;
            		else {
            			MOList.remove(i);
            			gravityTimes.remove(i);
            		}
            	}
            	else if(mario.collidingWithGreen) {
            		life ++;
            		if(MOList.get(i) instanceof Mario) continue;
            		else {
            			MOList.remove(i);
            			gravityTimes.remove(i);
            		}
            	}
            	else {
                    //mario murio
            		if(life<0) {
	                    endLostGame(); //funcion para saber si perdio
	                    if (gameOver) //el jugador ha elegido no volver a jugar
	                    {
	                        frame.dispose(); //cerrar el marco -> volver al menu principal
	                        return false; //declaraciones de retorno para la funcion menuPanel
	                    }
	                    else return true;
            		}else {
            			life--;
            			initGame();
            			thread.sleep(30);
            		}
            	}
            }
            if(luigi.checkMOCollision(MOList)) //Luigi es golpeado por un barril
            {
            	if(luigi.collidingWithHammer) {
            		if(MOList.get(i) instanceof Luigi) continue;
            		else {
            			MOList.remove(i);
            			gravityTimes.remove(i);
            		}
            	}
            	else if(luigi.collidingWithGreen) {
            		life2 ++;
            		if(MOList.get(i) instanceof Luigi) continue;
            		else {
            			MOList.remove(i);
            			gravityTimes.remove(i);
            		}
            	}
            	else {
                    //luigi murio
            		if(life2<0) {
	                    endLostGame(); //funcion para saber si perdio
	                    if (gameOver) //el jugador ha elegido no volver a jugar
	                    {
	                        frame.dispose(); //cerrar el marco -> volver al menu principal
	                        return false; //declaraciones de retorno para la funcion menuPanel
	                    }
	                    else return true;
            		}else {
            			life2--;
            			initGame();
            			thread.sleep(30);
            		}
            	}
            }
            else if(MOList.get(i).getYPos() >= SCREEN_Y) //eliminar objeto si esta fuera de la pantalla
            {
                if (mario.equals(MOList.get(i))) //Mario se salio de la pantalla
                { //juego perdido
                    endLostGame(); //funcion de juego perdido
                    if (gameOver) //el jugador ha elegido no volver a jugar
                    {
                    	frame.dispose(); //cerrar el marco -> volver al menu principal
                        return false; //declaraciones de retorno para la funcion menuPane
                    }
                    else return true;
                }
                if (luigi.equals(MOList.get(i))) //luigi se salio de la pantalla
                { //juego perdido
                    endLostGame(); //funcion de juego perdido
                    if (gameOver) //el jugador ha elegido no volver a jugar
                    {
                    	frame.dispose(); //cerrar el marco -> volver al menu principal
                        return false; //declaraciones de retorno para la funcion menuPane
                    }
                    else return true;
                }
                else
                {
                    //el objeto que cae de la pantalla es un barril, por lo tanto, retire el barril y es el tiempo de gravedad fuera de la pantalla
                    MOList.remove(i);
                    gravityTimes.remove(i);
                }
            }
            //aumentar la puntuacion al saltar sobre el barril
            else checkForPoints(MOList.get(i));
        }
    	return true;
    }
    /**
     * verifica si Mario esta saltando sobre un barril y si agrega puntos
     * @param tempMov
     */
    private void checkForPoints(ObjetoMovimiento tempMov) 
    {	
	    if(model=="normal") {    
	    	if(tempMov.getYPos() >= mario.getYPos() && tempMov.getYPos() <= mario.getYPos() + 100 && 
	            mario.getXPos() >= tempMov.getXPos()	&&
	            mario.getXPos() <= tempMov.getXPos()+tempMov.getWidth() &&
	            !(tempMov.pointAwarded) && mario.jumping && !mario.equals(tempMov))
	        {
	            tempMov.setPointAwarded();
	            score += 100;
	        }
	    	if(mario.collidingWithApple) {
	    		score +=5;
	    		mario.collidingWithApple=false;
	    		
	    	}
	    	if(mario.collidingWithCherry) {
	    		score +=10;
	    		mario.collidingWithCherry=false;
	    		
	    	}
	    }else if(model=="VS") {
	    	if(tempMov.getYPos() >= mario.getYPos() && tempMov.getYPos() <= mario.getYPos() + 100 && 
	    		mario.getXPos() >= tempMov.getXPos()	&&
	    		mario.getXPos() <= tempMov.getXPos()+tempMov.getWidth() &&
	    		!(tempMov.pointAwarded) && mario.jumping && !mario.equals(tempMov))
	    	{
	    		tempMov.setPointAwarded();
	    		score += 100;
	    	}else if(tempMov.getYPos() >= luigi.getYPos() && tempMov.getYPos() <= luigi.getYPos() + 100 && 
		    	luigi.getXPos() >= tempMov.getXPos()	&&
		    	luigi.getXPos() <= tempMov.getXPos()+tempMov.getWidth() &&
		    	!(tempMov.pointAwarded) && luigi.jumping && !luigi.equals(tempMov))
	    	{
	    		tempMov.setPointAwarded();
	    		score2 += 100;
	    	}
	    }
    }
    /**
     * Que hacer despues de que el jugador haya ganado
     * @return
     * @throws InterruptedException
     */
    private boolean continueAfterWin() throws InterruptedException 
    {
        score+=1000; //suma puntos por ganar el juego
        JOptionPane.showMessageDialog(null, "Ganaste! Tu puntaje es:" + score);
        gameOver = true;
        thread.sleep(50); //espera un momento
        return false;
    }
    /**
     * que hacer despues de que el jugador haya perdido
     * @throws InterruptedException
     */
    private void endLostGame() throws InterruptedException 
    {
        //mario o luigi murio
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Tu puntaje es: " + score + ". ¿Quieres intentarlo de nuevo?", "Game over", dialogButton);
        if(dialogResult == 0)
        {
            //si, por lo tanto, inicie nuevamente y comience nuevamente
            MOList.clear();
            gravityTimes.clear();
            score = 0;
            initGame();
            thread.sleep(30);
        }
        else
        {
            //no , entones termine y guarde el puntaje
            gameOver = true;  
            thread.sleep(50);
        }
    }
    /**
     * Pausa el juego
     * @throws InterruptedException
     */
    public void pause() throws InterruptedException {
    	if(wait) {
    		wait=false;
    	}else {
			wait=true;
    	}
    }
    /**
     * inicializa el comienzo del juego
     */
    public void initGame() 
    {
        initStaticObjects();
        initMovingObjects();
        timer = 0;
        gameOver = false;
        if(model=="normal") {
        	mario.setToDefault();
        }else if(model=="VS") {
        	mario.setToDefault();
        	luigi.setToDefault();
        }
    }
    /**
     * inicializar todos los objetos estaticos: agrega objetos estaticos a SOList
     */
    private void initStaticObjects() 
    {
        //inicializar lista
      //  SOList = new ArrayList<>();
        initSurprise2();
		initPlatform2();
		initLadders2();
        //creamos a peach
        peach = new Peach(250,70,35,20);
        SOList.add(peach);
    }
    /**
     * Prepara las escaleras del mapa
     */
    private void initLadders() {
    	//crear Escaleras Segmentadas 
		SOList.add(new EscaleraSegmentada(400,612-(612-532),(612-532),15));
        
		SOList.add(new EscaleraSegmentada(200,505-(505-375),(505-375),15));
        //crear Escaleras
        SOList.add(new Escalera(500,612-(612-532),(612-532),15));
        
        SOList.add(new Escalera(120,510-(510-380),(510-380),15));
        
        SOList.add(new Escalera(510,360-130,130,15));
        SOList.add(new Escalera(320,210-60,60,15));
        
        SOList.add(new Escalera(180,210-120,120,15));
        
        SOList.add(new Escalera(220,210-120,120,15));
        
    }
    /**
     * Prepara las plataformas del mapa
     */
    private void initPlatform() {
    	 //creamos las plataformas
		SOList.add(new PlataformaMagica(50 ,SCREEN_Y - 50, SCREEN_Y - 50, SCREEN_X/2));

        int y = SCREEN_Y - 50;
        SOList.add(new PlataformaMagica(SCREEN_X/2,y,622,SCREEN_X - 50));

        int x = SCREEN_X - 100;
        y = SCREEN_Y - 150;
        SOList.add(new PlataformaMagica(35,514,530,x - 20));


        y = SCREEN_Y - 300;
        SOList.add(new PlataformaMagica(100,380,370,590));
     
        x = SCREEN_X - 100;
        y = SCREEN_Y - 450;
        SOList.add(new PlataformaMagica(50,215,230,x - 20));
        //donde va a estar peach
        x = 230;
        y = 140;
        SOList.add(new PlataformaMagica(x, y, 140, x + 180));
    }
    /**
     * Prepara las sorpresas del mapa
     */
    private void initSurprise(){
    	//crear sorpresas
        int k=(int) (Math.random()*6);
		if(k==0) {

	        SOList.add(new Hongo(500,450,20,20));
			
		}else if(k==1) {

	        SOList.add(new Martillo(500,450,20,20));
		     
		}else if(k==2) {
			SOList.add(new Manzana(500,450,20,20));
		   
		}else if(k==3){
			SOList.add(new Corazon(500,450,20,20));
		  
		}else if(k==4){
			 SOList.add(new Cereza(500,450,20,20));
		  
		}else if(k==5){
			SOList.add(new Soga(500,450,20,20));
			  
		}
		int k2=(int) (Math.random()*6);
		if(k2==0) {

	        SOList.add(new Hongo(150,310,20,20));
			
		}else if(k2==1) {

	        SOList.add(new Martillo(150,310,20,20));
		     
		}else if(k2==2) {
			SOList.add(new Manzana(150,310,20,20));
		   
		}else if(k2==3){
			SOList.add(new Corazon(150,310,20,20));
		  
		}else if(k2==4){
			 SOList.add(new Cereza(150,310,20,20));
		  
		}else if(k2==5){
			SOList.add(new Soga(150,310,20,20));
			  
		}
    }
    /**
     * Prepara las escaleras del mapa
     */
    private void initLadders2() {
    	//crear Escaleras Segmentadas 
		SOList.add(new EscaleraSegmentada(300,540-80,80,15));
	    SOList.add(new EscaleraSegmentada(450,360-110,110,15));
		SOList.add(new EscaleraSegmentada(380,505-130,90,15));
        //crear Escaleras
        SOList.add(new Escalera(400,550,70,15));
        SOList.add(new Escalera(510,260-70,70,15));
        SOList.add(new Escalera(510,510-140,70,15));
        SOList.add(new Escalera(150,540-70,70,15));
        SOList.add(new Escalera(200,360-70,70,15));
        SOList.add(new Escalera(320,210-100,70,15));
        SOList.add(new Escalera(180,210-140,100,15));
        SOList.add(new Escalera(212,210-140,100,15));
        
    }
    /**
     * Prepara las plataformas del mapa
     */
    private void initPlatform2() {
    	 //creamos las plataformas
        int y = SCREEN_Y - 50;
        int x = SCREEN_X - 100;
    	SOList.add(new PlataformaMagica(20 ,SCREEN_Y - 50, SCREEN_Y - 50, SCREEN_X/2));
        y = SCREEN_Y - 50;
        SOList.add(new PlataformaMagica(SCREEN_X/2,y,622,SCREEN_X - 50));
        SOList.add(new PlataformaMagica(100,290,250,SCREEN_X - 80));
        SOList.add(new PlataformaMagica(20,350,390,SCREEN_X - 90));
        SOList.add(new PlataformaMagica(100,470,430,SCREEN_X - 20));
        SOList.add(new PlataformaMagica(10,530,550,SCREEN_X - 140));
		x = SCREEN_X - 100;
        y = SCREEN_Y - 480;
        SOList.add(new PlataformaMagica(50,180,210,x - 20));
		
		//donde va a estar peach
        x = 230;
        y = 100;
        SOList.add(new PlataformaMagica(x, y, 140, x + 180));
    }
    /**
     * Prepara las sorpresas del mapa
     */
    private void initSurprise2(){
    	//crear sorpresas
        int k=(int) (Math.random()*6);
		if(k==0) {

	        SOList.add(new Hongo(500,450,20,20));
			
		}else if(k==1) {

	        SOList.add(new Martillo(500,470,20,20));
		     
		}else if(k==2) {
			SOList.add(new Manzana(500,470,20,20));
		   
		}else if(k==3){
			SOList.add(new Corazon(500,470,20,20));
		  
		}else if(k==4){
			 SOList.add(new Cereza(500,470,20,20));
		  
		}else if(k==5){
			SOList.add(new Soga(500,470,20,20));
			  
		}
		int k2=(int) (Math.random()*6);
		if(k2==0) {

	        SOList.add(new Hongo(50,310,20,20));
			
		}else if(k2==1) {

	        SOList.add(new Martillo(50,310,20,20));
		     
		}else if(k2==2) {
			SOList.add(new Manzana(50,310,20,20));
		   
		}else if(k2==3){
			SOList.add(new Corazon(50,310,20,20));
		  
		}else if(k2==4){
			 SOList.add(new Cereza(50,310,20,20));
		  
		}else if(k2==5){
			SOList.add(new Soga(50,310,20,20));
			  
		}
    }
    /**
     * inicializar todos los objetos en movimiento: agregar objetos en movimiento a MOList
     */
    private void initMovingObjects() 
    {   
        //inicializar lista
       // MOList = new ArrayList<>();
        if(model=="normal") {
        	mario = new Mario(60,600,35,20, SOList);
        	MOList.add(mario);
        }
        else if(model=="VS") {
        	mario = new Mario(60,600,35,20, SOList);
        	luigi = new Luigi(65,600,35,20, SOList);
        	MOList.add(mario);
        	MOList.add(luigi);
        }
        //Inicializar los temporizadores de gravedad de los objetos en movimiento.
        gravityTimes = new ArrayList<>();
        for (int i = 0; i < MOList.size(); i++)
        {
            gravityTimes.add(0);
        }
                
    }
    /**
     * //agrega un nuevo barril al juego cada 150 milisegundos, el lugar de generacion esta al lado de Kong
     */
    public void spawnBarrel() 
    {	
    	int k=(int) (Math.random()*3);
		if(k==0) {

			gravityTimes.add(0);
	        MOList.add(new Barril(120,200,20,25, SOList, true));
			
		}else if(k==1) {

			gravityTimes.add(0);
	        MOList.add(new BarrilAzul(120,200,20,25, SOList, true));		     
		}else if(k==2) {
			
			gravityTimes.add(0);
	        MOList.add(new BarrilVerde(120,200,20,25, SOList, true));
		   
		}
    	
    }
    /**
     * //agrega un nuevo barril al juego cada 150 milisegundos, el lugar de generacion esta al lado de Kong
     */
    public void spawnBarrel2() 
    {	
    	//gravityTimes.add(0);
       // MOList.add(new BarrilVerde(120,170,20,25, SOList, true));
    	
    	int k=(int) (Math.random()*3);
		if(k==0) {

			gravityTimes.add(0);
	        MOList.add(new Barril(120,170,20,25, SOList, true));
			
		}else if(k==1) {

			gravityTimes.add(0);
	        MOList.add(new BarrilAzul(120,170,20,25, SOList, true));		     
		}else if(k==2) {
			
			gravityTimes.add(0);
	        MOList.add(new BarrilVerde(120,170,20,25, SOList, true));
		   
		}
    	
    }
    /**
     * incrementa los tiempos de gravedad para mover objetos desde MOList
     */
    public void incrementTime() 
    {
        for(int i = 0; i < MOList.size(); i++)
        {
            ObjetoMovimiento MO = MOList.get(i);
            if(MO.standing())
            {
                gravityTimes.set(i, 0);
                if(MO instanceof Mario) //Si el objeto es Mario y esta parado, salta a falso
                {
                    ((Mario) MOList.get(i)).setJump(false);
                }	
                else if(MO instanceof Luigi) //Si el objeto es Luigi y esta parado, salta a falso
                {
                    ((Luigi) MOList.get(i)).setJump(false);
                }
            }
            else
            {
                gravityTimes.set(i, (gravityTimes.get(i)) + 1) ;
            }		
        }	
    }
    /***
     * funcion utilizada para la lectura al presionar teclas en el teclado, utilizada en el Panel
     * @param down
     */
    public void passKeysDownToPlayer(boolean[] down) 
    {
    	if(model=="normal") {
    		mario.setKeysDown(down);
    	}
    	else if(model=="VS") {
    		mario.setKeysDown(down);
    		luigi.setKeysDown(down);
    	}
    }
    /**
     * Guarda el DonkeyPoob desde un archivo con extension (.donkey)
	 * @param file El archivo donde se va a guardar el DonkeyPoob
	 * @throws DonkeyException - TYPE_donkey_ERROR Si el archivo no tiene la extension .donkey.
	 * @throws DonkeyException - FILE_NOT_FOUND_ERROR Si no se encontro el archivo para guardar el DonkeyPoob
	 * @throws DonkeyException Si ocurrio un error al serializar el archivo.
    */
	public void save(File file) throws DonkeyException{
		donkeyDAO.save(this,file);
	}
	/**
     * Guarda el donkeypoob desde un archivo con extension (.donkey)
	 * @param file El archivo donde se va a guardar el DonkeyPoob
	 * @throws DonkeyException - TYPE_donkey_ERROR Si el archivo no tiene la extension .donkey.
	 * @throws DonkeyException - FILE_NOT_FOUND_ERROR Si no se encontro el archivo para guardar el DonkeyPoob
	 * @throws DonkeyException Si ocurrio un error al serializar el archivo.
    */
	/*public void exportar(File file) throws DonkeyException{
		donkeyDAO.exportar(this,file);
	}*/
	/**
     * Abre un nuevo donkeypoob desde un archivo con extension (.donkey)
	 * @param file El archivo donde se va a abrir el DonkeyPoob
     * @return El nuevo donkeypoob del archivo.
	 * @throws DonkeyException - TYPE_donkey_ERROR Si el archivo no tiene la extension .donkey.
	 * @throws DonkeyException Si ocurrio un error al deserializar el archivo.
    */
	public DonkeyPoob open(File file) throws DonkeyException{
		return donkeyDAO.open(file);
	}
    
    //funciones de retorno para elementos en la clase

    
    public ArrayList<ObjetoEstatico> getSOList()
    {
        return SOList;
    }

    public ArrayList<ObjetoMovimiento> getMOList()
    {
        return MOList;
    }

    public boolean isGameOver()
    {
        return gameOver;
    }
    public int getScore()
    {
        return score;
    }
    public String getModel()
    {
        return model;
    }    
}   


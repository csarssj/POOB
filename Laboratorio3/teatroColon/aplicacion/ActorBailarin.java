package aplicacion;
import java.awt.Color;
/**
 * Clase ActorBailarin actor que nunca para de moverse
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 3/10/2019
 */
public class ActorBailarin extends Actor {
    
    public int Bailarin= 0;
    public int color= 0;

    /**
     * Constructor del ActorBailarin
     * @param teatro en donde se trabaja
     * @param nombre del actor
     * @param  posicionx del actorBailarin
     * @param  posicionxy del actorBailarin
     */
    public ActorBailarin(Teatro teatro,String name,int posicionx, int posiciony)
    {
        super( teatro, name, posicionx,  posiciony);
    }
    @Override
    public void corte(){
        for (int i=0; i<550-getPosicionX();i++){
            muevase('E');
        }
        palabras="Chao";


    }
    @Override
    public void decida (){
        
        for (int i=0; i<getPosicionX()-300;i++){
            muevase('O');
        }
        color+=1;
        if (color>1 && color<5){
            super.setColor(Color.YELLOW);
        }
        else if(color>5 && color < 10){
            super.setColor(Color.BLUE);
        }
        else if (color>10){
            super.setColor(Color.RED);
        }
        else if (color>15){
            super.setColor(Color.BLACK);
        }
        
    }
    @Override
    public void actue (){
        Bailarin+=1;
        palabras="Soy Bailarin";
        if (getPosicionBrazo('I')==ABAJO && getPosicionBrazo('D')==ABAJO){
            muevaBrazo('I','S'); 
            muevaBrazo('D','S');
            muevaBrazo('I','S'); 
            muevaBrazo('D','S');
        } else if  (getPosicionBrazo('I')==ARRIBA && getPosicionBrazo('D')==ARRIBA){
            muevaBrazo('I','B'); 
            muevaBrazo('D','B');
        }
            else if  (getPosicionBrazo('I')==FRENTE && getPosicionBrazo('D')==FRENTE){
            muevaBrazo('I','S'); 
            muevaBrazo('D','S');
        
        }
        
        if (Bailarin>5){
            palabras="Soy Feliz";
           
           muevaPierna('I','S');
           muevaPierna('D','S');
           muevaPierna('I','S');
           muevaPierna('D','S');
           muevase('S');
           muevaPierna('I','B');
           muevaPierna('D','B');

        }
        if(Bailarin>7){
            Bailarin=0;
           palabras="Wiii";
           muevase('N');
           muevaPierna('I','B');
           muevaPierna('D','B');
           muevase('N');
        }
           
        
    }

}

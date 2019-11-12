package aplicacion;
import java.awt.Color;
/**
 * Clase actor necio que es extendiende a actor
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 3/10/2019
 */
public class ActorNecio extends Actor {
    
    public int necio= 0;
    /**
     * Constructor del Actor necio
     * @param teatro en donde se trabaja
     * @param nombre del actor
     * @param  posicionx del actor necio
     * @param  posicionxy del actor necio
     */
    public ActorNecio(Teatro teatro,String name,int posicionx, int posiciony)
    {
        super( teatro, name, posicionx,  posiciony);
    }
    @Override
    public void corte(){
       if(necio == 2){
           super.actue();
           necio = 0;
       }
       else{
           super.corte();
           necio ++;
       }
    }
    @Override
    public void actue (){
       if(necio == 2){
           super.corte();
           necio = 0;
       }
       else{
           super.actue();
           necio ++;
       }
    }

}

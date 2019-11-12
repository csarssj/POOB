package aplicacion;
import java.awt.Color;

public class ActorNecio extends Actor {
    
    public int necio= 0;

    /**
     * Constructor for objects of class ActorNecio
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

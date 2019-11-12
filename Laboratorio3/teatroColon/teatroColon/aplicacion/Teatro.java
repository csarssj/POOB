package aplicacion;
import java.util.*;


/**
 * @author ECI 2014
 * 
 */
/**
 * @author ECI
 *
 */
public class Teatro{
    public static final int MAXIMO = 500;
    private static Teatro teatro = null;
    private ArrayList<EnEscena> elementos;
    
    public static Teatro demeTeatro() {
        if (teatro==null){
            teatro=new Teatro();
        }
        return teatro;
    }
    
    private static void nuevoTeatro() {
        teatro=new Teatro();
    }   
    
    public static void cambieTeatro(Teatro d) {
        teatro=d;
    }       


    
    
    private Teatro() {
        elementos= new ArrayList<EnEscena>();
    }
    
    public void algunosEnEscena(){
        int[] x={1,200,355};
        int[] y={1,500,500};
        int[] x1={600,200,355};
        int[] y1={1,500,500};
        Luz centralDerecha = new Luz(this,"centralDerecha",x,y);
        Luz centralIzquierda = new Luz(this,"centralIzquierda",x1,y1);
        elementos.add(centralDerecha);
        elementos.add(centralIzquierda);
        Actor Romeo= new Actor(this,"romeo",100,100);
        Actor Julieta= new Actor(this,"julieta",200, 100);
        elementos.add(Romeo);
        elementos.add(Julieta);
        Actor Homer= new ActorNecio(this,"homer",100,300);
        Actor Bard= new ActorNecio(this,"bard",200, 300);
        elementos.add(Homer);
        elementos.add(Bard);
        ActorPerezoso Bella = new ActorPerezoso(this,"bella",50,50);
        ActorPerezoso Edward = new ActorPerezoso(this,"edward",20,50);
        elementos.add(Bella);
        elementos.add(Edward);
        

    }  
    
    
    public EnEscena demeEnEscena(int n){
        EnEscena h=null;
        if (1<=n && n<=elementos.size()){
            h=elementos.get(n-1);
        }    
        return h; 
    }
    
    
    public void adicione(EnEscena e){
        elementos.add(e);
    }
    
    public int numeroEnEscena(){
        return elementos.size();
    }
    
    
    
    public void accion(){
        for (int i=0;i<elementos.size();i++){
            elementos.get(i).actue();
        }
    }

    
    public void corten(){
        for (int i=0;i<elementos.size();i++){
            elementos.get(i).corte();
        }
    }    

    public void decidan(){
        for (int i=0;i<elementos.size();i++){
            elementos.get(i).decida();
        }
    }  
    
}

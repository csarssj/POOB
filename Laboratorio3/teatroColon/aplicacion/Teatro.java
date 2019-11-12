package aplicacion;
import java.util.*;


/**
 * Clase Teatro en donde se encuentran los actores y los elementos
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 3/10/2019
 */
public class Teatro{
    public static final int MAXIMO = 500;
    private static Teatro teatro = null;
    private ArrayList<EnEscena> elementos;
    
    /**
     * Retorna un teatro
     * @returns teatro teatro a retornar
     */
    
    public static Teatro demeTeatro() {
        if (teatro==null){
            teatro=new Teatro();
        }
        return teatro;
    }
    /**
     * Crea un nuevo teatro
     */
    private static void nuevoTeatro() {
        teatro=new Teatro();
    }   
    /**
     * Cambia el teatro actual
     * @param d teatro nuevo
     */
    public static void cambieTeatro(Teatro d) {
        teatro=d;
    }       
    
    /**
     * Constructor clase Teatro
     */
    private Teatro() {
        elementos= new ArrayList<EnEscena>();
    }
    /**
     * Crea y adiciona los elementos del teatro y los pone en Escena
     */
    public void algunosEnEscena(){
        int[] x={1,200,355};
        int[] y={1,500,500};
        int[] x1={600,200,355};
        int[] y1={1,500,500};
        Luz centralDerecha = new Luz(this,"Luz",x,y);
        Luz centralIzquierda = new Luz(this,"Luz",x1,y1);
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
        ActorBailarin Brayan = new ActorBailarin (this,"Brayan",300,10);
        ActorBailarin Cesar = new ActorBailarin (this,"Cesar",350,10);
        elementos.add(Brayan);
        elementos.add(Cesar);
        Cortinas Derecha = new Cortinas(this,"CortinaDerecha");
        Cortinas Izquierda = new Cortinas(this,"CortinaIzquierda");
        elementos.add(Derecha);
        elementos.add(Izquierda);
        

    }  
    /**
     * Retorna los eementos en escena
     * @returns h elemento EnEscena
     */
    
    public EnEscena demeEnEscena(int n){
        EnEscena h=null;
        if (1<=n && n<=elementos.size()){
            h=elementos.get(n-1);
        }    
        return h; 
    }
    
    /**
     * adiciona los elementos en Escena
     * @param e EnEscena elemento a adicionar
     */
    public void adicione(EnEscena e){
        elementos.add(e);
    }
    /**
     * Numero de elementos en escena
     * @returns int tamaño de la lista elementos
     */
    public int numeroEnEscena(){
        return elementos.size();
    }
    
    /**
     * Llama el metodo actue de cada uno de los elementos
     */
    
    public void accion(){
        for (int i=0;i<elementos.size();i++){
            elementos.get(i).actue();
        }
    }
    
    /**
     * Llama el metodo corte de cada uno de los elementos
     */
    public void corten(){
        for (int i=0;i<elementos.size();i++){
            elementos.get(i).corte();
        }
    }    
    
    /**
     * Llama el metodo decida de cada uno de los elementos
     */
    public void decidan(){
        for (int i=0;i<elementos.size();i++){
            elementos.get(i).decida();
        }
    }  
    
}

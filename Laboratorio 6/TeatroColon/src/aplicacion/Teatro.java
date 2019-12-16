package aplicacion;

import java.io.*;
import persistencia.*;
import java.util.*;

/**
 * Clase Teatro en donde se encuentran los actores y los elementos
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 3/10/2019
 */
public class Teatro implements Serializable{
    public static final int MAXIMO = 500;
    private static Teatro teatro = null;
    private ArrayList<EnEscena> elementos;
    
    private transient TeatroColonDAO dao;
    private static final long serialVersionUID = 8799656478674716638L;
    
    /**
     * Constructor clase Teatro
     */
    private Teatro() {
        elementos= new ArrayList<EnEscena>();
        dao = new TeatroColonDAO();
    }
    
    /**
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
    public static void nuevoTeatro() {
        teatro=new Teatro();
    }   
    /**
     * Cambia el teatro actual
     * @param d teatro nuevo
     */
    public void iniciarDeNuevo() {
    	elementos.clear();
    	teatro= Teatro.demeTeatro();
    	
    	//teatro.algunosEnEscena();
    	
    	
    }
    public static void cambieTeatro(Teatro d) {
        teatro=d;
    }       
    
    /*	
     * Crea y adiciona los elementos del teatro y los pone en Escena
     */
    public void algunosEnEscena(){

    	Luz centralDerecha = new Luz(this,"Luz",1,200,355,1,500,500);
        Luz centralIzquierda = new Luz(this,"Luz",600,200,355,1,500,500);
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
        Cortinas Derecha = new Cortinas(this,"CortinaDerecha",600);
        Cortinas Izquierda = new Cortinas(this,"CortinaIzquierda",0);
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
    public ArrayList<EnEscena> getElementos(){
		return elementos;
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
    public void salve(File file) throws TeatroColonException{
		dao.salve01(this,file);
	}
	
	public Teatro abrir(File file) throws TeatroColonException{
		return dao.abra01(file);
	}
	
	public void exporte(File file) throws TeatroColonException{
		dao.exporte(this,file);
	}
	
	public Teatro importe(File file) throws TeatroColonException{
		return dao.importe03(file);
	}
	
}

package aplicacion;

import java.io.*;
import persistencia.*;
import java.util.*;

/**
 * @author ECI 2014
 * Salon de la aplicación egiptologos
 */
/**
 * @author ECI
 *
 */
public class Salon implements Serializable{
    public static final int MAXIMO = 500;
    private static Salon salon = null;
	
	private transient BodyTicDAO dao;
    public static Salon demeSalon() {
        if (salon==null){
            salon=new Salon();
        }
        return salon;
    }
    
    /**
     * Crea un nuevo salon.
     */
    public static void nuevoSalon() {
        salon=new Salon();
    }   
    
    /**
     * Cambia el salon por uno dado.
	 * @param d el nuevo salon
     */
    public static void cambieSalon(Salon d) {
        salon=d;
    }       


    private ArrayList<EnSalon> elementos;
    
    private Salon() {
        elementos= new ArrayList<EnSalon>();
		dao = new BodyTicDAO();
    }
    
    
    /**
     * Retorna un objeto de tipo en salon dado su posición.
     * @param n la posicion del objeto en EnSalon.
     * @return h retorna un objeto de tipo EnSalon.
     */
    public EnSalon deme(int n){
        EnSalon h=null;
        if (1<=n && n<=elementos.size()){
            h=elementos.get(n-1);
        }    
        return h; 
    }
    
    
    /**
     * Adiciona un elemento al salon. 
     * @param e el objeto que se va a adicionar a salon.
     */
    public void adicione(EnSalon e){
        elementos.add(e);
    }
    
    /**
     * Retorna la cantidad de elementos que hay en el salon.
     * @return int el size de los elementos. 
     */
    public int numeroEnSalon(){
        return elementos.size();
    }
    
    /**
     * Crea distintos elementos para que entren en el salon. 
     */
    public void entrada(){  
        elementos.clear();
        new Deportista(this,"edward",100,100);
        new Deportista(this,"bella",200,100);
        new DeportistaAvanzado(this,"neo",300,3);
        new DeportistaAvanzado(this,"trinity",400,3);    
        new DeportistaHablador(this,"han",50,300);
        new DeportistaHablador(this,"leila",50,250);
        new DeportistaPrincipiante(this,"carlos",20,400);
        new DeportistaPrincipiante(this,"eduard",100,400); 
        new Bola(this,"Soy una bola",0,530); 
        new Bola(this,"Soy una bola",540,0); 
        new Dado(this,"Soy un dado",10,90);
        new Dado(this,"Soy un dado",540,90);
    }  
    
    /**
     * Sacar todos los elementos del salon.
     */
    public void salida(){  
        elementos.clear();
    }

    /**
     * Todos los elementos del salon comienzan sus movimientos respectivos.
     */
    public void inicio(){
        for(EnSalon elemento: elementos){
            elemento.inicie();
        }
    }  
    
    /**
     * Todos los elementos del salon paran sus movimientos respectivos.
     */
    public void parada(){
        for(EnSalon elemento: elementos){
            elemento.pare();
        }      
    }  
    
    /**
     * Todos los elementos del salon deciden sus siguiente movimiento.
     */
    public void decision(){
        for(EnSalon elemento: elementos){
            elemento.decida();
        }
    }  

	public Salon nuevo(){
		nuevoSalon();
		return salon;
	}	

	public void salve(File file) throws bodyTIcExcepcion{
		dao.salve01(this,file);
	}
	
	public void abrir(File file) throws bodyTIcExcepcion{
		Salon auto = dao.abra01(file);
		salon = auto;
	}
	
	public void exporte(File file) throws bodyTIcExcepcion{
		dao.exporte03(this,file);
	}
	
	public void importe(File file) throws bodyTIcExcepcion{
		dao.importe03(this,file);
	}
}

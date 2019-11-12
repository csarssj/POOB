<<<<<<< HEAD
package aplicacion;

=======
>>>>>>> 58bc69471f0187fc38fa82cc8c4679c136078c7c
import java.util.ArrayList;

/**
 * Clase actividad Compuesta
 * 
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 24/10/2019
 */
public class ActividadCompuesta extends Actividad{
    private ArrayList<Actividad> actividades;
    
    /**
     * Constructor actividad simple
     * @param id identificador
     */
<<<<<<< HEAD
    public ActividadCompuesta(String id)throws ActividadExcepcion{
=======
    public ActividadCompuesta(String id){
>>>>>>> 58bc69471f0187fc38fa82cc8c4679c136078c7c
        super(id);
        actividades= new ArrayList<Actividad>();
    }
    /**
     * Agrega actividades simples a la compuesta
     */
<<<<<<< HEAD
    public void actividad(Actividad a)throws ActividadExcepcion{
=======
    public void actividad(Actividad a){
>>>>>>> 58bc69471f0187fc38fa82cc8c4679c136078c7c
        actividades.add(a);
    }
    
    @Override
    public int creditos() throws ActividadExcepcion{
        int creditos = 0;
        if(actividades.size() == 0) throw new ActividadExcepcion(ActividadExcepcion.COMPUESTA_VACIA);
        for(Actividad act: actividades){            
            creditos += act.creditos();            
        }
        return creditos;
    }
    /**
     * Retorna los creditos de una actividad definida
     */
    public  int creditosDefinidos() throws ActividadExcepcion{
        int Creditos =0; 
        for(Actividad act: actividades){
            Creditos += act.creditos();
        }
        return Creditos;
        
    }
    @Override
    public int creditos(String descripcion) throws ActividadExcepcion{
        int creditos=0;
        int n=0;
        for(Actividad act: actividades){
            if(descripcion.equals(act.getDescripcion())){
                n++;
            }
            if(n==1) creditos = act.creditos();
        }if(n>1) throw new ActividadExcepcion(ActividadExcepcion.ACTIVIDADES_IGUALES);                
     return creditos;
    }
    /**
     * Retorna las actividades de una actividad compuesta
     */
    public ArrayList<Actividad> getActividades() {
        return actividades;
    }
}

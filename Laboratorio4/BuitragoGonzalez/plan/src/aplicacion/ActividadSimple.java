<<<<<<< HEAD
package aplicacion;

=======
>>>>>>> 58bc69471f0187fc38fa82cc8c4679c136078c7c
 
/**
 * Clase actividad simple
 * 
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 24/10/2019
 */
public class ActividadSimple extends Actividad{
    private Integer creditos;
    
    /**
     * Constructor actividad simple
     * @param id identificador
     * @param creditos
     */
<<<<<<< HEAD
    public ActividadSimple(String id, Integer creditos)throws ActividadExcepcion {
        super(id);
        boolean esEntero;
        try {
            
            this.creditos=creditos;
            esEntero = true;
        }catch(NumberFormatException e) {
            esEntero = false;
        }
        if(!esEntero) throw new ActividadExcepcion(ActividadExcepcion.CREDITOS);
=======
    public ActividadSimple(String id, Integer creditos){
        super(id);
        this.creditos=creditos;
>>>>>>> 58bc69471f0187fc38fa82cc8c4679c136078c7c
    }
    /**
     * Constructor actividad simple
     * @param id identificador
     * @param descripcion de la actividad
     * @param creditos
     */
<<<<<<< HEAD
    public ActividadSimple(String id,  String descripcion,Integer creditos)throws ActividadExcepcion{
        super(id,descripcion);
        boolean esEntero;
        try {
            this.creditos=creditos;
            esEntero = true;
        }catch(NumberFormatException e) {
            esEntero = false;
        }
        if(!esEntero) throw new ActividadExcepcion(ActividadExcepcion.CREDITOS);
=======
    public ActividadSimple(String id,  String descripcion,Integer creditos){
        super(id,descripcion);
        this.creditos=creditos;
>>>>>>> 58bc69471f0187fc38fa82cc8c4679c136078c7c
    }
    
    @Override
    public int creditos() throws ActividadExcepcion{
        if(creditos == null) throw new ActividadExcepcion(ActividadExcepcion.SIMPLE_CREDITOS);        
        return creditos;                        
    }
    
    /**Calcular el numero de creditos considerando las actividades bien definidas
     * @return 
     * */
    public int creditosDefinidos() throws ActividadExcepcion {
        if(this.descripcion=="Por definir" ){
            return creditos;
        }
        else{
            throw new ActividadExcepcion (ActividadExcepcion.POR_DEFINIR);
        }
    }
    @Override
    public int creditos(String descripcion) throws ActividadExcepcion{
       if(!(descripcion.equals(super.getDescripcion()))) throw new ActividadExcepcion(ActividadExcepcion.NO_EXISTE);
            return creditos;
    }
    /**
     * Retorna creditos de una actividad
     * @return creditos
     */
    public int getCreditos(){
           return creditos;
    }

    
}

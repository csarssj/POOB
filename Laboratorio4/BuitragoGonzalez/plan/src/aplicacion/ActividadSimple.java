package aplicacion;

 
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
    }
    /**
     * Constructor actividad simple
     * @param id identificador
     * @param descripcion de la actividad
     * @param creditos
     */
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

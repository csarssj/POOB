package aplicacion;

/**
 * Clase actividad
 * 
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 24/10/2019
 */
public abstract class Actividad{
    private String id;
    protected String descripcion;
    
    /**
     * Constructor actividad
     * @param id identificador
     */
    public Actividad(String id) throws ActividadExcepcion{
        if(id.length()==0)throw new ActividadExcepcion(ActividadExcepcion.SIGLAS_VACIAS);
        this.id=id;
        this.descripcion="Por definir";
    }
    /**
     * Constructor actividad
     * @param id identificador
     * @param descripcion de la actividad
     */
    public Actividad(String id, String descripcion) throws ActividadExcepcion{
        if(id.length()==0)throw new ActividadExcepcion(ActividadExcepcion.SIGLAS_VACIAS);
        this.id=id;
        this.descripcion=descripcion;
    }
    /**
     * Calcula el numero de creditos de una actividad
     * @return creditos
     */
    public abstract int creditos() throws ActividadExcepcion;
    
    /**Calcular el numero de creditos considerando las actividades bien definidas
     * @return 
     * */
    public abstract int creditosDefinidos() throws ActividadExcepcion;
    
    /**
     * busca los creditos de una actividad con la descripcion
     * @param descripcion de la actividad
     * @return creditos
     */
    public abstract int creditos(String descripcion) throws ActividadExcepcion;
    /**
     * Retorna la descripcion de una actividad
     * @return descripcion
     */
    public String getDescripcion(){
        return descripcion;
    }
    /**
     * Retorna el codigo de la actividad
     * @return id
     */
    public String getId() {
	return id;
    }
}

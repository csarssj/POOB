/**
 * Clase actividad Excepcion
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 24/10/2019
 */
public class ActividadExcepcion extends Exception{
   public static final String COMPUESTA_VACIA="Actividad compuesta vacia";
   public static final String SIMPLE_CREDITOS="Actividad simple no tiene creditos";
   public static final String POR_DEFINIR="Activdad por definir";
   public static final String NO_EXISTE="No existe actividad con esta descripcion";
   public static final String ACTIVIDADES_IGUALES="Dos actividades con esta misma descripcion";
    
    public ActividadExcepcion(String mensaje){
        super(mensaje);
    }
}

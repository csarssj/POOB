package aplicacion;

import java.io.Serializable;
/**
 * Clase TeatroColonExcepcion 
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 11/15/2019
 */
public class TeatroColonException extends Exception implements Serializable{
		public static final String DAT_ERROR = "La extension no es .dat";
		public static final String FILE_NOT_FOUND_ERROR = "Ocurrio un error al encontrar la clase";
		public static final String EN_CONSTRUCCION="opcion en construccion";
		public static final String TYPE_DAT_ERROR = "La extension no es .dat";
		public static final String TYPE_TXT_ERROR = "La extension no es .txt";
		public static final String NO_TEXT_FOUND = "El archivo de texto esta vacio";
		public static final String SIZE_ERROR = "La cantidad de datos ingresados no es correcta";
		public static final String NUMBER_FORMAT_EXCEPTION = "No es posible convertir a entero";
		
	    public TeatroColonException(String mensaje){
	        super(mensaje);
	    }
	}


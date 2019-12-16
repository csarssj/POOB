package aplicacion;

import java.io.Serializable;
/**
 * Clase TeatroColonExcepcion 
 * @author Cesar Gonzalez y Brayan Buitrago
 * @version 4/12/2019
 */
public class DonkeyException extends Exception implements Serializable{
		public static final String DAT_ERROR = "La extension no es .dat";
		public static final String FILE_NOT_FOUND_ERROR = "Ocurrio un error al encontrar la clase";
		public static final String EN_CONSTRUCCION="opcion en construccion";
		public static final String TYPE_DAT_ERROR = "La extension no es .dat";
		public static final String TYPE_TXT_ERROR = "La extension no es .txt";
		public static final String COLOR_ERROR = "Hubo un error en color de un barril";
		public static final String SUPRISE_ERROR = "Hubo un error en nombre de un Sorpresa";
		public static final String NO_TEXT_FOUND = "El archivo de texto esta vacio";
		public static final String SIZE_ERROR = "La cantidad de datos ingresados no es correcta";
		public static final String NUMBER_FORMAT_EXCEPTION = "No es posible convertir a entero";
		public static final String NOT_NAME = "Tiene que ingresar nombre";
		
	    public DonkeyException(String mensaje){
	        super(mensaje);
	    }
	}


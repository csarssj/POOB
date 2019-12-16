package aplicacion;

/**
 * Clase Escalera las cuales mario sube para cambiar de plataforma (extiende objetoEstatico)
 * @author: Cesar Gonzalez y Brayan Buitrago
 * @version: 9/12/2019
*/

public class Escalera extends ObjetoEstatico {
	/**
	 * Constructor para la clase Escalera
     * @param x posicion en coordenada x
     * @param y posicion en coordenada y
     * @param h alto del objeto
     * @param w ancho del objeto
	 */
    public Escalera(int x, int y, int h, int w)
    {
        super(x, y, h, w); 
    }
    
}

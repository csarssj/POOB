package aplicacion;

/**
 * Clase Martilo el cual mario usa para destruir a los barriles(extiende objetoEstatico)
 * @author Cesar Gonzalez y Brayan Buitrago
 * @version 4/12/2019
*/

public class Martillo extends ObjetoEstatico {
	/**
	 * Constructor para la clase s
     * @param x posicion en coordenada x
     * @param y posicion en coordenada y
     * @param h alto del objeto
     * @param w ancho del objeto
	 */
    public Martillo(int x, int y, int h, int w)
    {
        super(x, y, h, w); 
    }
    
}

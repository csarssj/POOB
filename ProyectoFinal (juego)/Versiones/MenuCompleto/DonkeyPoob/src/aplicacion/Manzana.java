package aplicacion;

/**
 * Clase Manzana la cual le brinda 5 puntos a mario(extiende objetoEstatico)
 * @author Cesar Gonzalez y Brayan Buitrago
 * @version 9/12/2019
*/

public class Manzana extends ObjetoEstatico {
	/**
	 * Constructor para la clase s
     * @param x posicion en coordenada x
     * @param y posicion en coordenada y
     * @param h alto del objeto
     * @param w ancho del objeto
	 */
    public Manzana(int x, int y, int h, int w)
    {
        super(x, y, h, w); 
    }
    
}

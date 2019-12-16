package aplicacion;

/**
 * Clase Escalera Segmentada las cuales solo pueden bajar los barriles(extiende objetoEstatico)
 * @author Cesar Gonzalez y Brayan Buitrago
 * @version 4/12/2019
*/

public class EscaleraSegmentada extends ObjetoEstatico {
	/**
	 * Constructor para la clase Escalera
     * @param x posicion en coordenada x
     * @param y posicion en coordenada y
     * @param h alto del objeto
     * @param w ancho del objeto
	 */
    public EscaleraSegmentada(int x, int y, int h, int w)
    {
        super(x, y, h, w); 
    }
    
}

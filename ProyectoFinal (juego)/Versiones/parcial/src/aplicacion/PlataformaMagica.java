package aplicacion;

/**
 * Clase plataforma donde mario camina(extiende objetoEstatico).
 * @author Cesar Gonzalez y Brayan Buitrago
 * @version 4/12/2019
 */
public class PlataformaMagica extends Plataforma {
	/**
	 * Constructor para la clase plataforma
     * @param x posicion en coordenada x
     * @param y posicion en coordenada y
     * @param h alto del objeto
     * @param w ancho del objeto
	 */
	  public PlataformaMagica(int x, int y, int h, int w) {
	      super(x, y, h, w);
	  }
}

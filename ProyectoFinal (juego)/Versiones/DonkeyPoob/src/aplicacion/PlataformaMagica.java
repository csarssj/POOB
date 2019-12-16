
package aplicacion;

/**
 * Clase plataforma donde mario camina(extiende objetoEstatico).
 * @author Cesar Gonzalez y Brayan Buitrago
 * @version 4/12/2019
 */
public class PlataformaMagica extends Plataforma {
	private int y3;
	private int y4;
	/**
	 * Constructor para la clase plataforma
     * @param x posicion en coordenada x
     * @param y posicion en coordenada y
     * @param h alto del objeto
     * @param w ancho del objeto
	 */
	  public PlataformaMagica(int x, int y, int x2, int y2) {
	      super(x, y, x2, y2);
	      p = ((float)x2-(float)y)/((float)y2-(float)x);
	      ye = p*(float)y2+(float)x2;
	  }
	  
	     
}
package pruebas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import aplicacion.Actor;
import aplicacion.Cortinas;
import aplicacion.Teatro;

	
class TeatroTest {
	 @Test
	 public void DeberiaDarmeBienElElemento(){
        Teatro teatro = Teatro.demeTeatro();
        teatro.nuevoTeatro();
        Actor actor1 = new Actor(teatro,"romeo",100,100);
        Actor actor2 = new Actor(teatro,"romeo",100,100);
        Cortinas Derecha = new Cortinas(teatro,"CortinaDerecha",600);
        Cortinas Izquierda = new Cortinas(teatro,"CortinaIzquierda",0);
        assertTrue((teatro.demeEnEscena(4)).equals(Izquierda));
    }
    @Test
    public void DeberiaNoDarmeBien(){
        Teatro teatro = Teatro.demeTeatro();
        teatro.nuevoTeatro();
        Actor actor1 = new Actor(teatro,"romeo",100,100);
        Actor actor2 = new Actor(teatro,"romeo",100,100);
        Cortinas Derecha = new Cortinas(teatro,"CortinaDerecha",600);
        Cortinas Izquierda = new Cortinas(teatro,"CortinaIzquierda",0);
        assertFalse(teatro.demeEnEscena(3).equals(actor1));
    }
    @Test
    public void DeberiaAdicionarBien(){
        Teatro teatro = Teatro.demeTeatro();	
        teatro.nuevoTeatro();
        Actor actor1 = new Actor(teatro,"romeo",100,100);
        Actor actor2 = new Actor(teatro,"romeo",100,100);
        Cortinas Derecha = new Cortinas(teatro,"CortinaDerecha",600);
        Cortinas Izquierda = new Cortinas(teatro,"CortinaIzquierda",0);
        System.out.println(teatro.numeroEnEscena());
	    assertTrue(4==teatro.numeroEnEscena());
	}
    @Test
    public void NoDeberiaAdicionarBien(){
    	Teatro teatro = Teatro.demeTeatro();
    	teatro.nuevoTeatro();
    	Actor actor1 = new Actor(teatro,"romeo",100,100);
        Actor actor2 = new Actor(teatro,"romeo",100,100);
        Cortinas Derecha = new Cortinas(teatro,"CortinaDerecha",600);
        Cortinas Izquierda = new Cortinas(teatro,"CortinaIzquierda",0);
	    assertFalse(4==teatro.numeroEnEscena());
    }

}

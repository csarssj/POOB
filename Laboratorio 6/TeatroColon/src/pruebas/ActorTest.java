package pruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import aplicacion.Actor;
import aplicacion.Teatro;

class ActorTest {

	
    @Test
    public void deberiaActuar(){
    	Teatro teatro = Teatro.demeTeatro();
        teatro.nuevoTeatro();
        Actor Actor1 = new Actor(teatro,"romeo",100,100);
        Actor1.actue();
        assertTrue(Actor1.getPosicionY()==120 || Actor1.getPosicionY()==80);
        Actor Actor2 = new Actor(teatro,"julieta",100,200);
        Actor2.actue();
        assertTrue(Actor2.getPosicionY()==220 || Actor2.getPosicionY()==180);
        Actor Actor3 = new Actor(teatro,"cesar",100,300);
        Actor3.actue();
        assertTrue(Actor3.getPosicionY()==320 || Actor3.getPosicionY()==280);
        Actor Actor4 = new Actor(teatro,"brayan",1000,400);
        Actor4.actue();
        assertTrue(Actor4.getPosicionY()==420 || Actor4.getPosicionY()==380);
    }
    @Test
    public void deberiaCortar(){
    	Teatro teatro = Teatro.demeTeatro();
        teatro.nuevoTeatro();
        Actor Actor1 = new Actor(teatro,"romeo",100,100);
        Actor1.corte();
        assertTrue(Actor1.mensaje().equals(""));
        Actor Actor2 = new Actor(teatro,"julieta",200,100);
        Actor2.corte();
        assertTrue(Actor2.mensaje().equals(""));
        Actor Actor3 = new Actor(teatro,"cesar",300,100);
        Actor3.corte();
        assertTrue(Actor3.mensaje().equals(""));
        Actor Actor4 = new Actor(teatro,"brayan",400,1000);
        Actor4.corte();
        assertTrue(Actor4.mensaje().equals(""));
    }

}

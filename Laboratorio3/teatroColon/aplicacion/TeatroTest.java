package aplicacion;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.*;

/**
 * The test class TeatroTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TeatroTest
{   
    private Teatro teatro=null;
    /**
     * Default constructor for test class TeatroTest
     */
    public TeatroTest()
    {
    }
    @Test 
    public void deberiainiciar(){
        teatro = Teatro.demeTeatro();
        teatro.algunosEnEscena();
        assertEquals(teatro.demeEnEscena(1).forma(),"Luz");
        assertEquals(teatro.demeEnEscena(2).forma(),"Luz");
        

    }
    
    @Test
    public void deberiaCrearActor(){
        teatro = Teatro.demeTeatro();
        teatro.algunosEnEscena();
        assertEquals(teatro.demeEnEscena(3).mensaje(),"¡Hola!");
        assertEquals(teatro.demeEnEscena(4).mensaje(),"¡Hola!");
        
    }
    @Test 
    public void deberiaPrenderLuz(){
        teatro = Teatro.demeTeatro();
        teatro.algunosEnEscena();
        teatro.accion();
        assertEquals(teatro.demeEnEscena(1).getColor(),new Color(255,255,255));
        assertEquals(teatro.demeEnEscena(2).getColor(),new Color(255,255,255));
    }
    @Test 
    public void deberiaApagarLuz(){
        teatro = Teatro.demeTeatro();
        teatro.algunosEnEscena();
        teatro.accion();
        teatro.corten();
        assertEquals(teatro.demeEnEscena(1).getColor(),new Color(102,102,102));
        assertEquals(teatro.demeEnEscena(2).getColor(),new Color(102,102,102));
    }
    @Test 
    public void deberiaDecirSoyBairalin(){
        teatro = Teatro.demeTeatro();
        teatro.algunosEnEscena();
        teatro.accion();
        assertEquals(teatro.demeEnEscena(9).mensaje(),"Soy Bailarin");
        assertEquals(teatro.demeEnEscena(10).mensaje(),"Soy Bailarin");
    }
    @Test
    public void deberiaNohacernada(){
        teatro = Teatro.demeTeatro();
        teatro.algunosEnEscena();
        teatro.decidan();
        assertEquals(teatro.demeEnEscena(7).mensaje(),"!Aquí perezosos!");
        assertEquals(teatro.demeEnEscena(8).mensaje(),"!Aquí perezosos!");
    }
    @Test
    public void deberiaCerrarTelon(){
        teatro = Teatro.demeTeatro();
        teatro.algunosEnEscena();
        teatro.accion();
        assertEquals(teatro.demeEnEscena(11).getPosicionX(),0);
        assertEquals(teatro.demeEnEscena(12).getPosicionY(),600);
    }
}

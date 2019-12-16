package Rainfall;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
* Pruebas de aceptacion
* 
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 27/10/2019
 */
public class AceptacionTest
{
    Valley v1;    
    @Test
    public void aceptacionGonzalez(){
        //Deberia crear un valle
        v1 = new Valley(500, 500);
        //Hacemos Visible todo para que se evidencie 
        v1.makeVisible();
        //Creamos dos lonas una normal y una hard
        v1.addTarp(new int[]{10,100},new int[]{200,130});
        v1.addTarp("hard",new int[]{10,300},new int[]{150,280});
        assertEquals(2,v1.lonas.size());
        //Hacemos invisibble el tablero pero solo la lona normal se hace invisible
        v1.makeInvisible();
        assertEquals(true,v1.lonas.get(1).getVisible());
        //Volvemos a hacer visible
        v1.makeVisible();
        //creamos un viñedo y las lonas deberian adquirir el color de la misma 
       v1.openVineYard( "uno",50,100);
       //hacemos la lluvia acida y debe traspasar cada lona
       v1.startRain("acid",70);
       //hacemos huecos en las dos lonas 
       v1.makePuncture(1,30);
       v1.makePuncture(2,90);
       //creamos la ultima lona
       v1.addTarp(new int[]{50,400},new int[]{200,450});
       //hacemos otro hueco en la lona nueva
       v1.makePuncture(3,90);
       //creamos la lluvia heavy y no debe caer sobre ningun hueco
       v1.startRain("heavy",150);
       v1.finish();
    } 
    @Test
    public void aceptacionBuitrago(){
         //Deberia crear un valle
        v1 = new Valley(500, 500);
        //Hacemos Visible todo para que se evidencie 
        v1.makeVisible();
        //Creamos dos lonas una normal y una flexible
        v1.addTarp(new int[]{10,100},new int[]{200,130});
        v1.addTarp("flexible",new int[]{10,300},new int[]{150,280});
        //creamos un viñedo y luego otro encima y no deberia crear el otro
       v1.openVineYard( "uno",50,100);
       v1.openVineYard( "dos",50,100);
       assertEquals(1,v1.nombres.size());
       //eliminamos el viñedo
       v1.closeVineYard( "uno");
       //creamos otro viñedo y deberian cambiar de color las lonas
        v1.openVineYard( "otro",50,100); 
        assertEquals(1,v1.nombres.size());
        //borramos la primera lona
        v1.removeTarp(1);
        //creamos otra lona pero esta es radical
        v1.addTarp("radical",new int[]{10,300},new int[]{150,280});
        //hacemos una lluvia normal
        v1.startRain(50);
        //hacemos un hueco y se borra la lona
       v1.makePuncture(2,50);
       assertEquals(1,v1.lonas.size());
       v1.finish();
    }
    
}

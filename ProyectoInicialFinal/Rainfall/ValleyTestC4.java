package Rainfall;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
/**
* Clase de pruebas ciclo 4
* 
* @author César Eduardo González y Brayan Santiango Buitrago 
* @version 20/10/2019
*/
public class ValleyTestC4
{
    Valley v1;
    @Test
    public void DeberiaBorrarLonaRadicalSiHaceHueco(){
        v1 = new Valley(500, 500);
        v1.addTarp(new int[]{100,100},new int[]{200,200});
        v1.addTarp("radical",new int[]{100,200},new int[]{200,100});
        assertEquals(2,v1.lonas.size());
        v1.makePuncture(1,120);
        v1.makePuncture(2,120);
        assertEquals(1,v1.lonas.size());
        
    }
    @Test
    public void NoDeberiaHacerInvisibleLonaHard(){
        v1 = new Valley(500, 500);
        v1.addTarp(new int[]{100,100},new int[]{200,200});
        v1.addTarp("hard",new int[]{100,200},new int[]{200,100});
        v1.makeInvisible();
        assertEquals(true,v1.lonas.get(1).getVisible());
    }   
    
    @Test
    public void DeberiaLalluviaAcidaDebeCaerEnElMismoPuntoDondeInicio()
    {
        v1 = new Valley(500, 500);
        v1.addTarp("normal", new int[]{100,100},new int[] {300,300});
        v1.startRain("acid", 199);
        ArrayList<Gota> gotas = v1.lluvias.get(0).getGotas();
        System.out.println(v1.lluvias.get(0).getGotas().get(gotas.size()).getPos()[0]);
        assertEquals(199, v1.lluvias.get(0).getGotas().get(gotas.size()).getPos()[0]);
    }
    @Test
    public void  DeberiaDesaparecerTarpRadicalSiSeAgujerea()
    {
        v1 = new Valley(500, 500);
        int[]ini = {0,170};
        int[]fin = {150,130};
        v1.addTarp("radical",ini,fin);
        int[]ini1 = {30,115};
        int[]fin2 = {80,100};
        v1.addTarp("hard",ini1,fin2);
        v1.makePuncture(1, 50);
        assertEquals(1, v1.lonas.size());
    }
    
    @Test
    public void NoDeberiaEliminarTarpHard()
    {
        v1 = new Valley(500, 500);
        v1.addTarp("hard",new int[] {10,10}, new int[]{200,130});
        assertEquals(1, v1.lonas.size());
        v1.removeTarp(1);
        assertEquals(1, v1.lonas.size());
    } 
    @Test
    public void DeberiaHacerHuecoEnLugarDiferenteEnLonzaCrazy()
    {
        v1 = new Valley(500, 500);
        v1.addTarp("crazy",new int[] {10,10}, new int[]{200,130});
        v1.makePuncture(1,50);
        assertEquals(1, v1.getTH(0));
        v1.patchPuncture(1,50);
        assertEquals(1, v1.getTH(0));
    }
    
    @Test
    public void DeberiaHacerSoloUnHuecoLonaFlexible ()
    {
        v1 = new Valley(500, 500);
        v1.addTarp("flexible",new int[] {10,10}, new int[]{200,130});
        assertEquals(0, v1.getTH(0));
        v1.makePuncture(1,50);
        assertEquals(1, v1.getTH(0));
        v1.makePuncture(1,70);
        assertEquals(1, v1.getTH(0));
    }    
    @Test
    public void NoDeberiaAgujerearLonaHard ()
    {
        v1 = new Valley(500, 500);
        v1.addTarp("hard",new int[] {10,10}, new int[]{200,130});
        assertEquals(0, v1.getTH(0));
        v1.makePuncture(1,50);
        assertEquals(0, v1.getTH(0));
    }
    
}

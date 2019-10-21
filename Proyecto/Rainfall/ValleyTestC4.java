package Rainfall;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
/**
 * The test class ValleyTestC4.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ValleyTestC4
{
    Valley v1;
    /*@Test
    public void DeberiaAgujerearLonaFlexible(){
        v1 = new Valley(500, 500);
        v1.addTarp(new int[]{100,100},new int[]{200,200});
        v1.addTarp("flexible",new int[]{100,200},new int[]{200,100});
        v1.makePuncture(1,20);
        v1.makePuncture(1,80);
        v1.makePuncture(2,50);
        assertEquals(v1.tarps()[0],new int[][]{{100,200},{200,210},{80}});
        
    }
    */
    
    @Test
    public void DeberiaLalluviaAcidaDebeCaerEnElMismoPuntoDondeInicio()
    {
        v1 = new Valley(500, 500);
        v1.addTarp("normal", new int[]{100,100},new int[] {300,300});
        v1.startRain("acid", 199);
        ArrayList<Gota> gotas = v1.lluvias.get(0).getGotas();
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
        int[]ini = {0,0};
        int[]fin = {100,100};
        v1.addTarp(ini,fin);
        int[]ini1 = {0,170};
        int[]fin1 = {150,130};
        v1.addTarp(ini1,fin1);
        int[]ini2 = {30,115};
        int[]fin2 = {80,100};
        v1.addTarp("hard",ini2,fin2);
        v1.removeTarp(3);
        assertEquals(3, v1.lonas.size());
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

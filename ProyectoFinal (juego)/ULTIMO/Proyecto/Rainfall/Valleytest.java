package Rainfall;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class Valleytest.
 *
 * @author César Eduardo González y Brayan Santiango Buitrago 
 * @version 27/10/2019
 */
public class Valleytest
{
    Valley v1;
    
    @Test
    public void shouldCreate(){
            v1 = new Valley(100, 100);
            assertTrue(v1.ok());
    }
    
    @Test
    public void shouldNotCreate(){
            v1 = new Valley(-100, 0);
            fail();
    }
    
    @Test
    public void shouldOpenVineyard(){
            v1 = new Valley(200, 200);
            v1.openVineYard("green", 20, 30);
            assertTrue(v1.ok());
            v1.openVineYard("blue", 89, 90);
            assertTrue(v1.ok());
    }
    
    @Test
    public void shouldNotOpenVineyard(){
            v1 = new Valley(200, 200);
            v1.openVineYard("green", 20, 30);
            v1.openVineYard("green", 20, 60);
            assertFalse(v1.ok());
            v1.openVineYard("yellow", 25, 55);
            assertFalse(v1.ok());
        }
    
    @Test
    public void shouldCloseVineyard(){
            v1 = new Valley(200, 200);
            v1.openVineYard("green", 20, 50);
            v1.openVineYard("blue", 60, 120);
            assertTrue(v1.ok());
            v1.closeVineYard("blue");
            v1.closeVineYard("green");
            assertTrue(v1.ok());
    }
    
    @Test
    public void shouldAddTarp(){
            v1 = new Valley(300, 300);
            v1.addTarp(new int[]{20,20}, new int[]{100, 100});
            assertTrue(v1.ok());
            v1.addTarp(new int[]{21,20}, new int[]{150, 101});
            assertTrue(v1.ok());
            v1.addTarp(new int[]{100, 20}, new int[]{100, 20});
            assertTrue(v1.ok());
    }
    @Test
    public void shouldRemoveTarp(){
        try{
            v1 = new Valley(300, 300);
            v1.addTarp(new int[]{1,1}, new int[]{300,300});
            v1.removeTarp(1);
            assertTrue(v1.ok());
            v1.addTarp(new int[]{1,1}, new int[]{300,300});
            assertTrue(v1.ok());
            v1.addTarp(new int[]{22,20}, new int[]{101, 100});
            v1.removeTarp(2);
            assertTrue(v1.ok());
        }catch(Exception e){
            fail();
        }
    }
    
    @Test
    public void shouldNotRemoveTarp(){
        v1 = new Valley(300, 300);
        v1.removeTarp(1);
        assertFalse(v1.ok());
        v1.addTarp(new int[]{1,1}, new int[]{9,9});
        v1.addTarp(new int[]{10,1}, new int[]{9,90});
        v1.removeTarp(10);
        assertFalse(v1.ok());
        v1.removeTarp(1);
        assertTrue(v1.ok());
        v1.removeTarp(2);
        assertFalse(v1.ok());
    }
    
    @Test
    public void shouldMakePuncture(){
            v1 = new Valley(400, 400);
            v1.addTarp(new int[]{10,10}, new int[]{100, 100});
            v1.addTarp(new int[]{1,1}, new int[]{200, 1});
            assertTrue(v1.ok());
            v1.makePuncture(1, 40);
            assertTrue(v1.ok());
            v1.makePuncture(1, 80);
            assertTrue(v1.ok());
            v1.makePuncture(2, 20);
            assertTrue(v1.ok());
            v1.makePuncture(2, 40);
            assertTrue(v1.ok());
            v1.makePuncture(1, 1);
            assertTrue(v1.ok());
            v1.makePuncture(2, 199);
            assertTrue(v1.ok());
    }
    
    @Test
    public void shouldPatchPuncture(){
            v1 = new Valley(300,300);
            v1.addTarp(new int[]{1,1}, new int[]{1,200});
            v1.addTarp(new int[]{30,30}, new int[]{70,70});
            assertTrue(v1.ok());
            v1.makePuncture(1, 30);
            v1.makePuncture(1, 100);
            v1.makePuncture(1, 150);
            v1.makePuncture(2, 30);
            v1.makePuncture(2, 34);
            assertTrue(v1.ok());
            v1.patchPuncture(1, 32);
            assertTrue(v1.ok());
            v1.patchPuncture(2, 32);
            assertTrue(v1.ok());
    }
    
    @Test
    public void shouldRain(){
        v1 = new Valley(400, 400);
        v1.startRain(100);
        assertTrue(v1.ok());
        v1.startRain(200);
        assertTrue(v1.ok());
        for (int i=0; i<10; i++){
            v1.startRain(100+i*2);
            assertTrue(v1.ok());
        }
    }
    
    @Test
    public void shouldStopRain(){
        v1 = new Valley(300,300);
        v1.startRain(20);
        v1.stopRain(20);
        assertTrue(v1.ok());
        v1.startRain(12);
        v1.stopRain(10);
        assertTrue(v1.ok());
        v1.startRain(100);
        v1.stopRain(102);
        assertTrue(v1.ok());
    }
    @Test
    public void shouldNotStopRain(){
        v1 = new Valley(300, 300);
        v1.stopRain(30);
        assertFalse(v1.ok());
        v1.stopRain(0);
        assertFalse(v1.ok());
        v1.startRain(100);
        v1.stopRain(50);
        assertFalse(v1.ok());
        v1.stopRain(105);
        assertFalse(v1.ok());
    }
    @Test 
    public void shouldZoom(){
        v1 = new Valley(300,300);
        v1.zoom('+');
        assertTrue(v1.ok());
        v1.makeVisible();
        v1.zoom('+');
        assertTrue(v1.ok());
        v1.zoom('-');
        assertTrue(v1.ok());
        v1.makeInvisible();
        v1.zoom('-');
        assertTrue(v1.ok());
    }
    @Test
    public void shouldQueryVineyards(){
        v1 = new Valley(400,400);
        assertEquals(v1.vineyards(), new int[][]{});
        v1.openVineYard("green", 10, 40);
        assertEquals(v1.vineyards(), new int[][]{{10,40}});
        v1.openVineYard("blue", 100, 150);
        assertEquals(v1.vineyards(), new int[][]{{10,40}, {100,150}});
        v1.openVineYard("yellow", 60, 70);
        assertEquals(v1.vineyards(), new int[][]{{10,40}, {100,150}, {60,70}});
        v1.closeVineYard("blue");
        assertEquals(v1.vineyards(), new int[][]{{10,40}, {60,70}});
    }
    
    @Test
    public void shouldQueryTarps(){
        v1 = new Valley(300,300);
        assertEquals(v1.Tarps(), new int[][][]{});
        v1.addTarp(new int[]{10,10}, new int[]{30,30});
        assertEquals(v1.Tarps(), new int[][][]{{{10,10}, {30,30}, {-1}}});
        v1.addTarp(new int[]{50, 120}, new int[]{120, 240});
        assertEquals(v1.Tarps(), new int[][][]{{{10,10}, {30,30}, {-1}}, {{50,120}, {120,240}, {-1}}});
        v1.makePuncture(1, 10);
        assertEquals(v1.Tarps(), new int[][][]{{{10,10}, {30,30}, {10}}, {{50,120}, {120,240}, {-1}}});
    }
    
}

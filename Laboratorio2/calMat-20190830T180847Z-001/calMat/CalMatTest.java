

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CalMatTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CalMatTest
{
    /**
     * Default constructor for test class CalMatTest
     */
    public CalMatTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }
    @Test    
    public void deberiaEmpilar(){
        CalMat cal = new CalMat();
        int [][] dA={{1,2,3},{4,5,6},{7,8,9}};
        cal.empile(dA);
        assertTrue(cal.ok());
    }
    
    @Test
    public void deberiaDesempilar(){
        CalMat cal = new CalMat();
        int [][] dA={{1,2,3},{4,5,6},{7,8,9}};
        cal.empile(dA);
        cal.desempile();
        assertTrue(cal.ok());
    }
    @Test
    public void deberiaDarElMaximo(){
        CalMat cal = new CalMat();
        int [][] dA={{1,2,3},{4,5,6},{7,8,9}};
        cal.empile(dA);
        int r = cal.opereMatriz('M');
        assertEquals(r,9);
    }
    @Test
    public void deberiaDarElMinimo(){
        CalMat cal = new CalMat();
        int [][] dA={{1,2,3},{4,5,6},{7,8,9}};
        cal.empile(dA);
        int r = cal.opereMatriz('m');
        assertEquals(r,1);
    }
    @Test
    public void deberiaDarElPromedio(){
        CalMat cal = new CalMat();
        int [][] dA={{1,2,3},{4,5,6},{7,8,9}};
        cal.empile(dA);
        int r = cal.opereMatriz('-');
        assertEquals(r,5);
    }
    @Test
    public void deberiaSumarDosMatrices(){
        int [][] dA={{1,2,3},{4,5,6},{7,8,9}};
        int [][] dB={{1,2,3},{4,5,6},{7,8,9}};
        int [][] dC={{2,4,6},{8,10,12},{14,16,18}}; 
        Matriz A=new Matriz(dA);
        Matriz B=new Matriz(dB);
        Matriz r=new Matriz(dC);
        Matriz res = A.sume(B);
        assertTrue(res.equals(r));
    }
    
    
    @Test
    public void deberiaRestarDosMatrices(){
        int [][] dA={{1,2,3},{4,5,6},{7,8,9}};
        int [][] dB={{1,2,3},{4,5,6},{7,8,9}};
        int [][] dC={{0,0,0},{0,0,0},{0,0,0}}; 
        Matriz A=new Matriz(dA);
        Matriz B=new Matriz(dB);
        Matriz r=new Matriz(dC);
        Matriz res = A.resta(B);
        assertTrue(res.equals(r));
    }


    @Test
    public void deberiaCrearMatricesDadoValor(){
        int [][] dA={{0,0,0},{0,0,0},{0,0,0}};
        Matriz A=new Matriz(dA);
        Matriz B=new Matriz(0,3,3);
        assertTrue(A.equals(B));
    }
    
    @Test
    public void deberiaCrearMatricesDiagonales(){
        int [] d={1,2,3,4};
        int [][] dD={{1,0,0,0},{0,2,0,0},{0,0,3,0},{0,0,0,4}};
        Matriz A=new Matriz(d);
        Matriz B=new Matriz(dD);
        assertTrue(A.equals(B));
    }    
    
    
    @Test
    public void deberiaCrearMatricesIdentidad(){
        int [][] dI={{1,0,0},{0,1,0},{0,0,1}};     
        Matriz A=new Matriz(3);
        Matriz I=new Matriz(dI);
        assertTrue(A.equals(I));
    }
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}

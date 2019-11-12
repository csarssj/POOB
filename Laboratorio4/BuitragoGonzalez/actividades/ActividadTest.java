import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class ActividadTest{
   
    private ActividadCompuesta plan;
    
    public ActividadTest(){
    }


    @Before
    public void setUp(){
        plan=new ActividadCompuesta("Plan de estudios");
        ActividadCompuesta is=new ActividadCompuesta("Software");
        ActividadCompuesta isb=new ActividadCompuesta("Software Basico");
        isb.actividad(new ActividadSimple("MBDA",4));
        isb.actividad(new ActividadSimple("POOB",4));
        ActividadCompuesta isi=new ActividadCompuesta("Software Intermedio");
        isi.actividad(new ActividadSimple("CVDS",4));
        isi.actividad(new ActividadSimple("ARSW",4));
        isi.actividad(new ActividadSimple("IETI",4));
        ActividadSimple isf=new ActividadSimple("Trabajo Dirigido",3);
        plan.actividad(isb);
        plan.actividad(isi);
        plan.actividad(isf);
    }

    @After
    public void tearDown(){
    }
    
    @Test
    public void deberiaCalcularElNumeroDeCreditosDeUnaActivia(){
        try {
           assertEquals(23,plan.creditos());
        } catch (ActividadExcepcion e){
            fail("Lanzó excepcion : "+e.getMessage());
        }    
    }    
    
    @Test
    public void deberiaCalcularElNumeroDeCreditosAsumiendo(){
        try {
           assertEquals(23,plan.creditos());
        } catch (ActividadExcepcion e){
            fail("Lanzó excepcion : "+e.getMessage());
        }    
    }       
    
    @Test
    public void deberiaLanzarExcepcionSiUnaActividadCompuestaNoTieneActividadesSimples(){
        plan.actividad(new ActividadCompuesta("Electivas"));
        try { 
           assertEquals(23,plan.creditos());
           fail("No lanzó excepcion");
        } catch (ActividadExcepcion e) {
            assertEquals(ActividadExcepcion.COMPUESTA_VACIA,e.getMessage());
        }    
    }    
    
    
   @Test
    public void deberiaLanzarExcepcionSiNoSeConocenLosCreditosDeUnaActividadSimple(){
        plan.actividad(new ActividadSimple("Practica",null));
        try { 
           assertEquals(23,plan.creditos());
           fail("No lanza la excepcion");
        } catch (ActividadExcepcion e) {
            assertEquals(ActividadExcepcion.SIMPLE_SIN_CREDITOS,e.getMessage());
        }    
    }     
    
    
}

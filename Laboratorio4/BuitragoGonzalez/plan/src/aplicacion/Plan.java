package aplicacion; 

import java.util.LinkedList;
import java.util.ArrayList;


public class Plan{
    private ActividadCompuesta plan;

<<<<<<< HEAD
    public Plan()throws ActividadExcepcion{
=======
    public Plan(){
>>>>>>> 58bc69471f0187fc38fa82cc8c4679c136078c7c
        plan = new ActividadCompuesta("Plan 14");
    }
    
    /**
     * Adiciona algunas materias
     */
<<<<<<< HEAD
    public void adicione() throws ActividadExcepcion{
=======
    public void adicione(){
>>>>>>> 58bc69471f0187fc38fa82cc8c4679c136078c7c
        Actividad ejemplos[] = {
                new ActividadSimple("AYED", "Al finalizar el curso el estudiante debe estar en capacidad de desarrollar soluciones informáticas "+
                "efectivas y modulares a problemas puntuales reales aplicando técnicas y herramientas adecuadas.",
                4),
            new ActividadSimple("POOB" , "Al finalizar el curso el estudiante debe estar en capacidad de desarrollar aplicaciones software "+
            "de tamaño pequeño en el paradigma orientado a objetos aplicando metodologías, técnicas y herramientas adecuadas.",
            4
            ),        
            new ActividadSimple("MBDA", "Al finalizar el curso el estudiante debe estar en capacidad de desarrollar modelos de datos "+
            "y diseñar, construir y manipular bases de datos relacionales.",
            4),
        };
        for(Actividad a : ejemplos) {
            plan.actividad(a);
        }
    }
    
   
    
    /**
     * Consulta la información de una actividad
     */
<<<<<<< HEAD
    public Actividad getInformacion(String id) {
=======
    public Actividad getInformacion(String id){
>>>>>>> 58bc69471f0187fc38fa82cc8c4679c136078c7c
        Actividad res=null;
        for(Actividad a: plan.getActividades()){
            if (a.getId().equals(id)){
                   res=a;
            }
        }
        return res;
    }


    /**
     * Adiciona una nueva actividad
     */
<<<<<<< HEAD
    public void adicione(String id, String creditos, String descripcion) throws ActividadExcepcion{
=======
    public void adicione(String id, String creditos, String descripcion){
>>>>>>> 58bc69471f0187fc38fa82cc8c4679c136078c7c
       plan.actividad(new ActividadSimple(id, descripcion, creditos.equals("") ? null: Integer.parseInt(creditos)));
    }


    

    
    /**
     * Consulta las actividades que contienen una informacion
     * @param subCadena La información a buscar
     * @return 
     */
<<<<<<< HEAD
    public ArrayList<Actividad> busque(String subCadena)throws ActividadExcepcion{
=======
    public ArrayList<Actividad> busque(String subCadena){
>>>>>>> 58bc69471f0187fc38fa82cc8c4679c136078c7c
        ArrayList<Actividad> resultados=null;
    	for(Actividad a: plan.getActividades()){
    	    if(a.getDescripcion().contains(subCadena)){
    	       resultados.add(a);
    	    }	
    	}
        return resultados;
    }

    /**
     * Consulta el numero de actividades
     * @return 
     */
    public int numeroActividades(){
        return plan.getActividades().size();
    }


    /**
     * Consulta todas las actividades
     * @return 
     */
    public String toString(){
	StringBuffer resultado=new StringBuffer();
	String creditos;
        for(Actividad a: plan.getActividades()) {
            try{
                creditos=a.creditos()+" ";
            }catch(ActividadExcepcion e){
                creditos="No definidos";
            }
            String informacion="Sigla: "+a.getId()+"\n"+"Creditos:"+creditos+"\n"+a.getDescripcion();
            resultado.append(informacion.length()<=150? informacion: informacion.substring(0,140)+"...");
            resultado.append('\n');
            resultado.append('\n');
        }
        return resultado.toString();
    }
}
  
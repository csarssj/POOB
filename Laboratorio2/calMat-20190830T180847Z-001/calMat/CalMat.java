
import java.util.Stack;

/** Calculadora.java
 * Representa una calculadora de matrices
 * @author ESCUELA 2019-02
 *  *Autores:
 *  -Santiago Buitrago
 *  -Cesar Gonzalez
 */
    
public class CalMat{

    private Stack<Matriz> operandos = new Stack<Matriz>();
    private boolean ok;
    /**
     * Constructor de la clase CalMat
     */
    public CalMat(){
    }

    /**
     * añade una matriz al stack de operandos
     * @param matriz, la matriz que se añade al stack 
     */
    public void empile(int [][] matriz){       
        Matriz m = new Matriz(matriz);
        operandos.push(m);
        ok=true;
    }
    
    /**
     * añade una matriz al stack de operandos con una diagonal definida
     * @param diagonal, la matriz con la diagonal pedida 
     * que se añade al stack 
     */
    public void empile(int [] diagonal){  
        Matriz m = new Matriz(diagonal);
        operandos.push(m);
        ok= true;
    }
    
    //Todos los elementos son e
    /**
     * añade una matriz al stack de operandos con un numero en una dimension
     * especifica
     * @param e, numero que se repite en toda la matriz
     * @param filas,columnas, dimensiones de la matriz a añadir
     */
    public void empile(int e, int filas, int columnas){ 
        Matriz m = new Matriz(e,filas,columnas);
        operandos.push(m);
        ok=true;
    }

    //Identidad de dimension d
    /**
     * añade una matriz  de indentidad al stack de operandos
     * @param d, dimension de la matriz identidad que se añade al stack 
     */
    public void empile(int d){ 
        Matriz m = new Matriz(d);
        operandos.push(m);
        ok = true;
    }
    
    /**
     * elimina la primer matriz  del stack de operandos
     */
    public void desempile(){
        if (!operandos.empty()){
            operandos.pop();
            ok= true;
        }
        else{
            ok= false;
        }
    }

    public String consulte(){
        return null;
    }
    

    // Los operadores binarios : + (suma), - (resta), . (multiplique elemento a elemento), * (multiplique matricial)
    /**
     * realiza operaciones binarias entre matrices.
     * @param operacion, Los operadores binarios :
     * + (suma), - (resta), 
     * . (multiplique elemento a elemento), * (multiplique matricial)
     */
    public void opereMatrices(char operacion){
        Matriz primera=  this.operandos.peek();
        this.desempile();
        Matriz segunda= this.operandos.peek();
        this.desempile();
        Matriz res;
        if(operacion =='+'){
            res = primera.sume(segunda);
        }
        else if(operacion =='-'){
            res = primera.resta(segunda);
        }
        else if (operacion =='.'){
            res =primera.multiplicacionElemento(segunda);
            
        }
        else{
            res =primera.multiplicacionMatricial(segunda);
        }
        this.operandos.push(res);
    }
    
    //Los operadores son: + (suma),  - (promedio), m (minimo), M (maximo), d(dimensones)
    //Los operadores consideran todos los elementos de la matriz
    /**
     * realiza operaciones sobre una matriz.
     * Los operadores consideran todos los elementos de la matriz
     * @param operacion, Los operadores binarios :
     * + (suma),  - (promedio), m (minimo), M (maximo), d(dimensones)
     */
    public int opereMatriz(char operacion){
        Matriz m=  this.operandos.peek();
        this.desempile();
        int res; 
        if(operacion =='+'){
            res = m.sume();
        }
        else if(operacion =='-'){
            res = m.promedio();
        }
        else if(operacion =='m'){
            res = m.min();
        }
        else if (operacion =='M'){
            res =m.max();
        }
        else{
            res =m.dimension();
        }
        return res;
    }
    
    
    //Los operadores son: + (suma),  - (promedio), m (minimo), M (maximo)
    //Las operaciones se realizan por filas    
    public void opereFilas(char operacion){
    }
    
    //Los operadores son: + (suma),  - (promedio), m (minimo), M (maximo)
    //Las operaciones se realizan por columnas
    public void opereColumnas(char operacion){
    } 
    /**
     * Indica si se logro realizar la ultima accion
     */
    public boolean ok(){
        return ok;
    }
}
    




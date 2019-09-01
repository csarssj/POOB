    /**
     * @author ECI, 2019
     *Autores:
     *  -Santiago Buitrago
     *  -Cesar Gonzalez
     */
    public class Matriz{
    
    public static Matriz UNCERO= new Matriz(new int[][]{{0}});
    public static int[][] matriz;
    private int x;
    private int y;
    /**
     * Retorna una matriz dados sus elementos. Si hay error en datos, retorna la matriz [0]
     * @param elementos, elementos a añadir a la matriz
     * 
     */
    public Matriz (int[][]  elementos) {
        this.matriz = elementos;

    } 
    /**
     * Retorna una matriz de tamaño x*y. Si hay error en datos, retorna la matriz [0]
     * @param x,y las dimensiones de la matriz
     */
    public Matriz (int  x, int y) {
        this.x= x;
        this.y =y;
        this.matriz = new int[x][y];

    } 
    
     /**
     * Retorna una matriz da su diagonal. Si hay error en datos, retorna la matriz [0]
     * @param la diagonal a añadir sobre la matriz
     */    
    public Matriz (int d []){
        int n= d.length;
        this.matriz = new int[n][n];
        int number=0;
        for(int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                 if(i==j)
                 this.matriz[i][j] = d[number];
            }
            number ++;
        }
    }    

    /**
     * Retorna una matriz de un numero repetido dada su dimension. Si hay error en datos, retorna la matriz [0]
     * @param e, numero que se repite en toda la matriz
     * @param filas,columnas, dimensiones de la matriz a añadir
     */
    public Matriz (int e, int f, int c) {
        this.matriz = new int[f][c];
        for(int i=0;i<f;i++){
            for (int j=0;j<c;j++){
                 this.matriz[i][j] = e;
            }
        }

    }
    
    /**
     * Retorna una matriz identidad dada su dimension. Si hay error en datos, retorna la matriz [0]
     * @param n, la dimension de la matriz
     */
    public Matriz (int n) {
        this.matriz = new int[n][n];
        for(int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                 if(i==j)
                 this.matriz[i][j] = 1;
            }
        }
        
    }
    
    /**
     * Retorna la dimension de la matriz
     */
    public int  dimension(){
        return this.matriz.length*this.matriz[0].length;
    }
    
    
    public int get(int f, int c){
        return 0;
    }
    
    /**
     * Compara esta matriz con otra
     * @param otra, matriz a comparar 
     */
    public boolean equals (Matriz otra) {
        if(this.matriz.length==otra.getMatriz().length ||this.matriz[0].length==otra.getMatriz()[0].length){
            for (int i=0;i<this.matriz[0].length;i++){
                for(int j=0;j<this.matriz.length;j++){
                    if(this.matriz[i][j]!=otra.getMatriz()[i][j]) return false;
                }
            }
            return true;
        }
        return false;
    }
    /**
     * imprime organizado por filas los elementos de la matriz
     */
    public void print(){
         for (int i=0;i<this.matriz[0].length;i++){
            for(int j=0;j<this.matriz.length;j++){
                System.out.print(this.matriz[i][j]);
            }
            System.out.println();
        }
        
    }
    
    /** 
     * Retorna una cadena con los datos de la matriz alineado por columna
     * 
    */
    @Override
    public String toString () {
          String s = "";
          for (int i=0;i<this.matriz[0].length;i++){
            for(int j=0;j<this.matriz.length;j++){
                s += Integer.toString(this.matriz[i][j]);
            }
            s +="\n";
          }
          return s;
    }   
    
    /**
     * Realiza la suma entre dos matrices
     * @param m, matriz a sumar con esta
     * @return matriz respuesta
     */
    public Matriz sume(Matriz m){
        matriz = new int [m.getMatriz().length][m.getMatriz()[0].length];
        for (int i=0; i<m.getMatriz().length; i++){
            for (int j=0; j<m.getMatriz()[0].length; j++) {
                matriz[i][j] = this.matriz[i][j]+m.getMatriz()[i][j];
            }
        }
        return new Matriz(matriz);
    }
    /**
     * Realiza la resta entre dos matrices
     * @param m, matriz a restar con esta
     * @return matriz respuesta
     */
    public Matriz resta(Matriz m){
        matriz = new int [m.getMatriz().length][m.getMatriz()[0].length];
        for (int i=0; i<m.getMatriz().length; i++){
            for (int j=0; j<m.getMatriz()[0].length; j++) {
                matriz[i][j] = this.matriz[i][j]-m.getMatriz()[i][j];
            }
        }
        return new Matriz(matriz);
    }
    /**
     * Realiza la multiplicacion de los elementos entre dos matrices
     * @param m, matriz a multiplicar con esta
     * @return matriz respuesta
     */
    public Matriz multiplicacionElemento(Matriz m){
        matriz = new int [m.getMatriz().length][m.getMatriz()[0].length];
        for (int i=0; i<m.getMatriz().length; i++){
            for (int j=0; j<m.getMatriz()[0].length; j++) {
                matriz[i][j] = this.matriz[i][j]*m.getMatriz()[i][j];
            }
        }
        return new Matriz(matriz);
    }
    /**
     * Realiza la multiplicacion Matricial entre dos matrices
     * @param m, matriz a multiplicar con esta
     * @return matriz respuesta
     */
    public Matriz multiplicacionMatricial(Matriz m){
        matriz = new int [m.getMatriz().length][m.getMatriz()[0].length];
        for (int i=0; i<m.getMatriz().length; i++){
            for (int j=0; j<m.getMatriz()[0].length; j++) {
                int sum=0;
                for (int k=0; k<m.getMatriz()[0].length; k++){
                        sum += this.matriz[i][k]*m.getMatriz()[k][j];
                }       
                matriz[i][j] = sum;
            }
        }
        return new Matriz(matriz);
    }
    /**
     * Realiza la suma de todos los elementos de una matriz
     * @return r, entero respuesta
     */
    public int sume(){
         int r = 0;
         for (int i=0; i<this.matriz.length; i++){
               for (int j=0; j<this.matriz[0].length; j++) {
                   r += matriz[i][j];
                }
         }
         return r;
    }
    /**
     * Retorna el promedio de la matriz
     * @Return int el promedio de la matriz
     */
    public int promedio(){
           int n= sume()/dimension();
           return n;
    }    
    /**
     * Retorna el maximo de la matriz
     * @Return int el maximo de la  matriz
     */
    public int max(){
           int n= 0;
           for (int i=0; i<this.matriz.length; i++){
               for (int j=0; j<this.matriz[0].length; j++) {
                   if(n< this.matriz[i][j]){
                       n=matriz[i][j];
                   }
                }
           } 
           return n;
    }  
    /**
     * Retorna el mínimo de la matriz
     * @Return int el minimo de la matriz
     */
    public int min(){
           int n= 10000000;
           for (int i=0; i<this.matriz.length; i++){
               for (int j=0; j<this.matriz[0].length; j++) {
                   if(n > this.matriz[i][j]){
                       n=matriz[i][j];
                   }
                }
           } 
           return n;
    }    
    //foc: indica si la suma es por filas('f') o por columnas('c')
    public Matriz sume(char foc){
        return null;
    }
    public int[][] getMatriz(){
        return matriz;
    }

    

    
 
}

package Rainfall;

/**
* Clase que soluciona el problema directing rainfalll
* 
* @author César Eduardo González y Brayan Santiango Buitrago 
* @version 20/10/2019
*/
import java.util.Arrays;
import java.lang.Math;
import java.util.Collections;

public class ValleyContest
{
    private int ini,fin,tarps;
    private String simulate[][];
    private int lona[][];
    private int max;
    private String[] pendientes;
    /**
     * Constructor clase ValleyContest
     */
    public ValleyContest(){
    }
    /**
     * Main de la solucion
     * @param vineyard[] posiciones en x del viñedo y la cantidad de lonas
     * @param tarp[][] contiene las lonas con sus dos puntos
     * 
     * @return res cantidad minima de huecos para abrir en las lonas
     */
    public int solve(int vineyard[],int tarp[][]){
        ini=vineyard[0];
        fin=vineyard[1];
        tarps=vineyard[2];
        lona= new int[tarps][4];
        pendientes  = new String[tarps];
        max=fin;
        for (int i=0; i<tarps; i++){
            int maX,minX;
            String m;
            if((tarp[i][3]-tarp[i][1])/(tarp[i][2]-tarp[i][0])>0){
                m="Negativo";
            }else{
                m="Positivo";
            }
            if (tarp[i][0]<tarp[i][2]){
                maX=tarp[i][2];minX=tarp[i][0];
            }else{
                maX=tarp[i][0];minX=tarp[i][2];
            }
            if (max<maX)max=maX;
            int f[]={tarp[i][1],tarp[i][3],minX,maX};
            pendientes[i]=m;
            lona[i]=f;                       
        }
        simulate=Crear(lona,pendientes,vineyard,max+1);
        int res=minimo(ini,0); 
        return res;
    }
    /**
     * Construccion de la matriz para simular el caso
     * @param s[][] matriz de las lonas con sus posiciones 
     * @param pendientes [] contiene las pendientes de la matriz
     * @param vine[] posiciones x del viñedo
     * @param n limite x de la matriz 
     * 
     * @return simulate[][] matriz con la simulación del caso 
     */

    private String[][] Crear(int s[][],String [] pendientes,int vine[],int n){
        String simulate[][]= new String[tarps+1][n];
        String inf="Suelo",b="Lona";
        for (int i=0;i<tarps+1;i++){
            for (int j=0;j<n;j++){
                simulate[i][j]=b;
                if(i==tarps){simulate[i][j]=inf;}
            }
        }
        for (int i=vine[0];i<=vine[1];i++){
           String f="Viñedo";
           simulate[tarps][i]=f;
        }
        for(int  i=0; i < s.length; i++){//ordena la matriz de abajo hacia arriba
            for(int j=0;j< s[0].length; j++){
                for(int x=0; x < s.length; x++){
                    for(int y=0; y <s[0].length; y++){
                        if(s[i][0] >= s[x][0]){
                            if(s[i][0] == s[x][0]){
                                if(s[i][1] >= s[x][1]){
                                    int[] t = s[i];
                                    s[i] = s[x];
                                    s[x] = t;
                                }
                            }
                            else{
                                int[] t = s[i];
                                s[i] = s[x];
                                s[x] = t;
                            }   
                        }
                    }   
                }    
            }
        }
        for (int i=0;i<tarps;i++){
            String temp=pendientes[i];
            for (int j=s[i][2];j<=s[i][3];j++){
                simulate[i][j]=temp;
            }
        }
        return simulate;
    }
    /**
     * Recursion para el camino minimo
     * @param x posicion en la matriz que simula el programa
     * @param y posicion en la matriz que simula el programa
     * 
     * @return hueco cantidad minima de huecos para abrir en las lonas
     */
    private int minimo(int x,int y){
        int hueco=0;
        if (x>max || x<0 || simulate[y][x]=="Suelo"){
            hueco=1000; 
        }else if (simulate[y][x]=="Viñedo" ){ 
            hueco=0;
        }else if(simulate[y][x]=="Lona"){
            hueco=minimo(x,y+1);
        }else if(simulate[y][x]=="Positivo" || simulate[y][x]=="Negativo"){ 
            int m;
            if(simulate[y][x]=="Positivo") m=1;
            else{m=-1;}
            int min=Math.min(minimo(x,y+1)+1,minimo(x+m,y));
            hueco=min;
        }
        return hueco;
    }
    
}
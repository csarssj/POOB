package aplicacion;

import java.util.*;
import java.awt.*;
import javax.swing.*;
public class Replicate {
    private Boolean estado;
    private int ve;
    private int tamano,n;
    private Casilla [][] fichas;
    final public static int[] DX = {-1,-1,-1,0,0,0,1,1,1};
    final public static int[] DY = {-1,0,1,-1,0,1,-1,0,1};
    public Replicate(int n ,int[][] puntos){
        this.tamano = 10;
        fichas = new Casilla[10][10];
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
              fichas[i][j]=new Casilla(i,j,0);
            }
        }
        for(int x = 0; x<n;x++){
                 fichas[puntos[x][0]][puntos[x][1]] = new Casilla(puntos[x][0],puntos[x][1],1);
        }
    }
    public void imprimir(){
        for(int i = 0; i<fichas.length;i++){
            for(int j = 0; j<fichas.length;j++){
                System.out.print(fichas[i][j].getEstado());
            }
            System.out.println();
        }
    }
    public Casilla[][] getFichas(){
        return fichas;
    }
     public void replicar(){
        for(int i = 0; i<fichas.length;i++){
            for(int j = 0; j<fichas .length;j++){
                this.ve=0;
                floodFillUtil(i,j);
            }
        }
    }
    public void floodFillUtil(int x,int y) { 
        int M = fichas.length;
        int N = fichas[0].length;
        if (x < 0 || x >= M || y < 0 || y >= N) 
            return ; 
        if (fichas[x][y].getEstado() == 1) 
            fichas[x][y]=new Casilla(x,y,0);
      
        
        fichas[x][y]=new Casilla(x,y,1);
      
        // Recur for north, east, south and west 
        floodFillUtil(x+1, y); 
        floodFillUtil(x-1, y); 
        floodFillUtil(x+1, y+1); 
        floodFillUtil(x+1, y-1); 
        floodFillUtil(x-1, y+1); 
        floodFillUtil(x-1, y-1);
        floodFillUtil(x, y+1); 
        floodFillUtil( x, y-1); 
        
    }
   
}
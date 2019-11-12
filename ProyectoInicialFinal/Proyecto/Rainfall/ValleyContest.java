package Rainfall;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;
import java.lang.Math;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
/**
* Clase que soluciona el problema directing rainfalll
* 
* @author César Eduardo González y Brayan Santiango Buitrago 
* @version 27/10/2019
*/
public class ValleyContest {
    static HashSet<Integer> visitados;
    static Hashtable<Integer,Integer> V;
    static Hashtable<Integer,ArrayList<Integer>> grafo;
    static ArrayList<Integer> ordenado;
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
         * @return min cantidad minima de huecos para abrir en las lonas
         */
    public int solve(int vineyard[],int tarp[][]){
        visitados = new HashSet<Integer>();
        V = new Hashtable<Integer ,Integer>();
        grafo = new Hashtable<Integer,ArrayList<Integer>>();
        ordenado = new ArrayList<Integer>(); 
        
        Hashtable<Integer,int []> lonas=new Hashtable<Integer ,int []>();
    
        
        int l = vineyard[0];
        int r = vineyard[1];
        int nlonas = vineyard[2];
        
        if (nlonas==0){return 0;}   
        else{
            
            int [] deltax = new int[nlonas*2];
            PriorityQueue<Integer> Q = new PriorityQueue<Integer>();
            
            int cont = 0;
            
            for (int i=0;i<nlonas;i++){
                int [] lona = new int[4];
                lona[0]=tarp[i][0];
                lona[1]=tarp[i][1];
                lona[2]=tarp[i][2];
                lona[3]=tarp[i][3];
                
                int x1 =(int)Math.min(lona[0], lona[2]);
                int x2 =(int)Math.max(lona[0], lona[2]);
                deltax[cont]=x1;
                deltax[cont+1]=x2;
                cont+=2;
                if (x1 == lona[2]){
                    int zz=lona[0];
                    int zz1=lona[1];
                    lona[0]=lona[2];
                    lona[1]=lona[3];
                    lona[2]=zz;
                    lona[3]=zz1;
                    }
                lonas.put(x1, lona);
                Q.add(x2);
            }
            Arrays.sort(deltax);
            if (l<deltax[0] || r>deltax[(nlonas*2)-1]){ return 0;}
            else{       
                int cont2=0;

                HashSet<Integer> T = new HashSet<Integer>();
                

                Hashtable<Integer,Integer> Z=new Hashtable<Integer ,Integer>();
                while (!Q.isEmpty()){
                    int xi= deltax[cont2];
                    if (Q.element()==xi){
                        Q.remove();
                        int zz=Z.get(xi);
                        T.remove(zz);
                        boolean flag =true;
                        for (int aa : grafo.get(zz)){
                            if (!visitados.contains(aa)){flag = false;break;}
                        }
                        if (flag){
                            grafo.remove(zz);
                        }
                        visitados.add(zz);
                        ordenado.add(zz);
                        Z.remove(xi);
                    }
                    else{
                        
                        int [] lona = lonas.get(xi);
                        if (grafo.get(xi)==null){grafo.put(xi,new ArrayList<Integer>());}
                        Z.put(lona[2], xi);
                        for (Integer i : T){
                            int [] lona2 = lonas.get(i);
                            double m = ((double)lona2[3]-(double)lona2[1])/((double)lona2[2]-(double)lona2[0]);
                            double b = (-m*(double)lona2[0])+(double)lona2[1];
                            
                            if (lona[1]<((xi*m)+b)){
                                
                                ArrayList<Integer> hijos = grafo.get(xi);
                                
                                hijos.add(i);
                                grafo.replace(xi, hijos);
                                
                                
                            }
                            else{
                                ArrayList<Integer> hijos = grafo.get(i);
                                hijos.add(xi);
                                grafo.replace(i,hijos);}
                            
                        }
                        T.add(xi);
                    }
                    cont2+=1;
                }
                Set<Integer> keys = grafo.keySet();
                for( int key: keys){
                    if (!visitados.contains(key)) {
                        topologicoVisitar(key);
                    }
                
                }

                for (int i=deltax[0];i<=deltax[(nlonas*2)-1];i++){
                    
                    V.put(i,0);
                
                }
                
                int  xprimera = ordenado.get(0);
                int [] primera = lonas.get(xprimera);
                double pendientepri = ((double)primera[3]-(double)primera[1])/((double)primera[2]-(double)primera[0]);
                int cambio;
                int [] anterior = new int [2];
                if (pendientepri>0){cambio=1;}
                else{cambio=-1;}
                
                for (int  xlona :ordenado){
                    int [] lona = lonas.get(xlona);
                    int xx1 = lona[0];
                    int yy1 = lona[1];
                    int xx2 = lona[2];
                    int yy2 = lona[3];
                    double m = ((double)yy2-(double)yy1)/((double)xx2-(double)xx1); 
                    int [] lona2 = lonas.get(anterior[0]);
                    double mm = ((double)lona2[3]-(double)lona2[1])/((double)lona2[2]-(double)lona2[0]);
                    if (mm>0){cambio=1;}
                    else{cambio=-1;}
                    if (m<0){
                        if (cambio==-1){
                            anterior[0]=xx1;
                            anterior[1]=xx2;
                            for (int i=xx1;i<xx2;i++){
                                V.replace(i,V.get(i)+1);
                            }
                        }
                        else{
                            int bandera =-1;
                            for (int i=xx1;i<=xx2;i++){
                                if (i>=anterior[0] && i<=anterior[1]){
                                    if (bandera==-1 && i!=xx2){bandera=V.get(i);V.replace(i,bandera+1);}
                                    else if (i!=xx2){V.replace(i,bandera+1);}
                                    else{V.replace(xx2,bandera);}
                                }
                                else if (i==xx2){V.replace(i, V.get(i));}
                                else{V.replace(i,V.get(i)+1);}
                            }
                            
                            cambio = -1;
                        }

                        
                    }
                    else{
                        
                        if (cambio==1){
                            anterior[0]=xx1;
                            anterior[1]=xx2;
                            for (int i=xx2;i>xx1;i--){
                                
                                V.replace(i,V.get(i)+1);
                            }
                        }
                        else{
                            int bandera=-1;
                            for (int i=xx2;i>=xx1;i--){
                                if (i>=anterior[0] && i<=anterior[1]){
                                    if(bandera==-1 && i!=xx1){bandera=V.get(i);V.replace(i,bandera+1);}
                                    else if (i!=xx1){V.replace(i, bandera+1);}
                                    else{V.replace(xx1, bandera);}
                                }
                                else if (i==xx1){V.replace(i, V.get(i));}
                                else{V.replace(i, V.get(i)+1);}
                            }
                            
                            cambio=1;
                        }

                    }
                
                }
                int min = V.get(l); 
                for (int i=l+1;i<=r;i++){
                    
                    if(min>V.get(i)){min=V.get(i);}
                }
                
                return min;
                
                }
        }
        
        
    }
    /**
         * ordena el grafo por medio de sus nodos
         * @param nodo nodo ingresado para ordenar en el grado
         * 
         */
	private static void topologicoVisitar(int nodo) {
		visitados.add(nodo);
		for (int i:grafo.get(nodo)) {
			if (!visitados.contains(i)) {
				topologicoVisitar(i);
			}
			
		}
		ordenado.add(nodo);
			
	}
	


}
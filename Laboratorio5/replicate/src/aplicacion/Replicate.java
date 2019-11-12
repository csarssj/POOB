<<<<<<< HEAD
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
   
=======
// Arup Guha
// 5/8/2018
// Solution to 2017 World Finals Problem G: Replicate Replicate Rfplicbte

import java.util.*;

public class Replicate {
	private Boolean estado;
	private Color color;
	final public static int[] DX = {-1,-1,-1,0,0,0,1,1,1};
	final public static int[] DY = {-1,0,1,-1,0,1,-1,0,1};
	public Replicate(Boolean estado, Color color){
		
	}
	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		int w = stdin.nextInt();
		int h = stdin.nextInt();
		boolean[][] grid = new boolean[h][w];

		// Read in the grid.
		for (int i=0; i<h; i++) {
			String line = stdin.next();
			for (int j=0; j<w; j++)
				grid[i][j] = line.charAt(j) == '#';
		}

		while (true) {

			if (grid.length < 3 || grid[0].length < 3) break;

			boolean[][] trans = getTrans(grid);

			int r = getError(grid);
			int c = getError(trans);

			// There was no transcription error.
			if (r == -1 && c == -1)
				grid = reduce(grid);

			// There was an error.
			else {

				grid[r][c] = !grid[r][c];
				boolean[][] tmp = reduce(grid);

				// This means that this wasn't the only error, so it's the smallest pattern.
				if (tmp == null) {
					grid[r][c] = !grid[r][c];
					break;
				}

				// That error fixed it, continue.
				else
					grid = tmp;
			}
		}

		// Copy booleans into a String and print.
		for (int i=0; i<grid.length; i++) {
			char[] line = new char[grid[0].length];
			Arrays.fill(line,'.');
			for (int j=0; j<grid[0].length; j++)
				if (grid[i][j])
					line[j] = '#';
			System.out.println(new String(line));
		}
	}

	// Returns the transpose of arr.
	public static boolean[][] getTrans(boolean[][] arr) {
		boolean[][] res = new boolean[arr[0].length][arr.length];
		for (int i=0; i<arr.length; i++)
			for (int j=0; j<arr[0].length; j++)
				res[j][i] = arr[i][j];
		return res;
	}

	// Returns the first row with an error in a, -1 if there was no error.
	public static int getError(boolean[][] a) {

		int r = a.length, c = a[0].length;
		boolean[][] res = new boolean[r][c];

		// Go through newer grid.
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {

				// This is filling mode.
				if (i<r-2 && j<c-2) {

					// Count # of surrounding true spots.
					int cntT = 0;
					for (int z=0; z<DX.length; z++) {
						int nX = i + DX[z];
						int nY = j + DY[z];
						if (inbounds(nX,nY,r,c) && res[nX][nY]) cntT++;
					}

					// We set the forced square. If this doesn't trigger, it should be false.
					if ((a[i][j] && cntT%2 == 0) || (!a[i][j] && cntT%2 == 1))
						res[i+1][j+1] = true;
				}

				// This is checking mode.
				else {

					// Count # of surrounding true spots.
					int cntT = 0;
					for (int z=0; z<DX.length; z++) {
						int nX = i + DX[z];
						int nY = j + DY[z];
						if (inbounds(nX,nY,r,c) && res[nX][nY]) cntT++;
					}

					// This is an error.
					if ((a[i][j] && cntT%2 == 0) || (!a[i][j] && cntT%2 == 1))
						return i;
				}
			} // endj
		} // end i

		// If we get here, there was no error.
		return -1;
	}

	// Returns the reduced square of a (step before) or null if there were any errors.
	public static boolean[][] reduce(boolean[][] a) {

		int r = a.length, c = a[0].length;
		boolean[][] res = new boolean[r][c];

		// Go through newer grid.
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {

				// This is filling mode.
				if (i<r-2 && j<c-2) {

					// Count # of surrounding true spots.
					int cntT = 0;
					for (int z=0; z<DX.length; z++) {
						int nX = i + DX[z];
						int nY = j + DY[z];
						if (inbounds(nX,nY,r,c) && res[nX][nY]) cntT++;
					}

					// We set the forced square. If this doesn't trigger, it should be false.
					if ((a[i][j] && cntT%2 == 0) || (!a[i][j] && cntT%2 == 1))
						res[i+1][j+1] = true;
				}

				// This is checking mode.
				else {

					// Count # of surrounding true spots.
					int cntT = 0;
					for (int z=0; z<DX.length; z++) {
						int nX = i + DX[z];
						int nY = j + DY[z];
						if (inbounds(nX,nY,r,c) && res[nX][nY]) cntT++;
					}

					// This is an error.
					if ((a[i][j] && cntT%2 == 0) || (!a[i][j] && cntT%2 == 1))
						return null;
				}
			} // endj
		} // end i

		// This is our result.
		return trim(res);
	}

	public static boolean inbounds(int x, int y, int r, int c) {
		return x >= 0 && x < r && y >= 0 && y < c;
	}

	public static boolean[][] trim(boolean[][] a) {

		// Find bounding box.
		int minX = a.length, minY = a[0].length, maxX = -1, maxY = -1;
		for (int i=0; i<a.length; i++) {
			for (int j=0; j<a[0].length; j++) {
				if (a[i][j]) {
					minX = Math.min(minX, i);
					maxX = Math.max(maxX, i);
					minY = Math.min(minY, j);
					maxY = Math.max(maxY, j);
				}
			}
		}

		// Copy over.
		boolean[][] res = new boolean[maxX-minX+1][maxY-minY+1];
		for (int i=minX; i<=maxX; i++)
			for (int j=minY; j<=maxY; j++)
				res[i-minX][j-minY] = a[i][j];

		return res;
	}
>>>>>>> 58bc69471f0187fc38fa82cc8c4679c136078c7c
}
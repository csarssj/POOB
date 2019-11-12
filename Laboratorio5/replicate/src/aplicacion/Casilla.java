package presentacion;

import java.util.*;
import java.awt.*;

public class Casilla {

    private int xPos, yPos, estado; 
    
    public Casilla(int x, int y, int estado) {
        xPos = x;
        yPos = y;
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public Point getPosition () {
        Point position = new Point(xPos, yPos);
        return position;
    }
}
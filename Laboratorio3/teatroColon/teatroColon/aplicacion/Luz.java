package aplicacion;
import java.awt.*;
public class Luz implements EnEscena{
    private Teatro teatro;
    private Color color;
    private int[] x;
    private int[] y;
    private String nombre;
    public Luz(Teatro teatro, String name, int[] x, int[] y ){
        this.x = x;
        this.y = y;
        this.teatro = teatro;
        this.color = new Color(102,102,102);
        this.nombre = name;
    }
    public int getPosicionX(){
        return x[0];
    }
    public int getPosicionY(){
        return y[0];
    }
    public int[] getPosicionX1(){
        return x;
    }
    public int[] getPosicionY1(){
        return y;
    }
    public Color getColor(){
        return color;
    }

    public void actue(){
        color = new Color(255,255,255);
    }
    public void corte(){
        color = new Color(102,102,102);
    };
    @Override
    public String forma(){
       return "Luz";
    }
    
}

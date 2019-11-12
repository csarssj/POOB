package presentacion;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

public class KalahButton extends JButton{
	private int coordX;
	private Color color;

	public KalahButton(String mensaje,int coordX , Color color){
		super(mensaje);
		this.color = color;
		this.coordX = coordX;
		setOpaque(false);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(false);
	}
	    protected void paintComponent(Graphics g) {
	        Color c;
	        Graphics2D g2 = (Graphics2D) g;
	         Paint oldPaint = g2.getPaint();
	         c=color;
	          RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(
	                    0,0,getWidth(),getHeight()-1,50,50);
	            g2.clip(r2d);
	            g2.setPaint(new GradientPaint(0.0f, 0.0f, c.brighter(),
	                    0.0f, getHeight(), c.brighter()));
	            g2.fillRect(0,0,getWidth(),getHeight());
	            
	            g2.drawRoundRect(0, 0, getWidth()-2 , getHeight() -2, 50, 50);

	        g2.setPaint(oldPaint);
	        super.paintComponent(g);
	    }
	    public void setBackground(Color newColor) {
	    	super.setBackground(newColor);
	    	this.color = newColor;
	    }
	public int getCoordX(){
		return coordX;
	}
}
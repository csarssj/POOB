package presentacion;

import javax.swing.*;
import java.awt.*;

public class myButton  extends JButton {
	/**
	private Color botonColor; 
	private int font;
	*/
	
	public myButton(Icon icono){
		super(icono);
	}
	
	public void setIcon(ImageIcon icono){
		super.setIcon(icono);
		
	}
	
	public void setTransparent(){
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusable(false);
	}
	
	
	
}
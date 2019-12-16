package presentacion;

import java.awt.Graphics;
import java.awt.Image;
 
import javax.swing.ImageIcon;
import javax.swing.JPanel;
 
public class JPanelB extends JPanel {
 
	private Image background;
 
	public void paintComponent(Graphics g) {
		int width = this.getSize().width;
		int height = this.getSize().height;
		if (this.background != null) {
			g.drawImage(this.background, 0, 0, width, height, null);
		}
		super.paintComponent(g);
	}
 
	
	public void setBackground(ImageIcon image) {
		this.setOpaque(false);
		this.background = image.getImage();
		repaint();
	}
}
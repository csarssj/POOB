package presentacion;

import aplicacion.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Observer;
import java.util.Observable;

public class GamePanel extends JPanel implements Observer {
    private final DonkeyPoob model; //model that uses the panel
    
    //images that will be painted
    Image mario = new ImageIcon(getClass().getResource("/resources/mario.png")).getImage();
    Image kong = new ImageIcon(getClass().getResource("/resources/konky_dong.gif")).getImage();
    Image peach = new ImageIcon(getClass().getResource("/resources/peach.png")).getImage();
    Image barrel = new ImageIcon(getClass().getResource("/resources/barrel.png")).getImage();
    Image platform = new ImageIcon(getClass().getResource("/resources/platform.png")).getImage();
    Image ladder = new ImageIcon(getClass().getResource("/resources/ladder.png")).getImage();

    public GamePanel(DonkeyPoob model) throws IOException //constructor
    {
        setOpaque(true);
        setBackground(Color.BLACK);
        this.model = model;
        this.model.addObserver(this);
    }

    @Override
    public void paintComponent(Graphics g) //overriden paintComponent function
    {
        super.paintComponent(g); 
        g.drawImage(kong, 60, 120, 100, 100, this); //draw kong
        for (StaticObject object : model.getSOList()) //paint every static object - ladders, platforms, peach
        {
            if(object instanceof Plataforma) g.drawImage(platform,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            if(object instanceof Escalera) g.drawImage(ladder,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            if(object instanceof Peach) g.drawImage(peach,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
        }
        
        //write the score on the top of the screen
        g.setColor(Color.WHITE);
        g.drawString("Score: " + model.getScore(), 500, 50); 

        for (MovingObject object : model.getMOList()) //paint every moving object - mario and barrel
        {
            if(object instanceof Barril) g.drawImage(barrel,(int)object.getXPos(), (int)object.getYPos(), (int)object.getWidth(), (int)object.getHeight(), null);
            if(object instanceof Mario) g.drawImage(mario, (int)object.getXPos(),(int)object.getYPos(),(int)object.getWidth(),(int)object.getHeight(), null);
        } 	
    }

    public void update(Observable caller, Object data)
    {
        repaint();
    }

}


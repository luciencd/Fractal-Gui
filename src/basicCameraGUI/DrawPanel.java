package basicCameraGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DrawPanel extends JPanel
{
	private BufferedImage image;

    public DrawPanel()
    {
        
        //setBackground(new Color.WHITE);
    }
    public void setImage(BufferedImage img){
    	System.out.println("SetImage drawpanel");
    	image = img;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        //super.paintComponent(g);
    	System.out.println("drawPanel");
    	//Currently BufferedImage is null.
        g.drawImage(image,0,0,getWidth(),getHeight(),this);
    }

   

}
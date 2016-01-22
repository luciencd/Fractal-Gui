package basicCameraGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel
{
	private BufferedImage image;
	private ArrayList<Coordinate<Double>> history = new ArrayList<Coordinate<Double>>();
	boolean historyVisible = true;
	
	private int width;
	private int height;
	/**
	 * To ensure the buffered image syncs with the view size.
	 * @param width_
	 */
	public void setWidth(int width_){
		width = width_;
	}
	public void setHeight(int height_){
		height = height_;
	}
	@Override
	public int getWidth(){
		return width;
	}
	@Override
	public int getHeight(){
		return height;
	}
	

    public void setImage(BufferedImage img){
    	//System.out.println("SetImage drawpanel");
    	image = img;
    }
    
    public void setHistory(ArrayList<Coordinate<Double>> history_){
    	history = history_;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {

        g.drawImage(image,0,0,getWidth(),getHeight(),this);
        
        
        /*drawing history of the component*/
        /*how to get different styles of history??*/
        Coordinate<Double> past;
    	if(history.size() == 0){
    		past = new Coordinate<Double>((double)getWidth()/2,(double)getHeight()/2);
    	}else{
    		past = history.get(0);
    	}
    	
        for(Coordinate<Double> item : history){//really awkward.
        	//System.out.println(past.x.intValue()+" "+past.y.intValue());
        	g.setColor(new Color(0,255,0));
        	g.fillOval(item.x.intValue(), item.y.intValue(), 2,2);
        	g.drawLine(past.x.intValue(), past.y.intValue(), item.x.intValue(), item.y.intValue());
        	past = item;
        }
        
        
    }

   

}
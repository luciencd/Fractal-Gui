package basicCameraGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.JobAttributes;
import java.awt.PageAttributes;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.SwingPropertyChangeSupport;

public class FractalDisplayModel implements Model{
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	
	
	private BufferedImage image;
	
	private int width = 500; 
	private int height = 500;
	private double resolution = 500;
	private ComplexNumber z;
	private SwingPropertyChangeSupport propChangeFirer;
	
	
	public FractalDisplayModel(){
		this(-1.0,1.0,-1.0,1.0);
		//this(-10,10,-10,10);
	}
	
	public FractalDisplayModel(double x1_,double x2_,double y1_,double y2_){
		x1=x1_;
		x2=x2_;
		y1=y1_;
		y2=y2_;
		
		//propChangeFirer = new SwingPropertyChangeSupport(this);
		image = computeImage();
		System.out.println("image constructor2");
	}


    public void addListener(PropertyChangeListener prop) {
        propChangeFirer.addPropertyChangeListener(prop);
        System.out.println("addListener");
    }
    
    /**
     * Sets the value of x to x_
     * @param x_
     * @modifies x
     * @effects changes x to x_
     */
    public void setX1(double x1_){
    	x1 = x1_;
    }
    /**
     * Sets the value of y to y_
     * @param x_
     * @modifies x
     * @effects changes x to x_
     */
    public void setY1(double y1_){
    	y1 = y1_;
    }
    
    
    /**
     * Sets the value of x to x_
     * @param x_
     * @modifies x
     * @effects changes x to x_
     */
    public void setX2(double x2_){
    	x2 = x2_;
    }
    /**
     * Sets the value of y to y_
     * @param x_
     * @modifies x
     * @effects changes x to x_
     */
    public void setY2(double y2_){
    	y2 = y2_;
    }
    
    public void setHeight(int height_){
    	height = height_;
    }
    public void setWidth(int width_){
    	width = width_;
    }
    
    /**
     * Sets the value of resolution to resolution_
     * @param resolution_
     * @modifies resolution_
     * @effects changes resolution to resolution_
     */
    public void setResolution(double resolution_){
    	resolution = resolution_;
    }
    
    //remove
    
    
    
    public int getWidth(){
    	return width;
    }
    
    public int getHeight(){
    	return width;
    }
    public BufferedImage getImage(){
    	System.out.println("getimagestart");
    	image = computeImage();
    	System.out.println("getimageend");
    	return image;
    }
    
    public void removeChangeListener(Controller c){
    	
    }
    public void addChangeListener(Controller c){
    	
    }
    
	/**
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	public BufferedImage computeImage(){
		//x1, x2
		//i1, i2 actual position of 4 corners in complex plane.
		
		BufferedImage img = new BufferedImage((int)getWidth(), (int)getHeight(),BufferedImage.TYPE_BYTE_GRAY);
		//ComplexNumber center = new ComplexNumber(x,y);
		
		int max = 255;

		Graphics g = img.getGraphics();
		//g.setColor(new Color(0,255,0));
		//g.drawLine(250,250,250,305);
		double stepx = (x2-x1)/resolution;//
		double stepy = (y2-y1)/resolution;
		
		double x = 0;
		double y = 0;
		double widthStep = (double)width/resolution;
		double heightStep = (double)height/resolution;
		
		//p1 (0.5,0.0)-> p2 (1.0,1.0)
		for(double i = x1; i<x2; i+=stepx){
			x+=widthStep;
			y = 0;
			for(double j = y1; j<y2;j+=stepy){
				
				y+=heightStep;
				ComplexNumber z0 = new ComplexNumber(i,j);
				int gray =  max - mand(z0,max);

				Color color;
				
				color = new Color(gray, gray, gray);
				
				
				g.setColor(color);
				//System.out.println(x+ " "+y+" "+gray);
				g.drawLine((int)x,(int)y,(int)x,(int)y);
			}
			
		}
		return img;
		
	}
	
	/**
	 * 
	 * @param z0
	 * @param max
	 * @return how many iterations it took to leave z0
	 */
	public static int mand(ComplexNumber z0,int max){
		ComplexNumber z = z0;
		int iteration = 0;
		double x = 0.0;
		double y = 0.0;
		while(Math.pow(2,x)+Math.pow(2,y) < 2*2 && iteration < max){
			//ComplexNumber xtemp = Math.pow(2,z.getRe())-Math.pow(2,z.getIm())+
			double xtemp = x*x-y*y+z.getRe();
			y = 2*x*y + z.getIm();
			x = xtemp;
			
			iteration++;
		}
		return iteration;
	}
	
	public void print(){
		System.out.println("x1("+x1+") x2("+x2+") y1("+y1+") y2("+y2+")"+"res("+resolution+")"+"width("+getWidth()+") height("+getHeight()+")");
	}
	
}

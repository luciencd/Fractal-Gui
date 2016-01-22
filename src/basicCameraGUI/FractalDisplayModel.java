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
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.SwingPropertyChangeSupport;

public class FractalDisplayModel implements Model, ChangeListener{
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	
	
	private BufferedImage image;
	
	private int width;// = 400; 
	private int height;// = 400;
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
		//image = computeImage();
		//System.out.println("image constructor2");
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
    public double getX1(){return x1;}
    public double getX2(){return x2;}
    public double getY1(){return y1;}
    public double getY2(){return y2;}
    
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
    
    //take in a square of the image and generate the two coordinates based on it.
    public void squareVision(double x1,double x2,double y1,double y2){
    	
    }
    
    public void zoom(double zoom){
    	x1/=zoom;
    	x2/=zoom;
    	y1/=zoom;
    	y2/=zoom;
    }
    
    //passing by reference to edit x and y
    //taking real values 0.23123 =>  230px
    public double realToWindowX(double x){
    	double widthFraction = (x-x1)/(x2-x1);
    	return widthFraction*width;
    }
    public double realToWindowY(double y){
    	double heightFraction = (y-y1)/(y2-y1);
    	return heightFraction*height;
    }
    
    //passing by reference to edit x and y
    //taking window values 230px => .5611
    public double windowToRealX(double x){
    	double w = x/width;//50%
    	return w*(x2-x1)+x1;//50% of the difference(midpoint)+first point
    }
    
    public double windowToRealY(double y){
    	double h = y/height;
    	return h*(y2-y1)+y1;
    }
    
    
    
    public int getWidth(){
    	return width;
    }
    
    public int getHeight(){
    	return width;
    }
    public void updateImage(){
    	image = computeImage();
    }
    public BufferedImage getImage(){
    	return image;
    }
    
    public void removeChangeListener(Controller c){
    	
    }
    
    //Do something with this.
    public void addChangeListener(Controller c){
    	//listenerList.add(ChangeListener.class, c);
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
		
		BufferedImage img = new BufferedImage((int)getWidth(), (int)getHeight(),BufferedImage.TYPE_INT_RGB);
		//ComplexNumber center = new ComplexNumber(x,y);
		
		Integer max = 120;

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
				double gray =  max - mand(z0,max);
				Color color;
				
				Color FAR = new Color(255, 255, 30);
				Color CLOSE = new Color(128, 0,0);

				
				//0...255 scale.
				double scale =((gray/(double)max));
				if(gray == max){
					
					color = new Color(255,0,255);
				}else{
					int red = (int)Math.abs((scale * FAR.getRed()) + ((1 - scale) * CLOSE.getRed()));
					int green = (int)Math.abs((scale * FAR.getGreen()) + ((1 - scale) * CLOSE.getGreen()));
					int blue = (int)Math.abs((scale * FAR.getBlue()) + ((1 - scale) * CLOSE.getBlue()));
					//System.out.println(red+" "+green+ " "+blue);
					
					color = new Color(red,green,blue);
				}
				
				
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
		while(Math.pow(2,x)+Math.pow(2,y) < 2*2){
			if(iteration>=max){
				break;
			}
			
			double xtemp = x*x-y*y+z.getRe();
			y = 2*x*y + z.getIm();
			x = xtemp;
			
			iteration++;
		}
		return iteration;
	}
	
	/**
	 * Generates a list of coordinates based on the start of a single complex number(getting journey of single element).
	 * return List<
	 */
	public ArrayList<Coordinate<Double>> listCoordinates(ComplexNumber z0,int max){
		ComplexNumber z = z0;
		int iteration = 0;
		double x = 0.0;
		double y = 0.0;
		ArrayList<Coordinate<Double>> history = new ArrayList<Coordinate<Double>>();
		history.add(new Coordinate<Double>(z.getRe(),z.getIm()));
	
		while(Math.pow(2,x)+Math.pow(2,y) < 2*2){
			if(iteration>=max){
				break;
			}
			
			double xtemp = x*x-y*y+z.getRe();
			y = 2*x*y + z.getIm();
			x = xtemp;
			history.add(new Coordinate<Double>(x,y));
			
			iteration++;
		}
		return history;
	}
	
	public void print(){
		System.out.println("x1("+x1+") x2("+x2+") y1("+y1+") y2("+y2+")"+"res("+resolution+")"+"width("+getWidth()+") height("+getHeight()+")");
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		image = computeImage();
	}
	
}

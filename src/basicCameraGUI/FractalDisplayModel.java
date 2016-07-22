package basicCameraGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.JobAttributes;
import java.awt.PageAttributes;
import java.awt.PrintJob;
import java.awt.RenderingHints;
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

	private ArrayList<Coordinate<Double,Double>> points;
	
	private int width;// = 400; 
	private int height;// = 400;
	private double resolution;// = 500;
	private ComplexNumber z;
	private SwingPropertyChangeSupport propChangeFirer;

	private FractalStore fractal;
	
	public FractalDisplayModel(){
		this(-1.0,1.0,-1.0,1.0);
	}
	
	public FractalDisplayModel(double x1_,double x2_,double y1_,double y2_){
		x1=x1_;
		x2=x2_;
		y1=y1_;
		y2=y2_;
	}


    public void addListener(PropertyChangeListener prop) {
        propChangeFirer.addPropertyChangeListener(prop);
        System.out.println("addListener");
    }
    

    public void setX1(double x1_){x1 = x1_;}
    public void setY1(double y1_){y1 = y1_;}
    public void setX2(double x2_){x2 = x2_;}
    public void setY2(double y2_){y2 = y2_;}
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
    	return widthFraction*(double)(width);
    }
    public double realToWindowY(double y){
    	double heightFraction = (y-y1)/(y2-y1);
    	return heightFraction*(double)(height);
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
    
    public void updateModel(){
    	generatePoints();
    }
    public FractalStore getModel(){
    	//check if fractal matches.
    	return fractal;
    }
    
    public void removeChangeListener(Controller c){
    	
    }
    
    //Do something with this.
    public void addChangeListener(Controller c){
    	//listenerList.add(ChangeListener.class, c);
    }
    /**
	 * The function must only return 
	 * @param width
	 * @param height
	 * @return
	 */
	public void generatePoints(){//Truthfully, the bufferedimage aspect should be in the VIEW.
		//x1, x2
		//i1, i2 actual position of 4 corners in complex plane.

		Integer max = 100;

		double stepx = (x2-x1)/resolution;
		double stepy = (y2-y1)/resolution;

		//Creating arraylist of all the points and values.
		ArrayList<Coordinate<Double,Double>> values = new ArrayList<Coordinate<Double,Double>>();
		
		System.out.println("start IMAGE");
		long startTime = System.nanoTime();
		
		

		
		for(double i = x1; i<x2; i+=stepx){
			//if(((i-x1)/stepx) == 0){System.out.println((i-x1)/stepx);}
			for(double j = y1; j<y2;j+=stepy){
				ComplexNumber z0 = new ComplexNumber(i,j);
				double iterations = mand(z0,max);
				Coordinate<Double,Double> n = new Coordinate<Double,Double>(i,j);
				n.setValue(iterations);
				values.add(n);
			}	
		}
		
		fractal = new FractalStore(x1,y1,x2,y2,resolution,values);
		
		long endTime = System.nanoTime();
		double duration = (endTime - startTime)/1000000000.0;
		System.out.println("Fractal generation: "+duration+"seconds elapsed");
	}


	/**
	 * 
	 * @param z0
	 * @param max
	 * @return how many iterations it took to leave z0
	 */
	//Depends on a cache.
	public int mand(ComplexNumber z0,int max){
		ComplexNumber z = z0;
		int iteration = 0;
		double x = z.getRe();
		double y = z.getIm();

		while(Math.pow(2,x)+Math.pow(2,y) < 2*2){
			
			if(iteration>=max){
				return iteration;
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
	public ArrayList<Coordinate<Double,Double>> listCoordinates(ComplexNumber z0,int max){
		ComplexNumber z = z0;
		int iteration = 0;
		double x = z0.getRe();
		double y = z0.getIm();
		ArrayList<Coordinate<Double,Double>> history = new ArrayList<Coordinate<Double,Double>>();
		history.add(new Coordinate<Double,Double>(z.getRe(),z.getIm()));
	
		while(Math.pow(2,x)+Math.pow(2,y) < 2*2){
			if(iteration>=max){
				break;
			}
			
			double xtemp = x*x-y*y+z.getRe();
			y = 2*x*y + z.getIm();
			x = xtemp;
			history.add(new Coordinate<Double,Double>(x,y));
			
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
		generatePoints();
	}
	
	
}

package basicCameraGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel
{
	public BufferedImage image;
	private ArrayList<Coordinate<Double,Double>> history = new ArrayList<Coordinate<Double,Double>>();
	private FractalStore fractal;
	boolean historyVisible = true;
	
	public int width;
	public int height;
	private int resolution;
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
	public void setResolution(int r){
		resolution = r;
	}

    public void setImage(BufferedImage img){
    	image = img;
    }
    
    public BufferedImage getImage(){
    	return image;
    }
    
    public void setHistory(ArrayList<Coordinate<Double,Double>> history_){
    	history = history_;
    }
    
    public void setPoints(FractalStore fractal_){
    	fractal = fractal_;
    }
    
    //passing by reference to edit x and y
    //taking real values 0.23123 =>  230px
    public BufferedImage createImage(){
    	return createImageSpec(width,height,resolution);
    }
    
    public BufferedImage createImageSpec(int width,int height,int resolution){
    	BufferedImage img = new BufferedImage((int)width, (int)height,BufferedImage.TYPE_INT_RGB);
		
    	double x1 = fractal.getX1();
    	double y1 = fractal.getY1();
    	double x2 = fractal.getX2();
    	double y2 = fractal.getY2();
    	
    	Graphics g = img.getGraphics();
    	
    	Color CLOSE = new Color(255, 0,0);//RED
    	Color MEDIUM = new Color(0,255,0);
    	Color FAR = new Color(0, 0, 255);//GOLD
    	
    	for(Coordinate<Double,Double> item : fractal.getPoints()){
    		Double x = item.x;
    		Double y = item.y;
    		int i = (int)((x-x1)/(x2-x1)*(double)(width));//Converting from coordinate in plane to bufferedimage
    		int j = (int)((y-y1)/(y2-y1)*(double)(height));//Converting from coordinate in plane to bufferedimage
    		double iterations = item.getValue();
    		//Color color = doubleColorIterated(iterations,20,CLOSE,FAR);
    		Color color = tripleColorIterated(iterations,40,CLOSE,MEDIUM,FAR);
    		g.setColor(color);
    		//g.drawLine(i, j, i, j);
    		int w = width/resolution;
    		int h = height/resolution;
    		if(w < 1){w=1;}
    		if(h < 1){h=1;}
    		g.fillRect(i, j, w, h);
        }
    	return img;
    }
    @Override
    public void paintComponent(Graphics g)
    {
    	System.out.println("paintComponent drawPanel width: "+width+" height "+height);
    	//fractal.print();
    	BufferedImage img = createImage();
    	image = img;
    	
    	g.drawImage(img,0,0,getWidth(),getHeight(),this);
    	
    	double x1 = fractal.getX1();
    	double y1 = fractal.getY1();
    	double x2 = fractal.getX2();
    	double y2 = fractal.getY2();
        
        /*drawing history of the component*/
        /*how to get different styles of history??*/
        if(history.size()>0){
        	
	        Coordinate<Double,Double> past = history.get(0);
	        System.out.println(past.x+" "+past.y);
	        int pastx = (int)((past.x-x1)/(x2-x1)*(double)(width));//Converting from coordinate in plane to bufferedimage
    		int pasty = (int)((past.y-y1)/(y2-y1)*(double)(height));//Converting from coordinate in plane to bufferedimage
	    	if(historyVisible){
		        for(Coordinate<Double,Double> item : history){//really awkward.
		        	
		        	g.setColor(new Color(0,255,0));
		        	//g.fillOval(item.x.intValue(), item.y.intValue(), 2,2);
		        	int nowx = (int)((item.x-x1)/(x2-x1)*(double)(width));//Converting from coordinate in plane to bufferedimage
		    		int nowy = (int)((item.y-y1)/(y2-y1)*(double)(height));//Converting from coordinate in plane to bufferedimage
		    		
		    		g.drawLine(pastx,pasty,nowx,nowy);
		        	pastx = nowx;
		        	pasty = nowy;
		        }
	    	}
        }
        
        
    }
    
    
    
    public Color doubleColorIterated(double gray, int itrs,Color CLOSE ,Color FAR){

		double scale = (gray%itrs)/itrs;
		
		int red = (int)Math.abs((scale * FAR.getRed()) + ((1 - scale) * CLOSE.getRed()));
		int green = (int)Math.abs((scale * FAR.getGreen()) + ((1 - scale) * CLOSE.getGreen()));
		int blue = (int)Math.abs((scale * FAR.getBlue()) + ((1 - scale) * CLOSE.getBlue()));
		//System.out.println(red+" "+green+ " "+blue);
		
		return new Color(red,green,blue);
		
	}
	
	public Color tripleColorIterated(double gray, int itrs,Color CLOSE, Color MEDIUM, Color FAR){
		//200%40 => 0
		//0 20 39
		double scale = (gray%itrs)/itrs;
		if(scale < .5){
			scale *= 2;
			int red = (int)Math.abs((scale * MEDIUM.getRed()) + ((1 - scale) * CLOSE.getRed()));
			int green = (int)Math.abs((scale * MEDIUM.getGreen()) + ((1 - scale) * CLOSE.getGreen()));
			int blue = (int)Math.abs((scale * MEDIUM.getBlue()) + ((1 - scale) * CLOSE.getBlue()));
			return new Color(red,green,blue);
		}else if(scale >= .5){
			scale *= 2;
			scale -=1;
			
			int red = (int)Math.abs((scale * FAR.getRed()) + ((1 - scale) * MEDIUM.getRed()));
			int green = (int)Math.abs((scale * FAR.getGreen()) + ((1 - scale) * MEDIUM.getGreen()));
			int blue = (int)Math.abs((scale * FAR.getBlue()) + ((1 - scale) * MEDIUM.getBlue()));
			return new Color(red,green,blue);
		}else{
			return new Color(0,0,0);
		}	
	}
}
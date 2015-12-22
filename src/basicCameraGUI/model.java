package basicCameraGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;

import javax.swing.event.SwingPropertyChangeSupport;

public class model {
	
	private ComplexNumber z;
	private SwingPropertyChangeSupport propChangeFirer;
	

	public model() {
        propChangeFirer = new SwingPropertyChangeSupport(this);
    }
    public void addListener(PropertyChangeListener prop) {
        propChangeFirer.addPropertyChangeListener(prop);
    }
    
	/**
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	public BufferedImage getImage(double x, double y, int width, int height,double zoom,double resolution){
		//x1, x2
		//i1, i2 actual position of 4 corners in complex plane.
		BufferedImage img = new BufferedImage(width, height,BufferedImage.TYPE_BYTE_GRAY);
		ComplexNumber center = new ComplexNumber(x,y);
		
		int max = 255;
		/*for(double i = x-width*zoom; i<x+width*zoom; i+=i*zoom*resolution){
			
			for(double j = y-height*zoom; j<y+height*zoom; j+=j*zoom*resolution){
				
				int imgWidth = (int)(i*(1/zoom));
				int imgHeight = (int)(j*(1/zoom));
				
				
				
			}
		}*/
		Graphics g = img.getGraphics();
		g.drawImage(img, 0, 0, null);
		
		for(int i = 0; i<width; i++){
			for(int j = 0;j<height;j++){
				double x0 = x - zoom/2 + zoom*(i/width);
				double y0 = y - zoom/2 + zoom*(j/height);
				ComplexNumber z0 = new ComplexNumber(x0,y0);
				int gray = max - mand(z0,max);
				Color color = new Color(gray, gray, gray);
				g.setColor(color);
				g.drawLine(i,j,i,j);
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
		for(int t = 0; t<max; t++){
			if(Math.pow(1/2,Math.pow(2,z.getRe())+Math.pow(2,z.getIm())) > 2.0) return t;
			z.multiply(z);
			z.add(z0);
		}
		return max;
	}
}

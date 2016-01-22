package basicCameraGUI;

import java.awt.image.BufferedImage;

import javax.swing.event.ChangeListener;

interface Model {
	abstract void setX1(double x1_);
	abstract void setY1(double y1_);

	abstract void setX2(double x2_);
	abstract void setY2(double y2_);
	
	abstract double getX1();
	abstract double getY1();

	abstract double getX2();
	abstract double getY2();
	
	public double realToWindowX(double x);
	public double realToWindowY(double y);
	public double windowToRealX(double x);
	public double windowToRealY(double y);

	abstract void setResolution(double resolution);
	
	abstract void setWidth(int width_);
	abstract void setHeight(int height_);
	
	abstract int getWidth();
	abstract int getHeight();
	
	abstract BufferedImage getImage();
	
	abstract void print();
	

}

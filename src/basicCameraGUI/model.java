package basicCameraGUI;

import java.awt.image.BufferedImage;

interface Model {
	abstract void setX1(double x1_);
	abstract void setY1(double y1_);

	abstract void setX2(double x2_);
	abstract void setY2(double y2_);

	abstract void setResolution(double resolution);
	
	abstract void setWidth(int width_);
	abstract void setHeight(int height_);
	
	abstract int getWidth();
	abstract int getHeight();
	
	abstract BufferedImage getImage();
	
	

}

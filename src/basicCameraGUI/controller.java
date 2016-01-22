package basicCameraGUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller extends JComponent implements ChangeListener,ActionListener,MouseListener,MouseMotionListener{
	private Model MODEL;
	private StandardView VIEW;
	public String getClassUIClassID(){return VIEW.UI_CLASS_ID;}

	private int Previousx = 0;
	private int Previousy = 0;
	
	private int baseRes = 500;

	public Controller(){
        setModel(new FractalDisplayModel());
        setView(new StandardView());
        System.out.println("start");
		VIEW.setImage(MODEL.getImage());
		System.out.println("end");
		VIEW.getDrawPanel().repaint();
		VIEW.getDrawPanel().revalidate();//This resets it all after all elements have been generated.
        
		//VIEW.addActionListener();

    }
	
	
	public void setModel(Model m){
		MODEL = m;
		//MODEL.addChangeListener(this);
	}
	public Model getModel(){
		return MODEL;
	}
	
	
	public void setView(StandardView v){
		VIEW = v;
		//Adding Listeners.
		VIEW.toolsPanel.colorButton.addActionListener(this);
		VIEW.toolsPanel.zoomButton.addActionListener(this);
		VIEW.toolsPanel.resolutionButton.addActionListener(this);
		VIEW.drawPanel.addMouseMotionListener(this);
		VIEW.drawPanel.addMouseListener(this);
	}
	public StandardView getView(){
		return VIEW;
	}
	

	public void resetViewPanel(){
		BufferedImage b = MODEL.getImage();
		VIEW.setImage(b);
		//Reseting View.
		VIEW.getDrawPanel().repaint();
		VIEW.getDrawPanel().revalidate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Not sure how to use design pattern, because i need to send
		//Data to the model, but drawpanel actionlistener is void..
		
		//Pretty sure im not supposed to have a switch statement ever.
		System.out.println("action: "+e.getActionCommand());
		if(e.getActionCommand().equals("submit")){
			
			//Getting information from toolsPanel textboxes.
			double [] coords = VIEW.toolsPanel.getCoordinates();
			MODEL.setX1(coords[0]);
			MODEL.setX2(coords[1]);
			MODEL.setY1(coords[2]);
			MODEL.setY2(coords[3]);
			
			//Setting View.
			resetViewPanel();
			
		}else if(e.getActionCommand().equals("zoom")){
			double newX1 = MODEL.getX1();
			double newX2 = MODEL.getX2();
			double newY1 = MODEL.getY1();
			double newY2 = MODEL.getY2();
			
			double width = (newX2-newX1);
			double height = (newY2-newY1);
			
			double centerx = newX1-(width/2.0);
			double centery = newY1-(height/2.0);
			
			newX1 += width/4;
			newX2 -= width/4;
			newY1 += height/4;
			newY2 -= height/4;//divides area in half.
			
			MODEL.setX1(newX1);
			MODEL.setX2(newX2);
			MODEL.setY1(newY1);
			MODEL.setY2(newY2);
			//Setting view
			resetViewPanel();
			
		}else if(e.getActionCommand().equals("resolution")){
			int baseRes = VIEW.toolsPanel.getResolution();
			pixelize(baseRes);
			System.out.println("resolution: "+VIEW.toolsPanel.getResolution());
			resetViewPanel();
		}
		
	}
	
	//Not sure about this coupling.
	public void pan(int x_mov,int y_mov){//x_mov and y_mov are distances traveled along the size of the window.
		
		double widthDouble = MODEL.getX2()-MODEL.getX1();
    	// if 1 is x2-x1, then panning 100px to the right is 100/500 = 1/5 of widthDouble
    	
    	double heightDouble = MODEL.getY2()-MODEL.getY1();
    	
    	double widthFraction = (double)x_mov/MODEL.getWidth();
    	double heightFraction = (double)y_mov/MODEL.getHeight();
    	MODEL.setX1(widthDouble*(widthFraction)+MODEL.getX1());
    	MODEL.setX2(widthDouble*(widthFraction)+MODEL.getX2());
    	MODEL.setY1(heightDouble*(heightFraction)+MODEL.getY1());
    	MODEL.setY2(heightDouble*(heightFraction)+MODEL.getY2());

	}
	
	//Makes generating the fractal faster.
	public void pixelize(int resolution){
		MODEL.setResolution(resolution);
	}

	@Override 
	public void mouseDragged(MouseEvent e){
		pixelize(50);
		System.out.println("dragged");
		int currentMX = e.getX();
		int currentMY = e.getY();
		
		
		int posX = (e.getX() - Previousx);
		int posY = (e.getY() - Previousy); 
		pan(-posX,-posY);
		Previousx = currentMX;
		Previousy = currentMY;
		VIEW.setImage(MODEL.getImage());
		VIEW.getDrawPanel().repaint();
		VIEW.getDrawPanel().revalidate();
		//Now that we changed(panned) we should probably reset image.
		
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Previousx = e.getX();
		Previousy = e.getY();
		System.out.println("pressed");
		pixelize(50);
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("released.");
		pixelize(baseRes);
		VIEW.setImage(MODEL.getImage());
		VIEW.getDrawPanel().repaint();
		VIEW.getDrawPanel().revalidate();
		MODEL.print();
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
}

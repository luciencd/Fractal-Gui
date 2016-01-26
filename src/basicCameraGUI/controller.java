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
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
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
	
	private int width = 500;
	private int height = 500;
	private int baseRes;

	public Controller(){
		baseRes = 500;
        setModel(new FractalDisplayModel());
        setView(new StandardView());
        getModel().setWidth(width);
        getModel().setHeight(height);
        getModel().setResolution(baseRes);
        
        VIEW.drawPanel.setWidth(width);
        VIEW.drawPanel.setHeight(height);
        VIEW.drawPanel.setResolution(baseRes);
        
        
        updateModel();
		sendViewModel();
		resetViewPanel();
		//This resets it all after all elements have been generated.

    }
	
	
	public void setModel(Model m){
		MODEL = m;
		MODEL.addChangeListener(this);
		
		//MODEL.addChangeListener(this);
	}
	public Model getModel(){
		return MODEL;
	}
	
	
	public void setView(StandardView v){
		VIEW = v;
		//Adding Listeners. BUTTONS
		VIEW.toolsPanel.colorButton.addActionListener(this);
		VIEW.toolsPanel.zoomButton.addActionListener(this);
		VIEW.toolsPanel.resolutionButton.addActionListener(this);
		VIEW.toolsPanel.saveButton.addActionListener(this);
		//VISUAL AREA MOUSE LISTENER
		VIEW.drawPanel.addMouseMotionListener(this);
		VIEW.drawPanel.addMouseListener(this);
		
		
		
	}
	public StandardView getView(){
		return VIEW;
	}
	
	public void updateModel(){
		
		MODEL.updateModel();
		System.out.println("updated model");
	}
	//Need to make sure this gets called only when the model actually changed.
	public void sendViewModel(){
		FractalStore m = MODEL.getModel();
		System.out.print("new model points here:");
		m.print();
		VIEW.getDrawPanel().setPoints(m);
		
		VIEW.repaint();
		VIEW.revalidate();
		System.out.println("sent view model");
	}
	
	
	public void resetViewPanel(){
		VIEW.getDrawPanel().repaint();
		VIEW.getDrawPanel().revalidate();
		System.out.println("reset view panel");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Not sure how to use design pattern, because i need to send
		//Data to the model, but drawpanel actionlistener is void..
		
		//Pretty sure im not supposed to have a switch statement ever.
		//System.out.println("action: "+e.getActionCommand());
		if(e.getActionCommand().equals("submit")){
			
			//Getting information from toolsPanel textboxes.
			double [] coords = VIEW.toolsPanel.getCoordinates();
			MODEL.setX1(coords[0]);
			MODEL.setX2(coords[1]);
			MODEL.setY1(coords[2]);
			MODEL.setY2(coords[3]);
			
			//Setting View.
			
			
		}else if(e.getActionCommand().equals("zoom")){
			double newX1 = MODEL.getX1();
			double newX2 = MODEL.getX2();
			double newY1 = MODEL.getY1();
			double newY2 = MODEL.getY2();
			
			double width = (newX2-newX1);
			double height = (newY2-newY1);
			
			double centerx = newX1-(width/2.0);
			double centery = newY1-(height/2.0);
			
			newX1 += width/3;
			newX2 -= width/3;
			newY1 += height/3;
			newY2 -= height/3;//divides area in half.
			
			MODEL.setX1(newX1);
			MODEL.setX2(newX2);
			MODEL.setY1(newY1);
			MODEL.setY2(newY2);
			

			//Setting view
			
		}else if(e.getActionCommand().equals("resolution")){
			baseRes = VIEW.toolsPanel.getResolution();
			System.out.println("resolution "+baseRes);
			pixelize(baseRes);
			//System.out.println("resolution: "+VIEW.toolsPanel.getResolution());

			System.out.println("resoluted");

		}else if(e.getActionCommand().equals("save")){
			int prevRes = baseRes;
			System.out.println(VIEW.drawPanel.image.getHeight());
			int saveRes = VIEW.toolsPanel.getResolution();
			
			pixelize(saveRes);
			updateModel();
			sendViewModel();
			save(saveRes);
			
			pixelize(prevRes);
			System.out.println(VIEW.drawPanel.image.getHeight());

		}
		System.out.println(VIEW.drawPanel.image.getHeight());
		updateModel();
		sendViewModel();
		resetViewPanel();
		System.out.println(VIEW.drawPanel.image.getHeight());
	}
	
	//Not sure about this coupling.
	public void pan(int x_mov,int y_mov){//x_mov and y_mov are distances traveled along the size of the window.
		
		double widthDouble = MODEL.getX2()-MODEL.getX1();
    	// if 1 is x2-x1, then panning 100px to the right is 100/500 = 1/5 of widthDouble
    	
    	double heightDouble = MODEL.getY2()-MODEL.getY1();
    	
    	double widthFraction = (double)x_mov/VIEW.getWidth();
    	double heightFraction = (double)y_mov/VIEW.getHeight();
    	
    	System.out.println(widthDouble+" "+ heightDouble+ " -> "+widthFraction+ " "+heightFraction);
    	MODEL.print();
    	MODEL.setX1(widthDouble*(widthFraction)+MODEL.getX1());
    	MODEL.setX2(widthDouble*(widthFraction)+MODEL.getX2());
    	MODEL.setY1(heightDouble*(heightFraction)+MODEL.getY1());
    	MODEL.setY2(heightDouble*(heightFraction)+MODEL.getY2());
    	MODEL.print();
	}
	
	//Makes generating the fractal faster.
	public void defaultPixels(){
		pixelize(baseRes);
	}
	public void pixelize(int resolution){
		MODEL.setResolution(resolution);
		VIEW.getDrawPanel().setResolution(resolution);
	}

	@Override 
	public void mouseDragged(MouseEvent e){
		pixelize(50);
		//System.out.println("dragged");
		int currentMX = e.getX();
		int currentMY = e.getY();
		
		
		int posX = (e.getX() - Previousx);
		int posY = (e.getY() - Previousy); 
		pan(-posX,-posY);
		Previousx = currentMX;
		Previousy = currentMY;
		
		updateModel();
		sendViewModel();
		resetViewPanel();
		//Now that we changed(panned) we should probably reset image.
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		pixelize(50);
		Previousx = e.getX();
		Previousy = e.getY();
		//System.out.println("pressed");
		
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println("released.");
		pixelize(baseRes);
		
		updateModel();
		sendViewModel();
		resetViewPanel();

	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		VIEW.getDrawPanel().historyVisible = false;
		
		resetViewPanel();
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		//System.out.println("mouse moved");
		//Want to show white arrow showing movement of particles inside space.
		//System.out.println("MOUSE POS: ("+e.getX()+","+e.getY()+")");
		double real_x = MODEL.windowToRealX(e.getX());
		double real_y = MODEL.windowToRealY(e.getY());
		ComplexNumber z0 = new ComplexNumber(real_x,real_y);
		
		/*ArrayList<Coordinate<Double>> history = new ArrayList<Coordinate<Double>>();
		history.add(new Coordinate<Double>((double)e.getX(),(double)e.getY()));
		
		MODEL.print();*/
		//System.out.println("ACTUAL POS: ("+(real_x)+","+(real_y)+")");
		ArrayList<Coordinate<Double,Double>> history = MODEL.listCoordinates(z0,30);
		
		
		//Updating the view here. only the 
		VIEW.getDrawPanel().historyVisible = true;
		VIEW.getDrawPanel().setHistory(history);
		
		resetViewPanel();
		
		//DrawPanel.historyVisible = true;
		
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void save(int resolution){
		
		
		BufferedImage bi = VIEW.getDrawPanel().createImageSpec(resolution,resolution,resolution);
		//Add location to save name too. or inside the actual data of the file.
		
		String saveName = Double.toString(new Random().nextDouble());
		
	    File outputfile = new File(System.getProperty("user.home")+File.separator+"Documents"+"/fractals/"+saveName+".png");
	    try{
	    	System.out.println("saved file at:"+ saveName+".png");
	    	ImageIO.write(bi, "png", outputfile);
	    }catch(IOException e){
	    	
	    }
	    
	}
}

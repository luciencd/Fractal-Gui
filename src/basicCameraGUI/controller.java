package basicCameraGUI;

import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;

public class Controller extends JComponent  {
	private Model MODEL;
	private View VIEW;
	

	
	public Controller(View view_, Model model_){
        this.VIEW = view_;
        this.MODEL = model_;
        
        //register the controller as the listener of the model
        

    }


    public void paintComponent(Graphics g){
    	System.out.println("p: "+getWidth()+" "+getHeight());
    	g.drawImage(MODEL.getImage(),0,0,getWidth(),getHeight(),this);
    }

}

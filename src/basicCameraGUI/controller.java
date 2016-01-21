package basicCameraGUI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller extends JComponent implements ChangeListener{
	private Model MODEL;
	private StandardView VIEW;
	public String getClassUIClassID(){return VIEW.UI_CLASS_ID;}

	
	public Controller(){
        setModel(new FractalDisplayModel());
        setView(new StandardView());
        System.out.println("start");
		VIEW.setImage(MODEL.getImage());
		System.out.println("end");
		VIEW.getDrawPanel().repaint();
		VIEW.getDrawPanel().revalidate();//This resets it all after all elements have been generated.
        

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
		//MODEL.addChangeListener(this);
	}
	public StandardView getView(){
		return VIEW;
	}
	
	
	//public void setUI(View ui) {super.setUI(ui);}
	
	/*public void updateUI(){
		setUI((View) UIManager.getUI(this));
		invalidate();
	}*/
	
	public void setImage(){
		
	}
	/*public void mousePressed(MouseEvent e)
    {
		System.out.println("pressed");
		VIEW.setImage(MODEL.getImage());
		VIEW.getDrawPanel().repaint();
    }*/
	@Override
    public void paintComponent(Graphics g){
		
    	System.out.println("paint");
    	MODEL.print();
    	VIEW.getDrawPanel().repaint();
    	//VIEW.setImage(MODEL.getImage());
    	//VIEW.print(g);
       	//g.drawImage(MODEL.getImage(),0,0,getWidth(),getHeight(),this);
    }


	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		System.out.println("StateChanged");
		
		
		
	}
}

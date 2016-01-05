package basicCameraGUI;

import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.event.ChangeListener;

public class Controller extends JComponent implements ChangeListener{
	private Model MODEL;
	private View VIEW;
	public String getClassUIClassID(){return VIEW.UI_CLASS_ID;}

	
	public Controller(View view_, Model model_){
        this.VIEW = view_;
        this.MODEL = model_;
    }
	
	public void setModel(Model m){
		/*
		if(MODEL != null){
			MODEL.removeChangeListener(this);
		}*/
		MODEL = m;
		//MODEL.addChangeListener(this);
	}
	public Model getModel(){
		return MODEL;
	}
	
	public void setUI(View ui) {super.setUI(ui);}
	
	public void updateUI(){
		setUI((StandardView) View.getUI(this));
		invalidate();
	}

    public void paintComponent(Graphics g){
       	g.drawImage(MODEL.getImage(),0,0,getWidth(),getHeight(),this);
    }
}

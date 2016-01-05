package basicCameraGUI;

import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller extends JComponent implements ChangeListener{
	private Model MODEL;
	private View VIEW;
	public String getClassUIClassID(){return VIEW.UI_CLASS_ID;}

	
	public Controller(){
        setModel(new FractalDisplayModel());
        updateUI();
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
		setUI((View) UIManager.getUI(this));
		invalidate();
	}

    public void paintComponent(Graphics g){
       	g.drawImage(MODEL.getImage(),0,0,getWidth(),getHeight(),this);
    }


	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
		
		
	}
}

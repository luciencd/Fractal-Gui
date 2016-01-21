package basicCameraGUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller extends JComponent implements ChangeListener,ActionListener{
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
	}
	public StandardView getView(){
		return VIEW;
	}
	




	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		System.out.println("StateChanged");
	}
	
	
	public ActionListener getButtonListener () {
        return new ActionListener() {
            @Override public void actionPerformed (ActionEvent e) {
                //MODEL.updateSomething();
            }

        };
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("button: "+e);
		
	}
}

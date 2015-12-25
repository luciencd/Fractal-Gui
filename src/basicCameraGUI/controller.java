package basicCameraGUI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Controller implements PropertyChangeListener  {
	private FractalDisplayModel MODEL;
	private View VIEW;
	

	
	public Controller(View view_, FractalDisplayModel model_){
        this.VIEW = view_;
        this.MODEL = model_;

        //register the controller as the listener of the model
        this.MODEL.addListener(this);
        //this.MODEL.getImage();

        //setUpViewEvents();
    }
	public void setViewPicture(){
		
	}
	
	public void setX(){
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

}

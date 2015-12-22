package basicCameraGUI;

import java.beans.PropertyChangeListener;

public class controller implements PropertyChangeListener, Controller  {
	private model MODEL;
	private view VIEW;

	
	public controller(view view_, model model_){
        this.VIEW = view_;
        this.MODEL = model_;

        //register the controller as the listener of the model
        this.MODEL.addListener(this);

        setUpViewEvents();
    }

}

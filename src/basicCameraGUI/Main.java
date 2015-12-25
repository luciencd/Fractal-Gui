package basicCameraGUI;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * 
 * 
 * @author luciencd
 * Whole point to start everything up from model view controller.
 */
public class Main{
    public static void main(String[] args){
        View view = new StandardView();
        FractalDisplayModel model = new FractalDisplayModel();


        //create jframe
        JFrame frame = new JFrame("Fractal");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view.controlPanel());
        frame.add(view.visualField());
        JTextField p = new JTextField("ass");
        
        //frame.add(p);

        //make sure the view and model is fully initialized before letting the controller control them.
        Controller controller = new Controller(view, model);


        frame.setVisible(true);
        frame.setSize(new Dimension(300,300));
        

    }
}
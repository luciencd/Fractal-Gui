package basicCameraGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * 
 * 
 * @author luciencd
 * Whole point to start everything up from model view controller.
 */
public class Main{
    public static void main(String[] args){
    	
        //Model model = new FractalDisplayModel();
        //View view = new StandardView();
    	System.out.println("controller b4");
        Controller controller = new Controller();
        System.out.println("controller aft4");
        //controller.setModel(model);
        //controller.setView(view);
        //UIManager.put(StandardView.UI_CLASS_ID, "StandardViewUI");
        
        //JFrame frame = new JFrame();
        
        
        /*frame.add(controller);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

 
        


        frame.setVisible(true);
        frame.setSize(new Dimension(500,500));*/



    }
}
package basicCameraGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * 
 * @author luciencd
 * Whole point to start everything up from model view controller.
 */
public class Main{
    public static void main(String[] args){
    	
        JComponent model = new FractalDisplayModel();
        System.out.println("start");
        JFrame frame = new JFrame();
        

        frame.add(model);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //frame.add(panel);
        
 
        


        frame.setVisible(true);
        frame.setSize(new Dimension(500,500));

        System.out.println("final");
        

    }
}
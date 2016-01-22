package basicCameraGUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class toolsPanel extends JPanel
{

    JButton colorButton;
    JButton zoomButton;
    JButton resolutionButton;
    
    JTextField x1;
    JTextField x2;
    JTextField y1;
    JTextField y2;
    JTextField resolution;
    
    private Dimension preferred = new Dimension(100,100);
    private Dimension maximum = new Dimension(130,130);
    private Dimension minimum = new Dimension(90,90);
    public toolsPanel()
    {
    	
    }
    
    public double[] getCoordinates(){
    	double dx1 = new Double(x1.getText());
    	double dx2 = new Double(x2.getText());
    	double dy1 = new Double(y1.getText());
    	double dy2 = new Double(y2.getText());
    	double [] coords = new double[]{dx1,dx2,dy1,dy2};
    	return coords;
    	
    }
    public int getResolution(){
    	
    	return new Integer(resolution.getText());
    }

    public void setTools()
    {
        int numberOfColumns = 2; // = 0 many columns as necessary
        int numberOfRows = 4; // 0 = many rows as necessary
        this.setLayout(new GridLayout(numberOfRows, numberOfColumns));
        
        x1 = new JTextField();
        x2 = new JTextField();
        y1 = new JTextField();
        y2 = new JTextField();
        resolution = new JTextField();
        
        colorButton = new JButton("submit");
        zoomButton = new JButton("zoom");
        resolutionButton = new JButton("resolution");
        this.add(x1);
        this.add(x2);
        this.add(y1);
        this.add(y2);
        
        this.add(colorButton);
        this.add(zoomButton);
        this.add(resolution);
        this.add(resolutionButton);
    }
    
    /*@Override
    public Dimension getPreferredSize(){
    	return preferred;
    }
    @Override
    public Dimension getMaximumSize(){
    	return maximum;
    }
    @Override
    public Dimension getMinimumSize(){
    	return minimum;
    }*/




    
}
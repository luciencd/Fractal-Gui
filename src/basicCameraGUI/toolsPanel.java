package basicCameraGUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class toolsPanel extends JPanel
{

    JButton colorButton;
    
    JTextField x1;
    JTextField x2;
    JTextField y1;
    JTextField y2;
    
    private Dimension preferred = new Dimension(100,100);
    private Dimension maximum = new Dimension(130,130);
    private Dimension minimum = new Dimension(90,90);
    public toolsPanel()
    {
    	
    }

    public void setTools()
    {
        int numberOfColumns = 4; // = 0 many columns as necessary
        int numberOfRows = 5; // 0 = many rows as necessary
        this.setLayout(new GridLayout(numberOfRows, numberOfColumns));
        
        x1 = new JTextField();
        x2 = new JTextField();
        y1 = new JTextField();
        y2 = new JTextField();
        colorButton = new JButton("submit");
        this.add(x1);
        this.add(x2);
        this.add(y1);
        this.add(y2);
        this.add(colorButton);
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
package basicCameraGUI;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class toolsPanel extends JPanel
{

    private JButton colorButton, rectangleButton, circleButton, pencilButton, lineButton, rubberButton,
            fillClosedShapeButton;
    private JLabel tipText;
    
    private JTextField x1;
    private JTextField x2;
    private JTextField y1;
    private JTextField y2;
    
    public toolsPanel()
    {
    	
    }

    public void setTools()
    {
        int numberOfColumns = 1; // = 0 many columns as necessary
        int numberOfRows = 5; // 0 = many rows as necessary
        this.setLayout(new GridLayout(numberOfRows, numberOfColumns));
        
        x1 = new JTextField();
        x2 = new JTextField();
        y1 = new JTextField();
        y2 = new JTextField();
        colorButton = new JButton();
        this.add(x1);
        this.add(x2);
        this.add(y1);
        this.add(y2);
        this.add(colorButton);
    }





    
}
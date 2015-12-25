package basicCameraGUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StandardView extends JFrame implements View{

	public StandardView() {
		// TODO Auto-generated constructor stub
	}
	
	public JLabel visualField(){
		JLabel vField = new JLabel();
		return vField;
	}
	
	public JPanel controlPanel(){
		JPanel cPanel = new JPanel();
		cPanel.setLayout(new BorderLayout());
		
		
		JTextField xField = new JTextField("x");
		JTextField yField = new JTextField("y");
		JTextField zoomField = new JTextField("zoom");
		//zoomField.setSize(new Dimension(20,20));
		
		cPanel.add(xField,BorderLayout.PAGE_START);
		cPanel.add(yField,BorderLayout.PAGE_START);
		cPanel.add(zoomField,BorderLayout.PAGE_START);
		
		JButton render = new JButton("Render");
		
		render.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		cPanel.add(render);
		cPanel.setSize(new Dimension(100,100));
		
		return cPanel;
		
	}
	
	public void close(){
		
	}
	

}

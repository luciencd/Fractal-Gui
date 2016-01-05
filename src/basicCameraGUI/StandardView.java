package basicCameraGUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.ComponentUI;

public class StandardView extends View implements MouseListener{
	public static final String UI_CLASS_ID = "StandardView";
	
	public static ComponentUI createUI(JComponent c) {
		return new StandardView();
	}
	
	public void installUI(JComponent c){
		((Controller) c).addMouseListener(this);
	}
	
	public void uninstallUI(JComponent c){
		((Controller) c).removeMouseListener(this);
	}
	
	public void paint(Graphics g,JComponent c){
		//do painting for the component here.
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

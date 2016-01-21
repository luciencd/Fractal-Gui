package basicCameraGUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

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

public class StandardView extends JFrame implements MouseListener{
	public static final String UI_CLASS_ID = "StandardView";
	
    private DrawPanel drawPanel;
    private toolsPanel toolsPanel;
    
    public StandardView()
    {
        prepareFrame();

        initializeToolsPanelComponents();
        drawPanel = new DrawPanel();

        JPanel mainLayoutforApplication = new JPanel(new BorderLayout());

        mainLayoutforApplication.add(toolsPanel, BorderLayout.SOUTH);
        mainLayoutforApplication.add(drawPanel, BorderLayout.CENTER);
        this.add(mainLayoutforApplication);
        
    } 
    
    public void prepareFrame()
    {
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // setResizable(false);
        setVisible(true);
    }
    public void initializeToolsPanelComponents()
    {
    	System.out.println("initialize tools panelComponents.");
        toolsPanel = new toolsPanel();
        toolsPanel.setTools();
    }
    public DrawPanel getDrawPanel(){
    	return drawPanel;
    }
    
    public void paintComponent(Graphics g){
    	System.out.println("StandardView paintComponents");
    	toolsPanel.repaint();
    	drawPanel.repaint();
    }
    public void repaint(){
    	System.out.println("StandardView repaint");
    	toolsPanel.repaint();
    	drawPanel.repaint();
    }
    /*
	public static ComponentUI createUI(JComponent c) {
		return new StandardView();
	}
	
	public void installUI(JComponent c){
		((Controller) c).addMouseListener(this);
	}
	
	public void uninstallUI(JComponent c){
		((Controller) c).removeMouseListener(this);
	}*/
	public void setImage(BufferedImage b){
		System.out.println("setImage standardView");
		drawPanel.setImage(b);
	}
	/*public void paint(Graphics g,JComponent c){
		//do painting for the component here.
		System.out.println("StandardViewPaint");
		c.paint(g);
	}*/

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse clicked at position:"+e.getX()+" "+e.getY());
		
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

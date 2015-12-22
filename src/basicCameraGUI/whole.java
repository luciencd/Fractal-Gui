package basicCameraGUI;


import hw7.building;
import hw9.VisualPath;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class whole extends JPanel
	implements MouseListener, MouseMotionListener,MouseWheelListener{

	public whole() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		
		//Visual Area to display whatever things we need displaying.
		
		visualArea = new visualPath();
		add(visualArea);
		
		panel = new ControlPanel();
		add(panel,BorderLayout.WEST);
	}
	
	public static void main(String[] args) {
        try {

            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
	
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
    	JFrame frame = new JFrame("Fractal Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Create and set up the content pane.
        JPanel newContentPane = new whole();
        //newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
         
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
	/**
	 * Prints events to the console for debugging.
	 * @param eventDescription
	 * @param e
	 */
	void eventOutput(String eventDescription, MouseEvent e) {
		System.out.println(eventDescription + " detected on "
				+ e.getComponent().getClass().getName()
				+ "." + "\n");
	}
	
	/**
	 * Aides in dragging the map of campus across the window.
	 * @param MouseEvent e
	 * @modifies Previousx, Previousy, visualArea.  
	 */
	public void mousePressed(MouseEvent e) {


	}
	
	//Undefined listeners, we dont need em.
	public void mouseReleased(MouseEvent e) {

	}
	
	public void mouseEntered(MouseEvent e) {
		//eventOutput("Mouse entered", e);
	}
	
	public void mouseExited(MouseEvent e) {
		//eventOutput("Mouse exited", e);
	}
	
	/**
	 * Aides in dragging the map of campus across the window.
	 * @param MouseEvent e
	 * @modifies Previousx, Previousy, visualArea.  
	 */
	public void mouseClicked(MouseEvent e) {

	}

	
	//Not used.
	public void mouseMoved(MouseEvent e) {
	    //saySomething("Mouse moved", e);
    }
	/**
	 * Makes modification to Visual Area corresponding to moving the camera after dragging the screen.
	 * 
	 * @param MouseEvent e
	 * @modifies Previousx, Previousy, visualArea.  
	 */
    public void mouseDragged(MouseEvent e) {


	   
    }
    
    /**
     * Checks for mousewheel to zoom in and out.
     * @param MouseWheelEvent e
     * @modifies visualArea.
     */
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
    /**
     * adds messages to textArea, useful when debugging.
     * @param eventDescription
     * @param e
     * @modifies textArea
     */
    void saySomething(String eventDescription, MouseEvent e) {
        textArea.append(eventDescription 
                        + " (" + e.getX() + "," + e.getY() + ")"
                        + " detected on "
                        + e.getComponent().getClass().getName()
                        + "\n");
    }

}

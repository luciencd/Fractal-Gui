package basicCameraGUI;

import javax.swing.*;

public interface View {
	public abstract JLabel visualField();
	public abstract JPanel controlPanel();
	public abstract void close();

}

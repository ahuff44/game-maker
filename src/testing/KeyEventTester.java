package testing;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;


public class KeyEventTester extends JFrame{

	public KeyEventTester(){
		KeyEventTesterKeyListener k = new KeyEventTesterKeyListener();
		addKeyListener(k);
		
		setScreenSettings();
	}
	
	/**
	 * Copy and paste this to make a basic JPanel window
	 */
	private void setScreenSettings() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screen.width - 640) / 2,
				  (screen.height - 480) / 2,
				  640 + 4 + 4,
				  480 + 4 + 4 + 20);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}

	public static void main(String[] args) throws InterruptedException {
		(new KeyEventTester()).setVisible(true);
	}

}

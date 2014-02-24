package gameMaker;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class IntuitiveKeyListener implements KeyListener {

	private ArrayList<Integer> currentlyPressed;
	private HasIntuitiveKeyListener owner;
	
	public IntuitiveKeyListener(HasIntuitiveKeyListener owner){
		this.owner = owner;
		currentlyPressed = new ArrayList<Integer>();
	}	
	
	public void keyPressed(KeyEvent arg0) {
		Integer code = arg0.getKeyCode();
		if (!currentlyPressed.contains(code)){
			currentlyPressed.add(code);
			owner.keyPressed(arg0);//TODO think of a better name for this, and this class
		}
		owner.keyDown(arg0);//TODO think of a better name for this, and this class
	}

	public void keyReleased(KeyEvent arg0) {
		Integer code = arg0.getKeyCode();
		currentlyPressed.remove(code);
		owner.keyReleased(arg0);//TODO think of a better name for this, and this class
	}

	public void keyTyped(KeyEvent arg0) { }
	
}

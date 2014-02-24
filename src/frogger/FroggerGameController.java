package frogger;

import java.awt.event.KeyEvent;

import gameMaker.*;
import frogger.rooms.*;


public class FroggerGameController extends GameController {

	public FroggerGameController(String title) {
		super(title);
	}

	@Override
	public String getImageFileLocation() {
		return "src/frogger/images/";
	}
	
	@Override
	public void initialize() {
		Room r = new Room1();
		r.load();
		setRoom(r);
		
		System.out.println("FroggerGameController.initialize() finished\n");
	}
	
	public void keyPressed(KeyEvent arg0){
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
		else
			super.keyPressed(arg0);
	}
	
	public static void main(String[] args) {
		setMainGame(new FroggerGameController("Frogger"));
	}
}


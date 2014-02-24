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
	public void initializeGame() {
		Room r = new Room1();
		r.load();
		setRoom(r);
		
		System.out.println("FroggerGameController.initialize() finished\n");
	}
	
	
	public static void main(String[] args) {
		setMainGame(new FroggerGameController("Frogger"));
	}
}


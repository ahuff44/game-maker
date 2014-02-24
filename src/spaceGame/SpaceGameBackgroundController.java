package spaceGame;

import java.awt.Point;

import gameMaker.*;
import spaceGame.rooms.*;


public class SpaceGameBackgroundController extends GameController{

	public SpaceGameBackgroundController(String title) {
		super(title);
	}

	@Override
	public String getImageFileLocation() {
		return "src/spaceGame/images/";
	}
	
	@Override
	public void initialize() {				
		Room r = new Room1();
		r.load();
		setRoom(r);
		
		System.out.println("SpaceGameBackgroundController.initialize() finished");
		System.out.println();
	}
	 
	public static void main(String[] args) {
		setMainGame(new SpaceGameBackgroundController("Space Game"));
	}

}

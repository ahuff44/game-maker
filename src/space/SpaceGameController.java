package space;

import gameMaker.*;
import space.rooms.*;


public class SpaceGameController extends GameController{

	public SpaceGameController(String title) {
		super(title);
	}

	@Override
	public String getImageFileLocation() {
		return "src/space/images/";
	}
	
	@Override
	public void initializeGame() {
		Room r = new Room1();
		r.load();
		setRoom(r);
		
		System.out.println("SpaceGameController.initialize() finished");
		System.out.println();
	}
	 
	public static void main(String[] args) {
		setMainGame(new SpaceGameController("Space"));
	}

}

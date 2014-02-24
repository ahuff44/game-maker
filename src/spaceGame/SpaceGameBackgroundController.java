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
		{
		//TODO this block should be in GameController or ObjectController, and it should create collision groups automatically	
			//Enemy.initializeCollisionList();  TODO make it work
			//this is not what it should be, but it work:s
				//ObjectController.addCollisionGroup(new CollisionGroup(Laser.class, Ship.class, true));
				//ObjectController.addCollisionGroup(new CollisionGroup(Enemy.class, false));
				//ObjectController.addCollisionGroup(new CollisionGroup(Enemy.class, Explosion.class, false));

		}
				
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

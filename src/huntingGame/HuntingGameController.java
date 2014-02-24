package huntingGame;

import java.awt.Point;

import javax.swing.JFrame;


import gameMaker.*;
import huntingGame.rooms.*;


public class HuntingGameController extends GameController {

	public HuntingGameController(String title) {
		super(title);
	}
	
	@Override
	public String getImageFileLocation() {
		return "src/huntingGame/images/";
	}

	@Override
	public void initialize() {
		//initialize collision groups
		{
			ObjectController.initialize();

			ObjectController.addCollisionGroup(new CollisionGroup(Hunter.class, Bird.class, false));
			ObjectController.addCollisionGroup(new CollisionGroup(Hunter.class, Tiger.class, false));
			ObjectController.addCollisionGroup(new CollisionGroup(Bird.class, Tiger.class, false));
			ObjectController.addCollisionGroup(new CollisionGroup(Bird.class, false));
			ObjectController.addCollisionGroup(new CollisionGroup(Tiger.class, false));

		}
		
		Room r = new Room1();
		r.load();
		setRoom(r);
		
		System.out.println("HuntingGameController.initialize() finished");
		System.out.println();
	}
	 
	public static void main(String[] args) {
		setMainGame(new HuntingGameController("Hunting"));
	}

}

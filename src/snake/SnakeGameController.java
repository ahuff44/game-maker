package snake;

import java.awt.event.KeyEvent;

import gameMaker.*;
import snake.rooms.*;


public class SnakeGameController extends GameController {

	public SnakeGameController(String title) {
		super(title);
	}

	@Override
	public String getImageFileLocation() {
		return "src/snake/images/";
	}
	
	@Override
	public void initialize() {
		Room r = new Room1();
		r.load();
		setRoom(r);
		
		System.out.println("SnakeGameController.initialize() finished\n");
	}
	
	@Override
	public void keyPressed(KeyEvent arg0){
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
		else
			super.keyPressed(arg0);
	}
	
	public static void main(String[] args) {
		setMainGame(new SnakeGameController("Snake"));
	}
}


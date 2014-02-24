package huntingGame;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import gameMaker.*;

public class Bird extends GameObject {

	private static final Image birdImg = GraphicsController.getImage("bird.png");

	public Bird(Point position) {
		super(true, position, birdImg);
		setSpeed(6);
		setDirection(Math.random() * 360);
	}
	
	
	
	//GameObject:
	
	
	
	@Override
	public void intersectBoundaryEvent() {
		gotoPreviousPosition();
		changeDirection(90);
	}
	
	@Override
	public void outsideRoomEvent() {
		destroy();
	}
	
	public Point spriteCenter(){
		return new Point(16, 16);
	}
	
	@Override
	public void collisionEvent(GameObject other) {
		if (other instanceof Hunter || other instanceof Bird){
			gotoPreviousPosition();
			changeDirection(90);
		}
	}

	@Override
	public void createEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroyEvent() {
		BirdController.changeScore(10);
	}

}

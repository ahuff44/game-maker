package safari;

import java.awt.Image;
import java.awt.Point;

import gameMaker.*;
import gameMaker.objects.GameObject;
import gameMaker.visuals.GraphicsController;

public class Bird extends GameObject {

	private static final Image birdImg = GraphicsController.getImage("bird.png");

	public Bird(Point position) {
		super(true, position, birdImg);
		getSprite().setCenter(new Point(16, 16));
		getMotion().setSpeed(6);
		getMotion().setDirection(Math.random() * 360);
	}
	
	
	
	//GameObject:
	
	
	
	@Override
	public void intersectBoundaryEvent() {
		getMotion().gotoPreviousPosition();
		getMotion().changeDirection(90);
	}
	
	@Override
	public void outsideRoomEvent() {
		destroy();
	}
		
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {Bird.class, Hunter.class};
		return (Class<? extends GameObject>[]) list;
	}

	@Override
	public void collisionEvent(GameObject other) {
		if (other instanceof Hunter || other instanceof Bird){
			getMotion().gotoPreviousPosition();
			getMotion().changeDirection(90);
		}
	}

	@Override
	public void createEvent() {}

	@Override
	public void destroyEvent() {
		EnemyController.addToScore(10);
	}

}

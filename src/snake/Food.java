package snake;

import java.awt.*;

import gameMaker.*;


public class Food extends GameObject {
	
	//fields
	
	private static final Image img_1 = GraphicsController.getImage("food\\1.png");
	private static final Image img_2 = GraphicsController.getImage("food\\2.png");
	private static final Image img_3 = GraphicsController.getImage("food\\3.png");
	private static final Image img_4 = GraphicsController.getImage("food\\4.png");

	//constructors
	
	

	public Food(Point p){
		super(true, p, new Animation(new Image[] {img_1, img_2, img_3, img_4, img_3, img_2}));
	}
	
		
	//Overridden methods

	
	public static Class<? extends GameObject>[] getCollisionList(){
		Class<?>[] list = {};
		return (Class<? extends GameObject>[]) list;
	}
	
	@Override
	public void collisionEvent(GameObject other) {	}	
	
	@Override
	public void intersectBoundaryEvent() {	}

	@Override
	public void outsideRoomEvent() { }
	
	@Override
	public void createEvent() {}
	
	@Override
	public void destroyEvent() {}


}

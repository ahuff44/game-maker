package safari.rooms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;

import gameMaker.*;
import gameMaker.visuals.GraphicsController;
import safari.*;

public class Room1 implements Room {

	private static final String SPRITE_ADDRESS = "grass.png";
	private final BackgroundType bType = BackgroundType.TILED;
	private Image backgroundImage;
	
	public Room1(){
		backgroundImage = GraphicsController.getImage(SPRITE_ADDRESS);
	}
	
	@Override
	public Color backgroundColor() {
		return null;
	}

	@Override
	public Image backgroundImage() {
		return backgroundImage;
	}

	@Override
	public void close() {
		System.out.println(this + " is closing.");
	}
	
	@Override
	public void load() {
		new EnemyController();
		new Hunter(new Point(320, 240));
	}

	@Override
	public Dimension getSize(){
		return new Dimension(640, 480);
	}

	@Override
	public BackgroundType backgroundType(){
		return bType;
	}
	
	@Override
	public Dimension getGrid() {
		return new Dimension(16, 16);
	}

}

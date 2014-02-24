package snake.rooms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;

import gameMaker.*;
import snake.*;

public class Room1 implements Room {

	private static final BackgroundType bType = BackgroundType.TILED;
	private static final Image backgroundImage = GraphicsController.getImage("grass.png");
	
	@Override
	public Color backgroundColor() {
		return Color.MAGENTA;
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
		Body tail = new Body(new Point(320 - 32, 240));
		new Snake(new Point(320, 240), tail);
	}

	@Override
	public Dimension getSize() {
		return new Dimension(640, 480);
	}

	@Override
	public Dimension getGrid() {
		return new Dimension(16, 16);
	}

	@Override
	public BackgroundType backgroundType() {
		return bType;
	}

}

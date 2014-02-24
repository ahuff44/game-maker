package gameMaker;

import java.awt.Image;
import java.awt.Point;

public class Animation {

	
	//fields
	
	//	TODO clearly separate this and Sprite
	private Image[] images;
	private int width = 0;
	private int height = 0;
	private int index;

	
	//constructors
	
	
	public Animation(Image img){
		this(new Image[] {img});
	}

	public Animation(Image[] imgs){
		images = imgs;
		index = 0;
	}
	
	
	//static methods
	
	
	public static Animation getEmptyAnimation(){
		return new Animation(new Image[]{});
	}
	
	
	//instance methods
	
	
	public int getLength(){
		return images.length;
	}
	
	public int getIndex(){
		return index;
	}
	
	public Image getImage(int ind){
		return images[ind];
	}
	
	public int incrementIndex(){
		if (getLength() != 0){
			index = (index + 1) % getLength();
		}
		return index;
	}
	
	public Image getImageAndIncrement(){
		int i = getIndex();
		incrementIndex();
		return getImage(i);
	}

	public int getWidth(){
		if (width == 0){//if it hasn't been calculated yet, calculate the maximum width of the images in this animation
			for (Image img : images){
				int w = img.
						getWidth
						(null);
				if (w > width)
					width = w;
			}
		}
		return width;
	}
	
	public int getHeight(){
		if (height == 0){//if it hasn't been calculated yet, calculate the maximum width of the images in this animation
			for (Image img : images){
				int h = img.getHeight(null);
				if (h > height)
					height = h;
			}
		}
		return height;
	}
	
}

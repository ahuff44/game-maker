package gameMaker.visuals;

import java.awt.Image;

/**
 * Holds a sequence of Images.
 * In-game, an object whose Sprite contains an Animation with multiple frames will be animated, at the rate of one frame of animation per game step
 * 
 * @author Andrew
 */
public class Animation {

	
	//fields
	
	//	TODO clearly separate this and Sprite
	// TODO make it so each frame can have a differnce width, height, and bounding box (is this a good idea??)
	private Image[] images;
	private int width;
	private int height;
	private int index;

	
	//constructors
	
	
	public Animation(Image img){
		this(new Image[] {img});
	}

	public Animation(Image[] imgs){
		images = imgs;
		width = 0;
		height= 0;
		index = 0;
	}
	
	
	//static methods
	
	
	public static Animation getEmptyAnimation(){
		return new Animation(new Image[]{});
	}
	
	
	//instance methods
	
	
	/**
	 * @return the number of Images (frames) in this Animation.
	 */
	public int getLength(){
		return images.length;
	}
	
	/**
	 * @return the index of the current Image (frame) that is being shown in-game.
	 */
	public int getIndex(){
		return index;
	}
	
	/**
	 * @param index
	 * @return the Image (frame) at index index in the animation
	 */
	public Image getImage(int index){
		return images[index];
	}
	
	/**
	 * Cycles one fram through the animation.
	 * 
	 * Automatically loops back to the beginning if the animation has just finished.
	 * @return the new index the animation is on
	 */
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

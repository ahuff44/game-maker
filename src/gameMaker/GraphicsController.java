package gameMaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.JComponent;

public class GraphicsController extends JComponent{

	private static FPSManager fpsManager;
	
	public GraphicsController(int frameRate){
		fpsManager = new FPSManager(frameRate);//TODO move this to GameController?
	}

	public static void preloadImages(String imageFileLocation) {
		try {
			ImageLoader.preload(imageFileLocation);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Image getImage(String imageAddress) {
		try {
			return ImageLoader.get(imageAddress);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * A debugging method that prints out all image keys.
	 */
	public static void printImageKeys(){
		ImageLoader.printImageKeys();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Room r = GameController.getRoom();
		int rw = r.getSize().width + 2;//room width
		int rh = r.getSize().height + 2;// and height

		//draw background color
		Color c = r.backgroundColor();
		if (c != null){
			g.setColor(c);
			g.fillRect(0, 0, rw, rh);
		}
		
		//TODO make this be preloaded so it doesn't have to recalculate on every step
		//draw background image
		Image img = r.backgroundImage();
		if (img != null){
			int iw = img.getWidth(null);//image width
			int ih = img.getHeight(null);//and height
			AffineTransform transform = new AffineTransform();//default is position (0,0)
			switch (r.backgroundType()){
			case TILED:
				for (int x = 0; x < rw; x += iw)
					for (int y = 0; y < rh; y += ih){
						transform.setToTranslation(x, y);
						((Graphics2D)g).drawImage(img, transform, null);
					}
				break;
			case SCALED:
				transform.scale(((double) rw) / iw,
									((double) rh) / ih);
				((Graphics2D)g).drawImage(img, transform, null);
				break;
			case NEITHER:
				((Graphics2D)g).drawImage(img, transform, null);
				break;
			}
		}//drawingOrder
		
		Comparator<GameObject> comparator = new DepthComparator(DepthComparator.SortOrder.DEEP_FIRST);
        PriorityQueue<GameObject> queue = new PriorityQueue<GameObject>(10, comparator);
		queue.addAll(ObjectController.getAllObjects());
		for (GameObject obj : queue){//old:   ObjectController.getAllObjects()){
			obj.draw(g);
			g.setColor(Color.BLACK);
		}
		fpsManager.update();
		g.drawString("FPS: " + fpsManager.getFPS(), 10, 10);
	}
	
}

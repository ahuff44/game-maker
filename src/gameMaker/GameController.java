package gameMaker;


/*      TODO  TODO  TODO  TODO  TODO  TODO  TODO  TODO  TODO
-clearly define the difference between uses of "object" and "instance"
-kill getRoomRectangle
-make rooms that remember previous state

-see  http://www.javapractices.com/topic/TopicAction.do?Id=160  ; which type of Timer should run the game??
-smooth out image loading; automatically preload them all into a Map?
*/

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * This drives games. It was HEAVILY based off GameMaker, by Mark Overmars
 * 
 * @author Andrew
 */
public abstract class GameController extends JFrame implements ActionListener, HasIntuitiveKeyListener{

	private static GameController mainGame;//There should only be one instance of GameController, and this is the static class' link to that one instance
	private static GraphicsController graphicsController;
	private Timer clock;

	private final static boolean displayDebugStatements = false;
	final static int FRAME_RATE = 30;
	
	private static Room currentRoom;
	private Point mousePosition;
	
	public GameController(String title){
		super(title);
		System.out.println("started");
		
		setScreenSettings();

		intitializeListeners(getContentPane());
		
		ObjectController.initialize();
		
		GraphicsController.preloadImages(getImageFileLocation());
		

			try {
				final String PREFIX = "bin\\";
				final String SUFFIX = ".class";
				String pkgName = getClass().getPackage().getName();
				ArrayList<File> classFiles = FileFinder.getFiles(PREFIX + pkgName, SUFFIX);
				for (File file : classFiles){
					try {
						String packageLocation = file.toString().substring(PREFIX.length());
						packageLocation = packageLocation.replace('\\', '.');
						packageLocation = packageLocation.substring(0, packageLocation.length() - SUFFIX.length());

						Class<?> c = Class.forName(packageLocation);
						System.out.println("trying on " + c.getSimpleName());
						if (GameObject.class.isAssignableFrom(c)){// if c "instanceof" GameObject
							System.out.println(" success");
	
							
							
							
							/*for (Method m : c.getDeclaredMethods())
								System.out.println(m);*/
							
							//c.getDeclaredMethod("initializeCollisionGroups", null).invoke(null, null);//TODO working here; why does it call the super static initializeCollisionGroups()???
						
							GameObject.initializeCollisionGroups((Class<? extends GameObject>) c);
						} else
							System.out.println(" failure");
	
						
					} catch (ClassCastException e){//for non-GameObjects, like Rooms
						System.out.println("*****************unable to cast");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
		initialize();//TODO smooth out game start/room start operations
		if (currentRoom == null)
			throw new IllegalStateException("The game developer must load a room in the initialize() method of the class that extends GameController.");
		
		resizeWindow(getRoom().getSize());
		
		setVisible(true);
		
		clock = new Timer(FRAME_RATE, this);
		clock.start();
	}
	
	public void intitializeListeners(Container c){
		mousePosition = new Point(-25, -25);
		graphicsController = new GraphicsController(FRAME_RATE);
		//TODO clear listeners somehow, so this can be used in restarting the game
		
		c.add(graphicsController);
		
		c.addMouseListener(new MouseListener() {
			
			@Override public void mouseEntered(MouseEvent e) { }
			@Override public void mouseExited(MouseEvent e) { }
			@Override public void mousePressed(MouseEvent e) { forwardMouseEvent(e); debugln(" mouse pressed"); }
			@Override public void mouseClicked(MouseEvent e) { forwardMouseEvent(e); debugln(" mouse clicked"); }
			@Override public void mouseReleased(MouseEvent e) { forwardMouseEvent(e); debugln(" mouse released"); }			
		});
		
		c.addMouseMotionListener(new MouseMotionListener(){//TODO find a better way to be able to find the mouse position
			
			@Override public void mouseDragged(MouseEvent e) { mousePosition = e.getPoint(); }
			@Override public void mouseMoved(MouseEvent e) { mousePosition = e.getPoint(); }
		});
		
		addKeyListener(new IntuitiveKeyListener(this));
	}
	
	/*
	 * Copy and paste this generic method into another class to make a basic JFrame window
	 */
	public void setScreenSettings() {
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void resizeWindow(Dimension newSize){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screen.width - newSize.width) / 2,
				  (screen.height - newSize.height) / 2,
				  newSize.width + 4 + 4,
				  newSize.height + 4 + 4 + 20);
	}

	public void forwardMouseEvent(MouseEvent e){
		ObjectController.forwardMouseEvent(e);
	}
	
	public void forwardKeyEvent(ModifiedKeyEvent e){
		ObjectController.forwardKeyEvent(e);
	}
	
	
	//from HasIntuitiveKeyListener:
	
	
	public void keyPressed(KeyEvent arg0){
		forwardKeyEvent(new ModifiedKeyEvent(arg0.getKeyCode(), KeyEventType.KEY_PRESSED));
	}

	public void keyDown(KeyEvent arg0){
		forwardKeyEvent(new ModifiedKeyEvent(arg0.getKeyCode(), KeyEventType.KEY_DOWN));
	}
	
	public void keyReleased(KeyEvent arg0){
		forwardKeyEvent(new ModifiedKeyEvent(arg0.getKeyCode(), KeyEventType.KEY_RELEASED));
	}


	public abstract void initialize();//this should be changed to load game or load room or something; TODO change and refine this
	public abstract String getImageFileLocation();
	
	
	/**
	 * This is the method that drives the game. It was based HEAVILY off this section of Game Maker's help file:
	 *  
	 *   <p>"In some situation it is important to understand the order in which Game Maker processes the events. This is as follows: 
	 *
	 *		<br>-Begin step events 
	 *	 	<br>-Alarm events 
	 *		<br>-Keyboard, Key press, and Key release events 
	 *		<br>-Mouse events 
	 *		<br>-Normal step events 
	 *	 <br>(now all instances are set to their new positions) 
	 *		<br>-Collision events 
	 *		<br>-End step events 
	 *		<br>-Drawing events 
	 *
	 *	 <p>The creation, destroy, and other events are performed when the corresponding things happen."
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		ObjectController.callBeginStepEvents();
		ObjectController.callAlarmEvents();
		ObjectController.callKeyEvents();
		ObjectController.callMouseEvents();
		ObjectController.callStepEvents();

		ObjectController.updatePositions();

		ObjectController.checkCollisions();
		ObjectController.callEndStepEvents();
		
		graphicsController.repaint();//calls the draw events inside

		ObjectController.updatePreviousPositions();

	}

	/**
	 * Call this to restart the game; although I'm not entirely sure it entirely works
	 */
	public static void restart(){
		GameController gc = getMainGame();
		//TODO make this work gc.intitializeListeners(gc.getContentPane()); , gc.initL
		gc.initialize();

	}

	/**
	 * Returns a <code>Rectangle</code> that represents the game room. TODO change later
	 * @return a Rectangle that represents the game room
	 */
	public static Rectangle getRoomRectangle() {
		return new Rectangle(0, 0, mainGame.getWidth() - 8, mainGame.getHeight() - 28);
	}
	
	public static void setRoom(Room room){
		currentRoom = room;
	}
	
	public static Room getRoom(){
		return currentRoom;
	}

	/**
	 * Returns a reference to the one instance of this (mostly) static class.  You shouldn't really need this.
	 * @return a reference to the one instance of this (mostly) static class
	 */
	public static GameController getMainGame(){
		return mainGame;
	}

	/**
	 * You ***really*** shouldn't need this.
	 * @param gc The GameController to set
	 */
	public static void setMainGame(GameController gc) {
		mainGame = gc;
	}
	
	/**
	 * Returns the game's graphics controller. You shouldn't really need this.
	 * @return a reference to the one instance of this (mostly) static class
	 */
	public static GraphicsController getGraphicsController(){
		return graphicsController;
	}

	/**
	 * You ***really*** shouldn't need this.
	 * @param gc The GraphicsController to set
	 */
	public static void setGraphicsController(GraphicsController gc) {
		graphicsController = gc;
	}
	
	public Point getMousePosition(){
		return mousePosition;
	}
	
	/**
	 * Equivalent to <code>System.out.print(str)</code>; however, all of these statements can be turned off by turning off the <code>displayDebugStatements</code> variable in <code>GameController</code>.
	 * @param str the <code>String</code> to be printed
	 */
	public static void debug(String str) {
		if (displayDebugStatements)
			System.out.print(str);
	}

	/**
	 * Equivalent to <code>System.out.println(str)</code>; however, all of these statements can be turned off by turning off the <code>displayDebugStatements</code> variable in <code>GameController</code>.
	 * @param str the <code>String</code> to be printed
	 */
	public static void debugln(String str) {
		if (displayDebugStatements)
			System.out.println(str);
	}
	
}

package gameMaker;


/*      TODO  TODO  TODO  TODO  TODO  TODO  TODO  TODO  TODO
-See if you can't clean up loading collision lists- your use of reflect is madness (and impressive)
-Clearly define the difference between uses of "object" and "instance"
Make Room a full class, not just an interface
	-refactor getRoomRectangle
	-make rooms that remember their previous state

-see  http://www.javapractices.com/topic/TopicAction.do?Id=160  ; which type of Timer should run the game??
-I think I've done this...: OLD COMMENT: smooth out image loading; automatically preload them all into a Map?
*/

import gameMaker.objects.GameObject;
import gameMaker.objects.ObjectController;
import gameMaker.objects.eventInterfaces.HasIntuitiveKeyListener;
import gameMaker.utilities.FileFinder;
import gameMaker.utilities.intuitiveKeyListener.IntuitiveKeyListener;
import gameMaker.utilities.intuitiveKeyListener.KeyEventType;
import gameMaker.utilities.intuitiveKeyListener.ModifiedKeyEvent;
import gameMaker.visuals.GraphicsController;

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
 * This drives games. It was HEAVILY inspired by GameMaker, a game-creation program written by Mark Overmars
 * 
 * Your subclass should override the constructor, getImageFileLocation(), and initializeGame()
 * 
 * @author Andrew
 */
public abstract class GameController extends JFrame implements ActionListener, HasIntuitiveKeyListener{

	/**
	 * There should only be one instance of GameController at any one time, and this is the static class' link to that one instance
	 */
	private static GameController mainGame;
	
	private static GraphicsController graphicsController;
	private Timer clock;

	private final static boolean displayDebugStatements = false;
	final static int FRAME_RATE = 30;
	
	private static Room currentRoom;
	private Point mousePosition;
	
	/**
	 * Whether the player can press the Escape key on their keyboard to exit the game at any time.
	 * 
	 * This is true by default.
	 */
	private boolean exitEscEnabled;
	
	/**
	 * Whether the player can press the F1 key on their keyboard to restart the game at any time.
	 * 
	 * This is true by default.
	 */
	private boolean restartF1Enabled;
	
	/**
	 * Loads and starts the game
	 * 
	 * @param title The title to give the window for your game (this gets passed up to the JFrame constructor)
	 */
	public GameController(String title){
		super(title);
		System.out.println("Loading game...");
		setScreenSettings();
		intitializeListeners(getContentPane());
		enableExitEsc();
		enableRestartF1();
		GraphicsController.preloadImages(getImageFileLocation());
		restartGame();
		clock = new Timer(FRAME_RATE, this);
		clock.start();
	}
		
	private void loadCollisionLists(){
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
					if (GameObject.class.isAssignableFrom(c)){// if c "instanceof" GameObject
						System.out.println("Loaded GameObject "+c.getSimpleName());

						//c.getDeclaredMethod("initializeCollisionGroups", null).invoke(null, null);//TODO figure out how to do this better, without reflection. Old TODO msg I don't understand anymore: why does it call the super static initializeCollisionGroups()???
						GameObject.initializeCollisionGroups((Class<? extends GameObject>) c);
					} else {
						System.out.println("Not a GameObject: "+c.getSimpleName());
					}
					
				} catch (ClassCastException ex){//for non-GameObjects, like Rooms
					System.out.println("*****************unable to cast");
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (FileNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
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
	
	
	@Override
	public void keyPressed(KeyEvent event){
		switch (event.getKeyCode()){
		case KeyEvent.VK_ESCAPE:
			if (exitEscEnabled){
				System.exit(0);
				return;
			}
			break;
		case KeyEvent.VK_F1:
			if (restartF1Enabled){
				GameController.getMainGame().restartGame();
				return;
			}
			break;
		}
		forwardKeyEvent(new ModifiedKeyEvent(event.getKeyCode(), KeyEventType.KEY_PRESSED));
	}

	@Override
	public void keyDown(KeyEvent event){
		forwardKeyEvent(new ModifiedKeyEvent(event.getKeyCode(), KeyEventType.KEY_DOWN));
	}
	
	@Override
	public void keyReleased(KeyEvent event){
		forwardKeyEvent(new ModifiedKeyEvent(event.getKeyCode(), KeyEventType.KEY_RELEASED));
	}
	
	


    
    // The following stuff is useful for game developers: (more useful than the above stuff, at least)
    
    
	
	
	
	/**
	 * Restarts the game.
	 * 
	 * Calling this will be like exiting and re-launching the game.
	 */
	public void restartGame(){
		System.out.println("Restarting game...");
		setVisible(false);
		ObjectController.initialize();
		loadCollisionLists();
		
		initializeGame(); //TODO smooth out game start/room start operations
		if (currentRoom == null)
			throw new IllegalStateException("The game developer must load a room in the initialize() method of the class that extends GameController.");
		resizeWindow(getRoom().getSize());
		
		setVisible(true);
	}

	/** 
	 * @see exitEscEnabled
	 */
	public void enableExitEsc(){
		exitEscEnabled = true;
	}
	
	/** 
	 * @see exitEscEnabled
	 */
	public void disableExitEsc(){
		exitEscEnabled = false;
	}
	
	/** 
	 * @see restartF1Enabled
	 */
	public void enableRestartF1(){
        restartF1Enabled = true;
    }

	/** 
	 * @see restartF1Enabled
	 */
    public void disableRestartF1(){
        restartF1Enabled = false;
    }
    
    
    /**
     * This method gets called at the beginning of the game.
     * You (as a game developer) must load a Room during this method.
     * 
     * This method is also called when restartGame() is called.
     */
	public abstract void initializeGame();
	
	/**
	 * Returns the location (relative to the directory you're executing your program from) for the main directory that holds all of the image files needed for this game.
	 * 
	 * @return a String that represents a relative filepath. For example, "src/space/images/"
	 */
	public abstract String getImageFileLocation();
	
	
	/**
	 * This is the method that drives the game. It gets called every "step" of the game.
	 * The game engine tries to execute this method 30 times per second; there are 30 steps in a second.
	 * 
	 * It was based HEAVILY off this section of the help file in Mark Overmars' GameMaker:
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
	 *	 <p>The createEvent(), destroyEvent(), and other similar methods are automatically called when the corresponding event happens."
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
		
		graphicsController.repaint(); // calls all objects' draw events

		ObjectController.updatePreviousPositions();
	}

	/**
	 * Returns a <code>Rectangle</code> that represents the game room.
	 * 
	 * TODO later change this into a class Room. (Currently I have an interface Room, but I could easily see it becoming a class soon)
	 * 
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
	 * @see #mainGame
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
	
	public Point getMousePosition(){ //TODO do I need this? apparently it's overriding something... actually I think this one takes care of the edges of the screen so I DO need it
		return mousePosition;
	}
	
	/**
	 * Equivalent to <code>System.out.print(str)</code>; however, all of these statements can be turned off by turning off the <code>displayDebugStatements</code> variable in <code>GameController</code>.
	 * @param str the <code>String</code> to be printed
	 */
	public static void debug(String str) {
		if (displayDebugStatements){
			System.out.print(str);
		}
	}

	/**
	 * Equivalent to <code>System.out.println(str)</code>; however, all of these statements can be turned off by turning off the <code>displayDebugStatements</code> variable in <code>GameController</code>.
	 * @param str the <code>String</code> to be printed
	 */
	public static void debugln(String str) {
		if (displayDebugStatements){
			System.out.println(str);
		}
	}
	
}

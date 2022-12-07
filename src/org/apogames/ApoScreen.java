package org.apogames; 

import java.awt.Canvas; 
import java.awt.Color; 
import java.awt.Component; 
import java.awt.Dimension; 
import java.awt.DisplayMode; 
import java.awt.Graphics2D; 
import java.awt.GraphicsConfiguration; 
import java.awt.GraphicsDevice; 
import java.awt.GraphicsEnvironment; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
import java.awt.image.BufferStrategy; 
import java.io.IOException; 

import javax.imageio.ImageIO; 
import javax.swing.JApplet; 
import javax.swing.JFrame; 

import org.apogames.input.ApoKeyboard; 
import org.apogames.input.ApoMouse; 

/**
 * Class that creates the actual frame and houses the component. <br />
 * and make sure that the update and rendering methods are properly passed <br />
 * @author Dirk Aporius
 *
 */
public  class  ApoScreen {
	

	/** the graphicsEnvironment object of the game */
	private GraphicsEnvironment graphicEnvironment;

	
	/** the graphicsDevice object of the game */
	private GraphicsDevice graphicDevice;

	
	/** the graphicsConfiguration object of the game */
	private GraphicsConfiguration graphicConfiguration;

	
	/** The title of the game */
	private String title;

	
	/** the display configuration of the game */
	private ApoDisplayConfiguration displayConfiguration;

	
	/** the BufferStrategy object for hardware-accelerated drawing */
	private BufferStrategy bufferStrategy;

	
	/** the actual component where the game is on */
	private Component component = null;

	
	/** Parent object to find out if it is a JApplet */
	private Component parent = null;

	
	/** the current subGame object */
	private ApoSubGame subGame;

	
	/** the actual JFrame, which contains and displays the game */
	private JFrame frame;

	
	/** Variable that stores the frames per second */
	private int fps;

	

	/**
	 * Constructor with title and display configuration of the game
	* @param title: title of the game
	* @param displayConfiguration: Display configurations
	 */
	public ApoScreen(String title, ApoDisplayConfiguration displayConfiguration) {
		this.graphicEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.graphicDevice = graphicEnvironment.getDefaultScreenDevice();
		this.graphicConfiguration = graphicDevice.getDefaultConfiguration();

		this.title = title;
		this.displayConfiguration = displayConfiguration;
	}

	

	/**
	 * is called when initializing the game and creates the actual window or applet
	 */
	public void init() {
		if (this.displayConfiguration.isApplet()) {
			this.initApplet();
		} else {
			if (this.displayConfiguration.isWindowed()) {
				this.initWindowed();
			} else {
				this.initFullscreen();
			}
		}
	}

	

	/**
	 * Gives the actual component where the game is on
	 * @return returns the actual component where the game is on
	 */
	public final Component getComponent() {
		return this.component;
	}

	

	/**
	 * returns the frames per second
	 * @return returns the frames per second
	 */
	public int getFps() {
		return this.fps;
	}

	

	/**
	 * sets the frames per seconds to the given value
	 * @param fps: new frames per seconds
	 */
	public void setFps(int fps) {
		this.fps = fps;
	}

	

	/**
	 * returns the game's current subGame
	 * @return returns the game's current game subGame
	 */
	public ApoSubGame getSubGame() {
		return this.subGame;
	}

	

	/**
	 * sets the SubGame to the given value and repaints it
	 * @param subGame: new subGame
	 */
	public void setSubGame(ApoSubGame subGame) {
		this.subGame = subGame;
		
		this.subGame.render();
	}

	

	/**
	 * returns the parent element of the screen
	 * @return returns the parent element of the screen
	 */
	public final Component getParent() {
		return this.parent;
	}

	

	/**
	 * sets the parent element of the screen to the given one
	 * @param parent: new parent element
	 */
	public void setParent(Component parent) {
		this.parent = parent;
	}

	

	/**
	 * initializes the applet
	 */
	public void initApplet() {
		if ((this.parent != null) && (this.parent instanceof JApplet)) {
			final int width = this.displayConfiguration.getWidth();
			final int height = this.displayConfiguration.getHeight();

			Canvas window = null;
			if (ApoGameConstants.BUFFER_STRATEGY) {
				window = new Canvas(graphicConfiguration);
			} else {
				window = new ApoCanvas();
			}
			window.setPreferredSize(new Dimension(width, height));
			window.setBackground(Color.BLACK);

			ApoGameConstants.B_APPLET = true;

			JApplet applet = (JApplet)this.parent;
			applet.setSize(new Dimension(width, height));
			applet.add(window);
			applet.setIgnoreRepaint(true);
			applet.add(window);
			applet.setEnabled(true);
			applet.setVisible(true);

			if (ApoGameConstants.BUFFER_STRATEGY) {
				window.createBufferStrategy(2);
				this.bufferStrategy = window.getBufferStrategy();
			}
			
			this.component = window;
		}
	}

	

	/**
	 * initializes the actual game window in window mode and differentiates whether you want to play with BufferStrategy or without
	 */
	private void initWindowed() {
		this.frame = new JFrame(this.title, this.graphicConfiguration);

		try {
			this.frame.setIconImage(ImageIO.read(this.getClass().getClassLoader().getResource("images/icon.gif")));
		} catch (IOException e) {
		} catch (Exception e) {
		}
		
		if (ApoGameConstants.BUFFER_STRATEGY) {
			Canvas window = new Canvas(this.graphicConfiguration);
	
			int width = this.displayConfiguration.getWidth();
			int height = this.displayConfiguration.getHeight();
			window.setPreferredSize(new Dimension(width, height));
			window.setBackground(Color.BLACK);
	
			this.addWindowListener(this.frame);
			this.frame.setResizable(false);
			this.frame.setIgnoreRepaint(true);
			this.frame.add(window);
			this.frame.pack();
			this.frame.setLocationRelativeTo(null);
			this.frame.setEnabled(true);
			this.frame.setVisible(true);
	
			window.createBufferStrategy(2);
			this.bufferStrategy = window.getBufferStrategy();
			
			this.component = window;
		} else {
			ApoCanvas window = new ApoCanvas();
			
			int width = this.displayConfiguration.getWidth();
			int height = this.displayConfiguration.getHeight();
			window.setPreferredSize(new Dimension(width, height));
			window.setBackground(Color.BLACK);
	
			this.addWindowListener(this.frame);
			this.frame.setResizable(false);
			this.frame.setIgnoreRepaint(false);
			this.frame.add(window);
			this.frame.pack();
			this.frame.setLocationRelativeTo(null);
			this.frame.setEnabled(true);
			this.frame.setVisible(true);
			
			this.component = window;
		}
	}

	
	
	/**
	 * Adds a window listener to a passed frame and overrides the Close method
	 * @param frame: window
	 */
	private void addWindowListener(JFrame frame) {
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ApoScreen.this.quit();
				System.exit(0);
			}
		});
	}

	
	
	/**
	 * is called if you want to turn off the game
	 */
	private void quit() {
	}

	

	/**
	 * initializes the frame in full screen mode
	 */
	private void initFullscreen() {
		this.frame = new JFrame(this.title, this.graphicConfiguration);
		java.awt.Window window = new java.awt.Window(this.frame, this.graphicConfiguration);

		this.addWindowListener(this.frame);
		this.frame.setEnabled(true);
		this.frame.setVisible(true);

		int width = this.displayConfiguration.getWidth();
		int height = this.displayConfiguration.getHeight();
		window.setPreferredSize(new Dimension(width, height));
		window.setBackground(Color.BLACK);
		window.setVisible(true);
		window.setEnabled(true);

		this.graphicDevice.setFullScreenWindow(window);
		DisplayMode display = new DisplayMode(this.displayConfiguration.getWidth(), this.displayConfiguration.getHeight(), this.displayConfiguration.getDepth(), ApoGameConstants.FPS_RENDER);
		this.graphicDevice.setDisplayMode(display);

		window.createBufferStrategy(2);
		this.bufferStrategy = window.getBufferStrategy();

		this.component = window;
	}

	

	/**
	 * trying to draw the game
	 */
	protected void update() {
		if (this.bufferStrategy != null) {
			try {
				this.bufferStrategy.getDrawGraphics().dispose();
				this.bufferStrategy.show();
			} catch (Exception ex) {
			}
		}
	}

	

	/**
	 * returns the BufferStrategy from the game (can also be NULL)
	 * @return returns the BufferStrategy from the game (can also be NULL)
	 */
	public final BufferStrategy getBufferStrategy() {
		return this.bufferStrategy;
	}

	

	/**
	 * returns the Graphics2D object for drawing
	 * @return returns the Graphics2D object for drawing
	 */
	public final Graphics2D getGraphics2D() {
		try {
			return (Graphics2D) this.bufferStrategy.getDrawGraphics();
		} catch (Exception ex) {
			return null;
		}
	}

	

	/**
	 * returns the display configuration of the game
	 * @return returns the display configuration of the game
	 */
	public final ApoDisplayConfiguration getDisplayConfiguration() {
		return this.displayConfiguration;
	}

	

	/**
	 * Adds a keyListener to the component so that key events can also be viewed
	 * @param keyboard: keyboard class object
	 */
	public void addKeyboard(ApoKeyboard keyboard) {
		this.component.addKeyListener(keyboard);
		this.component.requestFocus();
	}

	

	/**
	 * Adds a mouseListener to the component so mouse events can be viewed as well
	 * @param mouse: mouse class object
	 */
	public void addMouse(ApoMouse mouse) {
		this.component.addMouseListener(mouse);
		this.component.addMouseMotionListener(mouse);
		this.component.requestFocus();
	}


}

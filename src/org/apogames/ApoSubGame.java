package org.apogames; 

import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.image.BufferedImage; 

import org.apogames.input.ApoKeyboard; 
import org.apogames.input.ApoMouse; 

/**
 * abstract class from which inherits the actual game panel and all important acts
 * from the keyboard input, mouse input and the "actual engine"
 * 
 * @author Dirk Aporius
 *
 */
public abstract  class  ApoSubGame  extends Thread {
	

	/** the screen object for the game */
	protected final ApoScreen screen;

	
	/** the keyboard input object for the game */
	protected ApoKeyboard keyboard;

	
	/** the mouse object for the game */
	protected ApoMouse mouse;

	
	/** integer variable which indicates what the next SubGame ID is */
	private int nextID = 0;

	
	/** boolean Variable which indicates whether a game is in progress or not */
	private boolean isRunning = false;

	
	/** long variable which indicates what time was last in ms */
	private long last;

	
	/** Counts the number of new drawings per second */
	private int frames;

	
	/** boolean Variable indicating whether the game should always think */
	private boolean bShouldThink;

	
	/** boolean Variable indicating whether the game should always be redrawn */
	private boolean bShouldRepaint;

	
	/** long Variable which indicates how long the game has needed to draw in ns */
	protected long renderTime;

	
	/** long Variable which indicates how long the game needed in the last reflection in ns */
	private long thinkTime;

	
	
	private BufferedImage offscreenImage = new BufferedImage(ApoGameConstants.GAME_WIDTH, ApoGameConstants.GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);

	
    private Graphics offscreenGraphics = this.offscreenImage.getGraphics();

	

	/**
	 * Constructor with the actual screen
	 * @param screen : Screen object
	 */
	public ApoSubGame(final ApoScreen screen) {
		super();
		this.screen = screen;
		this.screen.setSubGame(this);
	}

	
	
	public void init() {
		this.keyboard = new ApoKeyboard();
		this.screen.addKeyboard(this.keyboard);
		this.mouse = new ApoMouse();
		this.screen.addMouse(this.mouse);
		this.bShouldRepaint = true;
		this.bShouldThink = true;
		
		this.renderTime = 0;
		this.thinkTime = 0;
		
		this.load();
	}

	

	/**
	 * returns the screen object
	 * @return returns the screen object
	 */
	public final ApoScreen getScreen() {
		return this.screen;
	}

	

	@Override
	public void run() {
		long unprocessedTimeThink = 0;
		long unprocessedTimeRender = 0;
		long msPerTickThink = (long)(1000.0 / (double)(ApoGameConstants.FPS_THINK));
		long msPerTickRender = (long)(1000.0 / (double)(ApoGameConstants.FPS_RENDER));
		this.frames = 0;
		long lastFpsTime = System.currentTimeMillis() - 1000;
		this.last = System.currentTimeMillis();
		this.isRunning = true;

		// actual game logic loop
		while (this.isRunning) {
			long now = System.currentTimeMillis();
			long passedTime = now - this.last;
			// if time has passed since the last call
			if (passedTime > 0) {
				unprocessedTimeThink += passedTime;
				unprocessedTimeRender += passedTime;
				// if you want to think then think about it
				while (unprocessedTimeThink >= msPerTickThink) {
					if (unprocessedTimeThink > 10 * msPerTickThink) {
						unprocessedTimeThink = 0;
					} else {
						unprocessedTimeThink -= msPerTickThink;
					}
					if (!this.bShouldThink) {
						unprocessedTimeThink = 0;
					} else {
						this.think(msPerTickThink);
					}
				}
				// if you want to draw, then draw again
				if (unprocessedTimeRender >= msPerTickRender) {
					unprocessedTimeRender -= msPerTickRender;
					if (this.bShouldRepaint) {
						this.render();
					}
				}
				this.last = now;
			}
			// if one second has passed, then update the FPS number
			if (System.currentTimeMillis() - lastFpsTime > 1000) {
				if (!this.bShouldRepaint) {
					this.render();
				}
				lastFpsTime += 1000;
				this.screen.setFps(this.frames);
				this.frames = 0;
			}

			// put the thread to sleep for 10 milliseconds
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
	}

	

	/**
	 * Sets the value of whether a game should always be updated or not on the given
	 * @param shouldThink : TRUE Game should always be updated, otherwise FALSE
	 */
	public void setShouldThink(boolean shouldThink) {
		this.bShouldThink = shouldThink;
	}

	

	/**
	 * returns whether the game should always be redrawn or not
	 * @return TRUE Game should always be redrawn, FALSE game should only be redrawn if you are using a button
	 */
	public final boolean shouldRepaint() {
		return this.bShouldRepaint;
	}

	

	/**
	 * sets the value, whether to be redrawn on the given
	 * @param shouldRepaint : TRUE Game should always be redrawn, FALSE game should only be redrawn if you are using a button
	 */
	public void setShouldRepaint(boolean shouldRepaint) {
		this.bShouldRepaint = shouldRepaint;
	}

	
	
	/**
	 * is called when thinking
	 * and gives back whether or not to think at all
	 * @param delta : Time elapsed since the last call in milliseconds
	 * @return TRUE, Game has been updated otherwise FALSE
	 */
	private final boolean think(long delta) {
		if (this.bShouldThink) {
			long time = System.nanoTime();
			this.update(delta);
			this.thinkTime = System.nanoTime() - time;
			return true;
		}
		return false;
	}

	

	/**
	 * is called when the game is to be drawn <br />
	 * It differentiates between a normal swing component that is simply repainted <br />
	 * or a BufferStrategy painted differently <br />
	 */
	public void render() {
		if (this.screen.getBufferStrategy() != null) {
			long time = System.nanoTime();
			this.render(this.screen.getGraphics2D());
			this.screen.update();
			this.addFrame();
			this.renderTime = System.nanoTime() - time;
		} else {
			long time = System.nanoTime();
			this.render((Graphics2D)(this.offscreenGraphics));
            this.addFrame();
            
            if (this.getScreen().getComponent() != null) {
            	Graphics g = this.getScreen().getComponent().getGraphics();
            	if (this.offscreenImage != null) {
            		g.drawImage(this.offscreenImage, 0, 0, null);
            	}
            	g.dispose();
            }
			this.renderTime = System.nanoTime() - time;
		}
	}

	

	/**
	 * adds 1 to the frames
	 */
	public void addFrame() {
		this.frames += 1;
	}

	
	
	/**
	 * returns how long the last drawing took
	 * @return returns how long the last drawing took
	 */
	public final long getRenderTime() {
		return this.renderTime;
	}

	

	/**
	 * returns how long the last "thought" took
	 * @return returns how long the last "thought" took
	 */
	public final long getThinkTime() {
		return this.thinkTime;
	}

	

	/**
	 * returns the next ID if you want to switch between different subgames
	 * @return returns the next ID if you want to switch between different subgames
	 */
	public final int getNextID() {
		return this.nextID;
	}

	
	
	/**
	 * returns the class with the keyboard entries
	 * @return returns the class with the keyboard entries
	 */
	public final ApoKeyboard getKeyboard() {
		return this.keyboard;
	}

	

	/** Is called during initialization and l d everything important */
	protected abstract void load();

	

	/**
	 * Method is called every delta milliseconds and causes the game to think <br />
	 * the actual game logic is in here <br />
	 * @param delta : Time in milliseconds that has elapsed since the last call
	 */
	protected abstract void update(long delta);

	

	/**
	 * Method called, u, render the actual game on the screen
	 * @param g : Graphics2D-Object
	 */
	protected abstract void render(Graphics2D g);


}

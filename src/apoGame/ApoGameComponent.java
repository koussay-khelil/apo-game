package apoGame; 

import java.awt.Cursor; 
import java.awt.Graphics2D; 
import java.io.File; 
import javax.swing.JFileChooser; 
import java.util.*; 

import org.apogames.entity.ApoButton; 
import org.apogames.input.ApoMouse; 
import org.apogames.ApoScreen; 
import org.apogames.ApoSubGame; 
import org.apogames.entity.ApoButton; 
import org.apogames.ApoGameConstants; 

/**
 * Abstract class from which inherits the actual game and 
 * provides basic functionality to the game object
 * @author Dirk Aporius
 *
 */
public abstract  class  ApoGameComponent  extends ApoSubGame {
	
	
	/** Object that can be used to create and load images with specific values */
	private ApoGameImages images;

	
	/** Array of the whole buttons in the game */
	private HashMap<String,ApoButton> buttons;

	
	/** boolean Variable, whether a hand cursor is currently displayed or not */
	private boolean bHandCursor;

	
	
	/** A FileChooser to load an ai */
	private JFileChooser fileChooser;

	
	/** A Class file filter for the ai */
	//private final ApoFileFilter	fileFilter = new ApoFileFilter("class");
	
	private boolean bWin;

	

	
	/**
	 * Constructor
	 * @param screen : Screenobjekt
	 */
	public ApoGameComponent(ApoScreen screen) {
		super(screen);
	}

	
	
	@Override
	protected void load() {
		if (this.images == null) {
			this.images = new ApoGameImages();
		}
		super.setShouldRepaint(false);
	}

	

	public boolean isBWin() {
		return this.bWin;
	}

	

	public void setBWin(boolean win) {
		this.bWin = win;
	}

	

	/**
	 * returns the file chooser to load an AI
	 * @return returns the filechooser to load an AI
	 */
	/*public JFileChooser getFileChooser() {
		if ((!ApoGameConstants.B_APPLET) && (this.fileChooser == null)) {
			this.fileChooser = new JFileChooser();
			this.fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + File.separator));
			this.fileChooser.setFileFilter(this.fileFilter);
		}
		return this.fileChooser;
	}*/
	
	/**
	 * returns the FileFilter for loading an AI
	 * @return returns the FileFilter for loading an AI
	 */
	/*public ApoFileFilter getFileFilter() {
		return this.fileFilter;
	}*/
	
	/**
	 * boolean Variable, whether the FPS should be displayed or not
	 * @return TRUE, FPS should be displayed, FALSE FPS should not be displayed
	 */
	public boolean isShowFPS() {
		return ApoGameConstants.FPS;
	}

	

	/**
	 * sets the boolean value, whether the FPS is to be displayed or not, to the given one
	 * @param showFPS : TRUE, FPS should be displayed, FALSE FPS should not be displayed
	 */
	public void setShowFPS(boolean showFPS) {
		ApoGameConstants.FPS = showFPS;
	}

	

	/**
	 * returns the frames per second
	 * @return returns the frames per second
	 */
	public final int getFPS() {
		return this.screen.getFps();
	}

	


	/**
	 * returns the array with the buttons
	 * @return returns the array with the buttons
	 */
	public final HashMap<String, ApoButton> getButtons() {
		return this.buttons;
	}

	
	
	/**
	 * sets the array with the buttons to the given value
	 * @param buttons: new array with buttons
	 */
	public void setButtons(HashMap<String, ApoButton> buttons) {
		this.buttons = buttons;
	}

	
	/**
	 * returns the image object, loads and creates images
	 * @return returns the image object for loading and creating images
	 */
	public final ApoGameImages getImages() {
		return this.images;
	}

	
	
	/**
	 * renders the buttons
	 * @param g: the Graphics2D Object
	 */
	public void renderButtons(Graphics2D g) {
		if (this.buttons != null) {
			for(ApoButton button : buttons.values()) {
				button.render(g, 0, 0);
			}
		}
	}

	
	
	// Method called every delta-milliseconds, dealing with the logic of the game and keyboard and mouse inputs
	@Override
	protected void update(long delta) {
		int[] keyPressed = this.keyboard.getPressed();
		if (keyPressed != null) {
			for (int i = 0; i < keyPressed.length; i++) {
				this.keyPressed(keyPressed[i], (char) ((int) keyPressed[i]));
			}
		}

		int[][] keyReleased = this.keyboard.getReleased();
		if (keyReleased != null) {
			for (int i = 0; i < keyReleased.length; i++) {
				this.keyReleased(keyReleased[i][0], (char)(keyReleased[i][1]));
			}
		}

		if (this.mouse.isDragged()) {
			this.mouseDragged(this.mouse.getX(), this.mouse.getY(), this.mouse.hasClicked(ApoMouse.LEFT));
		} else if (this.mouse.hasClicked(ApoMouse.LEFT)) {
			this.mousePressed(this.mouse.getX(), this.mouse.getY(), true);
		} else if (this.mouse.hasClicked(ApoMouse.RIGHT)) {
			this.mousePressed(this.mouse.getX(), this.mouse.getY(), false);
		} else if (this.mouse.isMoved()) {
			this.mouseMoved(this.mouse.getX(), this.mouse.getY());
		}
		boolean[] mouseReleased = this.mouse.getReleased();
		if (mouseReleased[ApoMouse.LEFT]) {
			this.mouseReleased(this.mouse.getX(), this.mouse.getY(), true);
		} else if (mouseReleased[ApoMouse.RIGHT]) {
			this.mouseReleased(this.mouse.getX(), this.mouse.getY(), false);
		}
		this.think(delta);
	}

	
	/**
	 * Called when a keyboard key is pressed <br />
	 * @param button: KeyEventConstant for the variable
	 * @param character: Character of KeyEventKostante
	 */
	public void keyPressed(int keyCode, char keyCharacter) {
	}

	

	/**
	 * is called when a keyboard key is released <br />
	 * @param button: KeyEventConstant for the variable
	 * @param character: Character of KeyEventKostante
	 */
	public void keyReleased(int keyCode, char keyChar) {
	}

	

	/**
	 * is called when the mouse has been moved while keeping a mouse button pressed
	* @param x: X-value of the mouse (seen in the frame)
	* @param y: Y-value of the mouse (seen in the frame)
	* @param left: TRUE, left mouse button pressed, otherwise FALSE
	 */
	public void mouseDragged(int x, int y, boolean left) {
	}

	

	/**
	 * is called when the mouse is moved and evaluates whether the mouse is over a button or not
	 * @param x: X-value of the mouse (seen in the frame)
	 * @param y: Y-value of the mouse (seen in the frame)
	 */
	public void mouseMoved(int x, int y) {
		boolean bOver = false;
		if (this.buttons != null) {
			/*for (int i = 0; i < this.buttons.length; i++) {
				if (this.buttons[i].getMove(x, y)) {
					bOver = true;
					if (!super.shouldRepaint()) {
						this.render();
					}
					break;
				} else if (this.buttons[i].isBOver()) {
					bOver = true;
				}
			}*/
			for(ApoButton button : buttons.values()) {
				if(button.getMove(x, y) ) {
					bOver = true;
					if(!super.shouldRepaint()) {
						this.render();
					}
					break;
				} else if (button.isBOver()) {
					bOver = true;
				}
			}
		}
		if (bOver) {
			if (!this.bHandCursor) {
				this.screen.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				this.bHandCursor = true;
			}
		} else {
			if (this.bHandCursor) {
				this.screen.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				this.bHandCursor = false;
			}
		}
	}

	
	
	public void setCursor(Cursor cursor) {
		this.screen.getComponent().setCursor(cursor);
	}

	

	/**
	 * is called when a mouse button is pressed and checks if it is over a button
	 * @param x: X-value of the mouse (seen in the frame)
	 * @param y: Y-value of the mouse (seen in the frame)
	 * @param left: TRUE, left mouse button pressed, otherwise FALSE
	 */
	public void mousePressed(int x, int y, boolean left) {
		if (this.buttons != null) {
			/*for (int i = 0; i < this.buttons.length; i++) {
				if (this.buttons[i].getPressed(x, y)) {
					if (!super.shouldRepaint()) {
						this.render();
					}
					break;
				}
			}*/
			for(ApoButton button : buttons.values()) {
				if(button.getPressed(x, y)) {
					if(!super.shouldRepaint()) {
						this.render();
					}
					break;
				}
			}
		}
	}

	

	/**
	 * is called when a mouse button is released and checks if it is over a button <br />
	* and in this case calls the setButtonFunction with the unique function of the button
	* @param x: X-value of the mouse (seen in the frame)
	* @param y: Y-value of the mouse (seen in the frame)
	* @param left: TRUE, left mouse button pressed, otherwise FALSE
	 */
	public void mouseReleased(int x, int y, boolean left) {
		if (this.buttons != null) {
			for(ApoButton button : buttons.values()) {
				if(button.getReleased(x, y) ) {
					String function = button.getFunction();
					this.setButtonFunction(function);
				}
			}
		}
		if (!super.shouldRepaint()) {
			this.render();
		}
	}

	

	/**
	 * is called when a button has been pressed
	 * @param function : Function the button should perform and make it unique
	 */
	public abstract void setButtonFunction(String function);

	
	
	/**
	 * abstract method that the class should use for logic calculation every delta milliseconds
	 * @param delta: time that has passed in milliseconds since the last call
	 */
	public abstract void think(long delta);


}

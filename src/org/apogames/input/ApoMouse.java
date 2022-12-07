package org.apogames.input; 

import java.awt.event.MouseEvent; 
import javax.swing.event.MouseInputAdapter; 

/**
 * Class that takes care of all mouse events
 * @author Dirk Aporius
 */
public final  class  ApoMouse  extends MouseInputAdapter {
	

	/** Integer variable for the left mouse button */
	public static final int LEFT = MouseEvent.BUTTON1;

	
	/** Integer variable for the right mouse button */
	public static final int RIGHT = MouseEvent.BUTTON3;

	
	/** x variable of the current mouse position */
	private int x = 0;

	
	/** y variable of the current mouse position */
	private int y = 0;

	
	/** x variable of the old previous mouse position */
	private int oldX = 0;

	
	/** Y variable of the old previous mouse position */
	private int oldY = 0;

	
	/** Boolean array which returns whether a mouse button is held straight or not */
	private boolean[] clicks = null;

	
	/** Boolean variable that indicates whether a mouse button is currently being pressed and the mouse is moving */
	private boolean dragged;

	
	/** Boolean array with all mouse events that have just been released */
	private boolean[] released;

	

	/**
	 * Constructor
	 */
	public ApoMouse() {
		this.x = 0;
		this.y = 0;
		this.oldX = this.x;
		this.oldY = this.y;
		this.clicks = new boolean[5];
		this.dragged = false;
		this.released = new boolean[5];
	}

	

	/**
	 * returns the current x-value of the mouse position
	 * @return returns the current x-value of the mouse position
	 */
	public int getX() {
		return this.x;
	}

	

	/**
	 * Returns the current y-value of the mouse position
	 * @return returns the current y-value of the mouse position
	 */
	public int getY() {
		return this.y;
	}

	

	/**
	 * returns whether a mouse button is being pressed and the mouse is moving
	 * @return TRUE, mouse button pressed and mouse moved, otherwise FALSE
	 */
	public boolean isDragged() {
		return this.dragged;
	}

	

	/**
	 * returns a booleanArray containing the mouse buttons that have just been released
	 * @return returns a booleanArray containing the mouse buttons that have just been released
	 */
	public boolean[] getReleased() {
		boolean[] returnReleased = this.released.clone();
		this.released = new boolean[5];
		return returnReleased;
	}

	

	/**
	 * Returns whether a mouse button to be checked is pressed
	 * @param click: mouse button to be checked
	 * @return TRUE, mouse button is pressed, else mouse button is not pressed
	 */
	public boolean hasClicked(int click) {
		return this.clicks[click];
	}

	

	@Override
	public void mousePressed(MouseEvent e) {
		this.clicks[e.getButton()] = true;
		this.updateCoord(e);
	}

	

	@Override
	public void mouseReleased(MouseEvent e) {
		this.clicks[e.getButton()] = false;
		this.dragged = false;
		this.released[e.getButton()] = true;
		this.updateCoord(e);
	}

	

	@Override
	public void mouseMoved(MouseEvent e) {
		this.updateCoord(e);
		this.dragged = false;
	}

	

	@Override
	public void mouseDragged(MouseEvent e) {
		this.updateCoord(e);
		this.dragged = true;
	}

	

	/**
	 * updates the mouse coordinates
	 * @param e : Mouse event
	 */
	private void updateCoord(MouseEvent e) {
		this.oldX = this.x;
		this.oldY = this.y;
		this.x = e.getX();
		this.y = e.getY();
	}

	

	/**
	 * returns whether the mouse has moved since the last mouse event or not
	 * @return TRUE Mouse has been moving since the last mouse event, otherwise FALSE
	 */
	public boolean isMoved() {
		if ((this.oldX != this.x) || (this.oldY != this.y)) {
			this.oldX = this.x;
			this.oldY = this.y;
			return true;
		}
		return false;
	}


}

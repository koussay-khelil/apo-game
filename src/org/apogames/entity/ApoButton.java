package org.apogames.entity; 

import java.awt.Graphics2D; 
import java.awt.image.BufferedImage; 

import org.apogames.entity.ApoEntity; 

/**
 * This class acts on a button that contains a 3-part image
 * the first is displayed when the mouse is not over
 * the second is displayed when the mouse is over the entity
 * the third is displayed when the mouse has clicked on the entity
 * @author Dirk Aporius
 *
 */
public  class  ApoButton  extends ApoEntity {
	
	
	private int				WAIT_DELAY = 70;

	
	private int				wait, maxWait;

	
	private boolean			bWait, bFirstWait;

	
	private String			function;

	
	private boolean 		bOver, bPressed, bVisible;

	
	
	public ApoButton( BufferedImage iBackground, int x, int y, int width, int height, String function )	{
		super(iBackground, x, y, width, height);
		
		this.function	= function;
		this.bOver		= false;
		this.bPressed	= false;
		
		this.wait		= 0;
		this.maxWait 	= 0;
		this.bWait		= false;
		this.bFirstWait	= true;
		this.bVisible	= false;
	}

	
	
	/**
	 * returns whether the entity should be displayed or not
	 *
	 * @return returns whether the entity should be displayed or not
	 */
	@Override
	public boolean isBVisible() {
		return this.bVisible;
	}

	

	/**
	 * Sets the visibility of the entity to the given value
	 *
	* @param bVisible
	 */
	@Override
	public void setBVisible(boolean bVisible) {
		this.bVisible = bVisible;
	}

	
	
	/**
	 * Returns if a mouse button is held every few milliseconds
	* should be checked, if something should change
	* @return returns if a mouse button is held every few milliseconds
	* should be checked, if something should change
	 */
	public boolean isBWait() {
		return this.bWait;
	}

	

	/**
	 * sets the boolean value if the mouse button is held every few milliseconds
	 * should be checked, if something should change, on the given value
	 * @param bWait
	 */
	public void setBWait(boolean bWait) {
		this.bWait = bWait;
	}

	
	
	/**
	 * gives the wait between 2 function calls when the mouse
	 * is held depressed, back
	* @return returns the wait between 2 function calls when the mouse is
	* is held depressed, back
	 */
	public int getWAIT_DELAY() {
		return this.WAIT_DELAY;
	}

	

	/**
	 * sets the waiting time between 2 function calls to the
	 * Passed value
	 * @param wait_delay = new wait time in milliseconds
	 */
	public void setWAIT_DELAY(int wait_delay) {
		this.WAIT_DELAY = wait_delay;
	}

	

	/**
	 * returns whether the mouse is over the button or not
	 * @return TRUE if mouse over it, otherwise FALSE
	 */
	public boolean isBOver() {
		return this.bOver;
	}

	

	/**
	 * sets the boolean value for bover to the passed value
	 * @param bover
	 */
	public void setBOver(boolean bOver) {
		this.bOver = bOver;
	}

	

	/**
	 * returns whether a mouse button is pressed above the button or not
	 * @return TRUE, if the mouse button is pressed, otherwise FALSE
	 */
	public boolean isBPressed()	{
		return this.bPressed;
	}

	

	/**
	 * sets the boolean value for bPressed to the passed value
	 * @param bPressed
	 */
	public void setBPressed(boolean bPressed) {
		this.bPressed = bPressed;
	}

	

	/**
	 * returns the function of the button
	 * @return function
	 */
	public String getFunction()	{
		return this.function;
	}

	

	/**
	 * depresses the function of the button to the given value
	 * @param function
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	
	
	/**
	 * what happens when the mouse is moved in the field
	* @param x: x-coordinate of the mouse
	* @param y: y coordinate of the mouse
	* @return TRUE if mouse over it, otherwise FALSE
	 */
	public boolean getMove( int x, int y ) {
		if ( ( !this.isBOver() ) && ( this.intersects( x, y ) ) && ( this.isBVisible() ) ) {
			this.setBOver( true );
			return true;
		} else if ( ( this.isBOver() ) && ( !this.intersects( x, y ) ) ) {
			this.bOver		= false;
			this.bPressed	= false;
			this.wait 		= 0;
			this.maxWait	= 0;
			this.bFirstWait	= true;
			return true;
		}
		return false;
	}

	
	
	/**
	 * what happens when a mouse button has been pressed in the field
	* @param x: x-coordinate of the mouse
	* @param y: y coordinate of the mouse
	* @return TRUE, if pressed via the mouse button, otherwise FALSE
	 */
	public boolean getPressed( int x, int y ) {
		if ( ( this.isBOver() ) && ( this.intersects( x, y ) ) && ( this.isBVisible() ) ) {
			this.setBPressed( true );
			return true;
		}
		return false;
	}

	
	
	/**
	 * What happens when a mouse button is released in the field
	* @param x: x-coordinate of the mouse
	* @param y: y coordinate of the mouse
	* @return TRUE if the mouse button was released and the player had also pressed this button, otherwise FALSE
	 */
	public boolean getReleased( int x, int y ) {
		if ( ( this.isBPressed() ) && ( this.intersects( x, y ) ) && ( this.isBVisible() ) ) {
			this.setBPressed( false );
			this.wait 		= 0;
			this.maxWait	= 0;
			this.bFirstWait	= true;
			return true;
		}
		return false;
	}

	
	
	public int getWait() {
		return this.wait;
	}

	

	/**
	 * what happens when a mouse button is pressed and held
	 * @param delay
	 */
	public void think( int delay ) {
		if ( !this.isBWait() ) {
			return;
		}
		if ( this.isBPressed() ) {
			this.wait += delay;
			this.maxWait += delay;
			if ( this.bFirstWait ) {
				if ( this.wait > 400) {
					this.wait -= 400;
					this.bFirstWait = false;
					return;
				}
			} else {
				if (this.maxWait > 2500) {
					if (this.wait > this.WAIT_DELAY/2) {
						this.wait -= this.WAIT_DELAY/2;
					}
				} else {
					if (this.wait > this.WAIT_DELAY) {
						this.wait -= this.WAIT_DELAY;
					}
				}
			}
		}
	}

	
	
	/**
	 * paint the button in place getX () + changeX and getY () + changeY
	* @param changeX: shift in x-direction
	* @param changeY: shift in y direction
	 */
	public void render(Graphics2D g, int changeX, int changeY) {
		if (this.isBVisible()) {
			if (this.isBPressed()) { 
				g.drawImage( this.getIBackground(), (int)this.getX() + changeX, (int)this.getY() + changeY, (int)( this.getX() + changeX + this.getWidth() ), (int)( this.getY() + changeY + this.getHeight() ), (int)( 2*this.getWidth() ), 0, (int)( 3*this.getWidth() ), (int)this.getHeight(), null);
			} else if (this.isBOver()) {
				g.drawImage( this.getIBackground(), (int)this.getX() + changeX, (int)this.getY() + changeY, (int)( this.getX() + changeX + this.getWidth() ), (int)( this.getY() + changeY + this.getHeight() ), (int)( 1*this.getWidth() ), 0, (int)( 2*this.getWidth() ), (int)this.getHeight(), null);
			} else if (this.isBUse())
				g.drawImage( this.getIBackground(), (int)this.getX() + changeX, (int)this.getY() + changeY, (int)( this.getX() + changeX + this.getWidth() ), (int)( this.getY() + changeY + this.getHeight() ), (int)( 3*this.getWidth() ), 0, (int)( 4*this.getWidth() ), (int)this.getHeight(), null);
			else {
				g.drawImage( this.getIBackground(), (int)this.getX() + changeX, (int)this.getY() + changeY, (int)( this.getX() + changeX + this.getWidth() ), (int)( this.getY() + changeY + this.getHeight() ), (int)( 0*this.getWidth() ), 0, (int)( 1*this.getWidth() ), (int)this.getHeight(), null);
			}
		}
	}


}

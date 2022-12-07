package org.apogames.entity; 

import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 

import java.awt.geom.Rectangle2D; 
import java.awt.image.BufferedImage; 

public   class  ApoEntity {
	
	private float x, y, startX, startY;

	

	private float width, height;

	

	private BufferedImage iBackground  ;

	

	private boolean bSelect, bVisible, bClose, bUse, bOpaque;

	
	public ApoEntity  (BufferedImage iBackground, float x, float y, float width, float height) {
		this.iBackground = iBackground;
		this.startX = x;
		this.startY = y;
		this.width = width;
		this.height = height;
	
		this.bOpaque = false;
		this.init();
	
		this.bOpaque = false;
		this.init();
	}

	

	/**
	 * sets the values ​​to their original values
	 */
	 private void  init__wrappee__WhiteMenu  () {
		this.x = this.startX;
		this.y = this.startY;
		this.bSelect = false;
		this.bVisible = true;
	}

	
	
	/**
	 * sets the values ​​to their original values
	 */
	public void init() {
		init__wrappee__WhiteMenu();
		this.velocityX = 0.0F;
		this.velocityY = 0.0F;
		this.setBUse(false);
	}

	

	/**
	 * returns the start X value of the entity that is always set
	* if init is called
	* @return returns the start X value of the entity, which is always set
	* if init is called
	 */
	public float getStartX  () {
		return this.startX;
	}

	

	/**
	 * sets the start X value to the given one
	 * @param startX: new X start value
	 */
	public void setStartX  (float startX) {
		this.startX = startX;
	}

	

	/**
	 * returns the start Y value of the entity that is always set
	* if init is called
	* @return returns the start Y value of the entity, which is always set
	* if init is called
	 */
	public float getStartY  () {
		return this.startY;
	}

	

	/**
	 * sets the start Y value to the given one
	 * @param startX: new Y start value
	 */
	public void setStartY  (float startY) {
		this.startY = startY;
	}

	

	/**
	 * Check if pixel accuracy should be checked
	 * @return TRUE, pixel accurate, not FALSE
	 */
	public boolean isBOpaque  () {
		return this.bOpaque;
	}

	

	/**
	 * sets the boolean value to whether true or false is considered when verifying 2 entities
	 * @param bOpaque
	 */
	public void setBOpaque  (boolean bOpaque) {
		this.bOpaque = bOpaque;
	}

	

	/**
	 * returns whether the entity should be displayed or not
	 *
	 * @return returns whether the entity should be displayed or not
	 */
	public boolean isBVisible  () {
		return this.bVisible;
	}

	

	/**
	 * Sets the visibility of the entity to the given value
	 *
	* @param bVisible
	 */
	public void setBVisible  (boolean bVisible) {
		this.bVisible = bVisible;
	}

	

	/**
	 * indicates whether the entity has been selected or not
	 *
	 * @return TRUE if selected otherwise FALSE
	 */
	public boolean isBSelect  () {
		return this.bSelect;
	}

	

	/**
	 * sets the boolean value if selected or not to the given one
	 *
	 * @param bSelect
	 */
	public void setBSelect  (boolean bSelect) {
		this.bSelect = bSelect;
	}

	

	/**
	 * Returns whether the JumpEntity is fixed or set by the player
	 *
	 * @return returns if the JumpEntity is fixed or set by the player
	 *	         has been
	 */
	public boolean isBClose  () {
		return this.bClose;
	}

	

	/**
	 * sets the JumpEntity whether it is fixed or not to the given value
	 *
	 * @param close
	 */
	public void setBClose  (boolean bClose) {
		this.bClose = bClose;
	}

	

	/**
	 * indicates whether an entity has already been used or not
	 *
	 * @return indicates whether an entity has already been used or not
	 */
	public boolean isBUse  () {
		return this.bUse;
	}

	

	/**
	 * sets the value for the entity, whether it was used or not
	 * Passed value
	 * 
	 * @param use
	 */
	public void setBUse  (boolean bUse) {
		this.bUse = bUse;
	}

	

	/**
	 * returns the picture
	 *
	* @return picture
	 */
	public BufferedImage getIBackground  () {
		return this.iBackground;
	}

	

	/**
	 * sets the picture to the given value
	 *
	 * @param background
	 */
	public void setIBackground  (BufferedImage background) {
		iBackground = background;
	}

	

	/**
	 * returns the width of the object
	 *
	 * @return width of the object
	 */
	public float getWidth  () {
		return this.width;
	}

	

	/**
	 * sets the width of the object to the given value
	 *
	 * @param width
	 */
	public void setWidth  (float width) {
		this.width = width;
	}

	

	/**
	 * returns the height of the object
	 *
	 * @return height of the object
	 */
	public float getHeight  () {
		return this.height;
	}

	

	/**
	 * sets the height of the object to the given value
	 *
	 * @param height
	 */
	public void setHeight  (float height) {
		this.height = height;
	}

	

	/**
	 * returns the x-value of the object (ie the left edge of the image
	*
	* @return x-value of the object
	 */
	public float getX  () {
		return this.x;
	}

	

	/**
	 * gives the central x-value of the object (ie the middle of the head, so to speak)
	 *
	 * @return x-value of the object
	 */
	public float getXMiddle  () {
		return this.x + this.width / 2;
	}

	

	/**
	 * sets the X value to the passed value
	 *
	 * @param x
	 */
	public void setX  (float x) {
		this.x = x;
	}

	

	/**
	 * returns the y-value of the object (ie the highest point on the head)
	 *
	 * @return y value of the object
	 */
	public float getY  () {
		return this.y;
	}

	

	/**
	 * sets the y-value of the object to the given one
	 *
	 * @param y
	 */
	public void setY  (float y) {
		this.y = y;
	}

	

	/**
	 * get X - compared to the Rectangl checks if the passed rgb value is transparent or not
	 * @param rgb = RGB value to be checked
	* @return TRUE if transparent otherwise FALSEe
	 */
	private boolean isOpaque  (int rgb) {

		int alpha = (rgb >> 24) & 0xff;
		// red = (rgb >> 16) & 0xff;
		// green = (rgb >> 8) & 0xff;
		// blue = (rgb ) & 0xff;

		if (alpha == 0) {
			return false;
		}

		return true;
	}

	

	/**
	 * Check if the passed values ​​are in the entity
	*
	* @param x: x-coordinate of the mouse
	* @param y: y coordinate of the mouse
	* @return: TRUE, if in there, otherwise FALSE
	 */
	public boolean intersects  (float x, float y) {
		return this.intersects(x, y, 1, 1);
	}

	

	/**
	 * returns the entity's current rectangle
	 * @return returns the entity's current rectangle
	 */
	public Rectangle2D.Float getRec  () {
		return new Rectangle2D.Float( this.getX(), this.getY(), this.getWidth(), this.getHeight() );
	}

	

	/**
	 * Checks whether the passed values ​​(which result in a rectangle) are the entity
	* to cut
	*
	* @param x: X value (top left of the rectangle)
	* @param y: Y value (top left of the rectangle)
	* @param width: width value (how wide is the rectangle)
	* @param height: height value (how tall is the rectangle)
	* @return TRUE, if in there, otherwise FALSE
	 */
	public boolean intersects  (float x, float y, float width, float height) {
		if (this.getRec().intersects(x, y, width, height)) {
			if (this.isBOpaque()) {
				if (this.getIBackground() != null) {
					Rectangle2D.Float myRec = (Rectangle2D.Float)this.getRec();
					Rectangle2D.Float cut = (Rectangle2D.Float)myRec.createIntersection(new Rectangle2D.Float(x, y, width, height));
					for (int i = (int)cut.y; i < (int)(cut.y + cut.height); i++) {
						for (int j = (int)cut.x; j < (int)(cut.x + cut.width); j++) {
							if ((i - this.getY() < this.getHeight()) &&
								(j - this.getX() < this.getWidth())) {
								if (!this.isOpaque(this.getIBackground().getRGB((int)(j - myRec.x), (int)(i - myRec.y)))) {
									return true;
								}
							}
						}
					}
					return false;
				} else {
					return true;
				}
			} else {
				return true;
			}
		}
		return false;
	}

	

	/**
	 * Method that is always called during the update method
	*
	* @param delta:
	* Time that has passed since the last call
	 */
	public void think  (int delta) {
	}

	
	
	/**
	 * Checks if 2 Entity intersect by rectangle,
	* if they really cut themselves visually
	* @param entity = other entity for intersection verification
	* @return TRUE if otherwise FALSE otherwise
	 */
	public boolean checkOpaqueColorCollisions(ApoEntity entity) {

		Rectangle2D.Float cut = (Rectangle2D.Float)this.getRec().createIntersection(entity.getRec());

		if ((cut.width < 1) || (cut.height < 1)) {
			return false;
		}

		// Rectangles related to the respective images
		Rectangle2D.Float sub_me = getSubRec(this.getRec(), cut);
		Rectangle2D.Float sub_him = getSubRec(entity.getRec(), cut);

		BufferedImage img_me = this.getIBackground().getSubimage((int) sub_me.x, (int) sub_me.y, (int) sub_me.width, (int) sub_me.height);
		BufferedImage img_him = entity.getIBackground().getSubimage((int) sub_him.x, (int) sub_him.y, (int) sub_him.width, (int) sub_him.height);

		for (int i = 0; i < img_me.getWidth(); i++) {
			for (int n = 0; n < img_him.getHeight(); n++) {

				int rgb1 = img_me.getRGB(i, n);
				int rgb2 = img_him.getRGB(i, n);

				if (isOpaque(rgb1) && isOpaque(rgb2)) {
					return true;
				}

			}
		}

		return false;
	}

	

	

	private Rectangle2D.Float getSubRec(Rectangle2D.Float source, Rectangle2D.Float part) {

		// Create rectangles
		Rectangle2D.Float sub = new Rectangle2D.Float();

		// get X - compared to the Rectangle
		if (source.x > part.x) {
			sub.x = 0;
		} else {
			sub.x = part.x - source.x;
		}

		if (source.y > part.y) {
			sub.y = 0;
		} else {
			sub.y = part.y - source.y;
		}

		sub.width = part.width;
		sub.height = part.height;

		return sub;
	}

	

	/**
	 * paints the object
	 * @param g
	 */
	public void render(Graphics2D g, int x, int y) {
		if ((this.getIBackground() != null) && (this.isBVisible())) {
			g.drawImage(this.iBackground, (int) (this.getX() + x), (int) (this.getY() + y), null);
			if (this.isBSelect()) {
				g.setColor(Color.red);
				g.drawRect((int) (this.getX() + x), (int) (this.getY() + y), (int) (this.getWidth() - 1), (int) (this.getHeight() - 1));
			}
		}
	}

	
	private float velocityX, velocityY;

	
	
	/**
	 * returns the speed in the y-direction
	 *
	 * @return returns the speed in y direction
	 */
	public float getVelocityY() {
		return this.velocityY;
	}

	

	/**
	 * resets the speed in the y-direction
	 *
	 * @param velocityX
	 */
	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}

	

	/**
	 * returns the velocity in the x-direction
	 *
	 * @return returns the speed in the x-direction
	 */
	public float getVelocityX() {
		return this.velocityX;
	}

	

	/**
	 * sets the speed back in the x-direction
	 *
	 * @param velocityX
	 */
	public void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}

	

	/**
	 * Checks if the passed entity intersects the entity
	*
	* @param entity: entity to be checked
	* @return TRUE, if in there, otherwise FALSE
	 */
	public boolean intersects(ApoEntity entity) {
		if (this.getRec().intersects( entity.getRec())) {
			if (this.isBOpaque()) {
				return this.checkOpaqueColorCollisions( entity );
			} else {
				return true;
			}
		}
		return false;
	}


}

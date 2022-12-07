package apoGame.entity; 

import java.awt.Graphics2D; 
import java.awt.image.BufferedImage; 

import org.apogames.entity.ApoEntity; 
import org.apogames.ApoGameConstants; 

public  class  ApoGamePlayer  extends ApoEntity {
	
	/** Variable that indicates how fast the player is */
	private float speed;

	
	/** Variable indicating whether the player is currently moving or not */
	private boolean isMoving;

	
	/** Variable that indicates which new direction should be taken */
	private int nextDirection = -1;

	
	
	/** Help variable indicating how far has been moving since the last standing */
	private float changeMovement;

	
	/** Help variable that indicates what X value the player had last time standing */
	private float changeMovementX;

	
	/** Auxiliary variable that indicates what Y-value the player had on the last standing */
	private float changeMovementY;

	

	private int fixedMovement = -1;

	

	public ApoGamePlayer(BufferedImage pic, float x, float y, float width, float height) {
		super(pic, x, y, width, height);
	}

	
	
	public void init() {
		super.init();
		this.setBOpaque(true);

		this.speed = ApoGameConstants.PLAYER_SPEED_MIN;
		this.isMoving = false;
		this.nextDirection = -1;
		this.changeMovement = 0;
	}

	

	public final int getFixedMovement() {
		return this.fixedMovement;
	}

	

	public void setFixedMovement(final int fixedMovement) {
		this.fixedMovement = fixedMovement;
	}

	

	/**
	 * returns whether the player is moving or not
	 * @return TRUE, player moves, FALSE player stands
	 */
	public final boolean isMoving() {
		return this.isMoving;
	}

	

	/**
	 * Sets the variable, whether a player is currently moving on the given
	 * @param isMoving: TRUE, player moves, FALSE player stands
	 */
	public void setMoving(final boolean isMoving) {
		this.isMoving = isMoving;
	}

	

	/**
	 * returns how fast the player is running every millisecond
	 * @return returns how fast the player is
	 */
	public final float getSpeed() {
		return this.speed;
	}

	

	/**
	 * Sets the speed, how fast the player is running every millisecond, to the given value
	 * @param speed: new speed of the player per millisecond while running
	 */
	public final void setSpeed(float speed) {
		if (speed < ApoGameConstants.PLAYER_SPEED_MIN) {
			speed = ApoGameConstants.PLAYER_SPEED_MIN;
		} else if (speed > ApoGameConstants.PLAYER_SPEED_MAX) {
			speed = ApoGameConstants.PLAYER_SPEED_MAX;
		}
		this.speed = speed;
	}

	
	
	/**
	 * returns which new direction to take <br />
	* Possibilities would be <br />
	* ApoGameConstants.PLAYER_DIRECTION_DOWN for down <br />
	* ApoGameConstants.PLAYER_DIRECTION_UP for high <br />
	* ApoGameConstants.PLAYER_DIRECTION_LEFT for left <br />
	* ApoGameConstants.PLAYER_DIRECTION_RIGHT for right <br />
	* @return returns which new direction to take <br />
	 */
	public final int getNextDirection() {
		return this.nextDirection;
	}

	

	/**
	 * sets the value, which new direction should be taken, on the given <br />
	* Possibilities would be <br />
	* ApoGameConstants.PLAYER_DIRECTION_DOWN for down <br />
	* ApoGameConstants.PLAYER_DIRECTION_UP for high <br />
	* ApoGameConstants.PLAYER_DIRECTION_LEFT for left <br />
	* ApoGameConstants.PLAYER_DIRECTION_RIGHT for right <br />
	* @param nextDirection: new direction
	 */
	public void setNextDirection(int nextDirection) {
		if ((nextDirection < 0) || (nextDirection > 3)) {
			return;
		}
		this.nextDirection = nextDirection;
	}

	

	/**
	 * causes the player to go in a new direction and sets his values ​​for it
	 */
	public void moveNextDirection() {
		if (this.isMoving) {
			return;
		}
		if (this.fixedMovement >= 0) {
			this.nextDirection = this.fixedMovement;
			if (this.nextDirection == ApoGameConstants.PLAYER_DIRECTION_LEFT) {
				this.nextDirection = ApoGameConstants.PLAYER_DIRECTION_UP;
			} else if (this.nextDirection == ApoGameConstants.PLAYER_DIRECTION_RIGHT) {
				this.nextDirection = ApoGameConstants.PLAYER_DIRECTION_DOWN;
			} else if ((this.nextDirection == ApoGameConstants.PLAYER_DIRECTION_STEP) ||
					(this.nextDirection == ApoGameConstants.PLAYER_DIRECTION_STEP_FINISH)) {
				this.nextDirection = -1;
			}
		}
		this.isMoving = true;
		if (this.nextDirection == ApoGameConstants.PLAYER_DIRECTION_DOWN) {
			this.setVelocityX(0);
			this.setVelocityY(1);
		} else if (this.nextDirection == ApoGameConstants.PLAYER_DIRECTION_UP) {
			this.setVelocityX(0);
			this.setVelocityY(-1);
		} else if (this.nextDirection == ApoGameConstants.PLAYER_DIRECTION_LEFT) {
			if (this.getX() <= 0) {
				this.isMoving = false;
			} else {
				this.setVelocityX(-1);
				this.setVelocityY(0);
			}
		} else if (this.nextDirection == ApoGameConstants.PLAYER_DIRECTION_RIGHT) {
			if (this.getX() + this.getWidth() >= ApoGameConstants.LEVEL_WIDTH) {
				this.isMoving = false;
			} else {
				this.setVelocityX(+1);
				this.setVelocityY(0);
			}
		} else if (this.nextDirection == ApoGameConstants.PLAYER_DIRECTION_CHANGEVISIBLE_UP) {
			super.setBVisible(!super.isBVisible());
			this.nextDirection = -1;
			this.isMoving = false;
		} else if (this.nextDirection == ApoGameConstants.PLAYER_DIRECTION_CHANGEVISIBLE_LEFT) {
			super.setBVisible(!super.isBVisible());
			this.nextDirection = -1;
			this.isMoving = false;
		} else if (this.nextDirection == ApoGameConstants.PLAYER_DIRECTION_FINISH) {
			this.isMoving = false;
		} else if (this.nextDirection == ApoGameConstants.PLAYER_DIRECTION_NO_MOVEMENT) {
			this.isMoving = false;
		}
		this.changeMovement = 0;
		this.changeMovementX = this.getX();
		this.changeMovementY = this.getY();
	}

	
	
	public void think(int delta) {
		super.think(delta);
		if (this.isMoving) {
			this.movePlayer(delta);
		} else {
			if (this.nextDirection >= 0) {
				this.moveNextDirection();
				if (this.isMoving) {
					this.movePlayer(delta);
				}
			}
		}
		this.nextDirection = -1;
	}

	
	
	/**
	 * moves the player
	 * @param delta: time that has passed since the last call
	 */
	private void movePlayer(int delta) {
		float changeX = this.speed * this.getVelocityX() * delta;
		float changeY = this.speed * this.getVelocityY() * delta;
		this.changeMovement += changeX + changeY;
		this.setX((this.getX() + changeX));
		this.setY((this.getY() + changeY));
		if (Math.abs(this.changeMovement) >= ApoGameConstants.TILE_SIZE) {
			this.isMoving = false;
			this.changeMovement = 0;
			this.setX((int)(((this.changeMovementX) / ApoGameConstants.TILE_SIZE) + this.getVelocityX()) * ApoGameConstants.TILE_SIZE);
			this.setY((int)(((this.changeMovementY) / ApoGameConstants.TILE_SIZE) + this.getVelocityY()) * ApoGameConstants.TILE_SIZE);
			this.setVelocityX(0);
			this.setVelocityY(0);
			if (this.getY() < 0) {
				this.setY(ApoGameConstants.LEVEL_HEIGHT - ApoGameConstants.TILE_SIZE);
			} else if (this.getY() + this.getHeight() > ApoGameConstants.LEVEL_HEIGHT) {
				this.setY(0);
			}
		}
	}

	
	
	public void render( Graphics2D g, int x, int y ) {
		if (super.isBVisible()) {
			if (super.getIBackground() != null) {
				g.drawImage(this.getIBackground(), (int)(this.getX() + x), (int)(this.getY() + y), null);
				if (this.getY() < 0) {
					g.drawImage(this.getIBackground(), (int)(this.getX() + x), (int)(this.getY() + y + ApoGameConstants.LEVEL_HEIGHT), null);
				} else if (this.getY() + this.getHeight() > ApoGameConstants.LEVEL_HEIGHT) {
					g.drawImage(this.getIBackground(), (int)(this.getX() + x), (int)(this.getY() + y - ApoGameConstants.LEVEL_HEIGHT), null);
				}
			}
		}
	}


}

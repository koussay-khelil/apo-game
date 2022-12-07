package apoGame.game; 

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Graphics2D; 
import java.awt.Stroke; 
import java.awt.image.BufferedImage; 

import org.apogames.entity.ApoButton; 

/**
 * OptionButton <br />
 * Class that has relatively abstract different outputs for a button either <br />
 * it is selected or not (in this case with a small hook) <br />
 * @author Dirk Aporius
 */
public  class  ApoGameOptionsButton  extends ApoButton {
	
	
	private boolean bActive;

	
		
	public ApoGameOptionsButton(BufferedImage iImage, float x, float y, float width, float height, String description, boolean bActive) {
		super(iImage, (int)x, (int)y, (int)width, (int)height, description);
		
		this.bActive = bActive;
	}

	
	
	public void init() {
		super.init();
	}

	

	public boolean isBActive() {
		return this.bActive;
	}

	

	public void setBActive(boolean bActive) {
		this.bActive = bActive;
	}

	
	
	public void render(Graphics2D g, int changeX, int changeY) {
		if (super.isBVisible()) {
			super.render(g, changeX, changeY);
			if (super.isBOver()) {
				g.setColor(new Color(254, 0, 0, 160));
				g.drawRoundRect((int)(this.getX() + 1), (int)(this.getY() + 1), (int)(this.getWidth() - 3), (int)(this.getHeight() - 3), 10, 10);
			}
			if (this.bActive) {
				g.setColor(Color.BLACK);
				Stroke stroke = g.getStroke();
				g.setStroke(new BasicStroke(4));
				g.drawLine((int)(this.getX() + 6), (int)(this.getY() + 6), (int)(this.getX() + this.getWidth() - 6), (int)(this.getY() + this.getHeight() - 6));
				g.drawLine((int)(this.getX() + this.getWidth() - 6), (int)(this.getY() + 6), (int)(this.getX() + 6), (int)(this.getY() + this.getHeight() - 6));
				g.setStroke(stroke);
			}
		}
	}


}

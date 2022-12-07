package apoGame.game; 

import java.awt.Graphics2D; 

import apoGame.ApoGamePanel; 

public abstract  class  ApoGameModel {
	
	
	private ApoGamePanel game;

	
	
	public ApoGameModel(ApoGamePanel game) {
		this.game = game;
	}

	
	
	public ApoGamePanel getGame() {
		return this.game;
	}

	
	
	public abstract void init();

	
	public abstract void keyButtonReleased(int button, char character);

	
	public abstract void mouseButtonFunction(String function);

	
	public abstract void mouseButtonReleased(int x, int y, boolean bRight);

	
	public abstract void think(int delta);

	
	public abstract void render(Graphics2D g);

	

	public boolean mouseMoved(int x, int y) {
		return false;
	}

	
	public boolean mouseDragged(int x, int y, boolean bRight) {
		return false;
	}

	
	public boolean mousePressed(int x, int y, boolean bRight) {
		return false;
	}


}

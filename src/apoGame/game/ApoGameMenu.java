package apoGame.game; 

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Font; 
import java.awt.Graphics2D; 

import apoGame.ApoGamePanel; 

import java.awt.RenderingHints; 
import java.awt.Stroke; 
import java.awt.event.KeyEvent; 
import java.awt.image.BufferedImage; 

import apoGame.game.ApoGameModel; 
import org.apogames.ApoGameConstants; 

public   class  ApoGameMenu  extends ApoGameModel {
	
	public static final String QUIT = "quit";

	
public static final String START  = "start";

	
	
	public ApoGameMenu  (ApoGamePanel game) {
		super(game);
	
		
	}

	

	@Override
	public void keyButtonReleased(int button, char character) {
		if (button == KeyEvent.VK_ESCAPE) {
			if (!ApoGameConstants.B_APPLET) {
				System.exit(0);
			}
		}
	}

	

	@Override
	public void mouseButtonReleased(int x, int y, boolean bRight) {
	}

	
	
	private BufferedImage iBackground;

	
	
	@Override
	public void init() {
		this.getGame().setShouldRepaint(false);
		if (this.iBackground == null) {
			this.iBackground = new BufferedImage(ApoGameConstants.GAME_WIDTH, ApoGameConstants.GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = this.iBackground.createGraphics();
			
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			this.getGame().renderBackground(g);
			
			Stroke stroke = g.getStroke();
			g.setStroke(new BasicStroke(5));
			
			g.setColor(Color.GRAY);
			g.drawRoundRect(5, 5, this.iBackground.getWidth() - 12, this.iBackground.getHeight() - 12, 20, 20);
			
			int change = 50;
			g.drawLine(7, this.iBackground.getHeight()/2 - change, this.iBackground.getWidth() - 2 * 5, this.iBackground.getHeight()/2 - change);
			g.drawLine(7, this.iBackground.getHeight()/2 + change, this.iBackground.getWidth() - 2 * 5, this.iBackground.getHeight()/2 + change);
			
			g.setColor(Color.BLACK);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 70));
			String s = ApoGameConstants.PROGRAM_NAME;
			int w = g.getFontMetrics().stringWidth(s);
			int h = g.getFontMetrics().getHeight() - 2 * g.getFontMetrics().getDescent();
			g.drawString(s, this.iBackground.getWidth()/2 - w/2, ApoGameConstants.GAME_HEIGHT/2 + h/2);
			
			g.setStroke(stroke);
			
			g.dispose();
		}
	}

	
	
	@Override
	public void render(Graphics2D g) {
		if (this.iBackground != null) {
			g.drawImage(this.iBackground, 0, 0, null);
		}
	}

	

	
	 private void  mouseButtonFunction__wrappee__WhiteMenu(String function) {
		if (function.equals(ApoGameMenu.QUIT)) {
			System.exit(0);
		} 
	}

	
	
	
	 private void  mouseButtonFunction__wrappee__Credits(String function) {
		mouseButtonFunction__wrappee__WhiteMenu(function);
		if (function.equals(ApoGameMenu.CREDITS)) {
			this.getGame().setCredits();
		}
	}

	
	
	
	 private void  mouseButtonFunction__wrappee__Options(String function) {
		mouseButtonFunction__wrappee__Credits(function);
		if (function.equals(ApoGameMenu.OPTIONS)) {
			this.getGame().setOptions();
		}
	}

	
	
	
	 private void  mouseButtonFunction__wrappee__NotSoSimpleLevel(String function) {
		mouseButtonFunction__wrappee__Options(function);
		if (function.equals(ApoGameMenu.START)) {
			this.getGame().setLevelChooser();
		}
	}

	
	
	
	 private void  mouseButtonFunction__wrappee__Editor(String function) {
		mouseButtonFunction__wrappee__NotSoSimpleLevel(function);
		if (function.equals(ApoGameMenu.EDITOR)) {
			this.getGame().setEditor(false);
		}
	}

	
	
	@Override
	public void mouseButtonFunction(String function) {
		mouseButtonFunction__wrappee__Editor(function);
		if (function.equals(ApoGameMenu.EDITOR)) {
			this.getGame().setEditor(false);
		}
	}

	

	@Override
	public void think(int delta) {
		
	}

	
	public static final String CREDITS = "credits";

	
	public static final String OPTIONS = "options";

	
	public static final String EDITOR = "editor";


}

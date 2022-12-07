package apoGame; 

import java.awt.Color; 
import java.awt.Graphics2D; 

import org.apogames.ApoGameConstants; 
import org.apogames.ApoScreen; 

import apoGame.ApoGameComponent; 
import apoGame.game.ApoGameMenu; 
import apoGame.game.ApoGameButtons; 
import java.util.ArrayList; 
// import org.apogames.sound.ApoMP3SoundHandler;
import org.apogames.entity.ApoButton; 
import java.awt.event.KeyEvent; 

import java.net.MalformedURLException; 
import java.net.URL; 
import java.util.Arrays; 
import apoGame.game.ApoGameModel; 

import apoGame.game.ApoGameCredits; 
import apoGame.game.ApoGameOptions; 
import org.apogames.help.ApoHelp; 

import apoGame.game.level.ApoGameLevel; 
import apoGame.game.ApoGameGame; 

import apoGame.game.ApoGameLevelChooser; 

import apoGame.game.ApoGameEditor; 

import org.apogames.sound.ApoMP3SoundHandler; 
import org.apogames.sound.ApoMP3Sound; 

public   class  ApoGamePanel  extends ApoGameComponent {
		
	private ApoGameMenu menu;

	
	private ApoGameButtons buttons  ;

	
	
	public ApoGamePanel(ApoScreen screen) {
		super(screen);
	}

	
	
	
	
	
	@Override
	public void setButtonFunction(String function) {
		if (this.model != null) {
			this.model.mouseButtonFunction(function);
			if (!function.equals(ApoGameMenu.QUIT)) {
				//this.playSound(ApoGamePanel.SOUND_BUTTON, 100);
			}
		}
	}

	
	
	@Override
	public void render(Graphics2D g) {
		long t = System.nanoTime();
		if (this.model != null) {
			this.model.render(g);
		}
		super.renderButtons(g);
		this.renderFPS(g);
		this.renderTime = (int)(System.nanoTime() - t);
	}

	
	
	public void setButtonVisible(ArrayList<String> bVisibile) {
		for(ApoButton button : this.getButtons().values()) {
			if(button.isBVisible()) {
				button.setBVisible(false);
			}
		}
		for(String button : bVisibile) {
			this.getButtons().get(button).setBVisible(true);
		}
		if (ApoGameConstants.B_APPLET) {
			//this.getButtons().get(0).setBVisible(false);
		}
	}

	
	
	private ApoGameModel model;

	
	/** Help variables to find out how long it took to think and draw */
	private long thinkTime, renderTime;

	
	private boolean bSoundEffects  = false;

	
	
	 private void  init__wrappee__WhiteMenu  () {
		super.init();
		super.setShowFPS(false);
		
		if (this.buttons == null) {
			this.buttons = new ApoGameButtons(this);
			this.buttons.init();
		}
		if (this.menu == null) {
			this.menu = new ApoGameMenu(this);
		}
		
		
		
		
		this.setMenu();
	}

	
	
	 private void  init__wrappee__Credits  () {
		init__wrappee__WhiteMenu();
		if (this.credits == null) {
			this.credits = new ApoGameCredits(this);
		}
	}

	
	
	 private void  init__wrappee__Options  () {
		init__wrappee__Credits();
		if (this.options == null) {
			this.options = new ApoGameOptions(this);
		}
	}

	
	
	 private void  init__wrappee__World  () {
		init__wrappee__Options();
		if (this.game == null) {
			this.game = new ApoGameGame(this);
		}
	}

	
	
	 private void  init__wrappee__LevelChooser  () {
		init__wrappee__World();
		if (this.levelChooser == null) {
			this.levelChooser = new ApoGameLevelChooser(this);
			if (ApoGameConstants.B_APPLET) {
				String load;
				try {
					load = ApoHelp.loadData(new URL(ApoGameConstants.PROGRAM_URL), ApoGameConstants.COOKIE_NAME);
					int level = Integer.valueOf(load);
					this.levelChooser.setMaxLevel(level);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	
	 private void  init__wrappee__Editor  () {
		init__wrappee__LevelChooser();
		if (this.editor == null) {
			this.editor = new ApoGameEditor(this);
		}
	}

	
	
	public void init() {
		init__wrappee__Editor();
		if (this.sounds == null) {
			this.sounds = new ApoMP3SoundHandler();
			this.sounds.loadSound(ApoGamePanel.SOUND_BACKGROUND, "/sounds/background.mp3");
			this.sounds.loadSound(ApoGamePanel.SOUND_POP, "/sounds/pop.mp3");
			this.sounds.loadSound(ApoGamePanel.SOUND_BUTTON, "/sounds/button.mp3");
			this.sounds.loadSound(ApoGamePanel.SOUND_LOOSE, "/sounds/loose.mp3");
			this.sounds.loadSound(ApoGamePanel.SOUND_WIN, "/sounds/win.mp3");

			this.sounds.playSound(ApoGamePanel.SOUND_BACKGROUND, true, 90);
			
			this.bSoundEffects = true;
		}
	}

	
	
	public void setMenu() {
		this.model = this.menu;
		this.initMenuButtons();
		this.setButtonVisible(ApoGameConstants.BUTTON_MENU);
		this.model.init();
		this.render();
	}

	

	 private void  initMenuButtons__wrappee__WhiteMenu  () {
		ApoGameConstants.BUTTON_MENU.add(ApoGameMenu.START);
		ApoGameConstants.BUTTON_MENU.add(ApoGameMenu.QUIT);
	}

	
	
	 private void  initMenuButtons__wrappee__Credits  () {
		initMenuButtons__wrappee__WhiteMenu();
		ApoGameConstants.BUTTON_MENU.add(ApoGameMenu.CREDITS);
	}

	
	
	 private void  initMenuButtons__wrappee__Options  () {
		initMenuButtons__wrappee__Credits();
		ApoGameConstants.BUTTON_MENU.add(ApoGameMenu.OPTIONS);
	}

	
	
	private void initMenuButtons() {
		initMenuButtons__wrappee__Options();
		ApoGameConstants.BUTTON_MENU.add(ApoGameMenu.EDITOR);
	}

	

	@Override
	public void mouseReleased(int x, int y, boolean left) {
		super.mouseReleased(x, y, left);
		if (this.model != null) {
			this.model.mouseButtonReleased(x, y, !left);
		}
	}

	
	
	public void mousePressed(int x, int y, boolean left) {
		super.mousePressed(x, y, left);
		if (this.model != null) {
			this.model.mousePressed(x, y, !left);
		}
	}

	
	
	public void mouseMoved(int x, int y) {
		super.mouseMoved(x, y);
		if (this.model != null) {
			this.model.mouseMoved(x, y);
		}
	}

	
	
	public void mouseDragged(int x, int y, boolean left) {
		super.mouseDragged(x, y, left);
		if (this.model != null) {
			this.model.mouseDragged(x, y, !left);
		}
	}

	

	
	public void renderBackground(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, ApoGameConstants.GAME_WIDTH, ApoGameConstants.GAME_HEIGHT);
	}

	
	
	public void think(long delta) {
		long t = System.nanoTime();
		if (this.model != null) {
			this.model.think((int)delta);
		}
		this.thinkTime = (int) (System.nanoTime() - t);
	}

	
	
	@Override
	public void keyReleased(int keyCode, char keyChar) {
		if (keyCode == KeyEvent.VK_F) {
			super.setShowFPS(!super.isShowFPS());
			this.render();
		}
		if (this.model != null) {
			this.model.keyButtonReleased(keyCode, keyChar);
		}
	}

	

	/**
	 * renders the ad for the FPS and think time
	 * @param g: the graphics object
	 */
	private void renderFPS(Graphics2D g) {
		if (super.isShowFPS()) {
			g.setColor(Color.red);
			g.setFont(ApoGameConstants.FONT_FPS);
			g.drawString("think time: " + this.thinkTime + " ns", 5, ApoGameConstants.GAME_HEIGHT - 45);
			g.drawString("think time: " + (this.thinkTime / 1000000) + " ms",5, ApoGameConstants.GAME_HEIGHT - 35);

			g.drawString("draw time: " + this.renderTime + " ns", 5, ApoGameConstants.GAME_HEIGHT - 25);
			g.drawString("draw time: " + (this.renderTime / 1000000) + " ms", 5, ApoGameConstants.GAME_HEIGHT - 15);
			g.drawString("FPS: " + super.getFPS(), 5, ApoGameConstants.GAME_HEIGHT - 5);
		}
	}

	
	private ApoGameCredits credits;

	
	
	public void setCredits() {
		this.model = this.credits;
		
		this.setButtonVisible(ApoGameConstants.BUTTON_CREDITS);
		
		this.model.init();
		
		this.render();
	}

	
	private ApoGameOptions options;

	
	
	public void setOptions() {
		this.model = this.options;
		this.initOptionsMenu();
		this.setButtonVisible(ApoGameConstants.BUTTON_OPTIONS);
		
		this.model.init();
		
		this.render();
	}

	
	
	private void initOptionsMenu() {
		ApoGameConstants.BUTTON_OPTIONS.add(ApoGameOptions.MENU);
		ApoGameConstants.BUTTON_OPTIONS.add(ApoGameOptions.MUSIC);
		ApoGameConstants.BUTTON_OPTIONS.add(ApoGameOptions.SOUND);
	}

	
	private ApoGameGame game;

	
	
	public void setGame(int level) {
		this.model = this.game;
		this.setButtonVisible(ApoGameConstants.BUTTON_GAME);
		
		this.model.init();
		this.game.init(level);
		this.render();
	}

	
	
	public int getMaxLevels() {
		return this.levelChooser.getMaxLevel();
	}

	
	
	public void setGameWithLevel(final ApoGameLevel level) {
		this.setGame(-1);
		this.game.setLevelFromEditor(level);
	}

	
	
	public void setMaxLevel(final int level) {
		if (this.levelChooser.setMaxLevel(level)) {
			if (ApoGameConstants.B_APPLET) {
				try {
					ApoHelp.saveData(new URL(ApoGameConstants.PROGRAM_URL), ApoGameConstants.COOKIE_NAME, String.valueOf(level));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	
	

	
	public String getSolution() {
		return this.game.getSolution();
	}

	
	private ApoGameLevelChooser levelChooser;

	
	
	public void setLevelChooser() {
		this.model = this.levelChooser;
		this.setButtonVisible(ApoGameConstants.BUTTON_LEVELCHOOSER);
		this.model.init();
		this.render();
	}

	
	public void loadHighscore() {
		//this.game.getUserLevels().loadHighscore();
	}

	
	private ApoGameEditor editor;

	
	
	public void setEditor(boolean bUpload) {
		this.model = this.editor;
		this.initEditorButtons();
		
		
		this.model.init();
		this.editor.setbUpload(bUpload);
		
		this.render();
	}

	
	
	private void initEditorButtons() {
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	
	public static final String SOUND_POP = "pop";

	
	public static final String SOUND_BUTTON = "button";

	
	public static final String SOUND_BACKGROUND = "background";

	
	public static final String SOUND_LOOSE = "loose";

	
	public static final String SOUND_WIN = "win";

	
	
	private ApoMP3SoundHandler sounds;

	
	
	public void playSound(String sound, int volumen) {
		this.playSound(sound, false, volumen);
	}

	
	
	public void playSound(String sound, boolean bLoop, int volumen) {
		if (sound.equals(ApoGamePanel.SOUND_BACKGROUND)) {
			this.sounds.playSound(sound, bLoop, volumen);
		} else if (this.bSoundEffects) {
			this.sounds.playSound(sound, bLoop, volumen);
		}
	}

	
	
	public void stopSound(String sound) {
		this.sounds.stopSound(sound);
	}

	
	
	public void setSoundEffects(boolean soundEffects) {
		this.bSoundEffects = soundEffects;
	}

	
	
	public boolean isSoundEffects() {
		return this.bSoundEffects;
	}


}

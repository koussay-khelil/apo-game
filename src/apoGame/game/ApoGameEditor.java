package apoGame.game; 

import java.awt.AlphaComposite; 
import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Graphics2D; 

import org.apogames.ApoGameConstants; 

import apoGame.ApoGamePanel; 

import java.awt.Point; 
import java.awt.RenderingHints; 
import java.awt.Stroke; 
import java.awt.event.KeyEvent; 
import java.awt.image.BufferedImage; 
import java.util.ArrayList; 
import java.util.Arrays; 

import org.apogames.entity.ApoNewTextField; 
import org.apogames.help.ApoHelp; 
import org.apogames.help.ApoHighscore; 
import org.apogames.entity.ApoButton; 

import apoGame.ApoGameImages; 
import apoGame.entity.ApoGameString; 
import apoGame.game.level.ApoGameLevel; 

public   class  ApoGameEditor  extends ApoGameModel {
	
	
	public ApoGameEditor  (ApoGamePanel game) {
		super(game);
	
		this.init();
	}

	
	
	@Override
	public void init() {
		this.bUpload = false;
		if (this.textFieldUsername == null) {
			int width = 250;
			int height = 30;
			int x = ApoGameConstants.LEVEL_WIDTH/2 - 40;
			int y = ApoGameConstants.GAME_HEIGHT - height - 20;
			this.textFieldUsername = new ApoNewTextField(x, y, width, height, ApoGameConstants.FONT_INSTRUCTIONS);
			this.textFieldUsername.setMaxLength(15);
			this.textFieldUsername.setCurString("Mister X");
		}
		if (this.textFieldLevelname == null) {
			int width = 250;
			int height = 30;
			int x = ApoGameConstants.LEVEL_WIDTH/2 - 40;
			int y = 15;
			this.textFieldLevelname = new ApoNewTextField(x, y, width, height, ApoGameConstants.FONT_INSTRUCTIONS);
			this.textFieldLevelname.setMaxLength(15);
			this.textFieldLevelname.setCurString("My Level");
		}
		if (this.textFieldDescription == null) {
			int width = 300;
			int height = 30;
			int x = ApoGameConstants.LEVEL_WIDTH/2 - 70;
			int y = ApoGameConstants.GAME_HEIGHT/2 - height - 80;
			this.textFieldDescription = new ApoNewTextField(x, y, width, height, ApoGameConstants.FONT_DESCRIPTIONS);
			this.textFieldDescription.setMaxLength(30);
			this.textFieldDescription.setCurString("User-generated level");
		}
		if (this.level == null) {
			byte[][][] array = new byte[1][3][10];
			array[0][1][0] = ApoGameConstants.LEVEL_PLAYER;
			array[0][1][9] = ApoGameConstants.LEVEL_FINISH;
			this.level = new ApoGameLevel(array, new String[2], "");
		}
		if (this.curMousePosition == null) {
			this.curMousePosition = new Point(-1, -1);
		}
		this.strings = new ArrayList<ApoGameString>();
		this.curTime = 0;
		this.curMouseLayer = -1;
		this.level.restart();
		this.makeBackground();
		if (this.selection <= 0) {
			this.changeSelection(ApoGameConstants.LEVEL_FIXED);
		}
	}

	
public static final int MAX_TIME_RENDER = 100;

	
	
	public static final String MENU_STRING = "menu";

	
	public static final String MENU = "credits_menu";

	
	public static final String LEFT_LAYER = "credits_left_layer";

	
	public static final String RIGHT_LAYER = "credits_right_layer";

	
	public static final String UPLOAD = "credits_upload";

	
	public static final String TEST = "credits_test";

	
	public static final String FIXED = "credits_fixed";

	
	public static final String PLAYER = "credits_player";

	
	public static final String FINISH = "credits_finish";

	
	public static final String LEFT = "credits_left";

	
	public static final String RIGHT = "credits_right";

	
	public static final String UP = "credits_up";

	
	public static final String DOWN = "credits_down";

	
	public static final String VISIBLE_TRUE = "credits_true";

	
	public static final String VISIBLE_FALSE = "credits_false";

	
	public static final String STEP = "credits_step";

	
	public static final String STEP_FINISH = "credits_step_finish";

	

	private BufferedImage iBackground;

	
	
	private ApoGameLevel level;

	
	
	private ArrayList<ApoGameString> strings;

	
	
	/** currently selected tile */
	private byte selection = -1;

	
	/** Image of the currently selected tile */
	private BufferedImage iSelection, iRealSelection;

	
	/** current mouse position in level data form */
	private Point curMousePosition;

	
	
	private int curMouseLayer;

	
	
	private boolean bUpload;

	
	
	private int curTime;

	
	
	private ApoNewTextField textFieldUsername;

	
	private ApoNewTextField textFieldLevelname;

	
	private ApoNewTextField textFieldDescription;

	
	
	private boolean checkKeyReleased(final ApoNewTextField textField, int button, char character) {
		if ((textField != null) && (textField.isBVisible()) && (textField.isBSelect())) {
			boolean bShift = false;
			if (this.getGame().getKeyboard().isPressed(KeyEvent.VK_CONTROL)) {
				if (button == KeyEvent.VK_V) {
					String s = ApoHelp.getClipboardContents();
					if (s != null) {
						for (int i = 0; i < s.length(); i++) {
							char chara = s.charAt(i);
							textField.addCharacter(button, chara);
						}
						bShift = true;
						this.getGame().render();
					}
				} else if (button == KeyEvent.VK_C) {
					String s = textField.getSelectedString();
					if (s != null) {
						ApoHelp.setClipboardContents(s);
					}
					bShift = true;
					this.getGame().render();
				} else if (button == KeyEvent.VK_X) {
					String s = textField.getSelectedString();
					if (s != null) {
						ApoHelp.setClipboardContents(s);
						textField.deleteSelectedString();
					}
					bShift = true;
					this.getGame().render();
				}
			} else if (this.getGame().getKeyboard().isPressed(KeyEvent.VK_SHIFT)) {
				if (button == KeyEvent.VK_LEFT) {
					textField.nextSelectedPosition(textField.getPosition() - 1);
					bShift = true;
					this.getGame().render();
				} else if (button == KeyEvent.VK_RIGHT) {
					textField.nextSelectedPosition(textField.getPosition() + 1);
					bShift = true;
					this.getGame().render();
				}
			}
			if (!bShift) {
				textField.addCharacter(button, character);
				this.getGame().render();
			}
			return true;
		}
		return false;
	}

	

	public void setbUpload(boolean bUpload) {
		this.bUpload = bUpload;
		this.getGame().getButtons().get(ApoGameEditor.UPLOAD).setBVisible(bUpload);
		if (this.bUpload) {
			this.strings.add(new ApoGameString(ApoGameConstants.GAME_WIDTH/2 - 60, ApoGameConstants.GAME_HEIGHT/2 - 10, 200, "Upload possible", true));
			this.strings.add(new ApoGameString(ApoGameConstants.GAME_WIDTH/2 - 100, ApoGameConstants.GAME_HEIGHT/2 + 35, 200, "Click the upload button", true));
		}
	}

	

	private void makeBackground() {
		this.iBackground = new BufferedImage(ApoGameConstants.GAME_WIDTH, ApoGameConstants.GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = this.iBackground.createGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.getGame().renderBackground(g);
		
		Stroke stroke = g.getStroke();
		g.setStroke(new BasicStroke(5));
		
		g.setColor(Color.DARK_GRAY);
		int startX = 5;
		int startY = 5;
		int width = (int)(65) * 7 + 5;
		int height = (int)(65) * 7 + 5;
		g.drawRoundRect(startX, startY, width, height, 20, 20);
		
		this.level.renderBackground(g, 10, 0);
		
		g.setStroke(stroke);
		
		g.dispose();
	}

	
	
	/**
	 * changes the selection of the selected tile
	 * @param newSelection: newly selected tile
	 */
	private void changeSelection(final int newSelection) {
		this.selection = (byte)newSelection;
		if (this.selection < 1) {
			this.selection = ApoGameConstants.LEVEL_STEP_FINISH;
		} else if (this.selection > ApoGameConstants.LEVEL_STEP_FINISH) {
			this.selection = 1;
		}
		BufferedImage iNewSelection = null;
		if (this.selection == ApoGameConstants.LEVEL_FIXED) {
			iNewSelection = ApoGameImages.ORIGINAL_FIXED;
		} else if (this.selection == ApoGameConstants.LEVEL_UP) {
			iNewSelection = ApoGameImages.ORIGINAL_UP;
		} else if (this.selection == ApoGameConstants.LEVEL_DOWN) {
			iNewSelection = ApoGameImages.ORIGINAL_DOWN;
		} else if (this.selection == ApoGameConstants.LEVEL_LEFT) {
			iNewSelection = ApoGameImages.ORIGINAL_LEFT;
		} else if (this.selection == ApoGameConstants.LEVEL_RIGHT) {
			iNewSelection = ApoGameImages.ORIGINAL_RIGHT;
		} else if (this.selection == ApoGameConstants.LEVEL_FINISH) {
			iNewSelection = ApoGameImages.ORIGINAL_FINISH;
		} else if (this.selection == ApoGameConstants.LEVEL_PLAYER) {
			iNewSelection = ApoGameImages.ORIGINAL_PLAYER;
		} else if (this.selection == ApoGameConstants.LEVEL_VISIBLE_TRUE) {
			iNewSelection = ApoGameImages.ORIGINAL_VISIBLE_TRUE;
		} else if (this.selection == ApoGameConstants.LEVEL_VISIBLE_FALSE) {
			iNewSelection = ApoGameImages.ORIGINAL_VISIBLE_FALSE;
		} else if (this.selection == ApoGameConstants.LEVEL_STEP) {
			iNewSelection = ApoGameImages.ORIGINAL_STEP;
		} else if (this.selection == ApoGameConstants.LEVEL_STEP_FINISH) {
			iNewSelection = ApoGameImages.ORIGINAL_STEP_FINISH;
		}
		if (iNewSelection != null) {
			this.iRealSelection = new BufferedImage(iNewSelection.getWidth(), iNewSelection.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
			Graphics2D g = this.iRealSelection.createGraphics();

			g.drawImage(iNewSelection, 0, 0, null);
			
			g.dispose();
			
			this.iSelection = new BufferedImage(iNewSelection.getWidth(), iNewSelection.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
			g = this.iSelection.createGraphics();
			
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			g.drawImage(iNewSelection, 0, 0, null);
			
			g.dispose();
		}
		this.getGame().render();
	}

	

	@Override
	public void keyButtonReleased(int button, char character) {
		if (button == KeyEvent.VK_ESCAPE) {
			this.getGame().setMenu();
		} else {
			if ((!this.checkKeyReleased(this.textFieldLevelname, button, character)) &&
				(!this.checkKeyReleased(this.textFieldUsername, button, character)) &&
				(!this.checkKeyReleased(this.textFieldDescription, button, character))) {
				if ((button == KeyEvent.VK_T) || (button == KeyEvent.VK_ENTER)) {
					this.getGame().setGameWithLevel(this.level);
				}
			}
		}
	}

	

	@Override
	public void mouseButtonFunction(String function) {
		if (function.equals(ApoGameEditor.MENU)) {
			this.getGame().setMenu();
		} else if (function.equals(ApoGameEditor.TEST)) {
			this.getGame().setGameWithLevel(this.level);
		} else if (function.equals(ApoGameEditor.UPLOAD)) {
			ApoHighscore.getInstance().save(this.level.getLevel(), this.textFieldLevelname.getText(), this.textFieldUsername.getText(), this.textFieldDescription.getText(), this.getGame().getSolution());
			this.getGame().loadHighscore();
			this.strings.add(new ApoGameString(ApoGameConstants.LEVEL_WIDTH/2 - 100 + 10, ApoGameConstants.GAME_HEIGHT/2 - 100, 200, "Upload successful", true));
		} else if (function.equals(ApoGameEditor.LEFT_LAYER)) {
			if (this.level.getLayer() > 1) {
				this.textFieldDescription.setY(ApoGameConstants.GAME_HEIGHT/2 - this.textFieldDescription.getHeight() - 80);
				byte[][][] array = new byte[1][3][10];
				array[0] = this.level.getLevel()[0];
				this.level.setLevel(array);
				this.level.restart();
				this.makeBackground();
			}
		} else if (function.equals(ApoGameEditor.RIGHT_LAYER)) {
			if (this.level.getLayer() <= 1) {
				this.textFieldDescription.setY(ApoGameConstants.GAME_HEIGHT/2 - this.textFieldDescription.getHeight()/2);
				byte[][][] array = new byte[2][3][10];
				array[0] = this.level.getLevel()[0];
				array[1][1][0] = ApoGameConstants.LEVEL_PLAYER;
				array[1][1][9] = ApoGameConstants.LEVEL_FINISH;
				this.level.setLevel(array);
				this.level.restart();
				this.makeBackground();
			}
		} else if (function.equals(ApoGameEditor.LEFT)) {
			this.changeSelection(ApoGameConstants.LEVEL_LEFT);
		} else if (function.equals(ApoGameEditor.RIGHT)) {
			this.changeSelection(ApoGameConstants.LEVEL_RIGHT);
		} else if (function.equals(ApoGameEditor.UP)) {
			this.changeSelection(ApoGameConstants.LEVEL_UP);
		} else if (function.equals(ApoGameEditor.DOWN)) {
			this.changeSelection(ApoGameConstants.LEVEL_DOWN);
		} else if (function.equals(ApoGameEditor.VISIBLE_TRUE)) {
			this.changeSelection(ApoGameConstants.LEVEL_VISIBLE_TRUE);
		} else if (function.equals(ApoGameEditor.VISIBLE_FALSE)) {
			this.changeSelection(ApoGameConstants.LEVEL_VISIBLE_FALSE);
		} else if (function.equals(ApoGameEditor.STEP)) {
			this.changeSelection(ApoGameConstants.LEVEL_STEP);
		} else if (function.equals(ApoGameEditor.STEP_FINISH)) {
			this.changeSelection(ApoGameConstants.LEVEL_STEP_FINISH);
		} else if (function.equals(ApoGameEditor.PLAYER)) {
			this.changeSelection(ApoGameConstants.LEVEL_PLAYER);
		} else if (function.equals(ApoGameEditor.FINISH)) {
			this.changeSelection(ApoGameConstants.LEVEL_FINISH);
		} else if (function.equals(ApoGameEditor.FIXED)) {
			this.changeSelection(ApoGameConstants.LEVEL_FIXED);
		}
		this.textFieldLevelname.setBSelect(false);
		this.textFieldUsername.setBSelect(false);
		this.textFieldDescription.setBSelect(false);
	}

	
	
	@Override
	public void mouseButtonReleased(int x, int y, boolean bRight) {
		this.mouseMoved(x, y);
		this.setSelection(bRight);
		
		if ((this.textFieldLevelname != null) && (this.textFieldLevelname.isBVisible())) {
			this.textFieldLevelname.mouseReleased(x, y);
			if (this.textFieldLevelname.intersects(x, y)) {
				this.textFieldLevelname.setBSelect(true);
			} else {
				this.textFieldLevelname.setBSelect(false);
			}
		}
		if ((this.textFieldUsername != null) && (this.textFieldUsername.isBVisible())) {
			this.textFieldUsername.mouseReleased(x, y);
			if (this.textFieldUsername.intersects(x, y)) {
				this.textFieldUsername.setBSelect(true);
			} else {
				this.textFieldUsername.setBSelect(false);
			}
		}
		if ((this.textFieldDescription != null) && (this.textFieldDescription.isBVisible())) {
			this.textFieldDescription.mouseReleased(x, y);
			if (this.textFieldDescription.intersects(x, y)) {
				this.textFieldDescription.setBSelect(true);
			} else {
				this.textFieldDescription.setBSelect(false);
			}
		}
	}

	
	
	public boolean mouseMoved(int x, int y) {
		super.mouseMoved(x, y);
		if ((this.textFieldLevelname != null) && (this.textFieldLevelname.isBVisible())) {
			if (this.textFieldLevelname.getMove(x, y)) {
				return true;
			}
		}
		if ((this.textFieldUsername != null) && (this.textFieldUsername.isBVisible())) {
			if (this.textFieldUsername.getMove(x, y)) {
				return true;
			}
		}
		if ((this.textFieldDescription != null) && (this.textFieldDescription.isBVisible())) {
			if (this.textFieldDescription.getMove(x, y)) {
				return true;
			}
		}
		
		int changeX = 10;
		int changeY = this.level.getChangeY();
		if ((changeX < x) && (changeX + ApoGameConstants.LEVEL_WIDTH > x) &&
			(changeY < y) && (changeY + ApoGameConstants.LEVEL_HEIGHT > y)) {
			int pointX = (int)(x - changeX)/ApoGameConstants.TILE_SIZE;
			int pointY = (int)(y - changeY)/ApoGameConstants.TILE_SIZE;
			if ((pointX >= 0) && (pointX + 1 <= this.level.getLevel()[0][0].length) &&
				(pointY >= 0) && (pointY + 1 <= this.level.getLevel()[0].length)) {
				if ((this.curMousePosition.x != pointX) || (this.curMousePosition.y != pointY)) {
					this.curMousePosition = new Point(pointX, pointY);
					this.curMouseLayer = 0;
					this.getGame().render();
				}
			} else {
				this.curMousePosition = new Point(-1, -1);
				this.curMouseLayer = -1;
				this.getGame().render();
			}
		} else if (this.level.getLayer() == 2) {
			changeY += 5 * ApoGameConstants.TILE_SIZE;
			if ((changeX < x) && (changeX + ApoGameConstants.LEVEL_WIDTH > x) &&
				(changeY < y) && (changeY + ApoGameConstants.LEVEL_HEIGHT > y)) {
				int pointX = (int)(x - changeX)/ApoGameConstants.TILE_SIZE;
				int pointY = (int)(y - changeY)/ApoGameConstants.TILE_SIZE;
				if ((pointX >= 0) && (pointX + 1 <= this.level.getLevel()[0][0].length) &&
					(pointY >= 0) && (pointY + 1 <= this.level.getLevel()[0].length)) {
					if ((this.curMousePosition.x != pointX) || (this.curMousePosition.y != pointY)) {
						this.curMousePosition = new Point(pointX, pointY);
						this.curMouseLayer = 1;
						this.getGame().render();
					}
				} else {
					this.curMousePosition = new Point(-1, -1);
					this.curMouseLayer = -1;
					this.getGame().render();
				}
			} else {
				this.curMousePosition = new Point(-1, -1);
				this.curMouseLayer = -1;
				this.getGame().render();
			}
		} else {
			this.curMousePosition = new Point(-1, -1);
			this.curMouseLayer = -1;
			this.getGame().render();
		}
		return false;
	}

	
	
	private void makeAndRender() {
		this.setbUpload(false);
		this.level.restart();
		this.makeBackground();
		this.getGame().render();
	}

	
	
	public boolean mouseDragged(int x, int y, boolean bRight) {
		super.mouseDragged(x, y, bRight);
		this.mouseMoved(x, y);
		this.setSelection(bRight);
		return true;
	}

	
	
	public boolean mousePressed(int x, int y, boolean bRight) {
		if ((this.textFieldLevelname != null) && (this.textFieldLevelname.isBVisible())) {
			if (this.textFieldLevelname.mousePressed(x, y)) {
				return true;
			}
		}
		if ((this.textFieldUsername != null) && (this.textFieldUsername.isBVisible())) {
			if (this.textFieldUsername.mousePressed(x, y)) {
				return true;
			}
		}
		if ((this.textFieldDescription != null) && (this.textFieldDescription.isBVisible())) {
			if (this.textFieldDescription.mousePressed(x, y)) {
				return true;
			}
		}
		return false;
	}

	
	
	/**
	 * Called when the mouse is released or the mouse is dragged <br />
	* she then sets the level tiles when needed. <br />
	* @param bRight: TRUE, right mouse button pressed, otherwise FALSE
	 */
	private void setSelection(final boolean bRight) {
		int x = this.curMousePosition.x;
		int y = this.curMousePosition.y;
		
		// if the mouse is above the level at all
		if ((this.curMouseLayer >= 0) && (x >= 0) && (y >= 0) &&
			(x < this.level.getLevel()[this.curMouseLayer][0].length) && (y < this.level.getLevel()[this.curMouseLayer].length)) {
			if (bRight) {
				if ((this.level.getLevel()[this.curMouseLayer][y][x] != ApoGameConstants.LEVEL_FREE) &&
					(this.level.getLevel()[this.curMouseLayer][y][x] != ApoGameConstants.LEVEL_PLAYER) &&
					(this.level.getLevel()[this.curMouseLayer][y][x] != ApoGameConstants.LEVEL_FINISH)) {
					this.level.getLevel()[this.curMouseLayer][y][x] = ApoGameConstants.LEVEL_FREE;
					this.makeAndRender();
				}
			} else if (this.level.getLevel()[this.curMouseLayer][y][x] != this.selection) {
				if ((this.selection == ApoGameConstants.LEVEL_FINISH) || (this.selection == ApoGameConstants.LEVEL_PLAYER)) {
					for (int sY = 0; sY < this.level.getLevel()[this.curMouseLayer].length; sY++) {
						for (int sX = 0; sX < this.level.getLevel()[this.curMouseLayer][0].length; sX++) {
							if (this.level.getLevel()[this.curMouseLayer][sY][sX] == this.selection) {
								this.level.getLevel()[this.curMouseLayer][sY][sX] = ApoGameConstants.LEVEL_FREE;
							}
						}
					}
					this.level.getLevel()[this.curMouseLayer][y][x] = this.selection;
					this.makeAndRender();
				} else {
					if ((this.level.getLevel()[this.curMouseLayer][y][x] != ApoGameConstants.LEVEL_FINISH) && 
						(this.level.getLevel()[this.curMouseLayer][y][x] != ApoGameConstants.LEVEL_PLAYER)) {
						this.level.getLevel()[this.curMouseLayer][y][x] = this.selection;
						this.makeAndRender();
					}
				}
			}
		}
	}

	

	@Override
	public void think(int delta) {
		if (this.textFieldLevelname != null) {
			this.textFieldLevelname.think(delta);
		}
		if (this.textFieldUsername != null) {
			this.textFieldUsername.think(delta);
		}
		if (this.textFieldDescription != null) {
			this.textFieldDescription.think(delta);
		}
		this.curTime += delta;
		if (this.curTime > ApoGameEditor.MAX_TIME_RENDER) {
			this.curTime -= ApoGameEditor.MAX_TIME_RENDER;
			this.getGame().render();
		}
		for (int i = this.strings.size() - 1; i >= 0; i--) {
			this.strings.get(i).think(delta);
			if (!this.strings.get(i).isBVisible()) {
				this.strings.remove(i);
			}
		}
	}

	
	
	@Override
	public void render(Graphics2D g) {
		if (this.iBackground != null) {
			g.drawImage(this.iBackground, 0, 0, null);
			
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);		
			
			this.level.render(g, 10, 0, true);
			
			if (this.selection >= 0) {
				Object[] objectArray = this.getGame().getButtons().values().toArray();
				ApoButton[] apoArray = Arrays.copyOf(objectArray, objectArray.length, ApoButton[].class);
				g.setColor(Color.RED);
				Stroke stroke = g.getStroke();
				g.setStroke(new BasicStroke(3));
				g.drawOval((int)(apoArray[this.selection + 10].getX() + 5), (int)(apoArray[this.selection + 10].getY() + 5), (int)(apoArray[this.selection + 10].getWidth() - 10), (int)(apoArray[this.selection + 10].getHeight() - 10));
				g.setStroke(stroke);
			}
			g.setColor(Color.BLACK);
			g.setFont(ApoGameConstants.FONT_INSTRUCTIONS);
			String s = String.valueOf("Layer");
			int w = g.getFontMetrics().stringWidth(s);
			final int x = (ApoGameConstants.GAME_WIDTH - 10 - ApoGameConstants.LEVEL_WIDTH)/2 + 10 + ApoGameConstants.LEVEL_WIDTH;
			g.drawString(s, x - w/2, 40);
			
			s = String.valueOf(this.level.getLayer());
			w = g.getFontMetrics().stringWidth(s);
			g.drawString(s, x - w/2, 70);
			
			if (this.curMouseLayer >= 0) {
				int changeY = this.level.getChangeY();
				if (this.curMouseLayer >= 1) {
					changeY += (curMouseLayer) * ApoGameConstants.TILE_SIZE * 5;
				}
				g.drawImage(this.iSelection, this.curMousePosition.x * ApoGameConstants.TILE_SIZE + 10, changeY + this.curMousePosition.y * ApoGameConstants.TILE_SIZE, null);
			}
			
			if (this.textFieldLevelname.isBVisible()) {
				this.textFieldLevelname.render(g, 0, 0);
				
				g.setFont(ApoGameConstants.FONT_INSTRUCTIONS);
				s = "Levelname: ";
				w = g.getFontMetrics().stringWidth(s);
				int h = g.getFontMetrics().getHeight() - 2 * g.getFontMetrics().getDescent();
				g.drawString(s, (int)(this.textFieldLevelname.getX() - w), (int)(this.textFieldLevelname.getY() + this.textFieldLevelname.getHeight()/2 + h/2));
			}
			if (this.textFieldUsername.isBVisible()) {
				this.textFieldUsername.render(g, 0, 0);
				
				g.setFont(ApoGameConstants.FONT_INSTRUCTIONS);
				s = "Username: ";
				w = g.getFontMetrics().stringWidth(s);
				int h = g.getFontMetrics().getHeight() - 2 * g.getFontMetrics().getDescent();
				g.drawString(s, (int)(this.textFieldUsername.getX() - w), (int)(this.textFieldUsername.getY() + this.textFieldUsername.getHeight()/2 + h/2));
			}
			if (this.textFieldDescription.isBVisible()) {
				this.textFieldDescription.render(g, 0, 0);
				
				g.setFont(ApoGameConstants.FONT_DESCRIPTIONS);
				s = "Description: ";
				w = g.getFontMetrics().stringWidth(s);
				int h = g.getFontMetrics().getHeight() - 2 * g.getFontMetrics().getDescent();
				g.drawString(s, (int)(this.textFieldDescription.getX() - w), (int)(this.textFieldDescription.getY() + this.textFieldDescription.getHeight()/2 + h/2));
			}
			
			try {
				for (int i = this.strings.size() - 1; i >= 0; i--) {
					this.strings.get(i).render(g, 0, 0);
				}
			} catch (Exception ex) {	
			}
		}
	}


}

package org.apogames; 

import java.awt.Font; 
import java.awt.FontFormatException; 
import java.util.ArrayList; 
import java.io.FileNotFoundException; 
import java.io.IOException; 
import apoGame.game.ApoGameMenu; 
import java.util.Arrays; 

import apoGame.game.ApoGameCredits; 

import apoGame.game.ApoGameOptions; 

import apoGame.game.ApoGameGame; 

import apoGame.game.ApoGameLevelChooser; 

import apoGame.game.ApoGameEditor; 

public   class  ApoGameConstants {
	
	
	
	
	public static final String PROGRAM_NAME  = "NotSoSimple";

	
	public static final int GAME_WIDTH = 640;

	
	public static final int GAME_HEIGHT  = 480;

	
	public static final double VERSION  = 0.21;

	
	
	public static boolean BUFFER_STRATEGY = false;

	
	/** Returns whether the application is an application or an applet */
	public static boolean B_APPLET = false;

	
	/** returns if the application is online */
	public static boolean B_ONLINE = true;

	
	
	public static int FPS_RENDER = 60;

	
	public static int FPS_THINK = 100;

	
	public static boolean FPS  = false;

	 
	
	public static Font font = null;

	
	public static Font ORIGINAL_FONT;

	
	public static final Font FONT_IMAGE = new Font(Font.SANS_SERIF, Font.BOLD, 25);

	
	public static final Font FONT_INSTRUCTIONS = new Font(Font.SANS_SERIF, Font.PLAIN, 25);

	
	
	static {
		try {
			ApoGameConstants.ORIGINAL_FONT = Font.createFont(Font.TRUETYPE_FONT, ApoGameConstants.class.getResourceAsStream("/font/reprise.ttf") );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public static ArrayList<String> BUTTON_MENU = new ArrayList<String>();

	
	public static final Font FONT_FPS = new Font(Font.SANS_SERIF, Font.PLAIN, 10);

	

	public static final ArrayList<String> BUTTON_CREDITS = new ArrayList<String>(
			Arrays.asList(ApoGameCredits.MENU)
		);

	
	public static ArrayList<String> BUTTON_OPTIONS = new ArrayList<String>();

	
	/** indicates the direction "down" or direction south */
	public static final int PLAYER_DIRECTION_DOWN = 0;

	
	/** indicates the direction "left" or west */
	public static final int PLAYER_DIRECTION_LEFT = 1;

	
	/** indicates the direction "right" or east */
	public static final int PLAYER_DIRECTION_RIGHT = 2;

	
	/** indicates the direction "high" or north */
	public static final int PLAYER_DIRECTION_UP = 3;

	
	/** indicates the direction "high" or north */
	public static final int PLAYER_DIRECTION_CHANGEVISIBLE_UP = 4;

	
	/** indicates the direction "high" or north */
	public static final int PLAYER_DIRECTION_CHANGEVISIBLE_LEFT = 5;

	
	/** indicates that it is the finish */
	public static final int PLAYER_DIRECTION_FINISH = 6;

	
	/** indicates that it is the finish */
	public static final int PLAYER_DIRECTION_STEP = 7;

	
	/** indicates that it is the finish */
	public static final int PLAYER_DIRECTION_STEP_FINISH = 8;

	
	/** indicates the direction "NULL" */
	public static final int PLAYER_DIRECTION_NO_MOVEMENT = 9;

	
	
	/** Constant indicating what the minimum speed of the player is per millisecond */
	public static final float PLAYER_SPEED_MIN = 0.15f;

	
	/** Constant that indicates what the maximum speed of the player is per millisecond */
	public static final float PLAYER_SPEED_MAX = 0.15f;

	
	/** Level player Field */
	public static final int LEVEL_PLAYER = 2;

	
	/** Level false Field */
	public static final int LEVEL_FINISH = 9;

	
	/** Level fixed FIeld */
	public static final int LEVEL_FIXED = 1;

	
	/** Level free field */
	public static final int LEVEL_FREE = 0;

	
	/** Level up movement Field */
	public static final int LEVEL_UP = 3;

	
	/** Level down movement Field */
	public static final int LEVEL_DOWN = 4;

	
	/** Level left movement Field */
	public static final int LEVEL_LEFT = 5;

	
	/** Level right movement Field */
	public static final int LEVEL_RIGHT = 6;

	
	/** Level true Field */
	public static final int LEVEL_VISIBLE_TRUE = 7;

	
	/** Level false Field */
	public static final int LEVEL_VISIBLE_FALSE = 8;

	
		/** Level false Field */
	public static final int LEVEL_STEP = 10;

	
	/** Level false Field */
	public static final int LEVEL_STEP_FINISH = 11;

	
	public static final int TILE_SIZE = 45;

	
	public static final int LEVEL_WIDTH = 10 * TILE_SIZE;

	
	public static final int LEVEL_HEIGHT = 3 * TILE_SIZE;

	
	public static final String PROGRAM_URL = "http://www.apo-games.de/apoNotSoSimple/";

	
	public static final String COOKIE_NAME = "apoNotSoSimple_level";

		
	
	public static final Font FONT_LEVELNAME = new Font(Font.SANS_SERIF, Font.BOLD, 35);

	
	public static final Font FONT_DESCRIPTIONS = new Font(Font.SANS_SERIF, Font.PLAIN, 20);

	
	
	public static final ArrayList<String> BUTTON_GAME = new ArrayList<String>(Arrays.asList(ApoGameGame.MENU));

	
	public static final ArrayList<String> BUTTON_LEVELCHOOSER = new ArrayList<String>(
			Arrays.asList(ApoGameLevelChooser.MENU));

	
	public final static String HIGHSCORE_GETPHP = "http://www.apo-games.de/apoNotSoSimple/get_highscore.php";

	
	public final static String HIGHSCORE_SAVEPHP = "http://www.apo-games.de/apoNotSoSimple/save_highscore.php";

	
	
	
	


}

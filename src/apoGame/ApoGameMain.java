package apoGame; 

import org.apogames.ApoDisplayConfiguration; 
import org.apogames.ApoLibraryGame; 
import org.apogames.ApoScreen; 
import org.apogames.ApoSubGame; 
import org.apogames.ApoGameConstants; 


public  class  ApoGameMain {
	

	/**
	 * Starting point of the game
	 * the properties are loaded and then the game is started
	 * @param args : Passed string array
	 */
    public static void main(String[] args) {
    	new ApoGameMain();
    }

	
    
    /**
     * Constructor in which the game is started
     */
    public ApoGameMain() {
        String title = "ApoGame";
        double version = 1;
        if(!(ApoGameConstants.PROGRAM_NAME == null))
        	title = ApoGameConstants.PROGRAM_NAME;
        if(!(ApoGameConstants.VERSION == 0))
        	version = ApoGameConstants.VERSION;
        
    	ApoDisplayConfiguration displayConfiguration = new ApoDisplayConfiguration(ApoGameConstants.GAME_WIDTH, ApoGameConstants.GAME_HEIGHT, 16, true, false);
        
        final ApoScreen screen = new ApoScreen(title + " Version: "+ version, displayConfiguration);
        final ApoSubGame subGame = new ApoGamePanel(screen);
        final ApoLibraryGame game = new ApoLibraryGame(subGame);
        game.getScreen().init();
        subGame.init();
        game.start();
    }


}

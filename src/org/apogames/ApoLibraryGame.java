package org.apogames; 

import org.apogames.ApoScreen; 
import org.apogames.ApoSubGame; 


/**
 * Game start class
 * @author Dirk Aporius
 *
 */
public final  class  ApoLibraryGame  extends Thread {
	

    /** SubGame ID for the game */
    public static final int GAME = 0;

	

    /** Next SubGame is set with this variable */
    private int nextGameID = ApoLibraryGame.GAME;

	
    /** Screen object of the game */
    private ApoScreen screen = null;

	
    
    private final ApoSubGame game;

	

    /**
     * Constructor
     * @param title : Title of the game
     * @param displayConfiguration : Display configuration
     */
    public ApoLibraryGame(final ApoSubGame game) {
        super();
        this.game = game;
        this.screen = this.game.getScreen();//new ApoScreen(title, displayConfiguration);
    }

	

    /** Game starts here */
    @Override
    public void run() {
        while (this.nextGameID != -1) {
            try {
                ApoSubGame subGame = selectGame(this.nextGameID);
                if (subGame == null) {
                    this.nextGameID = -1;
                } else {
                    subGame.start();
                    subGame.join();
                    this.nextGameID = subGame.getNextID();
                }
            } catch (InterruptedException e) {
            }
        }
    }

	
    
    /**
     * returns the actual screen of the game
     * @return returns the actual screen of the game
     */
    public final ApoScreen getScreen() {
    	return this.screen;
    }

	

	/**
	 * With this method you can switch back and forth between the individual subgames
	 * @param gameID : GameID for the respective SubGame object
	 * @return the new SubGame object
	 * @throws InterruptedException
	 */
    private ApoSubGame selectGame(int gameID) throws InterruptedException {
        switch (gameID) {
            case ApoLibraryGame.GAME:	return this.game;
            default:					return null;
        }
    }


}

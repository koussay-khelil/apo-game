package org.apogames; 

/**
 * Auxiliary class for storing the display configuration
 * @author Dirk Aporius
 *
 */
public  class  ApoDisplayConfiguration {
	
	/** Width of the frame */
    private final int width;

	
    /** High of the frame */
    private final int height;

	
    /** Color depth of the frame */
    private final int depth;

	
    /** boolean variable indicating whether the frame should be started in the window or in fullscreen */
    private final boolean windowed;

	
    /** boolean Variable indicating if the frame is a widescreen */
    private boolean widescreen;

	
    /** boolean variable indicating whether it is an applet or not */
    private final boolean applet;

	
    
    public ApoDisplayConfiguration(int width, int height, int depth, boolean windowed, boolean applet) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.windowed = windowed;
        this.widescreen = false;
        this.applet = applet;
        
        double w = width;
        double h = height;
        if (Double.compare(w / h, 16.0 / 9.0) == 0) {
            this.widescreen = true;
        }
    }

	

    /**
     * returns whether it is an applet or not
     * @return returns whether it is an applet or not
     */
    public final boolean isApplet() {
		return this.applet;
	}

	

    /**
     * returns whether the frame is widescreen or not
     * @return returns whether the frame is widescreen or not
     */
	public final boolean isWidescreen() {
		return this.widescreen;
	}

	

	/**
	 * returns the width of the frame
	 * @return returns the width of the frame
	 */
	public final int getWidth() {
        return this.width;
    }

	

	/**
	 * returns the height of the frame
	 * @return returns the height of the frame
	 */
    public final int getHeight() {
        return this.height;
    }

	

    /**
     * returns the color depth of the frame
     * @return returns the color depth of the frame
     */
    public final int getDepth() {
        return this.depth;
    }

	

    /**
     * returns whether the frame is started in the window or in fullscreen
     * @return TRUE, frame is started in the window, FALSE frame is started in fullscreen
     */
    public final boolean isWindowed() {
        return this.windowed;
    }


}

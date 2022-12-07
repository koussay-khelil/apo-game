package org.apogames.image; 

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Font; 
import java.awt.Graphics2D; 
import java.awt.GraphicsEnvironment; 
import java.awt.RenderingHints; 
import java.awt.Stroke; 
import java.awt.image.BufferedImage; 

public   class  ApoImageFromValue {
	
private ApoImage image  ;

	
	
	public ApoImageFromValue() {
		super();
		
		this.image = new ApoImage();
	}

	
	
	/**
	 * returns a three-part image back with passed width, height, colors, and text
	* @param width = width of the image
	* @param height = height of the image
	* @param text = text on the button
	* @param background = background color
	* @param font = text color
	* @param border = border color
	* @param over = color on mouse over it
	* @param pressed = color pressed at mouse
	* @return returns a three-part image back with passed width, height, colors, and text
	 */
	public BufferedImage getButtonImage  ( int width, int height, String text, Color background, Color font, Color border, Color over, Color pressed, Font writeFont, int round ) {
		BufferedImage iButton = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(width,height,BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = (Graphics2D)iButton.getGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Font fontOkButton = writeFont;
		g.setFont(fontOkButton);
		int h = g.getFontMetrics().getHeight() - 2 * g.getFontMetrics().getDescent();
		
		for ( int i = 0; i < 3; i++ ) {
			g.setColor( background );
			g.fillRoundRect( (width)/3 * i, 0, (width)/3 - 1, height - 1, round, round );
			
			int w = g.getFontMetrics().stringWidth( text );
			int x = iButton.getWidth()/3;

			g.setColor(font);
			g.drawString( text, i*x + x/2 - w/2, iButton.getHeight()/2 + h/2);

			Stroke stroke = g.getStroke();
			g.setStroke(new BasicStroke(2));
			if (i == 1) {
				g.setColor(over);
			} else if (i == 2) {
				g.setColor(pressed);
			} else {
				g.setColor(border);				
			}
			g.drawRoundRect((width)/3 * i + 1, 0 + 1, (width)/3 - 3, height - 3, round, round);
			g.setStroke(stroke);
		}
		
		g.dispose();
		return iButton;
	}


}

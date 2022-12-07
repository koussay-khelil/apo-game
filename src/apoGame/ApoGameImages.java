package apoGame; 

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Font; 
import java.awt.Graphics2D; 
import java.awt.GraphicsEnvironment; 
import java.awt.Polygon; 
import java.awt.RenderingHints; 
import java.awt.Shape; 
import java.awt.Stroke; 
import java.awt.geom.Rectangle2D; 
import java.awt.image.BufferedImage; 
import org.apogames.image.ApoImageFromValue; 

import org.apogames.ApoGameConstants; 

public   class  ApoGameImages {
	
	/** Auxiliary object for loading ready-made images (for example, for buttons) */
	private ApoImageFromValue image;

	
	public ApoGameImages() {
		this.image = new ApoImageFromValue();
	}

	
	
	public BufferedImage getButtonImage(int width, int height, String text, Color c, Color c2, Color c3, Color mouseOver, Color mousePressed, Font font, int round) {
		return this.image.getButtonImage(width, height, text, c, c2, c3, mouseOver, mousePressed, font, round);
	}

	
	
	
	public BufferedImage getButtonImageSimple(int width, int height, String text, Color background, Color font, Color border, Color over, Color pressed, boolean bLeft, boolean bRight, Font writeFont, int round) {
		BufferedImage iButton = this.getButtonImage(width, height, text, background, font, border, over, pressed, writeFont, round);
		Graphics2D g = (Graphics2D)iButton.getGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.BLACK);
		Stroke stroke = g.getStroke();
		Shape shape = g.getClip();
		
		for (int i = 0; i < 3; i++) {
			int startX = 6 + width * i / 3;
			int startY = 6;
			int myHeight = height - 2 * startY;
			g.setStroke(new BasicStroke(7));
			if (bLeft) {
				g.setClip(new Rectangle2D.Float(startX, startY, myHeight/2, myHeight));
				g.drawOval(startX + 3, startY + 3, myHeight - 7, myHeight - 7);
			}
			if (bRight) {
				g.setClip(new Rectangle2D.Float(width * (i + 1) / 3 - startY - myHeight/2, startY, myHeight/2, myHeight));
				g.drawOval(width * (i + 1) / 3 - startY - myHeight, startY + 3, myHeight - 7, myHeight - 7);
			}
			g.setStroke(stroke);
			g.setClip(shape);
		}
		
		g.dispose();
		return iButton;
	}

	
	public static BufferedImage ORIGINAL_DRIVE;

	
	public static BufferedImage ORIGINAL_FIXED;

	
	public static BufferedImage ORIGINAL_UP;

	
	public static BufferedImage ORIGINAL_DOWN;

	
	public static BufferedImage ORIGINAL_LEFT;

	
	public static BufferedImage ORIGINAL_RIGHT;

	
	public static BufferedImage ORIGINAL_PLAYER;

	
	public static BufferedImage ORIGINAL_FINISH;

	
	public static BufferedImage ORIGINAL_VISIBLE_TRUE;

	
	public static BufferedImage ORIGINAL_VISIBLE_FALSE;

	
	public static BufferedImage ORIGINAL_STEP;

	
	public static BufferedImage ORIGINAL_STEP_FINISH;

	
	
	static {
		ApoGameImages.ORIGINAL_DRIVE = ApoGameImages.getOriginalImageDrive(Color.BLACK);
		ApoGameImages.ORIGINAL_FIXED = ApoGameImages.getImageSimple(Color.DARK_GRAY, 0, false, null, Color.BLACK);
		ApoGameImages.ORIGINAL_UP = ApoGameImages.getImageSimpleRaute(Color.YELLOW, ApoGameConstants.PLAYER_DIRECTION_UP, true, null, Color.BLACK);
		ApoGameImages.ORIGINAL_DOWN = ApoGameImages.getImageSimpleRaute(Color.RED, ApoGameConstants.PLAYER_DIRECTION_DOWN, true, null, Color.BLACK);
		ApoGameImages.ORIGINAL_LEFT = ApoGameImages.getImageSimpleTriangle(Color.MAGENTA, ApoGameConstants.PLAYER_DIRECTION_LEFT, true, null, Color.BLACK, false);
		ApoGameImages.ORIGINAL_RIGHT = ApoGameImages.getImageSimpleTriangle(Color.PINK, ApoGameConstants.PLAYER_DIRECTION_RIGHT, true, null, Color.BLACK, true);
		ApoGameImages.ORIGINAL_PLAYER = ApoGameImages.getImageSimple(Color.WHITE, ApoGameConstants.PLAYER_DIRECTION_CHANGEVISIBLE_UP, true, null, Color.BLACK);
		ApoGameImages.ORIGINAL_FINISH = ApoGameImages.getImageSimple(Color.GREEN, 0, false, "X", Color.BLACK);
		ApoGameImages.ORIGINAL_VISIBLE_TRUE = ApoGameImages.getImageSimpleRec(new Color(160, 120, 255), 0, false, "+", Color.BLACK);
		ApoGameImages.ORIGINAL_VISIBLE_FALSE = ApoGameImages.getImageSimpleRec(Color.ORANGE, 0, false, "-", Color.BLACK);
		ApoGameImages.ORIGINAL_STEP = ApoGameImages.getImageSimple(Color.GREEN.darker().darker(), ApoGameConstants.PLAYER_DIRECTION_CHANGEVISIBLE_LEFT, true, null, Color.BLACK);
		ApoGameImages.ORIGINAL_STEP_FINISH = ApoGameImages.getImageSimple(Color.BLUE, ApoGameConstants.PLAYER_DIRECTION_FINISH, true, null, Color.BLACK);
	}

	
	
	public final static BufferedImage getImageSimple(Color color, int direction, boolean arrows, String value, Color textColor) {
		BufferedImage image = new BufferedImage(ApoGameConstants.TILE_SIZE, ApoGameConstants.TILE_SIZE, BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(ApoGameImages.ORIGINAL_DRIVE, 0, 0, null);
		g.setColor(color);
		g.fillOval(9, 9, ApoGameConstants.TILE_SIZE - 18, ApoGameConstants.TILE_SIZE - 18);
		
		ApoGameImages.getImageSimple(image, g, direction, arrows, value, textColor);
		
		g.dispose();
		
		return image;
	}

	
	public final static void getImageSimple(BufferedImage image, Graphics2D g, int direction, boolean arrows, String value, Color textColor) {
		if (arrows) {
			if (direction == ApoGameConstants.PLAYER_DIRECTION_FINISH) {
				g.setColor(textColor);
				int width = 15;
				int height = 15;
				g.fillOval(image.getWidth()/2 - width/2, image.getHeight()/2 - height/2, width, height);
			} else if (direction == ApoGameConstants.PLAYER_DIRECTION_CHANGEVISIBLE_LEFT) {
				g.setColor(textColor);
				int width = 15;
				int height = 15;
				g.setStroke(new BasicStroke(5));
				g.drawOval(image.getWidth()/2 - width/2, image.getHeight()/2 - height/2, width, height);
			} else if (direction == ApoGameConstants.PLAYER_DIRECTION_CHANGEVISIBLE_UP) {
				g.setColor(textColor);
				g.fillRoundRect(image.getWidth()/2 - 7, 12, 4, image.getHeight() - 30, 10, 10);
				g.fillRoundRect(image.getWidth()/2 + 4, 12, 4, image.getHeight() - 30, 10, 10);
			} else {
				Polygon poly = new Polygon();
				g.setColor(textColor);
				if (direction == ApoGameConstants.PLAYER_DIRECTION_UP) {
					poly.addPoint(image.getWidth()/2 - 2, image.getHeight() - 14);
					poly.addPoint(image.getWidth()/2 + 3, image.getHeight() - 14);
					poly.addPoint(image.getWidth()/2 + 3, image.getHeight()/2 + 1);
					poly.addPoint(image.getWidth() - 15, image.getHeight()/2 + 1);
					poly.addPoint(image.getWidth()/2, image.getHeight()/2 - 9);
					poly.addPoint(15, image.getHeight()/2 + 1);
					poly.addPoint(image.getWidth()/2 - 2, image.getHeight()/2 + 1);
				} else if (direction == ApoGameConstants.PLAYER_DIRECTION_DOWN) {
					poly.addPoint(image.getWidth()/2 - 2, 15);
					poly.addPoint(image.getWidth()/2 + 3, 15);
					poly.addPoint(image.getWidth()/2 + 3, image.getHeight()/2);
					poly.addPoint(image.getWidth() - 15, image.getHeight()/2);
					poly.addPoint(image.getWidth()/2, image.getHeight()/2 + 10);
					poly.addPoint(15, image.getHeight()/2);
					poly.addPoint(image.getWidth()/2 - 2, image.getHeight()/2);
				} else if (direction == ApoGameConstants.PLAYER_DIRECTION_LEFT) {
					poly.addPoint(image.getWidth()/2 - 2, image.getHeight() - 15);
					poly.addPoint(image.getWidth()/2 + 3, image.getHeight() - 15);
					poly.addPoint(image.getWidth()/2 + 3, image.getHeight()/2);
					poly.addPoint(image.getWidth() - 15, image.getHeight()/2);
					poly.addPoint(image.getWidth()/2, image.getHeight()/2 - 10);
					poly.addPoint(15, image.getHeight()/2);
					poly.addPoint(image.getWidth()/2 - 2, image.getHeight()/2);
				} else if (direction == ApoGameConstants.PLAYER_DIRECTION_RIGHT) {
					poly.addPoint(image.getWidth()/2 - 2, 15);
					poly.addPoint(image.getWidth()/2 + 3, 15);
					poly.addPoint(image.getWidth()/2 + 3, image.getHeight()/2);
					poly.addPoint(image.getWidth() - 15, image.getHeight()/2);
					poly.addPoint(image.getWidth()/2, image.getHeight()/2 + 10);
					poly.addPoint(15, image.getHeight()/2);
					poly.addPoint(image.getWidth()/2 - 2, image.getHeight()/2);
				}
				if (poly.npoints > 0) {
					g.fillPolygon(poly);
				}
			}
		} else if (value != null) {
			g.setColor(textColor);
			g.setFont(ApoGameConstants.FONT_IMAGE);
			int w = g.getFontMetrics().stringWidth(value);
			int h = g.getFontMetrics().getHeight() - 2 * g.getFontMetrics().getDescent();
			g.drawString(value, image.getWidth()/2 - w/2, image.getHeight()/2 + h/2);
		}
	}

	
	public final static BufferedImage getImageSimpleRaute(Color color, int direction, boolean arrows, String value, Color textColor) {
		BufferedImage image = new BufferedImage(ApoGameConstants.TILE_SIZE, ApoGameConstants.TILE_SIZE, BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Polygon p = new Polygon();

		p.addPoint(8, image.getHeight()/2);
		p.addPoint(image.getWidth()/2, 8);
		
		p.addPoint(image.getWidth() - 8, image.getHeight()/2);
		p.addPoint(image.getWidth()/2, image.getHeight() - 8);

		Stroke stroke = g.getStroke();

		g.setStroke(stroke);
		g.setColor(color);
		g.fillPolygon(p);

//		g.setStroke(new BasicStroke(2));
		g.setColor(Color.BLACK);
		g.drawPolygon(p);
		
		ApoGameImages.getImageSimple(image, g, direction, arrows, value, textColor);
		
		g.dispose();
		
		return image;
	}

	

	
	public final static BufferedImage getImageSimpleTriangle(Color color, int direction, boolean arrows, String value, Color textColor, boolean bDown) {
		BufferedImage image = new BufferedImage(ApoGameConstants.TILE_SIZE, ApoGameConstants.TILE_SIZE, BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Polygon p = new Polygon();

		if (!bDown) {
			p.addPoint(5, image.getHeight() - 10);
			p.addPoint(image.getWidth()/2, 5);
			p.addPoint(image.getWidth() - 5, image.getHeight() - 10);
		} else {
			p.addPoint(5, 10);
			p.addPoint(image.getWidth()/2, image.getHeight() - 5);
			p.addPoint(image.getWidth() - 5, 10);			
		}
		Stroke stroke = g.getStroke();

		g.setStroke(stroke);
		g.setColor(color);
		g.fillPolygon(p);
		
//		g.setStroke(new BasicStroke(2));
		g.setColor(Color.BLACK);
		g.drawPolygon(p);

		
		ApoGameImages.getImageSimple(image, g, direction, arrows, value, textColor);
		
		g.dispose();
		
		return image;
	}

	
	
	public final static BufferedImage getImageSimpleRec(Color color, int direction, boolean arrows, String value, Color textColor) {
		BufferedImage image = new BufferedImage(ApoGameConstants.TILE_SIZE, ApoGameConstants.TILE_SIZE, BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Stroke stroke = g.getStroke();

		g.setStroke(stroke);
		g.setColor(color);
		g.fillRoundRect(11, 11, image.getWidth() - 22, image.getHeight() - 22, 5, 5);
		

//		g.setStroke(new BasicStroke(2));
		g.setColor(Color.BLACK);
		g.drawRoundRect(11, 11, image.getWidth() - 22, image.getHeight() - 22, 5, 5);
		
		ApoGameImages.getImageSimple(image, g, direction, arrows, value, textColor);
		
		g.dispose();
		
		return image;
	}

	
	
	
	private final static BufferedImage getOriginalImageDrive(Color color) {
		BufferedImage image = new BufferedImage(ApoGameConstants.TILE_SIZE, ApoGameConstants.TILE_SIZE, BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLACK);
		g.fillOval(8, 8, ApoGameConstants.TILE_SIZE - 16, ApoGameConstants.TILE_SIZE - 16);
		g.dispose();
		
		return image;
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
	public BufferedImage getButtonImageLevelChooser(int width, int height, String text, Color background, Color font, Color border, Color over, Color pressed, Font writeFont, int round) {
		BufferedImage iButton = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(width,height,BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = (Graphics2D)iButton.getGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Font fontOkButton = writeFont;
		g.setFont(fontOkButton);
		int h = g.getFontMetrics().getHeight();
		
		for ( int i = 0; i < 3; i++ ) {
			g.setColor( background );
			g.fillRoundRect((width)/3 * i + 1, 1, (width)/3 - 2, height - 1, round, round);
			
			g.setFont( fontOkButton );
			int w = g.getFontMetrics().stringWidth( text );
			int x = iButton.getWidth()/3;
			
			if ( i == 1 ) {
				g.setColor(over);
				g.fillRoundRect(i*x + x/2 - w/2, iButton.getHeight()/2 - h/4 + 1, w, h/2 - 2, 20, 20);
			} else if ( i == 2 ) {
				g.setColor(pressed);
				g.fillRoundRect(i*x + x/2 - w/2, iButton.getHeight()/2 - h/4 + 1, w, h/2 - 2, 20, 20);
			}
			
			g.setColor( font );
			g.drawString( text, i*x + x/2 - w/2, iButton.getHeight()/2 + h/3 );
			
			Stroke stroke = g.getStroke();
			g.setStroke(new BasicStroke(3));
			g.setColor( border );
			g.drawRoundRect( (width)/3 * i + 1, 1, (width)/3 - 3, height - 3, round, round );
			g.setStroke(stroke);
		}
		
		g.dispose();
		return iButton;
	}

	
	
	public final static BufferedImage getImageSimpleEditor(BufferedImage iSimpleImage, Color over, Color pressed) {
		BufferedImage iImage = new BufferedImage(iSimpleImage.getWidth() * 3, iSimpleImage.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = iImage.createGraphics();
		
		int width = iSimpleImage.getWidth();
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setStroke(new BasicStroke(5));
		
		for (int i = 0; i < 3; i++) {
			g.drawImage(iSimpleImage, i * width, 0, null);
			if (i > 0) {
				if (i == 1) {
					g.setColor(over);
				} else if (i == 2) {
					g.setColor(pressed);
				}
				g.drawOval(8 + i * width, 8, ApoGameConstants.TILE_SIZE - 16, ApoGameConstants.TILE_SIZE - 16);
			}
		}
		
		g.dispose();
		return iImage;
	}


}

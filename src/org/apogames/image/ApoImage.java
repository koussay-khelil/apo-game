package org.apogames.image; 

import java.awt.Component; 
import java.awt.MediaTracker; 
import java.awt.image.BufferedImage; 
import java.awt.image.ImageObserver; 
import java.io.File; 
import java.io.IOException; 
import java.net.MalformedURLException; 
import java.net.URL; 

import javax.imageio.ImageIO; 

import org.apogames.ApoGameConstants; 

/**
 * Class that launches a BufferedImage
 * @author Dirk Aporius
 *
 */
public  class  ApoImage  extends Component  implements ImageObserver {
	
	
	private static final long serialVersionUID = 1L;

	
	
	private Component component;

	
	
	/**
	 * Constructor
	 */
	public ApoImage() {
		this.component = this;
	}

	
	
	/**
	 * deletes the image from a given string
	* @param BildName = where is the picture
	* @param bLoad
	* @return the picture
	 */
	private BufferedImage getImage(String pic, boolean bLoad)	{
		BufferedImage icon = null;
		
		BufferedImage img		= null;
		
		if ( !bLoad ) {
			try	{
				img = ImageIO.read( this.getClass().getClassLoader().getResource(pic));
				//result = this.graphicsConfiguration.createCompatibleImage(img.getWidth(),img.getHeight(), BufferedImage.TYPE_INT_ARGB);
				//result.createGraphics().drawImage(img,0,0,null);
				icon	= img;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (NullPointerException ex) {
				return  null;
			}
		} else {
			try {
				if ( ApoGameConstants.B_APPLET ) {
					img = ImageIO.read(new URL(pic));
				} else {
					img = ImageIO.read(new File(pic));
				}
				//result = this.graphicsConfiguration.createCompatibleImage(img.getWidth(),img.getHeight(), BufferedImage.TYPE_INT_ARGB);
				//result.createGraphics().drawImage(img,0,0,null);
				icon	= img;
			} catch (MalformedURLException e) {
				System.out.println("Konnte das Bild "+pic+" nicht laden");
				return null;
			} catch (IOException e) {
				System.out.println("Konnte das Bild "+pic+" nicht laden");
				return null;
			}
		}
		return (icon);
	}


}

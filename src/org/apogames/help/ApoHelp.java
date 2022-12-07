package org.apogames.help; 

import java.awt.Graphics2D; 
import java.awt.GraphicsEnvironment; 
import java.awt.Point; 
import java.awt.RenderingHints; 
import java.awt.Toolkit; 
import java.awt.datatransfer.Clipboard; 
import java.awt.datatransfer.DataFlavor; 
import java.awt.datatransfer.StringSelection; 
import java.awt.datatransfer.Transferable; 
import java.awt.datatransfer.UnsupportedFlavorException; 
import java.awt.geom.AffineTransform; 
import java.awt.geom.Point2D; 
import java.awt.image.BufferedImage; 
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.OutputStream; 
import java.math.BigDecimal; 
import java.math.BigInteger; 
import java.net.HttpURLConnection; 
import java.net.MalformedURLException; 
import java.net.URL; 
import java.net.URLConnection; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.util.ArrayList; 

import org.apogames.entity.ApoEntity; 

public   class  ApoHelp {
	

	/**
	 * Constructor
	 */
	public ApoHelp() {
	}

	

	/**
	 * find proper translations to keep rotated image correctly displayed
	 * @param at = AffineTransformObject
* 	 *	@param bi = BufferedImage
* 	 *@param degrees = angle
*	 *@return correct AffineTransform
	 */
	private static AffineTransform findTranslation(AffineTransform at, BufferedImage bi, double degrees ) {
		Point2D p2din, p2dout;
		double ytrans, xtrans = 0.0;

		AffineTransform tat = new AffineTransform();

		if(degrees == 180) {
			p2din = new Point2D.Double(0, bi.getHeight());
		} else {
			p2din = new Point2D.Double(0.0, 0.0);
		}

		p2dout = at.transform(p2din, null);

		if(degrees == 270) {
			xtrans = p2dout.getX();
			ytrans = xtrans;
		} else {
			ytrans = p2dout.getY();
			xtrans = ytrans;
		}

		tat.translate(-xtrans, -ytrans);

		return tat;
	}

	
	
	public static void saveData(URL codebase, String name, String value) {
		String parametersAsString = "SET=&name=" + name + "&value=" + value;
		byte[] parameterAsBytes = parametersAsString.getBytes();
		URL url;
		try {
			url = new URL(codebase + "cookies.php");
			URLConnection con = url.openConnection();
			((HttpURLConnection) con).setRequestMethod("GET");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
	
			OutputStream oStream = con.getOutputStream();
			oStream.write(parameterAsBytes);
			oStream.flush();
			// I don't know why this is necessary, but it really seems to be:
			BufferedReader iStream = new BufferedReader(new InputStreamReader(con.getInputStream()));
			iStream.readLine();
			iStream.close();
			oStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static String loadData(URL codebase, String name) {
		String retVal = "";
		String parametersAsString = "GET=&name=" + name;
		byte[] parameterAsBytes = parametersAsString.getBytes();
		URL url;
		try {
			url = new URL(codebase + "cookies.php");

			URLConnection con = url.openConnection();
			((HttpURLConnection) con).setRequestMethod("GET");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
	
			OutputStream oStream = con.getOutputStream();
			oStream.write(parameterAsBytes);
			oStream.flush();
	
			BufferedReader iStream = new BufferedReader(new InputStreamReader(con.getInputStream()));
			retVal = iStream.readLine();
			iStream.close();
			oStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retVal;
	}

	
	
	/**
	  * Get the String residing on the clipboard.
	  *
	  * @return any text found on the Clipboard; if none found, return an empty String.
	  */
	public static String getClipboardContents() {
		String result = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
		if (hasTransferableText) {
			try {
				result = (String)contents.getTransferData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException ex){
				//highly unlikely since we are using a standard DataFlavor
			} catch (IOException ex) {
			}
		}
		return result;
	}

	
	
	/**
     * Place a String on the clipboard
	 * @param string
	 */
	public static void setClipboardContents(String string){
	    StringSelection stringSelection = new StringSelection(string);
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(stringSelection, null);
	  }


}

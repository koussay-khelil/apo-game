package org.apogames.input; 

import java.awt.event.KeyListener; 
import java.awt.event.KeyEvent; 
import java.util.ArrayList; 
import java.util.TreeMap; 

/**
 * Class that takes care of keyboards
 * @author Dirk Aporius
 *
 */
public final  class  ApoKeyboard  implements KeyListener {
	

	/** TreeMap with the keys that are currently being pressed */
	private TreeMap<Integer, Boolean> keys;

	
	/** ArrayList with the keys that have just been released */
	private ArrayList<Integer> releasedKeys;

	
	/** ArrayList with the keys that have just been released */
	private ArrayList<Integer> releasedCharKeys;

	
	/** ArrayList with the keys that are currently being pressed */
	private ArrayList<Integer> pressedKeys;

	

	/**
	 * Constructor
	 */
	public ApoKeyboard() {
		this.keys = new TreeMap<Integer, Boolean>();
		this.releasedKeys = new ArrayList<Integer>();
		this.releasedCharKeys = new ArrayList<Integer>();
		this.pressedKeys = new ArrayList<Integer>();
	}

	
	
	/**
	 * returns whether a given key is currently being pressed or not
	 * @param key: Key to check if pressed
	* @return TRUE, key is pressed, else FALSE, key is not pressed
	 */
	public boolean isPressed(int key) {
		if (this.keys.containsKey(key)) {
			return this.keys.get(key);
		} else {
			return false;
		}
	}

	
	
	/**
	 * returns an array of keys that have just been released
	 * @return returns an array of keys that have just been released
	 */
	public synchronized int[][] getReleased() {
		if ((this.releasedKeys == null) || (this.releasedKeys.size() <= 0)) {
			return new int[0][0];
		}
		int[][] released = new int[this.releasedKeys.size()][2];
		for (int i = 0; i < this.releasedKeys.size(); i++) {
			try {
				released[i][0] = this.releasedKeys.get(i);
				released[i][1] = this.releasedCharKeys.get(i);
			} catch (Exception ex) {
			}
		}
		this.releasedKeys.clear();
		this.releasedCharKeys.clear();
		return released;
	}

	

	/**
	 * returns an array of keys that are being pressed back
	 * @return returns an array of keys that are being pressed
	 */
	public synchronized int[] getPressed() {
		if ((this.pressedKeys == null) || (this.pressedKeys.size() <= 0)) {
			return new int[0];
		}
		int[] pressed = new int[this.pressedKeys.size()];
		for (int i = 0; i < this.pressedKeys.size(); i++) {
			pressed[i] = this.pressedKeys.get(i);
		}
		return pressed;
	}

	

	public void keyPressed(KeyEvent e) {
		this.keys.put(e.getKeyCode(), true);
		if (this.pressedKeys.indexOf(e.getKeyCode()) < 0) {
			this.pressedKeys.add(e.getKeyCode());
		}
	}

	

	public void keyReleased(KeyEvent e) {
		this.keys.put(e.getKeyCode(), false);
		this.releasedCharKeys.add((int)e.getKeyChar());
		this.releasedKeys.add(e.getKeyCode());
		this.pressedKeys.remove((Integer) e.getKeyCode());
	}

	

	public void keyTyped(KeyEvent e) {
		this.keys.put(e.getKeyCode(), false);
		this.releasedCharKeys.add((int)e.getKeyChar());
		this.releasedKeys.add(e.getKeyCode());
		this.pressedKeys.remove((Integer) e.getKeyCode());
	}


}

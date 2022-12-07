package org.apogames.sound; 

import java.io.IOException; 
import java.net.URL; 
import java.sql.Time; 
import java.util.TimeZone; 

import javax.sound.sampled.AudioFormat; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.DataLine; 
import javax.sound.sampled.FloatControl; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.SourceDataLine; 
import javax.sound.sampled.UnsupportedAudioFileException; 

public  class  ApoMP3Sound  implements Runnable {
	
	private Thread runner = new Thread(this);

	 //playing thread
	private static int BufferSize = 1024;

	 // Number of data sent to the sound card at once.
    private static byte[]  buffer = new byte[BufferSize];

	
    private int gainPercent = 90;

	  //indicates the volume in percent. (0% = -80dB and 100% = 6dB) 
	private Boolean stop = false;

	
	private Boolean loopPlay = false;

	
	private URL song = null;

	
	private static long timeZoneKorrektur = TimeZone.getDefault().getOffset(0);

	
	private Time time = new Time(-timeZoneKorrektur);

	
	private Time songTime = new Time(-timeZoneKorrektur);

	
	private int sampleSizeInBits = 0;

	
	private long songLaenge = 0;

	
	private boolean reset = false;

	
	private Boolean isPlaying = false;

	
	private long resetKorrektur = 0;

	
	private boolean pause = false;

	
	private boolean mute = false;

	
	private int lautstaerke = gainPercent;

	

	/**
	 * For mp3 files to be played, the mp3plugin from Sun must be in classpath or
	* (eg under Eclipse) as a library.
	* The same is true for flac files.
	*
	* The volume "gainLevel" is logarithmically from -80dB to about 6dB. Therefore, the
	* Percent volume control is not completely correct. 
	 */
	public ApoMP3Sound() {
		
	}

	
	
	public ApoMP3Sound(String song) {
		this.setSong(song);
	}

	
	
	/**
	 * indicates the current time
	 */
	public Time getCurrentTime() {
		return time;
	}

	



	/**
	 * returns if the music is repeated
	 */
	public boolean isLoopPlay(){
		return loopPlay;
	}

	
	
	/**
	 * starts an infinite loop
	 *
	 */
	public void setLoopPlay(Boolean loop){
		loopPlay = loop;
	}

	
	
	/**
	 * stops playback
	 *
	 */
	public void stop()
	{
		stop=true;
	}

	
	
	/**
	 * start playback
	 *
	 */
	public void play()
	{
			stop=false;
			if (!runner.isAlive())
			{
				runner = new Thread(this);
				runner.start();
			}
	}

	
	
	/**
	 * returns the current volume
	 */
	public int getVolume(){
		return gainPercent;
	}

	
	
	/**
	 * Value between 0% and 100%
	 * @param volumen
	 */
	public void setVolumen(int volumen){
		if ((volumen <= 100) || (volumen >= 0)){
			gainPercent = volumen;
		}
		
	}

	
	
	
	/**
	 * the playback thread
	 */
	public void run() 
	{
		do{
		try {
			resetKorrektur = 0;
			AudioInputStream in = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED, AudioSystem.getAudioInputStream(song));
			AudioFormat audioFormat = in.getFormat();
			SourceDataLine line = (SourceDataLine) AudioSystem.getLine(new DataLine.Info(SourceDataLine.class, audioFormat));
		    line.open(audioFormat);
		    FloatControl gainControl = (FloatControl)line.getControl(FloatControl.Type.MASTER_GAIN);
		    line.start();
		    //songLaenge = song.length();
		    sampleSizeInBits = audioFormat.getSampleSizeInBits();	
		    
		    if (in.getFrameLength() == -1){
		    	songTime.setTime(songLaenge/sampleSizeInBits - timeZoneKorrektur);
		    }
		    else{
		    	songTime.setTime((in.getFrameLength()/(long)audioFormat.getFrameRate())*1000 - timeZoneKorrektur);
		    }
		    
		    in.mark(in.available());
		    while ((true)&&(!stop)){
		    	isPlaying = true;
		    	int gainLevel= (int) ((int)gainControl.getMinimum()+((gainControl.getMaximum()-gainControl.getMinimum())/100*gainPercent));
		    	gainControl.setValue(gainLevel);
		    	if (!pause){
			    	int n = in.read(buffer, 0, buffer.length);
			    	if ((n < 0)||(stop)) {
			        	break;
			        }
			    	if (reset) {
			    		resetKorrektur =  line.getMicrosecondPosition()/1000;
			    		in.reset();
			    		reset = false;
			    	}
			    	time.setTime(line.getMicrosecondPosition()/1000 - timeZoneKorrektur - resetKorrektur);
			    	line.write(buffer, 0, n);
		    	}
		      }
		      line.drain();
		      line.close();
		      in.close();
			} catch (UnsupportedAudioFileException e) {
			    System.out.println("nicht unterst�tztes Format");
			} catch (IOException e) {
				System.out.println("Datei nicht gefunden" + e );
			} catch (LineUnavailableException e) {
				System.out.println("Soundkartenfehler");
			}
		}while (loopPlay && !stop);
		isPlaying = false;
	}

	
	
	/**
	 * Name and path of the sound file
	 * @param song
	 */
	public void setSong(String song) {
		this.song = this.getClass().getResource(song);
		
	}

	

	/** 
	 * Name and path of the sound file
	 * @return
	 */
	public URL getSong() {
		return song;
	}

	

	/**
	 * the total length of the piece of music
	 * @return
	 */
	public Time getSongTime() {
		return songTime;
	}

	

	/**
	 * Pause title
	 * @param pause
	 */
	public void setPause(boolean pause) {
		this.pause = pause;
	}

	


	/**
	 * returns if the title pauses
	 * @return
	 */
	public boolean isPause() {
		return pause;
	}

	


	/**
	 * mutes the playback
	 * @param mute
	 */
	public void setMute(boolean mute) {
		if ((mute) &&(!this.mute)){
			lautstaerke = this.getVolume();
			this.setVolumen(0);
		}else{
			this.setVolumen(lautstaerke);
		}
		this.mute = mute;
	}

	


	/**
	 * Returns whether the playback is muted
	 * @return
	 */
	public boolean isMute() {
		return mute;
	}


}

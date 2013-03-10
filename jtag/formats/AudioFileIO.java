package jtag.formats;

import java.io.File;
import java.util.Hashtable;

import jtag.formats.wav.WavFileReader;

/** <p>Example of use:</p>
 * <p>
 * <code>
 *		AudioFile audioFile = AudioFileIO.read(new File("audiofile.mp3")); //Reads the given file.<br/>
 *		int bitrate = audioFile.getBitrate(); //Retreives the bitrate of the file.<br/>
 *		String artist = audioFile.getTag().getArtist(); //Retreive the artist name.<br/>
 *		audioFile.getTag().setGenre("Progressive Rock"); //Sets the genre to Prog. Rock, note the file on disk is still unmodified.<br/>
 *		AudioFileIO.write(audioFile); //Write the modifications in the file on disk.
 *	</code>
 * </p>
 * <p>
 * <code>
 * 		AudioFileIO reader = new AudioFileIO(true); // Cache on
 *		AudioFile audioFile = reader.readFile(new File("audiofile.mp3"));<br/>
 *		String artist = audioFile.getTag().getArtist(); //Retreive the artist name.<br/>
 *	</code>
 * </p> 
 */
public class AudioFileIO {
	private boolean cacheReader = false;
	private Hashtable<String, AudioFileReader> readers = new Hashtable<String, AudioFileReader>();
	private static AudioFileIO defaultInstance;

	/** @return the default instance for static use. */
	public static AudioFileIO getDefaultAudioFileIO() {
		if (defaultInstance == null) defaultInstance = new AudioFileIO(true);
		return defaultInstance;
	}

	/** <p>Read the tag contained in the given file.</p>
	 * @param f - The file to read.
	 * @return The AudioFile with the file tag and the file encoding info.
	 * @throws Exception */
	public static AudioFile read(File f) throws Exception { return new AudioFileIO().readFile(f); }

	public AudioFileIO() { }
	public AudioFileIO(boolean cache) { cacheReader = cache; }

	/** Read the tag contained in the given file.
	 * @param f - the file to read.
	 * @return The AudioFile with the file tag and the file encoding info.
	 * @throws Exception */
	public AudioFile readFile(File f) throws Exception {
		String ext = Utils.getExtension(f);
		AudioFileReader reader = null;
		
		if (cacheReader)
			if (readers.contains(ext))
				reader = (AudioFileReader)readers.get(ext);

		if (reader == null)
			switch (ext) {
//				case "mp3":	readers.put(ext, (reader = new Mp3FileReader()));	break;
//				case "mp4":
//				case "m4a":
//				case "m4p":
//				case "m4b":	
//					readers.put(ext, (reader = new Mp4FileReader()));	break;
//				case "ogg": readers.put(ext, (reader = new OggFileReader()));	break;
//				case "flac": readers.put(ext, (reader = new FlacFileReader()));	break; 
//				case "ape": readers.put(ext, (reader = new MonkeyFileReader()));	break;
				case "wav": readers.put(ext, (reader = new WavFileReader()));	break;
//				case "wv": readers.put(ext, (reader = new WvFileReader()));	break;
//				case "wma": readers.put(ext, (reader = new AsfFileReader()));	break;
//				case "mpc": //not tested
//				case "mp+": //not tested
//				case "mpp": //not tested
//					readers.put(ext, (reader = new MpcFileReader()));	break;
//				case "ra" : 
//				case "rm" :
//				case "rmvb" : //not tested						
//					readers.put(ext, (reader = new RealFileReader()));	break;
//				case "ram" : //not support yet					
				default: throw new Exception("No Reader associated to this extension: " + ext); 
			}

		return reader.read(f);
	}
}
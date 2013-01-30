package outag.formats.real.io;

import java.io.RandomAccessFile;

import outag.formats.generic.Utils;
import outag.formats.real.utils.AudioInfo;

/** MDPR chunk */
public class MediaPropertiesChunk extends GenericChunk {
	int streamId;
	long maxBitrate;
	long averageBitrate;
	long maxDataPacketSize;
	long averageDataPacketSize;
	long streamStartInMs;
	/** Preroll in ms (to be subtracted from timestamps?) */
	long prerollInMs;
	/** stream duration */
	long duration; // in ms
	String streamDescription;
	/**
	 * Possible :
	 * <ul>
	 * 	<li>audio/x-pn-realaudio</li>
	 * 	<li>audio/x-pn-multirate-realaudio</li>
	 * 	<li>audio/X-MP3-draft-00</li>
	 * 	<li>audio/x-ralf-mpeg4</li>
	 * </ul>
	 *  */
	String mimeType; 
	AudioInfo typeSpecificData;	
	
	
//	dword   Chunk type ('MDPR')
//	dword   Chunk size
//	word    Chunk version (always 0, for every known file)
//	word    Stream number
//	dword   Maximum bit rate
//	dword   Average bit rate
//	dword   Size of largest data packet
//	dword   Average size of data packet
//	dword   Stream start offset in ms
//	dword   Preroll in ms (to be subtracted from timestamps?)
//	dword   Stream duration in ms
//	byte    Size of stream description string
//	byte[]  Stream description string
//	byte    Size of stream mime type string
//	byte[]  Mime type string
//	dword   Size of type specific part of the header
//	byte[]  Type specific data, meaning and format depends on mime type	
	
	public MediaPropertiesChunk(RandomAccessFile f) throws Exception {
		super(f, "MDPR");
	
		streamId = Utils.readUint16(f);
		maxBitrate = Utils.readUint32(f);
		averageBitrate = Utils.readUint32(f);
		maxDataPacketSize = Utils.readUint32(f);
		averageDataPacketSize = Utils.readUint32(f);
		streamStartInMs = Utils.readUint32(f);
		prerollInMs  = Utils.readUint32(f);
		duration = Utils.readUint32(f);
//		byte    Size of stream description string
		streamDescription = Utils.readString(f, f.read());
//		byte    Size of stream mime type string
		mimeType = Utils.readString(f, f.read());
		
		int dataLength = (int)Utils.readUint32(f);
//		if (dataLength == 0 && mimeType equal "audio/X-MP3-draft-00") return; // no extra info
//		if (mymeType equal "audio/x-ralf-mpeg4") return; //dont know how parse info
		typeSpecificData = new AudioInfo(f, dataLength);
	}
}
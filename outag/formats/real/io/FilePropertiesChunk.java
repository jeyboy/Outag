package outag.formats.real.io;

import java.io.RandomAccessFile;

import outag.formats.generic.Utils;

/** PROP chunk */
public class FilePropertiesChunk extends GenericChunk {
	long maxBitrate;
	long averageBitrate;
	long maxDataPacketSize;
	long averageDataPacketSize;
	long dataPacketsCount;
	/** file common duration */
	long duration; //in ms
	long playBufferInMs;
	/** Offset of the first INDX chunk form the start of the file */
	long INDX_chunkOffset;
	
	/** Offset of the first DATA chunk form the start of the file */
	long DATA_chunkOffset;
	int streamCount;
	
	/**
	 * <ol start="0">
	 *   <li>bit : file can be saved on disk</li>
	 *   <li>bit : PerfectPlay can be used (extra buffering)</li>
	 *   <li>bit : the file is a live broadcast </li>
	 *  </ol>
	 * */
	int flags;
	
//	dword  Chunk type ('PROP')
//	dword  Chunk size (typically 0x32)
//	word   Chunk version (always 0, for every known file)
//	dword  Maximum bit rate
//	dword  Average bit rate
//	dword  Size of largest data packet
//	dword  Average size of data packet
//	dword  Number of data packets in the file
//	dword  File duration in ms
//	dword  Suggested number of ms to buffer before starting playback
//	dword  Offset of the first INDX chunk form the start of the file
//	dword  Offset of the first DATA chunk form the start of the file
//	word   Number of streams in the file
//	word   Flags (bitfield, see below)	
	
	public FilePropertiesChunk(RandomAccessFile f) throws Exception {
		super(f, "PROP");
	
		maxBitrate = Utils.readUint32(f);
		averageBitrate = Utils.readUint32(f);
		maxDataPacketSize = Utils.readUint32(f);
		averageDataPacketSize = Utils.readUint32(f);
		dataPacketsCount = Utils.readUint32(f);
		duration = Utils.readUint32(f);
		playBufferInMs = Utils.readUint32(f);
		INDX_chunkOffset = Utils.readUint32(f);
		DATA_chunkOffset = Utils.readUint32(f);
		streamCount = Utils.readUint16(f);
		flags = Utils.readUint16(f);		
	}
}
package outag.formats.real.io;

import java.io.RandomAccessFile;
import java.util.Vector;

import outag.formats.generic.Utils;
import outag.formats.real.utils.DataPacket;

/** .RMF chunk */
public class DataChunk extends GenericChunk {
	/** Offset of the next DATA chunk (form the start of the file) */
	long nextDataChunkOffset = -1;
	Vector<DataPacket> dataPackets;

	
//	dword   Chunk type ('DATA')
//	dword   Chunk size
//	word    Chunk version (always 0, for every known file)
//	dword   Number of data packets in this chunk
//	dword   Offset of the next DATA chunk (form the start of the file)
//	byte[]  Data packets
	
	public DataChunk(RandomAccessFile f) throws Exception {
		super(f, ".DATA");
		
//		long dataPacketsNum = Utils.readUint32(f);
		int dataPacketsNum = (int)f.readLong();
		dataPackets = new Vector<DataPacket>(dataPacketsNum);
		nextDataChunkOffset = Utils.readUint32(f);
		
		for(int packetNum = 0; packetNum < dataPacketsNum; packetNum++)
			dataPackets.add(new DataPacket(f));
	}
}
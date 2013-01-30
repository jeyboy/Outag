package outag.formats.real.utils;

import java.io.RandomAccessFile;

public class IndexEntry {
//	 word   Entry version (always 0, for every known file)
//	 dword  Timestamp (in ms)
//	 dword  Packet offset in file (form the start of the file)
//	 dword  Packet number
	
	long timestamp;
	/** Packet offset in file (form the start of the file) */
	long packetOffset;
	long packetSequenceNumber;
	
	public IndexEntry(RandomAccessFile f) throws Exception {
		f.readInt(); // version
		timestamp = f.readLong();
		packetOffset = f.readLong();
		packetSequenceNumber = f.readLong();
	}
}
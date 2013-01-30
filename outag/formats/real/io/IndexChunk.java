package outag.formats.real.io;

import java.io.RandomAccessFile;
import java.util.Vector;

import outag.formats.generic.Utils;
import outag.formats.real.utils.IndexEntry;

/** INDX chunk. Contains index entries. It comes after all the DATA chunks. 
 * An index chunk contains data for a single stream, A file can have more than one INDX chunk. */
public class IndexChunk extends GenericChunk {
	
//	dword   Number of entries in this chunk
//	word    Stream number
//	dword   Offset of the next INDX chunk (form the start of the file)
//	byte[]  Index entries
	
	/** Offset of the next INDX chunk (form the start of the file) */
	long nextIndexChunkOffset;
	Vector<IndexEntry> entries;
	int streamID;
	
	public IndexChunk(RandomAccessFile f) throws Exception {
		super(f, "INDX");
		
		int entriesCount = (int)f.readLong();
		entries = new Vector<IndexEntry>(entriesCount);
		streamID = f.readInt();
		nextIndexChunkOffset = f.readLong();
		for(int loop = 0; loop < entriesCount; loop++)
			entries.add(new IndexEntry(f));
	}
}
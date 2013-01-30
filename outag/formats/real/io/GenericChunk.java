package outag.formats.real.io;

import java.io.RandomAccessFile;

import outag.formats.exceptions.UnsupportedException;
import outag.formats.generic.Utils;

/** Common chunk */
public class GenericChunk {
	protected long obj_id;
	protected long size;
	protected int obj_version;
	
	public GenericChunk(RandomAccessFile f, String id) throws Exception {
		obj_id = Utils.readUint32(f);
//		if (!obj_id.equals(id))
//			throw new InvalidHeaderException("Chunk ID wrong. Wait " + id + " - get " + obj_id);
		
//		if ((obj_version != 0) || (obj_version != 1))
//			throw new UnsupportedException("Unsupported header");		
		
		size = Utils.readUint32(f);
		obj_version = Utils.readUint16(f);
	}
}
package outag.formats.real.io;

import java.io.RandomAccessFile;

import outag.formats.generic.Utils;

/** .RMF chunk */
public class FileHeaderChunk extends GenericChunk {
	long file_version = -1;
	long num_headers = -1;
	
//	dword chunk type ('.RMF')
//	dword chunk size (typically 0x12)
//	word  chunk version (always 0, for every known file)
//	dword file version
//	dword number of headers	
	
	public FileHeaderChunk(RandomAccessFile f) throws Exception {
		super(f, ".RMF");
		
		file_version = Utils.readUint32(f);
		num_headers = Utils.readUint32(f);			
	}
}
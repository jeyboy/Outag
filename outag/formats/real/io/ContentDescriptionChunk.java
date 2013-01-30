package outag.formats.real.io;

import java.io.RandomAccessFile;

import outag.formats.real.RealTag;

/** CONT chunk */
public class ContentDescriptionChunk extends GenericChunk {
	RealTag tag;
	
//	dword   Chunk type ('CONT')
//	dword   Chunk size
//	word    Chunk version (always 0, for every known file)
//	word    Title string length
//	byte[]  Title string
//	word    Author string length
//	byte[]  Author string
//	word    Copyright string length
//	byte[]  Copyright string
//	word    Comment string length
//	byte[]  Comment string	
	
	public ContentDescriptionChunk(RandomAccessFile f) throws Exception {
		super(f, "CONT");
		
		byte [] buffer;
		tag = new RealTag();
		
		buffer = new byte[f.readInt()]; f.read(buffer);
		tag.addTitle(new String(buffer));
		
		buffer = new byte[f.readInt()]; f.read(buffer);
		tag.addArtist(new String(buffer));
		
		buffer = new byte[f.readInt()]; f.read(buffer);
		tag.setCopyright(new String(buffer));
		
		buffer = new byte[f.readInt()]; f.read(buffer);
		tag.addComment(new String(buffer));		
	}
}
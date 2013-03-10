package jtag.formats;

import java.io.File;
import java.io.IOException;

import jtag.io.JBFile;

/* This is the skeleton for tag readers. It handles the creation/closing of
 * the random access file objects and then call the subclass method getEncodingInfo and getTag.
 * These two method have to be implemented in the subclass. */
public abstract class AudioFileReader {
	/* Returns the encoding info object associated with the current File.
	 * The subclass can assume the RAF pointer is at the first byte of the file.
	 * The RandomAccessFile must be kept open after this function, but can point
	 * at any offset in the file.
	 * 
	 * @param raf - The RandomAccessFile associated with the current file
	 * @exception IOException is thrown when the RandomAccessFile operations throw it (you should never throw them manually)
	 * @exception CannotReadException when an error occurred during the parsing of the encoding info */
	protected abstract EncodingInfo getEncodingInfo(JBFile p) throws IOException, Exception;
	
	/* Same as above but returns the Tag contained in the file, or a new one.
	 * 
	 * @param raf - The RandomAccessFile associated with the current file
	 * @exception IOException is thrown when the RandomAccessFile operations throw it (you should never throw them manually)
	 * @exception CannotReadException when an error occurred during the parsing of the tag */
	protected abstract Tag getTag(JBFile p) throws IOException, Exception;
	
	/* Reads the given file, and return an AudioFile object containing the Tag
	 * and the encoding info present in the file. If the file has no tag, an
	 * empty one is returned. If the encoding info is not valid , an error is thrown.
	 * 
	 * @param f - The file to read
	 * @exception CannotReadException If anything went bad during the read of this file */
	public AudioFile read(File f) throws Exception {
		if (!f.canRead())
			throw new Exception("Can't read file \""+f.getAbsolutePath()+"\"");
		
		if(f.length() <= 150)
			throw new Exception("Less than 150 byte \""+f.getAbsolutePath()+"\"");
		
		JBFile p = null;
		try{
			p = new JBFile(f);			
			EncodingInfo info = getEncodingInfo(p);
		
			Tag tag;
			try {
				p.pos(0);
				tag = getTag(p);
			} 
			catch (Exception e) {
				System.err.println(e.getMessage());
				tag = new Tag();
			}
		
			return new AudioFile(f, info, tag);
		} 
		catch (Exception e) { throw new Exception("\"" + f + "\" :" + e, e);	}
		finally {
			try{ if (p != null) p.close(); }
			catch(Exception ex) { System.err.println("\""+f+"\" :"+ex); }
		}
	}
}
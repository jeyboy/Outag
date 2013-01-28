package outag.formats.ogg;

import outag.formats.*;
import outag.formats.exceptions.*;
import outag.formats.generic.AudioFileReader;
import outag.formats.ogg.util.*;

import java.io.*;

public class OggFileReader extends AudioFileReader {
	private OggInfoReader ir = new OggInfoReader();
	private VorbisTagReader otr = new VorbisTagReader();
	
	protected EncodingInfo getEncodingInfo( RandomAccessFile raf )  throws CannotReadException, IOException {
		return ir.read(raf);
	}
	
	protected Tag getTag( RandomAccessFile raf )  throws CannotReadException, IOException {
		return otr.read(raf);
	}
}
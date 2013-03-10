package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;

public class NoteChunk extends RIFFChunk {
	public NoteChunk(Parseable p) throws IOException { super(p); }
	
	public void read(Parseable p) throws IOException {
		p.UInt(); // CUE Point ID
		p.skip(length - 4); // skip note
		//The note is a null terminated string of characters. If the number of characters in the string is not even,
		//padding must be appended to the string. The appended padding is not considered in the label chunk's chunk size field.
	}
}
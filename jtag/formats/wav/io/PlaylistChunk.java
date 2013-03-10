package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;

public class PlaylistChunk extends RIFFChunk {
	public PlaylistChunk(Parseable p) throws IOException { super(p); }
	
	int segmentsNum;
	
	public void read(Parseable p) throws IOException {
		segmentsNum = p.UInt(); //Number of Segments
		p.skip(length - 4); // skip List of Segments
	}
	
	public boolean isValidLength() {return length == segmentsNum * 12; } // length == segmentsNum * 12
}
package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;

public class FactChunk extends RIFFChunk {
	public FactChunk(Parseable p) throws IOException { super(p); }
	
	public void read(Parseable p) throws IOException {
		p.skip(length); // skip all compression code dependant information		
	}
}
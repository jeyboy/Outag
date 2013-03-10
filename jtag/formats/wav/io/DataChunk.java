package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;

public class DataChunk extends RIFFChunk {
	public DataChunk(Parseable p) throws IOException { super(p); }
	
	public void read(Parseable p) throws IOException {
		p.skip(length); // skip all digital audio sample data
	}
}
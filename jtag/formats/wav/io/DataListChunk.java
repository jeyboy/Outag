package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;

public class DataListChunk extends RIFFChunk {
	public DataListChunk(Parseable p) throws IOException { super(p); }
	
	public void read(Parseable p) throws IOException {
		p.Str(4).equals("adtl");
		p.skip(length - 4); // skip List of Text Labels and Names
	}
}
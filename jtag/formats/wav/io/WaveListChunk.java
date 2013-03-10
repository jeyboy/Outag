package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;

public class WaveListChunk extends RIFFChunk {
	public WaveListChunk(Parseable p) throws IOException { super(p); }
	
	public void read(Parseable p) throws IOException {
		p.skip(length); // skip List of Alternating "slnt" and "data" Chunks
	}
}
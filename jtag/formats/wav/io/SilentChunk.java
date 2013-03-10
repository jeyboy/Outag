package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;

public class SilentChunk extends RIFFChunk {
	public SilentChunk(Parseable p) throws IOException { super(p); }
	
	public void read(Parseable p) throws IOException {
		p.skip(length); // skip Number of Silent Samples (UINT)
	}
	
	public boolean isValidLength() {return length == 4; }
}
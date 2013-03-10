package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;

public abstract class RIFFChunk {
	public int length;
	
	public RIFFChunk(Parseable p) throws IOException {
		length = p.UInt();
		read(p);
	}
	
	public abstract void read(Parseable p) throws IOException;
}
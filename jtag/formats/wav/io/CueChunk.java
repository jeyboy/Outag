package jtag.formats.wav.io;

import java.io.IOException;

import jtag.io.Parseable;

public class CueChunk extends RIFFChunk {
	public CueChunk(Parseable p) throws IOException { super(p); }
	int cuePointsNum;
	
	public void read(Parseable p) throws IOException {
		cuePointsNum = p.UInt();
		p.skip(length - 4); // skip List of Cue Points
	}
	
	public boolean isValidLength() {return length == cuePointsNum * 24; }
}
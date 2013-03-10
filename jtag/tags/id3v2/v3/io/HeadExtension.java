package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;

public class HeadExtension {
	String ID;
	public int length;
	public int CRC;
	
	public HeadExtension(Parseable p) throws IOException {
		length = p.UInt();
		short flags = p.UShort();
		if (Parseable.getBit(flags, 15) == 1)
			CRC = p.UInt();
		int padding = p.UInt();
		p.skip(padding);
	}	
}
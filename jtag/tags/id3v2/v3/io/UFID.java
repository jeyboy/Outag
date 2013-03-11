package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.io.Encodings;
import jtag.tags.id3v2.v3.io.base.TextFrame;

public class UFID extends TextFrame {
	public String owner;
	public String id;
	
	public UFID(Parseable p) throws IOException {
		super(p);
		String [] vals = info.split("" + Encodings.nil);
		owner = vals[0]; id = vals[1];
	}
}
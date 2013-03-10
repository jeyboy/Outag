package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;

public class TCM extends T__ {	
	public TCM(Parseable p) throws IOException { super(p); }
	
	public String [] peoples() { return info.split("/"); }
}
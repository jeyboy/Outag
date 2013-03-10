package jtag.tags.id3v2.v2.io.base;

import java.io.IOException;

import jtag.io.Parseable;

public class Frame {
	public int length;
	
	public Frame(Parseable p) throws IOException { length = p.UBEInt24(); }
}
package jtag.tags.id3v2.v2.io.base;

import java.io.IOException;

import jtag.io.Parseable;

public class TextFrame extends Frame {
	public String info;
	
	public TextFrame(Parseable p) throws IOException {
		super(p);
		info = p.Str(length);
	}
}
package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.TextFrame;

/**  URL link frame   "W00" - "WZZ" , excluding "WXX"  */
public class W__ extends TextFrame {	
	public W__(Parseable p) throws IOException { super(p); }
}
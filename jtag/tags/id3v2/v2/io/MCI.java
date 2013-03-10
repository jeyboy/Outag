package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.TextFrame;

// info must execute as binary data
public class MCI extends TextFrame {
	public MCI(Parseable p) throws IOException { super(p); }
}
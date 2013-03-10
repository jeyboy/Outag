package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.ETextFrame;

public class TLA extends ETextFrame {
	// encoding 'ISO-639-2'
	public TLA(Parseable p) throws IOException { super(p); }
}
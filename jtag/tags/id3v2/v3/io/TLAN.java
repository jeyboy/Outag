package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v3.io.base.ETextFrame;

public class TLAN extends ETextFrame {
	// encoding 'ISO-639-2'
	public TLAN(Parseable p) throws IOException { super(p); }
}
package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.ETextFrame;

public class IPL extends ETextFrame {
	public IPL(Parseable p) throws IOException { super(p); }
}
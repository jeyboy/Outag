package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.ETextFrame;

/**  Text information identifier  "T00" - "TZZ" , excluding "TXX" */
public class T__ extends ETextFrame {
	public T__(Parseable p) throws IOException { super(p); }
}
package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;

public class TDA extends T__ {
	public int day;
	public int month;
	
	public TDA(Parseable p) throws IOException {
		super(p);
		day = Integer.getInteger(info.substring(0, 1));
		month = Integer.getInteger(info.substring(2, 3));
	}
}
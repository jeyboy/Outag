package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;

public class TIM extends T__ {
	public int hours;
	public int minutes;
	
	public TIM(Parseable p) throws IOException {
		super(p);
		hours = Integer.getInteger(info.substring(0, 1));
		minutes = Integer.getInteger(info.substring(2, 3));
	}
}
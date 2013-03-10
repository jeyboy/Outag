package jtag.tags.id3v2.v2.io.base;

import java.io.IOException;

import jtag.io.Parseable;

public class ETextFrame extends EFrame {
	public String info;
	
	public ETextFrame(Parseable p) throws IOException {
		super(p);
		info = p.Str(length - 1, encInfo.name);
	}
}
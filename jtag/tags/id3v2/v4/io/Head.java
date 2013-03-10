package jtag.tags.id3v2.v4.io;

import java.io.IOException;

import jtag.io.Parseable;

public class Head {
	public int length;
	public boolean unsynchronised;
	public boolean extended;
	public boolean has_footer;
	public HeadExtension ext_head;
	
	public Head(Parseable p) throws IOException {
		byte flag = p.UByte();
		
		unsynchronised = Parseable.getBit(flag, 7) == 1;
		extended = Parseable.getBit(flag, 6) == 1;
		has_footer = Parseable.getBit(flag, 4) == 1;
		
		length = p.BESynchSafeInt();
		
		if (extended)
			ext_head = new HeadExtension(p);
	}
}
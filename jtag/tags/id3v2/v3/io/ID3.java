package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;

public class ID3 {
	public int length;
	public boolean unsynchronised;
	public boolean extended;
	public HeadExtension ext_head;
	
	public ID3(Parseable p) throws IOException {
		byte flag = p.UByte();
		
		unsynchronised = Parseable.getBit(flag, 7) == 1;
		extended = Parseable.getBit(flag, 6) == 1;
		
		length = p.BESynchSafeInt();
		
		if (extended)
			ext_head = new HeadExtension(p);
	}
}
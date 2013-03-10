package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;

public class ID3 {
	public int length;
	boolean compressed;
	boolean unsynchronised;
	
	public ID3(Parseable p) throws IOException {
		byte flag = p.Byte();
		
		unsynchronised = Parseable.getBit(flag, 7) == 1;
		compressed = Parseable.getBit(flag, 6) == 1;
		
		length = p.BESynchSafeInt();
	}
}
package jtag.tags.id3v2.v3.io;

import java.io.IOException;
import jtag.tags.id3v2.io.Encodings;
import jtag.io.Parseable;

public class EFrame extends Frame {
	Encodings.EncodeInfo encoding;
	
	public EFrame(Parseable p) throws IOException {
		super(p);
		encoding = Encodings.getEncoding(p.UByte());
	}
}
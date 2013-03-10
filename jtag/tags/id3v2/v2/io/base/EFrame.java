package jtag.tags.id3v2.v2.io.base;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.io.Encodings;

public class EFrame extends Frame {
	public Encodings.EncodeInfo encInfo;
	
	public EFrame(Parseable p) throws IOException {
		super(p);
		encInfo = Encodings.getEncoding(p.UByte());
	}
}
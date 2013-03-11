package jtag.tags.id3v2.v3.io.base;

import java.io.IOException;
import jtag.tags.id3v2.io.Encodings;
import jtag.io.Parseable;

public class EFrame extends Base {
	public Encodings.EncodeInfo encInfo;
	
	public EFrame(Parseable p) throws IOException {
		super(p);
		encInfo = Encodings.getEncoding(p.UByte());
	}
}
package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.EFrame;

public class COM extends EFrame {
	public String preview;
	public String fullText;
	
	public COM(Parseable p) throws IOException { 
		super(p);		
		int language = p.UBEInt24();

		String content = p.Str(length - 4, encInfo.name);
		String [] vals = content.split(encInfo.null_terminate);
		preview = vals[0]; fullText = vals[1];
	}
}
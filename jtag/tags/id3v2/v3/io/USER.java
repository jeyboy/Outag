package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v3.io.base.EFrame;

public class USER extends EFrame {	
	public String text;
	
	public USER(Parseable p) throws IOException { 
		super(p);
		int language = p.UBEInt24();
	
		text = p.Str(length - 4, encInfo.name);
		
//		This frame contains a brief description of the terms of use and ownership
//		of the file. More detailed information concerning the legal terms might be
//		available through the "WCOP" frame. Newlines are allowed in the text. There may only be one "USER" frame in a tag. 
	}
}
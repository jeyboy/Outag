package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.Frame;

public class STC extends Frame {	
	public STC(Parseable p) throws IOException { 
		super(p);
		byte type = p.UByte();
//			$01  Absolute time, 32 bit sized, using MPEG [MPEG] frames as unit
//			$02  Absolute time, 32 bit sized, using milliseconds as unit

		// must interpret how binary data
		p.Str(length - 1);
	}
}
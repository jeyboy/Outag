package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.Frame;

public class RVA extends Frame {
	public String preview;
	public String fullText;
	
	public RVA(Parseable p) throws IOException { 
		super(p);
		p.skip(length);
		
//	     Increment/decrement           %000000xx
//	     Bits used for volume descr.   $xx
//	     Relative volume change, right $xx xx (xx ...)
//	     Relative volume change, left  $xx xx (xx ...)
//	     Peak volume right             $xx xx (xx ...)
//	     Peak volume left              $xx xx (xx ...)
//
//	   In the increment/decrement field bit 0 is used to indicate the right
//	   channel and bit 1 is used to indicate the left channel. 1 is
//	   increment and 0 is decrement.
//
//	   The 'bits used for volume description' field is normally $10 (16 bits)
//	   for MPEG 2 layer I, II and III [MPEG] and MPEG 2.5. This value may not
//	   be $00. The volume is always represented with whole bytes, padded in
//	   the beginning (highest bits) when 'bits used for volume description'
//	   is not a multiple of eight.		
	}
}
package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v3.io.base.Base;

public class EQUA extends Base {	
	public EQUA(Parseable p) throws IOException { 
		super(p);
		p.skip(length);
		
//	     Adjustment bits    $xx
//
//	   The 'adjustment bits' field defines the number of bits used for
//	   representation of the adjustment. This is normally $10 (16 bits) for
//	   MPEG 2 layer I, II and III [MPEG] and MPEG 2.5. This value may not be
//	   $00.
//
//	   This is followed by 2 bytes + ('adjustment bits' rounded up to the
//	   nearest byte) for every equalisation band in the following format,
//	   giving a frequency range of 0 - 32767Hz:
//
//	     Increment/decrement   %x (MSB of the Frequency)
//	     Frequency             (lower 15 bits)
//	     Adjustment            $xx (xx ...)
//
//	   The increment/decrement bit is 1 for increment and 0 for decrement.
//	   The equalisation bands should be ordered increasingly with reference
//	   to frequency. All frequencies don't have to be declared. Adjustments
//	   with the value $00 should be omitted. A frequency should only be
//	   described once in the frame.		
	}
}
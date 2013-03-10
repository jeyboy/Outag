package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.Frame;

public class CNT extends Frame {	
	public CNT(Parseable p) throws IOException { 
		super(p);
		p.skip(length);
		
//	     Counter        $xx xx xx xx (xx ...)		

//		   This is simply a counter of the number of times a file has been
//		   played. The value is increased by one every time the file begins to
//		   play. There may only be one "CNT" frame in each tag. When the counter
//		   reaches all one's, one byte is inserted in front of the counter thus
//		   making the counter eight bits bigger.  The counter must be at least
//		   32-bits long to begin with.
	}
}
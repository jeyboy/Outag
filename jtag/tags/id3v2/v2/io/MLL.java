package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.Frame;

public class MLL extends Frame {	
	public MLL(Parseable p) throws IOException { 
		super(p);
		p.skip(length);
		
//	     MPEG frames between reference  $xx xx
//	     Bytes between reference        $xx xx xx
//	     Milliseconds between reference $xx xx xx
//	     Bits for bytes deviation       $xx
//	     Bits for milliseconds dev.     $xx
//
//	   Then for every reference the following data is included;
//
//	     Deviation in bytes         %xxx....
//	     Deviation in milliseconds  %xxx....		
	}
}
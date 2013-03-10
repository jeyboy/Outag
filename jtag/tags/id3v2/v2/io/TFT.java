package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.ETextFrame;

public class TFT extends ETextFrame {
	public TFT(Parseable p) throws IOException {
		super(p);
		
//	     MPG    MPEG Audio
//	       /1     MPEG 2 layer I
//	       /2     MPEG 2 layer II
//	       /3     MPEG 2 layer III
//	       /2.5   MPEG 2.5
//	       /AAC   Advanced audio compression		
//		   ...
	}
}
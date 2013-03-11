package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v3.io.base.ETextFrame;

public class TFLT extends ETextFrame {
	public TFLT(Parseable p) throws IOException {
		super(p);
		
//	     MPG    MPEG Audio
//	       /1     MPEG 2 layer I
//	       /2     MPEG 2 layer II
//	       /3     MPEG 2 layer III
//	       /2.5   MPEG 2.5
//	       /AAC   Advanced audio compression
//			VQF       Transform-domain Weighted Interleave Vector Quantization
//			PCM       Pulse Code Modulated audio
//		   ...
	}
}
package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v3.io.base.TextFrame;

public class ENCR extends TextFrame {
	public String owner;
	public String encData;
	
	public ENCR(Parseable p) throws IOException {
		super(p);
//		Owner identifier    <text string> $00
//		Method symbol       $xx
//		Encryption data     <binary data>
		
//		To identify with which method a frame has been encrypted the encryption method must be registered in the
//		tag with this frame. The 'Owner identifier' is a null-terminated string with a URL containing an email
//		address, or a link to a location where an email address can be found, that belongs to the organisation
//		responsible for this specific encryption method. Questions regarding the encryption method should be sent
//		to the indicated email address. The 'Method symbol' contains a value that is associated with this method
//		throughout the whole tag. Values below $80 are reserved. The 'Method symbol' may optionally be followed by
//		encryption specific data. There may be several "ENCR" frames in a tag but only one containing the same symbol
//		and only one containing the same owner identifier. The method must be used somewhere in the tag. 
//		See section 3.3.1, flag j for more information. 		
	}
}
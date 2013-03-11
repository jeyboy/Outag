package jtag.tags.id3v2.v3.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v3.io.base.TextFrame;

public class PRIV extends TextFrame {
	public String owner;
	public String groupData;
	
	public PRIV(Parseable p) throws IOException {
		super(p);
//		Owner identifier        <text string> $00
//		The private data        <binary data>
////		
//		This frame is used to contain information from a software producer that its program uses and does
//		not fit into the other frames. The frame consists of an 'Owner identifier' string and the binary data.
//		The 'Owner identifier' is a null-terminated string with a URL containing an email address, or a link to
//		a location where an email address can be found, that belongs to the organisation responsible for the frame.
//		Questions regarding the frame should be sent to the indicated email address. The tag may contain more than
//		one "PRIV" frame but only with different contents. It is recommended to keep the number of "PRIV" frames as
//		low as possible. 
	}
}
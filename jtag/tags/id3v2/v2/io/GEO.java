package jtag.tags.id3v2.v2.io;

import java.io.IOException;

import jtag.io.Parseable;
import jtag.tags.id3v2.v2.io.base.EFrame;

public class GEO extends EFrame {	
	public GEO(Parseable p) throws IOException { 
		super(p);
		p.skip(length - 1);
		
//	     MIME type                   <textstring> $00
//	     Filename                    <textstring> $00 (00)
//	     Content description         <textstring> $00 (00)
//	     Encapsulated object         <binary data>		

//		   In this frame any type of file can be encapsulated. After the header,
//		   'Frame size' and 'Encoding' follows 'MIME type' [MIME] and 'Filename'
//		   for the encapsulated object, both represented as terminated strings
//		   encoded with ISO 8859-1 [ISO-8859-1]. The filename is case sensitive.
//		   Then follows a content description as terminated string, encoded as
//		   'Encoding'. The last thing in the frame is the actual object. The
//		   first two strings may be omitted, leaving only their terminations.
//		   MIME type is always an ISO-8859-1 text string. There may be more than
//		   one "GEO" frame in each tag, but only one with the same content
//		   descriptor.
	}
}